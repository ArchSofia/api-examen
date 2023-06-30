package com.dh.catalog.event;

import com.dh.catalog.config.RabbitMQConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PeliculasEventConsumer {
    @RabbitListener(queues = RabbitMQConfig.QUEUE_PELICULA)
    public void listen(PeliculasEventConsumer.Data message){
        System.out.print("NOMBRE DE PELICULA "+ message.name);
        //procesamiento
    }
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class Data{
        private String id;
        private String name;
        private String genre;
    }
}
