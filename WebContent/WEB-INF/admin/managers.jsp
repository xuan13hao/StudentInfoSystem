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
<title>�û���Ϣ�޸�</title>
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
 
  <%
  User users=(User)request.getAttribute("userinfo");
  if(null!=users)
	  
  %>
  
  <form action="user" method="post">
  
	  <td>
		<tr align="center" valign="middle"><%=users.getUsername() %></tr>
		<tr align="center" valign="middle"><%=users.getEmail() %><input type="hidden" name="email" value="<%=users.getEmail()%>"/></tr>
		<tr align="center" valign="middle"><%=users.getName()%><input type="text" name="name" value="<%=users.getName()%>"/></tr>
		<tr align="center" valign="middle"><%=users.getSex()%>
           <select name="sex">
             <option value="F" selected="selected">F</option>
             <option value="M">M</option>
           </select>
           </tr>
		<tr align="center" valign="middle"><%=users.getBirth()%><input type="text" name="birth" value="<%=users.getBirth()%>"/></tr>
		<tr align="center" valign="middle"><%=users.getNation()%><input type="text" name="nation" value="<%=users.getNation()%>"/></tr>
		<tr align="center" valign="middle"><%=users.getWork()%><input type="text" name="work" value="<%=users.getWork()%>"/></tr>
		<tr align="center" valign="middle"><%=users.getPhone()%><input type="text" name="phone" value="<%=users.getPhone()%>"/></tr>
		<tr align="center" valign="middle"><%=users.getPlace()%><input type="text" name="place" value="<%=users.getPlace()%>"/></tr>
		<tr align="center" valign="middle"><%=users.getEdu()%><input type="text" name="edu" value="<%=users.getEdu()%>"/></tr>
	   <tr align="center" valign="middle"> <input type="hidden" value="update" name="action"/><input name="submit" type="submit" value="�޸�"/> </tr>
	   
	   
	   

	    
	  </td>
</form>
  
  
</table>

</body>
</html>