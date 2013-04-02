var eventX;
var oldValueCode = "";
var oldValueLabel = "";
var oldValuePrice = "";

function submitProduct()
{
	if(document.forms[0].code.type.toUpperCase()=="TEXT" && document.forms[0].code.value=="")
	{
		alert(document.alertMessagesForm.saveOrUpdateProductAlertCode.value);
		document.forms[0].code.focus();
		return;
	}
	if(document.forms[0].label.value=="")
	{
		alert(document.alertMessagesForm.saveOrUpdateProductAlertLabel.value);		
		document.forms[0].label.focus();		
		return;
	}

	if(defaultRGBColor==document.colorPickerApplet.getColorRGB())
		document.forms[0].colorRGB.value = "NULL";
	else
		document.forms[0].colorRGB.value = document.colorPickerApplet.getColorRGB();
	
	if(window.parent.document.getElementById("idPageSize").value!="0")
		document.forms[0].pageSize.value = window.parent.document.getElementById("idPageSize").value;
	
	//1) Remettre ajouter un input text pour la saisie du numéro de table
	if(!window.parent.document.getElementById("idCustomerNumber"))
		resetTableNumber();
	else
		focusFirstInputText(window.parent.document);

	//2) Sauvegarder le produit
	document.forms[0].submit();
}

function processCell(cell)
{
	if(cell.name=="price")
	{
		if(isNaN(cell.value) || cell.value=="")
			cell.value="1.0";	
		cell.value = formatNumber(cell.value,2);
		if(cell.value<0)
		{
			alert(document.alertMessagesForm.alertLabelNegativePrice.value);
			return;
		}	
	}		
	submitProduct();
}

function cancel()
{
	if(window.parent.document.getElementById("idPageSize").value!="0")
		document.productsListForm.pageSize.value = window.parent.document.getElementById("idPageSize").value;
	else if(document.productsListForm.searchedField)
		document.productsListForm.action = contextPath+"/SearchProductsList.do";
	document.productsListForm.submit();
}

function moveCursorToRight(cell)
{
	cell.value = cell.value.toUpperCase()+' ';
	cell.value = cell.value.substring(0, cell.value.length-1);
}

function up(cell)
{
	cell.isValueEmpty = 'false';
	switch(cell.name)
	{
		case "price":
			if(cell.value=="")
				cell.value = "0";
			cell.value = formatNumber(cell.value,2);
			document.forms[0].label.focus();
			moveCursorToRight(document.forms[0].label);
		break;
		case "label":
			if(document.forms[0].label.value=="")
			{
				alert(document.alertMessagesForm.saveOrUpdateProductAlertLabel.value);		
				document.forms[0].label.focus();		
				return;
			}
			if(document.forms[0].code.type.toUpperCase()=="TEXT")
			{
				document.forms[0].code.focus();
				moveCursorToRight(document.forms[0].code);
			}	
		break;
	}
}

function down(cell)
{
	cell.isValueEmpty = 'false';
	switch(cell.name)
	{
		case "code":
			if(document.forms[0].code.type.toUpperCase()=="TEXT" && document.forms[0].code.value=="")
			{
				alert(document.alertMessagesForm.saveOrUpdateProductAlertCode.value);
				document.forms[0].code.focus();
				return;
			}
			document.forms[0].label.focus();
			moveCursorToRight(document.forms[0].label);
		break;
		case "label":
			if(document.forms[0].label.value=="")
			{
				alert(document.alertMessagesForm.saveOrUpdateProductAlertLabel.value);		
				document.forms[0].label.focus();		
				return;
			}
			document.forms[0].price.focus();
			moveCursorToRight(document.forms[0].price);
		break;
	}
}

function refreshInputText()
{
	var inputs = document.getElementsByTagName("input");
	for(var i=0; i< inputs.length; i++)
	{
		var input = inputs.item(i);
		if(input.type.toUpperCase()=="TEXT")
		{
			if(input.value=="")
				input.value = formatNumber(0, 2);
			input.value = formatNumber(input.value, 2);
		}
	}
}

function checkEntry(cell)
{
	cell.value = cell.value.toUpperCase();

	removeQuote(cell);

	if(cell.name=="price" || cell.name=="quantity")
	{
		isPositiveDecimal(cell);
	}
}

function processUserEntry(event, cell)
{
	eventX = event?event:window.event;

	checkEntry(cell);

	if(cell.name!="quantity")
	{
		switch(eventX.keyCode)
		{     	  
			//13 �quivaut � la touche ENTRER
			case 13 :
				processCell(cell);
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
	else
	{
		if(eventX.keyCode==13)
		{
			validQuantity();
		}	
	}	
}

function verifyCategoryQuantinty(checkbox)
{
	var categoryQuantintyArray = checkbox.value.split("-");
	
	if(checkbox.checked)
	{
		document.getElementById("idLabelQuantity").innerHTML = document.getElementById(categoryQuantintyArray[0]).innerHTML;
		if(categoryQuantintyArray.length==2)
		{
			var quantity = categoryQuantintyArray[1];
			if(quantity!="" && quantity!="0.00")
				document.forms[0].quantity.value = formatNumber(quantity, 2);
			else	
				document.forms[0].quantity.value = "";
		}
			
		document.forms[0].quantityHidden.value = checkbox.id;
		document.forms[0].quantity.focus();		
	}	
	else
	{
		document.getElementById(checkbox.id.substr(10, checkbox.id.length)).style.color="white";
		document.getElementById("idLabelQuantity").innerHTML = "";
		document.forms[0].quantity.value = "";
		checkbox.value = categoryQuantintyArray[0]+"-";
		focusFirstElement();
	}	
}

function displayCategoryQuantity(id)
{
	verifyCategoryQuantinty(document.getElementById("idCategory"+id));
}

function validQuantity()
{
	var checkbox = document.getElementById(document.forms[0].quantityHidden.value);
	var cell = document.forms[0].quantity;
	if(checkbox)
	{
		var categoryQuantintyArray = checkbox.value.split("-");
		if(isNaN(cell.value))
			cell.value="";
		if(cell.value!="")
		{
			cell.value = formatNumber(cell.value,2);
			if(cell.value<0)
			{
				alert(document.alertMessagesForm.alertLabelNegativeQuantity.value);
				cell.value="";
				return;
			}
		}
		
		checkbox.value = categoryQuantintyArray[0]+"-"+cell.value;
		if(cell.value!="")
			document.getElementById(document.forms[0].quantityHidden.value.substr(10, document.forms[0].quantityHidden.value.length)).style.color="blue";
	}
	else
		cell.value="";
	focusFirstElement();
}

function resetQuantity()
{
	var checkbox = document.getElementById(document.forms[0].quantityHidden.value);
	var categoryQuantintyArray = checkbox.value.split("-");
	document.getElementById(checkbox.id.substr(10, checkbox.id.length)).style.color="white";
	document.getElementById("idLabelQuantity").innerHTML = "";
	document.forms[0].quantity.value = "";
	checkbox.value = categoryQuantintyArray[0]+"-";
	focusFirstElement();
}

function displayCategories()
{
	if(document.getElementById("idDivQuantity").style.visibility == "visible")
	{
		document.getElementById("idDisplayOrHideCategories").innerHTML = document.alertMessagesForm.labelAddCategoriesRelation.value;
		document.getElementById("idDivQuantity").style.visibility = "hidden"; 
	}	
	else
	{
		document.getElementById("idDisplayOrHideCategories").innerHTML = document.alertMessagesForm.labelHideCategoriesRelation.value;
		document.getElementById("idDivQuantity").style.visibility = "visible"; 	
	}	
	focusFirstElement();	
}

function focusFirstElement()
{
	if(document.forms[0])
	{
		if(document.forms[0].code.type=="text")
		{
			document.forms[0].code.focus();
			document.forms[0].code.focus();
		}	
		else
		{
			document.forms[0].label.focus();
			document.forms[0].label.focus();
		}	
	}
}

var initPageTimeout;
var counter = 0;
var MAX_COUNTER = 10;

function initPage()
{
	document.getElementById("idDivIFrame").style.height = screen.height;

	if(counter==MAX_COUNTER)
	{
		window.clearTimeout(initPageTimeout);
	}	
	else
	{
		focusFirstElement();
		counter++;
		initPageTimeout = window.setTimeout("initPage()",200);
	}
}
