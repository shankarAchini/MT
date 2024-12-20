package com.project.Hotel.Reservation.System.exception;

public class RoomIdNotFound extends RuntimeException {
	private String message = "room id not found";

	public String getMessage() {
		return message;
	}
}
