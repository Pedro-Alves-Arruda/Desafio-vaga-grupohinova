package com.demo.desafio_hinova.Consumers;

import com.demo.desafio_hinova.Model.Usuarios;
import com.demo.desafio_hinova.Services.UsuarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UsuarioConsumers {

    @Autowired
    private UsuarioServices services;

    @KafkaListener(topicPartitions = @TopicPartition(topic = "pegar-usuarios", partitions = {"1"}), containerFactory = "usariosKafkaListenerContainerFactory")
    public List<Usuarios> pegarListener(){
        return services.listar();
    }

    @KafkaListener(topicPartitions = @TopicPartition(topic = "salvar-usuarios", partitions = {"1"}), containerFactory = "usariosKafkaListenerContainerFactory")
    public Usuarios salvarListener(Map<String, Usuarios> usuariosMap){
        return services.salvar(usuariosMap.get("usuario"));
    }

    @KafkaListener(topicPartitions = @TopicPartition(topic = "deletar-usuarios", partitions = {"1"}), containerFactory = "usariosKafkaListenerContainerFactory")
    public void deletarListener(Map<String, Usuarios> usuariosMap){
        services.deletar(usuariosMap.get("usuario").getId());
    }

    @KafkaListener(topicPartitions = @TopicPartition(topic = "atualizar-usuarios", partitions = {"1"}), containerFactory = "usariosKafkaListenerContainerFactory")
    public Usuarios pegarListener(Map<String, Usuarios> usuariosMap){
        return services.atualizar(usuariosMap.get("usuario").getId(), usuariosMap.get("usuario"));
    }
}
