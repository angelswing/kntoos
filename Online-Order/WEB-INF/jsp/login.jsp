<%@ page contentType="text/html; charset=gb2312"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>Online Order Manage System--Login</title>
<link href="resources/style/style_index.css" rel="stylesheet" type="text/css">
<script language="JavaScript">   
function checkSafe(form){

  if(form.userName.value == ""){
    alert("please input user name!");
    form.userName.focus();
    return false;
  }
  if(form.password.value == ""){
    alert("please input password");
    form.password.focus();
    return false;
  }  
  return true;
}
</script>
</head>

<body>
<table width="449" border="0" align="center" cellpadding="0" cellspacing="0">
  <form name="loginForm" action="<%=request.getContextPath()%>/login.ord" method="post" onSubmit="return checkSafe(loginForm)">
  <tr>
    <td width="449" height="155" background="<%=request.getContextPath()%>/resources/images/login_01_.jpg">
    	<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=7,0,19,0" width="213" height="150">
      <param name="movie" value="<%=request.getContextPath()%>/resources/images/dian2.swf" />
      <param name="quality" value="high" />
	     <param name="wmode" value="transparent">
      <embed src="<%=request.getContextPath()%>/resources/images/dian2.swf" quality="high" pluginspage="http://www.macromedia.com/go/getflashplayer" type="application/x-shockwave-flash" width="213" height="150" wmode="transparent"></embed>
    </object>
    </td>
  </tr>
  <tr>
    <td height="100" valign="top" background="<%=request.getContextPath()%>/resources/images/login_02_.jpg"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="26%" height="6"></td>
        <td width="39%"></td>
        <td width="35%"></td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>
          <input name="userName" type="text" size="13" onfocus="this.select();"/></td>
        <td><input name="password" type="password" size="13" onfocus="this.select();"/></td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td height="25">&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td colspan="3" align="center"><input type="submit" name="Submit3" value="Login" class="login_button" />
&nbsp;
<input type="reset" name="reset" value="Reset" class="login_button"/></td>
      </tr>
      <tr>
	   <td>&nbsp;</td>
        <td colspan="2" align="center">&nbsp;</td>
      </tr>
      
    </table></td>
  </tr>
  </form>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td align="center"><span class="copyright" style="WIDTH:100%">Copyright 2008 conant Electronics Inc.<span class="copyright" style="WIDTH:100%">All rights reserved. </span></span></td>
  </tr>
  <tr>
    <td align="center" class="temp">Advised Screen Resolution 1024*768  Browse</td>
  </tr>
</table>
<script language="JavaScript" type="text/javascript">
    document.forms["loginForm"].elements["userName"].focus();
</script>
</body>
</html>
