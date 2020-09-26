package com.capgemini.onlinemovieticketsystem.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Theater")
public class Theater {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="theaterId")
	private int theaterId;
	
	@Column(name="theaterName")
	private String theaterName;
	
	@Column(name="theaterCity")
	private String theaterCity;
	
	@OneToMany( orphanRemoval = true, cascade = CascadeType.PERSIST)
	private List<Movie> movieList;
	
	@OneToMany( orphanRemoval = true, cascade = CascadeType.PERSIST)
	private List<Screen> screenList;
	
	@Column(name="managerName")
	private String managerName;
	
	@Column(name="managerContact")
	private String managerContact;

	public Theater() {
		
	}

	public Theater(int theaterId, String theaterName, String theaterCity, List<Movie> movieList,
			List<Screen> screenList, String managerName, String managerContact) {
		super();
		this.theaterId = theaterId;
		this.theaterName = theaterName;
		this.theaterCity = theaterCity;
		this.movieList = movieList;
		this.screenList = screenList;
		this.managerName = managerName;
		this.managerContact = managerContact;
	}

	public int getTheaterId() {
		return theaterId;
	}

	public void setTheaterId(int theatreId) {
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

	public void setTheaterCity(String theatreCity) {
		this.theaterCity = theaterCity;
	}

	public List<Movie> getMovieList() {
		return movieList;
	}

	public void setMovieList(List<Movie> movieList) {
		this.movieList = movieList;
	}

	public List<Screen> getScreenList() {
		return screenList;
	}

	public void setScreenList(List<Screen> screenList) {
		this.screenList = screenList;
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

