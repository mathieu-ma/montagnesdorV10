package fr.montagnesdor.restaurant.services.authentication.spi;

import java.util.ArrayList;
import java.util.List;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;
import net.sf.hibernate.Transaction;

import fr.montagnesdor.restaurant.model.hibernate.HibernateUtil;
import fr.montagnesdor.restaurant.model.hibernate.Locale;
import fr.montagnesdor.restaurant.model.hibernate.User;
import fr.montagnesdor.restaurant.model.hibernate.UserAuthentication;
import fr.montagnesdor.restaurant.services.authentication.UserManager;
import fr.montagnesdor.util.log.MyLog;
import fr.montagnesdor.util.log.MyLogFactoryImpl;
import fr.montagnesdor.util.log.MyLogImpl;

public class DefaultUserManager implements UserManager
{
	/*
	SELECT T_COUNTRY_LANGUAGE.COU_CODE_ISO COU_CODE_ISO, T_COUNTRY_LANGUAGE.COU_NAME COU_NAME,
	(SELECT COUNT(1)  
	FROM T_STATS_USERS 
	WHERE STU_CONN_DATE >= TO_DATE( ? , 'DD/MM/YYYY' ) 
	AND STU_CONN_DATE <= TO_DATE( ? , 'DD/MM/YYYY HH24:MI:SS' ) 
	AND T_STATS_USERS.COU_CODE_ISO = T_COUNTRY_LANGUAGE.COU_CODE_ISO 
	GROUP BY T_STATS_USERS.COU_CODE_ISO) AS TOTAL, 
	DECODE(
	T_COUNTRY. COU_CODE_FORMAT, 
	1, (SELECT T2.DES_LIB FROM T_DESC_LANGUAGE T2 WHERE T2.DES_CODE='FORMAT.GROUP' AND T2.LAN_CODE_ISO=?),
	2, (SELECT T2.DES_LIB FROM T_DESC_LANGUAGE T2 WHERE T2.DES_CODE='EUROPE' AND T2.LAN_CODE_ISO=?),
	3, (SELECT T2.DES_LIB FROM T_DESC_LANGUAGE T2 WHERE T2.DES_CODE='AMERICAS' AND T2.LAN_CODE_ISO=?),
	4, (SELECT T2.DES_LIB FROM T_DESC_LANGUAGE T2 WHERE T2.DES_CODE='ASIA' AND T2.LAN_CODE_ISO=?)
	) AS COU_CODE_FORMAT_NAME
	FROM T_COUNTRY_LANGUAGE, T_COUNTRY 
	WHERE T_COUNTRY_LANGUAGE.LAN_CODE_ISO = ?
	AND T_COUNTRY.COU_CODE_ISO = T_COUNTRY_LANGUAGE.COU_CODE_ISO 
	ORDER BY T_COUNTRY. COU_CODE_FORMAT, T_COUNTRY_LANGUAGE.COU_NAME
	*/
	private final static String SELECT_COUNTRIES_CONNECTION =
		"SELECT T_COUNTRY_LANGUAGE.COU_CODE_ISO COU_CODE_ISO, T_COUNTRY_LANGUAGE.COU_NAME COU_NAME, "
			+ " (SELECT COUNT(1) "
			+ " FROM T_STATS_USERS "
			+ " WHERE STU_CONN_DATE >= TO_DATE( ? , 'DD/MM/YYYY' ) "
			+ " AND STU_CONN_DATE <= TO_DATE( ? , 'DD/MM/YYYY HH24:MI:SS' ) "
			+ "	AND T_STATS_USERS.COU_CODE_ISO = T_COUNTRY_LANGUAGE.COU_CODE_ISO "
			+ "	GROUP BY T_STATS_USERS.COU_CODE_ISO) AS TOTAL, "
			+ "	DECODE( "
			+ " T_COUNTRY. COU_CODE_FORMAT, "
			+ "	1, (SELECT T2.DES_LIB FROM T_DESC_LANGUAGE T2 WHERE T2.DES_CODE='FORMAT.GROUP' AND T2.LAN_CODE_ISO=?), "
			+ "	2, (SELECT T2.DES_LIB FROM T_DESC_LANGUAGE T2 WHERE T2.DES_CODE='EUROPE' AND T2.LAN_CODE_ISO=?), "
			+ "	3, (SELECT T2.DES_LIB FROM T_DESC_LANGUAGE T2 WHERE T2.DES_CODE='AMERICAS' AND T2.LAN_CODE_ISO=?), "
			+ "	4, (SELECT T2.DES_LIB FROM T_DESC_LANGUAGE T2 WHERE T2.DES_CODE='ASIA' AND T2.LAN_CODE_ISO=?) "
			+ "	) AS COU_CODE_FORMAT_NAME "
			+ "	FROM T_COUNTRY_LANGUAGE, T_COUNTRY "
			+ "	WHERE T_COUNTRY_LANGUAGE.LAN_CODE_ISO = ? "
			+ "	AND T_COUNTRY.COU_CODE_ISO = T_COUNTRY_LANGUAGE.COU_CODE_ISO "
			+ "	ORDER BY T_COUNTRY. COU_CODE_FORMAT, T_COUNTRY_LANGUAGE.COU_NAME ";

	private final static String SELECT_MONTHS =
		"SELECT DES_LIB, TO_NUMBER(SUBSTR(DES_CODE,16)) AS DES_CODE FROM T_DESC_LANGUAGE WHERE LAN_CODE_ISO=? AND DES_CODE LIKE 'CALENDAR.MONTH.%' ORDER BY DES_CODE";

	private static UserManager instance = null;
	private static MyLog logger = MyLogFactoryImpl.getInstance().getLogger(DefaultUserManager.class.getName());

	public DefaultUserManager()
	{
	}

	public static UserManager getInstance()
	{
		if (instance == null)
			synchronized (DefaultUserManager.class)
			{
				if (instance == null)
					instance = new DefaultUserManager();
			}

		return instance;
	}

	public List getConnections(String startDate, String endDate, String LAN_CODE_ISO)
	{
		List list = new ArrayList();

		/*		
				Connection connection = null;
				PreparedStatement stat = null;
				ResultSet result = null;
		
				try
				{
					connection = dataSource.connect();
		 			stat = connection.prepareStatement(SELECT_COUNTRIES_CONNECTION);
		 	 		stat.setString(1, startDate);
		 	 		stat.setString(2, endDate);
		 	 		stat.setString(3, LAN_CODE_ISO);
		 	 		stat.setString(4, LAN_CODE_ISO);
		 	 		stat.setString(5, LAN_CODE_ISO);
		 	 		stat.setString(6, LAN_CODE_ISO);
		 	 		stat.setString(7, LAN_CODE_ISO);
		
			  	  	result = stat.executeQuery();
			  	  	while (result.next()) 
			  	  	{
			  	  		StatsCountriesConnectionImpl countriesConnection = new StatsCountriesConnectionImpl();
			  	  		int total = 0;
			  	  		try
			  	  		{
				  	  		total = Integer.parseInt(result.getString("TOTAL"));
			  	  		}
			  	  		catch(NumberFormatException nfe)
			  	  		{
			  	  		}
			  	  		countriesConnection.setConnections(total);
			  	  		countriesConnection.setCountryCode(result.getString("COU_CODE_ISO"));
			  	  		countriesConnection.setCountryName(result.getString("COU_NAME"));
			  	  		countriesConnection.setCountryFormatName(result.getString("COU_CODE_FORMAT_NAME"));	
			  	  		list.add(countriesConnection);
				 	}
				}
				catch(QueryException qe)
				{
					qe.printStackTrace();
				}
				catch(SQLException sqle)
				{
					sqle.printStackTrace();
				}
				finally
				{
					try 
					{  
						if( result != null ) {
							result.close();
						}
					        if( stat != null ) {
							stat.close();                      
						}                                 
					        if( connection != null ) {
							connection.close();                      
						}                                 
					}
					catch(SQLException err) {}
				}
		
			*/
		return list;
	}

	public static void main(String[] args) throws Exception
	{
	}

	/* (non-Javadoc)
	 * @see fr.montagnesdor.restaurant.services.authentication.UserManager#getUserByLogin(java.lang.String)
	 */
	public User getUserByLogin(String login)
	{
		User user = new User();
		try
		{
			Session session = HibernateUtil.currentSession();
			Transaction tx = session.beginTransaction();

			//List userAuthenticationList = session.find("FROM UserAuthentication AS userAuthentication WHERE userAuthentication.login=?", login, new StringType());
			Query q = session.getNamedQuery("UserAuthentication.SelectByLogin");
			q.setString("login", login);
			List userAuthenticationList = q.list();
			UserAuthentication userAuthentication = null;
			if (userAuthenticationList != null && userAuthenticationList.size() == 1)
				userAuthentication = (UserAuthentication) userAuthenticationList.get(0);

			if (userAuthentication != null)
			{
				//List userList = session.find("FROM User AS user WHERE user.userAuthentication.id=?", userAuthentication.getId(), new LongType());
				q = session.getNamedQuery("User.SelectByUserAuthenticationId");
				q.setLong("userAuthenticationId", userAuthentication.getId().longValue());
				List userList = q.list();
				if (userList.size() == 1)
					user = (User) userList.get(0);
			}

			tx.commit();
			HibernateUtil.closeSession();
		}
		catch (HibernateException he)
		{
			logger.debug(MyLogImpl.MESSAGES.getString("message.error.authentication"));
		}

		return user;
	}

	public List getUserLocalesLanguages(User user, String currentLanguage)
	{
		List list = new ArrayList();
		try
		{
			Session session = HibernateUtil.currentSession();
			Transaction tx = session.beginTransaction();

			//Récupération des locales associées à l'utilisateur suivant la langue choisie par ce dernier
			Query q = session.getNamedQuery("LocaleLanguage.UserLocale.SelectLocaleLanguageByUserIdCurrentLanguage");
			q.setLong("userId", user.getId().longValue());
			q.setString("currentLanguage", currentLanguage);
			list = q.list();

			tx.commit();
			HibernateUtil.closeSession();
		}
		catch (HibernateException he)
		{
			logger.debug(MyLogImpl.MESSAGES.getString("message.error.authentication"));
		}

		return list;
	}

	public void changeUserPassword(User user, int levelPassword, String newPassword)
	{
		try
		{
			Session session = HibernateUtil.currentSession();
			Transaction tx = session.beginTransaction();
			
			UserAuthentication userAuthentication = user.getUserAuthentication();
			switch (levelPassword)
			{
				case 0:
					userAuthentication.setPassword(newPassword);				
				break;
				case 1:
					userAuthentication.setLevelPass1(newPassword);				
				break;
				case 2:
					userAuthentication.setLevelPass2(newPassword);				
				break;
				case 3:
					userAuthentication.setLevelPass3(newPassword);				
				break;
			}
			session.update(userAuthentication);

			tx.commit();
			HibernateUtil.closeSession();
		}
		catch (HibernateException he)
		{
			logger.debug(MyLogImpl.MESSAGES.getString("message.error.password.save"));
		}
	}
	
	public void changePrintLanguage(User user, String preferedPrintLanguage)
	{
		try
		{
			Session session = HibernateUtil.currentSession();
			Transaction tx = session.beginTransaction();
			
			Locale locale = (Locale)session.load(Locale.class, preferedPrintLanguage);
			user.setPreferedPrintLanguage(locale);
			session.update(user);

			tx.commit();
			HibernateUtil.closeSession();
		}
		catch (HibernateException he)
		{
			logger.debug(MyLogImpl.MESSAGES.getString("message.error.print.language.save"));
		}
	}
}
