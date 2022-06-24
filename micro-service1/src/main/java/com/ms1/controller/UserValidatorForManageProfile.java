package com.ms1.controller;

import com.ms1.model.User;
import com.ms1.service.UserService;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidatorForManageProfile implements Validator {

	@Autowired
	private UserService userService;

	@Override
	public boolean supports(Class<?> aClass) {
		return User.class.equals(aClass);
	}

	@Override
	public void validate(Object o, Errors errors) {
		User user = (User) o;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "answer1", "NotEmpty");

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "answer2", "NotEmpty");
		if (user.getUsername().length() < 6 || user.getUsername().length() > 32) {
			errors.rejectValue("username", "Size.userForm.username");
		}

		User user2 = userService.findByUsername(user.getUsername());
		if (Objects.nonNull(user2)) {
			errors.rejectValue("username", "Duplicate.userForm.username");
		} else {
			User user3 = userService.findByUsername(user.getOrigUsername());
			if (user.getAnswer1() == null) {
				user.setAnswer1("");
			}
			if (user.getAnswer2() == null) {
				user.setAnswer2("");
			}
			if (user3.getAnswer1() == null) {
				user3.setAnswer1("");
			}
			if (user3.getAnswer2() == null) {
				user3.setAnswer2("");
			}

			if (!user.getAnswer1().equals(user3.getAnswer1())) {
				errors.rejectValue("answer1", "Answer.doesnotMatch");
			}

			if (!user.getAnswer2().equals(user3.getAnswer2())) {
				errors.rejectValue("answer2", "Answer.doesnotMatch");
			}
		}


	}
}
