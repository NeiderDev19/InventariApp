package com.demo.inventariApp.infrastructure.adapters.out.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.demo.inventariApp.application.port.out.ProductoRepositoryPort;
import com.demo.inventariApp.domain.model.Categoria;
import com.demo.inventariApp.domain.model.Producto;
import com.demo.inventariApp.infrastructure.adapters.out.entity.ProductoEntity;
import com.demo.inventariApp.infrastructure.adapters.out.repository.ProductoJpaRepository;
import com.demo.inventariApp.infrastructure.mapper.CategoriaMapper;
import com.demo.inventariApp.infrastructure.mapper.ProductoMapper;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ProductoRepositoryJpaAdapter implements ProductoRepositoryPort  {


    private final ProductoJpaRepository productoJpaRepository;

    private final CategoriaMapper mapperC;

    private final ProductoMapper mapper;

    @Override
    public Producto saveProducto(Producto producto) {
        ProductoEntity entity = mapper.domainToEntity(producto);
        ProductoEntity save = productoJpaRepository.save(entity);
        return mapper.EntityToDomain(save);
    }

    @Override
    public List<Producto> getProductos() {
        return mapper.entityListtoDomainList(productoJpaRepository.findAll());
    }

    @Override
    public Producto getById(Long id) {
        return mapper.EntityToDomain(productoJpaRepository.findById(id).orElseThrow(()->new RuntimeException("No se encontro el producto")));
    }

    @Override
    public List<Producto> getCategoriaById(Long id) {
        return mapper.entityListtoDomainList(productoJpaRepository.findByCategoriaIdCategoria(id));
    }

    @Override
    public boolean existByNombre(String nombre) {
        return productoJpaRepository.existsByNombre(nombre);
    }

    @Override
    public boolean existByNombreAndIdNot(String nombre, Long id) {
        return productoJpaRepository.existsByNombreAndIdProductoNot(nombre, id);
    }

    @Override
    public List<Producto> getProductosByFiltros(String filtro) {
        List<ProductoEntity> productos = productoJpaRepository.getProductosByFiltro(filtro);
        return mapper.entityListtoDomainList(productos);
    }

    @Override
    public Producto getProductoByCodigoBarras(String codigoBarras) {
        ProductoEntity producto = productoJpaRepository.findByCodigoBarras(codigoBarras);
        return mapper.EntityToDomain(producto);
    }
    
    
    

}
