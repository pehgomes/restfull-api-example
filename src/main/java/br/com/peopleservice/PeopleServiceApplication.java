package br.com.peopleservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PeopleServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PeopleServiceApplication.class, args);
	}

//	@Bean
//	MappingJackson2HttpMessageConverter mappingJacksonHttpMessageConverter(ObjectMapper mapper) {
//		return new MappingJackson2HttpMessageConverter(mapper);
//	}

}
