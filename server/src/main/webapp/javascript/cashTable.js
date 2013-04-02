var eventX;
var oldValueCash = "0.00";
var oldValueTicket = "0.00";
var oldValueCheque = "0.00";
var oldValueCard = "0.00";

function deleteCashing(cashingId)
{
	document.modifyTableForm.action = contextPath+"/DeleteCashing.do";
	document.modifyTableForm.submit();
}

function submitCashTable()
{
	if(document.getElementById("idAmountPaid").innerHTML!="0.00")
	{
		alert(document.alertMessagesForm.cashTableJspAlertAmountPaid.value);
		document.forms[0].cash.focus();
		return;
	}
	if(document.getElementById("idAmountReturned").innerHTML!="0.00")
	{
		alert(document.alertMessagesForm.cashTableJspAlertAmountReturned.value);		
		document.forms[0].cash.focus();		
		return;
	}
	if(document.getElementById("idAmountPaid").innerHTML=="0.00" && document.getElementById("idAmountReturned").innerHTML=="0.00")
	{
		//1) Remettre ajouter un input text pour la saisie du numéro de table
		if(!window.parent.document.getElementById("idCustomerNumber"))
			resetTableNumber();
		else
			focusFirstInputText(window.parent.document);
		document.forms[0].newDate.value = window.parent.document.tableOrdersApplet.getEntryDate();
		
		//2) Formater tous les prix avec la précision demandée
		document.forms[0].cash.value = formatNumber(document.forms[0].cash.value, 2);
		document.forms[0].ticket.value = formatNumber(document.forms[0].ticket.value, 2);
		document.forms[0].cheque.value = formatNumber(document.forms[0].cheque.value, 2);		
		document.forms[0].card.value = formatNumber(document.forms[0].card.value, 2);		
		
		//3) Encaisser la table
		document.forms[0].action = contextPath+"/CashTable.do";
		document.forms[0].submit();
	}
}

function submitCashTableFromDaylyReceipts()
{
	if(document.getElementById("idAmountPaid").innerHTML!="0.00")
	{
		alert(document.alertMessagesForm.cashTableJspAlertAmountPaid.value);
		document.forms[0].cash.focus();
		return;
	}
	if(document.getElementById("idAmountReturned").innerHTML!="0.00")
	{
		alert(document.alertMessagesForm.cashTableJspAlertAmountReturned.value);		
		document.forms[0].cash.focus();		
		return;
	}
	if(document.getElementById("idAmountPaid").innerHTML=="0.00" && document.getElementById("idAmountReturned").innerHTML=="0.00")
	{
		document.forms[0].newDate.value = window.parent.document.tableOrdersApplet.getEntryDate();
		
		document.forms[0].action = contextPath+"/CashTable.do";
		document.forms[0].submit();
	}
}

function processSelect(select)
{
	document.modifyTableForm.filterList.value = select.value;
	document.modifyTableForm.submit();
}

function processMenusOptions(action)
{
	document.modifyTableForm.optionSelected.value = action;
	document.modifyTableForm.submit();
}

function calculateAmountReturned()
{
	var result = 0;
	if(document.forms[0].cash.value=="")
		result += 0;
	else
		result += parseFloat(document.forms[0].cash.value);
	if(document.forms[0].ticket.value=="")
		result += 0;
	else
		result += parseFloat(document.forms[0].ticket.value);
	if(document.forms[0].cheque.value=="")
		result += 0;
	else
		result += parseFloat(document.forms[0].cheque.value);
	if(document.forms[0].card.value=="")
		result += 0;
	else
		result += parseFloat(document.forms[0].card.value);

	return result;
}

function refreshAmountReturned()
{
	if(!document.forms[0].unpaidCheck.checked)
	{
		var amountReturned = parseFloat(calculateAmountReturned());
		
		amountReturned = formatNumber(amountReturned-parseFloat(document.getElementById("idAmountPay").innerHTML),2);

		if(parseFloat(amountReturned)>=0)
		{
			document.getElementById("idAmountReturned").innerHTML = amountReturned;
			document.getElementById("idAmountPaid").innerHTML = "0.00";
		}
		else
		{
			document.getElementById("idAmountReturned").innerHTML = "0.00";
			document.getElementById("idAmountPaid").innerHTML = formatNumber(-parseFloat(amountReturned), 2);
		}
	}
}

function processCell(cell)
{
	if(cell.value!="")
		cell.value = formatNumber(cell.value,2);
		
	submitCashTable();
}

function cancel()
{
	if(focusFirstInputText(window.parent.document))
	{
		document.modifyTableForm.filterList.value = window.parent.document.getElementById("idFilterList").value;
		document.modifyTableForm.action = contextPath+"/TablesListExcludeCashedTableIFrame.do";
		document.modifyTableForm.submit();
	}	
	else
	{
		document.forms[0].action = contextPath+"/TableOrdersIFrame.do";
		document.forms[0].submit();
	}	
}

function cancelFromDaylyReceipts()
{
	document.modifyTableForm.submit();
}

function moveCursorToRight(cell)
{
	cell.value = cell.value.toUpperCase()+' ';
	cell.value = cell.value.substring(0, cell.value.length-1);
}

function up(cell)
{
	cell.isValueEmpty = 'false';
	switch(cell.name)
	{
		case "ticket":
			document.forms[0].cash.focus();
			moveCursorToRight(document.forms[0].cash);
		break;
		case "cheque":
			document.forms[0].ticket.focus();
			moveCursorToRight(document.forms[0].ticket);
		break;
		case "card":
			document.forms[0].cheque.focus();
			moveCursorToRight(document.forms[0].cheque);
		break;
	}

	if(cell.value!="")
		cell.value = formatNumber(cell.value,2);
}

function down(cell)
{
	cell.isValueEmpty = 'false';
	switch(cell.name)
	{
		case "cash":
			document.forms[0].ticket.focus();
			moveCursorToRight(document.forms[0].ticket);
		break;
		case "ticket":
			document.forms[0].cheque.focus();
			moveCursorToRight(document.forms[0].cheque);
		break;
		case "cheque":
			document.forms[0].card.focus();
			moveCursorToRight(document.forms[0].card);
		break;
	}

	if(cell.value!="")
		cell.value = formatNumber(cell.value,2);
}

function refreshInputText()
{
	var inputs = document.getElementsByTagName("input");
	for(var i=0; i< inputs.length; i++)
	{
		var input = inputs.item(i);
		if(input.type.toUpperCase()=="TEXT")
		{
			if(input.value=="")
				input.value = formatNumber(0, 2);
			input.value = formatNumber(input.value, 2);
		}
	}
}

function autoCompletion(paymentType)
{
	refreshInputText();

	var cell = eval("document.forms[0]."+paymentType);
	var amount = parseFloat(document.getElementById("idAmountPaid").innerHTML);
	if(amount>=0)
	{
		if(cell.value!="")
			amount += parseFloat(cell.value);
		cell.value = formatNumber(amount, 2);
		refreshAmountReturned();

	}

	amount = parseFloat(document.getElementById("idAmountReturned").innerHTML);
	if(paymentType=="cash" && amount>0)
	{
		if(cell.value!="")
		{	
			var cash = parseFloat(cell.value);
			if(cash<0)
				amount -= cash;
			else
				amount = cash - amount;
		}
		cell.value = formatNumber(amount, 2);
		refreshAmountReturned();
	}
	
	refreshOldValues();	
	cell.focus();
	moveCursorToRight(cell);
}

function checkUnpaid(check)
{
	refreshInputText();
	
	var unpaid = "0.00";
	if(check.checked)
	{
		if(document.getElementById("idAmountPaid").innerHTML=="0.00")
		{
			alert(document.alertMessagesForm.cashTableJspAlertUnpaidOptionForbidden.value);
			check.checked = false;
			document.forms[0].cash.focus();
			moveCursorToRight(document.forms[0].cash);
		}
		else
		{
			unpaid = document.getElementById("idAmountPaid").innerHTML;	
			document.getElementById("idAmountPaid").innerHTML="0.00";
			document.forms[0].cash.readOnly=true;
			document.forms[0].ticket.readOnly=true;
			document.forms[0].cheque.readOnly=true;
			document.forms[0].card.readOnly=true;
		}
	}
	else
	{
		refreshAmountReturned();
		document.forms[0].cash.readOnly=false;
		document.forms[0].ticket.readOnly=false;
		document.forms[0].cheque.readOnly=false;
		document.forms[0].card.readOnly=false;
	}

	refreshOldValues();
	document.getElementById("idUnpaid").innerHTML = unpaid;
	document.forms[0].unpaid.value = unpaid;
	document.forms[0].cash.value = document.forms[0].cash.value.toUpperCase();
	document.forms[0].cash.focus();
}

function refreshOldValues()
{
	oldValueCash = formatNumber(document.forms[0].cash.value, 2);
	oldValueTicket = formatNumber(document.forms[0].ticket.value, 2);
	oldValueCheque = formatNumber(document.forms[0].cheque.value, 2);
	oldValueCard = formatNumber(document.forms[0].card.value, 2);
}

function checkEntry(cell)
{
	//69 �quivaut � la touche E
	//84 �quivaut � la touche T
	//67 �quivaut � la touche C
	//65 �quivaut � la touche A
	if(eventX.keyCode!= 69 && eventX.keyCode!= 84 && eventX.keyCode!= 67 && eventX.keyCode!= 65)
	{
		cell.value = cell.value.toUpperCase();
		if(!cell.isValueEmpty || cell.isValueEmpty=='false')
		{
			var flag = ((cell.name=="cash" && oldValueCash!=cell.value) || (cell.name=="ticket" && oldValueTicket!=cell.value) || (cell.name=="cheque" && oldValueCheque!=cell.value) || (cell.name=="card" && oldValueCard!=cell.value)); 
			if(flag)
			{
				var lastEntry = cell.value.charAt(cell.value.length-1);
				cell.value = lastEntry;
				cell.isValueEmpty = 'true';
			}	
		}	
	
		if(isDecimal(cell) || cell.value=="")
		{
			refreshAmountReturned();
			refreshOldValues();
		}
	}
	else
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
		//13 �quivaut � la touche ENTRER
		case 13 :
			processCell(cell);
		return; 

		//27 �quivaut � la touche Echap
		case 27 :
			if(document.forms[0].cashingId)
				cancelFromDaylyReceipts();
			else	
				cancel();
		return;

		//38 �quivaut � la touche Up
		case 38 :
			up(cell);
		return;
		
		//40 �quivaut � la touche Down
		case 40 :
			down(cell);
		return;

		//69 �quivaut � la touche E
		case 69 :
			autoCompletion('cash');
		return;

		//84 �quivaut � la touche T
		case 84 :
			autoCompletion('ticket');
		return;

		//67 �quivaut � la touche C
		case 67 :
			autoCompletion('cheque');
		return;

		//65 �quivaut � la touche A
		case 65 :
			autoCompletion('card');
		return;
	};
}

function displayTableDetails()
{
	if(document.getElementById("idDivDetailsTable").style.visibility == "visible")
	{
		document.getElementById("idDisplayOrHideDetails").innerHTML = document.alertMessagesForm.labelTableDetails.value;
		document.getElementById("idDivDetailsTable").style.visibility = "hidden"; 
	}	
	else
	{
		document.getElementById("idDisplayOrHideDetails").innerHTML = document.alertMessagesForm.labelHideDetails.value;
		document.getElementById("idDivDetailsTable").style.visibility = "visible"; 	
	}	
	document.forms[0].cash.focus();	
}

function initPage()
{
	document.getElementById("idDivIFrame").style.height = screen.height;

	if(document.forms[0] && document.forms[0].cash)
	{
		document.forms[0].cash.focus();
	}	
}

