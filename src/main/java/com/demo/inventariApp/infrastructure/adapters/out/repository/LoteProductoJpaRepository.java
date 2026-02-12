package com.demo.inventariApp.infrastructure.adapters.out.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.inventariApp.infrastructure.adapters.out.entity.LoteProductoEntity;

@Repository
public interface LoteProductoJpaRepository extends JpaRepository<LoteProductoEntity,Long> {
    List<LoteProductoEntity> findByProductoIdProductoOrderByFechaCompraAsc(Long id);
}
