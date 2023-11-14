package com.mercado.quincho.repository;

import com.mercado.quincho.entity.PhotoQuincho;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author QuinSDev
 */
@Repository
public interface PhotoQuinchoRepository extends JpaRepository<PhotoQuincho, String>{

    public List<PhotoQuincho> findByQuinchoIdQuincho(String idQuincho);
    
}
