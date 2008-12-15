<%@ page contentType="text/html; charset=gb2312" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>用户管理子系统</title>
<link rel=stylesheet href="<%=request.getContextPath()%>/user/css/master.css">
<script language="JavaScript" type="">
function checkForm(){
	if (document.LoginForm.userTag.value == ""){
		alert("用户帐号必须输入！");
		document.LoginForm.userTag.focus();
		return false;
	}
	if (document.LoginForm.userPasswd.value == ""){
		alert("密码必须输入！");
		document.LoginForm.userPasswd.focus();
		return false;
	}
}
if(window!=top){
	top.location.replace('login.jsp');
}
</script>
<style type="text/css">
<!--
body {
	margin-top: 90px;
}
-->
</style></head>

<body class="system" leftmargin="0" marginwidth="0" background="images/bg.gif" style="overflow-x:hidden;overflow-y:hidden">
<html:form method="POST" action="/LoginAction.do" onsubmit=" return checkForm(); ">
 <table width="1020" height="388" border="0" cellpadding="4" cellspacing="0">
   <tr>
     <td background="images/user.jpg"><table border=0 cellpadding=0 cellspacing=0 width="80%" valign="absmiddle" align="center">
       <tr height="28%">
         <td height="130">&nbsp;</td>
       </tr>
       <tr>
         <td valign="top"><table width="60%" border="0" align="center" cellpadding="0"  cellspacing="0" class="Font8pt">
             <tr >
               <td width="24%" height="26"  align="right" nowrap class="list_desc">&nbsp;</td>
               <td width="12%" height="26"  align="left" nowrap ><font size="3">
                 <label for="name">用户名：</label>
               </font ></td>
               <td width="14%" height="26" valign=top ><html:text property="userTag" style="width:124px" maxlength="16" onfocus="this.select();" styleClass="TextBox" value=""/> </td>
             </tr>
             <tr>
               <td height="26"  align="right" nowrap  class="list_desc">&nbsp;</td>
               <td height="26" align="left" ><font size="3">密&nbsp;&nbsp;码：</font></td>
               <td height="26" width="14%" align="left" ><html:password property="userPasswd" style="width:124px" maxlength="18" onfocus="this.select();" styleClass="TextBox" value=""/> </td>
               <td width="50%" height="45" align="left"  nowrap> </td>
             </tr>
             <tr >
			  <td height="45" align="left"  nowrap> </td>
              <td height="45" align="left"  nowrap> </td>
			  <td height="45" align="left"  nowrap> <html:submit value="确&nbsp;&nbsp;定" styleClass="button" /></td>
			  <td height="45" align="left"  nowrap> </td>
             </tr>
         </table></td>
       </tr>
       <tr height="50" align="center">
         <td>&nbsp;</td>
       </tr>
       <tr>
         <td>&nbsp;</td>
       </tr>
       <tr>
         <td>&nbsp;</td>
       </tr>
       <tr>
         <td>&nbsp;</td>
       </tr>
     </table></td>
   </tr>
 </table>
 <table width="1020" border="0" cellspacing="0" cellpadding="4">
   <tr height="40">
         <td align="center" style="font-size: 12px;font-family: Arial, Helvetica, sans-serif;"><span class="copyright" style="WIDTH:100%">Copyright 2008 conant Inc.<span class="copyright" style="WIDTH:100%">All rights reserved. </span></span></td>
       </tr>
 </table>
</html:form>
<script language="JavaScript" type="text/javascript">
    document.forms["LoginForm"].elements["userTag"].focus();
</script>
</body>
</html>
