package com.project.Hotel.Reservation.System.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.project.Hotel.Reservation.System.dao.BookingDao;
import com.project.Hotel.Reservation.System.dao.RoomDao;
import com.project.Hotel.Reservation.System.dto.Booking;
import com.project.Hotel.Reservation.System.dto.Room;
import com.project.Hotel.Reservation.System.exception.BookingIdNotFound;
import com.project.Hotel.Reservation.System.exception.RoomIdNotFound;
import com.project.Hotel.Reservation.System.util.ResponseStructure;
import com.project.Hotel.Reservation.System.util.ResponseStructureList;

@Service
public class BookingService {

	@Autowired
	BookingDao bookingDao;

	@Autowired
	RoomDao roomDao;

	@Autowired
	ResponseStructure<Booking> responseStructure;

	@Autowired
	ResponseStructureList<Booking> responseStructureList;

	public ResponseStructure<Booking> saveBooking(Booking booking, int roomId) {
		Room room = roomDao.fetchRoomById(roomId);
		if (room != null) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("success");
			responseStructure.setData(bookingDao.saveBooking(booking, roomId));
			return responseStructure;
		} else {
			throw new RoomIdNotFound();
		}
	}

	public ResponseStructure<Booking> fetchBookingById(int bookingId) {
		Booking booking = bookingDao.fetchBookingById(bookingId);
		if (booking != null) {
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			responseStructure.setMessage("success");
			responseStructure.setData(bookingDao.fetchBookingById(bookingId));
			return responseStructure;
		} else {
			throw new BookingIdNotFound();
		}
	}

	public ResponseStructureList<Booking> fetchAllBooking() {
		responseStructureList.setStatusCode(HttpStatus.FOUND.value());
		responseStructureList.setMessage("success");
		responseStructureList.setDataList(bookingDao.fetchAllBooking());
		return responseStructureList;
	}

	public ResponseStructure<Booking> updateBookingById(int oldBookingId, Booking newBooking) {
		Booking booking = bookingDao.fetchBookingById(oldBookingId);
		if (booking != null) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("success");
			responseStructure.setData(bookingDao.updateBookingById(oldBookingId, newBooking));
			return responseStructure;
		} else {
			throw new BookingIdNotFound();
		}
	}

	public ResponseStructure<Booking> deleteBookingById(int bookingId) {
		Booking booking = bookingDao.fetchBookingById(bookingId);
		if (booking != null) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("success");
			responseStructure.setData(bookingDao.deleteBookingById(bookingId));
			return responseStructure;
		} else {
			throw new BookingIdNotFound();
		}
	}

	public ResponseStructureList<Booking> fetchBookingByEmail(String Email) {
		List<Booking> list = bookingDao.fetchBookingByEmail(Email);
		if (list != null) {
			responseStructureList.setStatusCode(HttpStatus.OK.value());
			responseStructureList.setMessage("success");
			responseStructureList.setDataList(bookingDao.fetchBookingByEmail(Email));
			return responseStructureList;
		} else {
			throw new RoomIdNotFound();
		}
	}

}
