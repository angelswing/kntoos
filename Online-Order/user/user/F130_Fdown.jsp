<%@ page contentType="text/html; charset=GB2312" %>
<html>
<head>
    <link href="<%=request.getContextPath()%>/user/css/master.css" rel="stylesheet" type="text/css">
	<title>�û�����</title>
</head>
<%
//title��Ƭ��������ƣ�Ltitle��Ƭ�������Ŀ�ȣ�Ntitle��Ƭ����ĸ���,UrlLay��Ƭ��Ӧ�Ĳ��ҳ��
	String[] title = {"�û�������Ϣ"};
	String[] Ltitle = {"80"};
	String[] UrlLay = {""};
	int Ntitle = 0;
	if(title!=null){
		Ntitle = title.length;
	}
%>
<script LANGUAGE="JavaScript" type="">
//�����е���ʽ��Ϊδ���е���ʽ
function clearall(){
	with( document.forms[0] ){
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

    //����Ƿ�Ϊ�Ϸ��ĵ绰���� @raokun 2006-08-09
    function isTel(str){
        var numb = false;
        for(i=0;i<str.length;i++){
        var chr = str.charAt(i);
            if(!(chr>=0 && chr<=9 || chr=="-")){
                numb = true;
            }
        }
        return numb;
    }

    //����Ƿ�Ϊ�Ϸ����ʼ���ַ
    function isEmail(strEmail) {
       if (strEmail.search(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/) != -1)
           return false;
       else
           return true;
    }

   //ִ����������
   function insert(){
      if( group1.document.forms[0].user_tag.value == "" ){
        alert( "�������½�ʺţ�" );
        td1.onclick;
        group1.document.forms[0].user_tag.focus();
        return;
      }
      if( group1.document.forms[0].deptid.value == "" ){
        alert( "��ѡ���������ţ�" );
        td1.onclick;
        group1.document.forms[0].deptid.focus();
        return;
      }
      if( group1.document.forms[0].user_passwd.value != "" && group1.document.forms[0].user_passwd.value.length < 6  ){
        alert( "�������벻����λ��" );
        td1.onclick;
        group1.document.forms[0].user_passwd.focus();
        return;
      }
      if( group1.document.forms[0].user_passwd.value != group1.document.forms[0].user_passwd_t.value ){
        alert( "�����������벻ͬ��" );
        td1.onclick;
        group1.document.forms[0].user_passwd_t.focus();
        return;
      }
      if( group1.document.forms[0].user_name.value == "" ){
        alert( "�������û�������" );
        td1.onclick;
        group1.document.forms[0].user_name.focus();
        return;
      }
      if( group1.document.forms[0].email.value != "" && isEmail(group1.document.forms[0].email.value )){
        alert("�ʼ���ַ���Ϸ�!");
        group1.document.forms[0].email.focus();
        return;
      }      
      if( group1.document.forms[0].home_tel.value.indexOf(" ") >=0 ){
          alert("�绰�����д��ڿո�!");
          group1.document.forms[0].home_tel.focus();
          return;
      }      
      if( isTel(group1.document.forms[0].home_tel.value )){
        alert("�绰�������Ϊ���ֻ�'-'!");
        group1.document.forms[0].home_tel.focus();
        return;
      }
      if( group1.document.forms[0].mobile.value.indexOf(" ") >=0 ){
          alert("�绰�����д��ڿո�!");
          group1.document.forms[0].mobile.focus();
          return;
      }         
      if( isTel(group1.document.forms[0].mobile.value )){
        alert("�绰�������Ϊ���ֻ�'-'!");
        group1.document.forms[0].mobile.focus();
        return;
      }
      
    for(var m=0;m<group1.document.forms[0].userrole.length;m++){
        group1.document.forms[0].userrole.options[m].selected=true;
    }

    if ( group1.document.forms[0].user_id.value == "" ) {
        group1.document.forms[0].op.value = "insert";
        group1.document.forms[0].forward.value = "InsertUser";
     } else {
        group1.document.forms[0].op.value = "update";
        group1.document.forms[0].forward.value = "UpdateUser";
     }

     document.forms[0].save.disabled = true;
     group1.document.forms[0].submit();
  }
</script>

<body id="test" leftmargin="0" topmargin="0">
    <form action="">
    <table class="butt" CELLPADDING="0" CELLSPACING="0" width="98%" align="center">
      <tr>
<!--Title of card Start-->
          <%
          	if(Ntitle>0){
          %>
          <td width="10"><img id="img1" src="<%=request.getContextPath()%>/user/images/now_left.jpg" width="10" height="23"></td>
          <td id="td1" width="<%=Ltitle[0]%>" valign="bottom" background="<%=request.getContextPath()%>/user/images/now_back.jpg" class="Grid2" onclick=change(1); style="CURSOR: hand">
              <div align="center" onclick=change(1); style="CURSOR: hand"><%=title[0]%></div></td>
          <td width="10"><img id="img2" src="<%=request.getContextPath()%>/user/images/now_right.jpg" width="10" height="23"></td>
          <%
          	}
          	for(int i=1;i<Ntitle-1;i++){
          %>
          <td id="td<%=i+1%>" width="<%=Ltitle[i]%>" valign="bottom" background="<%=request.getContextPath()%>/user/images/fun_back.jpg" class="Grid2" onclick=change(<%=i+1%>); style="CURSOR: hand">
              <div align="center" onclick=change(<%=i+1%>); style="CURSOR: hand"><%=title[i]%></div></td>
          <td width="10"><img id="img<%=i+2%>" src="<%=request.getContextPath()%>/user/images/fun_lr.jpg" width="10" height="23"></td>
          <%
          	}
          	if(Ntitle>1){
          %>
          <td id="td<%=Ntitle%>" width="<%=Ltitle[Ntitle-1]%>" valign="bottom" background="<%=request.getContextPath()%>/user/images/fun_back.jpg" class="Grid2" onclick=change(<%=Ntitle%>); style="CURSOR: hand">
              <div align="center" onclick=change(<%=Ntitle%>); style="CURSOR: hand"><%=title[Ntitle-1]%></div></td>
          <td width="10"><img id="img<%=Ntitle+1%>" src="<%=request.getContextPath()%>/user/images/fun_right.jpg" width="10" height="23"></td>
          <%
          	}
          %>
<!--Title of card End -->
      <td height="23"  ID="t3"  class="tdright">
	<input type="button" name="save" value="��  ��" onclick="insert()" class="button"/>
        </td>
	</tr></table>

<!--div of card Start-->
	<%
          	if(Ntitle>=0){
          %>
        <div id="Layer1" class="divtype" style="position:absolute;  z-index:1; visibility: visible; width=100%; height=92%">
          <table  align="center" width="98%" height="100%" border="0" cellpadding="1" cellspacing="0">
            <tr><td class="wind" width="100%" bgcolor="#D8AE61"><p align="center">
              <IFRAME
                  name="group1"
                  src="<%=UrlLay[0]%>"
                  frameBorder=0 width="100%"
                  height="100%"
                  scrolling="NO" noresize
                  >
                 </IFRAME>
           </td></tr></table>
        </div>
	<%
          	}
          	for(int i=1;i<Ntitle;i++){
          %>
        <div id="Layer<%=i+1%>" class="divtype" style="position:absolute;  z-index:<%=i+1%>; visibility: hidden; width=100%; height=92%" >
          <table  align="center" width="98%" height="100%" border="0" cellpadding="1" cellspacing="0">
            <tr><td class="wind" width="100%" bgcolor="#94AAD6"><p align="center"><IFRAME
                  name="group<%=i+1%>"
                  src="<%=UrlLay[i]%>"
                  frameBorder=0 width="100%"
                  height="100%"
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
