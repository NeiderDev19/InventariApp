package com.demo.inventariApp.infrastructure.adapters.in.web.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.inventariApp.application.port.service.CategoriaService;
import com.demo.inventariApp.domain.model.Categoria;
import com.demo.inventariApp.infrastructure.adapters.in.web.dto.categoria.CreateCategoriaDTO;
import com.demo.inventariApp.infrastructure.adapters.in.web.dto.categoria.EditCategoriaDTO;
import com.demo.inventariApp.infrastructure.adapters.in.web.dto.categoria.EditEstadoDTO;
import com.demo.inventariApp.infrastructure.mapper.CategoriaMapper;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/categoria")
@AllArgsConstructor
public class CategoriaController {


    private final CategoriaService categoriaService;

    private final CategoriaMapper mapper;


    @GetMapping
    public ResponseEntity<List<Categoria>> getAllCategories(){
        return ResponseEntity.ok(categoriaService.getCategorias());
    }

    @PostMapping("/save")
    public ResponseEntity<CreateCategoriaDTO> saveCategoria(@RequestBody CreateCategoriaDTO categoria) {
        Categoria nueva = mapper.categoriaDTOtCategoria(categoria);
        Categoria creada = categoriaService.crearCategoria(nueva);
        return ResponseEntity.ok(mapper.toResponse(creada));
    }

    @PatchMapping("/edit/{id}")
    public ResponseEntity<Void> editCategoria(@PathVariable Long id,@RequestBody EditCategoriaDTO categoria){
        categoriaService.editarCategoria(id, categoria.getNombre());
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/estado/{id}")
    public ResponseEntity<Void> changeState(@PathVariable Long id,@RequestBody EditEstadoDTO edit ){
        categoriaService.cambiarEstado(id, edit.isEstado());
        return ResponseEntity.noContent().build();
    }
    



}
