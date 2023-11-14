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
 *
 * @author QuinSDev
 */
@Service
@RequiredArgsConstructor
public class PhotoQuinchoService {

    @Autowired
    private final PhotoQuinchoRepository photoQuinchoRepository;

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

    @Transactional
    public List<PhotoQuincho> getPhotosByQuinchoId(String idQuincho) {
        return photoQuinchoRepository.findByQuinchoIdQuincho(idQuincho);
    }

}
