package com.demo.inventariApp.infrastructure.adapters.out.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.inventariApp.infrastructure.adapters.out.entity.DetalleVentaEntity;
import java.util.List;


@Repository
public interface DetalleVentaJpaRepository extends JpaRepository<DetalleVentaEntity,Long>{

    List<DetalleVentaEntity> findByVentaId(Long id);
}
