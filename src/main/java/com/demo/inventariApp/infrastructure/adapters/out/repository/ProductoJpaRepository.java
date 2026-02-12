package com.demo.inventariApp.infrastructure.adapters.out.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demo.inventariApp.domain.model.Producto;
import com.demo.inventariApp.infrastructure.adapters.out.entity.CategoriaEntity;
import com.demo.inventariApp.infrastructure.adapters.out.entity.ProductoEntity;

@Repository
public interface ProductoJpaRepository extends JpaRepository<ProductoEntity,Long> {

    /* CategoriaEntity getCategoriaEntityByIdCategoria(Long idCategoria); */
    List<ProductoEntity> findByCategoriaIdCategoria(Long idCategoria);
    boolean existsByNombre(String nombre);
    boolean existsByNombreAndIdProductoNot(String nombre,Long idProducto); 
    @Query("SELECT p FROM ProductoEntity p WHERE p.nombre LIKE CONCAT('%',:filtro,'%') OR p.descripcion LIKE CONCAT('%',:filtro,'%') OR p.categoria.nombre LIKE CONCAT('%',:filtro,'%')")
    List<ProductoEntity> getProductosByFiltro(String filtro);
    ProductoEntity findByCodigoBarras(String codigoBarras);


}
