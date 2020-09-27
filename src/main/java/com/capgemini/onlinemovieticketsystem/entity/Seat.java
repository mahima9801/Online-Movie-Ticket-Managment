package com.capgemini.onlinemovieticketsystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Seat")
public class Seat {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="seatId")
	private int seatId;
	
	@Enumerated(EnumType.STRING)
    private SeatStatus seatStatus;
	
	@Column(name="seatPrice")
	private double seatPrice;
	
	public Seat() {
	
	}

	public Seat(int seatId, SeatStatus seatStatus, double seatPrice) {
		super();
		this.seatId = seatId;
		this.seatStatus = seatStatus;
		this.seatPrice = seatPrice;
	}

	public int getSeatId() {
		return seatId;
	}

	public void setSeatId(int seatId) {
		this.seatId = seatId;
	}

	public SeatStatus getSeatStatus() {
		return seatStatus;
	}

	public void setSeatStatus(SeatStatus seatStatus) {
		this.seatStatus = seatStatus;
	}

	public double getSeatPrice() {
		return seatPrice;
	}

	public void setSeatPrice(double seatPrice) {
		this.seatPrice = seatPrice;
	}
	
	public SeatStatus blockSeat(){
		this.setSeatStatus(SeatStatus.AVAILABLE.BLOCKED);
		return this.seatStatus;
	}
	
	public Seat bookSeat(){
		this.setSeatStatus(SeatStatus.AVAILABLE.BOOKED);
		return this;
	}
	
	public Seat cancelSeatBooking(){
		this.setSeatStatus(SeatStatus.AVAILABLE.AVAILABLE);
		return this;
	}
}
