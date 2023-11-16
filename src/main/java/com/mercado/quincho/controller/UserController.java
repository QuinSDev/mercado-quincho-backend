package com.mercado.quincho.controller;

import com.mercado.quincho.entity.User;
import com.mercado.quincho.request.RegisterRequest;
import com.mercado.quincho.response.AuthResponse;
import com.mercado.quincho.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controlador para operaciones relacionadas con usuario.
 * Proporciona endpoints para obtener datps de un usuario por ID y actualizar
 * el perfil de usuario.
 * 
 * @author QuinSDev
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private final UserService userService;
    
    /**
     * Obtiene los datos de un usuario por su ID
     * 
     * @param id: El ID único del usuario del que se quiere obtener la información.
     * @return ResponseEntity con los datos del usuario. 
     */
    @GetMapping(value = "datos/{id}")
    public ResponseEntity<User> dateUser(@PathVariable String id) {
        return ResponseEntity.ok(userService.getOne(id));
        
    }
    
    /**
     * Actualiza el perfil de un usuario.
     *
     * @param request: La solicitud con los datos actualizados del usuario.
     * @return ResponseEntity con la respuesta de la actualización del perfil del usuario.
     */
    @PostMapping(value = "perfil")
    public ResponseEntity<AuthResponse> uptadeUser(RegisterRequest request) {
        return ResponseEntity.ok(userService.updateUser(request));
    }
    
}