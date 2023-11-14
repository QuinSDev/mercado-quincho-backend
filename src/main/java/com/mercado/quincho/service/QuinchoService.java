package com.mercado.quincho.service;

import com.mercado.quincho.entity.PhotoQuincho;
import com.mercado.quincho.entity.Quincho;
import com.mercado.quincho.exception.MyException;
import com.mercado.quincho.repository.QuinchoRepository;
import com.mercado.quincho.request.RegisterQuinchoRequest;
import com.mercado.quincho.response.QuinchoResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
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
public class QuinchoService {

    @Autowired
    private final QuinchoRepository quinchoRepository;

    @Autowired
    private final PhotoQuinchoService photoQuinchoService;

    @Transactional
    public QuinchoResponse registerQuincho(RegisterQuinchoRequest request) {
        try {
            validQuincho(request);

            List<PhotoQuincho> photoQuincho = photoQuinchoService.savePhotoQuincho(request.getFiles());

            Quincho quincho = new Quincho();

            quincho.setNameQuincho(request.getNameQuincho());
            quincho.setDescription(request.getDescription());
            quincho.setLocation(request.getLocation());
            quincho.setTypeQuincho(request.getTypeQuincho());
            quincho.setNumBathroom(request.getNumBathroom());
            quincho.setNumBed(request.getNumBed());
            quincho.setNumBedroom(request.getNumBedroom());
            quincho.setNumGuest(request.getNumGuest());
            quincho.setPrice(request.getPrice());
            quincho.setPhotos(photoQuincho);

            quinchoRepository.save(quincho);

            return QuinchoResponse.builder()
                    .msg("Registro éxitoso")
                    .build();

        } catch (MyException ex) {
            return new QuinchoResponse("Error de registro: " + ex.getMessage());
        }

    }

    public Optional<Quincho> getOne(String idQuincho) {
        Optional<Quincho> quincho = quinchoRepository.findById(idQuincho);
        if (quincho.isPresent()) {
            // Log: Quincho found
            System.out.println("Quincho found: " + quincho.get());
        } else {
            // Log: Quincho not found
            System.out.println("Quincho not found for id: " + idQuincho);
        }
        return quincho;
    }

    @Transactional
    public List<Quincho> listQuincho() {
        List<Quincho> quinchos = quinchoRepository.findAll();
        
        return quinchos;
    }

    @Transactional
    public void validQuincho(RegisterQuinchoRequest request) throws MyException {

        if (request.getNameQuincho() == null || request.getNameQuincho().isEmpty()) {
            throw new MyException("El nombre del quincho no puede estar vacío");
        }
        if (request.getDescription() == null || request.getDescription().isEmpty()) {
            throw new MyException("La descripción del quincho no puede estar"
                    + " vacía");
        }
        if (request.getLocation() == null || request.getLocation().isEmpty()) {
            throw new MyException("La ubicación del quincho no puede estar "
                    + "vacía");
        }
        if (request.getTypeQuincho() == null || request.getTypeQuincho().isEmpty()) {
            throw new MyException("El tipo de quincho no puede estar vacío");
        }
        if (request.getNumBathroom() < 0) {
            throw new MyException("Dato incorrecto");
        }
        if (request.getNumBed() < 0) {
            throw new MyException("Dato incorrecto");
        }
        if (request.getNumBedroom() < 0) {
            throw new MyException("Dato incorrecto");
        }
        if (request.getNumGuest() < 0) {
            throw new MyException("Dato incorrecto");
        }
        if (request.getPrice() < 0) {
            throw new MyException("Dato incorrecto");
        }

    }
}
