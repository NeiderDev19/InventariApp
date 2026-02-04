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
public class Producto {

    private Long idProducto;
    private String nombre;
    private String descripcion;
    private Double precioCompra;
    private Double precioVenta;
    private Double iva;
    private int stock;
    private int stockMinimo;
    private UnidadProducto unidad;
    private String codigoBarras;
    private boolean activo;
    private Categoria categoria;
    private LocalDateTime fechaCreacion;
    
}
