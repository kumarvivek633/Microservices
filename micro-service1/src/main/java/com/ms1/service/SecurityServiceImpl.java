package com.ms1.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;


@Service
public class SecurityServiceImpl implements SecurityService {

	@Autowired
	private RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod = "fallBackMethhod")
	public boolean isAuthenticated(String username, String password) {
		
        String url = "http://micro-service2/login?userName=" + username + "&password=" + password;
        return restTemplate.getForObject(url, Boolean.class);
	}
	
	public boolean fallBackMethhod(String username, String password) {
		return false;
	}

}
