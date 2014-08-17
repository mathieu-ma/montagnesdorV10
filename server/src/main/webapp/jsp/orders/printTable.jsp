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
		<title><fmt:message key="montagnesdor.welcome"/> - <fmt:message key="printTable.jsp.title"/></title>
		<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}"/>/javascript/commons.js"></script>
		<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}"/>/javascript/tableOrders.js"></script>
		<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}"/>/javascript/printTable.js"></script>	
	</head>

	<body onload='initPage()'>
		<script language="javascript">
			//Cette variable est utilisée dans le fichier printTable.js
			var contextPath = '<c:out value="${pageContext.request.contextPath}"/>';
		</script>
		<c:if test="${(not empty userSession.room.currentTable.orders) and (not empty userSession.room.currentTable.printDate)}">
				<table class="border" width="100%" align="center">
					<tr>
						<td class="border"><b><font color="#FFCC00">|</font></b></td>
						<td class="border" width="100%" colspan="3"><fmt:message key="printTable.jsp.label.printing.table"><fmt:param><c:out value="${userSession.room.currentTable.number}"/></fmt:param></fmt:message></td>
						<td class="border"><b><font color="#FFCC00">|</font></b></td>
					</tr>
					<tr>
						<td class="border"><b><font color="#FFCC00">|</font></b></td>
						<td class="border" width="100%" colspan="3"><label class="policeGray"><fmt:message key="printTable.jsp.alert.printing"/></label></td>
						<td class="border"><b><font color="#FFCC00">|</font></b></td>
					</tr>	
					<tr>
						<td class="border"><b><font color="#FFCC00">|</font></b></td>
					   	<td class="border" width="50%"><a accesskey='<fmt:message key="printTable.jsp.accesskey.cancel"/>' href="javascript:cancel()" class="cancel"><fmt:message key="label.cancel"/></a></td>
						<td class="border"><b><font color="#FFCC00">|</font></b></td>
						<td class="border" width="50%"><a id="idFocusAnchor" accesskey='<fmt:message key="printTable.jsp.accesskey.confirm"/>' href="javascript:processPrinting('<c:out value="${param.printingType}"/>')" class="confirm" ><fmt:message key="label.confirm"/></a></td>
						<td class="border"><b><font color="#FFCC00">|</font></b></td>
					</tr>
				</table>
		</c:if>
		<c:if test="${empty userSession.room.currentTable.orders}">
				<table class="border" width="100%" align="center">
					<tr>
						<td class="border"><b><font color="#FFCC00">|</font></b></td>
						<td class="border" width="100%"><label class="policeGray"><fmt:message key="printTable.jsp.error.orders.empty"/></label></td>
						<td class="border"><b><font color="#FFCC00">|</font></b></td>
					</tr>	
					<tr>
						<td class="border"><b><font color="#FFCC00">|</font></b></td>
						<td class="border" width="100%"><a id="idFocusAnchor" accesskey='<fmt:message key="printTable.jsp.accesskey.cancel"/>' href="javascript:cancel()"><fmt:message key="label.back"/></a></td>
						<td class="border"><b><font color="#FFCC00">|</font></b></td>
					</tr>
				</table>
		</c:if>
		<form name="modifyTableForm" action="<c:out value="${pageContext.request.contextPath}"/>/DisplayJsp.do" method="post" onsubmit="return false" target="IFrameData">
			<input type="hidden" name="pageRequested" value="successTableOrdersIFrame">
			<input type="hidden" name="tableId" value="<c:out value="${userSession.room.currentTable.id}"/>">
			<input type="hidden" name="date">
			<input type="hidden" name="patternDate" value="dd/MM/yyyy-HH:mm:ss">
			<input type="hidden" name='actionPasswordChangeOrders' value="allowModifyOrders">
			<input type="hidden" name='printingType' value="NONE">				
		</form>
		<div style='position: relative; height: 0;visibility:hidden'>
			<c:if test="${not empty userSession.room.currentTable.orders}">	
			<label id="idCurrentTableRegistrationDate"><fmt:formatDate value="${userSession.room.currentTable.registrationDate}" pattern="dd/MM/yyyy" type="DATE"/></label>
			</c:if>	
		
			<!--
				Changement de la langue pour l'impression.
				L'impression doit se faire suivant le language que l'utilisateur aura choisi pour imprimer
			-->
			<jsp:useBean id="userSession" type="fr.montagnesdor.restaurant.struts.UserSession" scope="session"/>
			<c:set var="currentLanguageTemp" value="${userSession.currentLanguage}"/>
			<fmt:setLocale value="${userSession.user.preferedPrintLanguage.id}"/>
			<jsp:setProperty name="userSession" property="currentLanguage" value="${userSession.user.preferedPrintLanguage.id}" /> 

    		<label id='restaurantName'><fmt:message key="restaurant.name"/></label>
    		<label id='restaurantAddressRoad'><fmt:message key="restaurant.address.road"/></label>
    		<label id='restaurantAddressZipcode'><fmt:message key="restaurant.address.zipcode"/></label>
    		<label id='restaurantAddressCity'><fmt:message key="restaurant.address.city"/></label>
    		<label id='restaurantPhone'><fmt:message key="restaurant.phone"/></label>
    		<label id='restaurantOthersInfo1'><fmt:message key="restaurant.others.info1"/></label>
    		<label id='restaurantOthersInfo2'><fmt:message key="restaurant.others.info2"/></label>

			<!-- Details on customer billing order -->
			<c:if test="${not empty param.customerBillName}">
				<label id='customerBillName'><fmt:message key="customerBillInfo.jsp.label.customer.bill.full.name"/> <c:out value="${param.customerBillName}"/></label>
			</c:if>
			<c:if test="${not empty param.customerBillAddress}">
				<label id='customerBillAddress'><fmt:message key="customerBillInfo.jsp.label.customer.bill.address"/> <c:out value="${param.customerBillAddress}"/></label>
			</c:if>
			<c:if test="${not empty param.customerBillCity}">
				<label id='customerBillCity'><fmt:message key="customerBillInfo.jsp.label.customer.bill.city"/> <c:out value="${param.customerBillCity}"/></label>
			</c:if>

			<!-- Details on customer delivery order -->
			<c:if test="${not empty param.customerDeliveryName}">
				<label id='customerDeliveryFullName'><fmt:message key="customerDeliveryInfo.jsp.label.customer.delivery.full.name"/> <c:out value="${param.customerDeliveryName}"/></label>
			</c:if>
			<c:if test="${not empty param.customerDeliveryAddress1}">
				<label id='customerDeliveryAddress1'><fmt:message key="customerDeliveryInfo.jsp.label.customer.delivery.address1"/> <c:out value="${param.customerDeliveryAddress1}"/></label>
			</c:if>
			<c:if test="${not empty param.customerDeliveryAddress2}">
				<label id='customerDeliveryAddress2'><fmt:message key="customerDeliveryInfo.jsp.label.customer.delivery.address2"/> <c:out value="${param.customerDeliveryAddress2}"/></label>
			</c:if>
			<c:if test="${not empty param.customerDeliveryAddress3}">
				<label id='customerDeliveryAddress3'><fmt:message key="customerDeliveryInfo.jsp.label.customer.delivery.address3"/> <c:out value="${param.customerDeliveryAddress3}"/></label>
			</c:if>
			<c:if test="${not empty param.customerDeliveryPhone}">
				<label id='customerDeliveryPhone'><fmt:message key="customerDeliveryInfo.jsp.label.customer.delivery.phone"/> <c:out value="${param.customerDeliveryPhone}"/></label>
			</c:if>
			<c:if test="${not empty param.customerDeliveryEmail}">
				<label id='customerDeliveryEmail'><fmt:message key="customerDeliveryInfo.jsp.label.customer.delivery.email"/> <c:out value="${param.customerDeliveryEmail}"/></label>
			</c:if>
			
    		<label id='registrationDate'><fmt:formatDate value="${userSession.room.currentTable.registrationDate}" pattern="dd/MM/yyyy" type="DATE"/></label>
			<c:if test="${userSession.room.currentTable.takeaway}">
    		<label id='tableCustomerNumber'><fmt:message key="printTable.jsp.takeaway"><fmt:param><c:out value="${userSession.room.currentTable.number}"/></fmt:param></fmt:message></label>
    		</c:if>
			<c:if test="${not userSession.room.currentTable.takeaway}">
	   		<label id='tableCustomerNumber'><fmt:message key="printTable.jsp.inplace"><fmt:param><c:out value="${userSession.room.currentTable.number}"/></fmt:param><fmt:param><c:out value="${userSession.room.currentTable.customersNumber}"/></fmt:param></fmt:message></label>
	   		</c:if>

	   		<label id='ordersQuantity'><fmt:message key="printTable.jsp.orders.quantity"/></label>
	   		<label id='ordersLabel'><fmt:message key="printTable.jsp.orders.label"/></label>
	   		<label id='ordersAmount'><fmt:message key="printTable.jsp.orders.amount"/></label>
	   		<label id='reductionLabel'><fmt:message key="printTable.jsp.reduction.label"/></label>
	   		<label id='thank'><fmt:message key="printTable.jsp.thank"/></label>
	   		<label id='serviceIncluded'><fmt:message key="printTable.jsp.service.included"/></label>
	   		<label id='amountPayLabel'><fmt:message key="printTable.jsp.amount.pay.label"/></label>
	   		<label id='amountTaxeNet'><fmt:message key="printTable.jsp.amount.taxe.net"/></label>
	   		<label id='quantitiesSum'><fmt:formatNumber pattern="0.0" var="quantitiesSum" value="${userSession.room.currentTable.quantitiesSum}"/><c:out value="${fn:replace(quantitiesSum, ',', '.')}" /></label>
		    <label id='reductionRatio'><fmt:formatNumber pattern="0.00" var="reductionRatio" value="${userSession.room.currentTable.reductionRatio}"/><c:out value="${fn:replace(reductionRatio, ',', '.')}" /></label>
		    <label id='reduction'><fmt:formatNumber pattern="0.00" var="reduction" value="${userSession.room.currentTable.reduction}"/><c:out value="${fn:replace(reduction, ',', '.')}" /></label>
		    <label id='amountsSum'><fmt:formatNumber pattern="0.00" var="amountsSum" value="${userSession.room.currentTable.amountsSum}"/><c:out value="${fn:replace(amountsSum, ',', '.')}" /></label>
		    <label id='amountPay'><fmt:formatNumber pattern="0.00" var="amountPay" value="${userSession.room.currentTable.amountPay}"/><c:out value="${fn:replace(amountPay, ',', '.')}" /></label>
	
		<c:if test="${not empty userSession.room.currentTable.orders}">
			<table id='ordersLine'>
			<c:forEach var="orderLine" items="${userSession.room.currentTable.orders}" varStatus="status">
				<tr>
					<td id='TDQ<c:out value="${status.index}"/>'><fmt:formatNumber pattern="0.0" var="quantity" value="${orderLine.quantity}"/><c:out value="${fn:replace(quantity, ',', '.')}" /></td>
					<td id='TDC<c:out value="${status.index}"/>'><c:out value="${orderLine.product.id}"/><c:if test="${not empty orderLine.specialCodeValue}" ><c:out value="${orderLine.specialCodeValue}"/></c:if></td>
					<td id='TDL<c:out value="${status.index}"/>'><c:set var="isLanguageLabelExist" scope="page" value="false"/><c:if test="${orderLine.product.productSpecialCode.code!='USER_ORDER'}"><c:if test="${orderLine.product.labels.size!=0}"><c:forEach var="label" items="${orderLine.product.labels}"><c:if test="${label.key==sessionScope.userSession.currentLanguage}"><c:out value="${label.value}"/><c:if test="${orderLine.product.productSpecialCode.code=='NOTHING'}"><c:set var="isLanguageLabelExist" value="true"/></c:if><c:if test="${orderLine.product.productSpecialCode.code=='REDUCED_ORDER'}"><c:out value="${orderLine.specialCodeValue}"/> %<c:set var="isLanguageLabelExist" value="true"/></c:if></c:if></c:forEach></c:if></c:if><c:if test="${orderLine.product.productSpecialCode.code=='OFFERED_PRODUCT'}"><c:if test="${(not empty orderLine.specialCodeValueProduct) and orderLine.specialCodeValueProduct.labels.size!=0}"><c:forEach var="labelSpecialCodeProduct" items="${orderLine.specialCodeValueProduct.labels}"><c:if test="${labelSpecialCodeProduct.key==sessionScope.userSession.currentLanguage}"><c:out value="${labelSpecialCodeProduct.value}"/><c:set var="isLanguageLabelExist" value="true"/></c:if></c:forEach></c:if></c:if><c:if test="${isLanguageLabelExist=='false'}"><c:out value="${orderLine.label}"/></c:if></td>
					<td id='TDP<c:out value="${status.index}"/>'><fmt:formatNumber pattern="0.00" var="unitPrice" value="${orderLine.unitPrice}"/><c:out value="${fn:replace(unitPrice, ',', '.')}" /></td>
					<td id='TDA<c:out value="${status.index}"/>'><fmt:formatNumber pattern="0.00" var="amount" value="${orderLine.amount}"/><c:out value="${fn:replace(amount, ',', '.')}" /></td>
				</tr>
			</c:forEach>
			</table>
		</c:if>
		    <label id='billNumber'><fmt:message key="printTable.jsp.bill.number"><fmt:param><c:out value="${userSession.room.currentTable.id}"/></fmt:param></fmt:message></label>

		    <c:if test="${userSession.room.currentTable.customersNumber!=0}">
		    <label id='billCustomerNumber'><fmt:message key="printTable.jsp.bill.customer.number"><fmt:param><c:out value="${userSession.room.currentTable.customersNumber}"/></fmt:param></fmt:message></label>
			</c:if>
		    <c:if test="${userSession.room.currentTable.customersNumber==0}">
		    <label id='billCustomerNumber'></label>
			</c:if>

		<c:set var="vatsNumber" value="0"/>
		<c:forEach var="vat" items="${userSession.room.currentTable.vats}" varStatus="status">
			<c:set var="vatsNumber" value="${vatsNumber+1}"/>
			<label id='vatValueLabel<c:out value="${status.index}"/>'><fmt:message key="printTable.jsp.vat.amount"><fmt:param><c:out value="${vat.key.value}"/></fmt:param></fmt:message></label>
			<label id='vatValue<c:out value="${status.index}"/>'><fmt:formatNumber pattern="0.00" var="value" value="${vat.value.value}"/><c:out value="${fn:replace(value, ',', '.')}" /></label>
		</c:forEach>
		<label id='vatsNumber'><c:out value="${vatsNumber}"/></label>
	</div>
		<jsp:include page='/jsp/commons/includeDivIFrame.jsp'/>
		<c:if test="${(not empty userSession.room.currentTable.orders) and (empty userSession.room.currentTable.printDate)}">
		<script language="javascript">
			processPrinting('<c:out value="${param.printingType}"/>');
		</script>
		</c:if>
		<!--
			Mise à jour de la langue après l'impression.
			Après l'impression, on doit remettre le language courant choisi par l'utilisateur
		-->			
		<fmt:setLocale value="${currentLanguageTemp}"/>
		<jsp:setProperty name="userSession" property="currentLanguage" value="${currentLanguageTemp}"/> 
	</body>
</html>
