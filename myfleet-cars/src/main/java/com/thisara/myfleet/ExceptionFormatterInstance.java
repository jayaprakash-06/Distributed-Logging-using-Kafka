package com.thisara.myfleet;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.thisara.utils.ExceptionFormatter;


@Configuration
public class ExceptionFormatterInstance {

	@Bean
	public ExceptionFormatter getExceptionFormatter() {
		return new ExceptionFormatter();
	}
}
