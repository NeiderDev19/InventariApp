package com.demo.inventariApp.infrastructure.adapters.in.web.dto.categoria;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditEstadoDTO {

    @NotBlank
    boolean estado;
    

}
