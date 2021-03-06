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
		<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}"/>/javascript/cashTable.js"></script>	
	</head>
	<body onload='initPage()'>
		<script language="javascript">
			//Ces variables sont utilisées dans le fichier cashTable.js
			var contextPath = '<c:out value="${pageContext.request.contextPath}"/>';
		</script>
			<html:form method="post" action='/CashTable.do' onsubmit="return false">
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
							<c:if test="${requestScope.attributesOut.unpaid!=0}">
								<input type='text' name='cash' readOnly onkeyup='processUserEntry(event, this)' maxlength='10' class='cash' value='<fmt:formatNumber pattern="0.00" var="payment" value="${requestScope.attributesOut.cash}"/><c:out value="${fn:replace(payment, ',', '.')}" />'>
							</c:if>
							<c:if test="${requestScope.attributesOut.unpaid==0}">
								<input type='text' name='cash' onkeyup='processUserEntry(event, this)' maxlength='10' class='cash' value='<fmt:formatNumber pattern="0.00" var="payment" value="${requestScope.attributesOut.cash}"/><c:out value="${fn:replace(payment, ',', '.')}" />'>
							</c:if>
						</td>			
						<td class="border"><b><font color="#FFCC00">|</font></b></td>
					</tr>	
					<tr>
						<td class="border"><b><font color="#FFCC00">|</font></b></td>
						<td class="border" width="50%"><a href="javascript:autoCompletion('ticket')"><fmt:message key="cashTable.jsp.label.ticket"/></a></td>
						<td class="border"><b><font color="#FFCC00">|</font></b></td>
						<td class="border" width="50%">
							<c:if test="${requestScope.attributesOut.unpaid!=0}">
								<input type='text' name='ticket' readOnly onkeyup='processUserEntry(event, this)' maxlength='10' class='cash' value='<fmt:formatNumber pattern="0.00" var="payment" value="${requestScope.attributesOut.ticket}"/><c:out value="${fn:replace(payment, ',', '.')}" />'>
							</c:if>
							<c:if test="${requestScope.attributesOut.unpaid==0}">
								<input type='text' name='ticket' onkeyup='processUserEntry(event, this)' maxlength='10' class='cash' value='<fmt:formatNumber pattern="0.00" var="payment" value="${requestScope.attributesOut.ticket}"/><c:out value="${fn:replace(payment, ',', '.')}" />'>
							</c:if>
						</td>			
						<td class="border"><b><font color="#FFCC00">|</font></b></td>
					</tr>	
					<tr>
						<td class="border"><b><font color="#FFCC00">|</font></b></td>
						<td class="border" width="50%"><a href="javascript:autoCompletion('cheque')"><fmt:message key="cashTable.jsp.label.cheque"/></a></td>
						<td class="border"><b><font color="#FFCC00">|</font></b></td>
						<td class="border" width="50%">
							<c:if test="${requestScope.attributesOut.unpaid!=0}">
								<input type='text' name='cheque' readOnly onkeyup='processUserEntry(event, this)' maxlength='10' class='cash' value='<fmt:formatNumber pattern="0.00" var="payment" value="${requestScope.attributesOut.cheque}"/><c:out value="${fn:replace(payment, ',', '.')}" />'>
							</c:if>
							<c:if test="${requestScope.attributesOut.unpaid==0}">
								<input type='text' name='cheque' onkeyup='processUserEntry(event, this)' maxlength='10' class='cash' value='<fmt:formatNumber pattern="0.00" var="payment" value="${requestScope.attributesOut.cheque}"/><c:out value="${fn:replace(payment, ',', '.')}" />'>
							</c:if>
						</td>			
						<td class="border"><b><font color="#FFCC00">|</font></b></td>
					</tr>	
					<tr>
						<td class="border"><b><font color="#FFCC00">|</font></b></td>
						<td class="border" width="50%"><a href="javascript:autoCompletion('card')"><fmt:message key="cashTable.jsp.label.card"/></a></td>
						<td class="border"><b><font color="#FFCC00">|</font></b></td>
						<td class="border" width="50%">
						<c:if test="${requestScope.attributesOut.unpaid!=0}">
							<input type='text' name='card' readOnly onkeyup='processUserEntry(event, this)' maxlength='10' class='cash' value='<fmt:formatNumber pattern="0.00" var="payment" value="${requestScope.attributesOut.card}"/><c:out value="${fn:replace(payment, ',', '.')}" />'>
						</c:if>
						<c:if test="${requestScope.attributesOut.unpaid==0}">
							<input type='text' name='card' onkeyup='processUserEntry(event, this)' maxlength='10' class='cash' value='<fmt:formatNumber pattern="0.00" var="payment" value="${requestScope.attributesOut.card}"/><c:out value="${fn:replace(payment, ',', '.')}" />'>
						</c:if>
						</td>			
						<td class="border"><b><font color="#FFCC00">|</font></b></td>
					</tr>
					<tr>
						<td class="border"><b><font color="#FFCC00">|</font></b></td>
						<td class="border" width="50%">
						<c:if test="${requestScope.attributesOut.unpaid!=0}">
							<input type=checkbox name="unpaidCheck" checked onclick="checkUnpaid(this)">
						</c:if>
						<c:if test="${requestScope.attributesOut.unpaid==0}">
							<input type=checkbox name="unpaidCheck" onclick="checkUnpaid(this)">
						</c:if>
							<label class="policeGray"><fmt:message key="cashTable.jsp.label.unpaid"/></label></td>
						<td class="border"><b><font color="#FFCC00">|</font></b></td>
						<td class="border" width="50%" id="idUnpaid"><fmt:formatNumber pattern="0.00" var="payment" value="${requestScope.attributesOut.unpaid}"/><c:out value="${fn:replace(payment, ',', '.')}"/></td>			
						<td class="border"><b><font color="#FFCC00">|</font></b></td>
					</tr>
					<tr>
						<td class="border"><b><font color="#FFCC00">|</font></b></td>
						<td class="border" width="50%"><label class="policeGray"><fmt:message key="cashTable.jsp.label.amount.pay"/></label></td>
						<td class="border"><b><font color="#FFCC00">|</font></b></td>
						<td class="border" width="50%" id="idAmountPay"><fmt:formatNumber pattern="0.00" var="amountPay" value="${requestScope.attributesOut.dinnerTable.amountPay}"/><c:out value="${fn:replace(amountPay, ',', '.')}" /></td>
						<td class="border"><b><font color="#FFCC00">|</font></b></td>
					</tr>
					<tr>
						<td class="border"><b><font color="#FFCC00">|</font></b></td>
						<td class="border" width="50%"><label class="policeGray"><fmt:message key="cashTable.jsp.label.amount.paid"/></label></td>
						<td class="border"><b><font color="#FFCC00">|</font></b></td>
						<td class="border" width="50%" id="idAmountPaid">0.00</td>			
						<td class="border"><b><font color="#FFCC00">|</font></b></td>
					</tr>
					<tr>
						<td class="border"><b><font color="#FFCC00">|</font></b></td>
						<td class="border" width="50%"><label class="policeGray"><fmt:message key="cashTable.jsp.label.amount.returned"/></label></td>
						<td class="border"><b><font color="#FFCC00">|</font></b></td>
						<td class="border" width="50%" id="idAmountReturned">0.00</td>			
						<td class="border"><b><font color="#FFCC00">|</font></b></td>
					</tr>
					<c:if test="${requestScope.attributesOut.dinnerTable.vats.size!=0}">
						<c:forEach var="vat" items="${requestScope.attributesOut.dinnerTable.vats}">
					<tr>
						<td class="border"><b><font color="#FFCC00">|</font></b></td>
						<td class="border" width="50%">
							<table width="100%">
								<tr>
									<td align="center" width="50%">
										<label class="policeGray"><fmt:message key="cashTable.jsp.label.vat.amount"><fmt:param><c:out value="${vat.key.value}"/></fmt:param></fmt:message></label>
									</td>
									<td align="center" width="50%">
										<fmt:formatNumber pattern="0.00" var="vatAmount" value="${vat.value.amount}"/><c:out value="${fn:replace(vatAmount, ',', '.')}" />
									</td>
								</tr>
							</table>
						</td>
						<td class="border"><b><font color="#FFCC00">|</font></b></td>
						<td class="border" width="50%">
							<table width="100%">
								<tr>
									<td align="center" width="50%">
										<label class="policeGray"><fmt:message key="cashTable.jsp.label.vat.value"><fmt:param><c:out value="${vat.key.value}"/></fmt:param></fmt:message></label>
									</td>
									<td align="center" width="50%">
										<fmt:formatNumber pattern="0.00" var="value" value="${vat.value.value}"/><c:out value="${fn:replace(value, ',', '.')}" />
									</td>
								</tr>
							</table>
						 </td>
						<td class="border"><b><font color="#FFCC00">|</font></b></td>
					</tr>
						</c:forEach>
					</c:if>
					<tr>
						<td class="border"><b><font color="#FFCC00">|</font></b></td>
					   	<td class="border" width="50%"><a accesskey='<fmt:message key="cashTable.jsp.accesskey.cancel"/>' href="javascript:cancelFromDaylyReceipts()" class="cancel"><fmt:message key="label.cancel"/></a></td>
						<td class="border"><b><font color="#FFCC00">|</font></b></td>
						<td class="border" width="50%"><a accesskey='<fmt:message key="cashTable.jsp.accesskey.confirm"/>' href="javascript:submitCashTableFromDaylyReceipts()" class="confirm" ><fmt:message key="label.confirm"/></a></td>
						<td class="border"><b><font color="#FFCC00">|</font></b></td>
					</tr>
					<tr>
						<td class="border"><b><font color="#FFCC00">|</font></b></td>
		    			<td  class="border" colspan="3" width="100%"><a href="javascript:deleteCashing()" title="<fmt:message key="cashTable.jsp.title.delete.cashing"/>"><fmt:message key="cashTable.jsp.label.delete.cashing"/></a></td>
						<td class="border"><b><font color="#FFCC00">|</font></b></td>
	  				</tr>
					<tr>
						<td class="border"><b><font color="#FFCC00">|</font></b></td>
		    			<td  class="border" colspan="3" width="100%"><a href="javascript:displayTableDetails()" id="idDisplayOrHideDetails" title="<fmt:message key="cashTable.jsp.title.table.details"/>"><fmt:message key="cashTable.jsp.label.table.details"/></a></td>
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
				<div id="idDivDetailsTable" style="visibility: hidden">
					<table class="border" width='100%'>
						<tr>
						<td class="border"><b><font color="#FFCC00">|</font></b></td>
							<td class="border" height="5%" colspan="9">
								<table width="100%">
								  <tr>
								    <td style="width: 10%"><label class="policeGray"><fmt:message key="tablesList.label.registration.date"/></label></td>
								    <td style="width: 35%" id='quantitiesSum' class="footer"><fmt:formatDate value="${requestScope.attributesOut.dinnerTable.registrationDate}" type="BOTH" dateStyle="FULL" timeStyle ="SHORT"/></td>
								    <td style="width: 20%; text-align: center"><label class="policeGray"><fmt:message key="tablesList.label.print.date"/></label></td>
								    <td style="width: 10%" id='reduction' class="footer" nowrap><fmt:formatDate value="${requestScope.attributesOut.dinnerTable.printDate}" type="DATE" dateStyle="MEDIUM"/></td>
								    <c:if test="${requestScope.attributesOut.dinnerTable.takeaway}">
								    <td style="width: 25%; text-align: center"><label class="policeGray"><fmt:message key="menuTop.jsp.takeaway"/></label></td>
								    </c:if>
								    <c:if test="${!requestScope.attributesOut.dinnerTable.takeaway}">
								    <td style="width: 20%; text-align: center"><label class="policeGray"><fmt:message key="menuTop.jsp.number.customer"/></label></td>
								    <td style="width: 5%" id='amountsSum' class="footer"><c:out value="${requestScope.attributesOut.dinnerTable.customersNumber}"/></td>
								    </c:if>
								  </tr>
								</table>
							</td>
							<td class="border"><b><font color="#FFCC00">|</font></b></td>
						</tr>	
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
				<c:if test="${not empty requestScope.attributesOut.dinnerTable.orders}">
					<c:forEach var="orderLine" items="${requestScope.attributesOut.dinnerTable.orders}" varStatus="status">
						<c:if test="${not empty orderLine.product.colorRGB}" >
						<tr onmouseover="this.className='over';" onmouseout="this.className='default';" style='background-color:#<c:out value="${orderLine.product.colorRGB}"/>' ID='<c:out value="${orderLine.id}"/>'>
						</c:if>
						<c:if test="${empty orderLine.product.colorRGB}" >
						<tr onmouseover="this.className='over';" onmouseout="this.className='default';" ID='<c:out value="${orderLine.id}"/>'>
						</c:if>
							<td class="border"><b><font color="#FFCC00">|</font></b></td>
							<td class="quantity" ID='TDQ<c:out value="${status.index}"/>'><fmt:formatNumber pattern="0.0" var="quantity" value="${orderLine.quantity}"/><c:out value="${fn:replace(quantity, ',', '.')}" /></td>
							<td class="border"><b><font color="#FFCC00">|</font></b></td>
							<td class="code" ID='TDC<c:out value="${status.index}"/>'><c:out value="${orderLine.product.id}"/><c:if test="${not empty orderLine.specialCodeValue}" ><c:out value="${orderLine.specialCodeValue}"/></c:if></td>
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
						</c:forEach>
					</c:if>
						<tr>
							<td class="border"><b><font color="#FFCC00">|</font></b></td>
							<td class="border" height="5%" colspan="9">
								<table width="100%">
								  <tr>
								    <td style="width: 11%"><label class="policeGray"><fmt:message key="tableOrdersIFrame.jsp.label.quantites.sum"/></label></td>
								    <td style="width: 5%" id='quantitiesSum' class="footer"><fmt:formatNumber pattern="0.0" var="quantitiesSum" value="${requestScope.attributesOut.dinnerTable.quantitiesSum}"/><c:out value="${fn:replace(quantitiesSum, ',', '.')}" /></td>
								    <td style="width: 11%; text-align: center"><label class="policeGray"><fmt:message key="tableOrdersIFrame.jsp.label.reduction.ratio"/></label></td>
								    <td style="width: 5%" class="footer">
									    <c:if test="${!requestScope.attributesOut.dinnerTable.reductionRatioChanged}">
									    	<input disabled id='idReductionRatio' name='reductionRatio' class="reductionRatioYellow" type='text' onclick='document.forms[0].cash.focus()' onfocus='this.value = this.value.toUpperCase();' onblur='window.IFrameData.resetOldReductionRatioValue()' maxlength='7' value='<fmt:formatNumber pattern="0.00" var="reductionRatio" value="${requestScope.attributesOut.dinnerTable.reductionRatio}"/><c:out value="${fn:replace(reductionRatio, ',', '.')}" />'>
								    	</c:if>
									    <c:if test="${requestScope.attributesOut.dinnerTable.reductionRatioChanged}">
									    	<input disabled id='idReductionRatio' name='reductionRatio' class="reductionRatioRed" type='text' onclick='document.forms[0].cash.focus()' onfocus='this.value = this.value.toUpperCase();' onblur='window.IFrameData.resetOldReductionRatioValue()' maxlength='7' value='<fmt:formatNumber pattern="0.00" var="reductionRatio" value="${requestScope.attributesOut.dinnerTable.reductionRatio}"/><c:out value="${fn:replace(reductionRatio, ',', '.')}" />'>
								    	</c:if>							    	
								    </td>
								    <td style="width: 8%; text-align: center"><label class="policeGray"><fmt:message key="tableOrdersIFrame.jsp.label.reduction"/></label></td>
								    <td style="width: 8%" id='reduction' class="footer" nowrap><c:if test="${requestScope.attributesOut.dinnerTable.reduction!=0}"><fmt:formatNumber pattern="0.00" var="reduction" value="${-requestScope.attributesOut.dinnerTable.reduction}"/><c:out value="${fn:replace(reduction, ',', '.')}" /></c:if><c:if test="${requestScope.attributesOut.dinnerTable.reduction==0}"><fmt:formatNumber pattern="0.00" var="reduction" value="${requestScope.attributesOut.dinnerTable.reduction}"/><c:out value="${fn:replace(reduction, ',', '.')}" /></c:if></td>
								    <td style="width: 10%; text-align: center"><label class="policeGray"><fmt:message key="tableOrdersIFrame.jsp.label.amounts.sum"/></label></td>
								    <td style="width: 10%" id='amountsSum' class="footer"><fmt:formatNumber pattern="0.00" var="amountsSum" value="${requestScope.attributesOut.dinnerTable.amountsSum}"/><c:out value="${fn:replace(amountsSum, ',', '.')}" /></td>
								    <td style="width: 10%; text-align: center"><label class="policeGray"><fmt:message key="tableOrdersIFrame.jsp.label.amount.pay"/></label></td>
								    <td style="width: 10%" id='amountPay' class="footer"><fmt:formatNumber pattern="0.00" var="amountPay" value="${requestScope.attributesOut.dinnerTable.amountPay}"/><c:out value="${fn:replace(amountPay, ',', '.')}" /></td>
								  </tr>
								</table>
							</td>
							<td class="border"><b><font color="#FFCC00">|</font></b></td>
						</tr>	
					</table>
				</div>
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
			</html:form>
		<form name="modifyTableForm" action="<c:out value="${pageContext.request.contextPath}"/>/DaylyReceiptsListIFrame.do" method="post" onsubmit="return false" target="IFrameData">
			<input type="hidden" name="pageRequested" value="successPrintTableByCashing">
			<input type="hidden" name='tableId'>
			<input type="hidden" name='cashingId' value="<c:out value="${requestScope.attributesOut.id}"/>">		
			<input type="hidden" name='optionSelected' value='<c:out value="${param.optionSelected}"/>'>
			<input type="hidden" name='filterList' value='<c:out value="${param.filterList}"/>'>
			<input type="hidden" name='sortListBy' value='<c:out value="${param.sortListBy}"/>'>
			<input type="hidden" name='sortMonotony' value='<c:out value="${param.sortMonotony}"/>'>
		</form>
		<form name="alertMessagesForm" onsubmit="return false">
			<input type="hidden" name="cashTableJspAlertAmountPaid" value="<fmt:message key="cashTable.jsp.alert.amount.paid"/>">
			<input type="hidden" name="cashTableJspAlertAmountReturned" value="<fmt:message key="cashTable.jsp.alert.amount.returned"/>">
			<input type="hidden" name="cashTableJspAlertUnpaidOptionForbidden" value="<fmt:message key="cashTable.jsp.alert.unpaid.option.forbidden"/>">
			<input type="hidden" name="labelTableDetails" value="<fmt:message key="cashTable.jsp.label.table.details"/>">		
			<input type="hidden" name="labelHideDetails" value="<fmt:message key="cashTable.jsp.label.hide.details"/>">		
		</form>
		<jsp:include page='/jsp/commons/includeDivIFrame.jsp'/>			
	</body>
</html>