package com.jobpanel.jobpanel;

import com.jobpanel.jobpanel.business.entity.Role;
import com.jobpanel.jobpanel.business.entity.User;
import com.jobpanel.jobpanel.business.service.interfaces.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class JobPanelApplication {
	public static void main(String[] args) {
		SpringApplication.run(JobPanelApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run(UserService userService){
		return args->{
			userService.saveRole(new Role(null,"USER"));
			userService.saveRole(new Role(null,"FIRM_ADMIN"));

			userService.saveUser(new User(null,"Firm Admin","FirmAdmin","1234",new ArrayList<>()));
			userService.saveUser(new User(null,"User","User","1234",new ArrayList<>()));

			userService.addRoleToUser("FirmAdmin","FIRM_ADMIN");
			userService.addRoleToUser("User","USER");
		};
	}
}
