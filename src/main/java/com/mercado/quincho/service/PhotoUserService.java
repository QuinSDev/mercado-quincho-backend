package com.mercado.quincho.service;

import com.mercado.quincho.entity.PhotoUser;
import com.mercado.quincho.exception.MyException;
import com.mercado.quincho.repository.PhotoUserRepository;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * Servicio para gestionar operaciones relacionadas con la entidad PhotoUser.
 * Este servicio se encarga de realizar operaciones como guardar información
 * de fotos de uusarios.
 * 
 * @author QuinSDev
 */
@Service
public class PhotoUserService {
    
    @Autowired
    private PhotoUserRepository photoUserRepository;
    
    /**
     * Guarda la información de la foto del usuario en la base de datos.
     * 
     * @param file: El archivo de la foto del usuario.
     * @return La entidad PhotoUser guardada en la base de datos.
     * @throws MyException: Si ocurre algún error durante el proceso de guardado.
     */
    @Transactional
    public PhotoUser savePhotoUser(MultipartFile file) throws MyException {
        
        if (file != null) {
            try {
                // Crea una nueva entidad PhotoUser con la información del archivo.
                PhotoUser photoUser = PhotoUser.builder()
                        .mime(file.getContentType())
                        .name(file.getOriginalFilename())
                        .content(file.getBytes())
                        .build();
                
                return photoUserRepository.save(photoUser);
                
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return null;
        
    }
    
    @Transactional
    public PhotoUser updatePhotoUser(MultipartFile file, String idPhoto) {
        
        if (file != null) {
            try {
                
                PhotoUser photoUser = new PhotoUser();
                
                if (idPhoto != null) {
                    Optional<PhotoUser> response = photoUserRepository.findById(idPhoto);
                    
                    if (response.isPresent()) {
                        photoUser = response.get();
                    }
                    
                }
                
                photoUser.setMime(file.getContentType());
                photoUser.setName(file.getOriginalFilename());
                photoUser.setContent(file.getBytes());
                
                return photoUserRepository.save(photoUser);
                
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return null;
    }
}