<%@ page contentType="text/html; charset=gb2312"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>top</title>
<style type="text/css">
<!--
.STYLE9 {font-family: "宋体"; font-size: 12px; color: #444444; font-weight: bold; }
.STYLE10 {font-size: 12px; color: #444444; font-family: Arial, Helvetica, sans-serif;}
h1,h2,h3,h4,h5,h6 {
	font-family: TOP;
}
-->
</style>
</head>

<body>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <th width="680" height="84" align="center" valign="absmiddle" background="<%=request.getContextPath()%>/resources/images/top.jpg" scope="col"><img src="<%=request.getContextPath()%>/resources/images/name.gif" width="598" height="47" id="Image1" /></th>
    <th width="39%" align="right" background="<%=request.getContextPath()%>/resources/images/top.jpg" scope="col"><p><img src="<%=request.getContextPath()%>/resources/images/LOGO1.png" width="182" height="18" id="Image2" /></p>
    <p>&nbsp;</p></th>
  </tr>
</table>
<table width="100%" height="6" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <th height="6" background="<%=request.getContextPath()%>/resources/images/holine.gif" scope="col"></th>
  </tr>
</table>
<table width="100%" height="26" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <th height="26" background="<%=request.getContextPath()%>/resources/images/ho1.jpg" scope="col"><table width="24%" height="16" border="0" align="right" cellpadding="0" cellspacing="0">
      <tr>
        <th width="73%" valign="top" scope="col"><span class="STYLE9">Welcome <%=(String)request.getSession().getAttribute("user_name")%></span> </th>
        <th width="14%" valign="top" scope="col"><img src="<%=request.getContextPath()%>/resources/images/Exit_C.gif" width="16" height="20" /></th>
        <th width="13%" align="left" valign="absmiddle" class="STYLE10" scope="col">Exit</th>
      </tr>
    </table></th>
  </tr>
</table>
</body>
</html>
