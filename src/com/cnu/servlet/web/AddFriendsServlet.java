package com.cnu.servlet.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cnu.dao.core.ConnectionManager;
import com.cnu.pojo.Friend;
import com.cnu.pojo.User;
import com.cnu.servlet.core.ServletBase;


@SuppressWarnings("serial")
@WebServlet("/friend")
public class AddFriendsServlet extends ServletBase {

	
			
			public void add(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
		   {
				User user=(User)req.getSession().getAttribute("user");
				
				 Friend friend=new Friend();
				 this.getBean(req, friend);
				 
				 try {
					 
				friendDao.add(friend);
					
					req.setAttribute("email", user);
				} catch (SQLException e) {
					req.setAttribute("error", "增加用户信息失败");
				}finally
				{
					//this.forward(req, resp, "friends/query_friends.jsp");
					ConnectionManager.getInstance().closeConnection();
				}
				
		   }
			
			
			
			@Override
			public void execute(HttpServletRequest req, HttpServletResponse resp)
					throws ServletException, IOException
			{
				
				this.forward(req, resp, "friends/add_friends.jsp");
			}

	
}
