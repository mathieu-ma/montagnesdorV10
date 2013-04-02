<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/fn.tld" prefix="fn" %>

<jsp:include page='/jsp/commons/checkSessionAuthorization.jsp'/>

<html>
	<head> 
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" >
		<link rel="stylesheet" type="text/css" href="<c:out value="${pageContext.request.contextPath}"/>/css/montagnesdor.css" />
		<title><fmt:message key="montagnesdor.welcome"/> - <fmt:message key="productsListIFrame.jsp.title"/></title>
		<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}"/>/javascript/commons.js"></script>
		<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}"/>/javascript/menus.js"></script>
		<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}"/>/javascript/productsList.js"></script>
	</head>
	<body id="idBody" onload='initPageIFrame()'>
		<script language="javascript">
			//Cette variable est utilisée dans le fichier productsList.js
			var contextPath = '<c:out value="${pageContext.request.contextPath}"/>';
		</script>
		<table class="border" width='100%'>
		<c:set var="numberProducts" value="0"/>
		<c:if test="${not empty categoriesRelationList}">
			<c:forEach var="categoryRelation" items="${categoriesRelationList}" varStatus="status">
				<c:if test="${status.last}" >
					<c:set var="numberProducts" value="${status.index+1}"/>
				</c:if>	
				<tr onmouseover="this.className='over';" onmouseout="this.className='default';" style="background-color: #<c:out value="${categoryRelation.product.colorRGB}"/>"> 
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
		    		<td class="border" style="width: 8%;"> 
			    		<a href="javascript:processUserClick('DISPLAY', '<c:out value="${categoryRelation.product.id}"/>')" title="<fmt:message key="productsListIFrame.jsp.title.modify"><fmt:param><c:out value="${categoryRelation.product.id}"/></fmt:param></fmt:message>"><c:out value="${categoryRelation.product.id}"/></a>
					</td>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
		    		<td class="border" style="width: 47%;">
						<c:out value="${categoryRelation.product.labels[sessionScope.userSession.currentLanguage]}"/>
					</td>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
		    		<td class="price" style="width: 8%;"><fmt:formatNumber pattern="0.00" var="price" value="${categoryRelation.product.price}"/><c:out value="${fn:replace(price, ',', '.')}" /></td>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
		    		<td class="price" style="width: 8%;"><fmt:formatNumber pattern="0.00" var="vat" value="${categoryRelation.product.vat.value}"/><c:out value="${fn:replace(vat, ',', '.')}" /></td>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
		    		<td class="border" style="width: 29%;"> 
						<a href="javascript:processUserClick('DELETE', '<c:out value="${categoryRelation.product.id}"/>')" title="<fmt:message key="productsListIFrame.jsp.title.remove"><fmt:param><c:out value="${categoryRelation.product.id}"/></fmt:param></fmt:message>"><fmt:message key="productsListIFrame.jsp.label.remove"/></a>
					</td>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
				</tr>
			</c:forEach>	
		</c:if>
		<c:if test="${param.searchedField=='label' && empty totalPages}" >
			<c:forEach var="productLanguage" items="${productsList}" varStatus="status">
				<c:if test="${productLanguage.id.product.productSpecialCode.code=='NOTHING'}">
					<c:set var="numberProducts" value="${numberProducts+1}"/>
					<tr onmouseover="this.className='over';" onmouseout="this.className='default';" style="background-color: #<c:out value="${productLanguage.id.product.colorRGB}"/>"> 
						<td class="border"><b><font color="#FFCC00">|</font></b></td>
			    		<td class="border" style="width: 8%;"> 
				    		<a href="javascript:processUserClick('DISPLAY', '<c:out value="${productLanguage.id.product.id}"/>')" title="<fmt:message key="productsListIFrame.jsp.title.modify"><fmt:param><c:out value="${productLanguage.id.product.id}"/></fmt:param></fmt:message>"><c:out value="${productLanguage.id.product.id}"/></a>
						</td>
						<td class="border"><b><font color="#FFCC00">|</font></b></td>
			    		<td class="border" style="width: 47%;">
							<c:out value="${productLanguage.label}"/>
						</td>
						<td class="border"><b><font color="#FFCC00">|</font></b></td>
			    		<td class="price" style="width: 9%;"><fmt:formatNumber pattern="0.00" var="price" value="${productLanguage.id.product.price}"/><c:out value="${fn:replace(price, ',', '.')}" /></td>
						<td class="border"><b><font color="#FFCC00">|</font></b></td>
			    		<td class="price" style="width: 8%;"><fmt:formatNumber pattern="0.00" var="vat" value="${productLanguage.id.product.vat.value}"/><c:out value="${fn:replace(vat, ',', '.')}" /></td>
						<td class="border"><b><font color="#FFCC00">|</font></b></td>
			    		<td class="border" style="width: 29%;"> 
							<a href="javascript:processUserClick('DELETE', '<c:out value="${productLanguage.id.product.id}"/>')" title="<fmt:message key="productsListIFrame.jsp.title.remove"><fmt:param><c:out value="${productLanguage.id.product.id}"/></fmt:param></fmt:message>"><fmt:message key="productsListIFrame.jsp.label.remove"/></a>
						</td>
						<td class="border"><b><font color="#FFCC00">|</font></b></td>
					</tr>
				</c:if>					
			</c:forEach>	
		</c:if>
		<c:if test="${param.searchedField!='label' || not empty totalPages}" >
			<c:forEach var="product" items="${productsList}" varStatus="status">
				<c:if test="${status.last}" >
					<c:set var="numberProducts" value="${status.index+1}"/>
				</c:if>	
				<tr onmouseover="this.className='over';" onmouseout="this.className='default';" style="background-color: #<c:out value="${product.colorRGB}"/>"> 
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
		    		<td class="border" style="width: 8%;"> 
			    		<a href="javascript:processUserClick('DISPLAY', '<c:out value="${product.id}"/>')" title="<fmt:message key="productsListIFrame.jsp.title.modify"><fmt:param><c:out value="${product.id}"/></fmt:param></fmt:message>"><c:out value="${product.id}"/></a>
					</td>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
		    		<td class="border" style="width: 47%;">
						<c:out value="${product.labels[sessionScope.userSession.currentLanguage]}"/>	    		
					</td>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
		    		<td class="price" style="width: 8%;"><fmt:formatNumber pattern="0.00" var="price" value="${product.price}"/><c:out value="${fn:replace(price, ',', '.')}" /></td>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
		    		<td class="price" style="width: 8%;"><fmt:formatNumber pattern="0.00" var="vat" value="${product.vat.value}"/><c:out value="${fn:replace(vat, ',', '.')}" /></td>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
		    		<td class="border" style="width: 29%;"> 
						<a href="javascript:processUserClick('DELETE', '<c:out value="${product.id}"/>')" title="<fmt:message key="productsListIFrame.jsp.title.remove"><fmt:param><c:out value="${product.id}"/></fmt:param></fmt:message>"><fmt:message key="productsListIFrame.jsp.label.remove"/></a>
					</td>
					<td class="border"><b><font color="#FFCC00">|</font></b></td>
				</tr>
			</c:forEach>	
		</c:if>
		</table>
		<form name="productsListForm" action="<c:out value="${pageContext.request.contextPath}"/>/ProductsListIFrame.do" method="post" onsubmit="return false" target="IFrameData">
			<input type="hidden" name="pageRequested" value="successSearchProducts">
			<input type="hidden" name='pageSize' value='<c:if test="${not empty pageSize && empty param.size}"><c:out value="${pageSize}"/></c:if><c:if test="${not empty param.size}"><c:out value="${param.pageSize}"/></c:if>'>
			<input type="hidden" name='pageNumber' value='<c:out value="${pageNumber}"/>'>
			<input type="hidden" name='totalPages' value='<c:out value="${totalPages}"/>'>
			<input type="hidden" name='sortListBy' <c:if test="${not empty sortListBy}">value='<c:out value="${sortListBy}"/>'</c:if>>
			<input type="hidden" name='sortMonotony' <c:if test="${not empty sortMonotony}">value='<c:out value="${sortMonotony}"/>'</c:if>>
			<input type="hidden" name='code'>
			<c:if test="${not empty param.searchedField}">
			<input type="hidden" name='searchedField' value='<c:out value="${param.searchedField}"/>'>
			<input type="hidden" name='searchedValue' value='<c:out value="${param.searchedValue}"/>'>
			</c:if>
			<input type="hidden" name='actionDo' value='DISPLAY'>
		</form>
		<form name="menusForm" action="<c:out value="${pageContext.request.contextPath}"/>/ProductsListIFrame.do" method="post" onsubmit="return false">
			<input type="hidden" name="optionSelected" value="PRODUCTS">
		</form>
		<form name="alertMessagesForm" onsubmit="return false">
			<input type="hidden" name="productsListIFrameAlertNumberPage1" value="<fmt:message key="productsListIFrame.jsp.alert.number.page1"/>">
			<input type="hidden" name="productsListIFrameAlertNumberPage2" value="<fmt:message key="productsListIFrame.jsp.alert.number.page2"/>">
			<input type="hidden" name="productsListIFrameConfirmDeleteProduct" value="<fmt:message key="productsListIFrame.jsp.confirm.delete.product"/>">
		</form>
		<jsp:include page='/jsp/commons/includeDivIFrame.jsp'/>
		<div style='position: relative; height: 0;visibility:hidden'>
			<table width="100%" height="100%" cellspacing="0" cellpadding="0" border="0" >
				<tr>
					<td id="idTdDataOptions" width="15%" valign="top" title='Data Options'>
						<table class="border" width="95%">
							<c:if test="${empty param.optionSelected || param.optionSelected=='PRODUCTS'}" >
							<tr> 
						    	<td class="selectedWhite">
						    		<a class="selectedWhite" href="javascript:window.IFrameData.processMenusOptions('PRODUCTS');" title="<fmt:message key="productsListIFrame.jsp.title.products"/>"><fmt:message key="productsListIFrame.jsp.list.products"/></a>
						    	</td>
							</tr>
							<tr> 
								<td class="border">
								  <a href="javascript:window.IFrameData.processMenusOptions('CATEGORIES');" title="<fmt:message key="categoriesListIFrame.jsp.title.categories"/>"><fmt:message key="categoriesListIFrame.jsp.list.categories"/></a>
							    </td>
							</tr>
							<tr> 
								<td class="border">
								  <a href="javascript:window.IFrameData.processMenusOptions('STATS');" title="<fmt:message key="statsConsumptionProductsListIFrame.jsp.title.stats"/>"><fmt:message key="statsConsumptionProductsListIFrame.jsp.list.stats"/></a>
							    </td>
							</tr>
							</c:if>
							<c:if test="${not empty param.optionSelected && param.optionSelected=='CATEGORIES'}" >
							<tr> 
						    	<td class="border"> 
							      <a href="javascript:window.IFrameData.processMenusOptions('PRODUCTS');" title="<fmt:message key="productsListIFrame.jsp.title.products"/>"><fmt:message key="productsListIFrame.jsp.list.products"/></a>
							    </td>
							</tr>
							<tr> 
								<td class="selectedWhite">
								  <a class="selectedWhite" href="javascript:window.IFrameData.processMenusOptions('CATEGORIES');" title="<fmt:message key="categoriesListIFrame.jsp.title.categories"/>"><fmt:message key="categoriesListIFrame.jsp.list.categories"/></a>
							    </td>
							</tr>
							<tr> 
								<td class="border">
								  <a href="javascript:window.IFrameData.processMenusOptions('STATS');" title="<fmt:message key="statsConsumptionProductsListIFrame.jsp.title.stats"/>"><fmt:message key="statsConsumptionProductsListIFrame.jsp.list.stats"/></a>
							    </td>
							</tr>
							</c:if>
							<c:if test="${not empty param.optionSelected && param.optionSelected=='STATS'}" >
							<tr> 
						    	<td class="border"> 
							      <a href="javascript:window.IFrameData.processMenusOptions('PRODUCTS');" title="<fmt:message key="productsListIFrame.jsp.title.products"/>"><fmt:message key="productsListIFrame.jsp.list.products"/></a>
							    </td>
							</tr>
							<tr> 
								<td class="border">
								  <a href="javascript:window.IFrameData.processMenusOptions('CATEGORIES');" title="<fmt:message key="categoriesListIFrame.jsp.title.categories"/>"><fmt:message key="categoriesListIFrame.jsp.list.categories"/></a>
							    </td>
							</tr>
							<tr> 
								<td class="selectedWhite">
								  <a class="selectedWhite" href="javascript:window.IFrameData.processMenusOptions('STATS');" title="<fmt:message key="statsConsumptionProductsListIFrame.jsp.title.stats"/>"><fmt:message key="statsConsumptionProductsListIFrame.jsp.list.stats"/></a>
							    </td>
							</tr>
							</c:if>
						</table>
					</td>
				</tr>					
				<tr>
					<td  id="idTdDataHeader" height="5%" title='Data Header'>
						<table class="border" width='100%' id="idTableDataHeader">
							<tr>
							    <c:if test="${not empty totalPages}" >
							    <td class="border"><b><font color="#FFCC00">|</font></b></td>
							    <td class="border" style="width: 8%;"><a <c:if test="${sortListBy=='id'}">class='linkSelected'</c:if> href="javascript:window.IFrameData.processList('id');" title="<fmt:message key="productsListIFrame.jsp.order.code"/>"><fmt:message key="productsListIFrame.jsp.label.code"/></a></td>
							    <td class="border"><b><font color="#FFCC00">|</font></b></td>
							    <td class="border" style="width: 47%;"><label class="policeGray"><fmt:message key="productsListIFrame.jsp.label.label"/></label></td>
							    <td class="border"><b><font color="#FFCC00">|</font></b></td>
							    <td class="border" style="width: 8%;"><a <c:if test="${sortListBy=='price'}">class='linkSelected'</c:if> href="javascript:window.IFrameData.processList('price');" title="<fmt:message key="productsListIFrame.jsp.order.price"/>"><fmt:message key="productsListIFrame.jsp.label.price"/></a></td>
							    <td class="border"><b><font color="#FFCC00">|</font></b></td>
							    <td class="border" style="width: 8%;"><label class="policeGray"><fmt:message key="productsListIFrame.jsp.label.vat"/></label></td>
							    <td class="border"><b><font color="#FFCC00">|</font></b></td>
							    </c:if>
								<c:if test="${empty totalPages}" >
							    <td class="border"><b><font color="#FFCC00">|</font></b></td>
							    <td class="border" style="width: 8%;"><label class="policeGray"><fmt:message key="productsListIFrame.jsp.label.code"/></label></td>
							    <td class="border"><b><font color="#FFCC00">|</font></b></td>
							    <td class="border" style="width: 47%;"><label class="policeGray"><fmt:message key="productsListIFrame.jsp.label.label"/></label></td>
							    <td class="border"><b><font color="#FFCC00">|</font></b></td>
							    <td class="border" style="width: 8%;"><label class="policeGray"><fmt:message key="productsListIFrame.jsp.label.price"/></label></td>
							    <td class="border"><b><font color="#FFCC00">|</font></b></td>
							    <td class="border" style="width: 8%;"><label class="policeGray"><fmt:message key="productsListIFrame.jsp.label.vat"/></label></td>
							    <td class="border"><b><font color="#FFCC00">|</font></b></td>
							    </c:if>
							    <td class="border" style="width: 29%;">
							 		<SELECT id='idPageSize' style="width:100%" name="pageSize" onchange="javascript:window.IFrameData.processSelect(this)">
										<OPTGROUP label="<fmt:message key="productsListIFrame.jsp.select.pages"/>">
											<OPTION <c:if test="${pageSize=='10'}">selected</c:if> value="<fmt:message key="productsListIFrame.jsp.select.byX1.value"/>"><fmt:message key="productsListIFrame.jsp.select.byX1"/></OPTION>
											<OPTION <c:if test="${pageSize=='15'}">selected</c:if> value="<fmt:message key="productsListIFrame.jsp.select.byX2.value"/>"><fmt:message key="productsListIFrame.jsp.select.byX2"/></OPTION>
											<OPTION <c:if test="${pageSize=='20'}">selected</c:if> value="<fmt:message key="productsListIFrame.jsp.select.byX3.value"/>"><fmt:message key="productsListIFrame.jsp.select.byX3"/></OPTION>
									     </OPTGROUP>
									    <c:if test="${not empty categoriesList}" >
								 		<OPTGROUP label="<fmt:message key="productsListIFrame.jsp.select.categories"/>">
										<c:forEach var="category" items="${categoriesList}">
											<c:set var="categoryPageSizeValue" scope="page">CAT<c:out value="${category.id}"/></c:set>
											<OPTION <c:if test="${param.pageSize==categoryPageSizeValue}">selected</c:if> value="<c:out value="${categoryPageSizeValue}"/>">
												<c:if test="${not empty category.labels[sessionScope.userSession.currentLanguage]}">
													<c:out value="${category.labels[sessionScope.userSession.currentLanguage]}"/>
												</c:if>
												<c:if test="${empty category.labels[sessionScope.userSession.currentLanguage]}">
													<c:forEach var="label" items="${category.labels}" varStatus="status">
														<c:if test="${not empty label.value}">
															<c:set var="categoryLabel" value="${label.value}"/>
														</c:if>
													</c:forEach>
													<c:out value="${categoryLabel}"/>
												</c:if>
											</OPTION>
										</c:forEach>
								 		</OPTGROUP>											
										</c:if >
										<c:if test="${(empty totalPages && !fn:startsWith(param.pageSize, 'CAT'))}" >
										<OPTGROUP label="<fmt:message key="productsListIFrame.jsp.select.others"/>">
											<OPTION selected value="0"><fmt:message key="productsListIFrame.jsp.select.products.found"/></OPTION>
										</OPTGROUP>
										</c:if>
		 							</SELECT>
								</td>
							    <td class="border"><b><font color="#FFCC00">|</font></b></td>
							</tr>    							
						</table>							
					</td>
				</tr>	
				<tr>
					<td id="idTdDataFooter" height="5%"  title='Data Footer'>
						<table width="100%">
						  <tr>
						    <td style="text-align: right; width:10%"><label class="policeGray"><fmt:message key="productsListIFrame.jsp.products.number"/></label></td>
						    <td class="footer" style="text-align: left; width:10%"><c:out value="${numberProducts}" /><c:if test="${not empty pageSize}">/<c:out value="${productsSum}"/></c:if></td>
						    <td style="text-align: right; width:15%"><label><a href="javascript:window.IFrameData.searchProducts()" title="<fmt:message key="productsListIFrame.jsp.title.search.products"/>"><fmt:message key="productsListIFrame.jsp.label.search.products"/></a></label></td>
						    <td style="text-align: center; width:14%"><label><a href="javascript:window.IFrameData.processUserClick('DISPLAY', '')" title="<fmt:message key="productsListIFrame.jsp.title.add.product"/>"><fmt:message key="productsListIFrame.jsp.label.add.product"/></a></label></td>
						    <c:if test="${not empty totalPages && totalPages!=0}">
							    <td style="text-align: right; width:10%"><label class="policeGray"><fmt:message key="productsListIFrame.jsp.label.search.page"/></label></td>
							    <td class="footer"><input class="yellowPage" type='text' onkeyup="window.IFrameData.gotoPage(event, this, '<c:out value="${totalPages}"/>')" maxlength='3'></td>
									<c:if test="${pageNumber=='1'}">						    
							    <td style="text-align: right; width:11%"><label class="policeGray"><fmt:message key="productsListIFrame.jsp.label.page.begin"/></label></td>
									</c:if>
									<c:if test="${pageNumber!='1'}">						    
							    <td style="text-align: right; width:11%"><label><a href="javascript:window.IFrameData.previousPage()" title="<fmt:message key="productsListIFrame.jsp.title.previous"/>"><fmt:message key="productsListIFrame.jsp.label.previous"/></a></label></td>
									</c:if>
							    <td style="text-align: center; width:15%">
							    	<table>
										<tr>
										    <td style="text-align: right; width:10%"><label class="policeGray"><fmt:message key="productsListIFrame.jsp.label.page"/></label></td>
								    		<td class="footer" style="text-align: left; width:10%	"><c:out value="${pageNumber}"/>/<c:out value="${totalPages}"/></td>
								    	</tr>	
									</table>
								</td>	
									<c:if test="${pageNumber==totalPages}">						    
							    <td style="text-align: left; width:10%"><label class="policeGray"><fmt:message key="productsListIFrame.jsp.label.page.end"/></label></td>
									</c:if>
									<c:if test="${pageNumber!=totalPages}">						    
							    <td style="text-align: left; width:10%"><label><a href="javascript:window.IFrameData.nextPage()" title="<fmt:message key="productsListIFrame.jsp.title.next"/>"><fmt:message key="productsListIFrame.jsp.label.next"/></a></label></td>
									</c:if>
							</c:if>
						    <c:if test="${empty totalPages || totalPages==0}" >
							    <td style="text-align: right; width:10%"></td>
							    <td class="footer"></td>
							    <td style="text-align: right; width:11%"></td>
							    <td style="text-align: center; width:15%">
							    	<table>
										<tr>
										    <td style="text-align: right; width:10%"></td>
								    		<td class="footer" style="text-align: left; width:10%"></td>
								    	</tr>	
									</table>
								</td>	
							    <td style="text-align: left; width:10%"></td>
							</c:if>
						  </tr>
						</table>
					</td>
				</tr>	
			</table>			
		</div>
	</body>
</html>