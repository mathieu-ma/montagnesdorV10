var eventX;

function submitUser()
{
	//1) Remettre ajouter un input text pour la saisie du numéro de table
	if(!window.parent.document.getElementById("idCustomerNumber"))
		resetTableNumber();
	else
		focusFirstInputText(window.parent.document);

	//2) Save user
	document.forms[0].submit();
}

function submitAndPrintUser()
{
	document.forms[0].actionDo.value = "print";
	submitUser();
}

function processCell(cell)
{
	submitUser();
}

function cancel()
{
	document.usersListForm.submit();
}

function checkEntry(cell)
{
	cell.value = cell.value.toUpperCase();

	removeQuote(cell);
}

function processUserEntry(event, cell)
{
	eventX = event?event:window.event;

	checkEntry(cell);

	switch(eventX.keyCode)
	{     	  
		//13 �quivaut � la touche ENTRER
		case 13 :
			processCell(cell);
		return; 

		//27 �quivaut � la touche Echap
		case 27 :
			cancel();
		return;
	};
}

function focusFirstElement()
{
	if(document.forms[0])
	{
		document.forms[0]["user.name"].focus();
		document.forms[0]["user.name"].focus();
	}
}

var initPageTimeout;
var counter = 0;
var MAX_COUNTER = 10;

function initPage()
{
	document.getElementById("idDivIFrame").style.height = screen.height;

	if(counter==MAX_COUNTER)
	{
		window.clearTimeout(initPageTimeout);
	}	
	else
	{
		focusFirstElement();
		counter++;
		initPageTimeout = window.setTimeout("initPage()",200);
	}
}
