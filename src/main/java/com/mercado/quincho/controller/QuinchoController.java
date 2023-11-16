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
 * Define endpoints para registrar quinchos y obtener la lista de quinchos.
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
     * Registra un quincho con el ID del usuario proporcionado.
     * 
     * @param request: Solicitud de registro del quincho
     * @param idUser: Identificador único del usuario
     * @return Responsitycon la respuesta del registro del quincho
     */
    @PostMapping(value = "register/{idUser}")
    public ResponseEntity<QuinchoResponse> registerQuincho(RegisterQuinchoRequest request,
            @PathVariable String idUser) {
        try {
            return ResponseEntity.ok(quinchoService.registerQuincho(request, idUser));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new QuinchoResponse(
                    "Error al registrar el quincho: " + e.getMessage()));
        }
    }
    
    /**
     * Obtiene la lista de todos los quinchos
     * @return ResponseEntity con la lista de quinchos y el estado HTTP OK.
     */
    @GetMapping("/lista-quinchos")
    public ResponseEntity<List<Quincho>> getListaQuinchos() {
        List<Quincho> quinchos = quinchoService.listQuincho();
        return new ResponseEntity<>(quinchos, HttpStatus.OK);
    }
    
}
