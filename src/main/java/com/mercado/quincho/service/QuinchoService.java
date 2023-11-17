package com.mercado.quincho.service;

import com.mercado.quincho.entity.PhotoQuincho;
import com.mercado.quincho.entity.Quincho;
import com.mercado.quincho.entity.Role;
import com.mercado.quincho.entity.User;
import com.mercado.quincho.exception.MyException;
import com.mercado.quincho.repository.QuinchoRepository;
import com.mercado.quincho.repository.UserRepository;
import com.mercado.quincho.request.RegisterQuinchoRequest;
import com.mercado.quincho.response.QuinchoResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio para gestionar operaciones relacionadas con los quinchos.
 * Se encarga de funciones como registrar un quincho, obtener información
 * de un quincho y listar todos los quinchos disponibles.
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

    @Autowired
    private final UserRepository userRepository;
    
    /**
     * Registra un nuevo quincho en el sistema.
     * 
     * @param request La solicitud de registro del quincho.
     * @param id El ID del usuario dueño del quincho.
     * @return La respuesta del registro del quincho.
     */
    @Transactional
    public QuinchoResponse registerQuincho(RegisterQuinchoRequest request, String id) {
        try {
            Optional<User> response = userRepository.findByEmail(id);

            if (response.isPresent()) {

                User user = response.get();

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

                List<Quincho> quinchoList = new ArrayList<>();
                quinchoList.add(quincho);
                user.setQuincho(quinchoList);
                user.setRole(Role.OWNER);

                return QuinchoResponse.builder()
                        .msg("Registro éxitoso")
                        .build();
            }

        } catch (MyException ex) {
            return new QuinchoResponse("Error de registro: " + ex.getMessage());
        }
        return null;
    }
    
    /**
     * Obtiene un quincho por su ID.
     * 
     * @param idQuincho: El ID del quincho que se quiere obtener.
     * @return Un objeto Optional que puede contener el quincho si se encuentra, o vacío si no.
     */
    @Transactional
    public Optional<Quincho> getOne(String idQuincho) {
        Optional<Quincho> quincho = quinchoRepository.findById(idQuincho);
        return quincho;
    }
    
    /**
     * Lista todos los quinchos disponibles.
     * 
     * @return Una lista de todos los quinchos existentes en el sistema.
     */
    @Transactional
    public List<Quincho> listQuincho() {
        List<Quincho> quinchos = quinchoRepository.findAll();

        return quinchos;
    }
    
    /**
     * Valida los datos de registro del quincho.
     * 
     * @param request: La solicitud de registro del quincho.
     * @throws MyException Si algún dato del quincho es incorrecto o está ausente.
     */
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