
<%@ page language="java" contentType="text/html; charset=gb2312" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
	String totalCount = "0";
	String pageId = "0";
	String pageCount = "0";
	String pageSize = "0";
	
	if(request.getAttribute("totalCount") != null)
	{
		totalCount = (String)request.getAttribute("totalCount");
	}
	if(request.getAttribute("pageId") != null)
	{
		pageId = (String)request.getAttribute("pageId");
	}
	if(request.getAttribute("pageCount") != null)
	{
		pageCount = (String)request.getAttribute("pageCount");
	}
	if(request.getAttribute("pageSize") != null)
	{
		pageSize = (String)request.getAttribute("pageSize");
	}
	System.out.println("totalCount: " + totalCount + " pageId: " + pageId);
	System.out.println("pageCount: " + pageCount + " pageSize: " + pageSize);
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>Query Orders</title>
<link href="<%=request.getContextPath()%>/resources/style/style.css" type="text/css" rel="stylesheet" />
	<script language="javascript" type="text/javascript">
	function checkAll(form)
	{
		for(var i = 0; i < form.elements.length; i++)
		{
	    	var e = form.elements[i];
	    	if(e.name != 'checkall')
	    	{
	        	e.checked = form.checkall.checked;
	    	}
	   }
	}
	
	function checkSelected()
	{
		var allcheck = document.getElementsByName("ids");
	  	for(var i = 0; i < allcheck.length; i++)
	  	{
			if(allcheck[i].checked)
			{
		  		return true;
			}
	  	}
	  	alert("please select items which you want to delete!");
	  	return false;
	}
	</script>
</head>

<body style="overflow-x: hidden">
<br>
<table width="98%" border="0" cellspacing="0" cellpadding="0" align="center">
	<tr>
		<td height="30" class=table1><img
			src="<%=request.getContextPath()%>/resources/images/arrow.gif"
			width="20" height="18" hspace="4" align="absmiddle">Orders List</td>
	</tr>

	<tr>
		<td colspan="4">
		</td>
	</tr>

	<tr>
		<td>
		<form name="queryOrdersForm" id="queryOrdersForm"
			action="<c:url value="/queryOrders.ord"/>" method="post">
		<table class=table9 width="99%" border="1" align="center"
			cellpadding="0" cellspacing="0" bordercolor="#2268A6"
			bordercolordark="ffffff">
			<tr>
				<td align="left" width="7%" class="table8">&nbsp;Order Status&nbsp;</td>
				<td align="left" width="12%">
					<select class="input" name="orderstatus">
					<c:forEach var="order_status" items="${orderstatusList}"> 
						<option
							value="<c:out value="${order_status}"/>">
							<c:out value="${order_status}" />
						</option>
					</c:forEach>
					</select>
				</td>
				<td align="left" width="7%" class="table8">&nbsp;Order Type&nbsp;</td>
				<td align="left" width="12%">
					<select class="input" name="ordertype">
					<c:forEach var="order_type" items="${ordertypeList}"> 
						<option
							value="<c:out value="${order_type}"/>">
							<c:out value="${order_type}" />
						</option>
					</c:forEach>
					</select>
				</td>
				<td align="left" width="5%" class="table8">&nbsp;Order Id&nbsp;</td>
				<td align="left" width="12%">
					<input type="text" name="id" value="" />
				</td>
				<td align="left" width="32%">
					<input type="submit" width="74" height="22"
					align="absmiddle" class="button" value="Query"/>
				</td>
			</tr>
		</table>
		</form>
		</td>
	</tr>

	<tr>
		<td></td>
	</tr>

	<tr>
		<td>
		<table width="98%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td height="10"></td>
			</tr>
			<tr>
				<input name="addOrder" type="button" width="80" height="22"
					align="absmiddle" class="button_" value="AddOrder"
					onclick="javascript:location.href='<c:url value="/addLensOrder.ord"/>'">
				</td>
			</tr>
		</table>
		</td>
	</tr>

	<tr>
		<td></td>
	</tr>

	<tr>
		<form name="listOrdersForm" id="listOrdersForm"
			action="<%=request.getContextPath()%>/deleteOrders.ord" method="post" 
			onsubmit="return checkSelected()">
		<td>
		<table width="99%" border="1" align="center" cellpadding="0"
			cellspacing="0" bordercolor="#2268A6" bordercolordark="ffffff">

			<tr class=table7>
				<td width="3%">&nbsp;</td>
				<td width="3%">Id</td>
				<td width="10%">Client Name</td>
				<td width="10%">Order Type</td>
				<td width="12%">Order Status</td>
				<td width="20%">Ordered Date</td>
				<td width="10%">Requested Date</td>
				<td width="14%">Operation</td>
			</tr>
			<c:forEach var="order" items="${result}" varStatus="itemStatus">
				<c:choose>
			    <c:when test="${itemStatus.index % 2 == 0}">
	    		    <tr class="table5">
	    		</c:when>
	    		<c:otherwise>
	        		<tr class="table3">
	    		</c:otherwise>
				</c:choose>
					<td>&nbsp;<input name="ids" type="checkbox"	value="${order.id}"></td>
					<td><c:out value="${order.id}" /></td>
					<td><c:out value="${order.clientname}" /></td>
					<td><c:out value="${order.ordertype}" /></td>
					<td><c:out value="${order.orderstatus}" /></td>
					<td><fmt:formatDate value="${order.ordereddate}"
						pattern="yyyy/MM/dd" /></td>
					<td><fmt:formatDate value="${order.requesteddate}"
						pattern="yyyy/MM/dd" /></td>
					<td>
						<a href="<c:url value="/viewOrder.ord"><c:param name="orderId" value="${order.id}"/></c:url>">
							<img src="<c:url value="/resources/images/ck.gif"/>" border="0" alt="view" />
						</a>
						<a href="<c:url value="/modifyLensOrder.ord"><c:param name="orderId" value="${order.id}"/></c:url>">
							<img src="<c:url value="/resources/images/icon_edit.gif"/>" border="0" alt="modify" />
						</a>
					</td>
				</tr>
			</c:forEach>
		</table>
		<table width="99%" height="40" border="0" align="center"
			cellpadding="0" cellspacing="0">
			<tr class="fy">
				<td width="20%"><input name="checkall" type="checkbox" value="on"
					onclick="checkAll(this.form)" />check all&nbsp; <input name="deleteAll"
					type="image" src="<c:url value="/resources/images/del_.gif" />" width="50" height="22" align="absmiddle" border="0" value="delete" />
				</td>
				<td width="20%" align="center" valign="middle">&nbsp;&nbsp;&nbsp;page <c:out value="${pageId + 1}"/>
				&nbsp;<c:out value="${totalCount}" />records&nbsp;<c:out value="${pageCount}" />pages</td>
				<td width="40%" align="center">
					<a href="<%=request.getContextPath()%>/queryOrders.ord?pageId=0">
						<img src="<c:url value="/resources/images/go1.gif"/>"
						name=button width="51" height="19" hspace="8" border=0
						align="absmiddle">
					</a>
					<a href="<%=request.getContextPath()%>/queryOrders.ord?pageId=<%=Math.max((Integer.parseInt(pageId)-1),0)%>">
						<img src="<c:url value="/resources/images/go2.gif"/>"
						name=button width="51" height="19" hspace="8" border=0
						align="absmiddle">
					</a>
					<a href="<%=request.getContextPath()%>/queryOrders.ord?pageId=<%=Math.min((Integer.parseInt(pageId)+1),(Integer.parseInt(pageCount)-1))%>">
						<img src="<c:url value="/resources/images/go3.gif"/>"
						name=button width="51" height="19" hspace="8" border=0
						align="absmiddle">
					</a>
					<a href="<%=request.getContextPath()%>/queryOrders.ord?pageId=<%=Integer.parseInt(pageCount)-1%>">
						<img src="<c:url value="/resources/images/go4.gif"/>"
						name=button width="51" height="19" hspace="8" border=0
						align="absmiddle">
					</a>
				</td>
			</tr>
			
		</table>
		</td>
		</form>
	</tr>
</table>
</body>

</html>
