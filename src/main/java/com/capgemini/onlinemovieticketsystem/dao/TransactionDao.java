package com.capgemini.onlinemovieticketsystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.onlinemovieticketsystem.entity.BookingTransaction;

public interface TransactionDao extends JpaRepository<BookingTransaction, Integer>{

}
