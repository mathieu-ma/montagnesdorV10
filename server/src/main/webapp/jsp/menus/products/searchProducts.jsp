<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt" %>

<jsp:include page='/jsp/commons/checkSessionAuthorization.jsp'/>

<html>
	<head> 
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" >
		<link rel="stylesheet" type="text/css" href="<c:out value="${pageContext.request.contextPath}"/>/css/montagnesdor.css" />
		<title><fmt:message key="montagnesdor.welcome"/> - <fmt:message key="searchProducts.jsp.title"/></title>
		<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}"/>/javascript/commons.js"></script>
		<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}"/>/javascript/menus.js"></script>
		<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}"/>/javascript/productsList.js"></script>		
		<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}"/>/javascript/searchProducts.js"></script>
	</head>
	<body id="idBody" onload='initPage()'>
		<script language="javascript">
			//Cette variable est utilisée dans le fichier searchProducts.js
			var contextPath = '<c:out value="${pageContext.request.contextPath}"/>';
		</script>
		<form method="post" action="<c:out value="${pageContext.request.contextPath}"/>/SearchProductsList.do" onsubmit="javascript:return false" accept-charset="UTF-8">
			<table class="border" width="100%" align="center">
				<tr>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>						
					<td colspan=3 class="border" width="100%"><label class="policeGray"><fmt:message key="searchProducts.jsp.title"/></label></td>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
				</tr>	
				<tr>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
					<td class="border" width="50%"><label class="policeGray"><fmt:message key="searchProducts.jsp.label.search"/></label></td>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
					<td class="border" width="50%"><input class="text" type="text" style="width: 100%" name="searchedValue" onkeyup='processUserEntry(event, this)' maxlength="23"></td>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
				</tr>
				<tr>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
					<td class="border" colspan="3">
						<table width="100%">
							<tr>
								<td style="text-align: center"><input type="radio" name="searchedField" onclick="document.forms[0].searchedValue.focus()" value="id"><label class="policeGray"><fmt:message key="searchProducts.jsp.label.search.code"/></label></td>
								<td style="text-align: center"><input type="radio" name="searchedField" onclick="document.forms[0].searchedValue.focus()" value="label" checked><label class="policeGray"><fmt:message key="searchProducts.jsp.label.search.label"/></label></td>
								<td style="text-align: center"><input type="radio" name="searchedField" onclick="document.forms[0].searchedValue.focus()" value="price"><label class="policeGray"><fmt:message key="searchProducts.jsp.label.search.price"/></label></td>
							</tr>		
						</table>		
					</td>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
				</tr>
					<tr>
						<td class="border"><b><font color="#FFCC00">|</font></b></td>
					   	<td class="border" width="50%"><a accesskey='<fmt:message key="searchProducts.jsp.accesskey.cancel"/>' href="javascript:cancel()" class="cancel"><fmt:message key="label.cancel"/></a></td>
						<td class="border"><b><font color="#FFCC00">|</font></b></td>
						<td class="border" width="50%"><a accesskey='<fmt:message key="searchProducts.jsp.accesskey.confirm"/>' href="javascript:searchProductsList()" class="confirm" ><fmt:message key="label.confirm"/></a></td>
						<td class="border"><b><font color="#FFCC00">|</font></b></td>
					</tr>
			</table>
		</form>
		<form name="productsListForm" action="<c:out value="${pageContext.request.contextPath}"/>/ProductsListIFrame.do" method="post" onsubmit="return false" target="IFrameData">
			<input type="hidden" name="pageRequested" value="successSearchProducts">		
			<input type="hidden" name='pageSize' value='<fmt:message key="productsListIFrame.jsp.select.byX1.value"/>'>
			<input type="hidden" name='pageNumber' value='<c:out value="${param.pageNumber}"/>'>
			<input type="hidden" name='totalPages' value='<c:out value="${param.totalPages}"/>'>
			<input type="hidden" name='sortListBy' value='<c:out value="${param.sortListBy}"/>'>
			<input type="hidden" name='sortMonotony' value='<c:out value="${param.sortMonotony}"/>'>
			<input type="hidden" name='code' value='<c:out value="${param.code}"/>'>
			<c:if test="${empty product}">
			<input type="hidden" name="actionDo" value="create">
			</c:if>
			<c:if test="${not empty param.searchedField}">
			<input type="hidden" name='searchedField' value='<c:out value="${param.searchedField}"/>'>
			<input type="hidden" name='searchedValue' value='<c:out value="${param.searchedValue}"/>'>
			</c:if>
		</form>
		
		<form name="menusForm" action="<c:out value="${pageContext.request.contextPath}"/>/ProductsListIFrame.do" method="post" onsubmit="return false">
			<input type="hidden" name="optionSelected" value="PRODUCTS">
		</form>
		
		<jsp:include page='/jsp/commons/includeDivIFrame.jsp'/>					
	</body>
</html>