<!--
var tableOrdersApplet = window.parent.document.tableOrdersApplet;

function processMenuTop(tableNumber, customerNumber)
{
	var customerNumberLabel = window.parent.document.getElementById("idCustomerNumberLabel");
	var cellTDTableNumber = window.parent.document.getElementById("idTDTableNumber");	
	var cellTDCustomerNumber = window.parent.document.getElementById("idTDCustomerNumber");

	cellTDTableNumber.innerHTML = tableNumber;

	if(tableNumber.charAt(0)!='E')
	{
		//La variable customerNumberDescription est définie dans le fichier menuTop.jsp
		customerNumberLabel.innerHTML = window.parent.customerNumberDescription;
		cellTDCustomerNumber.innerHTML = customerNumber;
		
	}
	else
	{			
		customerNumberLabel.innerHTML = window.parent.takeawayDescription;
	}
}

function processList(sortListBy)
{
	document.modifyTableForm.filterList.value = window.parent.document.getElementById("idFilterList").value;
	
	document.modifyTableForm.sortListBy.value = sortListBy;

	if(document.modifyTableForm.sortMonotony.value=='asc')
		document.modifyTableForm.sortMonotony.value = 'desc';
	else
		document.modifyTableForm.sortMonotony.value	='asc';

	document.modifyTableForm.action = contextPath+"/TablesListExcludeCashedTableIFrame.do";
	document.modifyTableForm.submit();
}

function processSelect(select)
{
	document.modifyTableForm.filterList.value = select.value;
	document.modifyTableForm.action = contextPath+"/TablesListExcludeCashedTableIFrame.do";
	document.modifyTableForm.submit();
}

function processUserClick(action, tableId, tableNumber)
{
	document.modifyTableForm.tableId.value = tableId;
	tableOrdersApplet.isTableExist(tableNumber);
	switch(action)
	{
		case "cashTable" :
			document.modifyTableForm.pageRequested.value = "successCashTable";
		break;
		case "mergeTable" :
			document.modifyTableForm.pageRequested.value = "successMergeTable";
		break;
		case "changeTable" :
			document.modifyTableForm.pageRequested.value = "successChangeTable";
		break;
		case "orderTable" :
			processMenuTop(tableNumber, tableOrdersApplet.getCustomerNumber());
			document.modifyTableForm.pageRequested.value = "successOrderTable";
		break;
		case "deleteTable" :
			document.modifyTableForm.pageRequested.value = "successDeleteTable";
		break;
		case "printTable" :
			//processMenuTop(tableNumber, tableOrdersApplet.getCustomerNumber());		
			document.modifyTableForm.pageRequested.value = "successPrintTable";
		break;
	}
	document.modifyTableForm.submit();
}

function showHideMenu(id) 
{
	var d = window.parent.document.getElementById(id);
	for (var i = 1; i<=10; i++) 
	{
		if (window.parent.document.getElementById('smenu'+i)) 
		{
			window.parent.document.getElementById('smenu'+i).style.display='none';
		}
	}
	if (d) 
	{
		d.style.display='block';
	}
}

function showHideFooter(isShowed) 
{
	var d = window.parent.document.getElementById(id);
	for (var i = 1; i<=10; i++) 
	{
		if (window.parent.document.getElementById('smenu'+i)) 
		{
			window.parent.document.getElementById('smenu'+i).style.display='none';
		}
	}
	if (d) 
	{
		d.style.display='block';
	}
}

function processMenusOptions(selected)
{
	document.modifyTableForm.filterList.value = selected;
	document.modifyTableForm.action = contextPath+"/TablesListExcludeCashedTableIFrame.do";
	document.modifyTableForm.submit();
}

function initPageIFrame()
{
	document.getElementById("idDivIFrame").style.height = screen.height;
	document.getElementById("idTableDataHeader").style.width =  document.body.clientWidth*100/(self.innerWidth?(self.innerWidth):(document.documentElement.offsetWidth))+"%";
	window.parent.document.getElementById("idTdDataOptions").innerHTML = document.getElementById("idTdDataOptions").innerHTML;
	window.parent.document.getElementById("idTdDataHeader").innerHTML = document.getElementById("idTdDataHeader").innerHTML;
	window.parent.document.getElementById("idTdDataFooter").innerHTML = document.getElementById("idTdDataFooter").innerHTML;
	showHideMenu();
	focusFirstInputText(window.parent.document);
}


-->









