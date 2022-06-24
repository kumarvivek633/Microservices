package com.ms1.controller;

import com.ms1.model.Employee;
import com.ms1.model.User;
import com.ms1.service.EmployeeService;
import com.ms1.service.SecurityService;
import com.ms1.service.UserService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * The Class UserController.
 */
@Controller
@CrossOrigin
public class UserController {
	
	/** The user service. */
	@Autowired
	private UserService userService;

	/** The employee service. */
	@Autowired
	private EmployeeService employeeService;

	/** The security service. */
	@Autowired
	private SecurityService securityService;

	/** The user validator. */
	@Autowired
	private UserValidator userValidator;

	/** The user validator for reset password. */
	@Autowired
	private UserValidatorForResetPassword userValidatorForResetPassword;

	/** The user validator for manage profile. */
	@Autowired
	private UserValidatorForManageProfile userValidatorForManageProfile;

	/** The employee validator. */
	@Autowired
	private EmployeeValidator employeeValidator;

	/**
	 * Registration.
	 *
	 * @param model the model
	 * @return the string
	 */
	@GetMapping("/registration")
	public String registration(Model model) {
		model.addAttribute("userForm", new User());
		return "registration";
	}

	/**
	 * Registration.
	 *
	 * @param userForm the user form
	 * @param bindingResult the binding result
	 * @return the string
	 */
	@PostMapping("/registration")
	public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
		userValidator.validate(userForm, bindingResult);

		if (bindingResult.hasErrors()) {
			return "registration";
		}
		userService.save(userForm);

		boolean isAuth = securityService.isAuthenticated(userForm.getUsername(), userForm.getPasswordConfirm());
		
		if (isAuth) {
			model.addAttribute("userName", userForm.getUsername());
			return "redirect:/welcome" + "?userName=" + userForm.getUsername();
		} else {
			model.addAttribute("error", "Your username and password is invalid.");
		}
		

		return "login";
	}

	/**
	 * Login.
	 *
	 * @param model the model
	 * @param error the error
	 * @return the string
	 */
	@GetMapping({ "/", "/login" })
	public String login(Model model, String error) {
		model.addAttribute("userForm", new User());
		return "login";
	}

	/**
	 * Login.
	 *
	 * @param userForm the user form
	 * @param model the model
	 * @param error the error
	 * @return the string
	 */
	@PostMapping("/login")
	public String login(@ModelAttribute("userForm") User userForm, Model model, String error) {
		boolean isAuth = securityService.isAuthenticated(userForm.getUsername(), userForm.getPassword());
		if (isAuth) {
			model.addAttribute("userName", userForm.getUsername());
			return "redirect:/welcome" + "?userName=" + userForm.getUsername();
		} else {
			model.addAttribute("error", "Your username and password is invalid.");
		}

		return "login";
	}

	/**
	 * Logout.
	 *
	 * @param model the model
	 * @param error the error
	 * @param logout the logout
	 * @return the string
	 */
	@PostMapping("/logout")
	public String logout(Model model, String error, String logout) {
		model.addAttribute("userForm", new User());
		model.addAttribute("message", "You have been logged out successfully.");
		return "login";
	}

	/**
	 * Welcome.
	 *
	 * @param model the model
	 * @param userName the user name
	 * @return the string
	 */
	@GetMapping({ "/welcome" })
	public String welcome(Model model, @RequestParam String userName) {
		if (StringUtils.isEmpty(userName)) {
			return "redirect:/login";
		}
		model.addAttribute("userName", userName);
		return "welcome";
	}

	/**
	 * Reset pwd.
	 *
	 * @param model the model
	 * @return the string
	 */
	@GetMapping({ "/reset-password" })
	public String resetPwd(Model model) {
		model.addAttribute("userForm", new User());
		return "forgotpassword";
	}

	/**
	 * Reset password.
	 *
	 * @param userForm the user form
	 * @param bindingResult the binding result
	 * @param model the model
	 * @return the string
	 */
	@PostMapping("/reset-password")
	public String resetPassword(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
		userValidatorForResetPassword.validate(userForm, bindingResult);
		String returnPage;
		if (bindingResult.hasErrors()) {
			returnPage = "forgotpassword";
		} else {
			userService.resetPassword(userForm);
			returnPage = "redirect:/welcome" + "?userName=" + userForm.getUsername();
			securityService.isAuthenticated(userForm.getUsername(), userForm.getPasswordConfirm());
		}

		return returnPage;
	}

	/**
	 * Manage profile.
	 *
	 * @param model the model
	 * @param userName the user name
	 * @return the string
	 */
	@GetMapping({ "/manageProfile" })
	public String manageProfile(Model model, @RequestParam(required = false) String userName) {
		if (StringUtils.isEmpty(userName)) {
			return "redirect:/login";
		}
		User user = new User();
		user.setOrigUsername(userName);
		model.addAttribute("userForm", user);
		model.addAttribute("userNames", userName);
		return "manageprofile";
	}

	/**
	 * Manage profile.
	 *
	 * @param userForm the user form
	 * @param bindingResult the binding result
	 * @param model the model
	 * @return the string
	 */
	@PostMapping("/manageProfile")
	public String manageProfile(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
		userValidatorForManageProfile.validate(userForm, bindingResult);
		if (bindingResult.hasErrors()) {
			return "manageProfile";
		} else {
			userService.updateUserId(userForm);
			model.addAttribute("userNames", userForm.getUsername());
		}

		return "manageProfile";
	}

	/**
	 * View employee.
	 *
	 * @param model the model
	 * @param userName the user name
	 * @return the string
	 */
	@GetMapping({ "/viewEmployees" })
	public String viewEmployee(Model model, @RequestParam(required = false) String userName) {
		if (StringUtils.isEmpty(userName)) {
			return "redirect:/login";
		}
		List<Employee> employees = employeeService.findByCreatedBy(userName);
		model.addAttribute("employees", employees);
		model.addAttribute("userNames", userName);
		return "viewemployees";
	}

	/**
	 * Delete employee.
	 *
	 * @param empId the emp id
	 * @param userName the user name
	 * @return the string
	 */
	@GetMapping(value = "/delete_employee/{empId}")
	public String deleteEmployee(@PathVariable String empId, @RequestParam(required = false) String userName) {
		if (StringUtils.isEmpty(userName)) {
			return "redirect:/login";
		}
		employeeService.deleteByEmpId(Long.valueOf(empId));
		return "redirect:/viewEmployees?userName=" + userName;
	}

	/**
	 * Adds the emp.
	 *
	 * @param model the model
	 * @param userName the user name
	 * @return the string
	 */
	@GetMapping({ "/addEmp" })
	public String addEmp(Model model, @RequestParam(required = false) String userName) {
		if (StringUtils.isEmpty(userName)) {
			return "redirect:/login";
		}
		Employee employee = new Employee();
		employee.setCreatedBy(userName);
		model.addAttribute("empForm", employee);
		model.addAttribute("userNames", userName);
		return "addEmp";
	}

	/**
	 * Adds the emp.
	 *
	 * @param empForm the emp form
	 * @param bindingResult the binding result
	 * @param model the model
	 * @return the string
	 */
	@PostMapping({ "/addEmp" })
	public String addEmp(@ModelAttribute("empForm") Employee empForm, BindingResult bindingResult, Model model) {
		employeeValidator.validate(empForm, bindingResult);
		if (bindingResult.hasErrors()) {
			return "addEmp";
		} else {
			employeeService.save(empForm);
			model.addAttribute("userNames", empForm.getCreatedBy());
		}

		return "redirect:/viewEmployees?userName=" + empForm.getCreatedBy();
	}

	/**
	 * Update emp.
	 *
	 * @param model the model
	 * @param userName the user name
	 * @param empId the emp id
	 * @return the string
	 */
	@GetMapping({ "/edit_employee/{empId}" })
	public String updateEmp(Model model, @RequestParam(required = false) String userName, @PathVariable String empId) {
		if (StringUtils.isEmpty(userName)) {
			return "redirect:/login";
		}
		Employee employee = employeeService.findByEmpId(Long.valueOf(empId));
		model.addAttribute("empForm", employee);
		model.addAttribute("userNames", userName);
		return "editEmp";
	}

	/**
	 * Update emp.
	 *
	 * @param empForm the emp form
	 * @param bindingResult the binding result
	 * @param model the model
	 * @return the string
	 */
	@PostMapping({ "/edit_employee" })
	public String updateEmp(@ModelAttribute("empForm") Employee empForm, BindingResult bindingResult, Model model) {
		employeeService.save(empForm);
		model.addAttribute("userNames", empForm.getCreatedBy());

		return "redirect:/viewEmployees?userName=" + empForm.getCreatedBy();
	}

}
