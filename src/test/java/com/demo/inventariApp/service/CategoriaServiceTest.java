package com.demo.inventariApp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.demo.inventariApp.application.port.out.CategoriaRepositoryPort;
import com.demo.inventariApp.application.port.service.CategoriaService;
import com.demo.inventariApp.domain.model.Categoria;

@ExtendWith(MockitoExtension.class)
public class CategoriaServiceTest {

    @Mock
    CategoriaRepositoryPort categoriaRepositoryPort;

    @InjectMocks
    CategoriaService categoriaService;

    @Test
    void shouldCreateCategoria() {
        Categoria categoria = new Categoria();
        categoria.setNombre("Cuadernos");

        when(categoriaRepositoryPort.saveCategoria(any(Categoria.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Categoria result = categoriaService.crearCategoria(categoria);

        assertEquals("Cuadernos", result.getNombre());

        verify(categoriaRepositoryPort).saveCategoria(categoria);

    }

    @Test
    void shouldThrowExceptionWhenCategoriaAlreadyExists() {
        // GIVEN
        Categoria categoria = new Categoria();
        categoria.setNombre("Cuadernos");

        when(categoriaRepositoryPort.existsByNombre("Cuadernos"))
                .thenReturn(true);

        // WHEN + THEN
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> categoriaService.crearCategoria(categoria));

        assertEquals("La categoria ya existe", exception.getMessage());

        verify(categoriaRepositoryPort, never())
                .saveCategoria(any());
    }

}
