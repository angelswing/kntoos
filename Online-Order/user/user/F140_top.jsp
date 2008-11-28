<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page import="com.conant.ums.form.*" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>用户信息</title>
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
    function pass(id,irow,user_id){
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
            parent.parent.Frame2.document.all.group1.src = "<%=request.getContextPath()%>/user/F140_UserLimAction.do?forward=Qur_down&op=selectupdinfo&user_id=" + user_id;
    }

    //上下页按钮状态控制
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

    //页码下拉列表控制
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

    //按列排序，升序order=0，降序order=1
    function orderby(listno){
        var order = document.forms[0].sortOrder.value;
        if(order==""){
            order=1;
        }else{
            order=(order*1+1)%2;
        }
        if( listno==1 ){
            document.forms[0].sortCol.value = "user_tag";
            document.forms[0].sortOrder.value = order;
        }else if( listno==2 ){
            document.forms[0].sortCol.value = "user_id";
            document.forms[0].sortOrder.value = order;
        }
        document.forms[0].submit();
    }
</script>
<%
    String title = ((F140_UserLimForm)request.getAttribute("user_F140_UserLimForm")).getSortCol();
    String titlesort = ((F140_UserLimForm)request.getAttribute("user_F140_UserLimForm")).getSortOrder();
%>
<body bgcolor="#FFFFFF" topmargin="0" bottomMargin="0" leftMargin="1" onload="getPage()">
<html:form action="/user/F140_UserLimAction.do" method="post" enctype="multipart/form-data">
    <html:hidden property="user_id"/>
    <html:hidden property="user_tag"/>
    <html:hidden property="user_name"/>
    <html:hidden property="sortCol"/>
    <html:hidden property="sortOrder"/>
    <html:hidden property="curPageNo"/>
    <html:hidden property="totalPage"/>
    <html:hidden property="op"/>
    <html:hidden property="forward"/>
	<table width="100%" border="0">
  		<tr align="center">
    		<td width="15%" class="oracolumncenterheader" height="18" scope="col" onclick="orderby(1)" style="CURSOR: hand" id="title1">登陆帐号<%if(title!=null && title.equals("user_tag")){if(titlesort!=null && titlesort.equals("1")){%><img src="<%=request.getContextPath()%>/user/images/sortAsc_false.GIF" alt=""><%}else{%><img src="<%=request.getContextPath()%>/user/images/sortAsc_true.GIF" alt=""><%}}%></td>
    		<td width="20%" class="oracolumncenterheader" scope="col" onclick="orderby(2)" style="CURSOR: hand" id="title2">创建时间<%if(title!=null && title.equals("user_id")){if(titlesort!=null && titlesort.equals("1")){%><img src="<%=request.getContextPath()%>/user/images/sortAsc_false.GIF" alt=""><%}else{%><img src="<%=request.getContextPath()%>/user/images/sortAsc_true.GIF" alt=""><%}}%></td>
    		<td width="15%" class="oracolumncenterheader" scope="col">用户姓名</td>
            <td width="30%" class="oracolumncenterheader" scope="col">所属部门</td>
            <td width="10%" class="oracolumncenterheader" scope="col">在线状态</td>
    		<td width="10%" class="oracolumncenterheader" scope="col">锁定状态</td>
  		</tr>
        <%int i = 1;%>
        <logic:iterate id="UserInfo" name="user_F140_UserLimForm" property="selectResult">

        <%if ( i%2 == 1 ) {%>
        	<tr class="oracletdone" id="tr<%=i%>" onclick="pass(tr<%=i%>,<%=i%>,'<bean:write name="UserInfo" property="user_id"/>')">
        <%} else {%>
        	<tr class="oracletdtwo" id="tr<%=i%>" onclick="pass(tr<%=i%>,<%=i%>,'<bean:write name="UserInfo" property="user_id"/>')">
        <%}%>
        <script type="">
        <%if (i == 1) {%>
           pass(tr1,1,'<bean:write name="UserInfo" property="user_id"/>');
        <%}%>
        </script>
                <td height="18"><bean:write name="UserInfo" property="user_tag"/></td>
                <td align="center"><bean:write name="UserInfo" property="add_date"/></td>
                <td><bean:write name="UserInfo" property="user_name"/></td>
                <td><bean:write name="UserInfo" property="dept_name"/></td>
                <td align="center"><bean:write name="UserInfo" property="is_login"/></td>
                <td align="center"><bean:write name="UserInfo" property="lock_flag"/></td>
        	</tr>
        <%i++;%>
        </logic:iterate>
        </table>
	</html:form>
</body>
</html>
