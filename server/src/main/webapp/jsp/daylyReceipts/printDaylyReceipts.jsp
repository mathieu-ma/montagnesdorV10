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
		<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}"/>/javascript/printReceipts.js"></script>
	</head>
	<body>
	<!--
		Changement de la langue pour l'impression.
		L'impression doit se faire suivant le language que l'utilisateur aura choisi pour imprimer
	-->
	<jsp:useBean id="userSession" type="fr.montagnesdor.restaurant.struts.UserSession" scope="session"/>
	<c:set var="currentLanguageTemp" value="${userSession.currentLanguage}"/>
	<fmt:setLocale value="${userSession.user.preferedPrintLanguage.id}"/>
	<jsp:setProperty name="userSession" property="currentLanguage" value="${userSession.user.preferedPrintLanguage.id}" /> 
	
		<form name="printForm" onsubmit="return false">	
	   		<input type="hidden" name="restaurantName" value="<fmt:message key="restaurant.name"/>">
	   		<input type="hidden" name="restaurantAddressRoad" value="<fmt:message key="restaurant.address.road"/>">
	   		<input type="hidden" name="restaurantAddressZipcode" value="<fmt:message key="restaurant.address.zipcode"/>">
	   		<input type="hidden" name="restaurantAddressCity" value="<fmt:message key="restaurant.address.city"/>">
	   		<input type="hidden" name="restaurantPhone" value="<fmt:message key="restaurant.phone"/>">
	   		<input type="hidden" name="restaurantOthersInfo1" value="<fmt:message key="restaurant.others.info1"/>">
	   		<input type="hidden" name="restaurantOthersInfo2" value="<fmt:message key="restaurant.others.info2"/>">
	   		
	   		<input type="hidden" name="daylyReceiptsPrintLabelTakeway" value="<fmt:message key="daylyReceipts.print.label.takeway"><fmt:param><fmt:formatDate value="${cashingDate}" type="DATE" dateStyle="SHORT"/></fmt:param></fmt:message>">
	   		<input type="hidden" name="daylyReceiptsPrintLabelInplace" value="<fmt:message key="daylyReceipts.print.label.inplace"><fmt:param><fmt:formatDate value="${cashingDate}" type="DATE" dateStyle="SHORT"/></fmt:param></fmt:message>">
	   		<input type="hidden" name="daylyReceiptsPrintLabelTable" value="<fmt:message key="daylyReceipts.print.label.table"/>">
	   		<input type="hidden" name="daylyReceiptsPrintLabelCash" value="<fmt:message key="daylyReceipts.print.label.cash"/>">
	   		<input type="hidden" name="daylyReceiptsPrintLabelTicket" value="<fmt:message key="daylyReceipts.print.label.ticket"/>">
	   		<input type="hidden" name="daylyReceiptsPrintLabelCheque" value="<fmt:message key="daylyReceipts.print.label.cheque"/>">
	   		<input type="hidden" name="daylyReceiptsPrintLabelCard" value="<fmt:message key="daylyReceipts.print.label.card"/>">
	   		<input type="hidden" name="daylyReceiptsPrintLabelSumCash" value="<fmt:message key="daylyReceipts.print.label.sum.cash"/>">
	   		<input type="hidden" name="daylyReceiptsPrintLabelSumTicket" value="<fmt:message key="daylyReceipts.print.label.sum.ticket"/>">
	   		<input type="hidden" name="daylyReceiptsPrintLabelSumCheque" value="<fmt:message key="daylyReceipts.print.label.sum.cheque"/>">
	   		<input type="hidden" name="daylyReceiptsPrintLabelSumCard" value="<fmt:message key="daylyReceipts.print.label.sum.card"/>">
	   		<input type="hidden" name="daylyReceiptsPrintLabelSumUnpaid" value="<fmt:message key="daylyReceipts.print.label.sum.unpaid"/>">
	   		<input type="hidden" name="daylyReceiptsPrintLabelSumCashingByTable" value="<fmt:message key="daylyReceipts.print.label.sum.cashing.by.table"/>">
	   		<input type="hidden" name="daylyReceiptsPrintLabelDifference" value="<fmt:message key="daylyReceipts.print.label.difference"/>">
	   		<input type="hidden" name="daylyReceiptsPrintLabelSum" value="<fmt:message key="daylyReceipts.print.label.sum"/>">
			<c:forEach var="vatRevenue" varStatus="status" items="${requestScope.vatRevenuesAll}">
				<c:if test="${status.last}" >
					<c:set var="numberVats" value="${status.index+1}"/>
				</c:if>	
		   		<input type="hidden" name="daylyReceiptsPrintLabelSumCashingByVatALL<c:out value="${status.index}"/>" value="<fmt:message key="daylyReceipts.print.label.sum.cashing.by.vat"><fmt:param><fmt:formatNumber pattern="0.00" var="value" value="${vatRevenue.vat.value}"/><c:out value="${fn:replace(value, ',', '.')}"/></fmt:param></fmt:message>">
				<input type="hidden" name="daylyReceiptsPrintLabelVatALL<c:out value="${status.index}"/>" value="<fmt:message key="daylyReceipts.print.label.vat"><fmt:param><fmt:formatNumber pattern="0.00" var="value" value="${vatRevenue.vat.value}"/><c:out value="${fn:replace(value, ',', '.')}"/></fmt:param></fmt:message>">
		   		<input type="hidden" name="daylyReceiptsPrintVatAmountALL<c:out value="${status.index}"/>" value="<fmt:formatNumber pattern="0.00" var="vatAmount" value="${vatRevenue.amount}"/><c:out value="${fn:replace(vatAmount, ',', '.')}" />">
				<input type="hidden" name="daylyReceiptsPrintVatValueALL<c:out value="${status.index}"/>" value="<fmt:formatNumber pattern="0.00" var="value" value="${vatRevenue.value}"/><c:out value="${fn:replace(value, ',', '.')}"/>">
			</c:forEach>
	   		<input type="hidden" name="daylyReceiptsPrintNumberVats" value="<c:out value="${numberVats}"/>">
			<c:forEach var="vatRevenue" varStatus="status" items="${requestScope.vatRevenuesTakeaway}">
		   		<input type="hidden" name="daylyReceiptsPrintLabelSumCashingByVatTAKEAWAY<c:out value="${status.index}"/>" value="<fmt:message key="daylyReceipts.print.label.sum.cashing.by.vat"><fmt:param><fmt:formatNumber pattern="0.00" var="value" value="${vatRevenue.vat.value}"/><c:out value="${fn:replace(value, ',', '.')}"/></fmt:param></fmt:message>">
				<input type="hidden" name="daylyReceiptsPrintLabelVatTAKEAWAY<c:out value="${status.index}"/>" value="<fmt:message key="daylyReceipts.print.label.vat"><fmt:param><fmt:formatNumber pattern="0.00" var="value" value="${vatRevenue.vat.value}"/><c:out value="${fn:replace(value, ',', '.')}"/></fmt:param></fmt:message>">
		   		<input type="hidden" name="daylyReceiptsPrintVatAmountTAKEAWAY<c:out value="${status.index}"/>" value="<fmt:formatNumber pattern="0.00" var="vatAmount" value="${vatRevenue.amount}"/><c:out value="${fn:replace(vatAmount, ',', '.')}" />">
				<input type="hidden" name="daylyReceiptsPrintVatValueTAKEAWAY<c:out value="${status.index}"/>" value="<fmt:formatNumber pattern="0.00" var="value" value="${vatRevenue.value}"/><c:out value="${fn:replace(value, ',', '.')}"/>">
			</c:forEach>
			<c:forEach var="vatRevenue" varStatus="status" items="${requestScope.vatRevenuesInplace}">
		   		<input type="hidden" name="daylyReceiptsPrintLabelSumCashingByVatINPLACE<c:out value="${status.index}"/>" value="<fmt:message key="daylyReceipts.print.label.sum.cashing.by.vat"><fmt:param><fmt:formatNumber pattern="0.00" var="value" value="${vatRevenue.vat.value}"/><c:out value="${fn:replace(value, ',', '.')}"/></fmt:param></fmt:message>">
				<input type="hidden" name="daylyReceiptsPrintLabelVatINPLACE<c:out value="${status.index}"/>" value="<fmt:message key="daylyReceipts.print.label.vat"><fmt:param><fmt:formatNumber pattern="0.00" var="value" value="${vatRevenue.vat.value}"/><c:out value="${fn:replace(value, ',', '.')}"/></fmt:param></fmt:message>">
		   		<input type="hidden" name="daylyReceiptsPrintVatAmountINPLACE<c:out value="${status.index}"/>" value="<fmt:formatNumber pattern="0.00" var="vatAmount" value="${vatRevenue.amount}"/><c:out value="${fn:replace(vatAmount, ',', '.')}" />">
				<input type="hidden" name="daylyReceiptsPrintVatValueINPLACE<c:out value="${status.index}"/>" value="<fmt:formatNumber pattern="0.00" var="value" value="${vatRevenue.value}"/><c:out value="${fn:replace(value, ',', '.')}"/>">
			</c:forEach>
	   		<input type="hidden" name="daylyReceiptsPrintLabelWithoutVat" value="<fmt:message key="daylyReceipts.print.label.without.vat"/>">
	   		<input type="hidden" name="daylyReceiptsPrintLabelTotalSum" value="<fmt:message key="daylyReceipts.print.label.total.sum"/>">
	   		<input type="hidden" name="daylyReceiptsPrintLabelSumTable" value="<fmt:message key="daylyReceipts.print.label.sum.table"/>">
			<c:set var="totalSumAmounts" value="0"/>
			<c:set var="numberTablesTAKEAWAY" value="0"/>
			<c:set var="sumCashesTAKEAWAY" value="0"/>
			<c:set var="sumTicketsTAKEAWAY" value="0"/>
			<c:set var="sumChequesTAKEAWAY" value="0"/>
			<c:set var="sumCardsTAKEAWAY" value="0"/>
			<c:set var="sumUnpaidTAKEAWAY" value="0"/>
			<c:set var="sumAmountsTAKEAWAY" value="0"/>
			<c:set var="numberTablesINPLACE" value="0"/>
			<c:set var="sumCashesINPLACE" value="0"/>
			<c:set var="sumTicketsINPLACE" value="0"/>
			<c:set var="sumChequesINPLACE" value="0"/>
			<c:set var="sumCardsINPLACE" value="0"/>
			<c:set var="sumUnpaidINPLACE" value="0"/>
			<c:set var="sumAmountsINPLACE" value="0"/>
			<c:forEach var="cashing" items="${cashingList}" varStatus="status">
				<c:if test="${cashing.dinnerTable.takeaway}" >
					<c:set var="numberTablesTAKEAWAY" value="${numberTablesTAKEAWAY+1}"/>
					<c:set var="sumCashesTAKEAWAY" value="${sumCashesTAKEAWAY+cashing.cash}"/>
					<c:set var="sumTicketsTAKEAWAY" value="${sumTicketsTAKEAWAY+cashing.ticket}"/>
					<c:set var="sumChequesTAKEAWAY" value="${sumChequesTAKEAWAY+cashing.cheque}"/>
					<c:set var="sumCardsTAKEAWAY" value="${sumCardsTAKEAWAY+cashing.card}"/>
					<c:set var="sumUnpaidTAKEAWAY" value="${sumUnpaidTAKEAWAY+cashing.unpaid}"/>
					<c:set var="sumAmountsTAKEAWAY" value="${sumAmountsTAKEAWAY+cashing.dinnerTable.amountPay}"/>
					<input type="hidden" name='cashingDinnerTableNumberTAKEAWAY<c:out value="${numberTablesTAKEAWAY}"/>' value="<c:out value="${cashing.dinnerTable.number}"/>">		
					<input type="hidden" name='cashingCashTAKEAWAY<c:out value="${numberTablesTAKEAWAY}"/>' value="<fmt:formatNumber pattern="0.00" var="cash" value="${cashing.cash}"/><c:out value="${fn:replace(cash, ',', '.')}" />">		
					<input type="hidden" name='cashingTicketTAKEAWAY<c:out value="${numberTablesTAKEAWAY}"/>' value="<fmt:formatNumber pattern="0.00" var="ticket" value="${cashing.ticket}"/><c:out value="${fn:replace(ticket, ',', '.')}" />">		
					<input type="hidden" name='cashingChequeTAKEAWAY<c:out value="${numberTablesTAKEAWAY}"/>' value="<fmt:formatNumber pattern="0.00" var="cheque" value="${cashing.cheque}"/><c:out value="${fn:replace(cheque, ',', '.')}" />">		
					<input type="hidden" name='cashingCardTAKEAWAY<c:out value="${numberTablesTAKEAWAY}"/>' value="<fmt:formatNumber pattern="0.00" var="card" value="${cashing.card}"/><c:out value="${fn:replace(card, ',', '.')}" />">		
				</c:if>
				<c:if test="${!cashing.dinnerTable.takeaway}" >
					<c:set var="numberTablesINPLACE" value="${numberTablesINPLACE+1}"/>
					<c:set var="sumCashesINPLACE" value="${sumCashesINPLACE+cashing.cash}"/>
					<c:set var="sumTicketsINPLACE" value="${sumTicketsINPLACE+cashing.ticket}"/>
					<c:set var="sumChequesINPLACE" value="${sumChequesINPLACE+cashing.cheque}"/>
					<c:set var="sumCardsINPLACE" value="${sumCardsINPLACE+cashing.card}"/>
					<c:set var="sumUnpaidINPLACE" value="${sumUnpaidINPLACE+cashing.unpaid}"/>
					<c:set var="sumAmountsINPLACE" value="${sumAmountsINPLACE+cashing.dinnerTable.amountPay}"/>
					<input type="hidden" name='cashingDinnerTableNumberINPLACE<c:out value="${numberTablesINPLACE}"/>' value="<c:out value="${cashing.dinnerTable.number}"/>">		
					<input type="hidden" name='cashingCashINPLACE<c:out value="${numberTablesINPLACE}"/>' value="<fmt:formatNumber pattern="0.00" var="cash" value="${cashing.cash}"/><c:out value="${fn:replace(cash, ',', '.')}" />">		
					<input type="hidden" name='cashingTicketINPLACE<c:out value="${numberTablesINPLACE}"/>' value="<fmt:formatNumber pattern="0.00" var="ticket" value="${cashing.ticket}"/><c:out value="${fn:replace(ticket, ',', '.')}" />">		
					<input type="hidden" name='cashingChequeINPLACE<c:out value="${numberTablesINPLACE}"/>' value="<fmt:formatNumber pattern="0.00" var="cheque" value="${cashing.cheque}"/><c:out value="${fn:replace(cheque, ',', '.')}" />">		
					<input type="hidden" name='cashingCardINPLACE<c:out value="${numberTablesINPLACE}"/>' value="<fmt:formatNumber pattern="0.00" var="card" value="${cashing.card}"/><c:out value="${fn:replace(card, ',', '.')}" />">		
				</c:if>
				<c:set var="totalSumAmounts" value="${totalSumAmounts+cashing.dinnerTable.amountPay}"/>
			</c:forEach>
			<input type="hidden" name='numberTablesTAKEAWAY' value="<c:out value="${numberTablesTAKEAWAY}"/>">		
			<input type="hidden" name='sumCashesTAKEAWAY' value="<fmt:formatNumber pattern="0.00" var="sumCashes" value="${sumCashesTAKEAWAY}"/><c:out value="${fn:replace(sumCashes, ',', '.')}" />">		
			<input type="hidden" name='sumTicketsTAKEAWAY' value="<fmt:formatNumber pattern="0.00" var="sumTickets" value="${sumTicketsTAKEAWAY}"/><c:out value="${fn:replace(sumTickets, ',', '.')}" />">		
			<input type="hidden" name='sumChequesTAKEAWAY' value="<fmt:formatNumber pattern="0.00" var="sumCheques" value="${sumChequesTAKEAWAY}"/><c:out value="${fn:replace(sumCheques, ',', '.')}" />">		
			<input type="hidden" name='sumCardsTAKEAWAY' value="<fmt:formatNumber pattern="0.00" var="sumCards" value="${sumCardsTAKEAWAY}"/><c:out value="${fn:replace(sumCards, ',', '.')}" />">		
			<input type="hidden" name='sumUnpaidTAKEAWAY' value="<fmt:formatNumber pattern="0.00" var="sumUnpaid" value="${sumUnpaidTAKEAWAY}"/><c:out value="${fn:replace(sumUnpaid, ',', '.')}" />">
			<input type="hidden" name='sumAmountsTAKEAWAY' value="<fmt:formatNumber pattern="0.00" var="sumAmounts" value="${sumAmountsTAKEAWAY}"/><c:out value="${fn:replace(sumAmounts, ',', '.')}" />">
			<input type="hidden" name='numberTablesINPLACE' value="<c:out value="${numberTablesINPLACE}"/>">		
			<input type="hidden" name='sumCashesINPLACE' value="<fmt:formatNumber pattern="0.00" var="sumCashes" value="${sumCashesINPLACE}"/><c:out value="${fn:replace(sumCashes, ',', '.')}"/>">		
			<input type="hidden" name='sumTicketsINPLACE' value="<fmt:formatNumber pattern="0.00" var="sumTickets" value="${sumTicketsINPLACE}"/><c:out value="${fn:replace(sumTickets, ',', '.')}"/>">		
			<input type="hidden" name='sumChequesINPLACE' value="<fmt:formatNumber pattern="0.00" var="sumCheques" value="${sumChequesINPLACE}"/><c:out value="${fn:replace(sumCheques, ',', '.')}"/>">		
			<input type="hidden" name='sumCardsINPLACE' value="<fmt:formatNumber pattern="0.00" var="sumCards" value="${sumCardsINPLACE}"/><c:out value="${fn:replace(sumCards, ',', '.')}"/>">		
			<input type="hidden" name='sumUnpaidINPLACE' value="<fmt:formatNumber pattern="0.00" var="sumUnpaid" value="${sumUnpaidINPLACE}"/><c:out value="${fn:replace(sumUnpaid, ',', '.')}"/>">
			<input type="hidden" name='sumAmountsINPLACE' value="<fmt:formatNumber pattern="0.00" var="sumAmounts" value="${sumAmountsINPLACE}"/><c:out value="${fn:replace(sumAmounts, ',', '.')}"/>">
			
			<input type="hidden" name='totalSumAmounts' value="<fmt:formatNumber pattern="0.00" var="totalSumAmounts" value="${totalSumAmounts}"/><c:out value="${fn:replace(totalSumAmounts, ',', '.')}"/>">
		</form>
		<form name="modifyTableForm" action="<c:out value="${pageContext.request.contextPath}"/>/DaylyReceiptsListIFrame.do" method="post" onsubmit="return false" target="IFrameData">
			<input type="hidden" name="pageRequested" value="successPrintTableByCashing">
			<input type="hidden" name='tableId'>
			<input type="hidden" name='cashingId'>
			<input type="hidden" name='optionSelected' value='<c:out value="${param.optionSelected}"/>'>
			<input type="hidden" name='filterList' value='<c:if test="${not empty cashingDate}"><fmt:formatDate value="${cashingDate}" type="DATE" pattern="dd/MM/yyyy"/></c:if>'>
			<input type="hidden" name='sortListBy' value='<c:if test="${not empty sortListBy}"><c:out value="${sortListBy}"/></c:if>'>
			<input type="hidden" name='sortMonotony' value='<c:if test="${not empty sortMonotony}"><c:out value="${sortMonotony}"/></c:if>'>
		</form>
		<script language="javascript">
			<c:if test="${(not empty param.optionSelected) and (param.optionSelected=='TAKEAWAY')}">
				processPrinting('TAKEAWAY');
			</c:if>						
			<c:if test="${(not empty param.optionSelected) and (param.optionSelected=='INPLACE')}">
				processPrinting('INPLACE');
			</c:if>						
			<c:if test="${(empty param.optionSelected) || (param.optionSelected=='ALL')}">
				processPrinting('ALL');
			</c:if>
		</script>
		<!--
			Mise à jour de la langue après l'impression.
			Après l'impression, on doit remettre le language courant choisi par l'utilisateur
		-->			
		<fmt:setLocale value="${currentLanguageTemp}"/>
		<jsp:setProperty name="userSession" property="currentLanguage" value="${currentLanguageTemp}"/> 
	</body>
</html>