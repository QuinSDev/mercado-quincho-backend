package com.mercado.quincho.controller;

import com.mercado.quincho.entity.User;
import com.mercado.quincho.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author QuinSDev
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    
    @Autowired
    private final UserService userService;
    
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.listUser();
    }
    
}
