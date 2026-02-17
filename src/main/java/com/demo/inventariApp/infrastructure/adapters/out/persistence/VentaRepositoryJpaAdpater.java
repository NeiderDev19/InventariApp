package com.demo.inventariApp.infrastructure.adapters.out.persistence;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Component;

import com.demo.inventariApp.application.port.out.VentaRepositoryPort;
import com.demo.inventariApp.domain.model.Venta;
import com.demo.inventariApp.infrastructure.adapters.out.entity.VentaEntity;
import com.demo.inventariApp.infrastructure.adapters.out.repository.VentaJpaRepository;
import com.demo.inventariApp.infrastructure.mapper.VentaMapper;

import lombok.AllArgsConstructor;


@Component
@AllArgsConstructor
public class VentaRepositoryJpaAdpater implements VentaRepositoryPort  {

    private final VentaJpaRepository ventaJpaRepository;

    private final VentaMapper mapper;

    @Override
    public Venta saveVenta(Venta venta) {
        VentaEntity entity = mapper.domainToEntity(venta);
        VentaEntity save = ventaJpaRepository.save(entity);
        return mapper.EntityToDomain(save);   
    }

    @Override
    public Venta getVentaById(Long id) {
        VentaEntity venta = ventaJpaRepository.findById(id).orElseThrow(()-> new RuntimeException("No se encontro la venta"));
        return mapper.EntityToDomain(venta);
    }

    @Override
    public List<Venta> getVentas() {
        return mapper.entityListtoDomainList(ventaJpaRepository.findAll());
    }

    @Override
    public List<Venta> getVentasByFecha(LocalDateTime inicio,LocalDateTime fin) {
        return mapper.entityListtoDomainList(ventaJpaRepository.findByFechaVentaBetween(inicio, fin));
    }



}
