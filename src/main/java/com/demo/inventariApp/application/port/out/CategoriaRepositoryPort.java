package com.demo.inventariApp.application.port.out;

import java.util.List;

import com.demo.inventariApp.domain.model.Categoria;

public interface CategoriaRepositoryPort {

    Categoria saveCategoria(Categoria categoria);
    List<Categoria> getCategorias();
    Categoria getById(Long id);
    boolean existsByNombre(String nombre);

}
