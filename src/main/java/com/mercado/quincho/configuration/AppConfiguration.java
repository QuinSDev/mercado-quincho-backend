package com.mercado.quincho.configuration;

import com.mercado.quincho.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * Configuración de la aplicación que abarca múltiples aspectos de seguridad y 
 * autenticación,incluyendo la configuración de Cross-Origin Resource Sharing 
 * (CORS) para permitir solicitudes desde un origen específico, la gestión de 
 * autenticación con un proveedor personalizado, y otras configuraciones 
 * relacionadas con la seguridad de la aplicación.
 * 
 * Este archivo de configuración define los componentes necesarios para la 
 * autenticación de usuarios,como el administrador de autenticación, el 
 * proveedor de autenticación, el servicio de detalles deusuario y el 
 * codificador de contraseñas. Además, configura las reglas de CORS para 
 * permitir interacciones seguras con el frontend desde el origen http://localhost:5173.
 * 
 * @author QuinSDev
 */
@Configuration
@RequiredArgsConstructor
public class AppConfiguration {
    
    @Autowired
    private UserRepository userRepository;
    
    /**
     * Configuración de CORS para permitir solicitudes desde el origin frontend
     * @return una instancia nueva de CorsFilter que utiliza la configuración 
     * definida en la UrlBasedCorsConfigurationSource.
     */
    @Bean
    public CorsFilter corsFilter() {
        
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        
        corsConfiguration.addAllowedOriginPattern("http://localhost:5173");
        
        corsConfiguration.addAllowedMethod(HttpMethod.GET);
        corsConfiguration.addAllowedMethod(HttpMethod.POST);
        corsConfiguration.addAllowedMethod(HttpMethod.PUT);
        corsConfiguration.addAllowedMethod(HttpMethod.DELETE);
        
        corsConfiguration.addAllowedHeader("*");
        
         /* Crea una instancia de UrlBasedCorsConfigurationSource, que se 
        utiliza para configurar CORS basado en URL */
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        
        /* Registra la configuración CORS(CorsConfiguration) para que aplique
        a todas la rutas ("/**"). */
        source.registerCorsConfiguration("/**", corsConfiguration);
        
        return new CorsFilter(source);
        
    }
    
    /**
     * Define un administrador de autenticación personalizado.
     * 
     * @param config: La configuración de autenticación inyectada, que permite
     * acceder al administrador de autenticación
     * @return El administrador de autenticación personalizado
     * @throws Exception: Si ocurren errores durante la configuración o
     * recuperación del administrador.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) 
            throws Exception {
        return config.getAuthenticationManager();
    }
    
    /**
     * Define un proveedor de autenticación personalizado.
     * Este método configura y proporcioa un proovedor de autenticación que
     * utiliza un servicio de detalles de usuario y un codificador de contraseñas.
     * 
     * @return El proveedor de autenticación personalizado configurado.
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        // Crea una instancia de DaoAuthentication que manejará la autenticación
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        
        //Asigna una implementación personalizada de UserDetailsService
        authenticationProvider.setUserDetailsService(userDetailsService());
        
        /* Aigna un codificador de constraseñas(PasswordEncoder) para realizar 
        la verificación de contraseñas*/
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        
        return authenticationProvider;
        
    }
    
    /**
     * Define un servicio de detalles de usuario personalizado.
     * Este método configura y proporciona un servicio de detalles de usuario
     * que recupera información del usuario desde una base de datos.
     * 
     * @return El servicio de detalles de usuario personalizado configurado.
     */
    @Bean
    public UserDetailsService userDetailsService() {
        // Busca un usuario por su dirección de correo eletrónico.
        return email -> userRepository.findByEmail(email)
                // Lanza una excepción si el usuario no se encuentra.
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
                
    }
    
    /**
     * Define un codificador de contraseñas personalizados.
     * Este método configuraq y proporciona un codificador de contraseñas 
     * seguro (BCryptPasswordEncoder).
     * 
     * @return El codificador de contraseñas personalizado configurado.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
}