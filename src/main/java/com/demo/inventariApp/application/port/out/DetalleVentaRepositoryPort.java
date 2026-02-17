package com.demo.inventariApp.application.port.out;

import java.util.List;

import com.demo.inventariApp.domain.model.DetalleVenta;

public interface DetalleVentaRepositoryPort {

    DetalleVenta saveDetalle(DetalleVenta detalleVenta);
    List<DetalleVenta> findByVenta(Long idVenta);

}
