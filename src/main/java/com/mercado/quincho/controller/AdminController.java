package com.mercado.quincho.controller;

import com.mercado.quincho.entity.CustomerOpinion;
import com.mercado.quincho.entity.User;
import com.mercado.quincho.service.CustomerOpinionService;
import com.mercado.quincho.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador RESTful para operaciones administrativas relacionas con usuarios.
 * Proporciona endpoints para obtener información de usuarios para própositos 
 * administrivos.
 * 
 * @author QuinSDev
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    
    @Autowired
    private final UserService userService;
    
    @Autowired
     private final CustomerOpinionService customerOpinionService;       
    
    /**
     * Obtiene una lista de todos los usuarios.
     * 
     * @return Lista de usuarios existentes en el sistema.
     */
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.listUser();
    }
    
    @GetMapping("/opinions")
    public List<CustomerOpinion> getAllOpinions() {
        return customerOpinionService.listOpinions();
    }
    
}