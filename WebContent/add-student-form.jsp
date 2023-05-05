<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Student</title>
<!-- Add styles -->
<link type="text/css" rel="stylesheet" href="css/style.css" />
<link type="text/css" rel="stylesheet" href="css/add-student-style.css" />
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h2>Flibbertigibbet College</h2>
		</div>
	</div>

	<div id="container">
		<div id="content">
			<h3>Add Student</h3>

			<form action="StudentControllerServlet" method="GET">
				<input type="hidden" name="command" value="ADD" />

				<table>
					<tr>
						<td><label for="firstname">First name: </label></td>
						<td><input type="text" id="firstname" name="firstName" /></td>
					</tr>
					<tr>
						<td><label for="lastname">Last name: </label></td>
						<td><input type="text" id="lastname" name="lastName" /></td>
					</tr>
					<tr>
						<td><label for="email">Email: </label></td>
						<td><input type="email" id="email" name="email" /></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" class="save" value="Save" /></td>
					</tr>
				</table>
			</form>

			<br /> <a href="StudentControllerServlet?command=LIST">Back to list</a>
		</div>
	</div>
</body>
</html>