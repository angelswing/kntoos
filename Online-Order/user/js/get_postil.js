/* name:�������
              (�г����õ���ʾ���,���λ����쵼����ѡ��)
   author:    wenhui.ke@enogroup.com  2003/09/28
   modified:  raokun                2004.01.07
              ��ԭ����(������)���˸���,��Ӧ������Ŀ.
*/

//���������Ĵ���
popPostil = createPopup()
PostilBody = popPostil.document.body
PostilBody.style.border="outset 1pt #cccccc"
PostilBody.style.fontSize = "9pt"
PostilBody.style.backgroundColor= "#FFFFFF"
PostilBody.style.cursor="hand"

//����ѡ�����ɫ.�ƶ�����Ӧ��ʾ����ʱ,����ɫ
function postilTDMove(e){
	e.style.borderColor="#000000 #CCCCFF #CCCCFF #CCCCFF"
	}


//����ѡ�����ɫ.����Ӧ��ʾ����ʱ�뿪ʱ,����ɫ
function postilTDOut(e){
	e.style.border="solid 1pt #ffffff"
	}

//�رյ����Ĵ���
function hidePostil(){
	popPostil.hide()
	}

//��ѡĳ����ʾ����ʱ.
function PostilClick(e){

		tarObject.value= e.innerText;
		e.style.borderColor="#94aad6"
                popPostil.hide()
}

//����������ҳ��ĵ���,Ϊ�ӿ�.
function getPostil(s){

	tarObject=s

	var e=event.srcElement
	popPostil.show(-70,e.clientHeight+5,160,100,e)

}

var strPostil='<table bgcolor="#FFFFFF" id="whitecell" author="whitecell" border="1"'
    strPostil+=' bordercolorlight="#0053a6" bordercolordark="#ffffff" cellpadding="1"'
    strPostil+=' cellspacing="0" style="font-size:9pt;height:100;cursor:hand;">'
    strPostil+='<tr height="25"><td nowrap  align="center" bgcolor="#94aad6" '
    strPostil+='><font size="3" >&nbsp;&nbsp;&nbsp��ѡ������ʾ���&nbsp;&nbsp;&nbsp;&nbsp;</font></td></tr>'

 		strPostil+='<tr align=left>'
		strPostil+='<td nowrap bgcolor="#ffFFFF" COLOR="RED" style="border:solid 1pt #FFFFFF" onmouseover="parent.postilTDMove(this)"'
		strPostil+=' onmouseout="parent.postilTDOut(this)" onclick="parent.PostilClick(this)"><FONT COLOR="RED">'
		strPostil+='ͬ��'+'</FONT></td>'

                strPostil+='<tr align=left>'
		strPostil+='<td nowrap bgcolor="#ffFFFF" COLOR="RED" style="border:solid 1pt #FFFFFF" onmouseover="parent.postilTDMove(this)"'
		strPostil+=' onmouseout="parent.postilTDOut(this)" onclick="parent.PostilClick(this)"><FONT COLOR="RED">'
		strPostil+='��ͬ��'+'</FONT></td>'
		strPostil+='</tr>'

		strPostil+='<tr align=left>'
		strPostil+='<td nowrap  COLOR="RED" bgcolor="#ffFFFF" style="border:solid 1pt #FFFFFF" onmouseover="parent.postilTDMove(this)"'
		strPostil+=' onmouseout="parent.postilTDOut(this)" onclick="parent.PostilClick(this)"><FONT COLOR="RED">'
		strPostil+='�뿪������'+'</FONT></td>'


    strPostil+='<tr align=center bgcolor="#94aad6">'
    strPostil+='<td  style="color: #FFFFFF;  font-family: Arial, Helvetica, sans-serif;font-size: 12px;font-style: normal;font-weight: bold;border:1pt solid #94aad6"'
    strPostil+=' onclick="this.style.borderColor=\'#94aad6\';parent.hidePostil();"'
    strPostil+=' onmouseover="parent.postilTDMove(this)"'
    strPostil+=' onmouseout="this.style.borderColor=\'#94aad6\'">�ر�'
    strPostil+='<td style="border:1pt solid #94aad6">&nbsp;</td><tr>'
    strPostil+='</table>'

    PostilBody.innerHTML=strPostil
