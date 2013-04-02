<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/fn.tld" prefix="fn" %>

<jsp:include page='/jsp/commons/checkSessionAuthorization.jsp'/>

<html>
	<head> 
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" >
		<link rel='stylesheet' type='text/css' href='<c:out value="${pageContext.request.contextPath}"/>/css/montagnesdor.css'/>
		<title><fmt:message key="montagnesdor.welcome"/> - <fmt:message key="saveOrUpdateProduct.jsp.title"/></title>
		<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}"/>/javascript/commons.js"></script>
		<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}"/>/javascript/menus.js"></script>
		<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}"/>/javascript/productsList.js"></script>
		<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}"/>/javascript/saveOrUpdateProduct.js"></script>	
	</head>

	<body onload='initPage()'>
	<script language="javascript">
		//Ces variables sont utilisées dans le fichier saveOrUpdateProduct.js
		var contextPath = '<c:out value="${pageContext.request.contextPath}"/>';
		var defaultRGBColor = '<fmt:message key="applets.ColorPickerApplet.param.defaultRGBColor.value"/>';
	</script>
	
	<html:form method="post" action='/SaveOrUpdateProduct.do' onsubmit="return false">
		<table class="border" width="100%">
			<tr> 
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
		    	<td class="border" colspan="3" width="100%">
		    		<c:if test="${not empty product}">
						<fmt:message key="saveOrUpdateProduct.jsp.update.title"><fmt:param><c:out value="${param.code}"/></fmt:param></fmt:message>
					</c:if>
		    		<c:if test="${empty product}">
						<fmt:message key="saveOrUpdateProduct.jsp.add.title"/>
					</c:if>
    			</td>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
	  		</tr>
			<tr>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
				<td width="50%" class="border"><fmt:message key="saveOrUpdateProduct.jsp.code"/></td>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
				<td width="50%" class="border">
				<c:if test="${not empty product}">				
					<c:out value="${product.id}"/><input type="hidden" name="code" value="<c:out value="${product.id}"/>">
				</c:if>					
				<c:if test="${empty product}">
					<input class="text" type="text" style="width: 100%; height: 22" name="code" onkeyup='processUserEntry(event, this)' maxlength="5">
				</c:if>				
				</td>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
			</tr>
			<tr>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
				<td width="50%" class="border"><fmt:message key="saveOrUpdateProduct.jsp.label"/></td>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
				<td width="50%" class="border">
				<c:if test="${not empty product}">				
					<input class="text" type="text" style="width: 100%; height: 22" name="label" onkeyup='processUserEntry(event, this)' value="<c:out value="${product.labels[sessionScope.userSession.currentLanguage]}"/>" maxlength="60">
				</c:if>					
				<c:if test="${empty product}">
					<input class="text" type="text" style="width: 100%; height: 22" name="label" onkeyup='processUserEntry(event, this)' maxlength="60">
				</c:if>										
				</td>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
			</tr>
			<tr>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
				<td width="50%" class="border"><fmt:message key="saveOrUpdateProduct.jsp.price"/></td></td>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
				<td width="50%" class="border">
				<c:if test="${not empty product}">				
					<input class="cash" type="text" style="width: 100%; height: 22" name="price" onkeyup='processUserEntry(event, this)' value="<fmt:formatNumber pattern="0.00" var="price" value="${product.price}"/><c:out value="${fn:replace(price, ',', '.')}" />" maxlength="7">					
				</c:if>					
				<c:if test="${empty product}">
					<input class="cash" type="text" style="width: 100%; height: 22" name="price" onkeyup='processUserEntry(event, this)' maxlength="7">					
				</c:if>					
				</td>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
			</tr>
			<tr>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
				<td width="50%" class="border"><fmt:message key="saveOrUpdateProduct.jsp.vat"/></td></td>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
				<td width="50%" class="border">
					<c:if test="${not empty vatsList}" >
					<SELECT name="vatId" onchange="javascript:document.forms[0].label.focus();" style="width: 50%">
						<c:forEach var="vat" items="${vatsList}">
						<OPTION <c:if test="${not empty product && product.vat.id==vat.id}">selected</c:if> value="<c:out value="${vat.id}"/>">
							<fmt:formatNumber pattern="0.00" var="vatValue" value="${vat.value}"/><c:out value="${fn:replace(vatValue, ',', '.')}"/>
						</OPTION>
						</c:forEach>
					</SELECT>
					</c:if>
				</td>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
			</tr>
			<tr>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
				<td width="50%" class="border" style="background-repeat: no-repeat; background-position: center; background-image:url('<c:out value="${pageContext.request.contextPath}"/>/images/product_<c:out value="${param.code}"/>.gif')">&nbsp;</td>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
				<td width="50%" class="border">
					<applet name='colorPickerApplet' archive="<c:out value="${pageContext.request.contextPath}"/>/jsp/commons/SColorPickerApplet.jar" code="fr.montagnesdor.restaurant.applets.ColorPickerApplet.class" style="width: 200; height: 200;" mayscript="true">
						<param name='defaultRGBColor' value='<c:if test="${not empty product.colorRGB}"><c:out value="${product.colorRGB}"/></c:if><c:if test="${empty product.colorRGB}"><fmt:message key="applets.ColorPickerApplet.param.defaultRGBColor.value"/></c:if>'></param>
						<param name='defaultLabelRGBColor' value='<fmt:message key="applets.ColorPickerApplet.param.defaultLabelRGBColor.value"/>'></param>					
						<param name='defaultFont' value='<fmt:message key="applets.ColorPickerApplet.param.defaultFont.value"/>'></param>					
						<param name='startJavascriptFunction' value='initPage'> </param>
					</applet>
					<input type="hidden" name="colorRGB" value="<c:if test="${not empty product.colorRGB}"><c:out value="${product.colorRGB}"/></c:if><c:if test="${empty product.colorRGB}"><fmt:message key="applets.ColorPickerApplet.param.defaultRGBColor.value"/></c:if>">
				</td>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
			</tr>
			<tr>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
			   	<td class="border" width="50%"><a accesskey='<fmt:message key="saveOrUpdateProduct.jsp.accesskey.cancel"/>' href="javascript:cancel()" class="cancel"><fmt:message key="label.cancel"/></a></td>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
				<td class="border" width="50%"><a accesskey='<fmt:message key="saveOrUpdateProduct.jsp.accesskey.confirm"/>' href="javascript:submitProduct()" class="confirm" ><fmt:message key="label.confirm"/></a></td>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
			</tr>
			<c:if test="${not empty categoriesRelationListForProduct}">
			<tr>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
		    	<td  class="border" colspan="3" width="100%"><a href="javascript:displayCategories()" id="idDisplayOrHideCategories"><fmt:message key="saveOrUpdateProduct.jsp.add.categories.relation"/></a></td>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
	  		</tr>
	  		</c:if>
			<logic:messagesPresent>
			<tr>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
				<td colspan=3 class="border">
					<label class="policeGray">
						<html:errors property="saveOrUpdateProduct.jsp.alert.code"/>
						<html:errors property="saveOrUpdateProduct.jsp.alert.label"/>
						<logic:messagesPresent property="saveOrUpdateProduct.jsp.error.create.forbidden">
							<fmt:message key="saveOrUpdateProduct.jsp.error.create.forbidden"><fmt:param><c:out value="${param.code}"/></fmt:param></fmt:message>
						</logic:messagesPresent>
						<logic:messagesPresent property="saveOrUpdateProduct.jsp.error.update.forbidden">
							<fmt:message key="saveOrUpdateProduct.jsp.error.update.forbidden"><fmt:param><c:out value="${param.code}"/></fmt:param></fmt:message>
						</logic:messagesPresent>
					</label>
				</td>
				<td class="border"><b><font color="#FFCC00">|</font></b></td>
			</tr>	
			</logic:messagesPresent>
		</table>
		<div id="idDivQuantity" style="visibility: hidden">
			<table class="border" width="100%">
				<tr> 
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
			    	<td  class="border" colspan="3" width="100%"><fmt:message key="saveOrUpdateProduct.jsp.list.categories.relation"/></td>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
		  		</tr>
			<c:forEach var="categoryRelation" items="${categoriesRelationListForProduct}" varStatus="status">
				<c:if test="${status.index%2==0}">
				<tr>
				</c:if>	
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
		    		<td width="50%" <c:if test="${status.last && status.index%2==0}">colspan="3"</c:if> class="border" style="text-align: left"> 
					 	<input type="checkbox" name="category<c:out value="${categoryRelation.category.id}"/>" id="idCategory<c:out value="${categoryRelation.category.id}"/>" <c:if test="${not empty categoryRelation.id}">checked</c:if> value="<c:out value="${categoryRelation.category.id}"/>-<c:if test="${categoryRelation.quantity!=0}"><c:out value="${categoryRelation.quantity}"/></c:if>" onclick="verifyCategoryQuantinty(this)">
						<a id="<c:out value="${categoryRelation.category.id}"/>" href="javascript:displayCategoryQuantity('<c:out value="${categoryRelation.category.id}"/>')">
						<c:if test="${categoryRelation.category.labels.size!=0}">
							<c:forEach var="label" items="${categoryRelation.category.labels}">
								<c:if test="${label.key==sessionScope.userSession.currentLanguage}">
									<c:out value="${label.value}"/>
								</c:if>
							</c:forEach>
						</c:if>
						</a>
				<c:if test="${not empty categoryRelation.id && categoryRelation.quantity!=0}">
					<script language="Javascript">
						document.getElementById("<c:out value="${categoryRelation.category.id}"/>").style.color="blue";
					</script>
				</c:if>	
					</td>
				<c:if test="${status.index%2==1 || status.last}">
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
				</tr>
				</c:if>	
			</c:forEach>
			</table>
		
			<table class="border" width="100%">
				<tr>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
					<td width="70%" class="border"><fmt:message key="saveOrUpdateProduct.jsp.quantity.category.product"/> <label id="idLabelQuantity"><label></td>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
					<td width="30%" class="border">
						<input type="text" class="cash" style="width: 100%; height: 22" name="quantity" onkeyup='processUserEntry(event, this)' maxlength="7">					
						<input type="hidden" name="quantityHidden">					
					</td>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
				</tr>
				<tr>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
					<td width="50%" class="border">
						<a href="javascript:resetQuantity()"><fmt:message key="label.cancel"/></a>
					</td>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
					<td width="50%" class="border">
						<a href="javascript:validQuantity()" class="policeBlanc"><fmt:message key="label.ok"/></a>
					</td>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
				</tr>
			</table>
		</div>
		<c:if test="${not empty product}">
		<input type="hidden" name="actionDo" value="update">
		</c:if>					
		<c:if test="${empty product}">
		<input type="hidden" name="actionDo" value="create">
		</c:if>
		<input type="hidden" name='pageSize' value='<c:out value="${param.pageSize}"/>'>
		<input type="hidden" name='pageNumber' value='<c:out value="${param.pageNumber}"/>'>
		<input type="hidden" name='totalPages' value='<c:out value="${param.totalPages}"/>'>
		<input type="hidden" name='sortListBy' value='<c:out value="${param.sortListBy}"/>'>
		<input type="hidden" name='sortMonotony' value='<c:out value="${param.sortMonotony}"/>'>
		<c:if test="${not empty param.searchedField}">
		<input type="hidden" name='searchedField' value='<c:out value="${param.searchedField}"/>'>
		<input type="hidden" name='searchedValue' value='<c:out value="${param.searchedValue}"/>'>
		</c:if>
	</html:form>

	<form name="productsListForm" action="<c:out value="${pageContext.request.contextPath}"/>/ProductsListIFrame.do" method="post" onsubmit="return false" target="IFrameData">
		<input type="hidden" name="pageRequested" value="successSearchProducts">		
		<input type="hidden" name="actionDo" value="DISPLAY">		
		<input type="hidden" name='pageSize' value='<fmt:message key="productsListIFrame.jsp.select.byX1.value"/>'>
		<input type="hidden" name='pageNumber' value='<c:out value="${param.pageNumber}"/>'>
		<input type="hidden" name='totalPages' value='<c:out value="${param.totalPages}"/>'>
		<input type="hidden" name='sortListBy' value='<c:out value="${param.sortListBy}"/>'>
		<input type="hidden" name='sortMonotony' value='<c:out value="${param.sortMonotony}"/>'>
		<input type="hidden" name='code' value='<c:out value="${param.code}"/>'>
		<c:if test="${not empty param.searchedField}">
		<input type="hidden" name='searchedField' value='<c:out value="${param.searchedField}"/>'>
		<input type="hidden" name='searchedValue' value='<c:out value="${param.searchedValue}"/>'>
		</c:if>
	</form>
	
	<form name="menusForm" action="<c:out value="${pageContext.request.contextPath}"/>/ProductsListIFrame.do" method="post" onsubmit="return false">
		<input type="hidden" name="optionSelected" value="PRODUCTS">
	</form>
	
	<form name="alertMessagesForm" onsubmit="return false">
		<input type="hidden" name="saveOrUpdateProductAlertCode" value="<fmt:message key="saveOrUpdateProduct.jsp.alert.code"/>">
		<input type="hidden" name="saveOrUpdateProductAlertLabel" value="<fmt:message key="saveOrUpdateProduct.jsp.alert.label"/>">
		<input type="hidden" name="alertLabelNegativeQuantity" value="<fmt:message key="alert.label.negative.quantity"/>">		
		<input type="hidden" name="alertLabelNegativePrice" value="<fmt:message key="alert.label.negative.price"/>">
		<input type="hidden" name="labelAddCategoriesRelation" value="<fmt:message key="saveOrUpdateProduct.jsp.add.categories.relation"/>">		
		<input type="hidden" name="labelHideCategoriesRelation" value="<fmt:message key="saveOrUpdateProduct.jsp.hide.categories.relation"/>">		
	</form>
	
	<jsp:include page='/jsp/commons/includeDivIFrame.jsp'/>			

	</body>
</html>