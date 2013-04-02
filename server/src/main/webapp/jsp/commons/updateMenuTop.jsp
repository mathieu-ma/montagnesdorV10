<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<jsp:include page='/jsp/commons/checkSessionAuthorization.jsp'/>
<html>
	<head> 
		<link rel="stylesheet" type="text/css" href="<c:out value="${pageContext.request.contextPath}"/>/css/montagnesdor.css" />
		<script type="text/javascript">
			function initPage()
			{
				var tableOrdersApplet = window.parent.document.tableOrdersApplet;
				tableOrdersApplet.isTableExist('<c:out value="${userSession.room.currentTable.number}"/>');
				//Cette jsp permet de mettre à jour les libellés(numéro de table et nombre de personnes) et de faire une redirection l'action Struts UserInfosIFrame.do
				window.parent.document.getElementById("idTDTableNumber").innerHTML = '<c:out value="${userSession.room.currentTable.number}"/>';
				window.parent.document.getElementById("idTDCustomerNumber").innerHTML = '<c:out value="${userSession.room.currentTable.customersNumber}"/>';
				document.location = '<c:out value="${pageContext.request.contextPath}"/>/TableOrdersIFrame.do';
			}
		</script>
	</head>
	<body onload='initPage()'>
	</body>
</html>