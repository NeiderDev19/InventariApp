package com.demo.inventariApp.application.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VentaRequest {
    @NotNull
    Long idUsuario;
    String formaPago;
    List<DetalleVentaRequest> detalles;
    String cliente;
}