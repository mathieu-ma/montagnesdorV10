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
		<form name="modifyTableForm" action="<c:out value="${pageContext.request.contextPath}"/>/DaylyReceiptsListIFrame.do" method="post" onsubmit="return false" target="IFrameData">
			<input type="hidden" name='optionSelected' value='<c:out value="${param.optionSelected}"/>'>
			<input type="hidden" name='filterList' value='<c:out value="${param.filterList}"/>'>
			<input type="hidden" name='sortListBy' value='<c:out value="${param.sortListBy}"/>'>
			<input type="hidden" name='sortMonotony' value='<c:out value="${param.sortMonotony}"/>'>
		</form>
		<div style='position: relative; height: 0;visibility:hidden'>
    		<label id='restaurantName'><fmt:message key="restaurant.name"/></label>
    		<label id='restaurantAddressRoad'><fmt:message key="restaurant.address.road"/></label>
    		<label id='restaurantAddressZipcode'><fmt:message key="restaurant.address.zipcode"/></label>
    		<label id='restaurantAddressCity'><fmt:message key="restaurant.address.city"/></label>
    		<label id='restaurantPhone'><fmt:message key="restaurant.phone"/></label>
    		<label id='restaurantOthersInfo1'><fmt:message key="restaurant.others.info1"/></label>
    		<label id='restaurantOthersInfo2'><fmt:message key="restaurant.others.info2"/></label>

    		<label id='registrationDate'><fmt:formatDate value="${attributesOut.registrationDate}" pattern="dd/MM/yyyy" type="DATE"/></label>
			<c:if test="${attributesOut.takeaway}">
    		<label id='tableCustomerNumber'><fmt:message key="printTable.jsp.takeaway"><fmt:param><c:out value="${attributesOut.number}"/></fmt:param></fmt:message></label>
    		</c:if>
			<c:if test="${not attributesOut.takeaway}">
	   		<label id='tableCustomerNumber'><fmt:message key="printTable.jsp.inplace"><fmt:param><c:out value="${attributesOut.number}"/></fmt:param><fmt:param><c:out value="${attributesOut.customersNumber}"/></fmt:param></fmt:message></label>
	   		</c:if>

	   		<label id='ordersQuantity'><fmt:message key="printTable.jsp.orders.quantity"/></label>
	   		<label id='ordersLabel'><fmt:message key="printTable.jsp.orders.label"/></label>
	   		<label id='ordersAmount'><fmt:message key="printTable.jsp.orders.amount"/></label>
	   		<label id='reductionLabel'><fmt:message key="printTable.jsp.reduction.label"/></label>
	   		<label id='thank'><fmt:message key="printTable.jsp.thank"/></label>
	   		<label id='serviceIncluded'><fmt:message key="printTable.jsp.service.included"/></label>
	   		<label id='amountPayLabel'><fmt:message key="printTable.jsp.amount.pay.label"/></label>
	   		<label id='amountTaxeNet'><fmt:message key="printTable.jsp.amount.taxe.net"/></label>
	   		<label id='quantitiesSum'><fmt:formatNumber pattern="0.0" var="quantitiesSum" value="${attributesOut.quantitiesSum}"/><c:out value="${fn:replace(quantitiesSum, ',', '.')}" /></label>
		    <label id='reductionRatio'><fmt:formatNumber pattern="0.00" var="reductionRatio" value="${attributesOut.reductionRatio}"/><c:out value="${fn:replace(reductionRatio, ',', '.')}" /></label>
		    <label id='reduction'><fmt:formatNumber pattern="0.00" var="reduction" value="${attributesOut.reduction}"/><c:out value="${fn:replace(reduction, ',', '.')}" /></label>
		    <label id='amountsSum'><fmt:formatNumber pattern="0.00" var="amountsSum" value="${attributesOut.amountsSum}"/><c:out value="${fn:replace(amountsSum, ',', '.')}" /></label>
		    <label id='amountPay'><fmt:formatNumber pattern="0.00" var="amountPay" value="${attributesOut.amountPay}"/><c:out value="${fn:replace(amountPay, ',', '.')}" /></label>
	
		<c:if test="${not empty attributesOut.orders}">
			<table id='ordersLine'>
			<c:forEach var="orderLine" items="${attributesOut.orders}" varStatus="status">
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

		<c:set var="vatsNumber" value="0"/>
		<c:forEach var="vat" items="${attributesOut.vats}" varStatus="status">
			<c:set var="vatsNumber" value="${vatsNumber+1}"/>
			<label id='vatValueLabel<c:out value="${status.index}"/>'><fmt:message key="printTable.jsp.vat.amount"><fmt:param><c:out value="${vat.key.value}"/></fmt:param></fmt:message></label>
			<label id='vatValue<c:out value="${status.index}"/>'><fmt:formatNumber pattern="0.00" var="value" value="${vat.value.value}"/><c:out value="${fn:replace(value, ',', '.')}" /></label>
		</c:forEach>
		<label id='vatsNumber'><c:out value="${vatsNumber}"/></label>

		</div>
		<jsp:include page='/jsp/commons/includeDivIFrame.jsp'/>
		<c:if test="${(not empty attributesOut.orders)}">
		<script language="javascript">
			processPrintingByCashing();
		</script>
		</c:if>		
	</body>
</html>