package com.ms2.repository;

import com.ms2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The Interface UserRepository.
 */
public interface UserRepository extends JpaRepository<User, Long> {
	
	/**
	 * Find by username and password.
	 *
	 * @param username the username
	 * @param password the password
	 * @return the user
	 */
	User findByUsernameAndPassword(String username, String password);

	/**
	 * Find by username.
	 *
	 * @param username the username
	 * @return the user
	 */
	User findByUsername(String username);
}
