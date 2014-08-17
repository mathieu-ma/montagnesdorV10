package fr.montagnesdor.restaurant.struts.actions;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import fr.montagnesdor.restaurant.model.hibernate.Cashing;
import fr.montagnesdor.restaurant.model.hibernate.DinnerTable;
import fr.montagnesdor.restaurant.services.orders.OrdersManagerFactory;
import fr.montagnesdor.restaurant.services.receipts.ReceiptsManagerFactory;
import fr.montagnesdor.restaurant.struts.MontagnesDOrActionServlet;
import fr.montagnesdor.restaurant.struts.UserSession;
import fr.montagnesdor.restaurant.struts.forms.CashTableForm;
import fr.montagnesdor.util.FormatDecimal;

/**
 * @author administrateur
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class CashTableAction extends Action
{

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		HttpSession session = request.getSession();
		UserSession userSession = (UserSession) session.getAttribute(MontagnesDOrActionServlet.USER_SESSION_PREFIX_KEY);

		//Validate the request parameters specified by the user
		ActionErrors errors = new ActionErrors();

		CashTableForm cashTableForm = (CashTableForm) form;
		
		DinnerTable dinnerTable = OrdersManagerFactory.getManager().getDinnerTableById(cashTableForm.getTableId());
		
		Cashing cashing = new Cashing();
		String cashingId = request.getParameter("cashingId");
		if(cashingId!=null)
		{
			Long cashingId_L = null;
			try
			{
			    cashingId_L = Long.valueOf(cashingId);
			}
			catch(NumberFormatException nfe)
			{
			}
			if(cashingId_L!=null)
			    cashing = ReceiptsManagerFactory.getManager().getCashing(cashingId_L);
		}
		
		cashing.setDinnerTable(dinnerTable);
		cashing.setCash(cashTableForm.getCash());
		cashing.setTicket(cashTableForm.getTicket());
		cashing.setCheque(cashTableForm.getCheque());
		cashing.setCard(cashTableForm.getCard());
		cashing.setOnline(cashTableForm.getOnline());
		cashing.setUnpaid(cashTableForm.getUnpaid());
		
		float sum = cashing.getCash()+cashing.getTicket()+cashing.getCheque()+cashing.getCard()+cashing.getOnline()+cashing.getUnpaid();
		if(FormatDecimal.compare(sum, dinnerTable.getAmountPay()))
		{
        	String newDate = request.getParameter("newDate");
			String patternDate = request.getParameter("patternDate");
        	Date date = null;
			if(patternDate==null)
			    patternDate = "dd/MM/yyyy";
        	if(newDate==null)
        	    date = new Date();
        	if(!newDate.equals("null"))
        	{
				SimpleDateFormat patternDateSDF = new SimpleDateFormat(patternDate);
				try
				{
					date = patternDateSDF.parse(newDate);
				}
				catch(ParseException pe)
				{
				}
        	}
        	//Le champ cashingDate a la même valeur que le champ registrationDate mais sans les heures
			//cashing.getDinnerTable().setCashingDate(date);
        	cashing.getDinnerTable().setCashingDate(cashing.getDinnerTable().getRegistrationDate());
			OrdersManagerFactory.getManager().saveOrUpdateCashing(cashing);
			if(cashingId==null)
			    OrdersManagerFactory.getManager().saveOrUpdateVatTable(cashing.getDinnerTable());
			//S'assurer que la table courante est remise à zéro
			userSession.getRoom().setCurrentTable(null);
		}
		else if(sum>dinnerTable.getAmountPay())
		{
			errors.add("cashTable.jsp.alert.amount.returned", new ActionError("cashTable.jsp.alert.amount.returned"));
		}
		else
		{
			errors.add("cashTable.jsp.alert.amount.paid", new ActionError("cashTable.jsp.alert.amount.paid"));
		}
		
		// Report any errors we have discovered back to the original form
		if (!errors.isEmpty())
		{
			saveErrors(request, errors);
			return (new ActionForward(mapping.getInput()));
		}

		// Forward control to the specified success URI
		return (mapping.findForward(request.getParameter("pageRequested")));
	}
}
