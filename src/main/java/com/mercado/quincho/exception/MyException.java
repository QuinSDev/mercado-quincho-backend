package com.mercado.quincho.exception;

/**
 * Una excepción personalizada que se utiliza para manejar errores específicos
 * en la aplicación. Puede lanzarse con un mensaje descriptivo cuando ocurre
 * una situación excepcional.
 * 
 * @author QuinSDev
 */
public class MyException extends Exception{
    
    /**
     * Crea una instancia de MyExcepción con un mensaje de error personalizado.
     * 
     * @param msg: El mensaje descriptivo de la excepción.
     */
    public MyException(String msg) {
        super(msg);
    }
    
}
