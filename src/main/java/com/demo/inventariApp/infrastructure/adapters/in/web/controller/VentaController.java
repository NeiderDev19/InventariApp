package com.demo.inventariApp.infrastructure.adapters.in.web.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.inventariApp.application.dto.VentaRequest;
import com.demo.inventariApp.application.port.in.VentaInputPort;
import com.demo.inventariApp.application.port.service.VentaService;
import com.demo.inventariApp.domain.model.Venta;
import com.demo.inventariApp.infrastructure.adapters.in.web.dto.venta.FechaDTO;
import com.demo.inventariApp.infrastructure.adapters.in.web.dto.venta.VentaResponseDTO;
import com.demo.inventariApp.infrastructure.mapper.VentaMapper;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RequestMapping("/api/ventas")
@RestController
@AllArgsConstructor
public class VentaController {

    private final VentaInputPort ventaService;

    private final VentaMapper mapper;


    @GetMapping
    public ResponseEntity<List<VentaResponseDTO>> getVentas(){
        return ResponseEntity.ok(mapper.toResponse(ventaService.getVentas()));
    }


    @PostMapping("/realizar")
    public ResponseEntity<?> realizarVenta(@RequestBody VentaRequest request) {
        return ResponseEntity.ok(ventaService.realizarVentaProducto(request));
    }

    @GetMapping("/dia")
    public ResponseEntity<List<VentaResponseDTO>> getVentasByFecha(@RequestBody FechaDTO fechaDTO){
        return ResponseEntity.ok(mapper.toResponse(ventaService.getVentasByFecha(fechaDTO.getFecha())));
    }

    @GetMapping("/totalDia")
    public int getTotalVentaDia(@RequestBody FechaDTO fecha){
        return ventaService.sumaVentasDia(fecha.getFecha());
    }


    @GetMapping("/ticket/{id}")
    public ResponseEntity<byte[]> descargarTicket(@PathVariable Long id) {
        byte[] pdf = ventaService.generarTicketPdf(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "Factura_" + id + ".pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .body(pdf);
    }

    @GetMapping("/comprobante/{id}")
    public ResponseEntity<byte[]> descargarComprobante(@PathVariable Long id) {
        byte[] pdf = ventaService.generarComprobantePdf(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "comprobante_" + id + ".pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .body(pdf);
    }

}
