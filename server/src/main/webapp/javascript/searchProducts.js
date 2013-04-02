<!--
var eventX;

function searchProductsList()
{
	document.forms[0].submit();
}

function checkPrice(cell)
{
	for (i=0;i<document.forms[0].searchedField.length;i++) 
	{
	    if (document.forms[0].searchedField[i].checked==true) 
    	{
    		if(document.forms[0].searchedField[i].value=="price")
    			isDecimal(cell);
      		break;
      	}	
    }
}

function processUserEntry(event, cell)
{
	eventX = event?event:window.event;

	removeQuote(cell);
	checkPrice(cell);
	cell.value = cell.value.toUpperCase();
	switch(eventX.keyCode)
	{     	  
		//13 équivaut � la touche ENTRER
		case 13 :
			searchProductsList();
		return; 

		//27 é�quivaut � la touche Echap
		case 27 :
			cancel();
		return;
	};
}

function cancel()
{
	if(window.parent.document.getElementById("idPageSize").value!="0")
		document.productsListForm.pageSize.value = window.parent.document.getElementById("idPageSize").value;
		
	if(document.productsListForm.searchedField && document.productsListForm.searchedField.value!='NONE')
	{
		document.productsListForm.action = contextPath+"/SearchProductsList.do";
	}
	
	document.productsListForm.submit();
}

function initPage()
{
	document.getElementById("idDivIFrame").style.height = screen.height;
	document.forms[0].searchedValue.focus();
}

-->









