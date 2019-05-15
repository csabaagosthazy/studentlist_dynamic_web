package student_webapp;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import student_data.Student;
import student_data.StudentDAO;

@WebServlet("/studentInsertService")
public class StudentInsertServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Student student = null;
		int Id = -1;
		String firstName = null, lastName = null, street = null, postCode = null, postOffice = null;
		int msgCode = -99;
		String message = null;
		
		// input id req
		String inputIdText = request.getParameter("id");
		firstName = request.getParameter("firstName"); 
		lastName = request.getParameter("lastName");
		street = request.getParameter("street");
		postCode = request.getParameter("postCode");
		postOffice = request.getParameter("postOffice");
		
		StudentDAO studentDAO = null;
		
		if(inputIdText != null) {
			try {
				Id =Integer.parseInt(inputIdText);
			}catch (Exception ex) {
				message = "Please give a number as id!";
				ex.printStackTrace();
			}
		}
		
		try {
			studentDAO = new StudentDAO();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(Id > 0) {
			try {
				student = new Student(Id, firstName, lastName, street, postCode, postOffice);
				msgCode = studentDAO.insertStudent(student);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		switch(msgCode) {
		
		case 0: message ="Student data saved successfully.";
		break;
		case 1: message ="Cannot save the student data. Student id "+Id+" is already in use.";
		break;
		case -1: message = "The database is temporarily unavailable. Please try again later.";
		break;
		default: message ="Please give student data";
		}


		// 3. Add the data to the request attributes
		request.setAttribute("student", student);
		request.setAttribute("id", inputIdText);
		request.setAttribute("firstName", firstName);
		request.setAttribute("lastName", lastName);
		request.setAttribute("street", street);
		request.setAttribute("postCode", postCode);
		request.setAttribute("postOffice", postOffice);
		request.setAttribute("msg", message);
		


		// 4. Forward the request back to the JSP page
		request.getRequestDispatcher("StudentInsertPage.jsp").forward(request, response);

	}


}
