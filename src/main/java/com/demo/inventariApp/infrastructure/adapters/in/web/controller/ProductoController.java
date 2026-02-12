package com.demo.inventariApp.infrastructure.adapters.in.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.inventariApp.application.port.service.ProductoService;
import com.demo.inventariApp.domain.model.Producto;
import com.demo.inventariApp.infrastructure.adapters.in.web.dto.categoria.EditEstadoDTO;
import com.demo.inventariApp.infrastructure.adapters.in.web.dto.producto.EditProductDTO;
import com.demo.inventariApp.infrastructure.adapters.in.web.dto.producto.ProductCreateDTO;
import com.demo.inventariApp.infrastructure.adapters.in.web.dto.producto.ProductoResponseDTO;
import com.demo.inventariApp.infrastructure.mapper.ProductoMapper;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@RestController
@RequestMapping("/api/productos")
@AllArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    private final ProductoMapper mapper;

    @GetMapping
    public ResponseEntity<List<ProductoResponseDTO>> getProductos() {
        return ResponseEntity.ok(mapper.toResponseList(productoService.listarProductos()));
    }

    @GetMapping("/filter/{filtro}")
    public ResponseEntity<List<ProductoResponseDTO>> findProductosByFilter(@PathVariable String filtro){
        List<Producto> productos = productoService.getProductoByFiltro(filtro);
        return ResponseEntity.ok(mapper.toResponseList(productos));
    }

    @GetMapping("/codigoBarras/{codigoBarras}")
    public ResponseEntity<ProductoResponseDTO> findProductoByCodigoBarras (@PathVariable String codigoBarras) {
        return ResponseEntity.ok(mapper.domainToDTO(productoService.getProductoByCodigoBarras(codigoBarras)));
    }
    

    @PostMapping("/{idCategoria}")
    public ResponseEntity<ProductoResponseDTO> crearProducto(@RequestBody ProductCreateDTO producto,@PathVariable Long idCategoria){
        Producto nuevo = mapper.DTOtoDomain(producto);
        Producto guardado = productoService.crearProductos(nuevo,idCategoria);
         return ResponseEntity.ok(mapper.domainToDTO(guardado));
    }



    @GetMapping("/categoria/{id}")
    public ResponseEntity<List<ProductoResponseDTO>> getProductosByCategoria(@PathVariable Long id){    
        List<Producto> productos = productoService.getProductsByCategoria(id);
        return ResponseEntity.ok(mapper.toResponseList(productos));
    }

    @PatchMapping("/estado/{idProducto}")
    public ResponseEntity<Void> cambiarEstadoProducto(@PathVariable Long idProducto,@RequestBody EditEstadoDTO estado ){
        productoService.cambiarEstado(idProducto, estado.isEstado());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> getProductoById(@PathVariable Long id){
        Producto product = productoService.getProductoById(id);
        return ResponseEntity.ok(mapper.domainToDTO(product));
    }

    @PutMapping
    public ResponseEntity<ProductoResponseDTO> editarProducto(@RequestBody EditProductDTO editProductDTO){
        Producto producto = mapper.DtoEditToDomain(editProductDTO);
        Producto save = productoService.editarProducto(producto, editProductDTO.getIdProducto(), editProductDTO.getIdCategoria());
        return ResponseEntity.ok(mapper.domainToDTO(save));
    }

    
    



}
