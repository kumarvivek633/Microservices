package com.ms3.model;

import javax.persistence.*;

import java.io.Serializable;

/**
 * The Class Employee.
 */
@Entity
@Table(name = "employee")
public class Employee implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The emp id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long empId;

	/** The first name. */
	private String firstName;

	/** The last name. */
	private String lastName;

	/** The email id. */
	private String emailId;

	/** The created by. */
	private String createdBy;

	/** The exp. */
	private int exp;

	/**
	 * Gets the emp id.
	 *
	 * @return the emp id
	 */
	public Long getEmpId() {
		return empId;
	}

	/**
	 * Sets the emp id.
	 *
	 * @param empId the new emp id
	 */
	public void setEmpId(Long empId) {
		this.empId = empId;
	}

	/**
	 * Gets the exp.
	 *
	 * @return the exp
	 */
	public int getExp() {
		return exp;
	}

	/**
	 * Sets the exp.
	 *
	 * @param exp the new exp
	 */
	public void setExp(int exp) {
		this.exp = exp;
	}

	/**
	 * Gets the first name.
	 *
	 * @return the first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the first name.
	 *
	 * @param firstName the new first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Gets the last name.
	 *
	 * @return the last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the last name.
	 *
	 * @param lastName the new last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Gets the email id.
	 *
	 * @return the email id
	 */
	public String getEmailId() {
		return emailId;
	}

	/**
	 * Sets the email id.
	 *
	 * @param emailId the new email id
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	/**
	 * Gets the created by.
	 *
	 * @return the created by
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * Sets the created by.
	 *
	 * @param createdBy the new created by
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

}
