package com.demo.inventariApp.application.port.in;

import java.util.List;
import java.util.Optional;

import com.demo.inventariApp.domain.model.Producto;

public interface ProductoInputPort {

    List<Producto> listarProductos();
    Producto crearProductos(Producto producto,Long idCategoria);
    Producto editarProducto(Producto producto,Long id,Long idCategoria);
    void cambiarEstado(Long idProducto,boolean estado);
    Producto getProductoById(Long id);
    List<Producto> getProductsByCategoria(Long id);
    List<Producto> getProductoByFiltro(String filtro);
    Producto getProductoByCodigoBarras(String codigoBarras);
    
    
} 
