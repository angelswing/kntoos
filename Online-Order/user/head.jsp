<%@ page contentType="text/html; charset=GB2312" %>
<%@ page import="com.conant.ums.data.LoginData,com.conant.ums.util.tree.*,java.util.List"%>
<%
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
  <link href="css/master.css" rel="stylesheet" type="text/css">
<title>����ģ��</title>
<!--ȡϵͳ������ʱ��,demoʹ��.ϵͳ����ʱ,���ڻ�ӷ������˴�����,�����Կͻ���ʱ����Ϊ��׼-->
</head>

<script language="javascript" type="">
    function getClientDate(){
    var Tweek,Tmonth;
    var Tday=new Date();
    //ȡ��������ڣ�����������Tday
    switch(Tday.getDay())
    //getday�����������ڼ�,0Ϊ������
    {
    case 1:Tweek="����һ";break;
    case 2:Tweek="���ڶ�";break;
    case 3:Tweek="������";break;
    case 4:Tweek="������";break;
    case 5:Tweek="������";break;
    case 6:Tweek="������";break;
    case 0:Tweek="������";break;
    }
    switch(Tday.getMonth())
    //getMonth���������·���,0Ϊ1��
    {
    case 0:Tmonth="1";break;
    case 1:Tmonth="2";break;
    case 2:Tmonth="3";break;
    case 3:Tmonth="4";break;
    case 4:Tmonth="5";break;
    case 5:Tmonth="6";break;
    case 6:Tmonth="7";break;
    case 7:Tmonth="8";break;
    case 8:Tmonth="9";break;
    case 9:Tmonth="10";break;
    case 10:Tmonth="11";break;
    case 11:Tmonth="12";break;
    }
    //document.write(Tday.getYear()+"��"+Tmonth+Tday.getDate()+"��"+Tweek);
      document.write(Tday.getYear()+"-"+Tmonth+"-"+Tday.getDate()+" " + Tweek);
    }
</script>

<script type="text/javascript" language="javascript">
    function changePass() {
        var openurl = "changePass.jsp";
      window.open(openurl,'select','width=360,height=200, top=285, left=330, toolbar=no,scrollbars=yes,location=no, status=no');
    }

    function dropout(){
	parent.close();
    }
</script>
   <%
        LoginData loginData = (LoginData)session.getAttribute("LoginData");
        //TreeNode trRoot = loginData.getFuncRoot();
        //List funcList = trRoot.getChildren();

        TreeNode trRoot = null;
        List rootList = loginData.getDeptList();
        trRoot = (TreeNode)rootList.get(0);
        List funcList = trRoot.getChildren();
   %>

<body>
<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0">
    <tr align="center">
        <td align="center" height="30" width="25" ></td>
        <td align="center" height="30" width="1" >&nbsp;</td>
        <%
        	for(int i=0;i<funcList.size();i++){
                TreeNode menuNode = (TreeNode)funcList.get(i);
         %>
        <td align="center" height="30" width="52"><img src="images/bt.gif" width="200" height="35" alt=""></td>
        <%if(i!=funcList.size()-1){%>
        <td align="center" height="30" width="2">|</td>
        <%}%>
        <%}%>
        <td align="right" height="30" >
        <a target="_parent"><img id="img1" src="images/pass.gif" width="100" height="30" border="0" style="cursor:hand" onclick="changePass()"  onmouseover="this.src='images/pass_On.gif'" onmouseout="this.src='images/pass.gif'" alt="" title="�޸�����"></a>
        <a href="javascript:dropout()"><img id="img2" src="images/exit.gif" width="80" height="30" border="0" onmouseover="this.src='images/exit_On.gif'" onmouseout="this.src='images/exit.gif'" alt="" title="�˳�"></a></td>
        <td align="center" height="30" width="5">&nbsp;</td>
    </tr>
</table>
<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0">
    <tr align="center">
    	<td height="20" bgcolor="#DABB76" align="left">&nbsp;</td>
    	<td align="right" height="20" bgcolor="#DABB76"  class="Grid3"><%=loginData.getUserName()%>
   	  &nbsp;<script type="text/javascript">getClientDate()</script></td>
    	<td align="center" height="20" bgcolor="#DABB76" width="2">&nbsp;</td>
    </tr>
</table>
</body>
</html>
