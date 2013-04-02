<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt" %>

<jsp:include page='/jsp/commons/checkSessionAuthorization.jsp'/>

<html>
	<head> 
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" >
		<link rel='stylesheet' type='text/css' href='<c:out value="${pageContext.request.contextPath}"/>/css/montagnesdor.css'/>
		<title><fmt:message key="montagnesdor.welcome"/> - <fmt:message key="changeUserPassword.jsp.title"/></title>
		<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}"/>/javascript/commons.js"></script>
		<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}"/>/javascript/changeUserPassword.js"></script>	
	</head>

	<body onload='initPage()'>
		<script language="javascript">
			//Cette variable est utilisée dans le fichier changeUserPassword.js
			var contextPath = '<c:out value="${pageContext.request.contextPath}"/>';
		</script>
	
		<html:form method="post" action='/ChangeUserPassword.do'>
			<table class="border" width="100%" align="center">
				<tr>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
					<td class="border" width="100%" colspan="3">
						<fmt:message key="changeUserPassword.jsp.change.password">
							<c:if test="${!empty param.levelPassword}">
								<fmt:param>
									<fmt:message key="changeUserPassword.jsp.level.password${param.levelPassword}"/>
								</fmt:param>
							</c:if>	
						</fmt:message>
					</td>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
				</tr>	
				<tr>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
					<td class="border" width="50%">
						<fmt:message key="changeUserPassword.jsp.old.password">
							<c:if test="${!empty param.levelPassword}">
								<fmt:param>
									<fmt:message key="changeUserPassword.jsp.level.password${param.levelPassword}"/>
								</fmt:param>
							</c:if>	
						</fmt:message>
					</td>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
					<td class="border" width="50%">
						<input name="oldPassword" type="password" class="password" onkeyup='processUserEntry(event, this)'>
					</td>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
				</tr>
				<tr>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
					<td class="border" width="50%">
						<fmt:message key="changeUserPassword.jsp.new.password">
							<c:if test="${!empty param.levelPassword}">
								<fmt:param>
									<fmt:message key="changeUserPassword.jsp.level.password${param.levelPassword}"/>
								</fmt:param>
							</c:if>	
						</fmt:message>
					</td>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
					<td class="border" width="50%">
						<input name="newPassword" type="password" class="password" onkeyup='processUserEntry(event, this)'>
					</td>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
				</tr>
				<tr>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
					<td class="border" width="50%">
						<fmt:message key="changeUserPassword.jsp.repeated.password">
							<c:if test="${!empty param.levelPassword}">
								<fmt:param>
									<fmt:message key="changeUserPassword.jsp.level.password${param.levelPassword}"/>
								</fmt:param>
							</c:if>	
						</fmt:message>
					</td>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
					<td class="border" width="50%">
						<input name="repeatedPassword" type="password" class="password" onkeyup='processUserEntry(event, this)'>
					</td>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
				</tr>
				<tr>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
				   	<td class="border" width="50%"><a accesskey='<fmt:message key="accesskey.cancel"/>' href="javascript:cancel()" class="cancel"><fmt:message key="label.cancel"/></a></td>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
					<td class="border" width="50%"><a accesskey='<fmt:message key="accesskey.confirm"/>' href="javascript:updatePassword()" class="confirm" ><fmt:message key="label.confirm"/></a></td>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
				</tr>		
				<logic:messagesPresent>
				<tr>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
					<td colspan=3 class="border">
						<html:errors property="error.level.password.required"/>	
						<html:errors property="error.old.password.required"/>
						<html:errors property="error.new.password.required"/>
						<html:errors property="error.repeated.password.required"/>
						<html:errors property="error.password.noaccess"/>						
						<html:errors property="error.password.incorrect"/>						
						<html:errors property="error.repeated.password.different"/>						
					</td>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
				</tr>	
				</logic:messagesPresent>
			</table>
			<input name="levelPassword" type="hidden" value="<c:out value="${param.levelPassword}"/>">
		</html:form>
		<jsp:include page='/jsp/commons/includeDivIFrame.jsp'/>
	</body>
</html>