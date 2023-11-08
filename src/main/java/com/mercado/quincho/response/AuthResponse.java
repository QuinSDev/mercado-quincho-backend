package com.mercado.quincho.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa una respuesta de autenticación. Contiene un token de
 * autenticación que se genera después de un proceso de autenticación.
 * 
 * @author QuinSDev
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
        
    String token;
    String msg;
    boolean success;
    
}