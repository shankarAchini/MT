package com.project.Hotel.Reservation.System.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.Hotel.Reservation.System.dto.Hotel;

public interface HotelRepo extends JpaRepository<Hotel, Integer> {

}
