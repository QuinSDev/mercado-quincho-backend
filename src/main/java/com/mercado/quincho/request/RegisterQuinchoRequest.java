package com.mercado.quincho.request;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

/**
 * Clase que representa la solicitud de registro de un Quincho.
 * Contiene los campos necesarios para registrar la información de un Quincho 
 * en el sistema.
 * 
 * @author QuinSDev
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterQuinchoRequest {

    String nameQuincho;
    String location;
    String description;
    String typeQuincho;
    double price;
    int numGuest;
    int numBedroom;
    int numBed;
    int numBathroom;
    List<MultipartFile> files;
}