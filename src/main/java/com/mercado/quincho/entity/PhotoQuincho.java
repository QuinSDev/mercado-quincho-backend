/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mercado.quincho.entity;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

/**
 *
 * @author Vane Proaño
 */
@Entity
class PhotoQuincho {
  @Id 
  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String idPhoto; 

    private String mime; 

    private String name; 

    @Lob 
    @Basic(fetch = FetchType.LAZY) 
    private byte[] content; 

   
}

