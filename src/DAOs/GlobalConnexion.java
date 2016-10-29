package DAOs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GlobalConnexion 
{

	static String driver="com.mysql.jdbc.Driver";
	static String url = "jdbc:mysql://localhost:3306/pweb?autoReconnect=true&useSSL=false";
	static String uid = "root";
	static String passwd = "root";
	static Connection con = null;
	
	public static Connection getConnection()
	{
		try
		{
		Class.forName(driver);
		con = DriverManager.getConnection(url, uid, passwd);
		}
		catch(ClassNotFoundException e)
		{
			System.out.println(e.getMessage());
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return con;
	}
}
