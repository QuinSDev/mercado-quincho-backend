/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mercado.quincho.repository;

import com.mercado.quincho.entity.MultimediaOpinion;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para gestionar CRUD (Crear, Leer, Actualizar, Eliminar)
 * relacionadas con las opiniones multimedia de los clientes.
 * 
 * @author EdwarVelasquez
 */
@Repository
public interface MultimediaOpinionRepository {
   
    /**
     * Guarda una nueva opinión multimedia en el repositorio.
     *
     * @param multimediaOpinion La opinión multimedia a almacenar.
     * @return La opinión multimedia almacenada.
     */
    MultimediaOpinion save(MultimediaOpinion multimediaOpinion);

    /**
     * Recupera una opinión multimedia por su identificador único.
     *
     * @param idMultimediaOpinion El identificador único de la opinión
     * multimedia.
     * @return La opinión multimedia correspondiente al identificador dado.
     */
    MultimediaOpinion findById(String idMultimediaOpinion);

    /**
     * Actualiza una opinión multimedia existente en el repositorio.
     *
     * @param multimediaOpinion La opinión multimedia actualizada.
     * @return La opinión multimedia después de la actualización.
     */
    MultimediaOpinion update(MultimediaOpinion multimediaOpinion);

    /**
     * Elimina una opinión multimedia del repositorio por su identificador
     * único.
     *
     * @param idMultimediaOpinion El identificador único de la opinión 
     * multimedia a eliminar.
     */
    void deleteById(String idMultimediaOpinion);
    
}
