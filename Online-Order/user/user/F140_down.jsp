<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.conant.ums.form.*" %>
<html>
<head>
  <title>�û���������</title>
</head>
<link href="<%=request.getContextPath()%>/user/css/master.css" rel="stylesheet" type="text/css">
<script type="">
    <%
	String RUflag =request.getParameter("flag");
	if(RUflag!=null && RUflag.equals("1")){
    %>
        parent.parent.Frame1.selectInfo();
    <%}%>
    parent.document.forms[0].save.disabled = false;
</script>
<script language="javascript" type="">
    function addtime(){
        with ( document.forms[0]) {
        if(starttime.value == ""){
          alert("��ѡ����ʼʱ�䣡");
          starttime.focus();
          return;
        }
        if(endtime.value == ""){
          alert("��ѡ�����ʱ�䣡");
          endtime.focus();
          return;
        }
        if(starttime.value >= endtime.value){
          alert("��ʼʱ���ڽ���ʱ��֮��");
          endtime.focus();
          return;
        }
        if(checktime()){
            alert("��ʱ��δ��ڣ����������룡");
            ip.focus();
            return;
        }
        var sValue = starttime.value + ":" + endtime.value;
        var sText = starttime.value + " < * < " + endtime.value;
        var oOption = document.createElement("option");
        limitedtime.options.add(oOption);
        oOption.innerText=sText;
        oOption.value=sValue;
      }
    }

    function checktime(){
        with ( document.forms[0]) {
    	    var sValue = starttime.value + ":" + endtime.value;
    	    for(i=0;i<limitedtime.length;i++){
    	    if(limitedtime.options[i].value==sValue){
    	        return true;
            }
            }
            return false;
    	}
    }

    function deltime(){
        var flag=0;
    	with(document.forms[0]){
          if(limitedtime.length=="0"){
            alert("������ʱ��Σ�");
          }else{
            for(i=0;i<limitedtime.length-1;i++)
            if(limitedtime.options[i].selected || flag==1){
              limitedtime.options[i].text=limitedtime.options[i+1].innerText;
              limitedtime.options[i].value=limitedtime.options[i+1].value;
              flag=1;
            }
            limitedtime.length=limitedtime.length-1;
          }
        }
    }

    function addip(){
     with (document.forms[0]) {
        if(ip.value == ""){
          alert("������ip��ַ��");
          ip.focus();
          return;
        }
        if(checkip()){
            alert("��ip��ַ���ڣ����������룡");
            ip.focus();
            return;
        }
        if(checkIP2()){
           var ErrMsg="���������һ���Ƿ���IP��ַ�Σ�\nIP��Ϊ��:xxx.xxx.xxx.xxx��xxxΪ0-255)��"
           alert(ErrMsg);
           ip.focus();
           return;
        }else{
            var sValue = ip.value;
            var sText = ip.value;
            var oOption = document.createElement("option");
            limitedip.options.add(oOption);
            oOption.innerText=sText;
            oOption.value=sValue;
          }
       }
    }

    function checkip(){
        with ( document.forms[0]) {
    	    var sValue = ip.value;
    	    for(i=0;i<limitedip.length;i++){
    	    if(limitedip.options[i].value==sValue){
    	        return true;
            }
            }
            return false;
    	}
    }

    function delip(){
        var flag=0;
    	with(document.forms[0]){
          if(limitedip.length=="0"){
            alert("������ip��");
          }else{
            for(i=0;i<limitedip.length-1;i++)
            if(limitedip.options[i].selected || flag==1){
              limitedip.options[i].text=limitedip.options[i+1].innerText;
              limitedip.options[i].value=limitedip.options[i+1].value;
              flag=1;
            }
            limitedip.length=limitedip.length-1;
          }
        }
    }

    //�ж�ip��ַ�Ƿ�Ϸ���
    function checkIP2(){
        var sIPAddress = document.forms[0].ip.value;
        var exp=/^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/;
        var reg = sIPAddress.match(exp);
        if(reg==null){
            return true;
        }else{
            return false;
        }
    }
    
  //ִ����������
  function lockupupdate(){
     document.forms[0].op.value = "lockonupdate";
     document.forms[0].forward.value = "Upd_down";

     document.forms[0].submit();
  }

  //ִ�н�������
  function lockdownupdate(){
     document.forms[0].op.value = "lockoffupdate";
     document.forms[0].forward.value = "Upd_down";

     document.forms[0].submit();
  }
        
</script>

<body bgcolor="#ffffff" >
  <html:form method="post" action="/user/F140_UserLimAction.do" >
    <html:hidden property="user_id"/>
    <html:hidden property="op"/>
    <html:hidden property="forward"/>
    <html:hidden property="lock_flag"/>  
  <table width="100%" border="0" cellpadding="0" cellspacing="0" align="center" class="MainTable" >
        <tr valign="middle" class="Grid1">
          <td align="left" width="8%"  height="25">ʱ�����ͣ�</td>
          <td align="left" width="42%">
                <html:select property="timetype" style="width:116px" styleClass="SelectButton" >
                    <html:option value="">&nbsp;</html:option>
                    <html:optionsCollection name="user_F140_UserLimForm" property="parOptions" value="par_id" label="par_name"/>
                </html:select>
          </td>
          <td align="left" width="8%" height="25">IP�����ͣ�</td>
          <td align="left" width="42%">
                <html:select property="iptype" style="width:116px" styleClass="SelectButton" >
                    <html:option value="">&nbsp;</html:option>
                    <html:optionsCollection name="user_F140_UserLimForm" property="parOptions" value="par_id" label="par_name"/>
                </html:select>
          </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
        </tr>
        <tr valign="middle" class="Grid1">
          <td align="left" height="25">��ʼʱ�䣺</td>
          <td align="left"><input maxlength="10" size="17" class="TextBox" name="starttime" readonly="readonly" value="" />
          &nbsp;<img src="<%=request.getContextPath()%>/user/images/calendar.gif" style="cursor:hand" onclick="javaScript:getDATE(document.forms[0].starttime);" alt="">
          </td>
          <td align="left" height="25">&nbsp;</td>
          <td align="left">&nbsp;</td>
        </tr>
        <tr valign="middle" class="Grid1">
          <td align="left" height="25">��ֹʱ�䣺</td>
          <td align="left"><input maxlength="10" size="17" class="TextBox" name="endtime" readonly="readonly" value="" />
          &nbsp;<img src="<%=request.getContextPath()%>/user/images/calendar.gif" style="cursor:hand" onclick="javaScript:getDATE(document.forms[0].endtime);" alt="">
          </td>
          <td align="left" height="25">IP��ַ��</td>
          <td align="left"><input maxlength="15" size="17" class="TextBox" name="ip" value="" /></td>
        </tr>
          <tr>
           <td align="left">&nbsp;</td>
          </tr>
          <tr align="left" class="Grid1">
            <td align="left" height="25">ʱ���б�</td>
            <td align="left" colspan="1">
                 <html:select property="limitedtime" style="width:260px" styleClass="SelectButton" size="5" multiple="true">
                   <html:optionsCollection name="user_F140_UserLimForm" property="limitTimeGroup" value="limittimeid" label="limittimename"/>
                 </html:select>
                 &nbsp;<img src="<%=request.getContextPath()%>/user/images/Insert_icon.gif" onclick="addtime()" alt="" style="cursor:hand">
                 &nbsp;<img src="<%=request.getContextPath()%>/user/images/Delete_icon.gif" onclick="deltime()" alt="" style="cursor:hand">
          </td>
            <td align="left" height="25">IP���б�</td>
            <td align="left" colspan="1">
                 <html:select property="limitedip" style="width:260px" styleClass="SelectButton" size="5" multiple="true">
                   <html:optionsCollection name="user_F140_UserLimForm" property="limitIpGroup" value="limitipid" label="limitipname"/>
                 </html:select>
                 &nbsp;<img src="<%=request.getContextPath()%>/user/images/Insert_icon.gif" onclick="addip()" alt="" style="cursor:hand">
                 &nbsp;<img src="<%=request.getContextPath()%>/user/images/Delete_icon.gif" onclick="delip()" alt="" style="cursor:hand">
          </td>
          </tr>
          <tr>
            <td align="left">&nbsp;</td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td>
            	<input type="button" name="lockup" value="��  ��" onclick="lockupupdate()" class="button" disabled="true"/>
            	<input type="button" name="lockdown" value="��  ��" onclick="lockdownupdate()" class="button" disabled="true"/>
            </td>
          </tr>
        </table>
  </html:form>
</body>
<script language="javaScript" src="<%=request.getContextPath()%>/user/js/lockjudge.js" type=""></script>
<script language="javaScript" src="<%=request.getContextPath()%>/user/js/get_calendar.js" type=""></script>
</html>
