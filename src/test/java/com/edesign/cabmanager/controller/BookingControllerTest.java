package com.edesign.cabmanager.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.core.ParameterizedTypeReference;

import com.edesign.cabmanager.dto.ResponseDto;
import com.edesign.cabmanager.dto.ResponseDto;
import com.edesign.cabmanager.entity.Booking;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DisplayName("Webservice Application Test")
public class BookingControllerTest {
	
	@Autowired
	private BookingController bookingController;
	
	@LocalServerPort
	private int randomPort;
	
	@Autowired
	private TestRestTemplate restTempClient;

	@Test
	@DisplayName("Should return a booking transported in a ResponseDto")		
	public void testGetOneBooking() {
		String URL = "http://localhost:" + randomPort + "/cabmanager-webservice/api/bookings/12";
		ParameterizedTypeReference ptr = new ParameterizedTypeReference<ResponseDto>() {}; 
		ResponseEntity<ResponseDto> responseEntity = restTempClient.exchange(URL, HttpMethod.GET, null, ptr);
		// less generic: ResponseEntity<String> response = restTempClient.getForEntity(URL, String.class);
		ResponseDto responseDto = responseEntity.getBody();
		
		assertEquals(200, responseEntity.getStatusCodeValue());
		assertEquals("Booking found", responseDto.getMessage());
		assertNotNull(responseDto.getData());
	}

}
