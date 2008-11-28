<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
%>
<html>
<head>
    <link href="<%=request.getContextPath()%>/user/css/master.css" rel="stylesheet" type="text/css">
    <link href="<%=request.getContextPath()%>/user/css/espin.css" rel="stylesheet" type="text/css">
	<title>ЅЗЙ«№ЬАн</title>
</head>
<frameset rows="280,*" cols="*" framespacing="0" frameborder="NO" border="0" name="content">
  <frame src="F110_FTop.jsp" name="Frame1" scrolling="NO" noresize>
  <frame src="F110_FDown.jsp" name="Frame2" scrolling="NO" noresize>
</frameset>
</html>
