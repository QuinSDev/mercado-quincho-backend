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
    
    Optional<CustomerOpinion> findById(String idCustomerOpinion);
}
