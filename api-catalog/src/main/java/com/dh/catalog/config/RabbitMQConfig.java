package com.dh.catalog.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE_NAME = "backendExchange";
    public static final String TOPIC_SERIE = "com.dh.backend.serie";
    public static final String TOPIC_PELICULA = "com.dh.backend.pelicula";
    public static final String QUEUE_SERIE ="queueSerie";
    public static final String QUEUE_PELICULA ="queuePelicula";
    @Bean
    public TopicExchange appExchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue queueSerie(){
        return new Queue(QUEUE_SERIE);
    }
    @Bean
    public Queue queuePelicula(){
        return new Queue(QUEUE_PELICULA);
    }
    @Bean
    public Binding declareBindingSpecific(){
        return BindingBuilder.bind(queueSerie()).to(appExchange()).with(TOPIC_SERIE);
    }
    @Bean
    public Binding declareBindingSpecificPelicula(){
        return BindingBuilder.bind(queuePelicula()).to(appExchange()).with(TOPIC_PELICULA);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final var rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}


