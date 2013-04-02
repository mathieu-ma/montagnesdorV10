function cancel()
{
	if(focusFirstInputText(window.parent.document))
	{
		document.forms[0].action = contextPath+"/TablesListExcludeCashedTableIFrame.do";
		document.forms[0].submit();
	}	
	else
	{
		document.modifyTableForm.pageRequested.value = "successOrderTable";
		document.modifyTableForm.submit();
	}	
}

function deleteTable(tableId)
{
		//1) Remettre ajouter un input text pour la saisie du numéro de table
		if(!window.parent.document.getElementById("idCustomerNumber"))
			resetTableNumber();
		else
			focusFirstInputText(window.parent.document);

		//2) Mise à jour de table courante
		document.forms[0].action = contextPath+"/DeleteTable.do";
		document.forms[0].pageRequested.value="successTablesListIFrame";
		document.forms[0].submit();
}

function initPage()
{
	document.getElementById("idDivIFrame").style.height = screen.height;
	document.getElementById("idFocusAnchor").focus();
}

