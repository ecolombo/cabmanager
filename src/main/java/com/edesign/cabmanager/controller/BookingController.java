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
	public ResponseDto getBookings(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, 
			@RequestParam(defaultValue = "bookingId") String sort, @RequestParam(defaultValue = "ASC") String sortOrder){		
		Page<Booking> paginatedResults = bookingService.getBookings(page, size, sort, sortOrder);
		// todo: add response when no data found
		ResponseDto response = new ResponseDto("Bookings found", new Date(), HttpStatus.OK.name(), paginatedResults); 	
		return response;
	}
	
	
	/**
	 * Get one booking
	 * @param bookingId
	 * @return
	 */
	@GetMapping("/bookings/{bookingId}")
	public ResponseDto getBooking(@PathVariable int bookingId){
		// todo: add response when no data found
		ResponseDto response = new ResponseDto("Booking found", new Date(), HttpStatus.OK.name(), bookingService.getBooking(bookingId));
		return response; 
	}
	
	/**
	 * Add booking
	 * @param booking
	 * @return
	 */
	@PostMapping("/bookings")
	public ResponseDto addBooking(@RequestBody Booking booking){
		// todo: add response when conflict occurs
		Booking resultBooking = bookingService.addBooking(booking);
		ResponseDto response = new ResponseDto("Booking added", new Date(), HttpStatus.OK.name(), resultBooking);
		return response;
	}
	
	/**
	 * Update booking
	 * @param booking
	 * @return
	 */
	@PutMapping("/bookings")
	public ResponseDto updateBooking(@RequestBody Booking booking){
		// todo: add response if update not possible
		Booking resultBooking = bookingService.updateBooking(booking);
		ResponseDto response = new ResponseDto("Booking updated", new Date(), HttpStatus.OK.name(), resultBooking);
		return response;
	}
	
	/**
	 * Delete booking
	 * @param bookingId
	 * @return
	 */
	@DeleteMapping("/bookings/{bookingId}")
	public ResponseDto updateBooking(@PathVariable int bookingId){
		bookingService.deleteBooking(bookingId);
		ResponseDto response = new ResponseDto("Booking is deleted sucessfully with bookingId : "+bookingId, new Date(),HttpStatus.OK.name(),null); 
		return response;
	}
}
