/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mercado.quincho.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    private String id;
    
    private int qualification;
    private String comment;
    
    @ManyToOne
    @JsonBackReference
    private User user;
    
    @ManyToOne
    @JsonBackReference
    private Quincho quincho;
    
    @Override
    public String toString() {
        return "CustomerOpinion{" +
                "id='" + id + '\'' +
                ", comentario='" + comment + '\'' +
                // No imprimimos 'photos' para evitar la recursión
                '}';
    }
}
