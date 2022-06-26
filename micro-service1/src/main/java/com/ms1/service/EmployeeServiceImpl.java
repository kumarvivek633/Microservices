package com.ms1.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.ms1.model.Employee;
import com.ms1.repository.EmployeeRepository;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
/**
 * The Class EmployeeServiceImpl.
 */
@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	/** The employee repository. */
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private RestTemplate restTemplate;

	/**
	 * Find by created by.
	 *
	 * @param username the username
	 * @return the list
	 */
	@Override
	@HystrixCommand(fallbackMethod = "fallBackMethhodFindByCreatedBy")
	public List<Employee> findByCreatedBy(String username) {
		  String url = "http://micro-service3/viewEmployees?userName=" + username;
	        return restTemplate.exchange(
	        		url,
	        		  HttpMethod.GET,
	        		  null,
	        		  new ParameterizedTypeReference<List<Employee>>(){}).getBody();
	}

	/**
	 * Save.
	 *
	 * @param employee the employee
	 */
	@Override
	@HystrixCommand(fallbackMethod = "fallBackMethhodsave")
	public void save(Employee employee) {
		String url = "http://micro-service3/addEmp";
		restTemplate.postForEntity(url, employee, String.class);
	}

	@Override
	@HystrixCommand(fallbackMethod = "fallBackMethhodDelete")
	public void deleteByEmpId(Long empId) {
		  String url = "http://micro-service3/delete_employee/" + empId;
	        restTemplate.getForObject(url, String.class);
	}

	/**
	 * Find by emp id.
	 *
	 * @param empId the emp id
	 * @return the employee
	 */
	@Override
	public Employee findByEmpId(Long empId) {
		String url = "http://micro-service3/getEmployee?empId=" + empId;
        return restTemplate.getForObject(url, Employee.class);
	}
	
	public List<Employee> fallBackMethhodFindByCreatedBy(String username) {
		return new ArrayList<>();
	}
	
	public void fallBackMethhodsave(Employee employee) {
		System.err.print("Error");
	}
	
	public void fallBackMethhodDelete(Long empId) {
		System.err.print("Error");
	}

}
