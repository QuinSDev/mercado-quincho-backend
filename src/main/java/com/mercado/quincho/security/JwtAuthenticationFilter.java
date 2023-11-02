package com.mercado.quincho.security;

import com.mercado.quincho.service.JwtService;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Esta clase es un filtro de autenticación JWT que se encarga de procesar las
 * solicitudes y verificar la presencia de un token JWT en el encabezado de
 * autorización. Si se encuentra un token JWT válido, el filtro permite que
 * la solicitud continúe.
 * 
 * Este filtro se aplica una vez por cada solicitud entrante.
 * 
 * @author QuinSDev
 */
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    
    @Autowired
    private final JwtService jwtService;
    
    private final UserDetailsService userDetailsService;
    
    /**
     * Implementación del método doFilterInternal de OncePerRequestFilter que
     * verifica y procesa el token JWT.
     * 
     * @param request: La solicitud HTTP entrante.
     * @param response: La respuesta HTTP.
     * @param filterChain: Cadena de filtros para procesar la solicitud.
     * @throws ServletException: Si se produce un error duranre el procesamiento
     * de la solicitud.
     * @throws IOException: Si se produce un error de E/S durante el procesamiento.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, 
            HttpServletResponse response, FilterChain filterChain) 
            throws ServletException, IOException {
        
        final String token = getTokenFromRequest(request);
        final String userName;

        if (token == null) {
            /* Si no se encontró un Token JWT, se permite que la solicitud
            continúe sin autenticación. */
            filterChain.doFilter(request, response);
            return;
        }
        
        userName = jwtService.getUsernameFromToken(token);
        
        if (userName != null && 
                SecurityContextHolder.getContext().getAuthentication() == null) 
        {
            UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
            
            if (jwtService.isTokenValid(token, userDetails)) {
                // Crea un token de autenticación con el usuario y sus roles.
                UsernamePasswordAuthenticationToken authToken = 
                        new UsernamePasswordAuthenticationToken(
                                userDetails, 
                                null,
                                userDetails.getAuthorities());
                
                // Agrega detalles adicionales, como la solicitud web al token.
                authToken.setDetails(new WebAuthenticationDetailsSource()
                        .buildDetails(request));
                
                // Estable el token de autenticación en el contexto de seguridad.
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        
        filterChain.doFilter(request, response);
        
    }
    
    /**
     * Obtiene el token JWT del encabezado de autorización de la solicitud.
     * 
     * @param request: La solicitud entrante.
     * @return El token JWT si se encuenta en el encabezado de la autorización,
     * o null si no se encuentra.
     */
    private String getTokenFromRequest(HttpServletRequest request) {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        return null;
    }
    
}
