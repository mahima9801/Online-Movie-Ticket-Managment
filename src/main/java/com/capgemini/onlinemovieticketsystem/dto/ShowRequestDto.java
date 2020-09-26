package com.capgemini.onlinemovieticketsystem.dto;

import com.capgemini.onlinemovieticketsystem.entity.Movie;

import java.sql.Time;

public class ShowRequestDto {

    private Time showStartTime ;
    private Time showEndTime ;
    private String showName ;
    private Movie movieName ;
    private int theaterId;
    private int screenId;


    public Time getShowStartTime() {
        return showStartTime;
    }
    public void setShowStartTime(Time showStartTime) {
        this.showStartTime = showStartTime;
    }
    public Time getShowEndTime() {
        return showEndTime;
    }
    public void setShowEndTime(Time showEndTime) {
        this.showEndTime = showEndTime;
    }
    public String getShowName() {
        return showName;
    }
    public void setShowName(String showName) {
        this.showName = showName;
    }
    public Movie getMovieName() {
        return movieName;
    }
    public void setMovieName(Movie movieName) {
        this.movieName = movieName;
    }
    public int getTheaterId() {
        return theaterId;
    }
    public void setTheaterId(int theaterId) {
        this.theaterId = theaterId;
    }
    public int getScreenId() {
        return screenId;
    }
    public void setScreenId(int screenId) {
        this.screenId = screenId;
    }

}
