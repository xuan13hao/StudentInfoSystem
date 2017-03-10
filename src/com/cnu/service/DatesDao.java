package com.cnu.service;

import java.sql.SQLException;
import java.util.List;

import com.cnu.dao.core.BaseDao;
import com.cnu.pojo.Dates;


public interface DatesDao extends BaseDao<Dates> 
{
	public Dates getDateInfo(String username)throws SQLException;
	
	public List<Dates>  getAllDateInfo(String email) throws SQLException;
}
