<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<html>
<head>
    <link href="<%=request.getContextPath()%>/user/css/master.css" rel="stylesheet" type="text/css">
	<title>���Ų�ѯ</title>
</head>
<script type="">
	function doSelect(){
        with(document.forms[0]){
            window.opener.group1.document.forms[0].dept_name.value = dept_name.value;
        	window.opener.group1.document.forms[0].dept_desc.value = dept_desc.value;
        }
        window.opener.group1.document.forms[0].curPageNo.value = "";
        window.opener.group1.document.forms[0].totalPage.value = "";
        window.opener.selectInfo();
        window.close();
	}
function winclose(){
	window.close();
}
function doReset(){
    with( document.forms[0] ){
        dept_name.value = "";
        dept_desc.value = "";
        parent_dept_id.value = "";
        parent_role.value = "#";
        dept_name.focus();
    }
}
</script>
<body bgcolor="#ffffff" onload="">
<html:form method="post" action="/user/F150_DeptMgtAction.do">
	<table width="90%" border="0" cellpadding="0" cellspacing="0" align="center" class="MainTable" >
        <tr>
          <td class="orarowheader" colspan="2" height="20">���Ų�ѯ</td>
        </tr>
        <tr>
          <td colspan="2" height="5">&nbsp;</td>
        </tr>
        <tr valign="middle" class="Grid1">
            <td align="left" width="40%" height="18">�������ƣ�</td>
            <td align="left" width="60%">
                <html:text property="dept_name" maxlength="16" size="40" styleClass="TextBox"></html:text>
            </td>
        </tr>
        <tr valign="middle" class="Grid1">
            <td align="left" height="25">����������</td>
            <td align="left">
                <html:text property="dept_desc" maxlength="30" size="40" styleClass="TextBox"></html:text>
            </td>
        </tr>
<!--        <tr valign="middle" class="Grid1">
            <td align="left" height="25">�ϼ����ţ�</td>
            <td align="left">
             <html:select  property="parent_dept_id" style="width:252px" styleClass="SelectButton">
                  <html:option value="">&nbsp;</html:option>
                  <html:option value="#">���ϼ�����</html:option>
                  <html:optionsCollection name="user_F150_DeptMgtForm" property="dept_list" value="dept_id" label="dept_name"/>
             </html:select>
            </td>
        </tr>
        <tr valign="middle" class="Grid1">
          <td align="left" height="25">��ɫ�б�</td>
          <td align="left">
              <html:select property="parent_role" style="width:251px" styleClass="SelectButton">
                  <html:option value="#">&nbsp;</html:option>
                  <html:optionsCollection name="user_F150_DeptMgtForm" property="role_list" value="role_id" label="role_name"/>
              </html:select>
          </td>
        </tr>-->
        <tr><td>&nbsp;</td></tr>
        <tr><td>&nbsp;</td></tr>
         <tr valign="middle" class="Grid1">
          <td align="left" height="25">&nbsp;</td>
          <td align="right">
          <input type="button" name="butok" value="ȷ  ��" class="button" onclick="doSelect()" />
          <input type="button" name="butret" value="��  ��" class="button" onclick="doReset()"  />
          <input type="button" name="butcancle" value="ȡ  ��" class="button" onclick="winclose()" />
          </td>
        </tr>
    </table>
</html:form>
</body>
</html>
<script type="">document.forms[0].dept_name.value=window.opener.group1.document.forms[0].dept_name.value</script>
