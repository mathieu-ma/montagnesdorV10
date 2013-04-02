<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/fn.tld" prefix="fn" %>

<jsp:include page='/jsp/commons/checkSessionAuthorization.jsp'/>

<html>
	<head> 
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" >
		<link rel="stylesheet" type="text/css" href="<c:out value="${pageContext.request.contextPath}"/>/css/montagnesdor.css" />
		<title><fmt:message key="montagnesdor.welcome"/> - <fmt:message key="daylyReceiptsIFrame.jsp.title"/></title>
		<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}"/>/javascript/commons.js"></script>
		<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}"/>/javascript/daylyReceipts.js"></script>
	</head>
	<body onload='initPageIFrame()'>
	<script language="javascript">
		//Cette variable est utilisée dans le fichier daylyReceipts.js
		var contextPath = '<c:out value="${pageContext.request.contextPath}"/>';
	</script>
	<c:set var="numberTables" value="0"/>
	<c:set var="sumCashes" value="0"/>
	<c:set var="sumTickets" value="0"/>
	<c:set var="sumCheques" value="0"/>
	<c:set var="sumCards" value="0"/>
	<c:set var="sumUnpaid" value="0"/>
	<c:set var="sumAmounts" value="0"/>
	<c:if test="${not empty cashingList}">
		<table class="border" width='100%'>
		<c:forEach var="cashing" items="${cashingList}" varStatus="status">
			<c:if test="${empty param.optionSelected || param.optionSelected=='ALL'}" >
				<c:if test="${status.last}">
					<c:set var="numberTables" value="${status.index+1}"/>
				</c:if>	
				<c:set var="sumCashes" value="${sumCashes+cashing.cash}"/>
				<c:set var="sumTickets" value="${sumTickets+cashing.ticket}"/>
				<c:set var="sumCheques" value="${sumCheques+cashing.cheque}"/>
				<c:set var="sumCards" value="${sumCards+cashing.card}"/>
				<c:set var="sumUnpaid" value="${sumUnpaid+cashing.unpaid}"/>
				<c:set var="sumAmounts" value="${sumAmounts+cashing.dinnerTable.amountPay}"/>
			<tr class="<c:if test="${cashing.unpaid==0}">default</c:if><c:if test="${cashing.unpaid!=0}">unpaid</c:if>" onmouseover="this.className='over';" onmouseout="<c:if test="${cashing.unpaid==0}">this.className='default';</c:if><c:if test="${cashing.unpaid!=0}">this.className='unpaid';</c:if>">			
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
	    		<td class="border" style="width: 10%;"> 
	    			<a href="javascript:processUserClick('modify', '<c:out value="${cashing.id}"/>')" title="<fmt:message key="daylyReceiptsIFrame.jsp.title.modify"><fmt:param><c:out value="${cashing.dinnerTable.number}"/></fmt:param></fmt:message>"><c:out value="${cashing.dinnerTable.number}"/></a>
				</td>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
				<td class="amount" style="width: 10%;"><fmt:formatNumber pattern="0.00" var="cash" value="${cashing.cash}"/><c:out value="${fn:replace(cash, ',', '.')}" /></td>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
				<td class="amount" style="width: 10%;"><fmt:formatNumber pattern="0.00" var="ticket" value="${cashing.ticket}"/><c:out value="${fn:replace(ticket, ',', '.')}" /></td>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
				<td class="amount" style="width: 10%;"><fmt:formatNumber pattern="0.00" var="cheque" value="${cashing.cheque}"/><c:out value="${fn:replace(cheque, ',', '.')}" /></td>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
		    	<td class="amount" style="width: 10%;"><fmt:formatNumber pattern="0.00" var="card" value="${cashing.card}"/><c:out value="${fn:replace(card, ',', '.')}" /></td>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
		    	<td class="amount" style="width: 10%;"><fmt:formatNumber pattern="0.00" var="unpaid" value="${cashing.unpaid}"/><c:out value="${fn:replace(unpaid, ',', '.')}" /></td>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
		    	<td class="amount" style="width: 10%;"><fmt:formatNumber pattern="0.00" var="amountPay" value="${cashing.dinnerTable.amountPay}"/><c:out value="${fn:replace(amountPay, ',', '.')}" /></td>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
		    	<td class="border" style="width: 30%;">
					<a href="javascript:processUserClick('print', '<c:out value="${cashing.dinnerTable.id}"/>')" title="<fmt:message key="daylyReceiptsIFrame.jsp.title.print"><fmt:param><c:out value="${cashing.dinnerTable.number}"/></fmt:param></fmt:message>"><fmt:message key="daylyReceiptsIFrame.jsp.label.print"/></a>
		    	</td>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
			</tr>
			</c:if>	
		</c:forEach>	
		<c:forEach var="cashing" items="${cashingList}" varStatus="status">
			<c:if test="${not empty param.optionSelected && param.optionSelected=='TAKEAWAY' && cashing.dinnerTable.takeaway}" >
				<c:set var="numberTables" value="${numberTables+1}"/>
				<c:set var="sumCashes" value="${sumCashes+cashing.cash}"/>
				<c:set var="sumTickets" value="${sumTickets+cashing.ticket}"/>
				<c:set var="sumCheques" value="${sumCheques+cashing.cheque}"/>
				<c:set var="sumCards" value="${sumCards+cashing.card}"/>
				<c:set var="sumUnpaid" value="${sumUnpaid+cashing.unpaid}"/>
				<c:set var="sumAmounts" value="${sumAmounts+cashing.dinnerTable.amountPay}"/>
			<tr class="<c:if test="${cashing.unpaid==0}">default</c:if><c:if test="${cashing.unpaid!=0}">unpaid</c:if>" onmouseover="this.className='over';" onmouseout="<c:if test="${cashing.unpaid==0}">this.className='default';</c:if><c:if test="${cashing.unpaid!=0}">this.className='unpaid';</c:if>">			
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
	    		<td class="border" style="width: 10%;"> 
	    			<a href="javascript:processUserClick('modify', '<c:out value="${cashing.id}"/>')" title="<fmt:message key="daylyReceiptsIFrame.jsp.title.modify"><fmt:param><c:out value="${cashing.dinnerTable.number}"/></fmt:param></fmt:message>"><c:out value="${cashing.dinnerTable.number}"/></a>
				</td>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
				<td class="amount" style="width: 10%;"><fmt:formatNumber pattern="0.00" var="cash" value="${cashing.cash}"/><c:out value="${fn:replace(cash, ',', '.')}" /></td>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
				<td class="amount" style="width: 10%;"><fmt:formatNumber pattern="0.00" var="ticket" value="${cashing.ticket}"/><c:out value="${fn:replace(ticket, ',', '.')}" /></td>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
				<td class="amount" style="width: 10%;"><fmt:formatNumber pattern="0.00" var="cheque" value="${cashing.cheque}"/><c:out value="${fn:replace(cheque, ',', '.')}" /></td>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
		    	<td class="amount" style="width: 10%;"><fmt:formatNumber pattern="0.00" var="card" value="${cashing.card}"/><c:out value="${fn:replace(card, ',', '.')}" /></td>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
		    	<td class="amount" style="width: 10%;"><fmt:formatNumber pattern="0.00" var="unpaid" value="${cashing.unpaid}"/><c:out value="${fn:replace(unpaid, ',', '.')}" /></td>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
		    	<td class="amount" style="width: 10%;"><fmt:formatNumber pattern="0.00" var="amountPay" value="${cashing.dinnerTable.amountPay}"/><c:out value="${fn:replace(amountPay, ',', '.')}" /></td>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
		    	<td class="border" style="width: 30%;">
					<a href="javascript:processUserClick('print', '<c:out value="${cashing.dinnerTable.id}"/>')" title="<fmt:message key="daylyReceiptsIFrame.jsp.title.print"><fmt:param><c:out value="${cashing.dinnerTable.number}"/></fmt:param></fmt:message>"><fmt:message key="daylyReceiptsIFrame.jsp.label.print"/></a>
		    	</td>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
			</tr>
			</c:if>	
		</c:forEach>	
		<c:forEach var="cashing" items="${cashingList}" varStatus="status">
			<c:if test="${not empty param.optionSelected && param.optionSelected=='INPLACE' && !cashing.dinnerTable.takeaway}" >
				<c:set var="numberTables" value="${numberTables+1}"/>
				<c:set var="sumCashes" value="${sumCashes+cashing.cash}"/>
				<c:set var="sumTickets" value="${sumTickets+cashing.ticket}"/>
				<c:set var="sumCheques" value="${sumCheques+cashing.cheque}"/>
				<c:set var="sumCards" value="${sumCards+cashing.card}"/>
				<c:set var="sumUnpaid" value="${sumUnpaid+cashing.unpaid}"/>
				<c:set var="sumAmounts" value="${sumAmounts+cashing.dinnerTable.amountPay}"/>
			<tr class="<c:if test="${cashing.unpaid==0}">default</c:if><c:if test="${cashing.unpaid!=0}">unpaid</c:if>" onmouseover="this.className='over';" onmouseout="<c:if test="${cashing.unpaid==0}">this.className='default';</c:if><c:if test="${cashing.unpaid!=0}">this.className='unpaid';</c:if>">			
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
	    		<td class="border" style="width: 10%;"> 
	    			<a href="javascript:processUserClick('modify', '<c:out value="${cashing.id}"/>')" title="<fmt:message key="daylyReceiptsIFrame.jsp.title.modify"><fmt:param><c:out value="${cashing.dinnerTable.number}"/></fmt:param></fmt:message>"><c:out value="${cashing.dinnerTable.number}"/></a>
				</td>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
				<td class="amount" style="width: 10%;"><fmt:formatNumber pattern="0.00" var="cash" value="${cashing.cash}"/><c:out value="${fn:replace(cash, ',', '.')}" /></td>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
				<td class="amount" style="width: 10%;"><fmt:formatNumber pattern="0.00" var="ticket" value="${cashing.ticket}"/><c:out value="${fn:replace(ticket, ',', '.')}" /></td>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
				<td class="amount" style="width: 10%;"><fmt:formatNumber pattern="0.00" var="cheque" value="${cashing.cheque}"/><c:out value="${fn:replace(cheque, ',', '.')}" /></td>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
		    	<td class="amount" style="width: 10%;"><fmt:formatNumber pattern="0.00" var="card" value="${cashing.card}"/><c:out value="${fn:replace(card, ',', '.')}" /></td>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
		    	<td class="amount" style="width: 10%;"><fmt:formatNumber pattern="0.00" var="unpaid" value="${cashing.unpaid}"/><c:out value="${fn:replace(unpaid, ',', '.')}" /></td>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
		    	<td class="amount" style="width: 10%;"><fmt:formatNumber pattern="0.00" var="amountPay" value="${cashing.dinnerTable.amountPay}"/><c:out value="${fn:replace(amountPay, ',', '.')}" /></td>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
		    	<td class="border" style="width: 30%;">
					<a href="javascript:processUserClick('print', '<c:out value="${cashing.dinnerTable.id}"/>')" title="<fmt:message key="daylyReceiptsIFrame.jsp.title.print"><fmt:param><c:out value="${cashing.dinnerTable.number}"/></fmt:param></fmt:message>"><fmt:message key="daylyReceiptsIFrame.jsp.label.print"/></a>
		    	</td>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
			</tr>
			</c:if>	
		</c:forEach>	
		</table>
	</c:if>
	<form name="modifyTableForm" action="<c:out value="${pageContext.request.contextPath}"/>/DaylyReceiptsListIFrame.do" method="post" onsubmit="return false" target="IFrameData">
		<input type="hidden" name="forward" value="success">
		<input type="hidden" name="pageRequested" value="successPrintTableByCashing">
		<input type="hidden" name='tableId'>
		<input type="hidden" name='cashingId'>		
		<input type="hidden" name='optionSelected' value='<c:out value="${param.optionSelected}"/>'>
		<input type="hidden" name='filterList' value='<c:if test="${not empty cashingDate}"><fmt:formatDate value="${cashingDate}" type="DATE" pattern="dd/MM/yyyy"/></c:if>'>
		<input type="hidden" name='sortListBy' value='<c:if test="${not empty sortListBy}"><c:out value="${sortListBy}"/></c:if>'>
		<input type="hidden" name='sortMonotony' value='<c:if test="${not empty sortMonotony}"><c:out value="${sortMonotony}"/></c:if>'>
	</form>
	<jsp:include page='/jsp/commons/includeDivIFrame.jsp'/>
		<div style='position: relative; height: 0;visibility:hidden'>
			<table width="100%" height="100%" cellspacing="0" cellpadding="0" border="0">
				<tr>
					<td id="idTdDataOptions" width="15%" valign="top" title='Data Options'>
						<c:if test="${not empty cashingList}">
						<table class="border" width="95%">
							<c:if test="${empty param.optionSelected || param.optionSelected=='ALL'}" >
							<tr> 
						    	<td class="selectedWhite">
						    		<a class="selectedWhite" href="javascript:window.IFrameData.processMenusOptions('ALL');" title="<fmt:message key="daylyReceiptsIFrame.jsp.title.display.all"><fmt:param><fmt:formatDate value="${cashingDate}" type="DATE" dateStyle="FULL"/></fmt:param></fmt:message>"><fmt:message key="daylyReceiptsIFrame.jsp.label.display.all"/></a>
						    	</td>
							</tr>
							<tr> 
								<td class="border">
								  <a href="javascript:window.IFrameData.processMenusOptions('TAKEAWAY');" title="<fmt:message key="daylyReceiptsIFrame.jsp.title.display.takeaway"><fmt:param><fmt:formatDate value="${cashingDate}" type="DATE" dateStyle="FULL"/></fmt:param></fmt:message>"><fmt:message key="daylyReceiptsIFrame.jsp.label.display.takeaway"/></a>
							    </td>
							</tr>
							<tr> 
								<td class="border">
								  <a href="javascript:window.IFrameData.processMenusOptions('INPLACE');" title="<fmt:message key="daylyReceiptsIFrame.jsp.title.display.inplace"><fmt:param><fmt:formatDate value="${cashingDate}" type="DATE" dateStyle="FULL"/></fmt:param></fmt:message>"><fmt:message key="daylyReceiptsIFrame.jsp.label.display.inplace"/></a>
							    </td>
							</tr>
							<tr> 
								<td class="border">
								  <a href="javascript:window.IFrameData.processMenusOptions('PRINT');" title="<fmt:message key="daylyReceiptsIFrame.jsp.title.print.receipts.all"><fmt:param><fmt:formatDate value="${cashingDate}" type="DATE" dateStyle="FULL"/></fmt:param></fmt:message>"><fmt:message key="daylyReceiptsIFrame.jsp.label.print.receipts"/></a>
							    </td>
							</tr>
							<c:if test="${empty isCashingClosed || !isCashingClosed}" >
							<c:if test="${empty isCashingPrinted || !isCashingPrinted}">
							<tr> 
						    	<td class="border">
								  <label class="policeGray"><fmt:message key="daylyReceiptsIFrame.jsp.label.close"/></label>
							    </td>
							</tr>
							</c:if>
							<c:if test="${not empty isCashingPrinted && isCashingPrinted}">
							<tr> 
						    	<td class="border">
								  <a href="javascript:window.IFrameData.processMenusOptions('CLOSE');" title="<fmt:message key="daylyReceiptsIFrame.jsp.title.close"><fmt:param><fmt:formatDate value="${cashingDate}" type="DATE" dateStyle="FULL"/></fmt:param></fmt:message>"><fmt:message key="daylyReceiptsIFrame.jsp.label.close"/></a>
							    </td>
							</tr>
							</c:if>
							</c:if>							
							<c:if test="${not empty isCashingClosed && isCashingClosed}" >
							<tr> 
						    	<td class="border">
								  <a href="javascript:window.IFrameData.processMenusOptions('CLOSE');" title="<fmt:message key="daylyReceiptsIFrame.jsp.title.merge"><fmt:param><fmt:formatDate value="${cashingDate}" type="DATE" dateStyle="FULL"/></fmt:param></fmt:message>"><fmt:message key="daylyReceiptsIFrame.jsp.label.merge"/></a>
							    </td>
							</tr>
							</c:if>
							<tr> 
								<td class="border">
								  <a href="javascript:window.IFrameData.processMenusOptions('DOWNLOAD');" title="<fmt:message key="daylyReceiptsIFrame.jsp.title.download.receipts"><fmt:param><fmt:formatDate value="${cashingDate}" type="DATE" dateStyle="FULL"/></fmt:param></fmt:message>"><fmt:message key="daylyReceiptsIFrame.jsp.label.download.receipts"/></a>
							    </td>
							</tr>
							<tr> 
								<td class="border">
								  <a href="javascript:window.IFrameData.processMenusOptions('UPLOAD');" title="<fmt:message key="daylyReceiptsIFrame.jsp.title.upload.receipts"><fmt:param><fmt:formatDate value="${cashingDate}" type="DATE" dateStyle="FULL"/></fmt:param></fmt:message>"><fmt:message key="daylyReceiptsIFrame.jsp.label.upload.receipts"/></a>
							    </td>
							</tr>
							</c:if>
							<c:if test="${not empty param.optionSelected && param.optionSelected=='TAKEAWAY'}" >
							<tr> 
								<td class="border">
						    		<a href="javascript:window.IFrameData.processMenusOptions('ALL');" title="<fmt:message key="daylyReceiptsIFrame.jsp.title.display.all"><fmt:param><fmt:formatDate value="${cashingDate}" type="DATE" dateStyle="FULL"/></fmt:param></fmt:message>"><fmt:message key="daylyReceiptsIFrame.jsp.label.display.all"/></a>
						    	</td>
							</tr>
							<tr> 
						    	<td class="selectedWhite">
								  <a class="selectedWhite" href="javascript:window.IFrameData.processMenusOptions('TAKEAWAY');" title="<fmt:message key="daylyReceiptsIFrame.jsp.title.display.takeaway"><fmt:param><fmt:formatDate value="${cashingDate}" type="DATE" dateStyle="FULL"/></fmt:param></fmt:message>"><fmt:message key="daylyReceiptsIFrame.jsp.label.display.takeaway"/></a>
							    </td>
							</tr>
							<tr> 
								<td class="border">
								  <a href="javascript:window.IFrameData.processMenusOptions('INPLACE');" title="<fmt:message key="daylyReceiptsIFrame.jsp.title.display.inplace"><fmt:param><fmt:formatDate value="${cashingDate}" type="DATE" dateStyle="FULL"/></fmt:param></fmt:message>"><fmt:message key="daylyReceiptsIFrame.jsp.label.display.inplace"/></a>
							    </td>
							</tr>
							<tr> 
								<td class="border">
								  <a href="javascript:window.IFrameData.processMenusOptions('PRINT');" title="<fmt:message key="daylyReceiptsIFrame.jsp.title.print.receipts.takeaway"><fmt:param><fmt:formatDate value="${cashingDate}" type="DATE" dateStyle="FULL"/></fmt:param></fmt:message>"><fmt:message key="daylyReceiptsIFrame.jsp.label.print.receipts"/></a>
							    </td>
							</tr>
							<c:if test="${empty isCashingClosed || !isCashingClosed}">
							<c:if test="${empty isCashingPrinted || !isCashingPrinted}">
							<tr> 
						    	<td class="border">
								  <label class="policeGray"><fmt:message key="daylyReceiptsIFrame.jsp.label.close"/></label>
							    </td>
							</tr>
							</c:if>
							<c:if test="${not empty isCashingPrinted && isCashingPrinted}">
							<tr> 
						    	<td class="border">
								  <a href="javascript:window.IFrameData.processMenusOptions('CLOSE');" title="<fmt:message key="daylyReceiptsIFrame.jsp.title.close"><fmt:param><fmt:formatDate value="${cashingDate}" type="DATE" dateStyle="FULL"/></fmt:param></fmt:message>"><fmt:message key="daylyReceiptsIFrame.jsp.label.close"/></a>
							    </td>
							</tr>
							</c:if>
							</c:if>							
							<c:if test="${not empty isCashingClosed && isCashingClosed}">
							<tr> 
						    	<td class="border">
								  <a href="javascript:window.IFrameData.processMenusOptions('CLOSE');" title="<fmt:message key="daylyReceiptsIFrame.jsp.title.merge"><fmt:param><fmt:formatDate value="${cashingDate}" type="DATE" dateStyle="FULL"/></fmt:param></fmt:message>"><fmt:message key="daylyReceiptsIFrame.jsp.label.merge"/></a>
							    </td>
							</tr>
							</c:if>							
							</c:if>
							<c:if test="${not empty param.optionSelected && param.optionSelected=='INPLACE'}" >
							<tr> 
								<td class="border">
						    		<a href="javascript:window.IFrameData.processMenusOptions('ALL');" title="<fmt:message key="daylyReceiptsIFrame.jsp.title.display.all"><fmt:param><fmt:formatDate value="${cashingDate}" type="DATE" dateStyle="FULL"/></fmt:param></fmt:message>"><fmt:message key="daylyReceiptsIFrame.jsp.label.display.all"/></a>
						    	</td>
							</tr>
							<tr> 
								<td class="border">
								  <a href="javascript:window.IFrameData.processMenusOptions('TAKEAWAY');" title="<fmt:message key="daylyReceiptsIFrame.jsp.title.display.takeaway"><fmt:param><fmt:formatDate value="${cashingDate}" type="DATE" dateStyle="FULL"/></fmt:param></fmt:message>"><fmt:message key="daylyReceiptsIFrame.jsp.label.display.takeaway"/></a>
							    </td>
							</tr>
							<tr> 
						    	<td class="selectedWhite">
								  <a class="selectedWhite" href="javascript:window.IFrameData.processMenusOptions('INPLACE');" title="<fmt:message key="daylyReceiptsIFrame.jsp.title.display.inplace"><fmt:param><fmt:formatDate value="${cashingDate}" type="DATE" dateStyle="FULL"/></fmt:param></fmt:message>"><fmt:message key="daylyReceiptsIFrame.jsp.label.display.inplace"/></a>
							    </td>
							</tr>
							<tr> 
								<td class="border">
								  <a href="javascript:window.IFrameData.processMenusOptions('PRINT');" title="<fmt:message key="daylyReceiptsIFrame.jsp.title.print.receipts.inplace"><fmt:param><fmt:formatDate value="${cashingDate}" type="DATE" dateStyle="FULL"/></fmt:param></fmt:message>"><fmt:message key="daylyReceiptsIFrame.jsp.label.print.receipts"/></a>
							    </td>
							</tr>
							<c:if test="${empty isCashingClosed || !isCashingClosed}" >
							<c:if test="${empty isCashingPrinted || !isCashingPrinted}">
							<tr> 
						    	<td class="border">
								  <label class="policeGray"><fmt:message key="daylyReceiptsIFrame.jsp.label.close"/></label>
							    </td>
							</tr>
							</c:if>
							<c:if test="${not empty isCashingPrinted && isCashingPrinted}">
							<tr> 
						    	<td class="border">
								  <a href="javascript:window.IFrameData.processMenusOptions('CLOSE');" title="<fmt:message key="daylyReceiptsIFrame.jsp.title.close"><fmt:param><fmt:formatDate value="${cashingDate}" type="DATE" dateStyle="FULL"/></fmt:param></fmt:message>"><fmt:message key="daylyReceiptsIFrame.jsp.label.close"/></a>
							    </td>
							</tr>
							</c:if>
							</c:if>							
							<c:if test="${not empty isCashingClosed && isCashingClosed}" >
							<tr> 
						    	<td class="border">
								  <a href="javascript:window.IFrameData.processMenusOptions('CLOSE');" title="<fmt:message key="daylyReceiptsIFrame.jsp.title.merge"><fmt:param><fmt:formatDate value="${cashingDate}" type="DATE" dateStyle="FULL"/></fmt:param></fmt:message>"><fmt:message key="daylyReceiptsIFrame.jsp.label.merge"/></a>
							    </td>
							</tr>
							</c:if>							
							</c:if>
							<c:if test="${not empty param.optionSelected && param.optionSelected=='PRINT'}" >
							<tr> 
								<td class="border">
						    		<a href="javascript:window.IFrameData.processMenusOptions('ALL');" title="<fmt:message key="daylyReceiptsIFrame.jsp.title.display.all"><fmt:param><fmt:formatDate value="${cashingDate}" type="DATE" dateStyle="FULL"/></fmt:param></fmt:message>"><fmt:message key="daylyReceiptsIFrame.jsp.label.display.all"/></a>
						    	</td>
							</tr>
							<tr> 
								<td class="border">
								  <a href="javascript:window.IFrameData.processMenusOptions('TAKEAWAY');" title="<fmt:message key="daylyReceiptsIFrame.jsp.title.display.takeaway"><fmt:param><fmt:formatDate value="${cashingDate}" type="DATE" dateStyle="FULL"/></fmt:param></fmt:message>"><fmt:message key="daylyReceiptsIFrame.jsp.label.display.takeaway"/></a>
							    </td>
							</tr>
							<tr> 
								<td class="border">
								  <a href="javascript:window.IFrameData.processMenusOptions('INPLACE');" title="<fmt:message key="daylyReceiptsIFrame.jsp.title.display.inplace"><fmt:param><fmt:formatDate value="${cashingDate}" type="DATE" dateStyle="FULL"/></fmt:param></fmt:message>"><fmt:message key="daylyReceiptsIFrame.jsp.label.display.inplace"/></a>
							    </td>
							</tr>
							<tr> 
						    	<td class="selectedWhite">
								  <a class="selectedWhite" href="javascript:window.IFrameData.processMenusOptions('PRINT');" title="<c:if test="${empty param.optionSelected || param.optionSelected=='ALL'}"><fmt:message key="daylyReceiptsIFrame.jsp.title.print.receipts.all"><fmt:param><fmt:formatDate value="${cashingDate}" type="DATE" dateStyle="FULL"/></fmt:param></fmt:message></c:if><c:if test="${not empty param.optionSelected && param.optionSelected=='INPLACE'}"><fmt:message key="daylyReceiptsIFrame.jsp.title.print.receipts.inplace"><fmt:param><fmt:formatDate value="${cashingDate}" type="DATE" dateStyle="FULL"/></fmt:param></fmt:message></c:if><c:if test="${not empty param.optionSelected && param.optionSelected=='TAKEAWAY'}"><fmt:message key="daylyReceiptsIFrame.jsp.title.print.receipts.takeaway"><fmt:param><fmt:formatDate value="${cashingDate}" type="DATE" dateStyle="FULL"/></fmt:param></fmt:message></c:if>"><fmt:message key="daylyReceiptsIFrame.jsp.label.print.receipts"/></a>
							    </td>
							</tr>
							<c:if test="${empty isCashingClosed || !isCashingClosed}" >
							<c:if test="${empty isCashingPrinted || !isCashingPrinted}">
							<tr> 
						    	<td class="border">
								  <label class="policeGray"><fmt:message key="daylyReceiptsIFrame.jsp.label.close"/></label>
							    </td>
							</tr>
							</c:if>
							<c:if test="${not empty isCashingPrinted && isCashingPrinted}">
							<tr> 
						    	<td class="border">
								  <a href="javascript:window.IFrameData.processMenusOptions('CLOSE');" title="<fmt:message key="daylyReceiptsIFrame.jsp.title.close"><fmt:param><fmt:formatDate value="${cashingDate}" type="DATE" dateStyle="FULL"/></fmt:param></fmt:message>"><fmt:message key="daylyReceiptsIFrame.jsp.label.close"/></a>
							    </td>
							</tr>
							</c:if>
							</c:if>							
							<c:if test="${not empty isCashingClosed && isCashingClosed}" >
							<tr> 
						    	<td class="border">
								  <a href="javascript:window.IFrameData.processMenusOptions('CLOSE');" title="<fmt:message key="daylyReceiptsIFrame.jsp.title.merge"><fmt:param><fmt:formatDate value="${cashingDate}" type="DATE" dateStyle="FULL"/></fmt:param></fmt:message>"><fmt:message key="daylyReceiptsIFrame.jsp.label.merge"/></a>
							    </td>
							</tr>
							</c:if>							
							</c:if>
							<c:if test="${not empty param.optionSelected && param.optionSelected=='CLOSE'}" >
							<tr> 
								<td class="border">
						    		<a href="javascript:window.IFrameData.processMenusOptions('ALL');" title="<fmt:message key="daylyReceiptsIFrame.jsp.title.display.all"><fmt:param><fmt:formatDate value="${cashingDate}" type="DATE" dateStyle="FULL"/></fmt:param></fmt:message>"><fmt:message key="daylyReceiptsIFrame.jsp.label.display.all"/></a>
						    	</td>
							</tr>
							<tr> 
								<td class="border">
								  <a href="javascript:window.IFrameData.processMenusOptions('TAKEAWAY');" title="<fmt:message key="daylyReceiptsIFrame.jsp.title.display.takeaway"><fmt:param><fmt:formatDate value="${cashingDate}" type="DATE" dateStyle="FULL"/></fmt:param></fmt:message>"><fmt:message key="daylyReceiptsIFrame.jsp.label.display.takeaway"/></a>
							    </td>
							</tr>
							<tr> 
								<td class="border">
								  <a href="javascript:window.IFrameData.processMenusOptions('INPLACE');" title="<fmt:message key="daylyReceiptsIFrame.jsp.title.display.inplace"><fmt:param><fmt:formatDate value="${cashingDate}" type="DATE" dateStyle="FULL"/></fmt:param></fmt:message>"><fmt:message key="daylyReceiptsIFrame.jsp.label.display.inplace"/></a>
							    </td>
							</tr>
							<tr> 
								<td class="border">
								  <a href="javascript:window.IFrameData.processMenusOptions('PRINT');" title="<c:if test="empty param.optionSelected || param.optionSelected=='ALL'"><fmt:message key="daylyReceiptsIFrame.jsp.title.print.receipts.all"><fmt:param><fmt:formatDate value="${cashingDate}" type="DATE" dateStyle="FULL"/></fmt:param></fmt:message></c:if><c:if test="param.optionSelected=='INPLACE'"><fmt:message key="daylyReceiptsIFrame.jsp.title.print.receipts.inplace"><fmt:param><fmt:formatDate value="${cashingDate}" type="DATE" dateStyle="FULL"/></fmt:param></fmt:message></c:if><c:if test="param.optionSelected=='TAKEAWAY'"><fmt:message key="daylyReceiptsIFrame.jsp.title.print.receipts.takeaway"><fmt:param><fmt:formatDate value="${cashingDate}" type="DATE" dateStyle="FULL"/></fmt:param></fmt:message></c:if>"><fmt:message key="daylyReceiptsIFrame.jsp.label.print.receipts"/></a>
							    </td>
							</tr>
							<c:if test="${empty isCashingClosed || !isCashingClosed}" >
							<tr> 
						    	<td class="selectedWhite">
								  <a class="selectedWhite" href="javascript:window.IFrameData.processMenusOptions('CLOSE');" title="<fmt:message key="daylyReceiptsIFrame.jsp.title.close"><fmt:param><fmt:formatDate value="${cashingDate}" type="DATE" dateStyle="FULL"/></fmt:param></fmt:message>"><fmt:message key="daylyReceiptsIFrame.jsp.label.close"/></a>
							    </td>
							</tr>
							</c:if>							
							<c:if test="${not empty isCashingClosed && isCashingClosed}" >
							<tr> 
						    	<td class="selectedWhite">
								  <a class="selectedWhite" href="javascript:window.IFrameData.processMenusOptions('CLOSE');" title="<fmt:message key="daylyReceiptsIFrame.jsp.title.merge"><fmt:param><fmt:formatDate value="${cashingDate}" type="DATE" dateStyle="FULL"/></fmt:param></fmt:message>"><fmt:message key="daylyReceiptsIFrame.jsp.label.merge"/></a>
							    </td>
							</tr>
							</c:if>							
							</c:if>
							<c:if test="${not empty requestScope.vatRevenues}">
								<c:forEach var="vatRevenue" varStatus="status" items="${requestScope.vatRevenues}">
							<tr>
								<td class="border">
									<label class="policeGray"><fmt:message key="cashTable.jsp.label.vat.amount"><fmt:param><fmt:formatNumber pattern="0.00" var="value" value="${vatRevenue.vat.value}"/><c:out value="${fn:replace(value, ',', '.')}"/></fmt:param></fmt:message></label><br/>
									<fmt:formatNumber pattern="0.00" var="vatAmount" value="${vatRevenue.amount}"/><c:out value="${fn:replace(vatAmount, ',', '.')}" /> / <fmt:formatNumber pattern="0.00" var="value" value="${vatRevenue.value}"/><c:out value="${fn:replace(value, ',', '.')}"/>
								</td>
							</tr>
								</c:forEach>
							</c:if>
						</table>
						</c:if>
					</td>
				</tr>					
				<tr>
					<td id="idTdDataHeader" height="5%" title='Data Header'>
						<table class="border" width='100%' id="idTableDataHeader">
							<tr>
							    <td class="border"><b><font color="#FFCC00">|</font></b></td>
							    <td class="border" style="width: 10%;"><a <c:if test="${sortListBy=='cashing.dinnerTable.number'}">class='linkSelected'</c:if> href="javascript:window.IFrameData.processList('cashing.dinnerTable.number');" title="<fmt:message key="daylyReceiptsIFrame.jsp.title.table"/>"><fmt:message key="daylyReceiptsIFrame.jsp.label.table"/></a></td>
							    <td class="border"><b><font color="#FFCC00">|</font></b></td>
							    <td class="border" style="width: 10%;"><a <c:if test="${sortListBy=='cashing.cash'}">class='linkSelected'</c:if> href="javascript:window.IFrameData.processList('cashing.cash');" title="<fmt:message key="daylyReceiptsIFrame.jsp.title.cash"/>"><fmt:message key="daylyReceiptsIFrame.jsp.label.cash"/></a></td>
							    <td class="border"><b><font color="#FFCC00">|</font></b></td>
							    <td class="border" style="width: 10%;"><a <c:if test="${sortListBy=='cashing.ticket'}">class='linkSelected'</c:if> href="javascript:window.IFrameData.processList('cashing.ticket');" title="<fmt:message key="daylyReceiptsIFrame.jsp.title.ticket"/>"><fmt:message key="daylyReceiptsIFrame.jsp.label.ticket"/></a></td>
							    <td class="border"><b><font color="#FFCC00">|</font></b></td>
							    <td class="border" style="width: 10%;"><a <c:if test="${sortListBy=='cashing.cheque'}">class='linkSelected'</c:if> href="javascript:window.IFrameData.processList('cashing.cheque');" title="<fmt:message key="daylyReceiptsIFrame.jsp.title.cheque"/>"><fmt:message key="daylyReceiptsIFrame.jsp.label.cheque"/></a></td>
							    <td class="border"><b><font color="#FFCC00">|</font></b></td>
							    <td class="border" style="width: 10%;"><a <c:if test="${sortListBy=='cashing.card'}">class='linkSelected'</c:if> href="javascript:window.IFrameData.processList('cashing.card');" title="<fmt:message key="daylyReceiptsIFrame.jsp.title.card"/>"><fmt:message key="daylyReceiptsIFrame.jsp.label.card"/></a></td>
							    <td class="border"><b><font color="#FFCC00">|</font></b></td>
							    <td class="border" style="width: 10%;"><a <c:if test="${sortListBy=='cashing.unpaid'}">class='linkSelected'</c:if> href="javascript:window.IFrameData.processList('cashing.unpaid');" title="<fmt:message key="daylyReceiptsIFrame.jsp.title.unpaid"/>"><fmt:message key="daylyReceiptsIFrame.jsp.label.unpaid"/></a></td>
							    <td class="border"><b><font color="#FFCC00">|</font></b></td>
							    <td class="border" style="width: 10%;"><a <c:if test="${sortListBy=='cashing.dinnerTable.amountPay'}">class='linkSelected'</c:if> href="javascript:window.IFrameData.processList('cashing.dinnerTable.amountPay');" title="<fmt:message key="daylyReceiptsIFrame.jsp.title.amount"/>"><fmt:message key="daylyReceiptsIFrame.jsp.label.amount"/></a></td>
							    <td class="border"><b><font color="#FFCC00">|</font></b></td>
							    <td class="border" style="width: 30%;">
							 		<SELECT id='idFilterList' name="filterList" onchange="javascript:window.IFrameData.processSelect(this)">
							 		<c:if test="${not empty cashingDatesList}">
										<c:forEach var="date" varStatus="status" items="${cashingDatesList}">
										<OPTION <c:if test="${not empty cashingDate && cashingDate==date}">selected</c:if> value="<fmt:formatDate value="${date}" type="DATE" pattern="dd/MM/yyyy"/>"><fmt:formatDate value="${date}" type="DATE" dateStyle="FULL"/></OPTION>
										</c:forEach>
									</c:if>
									<c:if test="${empty cashingDatesList}">
										<OPTION><fmt:message key="daylyReceiptsIFrame.jsp.select.date.none"/></OPTION>
									</c:if>
		 							</SELECT>
								</td>
							    <td class="border"><b><font color="#FFCC00">|</font></b></td>
							</tr>
						</table>
					</td>
				</tr>	
				<tr>
					<td id="idTdDataFooter" height="5%"  title='Data Footer'>
						<table>
						  <tr>
						    <td width="5%"><label class="policeGray"><fmt:message key="daylyReceiptsIFrame.jsp.label.number.tables"/></label></td>
						    <td class="footer"><c:out value="${numberTables}" /></td>
						    <td width="1%"></td>
						    <td width="5%"><label class="policeGray"><fmt:message key="daylyReceiptsIFrame.jsp.label.sum.cash"/></label></td>
						    <td class="footer" style="width: 9%"><fmt:formatNumber pattern="0.00" var="sumCashes" value="${sumCashes}"/><c:out value="${fn:replace(sumCashes, ',', '.')}" /></td>
						    <td width="1%"></td>
						    <td width="5%"><label class="policeGray"><fmt:message key="daylyReceiptsIFrame.jsp.label.sum.ticket"/></label></td>
						    <td class="footer" style="width: 9%"><fmt:formatNumber pattern="0.00" var="sumTickets" value="${sumTickets}"/><c:out value="${fn:replace(sumTickets, ',', '.')}" /></td>
						    <td width="1%"></td>
						    <td width="5%"><label class="policeGray"><fmt:message key="daylyReceiptsIFrame.jsp.label.sum.cheque"/></label></td>
						    <td class="footer" style="width: 9%"><fmt:formatNumber pattern="0.00" var="sumCheques" value="${sumCheques}"/><c:out value="${fn:replace(sumCheques, ',', '.')}" /></td>
						    <td width="1%"></td>
						    <td width="5%"><label class="policeGray"><fmt:message key="daylyReceiptsIFrame.jsp.label.sum.card"/></label></td>
						    <td class="footer" style="width: 9%"><fmt:formatNumber pattern="0.00" var="sumCards" value="${sumCards}"/><c:out value="${fn:replace(sumCards, ',', '.')}" /></td>
						    <td width="1%"></td>
						    <td width="5%"><label class="policeGray"><fmt:message key="daylyReceiptsIFrame.jsp.label.sum.unpaid"/></label></td>
						    <td class="footer" style="width: 8%"><fmt:formatNumber pattern="0.00" var="sumUnpaid" value="${sumUnpaid}"/><c:out value="${fn:replace(sumUnpaid, ',', '.')}" /></td>
						    <td width="1%"></td>
						    <td width="5%"><label class="policeGray"><fmt:message key="daylyReceiptsIFrame.jsp.label.sum.amounts"/></label></td>
						    <td class="footer" style="width: 10%"><fmt:formatNumber pattern="0.00" var="sumAmounts" value="${sumAmounts}"/><c:out value="${fn:replace(sumAmounts, ',', '.')}" /></td>
						  </tr>
						</table>
					</td>
				</tr>	
			</table>			
		</div>
	</body>
</html>