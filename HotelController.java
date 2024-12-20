package com.project.Hotel.Reservation.System.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.Hotel.Reservation.System.dto.Hotel;
import com.project.Hotel.Reservation.System.dto.Room;
import com.project.Hotel.Reservation.System.service.HotelService;
import com.project.Hotel.Reservation.System.util.ResponseStructure;
import com.project.Hotel.Reservation.System.util.ResponseStructureList;


@RestController
public class HotelController {

	@Autowired
	HotelService hotelService;

	@PostMapping("/saveHotel")
	public ResponseStructure<Hotel> saveHotel(@RequestBody Hotel hotel) {
		return hotelService.saveHotel(hotel);
	}

	@GetMapping("/fetchHotelById")
	public ResponseStructure<Hotel> fetchHotelById(@RequestParam int hotelId) {
		return hotelService.fetchHotelById(hotelId);
	}

	@GetMapping("/fetchRoomByHotelId")
	public ResponseStructureList<Room> fetchRoomByHotelId(@RequestParam("hotelId") int hotelId) {
		return hotelService.fetchRoomByHotelId(hotelId);
	}

	@GetMapping("/fetchAllHotel")
	public ResponseStructureList<Hotel> fetchAllHotel() {
		return hotelService.fetchAllHotel();
	}

	@PutMapping("/updateHotelById")
	public ResponseStructure<Hotel> updateHotelById(@RequestParam int oldHotelId, @RequestBody Hotel newHotel) {
		return hotelService.updateHotelById(oldHotelId, newHotel);
	}

	@DeleteMapping("/deleteHotelById")
	public ResponseStructure<Hotel> deleteHotelById(@RequestParam int hotelId) {
		return hotelService.deleteHotelById(hotelId);
	}

	

}
