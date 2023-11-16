package com.mercado.quincho.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Configuración de seguridad de la aplicación. Esta clase define la
 * configuración de seguridad de la aplicación, incluyendo la autorización de
 * rutas, la configuración de filtros de autenticación y la gestión de sesiones.
 *
 * @author QuinSDev
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;

    /**
     * Configura la cadena de filtros de seguridad. Este método establece la
     * cadena de filtros de seguridad que controlan la autentitación y la
     * autorización de las solicitudes entrantes. Configura la desactivación de
     * CSRF, la autorización de rutas, la gestión de sesiones sin estado y la
     * adición de filtros personalizados.
     *
     * @param http: El objeto HttpSecurity que permite configurar la seguridad.
     * @return La cadena de filtros de seguridad configurada.
     * @throws Exception: Si ocurren errores durante la configuración.
     */
    @Bean
    public SecurityFilterChain secutiryFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors().and()
                // Desactiva la protección CSRF (Cross-Site Request Forgery).
                .csrf(csrf
                        -> csrf
                        .disable())
                // Configura la autorización de rutas.
                .authorizeHttpRequests(authRequest
                        -> authRequest
                        /* Permite que las solicitudes a rutas que comienzan 
                        con "/auth", "/quincho/lista-quinchos" y "/quinchos/**"
                        sean accesibles sin autenticación. */
                        .antMatchers("/auth/**").permitAll()
                        .antMatchers("/quincho/lista-quinchos").permitAll()
                        .antMatchers("/quinchos/**").permitAll()
                        // Requiere autenticación para cualquier otra solicitud.
                        .anyRequest().authenticated()
                )
                // Configura la gestión de sesiones sin estado (stateless).
                .sessionManagement(sessionManager
                        -> sessionManager
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // Configura el proveedor de autenticación personalizado.
                .authenticationProvider(authenticationProvider)
                /* Agrega el filtro de autenticación JWT antes del filtro 
                UsernamePasswordAuthenticationFilter. */
                .addFilterBefore(jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
