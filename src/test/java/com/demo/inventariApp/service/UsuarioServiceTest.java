package com.demo.inventariApp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.demo.inventariApp.application.port.out.UsuarioRepositoryPort;
import com.demo.inventariApp.application.port.service.UsuarioService;
import com.demo.inventariApp.domain.model.Rol;
import com.demo.inventariApp.domain.model.Usuario;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepositoryPort usuarioRepositoryPort;

    @InjectMocks
    private UsuarioService usuarioService;


    @Test
    void shouldCreateUsuarioWhitRol(){
        Usuario usuario = new Usuario();

         when(usuarioRepositoryPort.saveUsuario(any(Usuario.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Usuario result = usuarioService.createUsuario(usuario, Rol.VENDEDOR);

        assertEquals(Rol.VENDEDOR, result.getRol());
        verify(usuarioRepositoryPort).saveUsuario(usuario);
    }


    @Test
    void shouldReturnUsuarios() {
        when(usuarioRepositoryPort.getUsers())
                .thenReturn(List.of(new Usuario(), new Usuario()));

        List<Usuario> result = usuarioService.obtenerUsuarios();

        assertEquals(2, result.size());
        verify(usuarioRepositoryPort).getUsers();
    }

     @Test
    void shouldChangeUserState() {
        Usuario usuario = new Usuario();
        usuario.setActivo(true);

        when(usuarioRepositoryPort.getById(1L)).thenReturn(usuario);

        usuarioService.changeStateUser(1L, false);

        assertFalse(usuario.isActivo());
        verify(usuarioRepositoryPort).saveUsuario(usuario);
    }

    @Test
    void shouldReturnUserById() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);

        when(usuarioRepositoryPort.getById(1L)).thenReturn(usuario);

        Usuario result = usuarioService.getUserId(1L);

        assertEquals(1L, result.getId());
    }


}
