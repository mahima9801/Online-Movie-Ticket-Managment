package com.capgemini.onlinemovieticketsystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.onlinemovieticketsystem.entity.Show;

@Repository
public interface ShowDao extends JpaRepository<Show, Integer> {

}