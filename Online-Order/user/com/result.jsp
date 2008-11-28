<%@ page contentType="text/html; charset=GB2312" %>
<%@ page import = "com.conant.ums.util.*"%>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>
<html>
<head>
    <link type="text/css" rel="stylesheet" href="<c:url value="<%=request.getContextPath()%>/user/css/master.css"/>" >
	<title></title>
	<script type="text/javascript" language="JavaScript">
	<!--
	function local(val){
	if(val.lastIndexOf("login.jsp")==-1){
		window.location.replace(val);
	}
	else
		window.top.location.replace(val);

	}
	//-->
	</script>
</head>

<body>
 <%try{
 	String titleCode = (String)request.getAttribute("title");
 	//titleCode = "T0006";
 	String megCode = (String)request.getAttribute("message");
 	//megCode = "W0000";
 	String button1Code = new String();
 	String button2Code = new String();
 	if(request.getAttribute("url1")!=null){
	 	 button1Code = (String)request.getAttribute("button1");
	}
	if(request.getAttribute("url2")!=null){
 		 button2Code = (String)request.getAttribute("button2");
	}
 	//button1Code = "B0001";
 	//button2Code = "B0002";

 	String title = new String();
 	String message = new String();
 	String button1 = new String();
 	String button2 = new String();

    title=BytesConverter.asc2gb(GetMsg.getMsg(titleCode));

    message = BytesConverter.asc2gb(GetMsg.getMsg(megCode));
	if(!button1Code.equals("")){
		button1 = BytesConverter.asc2gb(GetMsg.getMsg(button1Code));
	}
	if(!button2Code.equals("")){
    	button2 = BytesConverter.asc2gb(GetMsg.getMsg(button2Code));
	}
%>
<table width="500" border="0" align="center" cellpadding="8" cellspacing="8" class="Font8pt">
    <tr>
    	<td align="center" nowrap class="Font8pt"><%=title%></td>
    </tr>
	<tr>
        <td align="center" nowrap class="Font8pt" ><%= message  %></td>
	</tr>
    <tr><td align="center" nowrap>
        <button class="buttons" onclick="history.back()">·µ»Ø</button>
<%
	if(!button1.equals("")){
%>
	<button class="buttons" onclick=local("<%=request.getAttribute("url1") %>") ><%=button1 %></button>
<%}%>
<%
	if(!button2.equals("")){
%>
	<button class="buttons" onclick=local("<%= request.getAttribute("url2")%>") ><%= button2 %></button>
<%}%>
    </td></tr>
</table>
<%
}catch(Exception e){out.println(e);}
 %>
</body>
</html>
