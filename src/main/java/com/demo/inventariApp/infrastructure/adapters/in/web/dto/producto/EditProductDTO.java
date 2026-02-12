package com.demo.inventariApp.infrastructure.adapters.in.web.dto.producto;

import com.demo.inventariApp.infrastructure.adapters.out.entity.CategoriaEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditProductDTO {

    Long idProducto;
    String nombre;
    String descripcion;
    Long idCategoria;
    int stockMinimo;
    String codigoBarras;

}
