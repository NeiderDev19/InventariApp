package com.demo.inventariApp.domain.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Categoria {

    private Long idCategoria;
    private String nombre;
    private boolean activo;
    private LocalDate fechaCreacion = LocalDate.now();
    
}
