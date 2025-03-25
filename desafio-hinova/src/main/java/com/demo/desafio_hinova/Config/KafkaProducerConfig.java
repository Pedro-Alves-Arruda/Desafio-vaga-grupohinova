package com.demo.desafio_hinova.Config;


import com.demo.desafio_hinova.Model.Usuarios;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String address;

    @Bean
    public ProducerFactory<String, Map<String, Usuarios>> usuariosProducerFactory(){
        Map<String, Object> props = new HashMap<>();
        props.put(JsonSerializer.ADD_TYPE_INFO_HEADERS, false);
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, address);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, String.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return new DefaultKafkaProducerFactory<>(props);
    }

    @Bean
    public KafkaTemplate<String, Map<String, Usuarios>> usuariosKafkaTemplate(){
        return new KafkaTemplate<>(usuariosProducerFactory());
    }

}
