package com.mercado.quincho.controller;

import com.mercado.quincho.entity.PhotoUser;
import com.mercado.quincho.entity.User;
import com.mercado.quincho.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controlador que gestiona las operaciones relacionadas con las fotos de perfil 
 * de usuario.
 * Proporciona un endpoint para obtener la foto de perfil de un usuario por su ID.
 * 
 * @author QuinSDev
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/photo")
public class PhotoUserController {

    @Autowired
    private final UserService userService;
    
    /**
     * Obtiene la foto de perfil de un usuario por su ID.
     * 
     * @param id: El ID único del usuario del que se quiere obtener la foto perfil.
     * @return ResponseEntity con la foto de perfil del usuario en formato de 
     * arreglo de bytes.
     */
    @GetMapping("/perfil/{id}")
    public ResponseEntity<byte[]> getUserPhoto(@PathVariable String id) {
        User user = userService.getOne(id);
        
        byte[] photo = user.getPhoto().getContent();
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        
        return new ResponseEntity<>(photo, headers, HttpStatus.OK);
    }

}