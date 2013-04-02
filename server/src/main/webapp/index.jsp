<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" >
		<link rel="SHORTCUT ICON" HREF="<c:out value="${pageContext.request.contextPath}"/>/favicon.ico">		
		<link rel="stylesheet" type="text/css" href="<c:out value="${pageContext.request.contextPath}"/>/css/montagnesdor.css" />
		<title><fmt:message key="montagnesdor.welcome"/> - <fmt:message key="index.jsp.title"/></title>
		<script type="text/javascript"> 
			function initPage()
			{
				var location = window.top.location;
				if(window.opener)
				{
					self.close();
					location = window.opener.top.location;
				}
				<c:if test="${!empty param.authorization && param.authorization=='true'}">
				location.href = "<c:out value="${pageContext.request.contextPath}"/>/AuthorizationFailed.do";
				</c:if>
				<c:if test="${empty param.authorization}">
				location.href = "<c:out value="${pageContext.request.contextPath}"/>/EntryPoint.do";
				</c:if>
			}
		</script>
	</head>
	<body onload='initPage()'>
	</body>
</html>