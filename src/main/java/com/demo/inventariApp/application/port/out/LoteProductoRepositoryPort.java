package com.demo.inventariApp.application.port.out;

import java.util.List;

import com.demo.inventariApp.domain.model.LoteProducto;

public interface LoteProductoRepositoryPort {

    LoteProducto save(LoteProducto loteProducto);
    LoteProducto getById(Long id);
    List<LoteProducto> getByIdProducto(Long id);
    
}
