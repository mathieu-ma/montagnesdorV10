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
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import fr.montagnesdor.restaurant.model.hibernate.DinnerTable;
import fr.montagnesdor.restaurant.services.orders.OrdersManagerFactory;
import fr.montagnesdor.restaurant.struts.MontagnesDOrActionServlet;
import fr.montagnesdor.restaurant.struts.UserSession;

/**
 * @author administrateur
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class PrintTableAction extends Action
{

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		HttpSession session = request.getSession();
		UserSession userSession = (UserSession) session.getAttribute(MontagnesDOrActionServlet.USER_SESSION_PREFIX_KEY);

		String tableId = request.getParameter("tableId");
		Long tableId_L = Long.valueOf(tableId);

		DinnerTable dinnerTable = OrdersManagerFactory.getManager().getDinnerTableById(tableId_L);
		String newDate = request.getParameter("date");
		String patternDate = request.getParameter("patternDate");
		Date date = null;
		if(newDate!=null)
		{
			if(patternDate==null)
				patternDate = "dd/MM/yyyy-HH:mm:ss";
			SimpleDateFormat patternDateSDF = new SimpleDateFormat(patternDate);
			try
			{
				date = patternDateSDF.parse(newDate);
			}
			catch(ParseException pe)
			{
			}
		}
                    	
		dinnerTable.setPrintDate(date);
		OrdersManagerFactory.getManager().saveOrUpdateDinnerTable(dinnerTable);

		String pageRequested = request.getParameter("pageRequested");
		
		if(pageRequested!=null && pageRequested.equals("successTablesListIFrame"))
			userSession.getRoom().setCurrentTable(null);

		// Forward control to the specified success URI
		return (mapping.findForward(pageRequested));
	}
}
