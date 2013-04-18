package fr.montagnesdor.restaurant.struts.actions;

import java.net.URI;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
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
	private final static String TAKE_AWAY_ORDERS_SERVICE_HOST_SCHEME = HttpHost.DEFAULT_SCHEME_NAME;
	private final static String TAKE_AWAY_ORDERS_SERVICE_HOST_NAME = "dev1.stoz.fr";
	private final static int TAKE_AWAY_ORDERS_SERVICE_HOST_PORT = 80;
	private final static String TAKE_AWAY_ORDERS_SERVICE_URL = TAKE_AWAY_ORDERS_SERVICE_HOST_SCHEME + "://"
															+ TAKE_AWAY_ORDERS_SERVICE_HOST_NAME + ":"
															+ TAKE_AWAY_ORDERS_SERVICE_HOST_PORT + "/json_export_cmd.html";

	
//	private final static String TAKE_AWAY_ORDERS_SERVICE_HOST_SCHEME = HttpHost.DEFAULT_SCHEME_NAME;
//	private final static String TAKE_AWAY_ORDERS_SERVICE_HOST_NAME = "localhost";
//	private final static int TAKE_AWAY_ORDERS_SERVICE_HOST_PORT = 8787;
//	private final static String TAKE_AWAY_ORDERS_SERVICE_URL = TAKE_AWAY_ORDERS_SERVICE_HOST_SCHEME + "://"
//															+ TAKE_AWAY_ORDERS_SERVICE_HOST_NAME + ":"
//															+ TAKE_AWAY_ORDERS_SERVICE_HOST_PORT + "/web-server/secure/json";
//
	//private final static String takeAwayOrdersServiceUrl = "http://localhost:8787/web-server/secure/json";
	
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
		    new UsernamePasswordCredentials("edouard", "kimsan123"));
		
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
	
	public static void main(String[] args) throws Exception {
		ResponseEntity<Object> tables = restTemplate.getForEntity(TAKE_AWAY_ORDERS_SERVICE_URL, Object.class);
		System.out.println(tables);
		
//		HttpHost targetHost = new HttpHost(TAKE_AWAY_ORDERS_SERVICE_HOST_NAME, TAKE_AWAY_ORDERS_SERVICE_HOST_PORT, TAKE_AWAY_ORDERS_SERVICE_HOST_SCHEME); 
//
//		DefaultHttpClient httpclient = new DefaultHttpClient();
//
//		httpclient.getCredentialsProvider().setCredentials(
//		        new AuthScope(targetHost.getHostName(), targetHost.getPort()), 
//		        new UsernamePasswordCredentials("edouard", "kimsan123"));
//
//		// Create AuthCache instance
//		AuthCache authCache = new BasicAuthCache();
//		// Generate BASIC scheme object and add it to the local auth cache
//		BasicScheme basicAuth = new BasicScheme();
//		authCache.put(targetHost, basicAuth);
//
//		// Add AuthCache to the execution context
//		BasicHttpContext localcontext = new BasicHttpContext();
//		localcontext.setAttribute(ClientContext.AUTH_CACHE, authCache);        
//
//		HttpGet httpget = new HttpGet(TAKE_AWAY_ORDERS_SERVICE_URL);
//		for (int i = 0; i < 3; i++) {
//		    HttpResponse response = httpclient.execute(targetHost, httpget, localcontext);
//		    HttpEntity entity = response.getEntity();
//		    EntityUtils.consume(entity);
//		    System.out.println(entity);
//		}
	}
}
