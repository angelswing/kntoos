<%-- 
    Document   : AddLensOrder
    Created on : 2008-11-16, 23:19:30
    Author     : Administrator
--%>

<%@ page contentType="text/html; charset=gb2312"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
<title>lens order</title>
<link href="<%=request.getContextPath()%>/resources/style/style.css"
	type=text/css rel=stylesheet>
<script language="JavaScript"
	src="<%=request.getContextPath()%>/resources/js/calendar.js"
	type="text/JavaScript"></script>
<script language="JavaScript">
	function checkSafe() {
		return true;
	}
</script>
</head>

<body style="overflow-x: hidden; overflow-y: hidden">
<c:if test="${empty lensOrderForm.order.id}">
	<form action="<c:url value="/addLensOrder.ord"/>" method="post">
</c:if>
<c:if test="${!empty lensOrderForm.order.id}">
	<form action="<c:url value="/modifyLensOrder.ord"/>" method="post">
</c:if>
<table width="100%" border="0" cellspacing="0" cellpadding="0"
	align="center">
	<tr>
		<td height="30" class=table1><img
			src="<%=request.getContextPath()%>/resources/images/arrow.gif"
			width="20" height="18" hspace="4" align="absmiddle">Lens Order</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td><spring:bind path="lensOrderForm.order.id">
			<input type="hidden" name="<c:out value="${status.expression}"/>"
				value="<c:out value="${status.value}"/>" />
		</spring:bind>
		<table width="93%" border="1" align="center" cellpadding="0"
			cellspacing="0" bordercolor="#2268A6" bordercolordark="ffffff">
			<tr>
				<td align="left" width="15%" class="table8">&nbsp;Customer
				Name&nbsp;</td>
				<td align="left" width="35%"><spring:bind
					path="lensOrderForm.order.clientname">
					<input type="text" name="<c:out value="${status.expression}"/>"
						value="<c:out value="${status.value}"/>" />
					<c:if test="${status.error}">
						<font class="red">${status.errorMessage}</font>
					</c:if>
				</spring:bind></td>
				<td align="left" width="15%" class="table8">&nbsp;Telephone&nbsp;</td>
				<td><spring:bind path="lensOrderForm.order.telephone">
					<input type="text" name="<c:out value="${status.expression}"/>"
						value="<c:out value="${status.value}"/>" />
					<c:if test="${status.error}">
						<font class="red">${status.errorMessage}</font>
					</c:if>
				</spring:bind></td>
			</tr>
			<tr>
				<td align="left" width="15%" class="table8">&nbsp;Requested
				Date&nbsp;</td>
				<td><spring:bind path="lensOrderForm.order.requesteddate">
					<input type="text" name="<c:out value="${status.expression}"/>"
						value="<c:out value="${status.displayValue}"/>"
						onFocus="setday(this)" />
					<c:if test="${status.error}">
						<font class="red">${status.errorMessage}</font>
					</c:if>
				</spring:bind></td>
				<td align="left" width="15%" class="table8">&nbsp;Ordered
				Date&nbsp;</td>
				<td><spring:bind path="lensOrderForm.order.ordereddate">
					<input type="text" name="<c:out value="${status.expression}"/>"
						value="<c:out value="${status.displayValue}"/>"
						onFocus="setday(this)" />
					<c:if test="${status.error}">
						<font class="red">${status.errorMessage}</font>
					</c:if>
				</spring:bind></td>
			</tr>
			<tr>
				<td align="left" width="15%" class="table8">&nbsp;Lens
				Type&nbsp;</td>
				<td align="left" colspan="3"><spring:bind
					path="lensOrderForm.lensdetail.lensmodel">
					<select name="<c:out value="${status.expression}"/>">
						<c:forEach var="lens_model" items="${lensmodels}">
							<option
								<c:if test="${lens_model.lensmodel == status.value}">selected</c:if>
								value="<c:out value="${lens_model.lensmodel}"/>"><c:out
								value="${lens_model.lensmodel}" /></option>
						</c:forEach>
					</select>
					<c:if test="${status.error}">
						<font class="red">${status.errorMessage}</font>
					</c:if>
				</spring:bind></td>
			</tr>
			<tr>
				<td align="left" width="15%" class="table8">&nbsp;Lens
				Diameter&nbsp;</td>
				<td align="left" width="35%"><spring:bind
					path="lensOrderForm.lensdetail.diameter">
					<spring:transform value="${status.value}" var="diameter" />
					<input type="text" name="<c:out value="${status.expression}"/>"
						value="<c:out value="${diameter}"/>" />
					<c:if test="${status.error}">
						<font class="red">${status.errorMessage}</font>
					</c:if>
				</spring:bind></td>
				<td align="left" width="15%" class="table8">&nbsp;Decentration&nbsp;</td>
				<td align="left" width="35%"><spring:bind
					path="lensOrderForm.lensdetail.decentration">
					<spring:transform value="${status.value}" var="decentration" />
					<input type="text" name="<c:out value="${status.expression}"/>"
						value="<c:out value="${decentration}"/>" />
					<c:if test="${status.error}">
						<font class="red">${status.errorMessage}</font>
					</c:if>						
				</spring:bind></td>
			</tr>
			<tr>
				<td align="left" width="15%" class="table8">&nbsp;Right
				Sphere&nbsp;</td>
				<td align="left" width="35%"><spring:bind
					path="lensOrderForm.lensdetail.rsphere">
					<spring:transform value="${status.value}" var="rsphere" />
					<input type="text" name="<c:out value="${status.expression}"/>"
						value="<c:out value="${rsphere}"/>" />
					<c:if test="${status.error}">
						<font class="red">${status.errorMessage}</font>
					</c:if>						
				</spring:bind></td>
				<td align="left" width="15%" class="table8">&nbsp;Left
				Sphere&nbsp;</td>
				<td align="left" width="35%"><spring:bind
					path="lensOrderForm.lensdetail.lsphere">
					<spring:transform value="${status.value}" var="lsphere" />
					<input type="text" name="<c:out value="${status.expression}"/>"
						value="<c:out value="${lsphere}"/>" />
					<c:if test="${status.error}">
						<font class="red">${status.errorMessage}</font>
					</c:if>						
				</spring:bind></td>
			</tr>
			<tr>
				<td align="left" width="15%" class="table8">&nbsp;Right
				Cylinder&nbsp;</td>
				<td align="left" width="35%"><spring:bind
					path="lensOrderForm.lensdetail.rcylinder">
					<spring:transform value="${status.value}" var="rcylinder" />
					<input type="text" name="<c:out value="${status.expression}"/>"
						value="<c:out value="${rcylinder}"/>" />
					<c:if test="${status.error}">
						<font class="red">${status.errorMessage}</font>
					</c:if>						
				</spring:bind></td>
				<td align="left" width="15%" class="table8">&nbsp;Left
				Cylinder&nbsp;</td>
				<td align="left" width="35%"><spring:bind
					path="lensOrderForm.lensdetail.lcylinder">
					<spring:transform value="${status.value}" var="lcylinder" />
					<input type="text" name="<c:out value="${status.expression}"/>"
						value="<c:out value="${lcylinder}"/>" />
					<c:if test="${status.error}">
						<font class="red">${status.errorMessage}</font>
					</c:if>						
				</spring:bind></td>
			</tr>
			<tr>
				<td align="left" width="15%" class="table8">&nbsp;Right
				Axis&nbsp;</td>
				<td align="left" width="35%"><spring:bind
					path="lensOrderForm.lensdetail.raxis">
					<spring:transform value="${status.value}" var="raxis" />
					<input type="text" name="<c:out value="${status.expression}"/>"
						value="<c:out value="${raxis}"/>" />
					<c:if test="${status.error}">
						<font class="red">${status.errorMessage}</font>
					</c:if>						
				</spring:bind></td>
				<td align="left" width="15%" class="table8">&nbsp;Left
				Axis&nbsp;</td>
				<td align="left" width="35%"><spring:bind
					path="lensOrderForm.lensdetail.laxis">
					<spring:transform value="${status.value}" var="laxis" />
					<input type="text" name="<c:out value="${status.expression}"/>"
						value="<c:out value="${laxis}"/>" />
					<c:if test="${status.error}">
						<font class="red">${status.errorMessage}</font>
					</c:if>						
				</spring:bind></td>
			</tr>
			<tr>
				<td align="left" width="15%" class="table8">&nbsp;Right
				ADD&nbsp;</td>
				<td align="left" width="35%"><spring:bind
					path="lensOrderForm.lensdetail.radd">
					<spring:transform value="${status.value}" var="radd" />
					<input type="text" name="<c:out value="${status.expression}"/>"
						value="<c:out value="${radd}"/>" />
					<c:if test="${status.error}">
						<font class="red">${status.errorMessage}</font>
					</c:if>						
				</spring:bind></td>
				<td align="left" width="15%" class="table8">&nbsp;Left
				ADD&nbsp;</td>
				<td align="left" width="35%"><spring:bind
					path="lensOrderForm.lensdetail.ladd">
					<spring:transform value="${status.value}" var="ladd" />
					<input type="text" name="<c:out value="${status.expression}"/>"
						value="<c:out value="${ladd}"/>" />
					<c:if test="${status.error}">
						<font class="red">${status.errorMessage}</font>
					</c:if>						
				</spring:bind></td>
			</tr>
			<tr>
				<td align="left" width="15%" class="table8">&nbsp;Right
				Prism&nbsp;</td>
				<td align="left" width="35%"><spring:bind
					path="lensOrderForm.lensdetail.rhprism">
					<spring:transform value="${status.value}" var="rhprism" />
					<input type="text" name="<c:out value="${status.expression}"/>"
						value="<c:out value="${rhprism}"/>" />
					<c:if test="${status.error}">
						<font class="red">${status.errorMessage}</font>
					</c:if>						
				</spring:bind></td>
				<td align="left" width="15%" class="table8">&nbsp;Left
				Prism&nbsp;</td>
				<td align="left" width="35%"><spring:bind
					path="lensOrderForm.lensdetail.lhprism">
					<spring:transform value="${status.value}" var="lhprism" />
					<input type="text" name="<c:out value="${status.expression}"/>"
						value="<c:out value="${lhprism}"/>" />
					<c:if test="${status.error}">
						<font class="red">${status.errorMessage}</font>
					</c:if>						
				</spring:bind></td>
			</tr>
			<tr>
				<td align="left" class="table8">&nbsp;Right Basecurve&nbsp;</td>
				<td align="left" width="35%"><spring:bind
					path="lensOrderForm.lensdetail.rbasecurve">
					<spring:transform value="${status.value}" var="rbasecurve" />
					<input type="text" name="<c:out value="${status.expression}"/>"
						value="<c:out value="${rbasecurve}"/>" />
					<c:if test="${status.error}">
						<font class="red">${status.errorMessage}</font>
					</c:if>						
				</spring:bind></td>
				<td align="left" class="table8">&nbsp;Left Basecurve&nbsp;</td>
				<td align="left" width="35%"><spring:bind
					path="lensOrderForm.lensdetail.lbasecurve">
					<spring:transform value="${status.value}" var="lbasecurve" />
					<input type="text" name="<c:out value="${status.expression}"/>"
						value="<c:out value="${lbasecurve}"/>" />
					<c:if test="${status.error}">
						<font class="red">${status.errorMessage}</font>
					</c:if>						
				</spring:bind></td>
			</tr>
			<tr>
				<td align="left" class="table8">&nbsp;Remarks&nbsp;</td>
				<td align="left" colspan="3"><spring:bind
					path="lensOrderForm.order.remarks">
					<textarea cols="50" rows="3"
						name="<c:out value="${status.expression}"/>"
						value="<c:out value="${status.value}"/>">
                            </textarea>
				</spring:bind></td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td></td>
	</tr>
	<tr>
		<td>
		<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td height="10"></td>
			</tr>
			<tr>
				<td align="center"><c:choose>
					<c:when test="${empty lensOrderForm.order.id}">
						<input name="submitok" type="submit" width="74" height="22"
							align="absmiddle" class="button_" value="Add">&nbsp;&nbsp; <input
							type="reset" name="reset" value="Reset" class="button">
					</c:when>
					<c:otherwise>
						<input name="submitok" type="submit" width="74" height="22"
							align="absmiddle" class="button_" value="Modify">&nbsp;&nbsp; <input
							type="reset" name="reset" value="Reset" class="button">
					</c:otherwise>
				</c:choose></td>
			</tr>
		</table>
		</td>
	</tr>
</table>
</form>
</body>
</html>
