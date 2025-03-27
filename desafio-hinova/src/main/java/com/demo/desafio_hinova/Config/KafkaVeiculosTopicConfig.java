package com.demo.desafio_hinova.Config;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaVeiculosTopicConfig {

    @Bean
    public NewTopic topicSalvaVeiculo(){
        return TopicBuilder.name("salvar-veiculos").build();
    }
    @Bean
    public NewTopic topicAtualizarVeiculo(){
        return TopicBuilder.name("atualizar-veiculos").build();
    }
    @Bean
    public NewTopic topicDeletarVeiculo(){
        return TopicBuilder.name("deletar-veiculos").build();
    }
}
