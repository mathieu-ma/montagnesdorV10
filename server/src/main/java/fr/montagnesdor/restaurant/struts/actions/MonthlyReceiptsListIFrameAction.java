package fr.montagnesdor.restaurant.struts.actions;

import fr.montagnesdor.restaurant.services.receipts.ReceiptsManagerFactory;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public final class MonthlyReceiptsListIFrameAction extends Action
{
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        String sortListBy = request.getParameter("sortListBy");
        if(sortListBy == null || sortListBy.length() < 1)
            sortListBy = "revenueDate";
        String sortMonotony = request.getParameter("sortMonotony");
        if(sortMonotony == null || sortListBy.length() < 1)
            sortMonotony = "asc";
        
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
        String optionSelected = request.getParameter("optionSelected");
        Boolean isTakeaway = null;
        if(optionSelected!=null)
        {
            if(optionSelected.toUpperCase().equals("TAKEAWAY"))
                isTakeaway = new Boolean(true);
            else if(optionSelected.toUpperCase().equals("INPLACE"))
                isTakeaway = new Boolean(false);
        }
        
        Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		ArrayList dateForMonthList = new ArrayList(12);
		for(int i=0; i<12; i++)
		{
			calendar.set(Calendar.MONTH, i);
			dateForMonthList.add(i, calendar.getTime());		
		}
		
        List dayRevenuesList = ReceiptsManagerFactory.getManager().getDayRevenuesList(revenueDate, isTakeaway, sortListBy, sortMonotony);
        request.setAttribute("dayRevenuesList", dayRevenuesList);
        request.setAttribute("dateForMonthList", dateForMonthList);
        request.setAttribute("dateForYearList", ReceiptsManagerFactory.getManager().getDayRevenuesYearList());
        request.setAttribute("revenueDate", revenueDate);
        request.setAttribute("sortListBy", sortListBy);
        request.setAttribute("sortMonotony", sortMonotony);
        request.setAttribute("vatRevenues", ReceiptsManagerFactory.getManager().getVatMonthlyRevenues(revenueDate, isTakeaway));

        // Forward control to the specified success URI
        return (mapping.findForward("success"));
    }

}