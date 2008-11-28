
<%@ page language="java" contentType="text/html; charset=gb2312" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>view order</title>
<link href="<%=request.getContextPath()%>/resources/style/style.css" type=text/css rel=stylesheet>
</head>
<body style="overflow-x: hidden; overflow-y: hidden">
<table width="60%" align="center" border="0" cellpadding="3"
	cellspacing="1" bordercolor="#2268A6" bordercolordark="ffffff">
	<tr>
		<td align="center" colspan="2"><font size="4"><b>Order
		#<c:out value="${order.id}" /></b></font>
	</tr>
	<tr>
		<td colspan="2">
			<font color="GREEN" size="4"><b>Customer Details</b></font>
		</td>
	</tr>
	<tr>
		<td>Customer Name:</td>
		<td><c:out value="${order.clientname}" /></td>
	</tr>
	<tr>
		<td>Telephone:</td>
		<td><c:out value="${order.telephone}" /></td>
	</tr>
	<tr>
		<td>Requested Date:</td>
		<td>
			<fmt:formatDate value="${order.requesteddate}" pattern="yyyy/MM/dd hh:mm:ss" />
		</td>
	</tr>		
	<tr>
		<td>Ordered Date:</td>
		<td>
			<fmt:formatDate value="${order.ordereddate}" pattern="yyyy/MM/dd hh:mm:ss" />
		</td>
	</tr>
	<tr>
		<td>Order Type:</td>
		<td><c:out value="${order.ordertype}" /></td>
	</tr>
	<tr>
		<td>Order Status:</td>
		<td><c:out value="${order.orderstatus}" /></td>
	</tr>
	<c:choose>
		<c:when test="${empty order.lensdetail}">
			<tr>
				<td colspan="2">
					<font color="GREEN" size="4">		
						<b>Frame Details</b>
					</font>
				</td>
			</tr>
		</c:when>
		<c:when test="${empty order.framedetail}">
			<tr>
				<td colspan="2">
					<font color="GREEN" size="4">		
						<b>Lens Details</b>
					</font>
				</td>
			</tr>
			<!--
			<tr>
				<td>Lens Type:</td>
				<td>
					<c:out value="${order.lensdetail.lensmodel}" />
				</td>
			</tr>
			-->
			<tr>
				<td>Diameter:</td>
				<td><c:out value="${order.lensdetail.diameter}" /></td>
			</tr>
			<tr>
				<td>Decentration:</td>
				<td><c:out value="${order.lensdetail.decentration}" /></td>
			</tr>
			<tr>
				<td>Right Sphere:</td>
				<td><c:out value="${order.lensdetail.rsphere}" /></td>
			</tr>
			<tr>
				<td>Left Sphere:</td>
				<td><c:out value="${order.lensdetail.lsphere}" /></td>
			</tr>
			<tr>
				<td>Right Cylinder:</td>
				<td><c:out value="${order.lensdetail.rcylinder}" /></td>
			</tr>
			<tr>
				<td>Left Cylinder:</td>
				<td><c:out value="${order.lensdetail.lcylinder}" /></td>
			</tr>
			<tr>
				<td>Right Axis:</td>
				<td><c:out value="${order.lensdetail.raxis}" /></td>
			</tr>
			<tr>
				<td>Left Axis:</td>
				<td><c:out value="${order.lensdetail.laxis}" /></td>
			</tr>
			<tr>
				<td>Right ADD:</td>
				<td><c:out value="${order.lensdetail.radd}" /></td>
			</tr>
			<tr>
				<td>Left ADD:</td>
				<td><c:out value="${order.lensdetail.ladd}" /></td>
			</tr>
			<tr>
				<td>Right Prism:</td>
				<td><c:out value="${order.lensdetail.rhprism}" /></td>
			</tr>
			<tr>
				<td>Left Prism:</td>
				<td><c:out value="${order.lensdetail.lhprism}" /></td>
			</tr>
			<tr>
				<td>Right Basecurve:</td>
				<td><c:out value="${order.lensdetail.rbasecurve}" /></td>
			</tr>
			<tr>
				<td>Left Basecurve:</td>
				<td><c:out value="${order.lensdetail.lbasecurve}" /></td>
			</tr>
		</c:when>
		<c:otherwise>
			<tr>
				<td colspan="2">
					<font color="GREEN" size="4">		
						<b>Details</b>
					</font>
				</td>
			</tr>
		</c:otherwise>
	</c:choose>
</table>
</body>
</html>
