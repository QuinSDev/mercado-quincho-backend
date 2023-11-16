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
     * Obtiene las fotos de un quincho identificado por su ID.
     * 
     * @param id: El ID único del quincho del que se quiere obtener las fotos.
     * @return ResponsityEntity con las fotos del quinchos en formato arreglo
     * de bytes.
     */
    @GetMapping("/fotos/{id}")
    public ResponseEntity<byte[]> getQuinchoPhotos(@PathVariable String id) {
        
        Optional<Quincho> quincho = quinchoService.getOne(id);

        if (quincho.isPresent()) {
            Quincho quinchoEntity = quincho.get();
            System.out.println("Quincho: " + quinchoEntity);
            List<PhotoQuincho> quinchoPhotos = quincho.get().getPhotos();

            if (!quinchoPhotos.isEmpty()) {
                PhotoQuincho quinchoPhoto = quinchoPhotos.get(0);
                
                // Configura los encabezados para la respuesta HTTP
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.IMAGE_JPEG);

                return new ResponseEntity<>(quinchoPhoto.getContent(), headers, 
                        HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}