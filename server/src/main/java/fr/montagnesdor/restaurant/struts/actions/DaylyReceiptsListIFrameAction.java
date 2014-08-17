package fr.montagnesdor.restaurant.struts.actions;

import fr.montagnesdor.restaurant.model.hibernate.Cashing;
import fr.montagnesdor.restaurant.services.receipts.ReceiptsManagerFactory;
import fr.montagnesdor.restaurant.struts.MontagnesDOrActionServlet;
import fr.montagnesdor.restaurant.struts.UserSession;

import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;
import org.jopendocument.dom.spreadsheet.SpreadSheet;

import fr.montagnesdor.restaurant.services.receipts.ReceiptsManagerFactory;
import fr.montagnesdor.restaurant.struts.MontagnesDOrActionServlet;
import fr.montagnesdor.restaurant.struts.UserSession;

public final class DaylyReceiptsListIFrameAction extends Action
{
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        HttpSession session = request.getSession();
        UserSession userSession = (UserSession) session.getAttribute(MontagnesDOrActionServlet.USER_SESSION_PREFIX_KEY);

        String sortListBy = request.getParameter("sortListBy");
        if(sortListBy == null || sortListBy.length() < 1)
            sortListBy = "cashing.dinnerTable.number";
        String sortMonotony = request.getParameter("sortMonotony");
        if(sortMonotony == null || sortListBy.length() < 1)
            sortMonotony = "asc";
        
        String filterList = request.getParameter("filterList");
        
        List cashingDatesList = ReceiptsManagerFactory.getManager().getCashingDates();

        Date cashingDate = null;
        if(filterList==null && cashingDatesList!=null && cashingDatesList.size()>0)
            cashingDate = (Date)cashingDatesList.get(0); 
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
        if(cashingDatesList!=null && cashingDatesList.size()!=0 && !cashingDatesList.contains(cashingDate))
        {
            cashingDate = (Date)cashingDatesList.get(0);            
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
        List cashingList = ReceiptsManagerFactory.getManager().getCashingTables(cashingDate, sortListBy, sortMonotony);
        Boolean isCashingClosed = new Boolean(ReceiptsManagerFactory.getManager().isCashingClosed(cashingDate));
        Boolean isCashingPrinted = new Boolean(ReceiptsManagerFactory.getManager().isCashingPrinted(cashingDate));

        request.setAttribute("isCashingClosed", isCashingClosed);
        request.setAttribute("isCashingPrinted", isCashingPrinted);
        request.setAttribute("cashingList", cashingList);
        request.setAttribute("cashingDatesList", cashingDatesList);
        request.setAttribute("cashingDate", cashingDate);
        request.setAttribute("sortListBy", sortListBy);
        request.setAttribute("sortMonotony", sortMonotony);
        request.setAttribute("vatRevenues", ReceiptsManagerFactory.getManager().getVatDaylyRevenues(cashingDate, isTakeaway));

        // Forward control to the specified success URI
        String forward = request.getParameter("forward");
        if (forward==null) {
        	forward = "success";
        }
        
        if ("successDownload".equals(forward)) {
        	createDownloadedFile(cashingList, request, response);
        	return null;
        }
        
        return (mapping.findForward(forward));
    }

	private void createDownloadedFile(List cashingList,  HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        UserSession userSession = (UserSession) session.getAttribute(MontagnesDOrActionServlet.USER_SESSION_PREFIX_KEY);
        Locale currentUserLocale = new Locale(userSession.getCurrentLanguage());
		// Create the data to save.
		String[] columns = new String[] { 
				super.getResources(request).getMessage(currentUserLocale, "daylyReceiptsIFrame.jsp.label.cashing.id"), 
				super.getResources(request).getMessage(currentUserLocale, "daylyReceiptsIFrame.jsp.label.table"), 
				super.getResources(request).getMessage(currentUserLocale, "daylyReceiptsIFrame.jsp.label.cash"),
				super.getResources(request).getMessage(currentUserLocale, "daylyReceiptsIFrame.jsp.label.ticket"), 
				super.getResources(request).getMessage(currentUserLocale, "daylyReceiptsIFrame.jsp.label.cheque"), 
				super.getResources(request).getMessage(currentUserLocale, "daylyReceiptsIFrame.jsp.label.card"),
				super.getResources(request).getMessage(currentUserLocale, "daylyReceiptsIFrame.jsp.label.online"),
				super.getResources(request).getMessage(currentUserLocale, "daylyReceiptsIFrame.jsp.label.unpaid"), 
				super.getResources(request).getMessage(currentUserLocale, "daylyReceiptsIFrame.jsp.label.amount")
			};
		final Object[][] data = new Object[cashingList.size()][columns.length];
		Cashing cashing = null;
		for (int i = 0; i < data.length; i++) {
			cashing = (Cashing) cashingList.get(i);
			data[i] = new Object[] { cashing.getId(),  cashing.getDinnerTable().getNumber(), cashing.getCash(), cashing.getTicket(), cashing.getCheque(), cashing.getCard(), cashing.getOnline(), cashing.getUnpaid(), cashing.getDinnerTable().getAmountPay() };
		}
		TableModel model = new DefaultTableModel(data, columns);

		// Save the data to an ODS file and open it.
		OutputStream baos = null;
		try {
			// Any cashing date in the cashingList
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			Date cashingDate = cashing.getDinnerTable().getCashingDate();
			String filename = super.getResources(request).getMessage("daylyReceiptsIFrame.jsp.file.backup")
			+ "_" + sdf.format(cashingDate);
			response.setHeader("Content-Disposition", "attachment; filename="+filename+".ods");
			baos = response.getOutputStream();
			SpreadSheet.createEmpty(model).getPackage().save(baos);
			baos.flush();
			
		} catch (IOException e) {
		} finally {
			try {
				baos.close();
			} catch (IOException e) {
			}
		}
	}
}