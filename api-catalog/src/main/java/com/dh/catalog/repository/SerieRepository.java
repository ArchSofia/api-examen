package com.dh.catalog.repository;

import com.dh.catalog.client.SerieServiceClient;
import com.dh.catalog.model.serie.Serie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SerieRepository extends MongoRepository<SerieServiceClient.Serie, String> {

    @Query("{'genre': ?0}")
    List<SerieServiceClient.Serie> findAllByGenre(String genre);

}
