package com.demo.desafio_hinova.Consumers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.demo.desafio_hinova.Model.Veiculos;
import com.demo.desafio_hinova.Services.VeiculosServices;

import java.util.List;

@Service
public class VeiculosConsumers {
    private static final Logger LOGGER = LoggerFactory.getLogger(com.demo.desafio_hinova.Consumers.VeiculosConsumers.class);

    @Autowired
    private VeiculosServices services;


    @KafkaListener(topics = "salvar-veiculos", groupId = "desafio-hinova")
    public void salvarListener(Veiculos veiculos){
        try{
            LOGGER.info("Salvando veiculos");
             services.salvar(veiculos);
        } catch (Exception e) {
            LOGGER.info("Erro ao salvar veiculo" + e.getMessage());
        }
    }

    @KafkaListener(topics = "deletar-veiculos", groupId = "desafio-hinova")
    public void deletarListener(Veiculos veiculos){
        try{
            LOGGER.info("Deletando veiculos");
            services.deletar(veiculos.getId());
        } catch (Exception e) {
            LOGGER.info("Erro ao deletar veiculos: " + e.getMessage());
        }
    }

    @KafkaListener(topics = "atualizar-veiculos", groupId = "desafio-hinova")
    public void atualizarListener(Veiculos veiculos){
        try{
            LOGGER.info("Atualizando veiculos");
            services.atualizar(veiculos.getId(), veiculos);
        } catch (Exception e) {
            LOGGER.info("Erro ao atualizar veiculos:" + e.getMessage());

        }
    }
}
