package fr.montagnesdor.restaurant.services.orders.spi;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import net.sf.hibernate.Criteria;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.ObjectNotFoundException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;
import net.sf.hibernate.Transaction;
import net.sf.hibernate.expression.Expression;
import net.sf.hibernate.expression.Order;
import net.sf.hibernate.type.LongType;
import fr.montagnesdor.restaurant.Constants;
import fr.montagnesdor.restaurant.model.hibernate.Cashing;
import fr.montagnesdor.restaurant.model.hibernate.DinnerTable;
import fr.montagnesdor.restaurant.model.hibernate.HibernateUtil;
import fr.montagnesdor.restaurant.model.hibernate.Locale;
import fr.montagnesdor.restaurant.model.hibernate.OrderLine;
import fr.montagnesdor.restaurant.model.hibernate.Product;
import fr.montagnesdor.restaurant.model.hibernate.ProductLanguage;
import fr.montagnesdor.restaurant.model.hibernate.ProductLanguageID;
import fr.montagnesdor.restaurant.model.hibernate.ProductPart;
import fr.montagnesdor.restaurant.model.hibernate.StatsConsumptionProduct;
import fr.montagnesdor.restaurant.model.hibernate.ValueAddedTax;
import fr.montagnesdor.restaurant.model.hibernate.VatTable;
import fr.montagnesdor.restaurant.services.orders.OrdersManager;
import fr.montagnesdor.util.log.MyLog;
import fr.montagnesdor.util.log.MyLogFactoryImpl;
import fr.montagnesdor.util.log.MyLogImpl;

public class DefaultOrdersManager implements OrdersManager
{
	public static int MAX_LABEL_LENGHT = 50;
	public boolean vatByTakeaway = false;
	private static OrdersManager instance = null;
	private static MyLog logger = MyLogFactoryImpl.getInstance().getLogger(DefaultOrdersManager.class.getName());

	public DefaultOrdersManager()
	{
	}

	public static OrdersManager getInstance()
	{
		if (instance == null)
			synchronized (DefaultOrdersManager.class)
			{
				if (instance == null)
					instance = new DefaultOrdersManager();
			}

		return instance;
	}

	public ProductLanguage getProduct(String productCode)
	{
		return getProduct(productCode, Constants.DEFAULT_LOCALE_LANGUAGE);
	}

	public ProductLanguage getProduct(String productCode, String localeStr)
	{
		ProductLanguage productLanguage = null;
		try
		{
			Session session = HibernateUtil.currentSession();
			Transaction tx = session.beginTransaction();

			if (localeStr == null)
				localeStr = Constants.DEFAULT_LOCALE_LANGUAGE;

			/*
						Query q = session.getNamedQuery("ProductLanguage.SelectByProductIdLanguage");
						q.setString("productId", productCode);
						q.setString("language", localeStr);
						
						productLanguage = (ProductLanguage)q.uniqueResult();
			*/
			Product product = null;
			try
			{
				product = (Product) session.load(Product.class, productCode);
			}
			catch (ObjectNotFoundException onfe)
			{
				logger.error(MyLogImpl.MESSAGES.getString("message.error.product") + " : " + productCode);
				return null;
			}

			Locale locale = null;
			try
			{
				locale = (Locale) session.load(Locale.class, localeStr);
			}
			catch (ObjectNotFoundException onfe)
			{
				localeStr = Constants.DEFAULT_LOCALE_LANGUAGE;
				locale = (Locale) session.load(Locale.class, localeStr);
			}

			ProductLanguageID productLanguageID = new ProductLanguageID();
			productLanguageID.setLocale(locale);
			productLanguageID.setProduct(product);
			productLanguage = (ProductLanguage) session.load(ProductLanguage.class, productLanguageID);

			tx.commit();
			HibernateUtil.closeSession();
		}
		catch (HibernateException he)
		{
			logger.error(MyLogImpl.MESSAGES.getString("message.error.product") + " : " + productCode);
		}
		finally
		{
			try
			{
				HibernateUtil.closeSession();
			}
			catch (HibernateException he)
			{
			}
		}

		return productLanguage;
	}

	public List getDinnerTablesExcludeCashedTable(int filterList, String sortListBy, String sortMonotony)
	{
		List result = null;

		try
		{
			Session session = HibernateUtil.currentSession();
			Transaction tx = session.beginTransaction();
			
			//SELECT dinnerTable FROM DinnerTable AS dinnerTable WHERE dinnerTable.cashingDate IS NULL ORDER BY dinnerTable.number ASC
			Criteria criteria = session.createCriteria(DinnerTable.class).add(Expression.isNull("cashingDate"));
			
			switch(filterList)
			{
				case 1:
					//Tables Non Imprimées
					criteria.add(Expression.isNull("printDate"));
				break;	
				case 2:
					//Tables à encaissées
					criteria.add(Expression.isNotNull("printDate"));
				break;	
				case 3:
					//Tables à emporter
					criteria.add(Expression.eq("takeaway", Boolean.TRUE));
				break;	
				case 4:
					//Tables sur place
					criteria.add(Expression.eq("takeaway", Boolean.FALSE));
				break;	
				case 5:
					//Imported orders
					criteria.add(Expression.eq("takeaway", Boolean.TRUE));
					criteria.add(Expression.like("number", "VAD%"));
				break;	
			}
			
			if(sortMonotony.equals("asc"))
				criteria.addOrder(Order.asc(sortListBy));
			else
				criteria.addOrder(Order.desc(sortListBy));
			result = criteria.list(); 
/*
			Query q = session.getNamedQuery("DinnerTable.SelectExcludeCashedTable");
			result = q.list();
*/
			tx.commit();
			HibernateUtil.closeSession();
		}
		catch (HibernateException he)
		{
			logger.error(MyLogImpl.MESSAGES.getString("message.error.dinner.tables"));
		}
		finally
		{
			try
			{
				HibernateUtil.closeSession();
			}
			catch (HibernateException he)
			{
			}
		}

		return result;
	}

	public DinnerTable getDinnerTable(String tableNumber)
	{
		DinnerTable dinnerTable = null;
		//SELECT dinnerTable FROM DinnerTable AS dinnerTable WHERE dinnerTable.number = :tableNumber
		try
		{
			Session session = HibernateUtil.currentSession();
			Transaction tx = session.beginTransaction();

			Query q = session.getNamedQuery("DinnerTable.SelectByTableNumber");
			q.setString("tableNumber", tableNumber);
			dinnerTable = (DinnerTable) q.uniqueResult();

			tx.commit();
			HibernateUtil.closeSession();
		}
		catch (HibernateException he)
		{
			logger.error(MyLogImpl.MESSAGES.getString("message.error.dinner.tables"));
		}
		finally
		{
			try
			{
				HibernateUtil.closeSession();
			}
			catch (HibernateException he)
			{
			}
		}

		return dinnerTable;
	}

	public DinnerTable getDinnerTableById(Long id)
	{
		DinnerTable dinnerTable = null;
		try
		{
			Session session = HibernateUtil.currentSession();
			Transaction tx = session.beginTransaction();

			dinnerTable = (DinnerTable) session.load(DinnerTable.class, id);

			tx.commit();
			HibernateUtil.closeSession();
		}
		catch (HibernateException he)
		{
			logger.error(MyLogImpl.MESSAGES.getString("message.error.dinner.tables"));
		}
		finally
		{
			try
			{
				HibernateUtil.closeSession();
			}
			catch (HibernateException he)
			{
			}
		}

		return dinnerTable;
	}

	public DinnerTable mergeTable(DinnerTable currentTable, DinnerTable tableToMerge)
	{
		DinnerTable result = null;

		tableToMerge.setAmountsSum(tableToMerge.getAmountsSum() + currentTable.getAmountsSum());
		tableToMerge.setQuantitiesSum(tableToMerge.getQuantitiesSum() + currentTable.getQuantitiesSum());
		tableToMerge.setAmountPay(tableToMerge.getAmountsSum() * (1 - tableToMerge.getReductionRatio() / 100));
		if (!tableToMerge.isTakeaway())
			tableToMerge.setCustomersNumber(tableToMerge.getCustomersNumber() + currentTable.getCustomersNumber());

		//Problème sur les réductions par ligne de commandes : à réfléchir
		OrderLine orderLine = null;
		for(Iterator i = currentTable.getOrders().iterator(); i.hasNext();)
		{
			orderLine = (OrderLine) i.next();
			orderLine.setDinnerTable(tableToMerge);
		}
		tableToMerge.getOrders().addAll(currentTable.getOrders());

		PreparedStatement preparedStatement = null;
		try
		{
			Session session = HibernateUtil.currentSession();
			Transaction tx = session.beginTransaction();

			session.saveOrUpdate(tableToMerge);
			
			Query q = session.getNamedQuery("OrderLine.UpdateByDinnerTable");
			preparedStatement = session.connection().prepareStatement(q.getQueryString());
			preparedStatement.setLong(1, tableToMerge.getId().longValue());
			preparedStatement.setLong(2, currentTable.getId().longValue());
			preparedStatement.execute();
			
			q = session.getNamedQuery("DinnerTable.DeleteById");
			session.delete(q.getQueryString(), currentTable.getId(), new LongType());

			result = tableToMerge;
			
			tx.commit();
//			preparedStatement.close();			
			HibernateUtil.closeSession();

		}
		catch (HibernateException he)
		{
			logger.error(MyLogImpl.MESSAGES.getString("message.error.merge.table") + " : " + tableToMerge.getNumber() + ":" + currentTable.getNumber());
		}
		catch (SQLException sqle)
		{
			logger.error(MyLogImpl.MESSAGES.getString("message.error.merge.table") + " : " + tableToMerge.getNumber() + ":" + currentTable.getNumber());
		}
		finally
		{
			try
			{
//				preparedStatement.close();
				HibernateUtil.closeSession();
			}
			catch (Exception e)
			{
			}
		}
		return result;
	}

	public void saveOrUpdateDinnerTable(DinnerTable dinnerTable)
	{
		try
		{
			Session session = HibernateUtil.currentSession();
			Transaction tx = session.beginTransaction();
			/*
						if (dinnerTable.getOrders() != null)
						{
							//On sauve la table demandée afin de s'assurer que les champs tbd_quantities_sum numeric, tbd_amounts_sum numeric et tbd_amount_pay numeric
							OrderLine orderLine = null;
							dinnerTable.setAmountsSum(0);
							dinnerTable.setQuantitiesSum(0);
							for (Iterator i = dinnerTable.getOrders().iterator(); i.hasNext();)
							{
								orderLine = (OrderLine) i.next();
								dinnerTable.setAmountsSum(orderLine.getAmount() + dinnerTable.getAmountsSum());
								dinnerTable.setQuantitiesSum(orderLine.getQuantity() + dinnerTable.getQuantitiesSum());
							}
							dinnerTable.setAmountPay(dinnerTable.getAmountsSum() * (1 - dinnerTable.getReductionRatio() / 100));
						}
			*/
			session.saveOrUpdate(dinnerTable);

			tx.commit();
			HibernateUtil.closeSession();
		}
		catch (HibernateException he)
		{
			logger.error(MyLogImpl.MESSAGES.getString("message.error.dinner.table.save") + " : " + dinnerTable.getNumber());
		}
		finally
		{
			try
			{
				HibernateUtil.closeSession();
			}
			catch (HibernateException he)
			{
			}
		}
	}

	public OrderLine saveOrUpdateOrderLine(DinnerTable dinnerTable, String orderLineId, String quantity, String code, String label, String price, String amount)
	{
		boolean isSpecialCodeValueFlag = false;
		OrderLine orderLine = new OrderLine();
		Long orderLineId_L = null;
		orderLine.setDinnerTable(dinnerTable);
		Product product = null;
		float quantity_f = 0;
		float price_f = 0;
		float amount_f = 0;
		try
		{
			quantity_f = Float.parseFloat(quantity);
			price_f = Float.parseFloat(price);
			amount_f = Float.parseFloat(amount);
			orderLineId_L = new Long(orderLineId);
		}
		catch (NumberFormatException nfe)
		{
		}
		try
		{
			Session session = HibernateUtil.currentSession();
			Transaction tx = session.beginTransaction();

			try
			{
				product = (Product) session.load(Product.class, code);
			}
			catch (ObjectNotFoundException onfe)
			{
				product = (Product) session.load(Product.class, code.charAt(0) + "");
				isSpecialCodeValueFlag = true;
			}
			
			if(orderLineId_L!=null)
			{
				orderLine = (OrderLine)session.load(OrderLine.class, orderLineId_L);
				dinnerTable.setAmountsSum(dinnerTable.getAmountsSum() - orderLine.getAmount());
				dinnerTable.setQuantitiesSum(dinnerTable.getQuantitiesSum() - orderLine.getQuantity());
			}
			orderLine.setQuantity(quantity_f);
			orderLine.setLabel(label.substring(0, label.length() > MAX_LABEL_LENGHT ? MAX_LABEL_LENGHT : label.length()));
			orderLine.setUnitPrice(price_f);
			orderLine.setAmount(amount_f);
			orderLine.setId(orderLineId_L);
			orderLine.setProduct(product);
			
			ProductPart productPart = new ProductPart();
			productPart.setId(new Long(1));
			orderLine.setProductPart(productPart);

			if (isSpecialCodeValueFlag)
				orderLine.setSpecialCodeValue(code.substring(1));

			session.saveOrUpdate(orderLine);

			tx.commit();
			HibernateUtil.closeSession();

			if (orderLine.getId() != null)
			{
				if (dinnerTable.getOrders() == null)
				{
					DinnerTable dinnerTableTemp = getDinnerTable(dinnerTable.getNumber());
					dinnerTable.setOrders(dinnerTableTemp.getOrders());
				}
				if(orderLineId_L==null)
				    dinnerTable.getOrders().add(orderLine);
				else
				{
				    for(Iterator i =dinnerTable.getOrders().iterator(); i.hasNext();)
				    {
				        OrderLine ol = (OrderLine)i.next();
				        if(orderLine.getId().equals(ol.getId()))
				        {
				            ol.setQuantity(orderLine.getQuantity());
				            ol.setAmount(orderLine.getAmount());
				            break;
				        }
				    }
				}
				dinnerTable.setAmountsSum(orderLine.getAmount() + dinnerTable.getAmountsSum());
				dinnerTable.setQuantitiesSum(orderLine.getQuantity() + dinnerTable.getQuantitiesSum());
				dinnerTable.setAmountPay(dinnerTable.getAmountsSum() * (1 - dinnerTable.getReductionRatio() / 100));
				//On sauve la table demandée afin de s'assurer que les champs tbd_quantities_sum numeric, tbd_amounts_sum numeric et tbd_amount_pay numeric				
				saveOrUpdateDinnerTable(dinnerTable);
			}
		}
		catch (HibernateException he)
		{
			logger.error(MyLogImpl.MESSAGES.getString("message.error.order.line.save") + " : " + dinnerTable.getNumber() + ":" + orderLine.getLabel());
		}
		finally
		{
			try
			{
				HibernateUtil.closeSession();
			}
			catch (HibernateException he)
			{
			}
		}
		return orderLine;
	}

	public void deleteOrderLine(DinnerTable dinnerTable, String orderLineId)
	{
		OrderLine orderLine = null;
		Long orderLineId_L = null;

		try
		{
			orderLineId_L = new Long(orderLineId);

			Session session = HibernateUtil.currentSession();
			Transaction tx = session.beginTransaction();

			orderLine = (OrderLine) session.load(OrderLine.class, orderLineId_L);
			session.delete(orderLine);

			tx.commit();
			HibernateUtil.closeSession();

			orderLine.getDinnerTable().getOrders().remove(orderLine);
			dinnerTable.setOrders(orderLine.getDinnerTable().getOrders());
			if (dinnerTable.getOrders().size() == 0)
			{
				deleteDinnerTable(dinnerTable);
			}
			else
			{
				dinnerTable.setAmountsSum(dinnerTable.getAmountsSum() - orderLine.getAmount());
				dinnerTable.setQuantitiesSum(dinnerTable.getQuantitiesSum() - orderLine.getQuantity());
				dinnerTable.setAmountPay(dinnerTable.getAmountsSum() * (1 - dinnerTable.getReductionRatio() / 100));
				//On sauve la table demandée afin de s'assurer que les champs tbd_quantities_sum numeric, tbd_amounts_sum numeric et tbd_amount_pay numeric				
				saveOrUpdateDinnerTable(dinnerTable);
			}
		}
		catch (NumberFormatException nfe)
		{
			logger.error(MyLogImpl.MESSAGES.getString("message.error.delete.orderline.id") + " : " + orderLineId_L);
		}
		catch (ObjectNotFoundException onfe)
		{
			logger.error(MyLogImpl.MESSAGES.getString("message.error.delete.orderline.id") + " : " + orderLineId_L);
		}
		catch (HibernateException he)
		{
			logger.error(MyLogImpl.MESSAGES.getString("message.error.delete.orderline") + " : " + orderLineId_L);
		}
		finally
		{
			try
			{
				HibernateUtil.closeSession();
			}
			catch (HibernateException he)
			{
			}
		}
	}

	public void deleteDinnerTable(DinnerTable dinnerTable)
	{
		try
		{
			Session session = HibernateUtil.currentSession();
			Transaction tx = session.beginTransaction();

			Query q = session.getNamedQuery("OrderLine.deleteByDinnerTable");
			if(dinnerTable.getOrders()!=null && dinnerTable.getOrders().size()>0)
			{
				session.delete(q.getQueryString(), dinnerTable.getId(), new LongType());
			}
			
			q = session.getNamedQuery("VatTable.deleteByDinnerTable");
			session.delete(q.getQueryString(), dinnerTable.getId(), new LongType());
			
			q = session.getNamedQuery("DinnerTable.DeleteById");
			session.delete(q.getQueryString(), dinnerTable.getId(), new LongType());
			
			tx.commit();
			HibernateUtil.closeSession();

			dinnerTable.clear();
		}
		catch (HibernateException he)
		{
			logger.error(MyLogImpl.MESSAGES.getString("message.error.delete.dinnertable") + " : " + dinnerTable.getNumber());
		}
		catch (Exception e)
		{
			logger.error(MyLogImpl.MESSAGES.getString("message.error.delete.dinnertable") );
		}
		finally
		{
			try
			{
				HibernateUtil.closeSession();
			}
			catch (HibernateException he)
			{
			}
		}
	}

	public void deleteDinnerTableById(Long id)
	{
		DinnerTable dinnerTable = null;
		try
		{
			Session session = HibernateUtil.currentSession();
			dinnerTable = (DinnerTable)session.load(DinnerTable.class, id);
			HibernateUtil.closeSession();
		}
		catch (HibernateException he)
		{
		}
		finally
		{
			try
			{
				HibernateUtil.closeSession();
			}
			catch (HibernateException he)
			{
			}
		}
		deleteDinnerTable(dinnerTable);
	}

	public List getDayRevenuesByTableId(Long id)
	{
		List result = null;
		DinnerTable dinnerTable = null;
		
		try
		{
			Session session = HibernateUtil.currentSession();
			
			dinnerTable = (DinnerTable)session.load(DinnerTable.class, id);
			
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(dinnerTable.getRegistrationDate());
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			Date registrationDate = calendar.getTime();
			
			Query q = session.getNamedQuery("DayRevenue.SelectByDinnerTableId");
			q.setLong("tableId", id.longValue());
			q.setDate("registrationDate", registrationDate);
						
			result = q.list();
			
			HibernateUtil.closeSession();
		}
		catch (HibernateException he)
		{
		}
		finally
		{
			try
			{
				HibernateUtil.closeSession();
			}
			catch (HibernateException he)
			{
			}
		}
		return result;
	}
	
	public void saveOrUpdateCashing(Cashing cashing)
	{
		try
		{
			Session session = HibernateUtil.currentSession();
			Transaction tx = session.beginTransaction();
			session.saveOrUpdate(cashing);
			
			session.saveOrUpdate(cashing.getDinnerTable());
			session.flush();
			
			DinnerTable dinnerTable = cashing.getDinnerTable();
			OrderLine orderLine = null;
			Product product = null;
			Criteria criteria = null;
			StatsConsumptionProduct statsConsumptionProduct = null;
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(dinnerTable.getCashingDate());
			Short day = new Short((short)calendar.get(Calendar.DAY_OF_MONTH)); 
			Short month = new Short((short)calendar.get(Calendar.MONTH));
			Short year = new Short((short)calendar.get(Calendar.YEAR));
			for(Iterator i=dinnerTable.getOrders().iterator(); i.hasNext();)
			{
			    orderLine = (OrderLine)i.next();
			    if(orderLine.getProduct().getProductSpecialCode().getCode().equals("OFFERED_PRODUCT"))
			    {
			        product = (Product)session.load(Product.class, orderLine.getSpecialCodeValue());
			        orderLine.setProduct(product);
			        orderLine.getProduct().getProductSpecialCode().setCode("NOTHING");
			    }
			    if(orderLine.getProduct().getProductSpecialCode().getCode().equals("NOTHING"))
			    {
			        criteria = session.createCriteria(StatsConsumptionProduct.class).add(Expression.eq("product.id", orderLine.getProduct().getId()));
			        criteria.add(Expression.eq("updatedDay", day));
			        criteria.add(Expression.eq("updatedMonth", month));
			        criteria.add(Expression.eq("updatedYear", year));
			        statsConsumptionProduct = (StatsConsumptionProduct)criteria.uniqueResult();
			        if(statsConsumptionProduct==null)
			        {
			            statsConsumptionProduct = new StatsConsumptionProduct();
			            statsConsumptionProduct.setProduct(orderLine.getProduct());
			        }
		            statsConsumptionProduct.setQuantity(statsConsumptionProduct.getQuantity()+orderLine.getQuantity());
		            statsConsumptionProduct.setUpdatedDay(day);
		            statsConsumptionProduct.setUpdatedMonth(month);
		            statsConsumptionProduct.setUpdatedYear(year);
		            session.saveOrUpdate(statsConsumptionProduct);
					session.flush();
			    }
			}

			tx.commit();
			HibernateUtil.closeSession();
		}
		catch (HibernateException he)
		{
			logger.error(MyLogImpl.MESSAGES.getString("message.error.cashing.save") + " : " + cashing.getDinnerTable().getNumber());
		}
		finally
		{
			try
			{
				HibernateUtil.closeSession();
			}
			catch (HibernateException he)
			{
			}
		}
	}

	public void saveOrUpdateVatTable(DinnerTable dinnerTable)
	{
		try
		{
			Session session = HibernateUtil.currentSession();
			Transaction tx = session.beginTransaction();

			Query q = session.createQuery("FROM ValueAddedTax");
			List vatList = q.list();
			ValueAddedTax vat = null;
			VatTable vatTable = null;
			for(int i=0; i<vatList.size(); i++)
			{
			    vat = (ValueAddedTax)vatList.get(i);
			    Criteria criteria = session.createCriteria(VatTable.class);
			    criteria.add(Expression.eq("dinnerTable.id", dinnerTable.getId()));
			    criteria.add(Expression.eq("vat.id", vat.getId()));
			    vatTable = (VatTable)criteria.uniqueResult();
			    
			    if(vatTable==null)
			    {
			        vatTable = new VatTable();
			        vatTable.setDinnerTable(dinnerTable);
			        vatTable.setVat(vat);
			    }    
			    q = session.getNamedQuery("OrderLine.SumAmountByVat");
			    q.setLong("vatId", vat.getId().longValue());
			    q.setLong("tableId", dinnerTable.getId().longValue());
			    float vatTableAmountValue = 0;
			    try
			    {
			        vatTableAmountValue = ((Float)(q.iterate()).next()).floatValue();
			    }
			    catch(Exception e)
			    {
			    }
			    if(vatByTakeaway)
			    {
			        vatTableAmountValue = dinnerTable.getAmountPay();
			        if(!(dinnerTable.isTakeaway() && vat.getValue()==5.5f) && !(!dinnerTable.isTakeaway() && vat.getValue()==19.6f))
			        {
					    vatTableAmountValue = 0;
			        }
			        vatTable.setAmount(vatTableAmountValue);
				    vatTableAmountValue *= vat.getValue()/(100+vat.getValue());
				    vatTable.setValue(vatTableAmountValue);
			    }
			    else
			    {
				    vatTableAmountValue *= dinnerTable.getAmountPay()/dinnerTable.getAmountsSum();
			        vatTable.setAmount(vatTableAmountValue);
				    vatTableAmountValue *= vat.getValue()/(100+vat.getValue());
				    vatTable.setValue(vatTableAmountValue);
			    }
			    session.saveOrUpdate(vatTable);
			}
			
			tx.commit();
			HibernateUtil.closeSession();
		}
		catch (HibernateException he)
		{
			logger.error(MyLogImpl.MESSAGES.getString("message.error.update.vattable"));
		}
		catch (Exception e)
		{
			logger.error(MyLogImpl.MESSAGES.getString("message.error.update.vattable") );
		}
		finally
		{
			try
			{
				HibernateUtil.closeSession();
			}
			catch (HibernateException he)
			{
			}
		}
	}
    public boolean isVatByTakeaway()
    {
        return vatByTakeaway;
    }
    
    public void setVatByTakeaway(boolean vatByTakeaway)
    {
        this.vatByTakeaway = vatByTakeaway;
    }
}
