<%@ page contentType="text/html; charset=gb2312"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>Online Order Management System</title>
</head>

<frameset rows="*,43" cols="*" framespacing="0" frameborder="no" border="0">
  <frameset rows="118,*" cols="*" frameborder="no" border="0" framespacing="0">
		<frame src="<%=request.getContextPath()%>/welcome.ord?targetURL=top" name="topFrame" scrolling="No" noresize="noresize" marginwidth="10" marginheight="0" id="topFrame" title="topFrame" />
		<frameset id="frame" rows="*" cols="225,15,*" framespacing="0" frameborder="no" border="0">
			<frame src="<%=request.getContextPath()%>/menu.ord?targetURL=menu" name="menuFrame" scrolling="No" noresize="noresize" marginwidth="10" marginheight="0" id="menuFrame" title="menuFrame" />
			<frame src="<%=request.getContextPath()%>/welcome.ord?targetURL=bar" name="barFrame" noresize scrolling="No"/>
			<frame src="<%=request.getContextPath()%>/welcome.html" name="mainFrame" marginwidth="1" marginheight="0" id="mainFrame" title="mainFrame" />
		</frameset>
  </frameset>
  <frame src="<%=request.getContextPath()%>/welcome.ord?targetURL=bottom" name="bottomFrame" scrolling="No" noresize="noresize" marginwidth="10" marginheight="0" id="bottomFrame" title="bottomFrame" />
</frameset>
<noframes><body>
</body>
</noframes></html>
