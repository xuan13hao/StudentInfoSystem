<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<%@taglib prefix="red" uri="/cnu-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>������Ϣ����ϵͳ</title>
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
		submitAfterAjaxPrompt : '�����������첽��֤�����Ե�...'
	});
	
	$("#email").formValidator({onShow:"�������˺�",onFocus:"����6-100���ַ�,������ȷ�˲����뿪����",onCorrect:"��ϲ��,�������",defaultValue:"xuanxuan@qq.com"}).inputValidator({min:6,max:100,onError:"��������˺ų��ȷǷ�,��ȷ��"}).regexValidator({regExp:"^([\\w-.]+)@(([[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}.)|(([\\w-]+.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(]?)$",onError:"��������˺Ÿ�ʽ����ȷ"});
	$("#pwd").formValidator({onShow:"����������",onFocus:"����1������",onCorrect:"����Ϸ�"}).inputValidator({min:1,empty:{leftEmpty:false,rightEmpty:false,emptyError:"�������߲����пշ���"},onError:"���벻��Ϊ��,��ȷ��"});
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
    <td width="114" align="right" valign="middle">�û���/Email��</td>
    <td align="left" width="200">
    <input name="email" type="text" id="email" size="30"></td>
	<td><div id="emailTip"></div></td>
  </tr>
  <tr>
    <td align="right" valign="middle">��&nbsp; �룺</td>
    <td align="left"><input name="passwd" type="password" id="pwd" size="30"  title="����������!"></td>
	<td ><div id="pwdTip"></div></td>
  </tr>
  <tr>
    <td align="right" valign="middle">��֤�룺</td>
    <td align="left" colspan="2"><table width="300" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="76"><input name="randimg" type="text" size="10"></td>
        <td width="85"><img name="randimg" src="../randimg" alt="��֤��" style=" cursor:pointer;" onClick="this.src='../randimg?aaa='+Math.random();"></td>
        <td width="139"><u>�����壬���ͼƬ</u></td>
      </tr>
    </table></td>
	
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td align="left"><input type="submit" name="Submit" value="��½" style=" width:120px;"></td>
	 <td><a href="../space/regist">ע��</a></td>
  </tr>
</table>
</form>
</div>
<red:msgdig/>
</body>
</html>