package com.demo.inventariApp.infrastructure.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.demo.inventariApp.domain.model.LoteProducto;
import com.demo.inventariApp.infrastructure.adapters.in.web.dto.loteProducto.LoteCreateDTO;
import com.demo.inventariApp.infrastructure.adapters.out.entity.LoteProductoEntity;

@Mapper(componentModel = "spring")
public interface LoteProductoMapper {

    LoteProducto EntityToDomain(LoteProductoEntity loteProducto);
    LoteProductoEntity domainToEntity(LoteProducto loteProducto);
    LoteProducto DTOtoDomain(LoteCreateDTO loteCreateDTO);
    List<LoteProducto> entityListToDomainList(List<LoteProductoEntity> lotes);
    List<LoteProducto> toResponseList(List<LoteProductoEntity> loteProducto);
}
