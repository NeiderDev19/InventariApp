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
public class LoteProducto {

    private Long id;
    private Producto producto;
    private Double precioCompra;
    private Double precioVentaSugerido;
    private Double iva;
    private int cantidad;
    private LocalDateTime fechaCompra;
    private String proveedor;
    private int cantidadAgregada;

}
