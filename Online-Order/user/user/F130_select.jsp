<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<html>
<head>
    <link href="<%=request.getContextPath()%>/user/css/master.css" rel="stylesheet" type="text/css">
	<title>�û���ѯ</title>
</head>
<script language="javascript" type="">
    //ִ�в�ѯ����
    function winselect(){
      with(document.forms[0]){
            window.opener.group1.document.forms[0].user_tag.value = user_tag.value;
            window.opener.group1.document.forms[0].user_name.value = user_name.value;
            window.opener.group1.document.forms[0].deptid.value = deptid.value;
        }
        window.opener.group1.document.forms[0].curPageNo.value = "";
        window.opener.group1.document.forms[0].totalPage.value = "";
        window.opener.selectInfo();
        window.close();
    }
    //�رմ���
    function winclose(){
        window.close();
    }

    function resetData(){
        document.forms[0].user_tag.value="";
        document.forms[0].user_name.value="";
        document.forms[0].deptid.value="";
        document.forms[0].user_tag.focus();
    }
</script>

<body>
<html:form method="post" action="/user/F130_UserMgtAction.do" enctype="multipart/form-data">
   <html:hidden property="user_id"/>
      <table width="90%" border="0" cellpadding="0" cellspacing="0" align="center" class="MainTable" >
        <tr>
          <td class="orarowheader" colspan="2" height="20">�û���ѯ</td>
        </tr>
        <tr>
          <td colspan="2" height="5">&nbsp;</td>
        </tr>
        <tr valign="middle" class="Grid1">
          <td align="left" width="30%"  height="25">��½�ʺţ�</td>
          <td align="left" width="70%"><input type="text" name="user_tag" maxlength="30" size="30" class="TextBox" /></td>
        </tr>
        <tr valign="middle" class="Grid1">
          <td align="left" height="25">�û�������</td>
          <td align="left"><input type="text" name="user_name" maxlength="30" size="30" class="TextBox" /></td>
        </tr>
        <tr valign="middle" class="Grid1">
          <td align="left" height="25">�������ţ�</td>
          <td align="left"><html:select property="deptid" style="width:192px" styleClass="SelectButton" onchange="selectrole()" >
                    <html:option value="">&nbsp;</html:option>
                    <html:optionsCollection name="user_F130_UserMgtForm" property="deptOptions" value="dept_id" label="dept_name"/>
                </html:select></td>
        </tr>
        <tr valign="middle" class="Grid1">
          <td align="left" height="25">&nbsp;</td>
          <td align="left" height="25">&nbsp;</td>
        </tr>
        <tr valign="middle" class="Grid1">
          <td align="left" height="25">&nbsp;</td>
          <td align="right">
          <input type="button" name="butok" value="ȷ  ��" class="button" onclick="winselect()" />
          <input type="button" name="butret" value="��  ��" class="button" onclick="resetData()" />
          <input type="button" name="butcancle" value="ȡ  ��" class="button" onclick="winclose()" />
          </td>
        </tr>
    </table>
</html:form>
</body>
</html>
<script type="">
    document.forms[0].user_tag.value=window.opener.group1.document.forms[0].user_tag.value;
    document.forms[0].user_name.value=window.opener.group1.document.forms[0].user_name.value;
</script>
