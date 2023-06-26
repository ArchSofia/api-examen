package com.dh.catalog.service;

public class SerieNotFoundException extends RuntimeException {
    public SerieNotFoundException(String message) {
        super(message);
    }
}
