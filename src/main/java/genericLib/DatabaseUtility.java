package genericLib;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.jdbc.Driver;

/**
 * This class contains reusable methods to perform actions on database
 * @author CBT
 *
 */
public class DatabaseUtility {
	private Connection connection = null;
	
	/**
	 * This method is used to initialize database
	 * @param url
	 * @param dbUser
	 * @param dbPwd
	 */
	public void databaseInit(String url, String dbUser, String dbPwd)  {
		Driver dbDriver = null;
		try {
			dbDriver = new Driver();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			DriverManager.registerDriver(dbDriver);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			connection = DriverManager.getConnection(url, dbUser, dbPwd);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method is used to fetch particular column data from the table 
	 * @param query
	 * @param colName
	 * @return
	 * @throws SQLException
	 */
	public List<String> getDataFromDatabase(String query, String colName) throws SQLException {
		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery(query);
		
		List<String> list = new ArrayList<>();
		while(result.next()) {
			list.add(result.getString(colName));
		}
		
		return list;
	}
	
	/**
	 * This method is used to update database
	 * @param query
	 * @return
	 */
	public int modifyDatabase(String query) {
		Statement statement = null;
		try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		int result = 0;
		try {
			result = statement.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * This method is used to close database connection
	 */
	public void closeDatabase() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
