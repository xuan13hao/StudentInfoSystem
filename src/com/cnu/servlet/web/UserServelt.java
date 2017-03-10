package com.cnu.servlet.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cnu.dao.core.ConnectionManager;
import com.cnu.pojo.User;
import com.cnu.servlet.core.ServletBase;
import com.cnu.util.Md5Encrypt;

@WebServlet("/space/user")
public class UserServelt extends ServletBase
{
	private static final long serialVersionUID = 2867729518726519060L;
	public void modifypwd(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		   User user=(User)req.getSession().getAttribute("user");
		  
		   String pwd=this.getString(req,"pwd");
		   String repwd=this.getString(req,"repwd");
		   System.out.println(pwd);
		   System.out.println(repwd);
			   if(pwd.equals(repwd))
			   {
				   user.setPassword(Md5Encrypt.md5(pwd));
				try
				{
					userDao.update(user);
				} catch (SQLException e)
				{
					   req.setAttribute("errors", "修改密码失败!");
					   this.forward(req, resp, "admin/modify_password.jsp");  
					   e.printStackTrace();
				}finally{
					ConnectionManager.getInstance().closeConnection();
				}
				   req.setAttribute("errors", "修改密码成功!");
				   this.forward(req, resp, "admin/modify_password.jsp");   
				   
			   }else
			   {
				   req.setAttribute("errors", "两次输入的密码不一玫!");
				   this.forward(req, resp, "admin/modify_password.jsp");  
			   }
		   }
	
	
	
	public void list(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException
     {
		
			User user=(User)req.getSession().getAttribute("user");
			User userinfo=null;
			try {
				userinfo=userDao.getUserInfo(user.getEmail());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		req.setAttribute("userinfo", userinfo);
		this.forward(req, resp, "admin/managers.jsp");
		
     }
	
	public void add(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException
   {
		 User user=new User();
		 this.getBean(req, user);
		 user.setPassword(Md5Encrypt.md5(user.getPassword()));
		 try {
			userDao.add(user);
			req.setAttribute("error", "增加信息成功");
		} catch (SQLException e) {
			req.setAttribute("error", "增加信息失败");
		}finally
		{
			ConnectionManager.getInstance().closeConnection();
		}
		list(req,resp);
   }
	
	public void update(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException
   {
		String email=this.getString(req, "email");
		/*
		String mid=this.getInt(req, "mid");
		String mid=this.getInt(req, "mid");
		String mid=this.getInt(req, "mid");
		String mid=this.getInt(req, "mid");
		*/
		if(null!=email)
		{
			try {
				User user=userDao.getUserInfo(email);
				 System.out.println(user);
			//User user=userDao.getById(User.class, null, mid);
			 this.getBean(req, user);
			
			//user.setPassword(Md5Encrypt.md5(user.getPassword()));
			// user.setBirth(user.getBirth());
			 //user.setEdu(user.getEdu());
			 //user.setNation(user.getNation());
			 //user.setPhone(user.getPhone());
			 //user.setSex(user.getSex());
			 //user.setWork(user.getWork());
		
			  userDao.update(user);
			  req.setAttribute("errors", "修改信息成功");
			} catch (SQLException e) {                   
				req.setAttribute("errors", "修改信息失败");
			}finally
			{
				ConnectionManager.getInstance().closeConnection();
			}
		}else
		{
			req.setAttribute("errors", "修改信息失败");
		}
		list(req,resp);
   }
	
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
			
	{
		
		list(req,resp);
	}

}
