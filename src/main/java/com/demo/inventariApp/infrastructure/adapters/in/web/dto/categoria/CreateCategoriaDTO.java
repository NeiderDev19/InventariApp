package com.demo.inventariApp.infrastructure.adapters.in.web.dto.categoria;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCategoriaDTO {

    @NotBlank
    String nombre;
    boolean activo = true;
    LocalDate fechaCreacion = LocalDate.now();


}
