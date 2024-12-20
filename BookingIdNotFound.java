package com.project.Hotel.Reservation.System.exception;

public class BookingIdNotFound extends RuntimeException {
	private String message = "Booking id not found";

	public String getMessage() {
		return message;
	}
}
