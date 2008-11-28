<%@ page contentType="text/html; charset=GB2312" %>
<html>
<head>
    <link href="<%=request.getContextPath()%>/user/css/master.css" rel="stylesheet" type="text/css">
	<title>业务实体维护</title>
</head>
<%
//title卡片标题的名称，Ltitle卡片标题栏的宽度，Ntitle卡片标题的个数,UrlLay卡片对应的层的页面，HeiLay层的高度
	String[] title = {"角色权限"};
	String[] Ltitle = {"80"};
	String[] UrlLay = {request.getContextPath() + "/user/F120_PurvMgtAction.do?forward=SelectRecord&op=select"};
	String[] HeiLay = {"500"};
	int Ntitle = 0;
	if(title!=null){
		Ntitle = title.length;
	}
%>

<body id="test" leftmargin="0" topmargin="8">
    <form action="" name="form1">
    <table class="tablecenter" CELLPADDING="0" CELLSPACING="0" width="98%"  align="center">
      <tr>
<!--Title of card Start-->
          <%
          	if(Ntitle>0){
          %>
          <td width="10"><img id="img1" src="<%=request.getContextPath()%>/user/images/now_left.jpg" width="10" height="23" alt=""></td>
          <td id="td1" width="<%=Ltitle[0]%>" valign="bottom" background="<%=request.getContextPath()%>/user/images/now_back.jpg" class="Grid2">
              <div align="center"><%=title[0]%></div></td>
          <td width="10"><img id="img2" src="<%=request.getContextPath()%>/user/images/now_right.jpg" width="10" height="23" alt=""></td>
          <%
          	}
          %>
<!--Title of card End -->
      <td height="23"  ID="t3"  class="tdright">&nbsp;
	
      </td>
      </tr>
      </table>
<!--div of card Start-->
	<%
          	if(Ntitle>=0){
          %>
        <div  id="Layer1" class="divtype" style="position:absolute;  z-index:1; visibility: visible; width:100%">
          <table  width="98%" height="100%" border="0" cellpadding="1" cellspacing="0"  align="center">
            <tr><td class="wind" bgcolor="#D8AE61"><p align="center">
                <IFRAME name="group1" src="<%=UrlLay[0]%>" frameBorder=0 width="100%" height="<%=HeiLay[0]%>" scrolling="NO" noresize></IFRAME>
           </td></tr></table>
        </div>
<%
          	}
%>
<!--div of card end-->
    </form>
  </body>
</html>
