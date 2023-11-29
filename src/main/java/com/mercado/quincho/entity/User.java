package com.mercado.quincho.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.Collection;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Entidad que representa un usuario en el sistema.
 * Esta clase define la estructura de un usuario, incluyendo sus atributos
 * como nombre, apellido, dirección, número de teléfono, correco eletrónico, rol
 * y contraseña. También se relaciona con una foto de perfil a través de una 
 * relación de uno a uno y con quinchos y reservas a través de una relación
 * de uno a muchos(One-To-Many)
 * 
 * Implementa la interfaz UserDetails de Spring Secuiry para habilitar la 
 * autenticación y autorización de usuarios en la aplicación.
 * 
 * @author QuinSDev
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User implements UserDetails{

    @Id 
    @GeneratedValue(generator = "uuid") 
    @GenericGenerator(name = "uuid", strategy = "uuid2") 
    @Column(length = 36) // Cambia la longitud a 36 caracteres para UUID
    private String id; 

    private String name; 
    private String lastName; 
    private String address; 
    private String phoneNumber;
    private boolean active;
    
    @Email
    private String email;
    
    @Enumerated(EnumType.STRING)
    private Role role; 
    private String password;

    @OneToOne 
    @JoinColumn(name = "id_photo") 
    private PhotoUser photo; 
    
    @OneToMany
    @JoinColumn(name = "user_id")
    @JsonManagedReference
    private List<Quincho> quincho;
    
    @OneToMany
    @JoinColumn(name = "user_id")
    @JsonManagedReference
    private List<Reservation> reservation;
    
    @OneToMany
    @JoinColumn(name = "user_id")
    @JsonManagedReference
    private List<CustomerOpinion> opinions;
    
    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", nameUser='" + name + '\'' +
                // No imprimimos 'photos' para evitar la recursión
                '}';
    }
    
    /* Métodos requeridos por UserDetails para la autenticación y autorización
    de Spring Security */
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority((role.name())));
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}