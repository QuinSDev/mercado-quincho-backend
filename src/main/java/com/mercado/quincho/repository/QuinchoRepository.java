package com.mercado.quincho.repository;

import com.mercado.quincho.entity.Quincho;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio que gestiona la entidad Quincho en la base de datos.
 * Proporciona métodos para realizar operaciones relacionas con los quinchos.
 * 
 * @author QuinSDev
 */
@Repository
public interface QuinchoRepository extends JpaRepository<Quincho, String>{
    
    /**
     * Busca y devuelve un quincho por su ID
     * 
     * @param idQuincho: El ID único del quincho que se quiere buscar
     * @return Un objeto Optional que puede contener el quincho si se encuentra,
     * o vacío si no.
     */
    Optional<Quincho> findById(String idQuincho);
}