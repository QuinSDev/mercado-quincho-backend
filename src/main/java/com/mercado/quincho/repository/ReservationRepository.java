/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mercado.quincho.repository;

import com.mercado.quincho.entity.Reservation;
import java.util.Date;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Este repositorio se utiliza para acceder a los datos relacionados con la
 * entidad 'Reservation' en la BD. Proporciona métodos para realizar operaciones
 * de acceso a los datos relacionados con las reservaciones en la BD
 *
 * @author monte
 */
@Repository
public interface ReservationRepository extends JpaRepository<Reservation,String>{
    
       /**
     * Buscar una reservación por su id
     *
     * @param idReservation:Buscar una reserva por su ID
     * @param Date startDate, Date endDate:Buscar reservas por un rango de fechas
     * @return Un objeto Optional que puede contener una reserva si se
     * encuentra, o estar vacío si no se encuentra y permite manejar de manera
     * segura de que la reserva no exista
     */

    Optional<Reservation> findByIdReservation(String idReservation);

    Optional<Reservation> findByStartDateBetween(Date startDate, Date endDate);
}
