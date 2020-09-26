package com.capgemini.onlinemovieticketsystem.dto;

public class SeatDto {


        private int seatId;

        private double seatPrice;

        public int getSeatId() {
            return seatId;
        }

        public void setSeatId(int seatId) {
            this.seatId = seatId;
        }

        public double getSeatPrice() {
            return seatPrice;
        }

        public void setSeatPrice(double seatPrice) {
            this.seatPrice = seatPrice;
        }

        @Override
        public String toString() {
            return "SeatDto [seatId=" + seatId + ", price=" + seatPrice + "]";
        }
    }




