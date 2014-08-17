var windowX;
var HALF_ROUND = '1';
var TENTH_ROUND = '2';

if(window.opener)
	windowX = window.opener.parent;
else	
	windowX = window.top.parent;

function specificRound(a)
{
	var result = a;
	switch(specificRoundChoose)
	{
		case HALF_ROUND:
	        var b = a - Math.floor(a);
	        if(b<0.25)
    	        b = 0;
        	else if(b<0.75)
	            b = 0.5;
    	    else
        	    b = 1;
	        result = Math.floor(a)+b;
	    break;
		case TENTH_ROUND:
	        result = Math.round(a*10)/10;
	    break;
	}    
	return result;        
}

function formatNumber(nbr, len)
{
	if(nbr=="")
		nbr = 0;
	nbr = parseFloat(nbr);
	var div = Math.pow(10, len);
	var result = Math.round(nbr*div)/div + "";

	var nbrSplitByPoint = result.split(".");

	if(nbrSplitByPoint.length == 2)
	{
		len -= nbrSplitByPoint[1].length;
		result = nbrSplitByPoint[0]+"."+padding(nbrSplitByPoint[1], "0", len);
	}
	else
	{
		result = padding(result+".", "0", len);
	}

	if(Math.abs(Number(result))==0)
		result = padding("0.", "0", len);
		
	return result;
}

function padding(str, chr, len)
{
	for(var i=0; i<len; i++)
		str += chr;

	return str;
}


function removeQuote(cell)
{
	var lastEntry = cell.value.charAt(cell.value.length-1);
	var auxString = cell.value.substr(0,cell.value.length-1);

	if(lastEntry=="'")	
	{
		cell.value = auxString;	
	}
}


function isInteger(cell)
{
	var lastEntry = cell.value.charAt(cell.value.length-1);
	var auxString = cell.value.substr(0,cell.value.length-1);
	var expReg =/[0-9]/;

	if(!expReg.test(lastEntry))	
	{
		cell.value = auxString;	
	}
}

function isDecimal(cell)
{
	return testDecimal(cell, 0);
}

function isPositiveDecimal(cell)
{
	return testDecimal(cell, 1);
}

function testDecimal(cell, sign)
{
	var lastEntry = cell.value.charAt(cell.value.length-1);
	var flag = true;
	var auxString = cell.value.substr(0,cell.value.length-1);
	var expReg =/[0-9]/;

	if(lastEntry==".")
	{
		expReg = /[.]/;
		flag = !expReg.test(auxString);
		if(auxString=="")
			flag = false;
	}
	else
	{
		if(sign==0)
		{
			if(lastEntry != "-" || cell.value.length != 1)
				flag = expReg.test(lastEntry);
		}
		else
		{
			flag = expReg.test(lastEntry);
		}		
	}

	if(!flag)	
	{
		cell.value = auxString;
	}

	return flag;
}

function focusFirstInputText(doc)
{
	var elements = doc.forms[0].elements;
	var i=0;
	for(; i<elements.length; i++)
	{
		if(elements[i].type=='text')
		{
			elements[i].focus();
			elements[i].focus();
			return true;
		}	
	}
	return false;
}

var menuTopFocusTimeout;
var counter = 0;
var MAX_COUNTER = 1;
function menuTopFocus()
{
	if(!focusFirstInputText(windowX.document) || counter==MAX_COUNTER)
	{
		window.clearTimeout(menuTopFocusTimeout);
	}	
	else
	{
		counter++;
		menuTopFocusTimeout = window.setTimeout("menuTopFocus()",200);
	}	
}

function selectModule(index)
{
	var maxModule = 7;

	for(var i=0; i<maxModule; i++)
	{
		try
		{
			if(i==index)
			{
				windowX.document.getElementById("IDTD"+i).bgColor = "white";
				windowX.document.getElementById("IDA"+i).style.color = "darkblue";			
			}
			else
			{
				windowX.document.getElementById("IDTD"+i).bgColor = "#660000";
				windowX.document.getElementById("IDA"+i).style.color = "white";
			}
		}
		catch(err)
		{
		}	
	}
	menuTopFocus();
}

function resetTableNumber()
{
		if(!windowX.document.getElementById("idTableNumber"))
		{
			windowX.document.getElementById("idTDCustomerNumber").innerHTML = "";
			windowX.document.getElementById("idCustomerNumberLabel").innerHTML = "";
			windowX.document.getElementById("idTDTableNumber").innerHTML = "<input type='text' class='text' id='idTableNumber' name='tableNumber' onkeyup='processUserEntry(event, this)' maxlength='5' size='6'>";
			windowX.document.getElementById("idTableNumber").focus();
			windowX.document.getElementById("idTableNumber").focus();
		}	
}

function changeModule(index)
{
		
	var redirectModule = "/UserInfosIFrame.do";

	switch(index)
	{
		case 0 : 
			redirectModule = "/UserInfosIFrame.do";
		break;

		case 1 : 
			redirectModule = "/TablesListExcludeCashedTableIFrame.do";
		break;

		case 2 : 
			redirectModule = "/html/blank.htm";
		break;

		case 3 : 
			redirectModule = "/DaylyReceiptsListIFrame.do";
		break;

		case 4 : 
			redirectModule = "/MonthlyReceiptsListIFrame.do";
		break;

		case 5 : 
			redirectModule = "/ProductsListIFrame.do";
		break;
		case 6 : 
			redirectModule = "/UsersListIFrame.do";
		break;
	}

	windowX.document.getElementById("IdIFrameData").src = contextPath+redirectModule;
	resetTableNumber();
	selectModule(index);
}


