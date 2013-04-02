<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/fn.tld" prefix="fn" %>

<jsp:include page='/jsp/commons/checkSessionAuthorization.jsp'/>

<html>
	<head> 
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" >
		<link rel="stylesheet" type="text/css" href="<c:out value="${pageContext.request.contextPath}"/>/css/montagnesdor.css" />
		<title><fmt:message key="montagnesdor.welcome"/> - <fmt:message key="statsConsumptionProductsListIFrame.jsp.title"/></title>
		<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}"/>/javascript/commons.js"></script>
		<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}"/>/javascript/menus.js"></script>
		<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}"/>/javascript/statsConsumptionProductsList.js"></script>
	</head>
	<body id="idBody" onload='initPageIFrame()'>
		<script language="javascript">
			//Cette variable est utilisée dans le fichier statsConsumptionProductsListIFrame.jsp
			var contextPath = '<c:out value="${pageContext.request.contextPath}"/>';
		</script>
		<table class="border" width='100%'>
		<c:set var="numberStatsConsumptionProducts" value="0"/>
		<c:set var="quantities" value="0"/>
		<c:set var="prices" value="0"/>
		<c:if test="${not empty param.selectBy && param.selectBy=='ALL'}">
			<tr class='over'> 
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
	    		<td class="border" colspan="9" style="width:  100%;">
					<fmt:message key="searchStatsConsumptionProductsListIFrame.jsp.product"/>
				</td>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
			</tr>
		</c:if>
		<c:if test="${not empty param.selectBy && param.selectBy=='NULL'}">
			<tr class='over'> 
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
	    		<td class="border" colspan="9" style="width:  100%;">
					<fmt:message key="searchStatsConsumptionProductsListIFrame.jsp.product.quantity.null"/>
				</td>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
			</tr>
		</c:if>
		<c:if test="${not empty param.selectBy && param.selectBy=='DELETED'}">
			<tr class='over'> 
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
	    		<td class="border" colspan="9" style="width:  100%;">
					<fmt:message key="searchStatsConsumptionProductsListIFrame.jsp.product.deleted"/>
				</td>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
			</tr>
		</c:if>
		<c:if test="${not empty param.selectBy && fn:startsWith(param.selectBy, 'CAT')}">
			<tr class='over'> 
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
	    		<td class="border" colspan="9" style="width:  100%;">
					<fmt:message key="searchStatsConsumptionProductsListIFrame.jsp.product.category">
						<fmt:param>
							<c:if test="${not empty category.labels[sessionScope.userSession.currentLanguage]}">
								<c:out value="${category.labels[sessionScope.userSession.currentLanguage]}"/>
							</c:if>
							<c:if test="${empty category.labels[sessionScope.userSession.currentLanguage]}">
								<c:forEach var="label" items="${category.labels}" varStatus="status">
									<c:if test="${not empty label.value}">
										<c:set var="categoryLabel" value="${label.value}"/>
									</c:if>
								</c:forEach>
								<c:out value="${categoryLabel}"/>
							</c:if>
						</fmt:param>
					</fmt:message>
				</td>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
			</tr>
		</c:if>
		<c:if test="${empty statsConsumptionProductsList}">
			<tr> 
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
	    		<td class="border" colspan="9" style="width:  100%;">
					<fmt:message key="searchStatsConsumptionProductsListIFrame.jsp.product.no.found"/>
				</td>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
			</tr>
		</c:if>

		<c:forEach var="statsConsumptionProduct" items="${statsConsumptionProductsList}" varStatus="status">
			<c:if test="${status.last}">
				<c:set var="numberStatsConsumptionProducts" value="${status.index+1}"/>
			</c:if>
			<c:set var="quantities" value="${quantities+statsConsumptionProduct.quantity}"/>
			<c:set var="prices" value="${prices+statsConsumptionProduct.product.price}"/>
			<c:if test="${not empty statsConsumptionProduct.product.labels}">	
			<tr onmouseover="this.className='over';" onmouseout="this.className='default';" style="background-color: #<c:out value="${statsConsumptionProduct.product.colorRGB}"/>"> 
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
	    		<td class="quantity" style="width: 8%;">
		    		<fmt:formatNumber pattern="0.0" var="quantity" value="${statsConsumptionProduct.quantity}"/><c:out value="${fn:replace(quantity, ',', '.')}" /> 
				</td>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
	    		<td class="border" style="width: 8%;"> 
		    		<c:out value="${statsConsumptionProduct.product.id}"/>
				</td>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
	    		<td class="border" style="width:  42%;">
					<c:out value="${statsConsumptionProduct.product.labels[sessionScope.userSession.currentLanguage]}"/>
				</td>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
	    		<td class="price" style="width: 8%;"><fmt:formatNumber pattern="0.00" var="price" value="${statsConsumptionProduct.product.price}"/><c:out value="${fn:replace(price, ',', '.')}" /></td>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
	    		<td class="border" style="width: 34%;">&nbsp;</td>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
			</tr>
			</c:if>
			<c:if test="${empty statsConsumptionProduct.product.labels}">	
			<tr onmouseover="this.className='over';" onmouseout="this.className='default';"> 
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
	    		<td class="quantity" style="width: 8%;">
		    		<fmt:formatNumber pattern="0.0" var="quantity" value="${statsConsumptionProduct.quantity}"/><c:out value="${fn:replace(quantity, ',', '.')}" /> 
				</td>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
	    		<td class="border" style="width: 8%;"> 
		    		<c:out value="${statsConsumptionProduct.product.id}"/>
				</td>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
	    		<td class="border" style="width: 42%;"><fmt:message key="statsConsumptionProductsListIFrame.jsp.label.product.deleted"/></td>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
	    		<td class="price" style="width: 8%;"><fmt:formatNumber pattern="0.00" var="price" value="${statsConsumptionProduct.product.price}"/><c:out value="${fn:replace(price, ',', '.')}" /></td>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
	    		<td class="border" style="width: 34%;">&nbsp;</td>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
			</tr>
			</c:if>
		</c:forEach>	
		</table>
		<form name="productsListForm" action="<c:out value="${pageContext.request.contextPath}"/>/StatsConsumptionProductsListIFrame.do" method="post" onsubmit="return false" target="IFrameData">
			<input type="hidden" name="day" value='<c:if test="${empty param.day}"><c:out value="-1"/></c:if><c:if test="${not empty param.day}"><c:out value="${param.day}"/></c:if>'>
			<input type="hidden" name='month' value='<c:if test="${empty param.month}"><c:out value="${maxMonth}"/></c:if><c:if test="${not empty param.month}"><c:out value="${param.month}"/></c:if>'>
			<input type="hidden" name='year' value='<c:if test="${empty param.year}"><c:out value="${maxYear}"/></c:if><c:if test="${not empty param.year}"><c:out value="${param.year}"/></c:if>'>
			<input type="hidden" name="sortMonotony" value="<c:out value="${param.sortMonotony}"/>">
			<input type="hidden" name="optionSelected" value="STATS">
		</form>
		<form name="downloadStatsProductsForm" action="<c:out value="${pageContext.request.contextPath}"/>/DownloadStatsConsumptionProductsList.do" method="post" onsubmit="return false" target="_parent">
			<input type="hidden" name="day" value='<c:if test="${empty param.day}"><c:out value="-1"/></c:if><c:if test="${not empty param.day}"><c:out value="${param.day}"/></c:if>'>
			<input type="hidden" name='month' value='<c:if test="${empty param.month}"><c:out value="${maxMonth}"/></c:if><c:if test="${not empty param.month}"><c:out value="${param.month}"/></c:if>'>
			<input type="hidden" name='year' value='<c:if test="${empty param.year}"><c:out value="${maxYear}"/></c:if><c:if test="${not empty param.year}"><c:out value="${param.year}"/></c:if>'>
		</form>
		<form name="menusForm" action="<c:out value="${pageContext.request.contextPath}"/>/ProductsListIFrame.do" method="post" onsubmit="return false">
			<input type="hidden" name="optionSelected" value="STATS">
		</form>
		<form name="alertMessagesForm" onsubmit="return false">
			<input type="hidden" name="statsConsumptionProductsListIFrameConfirmBackup" value="<fmt:message key="statsConsumptionProductsListIFrame.jsp.confirm.backup"/>">
			<input type="hidden" name="statsConsumptionProductsListIFrameConfirmPurge" value="<fmt:message key="statsConsumptionProductsListIFrame.jsp.confirm.purge"/>">
		</form>
		<jsp:include page='/jsp/commons/includeDivIFrame.jsp'/>
		<div style='position: relative; height: 0;visibility:hidden'>
			<table width="100%" height="100%" cellspacing="0" cellpadding="0" border="0" >
				<tr>
					<td id="idTdDataOptions" width="15%" valign="top" title='Data Options'>
						<table class="border" width="95%">
							<c:if test="${empty param.optionSelected || param.optionSelected=='PRODUCTS'}" >
							<tr> 
						    	<td class="selectedWhite">
						    		<a class="selectedWhite" href="javascript:window.IFrameData.processMenusOptions('PRODUCTS');" title="<fmt:message key="productsListIFrame.jsp.title.products"/>"><fmt:message key="productsListIFrame.jsp.list.products"/></a>
						    	</td>
							</tr>
							<tr> 
								<td class="border">
								  <a href="javascript:window.IFrameData.processMenusOptions('CATEGORIES');" title="<fmt:message key="categoriesListIFrame.jsp.title.categories"/>"><fmt:message key="categoriesListIFrame.jsp.list.categories"/></a>
							    </td>
							</tr>
							<tr> 
								<td class="border">
								  <a href="javascript:window.IFrameData.processMenusOptions('STATS');" title="<fmt:message key="statsConsumptionProductsListIFrame.jsp.title.stats"/>"><fmt:message key="statsConsumptionProductsListIFrame.jsp.list.stats"/></a>
							    </td>
							</tr>
							</c:if>
							<c:if test="${not empty param.optionSelected && param.optionSelected=='CATEGORIES'}" >
							<tr> 
						    	<td class="border"> 
							      <a href="javascript:window.IFrameData.processMenusOptions('PRODUCTS');" title="<fmt:message key="productsListIFrame.jsp.title.products"/>"><fmt:message key="productsListIFrame.jsp.list.products"/></a>
							    </td>
							</tr>
							<tr> 
								<td class="selectedWhite">
								  <a class="selectedWhite" href="javascript:window.IFrameData.processMenusOptions('CATEGORIES');" title="<fmt:message key="categoriesListIFrame.jsp.title.categories"/>"><fmt:message key="categoriesListIFrame.jsp.list.categories"/></a>
							    </td>
							</tr>
							<tr> 
								<td class="border">
								  <a href="javascript:window.IFrameData.processMenusOptions('STATS');" title="<fmt:message key="statsConsumptionProductsListIFrame.jsp.title.stats"/>"><fmt:message key="statsConsumptionProductsListIFrame.jsp.list.stats"/></a>
							    </td>
							</tr>
							</c:if>
							<c:if test="${not empty param.optionSelected && param.optionSelected=='STATS'}" >
							<tr> 
						    	<td class="border"> 
							      <a href="javascript:window.IFrameData.processMenusOptions('PRODUCTS');" title="<fmt:message key="productsListIFrame.jsp.title.products"/>"><fmt:message key="productsListIFrame.jsp.list.products"/></a>
							    </td>
							</tr>
							<tr> 
								<td class="border">
								  <a href="javascript:window.IFrameData.processMenusOptions('CATEGORIES');" title="<fmt:message key="categoriesListIFrame.jsp.title.categories"/>"><fmt:message key="categoriesListIFrame.jsp.list.categories"/></a>
							    </td>
							</tr>
							<tr> 
								<td class="selectedWhite">
								  <a class="selectedWhite" href="javascript:window.IFrameData.processMenusOptions('STATS');" title="<fmt:message key="statsConsumptionProductsListIFrame.jsp.title.stats"/>"><fmt:message key="statsConsumptionProductsListIFrame.jsp.list.stats"/></a>
							    </td>
							</tr>
							</c:if>
						</table>
					</td>
				</tr>					
				<tr>
					<td id="idTdDataHeader" height="5%" title='Data Header'>
						<table class="border" width='100%' id="idTableDataHeader">
							<tr>
							    <td class="border"><b><font color="#FFCC00">|</font></b></td>
							    <td class="border" style="width: 8%;">
								    <c:if test="${empty param.selectBy}">
										<a class='linkSelected' href="javascript:window.IFrameData.processList();" title="<fmt:message key="statsConsumptionProductsListIFrame.jsp.order.quantity"/>"><fmt:message key="statsConsumptionProductsListIFrame.jsp.label.quantity"/></a>
									</c:if>
								    <c:if test="${not empty param.selectBy}">
										<label class="policeGray"><fmt:message key="statsConsumptionProductsListIFrame.jsp.label.quantity"/></label>
									</c:if>
							    </td>
							    <td class="border"><b><font color="#FFCC00">|</font></b></td>
							    <td class="border" style="width: 8%;"><label class="policeGray"><fmt:message key="statsConsumptionProductsListIFrame.jsp.label.code"/></label></td>
							    <td class="border"><b><font color="#FFCC00">|</font></b></td>
							    <td class="border" style="width: 42%;"><label class="policeGray"><fmt:message key="statsConsumptionProductsListIFrame.jsp.label.label"/></label></td>
							    <td class="border"><b><font color="#FFCC00">|</font></b></td>
							    <td class="border" style="width: 8%;"><label class="policeGray"><fmt:message key="statsConsumptionProductsListIFrame.jsp.label.price"/></label></td>
								<td class="border"><b><font color="#FFCC00">|</font></b></td>
								<td class="border" width="34%">
									<SELECT name="day" style="width:25%" id="idDay" onchange="javascript:window.IFrameData.changeSelect(this)">
										<c:if test="${minYear!=-1 && maxYear!=-1}">
											<c:forEach var="value" begin="1" end="31">
										<OPTION <c:if test="${param.day==value}">selected</c:if> value="<c:out value="${value}"/>"><fmt:formatNumber value="${value}" type="NUMBER" pattern="00" minIntegerDigits="2" maxFractionDigits="0"/></OPTION>
											</c:forEach>
										</c:if>
										<OPTION <c:if test="${empty param.day || param.day==-1}">selected</c:if> value="-1"><fmt:message key="statsConsumptionProductsListIFrame.jsp.date.all"/></OPTION>
									</SELECT>
									<SELECT name="month" style="width:33%" id="idMonth" onchange="javascript:window.IFrameData.changeSelect(this)">
										<c:if test="${minYear!=-1 && maxYear!=-1}">									
											<c:forEach var="dateForMonth" varStatus="status" items="${dateForMonthList}">
										<OPTION <c:if test="${empty param.month && maxMonth==(status.index)}">selected</c:if> <c:if test="${not empty param.month && param.month==(status.index)}">selected</c:if> value="<c:out value="${status.index}"/>"><fmt:formatDate pattern="MMMM" value="${dateForMonth}" type="DATE"/></OPTION>
											</c:forEach>
										</c:if>
										<OPTION <c:if test="${param.month==-1}">selected</c:if> value="-1"><fmt:message key="statsConsumptionProductsListIFrame.jsp.date.all"/></OPTION>
									</SELECT>
									<SELECT name="year" style="width:25%" id="idYear" onchange="javascript:window.IFrameData.changeSelect(this)">
										<c:if test="${minYear!=-1 && maxYear!=-1}">
											<c:forEach var="value" begin="${minYear}" end="${maxYear}" varStatus="status">
										<OPTION <c:if test="${(empty param.year && maxYear==value)||(param.year==value)}">selected</c:if> value="<c:out value="${value}"/>"><c:out value="${value}"/></OPTION>
											</c:forEach>
										</c:if>
										<OPTION <c:if test="${param.year==-1}">selected</c:if> value="-1"><fmt:message key="statsConsumptionProductsListIFrame.jsp.date.all"/></OPTION>
									</SELECT>
									&nbsp;<label><a href="javascript:window.IFrameData.processList()" title="<fmt:message key="statsConsumptionProductsListIFrame.jsp.title.stats"/>"><fmt:message key="label.ok"/></a></label>
								</td>
								<td class="border"><b><font color="#FFCC00">|</font></b></td>
							</tr>    							
						</table>							
					</td>
				</tr>	
				<tr>
					<td id="idTdDataFooter" height="5%"  title='Data Footer'>
						<table width="100%">
						  <tr>
						    <td style="text-align: center; width:10%"><label class="policeGray"><fmt:message key="productsListIFrame.jsp.products.number"/></label></td>
						    <td class="footer" style="text-align: left; width:10%"><c:out value="${numberStatsConsumptionProducts}" /><c:if test="${not empty pageSize}">/<c:out value="${statsConsumptionProductsSumProducts}"/></c:if></td>
						    <td style="text-align: center; width:10%"><label class="policeGray"><fmt:message key="statsConsumptionProductsListIFrame.jsp.sum.quantities"/></label></td>
						    <td class="footer" style="text-align: left; width:10%"><fmt:formatNumber pattern="0.0" var="auxQuantities" value="${quantities}"/><c:out value="${fn:replace(auxQuantities, ',', '.')}"/><c:if test="${not empty pageSize}">/<fmt:formatNumber pattern="0.0" var="sumQuantities" value="${statsConsumptionProductsSumQuantities}"/><c:out value="${fn:replace(sumQuantities, ',', '.')}"/></c:if></td>
						    <td style="text-align: center; width:10%"><label class="policeGray"><fmt:message key="statsConsumptionProductsListIFrame.jsp.sum.prices"/></label></td>
						    <td class="footer" style="text-align: left; width:10%"><fmt:formatNumber pattern="0.00" var="auxPrices" value="${prices}"/><c:out value="${fn:replace(auxPrices, ',', '.')}"/><c:if test="${not empty pageSize}">/<fmt:formatNumber pattern="0.00" var="sumPrices" value="${statsConsumptionProductsSumPrices}"/><c:out value="${fn:replace(sumPrices, ',', '.')}"/></c:if></td>
						    <td style="text-align: center; width:10%"><label><a href="javascript:window.IFrameData.searchStatsConsumptionProductsList()" title="<fmt:message key="productsListIFrame.jsp.title.search.products"/>"><fmt:message key="productsListIFrame.jsp.label.search.products"/></a></label></td>
						    <td style="text-align: center; width:10%"><label><a href="javascript:window.IFrameData.downloadStatsConsumptionProductsList()" title="<fmt:message key="statsConsumptionProductsListIFrame.jsp.title.backup"/>"><fmt:message key="statsConsumptionProductsListIFrame.jsp.label.backup"/></a></label></td>
						    <td style="text-align: center; width:10%"><label><a href="javascript:window.IFrameData.purgeStatsConsumptionProducts()" title="<fmt:message key="statsConsumptionProductsListIFrame.jsp.title.purge"/>"><fmt:message key="statsConsumptionProductsListIFrame.jsp.label.purge"/></a></label></td>
						  </tr>
						</table>
					</td>
				</tr>	
			</table>			
		</div>
	</body>
</html>