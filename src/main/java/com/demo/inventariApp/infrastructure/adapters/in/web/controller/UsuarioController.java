package com.demo.inventariApp.infrastructure.adapters.in.web.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.inventariApp.application.port.service.UsuarioService;
import com.demo.inventariApp.domain.model.Usuario;
import com.demo.inventariApp.infrastructure.adapters.in.web.dto.usuario.UsuarioResponseDTO;
import com.demo.inventariApp.infrastructure.mapper.UsuarioMapper;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/usuarios")
@AllArgsConstructor
public class UsuarioController {

        private final UsuarioService usuarioService;

        private final UsuarioMapper mapper;

        @GetMapping
        public ResponseEntity<List<UsuarioResponseDTO>> getUsers(){
            List<Usuario> usuario = usuarioService.obtenerUsuarios();
            List<UsuarioResponseDTO> response = mapper.toResponseList(usuario);
            return ResponseEntity.ok(response);
        }

        @PostMapping("/estado/{estado}")
        public ResponseEntity<?> cambiarEstado(@PathVariable boolean estado,@RequestParam Long id){
            usuarioService.changeStateUser(id, estado);
            return ResponseEntity.ok("Estado del usuario cambiado");
        }


        

}
