package com.capgemini.onlinemovieticketsystem.controller;

import com.capgemini.onlinemovieticketsystem.dto.*;
import com.capgemini.onlinemovieticketsystem.entity.*;
import com.capgemini.onlinemovieticketsystem.exception.ScreenNotFoundException;
import com.capgemini.onlinemovieticketsystem.exception.SeatNotFoundException;
import com.capgemini.onlinemovieticketsystem.exception.TheaterNotFoundException;
import com.capgemini.onlinemovieticketsystem.exception.*;
import com.capgemini.onlinemovieticketsystem.service.AdminService;
import com.capgemini.onlinemovieticketsystem.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/onlinemovie")
public class Controller<TicketDto> {

    private static final Logger Log = LoggerFactory.getLogger(Controller.class);
    
    //----------------------------------------Seat--------------------------------------------//
    @Autowired
    private CustomerService customerService;

    public Seat convertToSeat(SeatDto dto) {
        Seat seat = new Seat();
        seat.setSeatId(dto.getSeatId());
        seat.setSeatStatus(BookingStatus.AVAILABLE);
        seat.setSeatPrice(dto.getSeatPrice());
        return seat;
    }



//    @PostMapping("/add")
//    public ResponseEntity<Seat> addSeat(@RequestBody SeatDto dto) {
//        Seat seat = convertToSeat(dto);
//        service.addSeat(seat);
//        ResponseEntity<Seat> response = new ResponseEntity<Seat>(seat, HttpStatus.OK);
//        return response;
//    }
//
//    @PutMapping("/blockseat/{id}")
//    public ResponseEntity<Seat> blockSeat(@PathVariable("id") int id) {
//        Seat seat = service.blockSeat(id);
//        ResponseEntity<Seat> response = new ResponseEntity<Seat>(seat, HttpStatus.OK);
//        return response;
//    }
//
//    @PutMapping("/bookseat/{id}")
//    public ResponseEntity<Seat> bookSeat(@PathVariable("id") int id) {
//        Seat seat = service.bookSeat(id);
//        ResponseEntity<Seat> response = new ResponseEntity<Seat>(seat, HttpStatus.OK);
//        return response;
//    }

//    @PutMapping("/cancelseat/{id}")
//    public ResponseEntity<Seat> cancelSeat(@PathVariable("id") int id) {
//        Seat seat = service.cancelSeat(id);
//        ResponseEntity<Seat> response = new ResponseEntity<Seat>(seat, HttpStatus.OK);
//        return response;
//    }
//

//    @GetMapping
//    public ResponseEntity<List<Seat>> fetchAllSeat() {
//        List<Seat> seats = customerService.fetchAllSeat();
//        ResponseEntity<List<Seat>> response = new ResponseEntity<>(seats, HttpStatus.OK);
//        return response;
//    }
//
//    @GetMapping("/find/{id}")
//    public ResponseEntity<Seat> findSeatById(@PathVariable("id")  int id) {
//        Seat seat = customerService.getSeat(id);
//        ResponseEntity<Seat> response = new ResponseEntity<>(seat, HttpStatus.OK);
//        return response;
//    }

    @ExceptionHandler(SeatNotFoundException.class)
    public ResponseEntity<String> SeatNotFound(SeatNotFoundException ex) {
        Log.error("SeatNotFound()", ex);
        String msg = ex.getMessage();
        ResponseEntity<String> response = new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
        return response;
    }





    //----------------------------------------Booking--------------------------------------------//

    /**
     * adding a booking and return bookingDetails
     * @Param bookintDto
     * @return
     */
    @PostMapping("/addbooking")
    ResponseEntity<BookingDetails> bookingprocess(@RequestBody CreateBookingRequest bookingDto){
        List<Seat> seats = choosenSeats(bookingDto.getChoosenSeats());
        double cost = getCost(seats);

        Booking booking=convertBookingDto(bookingDto,cost);
        booking = customerService.createBooking(booking,bookingDto.getPaymentMethod(),bookingDto.getScreenName());

        BookingDetails bookingDetails = convertBooking(booking);
        ResponseEntity<BookingDetails> response = new ResponseEntity<BookingDetails>(bookingDetails,HttpStatus.OK);
        return response;
    }

    /**
     * Fetching all bookings
     * @return
     */
    @GetMapping("fetchallbookings")
    ResponseEntity<List<Booking>> fetchAllBookings(){
        Controller<Object> bookingService = null;
        List<Booking> bookingList = (List<Booking>) bookingService.fetchAllBookings();
        return new ResponseEntity<List<Booking>>(bookingList,HttpStatus.OK);
    }

    /**
     * Fetching ticket with bookingId
     * @param bookingId
     * @return
     */
//    @GetMapping("/getTicket/{id}")
//    <TicketDto>
//    ResponseEntity<TicketDto> fetchTicket(@PathVariable("id") int bookingId){
//        CustomerService bookingService = null;
//        Ticket ticket = bookingService.showTicket(bookingId);
//        TicketDto ticketDto= (TicketDto) convertTicketDto(ticket);
//        ResponseEntity<TicketDto> response = new ResponseEntity<TicketDto>(ticketDto,HttpStatus.OK);
//        return response;
//    }

    /**
     * Fetching booking
     * @param bookingId
     * @return
     */
    @GetMapping("/getbooking/{id}")
    ResponseEntity<Booking> fetchBooking(@PathVariable("id") int bookingId){
        CustomerService bookingService = null;
        Booking booking = bookingService.fetchBookingById(bookingId);
        ResponseEntity<Booking> response = new ResponseEntity<Booking>(booking,HttpStatus.OK);
        return response;
    }

    /**
     * Cancel booking
     * @param bookingId
     * @return
     */
    @DeleteMapping("/cancelbooking/{id}")
    ResponseEntity<String> cancelBooking(@PathVariable("id") int bookingId){
        Controller bookingService = null;
        bookingService.cancelBooking(bookingId);
        ResponseEntity<String> response = new ResponseEntity<String>("",HttpStatus.OK);
        return response;
    }


    //---------------------------------------Theater----------------------------------//

    /**
     * Fetching theaters on city basis
     * @param city
     * @return
     */
//    @GetMapping("/getTheaters/{city}")
//    ResponseEntity<List<Theater>> findTheaters(@PathVariable("city") String city){
//        List<Theater> selectedTheaters = new ArrayList<Theater>();
//        List<Theater> theaterList = getTheaters();
//        for (Theater theater : theaterList) {
//            if(theater.getTheaterCity().equals(city)) {
//                selectedTheaters.add(theater);
//            }
//        }
//        ResponseEntity<List<Theater>> response = new ResponseEntity<List<Theater>>(selectedTheaters,HttpStatus.OK);
//        return response;
//    }
//
//    private List<Theater> getTheaters() {
//    }

    /**
     * Fetching all screen on theater basis
     * @param theaterId
     * @return
     */
//    @GetMapping("/getScreens/{id}")
//    ResponseEntity<List<Screen>> findScreens(@PathVariable("id") int theaterId){
//        List<Screen> selectedScreens = null;
//        List<Theater> theaterList = getTheaters();
//        for (Theater theater : theaterList) {
//            if(theater.getTheaterId()==theaterId) {
//                selectedScreens= theater.getScreenList();
//                break;
//            }
//        }
//        ResponseEntity<List<Screen>> response = new ResponseEntity<List<Screen>>(selectedScreens,HttpStatus.OK);
//        return response;
//    }

//    private List<Theater> getTheaters() {
//
//    }

    /**
     * Fetching all movies
     * @return
     */
//    @GetMapping("/getMovies")
//    ResponseEntity<List<Movie>> findMovies(){
//        List<Movie>     movieList = getMovies();
//        ResponseEntity<List<Movie>> response = new ResponseEntity<List<Movie>>(movieList,HttpStatus.OK);
//        return response;
//    }

    /**
     * Fetching all shows on movie basis
     * @param movieId
     * @return
     */
//    @GetMapping("/getShows/{movieId}")
//    ResponseEntity<List<Show>> findShows(@PathVariable("movieId") int movieId){
//        List<Show> selectedShows = new ArrayList<Show>();
//        String movieName="";
//        List<Movie> selectedMovies = getMovies();
//        for(Movie movie:selectedMovies){
//            if(movie.getMovieId()==movieId){
//                movieName=movie.getMovieName();
//                break;
//            }
//        }
//        List<Show> showList = getShows();
//        for (Show show : showList) {
//            if(show.getMovieName().equals(movieName)) {
//                selectedShows.add(show);
//            }
//        }
//        ResponseEntity<List<Show>> response = new ResponseEntity<List<Show>>(selectedShows,HttpStatus.OK);
//        return response;
//    }


//    private List<Movie> getMovies() {
//    }

//    private List<Show> getShows() {
//         return List<Show>;
//    }

//    private List<Movie> getMovies() {
//    }

    /**
     * Fetching choosenSeats
     * @param seatIds
     * @return
     */
    public List<Seat> choosenSeats(List<Integer> seatIds) {
        List<Seat> seats = new ArrayList<Seat>();
        for(Integer id:seatIds) {
            Seat seat = new Seat(id, BookingStatus.BOOKED, 1542);
            seats.add(seat);
        }
        return seats;
    }

    /**
     * converting bookingDto to booking
     * @param bookingDto
     * @return
     */
    public Booking convertBookingDto(CreateBookingRequest bookingDto,double cost) {
        Booking booking = new Booking();
        booking.setMovieId(bookingDto.getMovieId());
        booking.setShowId(bookingDto.getShowId());
        booking.setSeatIds(bookingDto.getChoosenSeats());
        booking.setTotalCost(cost);
        return booking;
    }

    /**
     * converting bookingDetails
     * @param booking
     * @return
     */
    public BookingDetails convertBooking(Booking booking) {
        BookingDetails bookingDetails = new BookingDetails(booking.getBookingId(), booking.getMovieId(),
                booking.getShowId(), booking.getBookingDate(),
                booking.getTransactionId(), booking.getTotalCost(), booking.getSeatIds());
        return bookingDetails;
    }

    /**
     * Calculating TotalCost
     * @param seatList
     * @return
     */
    public double getCost(List<Seat> seatList) {
        double price=0;
        for(Seat seat:seatList) {
            price=price+seat.getSeatPrice();
        }
        return price;
    }

    /**
     * Converting ticket to ticketDto
     * @param ticket
     * @return
     */
//    public TicketDto convertTicketDto(Ticket ticket) {
//        TicketDto ticketDto = new TicketDto(ticket.getTicketId(),ticket.getNoOfSeats(),ticket.getSeatIds(),ticket.getScreenName());
//        return ticketDto;
//    }

    /**
     * Handling BookingNotFoundException
     * @param exception
     * @return
     */

    @ExceptionHandler(BookingNotFoundException.class)
    public ResponseEntity<String> handleBookingNotFoundException(BookingNotFoundException exception){
        Log.error("Booking Exception",exception);
        String msg = exception.getMessage();
        ResponseEntity<String> response = new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
        return response;
    }


    /**
     * Handling TicketNotFoundException
     * @param exception
     * @return
     */
    @ExceptionHandler(TicketNotFoundException.class)
    public ResponseEntity<String> handleTicketNotFoundException(TicketNotFoundException exception){
        Log.error("Ticket Exception",exception);
        String msg = exception.getMessage();
        ResponseEntity<String> response = new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
        return response;
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<String> handleAll(Throwable ex) {
        Log.error("exception caught", ex);
        String msg = ex.getMessage();
        ResponseEntity<String> response = new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
        return response;
    }

    //-----------------------------------Movie------------------------------------//



    @Autowired
    private AdminService adminService;

    @PostMapping("/addmovie")
    private ResponseEntity<MovieDetailsDto> addMovie(@RequestBody MovieRequestDto movieDto){
        Movie movie = convertMovie(movieDto);
        movie = adminService.save(movie);
        MovieDetailsDto detailsDto = convertMovieDetails(movie) ;
        ResponseEntity<MovieDetailsDto> response = new ResponseEntity<MovieDetailsDto>(detailsDto, HttpStatus.OK);
        return response;
    }

    @GetMapping("/getmovie/{id}")
    private ResponseEntity<MovieDetailsDto> fetchById(@PathVariable("id") int movieId){;
        Movie movie = customerService.fetchMovieById(movieId);
        MovieDetailsDto detailsDto = convertMovieDetails(movie) ;
        ResponseEntity<MovieDetailsDto> response = new ResponseEntity<MovieDetailsDto>(detailsDto, HttpStatus.OK);
        return response;
    }

    private MovieDetailsDto convertMovieDetails(Movie movie) {

        MovieDetailsDto detailsDto = new MovieDetailsDto() ;
        detailsDto.setMovieId(movie.getMovieId());
        detailsDto.setMovieName(movie.getMovieName());
        detailsDto.setMovieDirector(movie.getMovieDirector());
        detailsDto.setMovieLength(movie.getMovieLength());
        detailsDto.setMovieReleaseDate(movie.getMovieReleaseDate());
        detailsDto.setLanguages(movie.getLanguages());
        detailsDto.setMovieGenre(movie.getMovieGenre()) ;
        return null;
    }

    @GetMapping("getallmovies")
    ResponseEntity<List<Movie>> fetchAll(){
        List<Movie> movieList = customerService.fetchAllMovie();
        ResponseEntity<List<Movie>> response = new ResponseEntity<List<Movie>>(movieList, HttpStatus.OK);
        return response;
    }

    @DeleteMapping("/deletemovie/{id}")
    private ResponseEntity<String> delete(@PathVariable("id") int movieId)
    {
        String result  = adminService.deleteMovieById(movieId);
        ResponseEntity<String> response = new ResponseEntity<String>(result, HttpStatus.OK);
        return response;
    }

    private Movie convertMovie(MovieRequestDto movieDto) {
        Movie movie = new Movie();
        movie.setMovieName(movieDto.getMovieName());
        movie.setMovieDirector(movieDto.getMovieDirector());
        movie.setMovieGenre(movieDto.getMovieGenre());
        movie.setMovieLength(movieDto.getMovieLength());
        movie.setMovieReleaseDate(movieDto.getMovieReleaseDate());
        movie.setLanguages(movieDto.getLanguages());
        return movie;
    }

    @ExceptionHandler(MovieNotFoundException.class)
    private ResponseEntity<String> handleMovieNotFound(MovieNotFoundException ex){
        Log.error("handleMovieNotFound()",ex);
        String msg = ex.getMessage();
        ResponseEntity<String> response = new ResponseEntity<String>(msg, HttpStatus.NOT_FOUND);
        return response;
    }


    //------------------------------------SCREEN----------------------------//


    /**
     * Adding Screen to theater
     * @param screenDto
     * @return
     */
//    @PostMapping("/add")
//    public ResponseEntity<ScreenDetailsDto> addScreen(@RequestBody CreateScreenRequest screenDto){
//        Screen screen = convertScreen(screenDto);
//        Screen newScreen = adminService.addScreen(screen);
//        ScreenDetailsDto detailsDto = convertScreenDetails(newScreen);
//        ResponseEntity<ScreenDetailsDto> response = new ResponseEntity<ScreenDetailsDto>(detailsDto, HttpStatus.OK);
//        return response;
//    }

//    /**
//     * Fetching all screens
//     * @return
//     */
//    @GetMapping
//    public ResponseEntity<List<Screen>> fetchAll()
//    {
//        List<Screen> screenList = screenService.fetchAll();
//        ResponseEntity<List<Screen>> response = new ResponseEntity<List<Screen>>(screenList, HttpStatus.OK);
//        return response;
//    }

    /**
     * Fetching screen by screen id
     * @param screenId
     * @return
     */
    @GetMapping("/getscreen/{id}")
    public ResponseEntity<ScreenDetailsDto> fetchScreens(@PathVariable("id") int screenId) {
        Screen screen = customerService.fetchScreenById(screenId);
        ScreenDetailsDto detailsDto = convertScreenDetails(screen);
        ResponseEntity<ScreenDetailsDto> response = new ResponseEntity<ScreenDetailsDto>(detailsDto, HttpStatus.OK);
        return response;
    }

    /**
     * Deleting screen by screen id
     * @param screenId
     * @return
     */
    @DeleteMapping("/deletescreen/{id}")
    public ResponseEntity<Boolean> deleteScreenById(@PathVariable("id") int screenId){
        Boolean result = adminService.deleteScreenById(screenId);
        ResponseEntity<Boolean> response = new ResponseEntity<Boolean>(Boolean.valueOf(result), HttpStatus.OK);
        return response;
    }

//    public Screen convertScreen(CreateScreenRequest screenDto)
//    {
//        Screen screen = new Screen();
//        screen.setColumns(screenDto.getColumns());
//        screen.setRows1(screenDto.getRows());
//        screen.setScreenName(screenDto.getScreenName());
//        screen.setTheaterId(screenDto.getTheaterId());
//        screen.setShowList(addShow());
//        return screen;
//    }

    public ScreenDetailsDto convertScreenDetails(Screen screen) {
        ScreenDetailsDto detailsDto = new ScreenDetailsDto();
        detailsDto.setScreenId(screen.getScreenId());
        detailsDto.setTheaterId((Integer) screen.getTheaterId());
        detailsDto.setScreenName(screen.getScreenName());
        detailsDto.setRows(screen.getRows1());
        detailsDto.setColumns(screen.getColumns());
        detailsDto.setShowList(addShow());
        return detailsDto;
    }

    private List<Integer> addShow(){
        List<Integer> showList = new ArrayList<Integer>();
        showList.add(101);
        showList.add(102);
        return showList;
    }

    @ExceptionHandler(ScreenNotFoundException.class)
    private ResponseEntity<String> handleTheaterNotFound(ScreenNotFoundException ex){
        Log.error("handleScreenNotFound()",ex);
        String msg = ex.getMessage();
        ResponseEntity<String> response = new ResponseEntity<String>(msg, HttpStatus.NOT_FOUND);
        return response;
    }

//    @ExceptionHandler(Throwable.class)
//    public ResponseEntity<String> handleAll(Throwable ex) {
//        Log.error("handleAll()", ex);// this will get logged
//        String msg = ex.getMessage();
//        ResponseEntity<String> response = new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
//        return response;
//    }


    //--------------------------------------THEATER---------------------------------//
    @Autowired
    private AdminService service;

    /**
     * Adding theater
     * @param theaterDto
     * @return
     */
//    @PostMapping("/add")
//    public <CreateTheaterRequest> ResponseEntity<TheaterDetailsDto> addTheater(@RequestBody CreateTheaterRequest theaterDto) {
//        Theater theater = convert(theaterDto);
//        theater = service.save(theater);
//        TheaterDetailsDto detailsDto = convertTheaterDetails(theater);
//        ResponseEntity<TheaterDetailsDto> response = new ResponseEntity<TheaterDetailsDto>(detailsDto, HttpStatus.OK);
//        return response;
//    }
//
//    private <CreateTheaterRequest> Theater convert(CreateTheaterRequest theaterDto) {
//    }

    /**
     * Fetching all theaters
     * @return
     */
//    @GetMapping
//    public ResponseEntity<List<Theater>> fetchAll() {
//        List<Theater> theaters = service.fetchAll();
//        ResponseEntity<List<Theater>> response = new ResponseEntity<>(theaters, HttpStatus.OK);
//        return response;
//    }

    /**
     * Fetching theater by theater id
     * @param theaterId
     * @return
     */
//    @GetMapping("/get/{id}")
//    public ResponseEntity<TheaterDetailsDto> fetchById(@PathVariable("id") int theaterId) {
//        Theater theater = service.fetchById(theaterId);
//        TheaterDetailsDto detailsDto = convertTheaterDetails(theater);
//        ResponseEntity<TheaterDetailsDto> response = new ResponseEntity<TheaterDetailsDto>(detailsDto, HttpStatus.OK);
//        return response;
//    }

    /**
     * Deleting theater by theater id
     * @param theaterId
     * @return
     */
    @DeleteMapping("/deletetheater/{id}")
    public ResponseEntity<Boolean> deleteTheater(@PathVariable("id") int theaterId) {
        Boolean result = service.deleteTheaterById(theaterId);
        ResponseEntity<Boolean> response = new ResponseEntity<Boolean>(Boolean.valueOf(result), HttpStatus.OK);
        return response;
    }

    /**
     * convert from theater: dto -> entity
     * @param theaterdto
     * @return
     */
//    public <CreateTheaterRequest> Theater convert(CreateTheaterRequest theaterdto) {
//        Theater theater = new Theater();
//        theater.setTheaterId(theaterdto.getTheaterId());
//        theater.setTheaterName(theaterdto.getTheaterName());
//        theater.setTheaterCity(theaterdto.getTheaterCity());
//        theater.setManagerName(theaterdto.getManagerName());
//
//        theater.setManagerContact(theaterdto.getManagerContact());
//        theater.setMovieList(addMovie());
//        return theater;
//    }

    /**
     * convert from theater: entity -> detailsdto
     * @param theater
     * @return
     */
    public TheaterDetailsDto convertTheaterDetails(Theater theater) {
        TheaterDetailsDto detailsDto = new TheaterDetailsDto();
        detailsDto.setTheaterId(theater.getTheaterId());
        detailsDto.setTheaterName(theater.getTheaterName());
        detailsDto.setTheaterCity(theater.getTheaterCity());
        detailsDto.setScreenList(theater.getScreenList());
        detailsDto.setMovieList(addMovie());
        detailsDto.setManagerName(theater.getManagerName());
        detailsDto.setManagerContact(theater.getManagerContact());
        return detailsDto;
    }

    public List<Integer> addMovie()
    {
        List<Integer> movieList = new ArrayList<Integer>();
        movieList.add(101);
        movieList.add(102);
        return movieList;
    }

    @ExceptionHandler(TheaterNotFoundException.class)
    public ResponseEntity<String> handleCustomerNotFound(TheaterNotFoundException ex) {
        Log.error("handleTheaterNotFound()", ex);
        String msg = ex.getMessage();
        ResponseEntity<String> response = new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
        return response;
    }

//    @ExceptionHandler(Throwable.class)
//    public ResponseEntity<String> handleAll(Throwable ex) {
//        Log.error("handleAll()", ex);// this will get logged
//        String msg = ex.getMessage();
//        ResponseEntity<String> response = new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
//        return response;
//    }


}
