package com.cnu.servlet.admin;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cnu.dao.core.ConnectionManager;
import com.cnu.page.PageDiv;
import com.cnu.pojo.Files;
import com.cnu.servlet.core.ServletBase;
@WebServlet("/admin/resource")
public class ResourceServlet extends ServletBase 
{
	private static final long serialVersionUID = 5501135377804179772L;

	
	
	public void reslist(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException 
	{
		int pageNo=this.getInt(req, "pageNo");
		if(pageNo==0)pageNo=1;
		int pageSize=Integer.parseInt(this.getWebInfo("pageSize"));
		try {
			PageDiv<Files> pd=filesDao.findFilesByValid(pageNo, pageSize, 0);
			req.setAttribute("pd", pd);
		} catch (SQLException e) {
			req.setAttribute("errors", "查询资源出错!");
		}finally{
			ConnectionManager.getInstance().closeConnection();
		}
        this.forward(req, resp, "admin/res_list.jsp");
	}
	
	public void attachment(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException 
	{
		reslist(req,resp);

	}
	public void delete(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException 
	{
	    int fids[]=this.getIntArray(req, "fid");
	 
	    try {
	    	   if(null!=fids&&fids.length>0)
	   	    {
	    		   for(int fid:fids)
	    		   {
						Files file=filesDao.getById(Files.class, null, fid);
						File f=new File(this.getServletContext().getRealPath(file.getPath()));
						if(f.exists())f.delete();
						filesDao.delete(Files.class,fid);
	    		   }
	   	    }
			req.setAttribute("errors","删除资源成功！");
		} catch (SQLException e) {
			req.setAttribute("errors","删除资源失败！");
		}finally
		{
			ConnectionManager.getInstance().closeConnection();
		}
	    
	   reslist(req, resp);
	}
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException 
	{
		reslist(req, resp);

	}

}
