package com.wipro.sales.unit;

import java.sql.*;

public class DButil {
public static Connection getDBConnection()
{	
	Connection con=null;
	try 
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
	 	con=DriverManager.getConnection("jdbc:mysql://localhost:3306/wipro","root","");
	}
	catch(Exception e)
	{
		e.getMessage();
	}
	
	return con;
}
}
