package fr.montagnesdor.restaurant.services.menus.spi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import net.sf.hibernate.Criteria;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.ObjectNotFoundException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;
import net.sf.hibernate.Transaction;
import net.sf.hibernate.expression.Expression;
import net.sf.hibernate.expression.Order;
import net.sf.hibernate.type.StringType;
import fr.montagnesdor.restaurant.model.hibernate.Category;
import fr.montagnesdor.restaurant.model.hibernate.CategoryLanguage;
import fr.montagnesdor.restaurant.model.hibernate.CategoryLanguageID;
import fr.montagnesdor.restaurant.model.hibernate.CategoryRelation;
import fr.montagnesdor.restaurant.model.hibernate.HibernateUtil;
import fr.montagnesdor.restaurant.model.hibernate.Locale;
import fr.montagnesdor.restaurant.model.hibernate.Product;
import fr.montagnesdor.restaurant.model.hibernate.ProductLanguage;
import fr.montagnesdor.restaurant.model.hibernate.ProductLanguageID;
import fr.montagnesdor.restaurant.model.hibernate.StatsConsumptionProduct;
import fr.montagnesdor.restaurant.model.hibernate.ValueAddedTax;
import fr.montagnesdor.restaurant.services.menus.MenusManager;
import fr.montagnesdor.util.log.MyLog;
import fr.montagnesdor.util.log.MyLogFactoryImpl;
import fr.montagnesdor.util.log.MyLogImpl;

public class DefaultMenusManager implements MenusManager
{
	private static MenusManager instance = null;
	private static MyLog logger = MyLogFactoryImpl.getInstance().getLogger(DefaultMenusManager.class.getName());

	public DefaultMenusManager()
	{
	}

	public static MenusManager getInstance()
	{
		if (instance == null)
			synchronized (DefaultMenusManager.class)
			{
				if (instance == null)
					instance = new DefaultMenusManager();
			}

		return instance;
	}

	public List getProducts(int pageSize, int pageNumber, String sortListBy, String sortMonotony, StringBuffer productsSum)
	{
		List result = null;
		try
		{
			Session session = HibernateUtil.currentSession();
			
			try
			{
			    productsSum.append(((Integer)session.iterate(session.getNamedQuery("Product.count").getQueryString()).next()).intValue());
			}
			catch(Exception e)
			{
			    
			}
			Criteria criteria = session.createCriteria(Product.class);
		    criteria.add(Expression.eq("this.productSpecialCode.id", new Long(1)));
			if(sortMonotony.equals("asc"))
				criteria.addOrder(Order.asc(sortListBy));
			else
				criteria.addOrder(Order.desc(sortListBy));
			criteria.setMaxResults(pageSize);
			criteria.setFirstResult(pageSize*pageNumber);
			result = criteria.list();
			
			HibernateUtil.closeSession();
		}
		catch (HibernateException he)
		{
			logger.error(MyLogImpl.MESSAGES.getString("message.error.products"));
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
			
			result = session.find("FROM ValueAddedTax");
			
			HibernateUtil.closeSession();
		}
		catch (HibernateException he)
		{
			logger.error(MyLogImpl.MESSAGES.getString("message.error.products"));
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

	public List searchProducts(String searchedField, String searchedValue, String sortMonotony, String language)
	{
		List result = null;
		try
		{
			Session session = HibernateUtil.currentSession();
			
			Criteria criteria = null;
			if(searchedField.equals("label"))
			{    
			    criteria = session.createCriteria(ProductLanguage.class);
			    criteria.add(Expression.eq("this.id.locale.id", language));
			}
			else
			{    
			    criteria = session.createCriteria(Product.class);
			    criteria.add(Expression.eq("this.productSpecialCode.id", new Long(1)));
			}
			if(searchedField.equals("price"))
			{
			    try
			    {
			        new Float(searchedValue);
			    }
			    catch(NumberFormatException nfe)
			    {
			        searchedValue = "0";
			    }
			    criteria.add(Expression.eq("this."+searchedField, new Float(searchedValue)));
			}    
			else    
			    criteria.add(Expression.like("this."+searchedField, "%"+searchedValue+"%"));
			if(sortMonotony.equals("asc"))
				criteria.addOrder(Order.asc(searchedField));
			else
				criteria.addOrder(Order.desc(searchedField));
			result = criteria.list();
			
			HibernateUtil.closeSession();
		}
		catch (HibernateException he)
		{
			logger.error(MyLogImpl.MESSAGES.getString("message.error.searching.products"));
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
	
	public Product getProduct(String code)
	{
	    Product result = null;
		try
		{
			Session session = HibernateUtil.currentSession();
			
			Criteria criteria = session.createCriteria(Product.class);
			criteria.add(Expression.eq("id", code));
			result = (Product)criteria.uniqueResult();
			
			HibernateUtil.closeSession();
		}
		catch (HibernateException he)
		{
			logger.error(MyLogImpl.MESSAGES.getString("message.error.product")+" : "+code);
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

	public List getProductsByCategory(String categoryId)
	{
		List result = null;
		try
		{
			Session session = HibernateUtil.currentSession();
			
			Criteria criteria = session.createCriteria(CategoryRelation.class);
			criteria.add(Expression.eq("category.id", new Long(categoryId)));
			criteria.addOrder(Order.asc("category.id"));
			result = criteria.list();
			
			HibernateUtil.closeSession();
		}
		catch (HibernateException he)
		{
			logger.error(MyLogImpl.MESSAGES.getString("message.error.categories.relation")+" : " + categoryId);
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
	
	public List getCategoriesRelation(String code)
	{
		List result = null;
		try
		{
			Session session = HibernateUtil.currentSession();
			
			Criteria criteria = session.createCriteria(CategoryRelation.class);
			criteria.add(Expression.eq("product.id", code));
			criteria.addOrder(Order.asc("category.id"));
			result = criteria.list();
			
			HibernateUtil.closeSession();
		}
		catch (HibernateException he)
		{
			logger.error(MyLogImpl.MESSAGES.getString("message.error.categories.relation")+" : " + code);
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

	public List getCategories()
	{
		List result = null;
		try
		{
			Session session = HibernateUtil.currentSession();
			
			Criteria criteria = session.createCriteria(Category.class);
			criteria.addOrder(Order.asc("id"));
			result = criteria.list();
			
			HibernateUtil.closeSession();
		}
		catch (HibernateException he)
		{
			logger.error(MyLogImpl.MESSAGES.getString("message.error.categories"));
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
	
	public void saveOrUpdateProduct(Product product, List categoriesRelationCheckedList, Long vatId, String action)
	{
		try
		{
			Session session = HibernateUtil.currentSession();
			Transaction tx = session.beginTransaction();
			
			ProductLanguage productLanguage = new ProductLanguage();
			ProductLanguageID productLanguageID = new ProductLanguageID();
			Locale locale=  new Locale();
			locale.setId(product.getCurrentLanguage());
			productLanguageID.setLocale(locale);
			productLanguageID.setProduct(product);
			productLanguage.setId(productLanguageID);
			productLanguage.setLabel(product.getCurrentLabel());

			ValueAddedTax valueAddedTax = null;
			try
			{
			    valueAddedTax = (ValueAddedTax)session.load(ValueAddedTax.class, vatId);
			}
			catch(ObjectNotFoundException onfe)
			{
			    //Envoyer une exception
			}
			
			if(valueAddedTax!=null)
			    product.setVat(valueAddedTax);

			if(action!=null && action.toUpperCase().equals("SAVE"))
			{    
			    session.save(product);
				session.save(productLanguage);
			}    
			if(action!=null && action.toUpperCase().equals("UPDATE"))
			{    
			    session.update(product);
			    if(product.getLabels().containsKey(product.getCurrentLanguage()))
			        session.update(productLanguage);
			    else
			        session.save(productLanguage);
			}
			
			session.delete(session.getNamedQuery("CategoryRelation.DeleteByProductId").getQueryString(), product.getId(), new StringType());
			session.flush();
			CategoryRelation categoryRelation = null;
			for(int i=0; i<categoriesRelationCheckedList.size(); i++)
			{
			    categoryRelation = (CategoryRelation)categoriesRelationCheckedList.get(i);
			    if(categoryRelation.getProduct()==null)
			        categoryRelation.setProduct(product);
			    session.save(categoryRelation);
			}
			
			tx.commit();
			HibernateUtil.closeSession();
		}
		catch (HibernateException he)
		{
			logger.error(MyLogImpl.MESSAGES.getString("message.error.save.product"));
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

	public void deleteProduct(Product product) throws DeleteProductException
	{
		try
		{
			Session session = HibernateUtil.currentSession();
			Transaction tx = session.beginTransaction();
			
			Query q = session.getNamedQuery("CategoryRelation.CountByProductId");
			q.setString("code", product.getId());
			int count = ((Integer)q.iterate().next()).intValue();
			
			if(count==0)
			{
			    q = session.getNamedQuery("ProductLanguage.DeleteByProductId");
			    session.delete(q.getQueryString(), product.getId(), new StringType());
			    session.flush();
			    q = session.getNamedQuery("Product.DeleteByProductId");
			    session.delete(q.getQueryString(), product.getId(), new StringType());
			}
			else
			    throw new DeleteProductException("saveOrUpdateProduct.jsp.error.delete.categories");
			
			tx.commit();
			HibernateUtil.closeSession();
		}
		catch (HibernateException he)
		{
			logger.error(MyLogImpl.MESSAGES.getString("message.error.delete.product"));
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

	public void saveOrUpdateCategory(Category category, String action)
	{
		try
		{
			Session session = HibernateUtil.currentSession();
			Transaction tx = session.beginTransaction();
			
			CategoryLanguage categoryLanguage = new CategoryLanguage();
			CategoryLanguageID categoryLanguageID = new CategoryLanguageID();
			Locale locale=  new Locale();
			locale.setId(category.getCurrentLanguage());
			categoryLanguageID.setLocale(locale);
			categoryLanguageID.setCategory(category);
			categoryLanguage.setId(categoryLanguageID);
			categoryLanguage.setLabel(category.getCurrentLabel());

			if(action!=null && action.toUpperCase().equals("SAVE"))
			{    
			    session.save(category);
				session.save(categoryLanguage);
			}    
			if(action!=null && action.toUpperCase().equals("UPDATE"))
			{    
			    session.update(category);
			    if(category.getLabels().containsKey(category.getCurrentLanguage()))
			        session.update(categoryLanguage);
			    else
			        session.save(categoryLanguage);
			}
			
			tx.commit();
			HibernateUtil.closeSession();
		}
		catch (HibernateException he)
		{
			logger.error(MyLogImpl.MESSAGES.getString("message.error.save.category"));
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

	public void deleteCategory(Category category)
	{
		try
		{
			Session session = HibernateUtil.currentSession();
			Transaction tx = session.beginTransaction();
			
//			Query q = session.getNamedQuery("Category.DeleteByCategoryId");
//			session.delete(q.getQueryString(), category.getId(), new LongType());
			session.delete(category);
			
			tx.commit();
			HibernateUtil.closeSession();
		}
		catch (HibernateException he)
		{
			logger.error(MyLogImpl.MESSAGES.getString("message.error.delete.category"));
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
	
	public Category getCategory(Long id)
	{
	    Category result = null;
		try
		{
			Session session = HibernateUtil.currentSession();
			
			Criteria criteria = session.createCriteria(Category.class);
			criteria.add(Expression.eq("id", id));
			result = (Category)criteria.uniqueResult();
			
			HibernateUtil.closeSession();
		}
		catch (HibernateException he)
		{
			logger.error(MyLogImpl.MESSAGES.getString("message.error.category")+" : "+id);
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

	public String[] getStatsConsumptionProductsMinYearMaxMonthMaxYear()
	{
	    String[] result = new String[3];
	    
	    Connection connection = null;
	    PreparedStatement prepareStatement = null;
	    ResultSet resultSet = null;
		try
		{
			Session session = HibernateUtil.currentSession();
			connection = session.connection();
			prepareStatement = connection.prepareStatement("SELECT min(scp_updated_year) AS minYear, max(scp_updated_year) AS maxYear from t_stats_consumption_product");
			resultSet = prepareStatement.executeQuery();
			if(resultSet.next())
			{
			    result[0] = resultSet.getString("minYear");
			    result[2] = resultSet.getString("maxYear");
			}
			prepareStatement = connection.prepareStatement("SELECT max(scp_updated_month) AS maxMonth from t_stats_consumption_product WHERE scp_updated_year = "+result[2]);
			resultSet = prepareStatement.executeQuery();
			if(resultSet.next())
			{
			    result[1] = resultSet.getString("maxMonth");
			}
		}
		catch (Exception e)
		{
		    logger.error(MyLogImpl.MESSAGES.getString("message.error.consumption.products.mim.max"));
		}
		finally
		{
//			try{resultSet.close();}catch (Exception e){}
//			try{prepareStatement.close();}catch (Exception e){}
//			try{connection.close();}catch (Exception e){}
			try{HibernateUtil.closeSession();}catch (HibernateException he){}
		}
	    return result;
	}
	
	public List getStatsConsumptionProducts(int pageSize, int pageNumber, String sortListBy, String sortMonotony, String[] statsConsumptionProductsSum)
	{
		List result = null;
		try
		{
			Session session = HibernateUtil.currentSession();
			
			try
			{
			    statsConsumptionProductsSum[0]=""+(((Integer)session.iterate("SELECT COUNT(*) FROM StatsConsumptionProduct").next()).intValue());
			    statsConsumptionProductsSum[1]=""+(((Float)session.iterate("SELECT SUM(statsConsumptionProduct.quantity) FROM StatsConsumptionProduct AS statsConsumptionProduct").next()).floatValue());
			    statsConsumptionProductsSum[2]=""+(((Float)session.iterate("SELECT SUM(product.price) FROM StatsConsumptionProduct AS statsConsumptionProduct, Product AS product WHERE statsConsumptionProduct.product.id=product.id").next()).floatValue());

			}
			catch(Exception e)
			{
			    
			}
			Criteria criteria = session.createCriteria(StatsConsumptionProduct.class);
			if(sortListBy.equals("code"))
			{
			    sortListBy = "product.id";
			}
			if(sortMonotony.equals("asc"))
				criteria.addOrder(Order.asc(sortListBy));
			else
				criteria.addOrder(Order.desc(sortListBy));
			criteria.setMaxResults(pageSize);
			criteria.setFirstResult(pageSize*pageNumber);
			result = criteria.list();
			fillProductsInStatsConsumptionProductsList(session, result, true);
			
			HibernateUtil.closeSession();
		}
		catch (HibernateException he)
		{
		    logger.error(MyLogImpl.MESSAGES.getString("message.error.consumption.products"));
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

	public List getStatsConsumptionProducts(Short day, Short month, Short year, String sortMonotony)
	{
	    return getStatsConsumptionProducts(null, null, day, month, year, sortMonotony);
	}
	
	public List getStatsConsumptionProducts(String code, Float quantity, Short day, Short month, Short year, String sortMonotony)
	{
	    return getStatsConsumptionProducts(code, quantity, day, month, year, sortMonotony, null);
	}
	
	public List getStatsConsumptionProducts(String code, Float quantity, Short day, Short month, Short year, String sortMonotony, String quantityHavingOperator)
	{
	    return getStatsConsumptionProducts(code, quantity, day, month, year, sortMonotony, quantityHavingOperator, null);
	}

	public List getStatsConsumptionProducts(String code, Float quantity, Short day, Short month, Short year, String sortMonotony, String quantityHavingOperator, Boolean isExceptProduct)
	{
	    return getStatsConsumptionProducts(code, quantity, day, month, year, sortMonotony, quantityHavingOperator, isExceptProduct, null);
	}

	public List getStatsConsumptionProducts(String code, Float quantity, Short day, Short month, Short year, String sortMonotony, String quantityHavingOperator, Boolean isExceptProduct, Long categoryId)
	{
		List result = null;
		try
		{
			Session session = HibernateUtil.currentSession();
			
			StringBuffer query = new StringBuffer("SELECT sum(statsConsumptionProduct.quantity), statsConsumptionProduct.product FROM StatsConsumptionProduct AS statsConsumptionProduct");
			if(categoryId!=null)
			    query.append(", CategoryRelation AS categoryRelation");
			if(code!=null || quantity!=null || day!=null || month!=null || year!=null || isExceptProduct!=null || categoryId!=null)
			{
			    boolean isAND = false;
			    query.append(" WHERE");
			    
			    if(isExceptProduct!=null && isExceptProduct.booleanValue())
			    {
				    query.append(" statsConsumptionProduct.product.id NOT IN (SELECT product.id FROM Product AS product)");
				    isAND = true;
			    }
				if(code!=null && !code.equals(""))
				{
				    if(isAND)
				        query.append(" AND");
				    else
				        isAND = true;
				    query.append(" statsConsumptionProduct.product.id LIKE '").append(code).append("%'");
				}    
				if(quantity!=null)
				{
				    if(quantityHavingOperator==null)
				    {
					    if(isAND)
					        query.append(" AND");
					    else
					        isAND = true;
					    query.append(" statsConsumptionProduct.quantity=").append(quantity);
				    }
				}    
				if(day!=null)
				{
				    if(isAND)
				        query.append(" AND");
				    else
				        isAND = true;
				    query.append(" statsConsumptionProduct.updatedDay=").append(day);
				}    
				if(month!=null)
				{
				    if(isAND)
				        query.append(" AND");
				    else
				        isAND = true;
				    query.append(" statsConsumptionProduct.updatedMonth=").append(month);
				}    
				if(year!=null)
				{
				    if(isAND)
				        query.append(" AND");
				    else
				        isAND = true;
				    query.append(" statsConsumptionProduct.updatedYear=").append(year);
				}
				if(categoryId!=null)
				{
				    if(isAND)
				        query.append(" AND");
				    else
				        isAND = true;
				    query.append(" categoryRelation.product.id=statsConsumptionProduct.product.id");
				    query.append(" AND categoryRelation.category.id=").append(categoryId);
				}
			}
			//query.append(" GROUP BY statsConsumptionProduct.product ORDER BY statsConsumptionProduct.product.id");
			query.append(" GROUP BY statsConsumptionProduct.product");
			if(quantityHavingOperator!=null && quantity!=null)
			    query.append(" HAVING sum(statsConsumptionProduct.quantity)").append(quantityHavingOperator).append(quantity.toString());
		    if(sortMonotony==null || (!sortMonotony.toUpperCase().equals("DESC") && !sortMonotony.toUpperCase().equals("ASC")))
		        sortMonotony = "DESC";
			query.append(" ORDER BY sum(statsConsumptionProduct.quantity) "+sortMonotony);
			Query q = session.createQuery(query.toString());
			//Query q = session.createQuery("SELECT sum(statsConsumptionProduct.quantity), statsConsumptionProduct.product FROM StatsConsumptionProduct AS statsConsumptionProduct WHERE statsConsumptionProduct.product.id NOT IN (SELECT product.id FROM Product AS product) GROUP BY statsConsumptionProduct.product ORDER BY sum(statsConsumptionProduct.quantity) ASC");			
			result = q.list();
			result = fillProductsInStatsConsumptionProductsList(session, result, true);
			
			HibernateUtil.closeSession();
		}
		catch (HibernateException he)
		{
		    logger.error(MyLogImpl.MESSAGES.getString("message.error.consumption.products"));
		    logger.error(he.toString());
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

	public List getProductsNotConsumed(Short day, Short month, Short year)
	{
		List result = null;
		try
		{
			Session session = HibernateUtil.currentSession();
			
			StringBuffer query = new StringBuffer("FROM Product AS product WHERE product.id NOT IN (SELECT statsConsumptionProduct.product.id FROM StatsConsumptionProduct AS statsConsumptionProduct "); 
			if(day!=null || month!=null || year!=null)
			{
			    boolean isAND = false;
			    query.append(" WHERE");
			    
				if(day!=null)
				{
				    if(isAND)
				        query.append(" AND");
				    else
				        isAND = true;
				    query.append(" statsConsumptionProduct.updatedDay=").append(day);
				}    
				if(month!=null)
				{
				    if(isAND)
				        query.append(" AND");
				    else
				        isAND = true;
				    query.append(" statsConsumptionProduct.updatedMonth=").append(month);
				}    
				if(year!=null)
				{
				    if(isAND)
				        query.append(" AND");
				    else
				        isAND = true;
				    query.append(" statsConsumptionProduct.updatedYear=").append(year);
				}    
			}
			query.append(")");
			query.append(" AND product.productSpecialCode.id=1");
			query.append(" ORDER BY product.id");
			Query q = session.createQuery(query.toString());
			result = q.list();
			result = fillProductsInStatsConsumptionProductsList(session, result, false);
			
			HibernateUtil.closeSession();
		}
		catch (HibernateException he)
		{
		    logger.error(MyLogImpl.MESSAGES.getString("message.error.consumption.products"));
		    logger.error(he.toString());
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

	private List fillProductsInStatsConsumptionProductsList(Session session, List dataList, boolean isQuantityExist) throws HibernateException
	{
	    List result = new ArrayList(dataList.size());
		if(dataList!=null)
		{
		    StatsConsumptionProduct statsConsumptionProduct = null;
		    Product product = null;
		    for(int i=0; i<dataList.size(); i++)
		    {
		        statsConsumptionProduct = new StatsConsumptionProduct();
		        if(isQuantityExist)
		        {
			        Object[] objectArray =  (Object[])dataList.get(i);
		            statsConsumptionProduct.setQuantity(((Float)objectArray[0]).floatValue());
		            product = (Product)objectArray[1];
		        }
		        else
		            product = (Product)dataList.get(i);
		        try
		        {
		            statsConsumptionProduct.setProduct((Product)session.load(Product.class, product.getId()));
		        }
		        catch(ObjectNotFoundException onfe)
		        {
		            statsConsumptionProduct.setProduct(product);
		        }
		        result.add(statsConsumptionProduct);
		    }
		}
		return result;
	}
	
	public List searchStatsConsumptionProducts(String searchedField, String searchedValue, String sortMonotony, String language)
	{
		List result = null;
		try
		{
			Session session = HibernateUtil.currentSession();
			if(searchedField.equals("label"))
			{
			    Query q = session.getNamedQuery("StatsConsumptionProduct.SelectByProductLanguage");
				q.setString("language", language);
				q.setString("label", "%"+searchedValue+"%");
				result = q.list();
			}
			else
			{
				Criteria criteria = session.createCriteria(StatsConsumptionProduct.class);
				
				if(searchedField.equals("code"))
				{
				    searchedField = "product.id";
				    criteria.add(Expression.eq("this."+searchedField, searchedValue));
				}    
				else if(searchedField.equals("quantity"))   
				    criteria.add(Expression.eq("this."+searchedField, new Float(searchedValue)));
				else if(searchedField.equals("updateDate"))
				{
				    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
				    try
				    {
				        criteria.add(Expression.eq("this."+searchedField, simpleDateFormat.parse(searchedValue)));
				    }
				    catch(ParseException pe)
				    {
				        
				    }
				}    
				if(sortMonotony.equals("asc"))
					criteria.addOrder(Order.asc(searchedField));
				else
					criteria.addOrder(Order.desc(searchedField));
				
				result = criteria.list();
				fillProductsInStatsConsumptionProductsList(session, result, true);
			}
		
			HibernateUtil.closeSession();
		}
		catch (HibernateException he)
		{
			logger.error(MyLogImpl.MESSAGES.getString("message.error.consumption.products.search"));
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

	public List getStatsConsumptionProductsByCategory(String categoryId)
	{
		List result = null;
		try
		{
			Session session = HibernateUtil.currentSession();
			
			Query q = session.getNamedQuery("StatsConsumptionProduct.SelectByCategoryId");
			q.setLong("id", Long.parseLong(categoryId));
			result = q.list();
			fillProductsInStatsConsumptionProductsList(session, result, true);

			HibernateUtil.closeSession();
		}
		catch (HibernateException he)
		{
			logger.error(MyLogImpl.MESSAGES.getString("message.error.consumption.products.category")+" : " + categoryId);
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

	public void purgeStatsConsumptionProducts(String code, Float quantity, Short day, Short month, Short year)
	{
		try
		{
			Session session = HibernateUtil.currentSession();
			Transaction tx = session.beginTransaction();
			
			StringBuffer query = new StringBuffer("FROM StatsConsumptionProduct AS statsConsumptionProduct");
			if(code!=null || quantity!=null || day!=null || month!=null || year!=null)
			{
			    boolean isAND = false;
			    query.append(" WHERE");
				if(code!=null)
				{
				    query.append(" statsConsumptionProduct.product.id=").append(code);
				    isAND = true;
				}    
				if(day!=null)
				{
				    if(isAND)
				        query.append(" AND");
				    else
				        isAND = true;
				    query.append(" statsConsumptionProduct.updatedDay=").append(day);
				}    
				if(month!=null)
				{
				    if(isAND)
				        query.append(" AND");
				    else
				        isAND = true;
				    query.append(" statsConsumptionProduct.updatedMonth=").append(month);
				}    
				if(year!=null)
				{
				    if(isAND)
				        query.append(" AND");
				    else
				        isAND = true;
				    query.append(" statsConsumptionProduct.updatedYear=").append(year);
				}    
			}
			if(code!=null && quantity!=null && quantity.floatValue()!=0 && day!=null && month!=null && year!=null)
			{
			    Query q = session.createQuery(query.toString());
			    StatsConsumptionProduct statsConsumptionProduct = (StatsConsumptionProduct)q.uniqueResult();
			    if(statsConsumptionProduct!=null)
			    {
			        statsConsumptionProduct.setQuantity(quantity.floatValue());
			        session.update(statsConsumptionProduct);
			    }
			}    
			else    
			    session.delete(query.toString());
			session.flush();
/*
 			Calendar calendar = Calendar.getInstance();
			
			Criteria criteria = session.createCriteria(Product.class);
		    criteria.add(Expression.eq("this.productSpecialCode.id", new Long(1)));
			List productsList = criteria.list();
			StatsConsumptionProduct statsConsumptionProducts = null;	
			for(int i=0; i<productsList.size(); i++)
			{
			    statsConsumptionProducts = new StatsConsumptionProduct();
			    statsConsumptionProducts.setProduct((Product)productsList.get(i));
			    
			    statsConsumptionProducts.setUpdatedDay(new Short((short)calendar.get(Calendar.DAY_OF_MONTH)));
			    statsConsumptionProducts.setUpdatedMonth(new Short((short)calendar.get(Calendar.MONTH)));
			    statsConsumptionProducts.setUpdatedYear(new Short((short)calendar.get(Calendar.YEAR)));
			    session.saveOrUpdate(statsConsumptionProducts);
			}
*/			
			tx.commit();
			HibernateUtil.closeSession();
		}
		catch (HibernateException he)
		{
			logger.error(MyLogImpl.MESSAGES.getString("message.error.purge.consumption.product"));
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

}
