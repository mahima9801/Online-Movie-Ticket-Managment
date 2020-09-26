package com.capgemini.onlinemovieticketsystem.serviceimpl;
import com.capgemini.onlinemovieticketsystem.service.CustomerService;

import java.util.List;
import java.util.Optional;
import com.capgemini.onlinemovieticketsystem.dao.MovieDao;
import com.capgemini.onlinemovieticketsystem.dao.ScreenDao;
import com.capgemini.onlinemovieticketsystem.dao.ShowDao;
import com.capgemini.onlinemovieticketsystem.dao.TheaterDao;
import com.capgemini.onlinemovieticketsystem.entity.*;
import com.capgemini.onlinemovieticketsystem.exception.MovieNotFoundException;
import com.capgemini.onlinemovieticketsystem.exception.ShowNotFoundException;
import com.capgemini.onlinemovieticketsystem.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;

public class AdminServiceImpl implements AdminService
{
  @Autowired
  private MovieDao  movieDao;

  @Autowired
  private ShowDao showDao;

  @Autowired
  private TheaterDao theaterDao;

  @Autowired
  private ScreenDao screenDao;


  //--------------------------------------Movie-----------------------------------------------//

    @Autowired
    private AdminService service;
  @Override
  public Movie addMovie(Movie movie)
  {
	  return movieDao.save(movie);  
  }
  
//  @Override
//  public String deleteMovie(int movieId)
//  {
//	  Optional<Movie> optional = movieDao.findById(movieId);
//      if (optional.isPresent()) {
//          Movie movie = optional.get();
//          movieDao.delete(movie);
//          return "Deleted Successfully";
//      }
//      throw new MovieNotFoundException("Movie not found for id=" + movieId);
//
//  }

@Override
public Screen addScreen(Screen screen) {
	return screenDao.save(screen);
}

    @Override
    public Theater addTheater(Theater theater) {
        return theaterDao.save(theater);
    }


@Override
public Show addShow(Show show) {
	return showDao.save(show) ;
}

    @Override
    public Booking addBooking(Booking booking) {
        return null;
    }

    @Override
public String deleteShowById(int showId) {
	Optional<Show> optional = showDao.findById(showId);
    if (optional.isPresent()) {
       Show show = optional.get();
        showDao.delete(show);
        return "Show Deleted Succesully";
    }
    throw new ShowNotFoundException("Show not found for id=" +showId);
   
}

    @Override
    public Boolean deleteTheaterById(int theaterId) {
        return null;
    }

//    @Override
//    public Boolean deleteTheaterById(int theaterId) {
//        Theater theater = fetchTheaterById(theaterId);
//        List<Screen> screenList = theater.getScreenList();
//        for (Screen screen : screenList) {
//            int screenId = screen.getScreenId();
//            service.deleteTheaterById(screenId);
//        }
//        theaterDao.delete(theater);
//        return true;
//    }

    @Override
    public String deleteMovieById(int movieId) {
        Optional<Movie> optional = movieDao.findById(movieId);
        if (optional.isPresent()) {
            Movie movie = optional.get();
            movieDao.delete(movie);
            return "Movie Deleted Succesully";
        }
        throw new MovieNotFoundException("Movie not found for id=" +movieId);
    }

    @Override
    public Boolean deleteScreenById(int screenId) {
        return null;
    }

    @Override
    public Movie save(Movie movie) {
            Movie mov = movieDao.save(movie);
            return mov;
        }

    }

//    @Override
//    public Boolean deleteScreenById(int screenId) {
//        Theater theater = fetchById(theaterId);
//        List<Screen> screenList = theater.getScreenList();
//        for (Screen screen : screenList) {
//            int screenId = screen.getScreenId();
//            service.delete(screenId);
//        }
//        screenDao.delete(theater);
//        return true;
//    }



