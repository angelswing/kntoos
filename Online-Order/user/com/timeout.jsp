<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<html>
<head>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/user/css/master.css" >
</head>
<script>
function login() {
    parent.parent.parent.location="../login.jsp";
}
</script>
<body bgcolor="#ffffff">
<table width="500" border="0" align="center" cellpadding="8"  cellspacing="8" bordercolordark="#FFFFFF" class="Font8pt">
    <tr>
    	<td align="center" nowrap class="Font8pt"><bean:write name="InfoForm" property="title"/></td>
    </tr>
	<tr>
        <td align="center" nowrap class="Font8pt" ><bean:write name="InfoForm" property="message"/></td>
	</tr>
    <tr>
        <td align="center" nowrap class="Font8pt" ><button class="buttons" onclick="login()">·µ»Ø</button></td>
	</tr>
</table>
</body>
</html>
