package com.demo.inventariApp.infrastructure.adapters.out.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "proveedor")
public class ProveedorEntity {

      @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String empresa;
    private String nit;
    private String vendedor;
    private String celular;
    private String direccion;
    @OneToMany(mappedBy = "proveedor")
    @JsonIgnore
    private List<LoteProductoEntity> loteProductos;
    private String email;
    private boolean estado;

}
