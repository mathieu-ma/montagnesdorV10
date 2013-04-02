<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>

	<table class="border" style="width: 80%; height: 25%;">
		<tr>
			<td class="border" style="background-repeat: no-repeat; background-position: center; background-image:url('<c:out value="${pageContext.request.contextPath}"/>/images/pictures/<c:out value="${sessionScope.userSession.forename1}"/>_<c:out value="${sessionScope.userSession.name}"/>.jpg')" title="<c:out value="${sessionScope.userSession.forename1}"/> : <c:out value="${sessionScope.userSession.name}"/>">&nbsp;
				<!--img src="<c:out value="${pageContext.request.contextPath}"/>/images/pictures/<c:out value="${sessionScope.userSession.picture}"/>" title="<c:out value="${sessionScope.userSession.forename1}"/> : <c:out value="${sessionScope.userSession.name}"/>"/-->
			</td>
		</tr>
	</table>

