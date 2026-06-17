package com.weeklab.weekbank;

import com.weeklab.weekbank.services.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WeekbankApplication {
	private UserService userService;
	public static void main(String[] args) {
		SpringApplication.run(WeekbankApplication.class, args);

		//service.insert(new Transaction(null, "2807388c-0437-4dc7-8043-82da5a830a46", "7b9b3b80-1307-4a33-b47c-59d7681c04e1", ))
	}

}
