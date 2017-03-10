package com.cnu.web.tld;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class WebROOTTag extends SimpleTagSupport 
{

	@Override
	public void doTag() throws JspException, IOException 
	{
		//ServletContext sc=((PageContext)this.getJspContext()).getServletContext();
		HttpServletRequest  request=(HttpServletRequest) ((PageContext)this.getJspContext()).getRequest();
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		this.getJspContext().getOut().write(basePath);
	}

}
