package br.com.esteroliver.auth_keycloak.services;

import br.com.esteroliver.auth_keycloak.config.KeycloakProperties;
import br.com.esteroliver.auth_keycloak.dto.*;
import br.com.esteroliver.auth_keycloak.exceptions.KeycloakConflictException;
import br.com.esteroliver.auth_keycloak.exceptions.KeycloakIntegrationException;
import br.com.esteroliver.auth_keycloak.exceptions.KeycloakUnauthorizedException;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
        String adminToken = adminToken();

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
            return extrairIdKeycloakDoUsuario(location);
        }
        catch (HttpClientErrorException e){
            if (e.getStatusCode() == HttpStatus.CONFLICT) {
                throw new KeycloakConflictException("Conflito entre os dados para criação do usuário (409): " + e.getMessage());
            }
            if (e.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                throw new KeycloakUnauthorizedException("Admin token rejeitado pelo Keycloak (401): " + e.getMessage());
            }
            throw new KeycloakIntegrationException("Falha ao criar usuario no Keycloak (%s): %s"
                    .formatted(e.getStatusCode(), e.getMessage())
            );
        }
    }

    public KeycloakLoginResponseDTO login(LoginRequestDTO request){
        var tokens = loginGenerico(request);
        return new KeycloakLoginResponseDTO(
                (String) tokens.get("access_token"),
                (String) tokens.get("refresh_token"),
                (Number) tokens.get("expires_in"),
                (String) tokens.get("token_type")
        );
    }

    private String adminToken(){
        var tokens = loginGenerico(null);
        return (String) tokens.get("access_token");
    }

    private Map loginGenerico(LoginRequestDTO request){
        String postUrl = String.format("%s/realms/%s/protocol/openid-connect/token", props.getBaseUrl(), props.getRealm());
        MultiValueMap<String, String> formData = criarFormDataParaLogin(request);

        try {
            var tokens = restClient.post()
                    .uri(postUrl)
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .body(formData)
                    .retrieve()
                    .body(Map.class);

            if (tokens == null || tokens.get("access_token") == null) {
                throw new KeycloakIntegrationException("Token ausente.");
            }

            return tokens;
        }
        catch (HttpClientErrorException e){
            throw new KeycloakUnauthorizedException("E-mail ou senha incorretos: ");
        }
    }

    private @NonNull MultiValueMap<String, String> criarFormDataParaLogin(LoginRequestDTO request) {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();

        if(request == null){
            formData.add("grant_type", "password");
            formData.add("client_id", props.getClientId());
            formData.add("client_secret", props.getClientSecret());
            formData.add("username", props.getAdminUsername());
            formData.add("password", props.getAdminPassword());
        }
        else{
            formData.add("grant_type", "password");
            formData.add("client_id", props.getClientId());
            formData.add("client_secret", props.getClientSecret());
            formData.add("username", request.email());
            formData.add("password", request.senha());
        }
        return formData;
    }

    private static String extrairIdKeycloakDoUsuario(URI location) {
        if (location == null) return null;
        String path = location.getPath();
        if (path == null || path.isBlank()) return null;
        int idx = path.lastIndexOf('/');
        if (idx < 0 || idx == path.length() - 1) return null;
        return path.substring(idx + 1);
    }
}
