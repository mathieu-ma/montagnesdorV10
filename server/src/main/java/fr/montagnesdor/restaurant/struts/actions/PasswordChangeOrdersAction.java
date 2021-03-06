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
import fr.montagnesdor.restaurant.struts.forms.PasswordChangeOrdersForm;

/**
 * @author administrateur
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class PasswordChangeOrdersAction extends Action
{
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		HttpSession session = request.getSession();
		UserSession userSession = (UserSession) session.getAttribute(MontagnesDOrActionServlet.USER_SESSION_PREFIX_KEY);

		//Validate the request parameters specified by the user
		ActionErrors errors = new ActionErrors();

		PasswordChangeOrdersForm passwordChangeOrdersForm = (PasswordChangeOrdersForm) form;
		String password = passwordChangeOrdersForm.getPassword();
		
		DinnerTable currentTable = userSession.getRoom().getCurrentTable();

		if(userSession.getLevelPass1()!=null)
		{
			if(!password.equals(userSession.getUser().getUserAuthentication().getLevelPass1()))
			{
				errors.add("error.authentication.failed", new ActionError("error.authentication.failed"));
			}
			else
			{
				String actionPasswordChangeOrders = request.getParameter("actionPasswordChangeOrders");
				if(actionPasswordChangeOrders!=null)
				{
					if(actionPasswordChangeOrders.equals("resetPrintDate"))
					{
						currentTable.setPrintDate(null);
						OrdersManagerFactory.getManager().saveOrUpdateDinnerTable(currentTable);
					}
					if(actionPasswordChangeOrders.equals("allowModifyOrders"))
					{
						currentTable.setAllowModifyOrdersPrinting(true);
					}
				}
			}
		}
		else
			errors.add("error.authentication.denied", new ActionError("error.authentication.denied"));
		
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
