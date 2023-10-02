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
import com.edesign.cabmanager.entity.Location;
import com.edesign.cabmanager.service.LocationService;

// CRUD operations for location

@RestController
public class LocationController {

	@Autowired 
	LocationService locationService;
	
	/**
	 * Get all locations
	 * @return
	 */
	@GetMapping("/locations")
	public Page<Location> getLocations(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, 
			@RequestParam(defaultValue = "locationId") String sort, @RequestParam(defaultValue = "ASC") String sortOrder){
		return locationService.getLocations(page,size, sort, sortOrder);
	}
	
	/**
	 * Get one location
	 * @param locationId
	 * @return
	 */
	@GetMapping("/locations/{locationId}")
	public Location getLocation(@PathVariable int locationId){
		return locationService.getLocation(locationId);
	}
	
	/**
	 * Add location
	 * @param location
	 * @return
	 */
	@PostMapping("/locations")
	public Location addLocation(@RequestBody Location location){
		return locationService.addLocation(location);
	}
	
	/**
	 * Update location
	 * @param location
	 * @return
	 */
	@PutMapping("/locations")
	public Location updateLocation(@RequestBody Location location){
		return locationService.updateLocation(location);
	}
	
	/**
	 * Delete location
	 * @param locationId
	 * @return
	 */
	@DeleteMapping("/locations/{locationId}")
	public ResponseDto updateLocation(@PathVariable int locationId){
		locationService.deleteLocation(locationId);
		return new ResponseDto("Location is deleted sucessfully with locationId : "+locationId, new Date(),HttpStatus.OK.name(),null);
	}
}
