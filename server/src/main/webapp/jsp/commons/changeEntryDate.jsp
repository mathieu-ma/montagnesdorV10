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
		<title><fmt:message key="montagnesdor.welcome"/> - <fmt:message key="changeEntryDate.jsp.title"/></title>
		<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}"/>/javascript/commons.js"></script>
		<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}"/>/javascript/changeEntryDate.js"></script>
	</head>
	<body onload='initPage()'>
		<html:form method="post" action='/ChangeEntryDate.do' onsubmit="return false">
			<table class="border" width="100%" align="center">
				<tr>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
					<td colspan=5 class="border" width="100%"><label class="policeGray"><fmt:message key="changeEntryDate.jsp.change.date"/></label></td>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
				</tr>	
				<tr>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
					<td class="border" width="33%"><fmt:message key="changeEntryDate.jsp.label.date"/></td>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
					<td class="border" width="33%"><fmt:message key="changeEntryDate.jsp.label.month"/></td>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
					<td class="border" width="33%"><fmt:message key="changeEntryDate.jsp.label.year"/></td>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
				</tr>	
				<tr>
					<!--On utilise now.date, now.month, now.year correspondant respectivement à now.getDate(), now.getMonth(), now.getYear() de la classe java.util.Date : ces fonctions sont Deprecated-->
					<!--Cette date vient du serveur-->
					<jsp:useBean id="now" class="java.util.Date"/>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
					<td class="border" width="33%">
						<SELECT name="date" onchange="document.forms[0].password.focus();">
						<c:forEach var="value" begin="1" end="31">
							<OPTION value="<c:out value="${value}"/>"><fmt:formatNumber value="${value}" type="NUMBER" pattern="00" minIntegerDigits="2" maxFractionDigits="0"/></OPTION>
						</c:forEach>
						</SELECT>
					</td>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
					<td class="border" width="33%">
						<SELECT name="month" onchange="document.forms[0].password.focus();">
						<c:forEach var="dateForMonth" varStatus="status" items="${attributesOut}">
							<OPTION value="<c:out value="${status.index+1}"/>"><fmt:formatDate pattern="MMMM" value="${dateForMonth}" type="DATE"/></OPTION>
						</c:forEach>
						</SELECT>
					</td>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
					<td class="border" width="33%">
						<SELECT name="year" onchange="document.forms[0].password.focus();">
						<c:forEach var="value" begin="${1900+now.year-1}" end="${1900+now.year+1}" varStatus="status">
							<OPTION value="<c:out value="${value}"/>"><c:out value="${value}"/></OPTION>
						</c:forEach>
						</SELECT>
					</td>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
				</tr>
			</table>
			<table class="border" width="100%" align="center">
				<tr>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
					<td class="border" width="50%"><fmt:message key="changeEntryDate.jsp.label.password"/></td>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
					<td class="border" width="50%"><input type="password" name="password" class="password" onkeyup='processUserEntry(event, this)' maxlength="16"></td>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
				</tr>
				<tr>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
				   	<td class="border" width="50%"><a accesskey='<fmt:message key="changeTable.jsp.accesskey.cancel"/>' href="javascript:cancel()" class="cancel"><fmt:message key="label.cancel"/></a></td>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
					<td class="border" width="50%"><a accesskey='<fmt:message key="changeTable.jsp.accesskey.confirm"/>' href="javascript:verifyPassword()" class="confirm" ><fmt:message key="label.confirm"/></a></td>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
				</tr>		
				<logic:messagesPresent>
				<tr>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
					<td class="border" colspan="3">
						<label class="policeGray">
							<html:errors property="error.authentication.failed"/>
							<html:errors property="error.password.required"/>
							<html:errors property="error.authentication.denied"/>
							<html:errors property="changeEntryDate.jsp.error.date.required"/>
							<html:errors property="changeEntryDate.jsp.error.date.required"/>
							<html:errors property="changeEntryDate.jsp.error.date.impossible"/>
							<html:errors property="changeEntryDate.jsp.error.jour.incompatible"/>
							<html:errors property="changeEntryDate.jsp.error.month.required"/>
							<html:errors property="changeEntryDate.jsp.error.month.impossible"/>
							<html:errors property="changeEntryDate.jsp.error.year.required"/>
							<html:errors property="changeEntryDate.jsp.error.year.impossible"/>
						</label>
					</td>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
				</tr>	
				</logic:messagesPresent>				
			</table>
		</html:form>
	</body>
</html>