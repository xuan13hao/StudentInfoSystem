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
  <script language="javascript" type="text/javascript" src="../res/js/WdatePicker.js">
  $(document).ready(function() {     
      $('#d1').datepicker();     
  }); 
  
  </script>



<style type="text/css">
.pudiv{ width:120px; height:24px; line-height:24px; float:left;}
</style>




</head>
<body>
<form action="dates" method="post" id="form1">
<input type="hidden" value="add" name="action"/>
<table width="660" border="0" align="center" cellpadding="0" cellspacing="0"  class="redtable">
  <tr>
    <th colspan="3" align="left">&nbsp;�ճ���Ϣ��д</th>
  </tr>
   <tr>
    <td width="200" align="right" valign="middle">���䣺</td>
    <td width="200"><input type="text" name="email" id="email" value="<%=list.getEmail()%>"></td>
    
  </tr>
  <tr>
  <td width="200" align="right" valign="middle">���ڣ�</td>
  <td width="200" align="right" valign="middle">
  <input type="text" id="d1" onFocus="WdatePicker({startDate:'1980-05-01'})" name="date"/>
 
  </td>
   </tr>
   <tr>
    <td width="200" align="right" valign="middle">�ճ���Ϣ��</td>
    <td width="200"><input type="text" name="thing" id="thing"></td>
    <td><div id="emailTip"></div></td>
  </tr>
   
  <tr>
    <td colspan="3" align="center" valign="middle"><input type="submit" name="Submit" value="OK"></td>
    </tr>
</table>
</form>

</body>
</html>