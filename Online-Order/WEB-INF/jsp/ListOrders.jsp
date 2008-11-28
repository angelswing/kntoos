
<%@ page language="java" contentType="text/html; charset=gb2312" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>My Orders</title>
</head>
<body>
<center><font size="4"><b>My Orders</b></font></center>
<table align="center" bgcolor="#008800" border="0" cellspacing="2"
	cellpadding="3">
	<tr bgcolor="#CCCCCC">
		<td><b>order ID</b></td>
		<td><b>client name</b></td>
		<td><b>order type</b></td>
		<td><b>order status</b></td>
		<td><b>ordered date</b></td>
		<td><b>requested date</b></td>
	</tr>
	<c:forEach var="order" items="${orderList}">
		<tr bgcolor="#FFFF88">
			<td><b><a
				href="<c:url value="/viewOrder.ord"><c:param name="orderId" value="${order.id}"/></c:url>">
			<font color="BLACK"><c:out value="${order.id}" /></font> </a></b></td>
			<td><c:out value="${order.clientname}" /></td>
			<td><c:out value="${order.ordertype}" /></td>
			<td><c:out value="${order.orderstatus}" /></td>
			<td><fmt:formatDate value="${order.ordereddate}"
				pattern="yyyy/MM/dd hh:mm:ss" /></td>
			<td><fmt:formatDate value="${order.requesteddate}"
				pattern="yyyy/MM/dd hh:mm:ss" /></td>
		</tr>
	</c:forEach>
</table>
</body>
</html>
