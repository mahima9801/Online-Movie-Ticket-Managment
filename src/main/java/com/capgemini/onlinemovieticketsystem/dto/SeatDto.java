package com.capgemini.onlinemovieticketsystem.dto;

import com.capgemini.onlinemovieticketsystem.entity.BookingStatus;

public class SeatDto {
	private int seatId;
	private BookingStatus seatStatus;
	private Integer seatPrice;
	public int getSeatId() {
		return seatId;
	}
	public void setSeatId(int seatId) {
		this.seatId = seatId;
	}
	public BookingStatus getSeatStatus() {
		return seatStatus;
	}
	public void setSeatStatus(BookingStatus seatStatus) {
		this.seatStatus = seatStatus;
	}
	public Integer getSeatPrice() {
		return seatPrice;
	}
	public void setSeatPrice(Integer seatPrice) {
		this.seatPrice = seatPrice;
	}

}
