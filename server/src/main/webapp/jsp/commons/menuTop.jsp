<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!--
	When you use an absolute URI, you do not have to add the taglib element to web.xml; 
	the JSP container automatically locates the TLD inside the JSTL library implementation.
-->
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt" %>

<jsp:include page='/jsp/commons/checkSessionAuthorization.jsp'/>

<script language="javascript">
	//Ces 4 variables sont utilisées dans le fichier menuTop.js
	var customerNumberDescription = '<fmt:message key="menuTop.jsp.number.customer"/>';
	var takeawayDescription = '<fmt:message key="menuTop.jsp.takeaway"/>';	
	var waitLoadAppletsMessage = '<fmt:message key="userInfos.jsp.wait.load.applets.message"/>';
	var contextPath = '<c:out value="${pageContext.request.contextPath}"/>';
</script>

	<tr height="6%">
		<td colspan="2" align="center">
		  	<table width="80%">
				<tr align="center">
					<td colspan="13">
						<applet name='dateTimeApplet' archive="<c:out value="${pageContext.request.contextPath}"/>/jsp/commons/SDateTimeApplet.jar" code="fr.montagnesdor.restaurant.applets.DateTimeApplet.class" mayscript="true">
							<param name='localeLanguage' value='<c:out value="${sessionScope.userSession.currentLanguage}"/>'></param>
							<param name='patternDate' value='<fmt:message key="applets.DateTimeApplet.param.patternDate.value"/>'></param>
							<param name='patternDateShort' value='<fmt:message key="applets.DateTimeApplet.param.patternDateShort.value"/>'></param>
							<param name='patternTime' value='<fmt:message key="applets.DateTimeApplet.param.patternTime.value"/>'></param>
							<param name='patternDateTime' value='<fmt:message key="applets.DateTimeApplet.param.patternDateTime.value"/>'></param>		
							<param name='debug' value='<fmt:message key="applets.DateTimeApplet.param.debug.value"/>'></param>
							<param name='startJavascriptFunction' value='initPage'></param>
						</applet>
						<applet name='tableOrdersApplet' archive='<c:out value="${pageContext.request.contextPath}"/>/jsp/commons/STableOrdersApplet.jar' code="fr.montagnesdor.restaurant.applets.TableOrdersApplet.class" mayscript="true">
							<param name='URLServlet' value='<c:out value="${pageContext.request.contextPath}"/><fmt:message key="applets.TableOrdersApplet.param.URLServlet.value"/>'></param>
							<param name='patternDate'  value='<fmt:message key="applets.TableOrdersApplet.param.patternDate.value"/>'></param>
							<param name='minAmmountSumsTakeaway'  value='<fmt:message key="restaurant.takeaway.min.amount.reduction"/>'></param>
							<param name='ratioReductionTakeaway'  value='<fmt:message key="restaurant.takeaway.basic.reduction"/>'></param>
						</applet>
						<applet name='printerApplet' archive='<c:out value="${pageContext.request.contextPath}"/>/jsp/commons/SPrinterApplet.jar' code="fr.mch.mdo.applets.PrinterApplet.class" mayscript="true">
							<param name='charset' value='<fmt:message key="applets.PrinterApplet.param.charset.value"/>'></param>
							<param name='linuxDriverName'  value='<fmt:message key="applets.PrinterApplet.param.linux.driver.name.value"/>'></param>
							<param name='linuxPortCom'  value='<fmt:message key="applets.PrinterApplet.param.linux.printer.portcom.value"/>'></param>
							<!-- param name='windowsDriverName'  value='<fmt:message key="applets.PrinterApplet.param.windows.driver.name.value"/>'></param-->
							<param name='windowsDriverName'  value='<fmt:message key="applets.PrinterApplet.param.linux.driver.name.value"/>'></param>
							<param name='windowsPortCom'  value='<fmt:message key="applets.PrinterApplet.param.windows.printer.portcom.value"/>'></param>
							<param name='serialportBauds'  value='<fmt:message key="applets.PrinterApplet.param.serialport.bauds.value"/>'></param>
							<param name='serialportBits'  value='<fmt:message key="applets.PrinterApplet.param.serialport.bits.value"/>'></param>
							<param name='serialportStopBits'  value='<fmt:message key="applets.PrinterApplet.param.serialport.stopbits.value"/>'></param>
							<param name='serialportParity'  value='<fmt:message key="applets.PrinterApplet.param.serialport.parity.value"/>'></param>
							<!--param name='packet'  value='<fmt:message key="applets.PrinterApplet.param.packet.value"/>'></param-->
							<!--param name='pause'  value='<fmt:message key="applets.PrinterApplet.param.pause.value"/>'></param-->
							<param name='packet'  value='40'></param>
							<param name='pause'  value='1500'></param>
							<param name='specialCaractersString'  value='<fmt:message key="applets.PrinterApplet.param.printer.specialCaracters.value"/>'></param>
							<param name='bindCaracteresSpeciauxString'  value='<fmt:message key="applets.PrinterApplet.param.printer.bindSpecialCaracters.value"/>'></param>
							<!--param name='debug' value='<fmt:message key="applets.PrinterApplet.param.debug.value"/>'></param-->
							<param name='debug' value='true'></param>
						</applet>
					<c:forEach var="localeLanguage" items="${userSession.localesLanguages}" varStatus="vs">
						<c:if test="${sessionScope.userSession.currentLanguage==localeLanguage.id.locale.id}" >
							<a class="image" accesskey='<fmt:message key="accesskey.flag${vs.index}"/>' href="#" onfocus="javascript:changeLanguage('<c:out value="${localeLanguage.id.locale.id}"/>')"  title='<c:out value="${localeLanguage.label}"/>'><img class="flagSelected" src="<c:out value="${pageContext.request.contextPath}"/>/images/flags/flag_<c:out value="${localeLanguage.id.locale.id}"/>.jpg" height="28" width="42"></a>
						</c:if>
						<c:if test="${sessionScope.userSession.currentLanguage!=localeLanguage.id.locale.id}" >
							<a class="image" accesskey='<fmt:message key="accesskey.flag${vs.index}"/>' href="#" onfocus="javascript:changeLanguage('<c:out value="${localeLanguage.id.locale.id}"/>')"  title='<c:out value="${localeLanguage.label}"/>'><img class="flagUnselected" src="<c:out value="${pageContext.request.contextPath}"/>/images/flags/flag_<c:out value="${localeLanguage.id.locale.id}"/>.jpg" height="28" width="42"></a>
						</c:if>
				 	</c:forEach>
				 	<b><font color="#0000FF"><fmt:message key="menuTop.jsp.welcome"/></font></b>
				 	<img src="<c:out value="${pageContext.request.contextPath}"/>/images/logo1.gif" height="30" width="30">
				 	</td>
				</tr>
				<tr>
				    <td class="border"><b><font color="#FFCC00">|</font></b></td>
					<td class="border" id='IDTD0' width="20%" align="center"><a accesskey='<fmt:message key="accesskey.module1"/>' id='IDA0' href='javascript:changeModule(0)' class="lienHTML"><fmt:message key="menuTop.jsp.manage.user"/></a></td>
				    <td class="border"><b><font color="#FFCC00">|</font></b></td>
					<td class="border" id='IDTD1' width="20%" align="center"><a accesskey='<fmt:message key="accesskey.module2"/>' id='IDA1' href='javascript:changeModule(1)' class="lienHTML"><fmt:message key="menuTop.jsp.manage.orders"/></a></td>
				    <td class="border"><b><font color="#FFCC00">|</font></b></td>
					<!--td class="border" id='IDTD2' width="16%" align="center"><a accesskey='<fmt:message key="accesskey.module3"/>' id='IDA2' href='javascript:changeModule(2)' class="lienHTML"><fmt:message key="menuTop.jsp.manage.tables"/></a></td>
				    <td class="border"><b><font color="#FFCC00">|</font></b></td-->
					<td class="border" id='IDTD3' width="20%" align="center"><a accesskey='<fmt:message key="accesskey.module4"/>' id='IDA3' href='javascript:changeModule(3)' class="lienHTML"><fmt:message key="menuTop.jsp.manage.day"/></a></td>
				    <td class="border"><b><font color="#FFCC00">|</font></b></td>
					<td class="border" id='IDTD4' width="20%" align="center"><a accesskey='<fmt:message key="accesskey.module5"/>' id='IDA4' href='javascript:changeModule(4)' class="lienHTML"><fmt:message key="menuTop.jsp.manage.month"/></a></td>
				    <td class="border"><b><font color="#FFCC00">|</font></b></td>
					<td class="border" id='IDTD5' width="20%" align="center"><a accesskey='<fmt:message key="accesskey.module6"/>' id='IDA5' href='javascript:changeModule(5)' class="lienHTML"><fmt:message key="menuTop.jsp.manage.menus"/></a></td>
			      	<td class="border"><b><font color="#FFCC00">|</font></b></td>
			    </tr>
		  	</table>
		</td>
	</tr>
	<tr height="4%">
		<td colspan="2" align="center">
			<!--div id='idDivTop' style='visibility:hidden'-->
			<div id='idDivTop'>
				<form name="topForm" action="<c:out value="${pageContext.request.contextPath}"/>/TableOrdersIFrame.do" method="post" target='IFrameData' onsubmit='return false'>
					<table border="0" width="100%">
						<tr height='25'> 
	    					<td width='20%' class="policeGray"><a accesskey='<fmt:message key="accesskey.date"/>' id='idDate' href='javascript:changeEntryDate()'></a></td>
						    <td width='10%' class="right">
						    	<label class="policeGray" id="idTableNumberLabel"><fmt:message key="menuTop.jsp.number.table"/><label>
						    </td>
					    	<td width='10%' class="center" id="idTDTableNumber">
						      	<input type='text' class="text" id='idTableNumber' name='tableNumber' onkeyup='processUserEntry(event, this)' maxlength='5' size='6'>
						    </td>
					    	<td width='10%' class="policeGray"></td>
						    <td width='20%' class="right"><label class="policeGray" id="idCustomerNumberLabel"></label></td>
					    	<td width='10%' class="center" id="idTDCustomerNumber"></td>
							<td width='20%' class="policeGray">
								<input type="checkbox" id="idAutoUpdateOrderLine" onclick="focusFirstInputText(document);focusFirstInputText(window.IFrameData.document)" name="autoUpdateOrderLine" checked="true"><label class="policeGray"><fmt:message key="tableOrdersIFrame.jsp.label.auto.update.order"/></label>
							</td>
						</tr>
					</table>
					<!--input type='hidden' name='hiddenTableNumber'-->
				</form>
				<form name="changeLanguageForm" action='<c:out value="${pageContext.request.contextPath}"/>/ChangeLanguage.do' method='post'>
		 			<input type="hidden" name="language"></input>
		 		</form>
				<form name="changeEntryDateForm" action="<c:out value="${pageContext.request.contextPath}"/>/DisplayJsp.do" method="post" target='pop_up' onsubmit='return false'>
					<input type="hidden" name="pageRequested" value="successChangeEntryDate">
					<input type="hidden" name='pageSize' value='<fmt:message key="productsListIFrame.jsp.select.byX1.value"/>'>
				</form>
			</div>			
		</td>
	</tr>	