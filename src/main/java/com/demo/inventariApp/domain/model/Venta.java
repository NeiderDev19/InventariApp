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
public class Venta {

    private Long idVenta;
    private LocalDateTime fechaVenta;
    private Double total;
    private TipoPago formaPago;
    private String estado;
    private Usuario usuario;


}
