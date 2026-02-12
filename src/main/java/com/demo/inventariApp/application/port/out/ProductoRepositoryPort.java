package com.demo.inventariApp.application.port.out;

import java.util.List;
import java.util.Optional;

import com.demo.inventariApp.domain.model.Categoria;
import com.demo.inventariApp.domain.model.Producto;

public interface ProductoRepositoryPort {

    Producto saveProducto(Producto producto);
    List<Producto> getProductos();
    Producto getById(Long id);
    List<Producto> getCategoriaById(Long id);
    boolean existByNombre(String nombre);
    boolean existByNombreAndIdNot(String nombre,Long id);
    List<Producto> getProductosByFiltros(String filtro);
    Producto getProductoByCodigoBarras(String codigoBarras);
    
}
