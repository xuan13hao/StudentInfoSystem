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
	String url;//�����ӵĵ�ַ
	int pageNo;//��ǰ�ڼ�ҳ
	int totalCount;//�ܹ�����������
	int pageSize; //��ҳ����������
	int startPage;  //�ӵڼ�ҳ��ʼ
	int endPage;   //�ӵڼ�ҳ����
	int totalPage;  //�ܹ�����ҳ
	int indexCount; //��ʾ����ҳ��
	
	String params;//���⴫�ݵĲ���
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
		sb.append("<a href='"+url+"?pageNo=1&"+params+"'>��һҳ</a>&nbsp;&nbsp;");
		if(pageNo>1)
		{
			sb.append("<a href='"+url+"?pageNo="+(pageNo-1)+"&"+params+"'>��һҳ</a>&nbsp;&nbsp;");
		}else
		{
			sb.append("<a href='"+url+"?pageNo=1&"+params+"'>��һҳ</a>&nbsp;&nbsp;");
		}
		
		for(int i=startPage;i<=endPage;i++)
		{
		sb.append("<a href='"+url+"?pageNo="+i+"&"+params+"'>"+i+"</a>&nbsp;");
		}
		
		if(pageNo<totalPage)
		{
			 sb.append("<a href='"+url+"?pageNo="+(pageNo+1)+"&"+params+"'>��һҳ</a>&nbsp;&nbsp;");	
		 
		}else
		{
			sb.append("<a href='"+url+"?pageNo="+totalPage+"&"+params+"'>��һҳ</a>&nbsp;&nbsp;");
		}
		sb.append("<a href='"+url+"?pageNo="+totalPage+"&"+params+"'>���һҳ</a>&nbsp;&nbsp;");
		sb.append("��ǰ<b><font color='#FF0000'>"+pageNo+"</font>/"+totalPage+"</b>ҳ �ܹ�<b>"+totalCount+"</b>��");
		sb.append("</div>");
		
		
		
		
		//<div class='page_div'> <a href='#'>��һҳ</a> <a href='#'>��һҳ</a> <a href='#'>1</a> 2 3 4 5 6 7 8 9 10 11 12 13 14 <a href='#'>��һҳ</a> <a href='#'>���һҳ</a>&nbsp;&nbsp; ��ǰ<b><font color='#FF0000'>12</font>/123</b>ҳ �ܹ�<b>103</b>��</div>
		
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
