/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mercado.quincho.entity;

import java.sql.Time;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;



/**
 * Entidad que representa una reserva en el sistema.
 * Esta clase define la estructura de una reserva, incluyendo sus atributos
 * como fecha de inicio y fecha fin de la reserva, la hora del check-in
 * y del check-out, el total a pagar de la reserva,el quincho asociado con la 
 * reserva:representa una relación Many-to-One con la entidad Quincho
 * 
 *
 * @author monte
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

    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Temporal(TemporalType.DATE)
    private Date endDate;

    @Temporal(TemporalType.TIME)
    private Time checkIn;

    @Temporal(TemporalType.TIME)
    private Time checkOut;

    private Double totalPayment;

    @ManyToOne
    @JoinColumn(name = "idQuincho")
    private Quincho idQuincho;

   

}
