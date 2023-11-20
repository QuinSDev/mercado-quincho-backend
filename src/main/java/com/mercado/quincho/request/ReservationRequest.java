package com.mercado.quincho.request;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa una solicitud de reserva.
 * Contiene datos de reserva en formato de cadena que se pueden convertir a tipos
 * de fecha y hora apropiados utilizando métodos de conversión específicos.
 * 
 * @author QuinSDev
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReservationRequest {

    private String starDate;
    private String endDate;
    private String checkIn;
    private String checkOut;
    private Double totalPayment;

    /**
     * Obtiene la fecha de inicio de la reserva como LocalDate.
     * @return Fecha de inicio de la reserva en formato LocalDate.
     */
    public LocalDate getStartDateAsDateTime() {
        return LocalDate.parse(starDate, DateTimeFormatter.ISO_DATE);
    }

    /**
     * Obtiene la fecha de finalización de la reserva como LocalDate.
     * @return Fecha de finalización de la reserva en formato LocalDate.
     */
    public LocalDate getEndDateAsDateTime() {
        return LocalDate.parse(endDate, DateTimeFormatter.ISO_DATE);
    }
    
    /**
     * Obtiene la hora de check-in de la reserva como LocalDateTime.
     * @return Hora de check-in de la reserva en formato LocalDateTime.
     */
    public LocalDateTime getCheckInAsDateTime() {
        return LocalDateTime.parse(checkIn, DateTimeFormatter.ISO_DATE_TIME);
    }

    /**
     * Obtiene la hora de check-out de la reserva como LocalDateTime.
     * @return Hora de check-out de la reserva en formato LocalDateTime.
     */
    public LocalDateTime getCheckOutAsDateTime() {
        return LocalDateTime.parse(checkOut, DateTimeFormatter.ISO_DATE_TIME);
    }
}