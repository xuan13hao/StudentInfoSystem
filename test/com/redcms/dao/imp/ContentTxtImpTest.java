package com.redcms.dao.imp;

import java.sql.SQLException;

import org.junit.Test;

import com.redcms.beans.ContentTxt;
import com.redcms.dao.ContentTxtDao;
import com.redcms.dao.core.DaoFactory;

public class ContentTxtImpTest 
{
	@Test
	public void testTxt()
	{
		ContentTxtDao cd=(ContentTxtDao)DaoFactory.getDao(ContentTxtDao.class);
		try {
			ContentTxt tt=cd.getContentTxtByContentId("articles", 2);
			System.out.println(tt.getTxt());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
