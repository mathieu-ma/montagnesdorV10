<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt" %>

<!--
		S'assurer que l'accès à ce fihier jsp se fait par l'intermédiaire d'une action Struts.
-->
<c:if test="${(empty requestScope.authorization) || (requestScope.authorization=='false')}">
	<script type="text/javascript"> 
		window.top.location.href = "<c:out value="${pageContext.request.contextPath}"/>/index.jsp";
	</script>
</c:if>

<html class="border">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" >
		<link rel="SHORTCUT ICON" HREF="<c:out value="${pageContext.request.contextPath}"/>/favicon.ico">				
		<link rel="stylesheet" type="text/css" href="<c:out value="${pageContext.request.contextPath}"/>/css/montagnesdor.css" />
		<title><fmt:message key="montagnesdor.welcome"/> - <fmt:message key="login.jsp.title"/></title>
		<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}"/>/javascript/common.js"></script>
		<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}"/>/javascript/logon.js"></script>
	</head>
	<body onload="initPage()">
		<script language="javascript">
			//Cette variable est utilisée dans le fichier tableOrders.js
			var contextPath = '<c:out value="${pageContext.request.contextPath}"/>';
		</script>
		<table width="100%" height="100%" cellspacing="0" cellpadding="0" border="0">
			<tr height="60%">
				<td>
				<html:form action="/Logon.do" method="post" onsubmit="return false">
					<table align="center" width="100%" border="0" style="background: url('<c:out value="${pageContext.request.contextPath}"/>/images/montagnesdor2.gif') no-repeat center center">
						<tr height="200">
				   			<td class="right">
			    			</td>
		    				<td class="right">
		    				</td>
				   			<td class="left">
			    			</td>
		    				<td class="left">
		    				</td>
		  				</tr>
				  		<tr>
				    		<td class="right" width="47%">
				    			<fmt:message key="login.jsp.login"/>
					    	</td>
					    	<td class="right" width="3%">
								&nbsp;
				    		</td>
					    	<td class="left" width="15%">
								<html:text property="login" styleClass="logon" maxlength="16" onkeyup='processUserEntry(event, this)'/>
				    		</td>
					    	<td class="left" width="35%">
								&nbsp;
				    		</td>
						</tr>
						<tr>
					    	<td class="right" width="47%">
						    	<fmt:message key="login.jsp.password"/>
					    	</td>
					    	<td class="right" width="3%">
								&nbsp;
				    		</td>
					    	<td class="left" width="15%">
					    		<html:password property="password" styleClass="logon" maxlength="16" onkeyup='processUserEntry(event, this)'/>
				    		</td>
					    	<td class="left" width="35%">
								&nbsp;
				    		</td>
				  		</tr>
						<tr height="100">
				   			<td class="right">
			    			</td>
		    				<td class="right">
		    				</td>
				   			<td class="left">
			    			</td>
		    				<td class="left">
		    				</td>
						</tr>
					</table>
				</html:form>
				</td>
			</tr>
			<tr height="40%">
				<td>
					<table align="center" border="0" width="50%">
			  			<tr>
			    			<td class="center">
			      				<a accesskey='<fmt:message key="accesskey.access.orders"/>' href="javascript:processClick('<c:out value="${pageContext.request.contextPath}"/>')" title='<fmt:message key="login.jsp.access.orders"/>' ><fmt:message key="login.jsp.access.orders"/></a>
			    			</td>
			    			<td class="center">
			      				<a accesskey='<fmt:message key="accesskey.access.admin"/>' href="javascript:processClick('<c:out value="${pageContext.request.contextPath}"/>')" title='<fmt:message key="login.jsp.access.administration"/>'><fmt:message key="login.jsp.access.administration"/></a>
			    			</td>
			  			</tr>
						<logic:messagesPresent>
						<tr>
							<td colspan=2 class="center">
								<fmt:message key="authorizationFailed.jsp.error"/>
							</td>
						</tr>	
						</logic:messagesPresent>
					</table>
				</td>
			</tr>
		</table>			
	</body>
</html>
