package fr.montagnesdor.restaurant.struts.actions;

import java.io.IOException;

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

import fr.montagnesdor.restaurant.model.hibernate.DinnerTable;
import fr.montagnesdor.restaurant.services.orders.OrdersManagerFactory;
import fr.montagnesdor.restaurant.struts.MontagnesDOrActionServlet;
import fr.montagnesdor.restaurant.struts.UserSession;
import fr.montagnesdor.restaurant.struts.forms.ChangeTableForm;

/**
 * @author administrateur
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class ChangeTableAction extends Action
{

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		HttpSession session = request.getSession();
		UserSession userSession = (UserSession) session.getAttribute(MontagnesDOrActionServlet.USER_SESSION_PREFIX_KEY);

		//Validate the request parameters specified by the user
		ActionErrors errors = new ActionErrors();

		ChangeTableForm changeTableForm = (ChangeTableForm) form;
		String tableNumber = changeTableForm.getTableNumber();
		DinnerTable currentTable = userSession.getRoom().getCurrentTable();

		if (currentTable.getNumber() == null)
		{
			return (mapping.findForward("error"));
		}
		else
		{
			if (currentTable.getNumber().equals(tableNumber))
			{
				errors.add("changeTable.jsp.error.same.table", new ActionError("changeTable.jsp.error.same.table"));
			}
			else
			{
				DinnerTable dinnerTable = OrdersManagerFactory.getManager().getDinnerTable(tableNumber);
				if(dinnerTable!=null)
				{
					errors.add("changeTable.jsp.error.table.exist", new ActionError("changeTable.jsp.error.table.exist"));
				}
				else
				{
					currentTable.setNumber(tableNumber);
					OrdersManagerFactory.getManager().saveOrUpdateDinnerTable(currentTable);
				}	
			}
		}
		
		// Report any errors we have discovered back to the original form
		if (!errors.isEmpty())
		{
			saveErrors(request, errors);
			return (new ActionForward(mapping.getInput()));
		}

		// Forward control to the specified success URI
		return (mapping.findForward("success"));
	}
}
