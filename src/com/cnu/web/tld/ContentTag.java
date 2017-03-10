package com.cnu.web.tld;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class ContentTag extends SimpleTagSupport 
{

	private String type;
	private String property;
	private SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Override
	public void doTag() throws JspException, IOException 
	{
		StringBuilder  sb=new StringBuilder();
		if(null!=type&&"ch".equals(type))
		{
			int ch=(Integer)this.getJspContext().getAttribute("ch",PageContext.REQUEST_SCOPE);
			sb.append(ch);
		}else 
		{
			if((null!=type&&null!=property))
			{
				Object obj=this.getJspContext().getAttribute(type,PageContext.REQUEST_SCOPE);
				if(null!=obj)
				{
				  sb.append(getFieldValue(obj));
				}
			}
			
		}/*else if(null!=type&&"contentTxt".equals(type))
		{
			ContentTxt =(ContentTxt)this.getJspContext().getAttribute("contentTxt",PageContext.REQUEST_SCOPE);
			if(null!=content)
			{
			  sb.append(getFieldValue(content));
			}
		}else if(null!=type&&"channel".equals(type))
		{
			
		}else if(null!=type&&"model".equals(type))
		{
			
		}*/
		
		
		
		this.getJspContext().getOut().write(sb.toString());
	}

	 public  String getFieldValue(Object obj)
	 {
		 String re="";
		 @SuppressWarnings("rawtypes")
		Class clazz=obj.getClass(); 
		 try {
			Field field=clazz.getDeclaredField(property);
			Object value=null;
			 if(null!=field)
			 {
				 field.setAccessible(true);
				 value=field.get(obj);
				 if(field.getType()==Date.class||value instanceof Date)
				 {
					 Date date=(Date)value;
					 re=sf.format(date);
				 }else if(null!=value)
				 {
					 re=value.toString();
				 }
				 
			 }
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return re;
	 }
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	
}
