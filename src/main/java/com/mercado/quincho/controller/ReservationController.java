package com.mercado.quincho.controller;

import com.mercado.quincho.entity.Quincho;
import com.mercado.quincho.entity.Reservation;
import com.mercado.quincho.request.ReservationRequest;
import com.mercado.quincho.response.QuinchoResponse;
import com.mercado.quincho.service.ReservationService;
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
 * /**
 * Controlador que maneja las solicitudes relacionadas con las reservas.
 * Proporciona un punto de entrada para registrar reservas a través de la
 * llamada al método "registerReservation".
 * 
 * @author QuinSDev
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/reservation")
public class ReservationController {
    
    @Autowired
    private final ReservationService reservationService;
    
    /**
     * Método para registrar una nueva reserva.
     * 
     * @param request: Datos de la reserva a crear
     * @param idUser: Identificador del usuario que realiza la reserva
     * @param idQuincho: Identificador del quincho reservado
     * @return ResponseEntity con la respuesta de la creación de la reserva
     */
    @PostMapping(value = "register/{idUser}/{idQuincho}")
    public ResponseEntity<QuinchoResponse> registerReservation(ReservationRequest
            request, @PathVariable String idUser, @PathVariable String idQuincho) 
    {
        return ResponseEntity.ok(reservationService.createReservation(request, 
                 idUser, idQuincho));   
    }
    
    @GetMapping(value = "/user/{idUser}")
    public ResponseEntity<List<Reservation>> getListReservation(@PathVariable 
        String idUser) {
        List<Reservation> reservations = reservationService.listReservationUser(idUser);
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }
    
    @GetMapping(value = "/quincho/{idReservation}")
    public ResponseEntity<Quincho> getQuincho(@PathVariable String idReservation) {
        Quincho quincho = reservationService.finQuinchoByReservationId(idReservation);
        return new ResponseEntity<>(quincho, HttpStatus.OK);
    }
    
}