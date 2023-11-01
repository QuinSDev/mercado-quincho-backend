package com.mercado.quincho.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
    private String idQuincho;
    
    private String nameQuincho;
    private String location;
    private String description;
    private String typeQuincho;
    private double price;
    private int numGuest;
    private int numBedroom;
    private int numBed;
    private int numBathroom;
    
    @OneToMany(mappedBy = "quincho", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PhotoQuincho> photos;
    
}