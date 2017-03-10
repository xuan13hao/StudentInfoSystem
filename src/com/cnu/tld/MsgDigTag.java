package com.cnu.tld;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class MsgDigTag extends SimpleTagSupport
{
    private String title="ÌבÊ¾";
    private String msg;
    private String themes="blitzer";

	@Override
	public void doTag() throws JspException, IOException
	{
		PageContext pc=(PageContext)this.getJspContext();
	    
		String path =pc.getServletContext().getContextPath();
		String basePath = pc.getRequest().getScheme()+"://"+pc.getRequest().getServerName()+":"+pc.getRequest().getServerPort()+path+"/";
		
		//String msg=(String)pc.findAttribute("msg");
		String msg=(String)pc.getAttribute("errors",PageContext.REQUEST_SCOPE);
		if(null!=msg)
		{
		StringBuilder sb=new StringBuilder();
		sb.append("<link type='text/css' href='"+basePath+"res/css/"+themes+"/jquery-ui-1.9.2.custom.min.css' rel='stylesheet' />");
		sb.append("<script src='"+basePath+"res/js/jquery-1.8.3.js'></script>");
		sb.append("<script src='"+basePath+"res/js/jquery-ui-1.9.2.custom.min.js'></script>");
		sb.append("<script>");
		sb.append("$(document).ready(function() {");
		
		sb.append("$('#dialog-message').dialog({");
		sb.append("modal: true,");
		sb.append("buttons: {");
		sb.append("Ok: function(){");
		sb.append("$(this).dialog('close');");
		sb.append("}");
		sb.append("}");
		sb.append("});");
		sb.append("});");
		sb.append("</script>");
		sb.append("<div id='dialog-message' title='"+title+"'>");
		sb.append("<p>");
		sb.append("<span class='ui-icon ui-icon-circle-check' style='float:left; margin:0 7px 50px 0;'></span>");
		sb.append(msg);
		sb.append("</p>");
		//sb.append("<p>");
		//sb.append(msg);
		//sb.append("</p>");
		sb.append("</div>");
        this.getJspContext().getOut().write(sb.toString());
		}

	}
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public String getMsg()
	{
		return msg;
	}
	public void setMsg(String msg)
	{
		this.msg = msg;
	}
	public String getThemes()
	{
		return themes;
	}
	public void setThemes(String themes)
	{
		this.themes = themes;
	}
	
	

}
