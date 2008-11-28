function setfocus() {
	var x;
	x=event.keyCode;
	if(x==13){
		document.gprs.gprs_btn.focus();
	}
}

function isNumber(num){
	if(num < "0"||num > "9"){
		return false;
	}else{
		return true;
	}
}

function gprs_query_onclick(){
	var msisdn=document.gprs.msIsdn.value;
	var start_time=document.gprs.begTimeStr.value;
	var end_time=document.gprs.endTimeStr.value;
	var noticeText = "";
	var flag = true;
	var regIP = new RegExp("^\(13[4-9]\\d{8}\)|\(159\\d{8}\)$");

	if (msisdn == ""){
		noticeText += "�ֻ����벻��Ϊ��\n";
		flag = false;
	} else if (!isNumber(msisdn)){
		noticeText += "�ֻ��������Ϊ����\n";
		flag = false;
	} else if (msisdn.length<11){
		noticeText += "�ֻ����벻��11λ\n";
		flag = false;
	} else if (isPhoneNum(msisdn) == false){
		noticeText += "�ֻ�����ӦΪ134-139�Ŷλ�159�Ŷ�\n";
		flag = false;
	}

	if(start_time == "") {
		noticeText += "��ʼʱ�䲻��Ϊ��\n";
		flag = false;
	}
	if(start_time != "") {
		if(start_time.length==19){
			for(i=0;i<start_time.length;i++)
			{
				var ch = start_time.charAt(i);

				if( (i==0||i==1||i==2||i==3||i==5||i==6||i==8||i==9||i==11||i==12||i==14|i==15||i==17||i==18)&&isNumber(ch) ){
					continue;
				}else if( (i==4||i==7)&&ch=="-" ){
					continue;
				}else if( (i==13||i==16)&&ch==":" ){
					continue;
				}else if( (i==10)&&ch==" " ){
					continue;
				}else{
					noticeText += "��ʼʱ���ʽ��������YYYY-MM-DD hh:mm:ss\n";
					flag = false;
				}
			}
		}else{
			noticeText += "��ʼʱ���ʽ��������YYYY-MM-DD hh:mm:ss\n";
			flag = false;
		}
	}

	if(end_time == "") {
		noticeText += "����ʱ�䲻��Ϊ��\n";
		flag = false;
	}
	if(end_time != "") {
		if(end_time.length==19){
			for(i=0;i<end_time.length;i++)
			{
				var ch = end_time.charAt(i);

				if( (i==0||i==1||i==2||i==3||i==5||i==6||i==8||i==9||i==11||i==12||i==14|i==15||i==17||i==18)&&isNumber(ch) ){
					continue;
				}else if( (i==4||i==7)&&ch=="-" ){
					continue;
				}else if( (i==13||i==16)&&ch==":" ){
					continue;
				}else if( (i==10)&&ch==" " ){
					continue;
				}else{
					noticeText += "����ʱ���ʽ��������YYYY-MM-DD hh:mm:ss\n";
					flag = false;
				}
			}
		}else{
			noticeText += "����ʱ���ʽ��������YYYY-MM-DD hh:mm:ss\n";
			flag = false;
		}
	}

	if(checkCompareDate(start_time,end_time) == false){
		noticeText += "��ʼʱ�䲻�ܴ��ڽ���ʱ��\n";
		flag = false;
	}

	if(!flag){
		alert(noticeText);
		return false;
	}
}

function checkTime(start_time,end_time){	
	if(checkCompareDate(start_time,end_time) == false){		
		return true;
	}
	return false;
}

function isPhoneNum(num){	
	var regNum = new RegExp("^\(13[4-9]\\d{8}\)|\(159\\d{8}\)$");
	if (isNaN(num)){
		return(false)
	}
	else if (num.length!= 11){
		return(false)
	}

	if(!regNum.test(num))
	{
		return(false)
	}
	else {
		return(true)
	}
}

function fPopUpCalendarDlg_8(ctrlobj)
{
	showx = event.screenX - event.offsetX - 4 - 210 ; // + deltaX;
	showy = event.screenY - event.offsetY + 18; // + deltaY;
	newWINwidth = 210 + 4 + 18;
    var formfield = eval("document.forms[0]."+ctrlobj);
	retval = window.showModalDialog("./CalendarDlg_8.htm", "Calendar", "dialogWidth:235px; dialogHeight:250px; dialogLeft:"+showx+"px; dialogTop:"+showy+"px; status:no; directories:yes;scrollbars:no;Resizable=no; "  );
	if( retval != null ){
		formfield.value = retval+":00";
	}else{	}
}

function checkCompareDate(dtStart,dtEnd)
{
	var temp,s;
	temp=new String(dtStart);

	s = new String("");
	for(var i=0;i<=temp.length-1;i++)
	{
		if(temp.charAt(i)=="-" || temp.charAt(i)=="/")
			s=s+"/";
		else
		{

			s=s+temp.charAt(i);
		}
	}
	dtOne=new Date(s);
	
	temp=new String(dtEnd);
	s=new String("");
	for(var i=0;i<=temp.length-1;i++)
	{
		if(temp.charAt(i)=="-" || temp.charAt(i)=="/")
			s=s+"/";
		else
		{
			s=s+temp.charAt(i);
		}
	}
	dtTwo=new Date(s);

	if(dtOne.valueOf()>dtTwo.valueOf())
	{
		return false;
	}
	return true;
}

//ȥ���ִ���ߵĿո� 
function lTrim(str) { 
	if (str.charAt(0) == " "){ //����ִ���ߵ�һ���ַ�Ϊ�ո� 
	 
		str = str.slice(1);//���ո���ִ���ȥ�� 
		str = lTrim(str); //�ݹ���� 
	} 
	return str; 
} 

//ȥ���ִ��ұߵĿո� 
function rTrim(str) { 
	var iLength; 
	
	iLength = str.length; 
	if (str.charAt(iLength - 1) == " "){  //����ִ��ұߵ�һ���ַ�Ϊ�ո� 
		str = str.slice(0, iLength - 1);//���ո���ִ���ȥ�� 
		str = rTrim(str); //�ݹ���� 
	} 
	return str; 
} 

//ȥ���ִ����ߵĿո� 
function trim(str) 
{ 
return lTrim(rTrim(str)); 
} 