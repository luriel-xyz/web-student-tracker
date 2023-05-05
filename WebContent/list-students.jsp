<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Student Tracker</title>
<!-- Style -->
<link type="text/css" rel="stylesheet" href="css/style.css" />
</head>

<body>
	<div id="wrapper">
		<div id="header">
			<h2>Flibbertigibbet College</h2>
		</div>
	</div>

	<div id="container">
		<div id="content">
			<input type="button" class="add-student-button" value="Add student"
				onclick="window.location.href='add-student-form.jsp'; return false;" />
			<table>
				<tr>
					<th>First name</th>
					<th>Last name</th>
					<th>Email</th>
					<th>Action</th>
				</tr>

				<c:forEach var="student" items="${STUDENT_LIST}">
					<!-- set up a link for each student -->
					<!-- StudentControllerServlet?command=LOAD&studentId=<id of the student> -->
					<c:url var="loadURL" value="StudentControllerServlet">
						<c:param name="command" value="LOAD" />
						<c:param name="studentId" value="${student.id}" />
					</c:url>

					<!-- set up a link to delete a student -->
					<c:url var="deleteURL" value="StudentControllerServlet">
						<c:param name="command" value="DELETE" />
						<c:param name="studentId" value="${student.id}" />
					</c:url>

					<tr>
						<td>${student.firstName}</td>
						<td>${student.lastName}</td>
						<td>${student.email}</td>
						<td><a href="${loadURL}">Update</a> | <a href="${deleteURL}"
							onclick="if (!confirm('Delete this student?')) return false;">Delete</a></td>
					</tr>
				</c:forEach>

			</table>
		</div>
	</div>
</body>
</html>