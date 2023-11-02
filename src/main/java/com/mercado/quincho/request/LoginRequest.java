package com.mercado.quincho.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * La clase LoginRequest representa una solicitud de inicio de sesión que contiene
 * los datos necesarios para autenticar a un usuario en el sistema.
 * 
 * @author QuinSDev
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    
    String email;
    String password;
    
}
