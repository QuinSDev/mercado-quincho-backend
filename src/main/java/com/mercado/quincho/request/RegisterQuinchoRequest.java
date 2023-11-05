package com.mercado.quincho.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
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
}
