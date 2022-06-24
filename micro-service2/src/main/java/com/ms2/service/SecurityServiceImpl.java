package com.ms2.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms2.model.User;
import com.ms2.repository.UserRepository;

/**
 * The Class SecurityServiceImpl.
 */
@Service
public class SecurityServiceImpl implements SecurityService {

	/** The user repository. */
	@Autowired
	private UserRepository userRepository;

	/**
	 * Checks if is authenticated.
	 *
	 * @param username the username
	 * @param password the password
	 * @return true, if is authenticated
	 */
	public boolean isAuthenticated(String username, String password) {
		User user = userRepository.findByUsernameAndPassword(username, password);
		if (Objects.isNull(user)) {
			return false;
		}
		return true;
	}

}
