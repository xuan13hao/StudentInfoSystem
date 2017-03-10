package com.cnu.page;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
public class PageDiv<T extends Serializable> 
{
    private int pageNo; //当前页
    private int pageSize;//每页条数
    private int totalCount;//总条数
    private int totalPage;//总页数
    private int indexCount=10;//索引数
    private int startPage=1;//开始页
    private int endPage=1;  //结束页
    private List<T> list=new ArrayList<T>();
    public PageDiv(){}
    public PageDiv(int pageNo,int pageSize,int totalCount,List<T> list)
    {
    	  this.pageNo=pageNo;
    	  this.pageSize=pageSize;
    	  this.totalCount=totalCount;
    	  this.list=list;
    	  this.totalPage=(this.totalCount+pageSize-1)/pageSize;
  
    }
 
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
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
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
   
    
}
