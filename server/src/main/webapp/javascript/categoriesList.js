<!--

function processUserClick(action, id)
{
	document.categoriesListForm.action = contextPath+"/SaveOrUpdateCategory.do";
	document.categoriesListForm.id.value = id;
	document.categoriesListForm.actionDo.value = action;
	if(action=="DISPLAY" || confirm(document.alertMessagesForm.categoriesListIFrameConfirmDeleteCategory.value+" : "+id+" ?"))
		document.categoriesListForm.submit();
	else
		focusFirstInputText(window.parent.document);
}

function initPageIFrame()
{
	document.getElementById("idDivIFrame").style.height = screen.height;
	document.getElementById("idTableDataHeader").style.width = document.body.clientWidth*100/(self.innerWidth?(self.innerWidth):(document.documentElement.offsetWidth))+"%";
	window.parent.document.getElementById("idTdDataOptions").innerHTML = document.getElementById("idTdDataOptions").innerHTML;
	window.parent.document.getElementById("idTdDataHeader").innerHTML = document.getElementById("idTdDataHeader").innerHTML;
	window.parent.document.getElementById("idTdDataFooter").innerHTML = document.getElementById("idTdDataFooter").innerHTML;
	focusFirstInputText(window.parent.document);
}

-->









