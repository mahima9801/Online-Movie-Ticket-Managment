package com.capgemini.onlinemovieticketsystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.onlinemovieticketsystem.entity.Ticket;

public interface TicketDao extends JpaRepository<Ticket, Integer>{

}
