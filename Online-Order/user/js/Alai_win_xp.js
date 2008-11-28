/*******************************************************************************
		阿赖浮动层窗口控件程序XP风格版 由赖国欣设计于2003年5月，保留所有权利！
*********************************************************************************/
var MAX_Z_INDEX=30
var IMG_PATH="images/"
function alai_win_xp(content,title,width,height,top,left)
{
	if(typeof(content)=="string")
	{
		var obj=document.createElement("div")
		obj.innerHTML=content
		document.body.insertAdjacentElement("beforeEnd",obj)
		content=obj
	}
	else if(typeof(content)!="object"){return}
	
	var buttons=[]
	var winPanel=document.createElement("div")
	var winBody=document.createElement("div")
	var winBar=document.createElement("div")
	var winBottom=document.createElement("div")
	var winTitle=document.createElement("div")
	var run=function(cmd,a0,a1,a2)
	{
		if(typeof(cmd)=="string")
		{	try{return eval(cmd);}
			catch(E){alert("run script string error:\n"+cmd);}
		}
		else if(typeof(cmd)=="function"){return cmd(a0,a1,a2);}
	}
	
	content.applyElement(winPanel,"outside")
	winPanel.applyElement(winBody,"outside")
	winBody.insertAdjacentElement("afterBegin",winBar)
	winBody.insertAdjacentElement("beforeEnd",winBottom)
	
	var IMG_PATH="images/"
	var script=document.getElementsByTagName("SCRIPT");
	for(var i=0;i<script.length;i++)
	{
		var s=script[i].src.toLowerCase()
		if(s.indexOf("alai_win_xp.js")!=-1){IMG_PATH=s.replace("alai_win_xp.js","")+"images/"}
	}	
	var s="       <table style=\"width: 100%; border-style: none; border-width:0\" height=\"35\" background=\"" +IMG_PATH+ "bg.gif\" border=0 cellpadding=\"0\" cellspacing=\"0\"><tr>   "
	s+="         <td height=\"15\" valign=\"top\" background=\"barbg/barbg.gif\"><img border=\"0\" src=\"" +IMG_PATH+ "left.gif\"></td>   "
	s+="         <td style=\"width: 100%\" background=\"" +IMG_PATH+ "barbg.gif\" valign=\"top\" height=\"15\" align=\"center\"></td>   "
	s+="         <td height=\"15\" valign=\"top\"><img border=\"0\" src=\"" +IMG_PATH+ "xpclose.gif\" id=\"flow_close\" style=\"cursor:default\"> </td>   "
	s+="       </tr></table>   "
	winBar.innerHTML=s
	var oTmp=document.all[winBar.sourceIndex+1].cells[1]
	oTmp.applyElement(winTitle,"inside")
	winBody.style.cssText="position:absolute;visibility:hidden;background-color:#eeeeee;color:black;text-align:center;font-size:10pt;border:3 solid blue;"
	winBar.style.cssText="width:100%;cursor:default;position:relative;top:0;left:0;"
	winPanel.style.cssText="width:100%;text-align:center;margin:2 4 1 4;overflow:auto;text-align:center;"
	winTitle.style.cssText="color:white;font-weight:bold;margin-top:4;padding-top:2;font-size:11pt;font-family:arial;"
	winBottom.style.cssText="margin:0;height:30;width:100%;text-algin:center;"
	var win=this
 	winBody.onmousedown=function(){if(winBody.style.zIndex<MAX_Z_INDEX)winBody.style.zIndex=++MAX_Z_INDEX}
	with(winBar)
	{
		var x1,y1,l1,t1,mflag=false
		onmousedown = function()
		{	if(event.button!=1)return;
			this.setCapture();this.style.cursor='move';mflag=true;
			x1=event.clientX;y1=event.clientY;l1=winBody.style.pixelLeft;t1=winBody.style.pixelTop;
		}	
  		onmousemove = function()
  		{	if(mflag)
  			{	winBody.style.pixelLeft=l1+event.clientX-x1;
  				winBody.style.pixelTop=t1+event.clientY-y1;
  			}
  		}
  		onmouseup =function(){this.releaseCapture();mflag=false;this.style.cursor='default';}
  		onSelectStart=function(){return false;}
  		onclick=function(){if(event.srcElement.id=="flow_close")win.hide();}
 	}
	this.copywrite="Copywrite by Alai(赖国欣) (c)2003，All right reserved!"
	this.buttons=buttons
	this.body=winBody
	this.content=content
	this.panel=winPanel
	this.bar=winBar
	this.bottom=winBottom
	this.setTitle=function(val){if(val!=null)winTitle.innerHTML=val;}
	this.setX=function(val){var x=document.body.offsetWidth-winBody.offsetWidth;winBody.style.pixelLeft=val!=null?val:(x>0?parseInt(x/2):0);}
	this.setY=function(val){var t=document.body.offsetHeight-winBody.offsetHeight;winBody.style.pixelTop=val!=null?val:(t>0?parseInt(t/2):0);}
	this.setW=function(val){winBody.style.pixelWidth=val==null?500:val;}
	this.setH=function(val)
	{	var h=val==null?300:val;winBody.style.pixelHeight=h;
		h-=winBar.offsetHeight+winBottom.offsetHeight;winPanel.style.pixelHeight=h>0?h:25;
	}
	this.hide=function(){winBody.style.display="none";}
	this.show=function(){winBody.style.display="block";winBody.style.zIndex=++MAX_Z_INDEX;}
	this.addButton=function(text,exeType,exeArg,width,target)
	{
		var btn=document.createElement("button")
		winBottom.insertAdjacentElement("beforeEnd",btn)
		btn.value=text
		btn.style.cssText="height:23;border:1 outset blue;margin:5;filter:glow(color=yellow,strength=5) alpha(opacity=80);font-size:11pt;"
		btn.style.pixelWidth=width==null?80:width;
		btn.onmouseover=function(){this.style.color="blue";this.style.borderTop=this.style.borderBottom="1 solid blue";this.style.filter="glow(color=red,strength=3)";}
		btn.onmouseout=function(){this.style.color="black";this.style.border="1 outset blue";this.style.filter="glow(color=yellow,strength=5) alpha(opacity=80)";}
		btn.execute=new Function()
		exeType=exeType==null?"":exeType
		
		switch(exeType.toLowerCase())
		{
			case "hide": 
				btn.execute=win.hide;break;
			case "js": 
				if(typeof(exeArg)!="string")break;
				btn.execute=function(){eval(exeArg);}
				break;
			case "url":
				if(typeof(exeArg)!="string")break;
				if(target==null||target=="")target="_blank";
				btn.execute=function(){open(exeArg,target);}
				break;
			case "open":
				if(typeof(exeArg)!="string")break;
				btn.execute=function(){open(exeArg,"_blank");};break;
		}
		buttons[buttons.length]=btn
		btn.onclick=function(){run(btn.execute)}
		return btn
	}

	this.setTitle(title)
	this.setW(width)
	this.setH(height)
	this.setX(left)
	this.setY(top)
	winBody.style.visibility="visible"
	winBody.style.display="none"
}

function MsgBox(msg)
{
	winLoad=new alai_win_xp(msg,"Message Box",350,60)
	winLoad.addButton("OK","hide")
	winLoad.show()
}