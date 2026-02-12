package com.demo.inventariApp.application.port.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.demo.inventariApp.application.port.in.LoteProductoInputPort;
import com.demo.inventariApp.application.port.out.LoteProductoRepositoryPort;
import com.demo.inventariApp.application.port.out.ProductoRepositoryPort;
import com.demo.inventariApp.domain.model.LoteProducto;
import com.demo.inventariApp.domain.model.Producto;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LoteProductoService implements LoteProductoInputPort{

    private final ProductoRepositoryPort productoRepositoryPort;

    private final LoteProductoRepositoryPort loteProductoRepositoryPort;
    
    @Override
    @Transactional
    public LoteProducto agregarLote(LoteProducto loteProducto, Long id) {
        Producto producto = productoRepositoryPort.getById(id);
        if(loteProducto.getCantidad() <= 0){
            throw new RuntimeException("La cantidad debe ser mayor a 0");
        }
        loteProducto.setProducto(producto);
        //Agrego el stock al producto
        producto.agregarStock(loteProducto.getCantidad());
        productoRepositoryPort.saveProducto(producto);
        return loteProductoRepositoryPort.save(loteProducto);
    }

    @Override
    public List<LoteProducto> getLoteProductoByProducto(Long idProducto) {
        List<LoteProducto> lotes = loteProductoRepositoryPort.getByIdProducto(idProducto);
        if(lotes.isEmpty()){
            throw new RuntimeException("No hay lotes agregados para este producto");
        }
        return lotes;
    }

}
