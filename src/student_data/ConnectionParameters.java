package student_data;

public class ConnectionParameters {
	
	
	// Set your own MariaDB username and password here!
	public static final String username = "xxxx"; 
	public static final String password = "xxxx";
	
	
	// Do NOT change any of the settings below!
	private static final String databaseName = username;
	public static final String databaseURL = "jdbc:mysql://localhost:3306/" + databaseName;
	public static final String jdbcDriver = "org.mariadb.jdbc.Driver";
	
	// PK violation: error code in MariaDB is 1062
	public static final int PK_VIOLATION_ERROR = 1062; 

}
