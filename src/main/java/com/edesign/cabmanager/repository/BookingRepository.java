package com.edesign.cabmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edesign.cabmanager.entity.Booking;
import com.edesign.cabmanager.entity.Category;

public interface BookingRepository extends JpaRepository<Booking, Integer>{

	// boolean existsByName(String name);

	// Category findByName(String name);

}
