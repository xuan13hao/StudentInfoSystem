<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<%@taglib prefix="red" uri="/cnu-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>个人信息管理系统</title>
<link href="../res/css/admin.css" rel="stylesheet" type="text/css">
<style type="text/css">
 #container{ margin:200px auto; width:500px;}
  #container table td{ line-height:34px; font-size:12px;}
</style>
<script src="../res/js/jquery-1.7.1.min.js" language="javascript"></script>
<link type="text/css" rel="stylesheet" href="../res/formvalidator/style/validator.css"></link>
<script src="../res/formvalidator/formValidator-4.0.1.js" type="text/javascript" charset="UTF-8"></script>
<script src="../res/formvalidator/formValidatorRegex.js" type="text/javascript" charset="UTF-8"></script>
<script type="text/javascript">
$(document).ready(function(){
	$.formValidator.initConfig({formID:"form1",debug:false,submitOnce:true,
		onError:function(msg,obj,errorlist){
			$("#errorlist").empty();
			$.map(errorlist,function(msg){
				$("#errorlist").append("<li>" + msg + "</li>")
			});
			alert(msg);
		},
		submitAfterAjaxPrompt : '有数据正在异步验证，请稍等...'
	});
	
	$("#email").formValidator({onShow:"请输入账号",onFocus:"邮箱6-100个字符,输入正确了才能离开焦点",onCorrect:"恭喜你,你输对了",defaultValue:"xuanxuan@qq.com"}).inputValidator({min:6,max:100,onError:"你输入的账号长度非法,请确认"}).regexValidator({regExp:"^([\\w-.]+)@(([[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}.)|(([\\w-]+.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(]?)$",onError:"你输入的账号格式不正确"});
	$("#pwd").formValidator({onShow:"请输入密码",onFocus:"至少1个长度",onCorrect:"密码合法"}).inputValidator({min:1,empty:{leftEmpty:false,rightEmpty:false,emptyError:"密码两边不能有空符号"},onError:"密码不能为空,请确认"});
});
</script>
</head>
<body>

<div id="container">
<form method="post" action="login" id="form1">
<input type="hidden" value="checkLogin" name="action"/> 
<table width="600" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td colspan="3" style="border-bottom:1px #CCCCCC solid; padding:5px 10px;"><img src="../res/img/admin/logo_login.png" width="279" height="34"></td>
  </tr>
  <tr>
    <td width="114" align="right" valign="middle">用户名/Email：</td>
    <td align="left" width="200">
    <input name="email" type="text" id="email" size="30"></td>
	<td><div id="emailTip"></div></td>
  </tr>
  <tr>
    <td align="right" valign="middle">密&nbsp; 码：</td>
    <td align="left"><input name="passwd" type="password" id="pwd" size="30"  title="请输入密码!"></td>
	<td ><div id="pwdTip"></div></td>
  </tr>
  <tr>
    <td align="right" valign="middle">验证码：</td>
    <td align="left" colspan="2"><table width="300" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="76"><input name="randimg" type="text" size="10"></td>
        <td width="85"><img name="randimg" src="../randimg" alt="验证码" style=" cursor:pointer;" onClick="this.src='../randimg?aaa='+Math.random();"></td>
        <td width="139"><u>看不清，点击图片</u></td>
      </tr>
    </table></td>
	
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td align="left"><input type="submit" name="Submit" value="登陆" style=" width:120px;"></td>
	 <td><a href="../space/regist">注册</a></td>
  </tr>
</table>
</form>
</div>
<red:msgdig/>
</body>
</html>