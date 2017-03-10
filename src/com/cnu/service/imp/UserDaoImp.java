package com.cnu.service.imp;

import java.sql.SQLException;


import com.cnu.dao.core.BaseDaoImp;
import com.cnu.pojo.User;
import com.cnu.service.UserDao;
import com.cnu.util.Md5Encrypt;



public class UserDaoImp extends BaseDaoImp<User> implements UserDao
{

	@Override
	public User checkLogin(String email, String password) throws SQLException {
		String sql="select * from user where email=? and password=?";
		
		
		return this.getByUniq(User.class, sql, new Object[]{email,password});
	}

	@Override
	public User getUserInfo(String email) throws SQLException {
		String sql="select * from user where email=?";
		
		return this.getByUniq(User.class, sql, new Object[]{email});
		
	}
public static void main(String[] args)
	
	{
		User user=new User();
		//user.setId(1);
		user.setEmail("xuanxuan@qq.com");
		user.setPassword(Md5Encrypt.md5("xuanhao13"));
		//Md5Encrypt.md5(ma.getPassword())
		user.setBirth("2015-01-01");
		user.setName("Xuan");
		user.setEdu("Master");
		user.setNation("China");
		user.setPhone("1876659");
		user.setSex("F");
		user.setWork("EDU");
		user.setUsername("xuan");
		user.setPlace("Bejing");
		//System.out.println(user.getEdu());
		
		UserDaoImp udp=new UserDaoImp();
		try {
			udp.add(user);
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
