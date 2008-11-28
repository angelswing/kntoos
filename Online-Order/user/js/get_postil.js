/* name:快捷输入
              (列出常用的批示意见,主任或者领导可以选择)
   author:    wenhui.ke@enogroup.com  2003/09/28
   modified:  raokun                2004.01.07
              对原程序(万年历)做了改造,适应陕西项目.
*/

//创建弹出的窗口
popPostil = createPopup()
PostilBody = popPostil.document.body
PostilBody.style.border="outset 1pt #cccccc"
PostilBody.style.fontSize = "9pt"
PostilBody.style.backgroundColor= "#FFFFFF"
PostilBody.style.cursor="hand"

//鼠标的选择框颜色.移动到相应批示内容时,变颜色
function postilTDMove(e){
	e.style.borderColor="#000000 #CCCCFF #CCCCFF #CCCCFF"
	}


//鼠标的选择框颜色.从相应批示内容时离开时,变颜色
function postilTDOut(e){
	e.style.border="solid 1pt #ffffff"
	}

//关闭弹出的窗口
function hidePostil(){
	popPostil.hide()
	}

//点选某个批示内容时.
function PostilClick(e){

		tarObject.value= e.innerText;
		e.style.borderColor="#94aad6"
                popPostil.hide()
}

//本函数用于页面的调用,为接口.
function getPostil(s){

	tarObject=s

	var e=event.srcElement
	popPostil.show(-70,e.clientHeight+5,160,100,e)

}

var strPostil='<table bgcolor="#FFFFFF" id="whitecell" author="whitecell" border="1"'
    strPostil+=' bordercolorlight="#0053a6" bordercolordark="#ffffff" cellpadding="1"'
    strPostil+=' cellspacing="0" style="font-size:9pt;height:100;cursor:hand;">'
    strPostil+='<tr height="25"><td nowrap  align="center" bgcolor="#94aad6" '
    strPostil+='><font size="3" >&nbsp;&nbsp;&nbsp请选择常用批示意见&nbsp;&nbsp;&nbsp;&nbsp;</font></td></tr>'

 		strPostil+='<tr align=left>'
		strPostil+='<td nowrap bgcolor="#ffFFFF" COLOR="RED" style="border:solid 1pt #FFFFFF" onmouseover="parent.postilTDMove(this)"'
		strPostil+=' onmouseout="parent.postilTDOut(this)" onclick="parent.PostilClick(this)"><FONT COLOR="RED">'
		strPostil+='同意'+'</FONT></td>'

                strPostil+='<tr align=left>'
		strPostil+='<td nowrap bgcolor="#ffFFFF" COLOR="RED" style="border:solid 1pt #FFFFFF" onmouseover="parent.postilTDMove(this)"'
		strPostil+=' onmouseout="parent.postilTDOut(this)" onclick="parent.PostilClick(this)"><FONT COLOR="RED">'
		strPostil+='不同意'+'</FONT></td>'
		strPostil+='</tr>'

		strPostil+='<tr align=left>'
		strPostil+='<td nowrap  COLOR="RED" bgcolor="#ffFFFF" style="border:solid 1pt #FFFFFF" onmouseover="parent.postilTDMove(this)"'
		strPostil+=' onmouseout="parent.postilTDOut(this)" onclick="parent.PostilClick(this)"><FONT COLOR="RED">'
		strPostil+='请开会讨论'+'</FONT></td>'


    strPostil+='<tr align=center bgcolor="#94aad6">'
    strPostil+='<td  style="color: #FFFFFF;  font-family: Arial, Helvetica, sans-serif;font-size: 12px;font-style: normal;font-weight: bold;border:1pt solid #94aad6"'
    strPostil+=' onclick="this.style.borderColor=\'#94aad6\';parent.hidePostil();"'
    strPostil+=' onmouseover="parent.postilTDMove(this)"'
    strPostil+=' onmouseout="this.style.borderColor=\'#94aad6\'">关闭'
    strPostil+='<td style="border:1pt solid #94aad6">&nbsp;</td><tr>'
    strPostil+='</table>'

    PostilBody.innerHTML=strPostil
