<%@ page contentType="text/html; charset=gb2312" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	response.setHeader("Cache-Control", "no-store");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
%>

<HTML>
<HEAD>
<TITLE>Errors</TITLE>
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<LINK href="<c:url value="/resources/style/style.css"/>" type="text/css" rel="stylesheet"/>
<STYLE type="text/css">
.style1
{
	COLOR: #ff0000
}

.style2
{
	FONT-SIZE: 12px;
	COLOR: #CC233B;
	font-weight: bolder
}

.style3
{
	FONT-SIZE: 12px;
	COLOR: #CC233B;
	font-weight: bolder
}
</STYLE>
</HEAD>
<BODY>
<TABLE cellSpacing="0" cellPadding="0" width="95%" align="center" border="0">
	<TBODY>
		<TR>
			<TD height=60>&nbsp;</TD>
		</TR>
	</TBODY>
</TABLE>
<TABLE cellSpacing="0" cellPadding="0" width="534" align="center" border="0">
	<TBODY>
		<TR>
			<TD colSpan=3><IMG height=42
				src="<c:url value="/resources/images/error_r1_c1.gif"/>" width=534
				border=0></TD>
			<TD><IMG height=42
				src="<c:url value="/resources/images/mipas"/>" width=1 border=0></TD>
		</TR>
		<TR>
			<TD rowSpan=3><IMG height=239
				src="<c:url value="/resources/images/error_r2_c1.gif"/>" width=43
				border=0></TD>
			<TD class=htd align=center width=479 bgColor=#f7f7f7 height=228>
			<TABLE cellSpacing=6 cellPadding=6 width="85%" border=0>
				<TBODY>
					<TR>
						<TD>
						<DIV class=style2 align=center><c:out value="${error.msg}" /></DIV>
						</TD>
					</TR>
					<TR>
						<TD align=center>
							<c:choose>
							<c:when test="${empty error.target}">
								<A href="<c:url value="${error.url}"/>" target="main">
									<img src="<c:url value="/resources/images/fh.gif"/>" border="0"/>
								</A>
							</c:when>
							<c:otherwise>
							<A href="<c:url value="${error.url}"/>" target="<c:out value="${error.target}"/>">
								<img src="<c:url value="/resources/images/fh.gif"/>" border="0"/>
							</A>
							</c:otherwise>
							</c:choose>
						</TD>
					</TR>
				</TBODY>
			</TABLE>
			</TD>
			<TD rowSpan=3><IMG height=239
				src="<c:url value="/resources/images/error_r2_c3.gif"/>" width=12
				border=0></TD>
			<TD><IMG height=228 src="" width=1 border=0></TD>
		</TR>
		<TR>
			<TD><IMG height=11
				src="<c:url value="/resources/images/error_r3_c2.gif"/>" width=479
				border=0></TD>
		</TR>
	</TBODY>
</TABLE>
</BODY>
</HTML>
