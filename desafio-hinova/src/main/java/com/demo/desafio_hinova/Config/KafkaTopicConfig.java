package com.demo.desafio_hinova.Config;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration

public class KafkaTopicConfig {

    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String address;

    @Bean
    public KafkaAdmin KafkaAdmin(){
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, address);
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic topicPegarUsuario(){
        return new NewTopic("pegar-usuarios", 1, (short) 1);
    }

    @Bean
    public NewTopic topicSalvaUsuario(){
        return new NewTopic("salvar-usuarios", 1, (short) 1);
    }
    @Bean
    public NewTopic topicAtualizarUsuario(){
        return new NewTopic("atualizar-usuarios", 1, (short) 1);
    }
    @Bean
    public NewTopic topicDeletarUsuario(){
        return new NewTopic("deletar-usuarios", 1, (short) 1);
    }
}
