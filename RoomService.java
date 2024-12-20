package com.project.Hotel.Reservation.System.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.project.Hotel.Reservation.System.dao.RoomDao;
import com.project.Hotel.Reservation.System.dto.Hotel;
import com.project.Hotel.Reservation.System.dto.Room;
import com.project.Hotel.Reservation.System.exception.RoomIdNotFound;
import com.project.Hotel.Reservation.System.repo.HotelRepo;
import com.project.Hotel.Reservation.System.util.ResponseStructure;
import com.project.Hotel.Reservation.System.util.ResponseStructureList;

@Service
public class RoomService {

	@Autowired
	RoomDao roomDao;

	@Autowired
	HotelRepo hotelRepo;

	@Autowired
	ResponseStructure<Room> responseStructure;

	@Autowired
	ResponseStructureList<Room> responseStructureList;

	public ResponseStructure<Room> saveRoom(Room room, int hotelId) {

		Hotel hotel = hotelRepo.findById(hotelId).orElseThrow(() -> new IllegalArgumentException("Hotel not found."));
		room.setHotel(hotel);
		Room savedRoom = roomDao.saveRoom(room);
		responseStructure.setStatusCode(HttpStatus.CREATED.value());
		responseStructure.setMessage("success");
		responseStructure.setData(savedRoom);
		return responseStructure;
	}

	public ResponseStructure<Room> fetchRoomById(int roomId) {
		Room room = roomDao.fetchRoomById(roomId);
		if (room != null) {
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			responseStructure.setMessage("success");
			responseStructure.setData(roomDao.fetchRoomById(roomId));
			return responseStructure;
		} else {
			throw new RoomIdNotFound();
		}

	}

	public ResponseStructureList<Room> fetchAllRoom() {
		responseStructureList.setStatusCode(HttpStatus.FOUND.value());
		responseStructureList.setMessage("success");
		responseStructureList.setDataList(roomDao.fetchAllRoom());
		return responseStructureList;
	}

	public ResponseStructure<Room> updateRoomAvailabilityById(int oldRoomId, boolean data) {
		Room room = roomDao.fetchRoomById(oldRoomId);
		if (room != null) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("success");
			responseStructure.setData(roomDao.updateRoomAvailabilityById(oldRoomId, data));
			return responseStructure;
		} else {
			throw new RoomIdNotFound();
		}
	}

	public ResponseStructure<Room> updateRoomById(int oldRoomId, Room newRoom) {
		Room room = roomDao.fetchRoomById(oldRoomId);
		if (room != null) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("success");
			responseStructure.setData(roomDao.updateRoomById(oldRoomId, newRoom));
			return responseStructure;
		} else {
			throw new RoomIdNotFound();
		}

	}

	public ResponseStructure<Room> deleteRoom(int roomId) {
		    Room room =  roomDao.fetchRoomById(roomId);
		if (room != null) {
		responseStructure.setStatusCode(HttpStatus.OK.value());
		responseStructure.setMessage("success");
		responseStructure.setData(roomDao.deleteRoom(roomId));
		return responseStructure;
		} else {
			throw new RoomIdNotFound();
		}
	}

}
