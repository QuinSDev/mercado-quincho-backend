package com.mercado.quincho.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
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
 * Entidad que representa una foto de usuario en el sistema.
 * Esta clase define la estructura de una foto de usuario, incluyendo sus 
 * atributos como el identificador único, el tipo MIME, el nombre de la foto y
 * su contenido en formato de bytes.
 * 
 * @author QuinSDev
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PhotoUser {

    @Id 
    @GeneratedValue(generator = "uuid") 
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(length = 36) // Cambia la longitud a 36 caracteres para UUID
    private String idPhoto; 

    private String mime; 
    private String name; 

    @Lob 
    @Basic(fetch = FetchType.LAZY) 
    private byte[] content; 

   
}