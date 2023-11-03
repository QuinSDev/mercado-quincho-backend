/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mercado.quincho.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

/**
 * Entidad que representa los servicios disponibles en un quincho.
 * Esta clase define la estructura de los servicios, incluyendo atributos
 * que representan sevicios como wifi, estacionamiento, aire acondicionado,
 * piscina, etc.
 * 
 * @author QuinSDev
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Service {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(length = 36) // Cambia la longitud a 36 caracteres para UUID
    private String id_serv;

    private Boolean wifi;
    private Boolean parking;
    private Boolean airConditioning;
    private Boolean pool;
    private Boolean television;
    private Boolean washingMachine;
    private Boolean kitchen;
    private Boolean refrigerator;
    private Boolean microwave;
    private Boolean kitchenUtensil;
       
}