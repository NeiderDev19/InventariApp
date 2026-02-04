package com.demo.inventariApp.infrastructure.mapper;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.demo.inventariApp.domain.model.Usuario;
import com.demo.inventariApp.infrastructure.adapters.in.web.dto.usuario.UsuarioResponseDTO;
import com.demo.inventariApp.infrastructure.adapters.out.entity.UsuarioEntity;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioEntity domaintoEntity(Usuario usuario);
    Usuario entitytoDomain(UsuarioEntity usuarioEntity);
    List<Usuario> entityListtoDomainList(List<UsuarioEntity> usuarios);
    List<UsuarioResponseDTO> toResponseList(List<Usuario> usuarios);


     @Mapping(target = "nombres", expression = "java(unir(usuario.getPrimerNombre(), usuario.getSegundoNombre()))")
    @Mapping(target = "apellidos", expression = "java(unir(usuario.getPrimerApellido(), usuario.getSegundoApellido()))")
    @Mapping(target = "rol", expression = "java(usuario.getRol().name())")
    UsuarioResponseDTO toResponse(Usuario usuario);

    default String unir(String... partes) {
        return Arrays.stream(partes)
                .filter(p -> p != null && !p.isBlank())
                .collect(Collectors.joining(" "));
    }
    
}
