package com.mercado.quincho.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

/**
 * Entidad que representa un usuario en el sistema.
 * Esta clase define la estructura de un usuario, incluyendo sus atributos
 * como nombre, apellido, dirección, número de teléfono, correco eletrónico, rol
 * y contraseña. También se relaciona con una foto de perfil a través de una 
 * relación de uno a uno.
 * 
 * @author QuinSDev
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

    private String name; 
    private String lastName; 
    private String address; 
    private String phoneNumber;

    @Email
    private String email;
    
    @Enumerated(EnumType.STRING)
    private Role rol; 
    private String password;

    @OneToOne 
    @JoinColumn(name = "id_photo") 
    private PhotoUser photo; 
}