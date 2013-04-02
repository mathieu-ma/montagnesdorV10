<!--
var eventX;

function processUserClick(action, code)
{
	document.productsListForm.action = contextPath+"/SaveOrUpdateProduct.do";
	document.productsListForm.code.value = code;
	document.productsListForm.actionDo.value = action;
	if(action=="DISPLAY" || confirm(document.alertMessagesForm.productsListIFrameConfirmDeleteProduct.value+" : "+code+" ?"))
		document.productsListForm.submit();
	else
		focusFirstInputText(window.parent.document);
}

function searchProducts()
{
	document.productsListForm.action = contextPath+"/DisplayJsp.do";
	document.productsListForm.pageRequested.value = "successSearchProducts";
	document.productsListForm.submit();
}

function processList(sortListBy)
{
	document.productsListForm.pageSize.value = window.parent.document.getElementById("idPageSize").value;
	document.productsListForm.sortListBy.value = sortListBy;
	
	if(document.productsListForm.sortMonotony.value=='asc')
		document.productsListForm.sortMonotony.value = 'desc';
	else
		document.productsListForm.sortMonotony.value	='asc';
	
	document.productsListForm.submit();
}

function processSelect(select)
{
	document.productsListForm.pageSize.value = select.value;
	processPage(1);
}

function nextPage()
{
	processPage(parseInt(document.productsListForm.pageNumber.value)+1);
}

function previousPage()
{
	processPage(parseInt(document.productsListForm.pageNumber.value)-1);
}

function processPage(pageNumber)
{
	if(document.productsListForm.searchedField)
		document.productsListForm.searchedField.value = "NONE";
	document.productsListForm.pageNumber.value = pageNumber;
	document.productsListForm.submit();
}

function gotoPage(event, cell, lastPage)
{
	eventX = event?event:window.event;

	isInteger(cell);
	
	switch(eventX.keyCode)
	{     	  
		//13 équivaut � la touche ENTRER
		case 13 :
			if(parseInt(cell.value)<1)
				alert(document.alertMessagesForm.productsListIFrameAlertNumberPage1.value);
			else if(parseInt(cell.value)>parseInt(lastPage))
				alert(document.alertMessagesForm.productsListIFrameAlertNumberPage2.value);
			else
				processPage(cell.value);
		return; 

		//27 é�quivaut � la touche Echap
		case 27 :
			cell.value = "";
			focusFirstInputText(window.parent.document);
		return;
	};
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









