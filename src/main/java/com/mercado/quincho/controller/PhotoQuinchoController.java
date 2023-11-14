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
 *
 * @author QuinSDev
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/quinchos")
public class PhotoQuinchoController {

    @Autowired
    private final QuinchoService quinchoService;

    @GetMapping("/fotos/{id}")
    public ResponseEntity<byte[]> getQuinchoPhotos(@PathVariable String id) {
        // Step 1: Obtain the Quincho
        Optional<Quincho> quincho = quinchoService.getOne(id);

        // Step 2: Obtain the Quincho's Photos
        if (quincho.isPresent()) {
            Quincho quinchoEntity = quincho.get();
            System.out.println("Quincho: " + quinchoEntity);
            // Assuming you have a method like getPhotos() in Quincho class
            // Adjust this based on the actual structure of your Quincho class
            List<PhotoQuincho> quinchoPhotos = quincho.get().getPhotos();

            // Assuming you want to return the first photo, adjust as needed
            if (!quinchoPhotos.isEmpty()) {
                PhotoQuincho quinchoPhoto = quinchoPhotos.get(0);

                // Step 3: Prepare the HTTP Response
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.IMAGE_JPEG);

                return new ResponseEntity<>(quinchoPhoto.getContent(), headers, HttpStatus.OK);
            }
        }

        // Return a 404 response if Quincho or Photo is not found
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
