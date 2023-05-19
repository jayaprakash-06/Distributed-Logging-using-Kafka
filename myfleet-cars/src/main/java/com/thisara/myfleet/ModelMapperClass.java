package com.thisara.myfleet;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ModelMapperClass {

	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}

}
