//一些常用的表单提交检测函数
//获取字符串长度
function getlenb(tstr)
{
j=0
for (i=0;i<tstr.length;i++)
{
    if(tstr.charCodeAt(i)>255)
        j++;
    j++;

}
//alert("长度")
return j;
}

//检查是否整数
function checkint(tstr)
{
re=/[^0-9]/;
rp=tstr.search(re); 
return rp;
}

//检查是否汉字
function checkhz(tstr)
{
for (i=0;i<tstr.length;i++)
    if( tstr.charCodeAt(i)>255 )
        {
            return 1;
            break;
        }
//alert(String.fromCharCode(258))
return 0;

}
//检查字符串
function checkstr(tstr)
{
re=/[^a-zA-Z0-9_]/;
//re=/\W/;
rp=tstr.search(re); 
return rp;

}

//检查HTML
function checkhtml(tstr)
{
//re=/[^a-zA-Z0-9_]/;
re=/<(.*)>.*<\/\1>/;
rp=tstr.search(re); 
return rp;

}
//检查空白
function checkblank(tstr)
{
//re=/[^a-zA-Z0-9_]/;
re=/\s/;
rp=tstr.search(re); 
return rp;

}

function checksqlstr(tstr)
{
re1=/[\^\s',\s\$]/;

rp1=tstr.search(re1);
//rp2=tstr.search(re2);
if (rp1!=-1)
    return false
else 
    return true;
    
}
//检查查询时间
function checkTime(y1,h1,m1,s1,y2,h2,m2,s2)
{
	if(y1>y2)
	{
      return false;	  
	}
	else if(y1<y2)
	{
      return true;
	}
	else if(Number(h1)>Number(h2))
	{
      return false;
	}
	else if(Number(h1)<Number(h2))
	{
      return true;
	}
	else if(Number(m1)>Number(m2))
	{
      return false;
	}
	else if(Number(m1)<Number(m2))
	{
      return true;
	}
	else if(Number(s1)>Number(s2))
	{
      return false;
	}
	else if(Number(s1)<Number(s2))
	{
      return true;
	}
	else
	{
       return true;
	}
}
	
//检查非法字符
function checkIllegal(tstr)
{ 
 for (i=0;i<tstr.length;i++)
 {  if( tstr.charCodeAt(i)==39//'
   	||	tstr.charCodeAt(i)==60//<
   	||	tstr.charCodeAt(i)==62)//>
        {
            return 1;
            break;
        }
    }
    return 0;
}


function checkemail(tstr)
{
re1=/([\w\-\.])+[@]{1}([\w\-])+(\.[\w\-])+/;
rp1=tstr.search(re1); 

re2=/[^a-zA-Z0-9_\.\-\@]/;
rp2=tstr.search(re2);
//alert(rp1)
if (rp1==-1||rp2!=-1)
    return 1;    
else
    return 0;
}

//
function isBlank(str)
{
	j=0
	for (i=0;i<str.length;i++)
	{
		if(str.charCodeAt(i)!=32)
			j++;
	}
	return  j;
}

// IP地址全面检查 
function checkIpValid (IPvalue) 
{
    errorString = "";
    theName = "IP地址";

    var ipPattern = /^(\d{1,3})\.(\d{1,3})\.(\d{1,3})\.(\d{1,3})$/;
    var ipArray = IPvalue.match(ipPattern);

    if (IPvalue == "0.0.0.0")
        errorString = errorString + theName + ': '+IPvalue+' 是一个特殊IP地址，不能使用';
    else if (IPvalue == "255.255.255.255")
        errorString = errorString + theName + ': '+IPvalue+' 是一个特殊IP地址，不能使用';
    
	if (ipArray == null)
        errorString = errorString + theName + ': '+IPvalue+' 不是一个合法的IP地址';
    else 
    {
        for (i = 0; i < 5; i++) 
		{
            thisSegment = ipArray[i];
            if (thisSegment > 255) 
			{
                errorString = errorString + theName + ': '+IPvalue+' 不是一个合法的IP地址';
                i = 5;
            }
            if ((i == 0) && (thisSegment > 255)) 
			{
                errorString = errorString + theName + ': '+IPvalue+' 是一个特殊IP地址，不能使用.';
                i = 5;
            }
       }
    }

    if (errorString != "")
	{
		 return true;
	}
	else
	{
		return false;
	}
}
