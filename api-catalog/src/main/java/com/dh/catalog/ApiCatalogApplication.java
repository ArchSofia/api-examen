package com.dh.catalog;

import com.dh.catalog.client.SerieServiceClient;
import com.dh.catalog.model.serie.Chapter;
import com.dh.catalog.model.serie.Season;
import com.dh.catalog.model.serie.Serie;
import com.dh.catalog.repository.SerieRepository;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.UUID;

@SpringBootApplication
@EnableFeignClients
@EnableRabbit
public class ApiCatalogApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiCatalogApplication.class, args);
    }



}
