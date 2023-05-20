package practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class ReadDataFromDatabase {

	public static void main(String[] args) throws SQLException {
		//Step 1: Create instance for Driver
		Driver dbDriver = new Driver();
		
		//Step 2: Register driver to JDBC
		DriverManager.registerDriver(dbDriver);
		
		//Step 3: Establish database connection
		Connection connection = DriverManager
			.getConnection("jdbc:mysql://localhost:3306/advsel", "root", "root");
		
		//Step 4: Create statement
		Statement statement = connection.createStatement();
		
		//Step 5: Execute Query to read data from database
		ResultSet result = statement.executeQuery("select * from students;");
		
		while(result.next()) {
			System.out.println(result.getInt("id")+"\t"+result.getString("name")
												+"\t"+result.getString("address"));
		}

		//Step 6: Close database connection
		connection.close();
	}

}
