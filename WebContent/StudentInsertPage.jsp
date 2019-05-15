<%-- The following two tags allow the use of the JSP and JSTL tags in this source text --%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Student List</title>
	<link rel="stylesheet" type="text/css" href="css/StudentList.css">
</head>
<body>
	
	<div>
	    <h3>Search Student</h3>

		<form action="studentInsertService" method="GET">
			<label>Student ID: </label>
			<input type="text" name="id" /><br>
			<label>Student first name: </label>
			<input type="text" name="firstName" /><br>
			<label>Student last name: </label>
			<input type="text" name="lastName" /><br>
			<label>Student street address: </label>
			<input type="text" name="street" /><br>
			<label>Student post code: </label>
			<input type="text" name="postCode" /><br>
			<label>Student post office: </label>
			<input type="text" name="postOffice" /><br>
			
			<br><br>
			<input type="submit" value="Insert student" />
		</form>
		<c:if test="${msg != null }">
			<p><c:out value="${msg}"></c:out></p>
		</c:if>


	</div>

</body>
</html>	