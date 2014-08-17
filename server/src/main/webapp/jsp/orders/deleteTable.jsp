<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/fn.tld" prefix="fn" %>

<jsp:include page='/jsp/commons/checkSessionAuthorization.jsp'/>

<html>
	<head> 
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" >
		<link rel='stylesheet' type='text/css' href='<c:out value="${pageContext.request.contextPath}"/>/css/montagnesdor.css'/>
		<title><fmt:message key="montagnesdor.welcome"/> - <fmt:message key="deleteTable.jsp.title"/></title>
		<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}"/>/javascript/commons.js"></script>		
		<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}"/>/javascript/tableOrders.js"></script>		
		<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}"/>/javascript/deleteTable.js"></script>
	</head>

	<body onload='initPage()'>
		<script language="javascript">
			//Cette variable est utilisée dans le fichier deleteTable.js
			var contextPath = '<c:out value="${pageContext.request.contextPath}"/>';
		</script>
		<c:if test="${(not empty userSession.room.currentTable.orders)}">
				<table class="border" width="100%" align="center">
					<tr>
						<td class="border"><b><font color="#FFCC00">|</font></b></td>
						<td class="border" width="100%" colspan="3"><fmt:message key="deleteTable.jsp.label.deleting.table"><fmt:param><c:out value="${userSession.room.currentTable.number}"/></fmt:param></fmt:message></td>
						<td class="border"><b><font color="#FFCC00">|</font></b></td>
					</tr>
					<tr>
						<td class="border"><b><font color="#FFCC00">|</font></b></td>
						<td class="border" width="100%" colspan="3"><label class="policeGray"><fmt:message key="deleteTable.jsp.alert.deleting"/></label></td>
						<td class="border"><b><font color="#FFCC00">|</font></b></td>
					</tr>	
					<tr>
						<td class="border"><b><font color="#FFCC00">|</font></b></td>
					   	<td class="border" width="50%"><a accesskey='<fmt:message key="deleteTable.jsp.accesskey.cancel"/>' href="javascript:cancel()" class="cancel"><fmt:message key="label.cancel"/></a></td>
						<td class="border"><b><font color="#FFCC00">|</font></b></td>
						<td class="border" width="50%"><a id="idFocusAnchor" accesskey='<fmt:message key="deleteTable.jsp.accesskey.confirm"/>' href="javascript:deleteTable('<c:out value="${userSession.room.currentTable.id}"/>')" class="confirm" ><fmt:message key="label.confirm"/></a></td>
						<td class="border"><b><font color="#FFCC00">|</font></b></td>
					</tr>
				</table>
		</c:if>
		<c:if test="${empty userSession.room.currentTable.orders}">
				<table class="border" width="100%" align="center">
					<tr>
						<td class="border"><b><font color="#FFCC00">|</font></b></td>
						<td class="border" width="100%"><label class="policeGray"><fmt:message key="deleteTable.jsp.error.orders.empty"><fmt:param><c:out value="${userSession.room.currentTable.number}"/></fmt:param></fmt:message></label></td>
						<td class="border"><b><font color="#FFCC00">|</font></b></td>
					</tr>	
					<tr>
						<td class="border"><b><font color="#FFCC00">|</font></b></td>
						<td class="border" width="100%"><a id="idFocusAnchor" accesskey='<fmt:message key="deleteTable.jsp.accesskey.cancel"/>' href="javascript:cancel()"><fmt:message key="label.back"/></a></td>
						<td class="border"><b><font color="#FFCC00">|</font></b></td>
					</tr>
				</table>
		</c:if>
		<c:if test="${not empty userSession.room.currentTable.cashingDate}">
				<table class="border" width="100%" align="center">
					<tr>
						<td class="border"><b><font color="#FFCC00">|</font></b></td>
						<td class="border" width="100%"><label class="policeGray"><fmt:message key="deleteTable.jsp.error.table.cashed"/></label></td>
						<td class="border"><b><font color="#FFCC00">|</font></b></td>
					</tr>	
					<tr>
						<td class="border"><b><font color="#FFCC00">|</font></b></td>
						<td class="border" width="100%"><a accesskey='<fmt:message key="printTable.jsp.accesskey.cancel"/>' href="javascript:cancel()"><fmt:message key="label.back"/></a></td>
						<td class="border"><b><font color="#FFCC00">|</font></b></td>
					</tr>
				</table>
		</c:if>
		<form name="modifyTableForm" action="<c:out value="${pageContext.request.contextPath}"/>/DisplayJsp.do" method="post" onsubmit="return false" target="IFrameData">
			<input type="hidden" name="pageRequested" value="successTableOrdersIFrame">
			<input type="hidden" name="tableId" value="<c:out value="${userSession.room.currentTable.id}"/>">
			<input type="hidden" name='actionPasswordChangeOrders' value="allowModifyOrders">
			<input type="hidden" name='printingType' value="NONE">		
		</form>
		<div style='position: relative; height: 0;visibility:hidden'>
			<c:if test="${not empty userSession.room.currentTable.orders}">	
			<label id="idCurrentTableRegistrationDate"><fmt:formatDate value="${userSession.room.currentTable.registrationDate}" pattern="dd/MM/yyyy" type="DATE"/></label>
			</c:if>	
		</div>
		<jsp:include page='/jsp/commons/includeDivIFrame.jsp'/>
	</body>
</html>