package com.cnu.tld;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class TempFileTag extends SimpleTagSupport 
{
   private String name;
   private String id;
   private String selected;
   private String temName="index";//index list content
   private String temPath="/WEB-INF/templates";
   
   
@Override
public void doTag() throws JspException, IOException
{
	 StringBuilder sb=new StringBuilder();
	
	 PageContext pc=(PageContext)this.getJspContext();
	 ServletContext sc=pc.getServletContext();
	 String path=sc.getRealPath(temPath);
     File f=new File(path);
     String[] files=f.list(new FilenameFilter()
		{
			@Override
			public boolean accept(File dir, String name)
			{
				if(name.indexOf("_"+temName+".jsp")!=-1)
				return true;
				else
				return false;
			}
		});
     if(null==id||"".equals(id))
     {
    	 id=name+"_id";
     }
	 sb.append("<select name="+name+" id="+id+">");
	 if("index".equals(temName)||"list".equals(temName))
	 {
		 sb.append("<option value=''>无需栏目首页</option>");
	 }
	 for(String tem:files)
	 {
		if(null!=selected&&tem.equals(selected))
		{
	      sb.append("<option value='"+tem+"' selected='selected'>"+tem+"</option>");
		}else
		{
		  sb.append("<option value='"+tem+"'>"+tem+"</option>");
		}
	 }
	 sb.append("</select>");
     
	 this.getJspContext().getOut().write(sb.toString());
}

public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getTemName() {
	return temName;
}
public void setTemName(String temName) {
	this.temName = temName;
}
public String getTemPath() {
	return temPath;
}
public void setTemPath(String temPath) {
	this.temPath = temPath;
}

public String getSelected() {
	return selected;
}

public void setSelected(String selected) {
	this.selected = selected;
}
   
   
}
