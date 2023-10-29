/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mercado.quincho.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author erik_
 * @version 3.4.1 jdk11 28/10/2023
 * Entidad moldeada al UML de Usuario con uso de Spring Boot y
 * sus respectivos atributos 
 * 
 * 
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id 
    @GeneratedValue(generator = "uuid") 
    @GenericGenerator(name = "uuid", strategy = "uuid2") 
    @Column(length = 36) // Cambia la longitud a 36 caracteres para UUID
    private String idUser; 

    @NotEmpty
    private String name; 
    @NotEmpty
    private String lastName; 
    @NotEmpty
    private String address; 
    @NotEmpty
    private String phoneNumber; 
    @NotEmpty
    private String cellPhoneNumber; 

    @Email
    @NotEmpty
    private String email; 
    @NotEmpty
    private Role rol; 
    @NotEmpty
    private String password;

    @OneToOne 
    @JoinColumn(name = "photo_id") 
    private Photo photo; 
}
