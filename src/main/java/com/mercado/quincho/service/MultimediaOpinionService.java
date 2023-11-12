/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mercado.quincho.service;

import com.mercado.quincho.entity.MultimediaOpinion;
import org.springframework.stereotype.Service;

/**
 * Servicio que proporciona funcionalidades relacionadas con la gestión de opiniones multimedia
 * de los clientes.
 * 
 * @author EdwarVelasquez
 */
@Service
public interface MultimediaOpinionService {
   
    /**
     * Guarda una nueva opinión multimedia.
     *
     * @param multimediaOpinion La opinión multimedia a almacenar.
     * @return La opinión multimedia almacenada.
     */
    MultimediaOpinion saveOpinion(MultimediaOpinion multimediaOpinion);

    /**
     * Recupera una opinión multimedia por su identificador único.
     *
     * @param idMultimediaOpinion El identificador único de la opinión 
     * multimedia.
     * @return La opinión multimedia correspondiente al identificador dado.
     */
    MultimediaOpinion getOpinionById(String idMultimediaOpinion);

    /**
     * Actualiza una opinión multimedia existente.
     *
     * @param multimediaOpinion La opinión multimedia actualizada.
     * @return La opinión multimedia después de la actualización.
     */
    MultimediaOpinion updateOpinion(MultimediaOpinion multimediaOpinion);

    /**
     * Elimina una opinión multimedia por su identificador único.
     *
     * @param idMultimediaOpinion El identificador único de la opinión 
     * multimedia a eliminar.
     */
    void deleteOpinionById(String idMultimediaOpinion);    
}