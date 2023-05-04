<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Student</title>
<!-- Add styles -->
<link type="text/css" rel="stylesheet" href="css/style.css" />
<link type="text/css" rel="stylesheet" href="css/add-student-style.css" />
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h2>FooBar University</h2>
		</div>
	</div>

	<div id="container">
		<div id="content">
			<h3>Update Student</h3>

			<form action="StudentControllerServlet" method="GET">
				<input type="hidden" name="command" value="UPDATE" />
				
				<input type="hidden" name="studentId" value="${STUDENT.id}" />

				<table>
					<tr>
						<td><label for="firstname">First name: </label></td>
						<td><input type="text" id="firstname" name="firstName"
							value="${STUDENT.firstName}" /></td>
					</tr>
					<tr>
						<td><label for="lastname">Last name: </label></td>
						<td><input type="text" id="lastname" name="lastName"
							value="${STUDENT.lastName}" /></td>
					</tr>
					<tr>
						<td><label for="email">Email: </label></td>
						<td><input type="email" id="email" name="email"
							value="${STUDENT.email}" /></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" class="save" value="Save" /></td>
					</tr>
				</table>
			</form>

			<br /> <a href="StudentControllerServlet">Back to list</a>
		</div>
	</div>
</body>
</html>