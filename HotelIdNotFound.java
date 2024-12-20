package com.project.Hotel.Reservation.System.exception;

public class HotelIdNotFound extends RuntimeException {
	private String message = "hotel id not found";

	public String getMessage() {
		return message;
	}

}
