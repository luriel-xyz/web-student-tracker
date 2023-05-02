package com.lm.web.jdbc;

/**
 * /** This Student class represents a student enrolled in a school. A student
 * has a unique ID, first name, last name, and email address.
 * 
 * @author Luriel Mapili
 * @version 1.0
 * @since April 29, 2023
 *
 */
public class Student {

	/**
	 * The unique identifier for the student.
	 */
	private int id;

	/**
	 * The first name of the student.
	 */
	private String firstName;

	/**
	 * The last name of the student.
	 */
	private String lastName;

	/**
	 * The email address of the student.
	 */
	private String email;

	/**
	 * Creates a new Student object with the specified ID, first name, last name,
	 * and email.
	 * 
	 * @param id        the unique identifier for the student
	 * @param firstName the first name of the student
	 * @param lastName  the last name of the student
	 * @param email     the email address of the student
	 */
	public Student(int id, String firstName, String lastName, String email) {
		this(firstName, lastName, email);
		this.id = id;
	}

	/**
	 * Creates a new Student object with the specified first name, last name, and
	 * email.
	 * 
	 * @param id        the unique identifier for the student
	 * @param firstName the first name of the student
	 * @param lastName  the last name of the student
	 * @param email     the email address of the student
	 */
	public Student(String firstName, String lastName, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	/**
	 * Returns the unique identifier for this student.
	 *
	 * @return the ID of the student
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the unique identifier for this student.
	 *
	 * @param id the new ID to set for the student
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Returns the first name of this student.
	 *
	 * @return the first name of the student
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the first name for this student.
	 *
	 * @param firstName the new first name to set for the student
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Returns the last name of this student.
	 *
	 * @return the last name of the student
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the last name for this student.
	 *
	 * @param lastName the new last name to set for the student
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Returns the email address of this student.
	 *
	 * @return the email address of the student
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email address for this student.
	 *
	 * @param email the new email address to set for the student
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Returns a string representation of this student.
	 *
	 * @return a string representation of the student
	 */
	@Override
	public String toString() {
		return "Student [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + "]";
	}

}
