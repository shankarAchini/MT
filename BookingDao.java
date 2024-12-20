package com.project.Hotel.Reservation.System.dao;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.Hotel.Reservation.System.dto.Booking;
import com.project.Hotel.Reservation.System.dto.Room;
import com.project.Hotel.Reservation.System.exception.BookingOverlapException;
import com.project.Hotel.Reservation.System.repo.BookingRepo;

@Repository
public class BookingDao {

	@Autowired
	BookingRepo bookingRepo;

	@Autowired
	RoomDao roomDao;

	public Booking saveBooking(Booking booking, int roomId) {
		Room room = roomDao.fetchRoomById(roomId);
		if (room == null) {
			throw new IllegalArgumentException("Invalid Room ID: Room does not exist.");
		}
		LocalDate startDate = booking.getStartDate();
		LocalDate endDate = booking.getEndDate();

		if (startDate.isAfter(endDate)) {
			throw new IllegalArgumentException("Start date cannot be after end date.");
		}
		List<Booking> dbBookings = room.getBookings();
		for (Booking existingBooking : dbBookings) {
			if (!(endDate.isBefore(existingBooking.getStartDate())
					|| startDate.isAfter(existingBooking.getEndDate()))) {
				throw new BookingOverlapException("The selected dates are already booked.");
			}
		}
		long days = ChronoUnit.DAYS.between(startDate, endDate) + 1;
		System.out.println("Calculated Days: " + days);
		double totalPrice = days * room.getRoomPrice();
		booking.setTotalPrice(totalPrice);
		booking.setRoom(room);
		return bookingRepo.save(booking);
	}

	public Booking fetchBookingById(int bookingId) {
		Optional<Booking> optionalBooking = bookingRepo.findById(bookingId);
		if (optionalBooking.isPresent()) {
			return optionalBooking.get();
		}
		return null;
	}

	public List<Booking> fetchAllBooking() {
		return bookingRepo.findAll();
	}

	public Booking updateBookingById(int oldBookingId, Booking newBooking) {
		newBooking.setBookingId(oldBookingId);
		return bookingRepo.save(newBooking);
	}

	public Booking deleteBookingById(int bookingId) {
		Optional<Booking> optionalBooking = bookingRepo.findById(bookingId);
		if (optionalBooking.isPresent()) {
			Booking booking = optionalBooking.get();
			if (booking.getRoom() != null) {
				booking.getRoom().getBookings().remove(booking);
			}
			bookingRepo.delete(booking);
			return booking;
		}
		return null;

	}

	public List<Booking> fetchBookingByEmail(String Email) {
		return bookingRepo.findByCustomerEmail(Email);
	}

}
