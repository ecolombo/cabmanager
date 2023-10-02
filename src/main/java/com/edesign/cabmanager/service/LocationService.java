package com.edesign.cabmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.edesign.cabmanager.repository.LocationRepository;
import com.edesign.cabmanager.entity.Location;
import com.edesign.cabmanager.exception.BadRequestException;
import com.edesign.cabmanager.exception.NotFoundException;

@Service
public class LocationService {
	
	@Autowired
	LocationRepository locationRepository;
	
	// Get all Locations
	public Page<Location> getLocations(int page, int size, String sort, String sortOrder) {
		Sort.Direction direction = sortOrder.equalsIgnoreCase("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC;
		Sort sortBy = Sort.by(direction,sort);
		Pageable pageable = PageRequest.of(page, size, sortBy);
		return locationRepository.findAll(pageable);
	}	
	
	
	// Get one Location by LocationId
	public Location getLocation(int LocationId) {
		return locationRepository.findById(LocationId).get();
	}
	
	// Add Location
	public Location addLocation(Location location) {
		System.out.println("location"+location.getXcoord());
		
		if(locationRepository.existsByName(location.getName()))
			throw new BadRequestException("Location with this name already exists.");
		return locationRepository.save(location);
	}
	
	// Update Location
	public Location updateLocation(Location location) {
		if(location.getLocationId() <=0 )
			throw new BadRequestException("LocationId cannot be null or empty.");
		if(locationRepository.existsById(location.getLocationId()))
			return locationRepository.save(location);
		else 
			throw new NotFoundException("Location does not exist with provided locationId.");

	}
	
	// Delete Location
	public void deleteLocation(int locationId) {
		if(locationId <=0 )
			throw new BadRequestException("locationId id cannot be null or empty.");
		if(locationRepository.existsById(locationId))
			locationRepository.deleteById(locationId);
		else 
			throw new NotFoundException("The location does not exist with provided locationId.");
	}
	
	
}
