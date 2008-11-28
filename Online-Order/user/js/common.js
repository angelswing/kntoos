 //改变排序方式（asc<-->desc）
function chgSortOrder(lastOrder){
	var newOrder = "";
	if(lastOrder != ""){  
		if(lastOrder=="asc"){
			newOrder = "desc";
		}else if(lastOrder=="desc"){
			newOrder = "asc";
		}
	}else{ //first time order is asc(default)
		newOrder = "asc";
	}
	return newOrder;
}


//检查字符的长度,按汉字的长度计算
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