package br.com.esteroliver.auth.c_infra.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

@Component("auth")
public class AuthorizationRules {

    public boolean usuarioAdministrador(Authentication authentication){
        if(authentication == null || authentication.getAuthorities() == null){
            return false;
        }
        return authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch("ADMINISTRADOR"::equals);
    }

    public boolean usuarioCliente(Authentication authentication){
        if(authentication == null || authentication.getAuthorities() == null){
            return false;
        }
        return authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch("CLIENTE"::equals);
    }

    public boolean usuarioGestor(Authentication authentication){
        if(authentication == null || authentication.getAuthorities() == null){
            return false;
        }
        return authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch("GESTOR"::equals);
    }

}
