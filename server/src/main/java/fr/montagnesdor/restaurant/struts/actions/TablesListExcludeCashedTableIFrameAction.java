package fr.montagnesdor.restaurant.struts.actions;

import fr.montagnesdor.restaurant.services.orders.OrdersManagerFactory;
import fr.montagnesdor.restaurant.struts.MontagnesDOrActionServlet;
import fr.montagnesdor.restaurant.struts.UserSession;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public final class TablesListExcludeCashedTableIFrameAction extends Action
{
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        HttpSession session = request.getSession();
        if(session != null)
        {
            UserSession userSession = (UserSession)session.getAttribute(MontagnesDOrActionServlet.USER_SESSION_PREFIX_KEY);
            if(userSession != null)
            {
		    	String filterList = request.getParameter("filterList");
		    	if(filterList==null)
					filterList = userSession.getCurrentTableListFilter();
				String sortListBy = request.getParameter("sortListBy");
				if(sortListBy==null)
					sortListBy = "number";
				String sortMonotony = request.getParameter("sortMonotony");
				if(sortMonotony==null)
					sortMonotony = "asc";
				
				int filterList_i = 0;
				try
				{	
					filterList_i = Integer.parseInt(filterList);
				}
				catch(NumberFormatException nfe)
				{
				}
				
				List list = OrdersManagerFactory.getManager().getDinnerTablesExcludeCashedTable(filterList_i, sortListBy, sortMonotony);
				request.setAttribute("tablesList", list);
				
				userSession.setCurrentTableListFilter(filterList);
				request.setAttribute("filterList", filterList);
				request.setAttribute("sortListBy", sortListBy);
				request.setAttribute("sortMonotony", sortMonotony);
				
				// Forward control to the specified success URI
		        return (mapping.findForward("success"));
            }
        }
        
        return (mapping.findForward("sessionInvalide"));
    }
    
}
