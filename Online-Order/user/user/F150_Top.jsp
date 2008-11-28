<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>部门管理</title>
</head>
<link href="<%=request.getContextPath()%>/user/css/master.css" rel="stylesheet" type="text/css">
<script type="text/javascript" language="JavaScript">
    var a;
    var b;
    var c;
    a=null;
    b=null;
    c=null;
    function pass(id,irow,dept_id,dept_name){
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
        document.forms[0].selecteddept_id.value = dept_id;
        parent.parent.Frame2.document.all.group1.src = "<%=request.getContextPath()%>/user/F150_DeptMgtAction.do?forward=AddDept&op=selectupdinfo&dept_id=" + dept_id;
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
    <html:form action="/user/F150_DeptMgtAction.do" method="post">
    <html:hidden property="dept_id"/>
    <html:hidden property="dept_name"/>
    <html:hidden property="dept_desc"/>
    <html:hidden property="parent_dept_id"/>
    <html:hidden property="op"/>
    <html:hidden property="forward"/>
    <html:hidden property="selecteddept_id"/>
    <html:hidden property="curPageNo"/>
    <html:hidden property="totalPage"/>
	<table width="100%" border="0">
  		<tr align="center">
    		<td width="24%" class="oracolumncenterheader" height="18" scope="col">部门名称</td>
    		<td width="61%" class="oracolumncenterheader" scope="col">部门简介</td>
    		<td width="15%" class="oracolumncenterheader" scope="col">创建日期</td>
  		</tr>
        <%int i = 1;%>
        <logic:iterate id="DeptInfo" name="user_F150_DeptMgtForm" property="selectResult">
        <%if ( i%2 == 1 ) {%>
        	<tr class="oracletdone" id="tr<%=i%>" onclick="pass(tr<%=i%>,<%=i%>,'<bean:write name="DeptInfo" property="dept_id"/>','<bean:write name="DeptInfo" property="dept_name"/>')">
            <script type=""><% if ( i == 1 ){%>
            	pass(tr<%=i%>,<%=i%>,'<bean:write name="DeptInfo" property="dept_id"/>','<bean:write name="DeptInfo" property="dept_name"/>');
            <%}%></script>
        <%} else {%>
        	<tr class="oracletdtwo" id="tr<%=i%>" onclick="pass(tr<%=i%>,<%=i%>,'<bean:write name="DeptInfo" property="dept_id"/>','<bean:write name="DeptInfo" property="dept_name"/>')">
        <%}%>
                <td height="18"><bean:write name="DeptInfo" property="dept_name"/></td>
                <td><bean:write name="DeptInfo" property="dept_desc"/></td>
                <td align="center"><bean:write name="DeptInfo" property="add_date"/></td>

        	</tr>
        <%i++;%>
        </logic:iterate>
        </table>
	</html:form>
</body>
</html>
