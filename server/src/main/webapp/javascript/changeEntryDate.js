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
	if(!focusFirstInputText(window.opener.document))
	{
		focusFirstInputText(window.opener.IFrameData.document);
	}
	self.close();		
}

function verifyPassword()
{
	document.forms[0].submit();
}

function initPage()
{
	//Utiliser l'applet dateTimeApplet pour sélectionner la date courante côté client
	//dd/MM/yyyy
	for(var i=0; i<document.forms[0].date.length; i++)
	{
		if(Number(document.forms[0].date.options[i].value)==Number(window.opener.document.dateTimeApplet.getDateShort().substr(0,2)))
		{
			document.forms[0].date.options[i].selected = true;
			break;
		}
	}
	for(var i=0; i<document.forms[0].month.length; i++)
	{
		if(Number(document.forms[0].month.options[i].value)==Number(window.opener.document.dateTimeApplet.getDateShort().substr(3,2)))
		{
			document.forms[0].month.options[i].selected = true;
			break;
		}
	}
	for(var i=0; i<document.forms[0].year.length; i++)
	{
		if(Number(document.forms[0].year.options[i].value)==Number(window.opener.document.dateTimeApplet.getDateShort().substr(6,4)))
		{
			document.forms[0].year.options[i].selected = true;
			break;
		}
	}
	document.forms[0].password.focus();
	document.forms[0].password.focus();	
}

