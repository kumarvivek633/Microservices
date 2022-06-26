package com.ms3.repository;

import com.ms3.model.Employee;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The Interface EmployeeRepository.
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	/**
	 * Find by created by.
	 *
	 * @param createdBy the created by
	 * @return the list
	 */
	List<Employee> findByCreatedBy(String createdBy);

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
