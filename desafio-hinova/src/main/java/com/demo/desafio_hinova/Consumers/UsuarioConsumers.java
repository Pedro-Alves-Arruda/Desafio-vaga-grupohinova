package com.demo.desafio_hinova.Consumers;

import com.demo.desafio_hinova.Model.Usuarios;
import com.demo.desafio_hinova.Services.UsuarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

@Service
public class UsuarioConsumers {

    private static final Logger LOGGER = LoggerFactory.getLogger(com.demo.desafio_hinova.Consumers.UsuarioConsumers.class);

    @Autowired
    private UsuarioServices services;

    @KafkaListener(topics = "pegar-usuarios", groupId = "desafio-hinova")
    public List<Usuarios> pegarListener(){
        return services.listar();
    }

    @KafkaListener(topics = "salvar-usuarios", groupId = "desafio-hinova")
    public Usuarios salvarListener(Usuarios usuario){
        try{
            LOGGER.info("Salvando usuario\n" + usuario.getName());
            return services.salvar(usuario);
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
            return new Usuarios();
        }
    }

    @KafkaListener(topics = "deletar-usuarios", groupId = "desafio-hinova")
    public void deletarListener(Usuarios usuarios){
        try{
            LOGGER.info("Deletando usuario");
            services.deletar(usuarios.getId());
        } catch (Exception e) {
            LOGGER.info("Erro ao deletar usuario: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @KafkaListener(topics = "atualizar-usuarios", groupId = "desafio-hinova")
    public Usuarios atualizarListener(Usuarios usuarios){
        try{
            LOGGER.info("Atualizando usuario");
            return services.atualizar(usuarios.getId(), usuarios);
        } catch (Exception e) {
            LOGGER.info("Erro ao atualizar usuario:" + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
