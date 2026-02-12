package com.demo.inventariApp.infrastructure.adapters.in.web.dto.loteProducto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoteCreateDTO {

    Long idProducto;
    Double precioCompra;
    Double precioVentaSugerido;
    Double iva;
    Double cantidad;
    LocalDateTime fechaCompra = this.fechaCompra.now();
    String proveedor;

}
