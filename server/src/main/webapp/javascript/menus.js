<!--
function processMenusOptions(action)
{
	if(action=='STATS')
	{
		document.menusForm.action = contextPath+"/StatsConsumptionProductsListIFrame.do";	
	}
	else if(action=='CATEGORIES')
	{
		document.menusForm.action = contextPath+"/CategoriesListIFrame.do";
	}
	else if(action=='PRODUCTS')
	{
		document.menusForm.action = contextPath+"/ProductsListIFrame.do";
	}
	
	document.menusForm.optionSelected.value = action;
	document.menusForm.submit();	
}
-->