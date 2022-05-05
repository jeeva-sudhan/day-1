/*DATABASE CLASS : 
 * This class connects local host sql server and our exe application and handles out data manipulation and data query tasks.
 */
package app_data;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class DataBaseRepository {
	ResultSet rs;
	Statement statement;
	PreparedStatement preparedStatement;
	Connection connection;
    ResultSet resultSet;
	int status;
	//method to get connect with database
	public void databaseConnection()throws SQLException,ClassNotFoundException {
		
		String url = "jdbc:mysql://localhost:3306/organization";
		String username = "root";
		String password = "";
		//Class.forName("com.mysql.jdbc.Driver");//registering the driver
		connection = DriverManager.getConnection(url,username,password);
	}
	//to close connection once user logout
	public void closeConnection() {
		try {
			if(statement != null && connection  != null) {
				statement.close();
				connection.close();
			}
		}
		catch(Exception exception) {
			exception.printStackTrace();
		}
	}
	//retrieve data from database
	public ResultSet select(String query) {
		try {
			databaseConnection();
			statement = connection.createStatement();
		    resultSet = statement.executeQuery(query);
		    return resultSet;
		}
		catch(ClassNotFoundException  | SQLException exception) {
			exception.printStackTrace();
		}
		return null;
	}
	//update data in database
	public int update(String query) {
		try {
			databaseConnection();
			statement = connection.createStatement();
		    status = statement.executeUpdate(query);
		    closeConnection();
		    return status;
		}
		catch(ClassNotFoundException  | SQLException exception) {
			exception.printStackTrace();
		}
		return 0;
	}
	//add data in database
	public int insert(String query) {
		try {
			databaseConnection();
			statement = connection.createStatement();
		    status = statement.executeUpdate(query);
		    closeConnection();
		    return status;
		}
		catch(ClassNotFoundException  | SQLException exception) {
			exception.printStackTrace();
		}
		return 0;
	}
	//remove data from database
	public int delete(String query) {
		try {
			databaseConnection();
			statement = connection.createStatement();
			statement.executeUpdate(query);
		    status = 1;
		    closeConnection();
		    return status;
		}
		catch(ClassNotFoundException  | SQLException exception) {
			exception.printStackTrace();
		}
		return status;
	}
}
