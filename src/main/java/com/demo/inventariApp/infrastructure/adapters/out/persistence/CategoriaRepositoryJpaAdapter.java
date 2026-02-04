package com.demo.inventariApp.infrastructure.adapters.out.persistence;

import java.util.List;

import org.springframework.stereotype.Component;

import com.demo.inventariApp.application.port.out.CategoriaRepositoryPort;
import com.demo.inventariApp.domain.model.Categoria;
import com.demo.inventariApp.infrastructure.adapters.out.repository.CategoriaJpaRepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class CategoriaRepositoryJpaAdapter implements CategoriaRepositoryPort {

    private final CategoriaJpaRepository categoriaJpaRepository;

    @Override
    public Categoria saveCategoria(Categoria categoria) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveCategoria'");
    }

    @Override
    public List<Categoria> getCategorias() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCategorias'");
    }

    @Override
    public Categoria getById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }

    @Override
    public boolean existsByNombre(String nombre) {
        return categoriaJpaRepository.existsByNombre(nombre);
    }


    
}
