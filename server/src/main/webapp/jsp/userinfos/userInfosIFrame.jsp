<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!--
	When you use an absolute URI, you do not have to add the taglib element to web.xml; 
	the JSP container automatically locates the TLD inside the JSTL library implementation.
-->
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt" %>

<jsp:include page='/jsp/commons/checkSessionAuthorization.jsp'/>

<html>
	<head> 
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" >
		<link rel="stylesheet" type="text/css" href="<c:out value="${pageContext.request.contextPath}"/>/css/montagnesdor.css" />
		<title><fmt:message key="montagnesdor.welcome"/> - <fmt:message key="userInfos.jsp.title"/></title>
		<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}"/>/javascript/commons.js"></script>
		<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}"/>/javascript/userInfos.js"></script>			
	</head>
	<body onload="initPageIFrame()">
	<c:if test="${!empty sessionScope.userSession}">
		<table border="0" width="100%" align="center">
			<tr>
				<td width="25%">
				</td>	
				<td width="50%">
					<br/>
					<fmt:message key="userInfos.jsp.welcome">
						<c:if test="${sessionScope.userSession.sex==0}">
							<fmt:param ><fmt:message key="userInfos.jsp.title.male"/></fmt:param>
						</c:if>
						<c:if test="${sessionScope.userSession.sex==1}">
							<fmt:param ><fmt:message key="userInfos.jsp.title.female"/></fmt:param>
						</c:if>
						<fmt:param ><c:out value="${userSession.forename1}"/></fmt:param>
						<fmt:param ><c:out value="${userSession.name}"/></fmt:param>
					</fmt:message>
					<br/>
					<br/>
				</td>
				<td width="25%">
				</td>	
			<tr>
		</table>
		<form name="changePrintLanguage" action="<c:out value="${pageContext.request.contextPath}"/>/ChangePrintLanguage.do" method="post" onsubmit="return false" target="IFrameData">
			<table class="border" width="50%" align="center">
				<tr>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
					<td class="border" width="50%">
						<font color="#999999">
							<fmt:message key="userInfos.jsp.name"/>
						</font>	
					</td>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
					<td class="border" width="50%">
						<c:out value="${userSession.name}" />
					</td>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
				</tr>	
				<tr>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
					<td class="border" width="50%">
						<font color="#999999">				
							<fmt:message key="userInfos.jsp.forename1"/>
						</font>	
					</td>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
					<td class="border" width="50%">
						<c:out value="${userSession.forename1}" />				
					</td>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
				</tr>
				<c:if test="${!empty sessionScope.userSession.forename2}">
				<tr>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
					<td class="border" width="50%">
						<font color="#999999">				
							<fmt:message key="userInfos.jsp.forename2"/>
						</font>						
					</td>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
					<td class="border" width="50%">
						<c:out value="${userSession.forename2}" />
					</td>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
				</tr>	
				</c:if>
				<tr>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
					<td class="border" width="50%">
						<font color="#999999">				
							<fmt:message key="userInfos.jsp.birthday"/>
						</font>	
					</td>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
					<td class="border" width="50%">
						<c:out value="${userSession.birthday}" />
					</td>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
				</tr>	
				<tr>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
					<td class="border" width="50%">
						<font color="#999999">				
							<fmt:message key="userInfos.jsp.role"/>
						</font>	
					</td>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
					<td class="border" width="50%">
						<c:out value="${userSession.role}" />					
					</td>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
				</tr>	
				<tr>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
					<td class="border" width="50%">
						<font color="#999999">
							<fmt:message key="userInfos.jsp.login"/>
						</font>	
					</td>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
					<td class="border" width="50%">
						<c:out value="${userSession.login}" />					
					</td>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
				</tr>	
				<tr>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
					<td class="border" width="50%">
						<font color="#999999">				
							<fmt:message key="userInfos.jsp.prefered.print.language"/>
						</font>	
					</td>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
					<td class="border" width="50%">
				 		<SELECT name="preferedPrintLanguage" onchange="javascript:document.changePrintLanguage.submit();" style="width: 100%;">
							<c:forEach var="localeLanguage" items="${userSession.localesLanguages}" varStatus="vs">
								<c:if test="${not empty sessionScope.userSession.user.preferedPrintLanguage}">
									<OPTION <c:if test="${sessionScope.userSession.user.preferedPrintLanguage.id==localeLanguage.id.locale.id}">selected</c:if> value="<c:out value="${localeLanguage.id.locale.id}"/>"><c:out value="${localeLanguage.label}"/></OPTION>
								</c:if>
								<c:if test="${empty sessionScope.userSession.user.preferedPrintLanguage}">
									<OPTION <c:if test="${sessionScope.userSession.currentLanguage==localeLanguage.id.locale.id}">selected</c:if> value="<c:out value="${localeLanguage.id.locale.id}"/>"><c:out value="${localeLanguage.label}"/></OPTION>
								</c:if>							
						 	</c:forEach>
			 			</SELECT>
					</td>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
				</tr>	
				<tr>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
					<td class="border" colspan="3" width="100%">
						<a accesskey='<fmt:message key="accesskey.password"/>' href="javascript:changeUserPassword('<c:out value="${pageContext.request.contextPath}"/>', '0')" title="<fmt:message key="userInfos.jsp.change.password"/>"><fmt:message key="userInfos.jsp.change.password"/></a>
					</td>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
				</tr>
				<c:if test="${!empty sessionScope.userSession.levelPass1}">
				<tr>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
					<td class="border" colspan="3" width="100%">
						<a accesskey='<fmt:message key="accesskey.password1"/>' href="javascript:changeUserPassword('<c:out value="${pageContext.request.contextPath}"/>', '1')" title="<fmt:message key="userInfos.jsp.change.level.password1"/>"><fmt:message key="userInfos.jsp.change.level.password1"/></a>
					</td>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
				</tr>	
				</c:if>
				<c:if test="${!empty sessionScope.userSession.levelPass2}">
				<tr>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
					<td class="border" colspan="3" width="100%">
						<a accesskey='<fmt:message key="accesskey.password2"/>' href="javascript:changeUserPassword('<c:out value="${pageContext.request.contextPath}"/>', '2')" title="<fmt:message key="userInfos.jsp.change.level.password2"/>"><fmt:message key="userInfos.jsp.change.level.password2"/></a>
					</td>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
				</tr>	
				</c:if>
				<c:if test="${!empty sessionScope.userSession.levelPass3}">
				<tr>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
					<td class="border" colspan="3" width="100%">
						<a accesskey='<fmt:message key="accesskey.password3"/>' href="javascript:changeUserPassword('<c:out value="${pageContext.request.contextPath}"/>', '3')" title="<fmt:message key="userInfos.jsp.change.level.password3"/>"><fmt:message key="userInfos.jsp.change.level.password3"/></a>
					</td>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
				</tr>	
				</c:if>
			</table>
		</form>
		<!--form action="https://<c:out value="${pageContext.request.serverName}"/>:8443/<c:out value="${pageContext.request.contextPath}"/>/DisplayJsp.do" method="post" onsubmit="return false" target="IFrameData"-->
		<form action="<c:out value="${pageContext.request.contextPath}"/>/DisplayJsp.do" method="post" onsubmit="return false" target="IFrameData">
			<input type="hidden" name="pageRequested" value="successChangeUserPassword">
			<input type="hidden" name="levelPassword" value="0">
		</form>
		<jsp:include page='/jsp/commons/includeDivIFrame.jsp'/>
		<div style='position: relative; height: 0;visibility:hidden'>
			<table width="100%" height="100%" cellspacing="0" cellpadding="0" border="0" >
				<tr>
					<td id="idTdDataOptions" width="15%" valign="top" title='Data Options'>
						&nbsp;
					</td>
				</tr>					
				<tr>
					<td  id="idTdDataHeader" height="5%" title='Data Header'>
						<table id="idTableDataHeader">
							<tr>
								<td>
								</td>
							</tr>
						</table>
					</td>
				</tr>	
				<tr>
					<td id="idTdDataFooter" height="5%"  title='Data Footer'>
						
					</td>
				</tr>	
			</table>			
		</div>
	</c:if>	
	</body>
</html>