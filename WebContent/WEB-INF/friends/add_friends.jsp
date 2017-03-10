<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
    <%@ page import="java.util.*,com.cnu.pojo.*"%>
<%@taglib prefix="red" uri="/cnu-tags" %>
<%
User user=(User)request.getSession().getAttribute("user");
Dates date=new Dates();
request.setAttribute("email", user);
User list=(User)request.getAttribute("email");


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
<script type="text/javascript">
$(document).ready(function(){
	$.formValidator.initConfig({formID:"form1",debug:false,submitOnce:true,
		submitAfterAjaxPrompt : '有数据正在异步验证，请稍等...'
	});
	
	$("#email").formValidator({onShow:"请输入邮箱",onFocus:"邮箱6-100个字符,输入正确了才能离开焦点",onCorrect:"恭喜你,你输对了",defaultValue:"xuanxuan@qq.com"}).inputValidator({min:6,max:100,onError:"你输入的邮箱长度非法,请确认"}).regexValidator({regExp:"^([\\w-.]+)@(([[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}.)|(([\\w-]+.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(]?)$",onError:"你输入的邮箱格式不正确"});
	$("#pwd").formValidator({onShow:"请输入密码",onFocus:"至少1个长度",onCorrect:"密码合法"}).inputValidator({min:1,empty:{leftEmpty:false,rightEmpty:false,emptyError:"密码两边不能有空符号"},onError:"密码不能为空,请确认"});
});
</script>
<style type="text/css">
.pudiv{ width:120px; height:24px; line-height:24px; float:left;}
</style>
</head>
<body>
<form action="friend" method="post" id="form1">
<input type="hidden" value="add" name="action"/>
<table width="660" border="0" align="center" cellpadding="0" cellspacing="0"  class="redtable">
  <tr>
    <th colspan="3" align="left">&nbsp;通讯信息填写</th>
  </tr>
   <tr>
    <td width="200" align="right" valign="middle">用户名：</td>
    <td width="200"><input type="text" name="username" id="username"></td>
    <td><div id="emailTip"></div></td>
  </tr>
  
   <tr>
    <td width="200" align="right" valign="middle">Email：</td>
    <td width="200"><input type="text" name="email" id="email" value="<%=list.getEmail() %>"></td>
    <td><div id="emailTip"></div></td>
  </tr>
   <tr>
    <td width="200" align="right" valign="middle">姓名：</td>
    <td width="200"><input type="text" name="name" id="name"></td>
    <td><div id="emailTip"></div></td>
  </tr>
  <tr>
    <td width="200" align="right" valign="middle">QQ：</td>
    <td width="200"><input type="text" name="qq" id="qq"></td>
    <td><div id="emailTip"></div></td>
  </tr>
  
  
   <tr>
    <td width="200" align="right" valign="middle">工作：</td>
    <td width="200"><input type="text" name="workplace" id="workplace"></td>
    <td><div id="emailTip"></div></td>
  </tr>
  <tr>
    <td width="200" align="right" valign="middle">联系方式：</td>
    <td width="200"><input type="text" name="phone" id="phone"></td>
    <td><div id="emailTip"></div></td>
  </tr>
 
  <tr>
    <td width="200" align="right" valign="middle">住址：</td>
    <td width="200"><input type="text" name="place" id="place"></td>
    <td><div id="emailTip"></div></td>
  </tr>
  <tr>
    <td colspan="3" align="center" valign="middle"><input type="submit" name="Submit" value="OK"></td>
    </tr>
</table>
</form>




</body>
</html>