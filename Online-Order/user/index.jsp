<%@ page contentType="text/html; charset=GB2312" %>
<%@ page import="java.util.*,com.conant.ums.data.LoginData,com.conant.ums.util.tree.*"%>
<html>
<head>
<title>用户管理子系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
</head>
<%
    LoginData loginData = (LoginData)session.getAttribute("LoginData");
%>
<script language="JavaScript" type="text/JavaScript">

var isloaded = 0;
var treeopen = new Array();
var treecontent = new Array();
var tmptreecontent = new Array();
var tmptreeopen = new Array();
var treecounter = 0;
var pleaseWait = '请稍候...';
var statusCollection = "no";
</script>
<!-- frames -->
<frameset  rows="55,*" frameborder="no" border="0" framespacing="0"  name="banner">
    <frame name="top" src="user/head.jsp" marginwidth="0" marginheight="0" scrolling="no" frameborder="no">
    <frameset cols="180,*" name="bottom">
        <frame name="content" src="user/left.jsp" marginwidth="0" marginheight="0" scrolling="no" frameborder="no" noresize>
        <frame name="main" src="" marginwidth="0" marginheight="0" scrolling="auto" frameborder="no">
    </frameset>
    <noframes>
<body bgcolor="#FFFFFF" text="#000000">
</body>
    </noframes>
</frameset>
</html>
