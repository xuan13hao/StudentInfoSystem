package com.cnu.service.imp;

import java.sql.SQLException;
import java.util.List;

import com.cnu.dao.core.BaseDaoImp;
import com.cnu.pojo.Dates;

import com.cnu.service.DatesDao;

public class DatesDaoImp extends BaseDaoImp<Dates> implements DatesDao 
{

	@Override
	public Dates getDateInfo(String email) throws SQLException {
        String sql="select * from dates where email=?";
		
		return this.getByUniq(Dates.class, sql, new Object[]{email});
	}
	public List<Dates>  getAllDateInfo(String email) throws SQLException {
	    
		String sql="select * from dates where email=?";
		
		return this.getAll(Dates.class, sql, new Object[]{email});
}
}