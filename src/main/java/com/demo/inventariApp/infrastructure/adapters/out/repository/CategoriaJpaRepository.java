package com.demo.inventariApp.infrastructure.adapters.out.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.inventariApp.domain.model.Categoria;

@Repository
public interface CategoriaJpaRepository extends JpaRepository<Categoria,Long> {
    boolean existsByNombre(String nombre);
}
