/* -------------------- tab_button.js ----------------------------
 *
 * ���JS��������tabpage��ť��htmlԭʼ��
 * ʹ��ʱ����ҳ��������һ��tablist�Ķ�ά���д������tabpage������
 * tablist = new Array(["��һҳ","tab1"],["�ڶ�ҳ","tab2"],["����ҳ","tab3"])
 *
 * Ȼ������Ҫ����tabpage��ť�ĵط��������js������
 * <script src="../script/tab_button.js"></script>
 *
 * author : Dark
*/


//���script������tablist�����ݶ�̬����htmlԭʼ��
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


//���function�����л�tabpage
function changeTab(sender,tablist,actpage){
  //�ı�tabpage button��״̬
  //alert(actpage.value);

  form1.cardpage.value=sender.id.substr(7,1);

  //alert(form1.cardpage.value);
  var leng = tablist.length;
  for(i=0; i < leng; i++){
	document.all["tab_btn"+i].className="normal_tab";
  }
  sender.className="active_tab";

  //�ı�tabpage��״̬
  for (i=0; i<leng; i++)
  {
  	//alert(tablist[i][1]);
    document.all[tablist[i][1]].style.display = "none";
  }
  actpage.style.display = "block";
  window.focus();
}

