var contextPath ="";
var point = ".";
var blink = "true";
var isFirstTime = "true";

function changeLanguage(language)
{
	document.changeLanguageForm.language.value = language;
	document.changeLanguageForm.submit();
}

var delay = 1000;
function time() 
{
	try
	{
		var entryDate = document.getElementById("idDate");
		var shortEntryDate = document.tableOrdersApplet.getEntryDate().substring(0, 10);

//alert(shortEntryDate+":"+document.dateTimeApplet.getDateShort() +":"+	document.dateTimeApplet.getDate()+":"+	document.dateTimeApplet.getDate("12/12/2004"));

		if(window.IFrameData.document.getElementById("idCurrentTableRegistrationDate") && window.IFrameData.document.getElementById("idCurrentTableRegistrationDate").innerHTML!=shortEntryDate)
		{
//alert(window.IFrameData.document.getElementById("idCurrentTableRegistrationDate").innerHTML)
		
			entryDate.style.color = "#00FF00";
			entryDate.innerHTML = document.dateTimeApplet.getDate(window.IFrameData.document.getElementById("idCurrentTableRegistrationDate").innerHTML);
		}
		else
		{
			var shortCurrentDate = document.dateTimeApplet.getDateShort()+"";
		
			if(shortCurrentDate==shortEntryDate)
			{
				entryDate.innerHTML = document.dateTimeApplet.getDate();
				entryDate.style.color = "#FFFFFF";
			}
			else
			{	
				entryDate.innerHTML = document.dateTimeApplet.getDate(shortEntryDate);
				if(blink=="true")
				{
					entryDate.style.color = "#FF0000";
					blink = "false";
				}
				else
				{
					entryDate.style.color = "#FFFFFF";			
					blink = "true";
				}
			}
		}
		window.status = document.dateTimeApplet.getDateTime();
//		delay = 1000;
	}
	catch(err)
	{
//		alert("Appuyer sur OK pour continuer : "+err.message);
		try
		{
			point+=".";
			//Variable waitLoadAppletsMessage définie dans la page userInfos.jsp
			entryDate.innerHTML = waitLoadAppletsMessage+point;
		}
		catch(err)
		{
			//alert("Appuyer sur OK pour continuer : "+err.message);		
		}
		delay = 2000;
	}
	
/*
	if(isFirstTime == "true")
	{
		if(focusFirstInputText(document))
			isFirstTime = "false";
		focusFirstInputText(document)
	}
*/	
	window.setTimeout("time()",delay);
}


function changeEntryDate()
{

//	var date = prompt("Entrez une date : ", dateTimeApplet.getDateCourte());
	var url = contextPath+"/jsp/commons/blank.htm";
	var attribute = 'location=no,toolbar=no,status=yes,directories=no,scrollbars=no,width=450,height=250';
	var pop_up = window.open(url, "pop_up", attribute);
	pop_up.focus();
	
	document.changeEntryDateForm.submit();
}

function refreshTableNumber(cell)
{
	if(cell.name == "customerNumber")
	{
		resetTableNumber();
	}
	else
		cell.value = "";
}

function processCell(cell)
{
	switch(cell.name)
	{       
		//numeroTable
		case "tableNumber" :    
			processTableNumber(cell);
		break;         
		
		//nombrePersonnes
		case "customerNumber" :    
			processCustomerNumber(cell);
		break;         
	};

}

function processTableNumber(cell)
{
	if(cell.value!="")
	{
		var customerNumber = "";
		
		var customerNumberLabel = document.getElementById("idCustomerNumberLabel");
		var cellTDTableNumber = document.getElementById("idTDTableNumber");	

		if(document.tableOrdersApplet.isTableExist(cell.value))
		{
			customerNumber = document.tableOrdersApplet.getCustomerNumber();
		}
		else if(!document.tableOrdersApplet.isSessionValide())
		{
			window.top.document.location = contextPath+"/EntryPoint.do";
			return;
		}	
		if(cell.value.charAt(0)!='E')
		{
			//La variable customerNumberDescription est définie dans le fichier menuTop.jsp
			customerNumberLabel.innerHTML = customerNumberDescription;
			var cellTDCustomerNumber = document.getElementById("idTDCustomerNumber");
			cellTDCustomerNumber.innerHTML = "<input type='text' name='customerNumber' id='idCustomerNumber' class='customerNumber' onkeyup='processUserEntry(event, this)' maxlength='3' size='6'>";
			document.topForm.customerNumber.value = customerNumber;
			if(customerNumber == "")
				 document.topForm.customerNumber.isValueEmpty = 'true';
			else	 
				document.topForm.customerNumber.isValueEmpty = 'false';
			
			document.topForm.customerNumber.focus();			
			document.topForm.customerNumber.value = document.topForm.customerNumber.value.toUpperCase();
			document.topForm.customerNumber.focus();			
			cellTDTableNumber.innerHTML = cell.value;
		}
		else
		{			
			customerNumberLabel.innerHTML = takeawayDescription;
			cellTDTableNumber.innerHTML = cell.value;
			document.tableOrdersApplet.setTakeaway(true);
			submitForm(cell);			
		}
	}
}

function submitForm(cell)
{
	selectModule(1);

	if(cell.name != "tableNumber")
	{
		var cellTDCustomerNumber = document.getElementById("idTDCustomerNumber");
		cellTDCustomerNumber.innerHTML = document.topForm.customerNumber.value;
	}
	//document.topForm.hiddenTableNumber.value = document.tableOrdersApplet.getTableNumber();

	document.topForm.submit();
	
}

function processCustomerNumber(cell)
{
	if(cell.value!="")
	{
		if(document.tableOrdersApplet.setCustomerNumber(cell.value))
			submitForm(cell);
		else
			window.top.document.location = contextPath+"/EntryPoint.do";
	}
}

var eventX;
function processUserEntry(event, cell)
{
	eventX = event ? event : window.event;
	
//	window.clearTimeout(menuTopFocusTimeout);
	
	cell.value = cell.value.toUpperCase();	

	if(cell.name=="customerNumber")
	{
		if(cell.isValueEmpty=="false" && eventX.keyCode!=13)
		{
			cell.value = cell.value.charAt(cell.value.length-1);
			cell.isValueEmpty = 'true';
		}
		isInteger(cell);
	}
	else
	{
		removeQuote(cell);
	}

	switch(eventX.keyCode)
	{     	  
		//13 �quivaut � la touche ENTRER
		case 13 :
			processCell(cell);
		return; 
	
		//27 �quivaut � la touche Echap
		case 27 :
			refreshTableNumber(cell);
		return;
		
		//65 �quivaut � la lettre A pour autoriser la mise à jour automatique des lignes de commandes
		case 65 :
			if(cell.name=="customerNumber")
				window.document.getElementById("idAutoUpdateOrderLine").click();
		return;
	};
}

function initPage()
{
	time();

	//document.getElementById("idDivTop").style.visibility="visible";
	selectModule(0);
}

