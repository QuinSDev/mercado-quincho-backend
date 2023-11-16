package com.mercado.quincho.repository;

import com.mercado.quincho.entity.PhotoQuincho;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio que gestiona la entidad PhotoQuincho en la base de datos.
 * Proporciona métodos para realizar operaciones relacionadas con las fotos de
 * los quinchos.
 * 
 * @author QuinSDev
 */
@Repository
public interface PhotoQuinchoRepository extends JpaRepository<PhotoQuincho, String>{
    
    /**
     * Busca y devuelve una lista de fotos de quinchos por el ID del quincho.
     * 
     * @param idQuincho: El ID del quincho del que se quieren obtener las fotos.
     * @return Lista de fotos asociadas al quincho identificado por el ID.
     */
    public List<PhotoQuincho> findByQuinchoId(String idQuincho);
    
}