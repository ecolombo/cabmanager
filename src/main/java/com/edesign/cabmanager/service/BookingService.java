package com.edesign.cabmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.edesign.cabmanager.repository.BookingRepository;
import com.edesign.cabmanager.entity.Booking;
import com.edesign.cabmanager.exception.BadRequestException;
import com.edesign.cabmanager.exception.NotFoundException;

@Service
public class BookingService {
	
	@Autowired
	BookingRepository bookingRepository;
	
	// Get all Bookings
	public Page<Booking> getBookings(int page, int size, String sort, String sortOrder) {
		Sort.Direction direction = sortOrder.equalsIgnoreCase("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC;
		Sort sortBy = Sort.by(direction,sort);
		Pageable pageable = PageRequest.of(page, size, sortBy);
		Page<Booking> resultPage = bookingRepository.findAll(pageable);
		// Update Fare computation in case anything was changed...
		resultPage.forEach(booking -> updateDistanceAndFare(booking));
		return resultPage;
	}	
	
	
	// Get one Booking by BookingId
	public Booking getBooking(int BookingId) {
		Booking booking = bookingRepository.findById(BookingId).get();
		updateDistanceAndFare(booking);
		return booking;
	}
	
	// Add Booking
	// todo: Add some kind of validation
	public Booking addBooking(Booking booking) {
		
		/** if(bookingRepository.existsByName(booking.getName()))
			throw new BadRequestException("Booking with this name already exists."); **/
		return bookingRepository.save(booking);
	}
	
	// Update Booking
	public Booking updateBooking(Booking booking) {
		if(booking.getBookingId() <=0 )
			throw new BadRequestException("BookingId cannot be null or empty.");
		if(bookingRepository.existsById(booking.getBookingId()))
			return bookingRepository.save(booking);
		else 
			throw new NotFoundException("Booking does not exist with provided bookingId.");

	}
	
	// Delete Booking
	public void deleteBooking(int bookingId) {
		if(bookingId <=0 )
			throw new BadRequestException("bookingId id cannot be null or empty.");
		if(bookingRepository.existsById(bookingId))
			bookingRepository.deleteById(bookingId);
		else 
			throw new NotFoundException("The booking does not exist with provided bookingId.");
	}
	
	private Booking updateDistanceAndFare(Booking booking) {		
		
		double x1 = booking.getFrom().getXcoord();
		double y1 = booking.getFrom().getYcoord();
		double x2 = booking.getTo().getXcoord();
		double y2 = booking.getTo().getYcoord();
		double distance = Math.sqrt(Math.pow((x2-x1),2)+Math.pow((y2-y1),2)) / 1000;
		double fare = distance * booking.getCategory().getPricePerKM();
		booking.setDistance(distance);
		booking.setFare(fare);
		return booking;
		
	}
	
	
}
