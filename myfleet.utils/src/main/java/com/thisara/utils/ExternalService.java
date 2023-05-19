package com.thisara.utils;

import org.springframework.web.client.RestTemplate;


public class ExternalService {

	public Object getResponseObject(String url, Class<?> response) {
		
		RestTemplate restTemplate = new RestTemplate();
		
		Object responseObject = restTemplate.getForObject(url, response);

		return responseObject;
	}
}
