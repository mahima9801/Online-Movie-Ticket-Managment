package com.capgemini.onlinemovieticketsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.capgemini.onlinemovieticketsystem.entity.Booking;
import com.capgemini.onlinemovieticketsystem.entity.BookingTransaction;
import com.capgemini.onlinemovieticketsystem.entity.Customer;
import com.capgemini.onlinemovieticketsystem.entity.Movie;
import com.capgemini.onlinemovieticketsystem.entity.Screen;
import com.capgemini.onlinemovieticketsystem.entity.Seat;
import com.capgemini.onlinemovieticketsystem.entity.Show;
import com.capgemini.onlinemovieticketsystem.entity.Theater;
import com.capgemini.onlinemovieticketsystem.entity.Ticket;

@Service
public interface CustomerService {
	
//     Movie searchMovie(int theatreId, String movieName );
//
//	 Screen searchScreen(int theatreId, int screenId);
//
//	 Theater selectByTheatre(String theatreName);
//
//	 List<Theater> selectByCityName(String cityName);
//
//	 List<Movie> findMoviesInTheatre(int theatreId);
//
//	 List<Screen> findScreensInTheatre(int theatreId);
//
//	 List<Show> findShowsInTheatre(int theatreId, int screenId);
//
//	 Show selectShow(int theatreId, int screenId, int showId);
//
//	 Screen selectScreen(int theatreId, int screenId);
//
//	 List<String> showCities();
//
//	 List<Seat> showSeats(int theatreId, int screenId, int showId);
//
//	 List<Movie> selectMoviesByCityName(String cityName);
//
//	 List<Theater> selectByMovieName(String movieName);
//
//	 List<Show> selectByMovieAndTheatre(String movieName, String theatreName);
//
//	 Optional<Theater> findByScreenId(int screenId);
//
//      List<Movie> searchMovieByTheater(String theatreName);
//
//	 List<Movie> searchTheaterByMovie(String theatreCity);
//
//	 List<String> findAllCities();
//
//	 List<Theater> findAllTheatres();
//

	//---------------------------------BOOKING------------------------------//


	 Booking fetchBookingById(int bookingId);
	
	 List<Booking> fetchAllBookings();
	
	 Ticket showTicket(int bookingId);
	
	 BookingTransaction makePayment(String paymentMethod, double cost);
	
	 Booking createBooking(Booking booking, String paymentMethod,String screenName);

	 String cancelBooking(int bookingId);


//	 //-------------------------------
//
//	 List<Movie> fetchAllMovies() ;
//
//	 Movie fetchById(int movieId) ;
//
//	 List<Show> fetchAllShows();
//
//	 Seat getSeat(int seatId);
//
//	  Seat blockSeat(int seatId);
//
//	  Seat bookSeat(int seatId);
//
//	  Seat cancelSeat(int seatId);
//
//	  Show findById(int showId);
//
//	  Customer addCustomer(Customer customer);
//
//	   Customer findCustomerById(int customerId);
//
//	   boolean chooseCity(String city);
//
//	   boolean bookMovieTicket();
//
//	   boolean cancelMovieTicket(int ticketId);
//
//	List<Theater> findAll();

//--------------------------------------SCREEN---------------------------------//



	List<Screen> fetchAllScreen();

	Screen fetchScreenById(int screenId);



	//------------------------------------THEATER-------------------------------//



	List<Theater> fetchAllTheater();

	Theater fetchTheaterById(int theaterId);



	//----------------------------------MOVIE------------------------------//



	List<Movie> fetchAllMovie() ;

	Movie fetchMovieById(int movieId) ;



	//-----------------------------------SHOW-----------------------------//


	Show fetchShowById(int showId) ;

	List<Show>fetchAllShow() ;

}
