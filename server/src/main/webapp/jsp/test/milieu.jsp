<%@ page language="java" import="fr.montagnesdor.util.*,fr.montagnesdor.restaurant.modele.metier.*,java.util.*,java.net.*" %>
<HTML>
<HEAD>
<TITLE>Information Sur les Tables en Cours</TITLE>
<style type="text/css">
<!--
.donneeTableau {  font-family: "Times New Roman", Times, serif; font-size: 12px; font-weight: bolder; color: #FFCC00; text-decoration: none; text-align: center}
.policeBlanc {  font-family: "Times New Roman", Times, serif; font-size: 12px; font-weight: bolder; text-decoration: none; color: #FFFFFF}
-->
</style>
<SCRIPT language="Javascript"> 

function initPage()
{
	var tableApplet = document.getElementById("idTableApplet");
	alert(tableApplet.isTableExist("2"));
}
</SCRIPT>
</HEAD>
<BODY onload='initPage()' bgColor='#660000'>
milieu.jsp
<APPLET id='idTableApplet' ARCHIVE="DateTimeImpressionTableApplet.jar" CODE = "fr.montagnesdor.restaurant.applet.TableApplet.class" WIDTH ="0" HEIGHT = "0" style='background-color: #660000'>
<PARAM NAME = "URLServlet" VALUE="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/TableApplet">
<PARAM NAME = "patternDate" VALUE="dd/MM/yyyy/HH/mm/ss">
</APPLET>

</BODY>
</HTML>
