package com.ms2.service;

/**
 * The Interface SecurityService.
 */
public interface SecurityService {

	/**
	 * Checks if is authenticated.
	 *
	 * @return true, if is authenticated
	 */
	boolean isAuthenticated(String username, String password);

}
