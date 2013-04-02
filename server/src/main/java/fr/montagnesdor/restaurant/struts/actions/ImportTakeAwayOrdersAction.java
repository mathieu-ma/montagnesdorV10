package fr.montagnesdor.restaurant.struts.actions;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.springframework.web.client.RestTemplate;

import fr.montagnesdor.restaurant.model.hibernate.DinnerTable;
import fr.montagnesdor.restaurant.model.hibernate.OrderLine;
import fr.montagnesdor.restaurant.model.hibernate.ProductLanguage;
import fr.montagnesdor.restaurant.services.orders.OrdersManagerFactory;
import fr.montagnesdor.restaurant.struts.MontagnesDOrActionServlet;
import fr.montagnesdor.restaurant.struts.UserSession;
import fr.montagnesdor.util.log.MyLog;
import fr.montagnesdor.util.log.MyLogFactoryImpl;

public final class ImportTakeAwayOrdersAction extends DispatchAction
{
	private static MyLog logger = MyLogFactoryImpl.getInstance().getLogger(ImportTakeAwayOrdersAction.class.getName());
	
    private static final Float MIN_AMOUNT_SUMS_TAKEAWAY = 15f;
    private static final Float RATIO_REDUCTION_TAKEAWAY = 10f;
	
	/**
	 * The base URL of the ArticleService web service - should be configurable
	 */
	private final static String takeAwayOrdersServiceUrl = "http://nsltdpn0021:9092/landed/i18n/test";
	
	private static RestTemplate restTemplate = new RestTemplate();
	
	public ActionForward processOrders(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        UserSession userSession = (UserSession)session.getAttribute(MontagnesDOrActionServlet.USER_SESSION_PREFIX_KEY);

		DinnerTable[] tables = restTemplate.getForObject(takeAwayOrdersServiceUrl, DinnerTable[].class);

		logger.debug("In processOrders, Liste of tables to be imported:");
		for (int i = 0; i < tables.length; i++) {
			DinnerTable processingTable = tables[i];
			DinnerTable tableToBeSaved = OrdersManagerFactory.getManager().getDinnerTable(processingTable.getNumber());
			if(tableToBeSaved != null) {
				throw new IllegalArgumentException("The dinner table already exist " + processingTable.getNumber());
			} else {
				tableToBeSaved = new DinnerTable();
				tableToBeSaved.setNumber(processingTable.getNumber());
			}
			tableToBeSaved.setTakeaway(true);
			tableToBeSaved.setRegistrationDate(processingTable.getRegistrationDate());
			tableToBeSaved.setUser(userSession.getUser());
			OrdersManagerFactory.getManager().saveOrUpdateDinnerTable(tableToBeSaved);
			
			Float amountForReductionRation = 0f;
            Set<OrderLine> ordersToBeSaved = processingTable.getOrders();
            for (OrderLine orderLineToBeSaved : ordersToBeSaved) {
                Float quantity = orderLineToBeSaved.getQuantity();
                String code = orderLineToBeSaved.getProduct().getId();

                // Get label
                ProductLanguage productLanguage = OrdersManagerFactory.getManager().getProduct(code, userSession.getCurrentLanguage());
                if (productLanguage == null) {
                	throw new IllegalArgumentException("Product label does NOT exist " + code);
                }
                String label = productLanguage.getLabel();
                Float price = productLanguage.getId().getProduct().getPrice();
                Float amount = quantity * price;
                amountForReductionRation += amount;
                
    			OrderLine orderLine = OrdersManagerFactory.getManager().saveOrUpdateOrderLine(tableToBeSaved, null, quantity.toString(), code, label, price.toString(), amount.toString());
    			logger.debug("OrderLine saved " + orderLine.toString());
			}
            if (amountForReductionRation>MIN_AMOUNT_SUMS_TAKEAWAY) {
            	tableToBeSaved.setReductionRatio(RATIO_REDUCTION_TAKEAWAY);
            	OrdersManagerFactory.getManager().saveOrUpdateDinnerTable(tableToBeSaved);
            }
			
			logger.debug("Dinner Table saved " + tableToBeSaved.toString());
		}

		return (mapping.findForward("success"));
	}
}
