<%@ page contentType="text/html; charset=GB2312" %>
<html>
<head>
    <link href="<%=request.getContextPath()%>/user/css/master.css" rel="stylesheet" type="text/css">
	<title>部门管理</title>
</head>
<%
//title卡片标题的名称，Ltitle卡片标题栏的宽度，Ntitle卡片标题的个数,UrlLay卡片对应的层的页面，HeiLay层的高度
	String[] title = {"部门管理"};
	String[] Ltitle = {"80"};
	String[] UrlLay = {request.getContextPath() + "/user/F150_DeptMgtAction.do?forward=SelectRecord&op=select"};
	String[] HeiLay = {"246"};
	int Ntitle = 0;
	if(title!=null){
		Ntitle = title.length;
	}
%>
<script LANGUAGE="JavaScript" type="">
    //将所有的样式至为未点中的样式
    function clearall(){
        <%
            for(int j=1;j<Ntitle;j++){
        %>
        img<%=j+1%>.src="<%=request.getContextPath()%>/user/images/fun_lr.jpg";
        td<%=j+1%>.background="<%=request.getContextPath()%>/user/images/fun_back.jpg";
        Layer<%=j+1%>.style.visibility="hidden";
        <%
            }
            if(Ntitle>1){
        %>
        img<%=Ntitle+1%>.src="<%=request.getContextPath()%>/user/images/fun_right.jpg";
        img1.src="<%=request.getContextPath()%>/user/images/fun_left.jpg";
        td1.background="<%=request.getContextPath()%>/user/images/fun_back.jpg";
        Layer1.style.visibility="hidden";
        <%
            }
        %>
    }

    //将点中的卡片样式的转化
    function change(n){
        clearall();
        var imgleft=document.getElementById('img'+n);
        var imgright=document.getElementById('img'+(n+1));
        var imgback=document.getElementById('td'+n);
        var vLayer=document.getElementById('Layer'+n);

        imgleft.src="<%=request.getContextPath()%>/user/images/now_left.jpg";
        imgright.src="<%=request.getContextPath()%>/user/images/now_right.jpg";
        imgback.background="<%=request.getContextPath()%>/user/images/now_back.jpg";
        vLayer.style.visibility="visible";
        if(n==1){
            goup();
        }else if(n==2){
            godown();
        }
    }

    //上下2帧的状态
    function goup(){
        parent.content.rows = "280,*";
    }

    //单独1帧的状态
    function godown(){
        parent.content.rows = "*,0";
    }

    function doSelect(){
        var url = "<%=request.getContextPath()%>/user/F150_DeptMgtAction.do?forward=QurCondition&op=selectoptions";
        window.open(url,"_query","width=400,height=200,top=259, left=312, toolbar=no,scrollbars=yes,location=no, status=no");
    }

    function doInsert(){
        group1.change_color();
        parent.Frame2.document.all.group1.src = "<%=request.getContextPath()%>/user/F150_DeptMgtAction.do?forward=AddDept&op=selectoptions";
    }

    function doDelete(){
        if ( group1.document.forms[0].selecteddept_id.value != "" ){
            if ( confirm("确定删除该记录吗？")) {
                group1.doDelete();
            }
        }
    }

    function chg_pageno(){
        group1.document.forms[0].curPageNo.value = document.forms[0].curPageNo.value;
        selectInfo();
    }

    function prePage(){
        group1.document.forms[0].curPageNo.value = parseInt( group1.document.forms[0].curPageNo.value ) - 1;
        selectInfo();
    }

    function nextPage(){
        group1.document.forms[0].curPageNo.value = parseInt( group1.document.forms[0].curPageNo.value ) + 1;
        selectInfo();
    }

    function selectInfo(){
        group1.document.forms[0].selecteddept_id.value = "";
        group1.document.forms[0].op.value="select";
        group1.document.forms[0].forward.value="SelectRecord";
        group1.document.forms[0].submit();
    }
</script>
<body  id="test" leftmargin="0" topmargin="8">
    <form action="" name="form1">
    <table class="tablecenter" CELLPADDING="0" CELLSPACING="0" width="98%"  align="center">
      <tr>
<!--Title of card Start-->
          <%
          	if(Ntitle>0){
          %>
          <td width="10"><img id="img1" src="<%=request.getContextPath()%>/user/images/now_left.jpg" width="10" height="23"></td>
          <td id="td1" width="<%=Ltitle[0]%>" valign="bottom" background="<%=request.getContextPath()%>/user/images/now_back.jpg" class="Grid2">
              <div align="center" onclick=change(1); style="CURSOR: hand"><%=title[0]%></div></td>
          <td width="10"><img id="img2" src="<%=request.getContextPath()%>/user/images/now_right.jpg" width="10" height="23"></td>
          <%
          	}
          	for(int i=1;i<Ntitle-1;i++){
          %>
          <td id="td<%=i+1%>" width="<%=Ltitle[i]%>" valign="bottom" background="<%=request.getContextPath()%>/user/images/fun_back.jpg" class="Grid2">
              <div align="center" onclick=change(<%=i+1%>); style="CURSOR: hand"><%=title[i]%></div></td>
          <td width="10"><img id="img<%=i+2%>" src="<%=request.getContextPath()%>/user/images/fun_lr.jpg" width="10" height="23"></td>
          <%
          	}
          	if(Ntitle>1){
          %>
          <td id="td<%=Ntitle%>" width="<%=Ltitle[Ntitle-1]%>" valign="bottom" background="<%=request.getContextPath()%>/user/images/fun_back.jpg" class="Grid2">
              <div align="center" onclick=change(<%=Ntitle%>); style="CURSOR: hand"><%=title[Ntitle-1]%></div></td>
          <td width="10"><img id="img<%=Ntitle+1%>" src="<%=request.getContextPath()%>/user/images/fun_right.jpg" width="10" height="23"></td>
          <%
          	}
          %>
<!--Title of card End -->
      <td height="23"  ID="t3"  class="tdright">
	<input type="button" value="查  询" class="button" onclick="doSelect()"/>
	<input type="button" value="新  增" class="button" onclick="doInsert()"/>
    <input type="button" value="删  除" class="button" onclick="doDelete()"/>
    <input type="button" name="preButton" value="上  页" class="button" onclick="prePage()" disabled="disabled"/>
    <input type="button" name="nextButton" value="下  页" class="button" onclick="nextPage()" disabled="disabled"/>
    <select name="curPageNo" onchange="chg_pageno()" style="width:70" Class="SelectButton"></select>
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
          	for(int i=1;i<Ntitle;i++){
          %>
        <div id="Layer<%=i+1%>" class="divtype" style="position:absolute;  z-index:<%=i+1%>; visibility: hidden; width:100%" >
          <table  width="98%" height="100%" border="0" cellpadding="1" cellspacing="0"  align="center">
            <tr><td class="wind" bgcolor="#94AAD6"><p align="center"><IFRAME
                  name="group<%=i+1%>"
                  src="<%=UrlLay[i]%>"
                  frameBorder=0 width="100%"
                  height="<%=HeiLay[i]%>"
                  scrolling="NO" noresize
                  >
             </IFRAME></td></tr></table>
        </div>
	<%
          	}
          %>
<!--div of card end-->
    </form>
  </body>
</html>
