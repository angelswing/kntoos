<%@ page contentType="text/html; charset=GB2312" %>
<%
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
%>
<html>
<head>
    <link href="<%=request.getContextPath()%>/user/css/master.css" rel="stylesheet" type="text/css">
    <link href="<%=request.getContextPath()%>/user/css/espin.css" rel="stylesheet" type="text/css">
	<title>权限管理</title>
</head>
<frameset rows="*" cols="*" framespacing="0" frameborder="NO" border="0" name="content">
  <frame src="F120_FTop.jsp" name="Frame1" scrolling="NO" noresize>
</frameset>
</html>
