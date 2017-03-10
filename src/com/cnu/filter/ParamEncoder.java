package com.cnu.filter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
//@WebFilter("/*")
public class ParamEncoder implements Filter {

	class ParamHtttpReqeust extends HttpServletRequestWrapper
	{
		public ParamHtttpReqeust(HttpServletRequest request)
		{
			super(request);
		}

		@Override
		public String getParameter(String name)
		{
			String re=null;
			String str=this.getRequest().getParameter(name);
			if(null!=str)
			{
				try
				{
					re=new String(str.getBytes("iso-8859-1"),"gbk");
				} catch (UnsupportedEncodingException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return re;
		}

		@Override
		public String[] getParameterValues(String name)
		{
			String [] strs=this.getRequest().getParameterValues(name);
			if(null!=strs&&strs.length>0)
			{
				for(int i=0;i<strs.length;i++)
				{
					try
					{
						strs[i]=new String(strs[i].getBytes("iso-8859-1"),"GBK");
					} catch (UnsupportedEncodingException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			return strs;
		}
	}
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest  req=(HttpServletRequest)request;
		HttpServletResponse resp=(HttpServletResponse)response;
		resp.setCharacterEncoding("GB18030");
		resp.setContentType("text/html;charset=GB18030");
		chain.doFilter(new ParamHtttpReqeust(req), resp);
		resp.setCharacterEncoding("GB18030");
	}

	
	public void init(FilterConfig fConfig) throws ServletException 
	{
		System.setProperty("file.encoding","utf-8");
	}


	@Override
	public void destroy()
	{
		// TODO Auto-generated method stub
		
	}

}
