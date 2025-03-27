package com.demo.desafio_hinova.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.demo.desafio_hinova.Producers.VeiculosProducers;
import com.demo.desafio_hinova.Services.VeiculosServices;
import com.demo.desafio_hinova.Model.Veiculos;

import java.util.List;

@RestController
@RequestMapping("veiculos")
public class VeiculosController {

    @Autowired
    private VeiculosProducers producers;

    @Autowired
    private VeiculosServices services;

    @GetMapping
    public List<Veiculos> listar(){
        return services.listar();
    }

    @PostMapping
    public HttpStatus salvar(@RequestBody Veiculos veiculos){
        producers.enviarMensagemSalvarVeiculo(veiculos);
        return HttpStatus.OK;
    }

    @DeleteMapping("/{id}")
    public HttpStatus deletar(@PathVariable Long id){
        producers.enviarMensagemDeletarVeiculo(id);
        return HttpStatus.OK;
    }

    @PutMapping("/{id}")
    public HttpStatus atualizar(@PathVariable Long id, @RequestBody Veiculos veiculo){
        producers.enviarMensagemAtualizarVeiculo(id, veiculo);
        return HttpStatus.OK;

    }
}
