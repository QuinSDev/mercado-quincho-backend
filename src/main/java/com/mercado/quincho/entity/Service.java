/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mercado.quincho.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;



/**
 *
 * @author Adan Suarez
 */
@Entity
@Data
public class Service {
    @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id_serv;

        private Boolean wifi;
        private Boolean parking;
        private Boolean airCon;
        private Boolean pool;
        private Boolean television;
        private Boolean washingMach;
        private Boolean kitchen;

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id_quincho;
}
