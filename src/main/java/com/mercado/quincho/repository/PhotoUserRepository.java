package com.mercado.quincho.repository;

import com.mercado.quincho.entity.PhotoUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para la entidad PhotoUser.
 * 
 * Extiende JpaRepository, proporcionando operaciones CRUD báscias para la entidad.
 * 
 * @author QuinSDev
 */
@Repository
public interface PhotoUserRepository extends JpaRepository<PhotoUser, String>{
    
}