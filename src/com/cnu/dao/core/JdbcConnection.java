package com.cnu.dao.core;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
public class JdbcConnection extends ConnectionManager
{
    private String driver=null;
    private String url=null;
    private String user=null;
    private String pwd=null;
    private Connection con=null;
	
    public JdbcConnection()
	{
    	ResourceBundle  res=ResourceBundle.getBundle("jdbc");
    	driver=res.getString("driver");
    	url=res.getString("url");
    	user=res.getString("user");
    	pwd=res.getString("pwd");
    	
    	try
		{
			Class.forName(driver);
			con= DriverManager.getConnection(url, user, pwd);
		} catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
	}

	@Override
	public Connection getRealConnection()
	{
		return con;
	}

}
