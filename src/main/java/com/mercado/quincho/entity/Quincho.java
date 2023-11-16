package com.mercado.quincho.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

/**
 * Entidad que representa un quincho en el sistema.
 * Esta clase define la estructura de un quincho, incluyendo atributos como
 * el identificador único, el nombre del quincho, la ubicación, descripción,
 * tipo de quincho, precio, capacidad de huéspedes, número de habitaciones,
 * número de camas, número de baños y una relación One-to-Many con fotos de
 * quincho asociadas.
 * 
 * @author QuinSDev
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Quincho implements Serializable{
    
    @Id 
    @GeneratedValue(generator = "uuid") 
    @GenericGenerator(name = "uuid", strategy = "uuid2") 
    @Column(length = 36) // Cambia la longitud a 36 caracteres para UUID
    private String id;
    
    private String nameQuincho;
    private String location;
    private String description;
    private String typeQuincho;
    private double price;
    private int numGuest;
    private int numBedroom;
    private int numBed;
    private int numBathroom;
    
    @OneToMany
    @JoinColumn(name = "quincho_id") 
    @JsonManagedReference
    private List<PhotoQuincho> photos;
    
    @ManyToOne
    @JsonBackReference
    private User user;
    
    @Override
    public String toString() {
        return "Quincho{" +
                "id='" + id + '\'' +
                ", nameQuincho='" + nameQuincho + '\'' +
                // No imprimimos 'photos' para evitar la recursión
                '}';
    }
    
}