<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
    <%@ page import="java.util.*,com.cnu.pojo.*"%>
<%@taglib prefix="red" uri="/cnu-tags" %>
<%
    

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>ͨѶ¼��Ϣ�޸�</title>
<link href="../res/css/admin.css" rel="stylesheet" type="text/css">
<script src="../res/js/jquery-1.7.1.min.js" language="javascript"></script>
<link type="text/css" rel="stylesheet" href="../res/formvalidator/style/validator.css"></link>
<script src="../res/formvalidator/formValidator-4.0.1.js" type="text/javascript" charset="UTF-8"></script>
<script src="../res/formvalidator/formValidatorRegex.js" type="text/javascript" charset="UTF-8"></script>
<script type="text/javascript">
$(document).ready(function(){
	$.formValidator.initConfig({formID:"form1",debug:false,submitOnce:true,
		submitAfterAjaxPrompt : '�����������첽��֤�����Ե�...'
	});
	
	$("#email").formValidator({onShow:"����������",onFocus:"����6-100���ַ�,������ȷ�˲����뿪����",onCorrect:"��ϲ��,�������",defaultValue:"info@redwww.com"}).inputValidator({min:6,max:100,onError:"����������䳤�ȷǷ�,��ȷ��"}).regexValidator({regExp:"^([\\w-.]+)@(([[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}.)|(([\\w-]+.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(]?)$",onError:"������������ʽ����ȷ"});
	$("#pwd").formValidator({onShow:"����������",onFocus:"����1������",onCorrect:"����Ϸ�"}).inputValidator({min:1,empty:{leftEmpty:false,rightEmpty:false,emptyError:"�������߲����пշ���"},onError:"���벻��Ϊ��,��ȷ��"});
});
</script>
<style type="text/css">
.pudiv{ width:120px; height:24px; line-height:24px; float:left;}
</style>
</head>
<body>
<table width="660" border="0" align="center" cellpadding="0" cellspacing="0"   class="redtable">
  <td>
    <th width="50" align="center">���</th>
    <th width="180" align="center">����Ա��</th>
    <th width="100" align="center">����</th>
    <th width="100" align="center">����</th>
    <th align="center">����</th>
  </td>
  <%
  List<Friend> list=(List<Friend>)request.getAttribute("friendsinfo");
  if(null!=list&&list.size()>0)
  {
	  int index=1;
	  for(Friend ma:list)
	  {
  %>
  <form action="friends" method="post">
    
	  <tr>
		<td align="center" valign="middle"><%=index++ %></td>
		<td align="center" valign="middle"><%=ma.getEmail()%></td>
		<td align="center" valign="middle"><%=ma.getQq()%></td>
		<td align="center" valign="middle"><%=ma.getUsername()%></td>
		<td align="center" valign="middle"><%=ma.getEmail()%></td>
		<td align="center" valign="middle"><%=ma.getPlace()%></td>
		<td align="center" valign="middle"><%=ma.getName()%></td>
		<td align="center" valign="middle"><%=ma.getWorkplace()%></td>
		<td align="center" valign="middle">
		
		<input type="hidden" name="mid" value="<%=ma.getId()%>"/>
		</td>
	    <td align="center" valign="middle">
	    <input type="button" value="ɾ��" onclick="window.location='friends?action=delete&mid=<%=ma.getId()%>'"/></td>
	  </tr>
  </form>
  <%  
	  }
  }
  %>
  
</table>

</body>
</html>