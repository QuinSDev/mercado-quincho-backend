package com.mercado.quincho.controller;

import com.mercado.quincho.request.LoginRequest;
import com.mercado.quincho.request.RegisterRequest;
import com.mercado.quincho.response.AuthResponse;
import com.mercado.quincho.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador que gestiona las operaciones de autenticación y registro de usuarios.
 * Este controlador se encarga de manejar las solicitudes relacionadas con la
 * autenticación de usuarios y el registro de nuevos usuarios en el sistema.
 * 
 * @author QuinSDev
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    
    @Autowired
    private AuthService authService;
    
    /**
     * Maneja solicitudes POST en la ruta "/auth/register" para registrar nuevos
     * usuarios.
     * 
     * @param request: La solicitud de registro que contiene los datos del nuevo
     *                 usuario.
     * @return Una respuesta HTTP que incluye un objeto AuthResponse que contiene
     *         un token de autenticación en caso de éxito o un mensaje de error
     *         en caso de fallo.
     */
    @PostMapping(value = "register")
    public ResponseEntity<AuthResponse> registerUser(@RequestBody RegisterRequest
            request) {
        return ResponseEntity.ok(authService.registerUser(request));
    }
    
    /**
     * Maneja solicitudes POST en la ruta "/auth/login" para autenticar a los
     * usuarios.
     * 
     * @param request: La solicitud de inicio de sesión que contiene las
     *                 credenciales de usuario.
     * @return Una respuesta HTTP que incluye un ubjeto AuthResponse que contiene
     *         un token de autenticación en caso de éxito o un mensaje de error
     *         en caso de fallo.
     */
    @PostMapping(value = "login")
    public ResponseEntity<AuthResponse> loginUser(@RequestBody LoginRequest
            request) {
        return ResponseEntity.ok(authService.loginUser(request));
    }
}
