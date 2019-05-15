package student_webapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import student_data.StudentDAO;

@WebServlet("/studentListJsonService")
public class StudentListJsonServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	public StudentListJsonServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String json = "";
		//String json=null;
		// 2. Create the data to be sent to the JSP page
		StudentDAO studentDAO = null;
		try {
			studentDAO = new StudentDAO();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			json = studentDAO.getStudentsJSONManual();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		response.setContentType("application/json");
		response.getWriter().write(json);
//		response.getWriter().write(array.toString());
		



	}
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }


}
