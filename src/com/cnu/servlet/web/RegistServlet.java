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


@SuppressWarnings("serial")
@WebServlet("/space/regist")
public class RegistServlet extends ServletBase {

	
			
			public void add(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
		   {
				 User user=new User();
				 this.getBean(req, user);
				 user.setPassword(Md5Encrypt.md5(user.getPassword()));
				 try {
					userDao.add(user);
					req.setAttribute("error", "增加用户成功");
				} catch (SQLException e) {
					req.setAttribute("error", "增加用户失败");
				}finally
				{
					this.forward(req, resp, "admin/login.jsp");
					ConnectionManager.getInstance().closeConnection();
				}
				
		   }
			
			
			
			@Override
			public void execute(HttpServletRequest req, HttpServletResponse resp)
					throws ServletException, IOException
			{
				this.forward(req, resp, "admin/regist.jsp");
			}

	
}
