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



    private final KafkaTemplate<String, Usuarios> usuariosSalvarKafkaTemplate;

    public UsuarioProducers(KafkaTemplate<String, Usuarios> usuariosSalvarKafkaTemplate) {

        this.usuariosSalvarKafkaTemplate = usuariosSalvarKafkaTemplate;
    }

    @SuppressWarnings("null")
    public void enviarMensagemPegarUsuarios(){
        //usuariosSalvarKafkaTemplate.send("pegar-usuarios", new HashMap<>());
    }

    public void enviarMensagemSalvarUsuarios(Usuarios usuarios){
        usuariosSalvarKafkaTemplate.send("salvar-usuarios", usuarios);
    }

    @SuppressWarnings("null")
    public void enviarMensagemAtualizarUsuarios(Long id, Usuarios usuarios){
        usuarios.setId(id);
        usuariosSalvarKafkaTemplate.send("atualizar-usuarios", usuarios);
    }

    @SuppressWarnings("null")
    public void enviarMensagemDeletarUsuarios(Long id){
        Usuarios usuario = new Usuarios();
        usuario.setId(id);
        usuariosSalvarKafkaTemplate.send("deletar-usuarios", usuario);
    }

}
