/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mercado.quincho.repository;

import com.mercado.quincho.entity.CustomerOpinion;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Se crea la clase de tipo repositorio para CustomerOpinion, donde
 * la llave primaria es de tipo String, se busca una CustomerOpinion
 * por su id.
 * 
 * @author EdwarVelasquez
 */
@Repository
public interface CustomerOpinionRepository extends JpaRepository<CustomerOpinion
        , String> {
    
    /**
     * Busca una CustomerOpinion por su ID.
     *
     * @param idCustomerOpinion El ID de la opinión del cliente a buscar.
     * @return Un Optional que contiene la CustomerOpinion si se encuentra, 
     * o vacío si no se encuentra.
     */
    Optional<CustomerOpinion> findByIdCustomerOpinion(String idCustomerOpinion);

    /**
     * Busca opiniones de clientes por su puntuación (qualification).
     *
     * @param qualification La puntuación por la cual buscar opiniones de 
     * clientes.
     * @return Una lista de CustomerOpinion que coinciden con la puntuación 
     * proporcionada.
     */
    List<CustomerOpinion> findByQualification(int qualification);

    /**
     * Guarda una nueva CustomerOpinion o actualiza una existente.
     *
     * @param customerOpinion La CustomerOpinion a guardar o actualizar.
     * @param <S>            Tipo de la entidad CustomerOpinion.
     * @return La CustomerOpinion guardada o actualizada.
     */    
    <S extends CustomerOpinion> S save(S customerOpinion);

    /**
     * Elimina una CustomerOpinion por su ID.
     *
     * @param idCustomerOpinion El ID de la opinión del cliente a eliminar.
     */
    void deleteByIdCustomerOpinion(String idCustomerOpinion);
}
