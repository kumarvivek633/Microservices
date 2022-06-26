package com.ms3.service;

import com.ms3.model.Employee;
import com.ms3.repository.EmployeeRepository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The Class EmployeeServiceImpl.
 */
@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	/** The employee repository. */
	@Autowired
	private EmployeeRepository employeeRepository;

	/**
	 * Find by created by.
	 *
	 * @param username the username
	 * @return the list
	 */
	@Override
	public List<Employee> findByCreatedBy(String username) {
		return employeeRepository.findByCreatedBy(username);
	}

	/**
	 * Save.
	 *
	 * @param employee the employee
	 */
	@Override
	public void save(Employee employee) {
		employeeRepository.save(employee);
	}

	/**
	 * Delete by emp id.
	 *
	 * @param empId the emp id
	 * @return the long
	 */
	@Override
	public Long deleteByEmpId(Long empId) {
		return employeeRepository.deleteByEmpId(empId);
	}

	/**
	 * Find by emp id.
	 *
	 * @param empId the emp id
	 * @return the employee
	 */
	@Override
	public Employee findByEmpId(Long empId) {
		return employeeRepository.findByEmpId(empId);
	}

}
