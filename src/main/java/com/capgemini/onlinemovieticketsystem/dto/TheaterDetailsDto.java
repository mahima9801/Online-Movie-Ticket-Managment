package com.capgemini.onlinemovieticketsystem.dto;

import com.capgemini.onlinemovieticketsystem.entity.Screen;

import java.util.List;

public class TheaterDetailsDto {

    private int theaterId;
    private String theaterName;
    private String theaterCity;
    private List<Integer> movieList;
    private List<Screen> screenList;
    private String managerName;
    private	String managerContact;

    public int getTheaterId() {
        return theaterId;
    }
    public void setTheaterId(int theaterId) {
        this.theaterId = theaterId;
    }
    public String getTheaterName() {
        return theaterName;
    }
    public void setTheaterName(String theaterName) {
        this.theaterName = theaterName;
    }
    public String getTheaterCity() {
        return theaterCity;
    }
    public void setTheaterCity(String theaterCity) {
        this.theaterCity = theaterCity;
    }
    public List<Screen> getScreenList() {
        return screenList;
    }
    public void setScreenList(List<Screen> screenList) {
        this.screenList = screenList;
    }
    public List<Integer> getMovieList() {
        return movieList;
    }
    public void setMovieList(List<Integer> movieList) {
        this.movieList = movieList;
    }
    public String getManagerName() {
        return managerName;
    }
    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }
    public String getManagerContact() {
        return managerContact;
    }
    public void setManagerContact(String managerContact) {
        this.managerContact = managerContact;
    }

}
