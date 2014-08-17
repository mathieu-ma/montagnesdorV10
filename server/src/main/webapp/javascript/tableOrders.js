<!--
var currentRowId = 0;
var oldValueQuantity = "0";
var oldValueCode = "";
var oldValueLabel = "";
var oldValuePrice = "0";


var tableOrdersApplet = window.parent.document.tableOrdersApplet;
//window.top.fenetrePrincipaleBasDroite.fenetreSecondaireHaut.document.getElementById("idTableApplet")
//var impressionApplet = window.top.fenetrePrincipaleHaut.document.getElementById("idImpressionApplet");

function checkEntry(cell)
{
	var flag = (cell.name=="quantity" && oldValueQuantity!=cell.value) || (cell.name=="price" && oldValuePrice != cell.value) || (cell.name=="code" && oldValueCode!=cell.value); 

	if(cell.isValueEmpty=='false')
	{
		if(flag)
		{
			var lastEntry = cell.value.charAt(cell.value.length-1);
			cell.value = lastEntry;
			cell.isValueEmpty = 'true';
		}
	}	

	switch(cell.name)
	{
		case "price" :
			//Cette fonction se trouve dans le fichier commons.js	
			isDecimal(cell);
		break;
		case "quantity" :
			//Cette fonction se trouve dans le fichier commons.js
			if(isLastRow(cell) && window.parent.document.getElementById("idAutoUpdateOrderLine").checked && !document.getElementById("TDA"+currentRowId).innerHTML)
				isDecimal(cell);
			else	
				isPositiveDecimal(cell);
		break;
		default :
			//Cette fonction se trouve dans le fichier commons.js	
			removeQuote(cell);
		break;
	}
}

function replaceInputByTextNode(cell)
{
	cell.value = cell.value.toUpperCase();
	
	var parentCell = cell.parentNode;
	parentCell.setAttribute("class", cell.name);
	if(document.all)
	{
		//Cette partie est ajoutée pour Internet Explorer
		var textAlign;
		var borderStyle;
		var height = "31"; 
		var paddingRight;
		var width;
		var paddingLeft;
		switch (cell.name)
		{       
			case "quantity" :
				textAlign = "right"; 
				borderStyle = "inset"; 
				paddingRight = "1em"; 
				width = "9%";

				parentCell.style.paddingRight = paddingRight;
	 		break;         
			
			case "code" :         
				textAlign = "center"; 
				borderStyle = "inset"; 
				width = "9%";
			break;         
	
			case "label" :               
				textAlign = "left"; 
				borderStyle = "inset"; 
				paddingLeft = "2em"; 
				width = "64%";

				parentCell.style.paddingLeft = paddingLeft;
			break;         

			case "price" :               
				textAlign = "right"; 
				borderStyle = "inset"; 
				paddingRight = "1em"; 
				width = "9%";

				parentCell.style.paddingRight = paddingRight;
			break;         
		};
		parentCell.style.textAlign = textAlign;
		parentCell.style.borderStyle = borderStyle;
		parentCell.style.height = height;
		parentCell.style.width = width;
	}
	
	if(cell.name=="quantity")
		parentCell.innerHTML = "<a class='quantity' href='#' ID='AQ"+currentRowId+"' onclick='setInputText(this)'>"+cell.value+"</a>";
	else
		parentCell.innerHTML = cell.value;
}

function changeColumn(cell)
{
	replaceInputByTextNode(cell);

	var parentCell = document.getElementById("TD"+cell.id+""+currentRowId);
	var siblingCellName;
	var siblingCellId;
	var siblingCellClass;
	var siblingCellStyle;
	var siblingCellSize;
	var siblingCellMaxLength;
	var parentSiblingCell;
	switch (cell.name)
	{       
		case "quantity" :
			siblingCellId = "C"+currentRowId;
			siblingCellName = "code";
			siblingCellClass = "code";
			siblingCellMaxLength = "5";
 		break;         
		
		case "code" :         
			siblingCellId = "L"+currentRowId;
			siblingCellName = "label";
			siblingCellClass = "label";
			siblingCellMaxLength = "52";
		break;         

		case "label" :               
			siblingCellId="P"+currentRowId;
			siblingCellName = "price";
			siblingCellClass = "price";
			siblingCellMaxLength = "6";
		break;         
	};

	parentSiblingCell = document.getElementById("TD"+siblingCellId);
	parentSiblingCell.setAttribute("class", siblingCellClass+"Center");
	if(document.all)
	{
		//Cette partie est ajoutée pour Internet Explorer
		var textAlign;
		var borderStyle;
		var height = "31";
		var width;
		switch (cell.name)
		{       
			case "quantity" :
				textAlign = "center"; 
				borderStyle = "inset"; 
				width = "9%";
	 		break;         
			
			case "code" :         
				textAlign = "center"; 
				borderStyle = "inset"; 
				width = "64%";
				parentSiblingCell.style.paddingLeft = "0em";
			break;         
	
			case "label" :               
				textAlign = "center"; 
				borderStyle = "inset"; 
				width = "9%";
				parentSiblingCell.style.paddingRight = "0em";
			break;         
		};
		parentSiblingCell.style.textAlign = textAlign;
		parentSiblingCell.style.borderStyle = borderStyle;
		parentSiblingCell.style.height = height;
		parentSiblingCell.style.width = width;
	}
	
	var siblingCellTextValue = parentSiblingCell.innerHTML;
	parentSiblingCell.innerHTML = "<input type=text name='"+siblingCellName+"' value='"+siblingCellTextValue+"' isValueEmpty='true' class='"+siblingCellClass+"' onkeyup='processUserEntry(event, this)' maxlength='"+siblingCellMaxLength+"' id='"+siblingCellId.charAt(0)+"'>";

	var siblingCell = document.getElementById(siblingCellId.charAt(0));
	if(siblingCell.value!="")
		siblingCell.isValueEmpty = "false";

	if(isFirstRow(cell))
		window.location.href = "#idBody";
	
	siblingCell.focus();
	siblingCell.focus();
	
	siblingCell.value = siblingCell.value.toUpperCase();

	return siblingCell;
}

function processQuantity(cell)
{
	if(isNaN(cell.value) || cell.value=="")
		cell.value="1.0";		

	//Cette fonction se trouve dans le fichier commons.js	
	cell.value = formatNumber(cell.value, 1);

	changeColumn(cell);
}

function positionner()
{
	var table = document.getElementById("IDTableOrders");

	if(table.rows.length >16)
	{
		document.getElementById("idDivIFrame").style.height = 0;
	    var position = document.getElementById("idPositionBottom");
   		position.focus();
	}	
	else
		document.getElementById("idDivIFrame").style.height = screen.height;

	focusFirstInputText(document);
}

function addRow(cell)
{
	var table = document.getElementById("IDTableOrders");
	var id = table.rows.length;

	//Cr�éer un fils pour arri�ère-grand-p�ère	
	//Insè�re une ligne du tableau � partir de "id+1"
	var row = table.insertRow(id);
	row.setAttribute("onmouseover", "this.className='over';");
	row.setAttribute("onmouseout", "this.className='default';");
	row.setAttribute("ID","x");

	var elementTDTrait = document.createElement("td");
	elementTDTrait.setAttribute("class","border");
	if(document.all)
	{
		//Cette partie est ajoutée pour Internet Explorer
		row.onmouseover = function() { this.className='over'; };
		row.onmouseout = function() { this.className='default'; };
		
		elementTDTrait.style.textAlign = "center";
		elementTDTrait.style.borderStyle = "inset";
		elementTDTrait.style.height = "31";
	}
	elementTDTrait.innerHTML = "<b><font color='#FFCC00'>|</font></b>";
	row.appendChild(elementTDTrait);

	var arrayCode = new Array("Q", "C", "L", "P", "A");
	var elementTD;
	var cellClass;
	var tempId;
	for(i=0; i<arrayCode.length; i++)
	{
		elementTD = document.createElement("td");
		switch (i)
		{       
			//quantite
			case 0 :
				elementTD.innerHTML ="<input type='text' ID='Q' name='quantity' isValueEmpty='true' class='quantity' onkeyup='processUserEntry(event, this)' maxlength='5' >"
				cellClass = "quantityCenter";
				tempId = "TDQ"+id;
			break;         
		
			//code
			case 1 :               
				cellClass = "code";
				tempId = "TDC"+id;
			break;         
			
			//designation
			case 2 :
				cellClass = "label";
				tempId = "TDL"+id;
			break;         
	
			//prix
			case  3 :
				cellClass = "price";               
				tempId = "TDP"+id;
			break;         

			//montant
			case  4 :
				cellClass = "amount";   
				tempId = "TDA"+id;
			break;         
		};
	
		elementTD.setAttribute("class", cellClass);
		if(document.all)
		{
			//Cette partie est ajoutée pour Internet Explorer
			var textAlign;
			var borderStyle;
			var height  = "31";
			var paddingRight;
			var width;
			var paddingLeft;
			switch (i)
			{       
				//quantite
				case 0 :
					textAlign = "center"; 
					borderStyle = "inset"; 
					width = "9%";
		 		break;         
				
				//code
				case 1 :               
					textAlign = "center"; 
					borderStyle = "inset"; 
					width = "9%";
				break;         
		
				//designation
				case 2 :
					textAlign = "left"; 
					borderStyle = "inset"; 
					paddingLeft = "2em"; 
					width = "64%";
	
					elementTD.style.paddingLeft = paddingLeft;
				break;         
	
				//prix
				case  3 :
					textAlign = "right"; 
					borderStyle = "inset"; 
					paddingRight = "1em"; 
					width = "9%";
	
					elementTD.style.paddingRight = paddingRight;
				break;         
	
				//montant
				case  4 :
					textAlign = "right"; 
					borderStyle = "inset"; 
					paddingRight = "1em"; 
					width = "9%";
	
					elementTD.style.paddingRight = paddingRight;
				break;         
			};
			elementTD.style.textAlign = textAlign;
			elementTD.style.borderStyle = borderStyle;
			elementTD.style.height = height;
			elementTD.style.width = width;
		}
		
		elementTD.setAttribute("id", tempId);
		
		row.appendChild(elementTD);

		elementTDTrait = document.createElement("td");
		elementTDTrait.setAttribute("class","border");
		if(document.all)
		{
			//Cette partie est ajoutée pour Internet Explorer
			elementTDTrait.style.textAlign = "center";
			elementTDTrait.style.borderStyle = "inset";
			elementTDTrait.style.height = "31";
		}
		elementTDTrait.innerHTML = "<b><font color='#FFCC00'>|</font></b>";
		row.appendChild(elementTDTrait);
	};
	positionner();
}

function displayRow(idNumber, quantity, code, label, price, amount)
{
	document.getElementById("TDQ"+idNumber).firstChild.innerHTML = quantity;
	document.getElementById("TDC"+idNumber).innerHTML = code;
	document.getElementById("TDL"+idNumber).innerHTML = label;
	document.getElementById("TDP"+idNumber).innerHTML = price;
	document.getElementById("TDA"+idNumber).innerHTML = amount;
}

function refreshFooter(cell, newQuantity, oldQuantity, newAmount, oldAmount)
{
	var minAmmountSumsTakeaway = parseFloat(tableOrdersApplet.getMinAmmountSumsTakeaway());
	var ratioReductionTakeaway = parseFloat(tableOrdersApplet.getRatioReductionTakeaway());						

	var parentDoc = window.parent.document;
	var quantitiesSum = parseFloat(parentDoc.getElementById("quantitiesSum").innerHTML);
	var amountsSum = parseFloat(parentDoc.getElementById("amountsSum").innerHTML);
	//Obliger de mettre le formulaire ici car bug dans Mozilla lors de la l'affectation 
	//window.parent.document.getElementById("idTdDataFooter").innerHTML
	var reductionRatio = parseFloat(parentDoc.getElementById("idReductionRatio").value);

	if(!isLastRow(cell) || oldAmount)
	{
		quantitiesSum = quantitiesSum - parseFloat(oldQuantity);			
		amountsSum  = amountsSum - parseFloat(oldAmount);		
	}
	quantitiesSum = quantitiesSum + parseFloat(newQuantity);			
	amountsSum  = amountsSum + parseFloat(newAmount);
	if(tableOrdersApplet.isTakeaway())
	{    
	    if(reductionRatio<=ratioReductionTakeaway && !tableOrdersApplet.isReductionRatioChanged())
	    {
	    	if(amountsSum>minAmmountSumsTakeaway)
		        reductionRatio = ratioReductionTakeaway;
		    else
			    reductionRatio = 0; 
			tableOrdersApplet.setReductionRatio(reductionRatio+"", "false");
	    }    
		parentDoc.getElementById("idReductionRatio").value = formatNumber(reductionRatio, 2);
	}
	parentDoc.getElementById("quantitiesSum").innerHTML = formatNumber(quantitiesSum, 1);			
	parentDoc.getElementById("amountsSum").innerHTML = formatNumber(amountsSum, 2);
	var reduction = formatNumber(specificRound(amountsSum*reductionRatio/100), 2);
	parentDoc.getElementById("amountPay").innerHTML = reductionRatio!="0.00"?formatNumber(amountsSum-reduction, 2):formatNumber(amountsSum, 2);
	if(reduction!="0.00")
		reduction = "-"+reduction;
	parentDoc.getElementById("reduction").innerHTML = reduction;
}

function getLabel(code, startCode)
{
	var result = tableOrdersApplet.getLabel(code);

	if(startCode=="#")
		result  =  "document.formulaireHiddenMessages.labelOffert.value"+" "+texteDesignation;

	return result;
}

function isLastRow(cell)
{
	return  (parseInt(currentRowId)==(document.getElementById("IDTableOrders").rows.length-1));
}

function isFirstRow(cell)
{
	return  (parseInt(currentRowId)==0);
}

function reductionOrders(ratio)
{
	var tempAmount = 0;
	for(var i=0; i<currentRowId; i++)
	{
		tempAmount += parseFloat(document.getElementById("TDA"+i).innerHTML);
	}

	var amount = specificRound(tempAmount*(ratio/100)*-1);
	if(amount!=0)
		amount*=-1;

	return formatNumber(amount, 2);
}

function saveOrderLine(cell)
{
	cell.value = cell.value.toUpperCase();

	var quantity =  document.getElementById("TDQ"+currentRowId).firstChild.innerHTML;
	var code = document.getElementById("TDC"+currentRowId).firstChild.nodeValue;
	var label = "";
	var price;
	var color;
	var oldAmount;
	var TDAmountElement = document.getElementById("TDA"+currentRowId);
	if(TDAmountElement.hasChildNodes())
		oldAmount = TDAmountElement.firstChild.nodeValue;

	if(cell.name == "code")
		code = cell.value;
	if(cell.name == "quantity")
	{
		replaceInputByTextNode(cell);
		quantity = cell.value;		
	}
	//Recherche de la désignation et du prix
	var isProductOrSessionValid = true;
	var startCode = code.substr(0,1);	
//	if(startCode=="/")
	if(code=="/")
	{
		label = document.getElementById("TDL"+currentRowId).firstChild.nodeValue;
		if(cell.name == "price")
			price = cell.value;
		else
			price = document.getElementById("TDP"+currentRowId).firstChild.nodeValue;
	}
	else
	{
		if(code == "-")
		{
			alert(document.alertMessagesForm.tableOrdersIFrameJspCodeInvalide.value);
			cell.value = "";
			return;
		}
/*	
		if(startCode == "-")
		{
			if(isNaN(code))
			{
				alert(document.alertMessagesForm.tableOrdersIFrameJspCodeInvalide.value);
				if(isLastRow(cell) && TDAmountElement.firstChild == null)
					cell.value = "";
				else				
					cell.value = oldValueCode;
				return;
			}
			if(tableOrdersApplet.isProductExist(startCode))
			{
				quantity = "1.0";
				label = tableOrdersApplet.getLabel(code)+" "+Math.abs(code)+"%";
				price = reductionOrders(code);
				color = tableOrdersApplet.getColor();
			}
			else
				isProductOrSessionValid = false;			
		} 
		else
*/		
		{
			var tempCode = code;
			if(startCode == "#" || startCode == "@")
			{
				if(tableOrdersApplet.isProductExist(startCode))
				{
					label = tableOrdersApplet.getLabel(code);
				}
				else
					isProductOrSessionValid = false;
				tempCode = code.substr(1);
			}
			
			if(tableOrdersApplet.isProductExist(tempCode))
			{
				label += tableOrdersApplet.getLabel(tempCode);
				if(startCode == "#" || startCode == "@")
				{
					price = "0.00";
				}
				else
				{
					price = formatNumber(tableOrdersApplet.getPrice(tempCode), 2);				
				}
				color = tableOrdersApplet.getColor();				
			}
			else
				isProductOrSessionValid = false;
		}
	}
	//On suppose que la session reste valide pendant un temps certain(long moment)
	if(!isProductOrSessionValid)
	{
		if(tableOrdersApplet.isSessionValide())	
		{
			alert(document.alertMessagesForm.tableOrdersIFrameJspProductNotExist.value);
			if(isLastRow(cell) && TDAmountElement.firstChild == null)
				cell.value = "";
			else				
				cell.value = oldValueCode;
		}
		else
			window.parent.document.location = contextPath+"/EntryPoint.do";
		return;
	}

	if(color)
		cell.parentNode.parentNode.style.background = "#"+color;

	var newAmount = formatNumber(parseFloat(quantity)*parseFloat(price), 2);

	var orderLineId = document.getElementById("TDC"+currentRowId).parentNode.id;

	document.getElementById("TDC"+currentRowId).parentNode.id = tableOrdersApplet.saveOrderLine(orderLineId, quantity, code, label, price, newAmount);

	displayRow(currentRowId, quantity, code, label, price, newAmount);
	refreshFooter(cell, quantity, oldValueQuantity, newAmount, oldAmount);

	addRow(cell);

	hashMapOrders[code] = currentRowId;
}

function deleteRowById(cell)
{
	var lastOrderRowIdFound = hashMapOrders[cell.value];
	var lastOrderQuantityFound = document.getElementById("TDQ"+lastOrderRowIdFound).firstChild.innerHTML;

	//Faire cette action avant de supprimer la ligne de commande dans le cas où c'est la dernière ligne car on efface la table
	var isReductionRatioChanged;
	//S'il reste que 2 lignes
	if(parseInt(currentRowId)==1 && parseInt(lastOrderRowIdFound)==0)
		isReductionRatioChanged = tableOrdersApplet.isReductionRatioChanged();

	var orderLineId = document.getElementById("TDC"+lastOrderRowIdFound).parentNode.id;
	var oldAmount;
	var TDAmountElement = document.getElementById("TDA"+lastOrderRowIdFound);
	if(TDAmountElement.hasChildNodes())
		oldAmount = TDAmountElement.firstChild.nodeValue;
	refreshFooter(cell, '0.0', lastOrderQuantityFound, '0.00', oldAmount);
	document.getElementById("IDTableOrders").deleteRow(lastOrderRowIdFound);
	currentRowId = lastOrderRowIdFound;
	updateRowId();
	currentRowId = cell.parentNode.id.substr(3);
	backward(cell);

	if(!tableOrdersApplet.deleteOrderLine(orderLineId))
		window.parent.document.location = contextPath+"/EntryPoint.do";
	else if(window.parent.document.getElementById("quantitiesSum").innerHTML == "0.0")
	{
		if(isReductionRatioChanged)
		{
			var cellReductionRatio = window.parent.document.getElementById("idReductionRatio");
			cellReductionRatio.value = "0.00";
			cellReductionRatio.setAttribute("class", "reductionRatioYellow");
			if(document.all)
				//Dans le cas d'IE la méthode setAttribute pour l'élément class n'aucun effet
				cellReductionRatio.style.backgroundColor = "yellow";
			alert(document.alertMessagesForm.tableOrdersIFrameJspRestoreReductionRatio.value);
		}	
	}
}

function updateOrderLine(cell)
{
	//Le risque ici est que pour un code produit donné, ce-dernier peut avoir changé de désignation ou de prix
	var lastOrderRowIdFound = hashMapOrders[cell.value];
	var lastOrderQuantityFound = document.getElementById("TDQ"+lastOrderRowIdFound).firstChild.innerHTML;
	var lastOrderLabelFound = document.getElementById("TDL"+lastOrderRowIdFound).innerHTML;
	var lastOrderPriceFound = document.getElementById("TDP"+lastOrderRowIdFound).innerHTML;
	var lastOrderAmountFound = document.getElementById("TDA"+lastOrderRowIdFound).innerHTML;

	var quantity =  document.getElementById("TDQ"+currentRowId).firstChild.innerHTML;
	quantity = formatNumber(parseFloat(quantity)+parseFloat(lastOrderQuantityFound), 1);
	var	code = cell.value;
	var label = lastOrderLabelFound;
	var price = lastOrderPriceFound;

	var newAmount = formatNumber(parseFloat(quantity)*parseFloat(price), 2);
	var orderLineId = document.getElementById("TDC"+lastOrderRowIdFound).parentNode.id;

	if(parseFloat(quantity)>0)
	{
		tableOrdersApplet.saveOrderLine(orderLineId, quantity, code, label, price, newAmount);
//alert(orderLineId+", "+quantity+", "+code+", "+label+", "+price+", "+newAmount+", "+oldAmount+", "+oldValueQuantity)
		displayRow(lastOrderRowIdFound, quantity, code, label, price, newAmount);
	
		refreshFooter(cell, quantity, lastOrderQuantityFound, newAmount, lastOrderAmountFound);
	}
	else if(parseFloat(quantity)==0)
	{
		deleteRowById(cell);
	}
	else
	{
		alert(document.alertMessagesForm.alertLabelNegativeQuantity.value);	
	}
	backward(cell);
}

function processCode(cell)
{
	var isAutoUpdateOrderLine = window.parent.document.getElementById("idAutoUpdateOrderLine").checked;
	cell.value = cell.value.toUpperCase();

	if((eventX.keyCode==13)&&(cell.value!=""))
	{
		if(parseFloat(document.getElementById("AQ"+currentRowId).innerHTML)<0)
		{
			//Dans ce cas, on est sur d'etre en derniere ligne et en ajout automatique
			if(cell.value=="/" || !hashMapOrders[cell.value])
			{
				alert(document.alertMessagesForm.alertLabelNegativeQuantity.value);
				backward(cell);
				return;
			}
		}
		if(cell.value=="/")
			changeColumn(cell);
		else
		{
			if(isLastRow(cell) && isAutoUpdateOrderLine && hashMapOrders[cell.value.toUpperCase()])
			{
				if(document.getElementById("TDA"+currentRowId).innerHTML)
				{
					saveOrderLine(cell);
				}	
				else
				{
					updateOrderLine(cell);
				}	
			}
			else
			{
//alert("hashMapOrders[cell.value]="+hashMapOrders[cell.value]+" cell.value="+cell.value+" oldValueCode="+oldValueCode)
				if(!isLastRow(cell))
				{
					hashMapOrders[oldValueCode] = null;
				}
				else
				{
					if(!hashMapOrders[cell.value])
					{
						var length = document.getElementById("IDTableOrders").rows.length;
						hashMapOrders = new Array();
						for(var i=0; i<length; i++)
							hashMapOrders[document.getElementById("TDC"+i).innerHTML] = i+"";
					}	
					if(isAutoUpdateOrderLine && hashMapOrders[cell.value])
					{
						updateOrderLine(cell);
						return;
					}
				}
				saveOrderLine(cell);
			}	
		}	
	}
}

function processLabel(cell)
{
	if((eventX.keyCode==13)&&(cell.value!=""))
		changeColumn(cell);
}

function processPrice(cell)
{
	if(isNaN(cell.value) || cell.value=="")
		cell.value = 0;

	cell.value = formatNumber(cell.value,2);

	replaceInputByTextNode(cell);

	saveOrderLine(cell);
}

function processCell(cell)
{
	if(window.parent.document.getElementById("idLabelPrinting").firstChild && !tableOrdersApplet.isAllowModifyOrdersPrinting())
	{
		allowModifyOrders();
		return;
	}

	if((!isNaN(cell.value) && cell.value<0))
	{
		if(cell.name!="label" && cell.name!="code")
		{
			if(cell.name=="price")
			{
				var quantity = parseFloat(document.getElementById("TDQ"+currentRowId).firstChild.innerHTML);
				var price = parseFloat(cell.value);
				var amountsSum = parseFloat(window.parent.document.getElementById("amountsSum").innerHTML);
//alert("quantity="+quantity+" price="+price+" amountsSum="+amountsSum)				
				if(isLastRow(cell) && !document.getElementById("TDA"+currentRowId).innerHTML  && (amountsSum+quantity*price>=0))
				{
					//Rien faire
				}
				else
				{
					alert(document.alertMessagesForm.alertLabelNegativePrice.value);
					return;
				}
			}	
			if(cell.name=="quantity")
			{
				if(isLastRow(cell) && window.parent.document.getElementById("idAutoUpdateOrderLine").checked && !document.getElementById("TDA"+currentRowId).innerHTML)
				{
					//Rien faire
				}
				else
				{
					cell.value = "";
					alert(document.alertMessagesForm.alertLabelNegativeQuantity.value);
					return;
				}	
			}	
		}	
	}		

	//Replace ' by nothing
	cell.value = cell.value.replace("'", "");	

	switch(cell.name)
	{       
		//quantite
		case "quantity" :    
			processQuantity(cell);
		break;         
	
		//codeProduit
		case "code" :        
			processCode(cell);
		break;         
			
		//designation
		case "label" :               
			processLabel(cell);
		break;         
	
		//prix
		case "price" :               
			processPrice(cell);
		break;         
	};
}

function getAllOldValue(cell)
{
	switch(cell.name)
	{
		case "quantity" :
			oldValueQuantity = cell.value;
			oldValueCode = document.getElementById("TDC"+currentRowId).firstChild.nodeValue;
			oldValueLabel = document.getElementById("TDL"+currentRowId).firstChild.nodeValue;
			oldValuePrice = document.getElementById("TDP"+currentRowId).firstChild.nodeValue;
		break;
		case "code" :
			oldValueQuantity = document.getElementById("AQ"+currentRowId).firstChild.nodeValue;
			oldValueCode = cellule.value;
			oldValueLabel = document.getElementById("TDL"+currentRowId).firstChild.nodeValue;
			oldValuePrice = document.getElementById("TDP"+currentRowId).firstChild.nodeValue;
		break;
		case "label" :
			oldValueQuantity = document.getElementById("AQ"+currentRowId).firstChild.nodeValue;
			oldValueCode = document.getElementById("TDC"+currentRowId).firstChild.nodeValue;
			oldValueLabel = cellule.value;
			oldValuePrice = document.getElementById("TDP"+currentRowId).firstChild.nodeValue;
		break;
		case "price" :
			oldValueQuantity = document.getElementById("AQ"+currentRowId).firstChild.nodeValue;
			oldValueCode = document.getElementById("TDC"+currentRowId).firstChild.nodeValue;
			oldValueLabel = document.getElementById("TDL"+currentRowId).firstChild.nodeValue;
			oldValuePrice = cellule.value;
		break;
	}
}

function move(cell, numberOfRow)
{
	if(isLastRow(cell) && document.getElementById("TDC"+currentRowId).firstChild==null)
		document.getElementById("IDTableOrders").deleteRow(currentRowId);
	else
	{
		replaceInputByTextNode(cell);
		getAllOldValue(cell);
	}

	if(numberOfRow!=0)
	{
		for(var i=0; i<Math.abs(numberOfRow); i++)
			if(numberOfRow>0)
				currentRowId--;
			else
				currentRowId++;

		var newCellValue = "";
		var parentNewCell = document.getElementById("TDQ"+currentRowId);	
		newCellValue = parentNewCell.firstChild.innerHTML; 
		parentNewCell.setAttribute("class", "QuantityCenter");
		if(document.all)
		{
			//Cette partie est ajoutée pour Internet Explorer
			parentNewCell.style.textAlign = "center";
			parentNewCell.style.borderStyle = "inset";
			parentNewCell.style.height = "31";
			parentNewCell.style.width = "9%";
			parentNewCell.style.paddingRight = "0em";
		}
		
		parentNewCell.innerHTML = "<input type='text' ID='Q' isValueEmpty='true' value='"+newCellValue+"' name='quantity' class='quantity' onkeyup='processUserEntry(event, this)' maxlength=5 >";
		var newCell = parentNewCell.firstChild;

		if(newCell.value!="")
			newCell.isValueEmpty = "false";

		getAllOldValue(newCell);
		
		if(isFirstRow(cell))
		{
			window.location.href = "#idBody";
			window.location.href = "#idBody";			
		}
		//On refait un deuxiè�me focus pour que cela marche : Va Savoir Pourquoi !
		newCell.focus();
		newCell.focus();		
		newCell.value = oldValueQuantity.toUpperCase();
	}
}

function allowModifyOrders()
{
	if(window.parent.document.getElementById("idLabelPrinting").firstChild && !tableOrdersApplet.isAllowModifyOrdersPrinting())
	{
		document.modifyTableForm.pageRequested.value = "successPasswordChangeOrders";
		document.modifyTableForm.actionPasswordChangeOrders.value = "allowModifyOrders";
		document.modifyTableForm.submit();
	}
}

function up(cell)
{
	if(cell.name=="quantity" && currentRowId!="0")
	{
		if(window.parent.document.getElementById("idLabelPrinting").firstChild && !tableOrdersApplet.isAllowModifyOrdersPrinting())
		{
			allowModifyOrders();
		}
		else
		{
			if(cell.value == "")
				cell.value = oldValueQuantity;
	
			cell.value = formatNumber(cell.value, 1);		

			if(oldValueQuantity==cell.value || document.getElementById("TDA"+currentRowId).firstChild == null)
			{
				move(cell, 1);
			}
			else
			{
				if(cell.value != "")
				{
					saveOrderLine(cell);
				}
				else
				{
					cell.value = oldValueQuantity;
					up(cell);
				}
			}
		}
	}
}

function down(cell)
{
	if(cell.name=="quantity")
	{
		if(cell.value!="")
			cell.value = formatNumber(cell.value, 1);		
		
		if(!isLastRow(cell))
		{
			if(oldValueQuantity==cell.value)
				move(cell, -1);
			else
			{
				if(cell.value != "")
					saveOrderLine(cell);
				else
				{
					cell.value = oldValueQuantity;
					down(cell);
				}
			}
		}
		else
		{
			if(cell.value != "" && cell.parentNode.nextSibling.nextSibling.firstChild && cell.parentNode.nextSibling.nextSibling.firstChild.nodeValue != "")
			{
				if(oldValueQuantity!=cell.value)
					saveOrderLine(cell);
				else
				{	
					move(cell, 0);
					addRow(cell);
				}
			}
		}
	}
}

function setInputText(cell)
{
	if(window.parent.document.getElementById("idLabelPrinting").firstChild && !tableOrdersApplet.isAllowModifyOrdersPrinting())
	{
		allowModifyOrders();
	}
	else
	{
		var oldCell = document.getElementsByTagName("input").item(0);
		currentRowId = oldCell.parentNode.id.substr(3);

		if(oldCell.name=="quantity")
		{
			var newRowId = parseInt(cell.id.substr(2));
			move(oldCell, currentRowId-newRowId)
			currentRowId = newRowId;
		}
		else
		{
			alert(document.alertMessagesForm.tableOrdersIFrameJspMoveForbidden.value);
			oldCell.focus();
		}
	}
}

function updateRowId()
{
	var length = document.getElementById("IDTableOrders").rows.length;
	var j = 0;
	for(var i=0; i<=length; i++)
	{
		if(i>currentRowId)
			j=i-1;
		else 
			continue;
		
		if(i<length)
			document.getElementById("AQ"+i).id = "AQ"+j;

		document.getElementById("TDQ"+i).id = "TDQ"+j;
		document.getElementById("TDC"+i).id = "TDC"+j;
		document.getElementById("TDL"+i).id = "TDL"+j;
		document.getElementById("TDP"+i).id = "TDP"+j;		
		document.getElementById("TDA"+i).id = "TDA"+j;
	}
	
	var isAutoUpdateOrderLine = window.parent.document.getElementById("idAutoUpdateOrderLine").checked;
	if(isAutoUpdateOrderLine)
	{
		hashMapOrders = new Array();
		for(var i=0; i<length; i++)
		{
			hashMapOrders[document.getElementById("TDC"+i).innerHTML] = i+"";
//alert(i+":"+document.getElementById("TDC"+i).innerHTML+":"+hashMapOrders[document.getElementById("TDC"+i).innerHTML])		
			
		}
	}
}

function deleteRow(cell)
{
	if(cell.name=="quantity")
	{
		if(isLastRow(cell) && document.getElementById("TDC"+currentRowId).firstChild == null)
			return;

		//Faire cette action avant de supprimer la ligne de commande dans le cas où c'est la dernière ligne car on efface la table
		var isReductionRatioChanged;
		if(isLastRow(cell))
			isReductionRatioChanged = tableOrdersApplet.isReductionRatioChanged();
		
		var orderLineId = document.getElementById("TDC"+currentRowId).parentNode.id;

		var oldAmount;
		var TDAmountElement = document.getElementById("TDA"+currentRowId);
		if(TDAmountElement.hasChildNodes())
			oldAmount = TDAmountElement.firstChild.nodeValue;
		refreshFooter(cell, '0.0', cell.value, '0.00', oldAmount);

		document.getElementById("IDTableOrders").deleteRow(currentRowId);
		updateRowId();

		addRow(cell);
		
		if(!tableOrdersApplet.deleteOrderLine(orderLineId))
				window.parent.document.location = contextPath+"/EntryPoint.do";
		else if(window.parent.document.getElementById("quantitiesSum").innerHTML == "0.0")
		{
			if(isReductionRatioChanged)
			{
				var cellReductionRatio = window.parent.document.getElementById("idReductionRatio");
				cellReductionRatio.value = "0.00";
				cellReductionRatio.setAttribute("class", "reductionRatioYellow");
				if(document.all)
					//Dans le cas d'IE la méthode setAttribute pour l'élément class n'aucun effet
					cellReductionRatio.style.backgroundColor = "yellow";
				alert(document.alertMessagesForm.tableOrdersIFrameJspRestoreReductionRatio.value);
			}	
		}
	}
}

function resetLastRow(cell)
{
	var elementTD = document.getElementById("TDQ"+currentRowId);	
	elementTD.setAttribute("class", "quantityCenter");
	if(document.all)
	{
		//Cette partie est ajoutée pour Internet Explorer
		elementTD.style.textAlign = "center";
		elementTD.style.borderStyle = "inset";
		elementTD.style.height = "31";
		elementTD.style.width = "9%";
		elementTD.style.paddingRight = "0em";
	}
	elementTD.innerHTML ="<input type='text' ID='Q' name='quantity' isValueEmpty='true' class='quantity' onkeyup='processUserEntry(event, this)' maxlength='5'>";

	if(isFirstRow(cell))
	{
		window.location.href = "#idBody";
		window.location.href = "#idBody";			
	}

	switch(cell.name)
	{
		case "price":
			document.getElementById("TDP"+currentRowId).innerHTML="";			
		case "label":
			document.getElementById("TDL"+currentRowId).innerHTML="";			
		case "code":
			document.getElementById("TDC"+currentRowId).innerHTML="";			
	}
	document.getElementById("TD"+cell.name.charAt(0).toUpperCase()+currentRowId).setAttribute("class", cell.name);
	if(document.all)
	{
		//Cette partie est ajoutée pour Internet Explorer
		var elementTD = document.getElementById("TD"+cell.name.charAt(0).toUpperCase()+currentRowId);
		var textAlign;
		var borderStyle;
		var height = "31";
		var paddingRight;
		var width;
		var paddingLeft;
		switch (cell.name)
		{       
			//quantite
			case "quantity" :
				textAlign = "center"; 
				borderStyle = "inset"; 
				width = "9%";
	 		break;         
			
			//code
			case "code" :               
				textAlign = "center"; 
				borderStyle = "inset"; 
				width = "9%";
			break;         
		
			//designation
			case "label" :
				textAlign = "left"; 
				borderStyle = "inset"; 
				paddingLeft = "2em"; 
				width = "64%";
	
				elementTD.style.paddingLeft = paddingLeft;
			break;         
	
			//prix
			case  "price" :
				textAlign = "right"; 
				borderStyle = "inset"; 
				paddingRight = "1em"; 
				width = "9%";
	
				elementTD.style.paddingRight = paddingRight;
			break;         
	
			//montant
			case  "amount" :
				textAlign = "right"; 
				borderStyle = "inset"; 
				paddingRight = "1em"; 
				width = "9%";

				elementTD.style.paddingRight = paddingRight;
			break;         
		};
		elementTD.style.textAlign = textAlign;
		elementTD.style.borderStyle = borderStyle;
		elementTD.style.height = height;
		elementTD.style.width = width;
	}
	
	document.getElementById("Q").focus();	
	document.getElementById("Q").focus();		
}

function backward(cell)
{
	if(isLastRow(cell))
	{
	 	if(document.getElementById("TDA"+currentRowId).firstChild == null)
		{
			if(cell.name == "quantity")
			{
				changeModule(1);
			}	
			else
				resetLastRow(cell);
			return;
		}
	}

	if(cell.name == "label")
		cell = changeColumn(cell);
	replaceInputByTextNode(cell);
	document.getElementById("TDQ"+currentRowId).innerHTML = "<a class='quantity' href='#' ID='AQ"+currentRowId+"' onclick='setInputText(this)'>"+oldValueQuantity+"</a>";
	document.getElementById("TDC"+currentRowId).innerHTML = oldValueCode;
	document.getElementById("TDL"+currentRowId).innerHTML = oldValueLabel;
	document.getElementById("TDP"+currentRowId).innerHTML = oldValuePrice;
	addRow(cell);	
}

function mergeTable(cell)
{
	if(window.parent.document.getElementById("idLabelPrinting").firstChild) // && !tableOrdersApplet.isAllowModifyOrdersPrinting())
	{
		alert(document.alertMessagesForm.tableOrdersIFrameJspAlertMergeForbidden.value);
	}
	else
	{
		if(!cell || cell.name=="quantity")
		{
			document.modifyTableForm.pageRequested.value = "successMergeTable";
			document.modifyTableForm.submit();
		}
	}	
}

function changeTable(cell)
{
/*
	if(window.parent.document.getElementById("idLabelPrinting").firstChild)// && !tableOrdersApplet.isAllowModifyOrdersPrinting())
	{
		alert(document.alertMessagesForm.tableOrdersIFrameJspAlertChangeForbidden.value);
	}
	else
*/	
	{
		if(!cell || cell.name=="quantity")
		{
			document.modifyTableForm.pageRequested.value = "successChangeTable";
			document.modifyTableForm.submit();
		}
	}	
}

function cashTable(cell)
{
	if(!cell || cell.name=="quantity")
	{
		document.modifyTableForm.pageRequested.value = "successCashTable";
		document.modifyTableForm.submit();
	}
}

function createBillsTable(cell)
{
	if(!cell || cell.name=="quantity")
	{
		document.modifyTableForm.pageRequested.value = "successCreateBillsTable";
		document.modifyTableForm.submit();
	}
}

function createCreditsTable(cell)
{
	if(!cell || cell.name=="quantity")
	{
		document.modifyTableForm.pageRequested.value = "successCreateCreditsTable";
		document.modifyTableForm.submit();
	}
}

function printTable(cell)
{
	if(!cell || cell.name=="quantity")
	{
		document.modifyTableForm.pageRequested.value = "successPrintTable";
		document.modifyTableForm.submit();
	}
}

function billPrint()
{
	document.modifyTableForm.printingType.value = "BILL";
	document.modifyTableForm.pageRequested.value = "successCustomerBillInfo";
	document.modifyTableForm.submit();
}

function deliveryPrint()
{
	document.modifyTableForm.printingType.value = "DELIVERY";
	document.modifyTableForm.pageRequested.value = "successCustomerDeliveryInfo";
	document.modifyTableForm.submit();
}

function deleteTable()
{
	if(window.parent.document.getElementById("idLabelPrinting").firstChild) //&& !tableOrdersApplet.isAllowModifyOrdersPrinting())
	{
		alert(document.alertMessagesForm.tableOrdersIFrameJspAlertRemoveForbidden.value);
	}
	else
	{
		document.modifyTableForm.pageRequested.value = "successDeleteTable";
		document.modifyTableForm.submit();
	}	
}

function focusReductionRatio(cell)
{
	if(cell.name=="quantity")
	{
		if(window.parent.document.getElementById("idLabelPrinting").firstChild && !tableOrdersApplet.isAllowModifyOrdersPrinting())
		{
			allowModifyOrders();
		}
		else
		{
			if(window.parent.document.getElementById("quantitiesSum").innerHTML != "0.0")
			{
				cell.value = "";
				window.parent.document.getElementById("idReductionRatio").focus();
			}
			else
				alert(document.alertMessagesForm.tableOrdersIFrameJspUpdateReductionRatioForbidden.value);
		}
	}	
}

var eventX;
function processUserEntry(event, cell)
{
	eventX = event?event:window.event;
	//cell.value = cell.value.toUpperCase();	

	currentRowId = cell.parentNode.id.substr(3);

	if(eventX.keyCode!=13 && eventX.keyCode!=27)
		checkEntry(cell);

//alert(eventX.keyCode)
	switch(eventX.keyCode)
	{     	  
		//13 équivaut � la touche ENTRER
		case 13 :
			processCell(cell);
		return;

		//27 équivaut � la touche Echap
		case 27 :
			backward(cell);
		return;

		//33 équivaut � la touche PageUp pour cumuler une table � une autre
		case 33 :
			mergeTable(cell);			
		return;
		
		//34 équivaut � la touche PageDown pour changer le num�ro de la table
		case 34 :
			changeTable(cell);
		return;

		//35 équivaut � la touche Fin pour encaisser
		case 35 :
			cashTable(cell);
		return;

		//36 équivaut � la touche Home pour imprimer
		case 36 :
			printTable(cell);
		return;
		
		//66 équivaut a la lettre B pour creer des factures
		case 66 :
			createBillsTable(cell);
		return;
		
		//65 équivaut a la lettre A pour creer des avoirs
		case 65 :
			createCreditsTable(cell);
		return;

		//65 équivaut � la lettre A pour autoriser la mise à jour automatique des lignes de commandes
		case 65 :
			if(cell.name=="quantity")
				window.parent.document.getElementById("idAutoUpdateOrderLine").click();
		return;
		
		//38 équivaut � la touche Up
		case 38 :
			up(cell);
		return;
		
		//40 équivaut � la touche Down
		case 40 :
			down(cell);
		return;

		//46 équivaut � la touche Suppr
		case 46 :
			deleteRow(cell);
		return;

		//84 équivaut � la lettre T pour passer � la modification du taux de r�duction.
		case 84 :
			focusReductionRatio(cell);
		return;
	};
}

//Partie concernant le taux de réduction
var oldReductionRatioValue = "";
function resetOldReductionRatioValue()
{
	oldReductionRatioValue = "";
}

function clearRatio(cell)
{
	if(document.getElementById("idLabelPrinting").firstChild && !tableOrdersApplet.isAllowModifyOrdersPrinting())
	{
		allowModifyOrders();
	}
	else
	{
		if(window.parent.document.getElementById("quantitiesSum").innerHTML == "0.0")
		{
			alert(document.alertMessagesForm.tableOrdersIFrameJspUpdateReductionRatioForbidden.value);
			focusFirstInputText(document);
			return;
		}

		oldReductionRatioValue = cell.value;
		cell.value = "";
	}	
}

function processUserEntryReductionRatio(event, cell)
{
	eventX = event?event:window.event;

	if(oldReductionRatioValue=="")
	{
		if(eventX.keyCode != 27)
		{
			oldReductionRatioValue = cell.value.substr(0, cell.value.length-1); 
			cell.value = cell.value.charAt(cell.value.length-1);
		}
		else
			oldReductionRatioValue = cell.value; 
	}

	cell.value = cell.value.toUpperCase();

	isDecimal(cell);

	switch(eventX.keyCode)
	{     	  
		//13 équivaut à la touche ENTRER
		case 13 :
			updateReductionRatio(cell);
		return; 

		//27 équivaut à la touche Echap
		case 27 :
			cell.value = formatNumber(oldReductionRatioValue, 2);
			focusFirstInputText(document);
		return;
	};
}

function updateReductionRatio(cell)
{
	if(cell.value=="")
	{
		cell.value = formatNumber(oldReductionRatioValue, 2);
		focusFirstInputText(document);	
		return;
	}

	cell.value = formatNumber(cell.value, 2);

	if(parseFloat(cell.value)>100)
	{
		var oldReductionRatioValueTemp = oldReductionRatioValue;
		alert(document.alertMessagesForm.tableOrdersIFrameJspReductionIncorrectInput.value);
		cell.value = "";
		cell.focus();
		oldTauxReductionValue = oldReductionRatioValueTemp;
		return;
	}

	if(!tableOrdersApplet.setReductionRatio(cell.value, "true"))
		window.parent.document.location = contextPath+"/EntryPoint.do";

	var amountsSum = window.parent.document.getElementById("amountsSum").innerHTML;
	var reduction = formatNumber(specificRound(amountsSum*cell.value/100), 2);
	var amountPay = cell.value!="0.00"?formatNumber(amountsSum-reduction, 2):amountsSum;

	if(reduction!="0.00")
		reduction = "-"+reduction;
	
	cell.setAttribute("class", "reductionRatioRed");
	if(document.all)
		//Dans le cas d'IE la méthode setAttribute pour l'élément class n'aucun effet
		cell.style.backgroundColor = "red";

	window.parent.document.getElementById("reduction").innerHTML = reduction;
	window.parent.document.getElementById("amountPay").innerHTML = amountPay;

	focusFirstInputText(document);
}

function resetPrintDate()
{
	document.modifyTableForm.pageRequested.value = "successPasswordChangeOrders";
	document.modifyTableForm.actionPasswordChangeOrders.value = "resetPrintDate";
	document.modifyTableForm.submit();
}

function initPageIFrame()
{
	document.getElementById("idDivIFrame").style.height = screen.height;
	document.getElementById("idTableDataHeader").style.width =  document.body.clientWidth*100/(self.innerWidth?(self.innerWidth):(document.documentElement.offsetWidth))+"%";
	window.parent.document.getElementById("idTdDataOptions").innerHTML = document.getElementById("idTdDataOptions").innerHTML;
	window.parent.document.getElementById("idTdDataHeader").innerHTML = document.getElementById("idTdDataHeader").innerHTML;
	window.parent.document.getElementById("idTdDataFooter").innerHTML = document.getElementById("idTdDataFooter").innerHTML;
	positionner();

	//Dans le cas d'un changement de nombre de personnes quand l'impression de la table a déjà été faite
	if(document.modifyTableForm.isTakeaway && document.modifyTableForm.isTakeaway.value=="false")
		window.parent.document.getElementById("idTDCustomerNumber").innerHTML = document.modifyTableForm.customersNumber.value;
	
	//Ceci est fait pour donner le focus à la fenêtre centrale dans le cas du browser ie
	if(document.getElementById)
		window.focus();
		
	document.forms[0].quantity.focus();
}

-->









