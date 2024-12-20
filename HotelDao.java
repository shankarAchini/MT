package com.project.Hotel.Reservation.System.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.Hotel.Reservation.System.dto.Hotel;
import com.project.Hotel.Reservation.System.dto.Room;
import com.project.Hotel.Reservation.System.repo.HotelRepo;

@Repository
public class HotelDao {

	@Autowired
	HotelRepo hotelRepo;

	@Autowired
	RoomDao roomDao;

	public Hotel saveHotel(Hotel hotel) {
		return hotelRepo.save(hotel);
	}

	public Hotel fetchHotelById(int hotelId) {
		Optional<Hotel> dbhotel = hotelRepo.findById(hotelId);
		if (dbhotel.isPresent()) {
			return dbhotel.get();
		}
		return null;
	}
	
	
	public List<Room> fetchRoomByHotelId(int hotelId) {
		Hotel hotel= fetchHotelById(hotelId);
		List<Room> room=hotel.getRooms();
		return room;
		
	}

	public List<Hotel> fetchAllHotel() {
		return hotelRepo.findAll();
	}

	public Hotel updateHotelById(int oldHotelId, Hotel newHotel) {
		newHotel.setHotelId(oldHotelId);
		return saveHotel(newHotel);
	}

	public Hotel deleteHotelById(int hotelId) {
		Hotel hotel = fetchHotelById(hotelId);
		hotelRepo.delete(hotel);
		return hotel;
	}

}
