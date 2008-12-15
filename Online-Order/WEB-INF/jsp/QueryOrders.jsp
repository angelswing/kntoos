
<%@ page language="java" contentType="text/html; charset=gb2312"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>Query Orders</title>
<link href="<c:url value="/resources/style/style.css" />"
	type="text/css" rel="stylesheet" />
<script language="javascript" 
	src="<c:url value="/resources/js/check.js"/>" type="text/javascript">
</script>
<script language="javascript" type="text/javascript">
	function checkAll(form) 
	{
		for(var i = 0; i < form.elements.length; i++)
		{
			var e = form.elements[i];
			if(e.name != "checkall")
			{
				e.checked = form.checkall.checked;
			}
		}
	}

	function checkSelected()
	{
		var allcheck = document.getElementsByTagName("input");
		for(var i=0; i< allcheck.length; i++)
		{
			if(allcheck[i].type == "checkbox" && allcheck[i].checked)
			{
				return true;
			}
		}
		alert("please select items which you want to delete!");
		return false;
	}
</script>
</head>

<body>
<br>
<table width="98%" border="0" cellspacing="0" cellpadding="0"
	align="center">
	<tr>
		<td height="30" class=table1><img
			src="<c:url value="/resources/images/arrow.gif" />"
			width="20" height="18" hspace="4" align="absmiddle">Orders List</td>
	</tr>

	<tr>
		<td colspan="4"></td>
	</tr>

	<tr>
		<td>
		<form name="orderQuerier" id="orderQuerier"
			action="<c:url value="/queryOrders.ord"/>" method="post">
		<table class=table9 width="99%" border="1" align="center"
			cellpadding="0" cellspacing="0" bordercolor="#2268A6"
			bordercolordark="ffffff">
			<tr>
				<td align="left" width="7%" class="table8">&nbsp;Order
				Status&nbsp;</td>
				<td align="left" width="12%">
				<spring:bind path="orderQuerier.orderstatus">
					<select class="input"
						name="<c:out value="${status.expression}"/>">
						<c:forEach var="order_status" items="${listOrderstatus}">
							<option <c:if test="${order_status == status.value}">selected</c:if> 
							value="<c:out value="${order_status}"/>"><c:out value="${order_status}"/>
							</option>
						</c:forEach>
					</select>
				</spring:bind>
				</td>
				<td align="left" width="7%" class="table8">&nbsp;Order
				Type&nbsp;</td>
				<td align="left" width="12%">
				<spring:bind path="orderQuerier.ordertype">
					<select class="input"
						name="<c:out value="${status.expression}"/>">
						<c:forEach var="order_type" items="${listOrdertype}">
							<option
							<c:if test="${order_type == status.value}">selected</c:if>  
							value="<c:out value="${order_type}"/>"><c:out
								value="${order_type}" /></option>
						</c:forEach>
					</select>
				</spring:bind>
				</td>
				<td align="left" width="5%" class="table8">&nbsp;Order Id&nbsp;</td>
				<td align="left" width="12%">
					<spring:bind path="orderQuerier.id">
						<input type="text" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>"/>
						<c:if test="${status.error}">
							<font class="red">${status.errorMessage}</font>
						</c:if>
					</spring:bind>
				</td>
				<td align="left" width="32%"><input type="submit" width="74"
					height="22" align="absmiddle" class="button" value="Query" /></td>
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
				<td align="right">
				<input name="addOrder" type="button" width="80" height="22" 
					align="absmiddle" class="button_" value="AddOrder" 
					onclick="javascript:location.href='<c:url value="/addLensOrder.ord"/>'"/>
				</td>
			</tr>
		</table>
		</td>
	</tr>

	<tr>
		<td></td>
	</tr>

	<tr>
		<td>
		<form name="listOrdersForm" id="listOrdersForm"
			action="<c:url value="/deleteOrders.ord"/>" method="post"
			onsubmit="return checkSelected()">
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
			<c:forEach var="order" items="${orderQuerier.listOrder}" varStatus="itemStatus">
				<c:choose>
					<c:when test="${itemStatus.index % 2 == 0}">
						<tr class="table5">
					</c:when>
					<c:otherwise>
						<tr class="table3">
					</c:otherwise>
				</c:choose>
				<td>
					<spring:bind path="orderQuerier.listOrder[${itemStatus.index}].id">
						<input name="<c:out value="${status.expression}"/>" 
						type="checkbox" value="<c:out value="${status.value}"/>" />
					</spring:bind>
				</td>
				<td><c:out value="${orderQuerier.listOrder[itemStatus.index].id}"/></td>
				<td><c:out value="${orderQuerier.listOrder[itemStatus.index].clientname}"/></td>
				<td>
					<spring:bind path="orderQuerier.listOrder[${itemStatus.index}].ordertype">
						<input type="hidden" name="<c:out value="${status.expression}"/>"
							value="<c:out value="${status.value}"/>"/>
						<c:out value="${status.value}"/>
					</spring:bind>
				</td>
				<td>
					<spring:bind path="orderQuerier.listOrder[${itemStatus.index}].orderstatus">
						<input type="hidden" name="<c:out value="${status.expression}"/>"
							value="<c:out value="${status.value}"/>"/>
						<c:out value="${status.value}"/>
					</spring:bind>
				</td>
				<td>
					<fmt:formatDate value="${orderQuerier.listOrder[itemStatus.index].ordereddate}"
					pattern="yyyy/MM/dd" />
				</td>
				<td>
					<fmt:formatDate value="${orderQuerier.listOrder[itemStatus.index].requesteddate}"
					pattern="yyyy/MM/dd" />
				</td>
				<td>
				<a href="<c:url value="/viewOrder.ord"><c:param name="orderId" value="${orderQuerier.listOrder[itemStatus.index].id}"/></c:url>">
				<img src="<c:url value="/resources/images/ck.gif"/>" border="0" alt="view" />
				</a>
				<a href="<c:url value="/modifyLensOrder.ord"><c:param name="orderId" value="${orderQuerier.listOrder[itemStatus.index].id}"/></c:url>">
				<img src="<c:url value="/resources/images/icon_edit.gif"/>"
					border="0" alt="modify" />
				</a>
				</td>
				</tr>
			</c:forEach>
		</table>
		<table width="99%" height="40" border="0" align="center"
			cellpadding="0" cellspacing="0">
			<tr class="fy">
				<td width="20%">
				<input name="checkall" type="checkbox" 
					value="on" onclick="checkAll(this.form)"/>check all&nbsp;
				<input name="deleteAll" type="image"
					src="<c:url value="/resources/images/del_.gif"/>" width="50"
					height="22" align="absmiddle" border="0" value="delete"/>
				</td>
				<td width="20%" align="center" valign="absmiddle">&nbsp;&nbsp;&nbsp;Records&nbsp;
					<c:out value="${orderQuerier.recordCount}"/>&nbsp;Page&nbsp;
					<c:out value="${orderQuerier.pageNo + 1}"/>&nbsp;of&nbsp;
					<c:out value="${orderQuerier.pageCount}"/>&nbsp;
				</td>
				<td width="40%" align="center">
					<a href="<c:url value="/queryOrders.ord"><c:param name="pageNo" value="0"/></c:url>">
					<img src="<c:url value="/resources/images/go1.gif"/>" name="button"
						width="51" height="19" hspace="8" border=0 align="absmiddle"/>
					</a>
					<a href="<c:url value="/queryOrders.ord"><c:param name="pageNo" value="${((orderQuerier.pageNo - 1) > 0) ? (orderQuerier.pageNo - 1) : 0}"/></c:url>">
					<img src="<c:url value="/resources/images/go2.gif"/>" name="button"
						width="51" height="19" hspace="8" border=0 align="absmiddle"/>
					</a>
					<a href="<c:url value="/queryOrders.ord"><c:param name="pageNo" value="${((orderQuerier.pageNo + 1) < (orderQuerier.pageCount - 1)) ? (orderQuerier.pageNo + 1) : (orderQuerier.pageCount - 1)}"/></c:url>">
					<img src="<c:url value="/resources/images/go3.gif"/>" name="button"
						width="51" height="19" hspace="8" border=0 align="absmiddle"/>
					</a>
					<a href="<c:url value="/queryOrders.ord"><c:param name="pageNo" value="${orderQuerier.pageCount - 1}"/></c:url>">
					<img src="<c:url value="/resources/images/go4.gif"/>" name="button"
						width="51" height="19" hspace="8" border=0 align="absmiddle"/>
					</a>
				</td>
			</tr>
		</table>
		</form>
		</td>
	</tr>
</table>
</body>

</html>
