/*
 * Project				montagnesdor
 * File name		TestAction.java
 * Created on		5 sept. 2004
 * @author			Mathieu MA sous conrad
 * @version		1.0
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package fr.montagnesdor.restaurant.struts.actions;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import fr.montagnesdor.restaurant.services.receipts.ReceiptsManagerFactory;

public class DownloadMonthlyReceiptsListAction extends Action
{
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
        String sortListBy = "revenueDate";
        String sortMonotony = "asc";
        
        String monthSelected = request.getParameter("monthSelected");
        String yearSelected = request.getParameter("yearSelected");
        
        Date maxRevenueDate = ReceiptsManagerFactory.getManager().getMaxRevenueDate();
        Date revenueDate = null;
        if((monthSelected==null || yearSelected==null) && maxRevenueDate!=null)
        {
            revenueDate = maxRevenueDate;
        }    
        if(monthSelected!=null && yearSelected!=null)
        {
            try
            {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                revenueDate = sdf.parse("01/"+monthSelected+"/"+yearSelected);
            }
            catch(ParseException pe)
            {
            }
        }
        request.setAttribute("revenueDate", revenueDate);
        request.setAttribute("vatsList", ReceiptsManagerFactory.getManager().getVats());
        request.setAttribute("dayRevenuesListALL", ReceiptsManagerFactory.getManager().getDayRevenuesList(revenueDate, null, sortListBy, sortMonotony));
        request.setAttribute("vatRevenuesALL", ReceiptsManagerFactory.getManager().getVatMonthlyRevenues(revenueDate, null));
        request.setAttribute("dayRevenuesListTAKEAWAY", ReceiptsManagerFactory.getManager().getDayRevenuesList(revenueDate, new Boolean(true), sortListBy, sortMonotony));
        request.setAttribute("vatRevenuesTAKEAWAY", ReceiptsManagerFactory.getManager().getVatMonthlyRevenues(revenueDate, new Boolean(true)));
        request.setAttribute("dayRevenuesListINPLACE", ReceiptsManagerFactory.getManager().getDayRevenuesList(revenueDate, new Boolean(false), sortListBy, sortMonotony));
        request.setAttribute("vatRevenuesINPLACE", ReceiptsManagerFactory.getManager().getVatMonthlyRevenues(revenueDate, new Boolean(false)));

	    // Forward control to the specified success URI
		return (mapping.findForward("success"));
	}
    
}
