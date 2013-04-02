<%@ page language="java" import="java.util.*" %>
<HTML>
<HEAD>
<TITLE>Num�o de Table et Nombre de Personnes</TITLE>
<SCRIPT type="text/javascript"> 
<!--
var blink = "true";
function time() 
{
	var dateTimeApplet = document.getElementById("idDateTimeApplet");	
	var tableApplet = document.getElementById("idTableApplet");	

	var delay = 1000;
	
	try
	{
		window.status = dateTimeApplet.getDateTime();
	}
	catch(err)
	{
		try
		{
			tableApplet = document.getElementById("idTableApplet");
		}
		catch(err)
		{
		}
	}
	window.setTimeout("time()",delay);
}
// -->

function changerDateEntreeOccupation()
{
//	var date = prompt("Entrez une date : ", dateTimeApplet.getDateCourte());
	var url = "<%=request.getContextPath()%>/PasswordChangeDate.do";
	var attributFenetre = 'location=no,toolbar=no,status=yes,directories=no,scrollbars=no,width=450,height=200';
	var pop_up = window.open(url, "pop_up", attributFenetre);
	pop_up.focus();
}

function isEntier(cellule)
{
	var derniereEntree = cellule.value.charAt(cellule.value.length-1);
	var auxString = cellule.value.substr(0,cellule.value.length-1);
	var expReg =/[0-9]/;


	if(!expReg.test(derniereEntree))	
	{
		cellule.value = auxString;	
	}
}

function controlerApostrophe(cellule)
{
	var flag = true;

	var derniereEntree = cellule.value.charAt(cellule.value.length-1);
	var auxString = cellule.value.substr(0,cellule.value.length-1);
	var expReg =/[']/;

	if(expReg.test(derniereEntree))	
	{
		cellule.value = auxString;	
	}

	return flag;
}

function lireEntree(event, cellule)
{
	cellule.value = cellule.value.toUpperCase();	
	

	if(cellule.name=="nombrePersonnes")
	{
		if(!cellule.isValueEmpty && event.keyCode!=13)
		{
			cellule.value = cellule.value.charAt(0);
			cellule.isValueEmpty = true;
		}
		isEntier(cellule);
	}
	else
	{
		controlerApostrophe(cellule);
	}

	switch(event.keyCode)
	{     	  
		//13 �uivaut �la touche ENTRER
		case 13 :
			lireCellule(cellule);
		return; 
	
		//27 �uivaut �la touche Echap
		case 27 :
			//window.top.location.replace("<%=request.getContextPath()%>/Logon.do");
			actualiserIndexHTML(cellule);
		return;
	};
}

function actualiserIndexHTML(cellule)
{
	if(cellule.name == "nombrePersonnes")
	{
		document.getElementById("idTDNombrePersonnes").innerHTML = "";
		document.getElementById("idNombrePersonnesLabel").innerHTML = "";
		document.getElementById("idTDNumeroTable").innerHTML = "<input type='text' id='idNumeroTable' style='text-align: left; padding-left: 1em' name='numeroTable' onkeyup='lireEntree(event, this)' maxlength=5 size=6>";
		document.getElementById("idNumeroTable").focus();
		document.getElementById("idNumeroTable").focus();
	}
	else
		cellule.value = "";
}

function lireCellule(cellule)
{
	switch(cellule.name)
	{       
		//numeroTable
		case "numeroTable" :    
			lireNumeroTable(cellule);
		break;         
		
		//nombrePersonnes
		case "nombrePersonnes" :    
			lireNombrePersonnes(cellule);
		break;         
	};

}

function lireNumeroTable(cellule)
{
	var tableApplet = document.getElementById("idTableApplet");

}

function lireNombrePersonnes(cellule)
{
	var tableApplet = document.getElementById("idTableApplet");
	if(cellule.value!="")
	{
		if(tableApplet.setNombrePersonnes(cellule.value))
			envoyerFormulaire(cellule);
		else
			window.top.document.location = "<%=request.getContextPath()%>/PointEntree.do";
	}
}

function envoyerFormulaire(cellule)
{
	if(cellule.name != "numeroTable")
	{
		var celluleTDNombrePersonnes = document.getElementById("idTDNombrePersonnes");
		celluleTDNombrePersonnes.innerHTML = document.formulaire.nombrePersonnes.value;
	}
	var tableApplet = document.getElementById("idTableApplet");
	document.formulaireHiddenFenetreSecondaireMilieu.numeroTable.value = tableApplet.getNumeroTable();
	if(document.formulaireHiddenFenetreSecondaireMilieu.numeroTable.value.charAt(0)=='E')
		tableApplet.setNombrePersonnes("0");
	document.formulaireHiddenFenetreSecondaireMilieu.submit();
	document.formulaireHiddenFenetrePrincipaleBasGauche.numeroTable.value = document.formulaireHiddenFenetreSecondaireMilieu.numeroTable.value;
	document.formulaireHiddenFenetrePrincipaleBasGauche.submit();
	
	setMenuPrincipalFocus();
}

function setMenuPrincipalFocus()
{
	var documentMenuPrincipal = window.top.fenetrePrincipaleHaut.document;

	documentMenuPrincipal.getElementById("IDTD1").bgColor="white";
	documentMenuPrincipal.getElementById("IDA1").style.color="darkblue";

	var oldIndex = documentMenuPrincipal.formulaire.oldIndex.value;

	if(oldIndex != 1)
	{
		documentMenuPrincipal.getElementById("IDTD"+oldIndex).bgColor="#660000";
		documentMenuPrincipal.getElementById("IDA"+oldIndex).style.color="white";
	}
	
	documentMenuPrincipal.formulaire.oldIndex.value = 1;
}

function initPage()
{
	time();
}

</SCRIPT>
<style type="text/css">
<!--
.policeGris {  font-family: "Times New Roman", Times, serif; font-size: 12px; font-weight: bolder; color: #CCCCCC; text-decoration: none}
-->
</style>
</HEAD>
<BODY onload='initPage()' style='background-color: #660000'>
InfosHaut.jsp
<APPLET id='idDateTimeApplet' ARCHIVE="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/jsp/communs/SDateTimeImpressionTableApplet.jar" CODE = "fr.montagnesdor.restaurant.applet.DateTimeApplet.class" WIDTH = "0" HEIGHT = "0" style='background-color: #660000'>
<PARAM NAME="patternDate" VALUE="EEEE dd MMMM yyyy">
<PARAM NAME="patternDateCourte" VALUE="dd/MM/yyyy">
<PARAM NAME="patternTime" VALUE="HH:mm:ss">
<PARAM NAME="patternDateTime" VALUE="EEEE dd MMMM yyyy �HH:mm:ss">
<PARAM NAME="debug" VALUE="false">
</APPLET>
<APPLET id='idTableApplet' ARCHIVE="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/jsp/communs/SDateTimeImpressionTableApplet.jar" CODE = "fr.montagnesdor.restaurant.applet.TableApplet.class" WIDTH = "0" HEIGHT = "0" style='background-color: #660000'>
<PARAM NAME = "URLServlet" VALUE="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/TableApplet">
<PARAM NAME = "patternDate" VALUE="dd/MM/yyyy/HH/mm/ss">
</APPLET>
</BODY>
</HTML>

