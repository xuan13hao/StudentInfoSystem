<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<%@page import="java.io.*,java.util.*,java.net.*" %>
<%!
String tblWidth = "700";
String tblAlign = "center";
String strOS =System.getProperty("os.name");
private String getIp()
{
   String strTmp="";
   try
   {
   strTmp =InetAddress.getLocalHost().getHostAddress();
   return strTmp;
   }
   catch(Exception e)
   {
   return strTmp;
   }
}
private String getSystemEnv() throws Exception
{
String OS = System.getProperty("os.name").toLowerCase();
StringBuffer sb=new StringBuffer("");
Process p=null;
if(OS.indexOf("windows")>-1)
{
   p=Runtime.getRuntime().exec("cmd /c set");
}
else
{
   p=Runtime.getRuntime().exec("/bin/sh -c set");
}
BufferedReader br=new BufferedReader(new InputStreamReader(p.getInputStream()));
String line;
while((line=br.readLine())!=null)
{
   sb.append("<li>"+line+"</li>");  
}
return sb.toString();
}
private String getDrivers()
{
StringBuffer sb=new StringBuffer("");
File roots[]=File.listRoots();
for(int i=0;i<roots.length;i++)
{
   sb.append(roots[i]+" ");
}
return sb.toString();
}
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>jsp̽��</title>
<style type="text/css">
<!--
body,td,th {
font-size: 9pt;
}
body {
margin-left: 0px;
margin-top: 0px;
margin-right: 0px;
margin-bottom: 0px;
}
a:link {
text-decoration: none;
}
a:visited {
text-decoration: none;
}
a:hover {
text-decoration: none;
}
a:active {
text-decoration: none;
}
-->
</style>
</head>

<body>
<br>
<table width="100%" align="center"   border="0" cellspacing="0" cellpadding="1">
   <tr>
     <td colspan="2" height="22" bgcolor="#E0E0E0"><div align="center"><strong>��ӭ������Ϣ����ϵͳ</strong></div></td>
   </tr>
      <tr>
     <td height="23" width="20%" >&nbsp;����������</td>
<td>&nbsp;<%= request.getServerName() %>
</td>
   </tr>
   <tr>
     <td height="23" width="20%" >&nbsp;����/IP </td>
<td>&nbsp;<%= request.getServerName() %>
<%=getIp()%>
</td>
   </tr>
   <tr>
     <td height="23">&nbsp;�������˿� </td>
<td>&nbsp;<%= request.getServerPort() %> </td>
   </tr>
    <tr>
     <td height="23">&nbsp;�ͻ��˶˿� </td>
<td>&nbsp;<%= request.getRemotePort() %> </td>
   </tr>
    <tr>
     <td height="23">&nbsp;�ͻ���IP </td>
<td>&nbsp;<%= request.getRemoteAddr() %> </td>
   </tr>
   <tr>
     <td height="23">&nbsp;Web ������ </td>
<td>&nbsp;<%= application.getServerInfo() %> </td>
   </tr>
  
   <tr>
     <td height="23">&nbsp;����ϵͳ </td>
<td>&nbsp;<%= strOS+" "+System.getProperty("sun.os.patch.level")+" Ver:"+System.getProperty("os.version") %> </td>
   </tr>
   <tr>
     <td height="23">&nbsp;������ʱ�� </td>
<td>&nbsp;<%=new Date().toLocaleString()%> </td>
   </tr>
   <tr>
     <td height="23" width="20%" >&nbsp;CPU ��Ϣ </td>
<td>&nbsp;<%= System.getProperty("os.arch")%>
</td>
   </tr>
   <tr>
     <td height="23" width="20%" >&nbsp;���̷��� </td>
<td>&nbsp;<%= getDrivers()%>
</td>
   </tr>
   <tr>
     <td height="23" width="20%" >&nbsp;�û���ǰ����Ŀ¼ </td>
<td>&nbsp;<%= System.getProperty("user.dir")%>
</td>
   </tr>
   <tr>
     <td height="23">&nbsp;���ļ�·�� </td>
<td>&nbsp;<%= application.getRealPath(request.getRequestURI()) %> </td>
   </tr>
</table>

<br>
<table width="<%=tblWidth%>" align="center"   border="0" cellspacing="0" cellpadding="1">
   <tr>
     <td colspan="2" height="22" bgcolor="#E0E0E0"><span> �����Ϣ</span></span> </td>
   </tr>
   <tr>
     <td height="23" width="20%" ><span> �汾</span> </td>
<td>&nbsp;<%= System.getProperty("java.version") %>
</td>
   </tr>
   <tr>
     <td height="23" width="20%" >&nbsp;Servlet �汾 </td>
<td>&nbsp;<%= application.getMajorVersion()+"."+application.getMinorVersion() %>
</td>
   </tr>
   <tr>
     <td height="23" width="20%" >&nbsp;JDK ��װ·�� </td>
<td>&nbsp;<%= System.getProperty("java.home")%>
</td>
   </tr>
   <tr>
     <td height="23" width="20%" >&nbsp;���� </td>
<td>&nbsp;<%= System.getProperty("file.encoding")%>
</td>
   </tr>
    <tr>
     <td height="23" width="20%" >&nbsp;JAVA��·�� </td>
<td>&nbsp;<%= System.getProperty("java.class.path")%>
</td>
   </tr>
</table>
<br>
<table width="<%=tblWidth%>" align="center"   border="0" cellspacing="0" cellpadding="1">
   <tr>
     <td colspan="2" height="22" bgcolor="#E0E0E0">&nbsp;�������������� &nbsp;</td>
   </tr>
   <tr>
<td colspan="2"><ul><%= getSystemEnv() %></ul>
</td>
   </tr>
</table>
<br>

</body>
</html>