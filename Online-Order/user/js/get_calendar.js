/*name:\uFFFDf年\uFFFDv控件
author:饶琨
email:cindy1417@126.com
update-date:2005/04/28
update-reason:由中文改成英文，并根据需要改变颜色风格
note:IE6.0下\uFFFDy\uFFFD\uFFFD通\uFFFD^
*/

//\uFFFD\uFFFD建\uFFFDf年\uFFFDv
popCale = createPopup()
CaleBody = popCale.document.body
CaleBody.style.border="outset 1pt #cccccc"
CaleBody.style.fontSize = "9pt"
CaleBody.style.backgroundColor= "#FFFFFF"
CaleBody.style.cursor="hand"
//鼠标的选择框颜色
function caleTDMove(e){
	e.style.borderColor="#000000 #CCCCFF #CCCCFF #CCCCFF"
	}

function caleTDOut(e){
	e.style.border="solid 1pt #ffffff"
	}

function hideCale(){
	popCale.hide()
	}

function yearChange(){  //重\uFFFD\uFFFD年份的<SELECT>
	var e=popCale.document.all('yearSel')
	e.options.length=0
	for (i=nYear-4;i<parseInt(nYear)+1;i++){
		e.options.length++
		e.options[e.options.length - 1].value=i
		e.options[e.options.length - 1].text=i
	}
    e.selectedIndex=4
}

function CaleClick(e){  //\uFFFDx定某一天\uFFFDr
	if(isNaN(parseInt(e.innerText))){	//如果是\uFFFDc\uFFFD\uFFFD"今天"而\uFFFD|\uFFFDl
		var d=new Date()
		var vNowMonth = d.getMonth()+1;
		if (vNowMonth.toString().length<2){
			vNowMonth = ""+0+""+vNowMonth;
		}
		var vNowDay = d.getDate();
		if (vNowDay.toString().length<2){
			vNowDay = ""+0+""+vNowDay;
		}
		tarObject.value=d.getFullYear()+'-'+(vNowMonth)+'-'+ vNowDay
		e.style.borderColor="#94aad6"
	}
	else{
		var vNowMonth = parseInt(nMonth)+1;
		if (vNowMonth.toString().length<2){
			vNowMonth = ""+0+""+vNowMonth;
		}
		var vNowDay = parseInt(e.innerText);
		if (vNowDay.toString().length<2){
			vNowDay = ""+0+""+vNowDay;
		}
		tarObject.value=nYear+'-'+(vNowMonth)+'-'+vNowDay
		e.style.borderColor="#FFFFFF"
	}
	popCale.hide()
}

function changeYM(e,n){		//改\uFFFD\uFFFD年份或月份\uFFFDo或\uFFFDc>>和<<\uFFFDr\uFFFD|\uFFFDl
	if (e.tagName=='SELECT'){
		if(e.value.length==4){
			nYear=e.value;
			yearChange()
		}
		else{
			nMonth=e.value
		}
	}
	else{nMonth=parseInt(nMonth)+n
		switch(nMonth){
		case 12:nYear++;nMonth=0;
			yearChange()
			popCale.document.all('monthSel').selectedIndex=0;
			break;
		case -1:nYear--;nMonth=11;
			yearChange()
			popCale.document.all('monthSel').selectedIndex=11;
			break;
		default:popCale.document.all('monthSel').selectedIndex=nMonth;
			break;
		}
	}
	rewriteCale()
}

function rewriteCale(){		//重\uFFFD\uFFFD日\uFFFDv
	var newTb,newTR,newTD
	newTb=popCale.document.all('whitecell').tBodies[0]
	for (i=0;i<=newTb.rows.length;i++){
		newTb.deleteRow(2)
	}
	qtyDay=(new Date(nYear,parseInt(nMonth)+1,1) - new Date(nYear,nMonth,1))/24/3600/1000
	fDay=1-(new Date(nYear,nMonth,1).getDay())
	for (i=0;i<42;i++){
		if (i % 7==0){
			newTR=newTb.insertRow(newTb.rows.length - 2)
		}
		newTD=newTR.insertCell()
		if (fDay>0 && fDay<=qtyDay){
			newTD.innerText=fDay
		}
		if (i % 7==0){
			newTD.style.color="red"
		}else if (i % 7==6){
			newTD.style.color="red"
		}else{
		}
		if(fDay==ftoday){
			newTD.style.color="white"
			newTD.style.backgroundColor="#0a246a"
		}
		newTD.style.border="solid 1pt #FFFFFF"
		newTD.align='center'
		newTD.onmouseover=Function("caleTDMove(this)")
		newTD.onmouseout=Function("caleTDOut(this)")
		newTD.onclick=Function("CaleClick(this)")

		fDay++
	}
}

function getDATE(s){	//\uFFFDO定那\uFFFD\uFFFD控件加入\uFFFDf年\uFFFDv
	tarObject=s

	var d=new Date()
	nYear=d.getFullYear();nMonth=d.getMonth();

	var e=event.srcElement
	popCale.show(-70,e.clientHeight+5,164,170,e)
	popCale.document.all('monthSel').selectedIndex=nMonth
	yearChange()
	rewriteCale()
}

var d=new Date()
var nYear=d.getFullYear()
var nMonth=d.getMonth()
var arMonth=new Array('01','02','03','04','05','06','07','08','09','10','11','12')
var qtyDay=(new Date(nYear,parseInt(nMonth)+1,1) - new Date(nYear,nMonth,1))/24/3600/1000
var strCale='<table bgcolor="#FFFFFF" id="whitecell" author="whitecell" border="1"'
    strCale+=' bordercolorlight="#0053a6" bordercolordark="#ffffff" cellpadding="1"'
    strCale+=' cellspacing="0" style="font-size:9pt;height:170;cursor:hand;">'
    strCale+='<tr height="20"><td bgcolor="#94aad6" style="color:#00ffff;border:solid 1pt #94aad6"'
    strCale+=' onclick="parent.changeYM(this,-1)"><<</td>'
    strCale+='<td colspan=5 bgcolor="#94aad6" align="center" style="border:solid 1pt #94aad6">'
    strCale+='<select id="yearSel" onchange="parent.changeYM(this)" style="font:9pt;"></select>'

    strCale+='<select id="monthSel" onchange="parent.changeYM(this)" style="font:9pt;">'
	for (i=0;i<12;i++){
		strCale+='<option value='+i+(i==nMonth+1?' selected':'')+'>'+arMonth[i]+'</option>'
	}
    strCale+='</select></td><td bgcolor="#94aad6" style="color:#00ffff;border:solid 1pt #94aad6"'
    strCale+=' onclick="parent.changeYM(this,1)">>></td></tr>'
    strCale+='<tr align=center >'
    /*modified by raokun 2005.11.31 将英文修改为中文
    strCale+='<td width=18 style="font-size :9px;font-style : normal;font-family: Arial, Helvetica, sans-serif;"><FONT COLOR="RED">SUN</FONT>'
    strCale+='<td width=18 style="font-size :9px;font-style : normal;font-family: Arial, Helvetica, sans-serif;">MON'
    strCale+='<td width=18 style="font-size :9px;font-style : normal;font-family: Arial, Helvetica, sans-serif;">TUE'
    strCale+='<td width=18 style="font-size :9px;font-style : normal;font-family: Arial, Helvetica, sans-serif;">WED'
    strCale+='<td width=18 style="font-size :9px;font-style : normal;font-family: Arial, Helvetica, sans-serif;">THU'
    strCale+='<td width=18 style="font-size :9px;font-style : normal;font-family: Arial, Helvetica, sans-serif;">FRI'
    strCale+='<td width=18 style="font-size :9px;font-style : normal;font-family: Arial, Helvetica, sans-serif;"><FONT COLOR="RED">SAT</FONT></td></tr>'
     */
    strCale+='<td width=18 style="font-size :9px;font-style : normal;font-family: Arial, Helvetica, sans-serif;"><FONT COLOR="RED">日</FONT>'
    strCale+='<td width=18 style="font-size :9px;font-style : normal;font-family: Arial, Helvetica, sans-serif;">一'
    strCale+='<td width=18 style="font-size :9px;font-style : normal;font-family: Arial, Helvetica, sans-serif;">二'
    strCale+='<td width=18 style="font-size :9px;font-style : normal;font-family: Arial, Helvetica, sans-serif;">三'
    strCale+='<td width=18 style="font-size :9px;font-style : normal;font-family: Arial, Helvetica, sans-serif;">四'
    strCale+='<td width=18 style="font-size :9px;font-style : normal;font-family: Arial, Helvetica, sans-serif;">五'
    strCale+='<td width=18 style="font-size :9px;font-style : normal;font-family: Arial, Helvetica, sans-serif;"><FONT COLOR="RED">六</FONT></td></tr>'



var fDay=1-(new Date(nYear,nMonth,1).getDay())
var ftoday=d.getDate()
for (i=0;i<42;i++){

	if (i % 7==0){
		strCale+='<tr align=center>'
		strCale+='<td bgcolor="#ffFFFF" COLOR="RED" style="border:solid 1pt #FFFFFF" onmouseover="parent.caleTDMove(this)"'
		strCale+=' onmouseout="parent.caleTDOut(this)" onclick="parent.CaleClick(this)"><FONT COLOR="RED">'
		strCale+=(fDay>0&&fDay<=qtyDay?fDay:'')+'</FONT></td>'

	}else if (i % 7==6){
		strCale+='<td bgcolor="#ffFFFF" COLOR="RED" style="border:solid 1pt #FFFFFF" onmouseover="parent.caleTDMove(this)"'
		strCale+=' onmouseout="parent.caleTDOut(this)" onclick="parent.CaleClick(this)"><FONT COLOR="RED">'
		strCale+=(fDay>0&&fDay<=qtyDay?fDay:'')+'</FONT></td>'
		strCale+='</tr>'
	}else{

		strCale+='<td style="border:solid 1pt #FFFFFF" onmouseover="parent.caleTDMove(this)"'
		strCale+=' onmouseout="parent.caleTDOut(this)" onclick="parent.CaleClick(this)">'
		strCale+=(fDay>0&&fDay<=qtyDay?fDay:'')+'</td>'

	}

	fDay++
}

    strCale+='<tr align=center bgcolor="#94aad6">'
    strCale+='<td style="border:1pt solid #94aad6">&nbsp;'
    strCale+='<td colspan=2 style="color: #FFFFFF; font-family: Arial, Helvetica, sans-serif;font-size: 12px;font-style: normal;font-weight: bold;border:1pt solid #94aad6" onmouseover="parent.caleTDMove(this)"'
    strCale+=' onmouseout="this.style.borderColor=\'#94aad6\'"'
    strCale+=' onclick="parent.CaleClick(this)">Today'
    strCale+='<td style="border:1pt solid #94aad6">&nbsp;'
    strCale+='<td colspan=2 style="color: #FFFFFF;  font-family: Arial, Helvetica, sans-serif;font-size: 12px;font-style: normal;font-weight: bold;border:1pt solid #94aad6"'
    strCale+=' onclick="this.style.borderColor=\'#94aad6\';parent.hideCale();"'
    strCale+=' onmouseover="parent.caleTDMove(this)"'
    strCale+=' onmouseout="this.style.borderColor=\'#94aad6\'">Close'
    strCale+='<td style="border:1pt solid #94aad6">&nbsp;</td><tr>'
    strCale+='</table>'
    CaleBody.innerHTML=strCale
    yearChange()
