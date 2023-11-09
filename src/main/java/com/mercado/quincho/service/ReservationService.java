/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mercado.quincho.service;

import com.mercado.quincho.entity.Quincho;
import com.mercado.quincho.entity.Reservation;
import com.mercado.quincho.exception.MyException;
import com.mercado.quincho.repository.ReservationRepository;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio que gestiona las operaciones relacionadas con los usuarios en el
 * sistema. Como puede ser registrar una reservación,modificar una reservación
 * existente, eliminar en su debido caso.
 *
 * @author monte
 */
@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    /**
     * Registra una nueva reservación en el sistema
     *
     * @param startDate: Fecha inicio reserva
     * @param endDate: Fecha finalización reserva
     * @param checkIn: Hora de ingreso
     * @param checkOut: Hora salida
     * @param totalPayment: Total a pagar
     * @param idQuincho: Identificador del Quincho
     * @throws MyException: Se lanza si se encuentran problemas en los datos,
     * con un mensaje descriptivo del problema
     *
     */
    @Transactional
    public void createReservation(Date startDate,
            Date endDate, Calendar checkIn, Calendar checkOut,
            Double totalPayment, Quincho idQuincho) throws MyException {

        try {

            Reservation reservation = new Reservation();

            validateReservation(startDate, endDate, checkIn, checkOut,
                    totalPayment, idQuincho);

            reservation.setStartDate(startDate);
            reservation.setEndDate(endDate);
            reservation.setCheckIn(checkIn);
            reservation.setCheckOut(checkOut);
            reservation.setTotalPayment(totalPayment);
            reservation.setIdQuincho(idQuincho);

        } catch (MyException ex) {
            throw new MyException(ex.getMessage());
        }

    }

    /**
     * Modífica los datos de una reservación existente basado en su id
     *
     * @param idReservation: identificador unico
     * @param startDate: Nueva fecha reserva
     * @param endDate: Finalización de la reserva
     * @param checkIn: Hora de ingreso
     * @param checkOut: Hora salida
     * @param totalPayment: Total a pagar
     * @param idQuincho: Identificador del Quincho
     */
    @Transactional
    public void updateReservation(String idReservation, Date startDate,
            Date endDate, Calendar checkIn, Calendar checkOut,
            Double totalPayment, Quincho idQuincho) {

        // Verifica si la reserva existe antes de actualizarla por su id
        Optional<Reservation> existingReservation = reservationRepository.findById(idReservation);
        if (existingReservation.isPresent()) {
            Reservation reservation = existingReservation.get();
            // Actualiza los campos necesarios de la reserva , si existe

            reservation.setStartDate(startDate);
            reservation.setEndDate(endDate);
            reservation.setCheckIn(checkIn);
            reservation.setCheckOut(checkOut);
            reservation.setTotalPayment(totalPayment);
            reservation.setIdQuincho(idQuincho);

            reservationRepository.save(reservation);
        }
    }

    /**
     * Recupera y devuelve una lista de todas las reservaciones registrados
     *
     * @return Una lista de objetos de tipo Reservation que representa a todos
     * las reservas
     */
    @Transactional
    public List<Reservation> listReservations() {
        return reservationRepository.findAll();
    }

    /**
     * Busca una reserva por su ID y devuelve la reserva si se encuentra
     * 
     * @param idReservation: Identificador unico de la reserva
     * @return : Devuelve la reserva si se encuentra.
     * @throws MyException: Se lanza si se encuentran problemas con el id,
     * con un mensaje descriptivo del problema 
     */
    @Transactional
    public Reservation getReservationById(String idReservation) throws MyException {
        Optional<Reservation> existingReservation = reservationRepository.findById(idReservation);
        if (existingReservation.isPresent()) {
            return existingReservation.get();
        } else {
            throw new MyException("La reserva con el ID proporcionado no se encontró.");
        }
    }

    /**
     * Elimina la resrva del sistema mediante su id
     *
     * @param idReservation: El identificador único de la reserva que se va a
     * eliminar
     */
    @Transactional
    public void deleteReservation(String idReservation) {
        reservationRepository.deleteById(idReservation);
    }

    /**
     * Valida los datos de una reserva antes de realizar una operación de
     * registro o modificación.Comprueba que los campos escenciales no estén
     * vacíos o nulos
     *
     * @param startDate: Fecha de inicio de la reserva
     * @param endDate: Fecha de finalización de la reserva
     * @param checkIn: Hora de ingreso
     * @param checkOut: Hora de salida
     * @param totalPayment: Total a pagar
     * @param idQuincho: Id del Quincho
     * @throws MyException: Se lanza si se encuentran problemas en los datos,
     * con un mensaje descriptivo del problema
     */
    @Transactional
    public void validateReservation(Date startDate,
            Date endDate, Calendar checkIn, Calendar checkOut,
            Double totalPayment, Quincho idQuincho) throws MyException {

        if (startDate == null) {
            throw new MyException("La fecha de inicio es obligatoria.");
        }

        if (startDate.before(new Date())) {
            throw new MyException("La fecha de inicio no puede ser anterior"
                    + "a la fecha actual.");
        }

        if (endDate == null) {
            throw new MyException("La fecha finalización es obligatoria.");
        }

        if (endDate.before(new Date())) {
            throw new MyException("La fecha finalización no puede ser anterior "
                    + "a la fecha actual.");
        }

        if (checkIn == null) {
            throw new MyException("La hora de entrada es obligatoria.");
        }

        if (checkOut == null) {
            throw new MyException("La hora de salida es obligatoria.");
        }

        if (totalPayment == null) {
            throw new MyException("El pago total es obligatorio.");
        }

        if (idQuincho == null) {
            throw new MyException("El ID del quincho es obligatorio.");
        }

    }
}