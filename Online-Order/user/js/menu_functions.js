
// Global variables



var cellwide = false;

var isNav4, isIE, isDom

var foropera = window.navigator.userAgent.toLowerCase();

var itsopera = foropera.indexOf("opera",0) + 1;
var itsgecko = foropera.indexOf("gecko",0) + 1;
var itsmozillacompat = foropera.indexOf("mozilla",0) + 1;
var itsmsie = foropera.indexOf("msie",0) + 1;


        if (itsopera > 0){
                //thebrowser = 3;
                //alert("its opera");
                isNav4 = true
        }


        if (itsmozillacompat > 0){ 
                //alert("its mozilla compatible");
                if (itsgecko > 0) {
                       //thebrowser = 4
                       // alert("its gecko");
                       isIE= true
                       isDom = true
                       document.all = document.getElementsByTagName("*");
 
                }
                else if (itsmsie > 0) {
                      //  alert("its msie");
                       // thebrowser = 2
                        isIE = true
                }
                else {
                        if (parseInt(navigator.appVersion) < 5) {
                                // alert("its ns4.x")
                                //thebrowser = 1
                                isNav4 = true
                        }
                        else {
                                //thebrowser = 2
                                isIE = true
                        }
                }
        } 
        


function NSResize() {
   location.reload();
   //top.header.location.reload()
   top.detail.location.reload();
   return false;
}




if (isNav4 && !isDom) {

   window.captureEvents(Event.RESIZE)
   window.onresize = NSResize

}


var showWaitButtons = ["installAction","button.save","button.start","button.stop","button.install","button.uninstall","button.new","button.delete"]

function arrayContains(theArray,value) {

        len = theArray.length;
        for (i=0;i<len;i++) {
                if (theArray[i] == value) {
                        //alert("true");
                        return true;                        
                }
        }
        return false;
}


function setTopVarOn(e) {
        top.isloaded = 0;
        if (isNav4) { routeEvent(e) }        

        
}

function setTopVarOff(e) {

        top.isloaded = 1;

        var thebutton = "";
        
        if (isIE) {
                if (isDom) {  
                        thebutton = e.target.name; 
                }
                else {  
                        e = event; 
                        thebutton = e.srcElement.name;
                        if (e.srcElement.tagName == "LABEL") {
                                return false;
                        } 
                }                
        } else {
                thebutton = e.target.name;
        }
        if (isDom) {
                if (arrayContains(showWaitButtons,thebutton)) {
                        document.all["progress"].style.top = e.clientY;
                        document.all["progress"].style.left = e.clientX;
                        document.all["progress"].style.display = "block";
                }        
        } else if (isNav4) {
                if (arrayContains(showWaitButtons,thebutton)) {
                        document.layers["progress"].visibility="show";
                }
        }
        else {
                if (arrayContains(showWaitButtons,thebutton)) {
                        document.all["progress"].style.pixelTop = e.clientY+document.body.scrollTop;
                        document.all["progress"].style.pixelLeft = e.clientX;
                        document.all["progress"].style.display = "block";
                }
        }
        if (isNav4) {routeEvent(e);}
}

if (isNav4) {
        document.captureEvents(Event.CLICK);    
        document.captureEvents(Event.KEYPRESS);
        document.captureEvents(Event.MOUSEOVER);
        document.captureEvents(Event.MOUSEOUT);

}
document.onclick = setTopVarOff;
document.onkeypress = setTopVarOff;
document.onmouseover = showLegend;
document.onmouseout = hideLegend;



if (isIE) {
        document.write("<div id='progress' style=\"position:absolute;display:none;top:200;left:200;border: 1px black solid;padding:3px 3px 3px 3px;background-color:#FFFFFF;font-family: sans-serif;font-size: 80%\"><NOBR><img src='/admin/images/appInstall_animated.gif' align='texttop'>" + top.pleaseWait + "</NOBR></div>");
        
        if (top.statusCollection == "yes") {
                document.write("<div id='statusLegend' style=\"position:absolute;width:150;height=90;visibility:hidden;top:0;left:0;border: 0px black solid;padding:0px 0px 0px 0px;background-color:#999999;\">");
                document.write("<table width='150' border=0 cellpadding=3 cellspacing=1 >");
                document.write("<tr><td class='column-head'> "+statusHeader+"</td></tr>");
                document.write("<tr><td class='table-text'><img src='/admin/images/running.gif'  align='texttop'  border='0' alt='started'> "+statusStarted+"</td></tr>");        
                document.write("<tr><td class='table-text'><img src='/admin/images/stop.gif' align='texttop'  border='0' alt='stopped'> "+statusStopped+"</td></tr>");
                document.write("<tr><td class='table-text'><img src='/admin/images/unavail.gif'  align='texttop'  border='0' alt='unavailable'> "+statusUnavailable+"</td></tr>");                
                document.write("<tr><td class='table-text'><img src='/admin/images/unknown.gif'  align='texttop'  border='0' alt='unknown'> "+statusUnknown+"</td></tr>");
                document.write("<tr><td class='table-text'><img src='/admin/images/part_start.gif'  align='texttop'  border='0' alt='partial start'> "+statusPartialStart+"</td></tr>");
                document.write("<tr><td class='table-text'><img src='/admin/images/part_stop.gif'  align='texttop'  border='0' alt='partial stop'> "+statusPartialStop+"</td></tr>");
                document.write("<tr><td class='table-text'><img src='/admin/images/synch.gif'  align='texttop'  border='0' alt='synchronized'> "+statusSynchronized+"</td></tr>");
                document.write("<tr><td class='table-text'><img src='/admin/images/not_synch.gif'  align='texttop'  border='0' alt='no synchronized'> "+statusNotSynchronized+"</td></tr>");
                document.write("</table>");
                document.write("</div>");
                top.statusCollection = "no";
        }
        

}
if (isNav4) {
        document.write("<layer id='progress' visibility='hide' width='200' Z-INDEX='0' top='25' left='200' bgColor='#999999'><table width='200' border='0' cellpadding='3' cellspacing='1'><tr><td class='table-text'><img src='/admin/images/appInstall_animated.gif' align='texttop'>" + top.pleaseWait + "</td></tr></table></layer>");
        
        if (top.statusCollection == "yes") {
                document.write("<layer id='statusLegend'  visibility='hide' width='150' height='90' top='200' left='200' bgColor='#999999'>");
                document.write("<table width='150' border=0 cellpadding=3 cellspacing=1 >");
                document.write("<tr><td class='column-head'> "+statusHeader+"</td></tr>");
                document.write("<tr><td class='table-text'><img src='/admin/images/running.gif'  align='texttop'  border='0' alt='started'> "+statusStarted+"</td></tr>");        
                document.write("<tr><td class='table-text'><img src='/admin/images/stop.gif' align='texttop'  border='0' alt='stopped'> "+statusStopped+"</td></tr>");
                document.write("<tr><td class='table-text'><img src='/admin/images/unavail.gif'  align='texttop'  border='0' alt='unavailable'> "+statusUnavailable+"</td></tr>");                
                document.write("<tr><td class='table-text'><img src='/admin/images/unknown.gif'  align='texttop'  border='0' alt='unknown'> "+statusUnknown+"</td></tr>");
                document.write("<tr><td class='table-text'><img src='/admin/images/part_start.gif'  align='texttop'  border='0' alt='partial start'> "+statusPartialStart+"</td></tr>");
                document.write("<tr><td class='table-text'><img src='/admin/images/part_stop.gif'  align='texttop'  border='0' alt='partial stop'> "+statusPartialStop+"</td></tr>");
                document.write("<tr><td class='table-text'><img src='/admin/images/synch.gif'  align='texttop'  border='0' alt='synchronized'> "+statusSynchronized+"</td></tr>");
                document.write("<tr><td class='table-text'><img src='/admin/images/not_synch.gif'  align='texttop'  border='0' alt='no synchronized'> "+statusNotSynchronized+"</td></tr>");
                document.write("</table>");
                document.write("</layer>");
                top.statusCollection = "no";
        }
        
        
}



function appInstallWaitShow() {

        if (isDom) {
                        document.all["progress"].style.display = "block";
        } else if (isNav4) {
                        document.layers["progress"].visibility="show";
        }
        else {
                        document.all["progress"].style.display = "block";
        }

}

function appInstallWaitHide() {

        if (isDom) {
                        document.all["progress"].style.display = "none";
        } else if (isNav4) {
                        document.layers["progress"].visibility="hide";
        }
        else {
                        document.all["progress"].style.display = "none";
        }

}



function showLegend(e) {

        var o, oT, oL, obj = "";
        if (isIE) {
                if (isDom) {  
                        oT = e.clientY;
                        oL = e.clientX;
                        obj = e.target.name;
                        thisWin = document.body.clientHeight;
                        thisWinscroll = document.body.scrollTop;
                        visibleWin = thisWinscroll + thisWin;                        
                }
                else {  
                        e = event; 
                        oT = e.clientY+document.body.scrollTop;
                        oL = e.clientX;
                        obj = e.srcElement.name;
                        thisWin = document.body.clientHeight;
                        thisWinscroll = document.body.scrollTop;
                        visibleWin = thisWinscroll + thisWin;                        
                }                
        } else {
        
                oT = e.pageY;
                oL = e.pageX;
                obj = e.target.target;
                thisWin =  window.innerHeight;
                thisWinscroll = window.pageYOffset;
                visibleWin = thisWinscroll + thisWin; 
        }
        if ((obj == "statusIcon") || (obj == "statuswindow")) {
                legendBottom = oT+200;

                if (legendBottom > visibleWin) {
                        oT = oT - (legendBottom - visibleWin);
                }
                if (isDom) {
                                document.all["statusLegend"].style.top = oT;
                                document.all["statusLegend"].style.left = oL+10;
                                document.all["statusLegend"].style.visibility="visible";
                                return false;
                } else if (isNav4) {
                                document.layers["statusLegend"].top = oT;
                                document.layers["statusLegend"].left = oL+10;
                                document.layers["statusLegend"].visibility="show";
                }
                else {
                                document.all["statusLegend"].style.pixelTop = oT;
                                document.all["statusLegend"].style.pixelLeft = oL+10;
                                document.all["statusLegend"].style.visibility="visible";
                                return false;
                }
                
        }
        
        if (isNav4) {routeEvent(e);}
        
        

}

function hideLegend(e) {

        var o, oT, oL, obj = "";
        
        if (isIE) {
                if (isDom) {  
                        obj = e.target.name;
                }
                else {  
                        e = event; 
                        obj = e.srcElement.name;
                }                
        } else {
                obj = e.target.target;
        }
        if ((obj == "statusIcon") || (obj == "statuswindow")) {
                
                if (isDom) {
                                document.all["statusLegend"].style.visibility="hidden";
                                 return false; 
                } else if (isNav4) {
                                document.layers["statusLegend"].visibility="hide";
        
                }
                else {
                                document.all["statusLegend"].style.visibility="hidden";
                                return false; 
                }
        }
                
        if (isNav4) {routeEvent(e);}
        
              
        
}


