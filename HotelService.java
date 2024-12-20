package com.project.Hotel.Reservation.System.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.project.Hotel.Reservation.System.dao.HotelDao;
import com.project.Hotel.Reservation.System.dao.RoomDao;
import com.project.Hotel.Reservation.System.dto.Hotel;
import com.project.Hotel.Reservation.System.dto.Room;
import com.project.Hotel.Reservation.System.exception.HotelIdNotFound;
import com.project.Hotel.Reservation.System.exception.RoomIdNotFound;
import com.project.Hotel.Reservation.System.util.ResponseStructure;
import com.project.Hotel.Reservation.System.util.ResponseStructureList;

@Service
public class HotelService {

	@Autowired
	HotelDao hotelDao;

	@Autowired
	RoomDao roomDao;

	@Autowired
	ResponseStructure<Hotel> responseStructure;

	@Autowired
	ResponseStructureList<Hotel> responseStructureList;

	@Autowired
	ResponseStructureList<Room> responseStructureList2;

	public ResponseStructure<Hotel> saveHotel(Hotel hotel) {
		if (hotel.getRooms() != null) {
			for (Room room : hotel.getRooms()) {
				room.setHotel(hotel);
			}
		}
		responseStructure.setStatusCode(HttpStatus.CREATED.value());
		responseStructure.setMessage("successfully Owner inserted in db");
		responseStructure.setData(hotelDao.saveHotel(hotel));
		return responseStructure;
	}

	public ResponseStructure<Hotel> fetchHotelById(int hotelId) {
		Hotel hotel = hotelDao.fetchHotelById(hotelId);
		if (hotel != null) {
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			responseStructure.setMessage("success");
			responseStructure.setData(hotelDao.fetchHotelById(hotelId));
			return responseStructure;
		} else {
			throw new HotelIdNotFound();
		}

	}

	public ResponseStructureList<Room> fetchRoomByHotelId(int hotelId) {
		List<Room> room = hotelDao.fetchRoomByHotelId(hotelId);
		if (room != null) {
			responseStructureList2.setStatusCode(HttpStatus.FOUND.value());
			responseStructureList2.setMessage("success");
			responseStructureList2.setDataList(hotelDao.fetchRoomByHotelId(hotelId));
			return responseStructureList2;
		} else {
			throw new RoomIdNotFound();
		}
	}

	public ResponseStructureList<Hotel> fetchAllHotel() {
		responseStructureList.setStatusCode(HttpStatus.FOUND.value());
		responseStructureList.setMessage("success");
		responseStructureList.setDataList(hotelDao.fetchAllHotel());
		return responseStructureList;

	}

	public ResponseStructure<Hotel> updateHotelById(int oldHotelId, Hotel newHotel) {
		Hotel hotel = hotelDao.fetchHotelById(oldHotelId);
		if (hotel != null) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("success");
			responseStructure.setData(hotelDao.updateHotelById(oldHotelId, newHotel));
			return responseStructure;
		} else {
			throw new HotelIdNotFound();
		}

	}

	public ResponseStructure<Hotel> deleteHotelById(int hotelId) {
		Hotel hotel = hotelDao.fetchHotelById(hotelId);
		if (hotel != null) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("success");
			responseStructure.setData(hotelDao.deleteHotelById(hotelId));
			return responseStructure;
		} else {
			throw new HotelIdNotFound();
		}

	}

	


}
