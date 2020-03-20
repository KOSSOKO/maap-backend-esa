package com.esa.bmap.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bedatadriven.jackson.datatype.jts.JtsModule;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Jackson configuration class.
 * 
 * @author QFAURE
 *
 */
@Configuration
public class JacksonConfiguration {

	/**
	 * Registers the JTS module on the global object mapper.
	 */
	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper().registerModule(new JtsModule());
	}
}
