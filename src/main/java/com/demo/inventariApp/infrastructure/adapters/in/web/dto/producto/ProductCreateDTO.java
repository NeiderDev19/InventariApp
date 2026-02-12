package com.demo.inventariApp.infrastructure.adapters.in.web.dto.producto;

import java.time.LocalDateTime;

import com.demo.inventariApp.domain.model.Categoria;
import com.demo.inventariApp.domain.model.UnidadProducto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductCreateDTO {

    String nombre;
    String descripcion;
    int stockMinimo;
    String unidad;
    String codigoBarras;
    LocalDateTime fechaCreacion = LocalDateTime.now();

}
