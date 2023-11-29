package com.mercado.quincho.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

/**
 * Entidad que representa una foto asociada a un quincho en el sistema. Esta
 * clase define la estructura de una foto de quincho, incluyendo atributos como
 * el identificador único, el tipo MIME, el nombre la foto y una relación
 * Many-to-One con un quincho específico. Las fotos de quincho se utilizan para
 * asociar imágenes con lugares de quinchos en el sistema.
 *
 * @author QuinSDev
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PhotoQuincho implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(length = 36) // Cambia la longitud a 36 caracteres para UUID
    private String idPhotoQuincho;

    private String mime;
    private String name;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] content;

    @ManyToOne
    @JsonBackReference
    private Quincho quincho;
    
    @Override
    public String toString() {
        return "PhotoQuincho{" +
                "idPhotoQuincho='" + idPhotoQuincho + '\'' +
                ", mime='" + mime + '\'' +
                ", name='" + name + '\'' +
                // No imprimimos 'quincho' para evitar la recursión
                '}';
    }

}
