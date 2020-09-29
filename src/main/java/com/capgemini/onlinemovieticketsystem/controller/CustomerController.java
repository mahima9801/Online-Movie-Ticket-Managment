package com.capgemini.onlinemovieticketsystem.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.capgemini.onlinemovieticketsystem.exceptions.SeatNotFoundException;
import com.capgemini.onlinemovieticketsystem.dto.SeatDto;
import com.capgemini.onlinemovieticketsystem.dto.ShowDto;
import com.capgemini.onlinemovieticketsystem.entity.Seat;
import com.capgemini.onlinemovieticketsystem.service.CustomerService;
import com.capgemini.onlinemovieticketsystem.entity.Show;
import com.capgemini.onlinemovieticketsystem.exceptions.ShowNotFoundException;


@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	private static final Logger Log = LoggerFactory.getLogger(CustomerController.class);
	
	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/addSeat")
	public ResponseEntity<Seat> addSeat(@RequestBody SeatDto requestData) {
	    Seat seat = convertToSeat(requestData);
	    Seat seat1=customerService.addSeat(seat);
	    ResponseEntity<Seat> response = new ResponseEntity<Seat>(seat1, HttpStatus.OK);
	    return response;
	}
	
	public Seat convertToSeat(SeatDto requestData)  {
	    Seat seat = new Seat();
	    seat.setSeatId(requestData.getSeatId());
	    seat.setSeatStatus(requestData.getSeatStatus());
	    seat.setSeatPrice(requestData.getSeatPrice());
	    
	    return seat;
	}
	
	//Seat Module

	/*
	 * blockSeat method which will change the status of the seat to "BLOCKED".
	 */
	
	@PutMapping("/blockSeat/{id}")
    public ResponseEntity<Seat> blockSeat(@PathVariable("id") int seatId) throws SeatNotFoundException {
        Seat seat = customerService.blockSeat(seatId);
        ResponseEntity<Seat> response = new ResponseEntity<Seat>(seat, HttpStatus.OK);
        return response;
    }
	
	/*
	 * bookSeat method which will change the status of the seat to "BOOKED".
	 */
	
	@PutMapping("/bookSeat/{id}")
    public ResponseEntity<Seat> bookSeat(@PathVariable("id") int seatId) throws SeatNotFoundException {
        Seat seat = customerService.bookSeat(seatId);
        ResponseEntity<Seat> response = new ResponseEntity<Seat>(seat, HttpStatus.OK);
        return response;
    }
	
	/*
	 * cancelSeat method which will change the status of the seat to "AVAILABLE".
	 */
	
	@PutMapping("/cancelSeat/{id}")
    public ResponseEntity<Seat> cancelSeat(@PathVariable("id") int seatId) throws SeatNotFoundException {
        Seat seat = customerService.cancelSeat(seatId);
        ResponseEntity<Seat> response = new ResponseEntity<Seat>(seat, HttpStatus.OK);
        return response;
    }
	
	/*
	 * findSeatById method which will get the data of Seat table through "seatId".
	 */
	
	@GetMapping("/find/{id}")
    public ResponseEntity<Seat> findSeatById(@PathVariable("id")  int seatId) throws SeatNotFoundException {
        Seat seat = customerService.getSeat(seatId);
        ResponseEntity<Seat> response = new ResponseEntity<>(seat, HttpStatus.OK);
        return response;
    }
	
	/*
	 * getAllSeats method which will fetch all the data stored in Seat table.
	 */
	
	@GetMapping("/getAllSeats")
	public ResponseEntity<List<Seat>> getAllSeats(){
		List<Seat> seatList = customerService.getAllSeats();
		return new ResponseEntity<List<Seat>>(seatList,HttpStatus.OK);
	}
	
	/*
	 * seatNotFound method to handle the exception
	 */

    @ExceptionHandler(SeatNotFoundException.class)
    public ResponseEntity<String> seatNotFound(SeatNotFoundException ex) {
        Log.error("SeatNotFound()", ex);
        String msg = ex.getMessage();
        ResponseEntity<String> response = new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
        return response;
    }
    
    //Show Module
    
    @PostMapping("/addShow")
	public ResponseEntity<Show> addShow(@RequestBody ShowDto requestData) {
	    Show show = convertToShow(requestData);
	    Show show1=customerService.addShow(show);
	    ResponseEntity<Show> response = new ResponseEntity<Show>(show1, HttpStatus.OK);
	    return response;
	}
	
	public Show convertToShow(ShowDto requestData)  {
	    Show show = new Show();
	    show.setShowId(requestData.getShowId());
	    show.setShowStartTime(requestData.getShowStartTime());
	    show.setShowEndTime(requestData.getShowEndTime());
	    show.setShowName(requestData.getShowName());
	    
	    return show;
	}
    
    /*
     * findShows method will fetch all the shows stored in Show table.
     */
    @GetMapping("/getShows")
    ResponseEntity<List<Show>> findShows(){
        List<Show> showList = customerService.fetchAllShows();
       ResponseEntity<List<Show>> response = new ResponseEntity<List<Show>>(showList,HttpStatus.OK);
        return response;
    }
    
    /*
     * findShowById method will fetch the show data through "showId".
     */
    
    @GetMapping("/getShowById/{id}")
    public ResponseEntity<Show> findShowById(@PathVariable("id") int showId) throws ShowNotFoundException{
    	Show show = customerService.findById(showId);
    	ResponseEntity<Show> response = new ResponseEntity<>(show, HttpStatus.OK);
		return response;
    	
    }
    /*
     * showNotFound method to handle the exception.
     */
    
    @ExceptionHandler(ShowNotFoundException.class)
    public ResponseEntity<String> showNotFound(ShowNotFoundException ex) {
        Log.error("showNotFound()", ex);
        String msg = ex.getMessage();
        ResponseEntity<String> response = new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
        return response;
    }
}
