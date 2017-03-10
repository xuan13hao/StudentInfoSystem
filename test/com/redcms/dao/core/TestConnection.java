package com.redcms.dao.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import org.junit.Test;

import com.cnu.pojo.User;
import com.cnu.service.imp.UserDaoImp;

public class TestConnection
{
	
	@Test
   public void testCon()
   {
    @SuppressWarnings("unused")
	ConnectionManager cm=ConnectionManager.getInstance();
	  /* System.out.println(cm);
	   ConnectionManager cm1=ConnectionManager.getInstance();
	   System.out.println(cm1);
	   System.out.println(cm.getConnection());
	   System.out.println(cm.getConnection());
	   System.out.println(cm1.getConnection());
	   System.out.println(cm1.getConnection());
	   ModelDao  md=(ModelDao)DaoFactory.getDao(ModelDao.class);
		ConnectionManager cm2=ConnectionManager.getInstance();
	try
	{
	
		cm2.setAutoCommit(false);
		List<Model> all= md.getAllModel();
		for(Model m:all)
		{
			System.out.println(m.getName());
		}
		cm2.commit();
	} catch (Exception e)
	{
		cm2.rollback();
		e.printStackTrace();
	}*/
   ResourceBundle  res=ResourceBundle.getBundle("jdbc");
   String	driver=res.getString("driver");
   String		url=res.getString("url");
   String		user=res.getString("user");
   String		pwd=res.getString("pwd");
   	Connection con=null;
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
	   try {
		   Statement stat=con.createStatement();
		   con.setAutoCommit(false);
		  // con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
	      
		   System.out.println(con.getAutoCommit());
		 
		   User user=new User();
			user.setEmail("xuan@126.com");
			user.setPassword("xuan");
			user.setBirth("2015-01-01");
			user.setName("Xuan");
			user.setEdu("Master");
			user.setNation("China");
			user.setPhone("1876659");
			user.setSex("F");
			user.setWork("EDU");
			user.setUsername("xuan");
			UserDaoImp udp=new UserDaoImp();
			
				udp.add(user);
			
		   
		   con.commit();
		   
	} catch (SQLException e) {
		try {
			con.rollback();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		e.printStackTrace();
	}
	   
   }

	
}
