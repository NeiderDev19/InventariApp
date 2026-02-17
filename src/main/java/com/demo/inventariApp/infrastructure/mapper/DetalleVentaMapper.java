package com.demo.inventariApp.infrastructure.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.demo.inventariApp.domain.model.DetalleVenta;
import com.demo.inventariApp.infrastructure.adapters.out.entity.DetalleVentaEntity;

@Mapper(componentModel = "spring", uses = {VentaMapper.class, ProductoMapper.class})
public interface DetalleVentaMapper {

    @Mapping(source = "id", target = "idDetalle")
    DetalleVenta entityToDomain(DetalleVentaEntity entity);

    @Mapping(source = "idDetalle", target = "id")
    DetalleVentaEntity domainToEntity(DetalleVenta domain);

    List<DetalleVenta> toListDomain(List<DetalleVentaEntity> detalles);
    
}