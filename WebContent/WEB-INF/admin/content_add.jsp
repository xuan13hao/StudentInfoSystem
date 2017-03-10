<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<%@ page import="java.util.*,com.cnu.pojo.*"%>
<%@taglib prefix="red" uri="/cnu-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>添加新文章</title>
<link href="../res/css/admin.css" rel="stylesheet" type="text/css">
<script charset="utf-8" src="../res/kindeditor_4_1_6/kindeditor.js"></script>
<script charset="utf-8" src="../res/kindeditor_4_1_6/lang/zh_CN.js"></script>
<%

  
%>

<script language="javascript">
        var editor;
        KindEditor.ready(function(K) {
                    editor =  K.create('#content', {
                    uploadJson : '../admin/upload_pic',
                    allowFileManager : false,
                    afterUpload : function( url, data, name) {
                        var reId=data.filesId;
                    	var ins=K.query('#filesId');
                    	if(ins.value=="")
                    		{
                    		ins.value=reId+',';
                    		}else
                    		{
                    		 ins.value=ins.value+reId+",";
                    		}
                        
                    }
                    });//end create
                    
                    K('#titleImage').click(function() {
    					editor.loadPlugin('image', function() {
    						editor.plugin.imageDialog({
    							imageUrl : K('#titlePic').val(),
    							clickFn : function(url, title, width, height, border, align) {
    								K('#titlePic').val(url);
    								editor.hideDialog();
    								document.getElementById("titleImage").src=url;
    							}
    						});//end imageDialog
    					});//end loadPlugin
    				});//end click
    		        
                    
              });//end redady
</script>


	<script>
			KindEditor.ready(function(K) {
				var colorpicker;
				K('#titleColor').bind('click', function(e) {
					e.stopPropagation();
					if (colorpicker) {
						colorpicker.remove();
						colorpicker = null;
						return;
					}
					var colorpickerPos = K('#titleColor').pos();
					colorpicker = K.colorpicker({
						x : colorpickerPos.x,
						y : colorpickerPos.y + K('#titleColor').height(),
						z : 19811214,
						selectedColor : 'default',
						noColor : '无颜色',
						click : function(color) {
							K('#titleColor').val(color);
							colorpicker.remove();
							colorpicker = null;
						}
					});
				});
				
				
				K(document).click(function() {
					if (colorpicker) {
						colorpicker.remove();
						colorpicker = null;
					}
				});
			});
		</script>
	<script>
		var mime;
		var oldname;
			KindEditor.ready(function(K) {
				var editorfile = K.editor({
					allowFileManager : false,
					uploadJson : '../admin/upload_files',
					afterUpload : function( url, data, name) {
                    mime=data.mime;
                    oldname=data.oldname;
					var reId=data.filesId;
                    var ins=K.query('#filesId');
                    	if(ins.value=="")
                    		{
                    		ins.value=reId+',';
                    		}else
                    		{
                    		 ins.value=ins.value+reId+",";
                    		}
                        
                    }
				});
				K('#insertfile').click(function() {
					editorfile.loadPlugin('insertfile', function() {
						editorfile.plugin.fileDialog({
							fileUrl : K('#files').val(),
							clickFn : function(url, title) {
								K('#files').val(url);
								editorfile.hideDialog();
								K('#oldname').val(oldname);
								K('#mime').val(mime);
							}
						});
					});
				});
			});
		</script>
		
		
<script src="../res/js/jquery-1.7.1.min.js" language="javascript"></script>
<link type="text/css" rel="stylesheet" href="../res/formvalidator/style/validator.css"></link>
<script src="../res/formvalidator/formValidator-4.0.1.js" type="text/javascript" charset="UTF-8"></script>
<script src="../res/formvalidator/formValidatorRegex.js" type="text/javascript" charset="UTF-8"></script>
<script type="text/javascript">
$(document).ready(function(){
	$.formValidator.initConfig({formID:"form1",alertMessage:true,onError:function(msg){alert(msg)}});
	$("#title").formValidator().inputValidator({min:1,onError:"标题不能为空,请确认"});
	$("#tags").formValidator().inputValidator({min:1,onError:"标签不能为空,请确认"});
	//$("#content").formValidator().inputValidator({min:1,onError:"内容不能为空,请确认"});
});
</script>

</head>
<body>

<form action="content" method="post" id="form1">
<input type="hidden" name="action" value="addSave"/>
<input type="hidden" name="modelId" value="${modelId}"/>
<input type="hidden" name="channelId" value="${channelId}"/>
<input type="hidden" name="managerId" value="${sessionScope.manager.id}"/>
<input type="hidden" name="filesId" id="filesId" value=""/>
<table width="100%" border="0" cellspacing="0" cellpadding="0"  class="redtable">
  
  <tr>
    <td align="right" valign="middle">内容:</td>
    <td align="left" valign="middle"><textarea style="width:730px; height:360px;" id="content" name="content"></textarea></td>
  </tr>
    <tr>
    <td align="center" valign="middle" colspan="2">

<table width="700" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td width="180"  style="border:0;">
	
	<img id="titleImage" width="60" height="60"  src="../res/img/admin/upload-pic.png" style="cursor:pointer; margin:10px; " align="left"/>
	<input type="hidden" name="titlePic" id="titlePic" value=""/>
	标题图：<br/>
	宽： 
	<input name="titleWidth" type="text" id="titleWidth" value="320" size="10" style="width:30px;" />
	<br>
	高：
	<input name="titleHeight" type="text" id="titleHeight" value="120" size="10"  style="width:30px;" />	</td>
    
    <td width="180"  style="border:0;">
	
	<img id="contentImage" width="60" height="60"  src="../res/img/admin/upload-pic.png" style="cursor:pointer; margin:10px; " align="left"/>
	内容图：<br/>
	<input type="hidden" name="contentPic" id="contentPic" value=""/>
	宽： 
	<input name="contentWidth" type="text" id="contentWidth"  size="10" style="width:30px;" />
	<br>
	高：
	<input name="contentHeight" type="text" id="contentHeight"  size="10"  style="width:30px;" />	
	
	</td>
    <td width="180"  style="border:0;">
	
	<img id="typeImage" width="60" height="60"  src="../res/img/admin/upload-pic.png" style="cursor:pointer; margin:10px; " align="left"/>
	<input type="hidden" name="typePic" id="typePic" value=""/>
	类型图:<br/>
	宽： 
	<input name="typeWidth" type="text" id="typeWidth" value="320" size="10" style="width:30px;" />
	<br>
	高：
	<input name="typeHeight" type="text" id="typeHeight" value="120" size="10"  style="width:30px;" />	</td>
    
    <td  style="border:0;" width="200"  align="left">附件：
    <input type="text" id="files" name="path" value="" /> 
    <input type="hidden" id="mime" name="mime"/>
    <input type="hidden" id="oldname" name="filename"/>
    <input type="hidden" id="fiel_desc" name="name"/>
    <input type="button" id="insertfile" value="选择" />
    </td>
  </tr>
</table>
	</td>
  </tr>
  <tr>
    <td colspan="2" align="center" valign="middle"><input type="submit" name="Submit" value="增加文章"></td>
  </tr>
</table>

</form>

<red:msgdig/>
</body>
</html>