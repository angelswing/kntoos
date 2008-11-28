<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <title>角色管理</title>
  <meta http-equiv="Content-Type" content="text/html; charset=gb2312">
</head>
<link href="<%=request.getContextPath()%>/user/css/master.css" rel="stylesheet" type="text/css">
<script type="text/javascript" language="JavaScript">
    var a;
    var b;
    var c;
    a=null;
    b=null;
    c=null;
    function pass(id,irow,role_id,role_name){
        if(id!=null) {
            b=a;
                    a=id;
                    a.style.backgroundColor="#ccccff";
                    if(b!=null&&b!=a&&c!=null) {
                        if (c == 0){b.style.backgroundColor="#F0F0F0";}
                if (c == 1){b.style.backgroundColor="#fffffd";}
                    }
            c = irow%2;
        }
        document.forms[0].selectedrole_id.value = role_id;
        parent.parent.Frame2.document.all.group1.src = "<%=request.getContextPath()%>/user/F110_RoleMgtAction.do?forward=AddDept&op=selectupdinfo&role_id=" + role_id;
      }

    //改变为新增状态时，清除其他行的颜色
    function change_color(){
    b=a;
    a=null;
        if(b!=null&&c!=null) {
                if (c == 0){b.style.backgroundColor="#F0F0F0";}
        if (c == 1){b.style.backgroundColor="#fffffd";}
        }
        c=(c+1)%2;
    }

    function doDelete(){
        with( document.forms[0] ){
            curPageNo.value = "";
            totalPage.value = "";
            forward.value="SelectRecord";
            op.value = "delete";
            submit();
        }
    }

    function getPage(){
        with( document.forms[0] ){
            parent.document.forms[0].preButton.disabled = false;
            parent.document.forms[0].nextButton.disabled = false;
            if ( curPageNo.value == "0" ){
                parent.document.forms[0].preButton.disabled = true;
                parent.document.forms[0].nextButton.disabled = true;
            }
            if ( curPageNo.value == "1" ){
                parent.document.forms[0].preButton.disabled = true;
            }
            if ( curPageNo.value == totalPage.value ){
                parent.document.forms[0].nextButton.disabled = true;
            }
            changeSelectButton();
        }
    }

    function changeSelectButton(){
        var oOption;
        parent.document.forms[0].curPageNo.length = 0;

        for ( var i = 1; i <= document.forms[0].totalPage.value; i ++ ){
            oOption = document.createElement( "option" );
            parent.document.forms[0].curPageNo.options.add( oOption );
            oOption.innerText = "第" + i + "页";
            oOption.value = i;
            if ( i == document.forms[0].curPageNo.value ){
                oOption.selected = true;
            }
        }
    }
</script>
<body bgcolor="#FFFFFF" topmargin="0" bottomMargin="0" leftMargin="1" onload="getPage()">
    <html:form action="/user/F110_RoleMgtAction.do" method="post">
    <html:hidden property="role_id"/>
    <html:hidden property="role_name"/>
    <html:hidden property="role_depict"/>
    <html:hidden property="op"/>
    <html:hidden property="forward"/>
    <html:hidden property="curPageNo"/>
    <html:hidden property="totalPage"/>
    <html:hidden property="selectedrole_id"/>
	<table width="100%" border="0">
  		<tr align="center">
    		<td width="17%" class="oracolumncenterheader" height="18" scope="col">角色名称</td>
    		<td width="68%" class="oracolumncenterheader" scope="col">角色描述</td>
    		<td width="15%" class="oracolumncenterheader" scope="col">创建日期</td>
  		</tr>
        <%int i = 1;%>
        <logic:iterate id="RoleInfo" name="user_F110_RoleMgtForm" property="selectResult">
        <%if ( i%2 == 1 ) {%>
        	<tr class="oracletdone" id="tr<%=i%>" onclick="pass(tr<%=i%>,<%=i%>,'<bean:write name="RoleInfo" property="role_id"/>','<bean:write name="RoleInfo" property="role_name"/>')">
            <script type=""><% if ( i == 1 ){%>
            	pass(tr<%=i%>,<%=i%>,'<bean:write name="RoleInfo" property="role_id"/>','<bean:write name="RoleInfo" property="role_name"/>');
            <%}%></script>
        <%} else {%>
        	<tr class="oracletdtwo" id="tr<%=i%>" onclick="pass(tr<%=i%>,<%=i%>,'<bean:write name="RoleInfo" property="role_id"/>','<bean:write name="RoleInfo" property="role_name"/>')">
        <%}%>
                <td height="18"><bean:write name="RoleInfo" property="role_name"/></td>
                <td><bean:write name="RoleInfo" property="role_depict"/></td>
                <td align="center"><bean:write name="RoleInfo" property="add_date"/></td>
        	</tr>
        <%i++;%>
        </logic:iterate>
        </table>
	</html:form>
</body>
</html>
