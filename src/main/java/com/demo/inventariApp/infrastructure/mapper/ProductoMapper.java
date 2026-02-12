package com.demo.inventariApp.infrastructure.mapper;

import java.util.List;
import java.util.Optional;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.demo.inventariApp.domain.model.Producto;
import com.demo.inventariApp.infrastructure.adapters.in.web.dto.producto.EditProductDTO;
import com.demo.inventariApp.infrastructure.adapters.in.web.dto.producto.ProductCreateDTO;
import com.demo.inventariApp.infrastructure.adapters.in.web.dto.producto.ProductoResponseDTO;
import com.demo.inventariApp.infrastructure.adapters.out.entity.ProductoEntity;

@Mapper(componentModel = "spring")
public interface ProductoMapper {

    ProductoEntity domainToEntity(Producto producto);
    Producto EntityToDomain(ProductoEntity productoEntity);
    List<Producto> entityListtoDomainList(List<ProductoEntity> productos);
    @Mapping(target = "unidad", source = "unidad")
    Producto DTOtoDomain(ProductCreateDTO producto);
    ProductoResponseDTO domainToDTO(Producto producto);
    @Mapping(target = "id",source = "idProducto")
    List<ProductoResponseDTO> toResponseList(List<Producto> producto);
    @Mapping(target = "categoria" , ignore = true)
    Producto DtoEditToDomain(EditProductDTO editProductDTO);
    

}
