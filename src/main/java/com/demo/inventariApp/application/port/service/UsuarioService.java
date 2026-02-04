package com.demo.inventariApp.application.port.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.demo.inventariApp.application.port.in.UsuarioInputPort;
import com.demo.inventariApp.application.port.out.UsuarioRepositoryPort;
import com.demo.inventariApp.domain.model.Rol;
import com.demo.inventariApp.domain.model.Usuario;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class UsuarioService implements UsuarioInputPort {

    private final UsuarioRepositoryPort usuarioRepositoryPort;


    @Override
    public Usuario createUsuario(Usuario usuario,Rol rol) {
        usuario.setRol(rol);
        return usuarioRepositoryPort.saveUsuario(usuario);
    }

    @Override
    public List<Usuario> obtenerUsuarios() {
        return usuarioRepositoryPort.getUsers();
    }

    @Override
    public void changeStateUser(Long idUser,boolean state) {
        Usuario usuario = usuarioRepositoryPort.getById(idUser);
        usuario.setActivo(state);
        usuarioRepositoryPort.saveUsuario(usuario);
    }

    @Override
    public Usuario editUser(Usuario usuario) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'editUser'");
    }

    @Override
    public Usuario getUserId(Long id) {
        return usuarioRepositoryPort.getById(id);
    }


    
}
