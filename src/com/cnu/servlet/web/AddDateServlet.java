package com.cnu.servlet.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cnu.dao.core.ConnectionManager;
import com.cnu.pojo.Dates;
import com.cnu.pojo.User;
import com.cnu.servlet.core.ServletBase;


@SuppressWarnings("serial")
@WebServlet("/dates")
public class AddDateServlet extends ServletBase {

	
			
			public void add(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
		   {
				
				User user=(User)req.getSession().getAttribute("user");
				 Dates date=new Dates();
				 req.setAttribute("email", user);
				
				 this.getBean(req, date);
				 
				 try {
					 
				datesDao.add(date);
					
				req.setAttribute("error", "增加日程chengong");
				} catch (SQLException e) {
					req.setAttribute("error", "增加日程失败");
				}finally
				{
					this.forward(req, resp, "admin/welcome.jsp");
					ConnectionManager.getInstance().closeConnection();
				}
				
		   }
			
			
			
			@Override
			public void execute(HttpServletRequest req, HttpServletResponse resp)
					throws ServletException, IOException
			{
				
				this.forward(req, resp, "date/add_date.jsp");
			}

	
}
