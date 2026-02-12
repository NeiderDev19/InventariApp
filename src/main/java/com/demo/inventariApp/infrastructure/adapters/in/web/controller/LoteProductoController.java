package com.demo.inventariApp.infrastructure.adapters.in.web.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.inventariApp.application.port.service.LoteProductoService;
import com.demo.inventariApp.domain.model.LoteProducto;
import com.demo.inventariApp.infrastructure.adapters.in.web.dto.loteProducto.LoteCreateDTO;
import com.demo.inventariApp.infrastructure.mapper.LoteProductoMapper;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/lote/producto")
@AllArgsConstructor
public class LoteProductoController {

    private final LoteProductoService loteProductoService;

    private final LoteProductoMapper mapper;


    @GetMapping("/{id}")
    public ResponseEntity<List<LoteProducto>> getLotesByProducto(@PathVariable Long id) {
        return ResponseEntity.ok(loteProductoService.getLoteProductoByProducto(id));        
    }
    

    @PostMapping
    public ResponseEntity<LoteProducto> agregarLote(@RequestBody LoteCreateDTO loteCreateDTO){
        LoteProducto lote = mapper.DTOtoDomain(loteCreateDTO);
        LoteProducto save = loteProductoService.agregarLote(lote, loteCreateDTO.getIdProducto());
        return ResponseEntity.ok(save);
    } 


}
