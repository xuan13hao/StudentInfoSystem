package com.cnu.service.imp;

import java.sql.SQLException;
import java.util.List;

import com.cnu.pojo.Files;
import com.cnu.service.FilesDao;
import com.cnu.dao.core.BaseDaoImp;
import com.cnu.page.PageDiv;

public class FilesDaoImp extends BaseDaoImp<Files> implements FilesDao
{

	@Override
	public List<Files> getFilesByChannel(Integer channelId) throws SQLException
	{
		
		return null;
	}

	@Override
	public void updateFilesBychannel(Integer channelId) throws SQLException
	{
	     String sql="update files set isvalid=0 where channel_id=?";
	     this.updateBySql(sql, new Object[]{channelId});
	}

	@Override
	public void deleteFilesByModeContentChannel(int modelId, int channelId,
			int contentId) throws SQLException {
		 String sql="delete from files where model_id=? and channel_id=? and content_id=?";
		 this.updateBySql(sql, new Object[]{modelId,channelId,contentId});
		
	}

	@Override
	public List<Files> FilesByModeContentChannel(int modelId, int channelId,
			int contentId) throws SQLException {
		String sql="select * from files where model_id=? and channel_id=? and content_id=?";
		return this.getAll(Files.class, sql, new Object[]{modelId,channelId,contentId});
	}

	@Override
	public PageDiv<Files> findFilesByValid(int pageNo,int pageSize,int valid) throws SQLException {
		String sql="select * from files where isvalid=?";
		return this.getByPage(Files.class, sql, pageNo, pageSize, new Object[]{valid});
	}

}
