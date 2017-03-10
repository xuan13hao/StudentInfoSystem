package com.cnu.servlet.admin;

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

@WebServlet("/admin/login")
public class LoginServlet extends ServletBase
{
	private static final long serialVersionUID = 8141629767777520409L;

/**
 * 跳转到login页面
 * @param req
 * @param resp
 * @throws ServletException
 * @throws IOException
 */
	public void tologin(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
			{
		    
			   //mage(resp, null, "admin/login.html");
		        this.forward(req, resp, "admin/login.jsp");
			}
		                 
	/**
	 * 退出登陆
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void logout(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
			{
		           req.getSession().invalidate();
		
		           this.forward(req, resp, "admin/login.jsp");
			}
	/**
	 * 验证用户名和密码
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void checkLogin(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
			{
		         //  Map<String,Object> root=new HashMap<String,Object>();
		           
		            String randimg=this.getString(req, "randimg");
		            String sessionimg=(String)req.getSession().getAttribute("randomCode");
		            
		            if(null!=sessionimg&&!"".equals(sessionimg)&&!"".equals(randimg)&&Md5Encrypt.md5(randimg).equals(sessionimg))
		            {
		            	User user=new User();
		            	this.getBean(req, user);
		                User newuser=null;
		                try
						{
							newuser=userDao.checkLogin(user.getEmail(), Md5Encrypt.md5(user.getPassword()));
						} catch (SQLException e)
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						}finally
						{
							ConnectionManager.getInstance().closeConnection();
						}
		                if(null!=newuser&&newuser.getEmail().equals(user.getEmail())&&newuser.getPassword().equals(Md5Encrypt.md5(user.getPassword())))
		                {
		                	req.getSession().setAttribute("user",user);
		                    //成功
		                	 this.forward(req, resp, "admin/admin_index.jsp");
		                }else
		                {
		                	//失败
		                	req.setAttribute("errors","用户名或密码不正确!");
		            	 this.forward(req, resp, "admin/login.jsp");
		                }
		            	
		            }else
		            {
		            	//验证码不对
		            	req.setAttribute("errors","验证码不对!");
		            	 this.forward(req, resp, "admin/login.jsp");
		            }
		     
		
			}
	
	
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		 tologin(req,resp);
		
	}

}
