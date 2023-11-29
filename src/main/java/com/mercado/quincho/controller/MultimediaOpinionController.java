/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mercado.quincho.controller;

import com.mercado.quincho.entity.MultimediaOpinion;
import com.mercado.quincho.service.MultimediaOpinionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador que maneja las solicitudes relacionadas con las opiniones 
 * multimedia de los clientes.
 * 
 * @author EdwarVelasquez
 */
@RestController
@RequestMapping("/opinions")
public class MultimediaOpinionController {

    //@Autowired
    //private MultimediaOpinionService multimediaOpinionService;

    /**
     * Endpoint para guardar una nueva opinión multimedia.
     *
     * @param multimediaOpinion La opinión multimedia a almacenar.
     * @return La respuesta con la opinión multimedia almacenada.
     */
    /*@PostMapping
    public ResponseEntity<MultimediaOpinion> saveOpinion(@RequestBody 
            MultimediaOpinion multimediaOpinion) {
        MultimediaOpinion savedOpinion = multimediaOpinionService.saveOpinion
        (multimediaOpinion);
        return new ResponseEntity<>(savedOpinion, HttpStatus.CREATED);
    }*/

    /**
     * Endpoint para obtener una opinión multimedia por su identificador único.
     *
     * @param idMultimediaOpinion El identificador único de la opinión 
     * multimedia.
     * @return La respuesta con la opinión multimedia correspondiente al 
     * identificador dado.
     */
    /*@GetMapping("/{id}")
    public ResponseEntity<MultimediaOpinion> getOpinionById(@PathVariable String
            idMultimediaOpinion) {
        MultimediaOpinion opinion = multimediaOpinionService.getOpinionById
        (idMultimediaOpinion);
        return new ResponseEntity<>(opinion, HttpStatus.OK);
    }*/

    /**
     * Endpoint para actualizar una opinión multimedia existente.
     *
     * @param multimediaOpinion La opinión multimedia actualizada.
     * @return La respuesta con la opinión multimedia después de la 
     * actualización.
     */
    /*@PutMapping
    public ResponseEntity<MultimediaOpinion> updateOpinion(@RequestBody 
            MultimediaOpinion multimediaOpinion) {
        MultimediaOpinion updatedOpinion = multimediaOpinionService.
            updateOpinion(multimediaOpinion);
        return new ResponseEntity<>(updatedOpinion, HttpStatus.OK);
    }*/

    /**
     * Endpoint para eliminar una opinión multimedia por su identificador único.
     *
     * @param idMultimediaOpinion El identificador único de la opinión 
     * multimedia a eliminar.
     * @return La respuesta indicando el éxito de la operación.
     */
    /*@DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOpinionById(@PathVariable String 
            idMultimediaOpinion) {
        multimediaOpinionService.deleteOpinionById(idMultimediaOpinion);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }*/

}
