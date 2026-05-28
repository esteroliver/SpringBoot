package br.com.esteroliver.auth_keycloak.services;

import br.com.esteroliver.auth_keycloak.config.KeycloakProperties;
import br.com.esteroliver.auth_keycloak.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;
import java.util.Map;

@Service
public class KeycloakAdminService {

    @Autowired
    KeycloakProperties props;
    RestClient restClient = RestClient.create();

    public String criarUsuario(UsuarioRequestDTO request){
        String postUrl = String.format("%s/admin/realms/%s/users", props.getBaseUrl(), props.getRealm());
        String adminToken = getAdminToken();

        List<KeycloakCredentialDTO> credentials = List.of(new KeycloakCredentialDTO(
                "password",
                 request.senha(),
                false
        ));

        KeycloakCreateUserRequestDTO body = new KeycloakCreateUserRequestDTO(
                request.nome() + "." + request.sobrenome(),
                request.email(),
                request.nome(),
                request.sobrenome(),
                true,
                credentials
        );
        try{
            ResponseEntity<Void> response = restClient.post()
                    .uri(postUrl)
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + adminToken)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(body)
                    .retrieve()
                    .toBodilessEntity();

            URI location = response.getHeaders().getLocation();
            return extractUserIdFromLocation(location);
        }
        catch (HttpClientErrorException e){
            if (e.getStatusCode() == HttpStatus.CONFLICT) {
                throw new RuntimeException("Usuario ja existe no Keycloak (409).", e);
            }
            if (e.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                throw new RuntimeException("Admin token rejeitado pelo Keycloak (401).", e);
            }
            throw new RuntimeException("Falha ao criar usuario no Keycloak: HTTP %s".formatted(e.getStatusCode()), e);
        }
    }

    public KeycloakLoginResponseDTO login(LoginRequestDTO request){
        LinkedMultiValueMap<String, String> formData = new LinkedMultiValueMap<>();

        formData.add("grant_type", "password");
        formData.add("client_id", props.getClientId());
        formData.add("client_secret", props.getClientSecret());
        formData.add("username", request.email());
        formData.add("password", request.senha());

        try{
            Map tokens = restClient.post()
                    .uri(props.getBaseUrl() + "/realms/" + props.getRealm() + "/protocol/openid-connect/token")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .body(formData)
                    .retrieve()
                    .body(Map.class);

            if(tokens == null){
                //throw exception
            }

            return new KeycloakLoginResponseDTO(
                    (String) tokens.get("access_token"),
                    (String) tokens.get("refresh_token"),
                    (Number) tokens.get("expires_in"),
                    (String) tokens.get("token_type")
            );
        }
        catch (HttpClientErrorException exc){
            throw new BadCredentialsException("Email ou senha inválidos");
        }
    }

    private String getAdminToken(){
        String postUrl = String.format("%s/realms/%s/protocol/openid-connect/token", props.getBaseUrl(), props.getRealm());

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("grant_type", "password");
        formData.add("client_id", props.getClientId());
        formData.add("client_secret", props.getClientSecret());
        formData.add("username", props.getAdminUsername());
        formData.add("password", props.getAdminPassword());

        try{
            var response = restClient.post()
                    .uri(postUrl)
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .body(formData)
                    .retrieve()
                    .body(Map.class);

           if(response == null){
               throw new RuntimeException("Token ausente.");
           }

           return (String) response.get("access_token");
        }
        catch (HttpClientErrorException exc){
            throw new RuntimeException("Erro ao obter o token do admin do keycloak.");
        }

    }

    private static String extractUserIdFromLocation(URI location) {
        if (location == null) return null;
        String path = location.getPath();
        if (path == null || path.isBlank()) return null;
        int idx = path.lastIndexOf('/');
        if (idx < 0 || idx == path.length() - 1) return null;
        return path.substring(idx + 1);
    }
}
