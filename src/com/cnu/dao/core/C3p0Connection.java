package com.cnu.dao.core;
import java.sql.Connection;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import com.mchange.v2.c3p0.ComboPooledDataSource;
public class C3p0Connection extends ConnectionManager
{
	private static final Logger log=Logger.getLogger(C3p0Connection.class);
    private static ComboPooledDataSource  ds=new ComboPooledDataSource();

	@Override
	public Connection getRealConnection()
	{
		Connection con=null;
		try
		{
			con=ds.getConnection();
		} catch (SQLException e)
		{
		 log.error("009_C3p0Connection_获取Connection有问题");
		}
		return con;
	}

}
