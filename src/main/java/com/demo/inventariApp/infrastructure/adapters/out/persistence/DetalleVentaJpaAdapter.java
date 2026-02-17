package com.demo.inventariApp.infrastructure.adapters.out.persistence;

import java.util.List;

import org.springframework.stereotype.Component;

import com.demo.inventariApp.application.port.out.DetalleVentaRepositoryPort;
import com.demo.inventariApp.domain.model.DetalleVenta;
import com.demo.inventariApp.infrastructure.adapters.out.entity.DetalleVentaEntity;
import com.demo.inventariApp.infrastructure.adapters.out.repository.DetalleVentaJpaRepository;
import com.demo.inventariApp.infrastructure.mapper.DetalleVentaMapper;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class DetalleVentaJpaAdapter implements DetalleVentaRepositoryPort {

    private final DetalleVentaJpaRepository detalleVentaJpaRepository;

    private final DetalleVentaMapper mapper;
    
    @Override
    public DetalleVenta saveDetalle(DetalleVenta detalleVenta) {
        DetalleVentaEntity entity = mapper.domainToEntity(detalleVenta);
        DetalleVentaEntity save = detalleVentaJpaRepository.save(entity);
        return mapper.entityToDomain(save);
        
    }

    @Override
    public List<DetalleVenta> findByVenta(Long idVenta) {
        List<DetalleVentaEntity> detalles = detalleVentaJpaRepository.findByVentaId(idVenta);
        return mapper.toListDomain(detalles);
    }



}
