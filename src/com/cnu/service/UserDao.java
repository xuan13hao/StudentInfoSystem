package com.cnu.service;

import java.sql.SQLException;


import com.cnu.dao.core.BaseDao;
import com.cnu.pojo.User;


public interface UserDao extends BaseDao<User> 
{
	/**
	 * ��֤��½
	 * @param email
	 * @param password
	 * @return
	 * @throws SQLException
	 */
     public User checkLogin(String username,String password)throws SQLException;
     /**
      * �õ����е��û�
      * @return
      * @throws SQLException
      */
     public User getUserInfo(String email)throws SQLException;

}
