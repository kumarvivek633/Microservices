package com.ms2.model;

import javax.persistence.*;

/**
 * The Class User.
 */
@Entity
@Table(name = "user")
public class User {
	
	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/** The username. */
	private String username;

	/** The password. */
	private String password;

	/** The security Q 1. */
	private String securityQ1;

	/** The security Q 2. */
	private String securityQ2;

	/** The answer 1. */
	private String answer1;

	/** The answer 2. */
	private String answer2;

	/** The password confirm. */
	@Transient
	private String passwordConfirm;

	/** The orig username. */
	@Transient
	private String origUsername;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the username.
	 *
	 * @param username the new username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the password confirm.
	 *
	 * @return the password confirm
	 */
	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	/**
	 * Sets the password confirm.
	 *
	 * @param passwordConfirm the new password confirm
	 */
	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	/**
	 * Gets the security Q 1.
	 *
	 * @return the security Q 1
	 */
	public String getSecurityQ1() {
		return securityQ1;
	}

	/**
	 * Sets the security Q 1.
	 *
	 * @param securityQ1 the new security Q 1
	 */
	public void setSecurityQ1(String securityQ1) {
		this.securityQ1 = securityQ1;
	}

	/**
	 * Gets the security Q 2.
	 *
	 * @return the security Q 2
	 */
	public String getSecurityQ2() {
		return securityQ2;
	}

	/**
	 * Sets the security Q 2.
	 *
	 * @param securityQ2 the new security Q 2
	 */
	public void setSecurityQ2(String securityQ2) {
		this.securityQ2 = securityQ2;
	}

	/**
	 * Gets the answer 1.
	 *
	 * @return the answer 1
	 */
	public String getAnswer1() {
		return answer1;
	}

	/**
	 * Sets the answer 1.
	 *
	 * @param answer1 the new answer 1
	 */
	public void setAnswer1(String answer1) {
		this.answer1 = answer1;
	}

	/**
	 * Gets the answer 2.
	 *
	 * @return the answer 2
	 */
	public String getAnswer2() {
		return answer2;
	}

	/**
	 * Sets the answer 2.
	 *
	 * @param answer2 the new answer 2
	 */
	public void setAnswer2(String answer2) {
		this.answer2 = answer2;
	}

	/**
	 * Gets the orig username.
	 *
	 * @return the orig username
	 */
	public String getOrigUsername() {
		return origUsername;
	}

	/**
	 * Sets the orig username.
	 *
	 * @param origUsername the new orig username
	 */
	public void setOrigUsername(String origUsername) {
		this.origUsername = origUsername;
	}

}
