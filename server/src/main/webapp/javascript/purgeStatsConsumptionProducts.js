var eventX;

function checkEntry(cell)
{
	if(cell.name!="password")
		cell.value = cell.value.toUpperCase();

	removeQuote(cell);

	if(cell.name=="quantity")
	{
		isDecimal(cell);
	}
}

function processUserEntry(event, cell)
{
	eventX = event?event:window.event;

	checkEntry(cell);
	
	switch(eventX.keyCode)
	{     	  
		//13 équivaut à la touche ENTRER
		case 13 :
			verifyPassword();
		return; 

		//27 équivaut à la touche Echap
		case 27 :
			cancel();
		return;

		//38 équivaut à la touche Up
		case 38 :
			up(cell);
		return;
			
		//40 équivaut à la touche Down
		case 40 :
			down(cell);
		return;
	};
}

function up(cell)
{
	switch(cell.name)
	{
		case "quantity":
			if(cell.value=="")
				cell.value = "0";
			cell.value = formatNumber(cell.value,2);
			document.forms[0].code.focus();
		break;
		case "password":
			document.forms[0].quantity.focus();
		break;
	}
}

function down(cell)
{
	switch(cell.name)
	{
		case "code":
			document.forms[0].quantity.focus();
		break;
		case "quantity":
			if(cell.value=="")
				cell.value = "0";
			cell.value = formatNumber(cell.value,2);
			document.forms[0].password.focus();
		break;
	}
}

function cancel()
{
	document.productsListForm.action = contextPath+"/StatsConsumptionProductsListIFrame.do";
	document.productsListForm.day.value = window.parent.document.getElementById("idDay").value;
	document.productsListForm.month.value = window.parent.document.getElementById("idMonth").value;	
	document.productsListForm.year.value = window.parent.document.getElementById("idYear").value;	
	document.productsListForm.submit();
}

function verifyPassword()
{
	document.forms[0].submit();
}

function initPage()
{
	document.getElementById("idDivIFrame").style.height = screen.height;
	document.forms[0].password.focus();
	document.forms[0].password.focus();	
}

