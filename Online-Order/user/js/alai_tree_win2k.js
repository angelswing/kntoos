/*****************************************************************************************
	����Ŀ¼���ؼ�Windows2000 Explorerģ����� �������������2003��7��16�գ���������Ȩ����
******************************************************************************************/

function getScriptPath(js)
{
	js=js.toLowerCase()
	var script=document.getElementsByTagName("SCRIPT");
	for(var i=0;i<script.length;i++)
	{
		var s=script[i].src.toLowerCase()
		if(s.indexOf(js)!=-1)return s.replace(js,"")
	}
	return null
}

function alai_tree_win2k(toObject)
{
	var path=getScriptPath("alai_tree.js")
	if(path==null){alert("run alai_tree_win2k() fail, please load alai_tree.js first!");return;}
	var icons=new alai_imagelist()
	icons.path=path+"images/"
	//icons.add("fold2k_close","default")
	//icons.add("fold2k_open","open")
	//icons.add("fold2k_close","close")
	icons.add("plus_m","expand")
	icons.add("plus_top","expand_top")
	icons.add("plus_end","expand_end")
	icons.add("minus_m","collapse")
	icons.add("minus_top","collapse_top")
	icons.add("minus_end","collapse_end")
	icons.add("branch","leaf")
	icons.add("branch_end","twig")
	icons.add("vline","line")
	icons.add("blank")
	var tree=new alai_tree(icons,0,toObject)
	var lastNode=null
	/*
	tree.onclick=function(srcNode)
	{
		if(lastNode!=null)lastNode.icon.src=icons.item["close"].src
		srcNode.icon.src=icons.item["open"].src
		lastNode=srcNode
		return true;
	}
	*/
	return tree
}
