<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<%@ page import="java.util.*,com.cnu.pojo.*,com.cnu.page.*"%>
<%@taglib prefix="red" uri="/cnu-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>��Դ����</title>
<link href="../res/css/admin.css" rel="stylesheet" type="text/css">
<script src="../res/js/jquery-1.7.1.min.js" language="javascript"></script>
<script type="text/javascript">
$(function(){
	 /*ȫ��ȫ��*/
	   $("#selectall").click(function()
		{
		   var buva=$(this).val();
		  if(buva=="ȫѡ")
			  {
			     $(".fidss").attr("checked","checked");   
			      $(this).val("ȡ��");
			  }else
				  {
				  $(".fidss").removeAttr("checked");
				  $(this).val("ȫѡ");
				  }
	   });
	 /*����ɾ��*/
	  $("#delbatch").click(function()
	  {
		  var ids=$(".fidss:checked");
			 var url="resource?action=delete";
			 var fid="";
			 ids.each(function(i){
				 fid=fid+"&fid="+ids[i].value;
			 });
			 window.location=url+fid;
	  });
	
});
</script>
</head>
<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center" class="redtable">
  <tr>
    <th width="5%">���</th>
    <th width="15%">����</th>
    <th>·��</th>
    <th width="28%">Ԥ��</th>
    <th width="13%">����</th>
  </tr>
  <%
  PageDiv<Files> pd=(PageDiv<Files>)request.getAttribute("pd");
  if(null!=pd)
  {
	  List<Files> list=pd.getList();
	  if(null!=list&&list.size()>0)
	  {
		  int index=1;
		 for(Files files:list)
		 {
  %>
  <tr>
    <td align="center"><input type="checkbox" name="fid" value="<%=files.getId()%>" class="fidss"/><%=index++ %></td>
    <td align="center"><%=files.getName() %></td>
    <td align="center"><%=files.getPath() %></td>
    <td align="center"><img src="../<%=files.getPath()%>" width="80" height="40"/></td>
    <td align="center"><a href="resource?action=delete&fid=<%=files.getId()%>">ɾ��</a></td>
  </tr>
  <%
		 }
	  }
  }
  %>
  <tr>
    <td colspan="5" align="left">
    <div style="float:left; margin-right:80px;">
    <input type="button" value="ȫѡ" id="selectall"/>
    <input type="button" value="����ɾ��" id="delbatch"/> 
    </div>
 <div class="showPage">
 <red:pager 
      indexCount="10" 
      pageSize="22" 
      pageNo="<%=pd.getPageNo() %>" 
      totalCount="<%=pd.getTotalCount() %>" 
      url="resource"
      params="action=reslist"/>
      </div>
    </td>
  </tr>

</table>
<red:msgdig/>
</body>
</html>