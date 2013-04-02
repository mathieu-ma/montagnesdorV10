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
		<title><fmt:message key="montagnesdor.welcome"/> - <fmt:message key="statsConsumptionProductsListIFrame.jsp.title.purge"/></title>
		<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}"/>/javascript/commons.js"></script>
		<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}"/>/javascript/menus.js"></script>
		<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}"/>/javascript/statsConsumptionProductsList.js"></script>
		<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}"/>/javascript/searchStatsConsumptionProducts.js"></script>
	</head>
	<body onload='initPage()'>
		<script language="javascript">
			//Cette variable est utilisée dans les fichiers purgeStatsConsumptionProducts.js et menus.js
			var contextPath = '<c:out value="${pageContext.request.contextPath}"/>';
		</script>
	
		<html:form method="post" action='/SearchStatsConsumptionProducts.do' onsubmit="return false">
			<table class="border" width="100%" align="center">
				<tr>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
					<td colspan=5 class="border" width="100%"><label class="policeGray"><fmt:message key="searchStatsConsumptionProductsListIFrame.jsp.title"/></label></td>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
				</tr>	
				<tr>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
					<td class="border" width="33%"><fmt:message key="statsConsumptionProductsListIFrame.jsp.label.date"/></td>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
					<td class="border" width="33%"><fmt:message key="statsConsumptionProductsListIFrame.jsp.label.month"/></td>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
					<td class="border" width="33%"><fmt:message key="statsConsumptionProductsListIFrame.jsp.label.year"/></td>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
				</tr>	
				<tr>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
					<td class="border" width="33%">
						<SELECT name="day" onchange="document.forms[0].code.focus();">
							<c:if test="${minYear!=-1 && maxYear!=-1}">
								<c:forEach var="value" begin="1" end="31">
							<OPTION <c:if test="${param.day==value}">selected</c:if> value="<c:out value="${value}"/>"><fmt:formatNumber value="${value}" type="NUMBER" pattern="00" minIntegerDigits="2" maxFractionDigits="0"/></OPTION>
								</c:forEach>
							</c:if>
							<OPTION <c:if test="${empty param.day || param.day==-1}">selected</c:if> value="-1"><fmt:message key="statsConsumptionProductsListIFrame.jsp.date.all"/></OPTION>
						</SELECT>
					</td>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
					<td class="border" width="33%">
						<SELECT name="month" onchange="document.forms[0].code.focus();">
							<c:if test="${minYear!=-1 && maxYear!=-1}">									
								<c:forEach var="dateForMonth" varStatus="status" items="${dateForMonthList}">
							<OPTION <c:if test="${empty param.month && maxMonth==(status.index)}">selected</c:if> <c:if test="${not empty param.month && param.month==(status.index)}">selected</c:if> value="<c:out value="${status.index}"/>"><fmt:formatDate pattern="MMMM" value="${dateForMonth}" type="DATE"/></OPTION>
								</c:forEach>
							</c:if>
							<OPTION <c:if test="${param.month==-1}">selected</c:if> value="-1"><fmt:message key="statsConsumptionProductsListIFrame.jsp.date.all"/></OPTION>
						</SELECT>
					</td>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
					<td class="border" width="33%">
						<SELECT name="year" onchange="document.forms[0].code.focus();">
							<c:if test="${minYear!=-1 && maxYear!=-1}">
								<c:forEach var="value" begin="${minYear}" end="${maxYear}" varStatus="status">
							<OPTION <c:if test="${(empty param.year && maxYear==value)||(param.year==value)}">selected</c:if> value="<c:out value="${value}"/>"><c:out value="${value}"/></OPTION>
								</c:forEach>
							</c:if>
							<OPTION <c:if test="${param.year==-1}">selected</c:if> value="-1"><fmt:message key="statsConsumptionProductsListIFrame.jsp.date.all"/></OPTION>
						</SELECT>
					</td>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
				</tr>
				<tr>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
					<td class="border" width="33%" colspan="3"><fmt:message key="searchStatsConsumptionProductsListIFrame.jsp.select.product.by"/></td>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
					<td class="border" width="66%">
						<SELECT name="selectBy" onchange="document.forms[0].code.focus();">
							<OPTION selected value="ALL"><fmt:message key="statsConsumptionProductsListIFrame.jsp.date.all"/></OPTION>
							<OPTION value="NULL"><fmt:message key="searchStatsConsumptionProductsListIFrame.jsp.select.product.quantity.null"/></OPTION>
							<OPTION value="DELETED"><fmt:message key="searchStatsConsumptionProductsListIFrame.jsp.select.product.deleted"/></OPTION>
						    <c:if test="${not empty categoriesList}" >
					 		<OPTGROUP label="<fmt:message key="searchStatsConsumptionProductsListIFrame.jsp.select.categories"/>">
								<c:forEach var="category" items="${categoriesList}">
									<OPTION value="CAT<c:out value="${category.id}"/>">
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
									</OPTION>
								</c:forEach>
					 		</OPTGROUP>							
							</c:if >
						</SELECT>
					</td>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
				</tr>	
				<tr>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
					<td class="border" width="33%" colspan="3"><fmt:message key="statsConsumptionProductsListIFrame.jsp.label.code"/></td>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
					<td class="border" width="66%"><input type="text" name="code" class="code" style="width:95%" onkeyup='processUserEntry(event, this)' maxlength="16"></td>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
				</tr>	
				<tr>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
					<td class="border" width="33%" colspan="3"><fmt:message key="statsConsumptionProductsListIFrame.jsp.label.quantity"/></td>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
					<td class="border" width="66%">
						<input type="radio" onclick="javascript:document.forms[0].quantity.focus();" name="quantityOperator" value="<fmt:message key="searchStatsConsumptionProductsListIFrame.jsp.quantity.operator.eq"/>"> <fmt:message key="searchStatsConsumptionProductsListIFrame.jsp.quantity.operator.eq"/> &nbsp;
						<input type="radio" onclick="javascript:document.forms[0].quantity.focus();" name="quantityOperator" checked value="<fmt:message key="searchStatsConsumptionProductsListIFrame.jsp.quantity.operator.gt"/>"> <fmt:message key="searchStatsConsumptionProductsListIFrame.jsp.quantity.operator.gt"/> &nbsp;
						<input type="radio" onclick="javascript:document.forms[0].quantity.focus();" name="quantityOperator" value="<fmt:message key="searchStatsConsumptionProductsListIFrame.jsp.quantity.operator.lt"/>"> <fmt:message key="searchStatsConsumptionProductsListIFrame.jsp.quantity.operator.lt"/> &nbsp;						
						<input type="text" name="quantity" class="quantity" style="width:40%" onkeyup='processUserEntry(event, this)' maxlength="16">
					</td>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
				</tr>	
				<tr>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
				   	<td class="border" width="66%" colspan="3"><a accesskey='<fmt:message key="accesskey.cancel"/>' href="javascript:cancel()" class="cancel"><fmt:message key="label.cancel"/></a></td>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
					<td class="border" width="34%"><a accesskey='<fmt:message key="accesskey.confirm"/>' href="javascript:search()" class="confirm" ><fmt:message key="label.confirm"/></a></td>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
				</tr>		
				<logic:messagesPresent>
				<tr>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
					<td class="border" colspan="5">
						<label class="policeGray">
							<html:errors property="searchStatsConsumptionProductsListIFrame.jsp.quantity.operator.undefined"/>
							<html:errors property="searchStatsConsumptionProductsListIFrame.jsp.select.undefined"/>
						</label>
					</td>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
				</tr>	
				</logic:messagesPresent>				
			</table>
			<input type="hidden" name="actionDo" value="PURGE">
			<input type="hidden" name="optionSelected" value="STATS">
		</html:form>
		<form name="menusForm" action="<c:out value="${pageContext.request.contextPath}"/>/ProductsListIFrame.do" method="post" onsubmit="return false">
			<input type="hidden" name="optionSelected" value="STATS">
		</form>
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
		<jsp:include page='/jsp/commons/includeDivIFrame.jsp'/>
	</body>
</html>