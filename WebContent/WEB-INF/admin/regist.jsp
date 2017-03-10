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
<form action="regist" method="post" id="form1">
<input type="hidden" value="add" name="action"/>
<table width="660" border="0" align="center" cellpadding="0" cellspacing="0"  class="redtable">
  <tr>
    <th colspan="3" align="left">&nbsp;用户信息填写</th>
  </tr>
   <tr>
    <td width="200" align="right" valign="middle">用户名：</td>
    <td width="200"><input type="text" name="username" id="username"></td>
    <td><div id="emailTip"></div></td>
  </tr>
  
   <tr>
    <td width="200" align="right" valign="middle">Email：</td>
    <td width="200"><input type="text" name="email" id="email"></td>
    <td><div id="emailTip"></div></td>
  </tr>
   <tr>
    <td width="200" align="right" valign="middle">姓名：</td>
    <td width="200"><input type="text" name="name" id="name"></td>
    <td><div id="emailTip"></div></td>
  </tr>
  <tr>
    <td width="200" align="right" valign="middle">国籍：</td>
    <td width="200"><input type="text" name="nation" id="nation"></td>
    <td><div id="emailTip"></div></td>
  </tr>
  
   <tr>
    <td width="200" align="right" valign="middle">生日：</td>
    <td width="200"><input type="text" name="birth" id="birth"></td>
    <td><div id="emailTip"></div></td>
  </tr>
  <tr>
    <td align="right" valign="middle">密码：</td>
    <td><input type="text" name="password" id="pwd"></td>
    <td><div id="pwdTip"></div></td>
  </tr>
  <tr>
    <td align="right" valign="middle">性别：</td>
    <td>
    <select name="sex">
         <option value="F">女</option>
         <option value="M">男</option>
    </select>
    </td>
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
    <td width="200" align="right" valign="middle">学历：</td>
    <td width="200"><input type="text" name="edu" id="edu"></td>
    <td><div id="emailTip"></div></td>
  </tr>
  
  <tr>
    <td width="200" align="right" valign="middle">住址：</td>
    <td width="200"><input type="text" name="place" id="place"></td>
    <td><div id="emailTip"></div></td>
  </tr>
  <tr>
    <td colspan="3" align="center" valign="middle"><input type="submit" name="Submit" value="注册用户"></td>
    </tr>
</table>
</form>




</body>
</html>