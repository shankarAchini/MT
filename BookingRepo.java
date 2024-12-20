package com.project.Hotel.Reservation.System.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.Hotel.Reservation.System.dto.Booking;

public interface BookingRepo extends JpaRepository<Booking, Integer> {

	@Query("from Booking b where b.customerEmail=?1")
	 List<Booking> findByCustomerEmail(String customerEmail);
	
	@Query("delete from Booking b where b.customerEmail=?1")
	Booking deleteByCustomerEmail(String customerEmail);
}
