package fr.montagnesdor.restaurant.struts;

import java.io.IOException;
import java.util.Locale;

import javax.security.auth.Subject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.config.ModuleConfig;

import fr.montagnesdor.restaurant.Constants;
import fr.montagnesdor.restaurant.jaas.authorization.Authorization;
import fr.montagnesdor.restaurant.model.hibernate.DinnerTable;
import fr.montagnesdor.restaurant.services.orders.OrdersManagerFactory;
import fr.montagnesdor.util.FormatDecimal;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class MontagnesDOrActionServlet extends ActionServlet
{
	public final static String USER_SESSION_PREFIX_KEY = "userSession";
	public final static String TAG_ACCESS_IN_JSP = "TAG_ACCESS_IN_JSP";

	private static boolean isCheckAuthorization = false;
	private static boolean isHTTPS = false;

	private static String schemeHTTP = "http";
	private static String schemeHTTPS = "https";
	private static String portHTTP = "8080";
	private static String portHTTPS = "8443";
	private static String entryPoint = "/EntryPoint.do";
	private static String logon = "/Logon.do";
	private static String authorizationFailed = "/AuthorizationFailed.do";
	private static String index = "/index.jsp";

	/**
	 * @see javax.servlet.GenericServlet#init()
	 */
	public void init() throws ServletException
	{
		super.init();

		try
		{
			isCheckAuthorization = getInitParameter("isCheckAuthorization")!=null && getInitParameter("isCheckAuthorization").equals("true");
			isHTTPS = getInitParameter("isHTTPS")!=null && getInitParameter("isHTTPS").equals("true"); 
			
			//Pour le développement, laisser la variable  isHTTPS à false
			//En production, commenter la ligne suivante(le choix de cette variable dépend des paramètres du fichier web.xml
			//isHTTPS = false;

			//Permet de savoir si la TVA dépend des tables à emporter
			boolean isVatByTakeaway = getInitParameter("isVatByTakeaway")!=null && getInitParameter("isVatByTakeaway").equals("true");
			OrdersManagerFactory.getManager().setVatByTakeaway(isVatByTakeaway);
			
			//Permet de savoir quel type d'arrondi il faut appliquer
			int specificRound = FormatDecimal.HALF_ROUND; 
			try{specificRound = Integer.parseInt(getInitParameter("specificRound"));}catch(Exception e){}
			DinnerTable.setSpecificRound(specificRound);
			
			schemeHTTP = getInitParameter("schemeHTTP");
			schemeHTTPS = getInitParameter("schemeHTTPS");
			portHTTP = getInitParameter("portHTTP");
			portHTTPS = getInitParameter("portHTTPS");

			ModuleConfig mc = initModuleConfig("config", config);
			entryPoint = mc.findForwardConfig("entryPoint").getPath();
			logon = mc.findForwardConfig("logon").getPath();
			authorizationFailed = mc.findForwardConfig("authorizationFailed").getPath();
			index = mc.findForwardConfig("index").getPath();
			
		}
		catch (Exception e)
		{
		}
	}
	/*
	request.getContextPath() /montagnesdor
	request.getProtocol() HTTP/1.1
	request.getRemoteAddr() 127.0.0.1
	request.getRemoteHost() 127.0.0.1
	request.getRequestURI() /montagnesdor/PointEntree.do
	request.getRequestURL() http://localhost:8080/montagnesdor/PointEntree.do
	request.getServerName() localhost
	request.getServerPort() 8080
	request.getServletPath() /PointEntree.do
	 */
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		//Permet d'autoriser l'affichage des jsp
		request.setAttribute("authorization", "true");

		if (checkSchemeHTTP(request, response))
		{
			checkSession(request);
			checkLanguage(request);
			checkAuthorization(request, response);
		}
	}

	private void checkSession(HttpServletRequest request) throws ServletException, IOException
	{
		String entryPointURI = request.getContextPath() + entryPoint;
		HttpSession session = request.getSession();
		UserSession userSession = (UserSession) session.getAttribute(USER_SESSION_PREFIX_KEY);

		if (userSession != null && request.getRequestURI().equals(entryPointURI))
		{
			reinitSession(request);
		}
	}

	/*
	 * Cette méthode est utilisée pour vérifier le choix du language de l'utilisateur.
	 * Dans le cas où l'utilisateur n'a pas choisi une langue, c'est le language du navigateur qui est pris en compte.
	 */
	private void checkLanguage(HttpServletRequest request) throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		UserSession userSession = (UserSession) session.getAttribute(USER_SESSION_PREFIX_KEY);

		Locale locale = (Locale)request.getLocale();
		if (userSession != null)
		{
			userSession.processLanguage(userSession.getCurrentLanguage(), locale.getCountry());
		}
		else
		{
			//Struts Configuration For I18n
			session.setAttribute(Globals.LOCALE_KEY, new Locale(Constants.DEFAULT_LOCALE_LANGUAGE, locale.getCountry()));
			//JSTL Configuration For I18n
			Config.set(session, Config.FMT_LOCALE, new Locale(Constants.DEFAULT_LOCALE_LANGUAGE, locale.getCountry()));
		}
	}

	private boolean checkSchemeHTTP(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String entryPointURI = request.getContextPath() + entryPoint;
		String logonURI = request.getContextPath() + logon;
		String indexURI = request.getContextPath() + index;
		String authorizationFailedURI = request.getContextPath() + authorizationFailed;
		
		HttpSession session = request.getSession();
		UserSession userSession = (UserSession) session.getAttribute(USER_SESSION_PREFIX_KEY);
		if (userSession == null && !request.getRequestURI().equals(entryPointURI) && !request.getRequestURI().equals(logonURI) && !request.getRequestURI().equals(authorizationFailedURI))
		{
			response.sendRedirect(schemeHTTP + "://" + request.getServerName() + ":" + portHTTP + indexURI);
			return false;
		}

		if(request.getRequestURI().equals(authorizationFailedURI))
		{
			checkLanguage(request);
			super.process(request, response);
			return false;
		}
		
		if (request.getScheme().equals(schemeHTTPS))
		{
			if (!request.getRequestURI().equals(logonURI) && !request.getRequestURI().equals(entryPointURI))
			{
				response.sendRedirect(schemeHTTP + "://" + request.getServerName() + ":" + portHTTP + request.getRequestURI());
				return false;
			}
		}
		else
		{
			if ((request.getRequestURI().equals(logonURI) || request.getRequestURI().equals(entryPointURI)) && isHTTPS)
			{
				response.sendRedirect(schemeHTTPS + "://" + request.getServerName() + ":" + portHTTPS + request.getRequestURI());
				return false;
			}
		}

		return true;
	}

	private boolean checkAuthorization(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String entryPointURI = request.getContextPath() + entryPoint;
		String logonURI = request.getContextPath() + logon;
		String indexURI = request.getContextPath() + index;
		String authorizationFailedURI = request.getContextPath() + authorizationFailed;

		HttpSession session = request.getSession();
		UserSession userSession = (UserSession) session.getAttribute(USER_SESSION_PREFIX_KEY);

		//request.setAttribute(TAG_ACCESS_IN_JSP, TAG_ACCESS_IN_JSP);

		if (userSession == null && !request.getRequestURI().equals(entryPointURI) && !request.getRequestURI().equals(logonURI))
		{
			//redirect to login page
			reinitSession(request);
			response.sendRedirect(schemeHTTP + "://" + request.getServerName() + ":" + portHTTP + indexURI);
			return true;
		}
		else if (userSession == null && (request.getRequestURI().equals(entryPointURI) || request.getRequestURI().equals(logonURI)))
		{
			// login page is always permitted
			super.process(request, response);
			return true;
		}
		else
		{
			if(!isCheckAuthorization)
			{
				super.process(request, response);
				return true;	
			}
			
			String pageReq = request.getRequestURI();
			try
			{
				pageReq = pageReq.substring(pageReq.indexOf('/', 1), pageReq.length());
			}
			catch (Exception e)
			{
			}

			Subject subject = userSession.getSubject();
			String action = null;
			if ((action = Authorization.permitted(subject, pageReq))==null)
			{
				reinitSession(request);
				if(isHTTPS)
					//response.sendRedirect(schemeHTTPS + "://" + request.getServerName() + ":" + portHTTPS + authorizationFailedURI);
				response.sendRedirect(schemeHTTPS + "://" + request.getServerName() + ":" + portHTTPS + indexURI+"?authorization=true");
				else	
					//response.sendRedirect(schemeHTTP + "://" + request.getServerName() + ":" + portHTTP + authorizationFailedURI);
				response.sendRedirect(schemeHTTP + "://" + request.getServerName() + ":" + portHTTP + indexURI+"?authorization=true");				
				return false;
			}
			else
			{
				if(action.equals("*"))
					super.process(request, response);
				else	
					response.sendRedirect(schemeHTTP + "://" + request.getServerName() + ":" + portHTTP + request.getContextPath() + action);
				return true;				
			}
		}
		//return false;
	}


	public static void reinitSession(HttpServletRequest request)
	{
		Locale locale = request.getLocale();
		HttpSession session = request.getSession();
		session.removeAttribute(USER_SESSION_PREFIX_KEY);
		//Struts Configuration For I18n
		session.setAttribute(Globals.LOCALE_KEY, locale);
		//JSTL Configuration For I18n
		Config.set(session, Config.FMT_LOCALE, locale);
	}
	
	/**
	 * Returns the portHTTP.
	 * @return String
	 */
	public static String getPortHTTP()
	{
		return portHTTP;
	}

	/**
	 * Returns the portHTTPS.
	 * @return String
	 */
	public static String getPortHTTPS()
	{
		return portHTTPS;
	}

	/**
	 * Returns the schemeHTTP.
	 * @return String
	 */
	public static String getSchemeHTTP()
	{
		return schemeHTTP;
	}

	/**
	 * Returns the schemeHTTPS.
	 * @return String
	 */
	public static String getSchemeHTTPS()
	{
		return schemeHTTPS;
	}

	/**
	 * Returns the index.
	 * @return String
	 */
	public static String getIndex()
	{
		return index;
	}

}
