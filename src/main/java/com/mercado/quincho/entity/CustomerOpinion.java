/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mercado.quincho.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
/**
 * Clase para las opiniones de los clientes, estos van a poder registrar,
 * archivos multimedia del lugar, dejar un comentario y generar una
 * calificación del quincho.
 * @author EdwarVelasquez
 * @version 1.0 1/11/2023
 */
@Data   
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CustomerOpinion {
    
    @Id 
    @GeneratedValue(generator = "uuid") 
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(length = 36) // Cambia la longitud a 36 caracteres para UUID    
    private String idCustomerClient;
    private String multimediaOpinion;
    private int qualification;
    private String comment;    
}
