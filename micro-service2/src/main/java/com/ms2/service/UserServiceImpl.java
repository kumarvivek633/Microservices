package com.ms2.service;

import com.ms2.model.User;
import com.ms2.repository.UserRepository;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The Class UserServiceImpl.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

	/** The user repository. */
	@Autowired
	private UserRepository userRepository;

	/**
	 * Save.
	 *
	 * @param user the user
	 */
	@Override
	public void save(User user) {
		userRepository.save(user);
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
		return userRepository.findByUsernameAndPassword(username, password);
	}

	/**
	 * Find by username.
	 *
	 * @param username the username
	 * @return the user
	 */
	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	/**
	 * Reset password.
	 *
	 * @param user the user
	 */
	@Override
	public void resetPassword(User user) {
		User usr = userRepository.findByUsername(user.getUsername());
		usr.setPassword(user.getPassword());
		userRepository.save(usr);
	}

	/**
	 * Update user id.
	 *
	 * @param user the user
	 */
	@Override
	public void updateUserId(User user) {
		User usr = userRepository.findByUsername(user.getOrigUsername());
		usr.setUsername(user.getUsername());
		userRepository.save(usr);
	}
}
