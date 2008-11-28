<%@ page contentType="text/html; charset=gb2312"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>main</title>
<style type="text/css">
<!--
@import url("<%=request.getContextPath()%>/resources/js/calender.css");
.STYLE1 {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	color: #333333;
}
.STYLE19 {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
}
body {
	background-image: url(<%=request.getContextPath()%>/resources/images/b.png);
}
-->
</style>
</head>

<body>

  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <th scope="col"><div align="left" class="STYLE1">TCM User Manage &gt;RoleManager</div></th>
    </tr>
  </table>
  <table width="100%" height="37" border="0" cellpadding="0" cellspacing="0">
    <tr>
      <th nowrap="nowrap" bgcolor="#cee3f8" scope="col"><div align="left"><span class="STYLE1"> &nbsp;&nbsp;&nbsp;Search</span>
          <input name="textfield" type="text" size="60" />
          <label></label>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;<img src="<%=request.getContextPath()%>/resources/images/ico_Search.gif" width="14" height="13" /><span class="STYLE19">Search</span></div></th>
    </tr>
  </table>

  <table width="100%" height="26" border="0" cellpadding="0" cellspacing="0">
    <tr>
      <th align="right" background="<%=request.getContextPath()%>/resources/images/ho1.jpg" scope="col"><table width="150" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <th scope="col"><img src="<%=request.getContextPath()%>/resources/images/ico_Delete.gif" width="15" height="13" /></th>
          <th scope="col"><span class="STYLE19">Deletle&nbsp;&nbsp; </span></th>
          <th scope="col"><img src="<%=request.getContextPath()%>/resources/images/ico_Add.gif" width="18" height="13" /></th>
          <th class="STYLE19" scope="col">Add </th>
          <th width="50" class="STYLE19" scope="col">&nbsp;</th>
        </tr>
      </table>   </th>
    </tr>
  </table>
</body>
</html>
