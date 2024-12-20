package com.project.Hotel.Reservation.System.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.Hotel.Reservation.System.dto.Room;

public interface RoomRepo extends JpaRepository<Room, Integer> {

}
