<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>


<jsp:include page='/jsp/commons/checkSessionAuthorization.jsp'/>

<html>
	<head> 
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" >
		<link rel='stylesheet' type='text/css' href='<c:out value="${pageContext.request.contextPath}"/>/css/montagnesdor.css'/>
		<title><fmt:message key="montagnesdor.welcome"/> - <fmt:message key="error.jsp.title"/></title>
		<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}"/>/javascript/commons.js"></script>
		<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}"/>/javascript/productsList.js"></script>		
		<script type="text/javascript">
		function initPage()
		{
			document.getElementById("idDivIFrame").style.height = screen.height;
			focusFirstInputText(window.parent.document);			
		}
		function cancel()
		{
			if(document.productsListForm.searchedField && document.productsListForm.searchedField.value!='NONE')
			{
				document.productsListForm.action = "<c:out value="${pageContext.request.contextPath}"/>"+"/SearchProductsList.do";
			}
			else if(window.parent.document.getElementById("idPageSize").value!="0")
				document.productsListForm.pageSize.value = window.parent.document.getElementById("idPageSize").value;
		
			document.productsListForm.submit()		
		}
		</script>		
	</head>

	<body onload='initPage()'>
	
		<table class="border" width="100%">
			<tr> 
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
		    	<td class="border" width="100%">
					<fmt:message key="error.jsp.message"/>
    			</td>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
	  		</tr>
			<logic:messagesPresent property="error.params.forbidden">
			<tr>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
				<td class="border">
					<label class="policeGray">
						<html:errors/>
					</label>
				</td>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
			</tr>	
			</logic:messagesPresent>
			<logic:messagesPresent property="saveOrUpdateProduct.jsp.error.delete.forbidden">
			<tr>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
				<td class="border">
					<label class="policeGray">
						<fmt:message key="saveOrUpdateProduct.jsp.error.delete.forbidden"><fmt:param><c:out value="${param.code}"/></fmt:param></fmt:message>
					</label>
				</td>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
			</tr>	
			</logic:messagesPresent>
			<logic:messagesPresent property="saveOrUpdateProduct.jsp.error.delete.categories">
			<tr>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
				<td class="border">
					<label class="policeGray">
						<fmt:message key="saveOrUpdateProduct.jsp.error.delete.categories"><fmt:param><c:out value="${param.code}"/></fmt:param></fmt:message>
					</label>
				</td>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
			</tr>	
			</logic:messagesPresent>
			<tr>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
			   	<td class="border"><a accesskey='<fmt:message key="saveOrUpdateProduct.jsp.accesskey.cancel"/>' href="javascript:cancel();" class="confirm"><fmt:message key="label.ok"/></a></td>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
			</tr>
		</table>
		<form name="productsListForm" action="<c:out value="${pageContext.request.contextPath}"/>/ProductsListIFrame.do" method="post" onsubmit="return false" target="IFrameData">
			<input type="hidden" name='pageSize' value='<c:out value="${param.pageSize}"/>'>
			<input type="hidden" name='pageNumber' value='<c:out value="${param.pageNumber}"/>'>
			<input type="hidden" name='totalPages' value='<c:out value="${param.totalPages}"/>'>
			<input type="hidden" name='sortListBy' value='<c:out value="${param.sortListBy}"/>'>
			<input type="hidden" name='sortMonotony' value='<c:out value="${param.sortMonotony}"/>'>
			<input type="hidden" name='code' value='<c:out value="${param.code}"/>'>
			<c:if test="${not empty param.searchedField}">
			<input type="hidden" name='searchedField' value='<c:out value="${param.searchedField}"/>'>
			<input type="hidden" name='searchedValue' value='<c:out value="${param.searchedValue}"/>'>
			</c:if>
		</form>

		<jsp:include page='/jsp/commons/includeDivIFrame.jsp'/>			
		
	</body>
</html>