package com.demo.inventariApp.infrastructure.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.demo.inventariApp.domain.model.Categoria;
import com.demo.inventariApp.infrastructure.adapters.in.web.dto.categoria.CreateCategoriaDTO;
import com.demo.inventariApp.infrastructure.adapters.out.entity.CategoriaEntity;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {

      CategoriaEntity domainToEntity(Categoria categoria);
      Categoria entitytoDomain(CategoriaEntity categoriaEntity);
      List<Categoria> entityListtoDomainList(List<CategoriaEntity> categoria);
      Categoria categoriaDTOtCategoria(CreateCategoriaDTO categoria);
      CreateCategoriaDTO toResponse(Categoria categoria);
      
}
