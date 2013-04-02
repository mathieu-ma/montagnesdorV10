<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt" %>

<jsp:include page='/jsp/commons/checkSessionAuthorization.jsp'/>

<html class="border">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link rel="SHORTCUT ICON" HREF="<c:out value="${pageContext.request.contextPath}"/>/favicon.ico">				
		<link rel="stylesheet" type="text/css" href="<c:out value="${pageContext.request.contextPath}"/>/css/montagnesdor.css" />
		<title><fmt:message key="montagnesdor.welcome"/> - <fmt:message key="userInfos.jsp.title"/></title>
		<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}"/>/javascript/commons.js"></script>
		<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}"/>/javascript/menuTop.js"></script>	
	</head>
	<body scroll="no">
		<table width="100%" height="100%" cellspacing="0" cellpadding="0" border="0">
			<jsp:include page='/jsp/commons/menuTop.jsp'/>
			<tr height="90%">
				<!--'Data Options'-->
				<td width="15%" align="center" valign="top">
					<table width="100%" height="100%" border="0">
						<tr>
							<td style="width: 100%; height: 24%;background-repeat: no-repeat; background-position: top; background-image:url('<c:out value="${pageContext.request.contextPath}"/>/images/pictures/<c:out value="${sessionScope.userSession.forename1}"/>_<c:out value="${sessionScope.userSession.name}"/>.jpg')" title="<c:out value="${sessionScope.userSession.forename1}"/> : <c:out value="${sessionScope.userSession.name}"/>">&nbsp;</td>
						</tr>
						<tr>
							<td id="idTdDataOptions" style="width: 100%; height: 90%;" align="center" valign="top">&nbsp;</td>
						</tr>
					</table>
				</td>
				<td width="85%">
					<table width="100%" height="100%" cellspacing="0" cellpadding="0" border="0" >
						<tr>
							<td id="idTdDataHeader" width="100% height="5%" title='Data Header'>

							</td>
						</tr>	
						<tr>
							<td id="idTdDataCenter" width="100%" height="90%" valign="top"  title='Data Center'>
								<iframe id="IdIFrameData" name="IFrameData" style="width: 100%; height: 100%;"  frameborder="0" marginwidth="0" marginheight="0" src='<c:out value="${pageContext.request.contextPath}"/>/UserInfosIFrame.do' scrolling="yes">
								</iframe>
							</td>
						</tr>	
						<tr>
							<td id="idTdDataFooter" height="5%"  title='Data Footer'>
							
							</td>
						</tr>	
					</table>
				</td>	
			</tr>
		</table>
		<script language='javascript'>
		</script>
	</body>
</html>