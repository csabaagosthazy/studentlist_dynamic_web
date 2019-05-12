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

		<form action="studentListByIdService" method="GET">
			<label>Student ID: </label>
			<input type="text" name="id" />
			<input type="submit" value="Search" />
		</form>
		<c:if test="${student.id != 0 }">
			<p><c:out value="${ student.id}" /> 
			<c:out value="${ student.lastName }" /> 
			<c:out value="${ student.firstName }" /></p>
		</c:if>
		
		<c:if test="${(student == null || student.id == 0) && msg == null}">
			<p>No students found for the given id: <c:out value="${id }"></c:out></p>
		</c:if>
		<c:if test="${msg != null }">
			<p><c:out value="${msg}"></c:out></p>
		</c:if>


	</div>

</body>
</html>