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
	 * This method handles HTTP GET requests and routes the request to the
	 * appropriate method based on the "command" parameter.
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
			// read the "command" parameter
			String command = request.getParameter("command");

			if (command == null) {
				command = "LIST";
			}

			// route to the appropriate method
			switch (command) {
			case "LIST":
				listStudents(request, response);
				break;
			case "ADD":
				addStudent(request, response);
				break;
			case "LOAD":
				loadStudent(request, response);
				break;
			case "UPDATE":
				updateStudent(request, response);
				break;
			case "DELETE":
				deleteStudent(request, response);
				break;
			default:
				listStudents(request, response);
				break;
			}
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
		List<Student> students = this.studentDbUtil.getAll();

		// add students to the request
		request.setAttribute("STUDENT_LIST", students);

		// send to JSP page
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-students.jsp");
		dispatcher.forward(request, response);

	}

	/**
	 * This method adds a new student to the database based on the information
	 * provided in the HTTP request.
	 * 
	 * @param request  the HTTP request object
	 * @param response the HTTP response object
	 *
	 * @throws Exception if there is an error adding the student or retrieving the
	 *                   student list
	 */
	private void addStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// read student info from form data
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");

		// create a new student object
		Student student = new Student(firstName, lastName, email);

		// add the student to the database
		this.studentDbUtil.add(student);

		// send back to main page with the updated list
		listStudents(request, response);
	}

	/**
	 * Loads a student with the given ID and forwards the request to the update
	 * student form page.
	 * 
	 * @param request  the HTTP servlet request object
	 * @param response the HTTP servlet response object
	 * 
	 * @throws Exception if there is an error while loading the student or
	 *                   forwarding the request
	 */
	private void loadStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// read student id from form data
		String studentId = request.getParameter("studentId");

		// get student from database (db util)
		Student student = this.studentDbUtil.get(studentId);

		// place student in the request attribute
		request.setAttribute("STUDENT", student);

		// send to update-student-form.jsp
		RequestDispatcher dispatcher = request.getRequestDispatcher("/update-student-form.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * Updates a student's information based on the data submitted through a form.
	 * 
	 * @param request  the HTTP request object containing the student's information
	 * @param response the HTTP servlet response object
	 */
	private void updateStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// read student info from form data
		int studentId = Integer.parseInt(request.getParameter("studentId"));
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");

		// create a new student object
		Student student = new Student(studentId, firstName, lastName, email);

		// perform update on database
		this.studentDbUtil.update(student);

		// send back to main page with the updated list
		listStudents(request, response);
	}

	private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// read student id from form data
		String studentId = request.getParameter("studentId");

		// delete student from database
		this.studentDbUtil.delete(studentId);

		// redirect to list-student.jsp page
		// send back to main page with the updated list
		listStudents(request, response);
	}

}
