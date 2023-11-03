package com.mercado.quincho.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa una solicitud de registro. Contiene los datos 
 * necesarios para crear una cuenta de usuario, incluyendo email del usuario,
 * contraseña, confirmación de contraseña, nombre, apellido, número de teléfono
 * y dirección.
 * 
 * @author QuinSDev
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    
    String email;
    String password;
    String confirmPassword;
    String name;
    String lastName;
    String phoneNumber;
    String address;
    
}
