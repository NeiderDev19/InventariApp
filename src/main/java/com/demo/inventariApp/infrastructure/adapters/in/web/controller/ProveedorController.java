package com.demo.inventariApp.infrastructure.adapters.in.web.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.inventariApp.application.port.in.ProveedorInputPort;
import com.demo.inventariApp.domain.model.Proveedor;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/proveedores")
@AllArgsConstructor
public class ProveedorController {

    private final ProveedorInputPort proveedorService;

    @GetMapping
    public List<Proveedor> getProveedores(){
        return proveedorService.getProveedores();
    }

    @GetMapping("/{id}")
    public Proveedor getProveedorById(@PathVariable Long id){
        return proveedorService.getProveedorId(id);
    }

    @PostMapping
    public Proveedor saveProveedor(@RequestBody Proveedor proveedor){
        return proveedorService.saveProveedor(proveedor);
    }

    @PutMapping("/{id}")
    public Proveedor editProveedor(@PathVariable Long id, @RequestBody Proveedor proveedor){
        return proveedorService.editProveedor(id,proveedor);
    }

    
}
