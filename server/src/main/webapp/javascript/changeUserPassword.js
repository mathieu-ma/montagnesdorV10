var eventX;

function processUserEntry(event, cell)
{
	eventX = event?event:window.event;

	switch(eventX.keyCode)
	{   
		//13 �équivaut � la touche ENTRER
		case 13 :
			updatePassword();
		return; 
			  	  
		//27 �quivaut � la touche Echap
		case 27 :
			cancel();
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
	var form = document.forms[0];
	switch(cell.name)
	{
		case "newPassword":
			form.oldPassword.focus();
			form.oldPassword.value = form.oldPassword.value+' ';
			form.oldPassword.value = form.oldPassword.value.substring(0, form.oldPassword.value.length-1);
		break;

		case "repeatedPassword":
			form.newPassword.focus();
			form.newPassword.value = form.newPassword.value+' ';
			form.newPassword.value = form.newPassword.value.substring(0, form.newPassword.value.length-1);
		break;
	}
}

function down(cell)
{
	var form = document.forms[0];
	switch(cell.name)
	{
		case "oldPassword":
			form.newPassword.focus();
			form.newPassword.value = form.newPassword.value+' ';
			form.newPassword.value = form.newPassword.value.substring(0, form.newPassword.value.length-1);
		break;

		case "newPassword":
			form.repeatedPassword.focus();
			form.repeatedPassword.value = form.repeatedPassword.value+' ';
			form.repeatedPassword.value = form.repeatedPassword.value.substring(0, form.repeatedPassword.value.length-1);
		break;
	}
}

function cancel()
{
	document.forms[0].action = contextPath+"/UserInfosIFrame.do";
	document.forms[0].submit();
	menuTopFocus();
}

function updatePassword()
{
	document.forms[0].submit();
}

function initPage()
{
	document.getElementById("idDivIFrame").style.height = screen.height;
	document.forms[0].oldPassword.focus();
}

