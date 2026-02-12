package com.demo.inventariApp.application.port.in;

import java.util.List;

import com.demo.inventariApp.domain.model.LoteProducto;

public interface LoteProductoInputPort {

    LoteProducto agregarLote(LoteProducto loteProducto,Long id);
    List<LoteProducto> getLoteProductoByProducto(Long idProducto);

}
