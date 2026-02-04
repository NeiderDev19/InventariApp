package com.demo.inventariApp.application.port.in;

import java.util.List;

import com.demo.inventariApp.domain.model.Categoria;

public interface CategoriaInputPort {

    List<Categoria> getCategorias();
    Categoria crearCategoria(Categoria categoria);
    Categoria editarCategoria(Long id,String nombre);
    void cambiarEstado(Long id,boolean estado);
    Categoria getCategoriaById(Long id);
    
    
}
