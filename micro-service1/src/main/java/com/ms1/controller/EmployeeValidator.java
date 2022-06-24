package com.ms1.controller;

import com.ms1.model.Employee;
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
public class EmployeeValidator implements Validator {

	@Override
	public boolean supports(Class<?> aClass) {
		return Employee.class.equals(aClass);
	}

	@Override
	public void validate(Object o, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "NotEmpty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "NotEmpty");

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emailId", "NotEmpty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "exp", "NotEmpty");
	}
}
