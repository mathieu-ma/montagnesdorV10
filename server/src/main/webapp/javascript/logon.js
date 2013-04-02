var _event;

function processUserEntry(eventParam, cell)
{
	if(eventParam==null)
		_event = event;
	else
		_event = eventParam;

	switch(_event.keyCode)
	{     	  
		//13 �quivaut � la touche ENTRER
		case 13 :
			processClick();
		return; 
	
	
		//38 �quivaut � la touche Up
		case 38 :
			up(cell);
		return;
		
		//40 �quivaut � la touche Down
		case 40 :
			down(cell);
		return;
	};
}

function up(cell)
{
	switch(cell.name)
	{
		case "password":
			document.forms[0].login.focus();
			document.forms[0].login.value = document.forms[0].login.value+' ';
			document.forms[0].login.value = document.forms[0].login.value.substring(0, document.forms[0].login.value.length-1);
		break;
	}
}

function down(cell)
{
	switch(cell.name)
	{
		case "login":
			document.forms[0].password.focus();
			document.forms[0].password.value = document.forms[0].password.value+' ';
			document.forms[0].password.value = document.forms[0].password.value.substring(0, document.forms[0].password.value.length-1);
		break;
	}
}

function processClick(contextPathSelect)
{
	if(contextPathSelect)
		contextPath = contextPathSelect;
	document.forms[0].action = contextPath+"/Logon.do";
	document.forms[0].submit();
}

function initPage()
{	
	document.forms[0].login.focus();
}



