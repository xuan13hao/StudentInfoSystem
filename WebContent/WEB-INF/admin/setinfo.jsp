<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<%@page import="java.util.*" %>
<%@taglib prefix="red" uri="/cnu-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>��վ������Ϣ����</title>
<link href="../res/css/admin.css" rel="stylesheet" type="text/css">
</head>
<body>
<%
    Properties pro=(Properties)request.getAttribute("res");
   
%>
<form action="setinfo" method="post">
<input type="hidden" name="action" value="savexml"> 
<table width="400" border="0" align="center" cellpadding="0" cellspacing="0" class="redtable">
  <tr>
    <th colspan="2">��վ������Ϣ����</th>
  </tr>
      <tr>
    <td width="95" align="right">��վ����</td>
    <td width="305"><input name="name" type="text" id="site" value="<%=pro.getProperty("name")%>"></td>
  </tr>
  <tr>
    <td width="95" align="right">������</td>
    <td width="305"><input name="site" type="text" id="site" value="<%=pro.getProperty("site")%>"></td>
  </tr>
   <tr>
    <td width="95" align="right">��ҳ������</td>
    <td width="305"><input name="pageSize" type="text" id="site" value="<%=pro.getProperty("pageSize")%>"></td>
  </tr>
  <tr>
    <td align="right">�����ţ�</td>
    <td><input name="icp" type="text" id="icp"  value="<%=pro.getProperty("icp")%>"></td>
  </tr>
  <tr>
    <td align="right">����Ա���䣺</td>
    <td><input name="email" type="text" id="email"  value="<%=pro.getProperty("email")%>"></td>
  </tr>
  <tr>
    <td align="right">SMTP:</td>
    <td><input name="smtp" type="text" id="smtp"  value="<%=pro.getProperty("smtp")%>"></td>
  </tr>
  <tr>
    <td align="right">�˿ڣ�</td>
    <td><input name="port" type="text" id="port"  value="<%=pro.getProperty("port")%>"></td>
  </tr>
  <tr>
    <td align="right">���룺</td>
    <td><input name="pwd" type="text" id="pwd"  value="<%=pro.getProperty("pwd")%>"></td>
  </tr>
  <tr>
    <td colspan="2" align="center" valign="middle"><input type="submit" name="Submit" value="����">
      &nbsp;&nbsp; <input type="reset" name="Submit2" value="����"></td>
  </tr>
</table>
</form>
<red:msgdig/>
</body>
</html>