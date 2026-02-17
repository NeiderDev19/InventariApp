package com.demo.inventariApp.application.port.service;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.inventariApp.application.dto.DetalleVentaRequest;
import com.demo.inventariApp.application.dto.VentaRequest;
import com.demo.inventariApp.application.port.in.VentaInputPort;
import com.demo.inventariApp.application.port.out.DetalleVentaRepositoryPort;
import com.demo.inventariApp.application.port.out.LoteProductoRepositoryPort;
import com.demo.inventariApp.application.port.out.ProductoRepositoryPort;
import com.demo.inventariApp.application.port.out.UsuarioRepositoryPort;
import com.demo.inventariApp.application.port.out.VentaRepositoryPort;
import com.demo.inventariApp.domain.model.DetalleVenta;
import com.demo.inventariApp.domain.model.LoteProducto;
import com.demo.inventariApp.domain.model.Producto;
import com.demo.inventariApp.domain.model.TipoPago;
import com.demo.inventariApp.domain.model.Usuario;
import com.demo.inventariApp.domain.model.Venta;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class VentaService implements VentaInputPort {

    private final UsuarioRepositoryPort usuarioRepositoryPort;

    private final ProductoRepositoryPort productoRepositoryPort;

    private final DetalleVentaRepositoryPort detalleVentaRepositoryPort;

    private final LoteProductoRepositoryPort loteProductoRepositoryPort;

    private final VentaRepositoryPort ventaRepositoryPort;

    @Override
    @Transactional
    public Venta realizarVentaProducto(VentaRequest venta) {
        Usuario usuario = usuarioRepositoryPort.getById(venta.getIdUsuario());
        if (usuario == null) {
            throw new RuntimeException("No existe el usuario");
        }
        // Crear Venta principal
        Venta newVenta = new Venta();
        newVenta.setFechaVenta(LocalDateTime.now());
        newVenta.setUsuario(usuario);
        newVenta.setTotal(0.0);
        newVenta.setSubtotal(0.0);
        newVenta.setTotalIva(0.0);
        newVenta.setFormaPago(TipoPago.valueOf(venta.getFormaPago()));
        if(venta.getFormaPago() == "EFECTIVO" || venta.getFormaPago() == "TRANSFERENCIA"){
            newVenta.setEstado("COMPLETADA");
        }
        if(venta.getFormaPago() == "CREDITO"){
            newVenta.setEstado("PENDIENTE");
        }

        newVenta.setCliente(venta.getCliente());

        newVenta = ventaRepositoryPort.saveVenta(newVenta);
        // variables acumuladoras
        Double totalVenta = 0.0;
        Double subtotal = 0.0;
        Double totalIva = 0.0;

        // Recorrer los detalles de la venta
        for (DetalleVentaRequest d : venta.getDetalles()) {
            Producto producto = productoRepositoryPort.getById(d.getIdProducto());
            if (d.getCantidad() > producto.getStockActual()) {
                throw new RuntimeException("No hay stock suficiente");
            }
            List<LoteProducto> lotes = loteProductoRepositoryPort.getByIdProducto(producto.getIdProducto());
            // Logica para descontar las cantidades del lote
            int cantidadRestante = d.getCantidad();
            for (LoteProducto lote : lotes) {

                if (lote.getCantidad() <= 0)
                    continue;

                if (cantidadRestante == 0) {
                    break;
                }

                int cantidadTomada = Math.min(cantidadRestante, lote.getCantidad()); // 20,15

                if (cantidadTomada == 0)
                    continue;

                Double subtotalLote = cantidadTomada * lote.getPrecioVentaSugerido();
                Double ivaLote = subtotalLote * lote.getIva() / 100;
                Double totalLote = subtotalLote + ivaLote;

                // Crear detalle venta por lote
                DetalleVenta detalle = new DetalleVenta();
                detalle.setCantidad(cantidadTomada);
                detalle.setPrecioUnitario(lote.getPrecioVentaSugerido());
                detalle.setSubtotal(subtotalLote);
                detalle.setIva(ivaLote);
                detalle.setTotal(totalLote);
                detalle.setProducto(producto);
                detalle.setVenta(newVenta);

                detalleVentaRepositoryPort.saveDetalle(detalle);

                // Actualizar stock del lote y producto
                lote.setCantidad(lote.getCantidad() - cantidadTomada);
                loteProductoRepositoryPort.save(lote);

                producto.setStockActual(producto.getStockActual() - cantidadTomada);
                productoRepositoryPort.saveProducto(producto);

                // Acumular totales
                subtotal += subtotalLote;
                totalIva += ivaLote;
                totalVenta += totalLote;

                cantidadRestante -= cantidadTomada;

                /*
                 * if(l.getCantidad() >= cantidadRestante){´
                 * 
                 * //descuento del lote
                 * l.setCantidad(l.getCantidad()-cantidadRestante);
                 * Double subtotalLote = cantidadRestante * l.getPrecioVentaSugerido();
                 * subtotalL = subtotalLote;
                 * subtotal += subtotalLote;
                 * Double iva = subtotalLote * l.getIva()/100;
                 * ivaProducto = iva;
                 * totalIva += iva;
                 * Double totalLote = (subtotalLote + iva) ;
                 * //guardar cantidad del lote
                 * loteProductoRepositoryPort.save(l);
                 * producto.setStockActual(producto.getStockActual()-cantidadRestante);
                 * productoRepositoryPort.saveProducto(producto);
                 * totalProducto += totalLote;
                 * cantidadRestante = 0;
                 * }else{
                 * //El lote no alcanza se usa completo
                 * int cantidadTomada = l.getCantidad();
                 * Double subtotalLote = cantidadTomada*l.getPrecioVentaSugerido();
                 * subtotalL = subtotalLote;
                 * subtotal += subtotalLote;
                 * Double iva = subtotalLote * l.getIva()/100;
                 * ivaProducto = iva;
                 * totalIva += iva;
                 * totalProducto += subtotalLote + iva;
                 * cantidadRestante -= cantidadTomada;
                 * l.setCantidad(0);
                 * producto.setStockActual(producto.getStockActual()- cantidadTomada);
                 * loteProductoRepositoryPort.save(l);
                 * }
                 * }
                 */

                /*
                 * DetalleVenta detalle = new DetalleVenta();
                 * detalle.setTotal(totalProducto);
                 * detalle.setCantidad(d.getCantidad());
                 * detalle.setIva(ivaProducto);
                 * detalle.setSubtotal(subtotalL);
                 * detalle.setProducto(producto);
                 * detalle.setVenta(newVenta);
                 * detalle.setPrecioUnitario(lote.get(0).getPrecioVentaSugerido());
                 * 
                 * detalleVentaRepositoryPort.saveDetalle(detalle);
                 * 
                 * totalVenta += totalProducto;
                 */
            }

            if (cantidadRestante > 0) {
                throw new RuntimeException("No hay Stock suficiente en los lotes");
            }

        }

        // Actualizar venta principal con totales finales
        newVenta.setTotalIva(totalIva);
        newVenta.setTotal(totalVenta);
        newVenta.setSubtotal(subtotal);

        newVenta.setCliente(venta.getCliente());

        newVenta = ventaRepositoryPort.saveVenta(newVenta);

        return newVenta;

    }

    @Override
    public byte[] generarComprobantePdf(Long idVenta) throws DocumentException {

        Venta venta = ventaRepositoryPort.getVentaById(idVenta);
        if (venta == null) {
            throw new RuntimeException("Venta no encontrada");
        }

        List<DetalleVenta> detalles = detalleVentaRepositoryPort.findByVenta(venta.getIdVenta());

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, out);

        document.open();

        // Logo
        try {
            Image logo = Image.getInstance(
                    getClass().getResource("/resources/static/img/PAPELERIA_LOGO.jpg"));
            logo.scaleToFit(80, 80);
            logo.setAlignment(Image.ALIGN_CENTER);
            document.add(logo);

        } catch (Exception e) {
            System.out.println("No se pudo cargar el logo: " + e.getMessage());
        }

        // Título
        Font fontTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
        Paragraph titulo = new Paragraph("COMPROBANTE DE VENTA", fontTitulo);
        titulo.setAlignment(Element.ALIGN_CENTER);
        document.add(titulo);

        document.add(new Paragraph(" ")); // línea vacía

        // Datos de venta
        Font fontNormal = FontFactory.getFont(FontFactory.HELVETICA, 12);
        document.add(new Paragraph("Venta: " + venta.getIdVenta(), fontNormal));
        document.add(new Paragraph("Fecha: " + venta.getFechaVenta(), fontNormal));
        // document.add(new Paragraph("Cliente: " + venta.getUsuario().getNombre(),
        // fontNormal));
        document.add(new Paragraph("Forma de pago: " + venta.getFormaPago(), fontNormal));
        document.add(new Paragraph(" "));

        // Tabla de productos
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        table.setWidths(new int[] { 2, 8, 3, 3 });

        Stream.of("Cant", "Producto", "P.Unit", "Subtotal")
                .forEach(headerTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(Color.LIGHT_GRAY);
                    header.setBorderWidth(1);
                    header.setPhrase(new Phrase(headerTitle));
                    header.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(header);
                });

        for (DetalleVenta d : detalles) {
            table.addCell(String.valueOf(d.getCantidad()));
            table.addCell(d.getProducto().getNombre());
            table.addCell(String.format("%.2f", d.getPrecioUnitario()));
            table.addCell(String.format("%.2f", d.getSubtotal()));
        }

        document.add(table);
        document.add(new Paragraph(" "));

        // Totales
        document.add(new Paragraph("Subtotal: " + String.format("%.2f", venta.getSubtotal()), fontNormal));
        document.add(new Paragraph("IVA: " + String.format("%.2f", venta.getTotalIva()), fontNormal));
        document.add(new Paragraph("Total: " + String.format("%.2f", venta.getTotal()), fontNormal));

        document.add(new Paragraph(" "));
        document.add(new Paragraph("¡Gracias por su compra!", fontNormal));

        document.close();

        return out.toByteArray();
    }

    public byte[] generarTicketPdf(Long idVenta) {
        try {
            Venta venta = ventaRepositoryPort.getVentaById(idVenta);
            if (venta == null) {
                throw new RuntimeException("Venta no encontrada");
            }

            List<DetalleVenta> detalles = detalleVentaRepositoryPort.findByVenta(venta.getIdVenta());

            // Tamaño del ticket: 80mm de ancho
            Rectangle ticketSize = new Rectangle(226, 600); // ancho fijo, alto flexible
            Document document = new Document(ticketSize, 10, 10, 10, 10);

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, out);
            document.open();

            Font fontTitle = new Font(Font.HELVETICA, 12, Font.BOLD);
            Font fontNormal = new Font(Font.HELVETICA, 9, Font.NORMAL);
            Font fontBold = new Font(Font.HELVETICA, 9, Font.BOLD);

            // Logo
            try {
                InputStream is = getClass().getResourceAsStream("/static/img/PAPELERIA_LOGO.jpg");
                if (is != null) {
                    Image logo = Image.getInstance(is.readAllBytes());
                    logo.scaleToFit(80, 80);
                    logo.setAlignment(Image.ALIGN_CENTER);
                    document.add(logo);
                }
            } catch (Exception e) {
                System.out.println("No se pudo cargar el logo: " + e.getMessage());
            }

            // Encabezado
            Paragraph tienda = new Paragraph("PAPELERIA CENTRAL", fontTitle);
            tienda.setAlignment(Element.ALIGN_CENTER);
            document.add(tienda);

            Paragraph info = new Paragraph("NIT: 123456789\nTel: 3001234567\nDir: CLL 21 5A 67 PARAMO", fontNormal);
            info.setAlignment(Element.ALIGN_CENTER);
            document.add(info);

            document.add(new Paragraph("-------------------------------------------------------", fontNormal));

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

            document.add(new Paragraph("Venta: 7000 " + venta.getIdVenta(), fontNormal));
            document.add(new Paragraph("Vendido por " + venta.getUsuario().getPrimerNombre()+ " " + venta.getUsuario().getPrimerApellido(), fontNormal));
            document.add(new Paragraph("Fecha: " + venta.getFechaVenta().format(formatter), fontNormal));
            document.add(new Paragraph("Cliente: " + venta.getCliente()) /*
                                                                          * venta.getUsuario().getNombre(), fontNormal)
                                                                          */);

            document.add(new Paragraph("-----------------------------------------------------", fontNormal));

            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100);
            table.setWidths(new float[] { 2, 6, 3, 3 });

            table.addCell(new Phrase("Cant", fontBold));
            table.addCell(new Phrase("Prod", fontBold));
            table.addCell(new Phrase("P.Unit", fontBold));
            table.addCell(new Phrase("Total", fontBold));

            for (DetalleVenta d : detalles) {
                table.addCell(new Phrase(String.valueOf(d.getCantidad()), fontNormal));
                table.addCell(new Phrase(d.getProducto().getNombre(), fontNormal));
                table.addCell(new Phrase(String.format("%.2f", d.getPrecioUnitario()), fontNormal));
                table.addCell(new Phrase(String.format("%.2f", d.getTotal()), fontNormal));
            }

            document.add(table);
            document.add(new Paragraph("------------------------------------------------------", fontNormal));

            // Totales
            document.add(new Paragraph("Subtotal: " + String.format("%.2f", venta.getSubtotal()), fontNormal));
            document.add(new Paragraph("IVA: " + String.format("%.2f", venta.getTotalIva()), fontNormal));

            Paragraph total = new Paragraph("TOTAL: " + String.format("%.2f", venta.getTotal()), fontBold);
            total.setAlignment(Element.ALIGN_RIGHT);
            document.add(total);

            document.add(new Paragraph("-----------------------------------------------------", fontNormal));

            Paragraph gracias = new Paragraph("¡Gracias por su compra!", fontNormal);
            gracias.setAlignment(Element.ALIGN_CENTER);
            document.add(gracias);

            document.close();
            return out.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("Error generando ticket", e);
        }
    }

    @Override
    public List<Venta> getVentas() {
        return ventaRepositoryPort.getVentas();
    }

    @Override
    public List<Venta> getVentasByFecha(LocalDate fecha) {
        LocalDateTime inicio = fecha.atStartOfDay();
        LocalDateTime fin = fecha.plusDays(1).atStartOfDay();
        return ventaRepositoryPort.getVentasByFecha(inicio,fin);
    }

    @Override
    public int sumaVentasDia(LocalDate fecha) {
        LocalDateTime inicio = fecha.atStartOfDay();
        LocalDateTime fin = fecha.plusDays(1).atStartOfDay();
        List<Venta> ventas = ventaRepositoryPort.getVentasByFecha(inicio,fin);
        int total = 0;
        for (Venta venta : ventas) {
            total += venta.getTotal();
        }
        return total;
    }

}
