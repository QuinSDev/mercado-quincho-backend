package com.mercado.quincho.service;

import com.mercado.quincho.entity.PhotoQuincho;
import com.mercado.quincho.repository.PhotoQuinchoRepository;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * Servicio que gestiona las operaciones relacionadas con las fotos de los quinchos.
 * 
 * @author QuinSDev
 */
@Service
@RequiredArgsConstructor
public class PhotoQuinchoService {

    @Autowired
    private final PhotoQuinchoRepository photoQuinchoRepository;
    
    /**
     * Guarda las fotos de quinchos en la base de datos.
     *
     * @param files: Lista de archivos de imágenes (fotos) de quinchos a guardar.
     * @return Lista de fotos de quinchos guardadas en la base de datos.
     */
    public List<PhotoQuincho> savePhotoQuincho(List<MultipartFile> files) {
        List<PhotoQuincho> savedPhotos = new ArrayList();

        if (files != null) {
            for (MultipartFile file : files) {
                try {
                    PhotoQuincho photoQuincho = new PhotoQuincho();
                    photoQuincho.setMime(file.getContentType());
                    photoQuincho.setName(file.getOriginalFilename());
                    photoQuincho.setContent(file.getBytes());

                    PhotoQuincho savedPhoto = photoQuinchoRepository.save(photoQuincho);
                    savedPhotos.add(savedPhoto);
                } catch (Exception e) {
                    // Manejar adecuadamente la excepción, lograrla si es necesario
                    e.printStackTrace();
                }
            }

            return savedPhotos;
        }
        return null;

    }
    
    /**
     * Obtiene las fotos de un quincho por su ID.
     *
     * @param idQuincho: El ID del quincho del que se quieren obtener las fotos.
     * @return Lista de fotos del quincho identificado por el ID.
     */
    @Transactional
    public List<PhotoQuincho> getPhotosByQuinchoId(String idQuincho) {
        return photoQuinchoRepository.findByQuinchoId(idQuincho);
    }

}