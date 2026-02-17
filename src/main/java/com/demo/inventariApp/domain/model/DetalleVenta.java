package com.demo.inventariApp.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DetalleVenta {

    private Long idDetalle;
    private int cantidad;
    private Double precioUnitario;
    private Double subtotal;
    private Venta venta;
    private Producto producto;
    private Double iva;
    private Double total;

}
