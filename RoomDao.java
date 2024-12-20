package com.project.Hotel.Reservation.System.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.Hotel.Reservation.System.dto.Room;
import com.project.Hotel.Reservation.System.repo.RoomRepo;

@Repository
public class RoomDao {

	@Autowired
	RoomRepo roomRepo;

	public Room saveRoom(Room room) {
		return roomRepo.save(room);
	}

	public Room fetchRoomById(int roomId) {
		Optional<Room> dbroom = roomRepo.findById(roomId);
		if (dbroom.isPresent()) {
			return dbroom.get();
		}
		return null;
	}

	public List<Room> fetchAllRoom() {
		return roomRepo.findAll();
	}

	public Room updateRoomAvailabilityById(int oldRoomId, boolean data) {
		Room room = fetchRoomById(oldRoomId);
		room.setRoomIsAvailable(data);
		return saveRoom(room);
	}

	public Room updateRoomById(int oldRoomId, Room newRoom) {
		Room existingRoom = fetchRoomById(oldRoomId);
		if (existingRoom != null) {
			existingRoom.setRoomType(newRoom.getRoomType());
			existingRoom.setRoomPrice(newRoom.getRoomPrice());
			existingRoom.setRoomNumber(newRoom.getRoomNumber());
			existingRoom.setRoomIsAvailable(newRoom.isRoomIsAvailable());
			existingRoom.setRoomImage(newRoom.getRoomImage());
			existingRoom.setFloorNumber(newRoom.getFloorNumber());
			existingRoom.setRoomDescription(newRoom.getRoomDescription());
			existingRoom.setRoomCapacity(newRoom.getRoomCapacity());
			if (newRoom.getHotel() != null) {
				existingRoom.setHotel(newRoom.getHotel());
			}
			return saveRoom(existingRoom);
		}
		return null;
	}

	public Room deleteRoom(int roomId) {
		Optional<Room> optionalRoom = roomRepo.findById(roomId);
		if (optionalRoom.isPresent()) {
			Room room = optionalRoom.get();
			if (room.getHotel() != null) {
				room.getHotel().getRooms().remove(room);
			}
			if (room.getBookings() != null) {
				room.getBookings().forEach(booking -> booking.setRoom(null));
				room.getBookings().clear();
			}
			roomRepo.delete(room);
			return room;
		}
		return null;
	}

}
