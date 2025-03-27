package com.demo.desafio_hinova.Producers;

import com.demo.desafio_hinova.Model.Usuarios;
import com.demo.desafio_hinova.Services.UsuarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UsuarioProducers {



    private final KafkaTemplate<String, Usuarios> usuariosKafkaTemplate;

    public UsuarioProducers(KafkaTemplate<String, Usuarios> usuariosKafkaTemplate) {

        this.usuariosKafkaTemplate = usuariosKafkaTemplate;
    }


    public void enviarMensagemPegarUsuarios(){
        //usuariosKafkaTemplate.send("pegar-usuarios", new HashMap<>());
    }

    public void enviarMensagemSalvarUsuarios(Usuarios usuarios){
        usuariosKafkaTemplate.send("salvar-usuarios", usuarios);
    }

    public void enviarMensagemAtualizarUsuarios(Long id, Usuarios usuarios){
        usuarios.setId(id);
        usuariosKafkaTemplate.send("atualizar-usuarios", usuarios);
    }

    public void enviarMensagemDeletarUsuarios(Long id){
        Usuarios usuario = new Usuarios();
        usuario.setId(id);
        usuariosKafkaTemplate.send("deletar-usuarios", usuario);
    }

}
