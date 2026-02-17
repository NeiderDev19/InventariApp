package com.demo.inventariApp.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.demo.inventariApp.application.dto.DetalleVentaRequest;
import com.demo.inventariApp.application.dto.VentaRequest;
import com.demo.inventariApp.application.port.out.DetalleVentaRepositoryPort;
import com.demo.inventariApp.application.port.out.LoteProductoRepositoryPort;
import com.demo.inventariApp.application.port.out.ProductoRepositoryPort;
import com.demo.inventariApp.application.port.out.UsuarioRepositoryPort;
import com.demo.inventariApp.application.port.out.VentaRepositoryPort;
import com.demo.inventariApp.application.port.service.VentaService;
import com.demo.inventariApp.domain.model.DetalleVenta;
import com.demo.inventariApp.domain.model.LoteProducto;
import com.demo.inventariApp.domain.model.Producto;
import com.demo.inventariApp.domain.model.Usuario;
import com.demo.inventariApp.domain.model.Venta;

@ExtendWith(MockitoExtension.class)
public class ProductoVentaServiceTest {

    @Mock
    private UsuarioRepositoryPort usuarioRepositoryPort;

    @Mock
    private ProductoRepositoryPort productoRepositoryPort;

    @Mock
    private DetalleVentaRepositoryPort detalleVentaRepositoryPort;

    @Mock
    private LoteProductoRepositoryPort loteProductoRepositoryPort;

    @Mock
    private VentaRepositoryPort ventaRepositoryPort;

    @InjectMocks
    private VentaService ventaService;

    @Test
    void deberiaRealizarVentaConStockSuficiente() {

        // Usuario
        Usuario usuario = new Usuario();
        usuario.setId(1L);

        // Producto
        Producto producto = new Producto();
        producto.setIdProducto(10L);
        producto.setStockActual(100);

        // Lote
        LoteProducto lote = new LoteProducto();
        lote.setCantidad(100);
        lote.setPrecioVentaSugerido(1000.0);
        lote.setIva(19.0);

        List<LoteProducto> lotes = List.of(lote);

        // Request de venta
        DetalleVentaRequest detalleRequest = new DetalleVentaRequest();
        detalleRequest.setIdProducto(10L);
        detalleRequest.setCantidad(2);

        VentaRequest ventaRequest = new VentaRequest();
        ventaRequest.setIdUsuario(1L);
        ventaRequest.setDetalles(List.of(detalleRequest));
        ventaRequest.setFormaPago("EFECTIVO");

        // Simulación de repositorios
        when(usuarioRepositoryPort.getById(1L)).thenReturn(usuario);
        when(productoRepositoryPort.getById(10L)).thenReturn(producto);
        when(loteProductoRepositoryPort.getByIdProducto(10L)).thenReturn(lotes);

        // Ejecución
        Venta venta = ventaService.realizarVentaProducto(ventaRequest);

        // Verificaciones
        assertNotNull(venta);
        verify(ventaRepositoryPort).saveVenta(any(Venta.class));
        verify(detalleVentaRepositoryPort).saveDetalle(any(DetalleVenta.class));
    }

    @Test
    void deberiaLanzarErrorCuandoNoHayStock() {

        Usuario usuario = new Usuario();
        usuario.setId(1L);

        Producto producto = new Producto();
        producto.setIdProducto(10L);
        producto.setStockActual(1); // stock insuficiente

        DetalleVentaRequest detalleRequest = new DetalleVentaRequest();
        detalleRequest.setIdProducto(10L);
        detalleRequest.setCantidad(5);

        VentaRequest ventaRequest = new VentaRequest();
        ventaRequest.setIdUsuario(1L);
        ventaRequest.setFormaPago("EFECTIVO");
        ventaRequest.setDetalles(List.of(detalleRequest));

        when(usuarioRepositoryPort.getById(1L)).thenReturn(usuario);
        when(productoRepositoryPort.getById(10L)).thenReturn(producto);

        assertThrows(RuntimeException.class, () -> ventaService.realizarVentaProducto(ventaRequest));
    }

}
