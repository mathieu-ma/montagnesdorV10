var MAX_PRINTING_COLUMN1 = 40;	
var MAX_PRINTING_COLUMN2 = 16;	

var separator = "";
for(var i=0; i<MAX_PRINTING_COLUMN1; i++) separator += '-';	

function cancel()
{
	if(focusFirstInputText(window.parent.document))
	{
		document.forms[0].action = contextPath+"/TablesListExcludeCashedTableIFrame.do";
		document.forms[0].submit();
	}	
	else
	{
//		document.modifyTableForm.pageRequested.value = "successOrderTable";
//		document.modifyTableForm.submit();
		document.forms[0].action = contextPath+"/TableOrdersIFrame.do";
		document.forms[0].submit();
	}	
}

function processPrinting(printingType)
{
	//1) Ajouter un input text pour la saisie du numéro de table
	//Ou bien donner le focus
	if(!window.parent.document.getElementById("idCustomerNumber"))
		resetTableNumber();
	else
		focusFirstInputText(window.parent.document);

	//2) Imprimer la table avec l'applet PrintingApplet
	if(!printingType || printingType=="")
		printingType = "NONE";
	switch (printingType) {
		case "NONE":
				printing();
			break;
		case "BILL":
				printingBill();
			break;
		case "DELIVERY":
				printingDelivery();
			break;
		default:
			break;
	}

	//3) Mise à jour de table courante
	document.forms[0].action = contextPath+"/PrintTable.do";
	document.forms[0].pageRequested.value = "successTablesListIFrame";
	document.forms[0].date.value = window.parent.document.tableOrdersApplet.getEntryDate().substring(0, 10)+"-"+window.parent.document.dateTimeApplet.getTime();
	document.forms[0].submit();
}

function processPrintingByCashing()
{
	//1) Ajouter un input text pour la saisie du numéro de table
	//Ou bien donner le focus
	if(!window.parent.document.getElementById("idCustomerNumber"))
		resetTableNumber();
	else
		focusFirstInputText(window.parent.document);

	//2) Imprimer la table avec l'applet PrintingApplet
	printing();
	
	//3) Afficher la liste des recettes journalières 
	document.forms[0].submit();
}

function spacePadding(str, maxLen, direction)
{
	var strLength = str.length;
	if(strLength>=maxLen)
	{
		return str.substr(0, maxLen);
	}

	spaces = "";
    nbrPaddingSpace = maxLen-strLength;
	for(var i=0; i<nbrPaddingSpace; i++)
    	spaces += " ";

	if(direction=="right")
		str += spaces;
	else
	 	str = spaces+str;

	return str;
}

function spacePaddingCenter(str)
{
	var halfLength = (MAX_PRINTING_COLUMN1-str.length)/2;
	str = spacePadding(str, str.length+halfLength, "left");
	str = spacePadding(str, str.length+halfLength, "right");

	return str;
}

function getOrderLine(indexOrder)
{
	var quantity = document.getElementById("TDQ"+indexOrder).innerHTML;
	var label = document.getElementById("TDL"+indexOrder).innerHTML;
	var amount = document.getElementById("TDA"+indexOrder).innerHTML;
	var result = spacePadding(quantity, 5, "left")+"  "+spacePadding(label, 23, "right")+"  "+spacePadding(amount, 8, "left");

	return result;
}

function printing()
{
//	alert("Debut Applet imprime ");

	var printerApplet = window.parent.document.printerApplet;

	printingHeader(printerApplet);
	
	// SPECIFIC
	{
		//Infos Table
		printerApplet.addData1(document.getElementById("registrationDate").innerHTML);	
		printerApplet.addData2(document.getElementById("tableCustomerNumber").innerHTML);
		printerApplet.addData1(" ");
	}

	//Commandes
	// SPECIFIC
	{
		printingOrder(printerApplet);
	}

	printingFooter(printerApplet, true);

	// Send data to printer
	printerApplet.print();

//	alert("Fin Applet imprime");
}

function printingOrder(printerApplet)
{
	printerApplet.addData1(spacePadding(document.getElementById("ordersQuantity").innerHTML, 5, "left")+spacePadding(document.getElementById("ordersLabel").innerHTML, 20, "left")+spacePadding(document.getElementById("ordersAmount").innerHTML, 15, "left"));
	printerApplet.addData1(separator);
	var ordersLineNumber = document.getElementById("ordersLine").rows.length;
	var startCode;
	for(var i=0; i<ordersLineNumber; i++)
	{
		startCode = document.getElementById("TDC"+i).innerHTML;
		if(startCode.substr(0,1)=="#")
			printerApplet.addData1(separator);
		printerApplet.addData1(getOrderLine(i));
		if(startCode.substr(0,1)=="#")
			printerApplet.addData1(separator);
	}
	printerApplet.addData1(" ");
}

function printingHeader(printerApplet)
{
	//Vider le buffer de l'applet 
	printerApplet.resetDataBuffer();

	//Entete
	printerApplet.addData2(document.getElementById("restaurantName").innerHTML);
	printerApplet.addData1(document.getElementById("restaurantAddressRoad").innerHTML);
	printerApplet.addData1(document.getElementById("restaurantAddressZipcode").innerHTML+" "+document.getElementById("restaurantAddressCity").innerHTML);
	printerApplet.addData1(document.getElementById("restaurantPhone").innerHTML);
	printerApplet.addData1(document.getElementById("restaurantOthersInfo1").innerHTML);
	printerApplet.addData1(document.getElementById("restaurantOthersInfo2").innerHTML);
	//Représente un saut de ligne pour l'imprimante
	printerApplet.addData1(" ");
}

function printingVat(printerApplet)
{
	var length = 0;
	var sumAmountWithoutVat = 0;
	var vatsNumber = Number(document.getElementById("vatsNumber").innerHTML);
	for(var i=0; i<vatsNumber; i++)
	{
		length = (document.getElementById("vatValueLabel"+i).innerHTML+" ").length;
		if(parseFloat(document.getElementById("vatValue"+i).innerHTML)!=0)
		{
			printerApplet.addData1(document.getElementById("vatValueLabel"+i).innerHTML+" "+spacePadding(document.getElementById("vatValue"+i).innerHTML, MAX_PRINTING_COLUMN1-length, "left"));
		}
		sumAmountWithoutVat += parseFloat(document.getElementById("vatValue"+i).innerHTML);
	}
	sumAmountWithoutVat = parseFloat(document.getElementById("amountPay").innerHTML)-sumAmountWithoutVat;
	lenght = (document.getElementById("amountTaxeNet").innerHTML+" ").length;
	printerApplet.addData1(document.getElementById("amountTaxeNet").innerHTML+" "+spacePadding(formatNumber(sumAmountWithoutVat, 2), MAX_PRINTING_COLUMN1-lenght, "left"));			
	printerApplet.addData1(document.getElementById("serviceIncluded").innerHTML);
	printerApplet.addData1(" ");
}

function printingFooter(printerApplet, isPrintingVat)
{
	//Réduction
	var reduction = document.getElementById("reduction").innerHTML;
	if(reduction!="0.00")
	{
		printerApplet.addData1(separator);
		printerApplet.addData1(spacePaddingCenter(document.getElementById("reductionRatio").innerHTML+" "+document.getElementById("reductionLabel").innerHTML+" -"+document.getElementById("reduction").innerHTML));
		printerApplet.addData1(separator);
	}
	printerApplet.addData1(" ");

	//Total
	printerApplet.addData2(spacePadding(document.getElementById("amountPayLabel").innerHTML, 8, "right")+spacePadding(document.getElementById("amountPay").innerHTML, 8, "left"));

	// VAT
	if (isPrintingVat) {
		printingVat(printerApplet);
	} else {
		printerApplet.addData1(" ");
	}
	
	//Pied de page
	printerApplet.addData1(spacePaddingCenter(document.getElementById("thank").innerHTML));
}



function printingBill()
{
//	alert("Debut Applet imprime ");
	var printerApplet = window.parent.document.printerApplet;

	printingHeader(printerApplet);
	
	// SPECIFIC
	{
		//Infos sur la facture du client
		if(document.getElementById("customerBillName"))
		{
			printerApplet.addData1(spacePadding(document.getElementById("customerBillName").innerHTML, MAX_PRINTING_COLUMN1, "left"));
		}
		if(document.getElementById("customerBillAddress"))
		{
			printerApplet.addData1(spacePadding(document.getElementById("customerBillAddress").innerHTML, MAX_PRINTING_COLUMN1, "left"));
		}
		if(document.getElementById("customerBillCity"))
		{
			printerApplet.addData1(spacePadding(document.getElementById("customerBillCity").innerHTML, MAX_PRINTING_COLUMN1, "left"));
		}
		if(document.getElementById("customerBillName") || document.getElementById("customerBillAddress") || document.getElementById("customerBillCity"))
		{
			printerApplet.addData1(" ");
		}
	}

	// SPECIFIC
	{
		//Infos Table
		printerApplet.addData1(document.getElementById("registrationDate").innerHTML);	
		printerApplet.addData2(document.getElementById("billNumber").innerHTML);
		printerApplet.addData1(" ");
	}

	// SPECIFIC
	{
		printerApplet.addData1(" ");		
		printerApplet.addData1(spacePadding("", 5, "left")+"  "+spacePadding(document.getElementById("tableCustomerNumber").innerHTML, 23, "right")+"  "+spacePadding("", 8, "left"));
		printerApplet.addData1(spacePadding("", 5, "left")+"  "+spacePadding(document.getElementById("billCustomerNumber").innerHTML, 23, "right")+"  "+spacePadding("", 8, "left"));
		printerApplet.addData1(" ");
	}

	printingFooter(printerApplet, true);
	
	// Send data to printer
	printerApplet.print();

//	alert("Fin Applet imprime");
}

function printingDelivery()
{
//	alert("Debut Applet imprime ");
	var printerApplet = window.parent.document.printerApplet;

	printingHeader(printerApplet);
	
	// SPECIFIC
	{
		//Infos sur la facture du client
		if(document.getElementById("customerDeliveryFullName"))
		{
			printerApplet.addData1(spacePadding(document.getElementById("customerDeliveryFullName").innerHTML, MAX_PRINTING_COLUMN1, "left"));
		}
		if(document.getElementById("customerDeliveryAddress1"))
		{
			printerApplet.addData1(spacePadding(document.getElementById("customerDeliveryAddress1").innerHTML, MAX_PRINTING_COLUMN1, "left"));
		}
		if(document.getElementById("customerDeliveryAddress2"))
		{
			printerApplet.addData1(spacePadding(document.getElementById("customerDeliveryAddress2").innerHTML, MAX_PRINTING_COLUMN1, "left"));
		}
		if(document.getElementById("customerDeliveryAddress3"))
		{
			printerApplet.addData1(spacePadding(document.getElementById("customerDeliveryAddress3").innerHTML, MAX_PRINTING_COLUMN1, "left"));
		}
		if(document.getElementById("customerDeliveryPhone"))
		{
			printerApplet.addData1(spacePadding(document.getElementById("customerDeliveryPhone").innerHTML, MAX_PRINTING_COLUMN1, "left"));
		}
		if(document.getElementById("customerDeliveryEmail"))
		{
			printerApplet.addData1(spacePadding(document.getElementById("customerDeliveryEmail").innerHTML, MAX_PRINTING_COLUMN1, "left"));
		}
		if(
				document.getElementById("customerDeliveryFullName") 
				|| document.getElementById("customerDeliveryAddress1") || document.getElementById("customerDeliveryAddress2") || document.getElementById("customerDeliveryAddress3") 
				|| document.getElementById("customerDeliveryPhone") || document.getElementById("customerDeliveryEmail")
		) 
		{
			printerApplet.addData1(" ");
		}
	}

	// SPECIFIC
	{
		//Infos Table
		printerApplet.addData1(document.getElementById("registrationDate").innerHTML);	
		printerApplet.addData2(document.getElementById("tableCustomerNumber").innerHTML);
		printerApplet.addData1(" ");
	}

	//Commandes
	// SPECIFIC
	{
		printingOrder(printerApplet);
	}

	printingFooter(printerApplet, false);

	// Send data to printer
	printerApplet.print();

//	alert("Fin Applet imprime");
}

function initPage()
{
	document.getElementById("idDivIFrame").style.height = screen.height;
	document.getElementById("idFocusAnchor").focus();
}

