<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglibs-request.tld" prefix="request" %>
<request:request id="req" />

<html>
<head>
<script type="text/javascript">
function initPage()
{
//	var a = document.getElementById("appletX");
//	var dateTimeApplet = document.applets["dateTimeApplet"];
//	alert(document.dateTimeApplet.getDate());		

alert("**** "+document.dateTimeApplet.getDate()+" ****");

}

function clickX()
{
	alert(document.dateTimeApplet.getDate());
	alert(document.dateTimeApplet.getDate());
}

</script>
</head>
<body onload="initPage()">
Hello World
			<applet name='dateTimeApplet' codebase='.' code='fr.montagnesdor.restaurant.applet.DateTimeApplet.class'>
				<param name='patternDate' value='<bean:message key="applets.DateTimeApplet.param.patternDate.value"/>'></param>
				<param name='patternDateCourte' value='<bean:message key="applets.DateTimeApplet.param.patternDateCourte.value"/>'></param>
				<param name='patternTime' value='<bean:message key="applets.DateTimeApplet.param.patternTime.value"/>'></param>
				<param name='patternDateTime' value='<bean:message key="applets.DateTimeApplet.param.patternDateTime.value"/>'></param>		
				<param name='debug' value='<bean:message key="applets.DateTimeApplet.param.debug.value"/>'></param>		
			</applet>
<form>
<input type="button" onclick="clickX()" value="Click">
</form>
</body>
</html>