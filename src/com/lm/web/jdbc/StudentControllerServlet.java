package com.lm.web.jdbc;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * This servlet serves as the controller for managing Student object-related
 * database operations. It extends the HttpServlet class and overrides the
 * init() and doGet() methods. The servlet references the utility class
 * StudentDbUtil to manage the database operations.
 *
 * @author Luriel Mapili
 * @version 1.0
 * @since 2023-05-01
 */
@WebServlet("/StudentControllerServlet")
public class StudentControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * This references the utility class for managing Student object-related
	 * database operations.
	 */
	private StudentDbUtil studentDbUtil;

	/**
	 * The DataSource object for accessing the database. The resource is identified
	 * by the name "jdbc/web_student_tracker".
	 */
	@Resource(name = "jdbc/web_student_tracker")
	private DataSource dataSource;

	/**
	 * 
	 * Overrides the {@code init()} method of the {@code GenericServlet} class to
	 * initialize the servlet.
	 * 
	 * @throws ServletException if an exception occurs while initializing the
	 *                          StudentDbUtil object with the data source
	 */
	@Override
	public void init() throws ServletException {
		super.init();

		// create the student db util and pass in the data source
		try {
			this.studentDbUtil = new StudentDbUtil(this.dataSource);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	/**
	 * 
	 * Handles HTTP GET requests sent to the servlet. Calls the listStudents()
	 * method to retrieve a list of students and forwards the list to the
	 * "list-students.jsp" JSP page.
	 * 
	 * @param request  the HttpServletRequest object containing the request
	 *                 information
	 * @param response the HttpServletResponse object containing the response
	 *                 information
	 * @throws ServletException if there is an issue with the servlet or the request
	 *                          processing
	 * @throws IOException      if there is an issue with the input/output stream
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		try {
			listStudents(request, response);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	/**
	 * 
	 * Retrieves a list of students from the database and forwards the list to the
	 * "list-students.jsp" JSP page.
	 * 
	 * @param request  the HttpServletRequest object containing the request
	 *                 information
	 * @param response the HttpServletResponse object containing the response
	 *                 information
	 * 
	 * @throws Exception if there is an issue retrieving the list of students or
	 *                   forwarding to the JSP page
	 */
	private void listStudents(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// get students from db util
		List<Student> students = this.studentDbUtil.getStudents();

		// add students to the request
		request.setAttribute("STUDENT_LIST", students);

		// send to JSP page
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-students.jsp");
		dispatcher.forward(request, response);

	}

}
