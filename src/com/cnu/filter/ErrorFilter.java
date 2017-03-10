package com.cnu.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
/*@WebFilter(urlPatterns={"/*"},initParams={
		@WebInitParam(name="404",value="error/404.html"),
		@WebInitParam(name="500",value="error/500.html")})*/
public class ErrorFilter implements Filter {

	private String page404="404.html";
	private String page500="500.html";
	@Override
	public void destroy() 
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain chain) throws IOException, ServletException 
	{
		HttpServletRequest req=(HttpServletRequest)arg0;
		HttpServletResponse resp=(HttpServletResponse)arg1;
		
		String path = req.getContextPath();
		String basePath = req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+path+"/";
		ErrorResponse  er=new ErrorResponse(resp,basePath);
		chain.doFilter(req, er);
	}

	@Override
	public void init(FilterConfig fc) throws ServletException {
		this.page404=fc.getInitParameter("404");
		this.page500=fc.getInitParameter("500");
		//ServletContext sc=fc.getServletContext();

	}
	
	class ErrorResponse extends HttpServletResponseWrapper
	{
        private String basepath;
		public ErrorResponse(HttpServletResponse response,String basepath) 
		{
			super(response);
			this.basepath=basepath;
		}

		@Override
		public void sendError(int sc, String msg) throws IOException 
		{
			if(sc==HttpServletResponse.SC_NOT_FOUND)
			{
				this.setStatus(HttpServletResponse.SC_OK);
				
				this.sendRedirect(this.basepath+ErrorFilter.this.page404);
				return;
				
				
			}else if(sc==HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
			{
                this.setStatus(HttpServletResponse.SC_OK);
				
				this.sendRedirect(this.basepath+ErrorFilter.this.page500);
				return;
			}else
			{
			  super.sendError(sc, msg);	
			}
			
		}

		@Override
		public void sendError(int sc) throws IOException {
			if(sc==HttpServletResponse.SC_NOT_FOUND)
			{
				this.setStatus(HttpServletResponse.SC_OK);
				
				this.sendRedirect(basepath+ErrorFilter.this.page404);
				
			}else if(sc==HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
			{
                this.setStatus(HttpServletResponse.SC_OK);
				
				this.sendRedirect(basepath+ErrorFilter.this.page500);
				
			}else
			{
			  super.sendError(sc);	
			}
		}
		
		
		
	}

}
