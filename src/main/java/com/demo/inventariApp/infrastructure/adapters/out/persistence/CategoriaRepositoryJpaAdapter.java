package com.demo.inventariApp.infrastructure.adapters.out.persistence;

import java.util.List;

import org.springframework.stereotype.Component;

import com.demo.inventariApp.application.port.out.CategoriaRepositoryPort;
import com.demo.inventariApp.domain.model.Categoria;
import com.demo.inventariApp.infrastructure.adapters.out.entity.CategoriaEntity;
import com.demo.inventariApp.infrastructure.adapters.out.repository.CategoriaJpaRepository;
import com.demo.inventariApp.infrastructure.mapper.CategoriaMapper;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class CategoriaRepositoryJpaAdapter implements CategoriaRepositoryPort {

    private final CategoriaMapper mapper;

    private final CategoriaJpaRepository categoriaJpaRepository;

    @Override
    public Categoria saveCategoria(Categoria categoria) {
        CategoriaEntity entity = mapper.domainToEntity(categoria);
        CategoriaEntity save = categoriaJpaRepository.save(entity);
        return mapper.entitytoDomain(save);
    }

    @Override
    public List<Categoria> getCategorias() {
        return mapper.entityListtoDomainList(categoriaJpaRepository.findAll());
    }

    @Override
    public Categoria getById(Long id) {
        return mapper.entitytoDomain(categoriaJpaRepository.findById(id).orElseThrow(()->new RuntimeException("Categoria no encontrada")));
    }

    @Override
    public boolean existsByNombre(String nombre) {
        return categoriaJpaRepository.existsByNombre(nombre);
    }

    @Override
    public boolean existsByNombreAndIdNot(String nombre, Long id) {
        return categoriaJpaRepository.existsByNombreAndIdCategoriaNot(nombre, id);
    }


    
}
