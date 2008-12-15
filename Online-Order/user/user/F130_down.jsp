<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.conant.ums.form.*" %>
<html>
<head>
  <title>用户基本信息</title>
</head>
<link href="<%=request.getContextPath()%>/user/css/master.css" rel="stylesheet" type="text/css">
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
<script language="javascript" type="">
    function selectrole(){
      if(document.forms[0].user_tag.readOnly == false){
        parent.parent.Frame2.document.all.group1.src = "<%=request.getContextPath()%>/user/F130_UserMgtAction.do?forward=Insertdown&op=selectchangerole" + getPrm();
      }else{
        parent.parent.Frame2.document.all.group1.src = "<%=request.getContextPath()%>/user/F130_UserMgtAction.do?forward=Qur_down&op=selectchangerole" + getPrm();
      }
    }

    function getPrm(){
        var prm = "&user_id=" + document.forms[0].user_id.value
                + "&user_tag=" + document.forms[0].user_tag.value
                + "&user_passwd=" + document.forms[0].user_passwd.value
                + "&user_passwd_t=" + document.forms[0].user_passwd_t.value
                + "&user_name=" + document.forms[0].user_name.value
                + "&deptid=" +document.forms[0].deptid.value
                + "&email=" + document.forms[0].email.value
                + "&address=" + document.forms[0].address.value
                + "&home_tel=" + document.forms[0].home_tel.value
                + "&mobile=" + document.forms[0].mobile.value;
        return prm;
    }

    function addrole(){
        with ( document.forms[0]) {
        if(role_id.options[ role_id.selectedIndex ].innerText==" "){
            alert("请选择角色！");
            role_id.focus();
        }else if(checkrole()){
            alert("该角色已存在，请重新选择角色！");
            role_id.focus();
        }else{
            var sValue = role_id.options[ role_id.selectedIndex ].value;
            var sText = role_id.options[ role_id.selectedIndex ].innerText;
            var oOption = document.createElement("option");
            userrole.options.add(oOption);
            oOption.innerText=sText;
            oOption.value=sValue;
        }
      }
    }

    function checkrole(){
        with ( document.forms[0]) {
    	    var sValue = role_id.options[ role_id.selectedIndex ].value;
    	    for(i=0;i<userrole.length;i++){
    	    if(userrole.options[i].value==sValue){
    	        return true;
            }
            }
            return false;
    	}
    }

    function delrole(){
        var flag=0;
    	with(document.forms[0]){
          if(userrole.length=="0"){
            alert("角色不存在！");
          }else{
            for(i=0;i<userrole.length-1;i++)
            if(userrole.options[i].selected || flag==1){
              userrole.options[i].text=userrole.options[i+1].innerText;
              userrole.options[i].value=userrole.options[i+1].value;
              flag=1;
            }
            userrole.length=userrole.length-1;
          }
        }
    }
</script>
<body>
<html:form method="post" action="/user/F130_UserMgtAction.do" >
    <html:hidden property="user_id"/>
    <html:hidden property="op"/>
    <html:hidden property="forward"/>
  <table width="100%" border="0" cellpadding="0" cellspacing="0" align="center" class="MainTable" >
    <tr valign="top" class="Grid1">
      <table width="100%" border="0" cellpadding="0" cellspacing="0" align="center" class="MainTable" >
        <tr valign="absmiddle" class="Grid1">
          <td align="left" width="12%"  height="25">登陆帐号：</td>
          <td align="left" width="35%"><html:text maxlength="15" size="30" styleClass="TextBox" property="user_tag" readonly="true" onkeyup="value=value.replace(/[\W]/g,'')"></html:text><font color="red">&nbsp;*</font></td>
          <td align="left" width="12%" height="25">邮件地址：</td>
          <td align="left" width="35%"><html:text maxlength="25" size="30" styleClass="TextBox" property="email"></html:text></td>
        </tr>
        <tr valign="absmiddle" class="Grid1">
          <td align="left" height="25">密  码：</td>
          <td align="left"><html:password maxlength="15" size="30" styleClass="TextBox" property="user_passwd" onkeyup="value=value.replace(/[^\a-zA-Z0-9]/g,'')"></html:password><font color="red">&nbsp;*</font></td>
          <td align="left" height="25">用户地址：</td>
          <td align="left"><html:text maxlength="40" size="30" styleClass="TextBox" property="address"></html:text></td>
        </tr>
        <tr valign="absmiddle" class="Grid1">
          <td align="left" height="25">再次输入密码：</td>
          <td align="left"><html:password maxlength="15" size="30" styleClass="TextBox" property="user_passwd_t" onkeyup="value=value.replace(/[^\a-zA-Z0-9]/g,'')"></html:password><font color="red">&nbsp;*</font></td>
          <td align="left" height="25">固定电话：</td>
          <td align="left"><html:text maxlength="15" size="30" styleClass="TextBox" property="home_tel"></html:text></td>
        </tr>
        <tr valign="absmiddle" class="Grid1">
          <td align="left" height="25">用户姓名：</td>
          <td align="left"><html:text maxlength="10" size="30" styleClass="TextBox" property="user_name" ></html:text><font color="red">&nbsp;*</font></td>
          <td align="left" height="25">移动电话：</td>
          <td align="left"><html:text maxlength="15" size="30" styleClass="TextBox" property="mobile"></html:text></td>
        </tr>
      </table>
    </tr>
    <tr align="left" class="Grid1">
      <table width="64%" border="0" cellpadding="0" cellspacing="0" align="left" class="MainTable" >
        <tr align="left" class="Grid1">
          <td align="left" width="23%" height="25">所属部门：</td>
          <td align="left" width="30%">
                <html:select property="deptid" style="width:140px" styleClass="SelectButton" onchange="selectrole()" >
                    <html:option value="">没有所属部门</html:option>
                    <html:optionsCollection name="user_F130_UserMgtForm" property="deptOptions" value="dept_id" label="dept_name"/>
                </html:select><font color="red">&nbsp;*</font>
          </td>
          <td align="left" width="15%">&nbsp;可选角色：</td>
          <td align="left" width="25%">
                <html:select property="role_id" style="width:140px" styleClass="SelectButton" >
                    <html:option value="">&nbsp;</html:option>
                    <html:optionsCollection name="user_F130_UserMgtForm" property="deptRoleGroup" value="role_id" label="role_name"/>
                </html:select>
          </td>
          <td align="left" width="8%">&nbsp;</td>
          </tr>
          <tr>
           <td align="left">&nbsp;</td>
          </tr>
          <tr align="left" class="Grid1">
            <td align="left" height="25">所属角色：</td>
            <td align="left" colspan="3">
                 <html:select property="userrole" style="width:380px" styleClass="SelectButton" size="5" multiple="true">
                   <html:optionsCollection name="user_F130_UserMgtForm" property="userRoleGroup" value="user_role_id" label="user_role_name"/>
                 </html:select>
          </td>
          <td align="left" valign="top">
          &nbsp;<img src="<%=request.getContextPath()%>/user/images/Insert_icon.gif" onclick="addrole()" alt="" style="cursor:hand"><br><br>
          &nbsp;<img src="<%=request.getContextPath()%>/user/images/Delete_icon.gif" onclick="delrole()" alt="" style="cursor:hand">
          </td>
          </tr>
          <tr><td>&nbsp;</td></tr>
          <tr align="left" class="Grid1">
          	<td align="center" colspan="4"><font color="#990000">修改提示：修改用户信息时，如果不输入密码，则表示保持原密码不变！</font></td>
          </tr>
        </table>
    </tr>
  </table>
</html:form>
</body>
<script type="">
    <%
        String tagFlag =request.getParameter("tagFlag");
        if(tagFlag!=null && tagFlag.equals("1")){
    %>
        document.forms[0].user_tag.readOnly = false;
	<%}%>
</script>
</html>
