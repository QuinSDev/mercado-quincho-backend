/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mercado.quincho.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Vane Proaño
 */
    
    @Entity
@Table(name= "Quincho")
public class Quincho implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idQuincho;

    @NotEmpty
    private String name;

    @NotEmpty
    private String location;

    @NotNull
    @ElementCollection
    private List<String> phone;

    @OneToOne
    @JoinColumn(name = "photo_id")
    private PhotoQuincho photoQuincho;

    @Column(nullable = false)
    private String methodPayment;

    @ElementCollection
    @MapKeyColumn(name = "social_media")
    @Column(name = "link")
    private Map<String, String> socialMedia;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    private String availability;

    @NotEmpty
    private String description;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date reservationDate;

    @NotEmpty
    private Double prices;

    @NotEmpty
    private String typeQuincho;

    @NotNull
    private int numGuest;

    @NotNull
    private int numRoom;

    @NotNull
    private int numBed;

    @NotNull
    private int numBath;

    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOpinionesQuinchos;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idServicios;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPropietario;*/
}

    

