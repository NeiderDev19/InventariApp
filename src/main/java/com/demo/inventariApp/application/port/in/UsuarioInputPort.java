package com.demo.inventariApp.application.port.in;

import java.util.List;

import com.demo.inventariApp.domain.model.Rol;
import com.demo.inventariApp.domain.model.Usuario;

public interface UsuarioInputPort {

    Usuario createUsuario(Usuario usuario,Rol rol); 
    List<Usuario> obtenerUsuarios();
    void changeStateUser(Long idUser,boolean state);
    Usuario editUser(Usuario usuario);
    Usuario getUserId(Long id);

}
