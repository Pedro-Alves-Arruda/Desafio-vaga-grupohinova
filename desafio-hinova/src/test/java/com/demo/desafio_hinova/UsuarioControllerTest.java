package com.demo.desafio_hinova;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import com.demo.desafio_hinova.Model.Usuarios;
import com.demo.desafio_hinova.Services.UsuarioServices;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(Usuarios.class)
@AutoConfigureMockMvc(addFilters = false)
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UsuarioServices usuariosServices;

    @Test
    @DisplayName("Deve retornar lista de todos os usuários")
    void deveRetornarTodosUsuarios() throws Exception {

        List<Usuarios> usuarios = Arrays.asList(
                new Usuarios("Pedro", "31997812990", "pedro@gmail.com", "08696514669","01001-000", "Rua das Flores", "45","", true),
                new Usuarios("Maria", "31991148099", "maria@gmail.com", "09678615628","01001-000", "Rua das Flores", "34", "", true));

        when(usuariosServices.listar()).thenReturn(usuarios);

        // Act & Assert
        mockMvc.perform(get("usuarios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }
}
