function processList(sortListBy)
{
	document.modifyTableForm.sortListBy.value = sortListBy;

	if(document.modifyTableForm.sortMonotony.value=='asc')
		document.modifyTableForm.sortMonotony.value = 'desc';
	else
		document.modifyTableForm.sortMonotony.value	='asc';

	document.modifyTableForm.action = contextPath+"/DaylyReceiptsListIFrame.do";
	document.modifyTableForm.submit();
}

function processSelect(select)
{
	document.modifyTableForm.optionSelected.value = "ALL";
	document.modifyTableForm.filterList.value = select.value;
	document.modifyTableForm.submit();
}

function processUserClick(action, cashingIdTableId)
{
	document.modifyTableForm.action = contextPath+"/DisplayJsp.do";

	switch(action)
	{
		case "print" :
			document.modifyTableForm.tableId.value = cashingIdTableId;
			document.modifyTableForm.pageRequested.value = "successPrintTableByCashing";		
		break;
		case "modify" :
			document.modifyTableForm.cashingId.value = cashingIdTableId;
			document.modifyTableForm.pageRequested.value = "successModifyCashTable";		
		break;
	}
	document.modifyTableForm.submit();
}

function processMenusOptions(action)
{
	if(action=="CLOSE") {
		document.modifyTableForm.action = contextPath+"/CloseOrMergeReceipts.do";
	}
	else if(action=="PRINT") {
		document.modifyTableForm.action = contextPath+"/PrintReceipts.do";
	}	
	else {
		if(action=="DOWNLOAD") {
			dailyReceiptsListDownload();
		}	
		if(action=="UPLOAD") {
			dailyReceiptsListUpload();
		}	
		document.modifyTableForm.optionSelected.value = action;
	}	
	document.modifyTableForm.submit();
}

function dailyReceiptsListDownload()
{
	document.modifyTableForm.forward.value = "successDownload";
	focusFirstInputText(window.parent.document);
}

function dailyReceiptsListUpload()
{
	document.modifyTableForm.action = contextPath+"/DisplayJsp.do";
	document.modifyTableForm.pageRequested.value = "successUploadDailyReceiptsList";		
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

function initPageIFrame()
{
	document.getElementById("idDivIFrame").style.height = screen.height;
	document.getElementById("idTableDataHeader").style.width = document.body.clientWidth*100/(self.innerWidth?(self.innerWidth):(document.documentElement.offsetWidth))+"%";
	window.parent.document.getElementById("idTdDataOptions").innerHTML = document.getElementById("idTdDataOptions").innerHTML;
	window.parent.document.getElementById("idTdDataHeader").innerHTML = document.getElementById("idTdDataHeader").innerHTML;
	window.parent.document.getElementById("idTdDataFooter").innerHTML = document.getElementById("idTdDataFooter").innerHTML;
	showHideMenu();
	focusFirstInputText(window.parent.document);
}
