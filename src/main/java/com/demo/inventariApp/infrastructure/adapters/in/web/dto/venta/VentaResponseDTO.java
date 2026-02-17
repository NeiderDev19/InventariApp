package com.demo.inventariApp.infrastructure.adapters.in.web.dto.venta;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VentaResponseDTO {

    Long idVenta;
    LocalDateTime fechaVenta;
    int subtotal;
    int totalIva;
    int total;
    String estado;
    String vendedor;
    String cliente;

}
