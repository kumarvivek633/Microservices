package com.ms2.controller;

import com.ms2.model.User;
import com.ms2.service.SecurityService;
import com.ms2.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * The Class UserController.
 */
@RestController
@CrossOrigin
public class UserController {

	/** The user service. */
	@Autowired
	private UserService userService;

	/** The security service. */
	@Autowired
	private SecurityService securityService;

	@PostMapping("/registration")
	public String registration(@RequestBody User user) {
		userService.save(user);
		return "success";
	}

	@GetMapping("/login")
	public boolean login(@RequestParam String userName, @RequestParam String password) {
		return securityService.isAuthenticated(userName, password);
	}

	@PostMapping("/reset-password")
	public String resetPassword(@RequestBody User userForm) {
		userService.resetPassword(userForm);
		return "success";
	}

	@PostMapping("/manageProfile")
	public String manageProfile(@RequestBody User userForm) {
		userService.updateUserId(userForm);
		return "success";
	}

}
