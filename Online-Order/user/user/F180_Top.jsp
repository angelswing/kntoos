<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.conant.ums.form.F180_OperMgtForm,com.conant.ums.util.tree.*,java.util.List"%>
<html>
<head >
<META HTTP-EQUIV="Expires" CONTENT="0">
<title>资源树</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/user/css/essp.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/user/css/css.css">
</head>

<body class="navtree" leftMargin=0 topMargin=0 MARGINWIDTH="0" MARGINHEIGHT="0" rightmargin="0">
<html:form action="/user/F180_OperMgtAction.do" method="post" >
    <html:hidden property="op"/>
    <html:hidden property="forward"/>
<table width="100%" height="100%" border="0" cellSpacing=0 cellPadding=0 class="LeftFrame" >
<tr>
<td width="100%" align="center" valign="top">
<table width="100%" height="100%" border="1" cellSpacing="5" cellPadding="5" align="center" bgcolor="#D8AE61">

<script type="text/javascript" language="JavaScript" src="<%=request.getContextPath()%>/user/js/aptree.js"></script>
<script type="text/javascript" language="JavaScript1.2">

setShowExpanders(true);

//全部展开
setExpandDepth(-1);
//采用卡片形式时，点击的样式
setclickType(1);

setKeepState(true);
setShowHealth(false);
setInTable(false);
setTargetFrame("main");
openFolder = "";
closedFolder = "";
plusIcon = "<%=request.getContextPath()%>/user/images/lplus.gif";
minusIcon = "<%=request.getContextPath()%>/user/images/lminus.gif";
blankIcon = "<%=request.getContextPath()%>/user/images/blank20.gif";

</script>

    <td id='mnuList'  class="LeftFrame" background="<%=request.getContextPath()%>/user/images/left_bg.gif">
    <div style="overflow:auto; height:100%">
    <table hight=100% border="0" cellpadding="0" cellspacing="0" >
<%
	F180_OperMgtForm piForm = (F180_OperMgtForm)request.getAttribute( "user_F180_OperMgtForm" );
    TreeNode trRoot = null;
	List rootList = piForm.getRootList();
    TreeNode tmp = (TreeNode)rootList.get(0);
    if ( tmp.getChildren().size() != 0 ) {
%>

<script type="text/javascript" language="JavaScript1.2">
	var companyGif = "";
	var rootGif = "";
	var funcGif = "";

	//root = addRoot(companyGif, '系统操作树', '');
	menu0 = addRoot(companyGif, '', '');
<%
		List menu1List = tmp.getChildren();
    	//TreeNode menu3 = null;
    	for(int i=0;i< menu1List.size();i++){
        	TreeNode menu1 = (TreeNode)menu1List.get(i);
%>
	tmp = addItem(menu0, funcGif, '<%=menu1.getName()%>','');
<%
            List menu2List = menu1.getChildren();
            for(int k=0;k<menu2List.size();k++){
                TreeNode menu2 = (TreeNode)menu2List.get(k);
%>
	menu1 = addItem(tmp, funcGif, '<%=menu2.getName()%>','');
<%
                List menu3List = menu2.getChildren();
				for(int j=0;j<menu3List.size();j++){
                	TreeNode menu3 = (TreeNode)menu3List.get(j);
%>
	menu2 = addItem(menu1, funcGif, '<%=menu3.getName() %>', '');
<%
					List menu4List = menu3.getChildren();
                   for(int l=0;l<menu4List.size();l++) {
                        TreeNode menu4 = (TreeNode)menu4List.get(l);
%>
	menu3 = addItem(menu2, funcGif, "<%=menu4.getName() %>", "");
<%
						List menu5List = menu4.getChildren();
                        for(int m=0;m<menu5List.size();m++) {
                             TreeNode menu5 = (TreeNode)menu5List.get(m);
%>
	menu4 = addItem(menu3, funcGif, "<%=menu5.getName() %>", "");
<%
						}
                    }
				}
			}
    	break;
    	}

%>
    </script>
    <script type="text/javascript" language="JavaScript1.2">
        initialize()
    </script>
<%
    } else {
%>
		<tr>
            <td class="Grid2"><div align="center" ><Strong>无任何系统操作</Strong></div></td>
        </tr>
<%
    }
%>
    </table>
    </div>
    </td>
    </table>
    </td>
    </tr>
</table>
</html:form>
</body>
</html>
