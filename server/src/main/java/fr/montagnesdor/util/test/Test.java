/*
 * Project				montagnesdor
 * File name		Test.java
 * Created on		7 nov. 2004
 * @author			Mathieu MA sous conrad
 * @version		1.0
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package fr.montagnesdor.util.test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.hibernate.Criteria;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;
import net.sf.hibernate.Transaction;
import net.sf.hibernate.expression.Expression;

import fr.montagnesdor.restaurant.model.hibernate.Category;
import fr.montagnesdor.restaurant.model.hibernate.CategoryRelation;
import fr.montagnesdor.restaurant.model.hibernate.DinnerTable;
import fr.montagnesdor.restaurant.model.hibernate.HibernateUtil;
import fr.montagnesdor.restaurant.model.hibernate.Locale;
import fr.montagnesdor.restaurant.model.hibernate.Product;
import fr.montagnesdor.restaurant.model.hibernate.ProductLanguage;
import fr.montagnesdor.restaurant.model.hibernate.ProductLanguageID;
import fr.montagnesdor.restaurant.model.hibernate.ProductPart;
import fr.montagnesdor.restaurant.model.hibernate.ProductSpecialCode;
import fr.montagnesdor.restaurant.model.hibernate.StatsConsumptionProduct;
import fr.montagnesdor.restaurant.model.hibernate.ValueAddedTax;
import fr.montagnesdor.restaurant.services.menus.MenusManagerFactory;
import fr.montagnesdor.restaurant.services.orders.OrdersManagerFactory;
import fr.montagnesdor.restaurant.services.receipts.ReceiptsManagerFactory;
import fr.montagnesdor.util.log.MyLogImpl;

public class Test
{
	public static void main(String[] args) throws Exception
	{

		int maxTestTime = 1;
		long testTime1 = 0;
		long testTime2 = 0;
		long testTime3 = 0;
		long testTime4 = 0;
		long start = 0;
		long end = 0;
		ProductLanguage productLanguage = null;
		String codeProduit = null;
		Session session = null;
		Transaction tx = null;
		Product product = null;
		Locale locale = null;
		ProductLanguageID productLanguageID = null;
		ProductSpecialCode productSpecialCode = null;
		ValueAddedTax vat = null;


		Date cashingDate = null;
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 15);
		cal.set(Calendar.MONTH, 2);
		cal.set(Calendar.YEAR, 2005);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		start = System.currentTimeMillis();
		List l1 ;
		ReceiptsManagerFactory.getManager().getDayRevenuesYearList();

		
		product = new Product();
		product.setId("01");
		product.setColorRGB("001122");
		product.setPrice(11);
		product.setCurrentLabel("POP");
		product.setCurrentLanguage("fr");
		ValueAddedTax valueAddedTax = new ValueAddedTax();
		valueAddedTax.setId(new Long(1));
		product.setVat(valueAddedTax);
		String[] t = new String[3];
		DinnerTable dinnerTable = OrdersManagerFactory.getManager().getDinnerTableById(new Long(3268)); 
		
		
//		List result = ReceiptsManagerFactory.getManager().getStatsConsumptionProducts(10,0,"id", "asc", t);
//		System.out.println(t[0]+":"+t[1]+":"+t[2]);
//		System.out.println(result.size());
/*		
		result = ReceiptsManagerFactory.getManager().searchStatsConsumptionProducts("code", "2", "asc", "fr");
		System.out.println(result);
		result = ReceiptsManagerFactory.getManager().searchStatsConsumptionProducts("quantity", "2", "asc", "fr");
		System.out.println(result);
		result = ReceiptsManagerFactory.getManager().searchStatsConsumptionProducts("updateDate", "30/01/2005", "asc", "fr");
		System.out.println(result);
*/		
		
		System.out.println(dinnerTable.getVats().get("1"));
		end = System.currentTimeMillis();
		System.out.println(" Temps écoulé en seconde sur le test : " + (end - start));

/*		
		productLanguage = OrdersManagerFactory.getManager().getProduct("11", "fr");
		
		start = System.currentTimeMillis();
		product = ReceiptsManagerFactory.getManager().getProduct("11");
		List categoriesList = ReceiptsManagerFactory.getManager().getCategories();
		List categoriesRelationList = ReceiptsManagerFactory.getManager().getCategoriesRelation("11");
		List list = new ArrayList(categoriesList.size());
		CategoryRelation categoryRelation = null;
		Category category = null;
		for(int i=0; i<categoriesList.size(); i++)
		{
		    category = (Category)categoriesList.get(i);
		    int j=0;
			for(; j<categoriesRelationList.size(); j++)
			{
			    categoryRelation = (CategoryRelation)categoriesRelationList.get(j);

			    if(category.getId().longValue()==categoryRelation.getCategory().getId().longValue())
			        break;
			    
			}
			if(j==categoriesRelationList.size())
			{
			    categoryRelation = new CategoryRelation();
			    categoryRelation.setCategory(category);
			    list.add(i, categoryRelation);
			}
			else
			    list.add(i, categoryRelation);
		}
		end = System.currentTimeMillis();
		System.out.println("Temps écoulé en seconde sur le test : " + (end - start));
		for(int i=0; i< list.size(); i++)
		{
		    categoryRelation = (CategoryRelation)list.get(i);
		    
		    System.out.println(i+":"+categoryRelation.getId()+":"+categoryRelation.getCategory());
		}
		
		
/*
		start = System.currentTimeMillis();
		codeProduit = "11";
		productLanguage = OrdersManagerFactory.getManager().getProduct(codeProduit, "fr");
		System.out.println(productLanguage.getLabel());
		end = System.currentTimeMillis();
		System.out.println("Temps écoulé en seconde sur le test : " + (end - start));		

		testTime1 = 0;
		for(int i=0; i<maxTestTime; i++)
		{		
			start = System.currentTimeMillis();
			codeProduit = "11";
			productLanguage = OrdersManagerFactory.getManager().getProduct(codeProduit, "fr");
			if(productLanguage!=null)
				System.out.println(productLanguage.getLabel());
			else	
				System.out.println("NULL");
			end = System.currentTimeMillis();
			testTime1 += (end - start);
			System.out.println("Temps écoulé en seconde sur e test : " + (end - start));		
		}

		testTime2 = 0;
		for(int i=0; i<maxTestTime; i++)
		{		
			start = System.currentTimeMillis();
			session = HibernateUtil.currentSession();
			tx = session.beginTransaction();
			codeProduit = "11";
			product = (Product)session.load(Product.class, codeProduit);
			locale = (Locale)session.load(Locale.class, "fr");
			productLanguageID = new ProductLanguageID(); 
			productLanguageID.setLocale(locale);
			productLanguageID.setProduct(product);
			productLanguage = (ProductLanguage)session.load(ProductLanguage.class, productLanguageID);
			tx.commit();
			HibernateUtil.closeSession();
			System.out.println(productLanguage.getLabel());
			end = System.currentTimeMillis();
			testTime2 += (end - start);
			System.out.println("Temps écoulé en seconde sur e test : " + (end - start));		
		}
		
		testTime3 = 0;
		for(int i=0; i<maxTestTime; i++)
		{		
			start = System.currentTimeMillis();
			session = HibernateUtil.currentSession();
			Connection connection = session.connection();
			connection.setAutoCommit(false);
			codeProduit = "11";
			Statement stmt = connection.createStatement();
			// Execute the query
			ResultSet rs = stmt.executeQuery( "SELECT  t_value_added_tax.vat_id, t_value_added_tax.vat_value, t_product_special_code.psc_id, t_product_special_code.psc_code,  t_product.pdt_price, t_product_language.pdl_label FROM t_value_added_tax, t_product_language, t_product, t_locale, t_product_special_code WHERE t_product_language.loc_id='fr' AND t_product_language.pdt_id='"+codeProduit+"' AND t_product_language.pdt_id = t_product.pdt_id AND t_product_language.loc_id = t_locale.loc_id AND t_product_special_code.psc_id= t_product.psc_id AND t_value_added_tax.vat_id = t_product.vat_id") ;
			productSpecialCode = new ProductSpecialCode();
			vat = new ValueAddedTax();
			product = new Product();
			product.setId(codeProduit);
			locale = new Locale();
			locale.setId("fr");
			productLanguageID = new ProductLanguageID();
			productLanguage = new ProductLanguage();
			// Loop through the result set
			while( rs.next() )
			{
				vat.setId(new Long(rs.getLong("vat_id")));
				vat.setValue(rs.getFloat("vat_value"));
				productSpecialCode.setId(new Long(rs.getLong("psc_id")));
				productSpecialCode.setCode(rs.getString("psc_code"));
				product.setPrice(rs.getFloat("pdt_price"));
				productLanguage.setLabel(rs.getString("pdl_label"));			
			}
			productLanguageID.setLocale(locale);
			product.setProductSpecialCode(productSpecialCode);
			product.setVat(vat);
			productLanguageID.setProduct(product);
			productLanguage.setId(productLanguageID);

			connection.commit();
	
			rs.close();
			stmt.close();
			connection.close();
			HibernateUtil.closeSession();
			System.out.println(productLanguage.getLabel());
			end = System.currentTimeMillis();
			testTime3 += (end - start);
			System.out.println("Temps écoulé en seconde sur e test : " + (end - start));		
		}
		
		System.out.println("Moyenne 1 : " + testTime1/maxTestTime);
		System.out.println("Moyenne 2 : " + testTime2/maxTestTime);
		System.out.println("Moyenne 3 : " + testTime3/maxTestTime);
String orderLineId = "21140";
String quantity = "1.0";
String code = "11";
String label = "allo123";
String price = "1.50";
String amount = "1.5";

//Long l = new Long(orderLineId);
System.out.println(orderLineId);


		DinnerTable dinnerTable = null;
		testTime2 = 0;
			
			dinnerTable = OrdersManagerFactory.getManager().getDinnerTable("5");
		start = System.currentTimeMillis();			
			OrdersManagerFactory.getManager().deleteDinnerTable(dinnerTable);
			end = System.currentTimeMillis();
			testTime2 += (end - start);
			System.out.println("Temps écoulé en seconde sur e test : " + (end - start));		

		System.out.println("Moyenne 2 : " + testTime2/maxTestTime);

		testTime4 = 0;
		ProductLanguage1 productLanguage1 = null;
		ProductLanguageID1 productLanguageID1 = null;
		for(int i=0; i<maxTestTime; i++)
		{		
			start = System.currentTimeMillis();
			session = HibernateUtil.currentSession();
			tx = session.beginTransaction();
			codeProduit = "11";
			productLanguageID1 = new ProductLanguageID1();
			productLanguageID1.setLocaleID("fr");
			productLanguageID1.setProductID(codeProduit);
			productLanguage1 = (ProductLanguage1)session.load(ProductLanguage1.class, productLanguageID1);
			tx.commit();
			HibernateUtil.closeSession();
			System.out.println(productLanguage1.getLabel());
			end = System.currentTimeMillis();
			testTime4 += (end - start);
			System.out.println("Temps écoulé en seconde sur e test : " + (end - start));		
		}


		testTime2 = 0;
		for(int i=0; i<maxTestTime+1; i++)
		{		
			start = System.currentTimeMillis();
			session = HibernateUtil.currentSession();
			tx = session.beginTransaction();
			vat = (ValueAddedTax)session.load(ValueAddedTax.class, new Long(1));
			tx.commit();
			HibernateUtil.closeSession();
			System.out.println(vat.getValue());
			end = System.currentTimeMillis();
			testTime2 += (end - start);
			System.out.println("Temps écoulé en seconde sur e test : " + (end - start));		
		}

		testTime3 = 0;
		for(int i=0; i<maxTestTime; i++)
		{		
			start = System.currentTimeMillis();
			session = HibernateUtil.currentSession();
			Connection connection = session.connection();
			connection.setAutoCommit(false);
			Statement stmt = connection.createStatement();
			// Execute the query
			ResultSet rs = stmt.executeQuery( "SELECT  t_value_added_tax.vat_id, t_value_added_tax.vat_value FROM t_value_added_tax WHERE t_value_added_tax.vat_id =1") ;
			vat = new ValueAddedTax();
			// Loop through the result set
			while( rs.next() )
			{
				vat.setId(new Long(rs.getLong("vat_id")));
				vat.setValue(rs.getFloat("vat_value"));
			}

			connection.commit();
	
			rs.close();
			stmt.close();
			connection.close();
			HibernateUtil.closeSession();
			System.out.println(vat.getValue());
			end = System.currentTimeMillis();
			testTime3 += (end - start);
			System.out.println("Temps écoulé en seconde sur e test : " + (end - start));		
		}

		System.out.println("Moyenne 2 : " + testTime2/maxTestTime);
		System.out.println("Moyenne 3 : " + testTime3/maxTestTime);

/*
		testTime3 = 0;
		ProductLanguage productLanguage3 = null;
		Product product3 = null;
		Locale locale3 = null;
		ProductLanguageID productLanguageID3 = null;
		ProductSpecialCode productSpecialCode3 = null;
		ValueAddedTax vat3 = null;
		codeProduit = "11";
		start = System.currentTimeMillis();
		session = HibernateUtil.currentSession();
		Connection connection = session.connection();
		connection.setAutoCommit(false);
		Statement stmt = connection.createStatement();
		// Execute the query
		ResultSet rs = stmt.executeQuery( "SELECT  t_value_added_tax.vat_id, t_value_added_tax.vat_value, t_product_special_code.psc_id, t_product_special_code.psc_code,  t_product.pdt_price, t_product_language.pdl_label FROM t_value_added_tax, t_product_language, t_product, t_locale, t_product_special_code WHERE t_product_language.loc_id='fr' AND t_product_language.pdt_id='"+codeProduit+"' AND t_product_language.pdt_id = t_product.pdt_id AND t_product_language.loc_id = t_locale.loc_id AND t_product_special_code.psc_id= t_product.psc_id AND t_value_added_tax.vat_id = t_product.vat_id") ;
		productSpecialCode3 = new ProductSpecialCode();
		vat3 = new ValueAddedTax();
		product3 = new Product();
		product3.setId(codeProduit);
		locale3 = new Locale();
		locale3.setId("fr");
		productLanguageID3 = new ProductLanguageID();
		productLanguage3 = new ProductLanguage();
		// Loop through the result set
		while( rs.next() )
		{
			vat3.setId(new Long(rs.getLong("vat_id")));
			vat3.setValue(rs.getFloat("vat_value"));
			productSpecialCode3.setId(new Long(rs.getLong("psc_id")));
			productSpecialCode3.setCode(rs.getString("psc_code"));
			product3.setPrice(rs.getFloat("pdt_price"));
			productLanguage3.setLabel(rs.getString("pdl_label"));			
		}
		productLanguageID3.setLocale(locale);
		product3.setProductSpecialCode(productSpecialCode);
		product3.setVat(vat);
		productLanguageID3.setProduct(product);
		productLanguage3.setId(productLanguageID);

		connection.commit();
		HibernateUtil.closeSession();
		System.out.println(productLanguage3.getLabel());
		end = System.currentTimeMillis();
		testTime3 += (end - start);
		System.out.println("Temps écoulé en seconde sur e test : " + (end - start));
		if(
			productLanguage.getLabel().equals(productLanguage3.getLabel()) &&
			productLanguage.getId().getLocale().getId().equals(productLanguage3.getId().getLocale().getId()) &&
			productLanguage.getId().getProduct().getId().equals(productLanguage3.getId().getProduct().getId()) &&
			productLanguage.getId().getProduct().getPrice() == productLanguage3.getId().getProduct().getPrice() &&
			productLanguage.getId().getProduct().getProductSpecialCode().getId().equals(productLanguage3.getId().getProduct().getProductSpecialCode().getId()) &&
			productLanguage.getId().getProduct().getProductSpecialCode().getCode().equals(productLanguage3.getId().getProduct().getProductSpecialCode().getCode()) &&
			productLanguage.getId().getProduct().getVat().getId().equals(productLanguage3.getId().getProduct().getVat().getId()) &&
			productLanguage.getId().getProduct().getVat().getValue() == productLanguage3.getId().getProduct().getVat().getValue()  
		)
		{
			System.out.println("True");		
		}
		else
			System.out.println("False");

*/
		cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 25);
		cal.set(Calendar.MONTH, 2);
		cal.set(Calendar.YEAR, 2003);
		List l = ReceiptsManagerFactory.getManager().getCashingTables(cal.getTime(), "cashing.dinnerTable.number", "asc");
		System.out.print(l.size());
	}
	
	public static void purgeStatsConsumptionProducts()
	{
		try
		{
			Session session = HibernateUtil.currentSession();
			Transaction tx = session.beginTransaction();
			
			session.delete("FROM StatsConsumptionProduct");
			session.flush();
			
			Criteria criteria = session.createCriteria(Product.class);
		    criteria.add(Expression.eq("this.productSpecialCode.id", new Long(1)));
			List productsList = criteria.list();
			StatsConsumptionProduct statsConsumptionProducts = null;
			for(short year=0; year<1; year++)
			{    
				for(short month=0; month<12; month++)
				{
					for(short day=0; day<31; day++)
					{
				    
						for(int i=0; i<productsList.size(); i++)
						{
						    statsConsumptionProducts = new StatsConsumptionProduct();
						    statsConsumptionProducts.setProduct((Product)productsList.get(i));
						    //statsConsumptionProducts.setUpdateDate(new Date());
						    statsConsumptionProducts.setUpdatedDay(new Short(day));
						    statsConsumptionProducts.setUpdatedMonth(new Short(month));
						    statsConsumptionProducts.setUpdatedYear(new Short((short)year));
						    
						    session.saveOrUpdate(statsConsumptionProducts);
						}
						session.flush();
					}	
				}	
			}
			tx.commit();
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
	}

}