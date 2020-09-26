package com.capgemini.onlinemovieticketsystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.onlinemovieticketsystem.entity.Theater;

import java.util.List;

@Repository
public interface TheaterDao extends JpaRepository<Theater, Integer> {


}