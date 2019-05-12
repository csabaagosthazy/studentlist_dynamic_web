package student_data;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import com.google.gson.Gson;
import com.google.gson.JsonArray;


/**
 * DAO class for accessing movies. 
 * NB! There is no user input/output in this class!
 * 
 * @author Kari
 * @version 1.1 2017-10-22
 */
public class StudentDAO 
{
	private final String username;			 
	private final String password; 
	private final String databaseURL;

	public StudentDAO() throws Exception {
		username = ConnectionParameters.username;			 
		password = ConnectionParameters.password;
		databaseURL = ConnectionParameters.databaseURL;
		
		// In *Tomcat 8* the JDBC driver must be explicitly loaded as below
		try {
			Class.forName(ConnectionParameters.jdbcDriver);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		} 
    }
	
	/**
	 * Opens a new database connection 
	 * @throws SQLException
	 */
	private Connection openConnection() throws SQLException	{
		Connection dbConnection = DriverManager.getConnection(databaseURL, username, password);
		return dbConnection;
	}
	
	/**
	 * Closes an existing database connection 
	 * @throws SQLException
	 */
	private void closeConnection(Connection dbConnection) throws SQLException {
		if (dbConnection != null) {
			dbConnection.close();
		}
	}
		
	public List<Student> getAllStudents() throws SQLException {
		List<Student> studentList = new ArrayList<Student>();
		Connection dbConnection = null;
		
		try {
			dbConnection = openConnection();

			String sqlText = 
					"SELECT id, firstname, lastname, streetaddress, postcode, postoffice " +
					"FROM Student ORDER BY id desc";
			
			PreparedStatement preparedStatement = dbConnection.prepareStatement(sqlText);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String firstName = resultSet.getString("firstname");
				String lastName = resultSet.getString("lastname");
				String streetAdd = resultSet.getString("streetaddress");
				String postcode = resultSet.getString("postcode");
				String postOffice = resultSet.getString("postoffice");

				studentList.add(new Student(id, lastName, firstName, streetAdd, postcode, postOffice));
			}
			
			return studentList;
			
		} catch (SQLException sqle)	{
			throw sqle;	// Let the caller decide what to do with the exception
			
		} finally {
			closeConnection(dbConnection);
		}
	}
	
	public Student getStudentsByID(int givenId) throws SQLException {
		Student student = new Student();
		Connection dbConnection = null;
		
		try {
			dbConnection = openConnection();
			
			String sqlText = "SELECT * "+
							 "FROM Student WHERE id=? ";
			
			PreparedStatement preparedStatement = dbConnection.prepareStatement(sqlText);
			preparedStatement.setInt(1, givenId);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String firstName = resultSet.getString("firstname");
				String lastName = resultSet.getString("lastname");
				String streetAddress = resultSet.getString("streetaddress");
				String postCode = resultSet.getString("postcode");
				String postOffice = resultSet.getString("postoffice");
				
				student = new Student(id, firstName, lastName, streetAddress, postCode, postOffice);
			}
			
			return student;
		} catch (SQLException sqle)	{
			throw sqle; 
		
		} finally {
			closeConnection(dbConnection);
		}
	}
	
	public int insertStudent(Student student) throws SQLException {
		Connection dbConnection = null;
		
		int result= 0;
		
		try {
			dbConnection = openConnection();
			
			String sqlText = "INSERT INTO Student (id, firstname, lastname, streetaddress, postcode, postoffice) VALUES (?, ?, ?, ?, ?, ?)";
			
			PreparedStatement preparedStatement = dbConnection.prepareStatement(sqlText);
			
			preparedStatement.setInt(1, student.getId());
			preparedStatement.setString(2, student.getFirstName());
			preparedStatement.setString(3, student.getLastName());
			preparedStatement.setString(4, student.getStreet());
			preparedStatement.setString(5, student.getPostcode());
			preparedStatement.setString(6, student.getPostOffice());
			
			preparedStatement.executeUpdate();

			return result;
		} catch (SQLException sqle)	{
			// First, check if the problem is primary key violation (the error code is vendor-dependent)
			if (sqle.getErrorCode() == ConnectionParameters.PK_VIOLATION_ERROR) {
				result = 1;
				return result;
			} else {
				result = -1;
				return result;
			}
		
		} finally {
			if (dbConnection != null) {
				try {
					dbConnection.close();
				} catch (SQLException sqle) {
					System.out.println("\nClose connection failed. \n" + sqle.getMessage());
				}
			}
		}
	}
	
	public JsonArray getAllStudentsJSON() throws SQLException {
		String jsonString;
		Gson gson;
		JsonArray array = new JsonArray();
		Map<String, String> linkedHashMap = new LinkedHashMap<>();
		Connection dbConnection = null;
		
		try {
			dbConnection = openConnection();

			String sqlText = 
					"SELECT id, firstname, lastname, streetaddress, postcode, postoffice " +
					"FROM Student ORDER BY firstname";
			
			PreparedStatement preparedStatement = dbConnection.prepareStatement(sqlText);
			
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				String id = resultSet.getString("id");
				String firstName = resultSet.getString("firstname");
				String lastName = resultSet.getString("lastname");
				String streetAdd = resultSet.getString("streetaddress");
				String postcode = resultSet.getString("postcode");
				String postOffice = resultSet.getString("postoffice");
				
				linkedHashMap.put("Id", id);
				linkedHashMap.put("firstname", firstName);
				linkedHashMap.put("lastname", lastName);
				linkedHashMap.put("streetaddress", streetAdd);
				linkedHashMap.put("postcode", postcode);
				linkedHashMap.put("postoffice", postOffice);

				gson = new Gson();

				jsonString = gson.toJson(linkedHashMap, LinkedHashMap.class);
				
				array.add(jsonString);
				
			}
			
			
			return array;
			
		} catch (SQLException sqle)	{
			throw sqle;	// Let the caller decide what to do with the exception
			
		} finally {
			closeConnection(dbConnection);
		}
	}
}

// End