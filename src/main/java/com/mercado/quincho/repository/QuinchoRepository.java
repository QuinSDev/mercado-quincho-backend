package com.mercado.quincho.repository;

import com.mercado.quincho.entity.Quincho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author QuinSDev
 */
@Repository
public interface QuinchoRepository extends JpaRepository<Quincho, String>{
    
}
