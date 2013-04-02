var eventX;

function processUserEntry(event, cell)
{
	eventX = event?event:window.event;

	switch(eventX.keyCode)
	{     	  
		//13 équivaut � la touche ENTRER
		case 13 :
			mergeTable();
		return; 

		//27 é�quivaut � la touche Echap
		case 27 :
			cancel();
		return;
	};
}

function cancel()
{
	if(focusFirstInputText(window.parent.document))
	{
		document.forms[0].action = contextPath+"/TablesListExcludeCashedTableIFrame.do";
		document.forms[0].submit();
	}	
	else
	{
//		document.modifyTableForm.pageRequested.value = "successOrderTable";
//		document.modifyTableForm.submit();
		document.forms[0].action = contextPath+"/TableOrdersIFrame.do";
		document.forms[0].submit();
	}	
}

function mergeTable()
{
	document.forms[0].elements[0].value = document.forms[0].elements[0].value.toUpperCase();
	document.forms[0].submit();
}

function initPage()
{
	document.getElementById("idDivIFrame").style.height = screen.height;
	
	if(document.forms[0] && document.forms[0].tableNumber)
		document.forms[0].elements[0].focus();
}

