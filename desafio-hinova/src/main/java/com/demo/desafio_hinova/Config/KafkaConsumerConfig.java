package com.demo.desafio_hinova.Config;

import com.demo.desafio_hinova.Model.Usuarios;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String address;

    @Bean
    public ConsumerFactory<String, Usuarios> pegarConsumerFactory(){
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, address);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "pegar-usuarios");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
        props.put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS, JsonDeserializer.class);
        props.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class);
        props.put(JsonDeserializer.TRUSTED_PACKAGES,  "*");
        props.put(JsonDeserializer.VALUE_DEFAULT_TYPE, "com.demo.desafio_hinova.Model.Usuarios");

        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean
    public ConsumerFactory<String, Usuarios> salvarConsumerFactory(){
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, address);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "salvar-usuarios");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
        props.put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS, JsonDeserializer.class);
        props.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class);
        props.put(JsonDeserializer.TRUSTED_PACKAGES,  "*");
        props.put(JsonDeserializer.VALUE_DEFAULT_TYPE, "com.demo.desafio_hinova.Model.Usuarios");

        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean
    public ConsumerFactory<String, Usuarios> deletarConsumerFactory(){
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, address);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "deletar-usuarios");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
        props.put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS, JsonDeserializer.class);
        props.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class);
        props.put(JsonDeserializer.TRUSTED_PACKAGES,  "*");
        props.put(JsonDeserializer.VALUE_DEFAULT_TYPE, "com.demo.desafio_hinova.Model.Usuarios");

        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean
    public ConsumerFactory<String, Usuarios> atualizarConsumerFactory(){
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, address);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "atualizar-usuarios");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
        props.put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS, JsonDeserializer.class);
        props.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class);
        props.put(JsonDeserializer.TRUSTED_PACKAGES,  "*");
        props.put(JsonDeserializer.VALUE_DEFAULT_TYPE, "com.demo.desafio_hinova.Model.Usuarios");

        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Usuarios> usariosKafkaListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<String, Usuarios> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(pegarConsumerFactory());
        factory.setConsumerFactory(salvarConsumerFactory());
        factory.setConsumerFactory(deletarConsumerFactory());
        factory.setConsumerFactory(atualizarConsumerFactory());
        return factory;
    }
}
