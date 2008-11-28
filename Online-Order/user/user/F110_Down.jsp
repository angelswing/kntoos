<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.conant.ums.form.F110_RoleMgtForm" %>
<%
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
%>
<html>
<head>
    <link href="<%=request.getContextPath()%>/user/css/master.css" rel="stylesheet" type="text/css">
	<title>角色管理</title>
</head>
<script type="">
    <%
	String RUflag =request.getParameter("flag");
	if(RUflag!=null && RUflag.equals("1")){
    %>
        parent.parent.Frame1.selectInfo();
    <%
	}
	   if(RUflag!=null && RUflag.equals("2")){
    %>
        parent.parent.Frame1.location.reload();
    <%}%>
    	parent.document.forms[0].save.disabled = false;
</script>
<script type="">
    function addrole(){
        with ( document.forms[0]) {
        if(parent_role.options[ parent_role.selectedIndex ].innerText==" "){
            alert("请选择角色！");
            parent_role.focus();
        }else if(checkrole()){
            alert("该角色已存在，请重新选择角色！");
            parent_role.focus();
        }else if(fatherrole()){
            alert("目标角色不能选择自身！");
            parent_role.focus();
        }else{
            var sValue = parent_role.options[ parent_role.selectedIndex ].value;
            var sText = parent_role.options[ parent_role.selectedIndex ].innerText;
            var oOption = document.createElement("option");
            parentrolelist.options.add(oOption);
            oOption.innerText=sText;
            oOption.value=sValue;
          }
        }
    }

    function checkrole(){
        with ( document.forms[0]) {
    	    var sValue = parent_role.options[ parent_role.selectedIndex ].value;
    	    for(i=0;i<parentrolelist.length;i++){
    	    if(parentrolelist.options[i].value==sValue){
    	        return true;
            }
            }
            return false;
    	}
    }

    function fatherrole(){
        with ( document.forms[0]) {
    	    var sValue = parent_role.options[ parent_role.selectedIndex ].value;
    	    if(role_id.value==sValue){
    	        return true;
            }
            return false;
    	}
    }

    function delrole(){
        var flag=0;
    	with(document.forms[0]){
          if(parentrolelist.length=="0"){
            alert("角色不存在！");
          }else{
            for(i=0;i<parentrolelist.length-1;i++)
            if(parentrolelist.options[i].selected || flag==1){
              parentrolelist.options[i].text=parentrolelist.options[i+1].innerText;
              parentrolelist.options[i].value=parentrolelist.options[i+1].value;
              flag=1;
            }
            parentrolelist.length=parentrolelist.length-1;
          }
        }
    }
</script>

<body onload="">
<html:form method="post" action="/user/F110_RoleMgtAction.do">
    <html:hidden property="role_id"/>
    <html:hidden property="op"/>
    <html:hidden property="forward"/>
	<table width="100%" border="0" cellpadding="2" cellspacing="2" align="left" class="MainTable" >
        <tr align="left" class="Grid1">
            <td align="left" width="10%" height="25">角色名称：</td>
            <td align="left" width="90%">
              <html:text maxlength="11" style="width:249px" styleClass="TextBox" property="role_name" ></html:text><font color="red">&nbsp;&nbsp;*</font>
            </td>
        </tr>
        <tr valign="middle" class="Grid1">
            <td align="left" height="25">角色描述：</td>
            <td align="left">
              <html:textarea property="role_depict" rows="4" cols="38" styleClass="TextBox"></html:textarea>
              <font color="red">&nbsp;*</font>
            </td>
        </tr>
    </table>
</html:form>
</body>
</html>
