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
<title>注册</title>
<link href="../res/css/admin.css" rel="stylesheet" type="text/css">
<script src="../res/js/jquery-1.7.1.min.js" language="javascript"></script>
<link type="text/css" rel="stylesheet" href="../res/formvalidator/style/validator.css"></link>
<script src="../res/formvalidator/formValidator-4.0.1.js" type="text/javascript" charset="UTF-8"></script>
<script src="../res/formvalidator/formValidatorRegex.js" type="text/javascript" charset="UTF-8"></script>

<style type="text/css">
.pudiv{ width:120px; height:24px; line-height:24px; float:left;}
</style>
</head>
<body>
<form action="friends" method="post" id="form1">
<input type="hidden" value="add" name="action"/>
<table width="660" border="0" align="center" cellpadding="0" cellspacing="0"  class="redtable">
  <tr>
    <th colspan="3" align="left">&nbsp;用户通讯信息填写</th>
  </tr>
   <tr>
    <td width="200" align="right" valign="middle">用户名：</td>
    <td width="200"><input type="text" name="username" value="username"/></td>
    <td><div id="emailTip"></div></td>
  </tr>
  
   <tr>
    <td width="200" align="right" valign="middle">Email：</td>
    <td width="200"><input type="text" name="email" id="email"></td>
    <td><div id="emailTip"></div></td>
  </tr>
  <tr>
    <td width="200" align="right" valign="middle">QQ：</td>
    <td width="200"><input type="text" name="qq" id="qq"></td>
    <td><div id="emailTip"></div></td>
  </tr>
   <tr>
    <td width="200" align="right" valign="middle">姓名：</td>
    <td width="200"><input type="text" name="name" id="name"></td>
    <td><div id="emailTip"></div></td>
  </tr>
   <tr>
    <td width="200" align="right" valign="middle">工作：</td>
    <td width="200"><input type="text" name="work" id="work"></td>
    <td><div id="emailTip"></div></td>
  </tr>
  <tr>
    <td width="200" align="right" valign="middle">联系方式：</td>
    <td width="200"><input type="text" name="phone" id="phone"></td>
    <td><div id="emailTip"></div></td>
  </tr>
  <tr>
    <td width="200" align="right" valign="middle">工作地址：</td>
    <td width="200"><input type="text" name="workpalce" id="workpalce"></td>
    <td><div id="emailTip"></div></td>
  </tr>
  
  <tr>
    <td width="200" align="right" valign="middle">住址：</td>
    <td width="200"><input type="text" name="place" id="place"></td>
    <td><div id="emailTip"></div></td>
  </tr>
  <tr>
    <td colspan="3" align="center" valign="middle"><input type="submit" name="Submit" value="确认"></td>
    </tr>
</table>
</form>



<red:msgdig/>
</body>
</html>