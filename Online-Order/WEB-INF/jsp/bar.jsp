<%@ page contentType="text/html; charset=gb2312"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>bar</title>
<style type="text/css">
<!--
#Layer1 {
	position:absolute;
	left:-2px;
	top:299px;
	width:7px;
	height:93px;
	z-index:1;
}
body {
	background-image: url(Images/b.png);
}
-->
</style>
</head>
<script language="JavaScript">
function preloadImg(src)
   {
    var img=new Image();
    img.src=src;    
   }
   preloadImg("<%=request.getContextPath()%>/resources/images/Sidebar_O.gif");
   
    var displayBar=true;
    
    function switchBar(obj)
    {   
        if(displayBar)
        {
            parent.frame.cols="0,12,*";
            displayBar=false;
            obj.src="<%=request.getContextPath()%>/resources/images/Sidebar_O.gif";
            obj.title="open left menu";
         }
          else
          {
            parent.frame.cols="225,12,*";
            displayBar=true;
            obj.src="<%=request.getContextPath()%>/resources/images/Sidebar_C.gif";
            obj.title="close left menu";
          }
   }
</script>
<body>

<div id="Layer1"><img src="<%=request.getContextPath()%>/resources/images/Sidebar_C.gif" width="8" height="47" onclick="switchBar(this)" /> </div>
</body>
</html>
