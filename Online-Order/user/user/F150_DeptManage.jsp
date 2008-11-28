<%@ page contentType="text/html; charset=GB2312" %>
<%
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
%>
<html>
<head>
    <link href="<%=request.getContextPath()%>/user/css/master.css" rel="stylesheet" type="text/css">
	<title>新增部门</title>
</head>
<frameset rows="280,*" cols="*" framespacing="0" frameborder="NO" border="0" name="content">
  <frame src="F150_FTop.jsp" name="Frame1" scrolling="NO" noresize>
  <frame src="F150_FDown.jsp" name="Frame2" scrolling="NO" noresize>
</frameset>
</html>
