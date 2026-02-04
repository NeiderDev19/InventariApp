package com.demo.inventariApp.application.port.out;

import java.util.List;

import com.demo.inventariApp.domain.model.Usuario;

public interface UsuarioRepositoryPort {

    Usuario saveUsuario(Usuario usuario);
    List<Usuario> getUsers();
    Usuario getById(Long id);

}
