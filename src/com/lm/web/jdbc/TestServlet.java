package com.lm.web.jdbc;

import java.sql.Statement;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Define data source / connection pool for Resource injection
	@Resource(name = "jdbc/web_student_tracker")
	private DataSource dataSource;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Step 1: Set up the print writer
		PrintWriter out = response.getWriter();
		response.setContentType("text/plain");

		// Step 2: Get a connection to the database
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			connection = dataSource.getConnection();

			// Step 3: Create SQL statements
			String sql = "SELECT * FROM student";
			statement = connection.createStatement();

			// Step 4: Execute SQL query
			resultSet = statement.executeQuery(sql);

			// Step 5: Process the result set
			out.println("It works bruh.");
			while (resultSet.next()) {
				String email = resultSet.getString("email");
				out.println(email);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
