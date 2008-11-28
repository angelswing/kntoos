<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.conant.ums.form.*" %>
<html>
<head>
  <title>用户基本信息</title>
</head>
<link href="<%=request.getContextPath()%>/user/css/master.css" rel="stylesheet" type="text/css">

<body bgcolor="#ffffff" >
<html:form method="post" action="/user/F160_UserOnlAction.do" >
    <html:hidden property="user_id"/>
    <html:hidden property="op"/>
    <html:hidden property="forward"/>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" align="left" class="MainTable" >
        <tr valign="middle" class="Grid1">
          <td align="left" width="12%"  height="25">登陆帐号：</td>
          <td align="left" width="38%"><html:text maxlength="20" size="30" styleClass="TextBox" property="user_tag" readonly="true"></html:text></td>
          <td align="left" width="12%" height="25">邮件地址：</td>
          <td align="left" width="38%"><html:text maxlength="25" size="30" styleClass="TextBox" property="email" readonly="true"></html:text></td>
        </tr>
        <tr valign="middle" class="Grid1">
          <td align="left" height="25">用户姓名：</td>
          <td align="left"><html:text maxlength="10" size="30" styleClass="TextBox" property="user_name" readonly="true"></html:text></td>
          <td align="left" height="25">用户地址：</td>
          <td align="left"><html:text maxlength="20" size="30" styleClass="TextBox" property="address" readonly="true"></html:text></td>
        </tr>
        <tr valign="middle" class="Grid1">
          <td align="left" height="25">固定电话：</td>
          <td align="left"><html:text maxlength="15" size="30" styleClass="TextBox" property="home_tel" readonly="true"></html:text></td>
          <td align="left" height="25">移动电话：</td>
          <td align="left"><html:text maxlength="15" size="30" styleClass="TextBox" property="mobile" readonly="true"></html:text></td>
        </tr>
        <tr align="left" class="Grid1">
          <td align="left" width="12%" height="25">所属部门：</td>
          <td align="left" width="38%">
                <html:select property="deptid" style="width:140px" styleClass="SelectButton" disabled="true">
                    <html:option value="">没有所属部门</html:option>
                    <html:optionsCollection name="user_F160_UserOnlForm" property="deptOptions" value="dept_id" label="dept_name"/>
                </html:select>
          </td>
          <td align="left">&nbsp;</td>
          <td align="left">&nbsp;</td>
          </tr>
          <tr align="left" class="Grid1">
            <td align="left" height="25">所属角色：</td>
            <td align="left" colspan="3">
                 <html:select property="userrole" style="width:380px" styleClass="SelectButton" size="5" multiple="true">
                   <html:optionsCollection name="user_F160_UserOnlForm" property="userRoleGroup" value="user_role_id" label="user_role_name"/>
                 </html:select>
          </td>
       </tr>
    </table>
</html:form>
</body>
</html>
