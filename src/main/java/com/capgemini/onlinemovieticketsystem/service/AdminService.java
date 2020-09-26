package com.capgemini.onlinemovieticketsystem.service;

import com.capgemini.onlinemovieticketsystem.entity.*;

public interface AdminService {
	
	 Theater addTheater(Theater theater);
	
	 Movie addMovie(Movie movie);

	 Screen addScreen(Screen screen);
	
	 Show addShow(Show show);

	 Booking addBooking(Booking booking);
	
	 String deleteShowById(int showId);

	 Boolean deleteTheaterById(int theaterId);

	 String deleteMovieById(int movieid) ;

	Boolean deleteScreenById(int screenId);


	Movie save(Movie movie);
}
