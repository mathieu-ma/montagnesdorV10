var eventX;

function processUserEntry(event, cell)
{
	eventX = event?event:window.event;

	switch(eventX.keyCode)
	{     	  
		//13 équivaut � la touche ENTRER
		case 13 :
			verifyPassword();
		return; 

		//27 é�quivaut � la touche Echap
		case 27 :
			cancel();
		return;
	};
}

function cancel()
{
	document.modifyTableForm.pageRequested.value = "successOrderTable";
	document.modifyTableForm.submit();
}

function verifyPassword()
{
	document.forms[0].submit();
}

function initPage()
{
	document.getElementById("idDivIFrame").style.height = screen.height;

	if(document.forms[0])
		document.forms[0].elements[0].focus();
}

