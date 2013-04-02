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
		<title><fmt:message key="montagnesdor.welcome"/> - <fmt:message key="saveOrUpdateCategory.jsp.title"/></title>
		<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}"/>/javascript/commons.js"></script>
		<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}"/>/javascript/menus.js"></script>
		<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}"/>/javascript/categoriesList.js"></script>
		<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}"/>/javascript/saveOrUpdateCategory.js"></script>	
	</head>

	<body onload='initPage()'>
	<script language="javascript">
		//Cette variable est utilisée dans le fichier saveOrUpdateCategory.js
		var contextPath = '<c:out value="${pageContext.request.contextPath}"/>';
	</script>
	
	<html:form method="post" action='/SaveOrUpdateCategory.do' onsubmit="return false">
		<table class="border" width="100%">
			<tr> 
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
		    	<td class="border" colspan="3" width="100%">
		    		<c:if test="${not empty category}">
						<fmt:message key="saveOrUpdateCategory.jsp.update.title"><fmt:param><c:out value="${param.id}"/></fmt:param></fmt:message>
					</c:if>
		    		<c:if test="${empty category}">
						<fmt:message key="saveOrUpdateCategory.jsp.add.title"/>
					</c:if>
    			</td>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
	  		</tr>
	  		<c:if test="${not empty category}">
			<tr>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
				<td width="50%" class="border"><fmt:message key="saveOrUpdateCategory.jsp.id"/></td>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
				<td width="50%" class="border">
					<c:out value="${category.id}"/><input type="hidden" name="id" value="<c:out value="${category.id}"/>">
				</td>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
			</tr>
			</c:if>			
			<tr>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
				<td width="50%" class="border"><fmt:message key="saveOrUpdateCategory.jsp.label"/></td>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
				<td width="50%" class="border">
				<c:if test="${not empty category}">				
					<input class="text" type="text" style="width: 100%; height: 22" name="label" onkeyup='processUserEntry(event, this)' value="<c:out value="${category.labels[sessionScope.userSession.currentLanguage]}"/>" maxlength="60">
				</c:if>					
				<c:if test="${empty category}">
					<input class="text" type="text" style="width: 100%; height: 22" name="label" onkeyup='processUserEntry(event, this)' maxlength="60">
				</c:if>										
				</td>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
			</tr>
			<tr>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
			   	<td class="border" width="50%"><a accesskey='<fmt:message key="saveOrUpdateCategory.jsp.accesskey.cancel"/>' href="javascript:cancel()" class="cancel"><fmt:message key="label.cancel"/></a></td>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
				<td class="border" width="50%"><a accesskey='<fmt:message key="saveOrUpdateCategory.jsp.accesskey.confirm"/>' href="javascript:submitCategory()" class="confirm" ><fmt:message key="label.confirm"/></a></td>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
			</tr>
			<logic:messagesPresent>
			<tr>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
				<td colspan=3 class="border">
					<label class="policeGray">
						<html:errors property="saveOrUpdateCategory.jsp.alert.label"/>
						<logic:messagesPresent property="saveOrUpdateCategory.jsp.error.create.forbidden">
							<fmt:message key="saveOrUpdateCategory.jsp.error.create.forbidden"><fmt:param><c:out value="${param.id}"/></fmt:param></fmt:message>
						</logic:messagesPresent>
						<logic:messagesPresent property="saveOrUpdateCategory.jsp.error.update.forbidden">
							<fmt:message key="saveOrUpdateCategory.jsp.error.update.forbidden"><fmt:param><c:out value="${param.id}"/></fmt:param></fmt:message>
						</logic:messagesPresent>
					</label>
				</td>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
			</tr>	
			</logic:messagesPresent>
		</table>
		<c:if test="${not empty category}">
		<input type="hidden" name="actionDo" value="update">
		</c:if>					
		<c:if test="${empty category}">
		<input type="hidden" name="actionDo" value="create">
		</c:if>
		<input type="hidden" name="optionSelected" value="CATEGORIES">
	</html:form>

	<form name="alertMessagesForm" onsubmit="return false">
		<input type="hidden" name="saveOrUpdateCategoryAlertLabel" value="<fmt:message key="saveOrUpdateCategory.jsp.alert.label"/>">
	</form>
	
	<form name="categoriesListForm" action="<c:out value="${pageContext.request.contextPath}"/>/SaveOrUpdateCategory.do" method="post" onsubmit="return false">
		<input type="hidden" name="actionDo" value="DISPLAY">
	</form>
	
	<form name="menusForm" action="<c:out value="${pageContext.request.contextPath}"/>/CategoriesListIFrame.do" method="post" onsubmit="return false">
		<input type="hidden" name="optionSelected" value="CATEGORIES">
	</form>
	
	<jsp:include page='/jsp/commons/includeDivIFrame.jsp'/>			

	</body>
</html>