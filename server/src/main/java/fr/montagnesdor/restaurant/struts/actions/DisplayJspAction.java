package fr.montagnesdor.restaurant.struts.actions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import fr.montagnesdor.restaurant.model.hibernate.DinnerTable;
import fr.montagnesdor.restaurant.model.hibernate.Room;
import fr.montagnesdor.restaurant.services.orders.OrdersManagerFactory;
import fr.montagnesdor.restaurant.services.receipts.ReceiptsManagerFactory;
import fr.montagnesdor.restaurant.struts.MontagnesDOrActionServlet;
import fr.montagnesdor.restaurant.struts.UserSession;

public final class DisplayJspAction extends Action
{
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
			HttpSession session = request.getSession();
			UserSession userSession = (UserSession)session.getAttribute(MontagnesDOrActionServlet.USER_SESSION_PREFIX_KEY);
		
			Object attributesOut = null;
			
			String pageRequested = request.getParameter("pageRequested");
			if(pageRequested==null)
				pageRequested = "errorChangeUserPassword";
		
			if(pageRequested.startsWith("successChangeUserPassword"))
				pageRequested = pageRequested+request.getParameter("levelPassword");

			if(pageRequested.equals("successCustomerBillInfo") || pageRequested.equals("successPrintTable") || pageRequested.equals("successOrderTable"))
			{
				Room room  = (Room)userSession.getRoom();
				//L'intérêt des instructions suivantes est de rafraîchir les lignes de commandes de la table courante au cas où l'utilisateur change de langue
				DinnerTable currentTable = room.getCurrentTable();
				DinnerTable currentTableTemp = null;
				if(currentTable!=null)
				        currentTableTemp = OrdersManagerFactory.getManager().getDinnerTable(room.getCurrentTable().getNumber());
				if(currentTableTemp==null)
					currentTableTemp = currentTable;
				if(currentTableTemp!=null)
				{    
				    if(currentTableTemp.getOrders()!=null && currentTableTemp.getOrders().size()!=0)
				        OrdersManagerFactory.getManager().saveOrUpdateVatTable(currentTableTemp);
				    room.setCurrentTable(OrdersManagerFactory.getManager().getDinnerTable(currentTableTemp.getNumber()));
				}
			}

			if(pageRequested.equals("successPrintTableByCashing"))
			{
			    attributesOut = OrdersManagerFactory.getManager().getDinnerTableById(new Long(request.getParameter("tableId")));
			}

			if(pageRequested.equals("successCashTable"))
			{
				String tableId = request.getParameter("tableId");
				Long tableId_L = null;
				try
				{
					tableId_L = Long.valueOf(tableId);
				}
				catch(NumberFormatException nfe)
				{
				}
				if(tableId_L==null)
				{
					try
					{
						tableId_L = userSession.getRoom().getCurrentTable().getId();
					}
					catch(Exception e)
					{
					}
				}	
				if(tableId_L==null)
					attributesOut = null;
				else					
					attributesOut = OrdersManagerFactory.getManager().getDayRevenuesByTableId(tableId_L);					
			}

			if(pageRequested.equals("successModifyCashTable"))
			{
				String cashingId = request.getParameter("cashingId");
				Long cashingId_L = null;
				try
				{
				    cashingId_L = Long.valueOf(cashingId);
				}
				catch(NumberFormatException nfe)
				{
				}
				if(cashingId_L==null)
					attributesOut = null;
				else					
					attributesOut = ReceiptsManagerFactory.getManager().getCashing(cashingId_L);					
			}
			
			if(pageRequested.equals("successChangeEntryDate"))
			{
				Calendar calendar = Calendar.getInstance();
				calendar.set(Calendar.DAY_OF_MONTH, 1);
				ArrayList dateForMonthList = new ArrayList(12);
				for(int i=0; i<12; i++)
				{
					calendar.set(Calendar.MONTH, i);
					dateForMonthList.add(i, calendar.getTime());		
				}
				attributesOut = dateForMonthList;
			}	


			//Attribut générique permettant d'être utilisé dans les différents jsp de redirection				
			request.setAttribute("attributesOut", attributesOut);
						
			// Forward control to the specified success URI
			return mapping.findForward(pageRequested);
	}
}
