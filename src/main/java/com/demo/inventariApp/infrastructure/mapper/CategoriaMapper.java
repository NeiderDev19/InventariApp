package com.demo.inventariApp.infrastructure.mapper;

import org.mapstruct.Mapper;

import com.demo.inventariApp.domain.model.Categoria;
import com.demo.inventariApp.infrastructure.adapters.out.entity.CategoriaEntity;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {

      CategoriaEntity domainToEntity(Categoria categoria);
      Categoria entitytoDomain(CategoriaEntity categoriaEntity);
      

}
