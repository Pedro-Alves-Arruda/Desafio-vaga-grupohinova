package com.demo.desafio_hinova.Config;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration

public class KafkaTopicConfig {

    @Bean
    public NewTopic topicPegarUsuario(){
        return TopicBuilder.name("pegar-usuarios").build();
    }

    @Bean
    public NewTopic topicSalvaUsuario(){
        return TopicBuilder.name("salvar-usuarios").build();
    }
    @Bean
    public NewTopic topicAtualizarUsuario(){
        return TopicBuilder.name("atualizar-usuarios").build();
    }
    @Bean
    public NewTopic topicDeletarUsuario(){
        return TopicBuilder.name("deletar-usuarios").build();
    }
}
