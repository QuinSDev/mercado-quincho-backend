package com.mercado.quincho.repository;

import com.mercado.quincho.entity.Quincho;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author QuinSDev
 */
@Repository
public interface QuinchoRepository extends JpaRepository<Quincho, String>{
    
    //@Query("SELECT u FROM Quincho u WHERE u.id_quincho = :id_quincho")
    //public Quincho buscarPorId(@Param("id_quincho")String id_quincho);
    Optional<Quincho> findById(String idQuincho);
}
