package com.cnu.service;

import java.sql.SQLException;


import com.cnu.dao.core.BaseDao;
import com.cnu.pojo.User;


public interface UserDao extends BaseDao<User> 
{
	/**
	 * 验证登陆
	 * @param email
	 * @param password
	 * @return
	 * @throws SQLException
	 */
     public User checkLogin(String username,String password)throws SQLException;
     /**
      * 得到所有的用户
      * @return
      * @throws SQLException
      */
     public User getUserInfo(String email)throws SQLException;

}
