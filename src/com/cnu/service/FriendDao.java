package com.cnu.service;

import java.sql.SQLException;
import java.util.List;

import com.cnu.dao.core.BaseDao;
import com.cnu.pojo.Friend;


public interface FriendDao extends BaseDao<Friend> 
{
	public Friend getFriendInfo(String email)throws SQLException;
	public List<Friend> getAllFriendInfo(String email)throws SQLException;

}
