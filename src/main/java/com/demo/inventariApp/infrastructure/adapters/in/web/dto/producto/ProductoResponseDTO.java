package com.demo.inventariApp.infrastructure.adapters.in.web.dto.producto;

import java.time.LocalDateTime;

import com.demo.inventariApp.domain.model.Categoria;
import com.demo.inventariApp.domain.model.UnidadProducto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductoResponseDTO {

    Long idProducto;
    String nombre;
    String descripcion;
    int stockActual;
    int stockMinimo;
    String unidad;
    String codigoBarras;
    boolean activo;
    Categoria categoria;
    LocalDateTime fechaCreacion;


}
