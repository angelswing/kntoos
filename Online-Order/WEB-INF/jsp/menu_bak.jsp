<%@ page contentType="text/html; charset=gb2312"%>
<%@ page language="java" import="com.conant.order.vo.*,java.util.*"%>
<%
  ArrayList arrayList = null;
  if(null != request.getAttribute("success")){
    arrayList = (ArrayList)request.getAttribute("success");
  }
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>Menu</title>



<style type="text/css">
<!--
.STYLE0001 {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 14px;
}
.STYLE0002 {
	font-size: 3pt;
	color: #2C4F8A;
}
.STYLE0005 {
	color: #335EA1;
	font-size: 16px;
}
-->
</style>

<script src="<%=request.getContextPath()%>/resources/js/alai_tree.js" language="javascript"></script>
<script src="<%=request.getContextPath()%>/resources/js/alai_tree_win2k.js" language="javascript"></script>

</head>

<body >

<table width="216" height="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <th width="216" height="400" valign="top" background="<%=request.getContextPath()%>/resources/images/14.png" scope="col">
    
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      
      <tr>
        <th height="30" align="center" bgcolor="#CEE3F8" scope="col"><span class="STYLE0001"><span class="STYLE0005">Function Menu</span> <br>
        </span></th>
      </tr>
      
      <tr>
        <td align="center"></td>
      </tr>
      
          <tr><td height="95%" valign="top">
	     <div id="divTree1"></div>
		<script language="javascript">
		var tree1=new alai_tree_win2k(divTree1)

		var n0=tree1.root		
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
           		var n<%=nodeId%>=tree1.add(n<%=nodePid%>,"","<%=nodeName%>","0","","","")		
		       <%
		      }else if(nodeType == 1){
		       %>
		        n<%=nodePid%>.addLink("<%=request.getContextPath()%>/<%=nodeURL%>","<%=nodeName%>","mainFrame","file");
		    <%
		      }
		    }
		  }
		}
		%>
		</script>
		<%
		if(null == arrayList){
		  out.println("Load MenuTree fail,please contact administrator!");
		}
		%>
         
		</td>
          </tr>
          
      <tr>
        <td align="center"></td>
      </tr>
      
      
    </table>    
    
    </th>
  </tr>
  <tr>
    <th height="400" valign="top" background="<%=request.getContextPath()%>/resources/images/14.png" scope="col">&nbsp;</th>
  </tr>
</table>
</body>
</html>
