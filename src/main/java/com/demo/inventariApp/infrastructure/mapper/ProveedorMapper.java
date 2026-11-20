package com.demo.inventariApp.infrastructure.mapper;

import org.mapstruct.Mapper;

import com.demo.inventariApp.domain.model.Proveedor;
import com.demo.inventariApp.infrastructure.adapters.out.entity.ProveedorEntity;

@Mapper(componentModel = "spring")
public interface ProveedorMapper {

     Proveedor toDomain(ProveedorEntity entity);

    ProveedorEntity toEntity(Proveedor proveedor);

}
