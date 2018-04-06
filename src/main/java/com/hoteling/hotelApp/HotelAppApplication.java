package com.hoteling.hotelApp;

import com.hoteling.hotelApp.config.SecurityUtility;
import com.hoteling.hotelApp.domain.User;
import com.hoteling.hotelApp.service.FileUploadService;
import com.hoteling.hotelApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class HotelAppApplication implements CommandLineRunner{

	@Autowired
	private UserService userService;

	@Resource
	FileUploadService fileUploadService;

	public static void main(String[] args) {
		SpringApplication.run(HotelAppApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		User user1 = new User();
		user1.setFirstName("John");
		user1.setLastName("Adams");
		user1.setUsername("j");
		user1.setPassword(SecurityUtility.passwordEncoder().encode("p"));
		user1.setEmail("JAdams@gmail.com");

		userService.createUser(user1);

		User user2 = new User();
		user2.setFirstName("Admin");
		user2.setLastName("Admin");
		user2.setUsername("admin");
		user2.setPassword(SecurityUtility.passwordEncoder().encode("p"));
		user2.setEmail("Admin@gmail.com");

		userService.createUser(user2);

		// File Uploads
		fileUploadService.deleteAll();
		fileUploadService.init();
	}
}
