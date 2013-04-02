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
			<logic:messagesPresent>
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
	  		
		</table>

		<jsp:include page='/jsp/commons/includeDivIFrame.jsp'/>			
		
	</body>
</html>