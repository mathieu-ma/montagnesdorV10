<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<request:request id="req" />

<html>
<head>
<script type="text/javascript">
function initPage()
{
	var dateTimeApplet = document.applets["dateTimeApplet"];
	alert( dateTimeApplet.getDate());		
}

</script>
</head>
<body onload="initPage()">
Hello World
			<applet name='dateTimeApplet' archive='<jsp:getProperty name="req"  property="scheme"/>://<jsp:getProperty name="req"  property="serverName"/>:<jsp:getProperty name="req"  property="serverPort"/><jsp:getProperty name="req"  property="contextPath"/>/jsp/commons/DateTimeImpressionTableApplet.jar' code='fr.montagnesdor.restaurant.applet.DateTimeApplet.class'>
				<param name='patternDate' value='<bean:message key="applets.DateTimeApplet.param.patternDate.value"/>'></param>
				<param name='patternDateCourte' value='<bean:message key="applets.DateTimeApplet.param.patternDateCourte.value"/>'></param>
				<param name='patternTime' value='<bean:message key="applets.DateTimeApplet.param.patternTime.value"/>'></param>
				<param name='patternDateTime' value='<bean:message key="applets.DateTimeApplet.param.patternDateTime.value"/>'></param>		
				<param name='debug' value='<bean:message key="applets.DateTimeApplet.param.debug.value"/>'></param>		
			</applet>



<0x1B>@<0x1B>R<0x01><0x1B>!8KIM SAN<0x1B>!<0x01>
11 all{e cl{mencet
93 340 Le Raincy
Tel : 01 43 02 50 90
R.C B 313 105 397
TVA n[ : FR 19 313 105 397 000 19
 
03/01/2005
<0x1B>!8TABLE 1/2<0x1B>!<0x01>
 
  QTE         DESIGNATION        MONTANT
----------------------------------------
  1.0  SOUPE AUX TROIS FRAICHE     10.00
  3.0  POTAGE AUX MAIS ET JAMB     12.00
  1.0  POTAGE AUX MAIS ET JAMB      4.00
----------------------------------------
  1.0  PRODUIT OFFERT :NEM          0.00
----------------------------------------
 
 
<0x1B>!8TOTAL :    26.00<0x1B>!<0x01>
** Dont TVA 19.60                   4.26
Total hors taxe                    21.74
** Service compris
 
       *** MERCI ET A BIENTOT ***       
<0x1B>J￰<0x1B>i<0x1B>@<0x1B>R<0x01><0x1B>!8KIM SAN<0x1B>!<0x01>
11 all{e cl{mencet
93 340 Le Raincy
Tel : 01 43 02 50 90
R.C B 313 105 397
TVA n[ : FR 19 313 105 397 000 19
 
08/01/2005
<0x1B>!8TABLE 10/1<0x1B>!<0x01>
 
  QTE         DESIGNATION        MONTANT
----------------------------------------
 15.0  FRAICHE    150.00
 15.0  POTAGE AUX AILERONS DE     105.00
  6.0  POTAGE AUX MAIS ET JAMB     24.00
----------------------------------------
  1.0  PRODUIT OFFERT :POTAGE       0.00
----------------------------------------
----------------------------------------
  1.0  PRODUIT OFFERT :SOUPE A      0.00
----------------------------------------
 
----------------------------------------
       5.00 % de r{duction -14.00       
----------------------------------------
 
<0x1B>!8TOTAL :   265.00<0x1B>!<0x01>
** Dont TVA 19.60                  43.43
Total hors taxe                   221.57
** Service compris
 
       *** MERCI ET A BIENTOT ***       
<0x1B>J￰<0x1B>i<0x1B>@<0x1B>R<0x01><0x1B>!8KIM SAN<0x1B>!<0x01>
11 all{e cl{mencet
93 340 Le Raincy
Tel : 01 43 02 50 90
R.C B 313 105 397
TVA n[ : FR 19 313 105 397 000 19
 
03/01/2005
<0x1B>!8TABLE 3/1<0x1B>!<0x01>
 
  QTE         DESIGNATION        MONTANT
----------------------------------------
  2.0  POTAGE AUX MAIS ET JAMB      8.00
  1.0  SOUPE AUX TROIS FRAICHE     10.00
 
 
<0x1B>!8TOTAL :    18.00<0x1B>!<0x01>
** Dont TVA 19.60                   2.95
Total hors taxe                    15.05
** Service compris
 
       *** MERCI ET A BIENTOT ***       
<0x1B>J￰<0x1B>i<0x1B>@<0x1B>R<0x01><0x1B>!8KIM SAN<0x1B>!<0x01>
11 all{e cl{mencet
93 340 Le Raincy
Tel : 01 43 02 50 90
R.C B 313 105 397
TVA n[ : FR 19 313 105 397 000 19
 
03/09/2005
<0x1B>!8TABLE 4/1<0x1B>!<0x01>
 
  QTE         DESIGNATION        MONTANT
----------------------------------------
  2.0  POTAGE ASPERGES AU CRAB      9.00
  1.0  POTAGE AUX AILERONS DE       7.00
 
 
<0x1B>!8TOTAL :    16.00<0x1B>!<0x01>
** Dont TVA 19.60                   2.62
Total hors taxe                    13.38
** Service compris
 
       *** MERCI ET A BIENTOT ***       
<0x1B>J￰<0x1B>i<0x1B>@<0x1B>R<0x01><0x1B>!8KIM SAN<0x1B>!<0x01>
11 all{e cl{mencet
93 340 Le el : 01 43 02 50 90
R.C B 313 105 397
TVA n[ : FR 19 313 105 397 000 19
 
06/01/2005
<0x1B>!8TABLE 5/11<0x1B>!<0x01>
 
  QTE         DESIGNATION        MONTANT
----------------------------------------
 12.0  NEM                         54.00
 12.0  SOUPE AUX TROIS FRAICHE    120.00
  1.0  SOUPE PHNOM-PENH SPECIA      5.50
 
 
<0x1B>!8TOTAL :   179.50<0x1B>!<0x01>
** Dont TVA 19.60                  29.42
Total hors taxe                   150.08
** Service compris
 
       *** MERCI ET A BIENTOT ***       
<0x1B>J￰<0x1B>i


</body>
</html>