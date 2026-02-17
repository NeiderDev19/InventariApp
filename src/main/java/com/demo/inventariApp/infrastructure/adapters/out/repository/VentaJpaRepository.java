package com.demo.inventariApp.infrastructure.adapters.out.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.inventariApp.infrastructure.adapters.out.entity.VentaEntity;
import java.util.List;
import java.time.LocalDateTime;


@Repository
public interface VentaJpaRepository  extends JpaRepository<VentaEntity,Long>{

    List<VentaEntity> findByFechaVentaBetween(LocalDateTime inicio,LocalDateTime fin);

}
