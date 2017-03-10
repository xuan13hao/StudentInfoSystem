package com.cnu.servlet.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cnu.dao.core.ConnectionManager;
import com.cnu.pojo.Friend;
import com.cnu.pojo.User;
import com.cnu.servlet.core.ServletBase;


@WebServlet("/space/friends")
public class FriendsServelt extends ServletBase
{
	private static final long serialVersionUID = 2867729518726519060L;
	
	public void list(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException
     {
		
			User user=(User)req.getSession().getAttribute("user");
		
			
			List<Friend> friendsinfo=null;
			try {
				friendsinfo=friendDao.getAllFriendInfo(user.getEmail());
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		req.setAttribute("friendsinfo", friendsinfo);
		this.forward(req, resp, "friends/query_friends.jsp");
		
     }
	
	
	public void alllist(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
		     {
				
					User user=(User)req.getSession().getAttribute("user");
					
					
					List<Friend> friendsinfo=null;
					try {
						friendsinfo=friendDao.getAllFriendInfo(user.getEmail());
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				req.setAttribute("friendsinfo", friendsinfo);
				this.forward(req, resp, "friends/modify_friends.jsp");
				
		     }
	
	public void add(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException
   {
		
		Friend friends =new Friend();
		 this.getBean(req, friends);
		 //friends.setPassword(Md5Encrypt.md5(user.getPassword()));
		 try {
			friendDao.add(friends);
			req.setAttribute("error", "���ӹ���Ա�ɹ�");
		} catch (SQLException e) {
			req.setAttribute("error", "���ӹ���Աʧ��");
		}finally
		{
			ConnectionManager.getInstance().closeConnection();
		}
		alllist(req,resp);
   }
	
	public void update(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException
   {
		String username=this.getString(req, "email");
		System.out.println(username);
		if(null!=username)
		{
			try {
				Friend friends=friendDao.getFriendInfo(username);
			//User user=userDao.getById(User.class, null, mid);
			 this.getBean(req, friends);
			  friendDao.update(friends);
			  req.setAttribute("errors", "���ͨ��¼�ɹ�");
			} catch (SQLException e) {                   
				req.setAttribute("errors", "���ͨ��¼ʧ��");
			}finally
			{
				ConnectionManager.getInstance().closeConnection();
			}
		}else
		{
			req.setAttribute("errors", "�޸�ͨ��¼��");
		}
		alllist(req,resp);
   }
	public void delete(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException
	{
		int mid=this.getInt(req, "mid");
	
		if(mid>0)
		{
			try {
				
			  friendDao.delete(Friend.class, mid);
			  req.setAttribute("errors", "ɾ����Ϣ�ɹ�");
			} catch (SQLException e) {
				req.setAttribute("errors", "ɾ����Ϣʧ��");
			}finally
			{
				ConnectionManager.getInstance().closeConnection();
			}
		}else
		{
			req.setAttribute("errors", "ɾ������Ϣʧ��");
		}
		alllist(req,resp);
	}
	/*
	public void deleteOrder(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
			{
				//int mid=this.getInt(req, "mid");
				int ordersid=this.getInt(req, "ordersid");
				if(ordersid>0)
				{
					try {
						orderDao.delete(Orders.class, ordersid);
					//  managerDao.delete(Manager.class, mid);
					  req.setAttribute("errors", "ɾ�������ɹ�");
					} catch (SQLException e) {
						req.setAttribute("errors", "ɾ������ʧ��");
					}finally
					{
						ConnectionManager.getInstance().closeConnection();
					}
				}else
				{
					req.setAttribute("errors", "ɾ������ʧ��");
				}
				list(req,resp);
			}
			*/
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
			
	{
		
		alllist(req,resp);
	}

}
