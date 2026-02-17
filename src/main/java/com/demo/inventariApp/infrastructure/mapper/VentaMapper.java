package com.demo.inventariApp.infrastructure.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.demo.inventariApp.domain.model.Usuario;
import com.demo.inventariApp.domain.model.Venta;
import com.demo.inventariApp.infrastructure.adapters.in.web.dto.venta.VentaResponseDTO;
import com.demo.inventariApp.infrastructure.adapters.out.entity.VentaEntity;

@Mapper(componentModel = "spring")
public interface VentaMapper {
    
    @Mapping(source = "id", target = "idVenta")
    Venta EntityToDomain(VentaEntity entity);

    @Mapping(source = "idVenta", target = "id")
    VentaEntity domainToEntity(Venta venta);

    List<Venta> entityListtoDomainList(List<VentaEntity> ventaEntity);

    @Mapping(source = "usuario", target = "vendedor")
    VentaResponseDTO toResponse(Venta venta);

    // Método personalizado
    default String mapUsuarioToVendedor(Usuario usuario) {
        if (usuario == null) {
            return null;
        }

        String nombre = usuario.getPrimerNombre();
        String apellido = usuario.getPrimerApellido();

        String primerNombre = nombre != null ? nombre.split(" ")[0] : "";
        String primerApellido = apellido != null ? apellido.split(" ")[0] : "";

        return (primerNombre + " " + primerApellido).trim();
    }


    List<VentaResponseDTO> toResponse(List<Venta> venta);

    

}
