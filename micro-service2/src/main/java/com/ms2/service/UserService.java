package com.ms2.service;

import com.ms2.model.User;

/**
 * The Interface UserService.
 */
public interface UserService {

	/**
	 * Save.
	 *
	 * @param user the user
	 */
	void save(User user);

	/**
	 * Reset password.
	 *
	 * @param user the user
	 */
	void resetPassword(User user);

	/**
	 * Find by username andpassword.
	 *
	 * @param username the username
	 * @param password the password
	 * @return the user
	 */
	User findByUsernameAndpassword(String username, String password);

	/**
	 * Find by username.
	 *
	 * @param username the username
	 * @return the user
	 */
	User findByUsername(String username);

	/**
	 * Update user id.
	 *
	 * @param user the user
	 */
	void updateUserId(User user);
}
