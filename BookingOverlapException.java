package com.project.Hotel.Reservation.System.exception;

public class BookingOverlapException extends RuntimeException {
	public BookingOverlapException(String message) {
        super(message);
    }
}
