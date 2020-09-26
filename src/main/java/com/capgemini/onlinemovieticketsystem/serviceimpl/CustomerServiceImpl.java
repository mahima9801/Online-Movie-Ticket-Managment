package com.capgemini.onlinemovieticketsystem.serviceimpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.capgemini.onlinemovieticketsystem.dao.*;
import com.capgemini.onlinemovieticketsystem.entity.Booking;
import com.capgemini.onlinemovieticketsystem.entity.BookingStatus;
import com.capgemini.onlinemovieticketsystem.entity.BookingTransaction;
import com.capgemini.onlinemovieticketsystem.entity.Customer;
import com.capgemini.onlinemovieticketsystem.entity.Movie;
import com.capgemini.onlinemovieticketsystem.entity.Screen;
import com.capgemini.onlinemovieticketsystem.entity.Seat;
import com.capgemini.onlinemovieticketsystem.entity.Show;
import com.capgemini.onlinemovieticketsystem.entity.Theater;
import com.capgemini.onlinemovieticketsystem.entity.Ticket;
import com.capgemini.onlinemovieticketsystem.entity.TicketStatus;
import com.capgemini.onlinemovieticketsystem.exception.*;
import com.capgemini.onlinemovieticketsystem.service.CustomerService;



public class CustomerServiceImpl implements CustomerService
{
	  private MovieDao  movieDao;
	  private CustomerDao customerDao;
	  private SeatDao seatDao;
	  private ShowDao showDao;
	  private BookingDao bookingDao;
	  private TheaterDao theaterDao;
	  private TicketDao ticketDao;
	  private TransactionDao transactionDao;
	  private ScreenDao screenDao;
	  
//	@Override
//    public Seat getSeat(int seatId) {
//        Optional<Seat> optional = seatDao.findById(seatId);
//        if(optional.isPresent())
//        {
//            Seat seat = optional.get();
//            return seat;
//        }
//        throw new SeatNotFoundException("Seat not found for id:"+seatId);
//    }
//    @Override
//    public Seat blockSeat(int id) {
//        Seat seat = getSeat(id);
//        seat.setSeatStatus(BookingStatus.BLOCKED);
//        seatDao.save(seat);
//        return seat;
//    }
//    @Override
//    public Seat bookSeat(int id) {
//        Seat seat = getSeat(id);
//        seat.setSeatStatus(BookingStatus.BOOKED);
//        seatDao.save(seat);
//        return seat;
//    }
//    @Override
//    public Seat cancelSeat(int id) {
//        Seat seat = getSeat(id);
//        seat.setSeatStatus(BookingStatus.AVAILABLE);
//        seatDao.save(seat);
//        return seat;
//    }
//
//    @Override
//	public Show findShowById(int showId) {
//		Optional<Show> optional = showDao.findById(showId);
//		if(optional.isPresent()) {
//			Show show = optional.get();
//			return show;
//		}
//		throw new  ShowNotFoundException("Show not found for id:"+showId);
//	}
//
//    @Override
//    public Booking addBooking(Booking booking) {
//   		return bookingDao.save(booking);
//   	}

	/**
	 * fetching booking by bookingId
	 * @param bookingId
	 * @return
	 */
    @Override
	public Booking fetchBookingById(int bookingId) {
		Optional<Booking> optional= bookingDao.findById(bookingId);
		if(!optional.isPresent()) {
			throw new BookingNotFoundException("Booking id is wrong. No booking exist with this booking id :"+bookingId);
		}
		Booking booking = optional.get();
		return booking;
	}

	/**
	 * fetching all bookings
	 * @return
	 */
	@Override
	public List<Booking> fetchAllBookings() {
		List<Booking> bookingList = bookingDao.findAll();
		return bookingList;
	}

	/**
	 * cancel booking
	 * @param bookingId
	 * @return
	 */
	
	@Override
	public String cancelBooking(int bookingId) {
		Booking booking = fetchBookingById(bookingId);
		Ticket ticket = booking.getTicket();
		ticketDao.delete(ticket);
		bookingDao.delete(booking);
		return "Cancelled";
	}

	@Override
	public List<Screen> fetchAllScreen() {
		List<Screen> screenList = screenDao.findAll();
		return screenList;
	}

	@Override
	public Screen fetchScreenById(int screenId) {
		Optional<Screen> optional = screenDao.findById(screenId);
		if(optional.isPresent()) {
			Screen screen = optional.get();
			return screen;
		}
		throw new ScreenNotFoundException("Screen not found at id ="+screenId);
	}

	@Override
	public List<Theater> fetchAllTheater() {

		List<Theater> theaters = theaterDao.findAll();
		return theaters;
	}

	@Override
	public Theater fetchTheaterById(int theaterId) {
		Optional<Theater> optional = theaterDao.findById(theaterId);
		if(optional.isPresent()) {
			Theater th = optional.get();
			return th;
		}
		throw new TheaterNotFoundException("theater not found for id="+theaterId);
	}

	@Override
	public List<Movie> fetchAllMovie() {
		List<Movie> movies = movieDao.findAll();
		return movies;
	}

	@Override
	public Movie fetchMovieById(int movieId) {
		Optional<Movie> optional = movieDao.findById(movieId);
		if (optional.isPresent()) {
			Movie mov = optional.get();
			return mov;
		}
		throw new MovieNotFoundException("Movie not found for id=" + movieId);
	}

	@Override
	public Show fetchShowById(int showId) {
		Optional<Show> optional = showDao.findById(showId);
		if (optional.isPresent()) {
			Show show = optional.get();
			return show;
		}
		throw new MovieNotFoundException("Show not found for id=" + showId);
	}

	@Override
	public List<Show> fetchAllShow() {
		List<Show> shows = showDao.findAll();
		return shows;
	}


	/**
	 * making payment returning bookingTransaction
	 * @return
	 */
	@Override
	public BookingTransaction makePayment(String paymentMethod, double cost) {
		BookingTransaction transaction = new BookingTransaction();
		transaction.setTransactionAmount(cost);
		transaction.setTransactionMethod(paymentMethod);
		transaction = transactionDao.save(transaction);
		return transaction;
	}

	@Override
	public Booking createBooking(Booking booking, String paymentMethod, String screenName) {
		return null;
	}

	/**
	 * fetching ticket with bookingId
	 * @param bookingId
	 * @return
	 */
	@Override
	public Ticket showTicket(int bookingId) {
		Booking booking = fetchBookingById(bookingId);
		Ticket ticket = booking.getTicket();
		if(ticket==null) {
			throw new TicketNotFoundException("No Ticket has booked yet.");
		}
		return ticket;
	}

	/**
	 * generating booking
	 */
	/**
	@Override
	public Booking createBooking(Booking booking,String paymentMethod,String screenName) {
		BookingTransaction bookingTransaction = makePayment(paymentMethod,booking.getTotalCost());
		Ticket ticket = createTicket(booking.getSeatIds(),screenName);
		booking.setBookingDate(LocalDate.now());
		booking.setTransactionId(bookingTransaction.getTransactionId());
		booking.setTicket(ticket);
		booking = addBooking(booking);
		return booking;
	}
	*/
	/**
	 * generating ticket
	 * @param seatIds
	 * @param screenName
	 * @return
	 */
	/**
	public Ticket createTicket(List<Integer> seatIds,String screenName) {
		Ticket ticket= new Ticket();
		ticket.setNoOfSeats(seatIds.size());
		ticket.setScreenName(screenName);
		ticket.setSeatIds(seatIds);
		ticket.setTicketStatus(TicketStatus.BOOKED);
		ticket = ticketDao.save(ticket);
		return ticket;
	}
	*/
//	@Override
//    public Customer addCustomer(Customer customer) {
//        customerDao.save(customer);
//        return customer;
//    }
//
//
//    @Override
//    public Customer findCustomerById(int customerId) {
//        Optional<Customer> optional = customerDao.findById(customerId);
//        if (optional.isPresent()) {
//            Customer customer = optional.get();
//            return customer;
//        }
//        throw new CustomerNotFoundException("Customer not found for id=" + customerId);
//    }
//
//
//    @Override
//    public boolean chooseCity(String city) {
//        return false;
//    }
//
//    @Override
//    public boolean bookMovieTicket() {
//        return false;
//    }
//
//    @Override
//    public boolean cancelMovieTicket(int ticketId) {
//        return false;
//    }
//
//	@Override
//	public List<Theater> findAllTheatres() {
//		 List<Theater> theaters = theaterDao.findAll();
//	        return theaters;
//	}
//	@Override
//	public List<Movie> fetchAllMovies() {
//		 List<Movie> movies = movieDao.findAll();
//	        return movies;
//	}
//
//	@Override
//	public Movie fetchById(int movieId) {
//		return null;
//	}
//
//	@Override
//	public List<Show> fetchAllShows() {
//		 List<Show> shows = showDao.findAll();
//	        return shows;
//
//	}
//
//	@Override
//	public Movie searchMovie(int theatreId, String movieName) {
//		Object customerService = null;
//		Optional<Movie> movieOptional = customerService.findByMovieName(movieName);
//		if(movieOptional.isPresent()){
//		List<Movie> movies = theaterDao.findMovieByName(theatreId, movieOptional.get());
//		if(movies.size()>0){
//			return movies.get(0);
//		}
//		else
//			return null;
//		}
//		else
//			return null;
//	}

//	@Override
//	public Screen searchScreen(int theatreId, int screenId) {
//		Screen screen = theaterDao.searchScreen(theatreId, screenId);
//		if(screen != null)
//		{
//			return screen;
//		}
//		return null;
//	}

//	@Override
//	public Theater selectByTheatre(String theatreName) {
//		Optional<Theater> theatreOptional = theaterDao.selectByTheatreName(theatreName);
//		if(theatreOptional.isPresent()){
//			return theatreOptional.get();
//		}
//		else
//			return null;
//	}

//	@Override
//	public List<Theater> selectByCityName(String cityName) {
//		List<Theater> theatres = theaterDao.selectByCity(cityName);
//		return theatres;
//	}

//	@Override
//	public List<Movie> findMoviesInTheatre(int theatreId) {
//		Optional<Theater> theatreOptional = theaterDao.findById(theatreId);
//		if(theatreOptional.isPresent()){
//			return theatreOptional.get().getListOfMovies();
//		}
//		return null;
//	}
//
//	@Override
//	public List<Screen> findScreensInTheatre(int theatreId) {
//		Optional<Theater> theaterOptional = theaterDao.findById(theatreId);
//		if(theaterOptional.isPresent()){
//			return theaterOptional.get().getListOfScreens();
//		}
//		return null;
//	}

//	@Override
//	public List<Show> findShowsInTheatre(int theatreId, int screenId) {
//		Optional<Screen> screenOptional = theaterDao.findScreenInTheatre(theatreId, screenId);
//		if(screenOptional.isPresent()){
//			return screenOptional.get().getShowList();
//		}
//		return null;
//	}

//	@Override
//	public Show selectShow(int theatreId, int screenId, int showId) {
//		Optional<Screen> screenOptional = theaterDao.findScreenInTheatre(theatreId, screenId);
//		if(screenOptional.isPresent()){
//			Optional<Show> showOptional = screenService.findShowInScreen(screenId, showId);
//			if(showOptional.isPresent()){
//				return showOptional.get();
//			}
//		}
//		return null;
//	}

//	@Override
//	public Screen selectScreen(int theaterId, int screenId) {
//		Optional<Screen> screenOptional = theaterDao.findScreenInTheater(theaterId, screenId);
//		if(screenOptional.isPresent()){
//			return screenOptional.get();
//		}
//		return null;
//	}

//	@Override
//	public List<String> showCities() {
//		List<String> cities = theaterDao.getAllCities();
//		return cities;
//	}

//	@Override
//	public List<Seat> showSeats(int theatreId, int screenId, int showId) {
//		return showService.showSeatsInShow(showId);
//	}

//	@Override
//	public List<Movie> selectMoviesByCityName(String cityName) {
//		return theaterDao.getMoviesByCityName(cityName);
//	}
//
	

//	@Override
//	public List<Theater> selectByMovieName(String movieName) {
//		return theaterDao.selectByMovieName(movieName);
//	}

//	@Override
//	public List<Show> selectByMovieAndTheatre(String movieName, String theatreName) {
//		Optional<Theater> theaterOptional = theaterDao.selectByTheaterName(theatreName);
//		Optional<Movie> movieOptional = theaterDao.findMovieInTheater(movieName);
//		if(theaterOptional.isPresent() && movieOptional.isPresent()){
//			int movieId = movieOptional.get().getMovieId();
//			int theatreId = theaterOptional.get().getTheatreId();
//			List<Show> shows = showService.findShowsByMovieAndTheatre(movieId, theatreId);
//			return shows;
//		}
//		return null;
//	}

//	@Override
//	public List<Theater> findAllTTheater() {
//		return theaterDao.findAll();
//	}

//	@Override
//	public Optional<Theater> findByScreenId(int screenId) {
//		return theaterDao.findByScreenId(screenId);
//	}

//	@Override
//	public List<Movie> searchMovieByTheater(String theatreName) {
//
//		return theaterDao.findMovieByTheaterName1(theatreName);
//	}

//	@Override
//	public List<Movie> searchTheaterByMovie(String theatreCity) {
//
//		return theaterDao.findTheaterByTheaterCity1(theatreCity);
//	}

//	@Override
//	public List<String> findAllCities() {
//		return theaterDao.getAllCities();
//	}

//	@Override
//	public List<String> findAllTheater() {
//		return theaterDao.findAllTheater();
//	}
}
