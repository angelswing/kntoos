<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.conant.ums.form.F150_DeptMgtForm" %>
<html>
<head>
    <link href="<%=request.getContextPath()%>/user/css/master.css" rel="stylesheet" type="text/css">
	<title>��������</title>
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
            alert("��ѡ���ɫ��");
            parent_role.focus();
        }else if(checkrole()){
            alert("�ý�ɫ�Ѵ��ڣ�������ѡ���ɫ��");
            parent_role.focus();
        }else{
            var sValue = parent_role.options[ parent_role.selectedIndex ].value;
            var sText = parent_role.options[ parent_role.selectedIndex ].innerText;
            var oOption = document.createElement("option");
            deptrolelist.options.add(oOption);
            oOption.innerText=sText;
            oOption.value=sValue;
        }
        }
    }

    function checkrole(){
        with ( document.forms[0]) {
    	    var sValue = parent_role.options[ parent_role.selectedIndex ].value;
    	    for(i=0;i<deptrolelist.length;i++){
    	    if(deptrolelist.options[i].value==sValue){
    	        return true;
            }
            }
            return false;
    	}
    }

    function delrole(){
        var flag=0;
    	with(document.forms[0]){
          if(deptrolelist.length=="0"){
            alert("��ɫ�����ڣ�");
          }else{
            for(i=0;i<deptrolelist.length-1;i++)
            if(deptrolelist.options[i].selected || flag==1){
              deptrolelist.options[i].text=deptrolelist.options[i+1].innerText;
              deptrolelist.options[i].value=deptrolelist.options[i+1].value;
              flag=1;
            }
            deptrolelist.length=deptrolelist.length-1;
          }
        }
    }
</script>

<body onload="">
<html:form method="post" action="/user/F150_DeptMgtAction.do" >
    <html:hidden property="dept_id"/>
    <html:hidden property="op"/>
    <html:hidden property="forward"/>
	<table width="100%" border="0" cellpadding="2" cellspacing="2" align="center" class="MainTable" >
        <tr valign="middle" class="Grid1">
            <td align="left" width="10%" height="25">�������ƣ�</td>
            <td align="left" width="40%">
              <html:text property="dept_name" maxlength="16" style="width:249px" styleClass="TextBox" ></html:text><font color="red">&nbsp;&nbsp;*</font>
            </td>
            <td align="left" width="10%" height="25">�����ˣ�</td>
            <td align="left" width="40%">
              <html:text property="principal" maxlength="10" size="40" styleClass="TextBox" ></html:text>
            </td>
        </tr>
        <tr valign="middle" class="Grid1">
            <td align="left" height="25">����������</td>
            <td align="left">
              <html:textarea property="dept_desc" rows="3" cols="38" styleClass="TextBox"></html:textarea>
              <font color="red">&nbsp;*</font>
            </td>
            <td align="left" width="10%" height="25">���ŵ绰��</td>
            <td align="left" width="40%">
              <html:text property="telephone" maxlength="15" size="40" styleClass="TextBox"></html:text>
            </td>
        </tr>
        <tr valign="middle" class="Grid1">
           <td align="left" height="25">�ϼ����ţ�</td>
           <td align="left">
             <html:select property="parent_dept_id" style="width:250px" styleClass="SelectButton">
                  <html:option value="#">û���ϼ�����</html:option>
                  <html:optionsCollection name="user_F150_DeptMgtForm" property="dept_list" value="dept_id" label="dept_name"/>
             </html:select>
           </td>
           <td align="left" width="10%" height="25">���Ŵ��棺</td>
           <td align="left" width="40%">
             <html:text property="faxes" maxlength="15" size="40" styleClass="TextBox"></html:text>
           </td>
        </tr>
        <tr valign="middle" class="Grid1">
          <td align="left" height="25">��ɫ�б�</td>
          <td align="left">
              <html:select property="parent_role" style="width:250px" styleClass="SelectButton">
                  <html:option value="#">&nbsp;</html:option>
                  <html:optionsCollection name="user_F150_DeptMgtForm" property="role_list" value="role_id" label="role_name"/>
              </html:select>
           </td>
          <td align="left" height="25">����������</td>
          <td align="left">
              <select style="width:65px">
                  <option value="#">����ʡ</option>
              </select>&nbsp;ʡ&nbsp;&nbsp;
              <html:select property="area" style="width:150px" styleClass="SelectButton">
                  <html:option value="����ʡ">&nbsp;</html:option>
                  <html:option value="�人��">�人��</html:option>
                  <html:option value="��ʯ��">��ʯ��</html:option>
                  <html:option value="ʮ����">ʮ����</html:option>
                  <html:option value="�˲���">�˲���</html:option>
                  <html:option value="�差��">�差��</html:option>
                  <html:option value="������">������</html:option>
                  <html:option value="������">������</html:option>
                  <html:option value="Т����">Т����</html:option>
                  <html:option value="������">������</html:option>
                  <html:option value="�Ƹ���">�Ƹ���</html:option>
                  <html:option value="������">������</html:option>
                  <html:option value="������">������</html:option>
                  <html:option value="��ʩ����������������">��ʩ����������������</html:option>
                  <html:option value="������">������</html:option>
                  <html:option value="Ǳ����">Ǳ����</html:option>
                  <html:option value="������">������</html:option>
                  <html:option value="��ũ������">��ũ������</html:option>
              </html:select>&nbsp;��
           </td>           
        </tr>
         <tr valign="middle" class="Grid1">
           <td align="left" height="25">���Ž�ɫ��</td>
            <td align="left" colspan="3">
               <html:select property="deptrolelist" style="width:251px" styleClass="SelectButton" size="4" multiple="true">
                   <html:optionsCollection name="user_F150_DeptMgtForm" property="deptrole" value="dept_role_id" label="dept_role_name"/>
               </html:select>
               <img src="<%=request.getContextPath()%>/user/images/Insert_icon.gif" onclick="addrole()" alt="" style="CURSOR: hand">&nbsp;
               <img src="<%=request.getContextPath()%>/user/images/Delete_icon.gif" onclick="delrole()" alt="" style="CURSOR: hand">
               </td>
         </tr>
    </table>
</html:form>
</body>
</html>
