package com.demo.inventariApp.application.port.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.demo.inventariApp.application.port.in.ProveedorInputPort;
import com.demo.inventariApp.application.port.out.ProveedorRepositoryPort;
import com.demo.inventariApp.domain.model.Proveedor;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProveedorService implements ProveedorInputPort {

    private final ProveedorRepositoryPort proveedorRepositoryPort;
    
    @Override
    public List<Proveedor> getProveedores() {
        return proveedorRepositoryPort.getProveedores();
    }

    @Override
    public Proveedor saveProveedor(Proveedor proveedor) {
        if(proveedorRepositoryPort.existsByNombre(proveedor.getEmpresa())){
            throw new RuntimeException("El proveedor ya existe");
        }
        return proveedorRepositoryPort.saveProveedor(proveedor);
    }

    @Override
    public Proveedor getProveedorId(Long id) {
        return proveedorRepositoryPort.getById(id);
    }

    @Override
    public Proveedor editProveedor(Long id,Proveedor proveedor) {
        Proveedor existente = proveedorRepositoryPort.getById(id);
        if(existente==null){
            throw new RuntimeException("Proveedor no encontrado");
        }
        if(proveedor.getEmpresa()!=null && proveedor.getEmpresa().isBlank()){
            throw new RuntimeException("El nombre de la empresa no puede estar vacio");
        }
        existente.setEmpresa(proveedor.getEmpresa());
        existente.setNit(proveedor.getNit());
        existente.setVendedor(proveedor.getVendedor());
        existente.setCelular(proveedor.getCelular());
        existente.setDireccion(proveedor.getDireccion());

        return proveedorRepositoryPort.saveProveedor(existente);
    }

}
