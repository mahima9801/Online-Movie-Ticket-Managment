package com.capgemini.onlinemovieticketsystem.dto;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class MovieRequestDto {
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
    public LocalDate getMovieReleaseDate() {
        return movieReleaseDate;
    }
    public void setMovieReleaseDate(LocalDate movieReleaseDate) {
        this.movieReleaseDate = movieReleaseDate;
    }
    public List<String> getLanguages() {
        return languages;
    }
    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

}
