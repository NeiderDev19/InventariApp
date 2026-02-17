package com.demo.inventariApp.application.port.in;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.demo.inventariApp.application.dto.VentaRequest;
import com.demo.inventariApp.domain.model.Venta;

public interface VentaInputPort {

    Venta realizarVentaProducto(VentaRequest venta);
    byte[] generarComprobantePdf(Long idVenta);
    byte[] generarTicketPdf(Long idVenta);
    List<Venta> getVentas();
    List<Venta> getVentasByFecha(LocalDate fecha);
    int sumaVentasDia(LocalDate fecha);

}
