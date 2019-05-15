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
	    <h3>List of Students</h3>

		<form action="studentListDeleteService" method="GET">
			<c:if test="${ studentList != null && studentList.size() > 0 }">
				<table>
					<thead>
						<tr>
							<th>Student id</th><th>Last Name</th><th>First Name</th><th>Street</th><th>Postcode</th><th>Post Office</th><th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${ studentList }" var="studentObject">
							<tr>
								<td><c:out value="${ studentObject.id}" /></td>
								<td><c:out value="${ studentObject.lastName }" /></td>
								<td><c:out value="${ studentObject.firstName }" /></td>
								<td><c:out value="${ studentObject.street }" /></td>
								<td><c:out value="${ studentObject.postcode }" /></td>
								<td><c:out value="${ studentObject.postOffice }" /></td>
								<td><a href='DeleteStudent?id=<c:out value="${ studentObject.id }" />'
									   onClick="return confirm('Do you want to delete the Student?')">Delete</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:if>
		</form>

	</div>

</body>
</html>
	
	
	
	
	
								