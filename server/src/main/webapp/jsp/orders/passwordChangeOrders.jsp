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
		<title><fmt:message key="montagnesdor.welcome"/> - <fmt:message key="passwordChangeOrders.jsp.title"/></title>
		<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}"/>/javascript/commons.js"></script>
		<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}"/>/javascript/tableOrders.js"></script>		
		<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}"/>/javascript/passwordChangeOrders.js"></script>
	</head>
	<body onload='initPage()'>
	<html:form method="post" action='/PasswordChangeOrders.do' onsubmit="return false">
		<table class="border" width="100%" align="center">
			<tr>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
				<td colspan=3 class="border" width="100%"><label class="policeGray"><fmt:message key="passwordChangeOrders.label"/></label></td>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
			</tr>	
			<tr>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
				<td class="border" width="50%"><fmt:message key="login.jsp.password"/></td>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
				<td class="border" width="50%"><input type="password" name="password" class="password" onkeyup='processUserEntry(event, this)' maxlength="16"></td>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
			</tr>
			<tr>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
			   	<td class="border" width="50%"><a accesskey='<fmt:message key="changeTable.jsp.accesskey.cancel"/>' href="javascript:cancel()" class="cancel"><fmt:message key="label.cancel"/></a></td>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
				<td class="border" width="50%"><a accesskey='<fmt:message key="changeTable.jsp.accesskey.confirm"/>' href="javascript:verifyPassword()" class="confirm" ><fmt:message key="label.confirm"/></a></td>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
			</tr>		
			<logic:messagesPresent>
			<tr>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
				<td colspan=3 class="border">
					<label class="policeGray">
					<html:errors property="error.authentication.failed"/>
					<html:errors property="error.password.required"/>
					<html:errors property="error.authentication.denied"/>
					</label>
				</td>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
			</tr>	
			</logic:messagesPresent>
		</table>
		<input type="hidden" name="actionPasswordChangeOrders" value="<c:out value="${param.actionPasswordChangeOrders}"/>">
	</html:form>
	<form name="modifyTableForm" action="<c:out value="${pageContext.request.contextPath}"/>/DisplayJsp.do" method="post" onsubmit="return false" target="IFrameData">
		<input type="hidden" name="pageRequested" value="successPasswordChangeOrders">
		<input type="hidden" name="actionPasswordChangeOrders" value="<c:out value="${param.actionPasswordChangeOrders}"/>">
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