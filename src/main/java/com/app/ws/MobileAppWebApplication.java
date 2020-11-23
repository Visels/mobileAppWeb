package com.app.ws;

import com.app.ws.ui.Security.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class MobileAppWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(MobileAppWebApplication.class, args);
	}

	//password encoder for encoding and decoding passwords
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder ()
	{
		return new BCryptPasswordEncoder();
	}

    //class/bean used to return new beans-> a string of the bean required is passed as a parameter
	@Bean
	public SpringApplicationContext springApplicationContext(){
		return new SpringApplicationContext();
	}

	//class/ bean used for accessing data in the properties file
	@Bean
	public AppProperties AppProperties(){
		return new AppProperties();
	}
}
