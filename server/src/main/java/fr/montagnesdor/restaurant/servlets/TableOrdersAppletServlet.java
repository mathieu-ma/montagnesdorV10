/*
 * ProduitsAppletServet.java
 *
 * Created on 21 f�vrier 2002, 21:12
 */

package fr.montagnesdor.restaurant.servlets;

import fr.montagnesdor.restaurant.model.hibernate.DinnerTable;
import fr.montagnesdor.restaurant.model.hibernate.OrderLine;
import fr.montagnesdor.restaurant.model.hibernate.ProductLanguage;
import fr.montagnesdor.restaurant.model.hibernate.Room;
import fr.montagnesdor.restaurant.services.orders.OrdersManagerFactory;
import fr.montagnesdor.restaurant.struts.MontagnesDOrActionServlet;
import fr.montagnesdor.restaurant.struts.UserSession;
import fr.montagnesdor.util.log.MyLog;
import fr.montagnesdor.util.log.MyLogFactoryImpl;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author  maxime
 * @version
 */
public class TableOrdersAppletServlet extends HttpServlet
{
    /** Initializes the servlet.
     */
    public void init(ServletConfig config) throws ServletException
    {
        super.init(config);
        
    }
    
    /** Destroys the servlet.
     */
    public void destroy()
    {
        
    }
    
    /** Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException//, java.io.IOException
    {
		MyLog logger = MyLogFactoryImpl.getInstance().getLogger(TableOrdersAppletServlet.class.getName());
         
        HttpSession session = request.getSession();
        UserSession userSession = (UserSession)session.getAttribute(MontagnesDOrActionServlet.USER_SESSION_PREFIX_KEY);
        logger.debug("La session de l'utilisateur est : "+userSession);
        
        java.io.ObjectOutputStream oos = null;
        try
        {
            //Ouvre un flux de sortie
            oos = new java.io.ObjectOutputStream(response.getOutputStream());
			logger.debug("Flux sortie : "+oos);
            if(userSession != null)
            {
                String tableNumber = request.getParameter("tableNumber");
				logger.debug("Numero table "+tableNumber);
                String action = request.getParameter("action");
				logger.debug("Action choisie "+action);
                
                Room room  = (Room)userSession.getRoom();
				logger.debug("Salle  "+room);

                if(tableNumber==null)
                {
					String code = request.getParameter("code");

                	if(action.equals("getProduct"))
                	{
	                    String[] product = new String[0];
	                    ProductLanguage productLanguage = null;
    	                if(code!=null)
							productLanguage = OrdersManagerFactory.getManager().getProduct(code, userSession.getCurrentLanguage());
    	                if(productLanguage==null)
    	                    productLanguage = OrdersManagerFactory.getManager().getProduct(code);
						if(productLanguage!=null)
						{
							product = new String[4];
							product[0] = code;
							product[1] = productLanguage.getLabel();
							product[2] = productLanguage.getId().getProduct().getPrice()+"";
							product[3] = productLanguage.getId().getProduct().getColorRGB();							
						}
                   
            	        //Ecriture sur le flux de sortie de la servlet de l'objet listeProduits
                	    oos.writeObject(product);
	                }
/*	                
					if(actionChoisie.equals("isProduitEffacable"))
					{
						boolean isProduitEffacable = salle.isProduitEffacable(codeProduit);
                    
						//Ecriture sur le flux de sortie de la servlet de l'objet listeProduits
						oos.writeObject(isProduitEffacable+"");
					}
					
					if(actionChoisie.equals("isTypeProduitEffacable"))
					{
						int idType = Integer.parseInt(request.getParameter("idType"));
						boolean isTypeProduitEffacable = salle.isTypeProduitEffacable(idType);
                    
						//Ecriture sur le flux de sortie de la servlet de l'objet listeProduits
						oos.writeObject(isTypeProduitEffacable+"");
					}
*/
                }
                else
                {
					DinnerTable currentTable = room.getCurrentTable();
					if(currentTable==null || currentTable.getNumber()==null || !currentTable.getNumber().equals(tableNumber))
					{
						currentTable = OrdersManagerFactory.getManager().getDinnerTable(tableNumber);
						if(currentTable == null)
						{
							currentTable = new DinnerTable();
							currentTable.setNumber(tableNumber);
						}
						room.setCurrentTable(currentTable);
					}
                    
                    if(action.equals("getCustomerNumber"))
                    {
                        String customerNumber = "";
                        //Dans le cas où l'impression est déjà sortie et que l'utilisateur s'est bien authentifié auparavant
						currentTable.setAllowModifyOrdersPrinting(false);
                        if(currentTable.getOrders()!=null && currentTable.getOrders().size()!=0)
							customerNumber = currentTable.getCustomersNumber()+"";
                        //Ecriture sur le flux de sortie de la servlet
                        oos.writeObject(customerNumber);
                    }

					if(action.equals("setTakeaway"))
					{
						boolean takeaway = Boolean.valueOf(request.getParameter("takeaway")).booleanValue();
						currentTable.setTakeaway(takeaway);
						
						if(currentTable.getOrders()!=null && currentTable.getOrders().size()!=0)
							OrdersManagerFactory.getManager().saveOrUpdateDinnerTable(currentTable);		

						//Ecriture sur le flux de sortie de la servlet
						oos.writeObject(new Boolean(true));
					}
                    
/*
                    if(actionChoisie.equals("changerTable"))
                    {
                        String newNumeroTable = request.getParameter("newNumeroTable");
                        oos.writeObject(salle.changerTable(numeroTable, newNumeroTable));
                    }
                    
                    if(actionChoisie.equals("cumulerTable"))
                    {
                        String newNumeroTable = request.getParameter("newNumeroTable");
                        oos.writeObject(salle.cumulerTable(numeroTable, newNumeroTable));
                    }
*/                    
                    if(action.equals("setCustomerNumber"))
                    {
                        int customerNumber = 0;
                        try
                        {
							customerNumber = Integer.parseInt(request.getParameter("customerNumber"));
                        }
                        catch(NumberFormatException nfe)
                        {
                        }
						currentTable.setCustomersNumber(customerNumber);

						if(currentTable.getOrders()!=null && currentTable.getOrders().size()!=0)
							OrdersManagerFactory.getManager().saveOrUpdateDinnerTable(currentTable);		

                        oos.writeObject(new Boolean(true));
                    }
                    if(action.equals("saveOrderLine"))
                    {
                        //Il faut vé�rifier que 'il y a au moins une commande non vide
                        String orderLineId = request.getParameter("orderLineId");
                        String quantity = request.getParameter("quantity");
                        String code = request.getParameter("code");
                        String label = request.getParameter("label");
                        byte [] hibytes = ((String)label).getBytes("ISO-8859-1");
                        label = new String(hibytes, "UTF-8");
                        String price = request.getParameter("price");
                        String amount = request.getParameter("amount");

                        //Float.parseFloat(quantite), codeProduit, designation, Float.parseFloat(prix), Float.parseFloat(montant));
                        if(currentTable.getOrders()==null || currentTable.getOrders().size() == 0)
                        {   
							String entryDate = request.getParameter("entryDate");
							String patternDate = request.getParameter("patternDate");						
							SimpleDateFormat patternDateSDF = new SimpleDateFormat(patternDate);
							try
							{
								currentTable.setRegistrationDate(patternDateSDF.parse(entryDate));
							}
							catch(ParseException pe)
							{
								logger.error("Impossible de parser la date d'entr�ée de la table "+tableNumber, pe);
							}
							
							currentTable.setUser(userSession.getUser());
							currentTable.setCustomersNumber(Integer.parseInt(request.getParameter("customerNumber")));
							OrdersManagerFactory.getManager().saveOrUpdateDinnerTable(currentTable);
                        }
						OrderLine orderLine = OrdersManagerFactory.getManager().saveOrUpdateOrderLine(currentTable, orderLineId, quantity, code, label, price, amount);
                        oos.writeObject(orderLine.getId()+"");
                    }
					if(action.equals("deleteOrderLine"))
					{
						OrdersManagerFactory.getManager().deleteOrderLine(currentTable, request.getParameter("orderLineId"));
						oos.writeObject(new Boolean(true));
					}
					if(action.equals("deleteDinnerTable"))
					{
						Long tableId_L = null;
						try
						{
							tableId_L = Long.valueOf(request.getParameter("tableId"));
						}
						catch(NumberFormatException nfe)
						{
						}
						OrdersManagerFactory.getManager().deleteDinnerTableById(tableId_L);
						currentTable.clear();
						oos.writeObject(new Boolean(true));
					}
                    
                    if(action.equals("setPrintDate"))
                    {
                    	String newDate = request.getParameter("newDate");
						String patternDate = request.getParameter("patternDate");                    	
                    	Date date = null;
                    	if(!newDate.equals("null"))
                    	{
							SimpleDateFormat patternDateSDF = new SimpleDateFormat(patternDate);
							try
							{
								date = patternDateSDF.parse(newDate);
							}
							catch(ParseException pe)
							{
								logger.error("Impossible de parser la date d'impression", pe);
							}
                    	}
                    	
                        currentTable.setPrintDate(date);
						OrdersManagerFactory.getManager().saveOrUpdateDinnerTable(currentTable);
                        oos.writeObject(new Boolean(true));
                    }
                    if(action.equals("setReductionRatio"))
                    {
                        float reductionRatio =  Float.parseFloat(request.getParameter("reductionRatio"));
						boolean isReductionRatioChanged =  request.getParameter("isReductionRatioChanged").equals("true");
						if(currentTable.getOrders()!=null && currentTable.getOrders().size()>0)
						{
						    currentTable.setReductionRatio(reductionRatio);
							currentTable.setReductionRatioChanged(isReductionRatioChanged);
							OrdersManagerFactory.getManager().saveOrUpdateDinnerTable(currentTable);
						}	
                        oos.writeObject(new Boolean(true));
                    }
					
					if(action.equals("isReductionRatioChanged"))
					{
						oos.writeObject(new Boolean(currentTable.isReductionRatioChanged()));
					}

					if(action.equals("isAllowModifyOrdersPrinting"))
					{
						oos.writeObject(new Boolean(currentTable.isAllowModifyOrdersPrinting()));
					}
                }
            }
            else
            {
                oos.writeObject(null);
				logger.debug("Reponse null car session invalide");
            }
        }
        catch(IOException ioe)
        {
			logger.fatal("Erreur dans le flux de sortie de l'applet-servlet", ioe);
        }
        finally
        {
            try
            {oos.close();}catch(Exception ioe)
            {}
        }
    }
    
    
    /** Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException
    {
        doGet(request, response);
    }
    
    /** Returns a short description of the servlet.
     */
    public String getServletInfo()
    {
        return "Servlet communiquant avec l'applet CommandeApplet!";
    }
    
}
