<%@page contentType="text/html; charset=GB2312"%>
<%@page import="com.conant.ums.data.LoginData,com.conant.ums.util.tree.*,com.conant.ums.util.ComGlobal,java.util.List"%>
<%
  response.setHeader("Cache-Control", "no-store");
  response.setHeader("Pragma", "no-cache");
  response.setDateHeader("Expires", 0);
%>
<html>
<head>
<META HTTP-EQUIV="Expires" CONTENT="0">
<title>无标题文档</title>
<style type="">
  .navPoint{font-family: Webdings;font-size:9pt;color:white;cursor:hand;}
  p{
  font-size:9pt;
  }
</style>
<link rel="stylesheet" type="text/css" href="css/essp.css">
<link rel=stylesheet href="css/css.css">

<style type="text/css">
<!--
.style1 {color: #990000}
-->
</style>
</head>
<SCRIPT language=javascript type="">

function switchSysBar(){
	if (switchPoint.innerText==3){
		switchPoint.innerText=4
		document.all("mnuList").style.display="none"
		parent.bottom.cols="15,*"
	}
	else{
		switchPoint.innerText=3
		document.all("mnuList").style.display=""
		parent.bottom.cols="180,*"
	}
}

/************************************************
*   Stuff from menu_functions.js *
************************************************/

// Global variables

var skin;
var isNav4, isIE, isDomvar, foropera = window.navigator.userAgent.toLowerCase();
// alert(foropera);

var itsopera = foropera.indexOf("opera",0) + 1;
var itsgecko = foropera.indexOf("gecko",0) + 1;
var itsmozillacompat = foropera.indexOf("mozilla",0) + 1;
var itsmsie = foropera.indexOf("msie",0) + 1;
if (itsopera > 0){
                //thebrowser = 3;
                //alert("its opera");
                isNav4 = true;
        }
        if (itsmozillacompat > 0){
                //alert("its mozilla compatible");
                if (itsgecko > 0) {
                        //thebrowser = 4
                       // alert("its gecko");
                       isNav4 = true;
                       isDom = true;
                }
                else if (itsmsie > 0) {
                      //  alert("its msie");
                       // thebrowser = 2
                        isIE = true;
                }
                else {
                        if (parseInt(navigator.appVersion) < 5) {
                                // alert("its ns4.x")
                                //thebrowser = 1
                                isNav4 = true;
                        }
                        else {
                                thebrowser = 2
                                isIE = true;
                        }
                }
        }
function NSResize() {
   top.header.location.reload();
   top.detail.location.reload();
   top.HelpNavigation.location.reload();
   return false;
}

if (isNav4 && !isDom) {
   window.captureEvents(Event.RESIZE);
   window.onresize = NSResize;
}

</SCRIPT>
<body class="navtree" leftMargin=0 topMargin=0 MARGINWIDTH="0" MARGINHEIGHT="0" rightmargin="0">
<table width="100%" border="0" cellSpacing=0 cellPadding=0 height="100%">
<script type="text/javascript" language="JavaScript" src="js/aptree.js"></script>
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
    plusIcon = "images/lplus.gif";
    minusIcon = "images/lminus.gif";
    blankIcon = "images/blank20.gif";

</script>
<td id='mnuList' class="LeftFrame">&nbsp;
    <div id="Layer2" style="position:absolute; left:5px; top:0px; height:80%;overflow:hidden;">
      <table height=100% border="0" cellpadding="0" cellspacing="0" >
<script type="text/javascript" language="JavaScript1.2">
	var companyGif = "";
	var rootGif = "";
	var funcGif = "";
	root = addRoot(companyGif, '','');
<%
	LoginData loginData = (LoginData)session.getAttribute("LoginData");
	if ( loginData == null ) {
%>
		parent.parent.location = "login.jsp";
<%
	} else {

    TreeNode trRoot = null;
    List rootList = loginData.getDeptList();
    trRoot = (TreeNode)rootList.get(0);
    List menu1List = trRoot.getChildren();

    TreeNode menu3 = null;
    for(int i=0;i< menu1List.size();i++){
        TreeNode menu1 = (TreeNode)menu1List.get(i);

            List menu2List = menu1.getChildren();
            for(int k=0;k<menu2List.size();k++){
                TreeNode menu2 = (TreeNode)menu2List.get(k);
%>
	menu = addItem(root, rootGif, '<%=menu2.getName()%>','');
<%         	List menu3List = menu2.getChildren();
			for(int j=0;j<menu3List.size();j++){
                menu3 = (TreeNode)menu3List.get(j);
                //String link = "/" + ComGlobal.MODULE + menu3.getLink();
%>
    menu1 = addItem(menu, funcGif, "<%=menu3.getName() %>", "<%=menu3.getLink()%>");
<%			}
		}
    	break;

    }
    }
%>
</script>
<script type="text/javascript" language="JavaScript1.2">
    initialize()
    //刷新右边窗口
    parent.document.all.main.src="user/main.html";
</script>
      </table>
   </div>
  </td>
  <td class="HalfWindow" width="15" height="100%" vAlign="middle" onclick="switchSysBar()" align="center">
    <SPAN class="navPoint" id="switchPoint">3</SPAN>
  </td>
</table>
</body>
</html>
