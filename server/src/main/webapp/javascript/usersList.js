function processUserClick(action, id)
{
	document.usersListForm.action = contextPath+"/SaveOrUpdateUser.do";
	if (id) {
		 //Create an input type dynamically.
	    var element = document.createElement("input");
	    //Assign different attributes to the element.
	    element.setAttribute("type", "hidden");
	    element.setAttribute("value", id);
	    element.setAttribute("name", "user.id");
	    //Append the element in page (in span).
		document.usersListForm.appendChild(element);
	}
	document.usersListForm.actionDo.value = action;
	if(action=="DISPLAY" || confirm(document.alertMessagesForm.usersListIFrameConfirmDeleteUser.value+" : "+id+" ?"))
		document.usersListForm.submit();
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
