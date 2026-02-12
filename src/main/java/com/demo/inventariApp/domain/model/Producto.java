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
    private int stockActual;
    private int stockMinimo;
    private UnidadProducto unidad;
    private String codigoBarras;
    private boolean activo = true;
    private Categoria categoria;
    private LocalDateTime fechaCreacion;

    public void agregarStock(int cantidad){
        if(cantidad <= 0){
            throw new RuntimeException("CantidadInvalida");
        }
        this.stockActual += cantidad;
    }
    
}
