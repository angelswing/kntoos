<%@ page contentType="text/html; charset=GB2312" %>
<html>
<head>
    <link href="<%=request.getContextPath()%>/user/css/master.css" rel="stylesheet" type="text/css">
	<title>��ɫ����</title>
</head>
<%
//title��Ƭ��������ƣ�Ltitle��Ƭ�������Ŀ�ȣ�Ntitle��Ƭ����ĸ���,UrlLay��Ƭ��Ӧ�Ĳ��ҳ��
	String[] title = {"��ɫ��ϸ��Ϣ"};
	String[] Ltitle = {"80"};
        String[] UrlLay = {""};
        //String[] HeiLay = {"242"};
	int Ntitle = 0;
	if(title!=null){
		Ntitle = title.length;
	}
%>
<script LANGUAGE="JavaScript" type="">
//�����е���ʽ��Ϊδ���е���ʽ
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
//�����еĿ�Ƭ��ʽ��ת��
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
}

function checkForm(){

        if ( group1.document.forms[0].role_name.value == "" ){
            alert( "�������ɫ���ƣ�" );
            group1.document.forms[0].role_name.focus();
            return;
        }

        if ( group1.document.forms[0].role_depict.value == "" ){
        	alert( "�������ɫ������" );
            group1.document.forms[0].role_depict.focus();
            return;
        }

        if ( group1.document.forms[0].role_depict.value.indexOf(" ") >=0 ){
            alert( "�����д��ڿո�" );
            group1.document.forms[0].role_depict.focus();
            return;
        }

        if ( group1.document.forms[0].role_depict.value.length > 45 ){
        	alert( "��ɫ����������" );
            group1.document.forms[0].role_depict.focus();
            return;
        }

        if ( group1.document.forms[0].role_id.value == "" ) {
        	group1.document.forms[0].op.value = "insert";
        	group1.document.forms[0].forward.value = "InsertDone";
        } else {
            group1.document.forms[0].op.value = "update";
            group1.document.forms[0].forward.value = "AddDeptDone";
        }

        document.forms[0].save.disabled = true;
        group1.document.forms[0].submit();
}
</script>

<body id="test" leftmargin="0" topmargin="0" >
  <form action="">
    <table class="butt" CELLPADDING="0" CELLSPACING="0" width="98%" align="center">
      <tr>
<!--Title of card Start-->
          <%
          	if(Ntitle>0){
          %>
          <td width="10"><img id="img1" src="<%=request.getContextPath()%>/user/images/now_left.jpg" width="10" height="23" alt=""></td>
          <td id="td1" width="<%=Ltitle[0]%>" valign="bottom" background="<%=request.getContextPath()%>/user/images/now_back.jpg" class="Grid2">
              <div align="center" onclick=change(1); style="CURSOR: hand"><%=title[0]%></div></td>
          <td width="10"><img id="img2" src="<%=request.getContextPath()%>/user/images/now_right.jpg" width="10" height="23" alt=""></td>
          <%
          	}
          	for(int i=1;i<Ntitle-1;i++){
          %>
          <td id="td<%=i+1%>" width="<%=Ltitle[i]%>" valign="bottom" background="<%=request.getContextPath()%>/user/images/fun_back.jpg" class="Grid2">
              <div align="center" onclick=change(<%=i+1%>); style="CURSOR: hand"><%=title[i]%></div></td>
          <td width="10"><img id="img<%=i+2%>" src="<%=request.getContextPath()%>/user/images/fun_lr.jpg" width="10" height="23" alt=""></td>
          <%
          	}
          	if(Ntitle>1){
          %>
          <td id="td<%=Ntitle%>" width="<%=Ltitle[Ntitle-1]%>" valign="bottom" background="<%=request.getContextPath()%>/user/images/fun_back.jpg" class="Grid2">
              <div align="center" onclick=change(<%=Ntitle%>); style="CURSOR: hand"><%=title[Ntitle-1]%></div></td>
          <td width="10"><img id="img<%=Ntitle+1%>" src="<%=request.getContextPath()%>/user/images/fun_right.jpg" width="10" height="23" alt=""></td>
          <%
          	}
          %>
<!--Title of card End -->
      <td height="23"  ID="t3"  class="tdright">
	<input type="button" name="save" value="��  ��" class="button" onclick="checkForm()"/>
        </td>
	</tr></table>

<!--div of card Start-->
	<%
          	if(Ntitle>=0){
          %>
        <div id="Layer1" class="divtype" style="position:absolute;  z-index:1; visibility: visible; width=100%; height=92%">
          <table align="center" width="98%" height="100%" border="0" cellpadding="1" cellspacing="0">
            <tr><td class="wind" width="100%" bgcolor="#D8AE61"><p align="center">
              <iframe name="group1" src="<%=UrlLay[0]%>" frameBorder=0 width="100%" height="100%" scrolling="NO" noresize></iframe>
           </td></tr></table>
        </div>
	<%
          	}
          	for(int i=1;i<Ntitle;i++){
          %>
        <div id="Layer<%=i+1%>" class="divtype" style="position:absolute;  z-index:<%=i+1%>; visibility: hidden; width=100%; height=92%" >
          <table align="center" width="98%" height="100%" border="0" cellpadding="1" cellspacing="0">
            <tr><td class="wind" width="100%" bgcolor="#94AAD6"><p align="center">
               <iframe name="group<%=i+1%>" src="<%=UrlLay[i]%>" frameBorder=0 width="100%" height="100%" scrolling="NO" noresize></iframe>
             </td></tr></table>
        </div>
	<%
          	}
          %>
<!--div of card end-->
</form>
</body>
</html>
