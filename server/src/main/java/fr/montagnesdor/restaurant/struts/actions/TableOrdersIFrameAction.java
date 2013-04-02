package fr.montagnesdor.restaurant.struts.actions;


import fr.montagnesdor.restaurant.model.hibernate.DinnerTable;
import fr.montagnesdor.restaurant.model.hibernate.Room;
import fr.montagnesdor.restaurant.services.orders.OrdersManagerFactory;
import fr.montagnesdor.restaurant.struts.MontagnesDOrActionServlet;
import fr.montagnesdor.restaurant.struts.UserSession;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public final class TableOrdersIFrameAction extends Action
{
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        HttpSession session = request.getSession();
        UserSession userSession = (UserSession)session.getAttribute(MontagnesDOrActionServlet.USER_SESSION_PREFIX_KEY);

		Room room  = (Room)userSession.getRoom();
		//L'intérêt des instructions suivantes est de rafraîchir les lignes de commandes de la table courante au cas où l'utilisateur change de langue
		DinnerTable currentTable = room.getCurrentTable();
		DinnerTable currentTableTemp = null;
		if(currentTable!=null)
		    currentTableTemp = OrdersManagerFactory.getManager().getDinnerTable(room.getCurrentTable().getNumber());
		if(currentTableTemp==null)
			currentTableTemp = currentTable;
		room.setCurrentTable(currentTableTemp);
/*		
		if (currentTableTemp.getOrders() != null)
		{
			//S'assurer que les champs quantitiesSum, amountsSum et amountPay soient renseignés
			OrderLine orderLine = null;
			currentTableTemp.setAmountsSum(0);
			currentTableTemp.setQuantitiesSum(0);
			for (Iterator i = currentTableTemp.getOrders().iterator(); i.hasNext();)
			{
				orderLine = (OrderLine) i.next();
				currentTableTemp.setAmountsSum(orderLine.getAmount() + currentTableTemp.getAmountsSum());
				currentTableTemp.setQuantitiesSum(orderLine.getQuantity() + currentTableTemp.getQuantitiesSum());
			}
			currentTableTemp.setAmountPay(currentTableTemp.getAmountsSum() * (1 - currentTableTemp.getReductionRatio() / 100));
		}
*/		
//		request.setAttribute("dinnerTable", room.getCurrentTable());
		
		// Forward control to the specified success URI
        return (mapping.findForward("success"));
    }
    
}
