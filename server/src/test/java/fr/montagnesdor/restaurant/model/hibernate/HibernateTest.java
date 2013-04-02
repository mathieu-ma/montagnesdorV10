/*
 * Project				montagnesdor
 * File name		A.java
 * Created on		13 juin 2004
 * @author			Mathieu MA sous conrad
 * @version		1.0
 *
 *	Cette classe est une classe de test permettant de testant les objets générer par le framework Hibernate.<BR/>
 *	Voici la liste des objets à tester : <BR/>
 *	Locale <BR/>
 *	Cashing <BR/>
 *	DinnerTable <BR/>
 *	OrderPart <BR/>
 *	OrderPartLanguage <BR/>
 *	OrderLine <BR/>
 *	ValueAddedTax <BR/>
 *	Product <BR/>
 *	ProductSpecialCode <BR/>
 *	ProductLanguage <BR/>
 *	Category <BR/>
 *	CategoryRelation <BR/>
 *	TypeTable <BR/>
 *	TypeTableLanguage <BR/>
 *	DayRevenue <BR/>
 *	StatsConsumptionProduct <BR/>
 * <BR/>
 *	UserRole <BR/>
 *	UserRoleLanguage <BR/>
 *	UserAuthentication <BR/>
 *	User <BR/>
 * 
 */
package fr.montagnesdor.restaurant.model.hibernate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import junit.framework.AssertionFailedError;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestListener;
import junit.framework.TestResult;
import junit.framework.TestSuite;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.ObjectDeletedException;
import net.sf.hibernate.Session;
import net.sf.hibernate.Transaction;
import net.sf.hibernate.type.LongType;
import net.sf.hibernate.type.StringType;
import net.sf.hibernate.type.Type;

import org.apache.log4j.Logger;

public class HibernateTest extends TestCase 
//implements TestListener
{
	public final static int T_ALL = -1;
	public final static int T_LOCALE = 0;
	public final static int T_LOCALELANGUAGE = 1;
	public final static int T_CASHING = 2;
	public final static int T_DINNERTABLE = 3;
	public final static int T_PRODUCTPART = 4;
	public final static int T_PRODUCTPARTLANGUAGE = 5;
	public final static int T_ORDERLINE = 6;
	public final static int T_VALUEADDEDTAX = 7;
	public final static int T_PRODUCT = 8;
	public final static int T_PRODUCTSPECIALCODE = 9;
	public final static int T_PRODUCTLANGUAGE = 10;
	public final static int T_CATEGORY = 11;
	public final static int T_CATEGORYLANGUAGE = 12;
	public final static int T_CATEGORYRELATION = 13;
	public final static int T_TYPETABLE = 14;
	public final static int T_TYPETABLELANGUAGE = 15;
	public final static int T_DAYREVENUE = 16;
	public final static int T_STATSCONSUMPTIONPRODUCT = 17;
	public final static int T_USERROLE = 18;
	public final static int T_USERROLELANGUAGE = 19;
	public final static int T_USERAUTHENTICATION = 20;
	public final static int T_USERLOCALE = 21;	
	public final static int T_USER = 22;


	private static Logger log = Logger.getLogger(HibernateTest.class);
	private static TestResult testResult;

/*
	private Locale locale;
	private Cashing cashing;
	private DinnerTable dinnerTable;
	private OrderPart orderPart;
	private OrderPartLanguage orderPartLanguage;
	private OrderLine orderLine;
	private ValueAddedTax valueAddedTax;
	//private Product product;
	private ProductSpecialCode productSpecialCode;
	private ProductLanguage productLanguage;
	private Category category;
	private CategoryRelation categoryRelation;
	private TypeTable typeTable;
	private TypeTableLanguage typeTableLanguage;
	private DayRevenue dayRevenue;
	private StatsConsumptionProduct statsConsumptionProduct;

	private UserRole userRole;
	private UserRoleLanguage userRoleLanguage;
	private UserAuthentication userAuthentication;
	private User user;
*/

	public HibernateTest(String name)
	{
		super(name);
//		testResult = createResult();
//		testResult.addListener(this);
	}

	public void testDummy() {
		assertTrue(true);
	}
	
//	/** 
//	* Ce test efface toutes les données de toutes les tables. <BR/>
//	*/
//	public void testDeleteAll()
//	{
//		long start = System.currentTimeMillis();
//		delete(T_ALL);
//		long end = System.currentTimeMillis();
//		log.debug("Temps écoulé en seconde sur le test : " + (end - start) / 1000);
//	}
//
//	/** 
//	* Cette méthode efface en cascade toutes les données d'une table choisie. <BR/>
//	*/
//	public void delete(int table)
//	{
//		try
//		{
//			Session session = HibernateUtil.currentSession();
//			Transaction tx = session.beginTransaction();
//
//			delete(table, session);
//
//			tx.commit();
//			HibernateUtil.closeSession();
//		}
//		catch (HibernateException he)
//		{
//			he.printStackTrace();
//			throw new AssertionFailedError(he.getMessage());
//		}
//	}
//
//	/** 
//	* Cette méthode est utilisée par la méthode public void delete(int table) pour effacer en cascade toutes les données d'une table choisie. <BR/>
//	*/
//	private void delete(int table, Session session)
//	{
//		try
//		{
//			int res = 0;
//
//			res = session.delete("FROM DayRevenue");
//			log.debug("Nombre d'enregistrements supprimés dans la table t_day_revenue : " + res);
//			if (table == T_DAYREVENUE)
//				return;
//
//			res = session.delete("FROM TypeTableLanguage");
//			log.debug("Nombre d'enregistrements supprimés dans la table t_type_table_language : " + res);
//			if (table == T_TYPETABLELANGUAGE)
//				return;
//
//			res = session.delete("FROM TypeTable");
//			log.debug("Nombre d'enregistrements supprimés dans la table t_type_table : " + res);
//			if (table == T_TYPETABLE)
//				return;
//
//			res = session.delete("FROM Cashing");
//			log.debug("Nombre d'enregistrements supprimés dans la table t_cashing : " + res);
//			if (table == T_CASHING)
//				return;
//
//			//Flush intermédiaire
//			session.flush();
//						  
//			res = session.delete("FROM CategoryRelation");
//			log.debug("Nombre d'enregistrements supprimés dans la table t_category_relation : " + res);
//			if (table == T_CATEGORYRELATION)
//				return;
//
//			res = session.delete("FROM StatsConsumptionProduct");
//			log.debug("Nombre d'enregistrements supprimés dans la table t_stats_consumption_product : " + res);
//			if (table == T_STATSCONSUMPTIONPRODUCT)
//				return;
//
//			res = session.delete("FROM OrderLine");
//			log.debug("Nombre d'enregistrements supprimés dans la table t_order_line : " + res);
//			if (table == T_ORDERLINE)
//				return;
//
//			res = session.delete("FROM ProductLanguage");
//			log.debug("Nombre d'enregistrements supprimés dans la table t_product_language : " + res);
//			if (table == T_PRODUCTLANGUAGE)
//				return;
//
//			//Flush intermédiaire
//			session.flush();
//
//			res = session.delete("FROM DinnerTable");
//				log.debug("Nombre d'enregistrements supprimés dans la table t_dinner_table : " + res);
//				if (table == T_DINNERTABLE)
//					return;
//
//			res = session.delete("FROM Product");
//			log.debug("Nombre d'enregistrements supprimés dans la table t_product : " + res);
//			if (table == T_PRODUCT)
//				return;
//
//			res = session.delete("FROM ProductPartLanguage");
//			log.debug("Nombre d'enregistrements supprimés dans la table t_order_part_language : " + res);
//			if (table == T_PRODUCTPARTLANGUAGE)
//				return;
//
//			res = session.delete("FROM ProductPart");
//			log.debug("Nombre d'enregistrements supprimés dans la table t_order_part : " + res);
//			if (table == T_PRODUCTPART)
//				return;
//
//			//Flush intermédiaire
//			session.flush();
//
//			res = session.delete("FROM ProductSpecialCode");
//			log.debug("Nombre d'enregistrements supprimés dans la table t_product_special_code : " + res);
//			if (table == T_PRODUCTSPECIALCODE)
//				return;
//
//			res = session.delete("FROM ValueAddedTax");
//			log.debug("Nombre d'enregistrements supprimés dans la table t_value_added_tax : " + res);
//			if (table == T_VALUEADDEDTAX)
//				return;
//
//			res = session.delete("FROM CategoryLanguage");
//			log.debug("Nombre d'enregistrements supprimés dans la table t_category_language : " + res);
//			if (table == T_CATEGORYLANGUAGE)
//				return;
//
//			res = session.delete("FROM Category");
//			log.debug("Nombre d'enregistrements supprimés dans la table t_category : " + res);
//			if (table == T_CATEGORY)
//				return;
//
//			//Flush intermédiaire
//			session.flush();
//
//			res = session.delete("FROM UserLocale");
//			log.debug("Nombre d'enregistrements supprimés dans la table t_user_locale : " + res);
//			if (table == T_USERLOCALE)
//				return;
//
//			res = session.delete("FROM User");
//			log.debug("Nombre d'enregistrements supprimés dans la table t_user : " + res);
//			if (table == T_USER)
//				return;
//
//			res = session.delete("FROM UserAuthentication");
//			log.debug("Nombre d'enregistrements supprimés dans la table t_user_authentication : " + res);
//			if (table == T_USERAUTHENTICATION)
//				return;
//
//			res = session.delete("FROM UserRoleLanguage");
//			log.debug("Nombre d'enregistrements supprimés dans la table t_user_role_language : " + res);
//			if (table == T_USERROLELANGUAGE)
//				return;
//
//			//Flush intermédiaire
//			session.flush();
//
//			res = session.delete("FROM UserRole");
//			log.debug("Nombre d'enregistrements supprimés dans la table t_user_role : " + res);
//			if (table == T_USERROLE)
//				return;
//
//			res = session.delete("FROM LocaleLanguage");
//			log.debug("Nombre d'enregistrements supprimés dans la table t_locale_language : " + res);
//			if (table == T_LOCALELANGUAGE)
//				return;
//
//			res = session.delete("FROM Locale");
//			log.debug("Nombre d'enregistrements supprimés dans la table t_locale : " + res);
//			if (table == T_LOCALE)
//				return;
//
//		}
//		catch (HibernateException he)
//		{
//			he.printStackTrace();
//			throw new AssertionFailedError(he.getMessage());
//		}
//		finally
//		{
//			try
//			{
//				//Flush intermédiaire
//				session.flush();
//			}
//			catch (HibernateException he)
//			{
//				he.printStackTrace();
//				throw new AssertionFailedError(he.getMessage());
//			}
//		}
//	}
//
//	/** 
//	* Cette méthode teste la table t_day_revenue.
//	* Les actionsde ce test sont les suivantes : <BR/>
//	* Effacer toutes les données de la table t_day_revenue. <BR/>
//	* Insérer des données dans la table t_day_revenue. <BR/>
//	* S'assurer que les données ont bien été insérées dans la table t_day_revenue. <BR/>
//	* Effacer un objet par un identifiant qui n'existe pas. <BR/>
//	* Sauver un seul objet. <BR/>
//	* Récupérer un objet par son identifiant. <BR/>
//	* Effacer un seul objet. <BR/>
//	* Récupérer un objet par son identifiant qui n'existe pas. <BR/>
//	*/
//	public void testDayRevenue()
//	{
//		long start = System.currentTimeMillis();		
//		try
//		{
//			//Effacer toutes les tables de la base de données
//			testDeleteAll();
//			
//			Session session = HibernateUtil.currentSession();
//			Transaction tx = session.beginTransaction();
//
//			//Stocke la liste des recettes journalières
//			ArrayList insertList = new ArrayList();
//			//Stocke la liste des TVA 
//			ArrayList insertListA = new ArrayList();
//			//Stocke la liste des types de tables 
//			ArrayList insertListB = new ArrayList();
//			//Nombre de type de table 	
//			int lengthTtb = 50;
//			for (int i = 0; i < lengthTtb; i++)
//			{
//				TypeTable typeTable = new TypeTable();
//				typeTable.setId((Long)session.save(typeTable));				
//				insertListB.add(typeTable);
//			}
//
//				for (int j = 0; j < lengthTtb; j++)
//				{
//					DayRevenue dayRevenue = new DayRevenue();
//					dayRevenue.setTakeaway(true);
//					dayRevenue.setAmount(Math.round((float)Math.random()));
//					dayRevenue.setCard(Math.round((float)Math.random()));
//					dayRevenue.setCash(Math.round((float)Math.random()));
//					dayRevenue.setCheque(Math.round((float)Math.random()));
//					dayRevenue.setTicket(Math.round((float)Math.random()));
//					dayRevenue.setUnpaid(Math.round((float)Math.random()));
//					dayRevenue.setClosingDate(new Date());
//					dayRevenue.setPrintDate(new Date());
//					dayRevenue.setRevenueDate(new Date());
//					dayRevenue.setId((Long)session.save(dayRevenue));
//					insertList.add(dayRevenue);
//				}				
//
//			//Nombre de ligne de types de tables suivant la langue
//			int length = insertList.size();
//			//Récupérer la liste précédement insérée dans la table t_day_revenue
//			List listObj = session.find("FROM DayRevenue");
//
//			//Test l'égalité des longueurs des 2 listes
//			assertEquals(length, listObj.size());
//			
//			//Test l'égalité des 2 listes
//			DayRevenue insertObj = null;
//			for (int i = 0; i < length; i++)
//			{
//				int j = 0;
//				DayRevenue obj = null;
//				insertObj = (DayRevenue)insertList.get(i);
//				for (; j < length; j++)
//				{
//					obj = (DayRevenue) listObj.get(j);
//					
//					if (	insertObj.getId().longValue()==obj.getId().longValue()	
//						)
//						break;
//				}
//				if (j == length)
//					assertFalse("La liste insérée n'est pas identique à liste récupérée", true);
//			}
//			
//			Long newObjectId = new Long(length+1);
//			//Effacer un objet par un identifiant qui n'existe pas
//			int res = session.delete("FROM DayRevenue as dayRevenue WHERE dayRevenue.id =? ", newObjectId, new LongType());
//			assertEquals(res, 0);
//
//			DayRevenue newObject = new DayRevenue();
//			newObject.setId(newObjectId);
//			newObject.setTakeaway(true);
//			newObject.setAmount(Math.round((float)Math.random()));
//			newObject.setCard(Math.round((float)Math.random()));
//			newObject.setCash(Math.round((float)Math.random()));
//			newObject.setCheque(Math.round((float)Math.random()));
//			newObject.setTicket(Math.round((float)Math.random()));
//			newObject.setUnpaid(Math.round((float)Math.random()));
//			newObject.setClosingDate(new Date());
//			newObject.setPrintDate(new Date());
//			newObject.setRevenueDate(new Date());
//			//Sauver un seul objet
//			session.save(newObject);
//
//			//Récupérer un objet par son identifiant
//			DayRevenue loadObj = (DayRevenue)session.load(DayRevenue.class, newObject.getId());
//			assertEquals(newObject.getId(), loadObj.getId());
//			assertEquals(newObject.getClosingDate(), loadObj.getClosingDate());
//			assertEquals(newObject.getPrintDate(), loadObj.getPrintDate());
//			assertEquals(newObject.getRevenueDate(), loadObj.getRevenueDate());
//			assertEquals(new Float(newObject.getAmount()), new Float(loadObj.getAmount()));
//			assertEquals(new Float(newObject.getCard()), new Float(loadObj.getCard()));
//			assertEquals(new Float(newObject.getCash()), new Float(loadObj.getCash()));
//			assertEquals(new Float(newObject.getCheque()), new Float(loadObj.getCheque()));
//			assertEquals(new Float(newObject.getTicket()), new Float(loadObj.getTicket()));
//			assertEquals(new Float(newObject.getUnpaid()), new Float(loadObj.getUnpaid()));
//			assertEquals(newObject.isTakeaway(), loadObj.isTakeaway());
//						
//			//Effacer un seul objet 
//			session.delete(newObject);
//
//			//Récupérer un objet par son identifiant qui n'existe pas
//			ObjectDeletedException odeTest =null;
//			try
//			{
//				session.load(DayRevenue.class, newObject.getId());
//			}
//			catch(ObjectDeletedException ode)
//			{
//				odeTest = ode;
//			}
//			assertNotNull(odeTest);
//
//			tx.commit();
//			HibernateUtil.closeSession();
//		}
//		catch (HibernateException he)
//		{
//			he.printStackTrace();
//			throw new AssertionFailedError(he.getMessage());			
//		}
//		long end = System.currentTimeMillis();
//		log.debug("Temps écoulé en seconde sur le test : " + (end - start) / 1000);
//	}
//
//	/** 
//	* Cette méthode teste la table t_type_table_language.
//	* Les actionsde ce test sont les suivantes : <BR/>
//	* Effacer toutes les données de la table t_type_table_language. <BR/>
//	* Insérer des données dans la table t_type_table_language. <BR/>
//	* S'assurer que les données ont bien été insérées dans la table t_type_table_language. <BR/>
//	* Effacer un objet par un identifiant qui n'existe pas. <BR/>
//	* Sauver un seul objet. <BR/>
//	* Récupérer un objet par son identifiant. <BR/>
//	* Effacer un seul objet. <BR/>
//	* Récupérer un objet par son identifiant qui n'existe pas. <BR/>
//	*/
//	public void testTypeTableLanguage()
//	{
//		long start = System.currentTimeMillis();		
//		try
//		{
//			//Effacer toutes les tables de la base de données
//			testDeleteAll();
//			
//			Session session = HibernateUtil.currentSession();
//			Transaction tx = session.beginTransaction();
//
//			//Stocke la liste des types de tables suivant la langue
//			ArrayList insertList = new ArrayList();
//			//Stocke la liste des locales 
//			ArrayList insertListA = new ArrayList();
//			//Récupérer la liste des langues de code ISO 639 de l'api Java
//			String[] listLanguages = java.util.Locale.getISOLanguages();
//			//Nombre de locales 	
//			int lengthLoc= listLanguages.length;
//			//Insère la liste dans la table t_locale
//			for (int i = 0; i <lengthLoc ; i++)
//			{
//				Locale locale = new Locale();
//				locale.setId(listLanguages[i]);
//				session.save(locale);
//				insertListA.add(locale);
//			}
//			//Stocke la liste des types de tables 
//			ArrayList insertListB = new ArrayList();
//			//Nombre de type de table 	
//			int lengthTtb = 50;
//			for (int i = 0; i < lengthTtb; i++)
//			{
//				TypeTable typeTable = new TypeTable();
//				typeTable.setId((Long)session.save(typeTable));				
//				insertListB.add(typeTable);
//			}
//
//			lengthLoc = 50;
//			for (int i = 0; i < lengthLoc; i++)
//			{
//				for (int j = 0; j < lengthTtb; j++)
//				{
//					TypeTableLanguage typeTableLanguage = new TypeTableLanguage();
//					typeTableLanguage.setLocale((Locale)insertListA.get(i));
//					typeTableLanguage.setTypeTable((TypeTable)insertListB.get(j));
//					typeTableLanguage.setLabel(((Locale)insertListA.get(i)).getId());
//					typeTableLanguage.setId((Long)session.save(typeTableLanguage));
//					insertList.add(typeTableLanguage);
//				}				
//			}
//
//			//Nombre de ligne de types de tables suivant la langue
//			int length = insertList.size();
//			//Récupérer la liste précédement insérée dans la table t_type_table_language
//			List listObj = session.find("FROM TypeTableLanguage");
//
//			//Test l'égalité des longueurs des 2 listes
//			assertEquals(length, listObj.size());
//			
//			//Test l'égalité des 2 listes
//			TypeTableLanguage insertObj = null;
//			for (int i = 0; i < length; i++)
//			{
//				int j = 0;
//				TypeTableLanguage obj = null;
//				insertObj = (TypeTableLanguage)insertList.get(i);
//				for (; j < length; j++)
//				{
//					obj = (TypeTableLanguage) listObj.get(j);
//					
//					if (	insertObj.getId().longValue()==obj.getId().longValue()	
//						)
//						break;
//				}
//				if (j == length)
//					assertFalse("La liste insérée n'est pas identique à liste récupérée", true);
//			}
//			
//			Long newObjectId = new Long(length+1);
//			//Effacer un objet par un identifiant qui n'existe pas
//			int res = session.delete("FROM TypeTableLanguage as typeTableLanguage WHERE typeTableLanguage.id =? ", newObjectId, new LongType());
//			assertEquals(res, 0);
//
//			TypeTableLanguage newObject = new TypeTableLanguage();
//			newObject.setId(newObjectId);
//			newObject.setLabel("Toto");
//			newObject.setLocale((Locale)insertListA.get(0));
//			newObject.setTypeTable((TypeTable)insertListB.get(0));
//			//Sauver un seul objet
//			session.save(newObject);
//
//			//Récupérer un objet par son identifiant
//			TypeTableLanguage loadObj = (TypeTableLanguage)session.load(TypeTableLanguage.class, newObject.getId());
//			assertEquals(newObject.getId(), loadObj.getId());
//			assertEquals(newObject.getLabel(), loadObj.getLabel());
//			assertEquals(newObject.getLocale().getId(), loadObj.getLocale().getId());
//			assertEquals(newObject.getTypeTable().getId(), loadObj.getTypeTable().getId());
//						
//			//Effacer un seul objet 
//			session.delete(newObject);
//
//			//Récupérer un objet par son identifiant qui n'existe pas
//			ObjectDeletedException odeTest =null;
//			try
//			{
//				session.load(TypeTableLanguage.class, newObject.getId());
//			}
//			catch(ObjectDeletedException ode)
//			{
//				odeTest = ode;
//			}
//			assertNotNull(odeTest);
//
//			tx.commit();
//			HibernateUtil.closeSession();
//		}
//		catch (HibernateException he)
//		{
//			he.printStackTrace();
//			throw new AssertionFailedError(he.getMessage());			
//		}
//		long end = System.currentTimeMillis();
//		log.debug("Temps écoulé en seconde sur le test : " + (end - start) / 1000);
//	}
//
//	/** 
//	* Cette méthode teste la table t_type_table.
//	* Les actionsde ce test sont les suivantes : <BR/>
//	* Effacer toutes les données de la table t_type_table. <BR/>
//	* Insérer des données dans la table t_type_table. <BR/>
//	* S'assurer que les données ont bien été insérées dans la table t_type_table. <BR/>
//	* Effacer un objet par un identifiant qui n'existe pas. <BR/>
//	* Sauver un seul objet. <BR/>
//	* Récupérer un objet par son identifiant. <BR/>
//	* Effacer un seul objet. <BR/>
//	* Récupérer un objet par son identifiant qui n'existe pas. <BR/>
//	*/
//	public void testTypeTable()
//	{
//		long start = System.currentTimeMillis();		
//		try
//		{
//			//Effacer toutes les tables de la base de données
//			testDeleteAll();
//			
//			Session session = HibernateUtil.currentSession();
//			Transaction tx = session.beginTransaction();
//
//			//Stocke la liste des types de tables
//			ArrayList insertList = new ArrayList();
//			
//			//Nombre de type de table 	
//			int lengthTtb = 150;
//			for (int i = 0; i < lengthTtb; i++)
//			{
//				TypeTable typeTable = new TypeTable();
//				typeTable.setId((Long)session.save(typeTable));				
//				insertList.add(typeTable);
//			}
//
//			//Nombre de ligne des payements
//			int length = insertList.size();
//			//Récupérer la liste précédement insérée dans la table t_type_table
//			List listObj = session.find("FROM TypeTable");
//
//			//Test l'égalité des longueurs des 2 listes
//			assertEquals(length, listObj.size());
//			
//			//Test l'égalité des 2 listes
//			TypeTable insertObj = null;
//			for (int i = 0; i < length; i++)
//			{
//				int j = 0;
//				TypeTable obj = null;
//				insertObj = (TypeTable)insertList.get(i);
//				for (; j < length; j++)
//				{
//					obj = (TypeTable) listObj.get(j);
//					
//					if (	insertObj.getId().longValue()==obj.getId().longValue()	
//						)
//						break;
//				}
//				if (j == length)
//					assertFalse("La liste insérée n'est pas identique à liste récupérée", true);
//			}
//
//			Long newObjectId = new Long(length+1);
//			//Effacer un objet par un identifiant qui n'existe pas
//			int res = session.delete("FROM TypeTable as typeTable WHERE typeTable.id =? ", newObjectId, new LongType());
//			assertEquals(res, 0);
//
//			TypeTable newObject = new TypeTable();
//			newObject.setId(newObjectId);
//			//Sauver un seul objet
//			session.save(newObject);
//
//			//Récupérer un objet par son identifiant
//			TypeTable loadObj = (TypeTable)session.load(TypeTable.class, newObject.getId());
//			assertEquals(newObject.getId(), loadObj.getId());
//						
//			//Effacer un seul objet 
//			session.delete(newObject);
//
//			//Récupérer un objet par son identifiant qui n'existe pas
//			ObjectDeletedException odeTest =null;
//			try
//			{
//				session.load(TypeTable.class, newObject.getId());
//			}
//			catch(ObjectDeletedException ode)
//			{
//				odeTest = ode;
//			}
//			assertNotNull(odeTest);
//
//			tx.commit();
//			HibernateUtil.closeSession();
//		}
//		catch (HibernateException he)
//		{
//			he.printStackTrace();
//			throw new AssertionFailedError(he.getMessage());			
//		}
//		long end = System.currentTimeMillis();
//		log.debug("Temps écoulé en seconde sur le test : " + (end - start) / 1000);
//	}
//
//	/** 
//	* Cette méthode teste la table t_cashing.
//	* Les actionsde ce test sont les suivantes : <BR/>
//	* Effacer toutes les données de la table t_cashing. <BR/>
//	* Insérer des données dans la table t_cashing. <BR/>
//	* S'assurer que les données ont bien été insérées dans la table t_cashing. <BR/>
//	* Effacer un objet par un identifiant qui n'existe pas. <BR/>
//	* Sauver un seul objet. <BR/>
//	* Récupérer un objet par son identifiant. <BR/>
//	* Effacer un seul objet. <BR/>
//	* Récupérer un objet par son identifiant qui n'existe pas. <BR/>
//	*/
//	public void testCashing()
//	{
//		long start = System.currentTimeMillis();		
//		try
//		{
//			//Effacer toutes les tables de la base de données
//			testDeleteAll();
//			
//			Session session = HibernateUtil.currentSession();
//			Transaction tx = session.beginTransaction();
//
//			//Stocke la liste des lignes des payements
//			ArrayList insertList = new ArrayList();
//			//Stocke la liste des tables 
//			ArrayList insertListA = new ArrayList();
//			fulfillDinnerTable(session, insertListA, 40);
//			
//			//Nombre de produits 	
//			int lengthDtb = insertListA.size();
//			for (int i = 0; i < lengthDtb; i++)
//			{
//				Cashing cashing = new Cashing();
//				cashing.setDinnerTable((DinnerTable)insertListA.get(i));
//				cashing.setCard(Math.round((float)Math.random()));
//				cashing.setCash(Math.round((float)Math.random()));
//				cashing.setCheque(Math.round((float)Math.random()));
//				cashing.setTicket(Math.round((float)Math.random()));
//				cashing.setUnpaid(Math.round((float)Math.random()));
//				cashing.setId((Long)session.save(cashing));				
//				insertList.add(cashing);
//			}
//
//			//Nombre de ligne des payements
//			int length = insertList.size();
//			//Récupérer la liste précédement insérée dans la table t_cashing
//			List listObj = session.find("FROM Cashing");
//
//			//Test l'égalité des longueurs des 2 listes
//			assertEquals(length, listObj.size());
//			
//			//Test l'égalité des 2 listes
//			Cashing insertObj = null;
//			for (int i = 0; i < length; i++)
//			{
//				int j = 0;
//				Cashing obj = null;
//				insertObj = (Cashing)insertList.get(i);
//				for (; j < length; j++)
//				{
//					obj = (Cashing) listObj.get(j);
//					
//					if (	insertObj.getId().longValue()==obj.getId().longValue()	
//						)
//						break;
//				}
//				if (j == length)
//					assertFalse("La liste insérée n'est pas identique à liste récupérée", true);
//			}
//			
//			Long newObjectId = new Long(length+1);
//			//Effacer un objet par un identifiant qui n'existe pas
//			int res = session.delete("FROM Cashing as cashing WHERE cashing.id =? ", newObjectId, new LongType());
//			assertEquals(res, 0);
//
//			Cashing newObject = new Cashing();
//			newObject.setId(newObjectId);
//			DinnerTable dinnerTable = new DinnerTable();
//			DinnerTable dinnerTableTemp = (DinnerTable)insertListA.get(0);
//			dinnerTable.setAmountPay(dinnerTableTemp.getAmountPay());
//			dinnerTable.setAmountsSum(dinnerTableTemp.getAmountsSum());
//			dinnerTable.setCashingDate(dinnerTableTemp.getCashingDate());
//			dinnerTable.setCustomersNumber(dinnerTableTemp.getCustomersNumber());
//			dinnerTable.setNumber(dinnerTableTemp.getNumber());
//			dinnerTable.setOrders(dinnerTableTemp.getOrders());
//			dinnerTable.setPrintDate(dinnerTableTemp.getPrintDate());
//			dinnerTable.setQuantitiesSum(dinnerTableTemp.getQuantitiesSum());
//			dinnerTable.setReductionRatio(dinnerTableTemp.getReductionRatio());
//			dinnerTable.setReductionRatioChanged(dinnerTableTemp.isReductionRatioChanged());
//			dinnerTable.setRegistrationDate(dinnerTableTemp.getRegistrationDate());
//			dinnerTable.setRoo_id(dinnerTableTemp.getRoo_id());
//			dinnerTable.setId((Long)session.save(dinnerTable));
//			newObject.setDinnerTable(dinnerTable);
//			newObject.getDinnerTable().setId(newObjectId);
//			newObject.setCard(Math.round((float)Math.random()));
//			newObject.setCash(Math.round((float)Math.random()));
//			newObject.setCheque(Math.round((float)Math.random()));
//			newObject.setTicket(Math.round((float)Math.random()));
//			newObject.setUnpaid(Math.round((float)Math.random()));			
//			//Sauver un seul objet
//			session.save(newObject);
//
//			//Récupérer un objet par son identifiant
//			Cashing loadObj = (Cashing)session.load(Cashing.class, newObject.getId());
//			assertEquals(newObject.getId(), loadObj.getId());
//			assertEquals(new Float(newObject.getCard()), new Float(loadObj.getCard()));
//			assertEquals(new Float(newObject.getCash()), new Float(loadObj.getCash()));
//			assertEquals(new Float(newObject.getCheque()), new Float(loadObj.getCheque()));
//			assertEquals(new Float(newObject.getTicket()), new Float(loadObj.getTicket()));
//			assertEquals(new Float(newObject.getUnpaid()), new Float(loadObj.getUnpaid()));			
//			assertEquals(newObject.getDinnerTable().getId(), loadObj.getDinnerTable().getId());
//			assertEquals(newObject.getDinnerTable().getNumber(), loadObj.getDinnerTable().getNumber());
//			assertEquals(newObject.getDinnerTable().getCashingDate(), loadObj.getDinnerTable().getCashingDate());
//			assertEquals(newObject.getDinnerTable().getPrintDate(), loadObj.getDinnerTable().getPrintDate());
//			assertEquals(newObject.getDinnerTable().getRegistrationDate(), loadObj.getDinnerTable().getRegistrationDate());
//			assertEquals(new Float(newObject.getDinnerTable().getAmountPay()), new Float(loadObj.getDinnerTable().getAmountPay()));
//			assertEquals(new Float(newObject.getDinnerTable().getAmountsSum()), new Float(loadObj.getDinnerTable().getAmountsSum()));
//			assertEquals(new Float(newObject.getDinnerTable().getQuantitiesSum()), new Float(loadObj.getDinnerTable().getQuantitiesSum()));
//			assertEquals(new Float(newObject.getDinnerTable().getReductionRatio()), new Float(loadObj.getDinnerTable().getReductionRatio()));
//			assertEquals(new Integer(newObject.getDinnerTable().getCustomersNumber()), new Integer(loadObj.getDinnerTable().getCustomersNumber()));
//			assertEquals(new Long(newObject.getDinnerTable().getRoo_id()), new Long(loadObj.getDinnerTable().getRoo_id()));
//			assertEquals(new Boolean(newObject.getDinnerTable().isReductionRatioChanged()), new Boolean(loadObj.getDinnerTable().isReductionRatioChanged()));
//						
//			//Effacer un seul objet 
//			session.delete(newObject);
//
//			//Récupérer un objet par son identifiant qui n'existe pas
//			ObjectDeletedException odeTest =null;
//			try
//			{
//				session.load(Cashing.class, newObject.getId());
//			}
//			catch(ObjectDeletedException ode)
//			{
//				odeTest = ode;
//			}
//			assertNotNull(odeTest);
//
//			tx.commit();
//			session.close();
//		}
//		catch (HibernateException he)
//		{
//			he.printStackTrace();
//			throw new AssertionFailedError(he.getMessage());			
//		}
//		long end = System.currentTimeMillis();
//		log.debug("Temps écoulé en seconde sur le test : " + (end - start) / 1000);
//	}
//
//	/** 
//	* Cette méthode teste la table t_category_relation.
//	* Les actionsde ce test sont les suivantes : <BR/>
//	* Effacer toutes les données de la table t_category_relation. <BR/>
//	* Insérer des données dans la table t_category_relation. <BR/>
//	* S'assurer que les données ont bien été insérées dans la table t_category_relation. <BR/>
//	* Effacer un objet par un identifiant qui n'existe pas. <BR/>
//	* Sauver un seul objet. <BR/>
//	* Récupérer un objet par son identifiant. <BR/>
//	* Effacer un seul objet. <BR/>
//	* Récupérer un objet par son identifiant qui n'existe pas. <BR/>
//	*/
//	public void testCategoryRelation()
//	{
//		long start = System.currentTimeMillis();		
//		try
//		{
//			//Effacer toutes les tables de la base de données
//			testDeleteAll();
//			
//			Session session = HibernateUtil.currentSession();
//			Transaction tx = session.beginTransaction();
//
//			//Stocke la liste des lignes de stats
//			ArrayList insertList = new ArrayList();
//			//Stocke la liste des produits 
//			ArrayList insertListA = new ArrayList();
//			fulfillProduct(session, insertListA);
//			//Stocke la liste des catégories 
//			ArrayList insertListB = new ArrayList();
//			fulfillCategory(session, insertListB, 32);
//			
//			//Nombre de produits 	
//			int lengthPdt = 32;//insertListA.size();
//			//Nombre de catégories 	
//			int lengthCat = insertListB.size();
//			for (int i = 0; i < lengthPdt; i++)
//			{
//				for (int j = 0; j < lengthCat; j++)
//				{
//					CategoryRelation categoryRelation = new CategoryRelation();
//					categoryRelation.setProduct((Product)insertListA.get(i));
//					categoryRelation.setCategory((Category)insertListB.get(j));
//					categoryRelation.setQuantity(Math.round((float)Math.random()));
//					categoryRelation.setId((Long)session.save(categoryRelation));
//					insertList.add(categoryRelation);
//				}				
//			}
//
//			//Nombre de ligne de catégories-relations
//			int length = insertList.size();
//			//Récupérer la liste précédement insérée dans la table t_category_relation
//			List listObj = session.find("FROM CategoryRelation");
//
//			//Test l'égalité des longueurs des 2 listes
//			assertEquals(length, listObj.size());
//			
//			//Test l'égalité des 2 listes
//			CategoryRelation insertObj = null;
//			for (int i = 0; i < length; i++)
//			{
//				int j = 0;
//				CategoryRelation obj = null;
//				insertObj = (CategoryRelation)insertList.get(i);
//				for (; j < length; j++)
//				{
//					obj = (CategoryRelation) listObj.get(j);
//					
//					if (	insertObj.getId().longValue()==obj.getId().longValue()	
//						)
//						break;
//				}
//				if (j == length)
//					assertFalse("La liste insérée n'est pas identique à liste récupérée", true);
//			}
//
//			Long newObjectId = new Long(length+1);
//			//Effacer un objet par un identifiant qui n'existe pas
//			int res = session.delete("FROM CategoryRelation as categoryRelation WHERE categoryRelation.id =? ", newObjectId, new LongType());
//			assertEquals(res, 0);
//
//			CategoryRelation newObject = new CategoryRelation();
//			newObject.setId(newObjectId);
//			Product product = new Product();
//			product.setId("xxx");
//			product.setPrice(Math.round((float)Math.random()));
//			product.setProductSpecialCode(((Product)insertListA.get(0)).getProductSpecialCode());
//			product.setVat(((Product)insertListA.get(0)).getVat());
//			session.save(product);
//			newObject.setProduct(product);
//			newObject.setQuantity(Math.round((float)Math.random()));
//			newObject.setCategory((Category)insertListB.get(0));
//			//Sauver un seul objet
//			session.save(newObject);
//
//			//Récupérer un objet par son identifiant
//			CategoryRelation loadObj = (CategoryRelation)session.load(CategoryRelation.class, newObject.getId());
//			assertEquals(newObject.getId(), loadObj.getId());
//			assertEquals(new Float(newObject.getQuantity()), new Float(loadObj.getQuantity()));
//			assertEquals(newObject.getProduct().getId(), loadObj.getProduct().getId());
//			assertEquals(new Float(newObject.getProduct().getPrice()), new Float(loadObj.getProduct().getPrice()));
//			assertEquals(newObject.getProduct().getVat().getId(), loadObj.getProduct().getVat().getId());
//			assertEquals(new Float(newObject.getProduct().getVat().getValue()), new Float(loadObj.getProduct().getVat().getValue()));
//			assertEquals(newObject.getProduct().getProductSpecialCode().getId(), loadObj.getProduct().getProductSpecialCode().getId());
//			assertEquals(newObject.getProduct().getProductSpecialCode().getCode(), loadObj.getProduct().getProductSpecialCode().getCode());
//			assertEquals(newObject.getCategory().getId(), loadObj.getCategory().getId());
//			
//			//Effacer un seul objet 
//			session.delete(newObject);
//
//			//Récupérer un objet par son identifiant qui n'existe pas
//			ObjectDeletedException odeTest =null;
//			try
//			{
//				session.load(CategoryRelation.class, newObject.getId());
//			}
//			catch(ObjectDeletedException ode)
//			{
//				odeTest = ode;
//			}
//			assertNotNull(odeTest);
//			
//			tx.commit();
//			HibernateUtil.closeSession();
//		}
//		catch (HibernateException he)
//		{
//			he.printStackTrace();
//			throw new AssertionFailedError(he.getMessage());			
//		}
//		long end = System.currentTimeMillis();
//		log.debug("Temps écoulé en seconde sur le test : " + (end - start) / 1000);
//	}
//
//	/** 
//	* Cette méthode teste la table t_stats_consumption_product.
//	* Les actionsde ce test sont les suivantes : <BR/>
//	* Effacer toutes les données de la table t_stats_consumption_product. <BR/>
//	* Insérer des données dans la table t_stats_consumption_product. <BR/>
//	* S'assurer que les données ont bien été insérées dans la table t_stats_consumption_product. <BR/>
//	* Effacer un objet par un identifiant qui n'existe pas. <BR/>
//	* Sauver un seul objet. <BR/>
//	* Récupérer un objet par son identifiant. <BR/>
//	* Effacer un seul objet. <BR/>
//	* Récupérer un objet par son identifiant qui n'existe pas. <BR/>
//	*/
//	public void testStatsConsumptionProduct()
//	{
//		long start = System.currentTimeMillis();		
//		try
//		{
//			//Effacer toutes les tables de la base de données
//			testDeleteAll();
//			
//			Session session = HibernateUtil.currentSession();
//			Transaction tx = session.beginTransaction();
//
//			//Stocke la liste des lignes de stats
//			ArrayList insertList = new ArrayList();
//			//Stocke la liste des produits 
//			ArrayList insertListA = new ArrayList();
//			fulfillProduct(session, insertListA);
//
//			//Nombre de produits 	
//			int lengthPdt = insertListA.size();
//			Calendar calendar = Calendar.getInstance();
//			for (int i = 0; i < lengthPdt; i++)
//			{
//				StatsConsumptionProduct statsConsumptionProduct = new StatsConsumptionProduct();
//				statsConsumptionProduct.setProduct((Product)insertListA.get(i));
//				statsConsumptionProduct.setQuantity(Math.round((float)Math.random()));
//				statsConsumptionProduct.setUpdatedDay(new Short((short)calendar.get(Calendar.DAY_OF_MONTH)));
//				statsConsumptionProduct.setUpdatedMonth(new Short((short)calendar.get(Calendar.MONTH)));
//				statsConsumptionProduct.setUpdatedYear(new Short((short)calendar.get(Calendar.YEAR)));
//				statsConsumptionProduct.setId((Long)session.save(statsConsumptionProduct));
//				insertList.add(statsConsumptionProduct);				
//			}
//
//			//Nombre de ligne de stats
//			int length = insertList.size();
//			//Récupérer la liste précédement insérée dans la table t_stats_consumption_product
//			List listObj = session.find("FROM StatsConsumptionProduct");
//
//			//Test l'égalité des longueurs des 2 listes
//			assertEquals(length, listObj.size());
//			
//			//Test l'égalité des 2 listes
//			StatsConsumptionProduct insertObj = null;
//			for (int i = 0; i < length; i++)
//			{
//				int j = 0;
//				StatsConsumptionProduct obj = null;
//				insertObj = (StatsConsumptionProduct)insertList.get(i);
//				for (; j < length; j++)
//				{
//					obj = (StatsConsumptionProduct) listObj.get(j);
//					
//					if (	insertObj.getId().longValue()==obj.getId().longValue()	
//						)
//						break;
//				}
//				if (j == length)
//					assertFalse("La liste insérée n'est pas identique à liste récupérée", true);
//			}
//			
//			Long newObjectId = new Long(length+1);
//			//Effacer un objet par un identifiant qui n'existe pas
//			int res = session.delete("FROM StatsConsumptionProduct as statsConsumptionProduct WHERE statsConsumptionProduct.id =? ", newObjectId, new LongType());
//			assertEquals(res, 0);
//
//			StatsConsumptionProduct newObject = new StatsConsumptionProduct();
//			newObject.setId(newObjectId);
//			Product product = new Product();
//			product.setId("xxx");
//			product.setPrice(Math.round((float)Math.random()));
//			product.setProductSpecialCode(((Product)insertListA.get(0)).getProductSpecialCode());
//			product.setVat(((Product)insertListA.get(0)).getVat());
//			session.save(product);
//			newObject.setProduct(product);
//			newObject.setQuantity(Math.round((float)Math.random()));		
//			newObject.setUpdatedDay(new Short((short)calendar.get(Calendar.DAY_OF_MONTH)));
//			newObject.setUpdatedMonth(new Short((short)calendar.get(Calendar.MONTH)));
//			newObject.setUpdatedYear(new Short((short)calendar.get(Calendar.YEAR)));
//			//Sauver un seul objet
//			session.save(newObject);
//
//			//Récupérer un objet par son identifiant
//			StatsConsumptionProduct loadObj = (StatsConsumptionProduct)session.load(StatsConsumptionProduct.class, newObject.getId());
//			assertEquals(newObject.getId(), loadObj.getId());
//			assertEquals(new Float(newObject.getQuantity()), new Float(loadObj.getQuantity()));
//			assertEquals(newObject.getUpdatedDay(), loadObj.getUpdatedDay());
//			assertEquals(newObject.getUpdatedMonth(), loadObj.getUpdatedMonth());
//			assertEquals(newObject.getUpdatedYear(), loadObj.getUpdatedYear());
//			assertEquals(newObject.getProduct().getId(), loadObj.getProduct().getId());
//			assertEquals(new Float(newObject.getProduct().getPrice()), new Float(loadObj.getProduct().getPrice()));
//			assertEquals(newObject.getProduct().getVat().getId(), loadObj.getProduct().getVat().getId());
//			assertEquals(new Float(newObject.getProduct().getVat().getValue()), new Float(loadObj.getProduct().getVat().getValue()));
//			assertEquals(newObject.getProduct().getProductSpecialCode().getId(), loadObj.getProduct().getProductSpecialCode().getId());
//			assertEquals(newObject.getProduct().getProductSpecialCode().getCode(), loadObj.getProduct().getProductSpecialCode().getCode());
//			
//			//Effacer un seul objet 
//			session.delete(newObject);
//
//			//Récupérer un objet par son identifiant qui n'existe pas
//			ObjectDeletedException odeTest =null;
//			try
//			{
//				session.load(StatsConsumptionProduct.class, newObject.getId());
//			}
//			catch(ObjectDeletedException ode)
//			{
//				odeTest = ode;
//			}
//			assertNotNull(odeTest);
//
//			tx.commit();
//			HibernateUtil.closeSession();
//		}
//		catch (HibernateException he)
//		{
//			he.printStackTrace();
//			throw new AssertionFailedError(he.getMessage());			
//		}
//		long end = System.currentTimeMillis();
//		log.debug("Temps écoulé en seconde sur le test : " + (end - start) / 1000);
//	}
//
//	/** 
//	* Cette méthode teste la table t_order_line.
//	* Les actionsde ce test sont les suivantes : <BR/>
//	* Effacer toutes les données de la table t_order_line. <BR/>
//	* Insérer des données dans la table t_order_line. <BR/>
//	* S'assurer que les données ont bien été insérées dans la table t_order_line. <BR/>
//	* Effacer un objet par un identifiant qui n'existe pas. <BR/>
//	* Sauver un seul objet. <BR/>
//	* Récupérer un objet par son identifiant. <BR/>
//	* Effacer un seul objet. <BR/>
//	* Récupérer un objet par son identifiant qui n'existe pas. <BR/>
//	*/
//	public void testOrderLine()
//	{
//		long start = System.currentTimeMillis();		
//		try
//		{
//			//Effacer toutes les tables de la base de données
//			testDeleteAll();
//			
//			Session session = HibernateUtil.currentSession();
//			Transaction tx = session.beginTransaction();
//
//			//Stocke la liste des lignes de commande
//			ArrayList insertList = new ArrayList();
//			//Stocke la liste des tables
//			ArrayList insertListA = new ArrayList();
//			fulfillDinnerTable(session, insertListA, 40);
//			//Stocke la liste des produits 
//			ArrayList insertListC = new ArrayList();
//			//Stocke la liste des produits 
//			ArrayList insertListD = new ArrayList();
//			fulfillProductLanguage(session, insertListD, insertListC);
//
//			//Nombre de table
//			int lengthDnt = 10;//32;//insertListA.size();
//			//Nombre de partie	
//			int lengthOrp = 10;//32;//insertListB.size();
//			//Nombre de commandes par table	
//			int lengthOrl = 10;//32;//insertListC.size()%100;
//			for (int i = 0; i < lengthDnt; i++)
//			{
//				for (int j = 0; j < lengthOrp; j++)
//				{
//					for (int k = 0; k < lengthOrl; k++)
//					{
//						ProductPart productPart = new ProductPart();  
//						session.save(productPart);
//						OrderLine orderLine = new OrderLine();
//						orderLine.setDinnerTable((DinnerTable)insertListA.get(i));
//						orderLine.setProduct((Product)insertListC.get(k));
//						orderLine.setLabel("Label-"+i+"-"+j+"-"+k);
//						orderLine.setQuantity(Math.round((float)Math.random()));		
//						orderLine.setUnitPrice(Math.round((float)Math.random()));
//						orderLine.setAmount(orderLine.getQuantity()*orderLine.getUnitPrice());
//						orderLine.setId((Long)session.save(orderLine));
//						
//						orderLine.setProductPart(productPart);
//
//						insertList.add(orderLine);
//					}
//				}
//			}
//
//			//Nombre de ligne de commande
//			int length = insertList.size();
//			//Récupérer la liste précédement insérée dans la table t_order_line
//			List listObj = session.find("FROM OrderLine");
//
//			//Test l'égalité des longueurs des 2 listes
//			assertEquals(length, listObj.size());
//			
//			//Test l'égalité des 2 listes
//			OrderLine insertObj = null;
//			for (int i = 0; i < length; i++)
//			{
//				int j = 0;
//				OrderLine obj = null;
//				insertObj = (OrderLine)insertList.get(i);
//				for (; j < length; j++)
//				{
//					obj = (OrderLine) listObj.get(j);
//					
//					if (	insertObj.getId().longValue()==obj.getId().longValue()	
//						)
//						break;
//				}
//				if (j == length)
//					assertFalse("La liste insérée n'est pas identique à liste récupérée", true);
//			}
//
//			Long newObjectId = new Long(length+1);
//			//Effacer un objet par un identifiant qui n'existe pas
//			int res = session.delete("FROM OrderLine as orderLine WHERE orderLine.id =? ", newObjectId, new LongType());
//			assertEquals(res, 0);
//
//			OrderLine newObject = new OrderLine();
//			newObject.setId(newObjectId);
//			newObject.setDinnerTable((DinnerTable)insertListA.get(0));
//			newObject.setProduct((Product)insertListC.get(0));
//			newObject.setLabel("Label-ijk");
//			newObject.setQuantity(Math.round((float)Math.random()));		
//			newObject.setUnitPrice(Math.round((float)Math.random()));
//			newObject.setAmount(newObject.getQuantity()*newObject.getUnitPrice());
//			//Sauver un seul objet
//			session.save(newObject);
//
//			//Récupérer un objet par son identifiant
//			OrderLine loadObj = (OrderLine)session.load(OrderLine.class, newObject.getId());
//			assertEquals(newObject.getId(), loadObj.getId());
//			assertEquals(newObject.getLabel(), loadObj.getLabel());
//			//SpecialCodeValue == Product ==null
//			assertEquals(newObject.getSpecialCodeValue(), loadObj.getSpecialCodeValue());
//			assertEquals(new Float(newObject.getAmount()), new Float(loadObj.getAmount()));
//			assertEquals(new Float(newObject.getQuantity()), new Float(loadObj.getQuantity()));
//			assertEquals(new Float(newObject.getUnitPrice()), new Float(loadObj.getUnitPrice()));
//			assertEquals(newObject.getDinnerTable().getId(), loadObj.getDinnerTable().getId());
//			assertEquals(newObject.getDinnerTable().getNumber(), loadObj.getDinnerTable().getNumber());
//			assertEquals(new Float(newObject.getDinnerTable().getAmountPay()), new Float(loadObj.getDinnerTable().getAmountPay()));
//			assertEquals(new Float(newObject.getDinnerTable().getAmountsSum()), new Float(loadObj.getDinnerTable().getAmountsSum()));
//			assertEquals(new Float(newObject.getDinnerTable().getQuantitiesSum()), new Float(loadObj.getDinnerTable().getQuantitiesSum()));
//			assertEquals(new Float(newObject.getDinnerTable().getReductionRatio()), new Float(loadObj.getDinnerTable().getReductionRatio()));
//			assertEquals(new Integer(newObject.getDinnerTable().getCustomersNumber()), new Integer(loadObj.getDinnerTable().getCustomersNumber()));
//			assertEquals(new Long(newObject.getDinnerTable().getRoo_id()), new Long(loadObj.getDinnerTable().getRoo_id()));
//			assertEquals(new Boolean(newObject.getDinnerTable().isReductionRatioChanged()), new Boolean(loadObj.getDinnerTable().isReductionRatioChanged()));			
//			assertEquals(newObject.getDinnerTable().getPrintDate(), loadObj.getDinnerTable().getPrintDate());
//			assertEquals(newObject.getDinnerTable().getRegistrationDate(), loadObj.getDinnerTable().getRegistrationDate());
//			assertEquals(newObject.getDinnerTable().getPrintDate(), loadObj.getDinnerTable().getPrintDate());
//			assertEquals(newObject.getProduct().getId(), loadObj.getProduct().getId());
//			assertEquals(new Float(newObject.getProduct().getPrice()), new Float(loadObj.getProduct().getPrice()));
//			assertEquals(newObject.getProduct().getVat().getId(), loadObj.getProduct().getVat().getId());
//			assertEquals(new Float(newObject.getProduct().getVat().getValue()), new Float(loadObj.getProduct().getVat().getValue()));
//			assertEquals(newObject.getProduct().getProductSpecialCode().getId(), loadObj.getProduct().getProductSpecialCode().getId());
//			assertEquals(newObject.getProduct().getProductSpecialCode().getCode(), loadObj.getProduct().getProductSpecialCode().getCode());
//			
//			//Effacer un seul objet 
//			session.delete(newObject);
//
//			//Récupérer un objet par son identifiant qui n'existe pas
//			ObjectDeletedException odeTest =null;
//			try
//			{
//				session.load(OrderLine.class, newObject.getId());
//			}
//			catch(ObjectDeletedException ode)
//			{
//				odeTest = ode;
//			}
//			assertNotNull(odeTest);
//
//			tx.commit();
//			HibernateUtil.closeSession();
//		}
//		catch (HibernateException he)
//		{
//			he.printStackTrace();
//			throw new AssertionFailedError(he.getMessage());			
//		}
//		long end = System.currentTimeMillis();
//		log.debug("Temps écoulé en seconde sur le test : " + (end - start) / 1000);
//	}
//
//	/**
//	 * Cette méthode permet de remplir la table t_product_language avec toutes ses contraintes.
//	 */
//	private void fulfillProductLanguage(Session session, ArrayList insertList, ArrayList insertListB) throws HibernateException
//	{
//		//Utiliser la liste des Locale pour les noms des produits et les locales
//		List insertListA = Arrays.asList(java.util.Locale.getISOLanguages());
//		int length = insertListA.size();
//		//Insère la liste dans la table t_locale
//		for (int i = 0; i < length; i++)
//		{
//			Locale locale = new Locale();
//			locale.setId((String)insertListA.get(i));
//			session.save(locale);
//		}
//
//		//Insertion des données dans la table t_product
//		fulfillProduct(session, insertListB);
//
//		//Insertion des données dans la table t_product_language
//		for(int i=0; i<length;i++)
//		{
//			ProductLanguage productLanguage = new ProductLanguage();
//			ProductLanguageID productLanguageID = new ProductLanguageID();
//			Locale locale = new Locale();
//			locale.setId((String)insertListA.get(i));
//			productLanguageID.setLocale(locale);
//			productLanguageID.setProduct((Product)insertListB.get(i));
//			productLanguage.setId(productLanguageID);
//				
//			productLanguage.setLabel("Label"+(String)insertListA.get(i));
//												
//			session.save(productLanguage);
//			insertList.add(productLanguage);
//		}
//	}
//
//	/** 
//	* Cette méthode teste la table t_product_language.
//	* Les actionsde ce test sont les suivantes : <BR/>
//	* Effacer toutes les données de la table t_product_language. <BR/>
//	* Insérer des données dans la table t_product_language. <BR/>
//	* S'assurer que les données ont bien été insérées dans la table t_product_language. <BR/>
//	* Effacer un objet par un identifiant qui n'existe pas. <BR/>
//	* Sauver un seul objet. <BR/>
//	* Récupérer un objet par son identifiant. <BR/>
//	* Effacer un seul objet. <BR/>
//	* Récupérer un objet par son identifiant qui n'existe pas. <BR/>
//	*/
//	public void testProductLanguage()
//	{
//		long start = System.currentTimeMillis();		
//		try
//		{
//			//Effacer toutes les tables de la base de données
//			testDeleteAll();
//
//			Session session = HibernateUtil.currentSession();
//			Transaction tx = session.beginTransaction();
//			
//			//Stocke la liste des produits suivant la langue
//			ArrayList insertList = new ArrayList();
//			//Stocke la liste des produits
//			ArrayList insertListB = new ArrayList(); 
//			
//			fulfillProductLanguage(session, insertList, insertListB);
//			
//			int length = java.util.Locale.getISOLanguages().length;
//			
//			//Récupérer la liste précédement insérée dans la table t_product_language
//			List listObj = session.find("FROM ProductLanguage");
//
//			//Test l'égalité des longueurs des 2 listes
//			assertEquals(length, listObj.size());
//			
//			//Test l'égalité des 2 listes
//			ProductLanguage insertObj = null;
//			for (int i = 0; i < length; i++)
//			{
//				int j = 0;
//				ProductLanguage obj = null;
//				insertObj = (ProductLanguage)insertList.get(i);
//				for (; j < length; j++)
//				{
//					obj = (ProductLanguage) listObj.get(j);
//					
//					if (	insertObj.getId().getLocale().getId().equals(obj.getId().getLocale().getId()) &&
//							insertObj.getId().getProduct().getId().equals(obj.getId().getProduct().getId())	
//						)
//						break;
//				}
//				if (j == length)
//					assertFalse("La liste insérée n'est pas identique à liste récupérée", true);
//			}
//
//			Object[] objs = new Object[2];
//			objs[0] = "xxx";
//			objs[1] = "xxx";
//			Type[] types = new Type[2];
//			types[0] = new StringType();
//			types[1] = new StringType();
//			//Effacer un objet par un identifiant qui n'existe pas
//			int res = session.delete("FROM ProductLanguage as productLanguage WHERE productLanguage.id.product.id=? AND productLanguage.id.locale.id=?", objs, types);
//			assertEquals(res, 0);
//
//			ProductLanguage newObject = new ProductLanguage();
//			Product product = (Product)insertListB.get(1);
//			Locale locale = new Locale();
//			locale.setId("aa");
//			ProductLanguageID productLanguageID = new ProductLanguageID();
//			productLanguageID.setLocale(locale);
//			productLanguageID.setProduct(product);
//			newObject.setId(productLanguageID);
//			newObject.setLabel("Toto");
//			//Sauver un seul objet			
//			session.save(newObject);
//
//			//Récupérer un objet par son identifiant
//			ProductLanguage loadObj = (ProductLanguage)session.load(ProductLanguage.class, newObject.getId());
//			assertEquals(newObject.getId().getLocale().getId(), loadObj.getId().getLocale().getId());
//			assertEquals(newObject.getId().getProduct().getId(), loadObj.getId().getProduct().getId());
//			
//			//Effacer un seul objet 
//			session.delete(newObject);
//
//			//Récupérer un objet par son identifiant qui n'existe pas
//			ObjectDeletedException odeTest =null;
//			try
//			{
//				session.load(ProductLanguage.class, newObject.getId());
//			}
//			catch(ObjectDeletedException ode)
//			{
//				odeTest = ode;
//			}
//			assertNotNull(odeTest);
//
//			tx.commit();
//			HibernateUtil.closeSession();
//		}
//		catch (HibernateException he)
//		{
//			he.printStackTrace();
//			throw new AssertionFailedError(he.getMessage());			
//		}
//		long end = System.currentTimeMillis();
//		log.debug("Temps écoulé en seconde sur le test : " + (end - start) / 1000);
//	}
//
//	/** 
//	* Cette méthode teste la table t_dinner_table.
//	* Les actionsde ce test sont les suivantes : <BR/>
//	* Effacer toutes les données de la table t_dinner_table. <BR/>
//	* Insérer des données dans la table t_dinner_table. <BR/>
//	* S'assurer que les données ont bien été insérées dans la table t_dinner_table. <BR/>
//	* Effacer un objet par un identifiant qui n'existe pas. <BR/>
//	* Sauver un seul objet. <BR/>
//	* Récupérer un objet par son identifiant. <BR/>
//	* Effacer un seul objet. <BR/>
//	* Récupérer un objet par son identifiant qui n'existe pas. <BR/>
//	*/
//	public void testDinnerTable()
//	{
//		long start = System.currentTimeMillis();		
//		try
//		{
//			delete(T_LOCALE);
//
//			Session session = HibernateUtil.currentSession();
//			Transaction tx = session.beginTransaction();
//
//			//Effacer en cascade les données de la table t_dinner_table
//			//delete(T_DINNERTABLE, session);
//			
//			//Stocke la liste des tables
//			ArrayList insertList = new ArrayList();
//			
//			//Utiliser la liste des Locale pour les numéros de tables
//			List insertListA = Arrays.asList(java.util.Locale.getISOLanguages());
//			//Insertion des données dans la table t_dinner_table
//			fulfillDinnerTable(session, insertList, 4);
//			
//			//Récupérer la liste précédement insérée dans la table t_dinner_table
//			List listObj = session.find("FROM DinnerTable");
//
//			//Test l'égalité des longueurs des 2 listes
//			assertEquals(insertList.size(), listObj.size());
//			
//			//Test l'égalité des 2 listes
//			DinnerTable insertObj = null;
//			for (int i = 0; i < insertList.size(); i++)
//			{
//				int j = 0;
//				DinnerTable obj = null;
//				insertObj = (DinnerTable)insertList.get(i);
//				for (; j < insertList.size(); j++)
//				{
//					obj = (DinnerTable) listObj.get(j);
//					
//					if (	insertObj.getId().longValue()==obj.getId().longValue()	)
//						break;
//				}
//				if (j == insertList.size())
//					assertFalse("La liste insérée n'est pas identique à liste récupérée", true);
//			}
//
//			Long newObjectId = new Long(1001);
//			//Effacer un objet par un identifiant qui n'existe pas
//			int res = session.delete("FROM DinnerTable as dinnerTable WHERE dinnerTable.id=?", newObjectId, new LongType());
//			assertEquals(res, 0);
//
//			DinnerTable newObject = new DinnerTable();
//			newObject.setId(newObjectId);
//			newObject.setUser((User)session.load(User.class, new Long(1)));
//			newObject.setRoo_id(Math.round(Math.random()));
//			newObject.setNumber((String)"Toto");
//			newObject.setCustomersNumber(Math.round((float)Math.random()));												
//			newObject.setQuantitiesSum((float)Math.random());
//			newObject.setAmountsSum((float)Math.random());
//			newObject.setReductionRatio((float)Math.random());
//			newObject.setAmountPay((float)Math.random());
//			newObject.setRegistrationDate(new Date());
//			newObject.setPrintDate(new Date());
//			newObject.setCashingDate(new Date());
//			newObject.setReductionRatioChanged(true);
//			newObject.setTakeaway(true);
//			//Sauver un seul objet			
//			session.save(newObject);
//
//			//Récupérer un objet par son identifiant
//			DinnerTable loadObj = (DinnerTable)session.load(DinnerTable.class, newObject.getId());
//			assertEquals(newObject.getId().longValue(), loadObj.getId().longValue());
//			
//			//Effacer un seul objet 
//			session.delete(newObject);
//
//			//Récupérer un objet par son identifiant qui n'existe pas
//			ObjectDeletedException odeTest =null;
//			try
//			{
//				session.load(DinnerTable.class, newObject.getId());
//			}
//			catch(ObjectDeletedException ode)
//			{
//				odeTest = ode;
//			}
//			assertNotNull(odeTest);
//
//			tx.commit();
//			HibernateUtil.closeSession();
//		}
//		catch (HibernateException he)
//		{
//			he.printStackTrace();
//			throw new AssertionFailedError(he.getMessage());			
//		}
//		long end = System.currentTimeMillis();
//		log.debug("Temps écoulé en seconde sur le test : " + (end - start) / 1000);
//	}
//
//	/**
//	 * Cette méthode permet de remplir la table t_dinner_table avec toutes ses contraintes.
//	 */
//	private void fulfillDinnerTable(Session session, ArrayList insertList, int size) throws HibernateException
//	{
//		//Utiliser la liste des Locale pour les numéros de tables
//		List insertListA = Arrays.asList(java.util.Locale.getISOLanguages());
//
//		//Liste des données à insérer dans la table t_user
//		ArrayList insertListB = new ArrayList();
//		fulfillUser(session, insertListB, 40);
//		
//		if(size>insertListA.size())
//			size = insertListA.size();
//
//		//Insertion des données dans la table t_dinner_table
//		for(int i=0; i<size; i++)
//		{
//			for(int j=0; j<insertListB.size(); j++)
//			{
//				DinnerTable dinnerTable = new DinnerTable();
//				dinnerTable.setUser((User) insertListB.get(j));
//				dinnerTable.setRoo_id(Math.round(Math.random()));
//				dinnerTable.setNumber((String)insertListA.get(i));
//				dinnerTable.setCustomersNumber(Math.round((float)Math.random()));												
//				dinnerTable.setQuantitiesSum((float)Math.random());
//				dinnerTable.setAmountsSum((float)Math.random());
//				dinnerTable.setReductionRatio((float)Math.random());
//				dinnerTable.setAmountPay((float)Math.random());
//				dinnerTable.setRegistrationDate(new Date());
//				dinnerTable.setPrintDate(new Date());
//				dinnerTable.setCashingDate(new Date());
//				dinnerTable.setReductionRatioChanged(true);
//				dinnerTable.setTakeaway(true);
//													
//				session.save(dinnerTable);
//				insertList.add(dinnerTable);
//			}	
//		}
//	}
//
//	/** 
//	* Cette méthode teste la table t_order_part_language.
//	* Les actionsde ce test sont les suivantes : <BR/>
//	* Effacer toutes les données de la table t_order_part_language. <BR/>
//	* Insérer des données dans la table t_order_part_language. <BR/>
//	* S'assurer que les données ont bien été insérées dans la table t_order_part_language. <BR/>
//	* Effacer un objet par un identifiant qui n'existe pas. <BR/>
//	* Sauver un seul objet. <BR/>
//	* Récupérer un objet par son identifiant. <BR/>
//	* Effacer un seul objet. <BR/>
//	* Récupérer un objet par son identifiant qui n'existe pas. <BR/>
//	*/
//	public void testProductPartLanguage()
//	{
//		long start = System.currentTimeMillis();		
//		try
//		{
//			//Effacer toutes les tables de la base de données
//			testDeleteAll();
//
//			Session session = HibernateUtil.currentSession();
//			Transaction tx = session.beginTransaction();
//			
//			//Stocke la liste des parties de commandes suivant la langue
//			ArrayList insertList = new ArrayList();
//			
//			//Utiliser la liste des Locale pour les libellés et les locales
//			List insertListA = Arrays.asList(java.util.Locale.getISOLanguages());
//			int length = insertListA.size();
//			//Insère la liste dans la table t_locale
//			for (int i = 0; i < length; i++)
//			{
//				Locale locale = new Locale();
//				locale.setId((String)insertListA.get(i));
//				session.save(locale);
//			}
//			List insertListB = new ArrayList(); 
//			//Insertion des données dans la table t_product_part
//			for(int i=0; i<length;i++)
//			{
//				ProductPart productPart = new ProductPart();				
//				productPart.setId((Long)session.save(productPart));
//				insertListB.add(productPart);
//			}
//				
//			//Insertion des données dans la table t_product_part_language
//			for(int i=0; i<length;i++)
//			{
//				ProductPartLanguage productPartLanguage = new ProductPartLanguage();
//				ProductPartLanguageID productPartLanguageID = new ProductPartLanguageID();
//				Locale locale = new Locale();
//				locale.setId((String)insertListA.get(i));
//				productPartLanguageID.setLocale(locale);
//				productPartLanguageID.setProductPart((ProductPart)insertListB.get(i));
//				productPartLanguage.setId(productPartLanguageID);
//				
//				productPartLanguage.setLabel("Label"+(String)insertListA.get(i));
//												
//				session.save(productPartLanguage);
//				insertList.add(productPartLanguage);
//			}
//
//			//Récupérer la liste précédement insérée dans la table t_product_part_language
//			List listObj = session.find("FROM ProductPartLanguage");
//
//			//Test l'égalité des longueurs des 2 listes
//			assertEquals(length, listObj.size());
//			
//			//Test l'égalité des 2 listes
//			ProductPartLanguage insertObj = null;
//			for (int i = 0; i < length; i++)
//			{
//				int j = 0;
//				ProductPartLanguage obj = null;
//				insertObj = (ProductPartLanguage)insertList.get(i);
//				for (; j < length; j++)
//				{
//					obj = (ProductPartLanguage) listObj.get(j);
//					
//					if (	insertObj.getId().getLocale().getId().equals(obj.getId().getLocale().getId()) &&
//							insertObj.getId().getProductPart().getId().longValue()==obj.getId().getProductPart().getId().longValue()	
//						)
//						break;
//				}
//				if (j == length)
//					assertFalse("La liste insérée n'est pas identique à liste récupérée", true);
//			}
//
//			ProductPartLanguageID newObjectId = new ProductPartLanguageID();
//			Locale locale = new Locale();
//			locale.setId((String)"xxx");
//			newObjectId.setLocale(locale);
//			ProductPart productPart = new ProductPart();
//			productPart.setId(new Long(1001)); 
//			newObjectId.setProductPart(productPart);
//
//			//Effacer un objet par un identifiant qui n'existe pas
//			Object[] objs = new Object[2];
//			Type[] types = new Type[2];
//			objs[0] = newObjectId.getLocale().getId();
//			objs[1] = newObjectId.getProductPart().getId();
//			types[0] = new StringType();
//			types[1] = new LongType();
//			int res = session.delete("FROM ProductPartLanguage as productPartLanguage WHERE productPartLanguage.id.locale.id=? AND productPartLanguage.id.productPart.id=?", objs, types);
//			assertEquals(res, 0);
//
//			ProductPartLanguage newObject = new ProductPartLanguage();
//			newObject.setId(newObjectId);
//			newObject.setLabel("Toto");
//			session.save(locale);			
//			session.save(productPart);
//			//Sauver un seul objet			
//			session.save(newObject);
//
//			//Récupérer un objet par son identifiant
//			ProductPartLanguage loadObj = (ProductPartLanguage)session.load(ProductPartLanguage.class, newObject.getId());
//			assertEquals(newObject.getId().getProductPart().getId(), loadObj.getId().getProductPart().getId());
//			assertEquals(newObject.getId().getLocale().getId(), loadObj.getId().getLocale().getId());
//			
//			//Effacer un seul objet 
//			session.delete(newObject);
//			session.delete(locale);			
//			session.delete(productPart);
//
//			//Récupérer un objet par son identifiant qui n'existe pas
//			ObjectDeletedException odeTest =null;
//			try
//			{
//				session.load(ProductPartLanguage.class, newObject.getId());
//			}
//			catch(ObjectDeletedException ode)
//			{
//				odeTest = ode;
//			}
//			assertNotNull(odeTest);
//
//			tx.commit();
//			HibernateUtil.closeSession();
//		}
//		catch (HibernateException he)
//		{
//			he.printStackTrace();
//			throw new AssertionFailedError(he.getMessage());			
//		}
//		long end = System.currentTimeMillis();
//		log.debug("Temps écoulé en seconde sur le test : " + (end - start) / 1000);
//	}
//
//	/** 
//	* Cette méthode teste la table t_product_part.
//	* Les actionsde ce test sont les suivantes : <BR/>
//	* Effacer toutes les données de la table t_product_part. <BR/>
//	* Insérer des données dans la table t_product_part. <BR/>
//	* S'assurer que les données ont bien été insérées dans la table t_product_part. <BR/>
//	* Effacer un objet par un identifiant qui n'existe pas. <BR/>
//	* Sauver un seul objet. <BR/>
//	* Récupérer un objet par son identifiant. <BR/>
//	* Effacer un seul objet. <BR/>
//	* Récupérer un objet par son identifiant qui n'existe pas. <BR/>
//	*/
//	public void testProductPart()
//	{
//		long start = System.currentTimeMillis();		
//		try
//		{
//			Session session = HibernateUtil.currentSession();
//			Transaction tx = session.beginTransaction();
//
//			//Effacer en cascade les données de la table t_product_part
//			delete(T_PRODUCTPART, session);
//			
//			//Liste des parties de commandes
//			ArrayList insertList = new ArrayList();
//			//Insertion des données dans la table t_product_part
//			fulfillProductPart(session, insertList);
//
//			int length = insertList.size();
//			//Récupérer la liste précédement insérée dans la table t_product_part
//			List listObj = session.find("FROM ProductPart");
//
//			//Test l'égalité des longueurs des 2 listes
//			assertEquals(length, listObj.size());
//			
//			//Test l'égalité des 2 listes
//			ProductPart insertObj = null;
//			for (int i = 0; i < length; i++)
//			{
//				int j = 0;
//				ProductPart obj = null;
//				insertObj = (ProductPart)insertList.get(i);
//				for (; j < length; j++)
//				{
//					obj = (ProductPart) listObj.get(j);
//					
//					if (	insertObj.getId().equals(obj.getId())	)
//						break;
//				}
//				if (j == length)
//					assertFalse("La liste insérée n'est pas identique à liste récupérée", true);
//			}
//
//			Long newObjectId = new Long(101);
//			//Effacer un objet par un identifiant qui n'existe pas
//			int res = session.delete("FROM ProductPart as productPart WHERE productPart.id=?", newObjectId, new LongType());
//			assertEquals(res, 0);
//			
//			ProductPart newObject = new ProductPart();
//			//Sauver un seul objet			
//			session.save(newObject);
//
//			//Récupérer un objet par son identifiant
//			ProductPart loadObj = (ProductPart)session.load(ProductPart.class, newObject.getId());
//			assertEquals(newObject.getId(), loadObj.getId());
//			
//			//Effacer un seul objet 
//			session.delete(newObject);
//			//Récupérer un objet par son identifiant qui n'existe pas
//			ObjectDeletedException odeTest =null;
//			try
//			{
//				session.load(ProductPart.class, newObject.getId());
//			}
//			catch(ObjectDeletedException ode)
//			{
//				odeTest = ode;
//			}
//			assertNotNull(odeTest);
//
//			tx.commit();
//			HibernateUtil.closeSession();
//		}
//		catch (HibernateException he)
//		{
//			he.printStackTrace();
//			throw new AssertionFailedError(he.getMessage());			
//		}
//		long end = System.currentTimeMillis();
//		log.debug("Temps écoulé en seconde sur le test : " + (end - start) / 1000);
//	}
//
//	/**
//	 * Cette méthode permet de remplir la table t_product_part avec toutes ses contraintes.
//	 */
//	private void fulfillProductPart(Session session, ArrayList insertList) throws HibernateException
//	{
//		int length = 100;
//		//Insertion des données dans la table t_product_part
//		for(int i=0; i<length;i++)
//		{
//			ProductPart productPart = new ProductPart();				
//			session.save(productPart);
//			insertList.add(productPart);
//		}
//	}
//
//	/** 
// 	* Cette méthode teste la table t_product.
//	* Les actionsde ce test sont les suivantes : <BR/>
//	* Effacer toutes les données de la table t_product. <BR/>
//	* Insérer des données dans la table t_product. <BR/>
//	* S'assurer que les données ont bien été insérées dans la table t_product. <BR/>
//	* Effacer un objet par un identifiant qui n'existe pas. <BR/>
//	* Sauver un seul objet. <BR/>
//	* Récupérer un objet par son identifiant. <BR/>
//	* Effacer un seul objet. <BR/>
//	* Récupérer un objet par son identifiant qui n'existe pas. <BR/>
//	*/
//	public void testProduct()
//	{
//		long start = System.currentTimeMillis();		
//		try
//		{
//			//Effacer toutes les tables de la base
//			testDeleteAll();
//
//			Session session = HibernateUtil.currentSession();
//			Transaction tx = session.beginTransaction();
//			
//			//Utiliser la liste des Locale pour les codes produit
//			List insertListA = Arrays.asList(java.util.Locale.getISOLanguages());
//			float price = (float)(Math.random());
//			float vat = (float)(10.22);
//			ArrayList insertList = new ArrayList();
//			int length = insertListA.size();
//
//			//Liste des données à insérer dans la table t_product et des libellés suivant les langages
//			fulfillProductLanguage(session, new ArrayList(), insertList);
//			
//			//Récupérer la liste précédement insérée dans la table t_product
//			List listObj = session.find("FROM Product");
//
//			//Test l'égalité des longueurs des 2 listes
//			assertEquals(length, listObj.size());
//			
//			//Test l'égalité des 2 listes
//			Product insertObj = null;
//			for (int i = 0; i < length; i++)
//			{
//				int j = 0;
//				Product obj = null;
//				insertObj = (Product)insertList.get(i);
//				for (; j < length; j++)
//				{
//					obj = (Product) listObj.get(j);
//					
//					if (	insertObj.getId().equals(obj.getId()) &&
//							insertObj.getPrice()==obj.getPrice() &&
//							insertObj.getProductSpecialCode().getId().longValue()==obj.getProductSpecialCode().getId().longValue() &&
//							insertObj.getProductSpecialCode().getCode().equals(obj.getProductSpecialCode().getCode()) &&
//							insertObj.getVat().getId().longValue()==obj.getVat().getId().longValue() &&
//							insertObj.getVat().getValue()==obj.getVat().getValue()
//						)
//						break;
//				}
//				if (j == length)
//					assertFalse("La liste insérée n'est pas identique à liste récupérée", true);
//			}
//
//			String newObjectId = "xx";
//			//Effacer un objet par un identifiant qui n'existe pas
//			int res = session.delete("FROM Product as product WHERE product.id=?", newObjectId, new StringType());
//			assertEquals(res, 0);
//			
//			Product newObject = new Product();
//			newObject.setId("Toto");
//			newObject.setPrice(price);
//			ValueAddedTax valueAddedTax = new ValueAddedTax();
//			valueAddedTax.setValue(vat);
//			List valueAddedTaxList = session.find("FROM ValueAddedTax AS valueAddedTax WHERE valueAddedTax.value='"+valueAddedTax.getValue()+"'");
//			if(valueAddedTaxList.size()==1)
//				valueAddedTax = (ValueAddedTax)valueAddedTaxList.get(0); 
//			else 
//				valueAddedTax.setId((Long)session.save(valueAddedTax));			
//			newObject.setVat(valueAddedTax);
//			//Sauver un seul objet			
//			newObject.setId((String)session.save(newObject));
//
//			//Récupérer un objet par son identifiant
//			Product loadObj = (Product)session.load(Product.class, newObject.getId());
//			assertEquals(newObject.getId(), loadObj.getId());
//			assertEquals(new Float(newObject.getPrice()), new Float(loadObj.getPrice()));
//			assertEquals(newObject.getProductSpecialCode(), loadObj.getProductSpecialCode());
//			assertEquals(newObject.getVat(), loadObj.getVat());
//			
//			//Effacer un seul objet 
//			session.delete(newObject);
//			//Récupérer un objet par son identifiant qui n'existe pas
//			ObjectDeletedException odeTest =null;
//			try
//			{
//				session.load(Product.class, newObject.getId());
//			}
//			catch(ObjectDeletedException ode)
//			{
//				odeTest = ode;
//			}
//			assertNotNull(odeTest);
//
//			tx.commit();
//			session.close();
//		}
//		catch (HibernateException he)
//		{
//			he.printStackTrace();
//			throw new AssertionFailedError(he.getMessage());			
//		}
//		long end = System.currentTimeMillis();
//		log.debug("Temps écoulé en seconde sur le test : " + (end - start) / 1000);
//	}
//
//	/**
//	 * Cette méthode permet de remplir la table t_product avec toutes ses contraintes.
//	 */
//	private void fulfillProduct(Session session, ArrayList insertList) throws HibernateException
//	{
//		//Utiliser la liste des Locale pour les codes produit
//		List insertListA = Arrays.asList(java.util.Locale.getISOLanguages());
//		//Liste des données à insérer dans la table t_product
//		float price = (float)(Math.random());
//		float vat = (float)(10.22);
//				
//		int length = insertListA.size();
//		for(int i=0; i<length;i++)
//		{
//			ProductSpecialCode productSpecialCode = new ProductSpecialCode();				
//			ValueAddedTax valueAddedTax = new ValueAddedTax();
//			Product product = new Product();
//			product.setId((String)insertListA.get(i));
//
//			productSpecialCode.setCode("Code"+(String)insertListA.get(i));
//			List productSpecialCodeList = session.find("FROM ProductSpecialCode AS productSpecialCode WHERE productSpecialCode.code='"+productSpecialCode.getCode()+"'");
//
//			if(productSpecialCodeList.size()==1)
//				productSpecialCode = (ProductSpecialCode)productSpecialCodeList.get(0); 
//			else 
//				productSpecialCode.setId((Long)session.save(productSpecialCode));
//			product.setProductSpecialCode(productSpecialCode);
//
//			product.setPrice(price+i);
//				
//			valueAddedTax.setValue(vat);
//			List valueAddedTaxList = session.find("FROM ValueAddedTax AS valueAddedTax WHERE valueAddedTax.value='"+valueAddedTax.getValue()+"'");
//			if(valueAddedTaxList.size()==1)
//				valueAddedTax = (ValueAddedTax)valueAddedTaxList.get(0); 
//			else 
//				valueAddedTax.setId((Long)session.save(valueAddedTax));
//			product.setVat(valueAddedTax);
//
//			session.save(product);
//			insertList.add(product);
//		}
//	}
//
//	/** 
// 	* Cette méthode teste la table t_product_special_code.
//	* Les actionsde ce test sont les suivantes : <BR/>
//	* Effacer toutes les données de la table t_product_special_code. <BR/>
//	* Insérer des données dans la table t_product_special_code. <BR/>
//	* S'assurer que les données ont bien été insérées dans la table t_product_special_code. <BR/>
//	* Effacer un objet par un identifiant qui n'existe pas. <BR/>
//	* Sauver un seul objet. <BR/>
//	* Récupérer un objet par son identifiant. <BR/>
//	* Effacer un seul objet. <BR/>
//	* Récupérer un objet par son identifiant qui n'existe pas. <BR/>
//	*/
//	public void testProductSpecialCode()
//	{
//		long start = System.currentTimeMillis();
//		try
//		{
//			Session session = HibernateUtil.currentSession();
//			Transaction tx = session.beginTransaction();
//
//			//Effacer en cascade les données de la table t_product_special_code
//			delete(T_PRODUCTSPECIALCODE, session);
//			
//			//Utiliser la liste des Locale pour les libellés
//			List insertListA = Arrays.asList(java.util.Locale.getISOLanguages());
//			//Liste des données à insérer dans la table t_product_special_code
//			ArrayList insertList = new ArrayList();
//			for(int i=0; i<insertListA.size();i++)
//			{
//				ProductSpecialCode productSpecialCode = new ProductSpecialCode();
//				String locale = (String)insertListA.get(i);
//				productSpecialCode.setCode("Code"+locale);
//				productSpecialCode.setId((Long)session.save(productSpecialCode));
//				insertList.add(productSpecialCode);
//			}
//			
//			//Récupérer la liste précédement insérée dans la table t_product_special_code
//			List listObj = session.find("FROM ProductSpecialCode");
//
//			int length = insertList.size();
//			//Test l'égalité des longueurs des 2 listes
//			assertEquals(length, listObj.size());
//			
//			//Test l'égalité des 2 listes
//			ProductSpecialCode insertObj = null;
//			for (int i = 0; i < length; i++)
//			{
//				int j = 0;
//				ProductSpecialCode obj = null;
//				insertObj = (ProductSpecialCode)insertList.get(i);
//				for (; j < length; j++)
//				{
//					obj = (ProductSpecialCode) listObj.get(j);
//					
//					if (	insertObj.getId().equals(obj.getId()) && 
//							insertObj.getCode().equals(obj.getCode())
//						)
//						break;
//				}
//				if (j == length)
//					assertFalse("La liste insérée n'est pas identique à liste récupérée", true);
//			}
//
//			Long newObjectId = new Long(length+1);
//			//Effacer un objet par un identifiant qui n'existe pas
//			int res = session.delete("FROM ProductSpecialCode as productSpecialCode WHERE productSpecialCode.id=?", newObjectId, new LongType());
//			assertEquals(res, 0);
//			
//			ProductSpecialCode newObject = new ProductSpecialCode();
//			newObject.setCode("CodeToto");
//			//Sauver un seul objet			
//			newObject.setId((Long)session.save(newObject));
//
//			//Récupérer un objet par son identifiant
//			ProductSpecialCode loadObj = (ProductSpecialCode)session.load(ProductSpecialCode.class, newObject.getId());
//			assertEquals(newObject.getId().longValue(), loadObj.getId().longValue());
//			assertEquals(newObject.getCode(), loadObj.getCode());
//			
//			//Effacer un seul objet 
//			session.delete(newObject);
//			//Récupérer un objet par son identifiant qui n'existe pas
//			ObjectDeletedException odeTest =null;
//			try
//			{
//				session.load(ProductSpecialCode.class, newObject.getId());
//			}
//			catch(ObjectDeletedException ode)
//			{
//				odeTest = ode;
//			}
//			assertNotNull(odeTest);
//
//			tx.commit();
//			HibernateUtil.closeSession();
//		}
//		catch (HibernateException he)
//		{
//			he.printStackTrace();
//			throw new AssertionFailedError(he.getMessage());			
//		}
//		long end = System.currentTimeMillis();
//		log.debug("Temps écoulé en seconde sur le test : " + (end - start) / 1000);
//	}
//
//	/** 
// 	* Cette méthode teste la table t_value_added_tax.
//	* Les actionsde ce test sont les suivantes : <BR/>
//	* Effacer toutes les données de la table t_value_added_tax. <BR/>
//	* Insérer des données dans la table t_value_added_tax. <BR/>
//	* S'assurer que les données ont bien été insérées dans la table t_value_added_tax. <BR/>
//	* Effacer un objet par un identifiant qui n'existe pas. <BR/>
//	* Sauver un seul objet. <BR/>
//	* Récupérer un objet par son identifiant. <BR/>
//	* Effacer un seul objet. <BR/>
//	* Récupérer un objet par son identifiant qui n'existe pas. <BR/>
//	*/
//	public void testValueAddedTax()
//	{
//		long start = System.currentTimeMillis();
//		try
//		{
//			Session session = HibernateUtil.currentSession();
//			Transaction tx = session.beginTransaction();
//
//			//Effacer en cascade les données de la table t_value_added_tax
//			delete(T_VALUEADDEDTAX, session);
//			
//			//Liste des données à insérer dans la table t_value_added_tax
//			ArrayList insertList = new ArrayList(100);
//			float value = (float)Math.random()+2;
//			for(int i=0; i<100;i++)
//			{
//				ValueAddedTax valueAddedTax = new ValueAddedTax();
//				valueAddedTax.setValue(value+i);
//				valueAddedTax.setId((Long)session.save(valueAddedTax));
//				insertList.add(valueAddedTax);
//			}
//			
//			//Récupérer la liste précédement insérée dans la table t_value_added_tax
//			List listObj = session.find("FROM ValueAddedTax");
//
//			int length = insertList.size();
//			//Test l'égalité des longueurs des 2 listes
//			assertEquals(length, listObj.size());
//			
//			//Test l'égalité des 2 listes
//			ValueAddedTax insertObj = null;
//			for (int i = 0; i < length; i++)
//			{
//				int j = 0;
//				ValueAddedTax obj = null;
//				insertObj = (ValueAddedTax)insertList.get(i);
//				for (; j < length; j++)
//				{
//					obj = (ValueAddedTax) listObj.get(j);
//					
//					if (	insertObj.getId().equals(obj.getId()) && 
//							insertObj.getValue()==(obj.getValue())
//						)
//						break;
//				}
//				if (j == length)
//					assertFalse("La liste insérée n'est pas identique à liste récupérée", true);
//			}
//
//			Long newObjectId = new Long(length+1);
//			//Effacer un objet par un identifiant qui n'existe pas
//			int res = session.delete("FROM ValueAddedTax as valueAddedTax WHERE valueAddedTax.id=?", newObjectId, new LongType());
//			assertEquals(res, 0);
//			
//			ValueAddedTax newObject = new ValueAddedTax();
//			newObject.setValue(1);
//			//Sauver un seul objet			
//			newObject.setId((Long)session.save(newObject));
//
//			//Récupérer un objet par son identifiant
//			ValueAddedTax loadObj = (ValueAddedTax)session.load(ValueAddedTax.class, newObject.getId());
//			assertEquals(newObject.getId().longValue(), loadObj.getId().longValue());
//			assertEquals(new Float(newObject.getValue()), new Float(loadObj.getValue()));
//			
//			//Effacer un seul objet 
//			session.delete(newObject);
//			//Récupérer un objet par son identifiant qui n'existe pas
//			ObjectDeletedException odeTest =null;
//			try
//			{
//				session.load(ValueAddedTax.class, newObject.getId());
//			}
//			catch(ObjectDeletedException ode)
//			{
//				odeTest = ode;
//			}
//			assertNotNull(odeTest);
//
//			tx.commit();
//			HibernateUtil.closeSession();
//		}
//		catch (HibernateException he)
//		{
//			he.printStackTrace();
//			throw new AssertionFailedError(he.getMessage());			
//		}
//		long end = System.currentTimeMillis();
//		log.debug("Temps écoulé en seconde sur le test : " + (end - start) / 1000);
//	}
//
//	/** 
// 	* Cette méthode teste la table t_category_language.
//	* Les actionsde ce test sont les suivantes : <BR/>
//	* Effacer toutes les données de la table t_category_language. <BR/>
//	* Insérer des données dans la table t_category_language. <BR/>
//	* S'assurer que les données ont bien été insérées dans la table t_category_language. <BR/>
//	* Effacer un objet par un identifiant qui n'existe pas. <BR/>
//	* Sauver un seul objet. <BR/>
//	* Récupérer un objet par son identifiant. <BR/>
//	* Effacer un seul objet. <BR/>
//	* Récupérer un objet par son identifiant qui n'existe pas. <BR/>
//	*/
//	public void testCategoryLanguage()
//	{
//		long start = System.currentTimeMillis();
//		try
//		{
//			//Effacer en cascade les données de la table t_category_language
//			delete(T_LOCALE);
//
//			Session session = HibernateUtil.currentSession();
//			Transaction tx = session.beginTransaction();
//			
//			//Liste des données à insérer dans la table t_category_language
//			ArrayList insertList = new ArrayList();
//			fulfillCategoryLanguage(session, insertList, 40);
//						
//			//Récupérer la liste précédement insérée dans la table t_category_language
//			List listObj = session.find("FROM CategoryLanguage");
//
//			int length = insertList.size();
//			//Test l'égalité des longueurs des 2 listes
//			assertEquals(length, listObj.size());
//			
//			//Test l'égalité des 2 listes
//			CategoryLanguage insertObj = null;
//			for (int i = 0; i < length; i++)
//			{
//				int j = 0;
//				CategoryLanguage obj = null;
//				insertObj = (CategoryLanguage)insertList.get(i);
//				for (; j < length; j++)
//				{
//					obj = (CategoryLanguage) listObj.get(j);
//					
//					if (	insertObj.getId().getLocale().getId().equals(obj.getId().getLocale().getId()) &&
//							insertObj.getId().getCategory().getId().longValue()==obj.getId().getCategory().getId().longValue()	
//						)
//						break;
//						
//				}
//				if (j == length)
//					assertFalse("La liste insérée n'est pas identique à liste récupérée", true);
//			}
//
//			CategoryLanguageID newObjectId = new CategoryLanguageID();
//			Locale locale = new Locale();
//			locale.setId((String)"xxx");
//			newObjectId.setLocale(locale);
//			Category category = new Category();
//			category.setId(new Long(1001)); 
//			newObjectId.setCategory(category);
//
//			//Effacer un objet par un identifiant qui n'existe pas
//			Object[] objs = new Object[2];
//			Type[] types = new Type[2];
//			objs[0] = newObjectId.getLocale().getId();
//			objs[1] = newObjectId.getCategory().getId();
//			types[0] = new StringType();
//			types[1] = new LongType();
//			int res = session.delete("FROM CategoryLanguage as categoryLanguage WHERE categoryLanguage.id.locale.id=? AND categoryLanguage.id.category.id=?", objs, types);
//			assertEquals(res, 0);
//
//			CategoryLanguage newObject = new CategoryLanguage();
//			newObject.setId(newObjectId);
//			newObject.setLabel("Toto");
//			session.save(locale);			
//			session.save(category);
//			//Sauver un seul objet			
//			session.save(newObject);
//
//			//Récupérer un objet par son identifiant
//			CategoryLanguage loadObj = (CategoryLanguage)session.load(CategoryLanguage.class, newObject.getId());
//			assertEquals(newObject.getId().getCategory().getId(), loadObj.getId().getCategory().getId());
//			assertEquals(newObject.getId().getLocale().getId(), loadObj.getId().getLocale().getId());
//			assertEquals(newObject.getLabel(), loadObj.getLabel());
//			
//			//Effacer un seul objet 
//			session.delete(newObject);
//			session.delete(locale);			
//			session.delete(category);
//
//			//Récupérer un objet par son identifiant qui n'existe pas
//			ObjectDeletedException odeTest =null;
//			try
//			{
//				session.load(CategoryLanguage.class, newObject.getId());
//			}
//			catch(ObjectDeletedException ode)
//			{
//				odeTest = ode;
//			}
//			assertNotNull(odeTest);
//
//			tx.commit();
//			HibernateUtil.closeSession();
//		}
//		catch (HibernateException he)
//		{
//			he.printStackTrace();
//			throw new AssertionFailedError(he.getMessage());			
//		}
//		long end = System.currentTimeMillis();
//		log.debug("Temps écoulé en seconde sur le test : " + (end - start) / 1000);
//	}
//
//	/** 
//	* Cette méthode teste la table t_category.
//	* Les actionsde ce test sont les suivantes : <BR/>
//	* Effacer toutes les données de la table t_category. <BR/>
//	* Insérer des données dans la table t_category. <BR/>
//	* S'assurer que les données ont bien été insérées dans la table t_category. <BR/>
//	* Effacer un objet par un identifiant qui n'existe pas. <BR/>
//	* Sauver un seul objet. <BR/>
//	* Récupérer un objet par son identifiant. <BR/>
//	* Effacer un seul objet. <BR/>
//	* Récupérer un objet par son identifiant qui n'existe pas. <BR/>
//	*/
//	public void testCategory()
//	{
//		long start = System.currentTimeMillis();		
//		try
//		{
//			//Effacer en cascade les données de la table t_category
//			delete(T_CATEGORY);
//
//			Session session = HibernateUtil.currentSession();
//			Transaction tx = session.beginTransaction();
//			
//			//Liste des parties de catégories
//			ArrayList insertList = new ArrayList();
//			//Insertion des données dans la table t_category
//			fulfillCategory(session, insertList, 40);
//
//			int length = insertList.size();
//			//Récupérer la liste précédement insérée dans la table t_category
//			List listObj = session.find("FROM Category");
//
//			//Test l'égalité des longueurs des 2 listes
//			assertEquals(length, listObj.size());
//			
//			//Test l'égalité des 2 listes
//			Category insertObj = null;
//			for (int i = 0; i < length; i++)
//			{
//				int j = 0;
//				Category obj = null;
//				insertObj = (Category)insertList.get(i);
//				for (; j < length; j++)
//				{
//					obj = (Category) listObj.get(j);
//					
//					if (	insertObj.getId().equals(obj.getId())	)
//						break;
//				}
//				if (j == length)
//					assertFalse("La liste insérée n'est pas identique à liste récupérée", true);
//			}
//
//			Long newObjectId = new Long(101);
//			//Effacer un objet par un identifiant qui n'existe pas
//			int res = session.delete("FROM Category as category WHERE category.id=?", newObjectId, new LongType());
//			assertEquals(res, 0);
//			
//			Category newObject = new Category();
//			//Sauver un seul objet			
//			session.save(newObject);
//
//			//Récupérer un objet par son identifiant
//			Category loadObj = (Category)session.load(Category.class, newObject.getId());
//			assertEquals(newObject.getId(), loadObj.getId());
//			
//			//Effacer un seul objet 
//			session.delete(newObject);
//			//Récupérer un objet par son identifiant qui n'existe pas
//			ObjectDeletedException odeTest =null;
//			try
//			{
//				session.load(Category.class, newObject.getId());
//			}
//			catch(ObjectDeletedException ode)
//			{
//				odeTest = ode;
//			}
//			assertNotNull(odeTest);
//
//			tx.commit();
//			HibernateUtil.closeSession();
//		}
//		catch (HibernateException he)
//		{
//			he.printStackTrace();
//			throw new AssertionFailedError(he.getMessage());			
//		}
//		long end = System.currentTimeMillis();
//		log.debug("Temps écoulé en seconde sur le test : " + (end - start) / 1000);
//	}
//
//	/**
//	 * Cette méthode permet de remplir la table t_category avec toutes ses contraintes.
//	 */
//	private void fulfillCategory(Session session, ArrayList insertList, int size) throws HibernateException
//	{
//		//Insère la liste dans la table t_category
//		for (int i = 0; i < size; i++)
//		{
//			Category category = new Category();
//			category.setId((Long)session.save(category));
//			insertList.add(category);
//		}
//	}
//
//	/**
//	 * Cette méthode permet de remplir la table t_category_language avec toutes ses contraintes.
//	 */
//	private void fulfillCategoryLanguage(Session session, ArrayList insertList, int size) throws HibernateException
//	{
//		//Liste contenant des catégories
//		ArrayList insertListA = new ArrayList();
//		fulfillCategory(session, insertListA, size);		
//		
//		//Récupérer la liste des langues de code ISO 639 de l'api Java
//		String[] listLanguages = java.util.Locale.getISOLanguages();
//		//Liste contenant les locales
//		List insertListB = new ArrayList();		
//		int length = listLanguages.length;
//		if(size>length)
//			size = length;
//		//Insère la liste dans la table t_locale
//		for (int i = 0; i < size; i++)
//		{
//			Locale locale = new Locale();
//			locale.setId(listLanguages[i]);
//			session.save(locale);
//			insertListB.add(locale);
//		}
//
//		for(int i=0; i<size; i++)
//		{
//			for(int j=0; j<size; j++)
//			{
//				CategoryLanguage categoryLanguage = new CategoryLanguage();
//				CategoryLanguageID categoryLanguageID = new CategoryLanguageID();
//				categoryLanguageID.setCategory((Category)insertListA.get(i));
//				categoryLanguageID.setLocale((Locale)insertListB.get(j));
//				categoryLanguage.setLabel("Label"+((Locale)insertListB.get(j)).getId());
//				categoryLanguage.setId(categoryLanguageID);
//				session.save(categoryLanguage);
//				insertList.add(categoryLanguage);
//			}
//		}
//	}
//
//	/** 
//	* Cette méthode teste la table t_user_locale.
//	* Les actionsde ce test sont les suivantes : <BR/>
//	* Effacer toutes les données de la table t_user_locale. <BR/>
//	* Insérer des données dans la table t_user_locale. <BR/>
//	* S'assurer que les données ont bien été insérées dans la table t_user_locale. <BR/>
//	* Effacer un objet par un identifiant qui n'existe pas. <BR/>
//	* Sauver un seul objet. <BR/>
//	* Récupérer un objet par son identifiant. <BR/>
//	* Effacer un seul objet. <BR/>
//	* Récupérer un objet par son identifiant qui n'existe pas. <BR/>
//	*/
//	public void testUserLocale()
//	{
//		long start = System.currentTimeMillis();		
//		try
//		{
//			Session session = HibernateUtil.currentSession();
//			Transaction tx = session.beginTransaction();
//
//			//Effacer en cascade les données de la table t_user_locale
//			delete(T_LOCALE, session);
//
//			//Récupérer la liste des utilisateurs
//			ArrayList insertListA = new ArrayList();
//			fulfillUser(session, insertListA, 40);
//
//			//Récupérer la liste des langues de code ISO 639 de l'api Java
//			List insertListB = new ArrayList();
//			List insertListBis = Arrays.asList(java.util.Locale.getISOLanguages());
//			int length = 40;
//			for (int i = 0; i < length; i++)
//			{
//				Locale localeTemp = new Locale();
//				localeTemp.setId((String)insertListBis.get(i));
//				session.save(localeTemp);
//				insertListB.add(localeTemp);
//			}			
//
//			//Liste des données à insérer dans la table t_user_locale
//			ArrayList insertList = new ArrayList();
//			for(int i=0; i<insertListA.size();i++)
//			{
//				for(int j=0; j<insertListB.size();j++)
//				{
//					UserLocale userLocale = new UserLocale();
//					userLocale.setUser((User)insertListA.get(i));
//					userLocale.setLocale((Locale)insertListB.get(j));
//					userLocale.setId((Long)session.save(userLocale));
//					insertList.add(userLocale);
//				}
//			}
//			
//			session.flush();
//			
//			//Récupérer la liste précédement insérée dans la table t_user_locale
//			List listObj = session.find("FROM UserLocale");
//
//			//Test l'égalité des longueurs des 2 listes
//			assertEquals(insertList.size(), listObj.size());
//			
//			//Test l'égalité des 2 listes
//			UserLocale insertObj = null;
//			for (int i = 0; i < insertList.size(); i++)
//			{
//				int j = 0;
//				UserLocale obj = null;
//				insertObj = (UserLocale)insertList.get(i);
//				for (; j < insertList.size(); j++)
//				{
//					obj = (UserLocale) listObj.get(j);
//					
//					if (	insertObj.getId().equals(obj.getId()) 	)
//						break;
//				}
//				if (j == insertList.size())
//					assertFalse("La liste insérée n'est pas identique à liste récupérée", true);
//			}
//
//			Long newObjectId = new Long(insertList.size()+1);
//			//Effacer un objet par un identifiant qui n'existe pas
//			int res = session.delete("FROM UserLocale as userLocale WHERE userLocale.id=?", newObjectId, new LongType());
//			assertEquals(res, 0);
//			
//			UserLocale newObject = new UserLocale();
//			newObject.setUser((User)insertListA.get(0));
//			Locale locale = new Locale();
//			locale.setId("sfr");
//			session.save(locale);
//			newObject.setLocale(locale);
//			//Sauver un seul objet			
//			newObject.setId((Long)session.save(newObject));
//
//			//Récupérer un objet par son identifiant
//			UserLocale loadObj = (UserLocale)session.load(UserLocale.class, newObject.getId());
//			assertEquals(newObject.getId().longValue(), loadObj.getId().longValue());
//			assertEquals(newObject.getUser().getId().longValue(), loadObj.getUser().getId().longValue());
//			assertEquals(newObject.getUser().getForename1(), loadObj.getUser().getForename1());
//			assertEquals(newObject.getUser().getForename2(), loadObj.getUser().getForename2());
//			assertEquals(newObject.getUser().getName(), loadObj.getUser().getName());
//			assertEquals(newObject.getUser().getBirthdate(), loadObj.getUser().getBirthdate());
//			assertEquals(newObject.getUser().getSex(), loadObj.getUser().getSex());
//			assertEquals(newObject.getUser().getPicture(), loadObj.getUser().getPicture());
//			assertEquals(newObject.getUser().getUserAuthentication().getId().longValue(), loadObj.getUser().getUserAuthentication().getId().longValue());
//			assertEquals(newObject.getUser().getUserAuthentication().getLogin(), loadObj.getUser().getUserAuthentication().getLogin());
//			assertEquals(newObject.getUser().getUserAuthentication().getPassword(), loadObj.getUser().getUserAuthentication().getPassword());
//			assertEquals(newObject.getUser().getUserAuthentication().getLevelPass1(), loadObj.getUser().getUserAuthentication().getLevelPass1());
//			assertEquals(newObject.getUser().getUserAuthentication().getLevelPass2(), loadObj.getUser().getUserAuthentication().getLevelPass2());
//			assertEquals(newObject.getUser().getUserAuthentication().getLevelPass3(), loadObj.getUser().getUserAuthentication().getLevelPass3());
//			assertEquals(newObject.getUser().getUserRole().getId().longValue(), loadObj.getUser().getUserRole().getId().longValue());
//			assertEquals(newObject.getUser().getUserRole().getLabelCode(), loadObj.getUser().getUserRole().getLabelCode());
//			assertEquals(newObject.getLocale().getId(), loadObj.getLocale().getId());
//			
//			//Effacer un seul objet 
//			session.delete(newObject);
//			//Récupérer un objet par son identifiant qui n'existe pas
//			ObjectDeletedException odeTest =null;
//			try
//			{
//				session.load(UserLocale.class, newObject.getId());
//			}
//			catch(ObjectDeletedException ode)
//			{
//				odeTest = ode;
//			}
//			assertNotNull(odeTest);
//
//			tx.commit();
//			HibernateUtil.closeSession();
//		}
//		catch (HibernateException he)
//		{
//			he.printStackTrace();
//			throw new AssertionFailedError(he.getMessage());			
//		}
//		long end = System.currentTimeMillis();
//		log.debug("Temps écoulé en seconde sur le test : " + (end - start) / 1000);
//	}
//
//	/**
//	 * Cette méthode permet de remplir la table t_user avec toutes ses contraintes.
//	 */
//	private void fulfillUser(Session session, ArrayList insertList, int size) throws HibernateException
//	{
//		//Utiliser la liste des Locale pour les noms, les prénoms 1 et 2
//		List insertListA = Arrays.asList(java.util.Locale.getISOLanguages());
//		byte[] picture = {(byte)255, (byte)216, (byte)255, (byte)224, (byte)0, (byte)16, (byte)74, (byte)70, (byte)73, (byte)70, (byte)0, (byte)1, (byte)1, (byte)1, (byte)0, (byte)72, (byte)0, (byte)72, (byte)0, (byte)0, (byte)255, (byte)219, (byte)0, (byte)67, (byte)0, (byte)8, (byte)6, (byte)6, (byte)7, (byte)6, (byte)5, (byte)8, (byte)7, (byte)7, (byte)7, (byte)9, (byte)9, (byte)8, (byte)10, (byte)12, (byte)20, (byte)13, (byte)12, (byte)11, (byte)11, (byte)12, (byte)25, (byte)18, (byte)19, (byte)15, (byte)20, (byte)29, (byte)26, (byte)31, (byte)30, (byte)29, (byte)26, (byte)28, (byte)28, (byte)32, (byte)36, (byte)46, (byte)39, (byte)32, (byte)34, (byte)44, (byte)35, (byte)28, (byte)28, (byte)40, (byte)55, (byte)41, (byte)44, (byte)48, (byte)49, (byte)52, (byte)52, (byte)52, (byte)31, (byte)39, (byte)57, (byte)61, (byte)56, (byte)50, (byte)60, (byte)46, (byte)51, (byte)52, (byte)50, (byte)255, (byte)219, (byte)0, (byte)67, (byte)1, (byte)9, (byte)9, (byte)9, (byte)12, (byte)11, (byte)12, (byte)24, (byte)13, (byte)13, (byte)24, (byte)50, (byte)33, (byte)28, (byte)33, (byte)50, (byte)50, (byte)50, (byte)50, (byte)50, (byte)50, (byte)50, (byte)50, (byte)50, (byte)50, (byte)50, (byte)50, (byte)50, (byte)50, (byte)50, (byte)50, (byte)50, (byte)50, (byte)50, (byte)50, (byte)50, (byte)50, (byte)50, (byte)50, (byte)50, (byte)50, (byte)50, (byte)50, (byte)50, (byte)50, (byte)50, (byte)50, (byte)50, (byte)50, (byte)50, (byte)50, (byte)50, (byte)50, (byte)50, (byte)50, (byte)50, (byte)50, (byte)50, (byte)50, (byte)50, (byte)50, (byte)50, (byte)50, (byte)50, (byte)50, (byte)255, (byte)192, (byte)0, (byte)17, (byte)8, (byte)0, (byte)91, (byte)0, (byte)91, (byte)3, (byte)1, (byte)17, (byte)0, (byte)2, (byte)17, (byte)1, (byte)3, (byte)17, (byte)1, (byte)255, (byte)196, (byte)0, (byte)28, (byte)0, (byte)1, (byte)0, (byte)2, (byte)2, (byte)3, (byte)1, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)6, (byte)7, (byte)4, (byte)8, (byte)1, (byte)3, (byte)5, (byte)2, (byte)255, (byte)196, (byte)0, (byte)52, (byte)16, (byte)0, (byte)2, (byte)1, (byte)3, (byte)2, (byte)4, (byte)5, (byte)2, (byte)5, (byte)2, (byte)7, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)1, (byte)2, (byte)3, (byte)0, (byte)4, (byte)17, (byte)5, (byte)6, (byte)18, (byte)33, (byte)49, (byte)65, (byte)7, (byte)19, (byte)81, (byte)97, (byte)113, (byte)20, (byte)34, (byte)21, (byte)50, (byte)129, (byte)145, (byte)161, (byte)35, (byte)66, (byte)55, (byte)83, (byte)114, (byte)130, (byte)179, (byte)209, (byte)240, (byte)255, (byte)196, (byte)0, (byte)26, (byte)1, (byte)1, (byte)0, (byte)3, (byte)1, (byte)1, (byte)1, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)1, (byte)2, (byte)6, (byte)5, (byte)4, (byte)3, (byte)255, (byte)196, (byte)0, (byte)49, (byte)17, (byte)0, (byte)2, (byte)1, (byte)3, (byte)1, (byte)5, (byte)6, (byte)5, (byte)4, (byte)3, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)1, (byte)2, (byte)3, (byte)4, (byte)17, (byte)33, (byte)5, (byte)18, (byte)49, (byte)65, (byte)81, (byte)19, (byte)97, (byte)113, (byte)129, (byte)145, (byte)209, (byte)20, (byte)50, (byte)161, (byte)177, (byte)193, (byte)6, (byte)34, (byte)82, (byte)240, (byte)35, (byte)52, (byte)241, (byte)255, (byte)218, (byte)0, (byte)12, (byte)3, (byte)1, (byte)0, (byte)2, (byte)17, (byte)3, (byte)17, (byte)0, (byte)63, (byte)0, (byte)191, (byte)232, (byte)5, (byte)0, (byte)160, (byte)20, (byte)6, (byte)45, (byte)246, (byte)165, (byte)101, (byte)166, (byte)67, (byte)231, (byte)95, (byte)93, (byte)195, (byte)109, (byte)30, (byte)112, (byte)26, (byte)87, (byte)11, (byte)147, (byte)237, (byte)158, (byte)180, (byte)62, (byte)148, (byte)233, (byte)78, (byte)163, (byte)196, (byte)22, (byte)72, (byte)254, (byte)161, (byte)185, (byte)245, (byte)41, (byte)109, (byte)154, (byte)77, (byte)11, (byte)72, (byte)121, (byte)128, (byte)126, (byte)17, (byte)53, (byte)216, (byte)104, (byte)213, (byte)134, (byte)70, (byte)74, (byte)198, (byte)1, (byte)145, (byte)135, (byte)49, (byte)207, (byte)29, (byte)234, (byte)79, (byte)93, (byte)59, (byte)74, (byte)106, (byte)88, (byte)173, (byte)60, (byte)119, (byte)45, (byte)126, (byte)188, (byte)23, (byte)169, (byte)151, (byte)161, (byte)13, (byte)195, (byte)57, (byte)105, (byte)181, (byte)139, (byte)136, (byte)81, (byte)29, (byte)17, (byte)227, (byte)142, (byte)8, (byte)56, (byte)56, (byte)78, (byte)78, (byte)84, (byte)150, (byte)36, (byte)158, (byte)65, (byte)123, (byte)14, (byte)180, (byte)62, (byte)119, (byte)31, (byte)15, (byte)29, (byte)41, (byte)47, (byte)87, (byte)236, (byte)103, (byte)126, (byte)24, (byte)230, (byte)4, (byte)13, (byte)123, (byte)112, (byte)211, (byte)174, (byte)8, (byte)127, (byte)49, (byte)194, (byte)231, (byte)150, (byte)126, (byte)208, (byte)195, (byte)35, (byte)151, (byte)66, (byte)77, (byte)65, (byte)242, (byte)237, (byte)86, (byte)116, (byte)138, (byte)199, (byte)145, (byte)152, (byte)33, (byte)28, (byte)110, (byte)93, (byte)99, (byte)101, (byte)39, (byte)237, (byte)28, (byte)28, (byte)198, (byte)112, (byte)78, (byte)79, (byte)126, (byte)99, (byte)61, (byte)187, (byte)80, (byte)249, (byte)239, (byte)116, (byte)62, (byte)204, (byte)104, (byte)115, (byte)148, (byte)83, (byte)146, (byte)9, (byte)229, (byte)212, (byte)142, (byte)148, (byte)35, (byte)44, (byte)235, (byte)146, (byte)57, (byte)216, (byte)199, (byte)193, (byte)58, (byte)168, (byte)13, (byte)151, (byte)5, (byte)51, (byte)196, (byte)61, (byte)58, (byte)242, (byte)161, (byte)41, (byte)199, (byte)92, (byte)163, (byte)178, (byte)48, (byte)202, (byte)138, (byte)29, (byte)131, (byte)48, (byte)28, (byte)216, (byte)12, (byte)103, (byte)244, (byte)161, (byte)15, (byte)25, (byte)208, (byte)250, (byte)161, (byte)2, (byte)128, (byte)80, (byte)17, (byte)157, (byte)209, (byte)185, (byte)165, (byte)211, (byte)101, (byte)77, (byte)43, (byte)75, (byte)142, (byte)57, (byte)181, (byte)139, (byte)132, (byte)227, (byte)137, (byte)101, (byte)200, (byte)142, (byte)53, (byte)206, (byte)11, (byte)177, (byte)198, (byte)0, (byte)28, (byte)207, (byte)50, (byte)58, (byte)124, (byte)3, (byte)40, (byte)247, (byte)90, (byte)90, (byte)170, (byte)139, (byte)181, (byte)169, (byte)164, (byte)23, (byte)175, (byte)130, (byte)56, (byte)209, (byte)54, (byte)108, (byte)22, (byte)55, (byte)163, (byte)85, (byte)213, (byte)46, (byte)100, (byte)213, (byte)53, (byte)131, (byte)146, (byte)110, (byte)103, (byte)57, (byte)88, (byte)242, (byte)115, (byte)136, (byte)215, (byte)162, (byte)129, (byte)255, (byte)0, (byte)177, (byte)76, (byte)138, (byte)247, (byte)210, (byte)156, (byte)123, (byte)42, (byte)75, (byte)118, (byte)29, (byte)23, (byte)63, (byte)23, (byte)204, (byte)147, (byte)0, (byte)1, (byte)36, (byte)0, (byte)9, (byte)57, (byte)62, (byte)245, (byte)7, (byte)132, (byte)230, (byte)128, (byte)80, (byte)10, (byte)1, (byte)64, (byte)40, (byte)5, (byte)0, (byte)160, (byte)58, (byte)46, (byte)174, (byte)69, (byte)180, (byte)106, (byte)113, (byte)151, (byte)118, (byte)9, (byte)26, (byte)243, (byte)251, (byte)152, (byte)244, (byte)28, (byte)129, (byte)229, (byte)220, (byte)158, (byte)192, (byte)19, (byte)66, (byte)240, (byte)142, (byte)243, (byte)35, (byte)123, (byte)215, (byte)117, (byte)38, (byte)222, (byte)211, (byte)12, (byte)113, (byte)202, (byte)162, (byte)246, (byte)68, (byte)45, (byte)203, (byte)170, (byte)32, (byte)234, (byte)70, (byte)123, (byte)146, (byte)66, (byte)175, (byte)94, (byte)108, (byte)14, (byte)8, (byte)6, (byte)165, (byte)35, (byte)219, (byte)99, (byte)102, (byte)235, (byte)207, (byte)45, (byte)126, (byte)223, (byte)239, (byte)253, (byte)125, (byte)200, (byte)197, (byte)218, (byte)91, (byte)122, (byte)11, (byte)157, (byte)57, (byte)47, (byte)117, (byte)133, (byte)150, (byte)227, (byte)84, (byte)103, (byte)71, (byte)184, (byte)19, (byte)18, (byte)66, (byte)149, (byte)92, (byte)199, (byte)31, (byte)163, (byte)5, (byte)4, (byte)30, (byte)255, (byte)0, (byte)119, (byte)51, (byte)204, (byte)114, (byte)51, (byte)233, (byte)121, (byte)115, (byte)40, (byte)207, (byte)114, (byte)150, (byte)145, (byte)229, (byte)143, (byte)171, (byte)238, (byte)207, (byte)219, (byte)78, (byte)4, (byte)214, (byte)160, (byte)229, (byte)138, (byte)1, (byte)64, (byte)40, (byte)14, (byte)132, (byte)189, (byte)180, (byte)146, (byte)81, (byte)18, (byte)93, (byte)66, (byte)210, (byte)30, (byte)92, (byte)11, (byte)32, (byte)39, (byte)246, (byte)161, (byte)119, (byte)78, (byte)105, (byte)101, (byte)163, (byte)190, (byte)133, (byte)5, (byte)0, (byte)160, (byte)20, (byte)4, (byte)69, (byte)245, (byte)232, (byte)102, (byte)213, (byte)117, (byte)11, (byte)176, (byte)202, (byte)62, (byte)129, (byte)141, (byte)157, (byte)178, (byte)184, (byte)228, (byte)92, (byte)178, (byte)172, (byte)146, (byte)117, (byte)28, (byte)131, (byte)16, (byte)184, (byte)235, (byte)133, (byte)62, (byte)181, (byte)39, (byte)69, (byte)91, (byte)181, (byte)78, (byte)48, (byte)254, (byte)90, (byte)191, (byte)12, (byte)60, (byte)47, (byte)201, (byte)84, (byte)65, (byte)172, (byte)157, (byte)215, (byte)191, (byte)231, (byte)191, (byte)184, (byte)102, (byte)109, (byte)62, (byte)25, (byte)26, (byte)237, (byte)184, (byte)163, (byte)7, (byte)134, (byte)40, (byte)129, (byte)242, (byte)215, (byte)135, (byte)62, (byte)167, (byte)29, (byte)121, (byte)150, (byte)205, (byte)79, (byte)35, (byte)65, (byte)42, (byte)31, (byte)11, (byte)104, (byte)161, (byte)31, (byte)153, (byte)233, (byte)230, (byte)248, (byte)255, (byte)0, (byte)123, (byte)139, (byte)242, (byte)194, (byte)223, (byte)233, (byte)108, (byte)162, (byte)136, (byte)146, (byte)92, (byte)40, (byte)46, (byte)199, (byte)151, (byte)19, (byte)99, (byte)153, (byte)198, (byte)78, (byte)50, (byte)121, (byte)252, (byte)154, (byte)169, (byte)145, (byte)169, (byte)45, (byte)233, (byte)54, (byte)100, (byte)208, (byte)160, (byte)160, (byte)20, (byte)5, (byte)101, (byte)227, (byte)30, (byte)227, (byte)190, (byte)210, (byte)52, (byte)171, (byte)45, (byte)58, (byte)202, (byte)67, (byte)16, (byte)191, (byte)243, (byte)60, (byte)233, (byte)20, (byte)225, (byte)184, (byte)87, (byte)135, (byte)237, (byte)30, (byte)153, (byte)226, (byte)231, (byte)241, (byte)82, (byte)142, (byte)230, (byte)196, (byte)181, (byte)133, (byte)90, (byte)146, (byte)169, (byte)61, (byte)119, (byte)113, (byte)143, (byte)50, (byte)140, (byte)134, (byte)121, (byte)109, (byte)231, (byte)89, (byte)225, (byte)149, (byte)227, (byte)149, (byte)14, (byte)85, (byte)209, (byte)136, (byte)96, (byte)125, (byte)65, (byte)171, (byte)26, (byte)185, (byte)69, (byte)73, (byte)97, (byte)173, (byte)13, (byte)133, (byte)240, (byte)207, (byte)123, (byte)54, (byte)232, (byte)210, (byte)222, (byte)210, (byte)250, (byte)69, (byte)58, (byte)165, (byte)168, (byte)28, (byte)103, (byte)24, (byte)243, (byte)83, (byte)160, (byte)124, (byte)122, (byte)246, (byte)62, (byte)228, (byte)122, (byte)213, (byte)90, (byte)49, (byte)219, (byte)86, (byte)195, (byte)225, (byte)167, (byte)189, (byte)15, (byte)149, (byte)253, (byte)31, (byte)79, (byte)98, (byte)119, (byte)80, (byte)114, (byte)69, (byte)1, (byte)209, (byte)123, (byte)114, (byte)182, (byte)118, (byte)55, (byte)23, (byte)79, (byte)158, (byte)8, (byte)99, (byte)105, (byte)27, (byte)29, (byte)112, (byte)6, (byte)104, (byte)90, (byte)17, (byte)115, (byte)146, (byte)138, (byte)230, (byte)82, (byte)250, (byte)198, (byte)226, (byte)26, (byte)111, (byte)135, (byte)154, (byte)102, (byte)155, (byte)57, (byte)113, (byte)123, (byte)123, (byte)111, (byte)45, (byte)227, (byte)156, (byte)17, (byte)198, (byte)101, (byte)226, (byte)199, (byte)62, (byte)127, (byte)230, (byte)22, (byte)237, (byte)208, (byte)15, (byte)81, (byte)86, (byte)230, (byte)105, (byte)232, (byte)91, (byte)118, (byte)151, (byte)147, (byte)169, (byte)31, (byte)150, (byte)45, (byte)47, (byte)76, (byte)123, (byte)96, (byte)142, (byte)236, (byte)121, (byte)214, (byte)198, (byte)51, (byte)112, (byte)33, (byte)89, (byte)30, (byte)231, (byte)82, (byte)179, (byte)179, (byte)44, (byte)253, (byte)17, (byte)12, (byte)158, (byte)97, (byte)253, (byte)115, (byte)26, (byte)254, (byte)213, (byte)44, (byte)246, (byte)95, (byte)199, (byte)125, (byte)238, (byte)231, (byte)132, (byte)100, (byte)254, (byte)152, (byte)252, (byte)155, (byte)35, (byte)84, (byte)49, (byte)66, (byte)128, (byte)80, (byte)10, (byte)2, (byte)153, (byte)241, (byte)215, (byte)131, (byte)234, (byte)116, (byte)60, (byte)112, (byte)249, (byte)156, (byte)19, (byte)103, (byte)215, (byte)25, (byte)76, (byte)126, (byte)157, (byte)127, (byte)154, (byte)180, (byte)77, (byte)55, (byte)233, (byte)252, (byte)238, (byte)212, (byte)242, (byte)252, (byte)149, (byte)13, (byte)88, (byte)209, (byte)30, (byte)230, (byte)208, (byte)215, (byte)95, (byte)110, (byte)110, (byte)155, (byte)29, (byte)73, (byte)79, (byte)244, (byte)209, (byte)248, (byte)37, (byte)29, (byte)140, (byte)109, (byte)201, (byte)187, (byte)142, (byte)199, (byte)35, (byte)220, (byte)10, (byte)134, (byte)121, (byte)111, (byte)109, (byte)213, (byte)197, (byte)9, (byte)83, (byte)244, (byte)241, (byte)54, (byte)157, (byte)89, (byte)93, (byte)3, (byte)41, (byte)5, (byte)88, (byte)100, (byte)17, (byte)220, (byte)85, (byte)12, (byte)11, (byte)88, (byte)209, (byte)156, (byte)208, (byte)17, (byte)221, (byte)235, (byte)39, (byte)153, (byte)177, (byte)117, (byte)166, (byte)133, (byte)145, (byte)215, (byte)233, (byte)37, (byte)4, (byte)231, (byte)151, (byte)32, (byte)65, (byte)233, (byte)223, (byte)53, (byte)43, (byte)137, (byte)236, (byte)176, (byte)88, (byte)186, (byte)167, (byte)158, (byte)168, (byte)215, (byte)157, (byte)127, (byte)82, (byte)184, (byte)212, (byte)237, (byte)52, (byte)151, (byte)152, (byte)42, (byte)172, (byte)22, (byte)139, (byte)111, (byte)10, (byte)170, (byte)17, (byte)148, (byte)64, (byte)23, (byte)139, (byte)61, (byte)242, (byte)193, (byte)190, (byte)49, (byte)241, (byte)86, (byte)54, (byte)86, (byte)212, (byte)163, (byte)78, (byte)83, (byte)75, (byte)155, (byte)203, (byte)243, (byte)212, (byte)251, (byte)219, (byte)45, (byte)245, (byte)166, (byte)227, (byte)66, (byte)105, (byte)56, (byte)63, (byte)16, (byte)225, (byte)250, (byte)119, (byte)47, (byte)194, (byte)18, (byte)225, (byte)78, (byte)80, (byte)159, (byte)99, (byte)146, (byte)191, (byte)238, (byte)20, (byte)101, (byte)110, (byte)214, (byte)230, (byte)43, (byte)255, (byte)0, (byte)30, (byte)62, (byte)15, (byte)143, (byte)185, (byte)176, (byte)219, (byte)67, (byte)112, (byte)38, (byte)224, (byte)208, (byte)227, (byte)145, (byte)199, (byte)151, (byte)125, (byte)7, (byte)244, (byte)111, (byte)32, (byte)99, (byte)247, (byte)69, (byte)34, (byte)242, (byte)57, (byte)207, (byte)174, (byte)50, (byte)62, (byte)126, (byte)106, (byte)172, (byte)199, (byte)94, (byte)219, (byte)58, (byte)21, (byte)90, (byte)95, (byte)43, (byte)213, (byte)62, (byte)168, (byte)247, (byte)234, (byte)15, (byte)32, (byte)160, (byte)56, (byte)36, (byte)0, (byte)73, (byte)56, (byte)3, (byte)169, (byte)52, (byte)6, (byte)180, (byte)120, (byte)141, (byte)184, (byte)147, (byte)113, (byte)238, (byte)251, (byte)137, (byte)224, (byte)147, (byte)142, (byte)210, (byte)220, (byte)8, (byte)32, (byte)32, (byte)156, (byte)16, (byte)58, (byte)145, (byte)242, (byte)73, (byte)254, (byte)42, (byte)232, (byte)220, (byte)108, (byte)203, (byte)103, (byte)111, (byte)110, (byte)148, (byte)184, (byte)189, (byte)89, (byte)19, (byte)169, (byte)58, (byte)2, (byte)128, (byte)218, (byte)13, (byte)129, (byte)124, (byte)117, (byte)29, (byte)135, (byte)163, (byte)220, (byte)54, (byte)114, (byte)32, (byte)17, (byte)28, (byte)128, (byte)63, (byte)33, (byte)41, (byte)219, (byte)253, (byte)53, (byte)71, (byte)196, (byte)194, (byte)109, (byte)26, (byte)125, (byte)157, (byte)212, (byte)227, (byte)223, (byte)247, (byte)212, (byte)146, (byte)84, (byte)30, (byte)35, (byte)194, (byte)189, (byte)181, (byte)58, (byte)134, (byte)214, (byte)191, (byte)179, (byte)44, (byte)45, (byte)139, (byte)164, (byte)176, (byte)179, (byte)23, (byte)192, (byte)238, (byte)50, (byte)78, (byte)73, (byte)25, (byte)235, (byte)235, (byte)67, (byte)213, (byte)78, (byte)125, (byte)157, (byte)120, (byte)207, (byte)143, (byte)6, (byte)106, (byte)244, (byte)210, (byte)206, (byte)203, (byte)29, (byte)188, (byte)204, (byte)248, (byte)183, (byte)5, (byte)21, (byte)24, (byte)99, (byte)131, (byte)153, (byte)36, (byte)126, (byte)228, (byte)213, (byte)205, (byte)228, (byte)84, (byte)117, (byte)146, (byte)230, (byte)117, (byte)2, (byte)85, (byte)131, (byte)41, (byte)32, (byte)131, (byte)144, (byte)71, (byte)106, (byte)146, (byte)196, (byte)186, (byte)195, (byte)123, (byte)220, (byte)216, (byte)75, (byte)30, (byte)173, (byte)111, (byte)43, (byte)197, (byte)173, (byte)33, (byte)88, (byte)230, (byte)251, (byte)65, (byte)134, (byte)242, (byte)44, (byte)127, (byte)120, (byte)229, (byte)134, (byte)24, (byte)28, (byte)199, (byte)94, (byte)188, (byte)143, (byte)58, (byte)140, (byte)28, (byte)234, (byte)150, (byte)17, (byte)154, (byte)236, (byte)164, (byte)179, (byte)14, (byte)93, (byte)83, (byte)238, (byte)238, (byte)44, (byte)221, (byte)23, (byte)198, (byte)93, (byte)6, (byte)242, (byte)5, (byte)26, (byte)170, (byte)77, (byte)97, (byte)56, (byte)31, (byte)113, (byte)8, (byte)100, (byte)140, (byte)159, (byte)98, (byte)185, (byte)63, (byte)184, (byte)170, (byte)224, (byte)225, (byte)215, (byte)216, (byte)117, (byte)224, (byte)255, (byte)0, (byte)197, (byte)251, (byte)151, (byte)163, (byte)61, (byte)41, (byte)124, (byte)89, (byte)217, (byte)209, (byte)196, (byte)206, (byte)186, (byte)148, (byte)146, (byte)176, (byte)232, (byte)137, (byte)109, (byte)38, (byte)79, (byte)238, (byte)160, (byte)127, (byte)52, (byte)195, (byte)62, (byte)11, (byte)99, (byte)222, (byte)55, (byte)135, (byte)28, (byte)121, (byte)162, (byte)180, (byte)222, (byte)94, (byte)42, (byte)223, (byte)110, (byte)27, (byte)121, (byte)44, (byte)52, (byte)232, (byte)154, (byte)198, (byte)193, (byte)242, (byte)28, (byte)150, (byte)204, (byte)146, (byte)143, (byte)66, (byte)71, (byte)65, (byte)236, (byte)63, (byte)122, (byte)148, (byte)142, (byte)221, (byte)142, (byte)200, (byte)133, (byte)187, (byte)83, (byte)168, (byte)247, (byte)165, (byte)244, (byte)69, (byte)121, (byte)86, (byte)59, (byte)34, (byte)128, (byte)80, (byte)27, (byte)41, (byte)225, (byte)111, (byte)248, (byte)113, (byte)164, (byte)252, (byte)75, (byte)255, (byte)0, (byte)43, (byte)213, (byte)31, (byte)19, (byte)19, (byte)181, (byte)255, (byte)0, (byte)220, (byte)159, (byte)151, (byte)217, (byte)19, (byte)10, (byte)131, (byte)154, (byte)87, (byte)58, (byte)206, (byte)181, (byte)248, (byte)6, (byte)249, (byte)187, (byte)179, (byte)186, (byte)82, (byte)150, (byte)122, (byte)141, (byte)171, (byte)74, (byte)178, (byte)176, (byte)226, (byte)70, (byte)33, (byte)112, (byte)84, (byte)175, (byte)46, (byte)44, (byte)97, (byte)143, (byte)35, (byte)156, (byte)16, (byte)42, (byte)78, (byte)213, (byte)10, (byte)29, (byte)189, (byte)170, (byte)156, (byte)120, (byte)197, (byte)148, (byte)166, (byte)226, (byte)178, (byte)187, (byte)176, (byte)220, (byte)55, (byte)214, (byte)247, (byte)203, (byte)139, (byte)129, (byte)51, (byte)51, (byte)28, (byte)12, (byte)54, (byte)78, (byte)67, (byte)12, (byte)114, (byte)193, (byte)7, (byte)35, (byte)230, (byte)172, (byte)105, (byte)237, (byte)167, (byte)9, (byte)209, (byte)140, (byte)161, (byte)195, (byte)7, (byte)153, (byte)82, (byte)125, (byte)197, (byte)0, (byte)160, (byte)20, (byte)2, (byte)128, (byte)80, (byte)10, (byte)1, (byte)64, (byte)109, (byte)62, (byte)204, (byte)176, (byte)109, (byte)51, (byte)102, (byte)233, (byte)54, (byte)140, (byte)129, (byte)29, (byte)45, (byte)149, (byte)153, (byte)113, (byte)140, (byte)51, (byte)125, (byte)199, (byte)35, (byte)215, (byte)36, (byte)231, (byte)222, (byte)168, (byte)204, (byte)21, (byte)245, (byte)78, (byte)210, (byte)230, (byte)114, (byte)93, (byte)79, (byte)118, (byte)160, (byte)242, (byte)16, (byte)95, (byte)20, (byte)118, (byte)244, (byte)186, (byte)214, (byte)221, (byte)23, (byte)54, (byte)176, (byte)135, (byte)187, (byte)179, (byte)62, (byte)98, (byte)56, (byte)36, (byte)58, (byte)175, (byte)126, (byte)30, (byte)96, (byte)15, (byte)127, (byte)129, (byte)233, (byte)82, (byte)142, (byte)174, (byte)201, (byte)185, (byte)84, (byte)107, (byte)110, (byte)201, (byte)233, (byte)35, (byte)95, (byte)164, (byte)148, (byte)221, (byte)67, (byte)153, (byte)95, (byte)50, (byte)196, (byte)138, (byte)170, (byte)89, (byte)143, (byte)220, (byte)163, (byte)144, (byte)28, (byte)253, (byte)57, (byte)99, (byte)216, (byte)85, (byte)141, (byte)130, (byte)91, (byte)175, (byte)78, (byte)12, (byte)198, (byte)169, (byte)46, (byte)40, (byte)5, (byte)0, (byte)160, (byte)20, (byte)2, (byte)128, (byte)80, (byte)18, (byte)109, (byte)133, (byte)183, (byte)159, (byte)114, (byte)110, (byte)203, (byte)75, (byte)92, (byte)31, (byte)167, (byte)136, (byte)249, (byte)211, (byte)176, (byte)236, (byte)139, (byte)255, (byte)0, (byte)103, (byte)3, (byte)245, (byte)168, (byte)103, (byte)135, (byte)104, (byte)220, (byte)171, (byte)123, (byte)119, (byte)46, (byte)111, (byte)68, (byte)108, (byte)245, (byte)80, (byte)194, (byte)138, (byte)1, (byte)64, (byte)82, (byte)158, (byte)34, (byte)248, (byte)98, (byte)214, (byte)173, (byte)113, (byte)173, (byte)232, (byte)139, (byte)24, (byte)180, (byte)84, (byte)50, (byte)79, (byte)109, (byte)156, (byte)20, (byte)198, (byte)50, (byte)87, (byte)215, (byte)57, (byte)39, (byte)29, (byte)177, (byte)203, (byte)210, (byte)172, (byte)153, (byte)167, (byte)217, (byte)155, (byte)85, (byte)75, (byte)20, (byte)107, (byte)113, (byte)228, (byte)253, (byte)202, (byte)154, (byte)172, (byte)104, (byte)69, (byte)0, (byte)160, (byte)20, (byte)2, (byte)128, (byte)80, (byte)29, (byte)182, (byte)182, (byte)179, (byte)222, (byte)221, (byte)69, (byte)109, (byte)109, (byte)19, (byte)203, (byte)60, (byte)172, (byte)21, (byte)35, (byte)65, (byte)146, (byte)196, (byte)246, (byte)168, (byte)43, (byte)57, (byte)198, (byte)17, (byte)114, (byte)147, (byte)194, (byte)70, (byte)202, (byte)236, (byte)61, (byte)159, (byte)30, (byte)209, (byte)209, (byte)60, (byte)151, (byte)42, (byte)247, (byte)211, (byte)225, (byte)238, (byte)100, (byte)29, (byte)51, (byte)217, (byte)71, (byte)176, (byte)254, (byte)106, (byte)173, (byte)228, (byte)196, (byte)237, (byte)27, (byte)215, (byte)117, (byte)87, (byte)43, (byte)229, (byte)92, (byte)9, (byte)85, (byte)65, (byte)207, (byte)20, (byte)2, (byte)128, (byte)80, (byte)21, (byte)86, (byte)246, (byte)240, (byte)146, (byte)61, (byte)66, (byte)89, (byte)53, (byte)29, (byte)188, (byte)35, (byte)134, (byte)225, (byte)178, (byte)210, (byte)90, (byte)19, (byte)132, (byte)115, (byte)158, (byte)170, (byte)122, (byte)41, (byte)246, (byte)233, (byte)241, (byte)86, (byte)76, (byte)239, (byte)216, (byte)109, (byte)151, (byte)77, (byte)42, (byte)117, (byte)245, (byte)93, (byte)125, (byte)250, (byte)148, (byte)230, (byte)165, (byte)165, (byte)95, (byte)233, (byte)23, (byte)70, (byte)219, (byte)80, (byte)180, (byte)154, (byte)218, (byte)97, (byte)253, (byte)178, (byte)46, (byte)51, (byte)238, (byte)61, (byte)71, (byte)184, (byte)169, (byte)52, (byte)180, (byte)171, (byte)83, (byte)171, (byte)29, (byte)234, (byte)111, (byte)40, (byte)195, (byte)169, (byte)62, (byte)130, (byte)128, (byte)80, (byte)30, (byte)246, (byte)222, (byte)217, (byte)250, (byte)214, (byte)230, (byte)184, (byte)88, (byte)236, (byte)45, (byte)31, (byte)202, (byte)63, (byte)154, (byte)226, (byte)64, (byte)86, (byte)53, (byte)249, (byte)110, (byte)255, (byte)0, (byte)3, (byte)157, (byte)70, (byte)79, (byte)37, (byte)205, (byte)237, (byte)27, (byte)101, (byte)153, (byte)189, (byte)122, (byte)115, (byte)47, (byte)109, (byte)149, (byte)225, (byte)254, (byte)159, (byte)180, (byte)160, (byte)19, (byte)48, (byte)75, (byte)173, (byte)77, (byte)129, (byte)227, (byte)185, (byte)43, (byte)249, (byte)65, (byte)254, (byte)212, (byte)29, (byte)135, (byte)191, (byte)83, (byte)252, (byte)85, (byte)91, (byte)201, (byte)148, (byte)191, (byte)218, (byte)85, (byte)46, (byte)158, (byte)22, (byte)145, (byte)233, (byte)238, (byte)76, (byte)42, (byte)14, (byte)104, (byte)160, (byte)20, (byte)2, (byte)128, (byte)80, (byte)10, (byte)3, (byte)22, (byte)251, (byte)78, (byte)177, (byte)212, (byte)161, (byte)242, (byte)111, (byte)173, (byte)32, (byte)185, (byte)143, (byte)159, (byte)219, (byte)52, (byte)97, (byte)128, (byte)237, (byte)203, (byte)61, (byte)40, (byte)125, (byte)41, (byte)213, (byte)157, (byte)55, (byte)152, (byte)54, (byte)188, (byte)10, (byte)139, (byte)196, (byte)77, (byte)159, (byte)160, (byte)233, (byte)16, (byte)92, (byte)77, (byte)97, (byte)167, (byte)172, (byte)14, (byte)32, (byte)243, (byte)7, (byte)12, (byte)143, (byte)128, (byte)197, (byte)143, (byte)98, (byte)113, (byte)250, (byte)85, (byte)147, (byte)52, (byte)91, (byte)50, (byte)246, (byte)189, (byte)86, (byte)148, (byte)229, (byte)157, (byte)123, (byte)138, (byte)158, (byte)213, (byte)22, (byte)75, (byte)184, (byte)81, (byte)198, (byte)85, (byte)164, (byte)80, (byte)71, (byte)168, (byte)205, (byte)73, (byte)160, (byte)155, (byte)196, (byte)91, (byte)70, (byte)192, (byte)108, (byte)253, (byte)143, (byte)182, (byte)190, (byte)144, (byte)221, (byte)182, (byte)145, (byte)12, (byte)147, (byte)172, (byte)165, (byte)65, (byte)148, (byte)180, (byte)128, (byte)12, (byte)3, (byte)209, (byte)137, (byte)31, (byte)197, (byte)85, (byte)179, (byte)33, (byte)123, (byte)127, (byte)115, (byte)189, (byte)184, (byte)167, (byte)167, (byte)161, (byte)61, (byte)68, (byte)72, (byte)145, (byte)82, (byte)52, (byte)84, (byte)69, (byte)24, (byte)10, (byte)163, (byte)0, (byte)10, (byte)131, (byte)146, (byte)219, (byte)111, (byte)44, (byte)250, (byte)161, (byte)2, (byte)128, (byte)80, (byte)10, (byte)3, (byte)255, (byte)217};			 
//		
//		if(size>insertListA.size())
//			size = insertListA.size();
//		for(int i=0; i<size;i++)
//		{
//			User user = new User();
//			String locale = (String)insertListA.get(i);
//			UserAuthentication userAuthentication = new UserAuthentication();
//			userAuthentication.setLogin("Login"+locale);
//			userAuthentication.setPassword("Password"+locale);
//			userAuthentication.setLevelPass1("LevelPass1"+locale);
//			userAuthentication.setLevelPass2("LevelPass2"+locale);
//			userAuthentication.setLevelPass3("LevelPass3"+locale);
//			List userAuthenticationList = session.find("FROM UserAuthentication AS userAuthentication WHERE userAuthentication.login='"+userAuthentication.getLogin()+"'");
//			if(userAuthenticationList.size()==1)
//				userAuthentication = (UserAuthentication)userAuthenticationList.get(0); 
//			else 
//				userAuthentication.setId((Long)session.save(userAuthentication));
//			user.setUserAuthentication(userAuthentication);
//			UserRole userRole = new UserRole();
//			userRole.setLabelCode("ADMINISTRATOR");
//			List userRoleList = session.find("FROM UserRole AS userRole WHERE userRole.labelCode=?",userRole.getLabelCode(), new StringType());
//			if(userRoleList.size()==1)
//				userRole = (UserRole)userRoleList.get(0); 
//			else 
//				userRole.setId((Long)session.save(userRole));
//			user.setUserRole(userRole);					
//			user.setName("Name"+locale);
//			user.setForename1("Forename1"+locale);
//			user.setForename2("Forename2"+locale);
//			user.setBirthdate(Calendar.getInstance(new java.util.Locale(locale)).getTime());
//			user.setSex((byte)(Math.round(Math.random())%2));
//			user.setPicture(picture);
//			user.setId((Long)session.save(user));
//			insertList.add(user);
//		}
//	}
//
//
//	/** 
//	* Cette méthode teste la table t_user.
//	* Les actionsde ce test sont les suivantes : <BR/>
//	* Effacer toutes les données de la table t_user. <BR/>
//	* Insérer des données dans la table t_user. <BR/>
//	* S'assurer que les données ont bien été insérées dans la table t_user. <BR/>
//	* Effacer un objet par un identifiant qui n'existe pas. <BR/>
//	* Sauver un seul objet. <BR/>
//	* Récupérer un objet par son identifiant. <BR/>
//	* Effacer un seul objet. <BR/>
//	* Récupérer un objet par son identifiant qui n'existe pas. <BR/>
//	*/
//	public void testUser()
//	{
//		long start = System.currentTimeMillis();		
//		try
//		{
//			Session session = HibernateUtil.currentSession();
//			Transaction tx = session.beginTransaction();
//
//			//Effacer en cascade les données de la table t_user_authentication
//			delete(T_USER, session);
//			
//			//Liste des données à insérer dans la table t_user
//			ArrayList insertList = new ArrayList();
//			fulfillUser(session, insertList, 40);
//			
//			//Récupérer la liste précédement insérée dans la table t_user
//			List listObj = session.find("FROM User");
//
//			int length = insertList.size();
//			//Test l'égalité des longueurs des 2 listes
//			assertEquals(length, listObj.size());
//			
//			//Test l'égalité des 2 listes
//			User insertObj = null;
//			for (int i = 0; i < length; i++)
//			{
//				int j = 0;
//				User obj = null;
//				insertObj = (User)insertList.get(i);
//				for (; j < length; j++)
//				{
//					obj = (User) listObj.get(j);
//					
//					if (	insertObj.getId().equals(obj.getId()) 	)
//						break;
//				}
//				if (j == length)
//					assertFalse("La liste insérée n'est pas identique à liste récupérée", true);
//			}
//
//			Long newObjectId = new Long(length+1);
//			//Effacer un objet par un identifiant qui n'existe pas
//			int res = session.delete("FROM User as user WHERE user.id=?", newObjectId, new LongType());
//			assertEquals(res, 0);
//			
//			String locale = null;
//			User newObject = new User();
//			UserAuthentication userAuthentication = new UserAuthentication();
//			userAuthentication.setLogin("Login"+locale);
//			userAuthentication.setPassword("Password"+locale);
//			userAuthentication.setLevelPass1("LevelPass1"+locale);
//			userAuthentication.setLevelPass2("LevelPass2"+locale);
//			userAuthentication.setLevelPass3("LevelPass3"+locale);
//			List userAuthenticationList = session.find("FROM UserAuthentication AS userAuthentication WHERE userAuthentication.login='"+userAuthentication.getLogin()+"'");
//			if(userAuthenticationList.size()==1)
//				userAuthentication = (UserAuthentication)userAuthenticationList.get(0); 
//			else 
//				userAuthentication.setId((Long)session.save(userAuthentication));
//			newObject.setUserAuthentication(userAuthentication);
//			UserRole userRole = new UserRole();
//			userRole.setLabelCode("ADMINISTRATOR");
//			List userRoleList = session.find("FROM UserRole AS userRole WHERE userRole.labelCode=?",userRole.getLabelCode(), new StringType());
//			if(userRoleList.size()==1)
//				userRole = (UserRole)userRoleList.get(0); 
//			else 
//				userRole.setId((Long)session.save(userRole));
//			newObject.setUserRole(userRole);					
//			newObject.setName("NameToto");
//			newObject.setForename1("Forename1Toto");
//			newObject.setForename2("Forename2Toto");
//			newObject.setBirthdate(new Date());
//			newObject.setSex((byte)0);
//			//Sauver un seul objet			
//			newObject.setId((Long)session.save(newObject));
//
//			//Récupérer un objet par son identifiant
//			User loadObj = (User)session.load(User.class, newObject.getId());
//			assertEquals(newObject.getId().longValue(), loadObj.getId().longValue());
//			assertEquals(newObject.getUserAuthentication().getId().longValue(), loadObj.getUserAuthentication().getId().longValue());
//			assertEquals(newObject.getUserAuthentication().getLogin(), loadObj.getUserAuthentication().getLogin());
//			assertEquals(newObject.getUserAuthentication().getPassword(), loadObj.getUserAuthentication().getPassword());
//			assertEquals(newObject.getUserAuthentication().getLevelPass1(), loadObj.getUserAuthentication().getLevelPass1());
//			assertEquals(newObject.getUserAuthentication().getLevelPass2(), loadObj.getUserAuthentication().getLevelPass2());
//			assertEquals(newObject.getUserAuthentication().getLevelPass3(), loadObj.getUserAuthentication().getLevelPass3());
//			assertEquals(newObject.getUserRole().getId().longValue(), loadObj.getUserRole().getId().longValue());
//			assertEquals(newObject.getUserRole().getLabelCode(), loadObj.getUserRole().getLabelCode());
//			assertEquals(newObject.getName(), loadObj.getName());
//			assertEquals(newObject.getForename1(), loadObj.getForename1());
//			assertEquals(newObject.getForename2(), loadObj.getForename2());
//			assertEquals(newObject.getBirthdate(), loadObj.getBirthdate());
//			assertEquals(newObject.getSex(), loadObj.getSex());
//			assertEquals(newObject.getPicture(), loadObj.getPicture());
//			
//			//Effacer un seul objet 
//			session.delete(newObject);
//			//Récupérer un objet par son identifiant qui n'existe pas
//			ObjectDeletedException odeTest =null;
//			try
//			{
//				session.load(User.class, newObject.getId());
//			}
//			catch(ObjectDeletedException ode)
//			{
//				odeTest = ode;
//			}
//			assertNotNull(odeTest);
//
//			tx.commit();
//			HibernateUtil.closeSession();
//		}
//		catch (HibernateException he)
//		{
//			he.printStackTrace();
//			throw new AssertionFailedError(he.getMessage());			
//		}
//		long end = System.currentTimeMillis();
//		log.debug("Temps écoulé en seconde sur le test : " + (end - start) / 1000);
//	}
//
//	/**
//	 * Cette méthode permet de remplir la table t_user_authentication avec toutes ses contraintes.
//	 */
//	private void fulfillUserAuthentication(Session session, ArrayList insertList) throws HibernateException
//	{
//		//Utiliser la liste des Locale pour les logins, les password, les niveaux de passe 1, 2 et 3
//		List insertListA = Arrays.asList(java.util.Locale.getISOLanguages());
//		for(int i=0; i<insertListA.size();i++)
//		{
//			UserAuthentication userAuthentication = new UserAuthentication();
//			String locale = (String)insertListA.get(i);
//			userAuthentication.setLogin("Login"+locale);
//			userAuthentication.setPassword("Password"+locale);
//			userAuthentication.setLevelPass1("LevelPass1"+locale);
//			userAuthentication.setLevelPass2("LevelPass2"+locale);
//			userAuthentication.setLevelPass3("LevelPass3"+locale);
//			userAuthentication.setId((Long)session.save(userAuthentication));
//			insertList.add(userAuthentication);
//		}
//	}
//
//	/** 
// 	* Cette méthode teste la table t_user_authentication.
//	* Les actionsde ce test sont les suivantes : <BR/>
//	* Effacer toutes les données de la table t_user_authentication. <BR/>
//	* Insérer des données dans la table t_user_authentication. <BR/>
//	* S'assurer que les données ont bien été insérées dans la table t_user_authentication. <BR/>
//	* Effacer un objet par un identifiant qui n'existe pas. <BR/>
//	* Sauver un seul objet. <BR/>
//	* Récupérer un objet par son identifiant. <BR/>
//	* Effacer un seul objet. <BR/>
//	* Récupérer un objet par son identifiant qui n'existe pas. <BR/>
//	*/
//	public void testUserAuthentication()
//	{
//		long start = System.currentTimeMillis();
//		try
//		{
//			Session session = HibernateUtil.currentSession();
//			Transaction tx = session.beginTransaction();
//			
//			//Effacer en cascade les données de la table t_user_authentication
//			delete(T_USERAUTHENTICATION, session);
//			
//			//Liste des données à insérer dans la table t_user_authentication
//			ArrayList insertList = new ArrayList();
//			fulfillUserAuthentication(session, insertList);
//						
//			//Récupérer la liste précédement insérée dans la table t_user_authentication
//			List listObj = session.find("FROM UserAuthentication");
//
//			int length = insertList.size();
//			//Test l'égalité des longueurs des 2 listes
//			assertEquals(length, listObj.size());
//			
//			//Test l'égalité des 2 listes
//			UserAuthentication insertObj = null;
//			for (int i = 0; i < length; i++)
//			{
//				int j = 0;
//				UserAuthentication obj = null;
//				insertObj = (UserAuthentication)insertList.get(i);
//				for (; j < length; j++)
//				{
//					obj = (UserAuthentication) listObj.get(j);
//					
//					if (	insertObj.getId().equals(obj.getId()) && 
//							insertObj.getLogin().equals(obj.getLogin()) &&
//							insertObj.getPassword().equals(obj.getPassword()) &&
//							insertObj.getLevelPass1().equals(obj.getLevelPass1()) &&
//							insertObj.getLevelPass2().equals(obj.getLevelPass2()) &&
//							insertObj.getLevelPass3().equals(obj.getLevelPass3())
//						)
//						break;
//				}
//				if (j == length)
//					assertFalse("La liste insérée n'est pas identique à liste récupérée", true);
//			}
//
//			Long newObjectId = new Long(length+1);
//			//Effacer un objet par un identifiant qui n'existe pas
//			int res = session.delete("FROM UserAuthentication as userAuthentication WHERE userAuthentication.id=?", newObjectId, new LongType());
//			assertEquals(res, 0);
//			
//			UserAuthentication newObject = new UserAuthentication();
//			newObject.setLogin("LoginToto");
//			newObject.setPassword("PasswordToto");
//			newObject.setLevelPass1("LevelPass1Toto");
//			newObject.setLevelPass2("LevelPass2Toto");
//			newObject.setLevelPass3("LevelPass3Toto");
//			//Sauver un seul objet			
//			newObject.setId((Long)session.save(newObject));
//
//			//Récupérer un objet par son identifiant
//			UserAuthentication loadObj = (UserAuthentication)session.load(UserAuthentication.class, newObject.getId());
//			assertEquals(newObject.getId().longValue(), loadObj.getId().longValue());
//			assertEquals(newObject.getLogin(), loadObj.getLogin());
//			assertEquals(newObject.getPassword(), loadObj.getPassword());
//			assertEquals(newObject.getLevelPass1(), loadObj.getLevelPass1());
//			assertEquals(newObject.getLevelPass2(), loadObj.getLevelPass2());
//			assertEquals(newObject.getLevelPass3(), loadObj.getLevelPass3());
//			
//			//Effacer un seul objet 
//			session.delete(newObject);
//			//Récupérer un objet par son identifiant qui n'existe pas
//			ObjectDeletedException odeTest =null;
//			try
//			{
//				session.load(UserAuthentication.class, newObject.getId());
//			}
//			catch(ObjectDeletedException ode)
//			{
//				odeTest = ode;
//			}
//			assertNotNull(odeTest);
//
//			tx.commit();
//			HibernateUtil.closeSession();
//		}
//		catch (HibernateException he)
//		{
//			he.printStackTrace();
//			throw new AssertionFailedError(he.getMessage());			
//		}
//		long end = System.currentTimeMillis();
//		log.debug("Temps écoulé en seconde sur le test : " + (end - start) / 1000);
//	}
//
//	/** 
// 	* Cette méthode teste la table t_user_role_language.
//	* Les actionsde ce test sont les suivantes : <BR/>
//	* Effacer toutes les données de la table t_user_role_language. <BR/>
//	* Insérer des données dans la table t_user_role_language. <BR/>
//	* S'assurer que les données ont bien été insérées dans la table t_user_role_language. <BR/>
//	* Effacer un objet par un identifiant qui n'existe pas. <BR/>
//	* Effacer un seul objet. <BR/>
//	* Sauver un seul objet. <BR/>
//	* Récupérer un objet par son identifiant. <BR/>
//	* Effacer un seul objet. <BR/>
//	* Récupérer un objet par son identifiant qui n'existe pas. <BR/>
//	* Sauver un seul objet. <BR/> 
//	*/
//	public void testUserRoleLanguage()
//	{
//		long start = System.currentTimeMillis();		
//		try
//		{
//			Session session = HibernateUtil.currentSession();
//			Transaction tx = session.beginTransaction();
//
//			//Effacer en cascade les données de la table t_user_role_language
//			delete(T_USERROLELANGUAGE, session);
//
//			//Récupérer la liste des Locale  
//			List insertListA = session.find("FROM Locale");
//			//Récupérer la liste des UserRole
//			List insertListB = session.find("FROM UserRole");
//			//Liste des données à insérer dans la table t_user_role_language
//			ArrayList insertList = new ArrayList();
//			for(int i=0; i<insertListA.size();i++)
//			{
//				for(int j=0; j<insertListB.size();j++)
//				{
//					UserRoleLanguageID userRoleLanguageID = new UserRoleLanguageID();
//					userRoleLanguageID.setLocale((Locale)insertListA.get(i));
//					userRoleLanguageID.setUserRole((UserRole)insertListB.get(j));
//					UserRoleLanguage userRoleLanguage = new UserRoleLanguage();
//					userRoleLanguage.setId(userRoleLanguageID);
//					userRoleLanguage.setLabel(((UserRole)insertListB.get(j)).getLabelCode()+((Locale)insertListA.get(i)).getId());
//					insertList.add(userRoleLanguage);
//					session.save(userRoleLanguage);
//				}
//			}
//			
//			//Récupérer la liste précédement insérée dans la table t_user_role_language
//			List listObj = session.find("FROM UserRoleLanguage");
//
//			int length = insertList.size();
//			//Test l'égalité des longueurs des 2 listes
//			assertEquals(length, listObj.size());
//			
//			//Test l'égalité des 2 listes
//			UserRoleLanguage insertObj = null;
//			for (int i = 0; i < length; i++)
//			{
//				int j = 0;
//				UserRoleLanguage obj = null;
//				insertObj = (UserRoleLanguage)insertList.get(i);
//				for (; j < length; j++)
//				{
//					obj = (UserRoleLanguage) listObj.get(j);
//					
//					if (insertObj.getId().equals(obj.getId()) && insertObj.getLabel().equals(obj.getLabel()))
//						break;
//				}
//				if (j == length)
//					assertFalse("La liste insérée n'est pas identique à liste récupérée", true);
//			}
//
//			Long newObjectId1 = new Long(length+1);
//			String newObjectId2 = "sfr";
//			Object[] objectId = new Object[2];
//			objectId[0] = newObjectId1;
//			objectId[1] = newObjectId2;
//			
//			UserRoleLanguageID newObjectId = new UserRoleLanguageID();
//			UserRole newUserRole = new UserRole();
//			newUserRole.setId(newObjectId1);
//			newUserRole.setLabelCode("Toto");
//			newObjectId.setUserRole(newUserRole); 
//			Locale newLocale = new Locale();
//			newLocale.setId(newObjectId2);
//			newObjectId.setLocale(newLocale);
//			 
//			Type types[] = new Type[2];
//			types[0] = new LongType();
//			types[1] = new StringType();
//			//Effacer un objet par un identifiant qui n'existe pas
//			int res = session.delete("FROM UserRoleLanguage as userRoleLanguage WHERE userRoleLanguage.id.userRole.id = ? AND userRoleLanguage.id.locale.id = ?", objectId, types);
//			assertEquals(res, 0);
//
//			UserRoleLanguage newObject = new UserRoleLanguage();
//			newObjectId.getUserRole().setId(new Long(1));
//			newObjectId.getLocale().setId("fr");
//			newObject.setId(newObjectId);
//			newObject.setLabel("Titi");
//
//			//Effacer un seul objet 
//			session.delete(newObject);
//		
//			//Sauver un seul objet			
//			UserRoleLanguageID objId = (UserRoleLanguageID)session.save(newObject);
//			assertEquals(newObjectId.getUserRole().getId().longValue(), objId.getUserRole().getId().longValue());
//			assertEquals(newObjectId.getLocale().getId(), objId.getLocale().getId());
//			assertNotSame(newObject.getLabel(), newObjectId.getUserRole().getLabelCode()+newObjectId.getLocale().getId());
//
//			//Récupérer un objet par son identifiant
//			UserRoleLanguage loadObj = (UserRoleLanguage)session.load(UserRoleLanguage.class, newObjectId);
//			assertEquals(newObject.getId().getUserRole().getId().longValue(), loadObj.getId().getUserRole().getId().longValue());
//			assertEquals(newObject.getId().getLocale().getId(), loadObj.getId().getLocale().getId());
//			assertEquals(newObject.getLabel(), loadObj.getLabel());
//
//			//Effacer un seul objet 
//			session.delete(newObject);
//			
//			//Récupérer un objet par son identifiant qui n'existe pas
//			ObjectDeletedException odeTest =null;
//			try
//			{
//				session.load(UserRoleLanguage.class, newObjectId);
//			}
//			catch(ObjectDeletedException ode)
//			{
//				odeTest = ode;
//			}
//			assertNotNull(odeTest);
//
//			tx.commit();
//			HibernateUtil.closeSession();
//		}
//		catch (HibernateException he)
//		{
//			he.printStackTrace();
//			throw new AssertionFailedError(he.getMessage());			
//		}
//		long end = System.currentTimeMillis();
//		log.debug("Temps écoulé en seconde sur le test : " + (end - start) / 1000);
//	}
//
//	/** 
// 	* Cette méthode teste la table t_user_role.
//	* Les actionsde ce test sont les suivantes : <BR/>
//	* Effacer toutes les données de la table t_user_role. <BR/>
//	* Insérer des données dans la table t_user_role. <BR/>
//	* S'assurer que les données ont bien été insérées dans la table t_user_role. <BR/>
//	* Effacer un objet par un identifiant qui n'existe pas. <BR/>
//	* Sauver un seul objet. <BR/>
//	* Récupérer un objet par son identifiant. <BR/>
//	* Effacer un seul objet. <BR/>
//	* Récupérer un objet par son identifiant qui n'existe pas. <BR/>
//	*/
//	public void testUserRole()
//	{
//		long start = System.currentTimeMillis();		
//		try
//		{
//			Session session = HibernateUtil.currentSession();
//			Transaction tx = session.beginTransaction();
//
//			//Effacer en cascade les données de la table t_user_role
//			delete(T_USERROLE, session);
//			
//			//Liste des données à insérer
//			String[] insertList = {"ADMINISTRATOR", "USER", "CUSTOMER", "GUEST"};
//
//			int length = insertList.length;
//			//Insère la liste dans la table t_user_role
//			for (int i = 0; i < length; i++)
//			{
//				UserRole obj = new UserRole();
//				obj.setLabelCode(insertList[i]);
//				session.save(obj);
//			}
//
//			//Récupérer la liste précédement insérée dans la table t_user_role
//			List listObj = session.find("FROM UserRole");
//
//			//Test l'égalité des longueurs des 2 listes
//			assertEquals(length, listObj.size());
//
//			//Test l'égalité des 2 listes
//			for (int i = 0; i < length; i++)
//			{
//				int j = 0;
//				UserRole obj = null;
//				for (; j < length; j++)
//				{
//					obj = (UserRole) listObj.get(j);
//					if (insertList[i].equals(obj.getLabelCode()))
//						break;
//				}
//				if (j == length)
//					assertFalse("La liste insérée n'est pas identique à liste récupérée", true);
//			}
//
//			Long newObjectId = new Long(length+1);
//			//Effacer un objet par un identifiant qui n'existe pas
//			int res = session.delete("FROM UserRole as userRole WHERE userRole.id=?", newObjectId, new LongType());
//			assertEquals(res, 0);
//			
//			UserRole newObject = new UserRole();
//			newObject.setId(newObjectId);
//			newObject.setLabelCode("Toto");
//			//Sauver un seul objet			
//			Long objId = (Long)session.save(newObject);
//			assertEquals(newObjectId.longValue(), objId.longValue());
//
//			//Récupérer un objet par son identifiant
//			UserRole loadObj = (UserRole)session.load(UserRole.class, newObjectId);
//			assertEquals(newObject.getId().longValue(), loadObj.getId().longValue());
//			assertEquals(newObject.getLabelCode(), loadObj.getLabelCode());
//			
//			//Effacer un seul objet 
//			session.delete(newObject);
//			//Récupérer un objet par son identifiant qui n'existe pas
//			ObjectDeletedException odeTest =null;
//			try
//			{
//				session.load(UserRole.class, newObjectId);
//			}
//			catch(ObjectDeletedException ode)
//			{
//				odeTest = ode;
//			}
//			assertNotNull(odeTest);
//			
//			tx.commit();
//			HibernateUtil.closeSession();
//		}
//		catch (HibernateException he)
//		{
//			he.printStackTrace();
//			throw new AssertionFailedError(he.getMessage());			
//		}
//		long end = System.currentTimeMillis();
//		log.debug("Temps écoulé en seconde sur le test : " + (end - start) / 1000);
//	}
//
//	/** 
//	 * Cette méthode teste la table t_locale_language.
//	 * Les actionsde ce test sont les suivantes : <BR/>
//	 * Effacer toutes les données de la table t_locale_language. <BR/>
//	 * Ajouter toutes les langues de code ISO 639 de l'api Java dans la table t_locale_language. <BR/>
//	 * S'assurer que les données ont bien été insérées dans la table t_locale_language. <BR/>
//	 * Effacer un objet par un identifiant qui n'existe pas. <BR/>
//	 * Sauver un seul objet. <BR/>
//	 * Récupérer un objet par son identifiant. <BR/>
//	 * Effacer un seul objet. <BR/>
//	 * Récupérer un objet par son identifiant qui n'existe pas. <BR/>
//	 */
//	public void testLocaleLanguage()
//	{
//		long start = System.currentTimeMillis();
//		try
//		{
//			Session session = HibernateUtil.currentSession();
//			Transaction tx = session.beginTransaction();
//			
//			//Effacer en cascade les données de la table t_locale_language
//			delete(T_LOCALE, session);
//
//			//Récupérer la liste des langues de code ISO 639 de l'api Java
//			String[] listLanguages = java.util.Locale.getISOLanguages();
//			int length = listLanguages.length-50;
//			for (int i = 0; i < length; i++)
//			{
//				Locale localeTemp = new Locale();
//				localeTemp.setId(listLanguages[i]);
//				session.save(localeTemp);
//			}			
//			List listA = new ArrayList();
//			//Insère la liste dans la table t_locale_language
//			for (int i = 0; i < length; i++)
//			{
//				for(int j=0; j<length; j++)
//				{
//					LocaleLanguage localeLanguage = new LocaleLanguage();
//					LocaleLanguageID localeLanguageID = new LocaleLanguageID();
//					Locale locale = new Locale();
//					locale.setId(listLanguages[i]);
//					localeLanguageID.setLocale(locale);
//					Locale language = new Locale();
//					language.setId(listLanguages[j]);
//					localeLanguageID.setLanguage(language);
//					localeLanguage.setId(localeLanguageID);
//					localeLanguage.setLabel("Label_"+localeLanguage.getId().getLocale().getId()+"_"+localeLanguage.getId().getLanguage().getId());
//					listA.add(localeLanguage);
//					session.save(localeLanguage);
//				}
//			}
//
//			//Récupérer la liste précédement insérée dans la table t_locale_language
//			List listLocaleLanguage = session.find("FROM LocaleLanguage");
//
//			//Test l'égalité des longueurs des 2 listes
//			assertEquals(listA.size(), listLocaleLanguage.size());
//
//			//Test l'égalité des 2 listes
//			for (int i = 0; i < listA.size(); i++)
//			{
//				int j = 0;
//				LocaleLanguage localeLanguageA = (LocaleLanguage) listLocaleLanguage.get(i);
//				for (; j < listLocaleLanguage.size(); j++)
//				{
//					LocaleLanguage localeLanguage = (LocaleLanguage) listLocaleLanguage.get(j);
//					if (localeLanguageA.getId().getLocale().getId().equals(localeLanguage.getId().getLocale().getId())
//						&& localeLanguageA.getId().getLanguage().getId().equals(localeLanguage.getId().getLanguage().getId())
//						&& localeLanguageA.getLabel().equals(localeLanguage.getLabel())
//					)
//						break;
//				}
//				if (j == listLocaleLanguage.size())
//					assertFalse("La liste insérée n'est pas identique à liste récupérée", true);
//			}
//			
//			String newObjectId1 = "sfr";
//			String newObjectId2 = "sfr";
//			Object[] objectId = new Object[2];
//			objectId[0] = newObjectId1;
//			objectId[1] = newObjectId2;
//			Type types[] = new Type[2];
//			types[0] = new StringType();
//			types[1] = new StringType();
//			
//			//Effacer un objet par un identifiant qui n'existe pas
//			int res = session.delete("FROM LocaleLanguage as localeLanguage WHERE localeLanguage.id.locale.id = ? AND localeLanguage.id.language.id = ?", objectId, types);
//			assertEquals(res, 0);
//			
//			LocaleLanguageID newObjectId = new LocaleLanguageID();
//			Locale locale = new Locale();
//			locale.setId("sfr");
//			session.save(locale);
//			newObjectId.setLocale(locale);
//			Locale language = new Locale();
//			language.setId("sfr");
//			newObjectId.setLanguage(language);
//			LocaleLanguage newObject = new LocaleLanguage();
//			newObject.setId(newObjectId);
//			newObject.setLabel("Titi");
//			//Sauver un seul objet			
//			LocaleLanguageID objId = (LocaleLanguageID)session.save(newObject);
//			assertEquals(newObjectId, objId);
//
//			//Récupérer un objet par son identifiant
//			LocaleLanguage loadObj = (LocaleLanguage)session.load(LocaleLanguage.class, newObject.getId());
//			assertEquals(newObject.getId(), loadObj.getId());
//			
//			//Effacer un seul objet 
//			session.delete(newObject);
//			//Effacer la locale sfr			
//			session.delete(locale);
//			//Récupérer un objet par son identifiant qui n'existe pas
//			ObjectDeletedException odeTest =null;
//			try
//			{
//				session.load(LocaleLanguage.class, newObject.getId());
//			}
//			catch(ObjectDeletedException ode)
//			{
//				odeTest = ode;
//			}
//			assertNotNull(odeTest);
//
//			tx.commit();
//			HibernateUtil.closeSession();
//		}
//		catch (HibernateException he)
//		{
//			he.printStackTrace();
//			throw new AssertionFailedError(he.getMessage());
//		}
//		long end = System.currentTimeMillis();
//		log.debug("Temps écoulé en seconde sur le test : " + (end - start) / 1000);
//	}
//
//	/** 
//	 * Cette méthode teste la table t_locale.
//	 * Les actionsde ce test sont les suivantes : <BR/>
//	 * Effacer toutes les données de la table t_locale. <BR/>
//	 * Ajouter toutes les langues de code ISO 639 de l'api Java dans la table t_locale. <BR/>
//	 * S'assurer que les données ont bien été insérées dans la table t_locale. <BR/>
//	 * Effacer un objet par un identifiant qui n'existe pas. <BR/>
//	 * Sauver un seul objet. <BR/>
//	 * Récupérer un objet par son identifiant. <BR/>
//	 * Effacer un seul objet. <BR/>
//	 * Récupérer un objet par son identifiant qui n'existe pas. <BR/>
//	 */
//	public void testLocale()
//	{
//		long start = System.currentTimeMillis();
//		try
//		{
//			Session session = HibernateUtil.currentSession();
//			Transaction tx = session.beginTransaction();
//			
//			//Effacer en cascade les données de la table t_locale
//			delete(T_LOCALE, session);
//
//			//Récupérer la liste des langues de code ISO 639 de l'api Java
//			String[] listLanguages = java.util.Locale.getISOLanguages();
//			int length = listLanguages.length;
//			//Insère la liste dans la table t_locale
//			for (int i = 0; i < length; i++)
//			{
//				Locale locale = new Locale();
//				locale.setId(listLanguages[i]);
//				session.save(locale);
//			}
//
//			//Récupérer la liste précédement insérée dans la table t_locale
//			List listLocale = session.find("FROM Locale");
//
//			//Test l'égalité des longueurs des 2 listes
//			assertEquals(length, listLocale.size());
//
//			//Test l'égalité des 2 listes
//			for (int i = 0; i < length; i++)
//			{
//				int j = 0;
//				Locale locale = null;
//				for (; j < length; j++)
//				{
//					locale = (Locale) listLocale.get(j);
//					if (listLanguages[i].equals(locale.getId()))
//						break;
//				}
//				if (j == length)
//					assertFalse("La liste insérée n'est pas identique à liste récupérée", true);
//			}
//			
//			String newObjectId = "sfr";
//			//Effacer un objet par un identifiant qui n'existe pas
//			int res = session.delete("FROM Locale as locale WHERE locale.id=?", newObjectId, new StringType());
//			assertEquals(res, 0);
//			
//			Locale newObject = new Locale();
//			newObject.setId(newObjectId);
//			//Sauver un seul objet			
//			String objId = (String)session.save(newObject);
//			assertEquals(newObjectId, objId);
//
//			//Récupérer un objet par son identifiant
//			Locale loadObj = (Locale)session.load(Locale.class, newObjectId);
//			assertEquals(newObject.getId(), loadObj.getId());
//			
//			//Effacer un seul objet 
//			session.delete(newObject);
//			//Récupérer un objet par son identifiant qui n'existe pas
//			ObjectDeletedException odeTest =null;
//			try
//			{
//				session.load(Locale.class, newObjectId);
//			}
//			catch(ObjectDeletedException ode)
//			{
//				odeTest = ode;
//			}
//			assertNotNull(odeTest);
//
//			tx.commit();
//			HibernateUtil.closeSession();
//		}
//		catch (HibernateException he)
//		{
//			he.printStackTrace();
//			throw new AssertionFailedError(he.getMessage());
//		}
//		long end = System.currentTimeMillis();
//		log.debug("Temps écoulé en seconde sur le test : " + (end - start) / 1000);
//	}
//
//	public void testDinnerTableLoad()
//	{
//		try
//		{
//			Session session = HibernateUtil.currentSession();
//			Transaction tx = session.beginTransaction();
//
//			System.out.println("Hello Debut");
//			Long IDDinnerTable = new Long(3268);
///*			
//			dinnerTable = (DinnerTable) session.get(DinnerTable.class, IDDinnerTable);
//
//			Set orders = dinnerTable.getOrders();
//			if (orders != null)
//			{
//				int j = 0;
//				System.out.println("Nombre de commande : " + orders.size());
//				for (Iterator i = orders.iterator(); i.hasNext(); j++)
//				{
//
//					OrderLine orl = (OrderLine) i.next();
//					//System.out.println("Numéro de commande : " + j + ", Code : " + orl.getProduct().getId() + " Désignation : " + orl.getLabel() + " : " + orl.getProduct().getLabel());
//				}
//			}
//			//SELECT * FROM t_order_line as orl, t_product as pdt WHERE orl.orl_product_code = pdt.pdt_code AND orl.dtb_id =  
//			//Iterator iter = session.iterate("Select orderLine.id, product.label from OrderLine as orderLine, Product as product where orderLine.productCode = product.code and orderLine.dinnerTable.id="+IDDinnerTable.longValue()+" and product.language='en' order by orl_id asc");
//			Iterator iter =
//				session.iterate(
//					"SELECT productLanguage from OrderLine as orderLine, ProductLanguage as productLanguage where productLanguage.id.product.id=orderLine.product.id and productLanguage.id.locale = 'en' and orderLine.dinnerTable.id=3268");
//			if (iter != null)
//			{
//				for (Iterator i = iter; i.hasNext();)
//				{
//					ProductLanguage o = (ProductLanguage) i.next();
//					for (Iterator k = orders.iterator(); k.hasNext();)
//					{
//						OrderLine orl = (OrderLine) k.next();
//						if (orl.getProduct().getId().equals(o.getId().getProduct().getId()))
//						{
//							//orl.getProduct().setLabel(o.getLabel());
//							//orl.getProduct().setLanguage(o.getId().getLocale().getId());
//							break;
//						}
//					}
//				}
//
//			}
//
//			if (orders != null)
//			{
//				int j = 0;
//				System.out.println("Nombre de commande : " + orders.size());
//				for (Iterator i = orders.iterator(); i.hasNext(); j++)
//				{
//
//					OrderLine orl = (OrderLine) i.next();
//					//System.out.println("Numéro de commande : " + j + ", Code : " + orl.getProduct().getId() + " Désignation : " + orl.getLabel() + " : " + orl.getProduct().getLabel());
//				}
//			}
//*/		
//			tx.commit();
//			session.close();
//		}
//		catch (Exception e)
//		{
//			e.printStackTrace();
//		}
//	}
//
//	public void testDinnerTableLoadLeftOuterJoinProduct()
//	{
//		try
//		{
//			Session session = HibernateUtil.currentSession();
//			Transaction tx = session.beginTransaction();
//
//			Long IDDinnerTable = new Long(3268);
///*
//			dinnerTable = (DinnerTable) session.get(DinnerTable.class, IDDinnerTable);
//
//			System.out.println("Hello Debut");
//			List iter =
//				session.find(
//					"SELECT orderLine FROM OrderLine as orderLine,  ProductLanguage productLanguage WHERE orderLine.product.id = productLanguage.id.product.id and productLanguage.id.language = 'en' and orderLine.dinnerTable.id=3268 ");
//
//			if (iter != null)
//			{
//				int j = 0;
//				for (Iterator i = iter.iterator(); i.hasNext(); j++)
//				{
//					OrderLine orl = (OrderLine) i.next();
//					//System.out.println("Numéro de commande : "+j+", Code : "+orl.getProduct().getId()+" Désignation : "+orl.getProduct().getId()+"  id "+orl.getProduct()+" language "+orl.getProduct());						
//				}
//
//			}
//*/
//		
//			tx.commit();
//			session.close();
//		}
//		catch (Exception e)
//		{
//		}
//	}
//
//	public void testToto()
//	{
//		/*
//		String t = "toto";
//		System.out.println("Toto : " + t);
//		//		assertEquals(t, "titi1");
//		assertEquals(false, true);
//*/
//	}
//
//	/* (non-Javadoc)
//	 * @see junit.framework.TestListener#addError(junit.framework.Test, java.lang.Throwable)
//	 */
//	public void addError(Test arg0, Throwable arg1)
//	{
//		System.out.println("Ajout d'une erreur : " + arg0);
//	}
//
//	/* (non-Javadoc)
//	 * @see junit.framework.TestListener#addFailure(junit.framework.Test, junit.framework.AssertionFailedError)
//	 */
//	public void addFailure(Test arg0, AssertionFailedError arg1)
//	{
//		System.out.println("Ajout d'une failure, nom du test : " + arg0);
//		System.out.println("Erreur : " + arg1);
//		System.out.println("Erreur : " + arg1.getLocalizedMessage());
//		System.out.println("Erreur : " + arg1.getMessage());
//		System.out.println("Erreur : " + arg1.getCause());
//		System.out.println("Erreur : " + arg1.getStackTrace().length);
//
//	}
//
//	/* (non-Javadoc)
//	 * @see junit.framework.TestListener#endTest(junit.framework.Test)
//	 */
//	public void endTest(Test arg0)
//	{
//
//	}
//
//	/* (non-Javadoc)
//	 * @see junit.framework.TestListener#startTest(junit.framework.Test)
//	 */
//	public void startTest(Test arg0)
//	{
//
//	}
//
//	public static void main(String[] args) throws Exception
//	{
//
//		long start = System.currentTimeMillis();
//		
//		TestSuite suite = new TestSuite();
//		
//
//		suite.addTest(new HibernateTest("testDeleteAll"));
//		suite.addTest(new HibernateTest("testLocale"));
//		suite.addTest(new HibernateTest("testLocaleLanguage"));		
//		suite.addTest(new HibernateTest("testUserRole"));
//		suite.addTest(new HibernateTest("testUserRoleLanguage"));
//		suite.addTest(new HibernateTest("testUserAuthentication"));
//		suite.addTest(new HibernateTest("testUser"));
//		suite.addTest(new HibernateTest("testUserLocale"));
//		suite.addTest(new HibernateTest("testCategory"));
//		suite.addTest(new HibernateTest("testCategoryLanguage"));
//		suite.addTest(new HibernateTest("testValueAddedTax"));	
//		suite.addTest(new HibernateTest("testProductSpecialCode"));
//		suite.addTest(new HibernateTest("testProductPart"));
//		suite.addTest(new HibernateTest("testProductPartLanguage"));
//		suite.addTest(new HibernateTest("testProduct"));
//		suite.addTest(new HibernateTest("testDinnerTable"));
//		suite.addTest(new HibernateTest("testProductLanguage"));
//		suite.addTest(new HibernateTest("testOrderLine"));
//		suite.addTest(new HibernateTest("testStatsConsumptionProduct"));
//		suite.addTest(new HibernateTest("testCategoryRelation"));
//		suite.addTest(new HibernateTest("testCashing"));
//		suite.addTest(new HibernateTest("testTypeTable"));
//		suite.addTest(new HibernateTest("testTypeTableLanguage"));
//		suite.addTest(new HibernateTest("testDayRevenue"));
//		suite.run(testResult);						
//
//
////		suite.addTest(new HibernateTest("testCategory"));
////		suite.addTest(new HibernateTest("testCategoryLanguage"));
////		suite.addTest(new HibernateTest("testUnit"));
//		suite.run(testResult);		
//				
//  		long end = System.currentTimeMillis();
//		log.debug("Temps Total écoulé en seconde sur le test : " + (end - start) / 1000);
//		
//	}
//	
//	public void testUnit()
//	{
//		long start = System.currentTimeMillis();		
//		try
//		{
//			Session session = HibernateUtil.currentSession();
//			Transaction tx = session.beginTransaction();
//			
//			
//			//Récupérer un objet par son identifiant
//			Product loadObj = (Product)session.load(Product.class, "2");
//
//			loadObj.getLabels();
//			
//			start = System.currentTimeMillis();
//			//Récupérer un objet par son identifiant
//			loadObj = (Product)session.load(Product.class, "ab");
//
//			tx.commit();
//			HibernateUtil.closeSession();
//		}
//		catch (HibernateException he)
//		{
//			he.printStackTrace();
//			throw new AssertionFailedError(he.getMessage());			
//		}
//		long end = System.currentTimeMillis();
//		System.out.println("Temps écoulé en seconde sur le test : " + (end - start) / 1000);
//		log.debug("Temps écoulé en seconde sur le test : " + (end - start) / 1000);
//	}

}
