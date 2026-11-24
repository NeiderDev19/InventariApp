package com.demo.inventariApp.infrastructure.adapters.out.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.inventariApp.infrastructure.adapters.out.entity.ProveedorEntity;


@Repository
public interface ProveedorJpaRepository extends JpaRepository<ProveedorEntity,Long> {

    ProveedorEntity findByid(Long idProveedor);
    List<ProveedorEntity> findAll();
    boolean existsByEmpresa(String empresa);
    

}
