package com.project.Hotel.Reservation.System.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.Hotel.Reservation.System.dto.Booking;
import com.project.Hotel.Reservation.System.service.BookingService;
import com.project.Hotel.Reservation.System.util.ResponseStructure;
import com.project.Hotel.Reservation.System.util.ResponseStructureList;

@RestController
public class BookingController {

	@Autowired
	BookingService bookingService;

	@PostMapping("/saveBooking")
	public ResponseStructure<Booking> saveBooking( @RequestBody Booking booking,@RequestParam int roomId) {
		return bookingService.saveBooking(booking, roomId);
	}

	@GetMapping("/fetchBookingById")
	public ResponseStructure<Booking> fetchBookingById(@RequestParam int bookingId) {
		return bookingService.fetchBookingById(bookingId);
	}

	@GetMapping("/fetchAllBooking")
	public ResponseStructureList<Booking> fetchAllBooking() {
		return bookingService.fetchAllBooking();
	}

	@PutMapping("/updateBookingById")
	public ResponseStructure<Booking> updateBookingById(@RequestParam int oldBookingId, @RequestBody Booking newBooking) {
		return bookingService.updateBookingById(oldBookingId, newBooking);
	}

	@DeleteMapping("/deleteBookingById")
	public ResponseStructure<Booking> deleteBookingById(@RequestParam int bookingId) {
		return bookingService.deleteBookingById(bookingId);
	}

	@GetMapping("/fetchBookingByEmail")
	public ResponseStructureList<Booking> fetchBookingByEmail(@RequestParam String Email) {
		return bookingService.fetchBookingByEmail(Email);
	}
	
}
