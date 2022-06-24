package com.ms1.service;

import com.ms1.model.User;

public interface UserService {
    
	void save(User user);
	
	void resetPassword(User user);

	User findByUsernameAndpassword(String username, String password);

	User findByUsername(String username);
	
	void updateUserId(User user);
}
