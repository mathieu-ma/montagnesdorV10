function processList(sortListBy)
{
	document.monthlyReceiptsForm.sortListBy.value = sortListBy;

	if(document.monthlyReceiptsForm.sortMonotony.value=='asc')
		document.monthlyReceiptsForm.sortMonotony.value = 'desc';
	else
		document.monthlyReceiptsForm.sortMonotony.value	='asc';

	document.monthlyReceiptsForm.submit();
}

function processSelect(select)
{
	document.monthlyReceiptsForm.optionSelected.value = "ALL";
	if(select.name=="monthSelected")
		document.monthlyReceiptsForm.monthSelected.value = select.value;
	else if(select.name=="yearSelected")	
		document.monthlyReceiptsForm.yearSelected.value = select.value;
	document.monthlyReceiptsForm.submit();
}

function processMenusOptions(action)
{
	if(action=="DOWNLOAD")
		downloadMonthlyReceiptsList();
	else
	{
		document.monthlyReceiptsForm.optionSelected.value = action;
		document.monthlyReceiptsForm.submit();
	}	
}

function downloadMonthlyReceiptsList()
{
	document.downloadMonthlyReceiptsForm.submit();
	document.monthlyReceiptsForm.submit();
	focusFirstInputText(window.parent.document);
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
