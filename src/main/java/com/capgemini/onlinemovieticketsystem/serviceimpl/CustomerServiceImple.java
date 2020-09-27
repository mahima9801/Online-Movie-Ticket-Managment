package com.capgemini.onlinemovieticketsystem.serviceimpl;

import com.capgemini.onlinemovieticketsystem.dao.*;
import com.capgemini.onlinemovieticketsystem.entity.*;
import com.capgemini.onlinemovieticketsystem.exception.*;
import com.capgemini.onlinemovieticketsystem.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class CustomerServiceImple implements CustomerService {


    @Autowired
    private MovieDao movieDao;

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private SeatDao seatDao;

    @Autowired
    private ShowDao showDao;

    @Autowired
    private BookingDao bookingDao;

    @Autowired
    private TheaterDao theaterDao;

    @Autowired
    private TicketDao ticketDao;

    @Autowired
    private TransactionDao transactionDao;

    @Autowired
    private ScreenDao screenDao;


    //-------------------------------------------BOOKING-----------------------------------//


    @Override
    public Booking addBooking(Booking booking) {
        booking = bookingDao.save(booking);
        return booking;
    }

    @Override
    public Booking createBooking(Booking booking, String paymentMethod, String screenName) {
        BookingTransaction bookingTransaction = makePayment(paymentMethod,booking.getTotalCost());
        Ticket ticket = createTicket(booking.getSeatIds(),screenName);
        booking.setBookingDate(LocalDate.now());
        booking.setTransactionId(bookingTransaction.getTransactionId());
        booking.setTicket(ticket);
        booking = addBooking(booking);
        return booking;
    }


    @Override
    public Booking fetchBookingById(int bookingId) {
        Optional<Booking> option= bookingDao.findById(bookingId);
        if(!option.isPresent()) {
            throw new BookingNotFoundException("Booking id is wrong. No booking exist with this booking id :"+bookingId);
        }
        Booking booking = option.get();
        return booking;
    }

    @Override
    public List<Booking> fetchAllBookings() {
        List<Booking> bookingList = bookingDao.findAll();
        return bookingList;
    }

    @Override
    public String cancelBooking(int bookingId) {
        Booking booking = fetchBookingById(bookingId);
        Ticket ticket = booking.getTicket();
        ticketDao.delete(ticket);
        bookingDao.delete(booking);
        return "Cancelled";
    }

    @Override
    public BookingTransaction makePayment(String paymentMethod, double cost) {
        BookingTransaction transaction = new BookingTransaction();
        transaction.setTransactionAmount(cost);
        transaction.setTransactionMethod(paymentMethod);
        transaction = transactionDao.save(transaction);
        return transaction;
    }

    //------------------------------------TICKET-------------------------------------//

    /**
     * generating ticket
     * @param seatIds
     * @param screenName
     * @return
     */
    public Ticket createTicket(List<Integer> seatIds,String screenName) {
        Ticket ticket= new Ticket();
        ticket.setNoOfSeats(seatIds.size());
        ticket.setScreenName(screenName);
        ticket.setSeatIds(seatIds);
        ticket.setTicketStatus(TicketStatus.BOOKED);
        ticket = ticketDao.save(ticket);
        return ticket;
    }


    @Override
    public Ticket showTicket(int bookingId) {
        Booking booking = fetchBookingById(bookingId);
        Ticket ticket = booking.getTicket();
        if(ticket==null) {
            throw new TicketNotFoundException("No Ticket has booked yet.");
        }
        return ticket;
    }

    //---------------------------------------SCREEN---------------------------------------//

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


    //---------------------------------------MOVIE----------------------------------//
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


    //----------------------------------------SHOW---------------------------------------//
    @Override
    public Show fetchShowById(int showId) {
        Optional<Show> optional = showDao.findById(showId);
        if(optional.isPresent()) {
            Show show = optional.get();
            return show;
        }
        throw new ShowNotFoundException("Show not found for id="+showId);

    }

    @Override
    public List<Show> fetchAllShow() {
        List<Show> shows = showDao.findAll();
        return shows;
    }

    //------------------------------------------------SEAT----------------------------------//

    @Override
    public Seat addSeat(Seat seat) {
        seatDao.save(seat);
        return seat;
    }
    @Override
    public Seat getSeat(int id) {
        Optional<Seat> optional = seatDao.findById(id);
        if(optional.isPresent())
        {
            Seat seat = optional.get();
            return seat;
        }
        throw new SeatNotFoundException("Seat not found for id:"+id);
    }
    @Override
    public Seat blockSeat(int id) {
        Seat seat = getSeat(id);
        seat.setSeatStatus(SeatStatus.BLOCKED);
        seatDao.save(seat);
        return seat;
    }
    @Override
    public Seat bookSeat(int id) {
        Seat seat = getSeat(id);
        seat.setSeatStatus(SeatStatus.BOOKED);
        seatDao.save(seat);
        return seat;
    }
    @Override
    public Seat cancelSeat(int id) {
        Seat seat = getSeat(id);
        seat.setSeatStatus(SeatStatus.AVAILABLE);
        seatDao.save(seat);
        return seat;
    }

    @Override
    public List<Seat> fetchAllSeats() {
        List<Seat> list = seatDao.findAll();
        return list;
    }

}
