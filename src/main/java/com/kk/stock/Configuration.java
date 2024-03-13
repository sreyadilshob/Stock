package com.kk.stock;

import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@org.springframework.context.annotation.Configuration
public class Configuration {
	@Bean
	RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

}
