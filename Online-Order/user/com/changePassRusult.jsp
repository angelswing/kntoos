<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>
<html>
<head>
    <link href="<c:url value="<%=request.getContextPath()%>/user/css/master.css"/>" rel="stylesheet" type="text/css">
	<title>修改密码</title>
</head>
<script language="javascript" type="">
//关闭窗口
function winclose(){
	window.close();
}
</script>
<body>
     <table width="90%" border="0" cellpadding="0" cellspacing="0" align="center" class="MainTable" >
       <tr>
          <td class="orarowheader" colspan="4" height="20">修改密码</td>
      </tr>
      <tr>
          <td colspan="4" height="5">&nbsp;</td>
      </tr>
        <tr align="center" class="Grid1" >
          <td  height="25"><font size="3">修改密码成功</font></td>
      </tr>
      <tr>
          <td colspan="4" height="40">&nbsp;</td>
      </tr>
      <tr align="center" class="Grid1" >
          <td align="center" >
          <input type="button" name="butok" value="确  定" class="button" onclick="winclose()" />
          </td>
      </tr>
    </table>
</body>
</html>