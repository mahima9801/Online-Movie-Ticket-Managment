package com.capgemini.onlinemovieticketsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.capgemini.onlinemovieticketsystem.entity.Seat;
import com.capgemini.onlinemovieticketsystem.entity.Show;

@Service
public interface CustomerService {

	//-----------------------------------SHOW-----------------------------//

	Show addShow(Show show);

	Show findById(int showId) throws ShowNotFoundException;
		
	List<Show> fetchAllShows();

	//------------------------------------SEAT-----------------------------------------------//

	Seat addSeat(Seat seat);
		
	Seat getSeat(int seatId) throws SeatNotFoundException;
		
	Seat blockSeat(int seatId) throws SeatNotFoundException;
		
	Seat bookSeat(int seatId) throws SeatNotFoundException;
		
	Seat cancelSeat(int seatId) throws SeatNotFoundException; 
		
	List<Seat> getAllSeats();


//-----------------------------------------------------------------------------------------------------//

}
