<html>
<head>
<script language="javascript">
function initPage()
{
	var tableApplet = document.getElementById("idTableApplet");
	alert(document.nameDateTimeApplet.getDateTime());
	alert(tableApplet.isProduitExist("1"));
	alert(tableApplet.getDesignation("1"));
}
</script>
<body>
milieu1.jsp
<form name="formulaire">
<input type="button" value="appuyer" onclick="initPage()">
</form>
<applet id="idTableApplet" archive="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/jsp/communs/SDateTimeImpressionTableApplet.jar" code="fr.montagnesdor.restaurant.applet.TableApplet">
<param name="URLServlet" value="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/TableApplet">
<param name="patternDate" value="dd/MM/yyyy/HH/mm/ss">
</applet>
<APPLET name="nameDateTimeApplet" ARCHIVE="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/jsp/test/SDateTimeImpressionTableApplet.jar" code="fr.montagnesdor.restaurant.applet.DateTimeApplet.class" WIDTH = "0" HEIGHT = "0" style='background-color: #660000'>
<PARAM NAME="patternDate" VALUE="EEEE dd MMMM yyyy">
<PARAM NAME="patternDateCourte" VALUE="dd/MM/yyyy">
<PARAM NAME="patternTime" VALUE="HH:mm:ss">
<PARAM NAME="patternDateTime" VALUE="EEEE dd MMMM yyyy �HH:mm:ss">
<PARAM NAME="debug" VALUE="false">
</APPLET>

</body>
</html>
