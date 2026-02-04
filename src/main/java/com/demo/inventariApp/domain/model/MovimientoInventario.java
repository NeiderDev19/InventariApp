package com.demo.inventariApp.domain.model;


import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovimientoInventario {

    private Long idMovimiento;
    private Producto producto;
    private int cantidad;
    private TipoMovimiento tipo;
    private String motivo;
    private LocalDateTime fecha;
    private Double costoUnitario;
    private Usuario usuario;

    
}
