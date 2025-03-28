package com.demo.desafio_hinova.Producers;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.demo.desafio_hinova.Model.Veiculos;

@Service
public class VeiculosProducers {

    private final KafkaTemplate<String, Veiculos> veiculosKafkaTemplate;

    public VeiculosProducers(KafkaTemplate<String, Veiculos> veiculosSalvarKafkaTemplate) {

        this.veiculosKafkaTemplate = veiculosSalvarKafkaTemplate;
    }

    public void enviarMensagemSalvarVeiculo(Veiculos veiculos){
        veiculosKafkaTemplate.send("salvar-veiculos", veiculos);
    }

    public void enviarMensagemAtualizarVeiculo(Long id, Veiculos veiculos){
        veiculos.setId(id);
        veiculosKafkaTemplate.send("atualizar-veiculos", veiculos);
    }

    public void enviarMensagemDeletarVeiculo(Long id){
        Veiculos veiculos = new Veiculos();
        veiculos.setId(id);
        veiculosKafkaTemplate.send("deletar-veiculos", veiculos);
    }

}
