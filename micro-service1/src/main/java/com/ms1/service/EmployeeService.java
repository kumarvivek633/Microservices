package com.ms1.service;

import java.util.List;

import com.ms1.model.Employee;

/**
 * The Interface EmployeeService.
 */
public interface EmployeeService {

	/**
	 * Find by created by.
	 *
	 * @param username the username
	 * @return the list
	 */
	public List<Employee> findByCreatedBy(String username);

	/**
	 * Save.
	 *
	 * @param employee the employee
	 */
	void save(Employee employee);

	/**
	 * Delete by emp id.
	 *
	 * @param empId the emp id
	 * @return the long
	 */
	Long deleteByEmpId(Long empId);

	/**
	 * Find by emp id.
	 *
	 * @param empId the emp id
	 * @return the employee
	 */
	Employee findByEmpId(Long empId);
}
