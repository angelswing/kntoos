<%@ page contentType="text/html; charset=gb2312"%>
<%@ page language="java" import="com.conant.order.vo.*,java.util.*"%>
<%
  ArrayList arrayList = null;
  if(null != request.getAttribute("success")){
    arrayList = (ArrayList)request.getAttribute("success");
  }
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>left</title>



<style type="text/css">
<!--
.test{
height:30px;
line-height:30px;
overflow:hidden;
color:#333333;
font-family: Arial, Helvetica, sans-serif;
font-size: 12px;
}

.test1{

width:150px;
height:30px;
line-height:30px;
overflow:hidden;
background-color:#cee3f8;
color:#FFFFFF;
font-family: Arial, Helvetica, sans-serif;
font-size:12px;}
.STYLE1 {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 14px;
}
.STYLE2 {
	font-size: 3pt;
	color: #2C4F8A;
}
.STYLE3 {
	height: 30px;
	line-height: 30px;
	overflow: hidden;
	color: #333333;
	font: Arial, Helvetica, sans-serif 12px;
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
}
a:link {
	
	text-decoration: none;
	color: #333333;
}
a:visited {
	
	text-decoration: none;
	color: #333333;
}
a:hover {
	
	text-decoration: none;
	color: #333333;
}
a:active {
	
	text-decoration: none;
	color: #333333;
}
.STYLE5 {
	color: #335EA1;
	font-size: 16px;
}
#Layer1 {
	position:absolute;
	left:218px;
	top:311px;
	width:15px;
	height:48px;
	z-index:1;
}
-->
</style>
</head>

<body >
<table width="216" height="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <th width="216" height="400" valign="top" background="<%=request.getContextPath()%>/resources/images/14.png" scope="col">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <th height="30" align="center" bgcolor="#CEE3F8" scope="col">
        <span class="STYLE1"><span class="STYLE5">Function Menu
        </span> <br>
        </span>
        </th>
      </tr>

		<%
		if(null != arrayList && arrayList.size() > 0){
		
		  System.out.println("tree node size ===" + arrayList.size());
		
		  int nodeType = 0;
		  String nodeName = "";
		  String nodePid = "";
		  String nodeURL = "";
		  String nodeId = "";
		  boolean nodeStatus = false;
		  
		  for(int i = 0;i < arrayList.size();i ++){
		  
		    FunctionInfo functionInfo = (FunctionInfo)arrayList.get(i);
                    		    
		    nodeType = functionInfo.getFunc_Type();
		    nodeName = functionInfo.getFunc_Name();
		    nodePid = String.valueOf(functionInfo.getFunc_Pid());
		    nodeURL = functionInfo.getFunc_URL();
		    nodeId = String.valueOf(functionInfo.getId());
		    
		    //System.out.println("type=" + nodeType + "|" + "name=" + nodeName + "|" + "pid=" + nodePid + "|" + "url=" + nodeURL + "|" + "id=" + nodeId);
		    
		    if(!"-1".equals(nodePid)){
		      if(nodeType == 0){	    
		%>
      <tr>
        <td height="31" align="center" valign="bottom">
        <div align="center" class="STYLE3" onMouseOver="this.className='test1'" onMouseOut="this.className='test'">
        <%=nodeName%>
        </div>
        </td>
      </tr>
      
      <tr>
        <td align="center"><span class="STYLE2">..............................................................</span></td>
      </tr>
      		
		       <%
		      }else if(nodeType == 1){
		       %>
      <tr>
        <td height="30" align="center">
        <div align="center" class="STYLE3" onMouseOver="this.className='test1'" onMouseOut="this.className='test'">
        <a href="<%=request.getContextPath()%>/<%=nodeURL%>" target="mainFrame"><%=nodeName%></a>
        </div>
        </td>
      </tr>
      
      <tr>
        <td align="center"><span class="STYLE2">..............................................................</span></td>
      </tr>
		    <%
		      }
		    }
		  }
		}
		%>      
    </table>    
    </th>
  </tr>
  <tr>
    <th height="400" valign="top" background="<%=request.getContextPath()%>/resources/images/14.png" scope="col">&nbsp;</th>
  </tr>
</table>
</body>
</html>
