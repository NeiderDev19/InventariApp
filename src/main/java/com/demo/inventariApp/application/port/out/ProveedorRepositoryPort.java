package com.demo.inventariApp.application.port.out;

import java.util.List;

import com.demo.inventariApp.domain.model.Proveedor;

public interface ProveedorRepositoryPort {

       Proveedor saveProveedor(Proveedor proveedor);
       List<Proveedor> getProveedores();
       Proveedor getById(Long id);
       boolean existsByNombre(String nombre);
       


}
