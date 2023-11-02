package com.mercado.quincho.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.GenericGenerator;

/**
 * Entidad que representa una foto asociada a un quincho en el sistema.
 * Esta clase define la estructura de una foto de quincho, incluyendo atributos
 * como el identificador único, el tipo MIME, el nombre la foto y una relación
 * Many-to-One con un quincho específico. Las fotos de quincho se utilizan para
 * asociar imágenes con lugares de quinchos en el sistema.
 * 
 * @author QuinSDev
 */
public class PhotoQuincho {
    
    @Id 
    @GeneratedValue(generator = "uuid") 
    @GenericGenerator(name = "uuid", strategy = "uuid2") 
    @Column(length = 36) // Cambia la longitud a 36 caracteres para UUID
    private String idPhotoQuincho;
    
    private String mime;
    private String name;
    
    @ManyToOne
    private Quincho quincho;
    
}