package com.demo.inventariApp.infrastructure.adapters.out.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.inventariApp.infrastructure.adapters.out.entity.CategoriaEntity;

@Repository
public interface CategoriaJpaRepository extends JpaRepository<CategoriaEntity,Long> {
    boolean existsByNombre(String nombre);
    boolean existsByNombreAndIdCategoriaNot(String nombre,Long id);
}
