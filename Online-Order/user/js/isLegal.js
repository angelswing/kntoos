//ȥ����ߵĿո�
function TrimLeft(str){
	if(str.charAt(0) == " "){
		str = str.slice(1);
		str = TrimLeft(str);
	}
 	return str;
}

//ȥ���ұߵĿո�
function TrimRight(str){
	//alert("str.charAt( str.length - 1 )=" + str.charAt( str.length - 1 )+".");
	if(str.charAt( str.length - 1 ) == " "){
		str = str.slice(0,str.length - 1);
		str = TrimRight(str);
	}
 	return str;
}

//���������С
function resizeWindow(){
	var aw = screen.availWidth;

	var ah = screen.availHeight;

	window.moveTo(0, 0);

	window.resizeTo(aw, ah);
}

 //�������
 	function show(seq,rows,formId){
		for ( var i=0; i<rows; i++){
			var obj = seq+i;
			var obj1 = seq;
			if(eval( obj + ".style.display=='block';" )){
				eval( obj + ".style.display='none';" );
				eval("formId." + obj1 + ".src='/sbls/images/lplus.gif'");
			} else {
				eval( obj + ".style.display='block';" );
				eval( "formId." + obj1 + ".src='/sbls/images/lminus.gif'");
			}
		}


	}


//�жϿؼ������ݲ���Ϊ����
function checkQuoter(obj){
	if(obj.value.indexOf("'")>=0){
		alert("���ֶβ��ܺ����š�");
		obj.focus();
		return false;
	}
	return true;
}

//���val�Ƿ���lo��hi֮��
function isBetween(val,lo,hi){
 if ((val < lo) || (val > hi)){ return(false);}
 else { return(true);}
}

//���theNum�Ƿ�Ϊ����
function isDigit(theNum){
 var theMask = "0123456789";
 if (isEmpty(theNum)) return(false);
 else if(theMask.indexOf(theNum) == -1) return(false);
 return(true);
}


//���str�Ƿ�Ϊ��
function isEmpty(str){
 if((str == null)||(str.length == 0)) return (true);
 else return(false);
}

//���str�Ƿ�Ϊ����
function isInt(str){
	if(str==""){
		return (false);
	}
	else{
		for(i=0;i<str.length;i++){
		var chr = str.charAt(i);
			if(!(chr>='0' && chr<='9')){
				return (false);
			}
		}
	}
	return (true);
}

//���theStr�Ƿ�Ϊʱ���ʽyyyymmdd���Լ���Ϸ���
function isDate(theStr){
 if(theStr.length!=8){
 alert("�Ƿ����ڣ�����������(yyyymmdd)��");
 return (false);}
 else {
  var y = theStr.substring(0,4);
  var m = theStr.substring(4,6);
  var d = theStr.substring(6,8);
  var maxDays = 31;
  if(isDateInt(m)==false||isDateInt(d)==false||isDateInt(y)==false){
   alert("�����к��з�������ֽ���������ַ�������������(yyyymmdd)��");
   return(false);
  }
  else if (y.length < 4){ alert("����Ӧ����λ����ʾ��");return(false);}
  else if (!isBetween(m,1,12)){ alert("�·�Ӧ��1~12֮�䣡");return(false);}
  if(m.length!=2){ alert("�����·�ǰ���㣡");return(false);}
  else if (m==4||m==6||m==9||m==11)maxDays = 30;
  else if (m==2){
   if(y%4>0)maxDays = 28;
   else if(y%100==0&&y%400>0)maxDays = 28;
   else maxDays = 29;
  }
  if(isBetween(d,1,maxDays)==false){ alert("����ֻ��"+maxDays+"�죡��ȷ�Ϻ��������룡");return(false);}
  if(d.length!=2){ alert("��������ǰ���㣡"); return(false);}
  return(true);

 }
}

//���theStr�Ƿ�Ϊʱ���ʽyyyy/mm/dd���Լ���Ϸ���
function isDate1(theStr){
 if(theStr.length!=8){
 alert("�Ƿ����ڣ�����������(yyyy/mm/dd)��");
 return (false);}
 else {
  var y = theStr.substring(0,the1st);
  var m = theStr.substring(the1st+1,the2nd);
  var d = theStr.substring(the2nd+1,theStr.length);
  var maxDays = 31;
  if(isDateInt(m)==false||isDateInt(d)==false||isDateInt(y)==false){
   alert("�����к��з�������ֽ���������ַ�������������(yyyy/mm/dd)��");
   return(false);
  }
  else if (y.length < 4){ alert("����Ӧ����λ����ʾ��");return(false);}
  else if (!isBetween(m,1,12)){ alert("�·�Ӧ��1~12֮�䣡");return(false);}
  if(m.length!=2){ alert("�����·�ǰ���㣡");return(false);}
  else if (m==4||m==6||m==9||m==11)maxDays = 30;
  else if (m==2){
   if(y%4>0)maxDays = 28;
   else if(y%100==0&&y%400>0)maxDays = 28;
   else maxDays = 29;
  }
  if(isBetween(d,1,maxDays)==false){ alert("����ֻ��"+maxDays+"�죡��ȷ�Ϻ��������룡");return(false);}
  if(d.length!=2){ alert("��������ǰ���㣡"); return(false);}
  return(true);

 }
}


//���str�Ƿ�Ϊ���֣��������ֲ��ɳ���i,С��λ���ɳ���j��
function isDecimal(str,i,j){
	var dot = str.indexOf(".");
	var dot_last = str.lastIndexOf(".");
	var str_f = "";
	var str_b = "";

	if ( dot != -1 ){
		str_f = str.substring(0,dot);
	} else {
		str_f = str;
	}

	if ( dot_last != -1 ){
		str_b = str.substring(dot+1);
	} else {
		str_b = str;
	}

	if( isInt( str_f ) == false ){
		alert("���ǺϷ�������");
		return false;
	} else if ( isInt( str_b ) == false ){
		alert("���ǺϷ�������");
		return false;
	} else if ( dot != dot_last ){
		alert("���ǺϷ�������");
		return false;
	} else if(dot==0 || dot_last==0){
		alert("���ǺϷ�������");
		return false;
	}

	if ( str_f == str_b ){
		if ( j != 0 ) {
			alert("С���ֱ���Ϊ"+j+"λ");
			return false;
		}
	}


	if(str_f.length>i){
		alert("����λ���ܳ���"+i+"λ");
		return false;
	}
	if(dot!=-1 && str_b.length!=j){
		alert("С���ֱ���Ϊ"+j+"λ");
		return false;
	}
	return true;
}

//���str�Ƿ�Ϊ�Ϸ��������
function isYear(str){

	if(str.length!=4){
		alert("��������д���е�����,��ݱ���Ϊ��λ����!");
		return false;
	}

	for(i=0 ;i<str.length;i++){
		var chr = str.charAt(i);
		if(!(chr>=0 && chr<=9 )){
			alert("��ݱ���Ϊ��λ����!");
			return false;
		}
	}

	if((str.charAt(0)<1)||(str.charAt(0)>2)){
		alert("��ݵ�һλ����Ϊ����1��2!");
		return false;
	}
	return true;
}

//���ʱ��ĺϷ��ԣ�year,month,day�ֱ��ʾ��ݣ��·ݼ�����
function isLegalDate(year,month,day){
	if(((month==4)||(month==6)||(month==9)||(month==11))&&(day==31)){
		alert("����û��31��");
		return false;
	}

	if((((year % 4)!=0)||(year%100==0)) && (month==2) && (day>28)){
		alert("����ֻ��28�죡");
		return false;
	}

	if((month==2) && (day>29)){
		alert("����ֻ��29�죡");
		return false;
	}
	return true;
}
//zhoujie---------------------------------------------------------------------------------------
function isLegalDate_zj(year,month,day){   //���ʱ��ĺϷ��ԣ�year,month,day�ֱ��ʾ��ݣ��·ݼ�����
	if((month>12)||(month<1)){
		alert("û�и��·ݣ�");
		return false;
	}
	if((day<1)||(day>31)){
		alert("��������ȷ��");
		return false;
	}
	if(((month==4)||(month==6)||(month==9)||(month==11))&&(day==31)){
		alert("����û��"+ day +"��");
		return false;
	}

	if((((year % 4)!=0)||(year%100==0)) && (month==2) && (day>28)){
		alert("����ֻ��28�죡");
		return false;
	}

	if((month==2) && (day>29)){
		alert("����ֻ��29�죡");
		return false;
	}
	return true;
}
//----------------------------------------------------------------------------------------------


//���str�Ƿ�Ϊ�Ϸ��ĵ绰�����
function isTel(str){
	for(i=0;i<str.length;i++){
	var chr = str.charAt(i);
		if(!(chr>=0 && chr<=9 || chr=="-")){
			alert("�绰���������Ϊ����!");
			return false;
		}
	}
	return true;
}

//����Ƿ�Ϊ����
function isDateInt(theStr){
 var flag = true;
 if (isEmpty(theStr)) { flag = false; }
 else
 { for (var i = 0; i < theStr.length;i++){
   if (isDigit(theStr.substring(i,i+1)) == false){
   flag = false; break;
   }
  }
 return (flag);
 }
}

//�Ƿ�Ϊ�Ϸ���email��ַ
function isEmail(theStr){
	var atIndex = theStr.indexOf('@');
	var dotIndex = theStr.indexOf('.',atIndex);
	var flag = true;
	var theSub = theStr.substring(0,dotIndex+1);
	if((atIndex < 1)||(atIndex != theStr.lastIndexOf('@'))||(dotIndex < atIndex + 2)||(theStr.length <= theSub.length))
	{ flag = false;	}
	else	{ flag =true;	}
	return(flag);
}

//�Ƿ�Ϊʵ��
function isReal(theStr,decLen){
	var dot1st = theStr.indexOf('.');
	var dot2nd = theStr.lastIndexOf('.');
	var OK = true;
	if (isEmpty(theStr)) return false;
	if (dot1st == -1){
		if (!isDateInt(theStr)) return(false);
		else return(true);
	}
	else if (dot1st != dot2nd) return (false);
	else if (dot1st == 0) return (false);
	else {
		var intPart = theStr.substring(0,dot1st);
		var decPart = theStr.substring(dot2nd+1);
		if (decPart.length > decLen) return(false);
		else if (!isDateInt(intPart) || !isDateInt(decPart)) return (false);
		else if (isEmpty(decPart)) return (false);
		else return (true);
	}
}

//�Ƿ�Ϊ����
function isNegative(theStr){
	var theSign = theStr.charAt(0);
	if(theSign=='-'){return true;}
	else{return false;}

}

//
function StrCom(str,len,type)
 {

     var Sspace = " ";
     var Strs = str;
     var strlength=0;
     var i;
      for ( i=0;i<str.length;i++)
                {
                        if(str.charCodeAt(i)>=1000)
                                strlength += 2;
                        else
                                strlength += 1;
                }


   for ( i=0;i<(len-strlength);i++)
   {
   Strs = Strs + Sspace;
   }

   return(Strs);
 }

//added by wushaochun 2003.07.08
//���ƺ��ֳ���
function ChineseLenLimit( str, maxLen){
     var Strs = str;
     var strlength=0;
     var i;
     for ( i=0;i<str.length;i++) {
	        if(str.charCodeAt(i)>=1000)
	                strlength += 2;
	        else
	                strlength += 1;
     }
     if ( strlength > maxLen ){
     	return false;
     }
     return true;

}



 // added by raokun 2003.06.24
 function isBrkId(str){

	if(str==""){
		alert("֤ȯ�̴��ű���Ϊ��λ���ֽ���λ�ַ���ȫ��Ϊ�Ǻ�");
		return (false);
	}
	else{
	        var chr;
		for(i=0;i<2;i++){
		chr = str.charAt(i);
			if(!(chr>='0' && chr<='9')){
			    alert("֤ȯ�̴��ű���Ϊ��λ���ֽ���λ�ַ���ȫ��Ϊ�Ǻ�");
			    return (false);
			}
		}
		var ch1;
        	for(j=2;j<4;j++){
	        ch1 = str.charAt(j);
			if(  ch1>='A' && ch1<='Z'){
			     return (true);
		        } else if ( ch1>='a' && ch1<='z'){
			     return(true);
		        } else if ( ch1>='0' && ch1<='9'){
			     return(true);
		        } else{
		             alert("֤ȯ�̴��ű���Ϊ��λ���ֽ���λ�ַ���ȫ��Ϊ�Ǻ�");
			     return(false);
			}
        	}
	}
	return (true);
}

	  function TrimLeft(str){
	if(str.charAt(0) == " "){
		str = str.slice(1);
		str = TrimLeft(str);
	}
 	return str;
}


function TrimRight(str){
	if(str.charAt( str.length - 1 ) == " "){
		str = str.slice(0,str.length - 1);
		str = TrimRight(str);
	}
 	return str;
}


function TrimBlank(str){
	return TrimLeft(TrimRight(str));
}
