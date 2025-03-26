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

    @KafkaListener(topics = "pegar-usuarios", groupId = "desafio-hinova")
    public List<Usuarios> pegarListener(){
        return services.listar();
    }

    @KafkaListener(topics = "salvar-usuarios", groupId = "desafio-hinova")
    public Usuarios salvarListener(Usuarios usuario){
        try{
            System.out.println("Salvando usuario\n" + usuario.getName());
            return services.salvar(usuario);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new Usuarios();
        }
    }

    @KafkaListener(topics = "deletar-usuarios", groupId = "desafio-hinova")
    public void deletarListener(Map<String, Usuarios> usuariosMap){
        services.deletar(usuariosMap.get("usuario").getId());
    }

    @KafkaListener(topics = "atualizar-usuarios", groupId = "desafio-hinova")
    public Usuarios atualizarListener(Map<String, Usuarios> usuariosMap){
        return services.atualizar(usuariosMap.get("usuario").getId(), usuariosMap.get("usuario"));
    }
}
