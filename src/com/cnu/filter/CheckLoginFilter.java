package com.cnu.filter;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cnu.pojo.User;
@WebFilter("/admin/*")
public class CheckLoginFilter implements Filter 
{

	@Override
	public void destroy() {}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException
		{
	           HttpServletRequest   req=(HttpServletRequest)arg0;
	           HttpServletResponse  resp=(HttpServletResponse)arg1;
	           User manager=(User)req.getSession().getAttribute("user");
	           String requri=req.getRequestURI();
	         //  String querystr=req.getQueryString();

	           if(requri.endsWith("login")||null!=manager)
	           {
	           arg2.doFilter(req, resp);
	           }else
	           {
	        	   resp.sendRedirect("login");
	           }
	           
		}

	@Override
	public void init(FilterConfig arg0) throws ServletException {}

}
