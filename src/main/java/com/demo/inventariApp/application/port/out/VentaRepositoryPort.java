package com.demo.inventariApp.application.port.out;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.demo.inventariApp.domain.model.Venta;

public interface VentaRepositoryPort {

    Venta saveVenta(Venta venta);
    Venta getVentaById(Long id);
    List<Venta> getVentas();
    List<Venta> getVentasByFecha(LocalDateTime inicio,LocalDateTime fin);
}
