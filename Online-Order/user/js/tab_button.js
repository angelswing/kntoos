/* -------------------- tab_button.js ----------------------------
 *
 * 这个JS用来产生tabpage按钮的html原始码
 * 使用时先在页面上宣告一个tablist的二维阵列存放所有tabpage的名称
 * tablist = new Array(["第一页","tab1"],["第二页","tab2"],["第叁页","tab3"])
 *
 * 然後在你要产生tabpage按钮的地方因入这个js档即可
 * <script src="../script/tab_button.js"></script>
 *
 * author : Dark
*/


//这段script会依据tablist的内容动态插入html原始码
var str = "";
var leng = tablist.length;
str += '<table cellspacing="0" cellpadding="0" class="tabpage"><tr height="26">';
str = str + '<td id="tab_btn0" class="active_tab" nowrap onclick="changeTab(this,tablist,document.all.'
    + tablist[0][1]+')">&nbsp;'+ tablist[0][0] +'&nbsp;</td>';
for ( var i = 1; i < leng; i++) {
  str = str + '<td id="tab_btn'+ i +'" class="normal_tab" nowrap onclick="changeTab(this,tablist,document.all.'
      + tablist[i][1]+')">&nbsp;'+ tablist[i][0] +'&nbsp;</td>';
}
str += '<td width="100%" style="background-color : white; border-bottom: 1 solid #808080; border-left: 1 solid #808080;">&nbsp;</td>';
str += '</tr></table>';
document.write(str);


//这个function用来切换tabpage
function changeTab(sender,tablist,actpage){
  //改变tabpage button的状态
  //alert(actpage.value);

  form1.cardpage.value=sender.id.substr(7,1);

  //alert(form1.cardpage.value);
  var leng = tablist.length;
  for(i=0; i < leng; i++){
	document.all["tab_btn"+i].className="normal_tab";
  }
  sender.className="active_tab";

  //改变tabpage的状态
  for (i=0; i<leng; i++)
  {
  	//alert(tablist[i][1]);
    document.all[tablist[i][1]].style.display = "none";
  }
  actpage.style.display = "block";
  window.focus();
}

