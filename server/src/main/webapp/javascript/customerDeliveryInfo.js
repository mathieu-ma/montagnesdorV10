function InputTextManager(actionX)
{
    this.actionX = actionX;
}

InputTextManager.prototype.initPage = function(form, input)
{
	document.getElementById("idDivIFrame").style.height = screen.height;

	if(!form)
		form = document.forms[0];
	
	this.form = form;
	this.inputTextArray = new Array();
	for(var i=0; i<this.form.elements.length; i++)
	{
		if(this.form.elements[i].type=='text')
	    {
	    	this.inputTextArray.push(this.form.elements[i]);
		}
	}
	
	if(input)
	{
		input.focus();
	    return;
	}
	
	if(this.inputTextArray[0])
	{
    	this.inputTextArray[0].focus();
        return;
	}
	
	document.getElementById("idFocusAnchor").focus();
}

InputTextManager.prototype.up = function()
{
	var upInput = this.currentInput;
	
	for(var i=0; i<this.inputTextArray.length; i++)
	{
		if(this.inputTextArray[i].type=='text' && this.currentInput.name==this.inputTextArray[i].name)
	    {
		    if(i>0)
			    upInput = this.inputTextArray[i-1];
	    	break;
		}
	}
	upInput.focus();
}

InputTextManager.prototype.down = function()
{
	var downInput = this.currentInput;
	
	for(var i=0; i<this.inputTextArray.length; i++)
	{
		if(this.inputTextArray[i].type=='text' && this.currentInput.name==this.inputTextArray[i].name)
	    {
			if(i+1<this.inputTextArray.length)
				downInput = this.inputTextArray[i+1];

	    	break;
		}
	}
	downInput.focus();
}

InputTextManager.prototype.processUserEntry = function(event, input)
{
	this.currentInput = input;

	var eventX = event?event:window.event;
	switch(eventX.keyCode)
	{     	  
		//13 touche ENTRER
		case 13 :
			this.submit();
		return; 

		//27 touche Echap
		case 27 :
			this.cancel();
		return;
		
		//38 touche up
		case 38 :
			this.up();
		return;

		//40 touche down
		case 40 :
			this.down();
		return;				
	};
}

InputTextManager.prototype.cancel = function()
{
	this.form.action = this.actionX;
	this.form.submit();
}

InputTextManager.prototype.submit = function()
{
	this.form.submit();
}



