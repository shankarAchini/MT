package com.project.Hotel.Reservation.System.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.project.Hotel.Reservation.System.util.ResponseStructure;

@RestControllerAdvice
public class ApplicationExceptionHandler {

	@Autowired
	ResponseStructure<String> responseStructure;

	@ExceptionHandler(HotelIdNotFound.class)
	public ResponseStructure<String> hotelIdNotFound(HotelIdNotFound hotelIdNotFound) {
		responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
		responseStructure.setMessage(" id not found in db");
		responseStructure.setData(hotelIdNotFound.getMessage());
		return responseStructure;
	}
	
	@ExceptionHandler(RoomIdNotFound.class)
	public ResponseStructure<String> roomIdNotFound(RoomIdNotFound roomIdNotFound) {
		responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
		responseStructure.setMessage(" id not found in db");
		responseStructure.setData(roomIdNotFound.getMessage());
		return responseStructure;
	}
	@ExceptionHandler(BookingIdNotFound.class)
	public ResponseStructure<String> bookingIdNotFound(BookingIdNotFound bookingIdNotFound) {
		responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
		responseStructure.setMessage(" id not found in db");
		responseStructure.setData(bookingIdNotFound.getMessage());
		return responseStructure;
	}


	

}
