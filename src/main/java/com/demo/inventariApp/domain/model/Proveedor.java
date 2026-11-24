package com.demo.inventariApp.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Proveedor {

    private Long id;
    private String empresa;
    private String nit;
    private String vendedor;
    private String celular;
    private String direccion;
    private String email;
    private boolean estado;
    
}
