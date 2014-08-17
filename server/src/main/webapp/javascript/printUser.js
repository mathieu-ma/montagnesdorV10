function processPrinting()
{
	printing();
	document.usersListForm.submit();
}

function printing()
{
//	alert("Debut Applet imprime "+typeTable);

	var printerApplet = window.parent.document.printerApplet;

	//Vider le buffer de l'applet 
	printerApplet.resetDataBuffer();

	if(document.SaveOrUpdateUserForm.email.value != "")
		printerApplet.addData1(document.SaveOrUpdateUserForm.emailLabel.value + ":" + document.SaveOrUpdateUserForm.email.value);
	if(document.SaveOrUpdateUserForm.name.value != "" || document.SaveOrUpdateUserForm.forename1.value != "") {
		printerApplet.addData1(document.SaveOrUpdateUserForm.nameLabel.value + ":");
		if(document.SaveOrUpdateUserForm.name.value != "")
			printerApplet.addData1(document.SaveOrUpdateUserForm.name.value);
		if(document.SaveOrUpdateUserForm.forename1.value != "")
			printerApplet.addData1(document.SaveOrUpdateUserForm.forename1.value);
	}
	if(document.SaveOrUpdateUserForm.phoneNumber.value != "")
		printerApplet.addData1(document.SaveOrUpdateUserForm.phoneLabel.value + ":" + document.SaveOrUpdateUserForm.phoneNumber.value);
	if(document.SaveOrUpdateUserForm.address1.value != "" 
		|| document.SaveOrUpdateUserForm.address2.value != ""
		|| document.SaveOrUpdateUserForm.postcode.value != ""
		|| document.SaveOrUpdateUserForm.city.value != "") {
		printerApplet.addData1(document.SaveOrUpdateUserForm.addressLabel.value + ":");
		if(document.SaveOrUpdateUserForm.address1.value != "")
			printerApplet.addData1(document.SaveOrUpdateUserForm.address1.value);
		if(document.SaveOrUpdateUserForm.address2.value != "")
			printerApplet.addData1(document.SaveOrUpdateUserForm.address2.value);
		if(document.SaveOrUpdateUserForm.postcode.value != "")
			printerApplet.addData1(document.SaveOrUpdateUserForm.postcode.value);
		if(document.SaveOrUpdateUserForm.city.value != "")
			printerApplet.addData1(document.SaveOrUpdateUserForm.city.value);
	}
	if(document.SaveOrUpdateUserForm.digitalCode.value != "")
		printerApplet.addData1(document.SaveOrUpdateUserForm.digitalCodeLabel.value + ":" + document.SaveOrUpdateUserForm.digitalCode.value);
	if(document.SaveOrUpdateUserForm.digitalCode.value != "")
		printerApplet.addData1(document.SaveOrUpdateUserForm.staircaseLabel.value + ":" + document.SaveOrUpdateUserForm.staircase.value);
	if(document.SaveOrUpdateUserForm.stage.value != "")
		printerApplet.addData1(document.SaveOrUpdateUserForm.stageLabel.value + ":" + document.SaveOrUpdateUserForm.stage.value);
	if(document.SaveOrUpdateUserForm.building.value != "")
		printerApplet.addData1(document.SaveOrUpdateUserForm.buildingLabel.value + ":" + document.SaveOrUpdateUserForm.building.value);
	
	printerApplet.print();
	
//	alert("Fin Applet imprime");
}

