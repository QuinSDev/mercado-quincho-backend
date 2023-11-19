package com.mercado.quincho.controller;

import com.mercado.quincho.entity.Quincho;
import com.mercado.quincho.request.RegisterQuinchoRequest;
import com.mercado.quincho.response.QuinchoResponse;
import com.mercado.quincho.service.QuinchoService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador RESTful para operaciones relacionadas con Quinchos.
 * Define endpoints para registrar quinchos, obtener la lista de quinchos
 * y editar un quincho específico.
 * 
 * Se ocupa de manejar las solicitudes HTTP relacionadas con los quinchos.
 * 
 * @author QuinSDev
 */
@RestController
@RequestMapping("/quincho")
@RequiredArgsConstructor
public class QuinchoController {

    @Autowired
    private QuinchoService quinchoService;
    
    /**
     * Registra un nuevo quincho asociado al ID del usuario proporcionado.
     * 
     * @param request: La solicitud de registro del quincho
     * @param idUser: El ID único del usuario
     * @return ResponseEntity con la respuesta del registro del quincho y el 
     * estado HTTP correspondiente
     */
    @PostMapping(value = "register/{id}")
    public ResponseEntity<QuinchoResponse> registerQuincho(RegisterQuinchoRequest request,
            @PathVariable String id) {
        try {
            return ResponseEntity.ok(quinchoService.registerQuincho(request, id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new QuinchoResponse(
                    "Error al registrar el quincho: " + e.getMessage()));
        }
    }
    
    /**
     * Edita un quincho existente identificado por su ID.
     * 
     * @param request: La solicitud de edición del quincho
     * @param idQuincho: El ID único del quincho a editar
     * @return ResponseEntity con la respuesta de la edición del quincho y 
     * el estado HTTP correspondiente
     */
    @PostMapping(value = "edit/{idQuincho}")
    public ResponseEntity<QuinchoResponse> updateQuincho(RegisterQuinchoRequest
            request, @PathVariable String idQuincho) {
        try {
            return ResponseEntity.ok(quinchoService.updateQuincho(request, idQuincho));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new QuinchoResponse(
                    "Error al modificar el quincho: " + e.getMessage()));
        }
    }
    
    /**
     * Obtiene la lista de todos los quinchos registrados en el sistema.
     * 
     * @return ResponseEntity con la lista de quinchos y el estado HTTP OK 
     * si la solicitud es exitosa
     */
    @GetMapping("/lista-quinchos")
    public ResponseEntity<List<Quincho>> getListaQuinchos() {
        List<Quincho> quinchos = quinchoService.listQuincho();
        return new ResponseEntity<>(quinchos, HttpStatus.OK);
    }
    
    /**
     * Obtiene la lista de quinchos asociados a un usuario específico por su ID.
     * 
     * @param id: El ID único del usuario para obtener sus quinchos
     * @return ResponseEntity con la lista de quinchos del usuario y el 
     * estado HTTP OK si la solicitud es exitosa
     */
    @GetMapping("/user/quinchos/{id}")
    public ResponseEntity<List<Quincho>> getListQuinchoUser(@PathVariable String id) {
        List<Quincho> quinchos = quinchoService.listQuinchoUser(id);
        return new ResponseEntity<>(quinchos, HttpStatus.OK);
    }
    
}
