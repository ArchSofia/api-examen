package com.dh.catalog.event;


import com.dh.catalog.config.RabbitMQConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class FinalizarCursoEventConsumer {


    @RabbitListener(queues = RabbitMQConfig.QUEUE_CURSO_FINALIZADO)
    public void listen(Data message){
        System.out.print("NOMBRE DE MOVIE "+ message.name);
        //procesamiento
    }


    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class Data{
        private Long id;

        private String name;

        private String genre;

        private String urlStream;

    }
}
