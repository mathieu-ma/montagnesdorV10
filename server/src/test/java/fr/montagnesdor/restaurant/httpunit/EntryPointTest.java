/*
 * Project				montagnesdor
 * File name		EntryPointTest.java
 * Created on		7 sept. 2004
 * @author			Mathieu MA sous conrad
 * @version		1.0
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package fr.montagnesdor.restaurant.httpunit;

import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebLink;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;


import junit.framework.AssertionFailedError;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestListener;
import junit.framework.TestResult;
import junit.framework.TestSuite;

public class EntryPointTest extends TestCase implements TestListener
{

	private static String protocol = "http";
	private static String hostname = "localhost";
	private static String port = "8080";
	//private static String path = "/montagnesdor/EntryPoint.do";
	private static String path = "/montagnesdor/index.jsp";

	private static TestResult testResult;

	public EntryPointTest(String name)
	{
		super(name);
		testResult = createResult();
		testResult.addListener(this);
	}

	public void testLogon()
	{
		try
		{
			WebConversation webConversation = new WebConversation();
			String url = protocol + "://" + hostname + ":" + port + path;
			WebRequest webRequest = new GetMethodWebRequest(url);
			WebResponse loginResponse = webConversation.getResponse(webRequest);
			loginResponse.getFormWithName("LogonForm").setParameter("login", "Loginaa");
			loginResponse.getFormWithName("LogonForm").setParameter("password", "Passwordaa");  
			WebLink[] links = loginResponse.getLinks();
			links[0].click();   
			WebResponse endResponse = webConversation.getCurrentPage();
			String ct = endResponse.getContentType();
			
		}
		catch (Exception e)
		{
			System.out.println("Erreur testLogon");
		}
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestListener#addError(junit.framework.Test, java.lang.Throwable)
	 */
	public void addError(Test arg0, Throwable arg1)
	{
		System.out.println("Ajout d'une erreur : " + arg0);
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestListener#addFailure(junit.framework.Test, junit.framework.AssertionFailedError)
	 */
	public void addFailure(Test arg0, AssertionFailedError arg1)
	{
		System.out.println("Ajout d'une failure, nom du test : " + arg0);
		System.out.println("Erreur : " + arg1);
		System.out.println("Erreur : " + arg1.getLocalizedMessage());
		System.out.println("Erreur : " + arg1.getMessage());
		System.out.println("Erreur : " + arg1.getCause());
		System.out.println("Erreur : " + arg1.getStackTrace().length);

	}

	/* (non-Javadoc)
	 * @see junit.framework.TestListener#endTest(junit.framework.Test)
	 */
	public void endTest(Test arg0)
	{

	}

	/* (non-Javadoc)
	 * @see junit.framework.TestListener#startTest(junit.framework.Test)
	 */
	public void startTest(Test arg0)
	{

	}

	public static void main(String[] args) throws Exception
	{

		long start = System.currentTimeMillis();

		TestSuite suite = new TestSuite();

		/*
				suite.addTest(new HibernateTest("testDeleteAll"));
				suite.addTest(new HibernateTest("testLocale"));
				suite.addTest(new HibernateTest("testUserRole"));
				suite.addTest(new HibernateTest("testUserRoleLanguage"));
				suite.addTest(new HibernateTest("testUserAuthentication"));
				suite.addTest(new HibernateTest("testUser"));
				suite.addTest(new HibernateTest("testCategory"));
				suite.addTest(new HibernateTest("testValueAddedTax"));	
				suite.addTest(new HibernateTest("testProductSpecialCode"));
				suite.addTest(new HibernateTest("testProduct"));
				suite.addTest(new HibernateTest("testOrderPart"));
				suite.addTest(new HibernateTest("testOrderPartLanguage"));
				suite.addTest(new HibernateTest("testDinnerTable"));
				suite.addTest(new HibernateTest("testProductLanguage"));
				suite.addTest(new HibernateTest("testOrderLine"));
				suite.addTest(new HibernateTest("testStatsConsumptionProduct"));
				suite.addTest(new HibernateTest("testCategoryRelation"));
				suite.addTest(new HibernateTest("testCashing"));
				suite.addTest(new HibernateTest("testTypeTable"));
				suite.addTest(new HibernateTest("testTypeTableLanguage"));
				suite.addTest(new HibernateTest("testDayRevenue"));				
		*/

		suite.addTest(new EntryPointTest("testLogon"));
		suite.run(testResult);

		long end = System.currentTimeMillis();
	}
}
