package com.edesign.cabmanager;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;

import com.edesign.cabmanager.controller.UserController;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DisplayName("Webservice Application Test")
class CabmanagerWebserviceApplicationTests {
	 
	@Autowired
	private UserController userController;
	
	@LocalServerPort
	private int randomPort;

	@Test
	@DisplayName("Application context should not be null")
	void contextLoads() {
		assertNotNull(userController);
	}
	
}
