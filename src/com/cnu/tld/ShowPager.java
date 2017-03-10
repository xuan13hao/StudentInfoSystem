package com.cnu.tld;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.cnu.page.PageDiv;

public class ShowPager extends SimpleTagSupport 
{
	String url;//表超链接的地址
	int pageNo;//当前第几页
	int totalCount;//总共多少条数据
	int pageSize; //第页多少条数据
	int startPage;  //从第几页开始
	int endPage;   //从第几页结束
	int totalPage;  //总共多少页
	int indexCount; //显示几个页码
	
	String params;//额外传递的参数
	@Override
	public void doTag() throws JspException, IOException 
	{
		StringBuilder  sb=new StringBuilder();
		
		if(indexCount<=0)indexCount=10;
		
		@SuppressWarnings("rawtypes")
		PageDiv pd=(PageDiv)this.getJspContext().getAttribute("pd",PageContext.REQUEST_SCOPE);
		if(null!=pd)
		{
			pageNo=pd.getPageNo();
		    pageSize=pd.getPageSize();
		    totalCount=pd.getTotalCount();
		}
		if(pageNo==0)pageNo=1;
		if(pageSize==0)
		{
			ServletContext sc=((PageContext)(this.getJspContext())).getServletContext();
			Properties p=(Properties)sc.getAttribute("webinfo");
			String re=null;
			if(null!=p)
				re=p.getProperty("pageSize");
			pageSize=Integer.parseInt(re);
		}
		if(null==url||"".equals(url))url="#";
		
		
		totalPage=(totalCount+pageSize-1)/pageSize;
		
		//15,16,17,18,19,20,21,22
		//123456
		if(pageNo-(indexCount/2)<=0)
		{
			startPage=1;
		}else
		{
			startPage=pageNo-(indexCount/2);
		}
		
		if(pageNo+(indexCount/2)>=totalPage)
		{
			endPage=totalPage;
		}else
		{
			if(indexCount%2==0)
			endPage=pageNo+(indexCount/2)-1;
			else
			endPage=pageNo+(indexCount/2);	
		}
		
		
		
		
		
		sb.append("<div class='page_div'>");
		sb.append("<a href='"+url+"?pageNo=1&"+params+"'>第一页</a>&nbsp;&nbsp;");
		if(pageNo>1)
		{
			sb.append("<a href='"+url+"?pageNo="+(pageNo-1)+"&"+params+"'>上一页</a>&nbsp;&nbsp;");
		}else
		{
			sb.append("<a href='"+url+"?pageNo=1&"+params+"'>上一页</a>&nbsp;&nbsp;");
		}
		
		for(int i=startPage;i<=endPage;i++)
		{
		sb.append("<a href='"+url+"?pageNo="+i+"&"+params+"'>"+i+"</a>&nbsp;");
		}
		
		if(pageNo<totalPage)
		{
			 sb.append("<a href='"+url+"?pageNo="+(pageNo+1)+"&"+params+"'>下一页</a>&nbsp;&nbsp;");	
		 
		}else
		{
			sb.append("<a href='"+url+"?pageNo="+totalPage+"&"+params+"'>下一页</a>&nbsp;&nbsp;");
		}
		sb.append("<a href='"+url+"?pageNo="+totalPage+"&"+params+"'>最后一页</a>&nbsp;&nbsp;");
		sb.append("当前<b><font color='#FF0000'>"+pageNo+"</font>/"+totalPage+"</b>页 总共<b>"+totalCount+"</b>条");
		sb.append("</div>");
		
		
		
		
		//<div class='page_div'> <a href='#'>第一页</a> <a href='#'>上一页</a> <a href='#'>1</a> 2 3 4 5 6 7 8 9 10 11 12 13 14 <a href='#'>下一页</a> <a href='#'>最后一页</a>&nbsp;&nbsp; 当前<b><font color='#FF0000'>12</font>/123</b>页 总共<b>103</b>条</div>
		
		getJspContext().getOut().println(sb.toString());
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getIndexCount() {
		return indexCount;
	}
	public void setIndexCount(int indexCount) {
		this.indexCount = indexCount;
	}
	public String getParams() {
		return params;
	}
	public void setParams(String params) {
		this.params = params;
	}

   
}
