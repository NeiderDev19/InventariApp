package com.demo.inventariApp.infrastructure.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.demo.inventariApp.domain.model.Proveedor;
import com.demo.inventariApp.infrastructure.adapters.out.entity.ProveedorEntity;

@Mapper(componentModel = "spring")
public interface ProveedorMapper {

      ProveedorEntity domainToEntity(Proveedor proveedor);
      Proveedor entityToDomain(ProveedorEntity proveedorEntity);
      List<Proveedor> entityListToDomainList(List<ProveedorEntity> proveedorEntity);
    
}
