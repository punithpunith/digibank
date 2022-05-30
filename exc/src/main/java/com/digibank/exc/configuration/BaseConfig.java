package com.digibank.exc.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BaseConfig {
	@Bean
	public Logger getLogger() {
		return LoggerFactory.getLogger(BaseConfig.class);
	}
}
