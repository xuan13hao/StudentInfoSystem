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
<title>ע��</title>
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
	
	$("#email").formValidator({onShow:"����������",onFocus:"����6-100���ַ�,������ȷ�˲����뿪����",onCorrect:"��ϲ��,�������",defaultValue:"xuanxuan@qq.com"}).inputValidator({min:6,max:100,onError:"����������䳤�ȷǷ�,��ȷ��"}).regexValidator({regExp:"^([\\w-.]+)@(([[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}.)|(([\\w-]+.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(]?)$",onError:"������������ʽ����ȷ"});
	$("#pwd").formValidator({onShow:"����������",onFocus:"����1������",onCorrect:"����Ϸ�"}).inputValidator({min:1,empty:{leftEmpty:false,rightEmpty:false,emptyError:"�������߲����пշ���"},onError:"���벻��Ϊ��,��ȷ��"});
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
    <th colspan="3" align="left">&nbsp;�û���Ϣ��д</th>
  </tr>
   <tr>
    <td width="200" align="right" valign="middle">�û�����</td>
    <td width="200"><input type="text" name="username" id="username"></td>
    <td><div id="emailTip"></div></td>
  </tr>
  
   <tr>
    <td width="200" align="right" valign="middle">Email��</td>
    <td width="200"><input type="text" name="email" id="email"></td>
    <td><div id="emailTip"></div></td>
  </tr>
   <tr>
    <td width="200" align="right" valign="middle">������</td>
    <td width="200"><input type="text" name="name" id="name"></td>
    <td><div id="emailTip"></div></td>
  </tr>
  <tr>
    <td width="200" align="right" valign="middle">������</td>
    <td width="200"><input type="text" name="nation" id="nation"></td>
    <td><div id="emailTip"></div></td>
  </tr>
  
   <tr>
    <td width="200" align="right" valign="middle">���գ�</td>
    <td width="200"><input type="text" name="birth" id="birth"></td>
    <td><div id="emailTip"></div></td>
  </tr>
  <tr>
    <td align="right" valign="middle">���룺</td>
    <td><input type="text" name="password" id="pwd"></td>
    <td><div id="pwdTip"></div></td>
  </tr>
  <tr>
    <td align="right" valign="middle">�Ա�</td>
    <td>
    <select name="sex">
         <option value="F">Ů</option>
         <option value="M">��</option>
    </select>
    </td>
  </tr>
  
   <tr>
    <td width="200" align="right" valign="middle">������</td>
    <td width="200"><input type="text" name="work" id="work"></td>
    <td><div id="emailTip"></div></td>
  </tr>
  <tr>
    <td width="200" align="right" valign="middle">��ϵ��ʽ��</td>
    <td width="200"><input type="text" name="phone" id="phone"></td>
    <td><div id="emailTip"></div></td>
  </tr>
  <tr>
    <td width="200" align="right" valign="middle">ѧ����</td>
    <td width="200"><input type="text" name="edu" id="edu"></td>
    <td><div id="emailTip"></div></td>
  </tr>
  
  <tr>
    <td width="200" align="right" valign="middle">סַ��</td>
    <td width="200"><input type="text" name="place" id="place"></td>
    <td><div id="emailTip"></div></td>
  </tr>
  <tr>
    <td colspan="3" align="center" valign="middle"><input type="submit" name="Submit" value="ע���û�"></td>
    </tr>
</table>
</form>




</body>
</html>