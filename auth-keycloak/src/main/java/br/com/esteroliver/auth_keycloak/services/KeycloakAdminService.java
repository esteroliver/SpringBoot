package br.com.esteroliver.auth_keycloak.services;

import br.com.esteroliver.auth_keycloak.config.KeycloakProperties;
import br.com.esteroliver.auth_keycloak.dto.LoginRequestDTO;
import br.com.esteroliver.auth_keycloak.dto.UsuarioRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class KeycloakAdminService {

    private final KeycloakProperties props;
    private final RestClient restClient = RestClient.create();

    public String criarUsuario(UsuarioRequestDTO request){
        String adminToken = getAdminToken();

        var credentials = List.of(Map.of(
                "type", "password",
                "value", request.senha(),
                "temporary", false
        ));

        String name = request.nome();

        Map<String, Object> body = Map.of(
                "username", request.email(),
                "email", request.email(),
                "firstName", name.split(" ")[0],
                "lastName", name.contains(" ") ? name.substring(name.indexOf(" ") + 1) : "",
                "enabled", true,
                "credentials", credentials
        );

        String endpoint = props.getBaseUrl() + "/admin/realms" + props.getRealm() + "/users";

        ResponseEntity<Void> response = restClient.post()
                .uri(endpoint)
                .header("Authorization", "Bearer" + adminToken)
                .contentType(MediaType.APPLICATION_JSON)
                .body(body)
                .retrieve()
                .toBodilessEntity();

        URI location = response.getHeaders().getLocation();

        if(location == null){
            throw new RuntimeException("Keycloak não retornou ID.");
        }

        String path = location.getPath();

        return path.substring(path.lastIndexOf("/") + 1);
    }

    public Map login(LoginRequestDTO request){
        LinkedMultiValueMap<String, String> formData = new LinkedMultiValueMap<>();

        formData.add("grant_type", "password");
        formData.add("client_id", props.getClientId());
        formData.add("client_secret", props.getClientSecret());
        formData.add("username", request.email());
        formData.add("password", request.senha());

        try{
            return restClient.post()
                    .uri(props.getBaseUrl() + "/realms/" + props.getRealm() + "/protocol/openid-connect/token")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .body(formData)
                    .retrieve()
                    .body(Map.class);
        }
        catch (HttpClientErrorException exc){
            throw new BadCredentialsException("Email ou senha inválidos");
        }
    }

    private String getAdminToken(){
        LinkedMultiValueMap<String, String> formData = new LinkedMultiValueMap<>();

        formData.add("grant_type", "password");
        formData.add("client_id", "admin-cli");
        formData.add("username", props.getAdminUsername());
        formData.add("password", props.getAdminPassword());

        var response = restClient.post()
                .uri(props.getBaseUrl() + "/realms/" + props.getRealm() + "/protocol/openid-connect/token")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(formData)
                .retrieve()
                .body(Map.class);

        assert response != null;
        return (String) response.get("access_token");
    }
}
