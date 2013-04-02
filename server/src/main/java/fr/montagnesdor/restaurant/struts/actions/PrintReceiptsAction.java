package fr.montagnesdor.restaurant.struts.actions;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import fr.montagnesdor.restaurant.services.receipts.ReceiptsManagerFactory;

/**
 * @author administrateur
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class PrintReceiptsAction extends Action
{

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
        String sortListBy = request.getParameter("sortListBy");
        if(sortListBy == null || sortListBy.length() < 1)
            sortListBy = "cashing.cash";
        String sortMonotony = request.getParameter("sortMonotony");
        if(sortMonotony == null || sortListBy.length() < 1)
            sortMonotony = "asc";
        String filterList = request.getParameter("filterList");
        Date cashingDate = null;
        if(filterList!=null)
        {
            try
            {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                cashingDate = sdf.parse(filterList);
            }
            catch(ParseException pe)
            {
            }
        }
        String optionSelected = request.getParameter("optionSelected");
        List cashingList = ReceiptsManagerFactory.getManager().getCashingTables(cashingDate, sortListBy, sortMonotony);
        Boolean isCashingPrinted = new Boolean(ReceiptsManagerFactory.getManager().isCashingPrinted(cashingDate));
        request.setAttribute("isCashingPrinted", isCashingPrinted);
        request.setAttribute("cashingList", cashingList);
        request.setAttribute("cashingDate", cashingDate);
        request.setAttribute("sortListBy", sortListBy);
        request.setAttribute("sortMonotony", sortMonotony);
        request.setAttribute("vatRevenuesAll", ReceiptsManagerFactory.getManager().getVatDaylyRevenues(cashingDate, null));
        request.setAttribute("vatRevenuesTakeaway", ReceiptsManagerFactory.getManager().getVatDaylyRevenues(cashingDate, new Boolean(true)));
        request.setAttribute("vatRevenuesInplace", ReceiptsManagerFactory.getManager().getVatDaylyRevenues(cashingDate, new Boolean(false)));
		
		if(optionSelected==null  || optionSelected.toUpperCase().equals("") || optionSelected.toUpperCase().equals("ALL"))
		{
		    if(!ReceiptsManagerFactory.getManager().isCashingPrinted(cashingDate))
		        ReceiptsManagerFactory.getManager().insertInitDayRevenue(cashingDate);
		}

		// Forward control to the specified success URI
		return (mapping.findForward("success"));
	}
}
