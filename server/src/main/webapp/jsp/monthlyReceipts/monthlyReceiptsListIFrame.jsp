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
		<title><fmt:message key="montagnesdor.welcome"/> - <fmt:message key="monthlyReceiptsIFrame.jsp.title"/></title>
		<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}"/>/javascript/commons.js"></script>
		<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}"/>/javascript/monthlyReceipts.js"></script>
	</head>
	<body onload='initPageIFrame()'>
	<script language="javascript">
		//Cette variable est utilisée dans le fichier daylyReceipts.js
		var contextPath = '<c:out value="${pageContext.request.contextPath}"/>';
	</script>
	<c:set var="numberDays" value="0"/>
	<c:set var="sumCashes" value="0"/>
	<c:set var="sumTickets" value="0"/>
	<c:set var="sumCheques" value="0"/>
	<c:set var="sumCards" value="0"/>
	<c:set var="sumOnlines" value="0"/>
	<c:set var="sumUnpaid" value="0"/>
	<c:set var="sumAmounts" value="0"/>
	<c:if test="${not empty dayRevenuesList}">
		<table class="border" width='100%'>
		<c:forEach var="dayRevenue" items="${dayRevenuesList}" varStatus="status">
			<c:set var="sumCashes" value="${sumCashes+dayRevenue.cash}"/>
			<c:set var="sumTickets" value="${sumTickets+dayRevenue.ticket}"/>
			<c:set var="sumCheques" value="${sumCheques+dayRevenue.cheque}"/>
			<c:set var="sumCards" value="${sumCards+dayRevenue.card}"/>
			<c:set var="sumOnlines" value="${sumOnlines+dayRevenue.online}"/>
			<c:set var="sumUnpaid" value="${sumUnpaid+dayRevenue.unpaid}"/>
			<c:set var="sumAmounts" value="${sumAmounts+dayRevenue.amount}"/>
			<c:set var="numberDays" value="${numberDays+1}"/>
			<tr class="<c:if test="${dayRevenue.unpaid==0}">default</c:if><c:if test="${dayRevenue.unpaid!=0}">unpaid</c:if>" onmouseover="this.className='over';" onmouseout="<c:if test="${dayRevenue.unpaid==0}">this.className='default';</c:if><c:if test="${dayRevenue.unpaid!=0}">this.className='unpaid';</c:if>">
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
		    	<td class="border" style="width: 10%;"><fmt:formatDate value="${dayRevenue.revenueDate}" type="DATE" pattern="dd/MM/yyyy"/></td>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
				<td class="amount" style="width: 10%;"><fmt:formatNumber pattern="0.00" var="cash" value="${dayRevenue.cash}"/><c:out value="${fn:replace(cash, ',', '.')}" /></td>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
				<td class="amount" style="width: 10%;"><fmt:formatNumber pattern="0.00" var="ticket" value="${dayRevenue.ticket}"/><c:out value="${fn:replace(ticket, ',', '.')}" /></td>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
				<td class="amount" style="width: 10%;"><fmt:formatNumber pattern="0.00" var="cheque" value="${dayRevenue.cheque}"/><c:out value="${fn:replace(cheque, ',', '.')}" /></td>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
		    	<td class="amount" style="width: 10%;"><fmt:formatNumber pattern="0.00" var="card" value="${dayRevenue.card}"/><c:out value="${fn:replace(card, ',', '.')}" /></td>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
		    	<td class="amount" style="width: 10%;"><fmt:formatNumber pattern="0.00" var="online" value="${dayRevenue.online}"/><c:out value="${fn:replace(online, ',', '.')}" /></td>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
		    	<td class="amount" style="width: 10%;"><fmt:formatNumber pattern="0.00" var="unpaid" value="${dayRevenue.unpaid}"/><c:out value="${fn:replace(unpaid, ',', '.')}" /></td>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
		    	<td class="amount" style="width: 10%;"><fmt:formatNumber pattern="0.00" var="amount" value="${dayRevenue.amount}"/><c:out value="${fn:replace(amount, ',', '.')}" /></td>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
		    	<td class="border" style="width: 30%;">
		    		<table style="width: 100%;">
					<c:forEach var="vatRevenue" varStatus="status" items="${dayRevenue.vats}">
						<c:if test="${not empty vatRevenue}">
							<tr>
								<td class="left"><label class="policeGray"><fmt:message key="monthlyReceiptsIFrame.jsp.label.vat.amount"><fmt:param><fmt:formatNumber pattern="0.00" var="value" value="${vatRevenue.vat.value}"/><c:out value="${fn:replace(value, ',', '.')}"/></fmt:param></fmt:message></label></td>
								<td class="right"><fmt:formatNumber pattern="0.00" var="vatAmount" value="${vatRevenue.amount}"/><c:out value="${fn:replace(vatAmount, ',', '.')}" /></td>
								<td class="center">/</td>
								<td class="right"><fmt:formatNumber pattern="0.00" var="value" value="${vatRevenue.value}"/><c:out value="${fn:replace(value, ',', '.')}"/></td>
							</tr>
						</c:if>
					</c:forEach>
					</table>
				</td>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
			</tr>
		</c:forEach>	
		</table>
	</c:if>
	<c:set var="monthSelected">
		<fmt:formatDate pattern="MM" value="${revenueDate}" type="DATE"/>
	</c:set>
	<c:set var="yearSelected">
		<fmt:formatDate pattern="yyyy" value="${revenueDate}" type="DATE"/>
	</c:set>
	<form name="monthlyReceiptsForm" action="<c:out value="${pageContext.request.contextPath}"/>/MonthlyReceiptsListIFrame.do" method="post" onsubmit="return false" target="IFrameData">
		<input type="hidden" name='monthSelected' value="<c:out value="${monthSelected}"/>">
		<input type="hidden" name='yearSelected' value="<c:out value="${yearSelected}"/>">		
		<input type="hidden" name='optionSelected' value='<c:out value="${param.optionSelected}"/>'>
		<input type="hidden" name='sortListBy' value='<c:if test="${not empty sortListBy}"><c:out value="${sortListBy}"/></c:if>'>
		<input type="hidden" name='sortMonotony' value='<c:if test="${not empty sortMonotony}"><c:out value="${sortMonotony}"/></c:if>'>
	</form>
	<form name="downloadMonthlyReceiptsForm" action="<c:out value="${pageContext.request.contextPath}"/>/DownloadMonthlyReceiptsList.do" method="post" onsubmit="return false" target="_parent">
		<input type="hidden" name='monthSelected' value="<c:out value="${monthSelected}"/>">
		<input type="hidden" name='yearSelected' value="<c:out value="${yearSelected}"/>">		
	</form>
	<jsp:include page='/jsp/commons/includeDivIFrame.jsp'/>
		<div style='position: relative; height: 0;visibility:hidden'>
			<table width="100%" height="100%" cellspacing="0" cellpadding="0" border="0" >
				<tr>
					<td id="idTdDataOptions" width="15%" valign="top" title='Data Options'>
						<table class="border" width="95%">
							<c:if test="${empty param.optionSelected || param.optionSelected=='ALL'}" >
							<tr> 
						    	<td class="selectedWhite">
						    		<a class="selectedWhite" href="javascript:window.IFrameData.processMenusOptions('ALL');" title="<fmt:message key="monthlyReceiptsIFrame.jsp.title.display.all"><fmt:param><fmt:formatDate pattern="MMMM yyyy" value="${revenueDate}" type="DATE"/></fmt:param></fmt:message>"><fmt:message key="monthlyReceiptsIFrame.jsp.label.display.all"/></a>
						    	</td>
							</tr>
							<tr> 
								<td class="border">
								  <a href="javascript:window.IFrameData.processMenusOptions('TAKEAWAY');" title="<fmt:message key="monthlyReceiptsIFrame.jsp.title.display.takeaway"><fmt:param><fmt:formatDate pattern="MMMM yyyy" value="${revenueDate}" type="DATE"/></fmt:param></fmt:message>"><fmt:message key="monthlyReceiptsIFrame.jsp.label.display.takeaway"/></a>
							    </td>
							</tr>
							<tr> 
								<td class="border">
								  <a href="javascript:window.IFrameData.processMenusOptions('INPLACE');" title="<fmt:message key="monthlyReceiptsIFrame.jsp.title.display.inplace"><fmt:param><fmt:formatDate pattern="MMMM yyyy" value="${revenueDate}" type="DATE"/></fmt:param></fmt:message>"><fmt:message key="monthlyReceiptsIFrame.jsp.label.display.inplace"/></a>
							    </td>
							</tr>
							<tr> 
								<td class="border">
								  <a href="javascript:window.IFrameData.processMenusOptions('DOWNLOAD');" title="<fmt:message key="monthlyReceiptsIFrame.jsp.title.download.receipts"><fmt:param><fmt:formatDate pattern="MMMM yyyy" value="${revenueDate}" type="DATE"/></fmt:param></fmt:message>"><fmt:message key="monthlyReceiptsIFrame.jsp.label.download.receipts"/></a>
							    </td>
							</tr>
							</c:if>
							<c:if test="${param.optionSelected=='TAKEAWAY'}" >
							<tr> 
						    	<td class="border">
						    		<a href="javascript:window.IFrameData.processMenusOptions('ALL');" title="<fmt:message key="monthlyReceiptsIFrame.jsp.title.display.all"><fmt:param><fmt:formatDate pattern="MMMM yyyy" value="${revenueDate}" type="DATE"/></fmt:param></fmt:message>"><fmt:message key="monthlyReceiptsIFrame.jsp.label.display.all"/></a>
						    	</td>
							</tr>
							<tr> 
								<td class="selectedWhite">
								  <a class="selectedWhite" href="javascript:window.IFrameData.processMenusOptions('TAKEAWAY');" title="<fmt:message key="monthlyReceiptsIFrame.jsp.title.display.takeaway"><fmt:param><fmt:formatDate pattern="MMMM yyyy" value="${revenueDate}" type="DATE"/></fmt:param></fmt:message>"><fmt:message key="monthlyReceiptsIFrame.jsp.label.display.takeaway"/></a>
							    </td>
							</tr>
							<tr> 
								<td class="border">
								  <a href="javascript:window.IFrameData.processMenusOptions('INPLACE');" title="<fmt:message key="monthlyReceiptsIFrame.jsp.title.display.inplace"><fmt:param><fmt:formatDate pattern="MMMM yyyy" value="${revenueDate}" type="DATE"/></fmt:param></fmt:message>"><fmt:message key="monthlyReceiptsIFrame.jsp.label.display.inplace"/></a>
							    </td>
							</tr>
							<tr> 
								<td class="border">
								  <a href="javascript:window.IFrameData.processMenusOptions('DOWNLOAD');" title="<fmt:message key="monthlyReceiptsIFrame.jsp.title.download.receipts"><fmt:param><fmt:formatDate pattern="MMMM yyyy" value="${revenueDate}" type="DATE"/></fmt:param></fmt:message>"><fmt:message key="monthlyReceiptsIFrame.jsp.label.download.receipts"/></a>
							    </td>
							</tr>
							</c:if>
							<c:if test="${param.optionSelected=='INPLACE'}" >
							<tr> 
						    	<td class="border">
						    		<a href="javascript:window.IFrameData.processMenusOptions('ALL');" title="<fmt:message key="monthlyReceiptsIFrame.jsp.title.display.all"><fmt:param><fmt:formatDate pattern="MMMM yyyy" value="${revenueDate}" type="DATE"/></fmt:param></fmt:message>"><fmt:message key="monthlyReceiptsIFrame.jsp.label.display.all"/></a>
						    	</td>
							</tr>
							<tr> 
								<td class="border">
								  <a href="javascript:window.IFrameData.processMenusOptions('TAKEAWAY');" title="<fmt:message key="monthlyReceiptsIFrame.jsp.title.display.takeaway"><fmt:param><fmt:formatDate pattern="MMMM yyyy" value="${revenueDate}" type="DATE"/></fmt:param></fmt:message>"><fmt:message key="monthlyReceiptsIFrame.jsp.label.display.takeaway"/></a>
							    </td>
							</tr>
							<tr> 
								<td class="selectedWhite">
								  <a class="selectedWhite" href="javascript:window.IFrameData.processMenusOptions('INPLACE');" title="<fmt:message key="monthlyReceiptsIFrame.jsp.title.display.inplace"><fmt:param><fmt:formatDate pattern="MMMM yyyy" value="${revenueDate}" type="DATE"/></fmt:param></fmt:message>"><fmt:message key="monthlyReceiptsIFrame.jsp.label.display.inplace"/></a>
							    </td>
							</tr>
							<tr> 
								<td class="border">
								  <a href="javascript:window.IFrameData.processMenusOptions('DOWNLOAD');" title="<fmt:message key="monthlyReceiptsIFrame.jsp.title.download.receipts"><fmt:param><fmt:formatDate pattern="MMMM yyyy" value="${revenueDate}" type="DATE"/></fmt:param></fmt:message>"><fmt:message key="monthlyReceiptsIFrame.jsp.label.download.receipts"/></a>
							    </td>
							</tr>
							</c:if>
							<c:if test="${not empty requestScope.vatRevenues}">
								<c:forEach var="vatRevenue" varStatus="status" items="${requestScope.vatRevenues}">
							<tr>
								<td class="border">
									<label class="policeGray"><fmt:message key="monthlyReceiptsIFrame.jsp.label.vat.amount"><fmt:param><fmt:formatNumber pattern="0.00" var="value" value="${vatRevenue.vat.value}"/><c:out value="${fn:replace(value, ',', '.')}"/></fmt:param></fmt:message></label><br/>
									<fmt:formatNumber pattern="0.00" var="vatAmount" value="${vatRevenue.amount}"/><c:out value="${fn:replace(vatAmount, ',', '.')}" /> / <fmt:formatNumber pattern="0.00" var="value" value="${vatRevenue.value}"/><c:out value="${fn:replace(value, ',', '.')}"/>
								</td>
							</tr>
								</c:forEach>
							</c:if>
						</table>
					</td>
				</tr>					
				<tr>
					<td  id="idTdDataHeader" height="5%" title='Data Header'>
						<table class="border" width='100%' id="idTableDataHeader">
							<tr>
							    <td class="border"><b><font color="#FFCC00">|</font></b></td>
							    <td class="border" style="width: 10%;"><a <c:if test="${sortListBy=='revenueDate'}">class='linkSelected'</c:if> href="javascript:window.IFrameData.processList('revenueDate');" title="<fmt:message key="monthlyReceiptsIFrame.jsp.title.day"/>"><fmt:message key="monthlyReceiptsIFrame.jsp.label.day"/></a></td>
							    <td class="border"><b><font color="#FFCC00">|</font></b></td>
							    <td class="border" style="width: 10%;"><a <c:if test="${sortListBy=='cash'}">class='linkSelected'</c:if> href="javascript:window.IFrameData.processList('cash');" title="<fmt:message key="monthlyReceiptsIFrame.jsp.title.cash"/>"><fmt:message key="monthlyReceiptsIFrame.jsp.label.cash"/></a></td>
							    <td class="border"><b><font color="#FFCC00">|</font></b></td>
							    <td class="border" style="width: 10%;"><a <c:if test="${sortListBy=='ticket'}">class='linkSelected'</c:if> href="javascript:window.IFrameData.processList('ticket');" title="<fmt:message key="monthlyReceiptsIFrame.jsp.title.ticket"/>"><fmt:message key="monthlyReceiptsIFrame.jsp.label.ticket"/></a></td>
							    <td class="border"><b><font color="#FFCC00">|</font></b></td>
							    <td class="border" style="width: 10%;"><a <c:if test="${sortListBy=='cheque'}">class='linkSelected'</c:if> href="javascript:window.IFrameData.processList('cheque');" title="<fmt:message key="monthlyReceiptsIFrame.jsp.title.cheque"/>"><fmt:message key="monthlyReceiptsIFrame.jsp.label.cheque"/></a></td>
							    <td class="border"><b><font color="#FFCC00">|</font></b></td>
							    <td class="border" style="width: 10%;"><a <c:if test="${sortListBy=='card'}">class='linkSelected'</c:if> href="javascript:window.IFrameData.processList('card');" title="<fmt:message key="monthlyReceiptsIFrame.jsp.title.card"/>"><fmt:message key="monthlyReceiptsIFrame.jsp.label.card"/></a></td>
							    <td class="border"><b><font color="#FFCC00">|</font></b></td>
							    <td class="border" style="width: 10%;"><a <c:if test="${sortListBy=='online'}">class='linkSelected'</c:if> href="javascript:window.IFrameData.processList('online');" title="<fmt:message key="monthlyReceiptsIFrame.jsp.title.online"/>"><fmt:message key="monthlyReceiptsIFrame.jsp.label.online"/></a></td>
							    <td class="border"><b><font color="#FFCC00">|</font></b></td>
							    <td class="border" style="width: 10%;"><a <c:if test="${sortListBy=='unpaid'}">class='linkSelected'</c:if> href="javascript:window.IFrameData.processList('unpaid');" title="<fmt:message key="monthlyReceiptsIFrame.jsp.title.unpaid"/>"><fmt:message key="monthlyReceiptsIFrame.jsp.label.unpaid"/></a></td>
							    <td class="border"><b><font color="#FFCC00">|</font></b></td>
							    <td class="border" style="width: 10%;"><a <c:if test="${sortListBy=='amount'}">class='linkSelected'</c:if> href="javascript:window.IFrameData.processList('amount');" title="<fmt:message key="monthlyReceiptsIFrame.jsp.title.amount"/>"><fmt:message key="monthlyReceiptsIFrame.jsp.label.amount"/></a></td>
							    <td class="border"><b><font color="#FFCC00">|</font></b></td>
							    <td class="border" style="width: 30%;">
								    <c:if test="${not empty dateForYearList}">
										<c:set var="monthSelected">
											<fmt:formatDate pattern="M" value="${revenueDate}" type="DATE"/>
										</c:set>
										<SELECT name="monthSelected" style="width:54%" onchange="javascript:window.IFrameData.processSelect(this)">
										<c:forEach var="dateForMonth" varStatus="status" items="${dateForMonthList}">
											<OPTION <c:if test="${monthSelected==status.index+1}">selected</c:if> value="<c:out value="${status.index+1}"/>"><fmt:formatDate pattern="MMMM" value="${dateForMonth}" type="DATE"/></OPTION>
										</c:forEach>
										</SELECT>
										<SELECT name="yearSelected" style="width:44%" onchange="javascript:window.IFrameData.processSelect(this)">
										<c:forEach var="dateForYear" varStatus="status" items="${dateForYearList}">
										    <c:set var="year">
												<fmt:formatDate pattern="yyyy" value="${dateForYear}" type="DATE"/>
											</c:set>
											<OPTION <c:if test="${yearSelected==year}">selected</c:if> value="<c:out value="${year}"/>"><c:out value="${year}"/></OPTION>
										</c:forEach>
										</SELECT>
									</c:if>
								    <c:if test="${empty dateForYearList}">
										&nbsp;
									</c:if>
								</td>
							    <td class="border"><b><font color="#FFCC00">|</font></b></td>
							</tr>
						</table>
					</td>
				</tr>	
				<tr>
					<td id="idTdDataFooter" height="5%" title='Data Footer'>
						<table>
						  <tr>
						    <td width="6%"><label class="policeGray"><fmt:message key="monthlyReceiptsIFrame.jsp.label.number.days"/></label></td>
						    <td class="footer" style="width: 8%"><c:out value="${numberDays}"/></td>
						    <td width="1%"></td>
						    <td width="5%"><label class="policeGray"><fmt:message key="monthlyReceiptsIFrame.jsp.label.sum.cash"/></label></td>
						    <td class="footer" style="width: 9%"><fmt:formatNumber pattern="0.00" var="sumCashes" value="${sumCashes}"/><c:out value="${fn:replace(sumCashes, ',', '.')}" /></td>
						    <td width="1%"></td>
						    <td width="5%"><label class="policeGray"><fmt:message key="monthlyReceiptsIFrame.jsp.label.sum.ticket"/></label></td>
						    <td class="footer" style="width: 9%"><fmt:formatNumber pattern="0.00" var="sumTickets" value="${sumTickets}"/><c:out value="${fn:replace(sumTickets, ',', '.')}" /></td>
						    <td width="1%"></td>
						    <td width="5%"><label class="policeGray"><fmt:message key="monthlyReceiptsIFrame.jsp.label.sum.cheque"/></label></td>
						    <td class="footer" style="width: 9%"><fmt:formatNumber pattern="0.00" var="sumCheques" value="${sumCheques}"/><c:out value="${fn:replace(sumCheques, ',', '.')}" /></td>
						    <td width="1%"></td>
						    <td width="5%"><label class="policeGray"><fmt:message key="monthlyReceiptsIFrame.jsp.label.sum.card"/></label></td>
						    <td class="footer" style="width: 9%"><fmt:formatNumber pattern="0.00" var="sumCards" value="${sumCards}"/><c:out value="${fn:replace(sumCards, ',', '.')}" /></td>
						    <td width="1%"></td>
						    <td width="5%"><label class="policeGray"><fmt:message key="monthlyReceiptsIFrame.jsp.label.sum.online"/></label></td>
						    <td class="footer" style="width: 9%"><fmt:formatNumber pattern="0.00" var="sumOnlines" value="${sumOnlines}"/><c:out value="${fn:replace(sumOnlines, ',', '.')}" /></td>
						    <td width="1%"></td>
						    <td width="5%"><label class="policeGray"><fmt:message key="monthlyReceiptsIFrame.jsp.label.sum.unpaid"/></label></td>
						    <td class="footer" style="width: 8%"><fmt:formatNumber pattern="0.00" var="sumUnpaid" value="${sumUnpaid}"/><c:out value="${fn:replace(sumUnpaid, ',', '.')}" /></td>
						    <td width="1%"></td>
						    <td width="5%"><label class="policeGray"><fmt:message key="monthlyReceiptsIFrame.jsp.label.sum.amounts"/></label></td>
						    <td class="footer" style="width: 10%"><fmt:formatNumber pattern="0.00" var="sumAmounts" value="${sumAmounts}"/><c:out value="${fn:replace(sumAmounts, ',', '.')}" /></td>
						  </tr>
						</table>
					</td>
				</tr>	
			</table>			
		</div>
	</body>
</html>