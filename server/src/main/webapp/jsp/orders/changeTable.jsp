<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt" %>

<jsp:include page='/jsp/commons/checkSessionAuthorization.jsp'/>

<html>
	<head> 
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" >
		<link rel='stylesheet' type='text/css' href='<c:out value="${pageContext.request.contextPath}"/>/css/montagnesdor.css'/>
		<title><fmt:message key="montagnesdor.welcome"/> - <fmt:message key="changeTable.jsp.title"/></title>
		<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}"/>/javascript/commons.js"></script>
		<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}"/>/javascript/tableOrders.js"></script>
		<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}"/>/javascript/changeTable.js"></script>
	</head>

	<body onload='initPage()'>
		<script language="javascript">
			//Cette variable est utilisée dans le fichier changeTable.js
			var contextPath = '<c:out value="${pageContext.request.contextPath}"/>';
		</script>
		<c:if test="${not empty userSession.room.currentTable.orders}">
			<html:form method="post" action='/ChangeTable.do' onsubmit="return false">
				<table class="border" width="100%" align="center">
					<tr>
						<td class="border"><b><font color="#FFCC00">|</font></b></td>
						<td class="border" width="50%"><fmt:message key="changeTable.jsp.label.change.table"><fmt:param><c:out value="${userSession.room.currentTable.number}"/></fmt:param></fmt:message></td>
						<td class="border"><b><font color="#FFCC00">|</font></b></td>
						<td class="border" width="50%">
							<input type='text' name='tableNumber' onkeyup='processUserEntry(event, this)' maxlength='5' class='text'>
						</td>			
						<td class="border"><b><font color="#FFCC00">|</font></b></td>
					</tr>	
					<tr>
						<td class="border"><b><font color="#FFCC00">|</font></b></td>
					   	<td class="border" width="50%"><a accesskey='<fmt:message key="changeTable.jsp.accesskey.cancel"/>' href="javascript:cancel()" class="cancel"><fmt:message key="label.cancel"/></a></td>
						<td class="border"><b><font color="#FFCC00">|</font></b></td>
						<td class="border" width="50%"><a accesskey='<fmt:message key="changeTable.jsp.accesskey.confirm"/>' href="javascript:changeTable()" class="confirm" ><fmt:message key="label.confirm"/></a></td>
						<td class="border"><b><font color="#FFCC00">|</font></b></td>
					</tr>
					<logic:messagesPresent>
					<tr>
						<td class="border"><b><font color="#FFCC00">|</font></b></td>
						<td colspan=3 class="border">
							<label class="policeGray">
							<html:errors property="changeTable.jsp.error.table.number"/>
							<html:errors property="changeTable.jsp.error.table.exist"/>
							<html:errors property="changeTable.jsp.error.same.table"/>
							</label>
						</td>
						<td class="border"><b><font color="#FFCC00">|</font></b></td>
					</tr>	
					</logic:messagesPresent>
				</table>
			</html:form>
		</c:if>
		<c:if test="${empty userSession.room.currentTable.orders}">
				<table class="border" width="100%" align="center">
					<tr>
						<td class="border"><b><font color="#FFCC00">|</font></b></td>
						<td class="border" width="100%"><label class="policeGray"><fmt:message key="changeTable.jsp.error.orders.empty"><fmt:param><c:out value="${userSession.room.currentTable.number}"/></fmt:param></fmt:message></label></td>
						<td class="border"><b><font color="#FFCC00">|</font></b></td>
					</tr>	
					<tr>
						<td class="border"><b><font color="#FFCC00">|</font></b></td>
						<td class="border" width="100%"><a id="idFocusAnchor" accesskey='<fmt:message key="changeTable.jsp.accesskey.back"/>' href="javascript:cancel()"><fmt:message key="label.back"/></a></td>
						<td class="border"><b><font color="#FFCC00">|</font></b></td>
					</tr>
				</table>
		</c:if>
		<form name="modifyTableForm" action="<c:out value="${pageContext.request.contextPath}"/>/DisplayJsp.do" method="post" onsubmit="return false" target="IFrameData">
			<input type="hidden" name="pageRequested" value="successChangeTable">
			<input type="hidden" name='actionPasswordChangeOrders' value="allowModifyOrders">
			<input type="hidden" name='isBillPrinting' value="false">			
		</form>
		<div style='position: relative; height: 0;visibility:hidden'>
			<c:if test="${not empty userSession.room.currentTable.orders}">	
			<label id="idCurrentTableRegistrationDate"><fmt:formatDate value="${userSession.room.currentTable.registrationDate}" pattern="dd/MM/yyyy" type="DATE"/></label>
			</c:if>	
		</div>
		<jsp:include page='/jsp/commons/includeDivIFrame.jsp'/>			
	</body>
</html>