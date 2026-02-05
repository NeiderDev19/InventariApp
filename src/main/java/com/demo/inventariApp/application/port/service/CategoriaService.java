package com.demo.inventariApp.application.port.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.demo.inventariApp.application.port.in.CategoriaInputPort;
import com.demo.inventariApp.application.port.out.CategoriaRepositoryPort;
import com.demo.inventariApp.domain.model.Categoria;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CategoriaService implements CategoriaInputPort {

    private final CategoriaRepositoryPort categoriaRepositoryPort;
    
    
    @Override
    public List<Categoria> getCategorias() {
        return categoriaRepositoryPort.getCategorias();
    }

    @Override
    public Categoria crearCategoria(Categoria categoria) {
        boolean existe = categoriaRepositoryPort.existsByNombre(categoria.getNombre());
        if(existe){
            throw new RuntimeException("La categoria ya existe");
        }
        return categoriaRepositoryPort.saveCategoria(categoria);
    }

    @Override
    public Categoria editarCategoria(Long id, String nombre) {
        Categoria categoria = categoriaRepositoryPort.getById(id);
        boolean existe = categoriaRepositoryPort.existsByNombreAndIdNot(nombre, id);
        if(existe){
            throw new RuntimeException("La categoria ya existe");
        }
        categoria.setNombre(nombre);
        return categoriaRepositoryPort.saveCategoria(categoria); 
    }

    @Override
    public void cambiarEstado(Long id, boolean estado) {
        Categoria categoria = categoriaRepositoryPort.getById(id);
        categoria.setActivo(estado);
        categoriaRepositoryPort.saveCategoria(categoria);
    }

    @Override
    public Categoria getCategoriaById(Long id) {
        return categoriaRepositoryPort.getById(id);
    }

    



}
