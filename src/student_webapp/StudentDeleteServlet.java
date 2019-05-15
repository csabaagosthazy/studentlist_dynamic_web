package student_webapp;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import student_data.StudentDAO;

@WebServlet("/DeleteStudent")
public class StudentDeleteServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		// input id req
		int Id = Integer.parseInt(request.getParameter("id"));
		
		StudentDAO studentDAO = null;
		
		try {
			studentDAO = new StudentDAO();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			try {
				studentDAO.deleteStudent(Id);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		



		// 3. Add the data to the request attributes
		request.setAttribute("id", Id);
		


		// 4. Forward the request back to the JSP page
		request.getRequestDispatcher("/studentListDeleteService").forward(request, response);

	}

}
