<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.conant.ums.form.*" %>
<html>
<head>
  <title>�û�������Ϣ</title>
</head>
<link href="<%=request.getContextPath()%>/user/css/master.css" rel="stylesheet" type="text/css">

<body bgcolor="#ffffff" >
<html:form method="post" action="/user/F160_UserOnlAction.do" >
    <html:hidden property="user_id"/>
    <html:hidden property="op"/>
    <html:hidden property="forward"/>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" align="left" class="MainTable" >
        <tr valign="middle" class="Grid1">
          <td align="left" width="12%"  height="25">��½�ʺţ�</td>
          <td align="left" width="38%"><html:text maxlength="20" size="30" styleClass="TextBox" property="user_tag" readonly="true"></html:text></td>
          <td align="left" width="12%" height="25">�ʼ���ַ��</td>
          <td align="left" width="38%"><html:text maxlength="25" size="30" styleClass="TextBox" property="email" readonly="true"></html:text></td>
        </tr>
        <tr valign="middle" class="Grid1">
          <td align="left" height="25">�û�������</td>
          <td align="left"><html:text maxlength="10" size="30" styleClass="TextBox" property="user_name" readonly="true"></html:text></td>
          <td align="left" height="25">�û���ַ��</td>
          <td align="left"><html:text maxlength="20" size="30" styleClass="TextBox" property="address" readonly="true"></html:text></td>
        </tr>
        <tr valign="middle" class="Grid1">
          <td align="left" height="25">�̶��绰��</td>
          <td align="left"><html:text maxlength="15" size="30" styleClass="TextBox" property="home_tel" readonly="true"></html:text></td>
          <td align="left" height="25">�ƶ��绰��</td>
          <td align="left"><html:text maxlength="15" size="30" styleClass="TextBox" property="mobile" readonly="true"></html:text></td>
        </tr>
        <tr align="left" class="Grid1">
          <td align="left" width="12%" height="25">�������ţ�</td>
          <td align="left" width="38%">
                <html:select property="deptid" style="width:140px" styleClass="SelectButton" disabled="true">
                    <html:option value="">û����������</html:option>
                    <html:optionsCollection name="user_F160_UserOnlForm" property="deptOptions" value="dept_id" label="dept_name"/>
                </html:select>
          </td>
          <td align="left">&nbsp;</td>
          <td align="left">&nbsp;</td>
          </tr>
          <tr align="left" class="Grid1">
            <td align="left" height="25">������ɫ��</td>
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
