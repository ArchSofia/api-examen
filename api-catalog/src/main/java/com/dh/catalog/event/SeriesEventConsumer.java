package com.dh.catalog.event;

import com.dh.catalog.config.RabbitMQConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
@Component
public class SeriesEventConsumer {
    @RabbitListener(queues = RabbitMQConfig.QUEUE_SERIE)
    public void listen(SeriesEventConsumer.Data message){
        System.out.print("NOMBRE DE SERIE "+ message.name);
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
