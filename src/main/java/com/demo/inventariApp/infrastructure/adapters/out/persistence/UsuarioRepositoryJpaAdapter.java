package com.demo.inventariApp.infrastructure.adapters.out.persistence;

import java.util.List;

import org.springframework.stereotype.Component;

import com.demo.inventariApp.application.port.out.UsuarioRepositoryPort;
import com.demo.inventariApp.domain.model.Usuario;
import com.demo.inventariApp.infrastructure.adapters.out.entity.UsuarioEntity;
import com.demo.inventariApp.infrastructure.adapters.out.repository.UsuarioJpaRepository;
import com.demo.inventariApp.infrastructure.mapper.UsuarioMapper;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UsuarioRepositoryJpaAdapter implements UsuarioRepositoryPort {
    

    private final UsuarioMapper mapper;

    private final UsuarioJpaRepository usuarioJpaRepository;
    
    @Override
    public Usuario saveUsuario(Usuario usuario) {
        UsuarioEntity entity = mapper.domaintoEntity(usuario);
        UsuarioEntity save = usuarioJpaRepository.save(entity);
        return mapper.entitytoDomain(save);
    }

    @Override
    public List<Usuario> getUsers() {
        return mapper.entityListtoDomainList(usuarioJpaRepository.findAll());
    }

    @Override
    public Usuario getById(Long id) {
        return mapper.entitytoDomain(usuarioJpaRepository.findById(id).orElseThrow(()-> new RuntimeException("No se encontro el usuario")));
    }



}
