package com.capgemini.onlinemovieticketsystem.exception;

public class TheaterNotFoundException extends RuntimeException{
    public TheaterNotFoundException(String msg) {
        super(msg);
    }
}
