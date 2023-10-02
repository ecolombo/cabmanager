package com.edesign.cabmanager.controller;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edesign.cabmanager.dto.ResponseDto;
import com.edesign.cabmanager.entity.Booking;
import com.edesign.cabmanager.service.BookingService;

// CRUD operations for booking

@RestController
public class BookingController {

	@Autowired 
	BookingService bookingService;
	
	/**
	 * Get all bookings
	 * @return
	 */
	@GetMapping("/bookings")
	public Page<Booking> getBookings(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, 
			@RequestParam(defaultValue = "bookingId") String sort, @RequestParam(defaultValue = "ASC") String sortOrder){
		return bookingService.getBookings(page,size, sort, sortOrder);
	}
	
	/**
	 * Get one booking
	 * @param bookingId
	 * @return
	 */
	@GetMapping("/bookings/{bookingId}")
	public Booking getBooking(@PathVariable int bookingId){
		return bookingService.getBooking(bookingId);
	}
	
	/**
	 * Add booking
	 * @param booking
	 * @return
	 */
	@PostMapping("/bookings")
	public Booking addBooking(@RequestBody Booking booking){
		System.out.println("BOOKING: " + booking);
		return bookingService.addBooking(booking);
	}
	
	/**
	 * Update booking
	 * @param booking
	 * @return
	 */
	@PutMapping("/bookings")
	public Booking updateBooking(@RequestBody Booking booking){
		return bookingService.updateBooking(booking);
	}
	
	/**
	 * Delete booking
	 * @param bookingId
	 * @return
	 */
	@DeleteMapping("/bookings/{bookingId}")
	public ResponseDto updateBooking(@PathVariable int bookingId){
		bookingService.deleteBooking(bookingId);
		return new ResponseDto("Booking is deleted sucessfully with bookingId : "+bookingId, new Date(),HttpStatus.OK.name(),null);
	}
}
