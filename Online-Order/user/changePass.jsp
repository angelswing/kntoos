<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="java.util.*" %>
<%@ page import="com.conant.ums.form.*" %>
<%@ page import="com.conant.ums.data.*" %>
<html>
<head>
    <link href="css/master.css" rel="stylesheet" type="text/css">
	<title>�޸�����</title>
</head>
<script language="javascript">
//ִ�в�ѯ����
function changePass(){
		with (document.forms[0]){
		if( user_passwd.value == "" ){
		        	alert( "���������룡" );
		            user_passwd.focus();
		            return;
		        }
			else if( user_Dpasswd.value == ""  ){
		        	alert( "���ٴ��������룡" );
		            user_Dpasswd.focus();
		            return;
		        }
		        else if( user_passwd.value != user_Dpasswd.value ){
		        	alert( "�����������벻ͬ��" );
		            user_Dpasswd.focus();
		            return;
		        }

		submit();
		}
}
//�رմ���
function winclose(){
	window.close();
}
</script>
<body bgcolor="#ffffff" >
<html:form method="post" action="/ChangePassAction.do" enctype="multipart/form-data">
     <table width="90%" border="0" cellpadding="0" cellspacing="0" align="center" class="MainTable" >
       <tr>
          <td class="orarowheader" colspan="4" height="20">�޸�����</td>
      </tr>
      <tr>
          <td colspan="4" height="5">&nbsp;</td>
      </tr>

        <tr align="left" nowrap="nowrap" class="Grid1" >
          <td  width="30%"  height="25">�����룺</td>
          <td align="left" nowrap="nowrap" class="table-text"  width="70%"><input type="password" name="user_passwd" maxlength="25" size="20" class="TextBox" value="" /></td>
        </tr>
        <tr align="left" nowrap="nowrap" class="Grid1" >
          <td  width="30%" height="25">ȷ�����룺</td>
          <td  align="left" nowrap="nowrap" class="table-text" width="136" width="70%"><input type="password" name="user_Dpasswd" maxlength="25" size="20" class="TextBox" value="" /></td>
        </tr>
        <tr align="left" nowrap="nowrap" class="Grid1" >
          <td  height="25">&nbsp;</td>
          <td  height="25">&nbsp;</td>
        </tr>

        <tr align="left" nowrap="nowrap" class="Grid1" >
         <td align="left" >&nbsp; </td>
          <td align="right" >
          <input type="button" name="butok" value="ȷ  ��" class="button" onclick="changePass()" />
          <input type="reset" name="butret" value="��  ��" class="button"  />
          <input type="button" name="butcancle" value="ȡ  ��" class="button" onclick="winclose()" />
          </td>
        </tr>
    </table>
    <input type="hidden" name="op" value="changePass"/>
</html:form>

</body>
</html>
