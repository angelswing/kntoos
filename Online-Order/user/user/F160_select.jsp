<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.conant.ums.form.*" %>
<html>
<head>
    <link href="<%=request.getContextPath()%>/user/css/master.css" rel="stylesheet" type="text/css">
	<title>用户查询</title>
</head>
<script language="javascript" type="">
    //执行查询动作
    function winselect(){
      with(document.forms[0]){
            window.opener.group1.document.forms[0].user_tag.value = user_tag.value;
        	window.opener.group1.document.forms[0].user_name.value = user_name.value;
        }
        window.opener.group1.document.forms[0].curPageNo.value = "";
        window.opener.group1.document.forms[0].totalPage.value = "";
        window.opener.selectInfo();
        window.close();
    }
    //关闭窗口
    function winclose(){
        window.close();
    }

    function resetData(){
        document.forms[0].user_tag.value="";
        document.forms[0].user_name.value="";
        document.forms[0].user_tag.focus();
    }
</script>

<body bgcolor="#ffffff">
<html:form method="post" action="/user/F160_UserOnlAction.do" enctype="multipart/form-data">
   <html:hidden property="user_id"/>
      <table width="90%" border="0" cellpadding="0" cellspacing="0" align="center" class="MainTable" >
        <tr>
          <td class="orarowheader" colspan="2" height="20">用户查询</td>
        </tr>
        <tr>
          <td colspan="2" height="5">&nbsp;</td>
        </tr>
        <tr valign="absmiddle" class="Grid1">
          <td align="left" width="30%"  height="25">登陆帐号：</td>
          <td align="left" width="70%"><input type="text" name="user_tag" maxlength="30" size="30" class="TextBox" value="" /></td>
        </tr>
        <tr valign="absmiddle" class="Grid1">
          <td align="left" height="25">用户姓名：</td>
          <td align="left"><input type="text" name="user_name" maxlength="30" size="30" class="TextBox" value="" /></td>
        </tr>
        <tr valign="absmiddle" class="Grid1">
          <td align="left" height="25">&nbsp;</td>
          <td align="left" height="25">&nbsp;</td>
        </tr>
        <tr valign="absmiddle" class="Grid1">
          <td align="left" height="25">&nbsp;</td>
          <td align="right">
          <input type="button" name="butok" value="确  定" class="button" onclick="winselect()" />
          <input type="button" name="butret" value="重  置" class="button" onclick="resetData()" />
          <input type="button" name="butcancle" value="取  消" class="button" onclick="winclose()" />
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
