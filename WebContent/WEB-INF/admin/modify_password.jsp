<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
 <%@taglib prefix="red" uri="/cnu-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>修改密码</title>
<style type="text/css">
.redtable{ margin:10px auto; border-collapse:collapse;}
.redtable td{ border:1px #CCCCCC solid; line-height:30px;}
.redtable th{ padding-left:10px; background:#CC0000; color:#FFFFFF; font-weight:bold; line-height:34px;}

.redinput{ width:120px; line-height:26px;}

</style>
<style type="text/css">
 #container{ margin:200px auto; width:500px;}
  #container table td{ line-height:34px; font-size:12px;}
</style>
<script src="res/js/jquery-1.7.1.min.js" language="javascript"></script>
<link type="text/css" rel="stylesheet" href="res/formvalidator/style/validator.css"></link>
<script src="res/formvalidator/formValidator-4.0.1.js" type="text/javascript" charset="UTF-8"></script>
<script src="res/formvalidator/formValidatorRegex.js" type="text/javascript" charset="UTF-8"></script>


</head>
<body>
<form action="space/user" method="post" id="form1">
<input type="hidden" name="action" value="modifypwd"/>
<table width="500" border="0" align="center" cellpadding="0" cellspacing="0" class="redtable">
  <tr>
    <th colspan="3" align="left">修改密码：</th>
  </tr>
  
  <tr>
    <td align="right">新密码：</td>
    <td><input name="pwd" type="password" class="redinput" id="pwd"></td>
    <td><div id="pwdTip"></div></td>
  </tr>
  <tr>
    <td align="right">重复密码：</td>
    <td><input name="repwd" type="password" class="redinput" id="repwd"></td>
     <td><div id="repwdTip"></div></td>
  </tr>
  <tr>
    <td colspan="3" align="center"><input type="submit" id="submm" name="Submit" value="提交">
      &nbsp;&nbsp; 
      <input type="reset" name="Submit2" value="重置"></td>
  </tr>
</table>
</form>
<red:msgdig/>
</body>
</html>