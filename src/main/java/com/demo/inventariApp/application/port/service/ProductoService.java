package com.demo.inventariApp.application.port.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.demo.inventariApp.application.port.in.ProductoInputPort;
import com.demo.inventariApp.application.port.out.CategoriaRepositoryPort;
import com.demo.inventariApp.application.port.out.ProductoRepositoryPort;
import com.demo.inventariApp.domain.model.Categoria;
import com.demo.inventariApp.domain.model.Producto;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductoService implements ProductoInputPort {

    private final ProductoRepositoryPort productoRepositoryPort;

    private final CategoriaRepositoryPort categoriaRepositoryPort;
    
    @Override
    public List<Producto> listarProductos() {
        return productoRepositoryPort.getProductos();
    }

    @Override
    public Producto crearProductos(Producto producto, Long idCategoria) {
        Categoria categoria = categoriaRepositoryPort.getById(idCategoria);
        producto.setCategoria(categoria);
         if(producto.getNombre()!=null && producto.getNombre().isBlank()){
            throw new RuntimeException("El nombre no puede estar vacio");
        }
        return productoRepositoryPort.saveProducto(producto);
    }

    @Override
    public Producto editarProducto(Producto producto,Long id,Long idCategoria) {
        Producto existente = productoRepositoryPort.getById(id);
        //Validaciones
        if(existente==null){
            throw new RuntimeException("Producto no encontrado");
        }

        if(producto.getNombre()!=null && producto.getNombre().isBlank()){
            throw new RuntimeException("El nombre no puede estar vacio");
        }
        if(producto.getStockMinimo() <= 0 ){
            throw new RuntimeException("El stock minimo debe ser mayor a 0");
        }
        //cambios
        existente.setNombre(producto.getNombre());
        existente.setDescripcion(producto.getDescripcion());
        existente.setStockMinimo(producto.getStockMinimo());

        //validacion producto vendido
        existente.setCodigoBarras(producto.getCodigoBarras());

        if(idCategoria!=null){
            Categoria categoria = categoriaRepositoryPort.getById(idCategoria);
            existente.setCategoria(categoria);
        }

        return productoRepositoryPort.saveProducto(existente);

    }

    @Override
    public void cambiarEstado(Long idProducto, boolean estado) {
        Producto producto = productoRepositoryPort.getById(idProducto);
        producto.setActivo(estado);
        productoRepositoryPort.saveProducto(producto);
    }

    @Override
    public Producto getProductoById(Long id) {
        return productoRepositoryPort.getById(id);
    }

    @Override
    public List<Producto> getProductsByCategoria(Long id) {
        return productoRepositoryPort.getCategoriaById(id);
    }

    @Override
    public List<Producto> getProductoByFiltro(String filtro) {
        return productoRepositoryPort.getProductosByFiltros(filtro);
    }

    @Override
    public Producto getProductoByCodigoBarras(String codigoBarras) {
        return productoRepositoryPort.getProductoByCodigoBarras(codigoBarras);
    }



}
