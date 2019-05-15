package student_webapp;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import student_data.Student;
import student_data.StudentDAO;

@WebServlet("/studentListDeleteService")
public class StudentListWithDeleteServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Student> studentList = null;
		// 2. Create the data to be sent to the JSP page
		StudentDAO studentDAO = null;
		try {
			studentDAO = new StudentDAO();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			studentList = studentDAO.getAllStudents();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 3. Add the data to the request attributes
		request.setAttribute("studentList", studentList);
		


		// 4. Forward the request back to the JSP page
		request.getRequestDispatcher("StudentListWithDelete.jsp").forward(request, response);

	}


}
