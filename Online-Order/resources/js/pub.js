<!--hide script contents from old browsers
//for error Handle
function errorHandler(){
  return true;
}
//back window
function back(){
  if(window.opener != null){
    window.close();
    return;
  }
  window.history.back();
  return;
}
//hide waiting
function nowait(state){
  if(state==null) state="none";
  var waiting=document.getElementById("waiting");
  if(waiting!=null)
	waiting.style.display=state;
  return;
}
function hide(framen){
  var pdoc=document;
  var iframe=pdoc.getElementById(framen);
  var ctrlframe=pdoc.getElementById("ctrlframe");
  if(iframe!=null){
	if(iframe.style.display=='none'){
	  iframe.style.display="block";
	  ctrlframe.innerHTML="<!--a href=\"javascript:hide('lefttd')\"><font color=\"gray\">◢</font></a-->";
	}else{
	  iframe.style.display="none";
	  ctrlframe.innerHTML="<!--a href=\"javascript:hide('lefttd')\"><font color=\"gray\">◣</font></a-->";
	}
  }
  return;
}
function isNumber(v) {
  var b = true;
  if(v==null || v=='') return false;
  for(var i=0; i<v.length; i++) {
	var s = v.charAt(i);
	if(!(s >= '0' && s <='9') && !(s=='.')){
	  b = false;
	  break;
	}
  }
  return b;
}
function isInteger(v) {
  var b = true;
  if(v == null || v == '') return (false);
  for(var i = 0; i < v.length; i++) {
	var s = v.charAt(i);
	if(!(s >= '0' && s <='9')) {
	  b = false;
	  break;
	}
  }
  return b;
}
function isMobile(v) {
  var b = true;
  if(v == null || v == '') return (false);
  for(var i = 0; i < v.length; i++) {
	var s = v.charAt(i);
	if(!((s >= '0' && s <='9') || s==' ')){
	  b = false;
	  break;
	}
  }
  return b;
}
function focus(v){
  if(v!=null){
	v.focus();
	v.select();
  }
  return;
}
function isNull(vname){
  var varient=document.getElementById(vname);
  if(varient==null || trim(varient.value)==""){
	alert('对不起，带*号为必填项！');
	varient.focus();
	return true;
  }
  return false;
}
/**
 * 分解替换URL字符串,mode=0 old->new mode=1 new->old
 */
function parseURL(oldURL,mode){
  var newURL = "";
  if(oldURL!=null && oldURL!=""){
	newURL = oldURL;
    if(mode==1){
  	  newURL = newURL.replace(/ /g,'|');
	  newURL = newURL.replace(/=/g,'^');
	  newURL = newURL.replace(/%/g,'$');
    }else if(mode==0){
	  newURL = newURL.replace(/|/g,' ');
	  newURL = newURL.replace(/^/g,'=');
	  newURL = newURL.replace(/$/g,'%');
	}
  }
  return newURL;
}
function getParam(name){
  return getParamByUrl(window.location.href,name);
}
function getParamByUrl(url,name){
  var st1=url.indexOf(name+'=');
  if(st1>=0){
    var st2=url.indexOf("&",st1);
    if(st2<st1||st2<0){
	  st2=url.length;
    }
	return decodeUrl(url.substring(st1+name.length+1, st2));
  }
  return null;
}
function decodeUrl(url){
  url=url.replace(/%20/g," ");
  return url;
}
function replaceURL(oldurl,name,value){
  var st1=oldurl.indexOf('?'+name+'=');
  if(st1<0) st1=oldurl.indexOf('&'+name+'=');
  if(st1>=0){
	st1++;
	var st2=oldurl.indexOf("&",st1);
	if(st2<st1||st2<0){
	  st2=oldurl.length;
    }
    oldurl=oldurl.replace(oldurl.substring(st1, st2), name+'='+value);
  }else{
    st1=oldurl.indexOf('?');
    if(st1>0){
      oldurl+=('&'+name+'='+value);
    }else{
      oldurl+=('?'+name+'='+value);
	}
  }
  return oldurl;
}
function get2Number(num){
  return (num < 10 ? "0" : "") + num;
}
function get3Number(num){
  return (num < 10 ? "00" : (num<100 ? "0" : "")) + num;
}
function getTimeStamp(){
  var now = new Date();
  var year = now.getYear();
  var month = now.getMonth()+1;
  var day = now.getDate();
  var hours = now.getHours();
  var minutes = now.getMinutes();
  var seconds = now.getSeconds();
  var msec = now.getMilliseconds();
  var timeValue = "" + year + get2Number(month) + get2Number(day) + get2Number(hours) + get2Number(minutes) + get2Number(seconds)+get3Number(msec);
  return timeValue;
}
function getTimeString(timeStamp){
  if(timeStamp!=null){
	return timeStamp.substr(0,4)+"-"+timeStamp.substr(4,2)+"-"+timeStamp.substr(6,2)+" "+timeStamp.substr(8,2)+":"+timeStamp.substr(10,2)+":"+timeStamp.substr(12,2);
  }
}
function getSimTimeString(timeStamp){
  if(timeStamp!=null){
	return timeStamp.substr(4,2)+"-"+timeStamp.substr(6,2)+" "+timeStamp.substr(8,2)+":"+timeStamp.substr(10,2)+":"+timeStamp.substr(12,2);
  }
}
function getFullTimeString(timeStamp){
  return timeStamp+"00000000000000000".substr(0,17-timeStamp.length);
}
function getFullTimeStringMax(timeStamp){
  return timeStamp+"99991231235959999".substr(timeStamp.length,17);
}
function selectAllObjs(bool,objName){
  var objs=document.getElementsByName(objName);
  if(objs!=null){
	var count=objs.length;
	for(var i=0;i<count;i++){
      objs[i].checked=bool;
	}
  }
}
//for status-clock display //
var timerID = null;
var timerRunning = false;
function stopclock(){
  if(timerRunning){
    clearTimeout(timerID);
  }
  timerRunning = false;
  return false;
}
function showtime(){
  var timeVar=document.getElementById("timeVar");
  if(timeVar!=null){
	var now = new Date();
	var hours = now.getHours();
	var minutes = now.getMinutes();
	var seconds = now.getSeconds();
	timeVar.innerHTML="<font color='yellow'>"+get2Number(hours)+":"+get2Number(minutes)+":"+get2Number(seconds)+"</font>";
  }
  //window.status = 'time info: '+ getTimeStamp();
  //timerID = setTimeout('showtime()',1000);
  timerID = setTimeout('showtime()',1000);
  timerRunning = true;
  return false;
}
function startclock(){
  stopclock();
  showtime();
  return false;
}
function openWindow(winname, url, w, h){
  var options = "left="+(screen.availWidth-w)/2+",top="+(screen.availHeight-h)/2+",width="+w+",height="+h+",";
  options += "resizable=no,scrollbars=yes,status=yes,";
  options += "location=no,menubar=no,toolbar=no,directories=no";
  var newWin=window.open(url, winname, options);
  newWin.focus();
  return;
}
function openMaxWin(winname,url){
  window.open(url,winname,'top=0,left=0,toolbar=0,status=1,location=0,menubar=0,scrollbars=1,resizable=0,width='+(screen.availWidth-10)+',height='+(screen.availHeight-50));
  return;
}
function openModelDlg(url, w, h){
  var rt=window.showModalDialog(url,'','dialogHeight:'+h+'px;dialogWidth:'+w+'px;help:0;status:0;');
  return;
}
function isChecked(checkname){
  var allcheck=document.getElementsByName(checkname);
  for(i=0;i<allcheck.length;i++){
	if(allcheck[i].checked){
	  return true;
	}
  }
  return false;
}
function checkAll(ctrlcheck,checkname){
  var ctrlcheck=document.getElementById(ctrlcheck);
  var allcheck=document.getElementsByName(checkname);
  for(i=0;i<allcheck.length;i++){
	if(ctrlcheck.checked){
	  if(!allcheck[i].disabled)
		allcheck[i].checked=true;
	}else{
	  if(!allcheck[i].disabled)
		allcheck[i].checked=false;
	}
  }
  return;
}
function checkWhere(predix,checkname){
  var allcheck=document.getElementsByName(checkname);
  var cwhere="1=0";
  for(i=0;i<allcheck.length;i++){
	if(allcheck[i].checked)
	  cwhere += (" or " + predix + "=" + allcheck[i].value);
  }
  return cwhere;
}
function fileupload(action){
  var formvar=document.getElementById("f1");
  if(formvar!=null){
	formvar.action = action;
	formvar.method = "Post";
	formvar.encoding = "multipart/form-data";
	formvar.submit();
  }
  return;
}
//0:means numb
//1:means text
function getIdValue(idname){
  var idvalue=document.getElementById(idname);
  if(idvalue!=null){
	return idvalue.value;
  }
  return "";
}
function setIdValue(idname,newval){
  var idvalue=document.getElementById(idname);
  if(idvalue!=null){
	idvalue.value = newval;
  }
}
function getNameValue(idname,idtype){
  var namevalues=document.getElementsByName(idname);
  if(namevalues!=null){
	for(i=0;i<namevalues.length;i++){
      if(namevalues[i].checked){
	    if(idtype==0)  return namevalues[i].value;
	    else return "\'"+namevalues[i].value+"\'";
	  }
    }
  }
  if(idtype==0) return -1;
  else return "\'-\'";
}
function trim(str){
  if(str==null) return "";
  return (""+str).replace(/^\s*([\s\S]*\S+)\s*$|^\s*$/,'$1');
}
function openCalendar(viewvar,retvar,ismonth){
  var options = "width=376,height=324";
  options += "resizable=yes,scrollbars=no,status=yes,";
  options += "location=no,menubar=no,toolbar=no,directories=no,top=160,left=320";
  var url = 'resources/js/calender.htm?viewvar='+viewvar+'&retvar='+retvar;
  if(ismonth!=null){
    url += ('&ismonth='+ismonth);
  }
  var newWin=window.open(url,'calendar',options);
  newWin.focus();
  return;
}
// CAPICOM constants
var CAPICOM_CERTIFICATE_FIND_SHA1_HASH = 0;
var CAPICOM_CERTIFICATE_FIND_SUBJECT_NAME = 1; 
var CAPICOM_CURRENT_USER_STORE = 2;
var CAPICOM_STORE_OPEN_READ_ONLY = 0;
var CAPICOM_INFO_SUBJECT_SIMPLE_NAME = 0;
var CAPICOM_INFO_ISSUER_SIMPLE_NAME = 1;
var CAPICOM_INFO_SUBJECT_EMAIL_NAME = 2;
var CAPICOM_INFO_ISSUER_EMAIL_NAME  = 3;
var CAPICOM_CHECK_NONE = 0;
var CAPICOM_CHECK_TRUSTED_ROOT = 1;
var CAPICOM_CHECK_TIME_VALIDITY = 2;
var CAPICOM_CHECK_SIGNATURE_VALIDITY = 4;
var CAPICOM_CHECK_ONLINE_REVOCATION_STATUS = 8;
var CAPICOM_CHECK_OFFLINE_REVOCATION_STATUS = 16;
var CAPICOM_TRUST_IS_NOT_TIME_VALID = 1;
var CAPICOM_TRUST_IS_NOT_TIME_NESTED = 2;
var CAPICOM_TRUST_IS_REVOKED = 4;
var CAPICOM_TRUST_IS_NOT_SIGNATURE_VALID = 8;
var CAPICOM_TRUST_IS_NOT_VALID_FOR_USAGE = 16;
var CAPICOM_TRUST_IS_UNTRUSTED_ROOT = 32;
var CAPICOM_TRUST_REVOCATION_STATUS_UNKNOWN = 64;
var CAPICOM_TRUST_IS_CYCLIC = 128;
var CAPICOM_TRUST_IS_PARTIAL_CHAIN = 65536;
var CAPICOM_TRUST_CTL_IS_NOT_TIME_VALID = 131072;
var CAPICOM_TRUST_CTL_IS_NOT_SIGNATURE_VALID = 262144;
var CAPICOM_TRUST_CTL_IS_NOT_VALID_FOR_USAGE = 524288;
function displayCert(szSubjectName,szSerialNumber){
  // find the certificate specified!
  var MyStore = new ActiveXObject("CAPICOM.Store");
  var AddrStore = new ActiveXObject("CAPICOM.Store");
  var CAStore = new ActiveXObject("CAPICOM.Store");
  var RootStore = new ActiveXObject("CAPICOM.Store");
  var FoundCertificates = new ActiveXObject("CAPICOM.Certificates");
  // open the store objects
  try{
     MyStore.Open(CAPICOM_CURRENT_USER_STORE, "My", CAPICOM_STORE_OPEN_READ_ONLY);
     AddrStore.Open(CAPICOM_CURRENT_USER_STORE, "AddressBook", CAPICOM_STORE_OPEN_READ_ONLY);
     CAStore.Open(CAPICOM_CURRENT_USER_STORE, "CA", CAPICOM_STORE_OPEN_READ_ONLY);
     RootStore.Open(CAPICOM_CURRENT_USER_STORE, "Root", CAPICOM_STORE_OPEN_READ_ONLY);
  }catch(e){
     alert("打开证书库时出现异常!");
     return false;
  }
  // this may take a second so lets update the user with what we are doing
  window.status="证书主题 " + szSubjectName + ".";
  // create an array of all of the stores
  MyStores = new Array(MyStore, AddrStore, CAStore, RootStore);
  // enumerate through the stores
  for(iStore = 0; iStore <= (MyStores.length -1); iStore++){
    // look for our thumbprint in this store
    var Certificates = MyStores[iStore].Certificates.Find(CAPICOM_CERTIFICATE_FIND_SUBJECT_NAME, szSubjectName);
    // enumerate through each of the certificates we found (if any)
    for(iCert = 1; iCert <= (Certificates.Count); iCert++){
      if(szSerialNumber.toLowerCase()==Certificates.Item(iCert).SerialNumber.toLowerCase()){
        Certificates.Item(iCert).Display();
		window.status="";
        return;
      }
    }
  }
  window.status="";
  return;
}
//define mycert, mycert_lab, adcert, adcert_lab in the document
function selcert(storename){
  try{
    var cstore = new ActiveXObject("CAPICOM.Store");
    cstore.Open(CAPICOM_CURRENT_USER_STORE, storename, CAPICOM_STORE_OPEN_READ_ONLY);
    var ccerts = cstore.Certificates;
    //ccerts = ccerts.Find(CAPICOM_CERTIFICATE_FIND_SHA1_HASH);
    if(trim(storename.toLowerCase())=="my"){
      for(i=1; i<=ccerts.Count; i++){
      	if(!ccerts.Item(i).HasPrivateKey()){
      		ccerts.Remove(i);
      	}
      }
    }
    var scerts = ccerts.select("证书", "请从以下列表中选择证书", false);
    if(scerts!=null){
      var mycert=document.getElementById("mycert");
      var mycert_lab=document.getElementById("mycert_lab");
      var adcert=document.getElementById("adcert");
      var adcert_lab=document.getElementById("adcert_lab");
      for(k=1;k<=scerts.Count;k++){
   		if(mycert!=null && trim(storename.toLowerCase())=="my"){
   		  mycert.value=scerts.Item(k).SerialNumber;
   		  mycert_lab.value=scerts.Item(k).GetInfo(CAPICOM_INFO_SUBJECT_SIMPLE_NAME);
   		  break;
   		}else if(adcert!=null && trim(storename.toLowerCase())=="addressbook"){
   		  adcert.value=scerts.Item(k).SerialNumber;
   		  adcert_lab.value=scerts.Item(k).GetInfo(CAPICOM_INFO_SUBJECT_SIMPLE_NAME);
   		  break;
   		}
      }
    }
  }catch(e){
  	alert(e.message);
  }
  return;
}
function gotopage(id, maxp){
  var gopage=document.getElementById(id);
  if(isNaN(gopage.value) || !isInteger(gopage.value) || gopage.value<=0 || gopage.value>maxp){
	return;
  }
  window.location=replaceURL(window.location.href, 'pgid', gopage.value-1);
  return;
}
function Round(a_Num, a_Bit){
  return(Math.round(a_Num*Math.pow(10, a_Bit))/Math.pow(10, a_Bit));
}
function scrollTop(){
  var obj;
  if(top.frames["mainFrame"]!=null){
	obj=top.frames["mainFrame"];
  }else{
	obj=top;
  }
  obj.scrollTo(0,0);
  return;
}
function lenCheck(id,maxlen){
  var obj=document.getElementById(id);
  if(obj!=null){
	//alert(id+":"+obj.value.length+":"+(obj.value.length<=maxlen));
	if(obj.value.length<=maxlen){
	  return true;
	}
	alert('字段长度太长了,不能超过'+maxlen+'个字(符)!');
	obj.select();
	obj.focus();
	return false;
  }
  return true;
}
function setCurMonth(predix){
  var now = new Date();
  var year= now.getYear();
  var month=now.getMonth()+1;
  var id1=document.getElementById(predix+"1");
  var id10=document.getElementById(predix+"10");
  id1.value=year+get2Number(month)+'01';
  id10.value=year+'-'+get2Number(month)+'-01';
  var id2=document.getElementById(predix+"2");
  var id20=document.getElementById(predix+"20");
  id2.value=year+get2Number(month)+'31';
  id20.value=year+'-'+get2Number(month)+'-31';
  return;
}
function setCurYear(predix){
  var now = new Date();
  var year= now.getYear();
  var month=now.getMonth()+1;
  var id1=document.getElementById(predix+"1");
  var id10=document.getElementById(predix+"10");
  id1.value=year+'0101';
  id10.value=year+'-01-01';
  var id2=document.getElementById(predix+"2");
  var id20=document.getElementById(predix+"20");
  id2.value=year+'1231';
  id20.value=year+'-12-31';
  return;
}
//校验函数
function getStringLength(str){
  var endvalue=0;
  var sourcestr=new String(str);
  var tempstr;
  for(var strposition = 0; strposition < sourcestr.length; strposition ++) {
    tempstr=sourcestr.charAt(strposition);
    if(tempstr.charCodeAt(0)>255 || tempstr.charCodeAt(0)<0){
      endvalue=endvalue+2;
    }else{
      endvalue=endvalue+1;
    }
  }
  return(endvalue);
}
function validateDate(date,format,alt){
  var time=trim(date.value);
  if(time=="") return;
  var reg=format;
  var reg=reg.replace(/yyyy/,"[0-9]{4}");
  var reg=reg.replace(/yy/,"[0-9]{2}");
  var reg=reg.replace(/MM/,"((0[1-9])|1[0-2])");
  var reg=reg.replace(/M/,"(([1-9])|1[0-2])");
  var reg=reg.replace(/dd/,"((0[1-9])|([1-2][0-9])|30|31)");
  var reg=reg.replace(/d/,"([1-9]|[1-2][0-9]|30|31))");
  var reg=reg.replace(/HH/,"(([0-1][0-9])|20|21|22|23)");
  var reg=reg.replace(/H/,"([0-9]|1[0-9]|20|21|22|23)");
  var reg=reg.replace(/mm/,"([0-5][0-9])");
  var reg=reg.replace(/m/,"([0-9]|([1-5][0-9]))");
  var reg=reg.replace(/ss/,"([0-5][0-9])");
  var reg=reg.replace(/s/,"([0-9]|([1-5][0-9]))");
  reg=new RegExp("^"+reg+"$");
  if(reg.test(time)==false){//验证格式是否合法
    alert(alt);
    date.focus();
    return false;
  }
  return true;
}
function validateDateGroup(year,month,day,alt){
  var array=new Array(31,28,31,30,31,30,31,31,30,31,30,31);
  var y=parseInt(year.value);
  var m=parseInt(month.value);
  var d=parseInt(day.value);
  var maxday=array[m-1];
  if(m==2){
    if((y%4==0&&y%100!=0)||y%400==0){
      maxday=29;
    }
  }
  if(d>maxday){
    alert(alt);
    return false;
  }
  return true;
}
function validateCheckbox(obj,alt){
  var rs=false;
  if(obj!=null){
    if(obj.length==null){//注意:必须一个以上的check项才能使用
      return obj.checked;
    }
    for(i=0;i<obj.length;i++){
      if(obj[i].checked==true){
        return true;
      }
    }
  }
  alert(alt);
  return rs;
}
function validateRadio(obj,alt){
  var rs=false;
  if(obj!=null){
    if(obj.length==null){
      return obj.checked;
    }
    for(i=0;i<obj.length;i++){
      if(obj[i].checked==true){
        return true;
      }
    }
  }
  alert(alt);
  return rs;
}
function validateSelect(obj,alt){
  var rs=false;
  if(obj!=null){
	for(i=0;i<obj.options.length;i++){
      if(obj.options[i].selected==true && obj.options[i].value.length>0){
        return true;
      }
    }
  }
  alert(alt);
  return rs;
}
function validateEmail(email,alt,separator){
  var mail=trim(email.value);
  if(mail=="") return;
  var em;
  var myReg = /^[_a-z0-9]+@([_a-z0-9]+\.)+[a-z0-9]{2,3}$/;
  if(separator==null){
    if(myReg.test(email.value)==false){
      alert(alt);
      email.focus();
      return false;
    }
  }else{
    em=email.value.split(separator);
    for(i=0;i<em.length;i++){
      em[i]=em[i].trim();
      if(em[i].length>0&&myReg.test(em[i])==false){
        alert(alt);
        email.focus();
        return false;
      }
    }
  }
  return true;
}
function validateForm(theForm){//若验证通过则返回true
  var disableList=new Array();
  var field = theForm.elements; //将表单中的所有元素放入数组
  for(var i = 0; i < field.length; i++){
    var vali=theForm.validate;
	if(vali!=null){
	  if(vali.value=="0"){
		var fun=vali.functionName;
		if(fun!=null){
			return eval(fun+"()");
		}else{
			return true;
		}
	  }
	}
    var empty=false;
    var value=trim(field[i].value);
    if(value.length==0){//是否空值
      empty=true;
    }
    var emptyInfo=field[i].emptyInfo;//空值验证
    if(emptyInfo!=null&&empty==true){
      alert(emptyInfo);
      field[i].focus();
      return false;
    }
    var lengthInfo=field[i].lengthInfo;//最大长度验证
    if(lengthInfo!=null&&getStringLength(value)>field[i].maxLength){
      alert(lengthInfo);
      field[i].focus();
      return false;
    }
    var validatorType=field[i].validatorType;
    if(validatorType!=null){//其它javascript
      var rs=true;
      if(validatorType=="javascript"){
        eval("rs="+field[i].functionName+"()");
        if(rs==false){
          return false;
        }else{
          continue;
        }
      }else if(validatorType=="disable"){//提交表单前disable的按钮
        disableList.length++;
        disableList[disableList.length-1]=field[i];
        continue;
      }else if(validatorType=="Date"){
        rs=validateDate(theForm.elements(field[i].fieldName),field[i].format,field[i].errorInfo);
      }else if(validatorType=="DateGroup"){
        rs=validateDateGroup(theForm.elements(field[i].year),theForm.elements(field[i].month),theForm.elements(field[i].day),field[i].errorInfo);
      }else if(validatorType=="Checkbox"){
        rs=validateCheckbox(theForm.elements(field[i].fieldName),field[i].errorInfo);
      }else if(validatorType=="Radio"){
        rs=validateRadio(theForm.elements(field[i].fieldName),field[i].errorInfo);
      }else if(validatorType=="Select"){
        rs=validateSelect(theForm.elements(field[i].fieldName),field[i].errorInfo);
      }else if(validatorType=="Email"){
        rs=validateEmail(theForm.elements(field[i].fieldName),field[i].errorInfo);
      }else{
        alert("验证类型不被支持, fieldName: "+field[i].name);
        return false;
      }
      if(rs==false){
        return false;
      }
    }else{//一般验证
      if(empty==false){
        var v = field[i].validator; // 获取其validator属性
        if(!v) continue;            // 如果该属性不存在,忽略当前元素
        var reg=new RegExp(v);
        if(reg.test(field[i].value)==false){
          alert(field[i].errorInfo);
          field[i].focus();
          return false;
        }
      }
    }
  }
  for(i=0;i<disableList.length;i++){
    disableList[i].disabled=true;
  }
  return true;
}
function doexp(){
	if(!window.confirm("你确定要导出为excel吗?")) return;
	window.location.href=replaceURL(window.location.href, "2xls", '1');
	return;
}
function setActiveStyle(title){
  var i;
  var a=document.getElementsByTagName("link");
  for(i=0; a!=null && i<a.length; i++) {
  	var relv=a[i].getAttribute("rel");
	if(relv.indexOf("style") != -1 && a[i].getAttribute("title")){
	    a[i].disabled = true;
	    if(a[i].getAttribute("title")==title){
			a[i].disabled = false;
		}
	}
  }
}
function getActiveStyle(){
  var i;
  var a=document.getElementsByTagName("link");
  for(i=0; a!=null && i<a.length; i++) {
  	var relv=a[i].getAttribute("rel");
	if(relv.indexOf("style") != -1 && a[i].getAttribute("title") && !a[i].disabled){
		return a[i].getAttribute("title");
	}
  }
  return null;
}
function getPreferredStyle(){
  var i;
  var a=document.getElementsByTagName("link");
  for(i=0; a!=null && i<a.length; i++) {
  	var relv=a[i].getAttribute("rel");
	if(relv.indexOf("style") != -1 && relv.indexOf("alt") == -1	&& a[i].getAttribute("title")){
		return a[i].getAttribute("title");
	}
  }
  return null;
}
function setCookie(name,value,days){
  if(days){
	var date = new Date();
	date.setTime(date.getTime()+(days*24*60*60*1000));
	var expires="; expires="+date.toGMTString();
  }else{
	expires = "";
  }
  document.cookie = name+"="+value+expires+"; path=/";
}
function getCookie(name){
  var arr = document.cookie.match(new RegExp("(^| )"+name+"=([^;]*)(;|$)"));
  if(arr!=null) 
  	return unescape(arr[2]); 
  return null;
}
function openDev(ip,idt){
	var options = "left="+(screen.availWidth-360)/2+",top="+(screen.availHeight-260)/2+",width=380,height=260,";
	options += "resizable=no,scrollbars=yes,status=yes,location=no,menubar=no,toolbar=no,directories=no";
	var newWin;
	if(idt==3){
		newWin=window.open('../dev/bsinfo.jsp?id='+ip, 'stat', options);
	}else if (idt==2){
		newWin=window.open('../dev/pcfinfo.jsp?ip='+ip, 'stat', options);
	}else if (idt==1){
		newWin=window.open('../dev/pdsninfo.jsp?ip='+ip, 'stat', options);
	}
	newWin.focus();
	return;
}
function openStat(){
	var options = "left="+(screen.availWidth-980)/2+",top="+(screen.availHeight-550)/2+",width=980,height=550,";
	options += "resizable=no,scrollbars=yes,status=yes,location=no,menubar=no,toolbar=no,directories=no";
	var newWin=window.open('../trace/stat.jsp', 'stat', options);
	newWin.focus();
	return;
}
function enc(raw){
	return trim(raw).replace(/&/g,'＆').replace(/%/g,'％').replace(/\+/g,'＋');
}
function testMin(v){
	var reg=/^(\d{4})-(\d{2})-(\d{2}) (\d{2}):(\d{2})$/;
	if(!reg.test(v.value) || 
		RegExp.$2>12 || RegExp.$2<=0 ||
		RegExp.$3>31 || RegExp.$3<=0 ||
		RegExp.$4>23 || RegExp.$5>59){
		alert("请保证输入的日期格式为yyyy-mm-dd HH24:mi!");
		v.focus();
		return false;
	}
	return true;
}
function testHour(v){
	var reg=/^(\d{4})-(\d{2})-(\d{2}) (\d{2})$/;
	if(!reg.test(v.value) || 
		RegExp.$2>12 || RegExp.$2<=0 ||
		RegExp.$3>31 || RegExp.$3<=0 ||
		RegExp.$4>23){
		alert("请保证输入的日期格式为yyyy-mm-dd HH24!");
		v.focus();
		return false;
	}
	return true;
}
function testDay(v){
	var reg=/^(\d{4})-(\d{2})-(\d{2})$/;
	if(!reg.test(v.value) || 
		RegExp.$2>12 || RegExp.$2<=0 ||
		RegExp.$3>31 || RegExp.$3<=0){
		alert("请保证输入的日期格式为yyyy-mm-dd!");
		v.focus();
		return false;
	}
	return true;
}
function testMon(v){
	var reg = /^(\d{4})-(\d{2})$/;
	if(!reg.test(v.value) || 
		RegExp.$2>12 || RegExp.$2<=0){
		alert("请保证输入的日期格式为yyyy-mm!");
		v.focus();
		return false;
	}
	return true;
}
// end hiding contents from old browsers  -->
