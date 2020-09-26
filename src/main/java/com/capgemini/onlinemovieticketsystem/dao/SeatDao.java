package com.capgemini.onlinemovieticketsystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.onlinemovieticketsystem.entity.Seat;



@Repository
public interface SeatDao extends JpaRepository<Seat, Integer>
{
	
}