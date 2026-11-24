package com.demo.inventariApp.infrastructure.adapters.out.persistence;

import java.util.List;

import org.springframework.stereotype.Component;

import com.demo.inventariApp.application.port.out.ProveedorRepositoryPort;
import com.demo.inventariApp.domain.model.Proveedor;
import com.demo.inventariApp.infrastructure.adapters.out.entity.ProveedorEntity;
import com.demo.inventariApp.infrastructure.adapters.out.repository.ProveedorJpaRepository;
import com.demo.inventariApp.infrastructure.mapper.ProveedorMapper;

import lombok.AllArgsConstructor;

 @Component
@AllArgsConstructor
public class ProveedorRepositoryJpaAdapter implements ProveedorRepositoryPort {

    private final ProveedorMapper mapper;
    private final ProveedorJpaRepository proveedorJpaRepository;    

    @Override
    public Proveedor saveProveedor(Proveedor proveedor) {
        ProveedorEntity entity = mapper.domainToEntity(proveedor);
        ProveedorEntity save = proveedorJpaRepository.save(entity);
        return mapper.entityToDomain(save);
    }

    @Override
    public List<Proveedor> getProveedores() {
        return mapper.entityListToDomainList(proveedorJpaRepository.findAll());
    }

    @Override
    public Proveedor getById(Long id) {
        return mapper.entityToDomain(proveedorJpaRepository.findById(id).orElseThrow(()->new RuntimeException("Proveedor no encontrado")));
    }

    @Override
    public boolean existsByNombre(String nombre) {
        return proveedorJpaRepository.existsByEmpresa(nombre);
    }

}
