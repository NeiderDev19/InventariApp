package com.demo.inventariApp.infrastructure.adapters.out.entity;

import java.time.LocalDateTime;

import com.demo.inventariApp.domain.model.Rol;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@Table(name ="usuarios")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String primerNombre;
    private String segundoNombre;
    @Column(nullable = false)
    private String primerApellido;
    private String segundoApellido;
    @Column(nullable = false, unique = true)
    private String correo;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private boolean activo;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Rol rol;
    @Column(nullable = false)
    private LocalDateTime fechaCreacion;

}
