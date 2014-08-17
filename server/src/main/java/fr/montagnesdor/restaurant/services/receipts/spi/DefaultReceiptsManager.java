package fr.montagnesdor.restaurant.services.receipts.spi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import net.sf.hibernate.Criteria;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;
import net.sf.hibernate.Transaction;
import net.sf.hibernate.expression.Expression;
import net.sf.hibernate.type.LongType;
import fr.montagnesdor.restaurant.model.hibernate.Cashing;
import fr.montagnesdor.restaurant.model.hibernate.DayRevenue;
import fr.montagnesdor.restaurant.model.hibernate.DinnerTable;
import fr.montagnesdor.restaurant.model.hibernate.HibernateUtil;
import fr.montagnesdor.restaurant.model.hibernate.OrderLine;
import fr.montagnesdor.restaurant.model.hibernate.StatsConsumptionProduct;
import fr.montagnesdor.restaurant.model.hibernate.ValueAddedTax;
import fr.montagnesdor.restaurant.model.hibernate.VatRevenue;
import fr.montagnesdor.restaurant.services.receipts.ReceiptsManager;
import fr.montagnesdor.util.log.MyLog;
import fr.montagnesdor.util.log.MyLogFactoryImpl;
import fr.montagnesdor.util.log.MyLogImpl;

public class DefaultReceiptsManager implements ReceiptsManager
{
	private static ReceiptsManager instance = null;
	private static MyLog logger = MyLogFactoryImpl.getInstance().getLogger(DefaultReceiptsManager.class.getName());

	public DefaultReceiptsManager()
	{
	}

	public static ReceiptsManager getInstance()
	{
		if (instance == null)
			synchronized (DefaultReceiptsManager.class)
			{
				if (instance == null)
					instance = new DefaultReceiptsManager();
			}

		return instance;
	}

	public List getCashingTables(Date date, String sortListBy, String sortMonotony)
	{
		List result = null;

		try
		{
			Session session = HibernateUtil.currentSession();
			Transaction tx = session.beginTransaction();
/*			
			Criteria criteria = session.createCriteria(Cashing.class);
			criteria.createAlias("dinnerTable", "dinnerTable");
			criteria.add(Expression.eq("dinnerTable.cashingDate", date));
			if(sortMonotony!=null && sortMonotony.toUpperCase().equals("ASC"))
				criteria.addOrder(Order.asc(sortListBy));
			else
				criteria.addOrder(Order.desc(sortListBy));
			result = criteria.list(); 
*/
			String pattern  = "yyyy-MM-dd";
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			Query q = session.createQuery("FROM Cashing AS cashing WHERE TO_CHAR(cashing.dinnerTable.registrationDate, '"+pattern+"')=:registrationDate ORDER BY "+sortListBy+ " "+sortMonotony);
			if(date!=null)
			    q.setString("registrationDate", sdf.format(date));
			else
			    q.setString("registrationDate", null);
			result = q.list();
			tx.commit();
			HibernateUtil.closeSession();
		}
		catch (HibernateException he)
		{
			logger.error(MyLogImpl.MESSAGES.getString("message.error.cashing.tables")+" "+he);
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

	public List getVats()
	{
		List result = null;

		try
		{
			Session session = HibernateUtil.currentSession();
			result = session.find("FROM ValueAddedTax AS valueAddedTax ORDER BY valueAddedTax.id ASC");
			HibernateUtil.closeSession();
		}
		catch (HibernateException he)
		{
			logger.error(MyLogImpl.MESSAGES.getString("message.error.vats")+" "+he);
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
	

	public List getCashingDates()
	{
		List result = null;

		try
		{
			Session session = HibernateUtil.currentSession();
			Query q = session.createQuery("SELECT DISTINCT dinnerTable.cashingDate FROM DinnerTable AS dinnerTable WHERE dinnerTable.cashingDate IS NOT NULL ORDER BY dinnerTable.cashingDate ASC");
			result = q.list();
		}	
		catch (Exception e)
		{
			logger.error(MyLogImpl.MESSAGES.getString("message.error.cashing.dates"));
		}
		finally
		{
			try{HibernateUtil.closeSession();}catch (Exception he){}
		}

		return result;
	}

	public Date getMaxRevenueDate()
	{
		Date result = null;
		try
		{
			Session session = HibernateUtil.currentSession();
			Query q = session.createQuery("SELECT MAX(dayRevenue.revenueDate) FROM DayRevenue AS dayRevenue WHERE dayRevenue.closingDate IS NOT NULL");
			result = (Date)q.iterate().next();
		}	
		catch (Exception e)
		{
			logger.error(MyLogImpl.MESSAGES.getString("message.error.max.revenue.date"));
		}
		finally
		{
			try{HibernateUtil.closeSession();}catch (Exception he){}
		}

		return result;
	}
	
	public Cashing getCashing(Long id)
	{
	    Cashing result = null;
	    try
		{
			Session session = HibernateUtil.currentSession();
			
			result = (Cashing)session.load(Cashing.class, id);
			
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

	public void deleteCashing(Long id)
	{
		try
		{
			Session session = HibernateUtil.currentSession();
			Transaction tx = session.beginTransaction();

			Criteria criteria = null;
			StatsConsumptionProduct statsConsumptionProduct = null;
			OrderLine orderLine = null;
			Cashing cashing = (Cashing)session.load(Cashing.class, id);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(cashing.getDinnerTable().getCashingDate());
			Short day = new Short((short)calendar.get(Calendar.DAY_OF_MONTH)); 
			Short month = new Short((short)calendar.get(Calendar.MONTH));
			Short year = new Short((short)calendar.get(Calendar.YEAR));
			if(cashing!=null)
			{
			    DinnerTable dinnerTable = cashing.getDinnerTable();
				Query q = session.getNamedQuery("OrderLine.deleteByDinnerTable");
				if(dinnerTable.getOrders()!=null && dinnerTable.getOrders().size()>0)
				{
				    //Update StatsConsumptionProduct
				    for(Iterator i = dinnerTable.getOrders().iterator(); i.hasNext();)
				    {    
				        orderLine = (OrderLine)i.next();
				        criteria = session.createCriteria(StatsConsumptionProduct.class).add(Expression.eq("updatedDay", day)).add(Expression.eq("updatedMonth", month)).add(Expression.eq("updatedYear", year));
				        criteria.add(Expression.eq("product", orderLine.getProduct()));
				        statsConsumptionProduct = (StatsConsumptionProduct)criteria.uniqueResult();
				        if(statsConsumptionProduct!=null)
				        {    
					        statsConsumptionProduct.setQuantity(statsConsumptionProduct.getQuantity()-orderLine.getQuantity());
					        if(statsConsumptionProduct.getQuantity()>0)
					            session.saveOrUpdate(statsConsumptionProduct);
					        else
					            session.delete(statsConsumptionProduct);
				        }
				    }    
					
					session.delete(q.getQueryString(), dinnerTable.getId(), new LongType());
				}
				
				q = session.getNamedQuery("VatTable.deleteByDinnerTable");
				session.delete(q.getQueryString(), dinnerTable.getId(), new LongType());

			    session.delete(cashing);
				
				q = session.getNamedQuery("DinnerTable.DeleteById");
				session.delete(q.getQueryString(), dinnerTable.getId(), new LongType());
			}
			tx.commit();
			HibernateUtil.closeSession();
		}
		catch (HibernateException he)
		{
			logger.error(MyLogImpl.MESSAGES.getString("message.error.delete.cashing")+" "+he);
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

	public void saveOrUpdateDayRevenue(Date revenueDate)
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try
		{
		    Session session = HibernateUtil.currentSession();
			Transaction tx = session.beginTransaction();

			Query q = null;
			Criteria criteria = null;
			DayRevenue dayRevenueByCriteria = null;
			VatRevenue vatRevenueByCriteria = null; 
			Date closingDate = new Date();
			boolean isTakeaway = true;
		    for(int i=0; i<2; i++)
		    {
		        if(i==1)
		            isTakeaway = false;
				String query = "SELECT SUM(cashing.cash), SUM(cashing.ticket), SUM(cashing.cheque), SUM(cashing.card), SUM(cashing.online), SUM(cashing.unpaid), SUM(dinnerTable.amountPay) FROM DinnerTable AS dinnerTable, Cashing AS cashing WHERE dinnerTable.id = cashing.dinnerTable.id AND dinnerTable.cashingDate = :revenueDate AND dinnerTable.takeaway = :takeaway";
				q = session.createQuery(query);
			    q.setDate("revenueDate", revenueDate);
		        q.setBoolean("takeaway", isTakeaway);
			    DayRevenue dayRevenue = new DayRevenue();
				Iterator iterator = q.iterate();
				Object[] objects = (Object[])iterator.next();
				try{dayRevenue.setCash(((Float)objects[0]).floatValue());}catch(Exception e){}
				try{dayRevenue.setTicket(((Float)objects[1]).floatValue());}catch(Exception e){}
				try{dayRevenue.setCheque(((Float)objects[2]).floatValue());}catch(Exception e){}
				try{dayRevenue.setCard(((Float)objects[3]).floatValue());}catch(Exception e){}
				try{dayRevenue.setOnline(((Float)objects[4]).floatValue());}catch(Exception e){}
				try{dayRevenue.setUnpaid(((Float)objects[5]).floatValue());}catch(Exception e){}
				try{dayRevenue.setAmount(((Float)objects[6]).floatValue());}catch(Exception e){}
				dayRevenue.setRevenueDate(revenueDate);
				dayRevenue.setTakeaway(isTakeaway);
				criteria = session.createCriteria(DayRevenue.class).add(Expression.eq("takeaway", new Boolean(isTakeaway))).add(Expression.eq("revenueDate", revenueDate)); 
				dayRevenueByCriteria = (DayRevenue)criteria.uniqueResult();
				if(dayRevenueByCriteria!=null)
				{
				    dayRevenueByCriteria.setCash(dayRevenue.getCash()+dayRevenueByCriteria.getCash());
				    dayRevenueByCriteria.setTicket(dayRevenue.getTicket()+dayRevenueByCriteria.getTicket());
				    dayRevenueByCriteria.setCheque(dayRevenue.getCheque()+dayRevenueByCriteria.getCheque());
				    dayRevenueByCriteria.setCard(dayRevenue.getCard()+dayRevenueByCriteria.getCard());
				    dayRevenueByCriteria.setOnline(dayRevenue.getOnline()+dayRevenueByCriteria.getOnline());
				    dayRevenueByCriteria.setUnpaid(dayRevenue.getUnpaid()+dayRevenueByCriteria.getUnpaid());
				    dayRevenueByCriteria.setAmount(dayRevenue.getAmount()+dayRevenueByCriteria.getAmount());
				}
				else
				    dayRevenueByCriteria = dayRevenue;
				dayRevenueByCriteria.setClosingDate(closingDate);
				session.saveOrUpdate(dayRevenueByCriteria);
				
				query = "SELECT SUM(vatTable.amount), SUM(vatTable.value), vatTable.vat.id FROM VatTable AS vatTable, DinnerTable AS dinnerTable WHERE dinnerTable.cashingDate = :revenueDate AND dinnerTable.takeaway = :takeaway AND dinnerTable.id = vatTable.dinnerTable.id GROUP BY vatTable.vat.id";
				q = session.createQuery(query);
			    q.setDate("revenueDate", revenueDate);
		        q.setBoolean("takeaway", isTakeaway);
		        VatRevenue vatRevenue = null;
		        iterator = q.iterate();
		        if(!iterator.hasNext())
		        {
			        //Maybe useless
					List vats = session.find("FROM ValueAddedTax");
					for(int j=0; j<vats.size(); j++)
					{
					    vatRevenue = new VatRevenue();
					    vatRevenue.setDayRevenue(dayRevenueByCriteria);
					    vatRevenue.setVat((ValueAddedTax)vats.get(j));
						criteria = session.createCriteria(VatRevenue.class).add(Expression.eq("dayRevenue", dayRevenueByCriteria)).add(Expression.eq("vat", (ValueAddedTax)vats.get(j))); 
						vatRevenueByCriteria = (VatRevenue)criteria.uniqueResult();
						if(vatRevenueByCriteria==null)
						    vatRevenueByCriteria = vatRevenue;
			            session.saveOrUpdate(vatRevenueByCriteria);
					}    
		        }
		        for(; iterator.hasNext();)
		        {
		            vatRevenue = new VatRevenue();
		            vatRevenue.setDayRevenue(dayRevenueByCriteria);		            
		            objects = (Object[])iterator.next();
					try{vatRevenue.setAmount(((Float)objects[0]).floatValue());}catch(Exception e){}
					try{vatRevenue.setValue(((Float)objects[1]).floatValue());}catch(Exception e){}
					Long vatId = (Long)objects[2];
					ValueAddedTax vat = (ValueAddedTax)session.load(ValueAddedTax.class, vatId);
		            vatRevenue.setVat(vat);
					criteria = session.createCriteria(VatRevenue.class).add(Expression.eq("dayRevenue", dayRevenueByCriteria)).add(Expression.eq("vat", vat)); 
					vatRevenueByCriteria = (VatRevenue)criteria.uniqueResult();
					if(vatRevenueByCriteria!=null)
					{
					    vatRevenueByCriteria.setAmount(vatRevenue.getAmount()+vatRevenueByCriteria.getAmount());
					    vatRevenueByCriteria.setValue(vatRevenue.getValue()+vatRevenueByCriteria.getValue());
					}
					else
					    vatRevenueByCriteria = vatRevenue;
		            session.saveOrUpdate(vatRevenueByCriteria);
		        }    
		    }
			//Suppression de toutes les lignes de commandes, les tables et les encaissements datés du revenueDate 
			connection = session.connection();
			java.sql.Date revenueDateSQL = new java.sql.Date(revenueDate.getTime()); 
			//Suppressions les lignes de commandes
			q = session.getNamedQuery("SQL.OrderLine.DeleteByCashingDate");
			preparedStatement = connection.prepareStatement(q.getQueryString());
			preparedStatement.setDate(1, revenueDateSQL);
			preparedStatement.executeUpdate();
			//Suppressions les TVAs dépendant des tables
			q = session.getNamedQuery("SQL.VatTable.DeleteByCashingDate");
			preparedStatement = connection.prepareStatement(q.getQueryString());
			preparedStatement.setDate(1, revenueDateSQL);
			preparedStatement.executeUpdate();
			//Suppressions les encaissements
			q = session.getNamedQuery("SQL.Cashing.DeleteByCashingDate");
			preparedStatement = connection.prepareStatement(q.getQueryString());
			preparedStatement.setDate(1, revenueDateSQL);
			preparedStatement.executeUpdate();
			//Suppressions les tables
			q = session.getNamedQuery("SQL.TableDinner.DeleteByCashingDate");
			preparedStatement = connection.prepareStatement(q.getQueryString());
			preparedStatement.setDate(1, revenueDateSQL);
			preparedStatement.executeUpdate();
			
			tx.commit();
			HibernateUtil.closeSession();
		}
		catch (HibernateException he)
		{
			logger.error(MyLogImpl.MESSAGES.getString("message.error.save.day.revenue")+" "+he);
		}
		catch (SQLException sqle)
		{
			logger.error(MyLogImpl.MESSAGES.getString("message.error.save.day.revenue")+" "+sqle);
		}
		finally
		{
//			try{preparedStatement.close();}catch (Exception e){}
//			try{connection.close();}catch (Exception e){}
			try{HibernateUtil.closeSession();}catch (Exception e){}
		}
	}

	public void insertInitDayRevenue(Date cashingDate)
	{
		try
		{
		    Session session = HibernateUtil.currentSession();
			Transaction tx = session.beginTransaction();

			boolean isTakeaway = true;
			Date printDate = new Date();
		    for(int i=0; i<2; i++)
		    {
		        if(i==1)
		            isTakeaway = false;
			    DayRevenue dayRevenue = new DayRevenue();
				dayRevenue.setRevenueDate(cashingDate);
				dayRevenue.setTakeaway(isTakeaway);
				dayRevenue.setPrintDate(printDate);
				session.saveOrUpdate(dayRevenue);
		    }			
			tx.commit();
			HibernateUtil.closeSession();
		}
		catch (HibernateException he)
		{
			logger.error(MyLogImpl.MESSAGES.getString("message.error.save.day.revenue")+" "+he);
		}
		finally
		{
			try{HibernateUtil.closeSession();}catch (Exception e){}
		}
	}

	public void saveOrUpdateCashing(Cashing cashing)
	{
		try
		{
		    Session session = HibernateUtil.currentSession();
			Transaction tx = session.beginTransaction();

			session.saveOrUpdate(cashing);
			tx.commit();
			HibernateUtil.closeSession();
		}
		catch (HibernateException he)
		{
			logger.error(MyLogImpl.MESSAGES.getString("message.error.save.day.revenue")+" "+he);
		}
		finally
		{
			try{HibernateUtil.closeSession();}catch (Exception e){}
		}
	}

	public List getVatDaylyRevenues(Date cashingDate, Boolean isTakeaway)
	{
		List result = null;

		try
		{
			Session session = HibernateUtil.currentSession();
			List vats = session.find("FROM ValueAddedTax AS vat ORDER BY vat.id ASC");
			String query = "SELECT SUM(vatTable.amount), SUM(vatTable.value) FROM DinnerTable AS dinnerTable, VatTable AS vatTable WHERE dinnerTable.id = vatTable.dinnerTable.id AND dinnerTable.cashingDate = :cashingDate AND vatTable.vat.id = :vatId";
			if(isTakeaway!=null)
			    query += " AND dinnerTable.takeaway = :isTakeaway";
			Query q = session.createQuery(query);
		    q.setDate("cashingDate", cashingDate);
		    if(isTakeaway!=null)
		        q.setBoolean("isTakeaway", isTakeaway.booleanValue());
		    VatRevenue vatRevenue = null;
		    
		    result  = new ArrayList(vats.size());
			for(int i=0; i<vats.size(); i++)
			{
			    q.setLong("vatId", ((ValueAddedTax)vats.get(i)).getId().longValue());
			    vatRevenue = new VatRevenue();
			    vatRevenue.setVat((ValueAddedTax)vats.get(i));
			    Iterator iterator = q.iterate();
			    Object[] objects = (Object[])iterator.next();
			    try{vatRevenue.setAmount(((Float)objects[0]).floatValue());}catch(Exception e){}
			    try{vatRevenue.setValue(((Float)objects[1]).floatValue());}catch(Exception e){}
			    result.add(i, vatRevenue);
			}
		}	
		catch (Exception e)
		{
			logger.error(MyLogImpl.MESSAGES.getString("message.error.vat.revenue"));
		}
		finally
		{
			try{HibernateUtil.closeSession();}catch (Exception he){}
		}

		return result;
	}

	public List getVatMonthlyRevenues(Date revenueDate, Boolean isTakeaway)
	{
		String pattern  = "MM/yyyy";
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		List result = null;
		try
		{
			Session session = HibernateUtil.currentSession();
			List vats = session.find("FROM ValueAddedTax AS vat ORDER BY vat.id ASC");
			String query = "SELECT SUM(vatRevenue.amount), SUM(vatRevenue.value) FROM DayRevenue AS dayRevenue, VatRevenue AS vatRevenue WHERE dayRevenue.id = vatRevenue.dayRevenue.id AND TO_CHAR(dayRevenue.revenueDate, 'MM/yyyy') = :revenueDate AND vatRevenue.vat.id = :vatId AND dayRevenue.closingDate IS NOT NULL";
			if(isTakeaway!=null)
			    query += " AND dayRevenue.takeaway = :isTakeaway";
			Query q = session.createQuery(query);
			if(revenueDate!=null)
			    q.setString("revenueDate", sdf.format(revenueDate));
			else
			    q.setString("revenueDate", null);
		    if(isTakeaway!=null)
		        q.setBoolean("isTakeaway", isTakeaway.booleanValue());

		    VatRevenue vatRevenue = null;
		    result  = new ArrayList(vats.size());
			for(int i=0; i<vats.size(); i++)
			{
			    q.setLong("vatId", ((ValueAddedTax)vats.get(i)).getId().longValue());
			    vatRevenue = new VatRevenue();
			    vatRevenue.setVat((ValueAddedTax)vats.get(i));
			    Iterator iterator = q.iterate();
			    Object[] objects = (Object[])iterator.next();
			    try{vatRevenue.setAmount(((Float)objects[0]).floatValue());}catch(Exception e){}
			    try{vatRevenue.setValue(((Float)objects[1]).floatValue());}catch(Exception e){}
			    result.add(i, vatRevenue);
			}
		}	
		catch (Exception e)
		{
			logger.error(MyLogImpl.MESSAGES.getString("message.error.vat.revenue"));
		}
		finally
		{
			try{HibernateUtil.closeSession();}catch (Exception he){}
		}

		return result;
	}

	public List getDayRevenuesList(Date revenueDate, Boolean isTakeaway, String sortListBy, String sortMonotony)
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List result = null;
		try
		{
		    DayRevenue dayRevenue = null;
			String pattern  = "MM/yyyy";
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			Session session = HibernateUtil.currentSession();
			if(isTakeaway!=null)
			{
				Query q = session.createQuery("FROM DayRevenue AS dayRevenue WHERE TO_CHAR(dayRevenue.revenueDate, 'MM/yyyy') = :monthYear AND dayRevenue.takeaway = :isTakeaway AND dayRevenue.closingDate IS NOT NULL ORDER BY dayRevenue."+sortListBy+" "+sortMonotony);
			    q.setString("monthYear", sdf.format(revenueDate));
			    q.setBoolean("isTakeaway", isTakeaway.booleanValue());
			    result = q.list();
			}
			else
			{
			    Query qDayRevenue = session.getNamedQuery("SQL.DayRevenue.SelectAll");
			    connection = session.connection();
			    preparedStatement = connection.prepareStatement(qDayRevenue.getQueryString()+" ORDER BY "+sortListBy+" "+sortMonotony);
			    if(revenueDate!=null)
			        preparedStatement.setString(1, sdf.format(revenueDate));
			    else
			        preparedStatement.setString(1, null);
			    resultSet = preparedStatement.executeQuery(); 
			    result = new ArrayList(resultSet.getFetchSize());
				while(resultSet.next())
				{
				    dayRevenue = new DayRevenue();
				    try{dayRevenue.setRevenueDate(resultSet.getDate(1));}catch(Exception e){}				    
				    try{dayRevenue.setCash(resultSet.getFloat(2));}catch(Exception e){}
				    try{dayRevenue.setTicket(resultSet.getFloat(3));}catch(Exception e){}
				    try{dayRevenue.setCheque(resultSet.getFloat(4));}catch(Exception e){}
				    try{dayRevenue.setCard(resultSet.getFloat(5));}catch(Exception e){}
				    try{dayRevenue.setOnline(resultSet.getFloat(6));}catch(Exception e){}
				    try{dayRevenue.setUnpaid(resultSet.getFloat(7));}catch(Exception e){}
				    try{dayRevenue.setAmount(resultSet.getFloat(8));}catch(Exception e){}
				    result.add(dayRevenue);
				}
			}
			List vats = session.find("FROM ValueAddedTax AS vat ORDER BY vat.id ASC");
			String query = "SELECT SUM(vatRevenue.amount), SUM(vatRevenue.value) FROM DayRevenue AS dayRevenue, VatRevenue AS vatRevenue WHERE dayRevenue.revenueDate = :revenueDate AND vatRevenue.vat.id = :vatId AND vatRevenue.dayRevenue.id = dayRevenue.id";
			if(isTakeaway!=null)
			{
			    query = "SELECT SUM(vatRevenue.amount), SUM(vatRevenue.value) FROM DayRevenue AS dayRevenue, VatRevenue AS vatRevenue WHERE dayRevenue.revenueDate = :revenueDate AND vatRevenue.vat.id = :vatId AND vatRevenue.dayRevenue.id = dayRevenue.id AND dayRevenue.takeaway = :isTakeaway";
			}    
			Query qVatRevenue = session.createQuery(query);
		    List vatsList = null;
		    VatRevenue vatRevenue = null;
		    for(int i=0; i<result.size(); i++)
		    {
		        dayRevenue = (DayRevenue)result.get(i);
		        vatsList = new ArrayList(vats.size());
				for(int j=0; j<vats.size(); j++)
				{
					if(isTakeaway!=null)
					    qVatRevenue.setBoolean("isTakeaway", isTakeaway.booleanValue());
				    qVatRevenue.setDate("revenueDate", dayRevenue.getRevenueDate());					    
				    qVatRevenue.setLong("vatId", ((ValueAddedTax)vats.get(j)).getId().longValue());
				    vatRevenue = new VatRevenue();
				    vatRevenue.setVat((ValueAddedTax)vats.get(j));
				    Iterator iterator = qVatRevenue.iterate();
				    Object[] objects = (Object[])iterator.next();
				    try{vatRevenue.setAmount(((Float)objects[0]).floatValue());}catch(Exception e){}
				    try{vatRevenue.setValue(((Float)objects[1]).floatValue());}catch(Exception e){}
				    vatsList.add(vatRevenue);
				}
				dayRevenue.setVats(vatsList);
		    }	
		}	
		catch (Exception e)
		{
			logger.error(MyLogImpl.MESSAGES.getString("message.error.day.revenue"));
		}
		finally
		{
//			try{resultSet.close();}catch (Exception e){}		    
//			try{preparedStatement.close();}catch (Exception e){}
//			try{connection.close();}catch (Exception e){}
			try{HibernateUtil.closeSession();}catch (Exception he){}
		}

		return result;
	}

	public List getDayRevenuesYearList()
	{
		List result = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try
		{
		    Calendar calendar = Calendar.getInstance();
		    calendar.set(Calendar.DAY_OF_MONTH, 1);
		    calendar.set(Calendar.MONTH, 0);
			Session session = HibernateUtil.currentSession();
		    Query q = session.getNamedQuery("SQL.DayRevenue.SelectYear");
			connection = session.connection();
			preparedStatement = connection.prepareStatement(q.getQueryString());
			resultSet = preparedStatement.executeQuery();
			int year = calendar.get(Calendar.YEAR);
		    result = new ArrayList(resultSet.getFetchSize());
			while(resultSet.next())
			{
			    try
			    {
				    year = resultSet.getInt(1); 
			    }
			    catch(Exception e)
			    {
			    }
			    calendar.set(Calendar.YEAR, year);
			    result.add(calendar.getTime());    
			}
		}	
		catch (Exception e)
		{
			logger.error(MyLogImpl.MESSAGES.getString("message.error.day.revenue.year"));
		}
		finally
		{
//			try{resultSet.close();}catch (Exception he){}
//			try{preparedStatement.close();}catch (Exception he){}
//			try{connection.close();}catch (Exception he){}
			try{HibernateUtil.closeSession();}catch (Exception he){}
		}

		return result;
	}

	public boolean isCashingClosed(Date cashingDate)
	{
	    List dayRevenueList=null; 
	    try
		{
	        Session session = HibernateUtil.currentSession();
			
			Criteria criteria = session.createCriteria(DayRevenue.class).add(Expression.eq("revenueDate", cashingDate)).add(Expression.isNotNull("closingDate")); 
			dayRevenueList = criteria.list(); 
			
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
	    return dayRevenueList!=null && dayRevenueList.size()>0?true:false;
	}

	public boolean isCashingPrinted(Date cashingDate)
	{
	    List dayRevenueList=null; 
	    try
		{
	        Session session = HibernateUtil.currentSession();
			
			Criteria criteria = session.createCriteria(DayRevenue.class).add(Expression.eq("revenueDate", cashingDate)).add(Expression.isNotNull("printDate")); 
			dayRevenueList = criteria.list(); 
			
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
	    return dayRevenueList!=null && dayRevenueList.size()>0?true:false;
	}
}


