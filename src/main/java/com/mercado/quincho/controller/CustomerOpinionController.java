/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mercado.quincho.controller;

import com.mercado.quincho.entity.CustomerOpinion;
import com.mercado.quincho.request.OpinionRequest;
import com.mercado.quincho.response.QuinchoResponse;
import com.mercado.quincho.service.CustomerOpinionService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador que maneja las solicitudes relacionadas con las opiniones
 * de los clientes.
 * @author EdwarVelasquez
 */
@RestController
@RequestMapping("/customer-opinions")
@RequiredArgsConstructor
public class CustomerOpinionController {
    
    @Autowired
    private final CustomerOpinionService customerOpinionService;
            
    @PostMapping(value = "register/{idUser}/{idQuincho}")
    public ResponseEntity<QuinchoResponse> registerOpinion(OpinionRequest request,
            @PathVariable String idUser, @PathVariable String idQuincho) {
        return ResponseEntity.ok(customerOpinionService.registerOpinion(idUser, idQuincho, request));
    }
    
    @GetMapping(value = "quincho/{idQuincho}")
    public ResponseEntity<List<CustomerOpinion>> getListOpinionsQuincho(@PathVariable String idQuincho)
    {
        return ResponseEntity.ok(customerOpinionService.getListOpinionsQuincho(idQuincho));
    }
}
