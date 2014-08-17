<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!--
	When you use an absolute URI, you do not have to add the taglib element to web.xml; 
	the JSP container automatically locates the TLD inside the JSTL library implementation.
-->
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/fn.tld" prefix="fn" %>
<!--%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %-->
<!--%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %-->
<!--%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %-->

<jsp:include page='/jsp/commons/checkSessionAuthorization.jsp'/>

<html>
	<head> 
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" >
		<link rel="stylesheet" type="text/css" href="<c:out value="${pageContext.request.contextPath}"/>/css/montagnesdor.css" />
		<title><fmt:message key="montagnesdor.welcome"/> - <fmt:message key="tableOrdersIFrame.jsp.title"/></title>
		<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}"/>/javascript/commons.js"></script>
		<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}"/>/javascript/tableOrders.js"></script>
	</head>
	<body id="idBody" onload='initPageIFrame()'>
	<script language="javascript">
		//Cette variable est utilisée dans le fichier tableOrders.js
		var contextPath = '<c:out value="${pageContext.request.contextPath}"/>';
		//Cette variable permet de stocker les codes produits associés aux numéros de de lignes du tableau
		//la clé de ce hashmap est le code produit et la valeur est le numéro de ligne
		//Pour savoir s'il faut mettre à jour automatiquement les lignes de commandes déjà enregistrées, il faut tester le checkbox idAutoUpdateOrderLine dans la partie Option
		var hashMapOrders = new Array();
		//Permet de savoir quel type d'arrondi il faut appliquer
		var specificRoundChoose = HALF_ROUND;
		specificRoundChoose = '<c:out value="${userSession.room.currentTable.specificRound}"/>';
		if(specificRoundChoose == '')
			specificRoundChoose = HALF_ROUND;
	</script>
		<form onsubmit="return false" accept-charset="UTF-8">
			<table class="border" width='100%' id="IDTableOrders">
		<c:if test="${not empty userSession.room.currentTable.orders}">
			<c:forEach var="orderLine" items="${userSession.room.currentTable.orders}" varStatus="status">
				<c:if test="${not empty orderLine.product.colorRGB}" >
				<tr onmouseover="this.className='over';" onmouseout="this.className='default';" style='background-color:#<c:out value="${orderLine.product.colorRGB}"/>' ID='<c:out value="${orderLine.id}"/>'>
				</c:if>
				<c:if test="${empty orderLine.product.colorRGB}" >
				<tr onmouseover="this.className='over';" onmouseout="this.className='default';" ID='<c:out value="${orderLine.id}"/>'>
				</c:if>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
					<td class="quantity" ID='TDQ<c:out value="${status.index}"/>'><a class='quantity' href='#' ID='AQ<c:out value="${status.index}"/>' onclick='setInputText(this)'><fmt:formatNumber pattern="0.0" var="quantity" value="${orderLine.quantity}"/><c:out value="${fn:replace(quantity, ',', '.')}" /></a></td>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
					<td class="code" ID='TDC<c:out value="${status.index}"/>'><c:out value="${orderLine.product.id}"/><c:if test="${not empty orderLine.specialCodeValue}" ><c:out value="${orderLine.specialCodeValue}"/></c:if></td>
					<script language="javascript">
						hashMapOrders['<c:out value="${orderLine.product.id}"/><c:if test="${not empty orderLine.specialCodeValue}" ><c:out value="${orderLine.specialCodeValue}"/></c:if>'] = '<c:out value="${status.index}"/>';
					</script>						
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
					<td class="label" ID='TDL<c:out value="${status.index}"/>'>
					<c:set var="isLanguageLabelExist" scope="page" value="false"/>
					<c:if test="${orderLine.product.productSpecialCode.code!='USER_ORDER'}">
						<c:if test="${orderLine.product.labels.size!=0}">
							<c:forEach var="label" items="${orderLine.product.labels}">
								<c:if test="${label.key==sessionScope.userSession.currentLanguage}">
									<c:out value="${label.value}"/>
									<c:if test="${orderLine.product.productSpecialCode.code=='NOTHING'}">									
										<c:set var="isLanguageLabelExist" value="true"/>
									</c:if>
									<c:if test="${orderLine.product.productSpecialCode.code=='REDUCED_ORDER'}">									
										<c:out value="${orderLine.specialCodeValue}"/> %
										<c:set var="isLanguageLabelExist" value="true"/>
									</c:if>		
								</c:if>
							</c:forEach>
						</c:if>
					</c:if>		
					<c:if test="${orderLine.product.productSpecialCode.code=='OFFERED_PRODUCT'}">
						<c:if test="${(not empty orderLine.specialCodeValueProduct) and orderLine.specialCodeValueProduct.labels.size!=0}">
							<c:forEach var="labelSpecialCodeProduct" items="${orderLine.specialCodeValueProduct.labels}">
								<c:if test="${labelSpecialCodeProduct.key==sessionScope.userSession.currentLanguage}">
									<c:out value="${labelSpecialCodeProduct.value}"/>
									<c:set var="isLanguageLabelExist" value="true"/>
								</c:if>
							</c:forEach>
						</c:if>		
					</c:if>
					<c:if test="${isLanguageLabelExist=='false'}">
							<c:out value="${orderLine.label}"/>
					</c:if>
					</td>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
					<td class="price" ID='TDP<c:out value="${status.index}"/>'><fmt:formatNumber pattern="0.00" var="unitPrice" value="${orderLine.unitPrice}"/><c:out value="${fn:replace(unitPrice, ',', '.')}" /></td>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
					<td class="amount" ID='TDA<c:out value="${status.index}"/>'><fmt:formatNumber pattern="0.00" var="amount" value="${orderLine.amount}"/><c:out value="${fn:replace(amount, ',', '.')}" /></td>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
				</tr>
				<c:if test="${status.last}" >
		  		<tr onmouseover="this.className='over';" onmouseout="this.className='default';" ID='x'> 
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
	    			<td class="quantityCenter" ID='TDQ<c:out value="${status.index+1}"/>'> 
	    				<input type='text' ID='Q' name='quantity' isValueEmpty='true' class='quantity' onkeyup='processUserEntry(event, this)' maxlength='5'>
					</td>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
	    			<td class="code" ID='TDC<c:out value="${status.index+1}"/>'></td>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
					<td class="label" ID='TDL<c:out value="${status.index+1}"/>'></td>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
					<td class="price" ID='TDP<c:out value="${status.index+1}"/>'></td>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
	    			<td class="amount" ID='TDA<c:out value="${status.index+1}"/>'></td>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
				</tr>
				</c:if>
			</c:forEach>
		</c:if>
		<c:if test="${empty userSession.room.currentTable.orders}">	
				<tr onmouseover="this.className='over';" onmouseout="this.className='default';" ID='x'> 
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
	    			<td class="quantityCenter" ID='TDQ0'> 
		    				<input type='text' ID='Q' name='quantity' isValueEmpty='true' class='quantity' onkeyup='processUserEntry(event, this)' maxlength='5'>
					</td>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
	    			<td class="code" ID='TDC0'></td>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
					<td class="label" ID='TDL0'></td>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
					<td class="price" ID='TDP0'></td>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
	    			<td class="amount" ID='TDA0'></td>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
				</tr>
		</c:if>
		</table>
	</form>
	<form name="modifyTableForm" action="<c:out value="${pageContext.request.contextPath}"/>/DisplayJsp.do" method="post" onsubmit="return false" target="IFrameData">
		<input type="hidden" name="pageRequested" value="successMergeTable">
		<input type="hidden" name='tableId' value="<c:out value="${userSession.room.currentTable.id}"/>">
		<input type="hidden" name='actionPasswordChangeOrders' value="allowModifyOrders">
		<input type="hidden" name='optionSelected' value="MERGE">
		<input type="hidden" name='isTakeaway' value="${userSession.room.currentTable.takeaway}">
		<input type="hidden" name='customersNumber' value="${userSession.room.currentTable.customersNumber}">
		<input type="hidden" name='printingType' value="NONE">
	</form>
	<form name="alertMessagesForm" onsubmit="return false">
		<input type="hidden" name="tableOrdersIFrameJspCodeInvalide" value="<fmt:message key="tableOrdersIFrame.jsp.code.invalide"/>">
		<input type="hidden" name="tableOrdersIFrameJspProductNotExist" value="<fmt:message key="tableOrdersIFrame.jsp.product.not.exist"/>">
		<input type="hidden" name="tableOrdersIFrameJspMoveForbidden" value="<fmt:message key="tableOrdersIFrame.jsp.move.forbidden"/>">		
		<input type="hidden" name="tableOrdersIFrameJspRestoreReductionRatio" value="<fmt:message key="tableOrdersIFrame.jsp.restore.reduction.ratio"/>">		
		<input type="hidden" name="tableOrdersIFrameJspUpdateReductionRatioForbidden" value="<fmt:message key="tableOrdersIFrame.jsp.update.reduction.ratio.forbidden"/>">		
		<input type="hidden" name="tableOrdersIFrameJspReductionIncorrectInput" value="<fmt:message key="tableOrdersIFrame.jsp.reduction.incorrect.input"/>">		
		<input type="hidden" name="tableOrdersIFrameJspAlertMergeForbidden" value="<fmt:message key="tableOrdersIFrame.jsp.alert.merge.forbidden"/>">		
		<input type="hidden" name="tableOrdersIFrameJspAlertChangeForbidden" value="<fmt:message key="tableOrdersIFrame.jsp.alert.change.forbidden"/>">		
		<input type="hidden" name="tableOrdersIFrameJspAlertRemoveForbidden" value="<fmt:message key="tableOrdersIFrame.jsp.alert.remove.forbidden"/>">		
		<input type="hidden" name="alertLabelNegativeQuantity" value="<fmt:message key="alert.label.negative.quantity"/>">		
		<input type="hidden" name="alertLabelNegativePrice" value="<fmt:message key="alert.label.negative.price"/>">		
	</form>
	<jsp:include page='/jsp/commons/includeDivIFrame.jsp'/>
		<div style='position: relative; height: 0;visibility:hidden'>
			<c:if test="${not empty userSession.room.currentTable.orders}">	
			<label id="idCurrentTableRegistrationDate"><fmt:formatDate value="${userSession.room.currentTable.registrationDate}" pattern="dd/MM/yyyy" type="DATE"/></label>
			</c:if>	
		
			<table width="100%" height="100%" cellspacing="0" cellpadding="0" border="0" >
				<tr>
					<td id="idTdDataOptions" width="15%" valign="top" title='Data Options'>
						<table class="border" width="95%">
							<tr>
						    	<td class="border"> 
							      <a href="javascript:window.IFrameData.printTable();" title="<fmt:message key="tableOrdersIFrame.jsp.title.print"><fmt:param><c:out value="${userSession.room.currentTable.number}"/></fmt:param></fmt:message>"><fmt:message key="tableOrdersIFrame.jsp.label.print"/></a>
							    </td>
							</tr>
							<tr>
								<td class="border">
								  <a href="javascript:window.IFrameData.cashTable();" title="<fmt:message key="tableOrdersIFrame.jsp.title.cash"><fmt:param><c:out value="${userSession.room.currentTable.number}"/></fmt:param></fmt:message>"><fmt:message key="tableOrdersIFrame.jsp.label.cash"/></a>
							    </td>
							</tr>
							<c:if test="${not empty userSession.room.currentTable.printDate}">
						  	<tr> 
						    	<td class="border">
									<label class="policeGray"><fmt:message key="tableOrdersIFrame.jsp.label.merge"/></label>
						    	</td>
						  	</tr>
						  	<tr>
						    	<td class="border">
						      		<a href="javascript:window.IFrameData.changeTable();" title="<fmt:message key="tableOrdersIFrame.jsp.title.change"><fmt:param><c:out value="${userSession.room.currentTable.number}"/></fmt:param></fmt:message>"><fmt:message key="tableOrdersIFrame.jsp.label.change"/></a>
						    	</td>
						  	</tr>
							<tr> 
							    <td class="border">
									<label class="policeGray"><fmt:message key="tableOrdersIFrame.jsp.label.remove"/></label>
						    	</td>
							</tr>
					    	</c:if>
							<c:if test="${empty userSession.room.currentTable.printDate}">
						  	<tr>
						    	<td class="border">
							  		<a href="javascript:window.IFrameData.mergeTable();" title="<fmt:message key="tableOrdersIFrame.jsp.title.merge"><fmt:param><c:out value="${userSession.room.currentTable.number}"/></fmt:param></fmt:message>"><fmt:message key="tableOrdersIFrame.jsp.label.merge"/></a>
						    	</td>
						  	</tr>
						  	<tr> 
						    	<td class="border">
						      		<a href="javascript:window.IFrameData.changeTable();" title="<fmt:message key="tableOrdersIFrame.jsp.title.change"><fmt:param><c:out value="${userSession.room.currentTable.number}"/></fmt:param></fmt:message>"><fmt:message key="tableOrdersIFrame.jsp.label.change"/></a>
						    	</td>
						  	</tr>
							<tr>
							    <td class="border">
						      		<a href="javascript:window.IFrameData.deleteTable()" title="<fmt:message key="tableOrdersIFrame.jsp.title.remove"><fmt:param><c:out value="${userSession.room.currentTable.number}"/></fmt:param></fmt:message>"><fmt:message key="tableOrdersIFrame.jsp.label.remove"/></a>
						    	</td>
							</tr>
					    	</c:if>
							<tr>
						    	<td class="border"> 
							      <a href="javascript:window.IFrameData.billPrint();" title="<fmt:message key="tableOrdersIFrame.jsp.title.bill.printing"><fmt:param><c:out value="${userSession.room.currentTable.number}"/></fmt:param></fmt:message>"><fmt:message key="tableOrdersIFrame.jsp.label.bill.printing"/></a>
							    </td>
							</tr>
							<!-- tr>
						    	<td class="border"> 
							      <a href="javascript:window.IFrameData.deliveryPrint();" title="<fmt:message key="tableOrdersIFrame.jsp.title.delivery.printing"><fmt:param><c:out value="${userSession.room.currentTable.number}"/></fmt:param></fmt:message>"><fmt:message key="tableOrdersIFrame.jsp.label.delivery.printing"/></a>
							    </td>
							</tr-->
						</table>
					</td>
				</tr>					
				<tr>
					<td  id="idTdDataHeader" height="5%" title='Data Header'>
						<table class="border" id="idTableDataHeader">
							<tr>
							    <td class="border"><b><font color="#FFCC00">|</font></b></td>
							    <td class="border" style="width: 9%;"><label class="policeGray"><fmt:message key="tableOrdersIFrame.jsp.label.quantity"/></label></td>
							    <td class="border"><b><font color="#FFCC00">|</font></b></td>
							    <td class="border" style="width: 9%;"><label class="policeGray"><fmt:message key="tableOrdersIFrame.jsp.label.code"/></label></td>
							    <td class="border"><b><font color="#FFCC00">|</font></b></td>
							    <td class="border" style="width: 64%;"><label class="policeGray"><fmt:message key="tableOrdersIFrame.jsp.label.description"/></label></td>
							    <td class="border"><b><font color="#FFCC00">|</font></b></td>
							    <td class="border" style="width: 9%;"><label class="policeGray"><fmt:message key="tableOrdersIFrame.jsp.label.unit.price"/></label></td>
							    <td class="border"><b><font color="#FFCC00">|</font></b></td>
							    <td class="border" style="width: 9%;"><label class="policeGray"><fmt:message key="tableOrdersIFrame.jsp.label.amount"/></label></td>
							    <td class="border"><b><font color="#FFCC00">|</font></b></td>
							</tr>
						</table>
					</td>
				</tr>	
				<tr>
					<td id="idTdDataFooter" height="5%"  title='Data Footer'>
							<table width="100%">
							  <tr>
							    <td style="width: 11%"><label class="policeGray"><fmt:message key="tableOrdersIFrame.jsp.label.quantites.sum"/></label></td>
							    <td style="width: 5%" id='quantitiesSum' class="footer"><fmt:formatNumber pattern="0.0" var="quantitiesSum" value="${userSession.room.currentTable.quantitiesSum}"/><c:out value="${fn:replace(quantitiesSum, ',', '.')}" /></td>
							    <td style="width: 11%; text-align: center"><label class="policeGray"><fmt:message key="tableOrdersIFrame.jsp.label.reduction.ratio"/></label></td>
							    <td style="width: 5%" class="footer">
								    <c:if test="${!userSession.room.currentTable.reductionRatioChanged}">
								    	<input id='idReductionRatio' name='reductionRatio' class="reductionRatioYellow" type='text' onkeyup='window.IFrameData.processUserEntryReductionRatio(event, this)' onclick='window.IFrameData.clearRatio(this)' onfocus='this.value = this.value.toUpperCase();' onblur='window.IFrameData.resetOldReductionRatioValue()' maxlength='7' value='<fmt:formatNumber pattern="0.00" var="reductionRatio" value="${userSession.room.currentTable.reductionRatio}"/><c:out value="${fn:replace(reductionRatio, ',', '.')}" />'>
							    	</c:if>
								    <c:if test="${userSession.room.currentTable.reductionRatioChanged}">
								    	<input id='idReductionRatio' name='reductionRatio' class="reductionRatioRed" type='text' onkeyup='window.IFrameData.processUserEntryReductionRatio(event, this)' onclick='window.IFrameData.clearRatio(this)' onfocus='this.value = this.value.toUpperCase();' onblur='window.IFrameData.resetOldReductionRatioValue()' maxlength='7' value='<fmt:formatNumber pattern="0.00" var="reductionRatio" value="${userSession.room.currentTable.reductionRatio}"/><c:out value="${fn:replace(reductionRatio, ',', '.')}" />'>
							    	</c:if>							    	
							    </td>
							    <td style="width: 8%; text-align: center"><label class="policeGray"><fmt:message key="tableOrdersIFrame.jsp.label.reduction"/></label></td>
							    <td style="width: 8%" id='reduction' class="footer" nowrap><c:if test="${userSession.room.currentTable.reduction!=0}"><fmt:formatNumber pattern="0.00" var="reduction" value="${-userSession.room.currentTable.reduction}"/><c:out value="${fn:replace(reduction, ',', '.')}" /></c:if><c:if test="${userSession.room.currentTable.reduction==0}"><fmt:formatNumber pattern="0.00" var="reduction" value="${userSession.room.currentTable.reduction}"/><c:out value="${fn:replace(reduction, ',', '.')}" /></c:if></td>
							    <td style="width: 10%; text-align: center"><label class="policeGray"><fmt:message key="tableOrdersIFrame.jsp.label.amounts.sum"/></label></td>
							    <td style="width: 10%" id='amountsSum' class="footer"><fmt:formatNumber pattern="0.00" var="amountsSum" value="${userSession.room.currentTable.amountsSum}"/><c:out value="${fn:replace(amountsSum, ',', '.')}" /></td>
							    <td style="width: 10%; text-align: center"><label class="policeGray"><fmt:message key="tableOrdersIFrame.jsp.label.amount.pay"/></label></td>
							    <td style="width: 10%" id='amountPay' class="footer"><fmt:formatNumber pattern="0.00" var="amountPay" value="${userSession.room.currentTable.amountPay}"/><c:out value="${fn:replace(amountPay, ',', '.')}" /></td>
							    <td style="width: 12%; text-align: right">
								    <label id="idLabelPrinting"><c:if test="${not empty userSession.room.currentTable.printDate}" ><a href="javascript:window.IFrameData.resetPrintDate()" title="<fmt:message key="tableOrdersIFrame.jsp.reset.print.date"/>" class="blink"><fmt:message key="tableOrdersIFrame.jsp.label.printing.done"/></a></c:if></label>
							    </td>
							  </tr>
							</table>
					</td>
				</tr>	
			</table>			
		</div>
		<a id='idPositionBottom' href="#"></a>
	</body>
</html>