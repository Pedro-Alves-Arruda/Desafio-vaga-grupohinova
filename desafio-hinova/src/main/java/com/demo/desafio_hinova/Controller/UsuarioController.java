package com.demo.desafio_hinova.Controller;


import com.demo.desafio_hinova.Model.Usuarios;
import com.demo.desafio_hinova.Producers.UsuarioProducers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioProducers producers;

    @GetMapping
    public HttpStatus Listar(){
        producers.enviarMensagemPegarUsuarios();
        return HttpStatus.OK;
    }

    @PostMapping
    public HttpStatus salvar(@RequestBody Usuarios usuario){
        producers.enviarMensagemSalvarUsuarios(usuario);
        return HttpStatus.OK;
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Integer id){
        producers.enviarMensagemDeletarUsuarios(id);
    }

    @PutMapping
    public HttpStatus atualizar(@PathVariable Integer id, @RequestBody Usuarios usuario){
        producers.enviarMensagemAtualizarUsuarios(id, usuario);
        return HttpStatus.OK;
    }

}
