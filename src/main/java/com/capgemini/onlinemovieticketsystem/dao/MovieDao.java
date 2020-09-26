package com.capgemini.onlinemovieticketsystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.onlinemovieticketsystem.entity.Movie;

@Repository
public interface MovieDao extends JpaRepository<Movie, Integer> {

}