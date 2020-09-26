package com.capgemini.onlinemovieticketsystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.capgemini.onlinemovieticketsystem.entity.Booking;


@Repository
public interface BookingDao extends JpaRepository<Booking, Integer> {

}
