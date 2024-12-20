package com.project.Hotel.Reservation.System.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.Hotel.Reservation.System.dto.Room;
import com.project.Hotel.Reservation.System.service.RoomService;
import com.project.Hotel.Reservation.System.util.ResponseStructure;
import com.project.Hotel.Reservation.System.util.ResponseStructureList;

@RestController
public class RoomController {

	@Autowired
	RoomService roomService;

	@PostMapping("/saveRoom")
	public ResponseStructure<Room> saveRoom(@RequestBody Room room,@RequestParam int hotelId) {
		return roomService.saveRoom(room, hotelId);
	}

	@GetMapping("/fetchRoomById")
	public ResponseStructure<Room> fetchRoomById(@RequestParam("roomId") int roomId) {
		return roomService.fetchRoomById(roomId);
	}

	@PutMapping("/updateRoomAvailabilityById")
	public ResponseStructure<Room> updateRoomAvailabilityById(@RequestParam int oldRoomId, @RequestBody boolean data) {
		return roomService.updateRoomAvailabilityById(oldRoomId, data);
	}

	@GetMapping("/fetchAllRoom")
	public ResponseStructureList<Room> fetchAllRoom() {
		return roomService.fetchAllRoom();
	}

	@PutMapping("/updateRoomById")
	public ResponseStructure<Room> updateRoomById(@RequestParam int oldRoomId, @RequestBody Room newRoom) {
		return roomService.updateRoomById(oldRoomId, newRoom);
	}

	@DeleteMapping("/deleteRoom")
	public ResponseStructure<Room> deleteRoom(int roomId) {
		return roomService.deleteRoom(roomId);
	}
}
