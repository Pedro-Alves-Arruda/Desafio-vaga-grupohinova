package com.demo.desafio_hinova.Producers;

import com.demo.desafio_hinova.Model.Usuarios;
import com.demo.desafio_hinova.Services.UsuarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UsuarioProducers {


    private final KafkaTemplate<String, Map<String, Usuarios>> usuariosKafkaTemplate;

    public UsuarioProducers(KafkaTemplate<String, Map<String, Usuarios>> usuariosKafkaTemplate) {
        this.usuariosKafkaTemplate = usuariosKafkaTemplate;
    }

    @SuppressWarnings("null")
    public void enviarMensagemPegarUsuarios(){
        usuariosKafkaTemplate.send("pegar-usuarios", new HashMap<>());
    }

    @SuppressWarnings("null")
    public void enviarMensagemSalvarUsuarios(Usuarios usuarios){
        Map<String, Usuarios> message = new HashMap<>();

        message.put("usuario", usuarios);

        usuariosKafkaTemplate.send("salvar-usuarios", message);
    }

    @SuppressWarnings("null")
    public void enviarMensagemAtualizarUsuarios(Integer id, Usuarios usuarios){
        Map<String, Usuarios> message = new HashMap<>();

        Usuarios usuarioAntigo = new Usuarios();
        usuarioAntigo.setId(id);

        message.put("id", usuarioAntigo);
        message.put("usuario", usuarios);
        usuariosKafkaTemplate.send("atualizar-usuarios", message);
    }

    @SuppressWarnings("null")
    public void enviarMensagemDeletarUsuarios(Integer id){
        Map<String, Usuarios> message = new HashMap<>();

        Usuarios usuarioAntigo = new Usuarios();
        usuarioAntigo.setId(id);

        message.put("id", usuarioAntigo);
        usuariosKafkaTemplate.send("deletar-usuarios", message);
    }

}
