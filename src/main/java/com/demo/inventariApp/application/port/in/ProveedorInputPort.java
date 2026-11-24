package com.demo.inventariApp.application.port.in;

import java.util.List;

import com.demo.inventariApp.domain.model.Proveedor;

public interface ProveedorInputPort {

    List<Proveedor> getProveedores();
    Proveedor saveProveedor(Proveedor proveedor);
    Proveedor getProveedorId(Long id);
    Proveedor editProveedor(Long id,Proveedor proveedor);

    

}
