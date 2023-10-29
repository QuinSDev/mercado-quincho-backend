/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mercado.quincho.entity;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author erik_
 * @version 3.4.1 jdk11 28/10/2023
 * Entidad creada con el fin de ingresar archivos multimedia con Spring Boot
 * "En este caso imagenes" 
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Photo {

    @Id 
    @GeneratedValue(generator = "uuid") 
    @GenericGenerator(name = "uuid", strategy = "uuid2") 
    private String idPhoto; 

    private String mime; 

    private String name; 

    @Lob 
    @Basic(fetch = FetchType.LAZY) 
    private byte[] content; 

   
}
