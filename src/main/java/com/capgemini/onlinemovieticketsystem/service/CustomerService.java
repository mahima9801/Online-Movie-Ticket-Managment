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

	public Seat addSeat(Seat seat);

	public Seat getSeat(int id);

	public Seat blockSeat(int id);

	public Seat bookSeat(int id);

	public Seat cancelSeat(int id);

	List<Seat> getAllSeats();


//-----------------------------------------------------------------------------------------------------//

}
