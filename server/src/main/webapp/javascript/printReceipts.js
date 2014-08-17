var MAX_PRINTING_COLUMN1 = 40;	
var MAX_PRINTING_COLUMN2 = 16;	

function spacePadding(str, maxLen, direction)
{
	strLength = str.length;
	if(strLength>=maxLen)
	{
		return str.substr(0, maxLen);
	}

	spaces = "";
    nbrPaddingSpace = maxLen-strLength;
	for(i=0; i<nbrPaddingSpace; i++)
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

function processPrinting(typeTable)
{
	//Imprimer la table avec l'applet PrintingApplet
	if(typeTable=='ALL')
	{
		printing('TAKEAWAY');
		printing('INPLACE');
	}
	else
		printing(typeTable);
	document.modifyTableForm.submit();
}

function getCashing(typeTable, indexCashing)
{
//alert("typeTable : "+typeTable+" indexCashing : "+indexCashing);
	var tableNumber = eval("document.printForm.cashingDinnerTableNumber"+typeTable+indexCashing).value;
	var cash = eval("document.printForm.cashingCash"+typeTable+indexCashing).value;
	var ticket = eval("document.printForm.cashingTicket"+typeTable+indexCashing).value;
	var cheque = eval("document.printForm.cashingCheque"+typeTable+indexCashing).value;
	var card = eval("document.printForm.cashingCard"+typeTable+indexCashing).value;
	var online = eval("document.printForm.cashingOnline"+typeTable+indexCashing).value;
	var result = spacePadding(tableNumber, 3, "right")+" !"+spacePadding(cash, 7, "left")+" !"+spacePadding(ticket, 6, "left")+" !"+spacePadding(cheque, 7, "left")+" !"+spacePadding(card, 7, "left")+" !"+spacePadding(online, 7, "left");
	return result;
}

function printing(typeTable)
{
//	alert("Debut Applet imprime "+typeTable);

	var separator = ""; for(i=0; i<MAX_PRINTING_COLUMN1; i++) separator += '-';	

	var printerApplet = window.parent.document.printerApplet;

	//Vider le buffer de l'applet 
	printerApplet.resetDataBuffer();

	//Entete
	printerApplet.addData2(document.printForm.restaurantName.value);
	printerApplet.addData1(document.printForm.restaurantAddressRoad.value);
	printerApplet.addData1(document.printForm.restaurantAddressZipcode.value+" "+document.printForm.restaurantAddressCity.value);
	printerApplet.addData1(document.printForm.restaurantPhone.value);
	printerApplet.addData1(document.printForm.restaurantOthersInfo1.value);
	printerApplet.addData1(document.printForm.restaurantOthersInfo2.value);
	//Représente un saut de ligne pour l'imprimante
	printerApplet.addData1(" ");

	//Infos Recettes Journalières
	printerApplet.addData1("  ************************************* ");
	if(typeTable=="TAKEAWAY")
		printerApplet.addData1("  *  "+document.printForm.daylyReceiptsPrintLabelTakeway.value+"  * ");
	else
		printerApplet.addData1("  *  "+document.printForm.daylyReceiptsPrintLabelInplace.value+"  * ");	
	printerApplet.addData1("  ************************************* ");
	printerApplet.addData1(" ");

	//Recettes
	printerApplet.addData1(separator);
	printerApplet.addData1(document.printForm.daylyReceiptsPrintLabelTable.value+"!    "+document.printForm.daylyReceiptsPrintLabelCash.value+"!  "+document.printForm.daylyReceiptsPrintLabelTicket.value+"!   "+document.printForm.daylyReceiptsPrintLabelCheque.value+"!   "+document.printForm.daylyReceiptsPrintLabelCard.value+"!"+"!   "+document.printForm.daylyReceiptsPrintLabelOnline.value+"!");
	printerApplet.addData1(separator);

	var cashingNumber = eval("document.printForm.numberTables"+typeTable).value;
//alert("cashingNumber "+cashingNumber)	
	for(var i=1; i<=cashingNumber; i++)
	{
		printerApplet.addData1(getCashing(typeTable, i));
	}
	printerApplet.addData1(" ");
	printerApplet.addData1(separator);
	printerApplet.addData1(separator);
	printerApplet.addData1(" ");

	//Somme recettes
	var sumCash = eval("document.printForm.sumCashes"+typeTable).value;
	var sumTicket = eval("document.printForm.sumTickets"+typeTable).value;
	var sumCheque = eval("document.printForm.sumCheques"+typeTable).value;
	var sumCard = eval("document.printForm.sumCards"+typeTable).value;
	var sumOnline = eval("document.printForm.sumOnlines"+typeTable).value;
	var sumUnpaid = eval("document.printForm.sumUnpaid"+typeTable).value;
	printerApplet.addData1("*** "+document.printForm.daylyReceiptsPrintLabelSumCash.value+" ............... : "+spacePadding(sumCash, 10, "left"));
	printerApplet.addData1("*** "+document.printForm.daylyReceiptsPrintLabelSumTicket.value+" ......... : "+spacePadding(sumTicket, 10, "left"));
	printerApplet.addData1("*** "+document.printForm.daylyReceiptsPrintLabelSumCheque.value+" ..... : "+spacePadding(sumCheque, 10, "left"));
	printerApplet.addData1("*** "+document.printForm.daylyReceiptsPrintLabelSumCard.value+" ...... : "+spacePadding(sumCard, 10, "left"));
	printerApplet.addData1("*** "+document.printForm.daylyReceiptsPrintLabelSumOnline.value+" ............... : "+spacePadding(sumOnline, 10, "left"));
	printerApplet.addData1("*** "+document.printForm.daylyReceiptsPrintLabelSumUnpaid.value+" ............... : "+spacePadding(sumUnpaid, 10, "left"));
	printerApplet.addData1(separator);
	printerApplet.addData1(" ");

	//Total recette
	var sumCashingByTable = parseFloat(eval("document.printForm.sumAmounts"+typeTable).value);
	var sumDifference = eval(parseFloat(sumCashingByTable)-(parseFloat(sumCash)+parseFloat(sumTicket)+parseFloat(sumCheque)+parseFloat(sumCard)+parseFloat(sumOnline)+parseFloat(sumUnpaid)));
	printerApplet.addData1("*** "+document.printForm.daylyReceiptsPrintLabelSumCashingByTable.value+" ........ : "+spacePadding(eval("document.printForm.sumAmounts"+typeTable).value, 10, "left"));
	var vatsNumber = Number(document.printForm.daylyReceiptsPrintNumberVats.value);
	for(var i=0; i<vatsNumber; i++)
	{
		length = ("*** "+eval("document.printForm.daylyReceiptsPrintLabelSumCashingByVat"+typeTable+i).value).length;
		printerApplet.addData1("*** "+eval("document.printForm.daylyReceiptsPrintLabelSumCashingByVat"+typeTable+i).value+spacePadding(eval("document.printForm.daylyReceiptsPrintVatAmount"+typeTable+i).value, MAX_PRINTING_COLUMN1-length, "left"));
		sumWithoutVat += parseFloat(eval("document.printForm.daylyReceiptsPrintVatAmount"+typeTable+i).value);
	}
	printerApplet.addData1("*** "+document.printForm.daylyReceiptsPrintLabelDifference.value+" .. : "+spacePadding(formatNumber(sumDifference,2), 10, "left"));
	printerApplet.addData1(separator);
	printerApplet.addData1(separator);

	sumCashingByTable = sumCashingByTable-sumDifference;
	printerApplet.addData1("*** "+document.printForm.daylyReceiptsPrintLabelSum.value+" ................. : "+spacePadding(formatNumber(sumCashingByTable,2), 10, "left"));
	var length = 0;
	var sumWithoutVat = 0;
	for(var i=0; i<vatsNumber; i++)
	{
		length = ("*** "+eval("document.printForm.daylyReceiptsPrintLabelVat"+typeTable+i).value).length;
		printerApplet.addData1("*** "+eval("document.printForm.daylyReceiptsPrintLabelVat"+typeTable+i).value+spacePadding(eval("document.printForm.daylyReceiptsPrintVatValue"+typeTable+i).value, MAX_PRINTING_COLUMN1-length, "left"));
		sumWithoutVat += parseFloat(eval("document.printForm.daylyReceiptsPrintVatValue"+typeTable+i).value);
	}
	sumWithoutVat =	sumCashingByTable-sumWithoutVat;
	length = ("*** "+document.printForm.daylyReceiptsPrintLabelWithoutVat.value+" ....... : ").length;
	printerApplet.addData1("*** "+document.printForm.daylyReceiptsPrintLabelWithoutVat.value+" ....... : "+spacePadding(formatNumber(sumWithoutVat,2), MAX_PRINTING_COLUMN1-length, "left"));			
	printerApplet.addData1(" ");
	printerApplet.addData1(" ");
	
	printerApplet.addData1("*** "+document.printForm.daylyReceiptsPrintLabelTotalSum.value+" ......... : "+spacePadding(document.printForm.totalSumAmounts.value, 10, "left"));
	printerApplet.addData1(" ");
	printerApplet.addData1("*** "+document.printForm.daylyReceiptsPrintLabelSumTable.value+" ...... : "+spacePadding(eval("document.printForm.numberTables"+typeTable).value, 10, "left"));

	printerApplet.print();
	
//	alert("Fin Applet imprime");
}

