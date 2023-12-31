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
 * La clase MultimediaOpinion permite a los clientes almacenar y gestionar
 * sus opiniones acerca de como fue su experiencia en los quinchos.
 * Los quinchos pueden ser evaluados y comentados por los clientes para
 * proporcionar retroalimentación sobre la calidad del servicio y las 
 * instalaciones.
 * @author EdwarVelasquez
 * @version 1.0 2/11/2023
 */

@Data   
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MultimediaOpinion {
   
    @Id 
    @GeneratedValue(generator = "uuid") 
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(length = 36) // Cambia la longitud a 36 caracteres para UUID
    
    /**
     * Identificador único de la opinión multimedia.
     */
    private String idMultimediaOpinion;
    
    /**
     * Tipo MIME (Multipurpose Internet Mail Extensions) del contenido 
     * multimedia.
     * Ejemplo: "image/jpeg", "video/mp4", "audio/mp3".
     */
    private String mime;
    
    /**
     * Contenido multimedia representado como una cadena de datos.
     */
    private String content;
    
    /**
     * Nombre descriptivo del contenido multimedia.
     */
    private String name;
}
