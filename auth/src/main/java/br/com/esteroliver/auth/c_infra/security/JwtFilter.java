package br.com.esteroliver.auth.c_infra.security;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.esteroliver.auth.a_domain.model.Usuario;
import br.com.esteroliver.auth.a_domain.repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    JwtTokenService jwtTokenService;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        // Configuração para endpoints privados
        if(endpointPrivado(request)){
            String token = recuperarToken(request);
            if(token != null){
                String subject = jwtTokenService.verificarToken(token);
                Usuario usuario = usuarioRepository.findByEmail(subject).get();
                UserDetailsImpl userDetails = new UserDetailsImpl(usuario);

                Authentication authentication = 
                    new UsernamePasswordAuthenticationToken(
                        userDetails.getUsername(), null, userDetails.getAuthorities()
                    );

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }

    private boolean endpointPrivado(HttpServletRequest request){
        String requestUri = request.getRequestURI();
        return Arrays.stream(SecurityConfiguration.ENDPOINTS_PUBLICOS).noneMatch(requestUri::contains);
    }
    
    private String recuperarToken(HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");
        if(authHeader != null){
            return authHeader.replace("Bearer ", "");
        }
        return null;
    }
}
