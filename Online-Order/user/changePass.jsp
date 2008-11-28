<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="java.util.*" %>
<%@ page import="com.conant.ums.form.*" %>
<%@ page import="com.conant.ums.data.*" %>
<html>
<head>
    <link href="css/master.css" rel="stylesheet" type="text/css">
	<title>修改密码</title>
</head>
<script language="javascript">
//执行查询动作
function changePass(){
		with (document.forms[0]){
		if( user_passwd.value == "" ){
		        	alert( "请输入密码！" );
		            user_passwd.focus();
		            return;
		        }
			else if( user_Dpasswd.value == ""  ){
		        	alert( "请再次输入密码！" );
		            user_Dpasswd.focus();
		            return;
		        }
		        else if( user_passwd.value != user_Dpasswd.value ){
		        	alert( "两次输入密码不同！" );
		            user_Dpasswd.focus();
		            return;
		        }

		submit();
		}
}
//关闭窗口
function winclose(){
	window.close();
}
</script>
<body bgcolor="#ffffff" >
<html:form method="post" action="/ChangePassAction.do" enctype="multipart/form-data">
     <table width="90%" border="0" cellpadding="0" cellspacing="0" align="center" class="MainTable" >
       <tr>
          <td class="orarowheader" colspan="4" height="20">修改密码</td>
      </tr>
      <tr>
          <td colspan="4" height="5">&nbsp;</td>
      </tr>

        <tr align="left" nowrap="nowrap" class="Grid1" >
          <td  width="30%"  height="25">新密码：</td>
          <td align="left" nowrap="nowrap" class="table-text"  width="70%"><input type="password" name="user_passwd" maxlength="25" size="20" class="TextBox" value="" /></td>
        </tr>
        <tr align="left" nowrap="nowrap" class="Grid1" >
          <td  width="30%" height="25">确认密码：</td>
          <td  align="left" nowrap="nowrap" class="table-text" width="136" width="70%"><input type="password" name="user_Dpasswd" maxlength="25" size="20" class="TextBox" value="" /></td>
        </tr>
        <tr align="left" nowrap="nowrap" class="Grid1" >
          <td  height="25">&nbsp;</td>
          <td  height="25">&nbsp;</td>
        </tr>

        <tr align="left" nowrap="nowrap" class="Grid1" >
         <td align="left" >&nbsp; </td>
          <td align="right" >
          <input type="button" name="butok" value="确  定" class="button" onclick="changePass()" />
          <input type="reset" name="butret" value="重  置" class="button"  />
          <input type="button" name="butcancle" value="取  消" class="button" onclick="winclose()" />
          </td>
        </tr>
    </table>
    <input type="hidden" name="op" value="changePass"/>
</html:form>

</body>
</html>
