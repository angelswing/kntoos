<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<html>
<head>
<link href="<%=request.getContextPath()%>/user/css/master.css" rel="stylesheet" type="text/css">
      <link href="<%=request.getContextPath()%>/user/css/espin.css" rel="stylesheet" type="text/css">
</head>

<body bgcolor="#F1F1F1" >
<table width="500" border="0" align="center" cellpadding="1"  cellspacing="1" background="#F1F1F1" >
  <br>
       <br> <br> <br>
    <tr class="Grid1" valign="middle" >
    	<td  width="20%" align="right" nowrap ><bean:write name="InfoForm" property="title"/>£º</td>
        <td  width="20%" align="left" nowrap><bean:write name="InfoForm" property="message"/></td>
	</tr>
      <tr class="Grid1" valign="middle" >
        <td > &nbsp;</td>
	</tr>
     <tr class="Grid1" valign="middle" >
        <td align="center"  colspan="2" nowrap  ><button class="button" onclick="history.back()">·µ»Ø</button></td>
	</tr>
</table>
</body>
</html>
