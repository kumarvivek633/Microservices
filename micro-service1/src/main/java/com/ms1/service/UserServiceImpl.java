package com.ms1.service;

import com.ms1.model.User;
import com.ms1.repository.UserRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * The Class UserServiceImpl.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	/** The user repository. */
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RestTemplate restTemplate;

	/**
	 * Save.
	 *
	 * @param user the user
	 */
	@Override
	@HystrixCommand(fallbackMethod = "fallBackMethhod")
	public void save(User user) {
		String url = "http://micro-service2/registration";
		restTemplate.postForEntity(url, user, String.class);
	}

	/**
	 * Find by username andpassword.
	 *
	 * @param username the username
	 * @param password the password
	 * @return the user
	 */
	@Override
	public User findByUsernameAndpassword(String username, String password) {
		String url = "http://micro-service2/findByUsernameAndpassword?userName=" + username + "&password=" + password;
        return restTemplate.getForObject(url, User.class);
	}

	/**
	 * Find by username.
	 *
	 * @param username the username
	 * @return the user
	 */
	@Override
	public User findByUsername(String username) {
		String url = "http://micro-service2/findByUsername?userName=" + username;
        return restTemplate.getForObject(url, User.class);
	}

	/**
	 * Reset password.
	 *
	 * @param user the user
	 */
	@Override
	@HystrixCommand(fallbackMethod = "fallBackMethhod")
	public void resetPassword(User user) {
		String url = "http://micro-service2/reset-password";
		restTemplate.postForEntity(url, user, String.class);
	}

	/**
	 * Update user id.
	 *
	 * @param user the user
	 */
	@Override
	@HystrixCommand(fallbackMethod = "fallBackMethhod")
	public void updateUserId(User user) {
		String url = "http://micro-service2/manageProfile";
		restTemplate.postForEntity(url, user, String.class);
		
	}
	
	public void fallBackMethhod(User user) {
		System.err.println("Error while processing");
	}
}
