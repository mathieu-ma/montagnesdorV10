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
import fr.montagnesdor.restaurant.struts.forms.MergeTableForm;

/**
 * @author administrateur
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class MergeTableAction extends Action
{

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		HttpSession session = request.getSession();
		UserSession userSession = (UserSession) session.getAttribute(MontagnesDOrActionServlet.USER_SESSION_PREFIX_KEY);

		//Validate the request parameters specified by the user
		ActionErrors errors = new ActionErrors();

		MergeTableForm mergeTableForm = (MergeTableForm) form;
		String tableNumber = mergeTableForm.getTableNumber();
		DinnerTable currentTable = userSession.getRoom().getCurrentTable();

		if (currentTable.getNumber() == null)
		{
			return (mapping.findForward("error"));
		}
		else
		{
			if (currentTable.getNumber().equals(tableNumber))
			{
				errors.add("mergeTable.jsp.error.same.table", new ActionError("mergeTable.jsp.error.same.table"));
			}
			else
			{
				DinnerTable mergedTable = OrdersManagerFactory.getManager().getDinnerTable(tableNumber);
				if(mergedTable==null)
				{
					errors.add("mergeTable.jsp.error.table.not.found", new ActionError("mergeTable.jsp.error.table.not.found"));
				}
				else
				{
					mergedTable = OrdersManagerFactory.getManager().mergeTable(currentTable, mergedTable);
					if(mergedTable!=null)
						userSession.getRoom().setCurrentTable(mergedTable);
					else
						errors.add("mergeTable.jsp.error.database", new ActionError("mergeTable.jsp.error.database"));
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
