<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/fn.tld" prefix="fn" %>

<jsp:include page='/jsp/commons/checkSessionAuthorization.jsp'/>

<html>
	<head> 
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" >
		<link rel="stylesheet" type="text/css" href="<c:out value="${pageContext.request.contextPath}"/>/css/montagnesdor.css" />
		<title><fmt:message key="montagnesdor.welcome"/> - <fmt:message key="tablesList.title"/></title>
		<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}"/>/javascript/commons.js"></script>
		<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}"/>/javascript/tablesListExcludeCashedTable.js"></script>
	</head>
	<body id="idBody" onload='initPageIFrame()'>
	<script language="javascript">
		//Cette variable est utilisée dans le fichier tableOrders.js
		var contextPath = '<c:out value="${pageContext.request.contextPath}"/>';
	</script>
	<c:set var="numberTables" value="0"/>
	<c:set var="numberCustomers" value="0"/>
	<c:set var="sumQuantities" value="0"/>
	<c:set var="sumAmounts" value="0"/>
	<table class="border" width='100%'>
		<logic:messagesPresent>
			<tr onmouseover="this.className='over';" onmouseout="this.className='default';"> 
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
	    		<td class="border" colspan="13"> 
					<fmt:message key="tablesList.error.import.takeaway.orders"/><br/>
				      <ul id="errors">
				         <html:messages id="msg">
				            <li>${msg}</li>
				         </html:messages>
				      </ul>
   				</td>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
			</tr>	
		</logic:messagesPresent>
	<c:if test="${not empty tablesList}">
		<c:forEach var="dinnerTable" items="${tablesList}" varStatus="status">
			<c:if test="${status.last}" >
				<c:set var="numberTables" value="${status.index+1}"/>
			</c:if>	
			<c:set var="numberCustomers" value="${numberCustomers+dinnerTable.customersNumber}"/>
			<c:set var="sumQuantities" value="${sumQuantities+dinnerTable.quantitiesSum}"/>
			<c:set var="sumAmounts" value="${sumAmounts+dinnerTable.amountPay}"/>
			<tr onmouseover="this.className='over';" onmouseout="this.className='default';"> 
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
	    		<td class="border" style="width: 9%;"> 
	    			<a href="javascript:processUserClick('orderTable', '<c:out value="${dinnerTable.id}"/>', '<c:out value="${dinnerTable.number}"/>')" title="<fmt:message key="tablesList.display.table"><fmt:param><c:out value="${dinnerTable.number}"/></fmt:param></fmt:message>"><c:out value="${dinnerTable.number}"/></a>
				</td>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
				<td class="border" style="width: 9%;">
					<c:if test="${!dinnerTable.takeaway}">
				    	<c:out value="${dinnerTable.customersNumber}"/>
			    	</c:if>
					<c:if test="${dinnerTable.takeaway}">
			    		<fmt:message key="menuTop.jsp.takeaway"/>
			    	</c:if>
		    	</td>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
				<td class="quantity" style="width: 9%;"><fmt:formatNumber pattern="0.0" var="quantitiesSum" value="${dinnerTable.quantitiesSum}"/><c:out value="${fn:replace(quantitiesSum, ',', '.')}" /></td>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
				<td class="amount" style="width: 9%;"><fmt:formatNumber pattern="0.00" var="amountPay" value="${dinnerTable.amountPay}"/><c:out value="${fn:replace(amountPay, ',', '.')}" /></td>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
		    	<td class="border" style="width: 19%;"><fmt:formatDate value="${dinnerTable.registrationDate}" type="BOTH" dateStyle="FULL" timeStyle ="MEDIUM"/></td>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
		    	<td class="border" style="width: 18%;">
					<c:if test="${not empty dinnerTable.printDate}">
						<fmt:formatDate value="${dinnerTable.printDate}" type="DATE" dateStyle="MEDIUM"/><br>
						<a href="javascript:processUserClick('cashTable', '<c:out value="${dinnerTable.id}"/>', '<c:out value="${dinnerTable.number}"/>')" title="<fmt:message key="tableOrdersIFrame.jsp.title.cash"><fmt:param><c:out value="${dinnerTable.number}"/></fmt:param></fmt:message>"><fmt:message key="tableOrdersIFrame.jsp.label.cash"/></a>
			    	</c:if>
					<c:if test="${empty dinnerTable.printDate}">
						<a href="javascript:processUserClick('printTable', '<c:out value="${dinnerTable.id}"/>', '<c:out value="${dinnerTable.number}"/>')" title="<fmt:message key="tableOrdersIFrame.jsp.title.print"><fmt:param><c:out value="${dinnerTable.number}"/></fmt:param></fmt:message>"><fmt:message key="tableOrdersIFrame.jsp.label.print"/></a>
			    	</c:if>
		    	</td>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
				<td class="border" style="width: 27%;">
					<c:if test="${not empty dinnerTable.printDate}">
						<!--label class="policeGray"><fmt:message key="tableOrdersIFrame.jsp.label.remove"/></label-->
						<a href="javascript:processUserClick('changeTable', '<c:out value="${dinnerTable.id}"/>', '<c:out value="${dinnerTable.number}"/>')" title="<fmt:message key="tableOrdersIFrame.jsp.title.change"><fmt:param><c:out value="${dinnerTable.number}"/></fmt:param></fmt:message>"><fmt:message key="tableOrdersIFrame.jsp.label.change"/></a>
						<label class="policeGray"><fmt:message key="tableOrdersIFrame.jsp.label.merge"/></label>
			    	</c:if>
					<c:if test="${empty dinnerTable.printDate}">
						<!--a href="javascript:processUserClick('deleteTable', '<c:out value="${dinnerTable.id}"/>', '<c:out value="${dinnerTable.number}"/>')" title="<fmt:message key="tableOrdersIFrame.jsp.title.remove"><fmt:param><c:out value="${dinnerTable.number}"/></fmt:param></fmt:message>"><fmt:message key="tableOrdersIFrame.jsp.label.remove"/></a-->
						<a href="javascript:processUserClick('changeTable', '<c:out value="${dinnerTable.id}"/>', '<c:out value="${dinnerTable.number}"/>')" title="<fmt:message key="tableOrdersIFrame.jsp.title.change"><fmt:param><c:out value="${dinnerTable.number}"/></fmt:param></fmt:message>"><fmt:message key="tableOrdersIFrame.jsp.label.change"/></a>
						<a href="javascript:processUserClick('mergeTable', '<c:out value="${dinnerTable.id}"/>', '<c:out value="${dinnerTable.number}"/>')" title="<fmt:message key="tableOrdersIFrame.jsp.title.merge"><fmt:param><c:out value="${dinnerTable.number}"/></fmt:param></fmt:message>"><fmt:message key="tableOrdersIFrame.jsp.label.merge"/></a>
			    	</c:if>
				</td>					
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
			</tr>
		</c:forEach>	
	</c:if>
	</table>
	<form name="modifyTableForm" action="<c:out value="${pageContext.request.contextPath}"/>/DisplayJsp.do" method="post" onsubmit="return false" target="IFrameData">
		<input type="hidden" name="pageRequested" value="successMergeTable">
		<input type="hidden" name='tableId'>
		<input type="hidden" name='filterList'>
		<input type="hidden" name='sortListBy' value='<c:if test="${not empty sortListBy}"><c:out value="${sortListBy}"/></c:if>'>
		<input type="hidden" name='sortMonotony' value='<c:if test="${not empty sortMonotony}"><c:out value="${sortMonotony}"/></c:if>'>
	</form>
	<jsp:include page='/jsp/commons/includeDivIFrame.jsp'/>
		<div style='position: relative; height: 0;visibility:hidden'>
			<table width="90%" height="100%" cellspacing="0" cellpadding="0" border="0" >
				<tr>
					<td id="idTdDataOptions" width="15%" valign="top" title='Data Options'>
						<!-- table class="border" width="95%">
							<tr>
						    	<td class="border"> 
							      <a href="javascript:window.IFrameData.importTakeawayTables();" title="<fmt:message key="tablesList.title.import.takeaway.table" />"><fmt:message key="tablesList.title.import.takeaway.table" /></a>
							    </td>
							</tr>
						</table-->
					</td>
				</tr>					
				<tr>
					<td id="idTdDataHeader" height="5%" title='Data Header'>
						<table class="border" width='100%' id="idTableDataHeader">
							<tr>
							    <td class="border"><b><font color="#FFCC00">|</font></b></td>
							    <td class="border" style="width: 9%;"><a <c:if test="${sortListBy=='number'}">class='linkSelected'</c:if> href="javascript:window.IFrameData.processList('number');" title="<fmt:message key="tablesList.title.table"/>"><fmt:message key="tablesList.label.table"/></a></td>
							    <td class="border"><b><font color="#FFCC00">|</font></b></td>
							    <td class="border" style="width: 9%;"><a <c:if test="${sortListBy=='customersNumber'}">class='linkSelected'</c:if> href="javascript:window.IFrameData.processList('customersNumber');" title="<fmt:message key="tablesList.title.customers"/>"><fmt:message key="tablesList.label.customers"/></a></td>
							    <td class="border"><b><font color="#FFCC00">|</font></b></td>
							    <td class="border" style="width: 9%;"><a <c:if test="${sortListBy=='quantitiesSum'}">class='linkSelected'</c:if> href="javascript:window.IFrameData.processList('quantitiesSum');" title="<fmt:message key="tablesList.title.quantities"/>"><fmt:message key="tablesList.label.quantities"/></a></td>
							    <td class="border"><b><font color="#FFCC00">|</font></b></td>
							    <td class="border" style="width: 9%;"><a <c:if test="${sortListBy=='amountPay'}">class='linkSelected'</c:if> href="javascript:window.IFrameData.processList('amountPay');" title="<fmt:message key="tablesList.title.amounts"/>"><fmt:message key="tablesList.label.amounts"/></a></td>
							    <td class="border"><b><font color="#FFCC00">|</font></b></td>
							    <td class="border" style="width: 19%;"><a <c:if test="${sortListBy=='registrationDate'}">class='linkSelected'</c:if> href="javascript:window.IFrameData.processList('registrationDate');" title="<fmt:message key="tablesList.title.registration.date"/>"><fmt:message key="tablesList.label.registration.date"/></a></td>
							    <td class="border"><b><font color="#FFCC00">|</font></b></td>
							    <td class="border" style="width: 18%;"><a <c:if test="${sortListBy=='printDate'}">class='linkSelected'</c:if> href="javascript:window.IFrameData.processList('printDate');" title="<fmt:message key="tablesList.title.print.date"/>"><fmt:message key="tablesList.label.print.date"/></a></td>
							    <td class="border"><b><font color="#FFCC00">|</font></b></td>
							    <td class="border" style="width: 27%;">
							 		<SELECT id='idFilterList' style="width:100%" name="filterList" onchange="javascript:window.IFrameData.processSelect(this)">
										<OPTION <c:if test="${filterList=='0'}">selected</c:if> value="0"><fmt:message key="tablesList.label.list.tables.all"/></OPTION>
										<OPTION <c:if test="${filterList=='1'}">selected</c:if> value="1"><fmt:message key="tablesList.label.list.tables.print"/></OPTION>
										<OPTION <c:if test="${filterList=='2'}">selected</c:if> value="2"><fmt:message key="tablesList.label.list.tables.cash"/></OPTION>
										<OPTION <c:if test="${filterList=='3'}">selected</c:if> value="3"><fmt:message key="tablesList.label.list.tables.takeaway"/></OPTION>
										<OPTION <c:if test="${filterList=='4'}">selected</c:if> value="4"><fmt:message key="tablesList.label.list.tables.inplace"/></OPTION>
										<OPTION <c:if test="${filterList=='5'}">selected</c:if> value="5"><fmt:message key="tablesList.label.list.tables.imported"/></OPTION>
		 							</SELECT>
								</td>
							    <td class="border"><b><font color="#FFCC00">|</font></b></td>
							</tr>
						</table>
					</td>
				</tr>	
				<tr>
					<td id="idTdDataFooter" height="5%" title='Data Footer'>
						<div onmouseover="window.parent.document.getElementById('idTableDataFooter').style.display='block'" onmouseout="window.parent.document.getElementById('idTableDataFooter').style.display='none'">
							<table width="100%">
							  <tr class="pointer">
							  	<td><label class="policeGray">&nbsp;<br><br><br></label></td>
							  	<td width="100%">
									<table width="100%" id="idTableDataFooter" style="display:none">
									  <tr>
									    <td width="12%"><label class="policeGray"><fmt:message key="tablesList.label.number.tables"/></label></td>
									    <td class="footer"><c:out value="${numberTables}" /></td>
									    <td width="0.5%"></td>
									    <td width="15%"><label class="policeGray"><fmt:message key="tablesList.label.number.customers"/></label></td>
									    <td class="footer"><c:out value="${numberCustomers}"/></td>
									    <td width="0.5%"></td>
									    <td width="12%"><label class="policeGray"><fmt:message key="tablesList.label.sum.quantities"/></label></td>
									    <td class="footer"><fmt:formatNumber pattern="0.00" var="sumQuantities" value="${sumQuantities}"/><c:out value="${fn:replace(sumQuantities, ',', '.')}" /></td>
									    <td width="0.5%"></td>
									    <td width="12%"><label class="policeGray"><fmt:message key="tablesList.label.sum.amounts"/></label></td>
									    <td class="footer"><fmt:formatNumber pattern="0.00" var="sumAmounts" value="${sumAmounts}"/><c:out value="${fn:replace(sumAmounts, ',', '.')}" /></td>
									  </tr>   
									</table>	 
							    </td>
							  </tr>
							</table>
						</div>
					</td>
				</tr>	
			</table>			
		</div>
	</body>
</html>