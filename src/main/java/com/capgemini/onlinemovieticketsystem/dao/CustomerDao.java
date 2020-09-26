package com.capgemini.onlinemovieticketsystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.onlinemovieticketsystem.entity.Customer;

@Repository
public interface CustomerDao extends JpaRepository<Customer, Integer> {

}