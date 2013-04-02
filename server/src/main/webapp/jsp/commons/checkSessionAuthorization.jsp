<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<!--
		Ce fichier doit être inclus dans tous les fichiers jsp afin de s'assurer que :
		1) La session de l'utilisateur est valide.
		2) L'accès aux jsp se fait par l'intermédiaire d'une action Struts.
-->
<c:if test="${(empty sessionScope.userSession) || (empty requestScope.authorization) || (requestScope.authorization=='false')}">
	<script type="text/javascript"> 
		window.top.location.href = "<c:out value="${pageContext.request.contextPath}"/>/index.jsp";
	</script>
</c:if>
