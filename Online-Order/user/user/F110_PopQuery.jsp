<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<html>
<head>
    <link href="<%=request.getContextPath()%>/user/css/master.css" rel="stylesheet" type="text/css">
	<title>角色查询</title>
</head>
<script language="javascript" type="">
	function doSelect(){
        with(document.forms[0]){
            window.opener.group1.document.forms[0].role_name.value = role_name.value;
            window.opener.group1.document.forms[0].role_depict.value = role_depict.value;
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
        role_name.value = "";
        role_depict.value = "";
        parent_role.value = "";
        role_name.focus();
    }
}
</script>

<body onload="">
<html:form method="post" action="/user/F110_RoleMgtAction.do" enctype="multipart/form-data">
	<table width="90%" border="0" cellpadding="0" cellspacing="0" align="center" class="MainTable" >
        <tr>
          <td class="orarowheader" colspan="2" height="20">角色查询</td>
        </tr>
        <tr>
          <td colspan="2" height="5">&nbsp;</td>
        </tr>
        <tr valign="middle" class="Grid1">
            <td align="left" width="40%" height="18">角色名称：</td>
            <td align="left" width="60%">
               <html:text property="role_name" maxlength="11" size="40" styleClass="TextBox" ></html:text>
            </td>
        </tr>
        <tr valign="middle" class="Grid1">
          <td align="left" height="25">角色描述：</td>
          <td align="left">
             <html:text property="role_depict" maxlength="30" size="40" styleClass="TextBox"></html:text>
          </td>
        </tr>
<!--         <tr valign="middle" class="Grid1">
           <td align="left" height="25">父角色：</td>
           <td align="left">
               <html:select property="parent_role" style="width:252px" styleClass="SelectButton">
                   <html:option value="##">&nbsp;</html:option>
                   <html:option value="#">无上级角色</html:option>
                   <html:optionsCollection name="user_F110_RoleMgtForm" property="role_list" value="role_id" label="role_name"/>
               </html:select>
           </td>
         </tr>-->
         <tr><td>&nbsp;</td></tr>
         <tr><td>&nbsp;</td></tr>
         <tr valign="middle" class="Grid1">
          <td align="left" height="25">&nbsp;</td>
          <td align="right">
          <input type="button" name="butok" value="确  定" class="button" onclick="doSelect()" />
          <input type="button" name="butret" value="重  置" class="button" onclick="doReset()"  />
          <input type="button" name="butcancle" value="取  消" class="button" onclick="winclose()" />
          </td>
        </tr>
    </table>
</html:form>
</body>
</html>
