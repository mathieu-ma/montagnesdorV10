<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/fn.tld" prefix="fn" %>

<jsp:include page='/jsp/commons/checkSessionAuthorization.jsp'/>

<html>
	<head> 
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" >
		<link rel="stylesheet" type="text/css" href="<c:out value="${pageContext.request.contextPath}"/>/css/montagnesdor.css" />
		<title><fmt:message key="montagnesdor.welcome"/> - <fmt:message key="categoriesListIFrame.jsp.title"/></title>
		<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}"/>/javascript/commons.js"></script>
		<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}"/>/javascript/menus.js"></script>
		<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}"/>/javascript/categoriesList.js"></script>
	</head>
	<body id="idBody" onload='initPageIFrame()'>
		<script language="javascript">
			//Cette variable est utilisée dans le fichier productsList.js
			var contextPath = '<c:out value="${pageContext.request.contextPath}"/>';
		</script>
		<table class="border" width='100%'>
		<c:set var="numberCategories" value="0"/>
		<c:if test="${not empty categoriesList}">
			<c:forEach var="category" items="${categoriesList}" varStatus="status">
				<c:if test="${status.last}" >
					<c:set var="numberCategories" value="${status.index+1}"/>
				</c:if>	
				<tr onmouseover="this.className='over';" onmouseout="this.className='default';"> 
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
		    		<td class="border" style="width: 20%;"> 
			    		<a href="javascript:processUserClick('DISPLAY', '<c:out value="${category.id}"/>')" title="<fmt:message key="categoriesListIFrame.jsp.title.modify"><fmt:param><c:out value="${category.id}"/></fmt:param></fmt:message>"><c:out value="${category.id}"/></a>
					</td>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
		    		<td class="border" style="width: 55%;">
					<c:if test="${not empty category.labels[sessionScope.userSession.currentLanguage]}">
						<c:out value="${category.labels[sessionScope.userSession.currentLanguage]}"/>
					</c:if>
					</td>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
		    		<td class="border" style="width: 25%;"> 
						<a href="javascript:processUserClick('DELETE', '<c:out value="${category.id}"/>')" title="<fmt:message key="categoriesListIFrame.jsp.title.remove"><fmt:param><c:out value="${category.id}"/></fmt:param></fmt:message>"><fmt:message key="categoriesListIFrame.jsp.label.remove"/></a>
					</td>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
				</tr>
			</c:forEach>	
		</c:if>
		</table>
		<form name="categoriesListForm" action="<c:out value="${pageContext.request.contextPath}"/>/SaveOrUpdateCategory.do" method="post" onsubmit="return false">
			<input type="hidden" name="actionDo" value="DISPLAY">
			<input type="hidden" name="optionSelected" value="CATEGORIES">
			<input type="hidden" name="id">
		</form>
		<form name="menusForm" action="<c:out value="${pageContext.request.contextPath}"/>/ProductsListIFrame.do" method="post" onsubmit="return false">
			<input type="hidden" name="optionSelected" value="CATEGORIES">
		</form>
		<form name="alertMessagesForm" onsubmit="return false">
			<input type="hidden" name="categoriesListIFrameConfirmDeleteCategory" value="<fmt:message key="categoriesListIFrame.jsp.confirm.delete.category"/>">
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
					<td  id="idTdDataHeader" height="5%" title='Data Header'>
						<table class="border" width='100%' id="idTableDataHeader">
							<tr>
								<td class="border"><b><font color="#FFCC00">|</font></b></td>
		    					<td class="border" style="width: 20%;">
			    					<label class="policeGray"><fmt:message key="categoriesListIFrame.jsp.label.id"/></label>
								</td>
								<td class="border"><b><font color="#FFCC00">|</font></b></td>
					    		<td class="border" style="width: 55%;">
						    		<label class="policeGray"><fmt:message key="categoriesListIFrame.jsp.label.label"/></label>
								</td>
								<td class="border"><b><font color="#FFCC00">|</font></b></td>
		    					<td class="border" style="width: 25%;"> 
									<label class="policeGray"><fmt:message key="categoriesListIFrame.jsp.label.action"/></label>
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
						    <td style="text-align: left; width:5%"><label class="policeGray"><fmt:message key="categoriesListIFrame.jsp.categories.number"/></label></td>
						    <td class="footer" style="text-align: left; width:1%"><c:out value="${numberCategories}"/></td>
						    <td style="text-align: center; width:15%"><label><a href="javascript:window.IFrameData.processUserClick('DISPLAY', '')" title="<fmt:message key="categoriesListIFrame.jsp.title.add"/>"><fmt:message key="categoriesListIFrame.jsp.label.add"/></a></label></td>
						  </tr>
						</table>
					</td>
				</tr>	
			</table>			
		</div>
	</body>
</html>