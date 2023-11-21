/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mercado.quincho.service;

import com.mercado.quincho.entity.CustomerOpinion;
import com.mercado.quincho.repository.CustomerOpinionRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author EdwarVelasquez
 */
@Service
public class CustomerOpinionService {
    //private final CustomerOpinionRepository customerOpinionRepository;

    /*@Autowired
    public CustomerOpinionService(CustomerOpinionRepository 
           customerOpinionRepository) {
        this.customerOpinionRepository = customerOpinionRepository;
    }*/

    /**
     * Busca una opinión de cliente por su ID.
     *
     * @param idCustomerOpinion El ID de la opinión del cliente a buscar.
     * @return Un Optional que contiene la CustomerOpinion si se encuentra, 
     * o vacío si no se encuentra.
     */
    /*public Optional<CustomerOpinion> findCustomerOpinionById
        (String idCustomerOpinion) {
        return customerOpinionRepository.findByIdCustomerOpinion
        (idCustomerOpinion);
    }*/

    /**
     * Busca opiniones de clientes por su puntuación (qualification).
     *
     * @param qualification La puntuación por la cual buscar opiniones de 
     * clientes.
     * @return Una lista de CustomerOpinion que coinciden con la puntuación 
     * proporcionada.
     */
    /*public List<CustomerOpinion> findCustomerOpinionsByQualification
        (int qualification) {
        return customerOpinionRepository.findByQualification(qualification);
    }*/

    /**
     * Guarda una nueva CustomerOpinion o actualiza una existente.
     *
     * @param customerOpinion La CustomerOpinion a guardar o actualizar.
     * @return La CustomerOpinion guardada o actualizada.
     */
    /*public CustomerOpinion saveCustomerOpinion(CustomerOpinion customerOpinion){
        return customerOpinionRepository.save(customerOpinion);
    }*/

    /**
     * Elimina una CustomerOpinion por su ID.
     *
     * @param idCustomerOpinion El ID de la opinión del cliente a eliminar.
     */
    /*public void deleteCustomerOpinionById(String idCustomerOpinion) {
        customerOpinionRepository.deleteByIdCustomerOpinion(idCustomerOpinion);
    }*/
    
}
