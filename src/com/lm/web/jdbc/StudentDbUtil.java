package com.lm.web.jdbc;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
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
