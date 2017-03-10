package com.cnu.service.imp;

import java.sql.SQLException;
import java.util.List;

import com.cnu.dao.core.BaseDaoImp;
import com.cnu.pojo.Friend;

import com.cnu.service.FriendDao;



public class FriendDaoImp extends BaseDaoImp<Friend> implements FriendDao
{

	
	public Friend getFriendInfo(String email) throws SQLException
	{
        String sql="select * from friend where email=?";
		
		return this.getByUniq(Friend.class, sql, new Object[]{email});
		
	}

	@Override
	public List<Friend>  getAllFriendInfo(String email) throws SQLException {
    
		String sql="select * from friend where email=?";
		
		return this.getAll(Friend.class, sql, new Object[]{email});
	}

}
