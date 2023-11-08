/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mercado.quincho.controller;

import com.mercado.quincho.entity.CustomerOpinion;
import com.mercado.quincho.service.CustomerOpinionService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
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
public class CustomerOpinionController {
     private final CustomerOpinionService customerOpinionService;

    @Autowired
    public CustomerOpinionController(CustomerOpinionService 
           customerOpinionService) {
        this.customerOpinionService = customerOpinionService;
    }

    /**
     * Obtiene una opinión de cliente por su ID.
     *
     * @param id El ID de la opinión del cliente.
     * @return Un Optional que contiene la CustomerOpinion si se 
     * encuentra, o vacío si no se encuentra.
     */
    @GetMapping("/{id}")
    public Optional<CustomerOpinion> getCustomerOpinionById(@PathVariable
           String id) {
        return customerOpinionService.findCustomerOpinionById(id);
    }

    /**
     * Obtiene opiniones de clientes por su puntuación (qualification).
     *
     * @param qualification La puntuación por la cual buscar opiniones 
     * de clientes.
     * @return Una lista de CustomerOpinion que coinciden con la 
     * puntuación proporcionada.
     */
    @GetMapping("/by-qualification/{qualification}")
    public List<CustomerOpinion> getCustomerOpinionsByQualification
        (@PathVariable int qualification) {
        return customerOpinionService.findCustomerOpinionsByQualification
        (qualification);
    }

    /**
     * Agrega una nueva CustomerOpinion.
     *
     * @param customerOpinion La CustomerOpinion a agregar.
     * @return La CustomerOpinion agregada.
     */
    @PostMapping
    public CustomerOpinion addCustomerOpinion(@RequestBody CustomerOpinion 
           customerOpinion) {
        return customerOpinionService.saveCustomerOpinion(customerOpinion);
    }

    /**
     * Elimina una CustomerOpinion por su ID.
     *
     * @param id El ID de la opinión del cliente a eliminar.
     */
    @DeleteMapping("/{id}")
    public void deleteCustomerOpinion(@PathVariable String id) {
        customerOpinionService.deleteCustomerOpinionById(id);
    }
}
