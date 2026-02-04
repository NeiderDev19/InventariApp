package com.demo.inventariApp.infrastructure.adapters.in.web.dto.usuario;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioResponseDTO {

    Long id;
    String nombres;
    String apellidos;
    String correo;
    String rol;
    boolean activo;
    LocalDateTime fechaCreacion;
    
}
