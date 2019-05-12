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

@WebServlet("/studentListByIdService")
public class StudentListById extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Student student = null;
		int inputId = -1;
		String message = null;
		
		// input id req
		String inputIdText = request.getParameter("id");
		
		StudentDAO studentDAO = null;
		
		if(inputIdText != null) {
			try {
				inputId =Integer.parseInt(inputIdText);
			}catch (Exception ex) {
				message = "Please give an id!";
				ex.printStackTrace();
			}
		}
		
		try {
			studentDAO = new StudentDAO();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(inputId > 0) {
			try {
				student = studentDAO.getStudentsByID(inputId);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}



		// 3. Add the data to the request attributes
		request.setAttribute("student", student);
		request.setAttribute("id", inputIdText);
		request.setAttribute("msg", message);
		


		// 4. Forward the request back to the JSP page
		request.getRequestDispatcher("StudentListById.jsp").forward(request, response);

	}


}
