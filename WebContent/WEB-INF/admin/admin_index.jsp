<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<%@taglib prefix="red" uri="/cnu-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>������Ϣ����ϵͳ</title>
<link href="../res/css/admin.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="../res/treeview/jquery.treeview.css" />
<script src="../res/js/jquery-1.7.1.min.js" language="javascript"></script>
<script src="../res/treeview/jquery.treeview.js" type="text/javascript"></script>
<style type="text/css">
#top,#main{margin:0 auto; width: 100%;}
/*ͷ����*/
#top{ height:68px; background:url("../res/img/admin/toprightback.png") repeat-x;}
#recmsadmintop{ background:url(../res/img/admin/topback.png) no-repeat;}
#top #top_right{position:relative;}
#top #top_top{ width:400px; /*position:absolute; left:300px; top:8px;*/ float: right;} 
#top #top_top li{ float:right;}
#top #top_top li a{ text-decoration:none; color:#FFFFFF; font-weight:bold; margin:0 10px;}
#top #top_top li a:hover{ text-decoration:underline; color:#FFFFFF;}


/*����*/
#top #nav{width:719px;position:absolute;left:277px;top:38px;height:30;}
#top #nav li{ float:left; text-align:center;  width:80px; text-align:center; line-height:30px; background:url(../res/img/admin/lotline.png) no-repeat right top;}
#top #nav li a{ font-size:16px; font-weight:bold; color:#666; display:block; width:70px; text-align:center; height:30px; line-height:30px; margin-left:4px; text-decoration:none; }
#top #nav li a:hover{ font-size:16px; background:url(../res/img/admin/nav_bak.png) no-repeat; color:#FFFFFF; text-decoration:none;}

#right #nav_pointer{ height:30px; line-height:30px; font-weight:bold; border-bottom:1px #CCCCCC solid; padding-left:10px;}


/*�������*/
#main #left{width:180px; height:100%; background:#F9F9F9;}
#main #left dl{ width:170px; margin:0 auto; display:none;}
#main #left dl dt{ border-bottom:1px #CCCCCC solid; height:28px; line-height:28px; padding-left:10px; font-size:16px; font-weight:bold; letter-spacing:5px; margin-top:10px; background:url(../res/img/admin/dot_down.png) no-repeat 150px 13px; cursor:pointer;}

#main #left dl dd{ margin-left:20px;font-size:14px; line-height:30px;}
#main #left dl dd a{ text-decoration:none; color:#333333;}
#main #left dl dd a:hover{ text-decoration:underline;}

#main #middle{width:10px; height:auto; background:#F7F7F7 url(../res/img/admin/dot_left.png) no-repeat 2px 50%; cursor:pointer; }
#main #middle:hover{ background-color:#DBDBDB;}

#main #right{}

.folder a,.file a{color:#000; text-decoration:none;}
.forlder a:hover,.file a:hover{text-decoration: underline;}

</style>
<script language="javascript" type="text/javascript" src="../res/js/WdatePicker.js">
  $(function() {     
      $('#d1').datepicker();     
  });  
  
  </script>

<script language="javascript" type="text/javascript">
var one_title="��ҳ";
var two_title="";
$(function(){
	$("#browser").treeview();
/*����������ͬ��*/
//var mainheigh=$("#main").height();
//$("#left").height(mainheigh);
//$("#right").height(mainheigh);
//$("#middle").height(mainheigh);

/*����Ч��*/
 $("#nav li:first a").css({color: "#FFF",background: "url(../res/img/admin/nav_bak.png) no-repeat"});
		$("#nav li a").mouseover(function(){
		   // $("#nav li:first a").css({color: "#666",background:"none"});
			$("#nav li a").css({color: "#666",background:"none"});
		    $(this).css({color: "#FFF",background: "url(../res/img/admin/nav_bak.png) no-repeat"});
		});
	
	
/*�м�Ч��*/
$("#middle").toggle(
  function () {
      $("#left").hide();
      $("#right").css("width","990px");
      $("#middle").css("background-image","url(../res/img/admin/dot_right.png)");
  },
  function () {
   $("#left").show();
    $("#right").css("width","810px");
	 $("#middle").css("background-image","url(../res/img/admin/dot_left.png)");
  }
);

/*����Ӳ˵��������۵�*/
  
   $("#left dl dt").toggle(
  function () {
     $(this).nextUntil("dt").hide();
	 $(this).css("background-image","url(../res/img/admin/dot_right.png)");
  },
  function () {
     $(this).nextUntil("dt").show();
  $(this).css("background-image","url(../res/img/admin/dot_down.png)");
  }
);
/*ʵ��������������Ӳ˵��Ĺ���*/


$("#top #nav li a").click(function()
{
   one_title=$(this).html();
   two_title="";
   
   $("#left dl").hide();
   var submenuid=$(this).attr("id").split("_")[0]+"_link";
   $("#left #"+submenuid).show();
   initlink();

});

$("#left dl dd a").click(function(){
   two_title=$(this).html();
   initlink();
});
	
});


function initlink()
{
     if(two_title=="")
	 {
     $("#right #nav_pointer").html("��ǰλ�ã�&nbsp;"+one_title);
	 }else
	 {
	 $("#right #nav_pointer").html("��ǰλ�ã�&nbsp;"+one_title+"&nbsp;->&nbsp;"+two_title);
	 }

}
</script>
</head>
<body>
<div id="top">
<table width="100%" height="68" border="0" cellpadding="0" cellspacing="0" id="recmsadmintop">
  <tr>
    <td width="261" rowspan="2"></td>
    <td align="left" valign="top">		  
        <table width="100%" height="32" align="left"  border="0" cellpadding="0" cellspacing="0">
        <tr>
         <td width="300" align="left" style="color:#000;">���,${sessionScope.user.email}</td>
         <td align="right" valign="middle">
		  <ul id="top_top">
		      <li><a href="login?action=logout">�˳�</a></li>
			  <li><a href="#">��վ��ͼ</a></li>	 
		  </ul>
		  </td>
		  </tr>
		 </table>
	</td>

  </tr>
  <tr>
    <td>
          <ul id="nav">
		      <li><a href="#" class="navcurrent" id="home_nav">��ҳ</a></li>
		      <li><a href="#" id="model_nav">�ճ̹���</a></li>
			  <li><a href="#" id="channel_nav">ͨѶĿ¼</a></li>
			  <li><a href="#" id="content_nav">�û���Ϣ</a></li>
			  <li><a href="#" id="res_nav">�ļ���Դ</a></li>
			 
			  <!--
			  <li><a href="#" id="modify_nav">ά��</a></li>
			  <li><a href="#" id="publish_nav">����</a></li>
			  -->   
		  </ul>
    </td>
  </tr>
</table>
</div>
<div id="main">
<table width="100%" height="768" border="0" cellpadding="0" cellspacing="0" >
<tr>
  <td id="left" align="left" valign="top">
<!--��ҳ��Ӧ������-->
	<dl id="home_link" style="display:block;">
	   <dt>��ʼ����</dt>
	   <dd><a  href="../forward?page=admin/welcome.jsp" target="main">��ӭҳ</a></dd>
	   <dd><a  href="../forward?page=admin/modify_password.jsp"  target="main">�޸�����</a></dd>
	  
	     
	   
	    <dt>����</dt>
	   <dd><a  href="../space/user?action=list"  target="main">������Ϣ</a></dd>
	   <dt>����</dt>
	   
	   
	   <dt>���԰����</dt>
	   <dd><a  href="advice"  target="main">���԰�</a></dd>
	   <dt>�˳�����</dt>
	   <dd><a  href="login?action=logout"  target="_parent">ע��</a></dd>
	   <dd><a  href="setinfo"  target="main">����</a></dd>
	</dl>
	<!--ģ��-->
	<dl id="model_link">
	  <dt>�ճ̹���</dt>
	  <dd><a  href="../forward?page=date/add_date.jsp"  target="main">�����ճ�</a></dd>
	  <dd><a  href="../space/date?action=alllist"  target="main">�����ճ�</a></dd>
	  <dd><a  href="../space/date?action=list"  target="main">�鿴�ճ�</a></dd>
	</dl>
	<!--��Ŀ-->
	<dl id="channel_link">
	  <dt>ͨѶ¼����</dt>
	  <dd><a  href="../forward?page=friends/add_friends.jsp"  target="main">���ͨѶ¼</a></dd>
	  <dd><a  href="../space/friends?action=alllist"  target="main">����ͨѶ¼</a></dd>
	  <dd><a  href="../space/friends?action=list"  target="main">�鿴ͨѶ¼</a></dd>
	  
	</dl>
		<!--����-->
	<dl id="content_link">
	
	<dt>��Ŀ�б�</dt>
	<dd>������</dd>
	
	</dl>

	<!--��Դ-->
	<dl id="res_link">
	  <dt>��Դ����</dt>
	  <dd><a  href="resource?action=reslist"  target="main">��Դ����</a></dd>
	  <dd><a  href="resource?action=attachment"  target="main">��������</a></dd>
	</dl>
	
    <!--ģ�����-->
	<dl id="temp_link">
	  <dt>ģ�����</dt>
	  <dd><a href="template?action=execute" target="main">����ģ��</a>
	
	</dl>
	
   </td>
  <td id="middle" width="3"></td>
  <td align="left" valign="top">
     <div id="right"><div id="nav_pointer">��ǰλ��:</div> </div>
	 <iframe src="../forward?page=admin/welcome.jsp" name="main" width="100%" marginwidth="0" height="720" marginheight="0" align="top" frameborder="0"></iframe>
   
  </td>
</tr>

</table>
</div>
  
</body>
</html>