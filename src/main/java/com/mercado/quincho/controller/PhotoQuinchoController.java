package com.mercado.quincho.controller;

import com.mercado.quincho.entity.PhotoQuincho;
import com.mercado.quincho.entity.Quincho;
import com.mercado.quincho.service.QuinchoService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controlador que gestiona las operaciones relacionadas con las fotos de quinchos.
 * Proporciona un endpoint para obtener las fotos de un quincho específico.
 * 
 * @author QuinSDev
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/quinchos")
public class PhotoQuinchoController {

    @Autowired
    private final QuinchoService quinchoService;
    
    /**
     * Obtiene una foto específica de un Quincho según su ID y su índice.
     *
     * @param id:     ID del Quincho del cual se quiere obtener la foto.
     * @param index:  Índice de la foto deseada dentro de las fotos del Quincho.
     * @return       Respuesta con la foto del Quincho o un estado de error si no se encuentra.
     */
    @GetMapping("/fotos/{id}/{index}")
    public ResponseEntity<byte[]> getQuinchoPhoto(@PathVariable String id, @PathVariable int index) {
        Optional<Quincho> quincho = quinchoService.getOne(id);

        if (quincho.isPresent()) {
            List<PhotoQuincho> quinchoPhotos = quincho.get().getPhotos();

            if (!quinchoPhotos.isEmpty() && index >= 0 && index < quinchoPhotos.size()) {
                PhotoQuincho quinchoPhoto = quinchoPhotos.get(index);
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.IMAGE_JPEG);

                return new ResponseEntity<>(quinchoPhoto.getContent(), headers, HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
   
}