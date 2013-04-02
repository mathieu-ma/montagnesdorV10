<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<jsp:include page='/jsp/commons/checkSessionAuthorization.jsp'/>
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="<c:out value="${pageContext.request.contextPath}"/>/css/montagnesdor.css" />
		<script type="text/javascript"> 
		function initPage()
		{
			if(window.opener)
			{
				window.opener.document.tableOrdersApplet.setEntryDate('<c:out value="${date}"/>', '<c:out value="${month}"/>', '<c:out value="${year}"/>');
				if(!window.opener.focusFirstInputText(window.opener.document))
					window.opener.focusFirstInputText(window.opener.IFrameData.document);
				self.close();
			}
		}
		</script>
	</head>
	<body onload='initPage()'>
	</body>
</html>