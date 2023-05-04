package com.lm.web.jdbc;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * The StudentDbUtil class provides utility methods for interacting with the
 * database table "student".
 * 
 * @author Luriel Mapili
 * @version 1.0
 * @since 2023-05-01
 */
public class StudentDbUtil {

	/**
	 * The DataSource object for accessing the database.
	 */
	private DataSource dataSource;

	/**
	 * Creates a new instance of the StudentDbUtil class with the provided
	 * DataSource object.
	 *
	 * @param dataSource the {@code DataSource} object for accessing the database
	 */
	public StudentDbUtil(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/**
	 * Retrieves a list of all students from the database table "student". The
	 * students are ordered by last name.
	 *
	 * @return a list of all students from the database table "student"
	 *
	 * @throws Exception if an error occurs while retrieving the students from the
	 *                   database
	 */
	public List<Student> getStudents() throws Exception {
		List<Student> students = new ArrayList<>();

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			// get a connection
			connection = this.dataSource.getConnection();

			// create sql statement
			statement = connection.createStatement();
			String sql = "SELECT * FROM student ORDER BY last_name";

			// execute query
			resultSet = statement.executeQuery(sql);

			// process result set
			while (resultSet.next()) {
				// retrieve data from result set
				int id = resultSet.getInt("id");
				String firstName = resultSet.getString("first_name");
				String lastName = resultSet.getString("last_name");
				String email = resultSet.getString("email");

				// create new student object
				Student student = new Student(id, firstName, lastName, email);

				// add it to the list of students
				students.add(student);
			}

			return students;
		} finally {
			// close JDBC objects
			close(connection, statement, resultSet);
		}

	}

	/**
	 * Adds a new student to the database.
	 *
	 * @param student the student to be added
	 *
	 * @throws Exception if an error occurs while accessing the database
	 */
	public void addStudent(Student student) throws Exception {
		Connection connection = null;
		PreparedStatement pStatement = null;

		try {
			connection = this.dataSource.getConnection();

			String sql = "INSERT INTO student (first_name, last_name, email) VALUES (?, ?, ?)";
			pStatement = connection.prepareStatement(sql);

			pStatement.setString(1, student.getFirstName());
			pStatement.setString(2, student.getLastName());
			pStatement.setString(3, student.getEmail());

			pStatement.execute();
		} finally {
			close(connection, pStatement, null);
		}
	}

	/**
	 * Retrieves a student with the specified ID from the database.
	 * 
	 * @param studentId the ID of the student to retrieve
	 * 
	 * @return the Student object representing the selected student
	 * 
	 * @throws Exception if there is an error retrieving the student from the
	 *                   database
	 */
	public Student getStudent(String studentId) throws Exception {
		Student student = null;

		Connection connection = null;
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		int studentIdInt;

		try {
			// convert student id to int
			studentIdInt = Integer.parseInt(studentId);

			// get connection to database
			connection = this.dataSource.getConnection();

			// create sql to get selected student
			String sql = "SELECT * FROM student WHERE id = ?";

			// create prepared statement
			pStatement = connection.prepareStatement(sql);

			// set params
			pStatement.setInt(1, studentIdInt);

			// execute statement
			resultSet = pStatement.executeQuery();

			// retrieve data from result row
			if (resultSet.next()) {
				String firstName = resultSet.getString("first_name");
				String lastName = resultSet.getString("last_name");
				String email = resultSet.getString("email");

				student = new Student(studentIdInt, firstName, lastName, email);
			} else {
				throw new Exception("Could not find student with an id: " + studentId);
			}
		} finally {
			close(connection, pStatement, resultSet);
		}

		return student;
	}

	/**
	 * Updates a student record in the database.
	 *
	 * @param student the Student object containing the updated information.
	 * 
	 * @throws Exception if there is an error executing the SQL statement or closing
	 *                   the database connection.
	 */
	public void updateStudent(Student student) throws Exception {
		Connection connection = null;
		PreparedStatement pStatement = null;

		try {
			// get connection to database
			connection = this.dataSource.getConnection();

			// create sql to update the student
			String sql = "UPDATE student SET first_name = ?, last_name = ?, email = ? WHERE id = ?";

			// create prepared statement
			pStatement = connection.prepareStatement(sql);

			// set params
			pStatement.setString(1, student.getFirstName());
			pStatement.setString(2, student.getLastName());
			pStatement.setString(3, student.getEmail());
			pStatement.setInt(4, student.getId());

			// execute statement
			pStatement.execute();
		} finally {
			close(connection, pStatement, null);
		}
	}

	/**
	 * Closes the provided JDBC objects.
	 * 
	 * @param connection the Connection object to close
	 * @param statement  the Statement object to close
	 * @param resultSet  the ResultSet object to close
	 */
	private void close(Connection connection, Statement statement, ResultSet resultSet) {
		try {
			if (connection != null) {
				connection.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (resultSet != null) {
				resultSet.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
