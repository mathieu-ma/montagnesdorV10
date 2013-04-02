<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/fn.tld" prefix="fn" %>

<jsp:include page='/jsp/commons/checkSessionAuthorization.jsp'/>

<html>
	<head> 
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" >
		<link rel='stylesheet' type='text/css' href='<c:out value="${pageContext.request.contextPath}"/>/css/montagnesdor.css'/>
		<title><fmt:message key="montagnesdor.welcome"/> - <fmt:message key="cashTable.jsp.title"/></title>
		<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}"/>/javascript/commons.js"></script>
		<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}"/>/javascript/daylyReceipts.js"></script>
		<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}"/>/javascript/dailyReceiptsListUpload.js"></script>	
	</head>
	<body onload='initPage()'>
		<script language="javascript">
			//Ces variables sont utilisées dans le fichier cashTable.js
			var contextPath = '<c:out value="${pageContext.request.contextPath}"/>';
		</script>
			<form name="dailyReceiptsListUploadForm" method="post" action='<c:out value="${pageContext.request.contextPath}"/>/DaylyReceiptsListUpload.do' onsubmit="return false" enctype="multipart/form-data">
				<table class="border" width="100%" align="center">
					<tr>
						<td class="border"><b><font color="#FFCC00">|</font></b></td>
						<td class="border" colspan="3" width="100%"><fmt:message key="cashTable.jsp.label.cash.table"><fmt:param><c:out value="${requestScope.attributesOut.dinnerTable.number}"/></fmt:param></fmt:message></td>
						<td class="border"><b><font color="#FFCC00">|</font></b></td>
					</tr>
					<tr>
						<td class="border"><b><font color="#FFCC00">|</font></b></td>
						<td class="border" width="50%"><a href="javascript:autoCompletion('cash')"><fmt:message key="cashTable.jsp.label.cash"/></a></td>
						<td class="border"><b><font color="#FFCC00">|</font></b></td>
						<td class="border" width="50%">
							<input type="file" name="fileToBeUploaded"/>
						</td>			
						<td class="border"><b><font color="#FFCC00">|</font></b></td>
					</tr>	
					<tr>
						<td class="border"><b><font color="#FFCC00">|</font></b></td>
					   	<td class="border" width="50%"><a accesskey='<fmt:message key="cashTable.jsp.accesskey.cancel"/>' href="javascript:processMenusOptions('ALL');" class="cancel"><fmt:message key="label.cancel"/></a></td>
						<td class="border"><b><font color="#FFCC00">|</font></b></td>
						<td class="border" width="50%"><a accesskey='<fmt:message key="cashTable.jsp.accesskey.confirm"/>' href="javascript:document.dailyReceiptsListUploadForm.submit();" class="confirm" ><fmt:message key="label.confirm"/></a></td>
						<td class="border"><b><font color="#FFCC00">|</font></b></td>
					</tr>
					<logic:messagesPresent>
					<tr>
						<td class="border"><b><font color="#FFCC00">|</font></b></td>
						<td colspan=3 class="border">
							<label class="policeGray">
							<html:errors property="error.table.id"/>
							<html:errors property="cashTable.jsp.alert.amount.paid"/>
							<html:errors property="cashTable.jsp.alert.amount.returned"/>
							</label>
						</td>
						<td class="border"><b><font color="#FFCC00">|</font></b></td>
					</tr>	
					</logic:messagesPresent>
				</table>
				<input type="hidden" name='tableId' value="<c:out value="${requestScope.attributesOut.dinnerTable.id}"/>">
				<input type="hidden" name='cashingId' value="<c:out value="${param.cashingId}"/>">		
				<input type="hidden" name='unpaid' value="<fmt:formatNumber pattern="0.00" var="unpaid" value="${requestScope.attributesOut.unpaid}"/><c:out value="${fn:replace(unpaid, ',', '.')}" />">
				<input type="hidden" name="pageRequested" value="successDaylyReceiptsListIFrame">
				<input type="hidden" name='newDate'>
				<input type="hidden" name='patternDate' value="<fmt:message key="cashTable.jsp.patternDate.value"/>">
				<input type="hidden" name='optionSelected' value='<c:out value="${param.optionSelected}"/>'>
				<input type="hidden" name='filterList' value='<c:out value="${param.filterList}"/>'>
				<input type="hidden" name='sortListBy' value='<c:out value="${param.sortListBy}"/>'>
				<input type="hidden" name='sortMonotony' value='<c:out value="${param.sortMonotony}"/>'>
			</form>
		<form name="modifyTableForm" action="<c:out value="${pageContext.request.contextPath}"/>/DaylyReceiptsListIFrame.do" method="post" onsubmit="return false" target="IFrameData">
			<input type="hidden" name="forward" value="success">
			<input type="hidden" name="pageRequested" value="successPrintTableByCashing">
			<input type="hidden" name='tableId'>
			<input type="hidden" name='cashingId' value="<c:out value="${requestScope.attributesOut.id}"/>">		
			<input type="hidden" name='optionSelected' value='<c:out value="${param.optionSelected}"/>'>
			<input type="hidden" name='filterList' value='<c:out value="${param.filterList}"/>'>
			<input type="hidden" name='sortListBy' value='<c:out value="${param.sortListBy}"/>'>
			<input type="hidden" name='sortMonotony' value='<c:out value="${param.sortMonotony}"/>'>
		</form>
		<jsp:include page='/jsp/commons/includeDivIFrame.jsp'/>			
	</body>
</html>