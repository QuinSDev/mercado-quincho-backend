package com.mercado.quincho.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

/**
 * Entidad que representa una reserva en el sistema.
 * Esta clase define la estructura de una reserva, incluyendo atributos como
 * el identificador único, la fecha de inicio, fecha de fin, hora de check-in,
 * hora de check-out, el pago total y las relaciones Many-to-One con usuarios
 * y quinchos asociados.
 * 
 * @author monte
 * @author QuinSDev
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(length = 36) // Cambia la longitud a 36 caracteres para UUID
    private String idReservation;

    private LocalDate startDate;
    private LocalDate endDate;

    private LocalDateTime checkIn;
    private LocalDateTime checkOut;

    private Double totalPayment;
    
    @ManyToOne
    @JsonBackReference
    private User user;

    @ManyToOne
    @JsonBackReference
    private Quincho quincho;

}