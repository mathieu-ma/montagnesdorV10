package fr.montagnesdor.restaurant.struts.actions;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
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
	//private final static String takeAwayOrdersServiceUrl = "http://nsltdpn0021:9092/landed/i18n/test";
	private final static String TAKE_AWAY_ORDERS_SERVICE_USER = "edouard";
	private final static String TAKE_AWAY_ORDERS_SERVICE_USER_PASSWORD = "kimsan123";

    private final static String TAKE_AWAY_ORDERS_SERVICE_HOST_SCHEME = HttpHost.DEFAULT_SCHEME_NAME;
	private final static String TAKE_AWAY_ORDERS_SERVICE_HOST_NAME = "dev1.stoz.fr";
	private final static int TAKE_AWAY_ORDERS_SERVICE_HOST_PORT = 80;
	private final static String TAKE_AWAY_ORDERS_SERVICE_URL_PREFIX = TAKE_AWAY_ORDERS_SERVICE_HOST_SCHEME + "://"
															+ TAKE_AWAY_ORDERS_SERVICE_HOST_NAME + ":"
															+ TAKE_AWAY_ORDERS_SERVICE_HOST_PORT;

	private final static String TAKE_AWAY_ORDERS_SERVICE_URL = TAKE_AWAY_ORDERS_SERVICE_URL_PREFIX + "/json_export_cmd.html";

	private final static String TAKE_AWAY_ORDERS_SERVICE_URL_CALLBACK_OK = TAKE_AWAY_ORDERS_SERVICE_URL_PREFIX + "/json_export_cmd.html?response=OK";

	private final static String TAKE_AWAY_ORDERS_SERVICE_URL_CALLBACK_NOT_OK = TAKE_AWAY_ORDERS_SERVICE_URL_PREFIX + "/json_export_cmd.html?response=NOK";

//	private final static String TAKE_AWAY_ORDERS_SERVICE_HOST_SCHEME = HttpHost.DEFAULT_SCHEME_NAME;
//	private final static String TAKE_AWAY_ORDERS_SERVICE_HOST_NAME = "localhost";
//	private final static int TAKE_AWAY_ORDERS_SERVICE_HOST_PORT = 8787;
//	private final static String TAKE_AWAY_ORDERS_SERVICE_URL = TAKE_AWAY_ORDERS_SERVICE_HOST_SCHEME + "://"
//															+ TAKE_AWAY_ORDERS_SERVICE_HOST_NAME + ":"
//															+ TAKE_AWAY_ORDERS_SERVICE_HOST_PORT + "/web-server/secure/json";
//
	//private final static String takeAwayOrdersServiceUrl = "http://localhost:8787/web-server/secure/json";

    private final static String TAKE_AWAY_ORDERS_PREFIX_TABLE_NUMBER = "VAD";

	private static RestTemplate restTemplate = null;
	
	
	public static class ContextAwareHttpComponentsClientHttpRequestFactory extends HttpComponentsClientHttpRequestFactory {
		private HttpContext httpContext;

		public ContextAwareHttpComponentsClientHttpRequestFactory(HttpClient httpClient) {
			super(httpClient);
		}

		protected HttpContext createHttpContext(HttpMethod httpMethod, URI uri) {
			// Ignoring the URI and method.
			return httpContext;
		}

		public void setHttpContext(HttpContext httpContext) {
			this.httpContext = httpContext;
		}
	}
	
	static {
		HttpHost targetHost = new HttpHost(TAKE_AWAY_ORDERS_SERVICE_HOST_NAME, TAKE_AWAY_ORDERS_SERVICE_HOST_PORT, TAKE_AWAY_ORDERS_SERVICE_HOST_SCHEME); 

		CredentialsProvider credsProvider = new BasicCredentialsProvider();
		credsProvider.setCredentials(
			new AuthScope(targetHost.getHostName(), targetHost.getPort()),	
		    new UsernamePasswordCredentials(TAKE_AWAY_ORDERS_SERVICE_USER, TAKE_AWAY_ORDERS_SERVICE_USER_PASSWORD));
		
		DefaultHttpClient httpClient = new DefaultHttpClient();
		httpClient.setCredentialsProvider(credsProvider);
		
		ContextAwareHttpComponentsClientHttpRequestFactory customFactory = new ContextAwareHttpComponentsClientHttpRequestFactory(httpClient);

		// Create AuthCache instance
		AuthCache authCache = new BasicAuthCache();
		// Generate BASIC scheme object and add it to the local auth cache
		BasicScheme basicAuth = new BasicScheme();
		authCache.put(targetHost, basicAuth);

		// Add AuthCache to the execution context
		BasicHttpContext localContext = new BasicHttpContext();
		localContext.setAttribute(ClientContext.AUTH_CACHE, authCache);    
		
		customFactory.setHttpContext(localContext);
		restTemplate =  new RestTemplate(customFactory);	
	}
	
	public ActionForward processOrders(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        UserSession userSession = (UserSession)session.getAttribute(MontagnesDOrActionServlet.USER_SESSION_PREFIX_KEY);

		DinnerTable[] tables = restTemplate.getForObject(TAKE_AWAY_ORDERS_SERVICE_URL, DinnerTable[].class);

		logger.debug("In processOrders, validation of imported tables.");
		logger.debug("In processOrders, number of tables to be imported: " + tables.length);
		ActionErrors errors = new ActionErrors();
		List<DinnerTable> tablesToBeSaved = new ArrayList<DinnerTable>();
		for (int i = 0; i < tables.length; i++) {
			DinnerTable processingTable = tables[i];
			
			String processingTableNumber = TAKE_AWAY_ORDERS_PREFIX_TABLE_NUMBER + processingTable.getNumber();
			DinnerTable tableToBeSaved = OrdersManagerFactory.getManager().getDinnerTable(processingTableNumber);
			logger.debug("In processOrders, processing table: " + processingTableNumber);
			if(tableToBeSaved != null) {
				ActionError error = new ActionError("tablesList.error.import.takeaway.orders.table.number", processingTableNumber);
				errors.add("tablesList.error.import.takeaway.orders.table.number", error);
				logger.error("The dinner table already exists " + processingTableNumber);
				continue;
			} else {
				tableToBeSaved = new DinnerTable();
				tableToBeSaved.setNumber(processingTableNumber);
			}
			tableToBeSaved.setTakeaway(true);
			tableToBeSaved.setRegistrationDate(processingTable.getRegistrationDate());
			tableToBeSaved.setUser(userSession.getUser());
			tablesToBeSaved.add(tableToBeSaved);
			
            Set<OrderLine> ordersToBeSaved = processingTable.getOrders();
            tableToBeSaved.setOrders(new HashSet<OrderLine>());
            boolean orderLineNoError = true;
            for (OrderLine orderLineToBeSaved : ordersToBeSaved) {
                String code = orderLineToBeSaved.getProduct().getId();
    			logger.debug("In processOrders, processing order line with quantity " + orderLineToBeSaved.getQuantity() + " and code " + code + " for table " + tableToBeSaved.getNumber());
                // Get label
                ProductLanguage productLanguage = OrdersManagerFactory.getManager().getProduct(code, userSession.getCurrentLanguage());
                if (productLanguage == null) {
    				ActionError error = new ActionError("tablesList.error.import.takeaway.orders.product.code", new String[] {code, tableToBeSaved.getNumber()});
    				errors.add("tablesList.error.import.takeaway.orders.product.code", error);
    				logger.error("Product does NOT exist with code " + code + " for table " + tableToBeSaved.getNumber());
    				orderLineNoError = false;
    				continue;
                }
                String label = productLanguage.getLabel();
                orderLineToBeSaved.setLabel(label);
                Float price = productLanguage.getId().getProduct().getPrice();
                orderLineToBeSaved.setUnitPrice(price);
                tableToBeSaved.getOrders().add(orderLineToBeSaved);
			}
            // The current order is OK but there is at least one previous order that throws an error.
			if (!errors.isEmpty() && orderLineNoError) {
				ActionError error = new ActionError("tablesList.error.import.takeaway.orders.table.valid.not.processed", processingTableNumber);
				errors.add("tablesList.error.import.takeaway.orders.table.valid.not.processed", error);
				logger.error("The dinner table is not processed " + processingTableNumber);
				continue;
			}				
		}

		if (!errors.isEmpty()) {
			saveErrors(request, errors);
			restTemplate.getForObject(TAKE_AWAY_ORDERS_SERVICE_URL_CALLBACK_NOT_OK, Object.class);
		} else {
			logger.debug("In processOrders, saving of imported tables.");
	        for (DinnerTable tableToBeSaved : tablesToBeSaved) {
	        	Set<OrderLine> ordersToBeSaved = new HashSet<OrderLine>(tableToBeSaved.getOrders());
	        	// Reset before saving table because we want to process order line of this table later.
	        	tableToBeSaved.getOrders().clear();
				OrdersManagerFactory.getManager().saveOrUpdateDinnerTable(tableToBeSaved);
				logger.debug("In processOrders, Dinner Table saved " + tableToBeSaved.toString());
				Float amountForReductionRation = 0f;
	        	for (OrderLine orderLineToBeSaved : ordersToBeSaved) {
	                Float quantity = orderLineToBeSaved.getQuantity();
	                String code = orderLineToBeSaved.getProduct().getId();
	                String label = orderLineToBeSaved.getLabel();
	                Float price = orderLineToBeSaved.getUnitPrice();
	                Float amount = quantity * price;
	                amountForReductionRation += amount;
	                
	    			OrderLine orderLine = OrdersManagerFactory.getManager().saveOrUpdateOrderLine(tableToBeSaved, null, quantity.toString(), code, label, price.toString(), amount.toString());
	    			logger.debug("In processOrders, OrderLine saved " + orderLine.toString());
				}
	            if (amountForReductionRation>MIN_AMOUNT_SUMS_TAKEAWAY) {
	            	tableToBeSaved.setReductionRatio(RATIO_REDUCTION_TAKEAWAY);
	            	OrdersManagerFactory.getManager().saveOrUpdateDinnerTable(tableToBeSaved);
	    			logger.debug("In processOrders, Dinner Table saved with reduction ratio " + tableToBeSaved.toString());
	            }
			}
			restTemplate.getForObject(TAKE_AWAY_ORDERS_SERVICE_URL_CALLBACK_OK, Object.class);
		}

		return mapping.findForward("success");
	}
}
