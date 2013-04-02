var eventX;
var oldValueCode = "";
var oldValueLabel = "";
var oldValuePrice = "";

function submitCategory()
{
	if(document.forms[0].label.value=="")
	{
		alert(document.alertMessagesForm.saveOrUpdateCategoryAlertLabel.value);		
		document.forms[0].label.focus();		
		return;
	}

	//1) Ajouter un input text pour la saisie du numéro de table
	if(!window.parent.document.getElementById("idCustomerNumber"))
		resetTableNumber();
	else
		focusFirstInputText(window.parent.document);

	//2) Sauvegarder le produit
	document.forms[0].submit();
}

function processCell(cell)
{
	submitCategory();
}

function cancel()
{
	document.menusForm.submit();
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
		//13 équivaut à la touche ENTRER
		case 13 :
			processCell(cell);
		return; 
	
		//27 équivaut à la touche Echap
		case 27 :
			cancel();
		return;
	};
}

function initPage()
{
	document.getElementById("idDivIFrame").style.height = screen.height;
	document.forms[0].label.focus();
}
