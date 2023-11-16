package com.mercado.quincho.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa la respuesta de un Quincho.
 * Contiene un mensaje relacionado con la operación realizada.
 * 
 * @author QuinSDev
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuinchoResponse {
    
    String msg;
    
}