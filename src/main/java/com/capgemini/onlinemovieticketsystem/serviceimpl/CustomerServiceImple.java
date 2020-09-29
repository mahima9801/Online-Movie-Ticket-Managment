package com.capgemini.onlinemovieticketsystem.serviceimpl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.capgemini.onlinemovieticketsystem.dao.SeatDao;
import com.capgemini.onlinemovieticketsystem.dao.ShowDao;
import com.capgemini.onlinemovieticketsystem.entity.BookingStatus;
import com.capgemini.onlinemovieticketsystem.entity.Seat;
import com.capgemini.onlinemovieticketsystem.entity.Show;
import com.capgemini.onlinemovieticketsystem.exceptions.SeatNotFoundException;
import com.capgemini.onlinemovieticketsystem.exceptions.ShowNotFoundException;
import com.capgemini.onlinemovieticketsystem.service.CustomerService;

@Service
@Transactional
public  class CustomerServiceImpl implements CustomerService{
	@Autowired
	private SeatDao seatDao;
	
	@Autowired
	private ShowDao showDao;

	@Override
	public Seat addSeat(Seat seat) {
		seatDao.save(seat);
		return seat;
	}
	
	/*
	 * getSeat method which will get the data of particular seat from Seat table through "seatId".
	 */
	
	@Override
	public Seat getSeat(int seatId) throws SeatNotFoundException {
        Optional<Seat> optional = seatDao.findById(seatId);
        if(optional.isPresent())
        {
            Seat seat = optional.get();
           	return seat;
        }
        	throw new SeatNotFoundException("Seat not found for id:"+seatId);
    }
	
	/*
	 * blockSeat method which will change the status of the seat to "BLOCKED".
	 */
	
	@Override
	public Seat blockSeat(int seatId) throws SeatNotFoundException {
      Seat seat = getSeat(seatId);
      seat.setSeatStatus(BookingStatus.BLOCKED);
      seatDao.save(seat);
      return seat;
  }
	
	/*
	 * bookSeat method which will change the status of the seat to "BOOKED".
	 */
	@Override
	public Seat bookSeat(int id) throws SeatNotFoundException {
      Seat seat = getSeat(id);
      seat.setSeatStatus(BookingStatus.BOOKED);
      seatDao.save(seat);
      return seat;
  }
	
	/*
	 * cancelSeat method which will change the status of the seat to "AVAILABLE".
	 */
	@Override
	public Seat cancelSeat(int id) throws SeatNotFoundException {
      Seat seat = getSeat(id);
      seat.setSeatStatus(BookingStatus.AVAILABLE);
      seatDao.save(seat);
      return seat;
  }
	
	/*
	 * getAllSeats method which will fetch all the data stored in Seat table.
	 */
	@Override
	public List<Seat> getAllSeats() {
		 List<Seat> seat = seatDao.findAll();
	     return seat;
	}

	//Show Module
	@Override
	public Show addShow(Show show) {
		showDao.save(show);
		return show;
	}
	
	/*
     * findById method will fetch the data of particular show from Show table through "showId".
     */

	@Override
	public Show findById(int showId) throws ShowNotFoundException {
		Optional<Show> optional = showDao.findById(showId);
		if(optional.isPresent()) {
			Show show = optional.get();
			return show;
		}
		throw new ShowNotFoundException("Show not found for id:"+showId);
	}
	
	/*
     * fetchAllShows method will fetch all the shows stored in Show table.
     */
	@Override
	public List<Show> fetchAllShows() {
		 List<Show> shows = showDao.findAll();
	     return shows;
	}


	
	
}
