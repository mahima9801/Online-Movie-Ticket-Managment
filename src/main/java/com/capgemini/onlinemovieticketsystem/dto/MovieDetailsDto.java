package com.capgemini.onlinemovieticketsystem.dto;

import java.time.LocalDate;
import java.util.List;

public class MovieDetailsDto {
    private int movieId ;
    private String	movieName ;
    private String  movieGenre ;
    private String	movieDirector ;
    private int movieLength  ;
    private List<String> languages;
    private LocalDate movieReleaseDate ;

    public String getMovieName() {
        return movieName;
    }
    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }
    public String getMovieGenre() {
        return movieGenre;
    }
    public void setMovieGenre(String movieGenre) {
        this.movieGenre = movieGenre;
    }
    public String getMovieDirector() {
        return movieDirector;
    }
    public void setMovieDirector(String movieDirector) {
        this.movieDirector = movieDirector;
    }
    public int getMovieLength() {
        return movieLength;
    }
    public void setMovieLength(int movieLength) {
        this.movieLength = movieLength;
    }
    public List<String> getLanguages() {
        return languages;
    }
    public void setLanguages(List<String> list) {
        this.languages = list;
    }
    public LocalDate getMovieReleaseDate() {
        return movieReleaseDate;
    }
    public void setMovieReleaseDate(LocalDate movieReleaseDate) {
        this.movieReleaseDate = movieReleaseDate;
    }
    public int getMovieId() {
        return movieId;
    }
    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }



}
