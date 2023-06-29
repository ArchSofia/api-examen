package com.dh.apiserie.service;

import com.dh.apiserie.event.SerieEventProducer;
import com.dh.apiserie.model.Serie;
import com.dh.apiserie.repository.SerieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SerieService {
    private final SerieRepository repository;
    private final SerieEventProducer serieEventProducer;

    public SerieService(SerieRepository repository, SerieEventProducer serieEventProducer) {
        this.repository = repository;
        this.serieEventProducer = serieEventProducer;
    }

    public List<Serie> getSeriesBygGenre(String genre) {
        return repository.findAllByGenre(genre);
    }

    public Serie createSerie(Serie serie) {

        serieEventProducer.publishFinalizarCursoEvent(new SerieEventProducer.Data(serie.getId(), serie.getName(), serie.getGenre()));
        return repository.save(serie);
    }
}
