package fr.montagnesdor.restaurant.struts;

import java.util.Date;
import java.util.List;

import javax.security.auth.Subject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;

import org.apache.struts.Globals;

import fr.montagnesdor.restaurant.Constants;
import fr.montagnesdor.restaurant.model.hibernate.User;
//import fr.montagnesdor.restaurant.modele.metier.Salle;
import fr.montagnesdor.restaurant.model.hibernate.Room;
import fr.montagnesdor.restaurant.services.authentication.UserManagerFactory;
import fr.montagnesdor.util.FormatDate;
import fr.montagnesdor.util.log.MyLog;
import fr.montagnesdor.util.log.MyLogFactoryImpl;


public final class UserSession
{
    public static final String TABLE_LIST_FILTER_ALL = "0";
    public static final String TABLE_LIST_FILTER_PRINT = "1";
    public static final String TABLE_LIST_FILTER_CASH = "2";
    public static final String TABLE_LIST_FILTER_TAKEAWAY = "3";
    public static final String TABLE_LIST_FILTER_INPLACE = "4";
    
    private HttpSession session = null;
    private ServletContext servletContext = null;
    private Subject subject = null;
	private User user = null;
	private String currentLanguage = null;
	private String currentTableListFilter = TABLE_LIST_FILTER_ALL;
	
    public UserSession(ServletContext servletContext, HttpSession session, User user)
    {
		MyLog logger = MyLogFactoryImpl.getInstance().getLogger(UserSession.class.getName());
		this.user = user;
        this.servletContext = servletContext;
        this.session = session;
/*        
        int delay = 900;
        try
        {
            delay = Integer.parseInt(resources.getString("usersession.delay"));
            
			logger.debug("Delai trouv� sur resources "+delay);
        }
        catch(Exception e)
        {
			logger.info("Delai par d�faut est "+delay, e);
        }
*/        
        if(servletContext.getAttribute("salleDragon")==null)
        {
			logger.debug("Salle Dragon n'existe pas encore");
            servletContext.setAttribute("salleDragon", new Room("dragon"));
			logger.debug("Fin de cr�ation de la salle Dragon");
        }
        else
        {
			logger.debug("Salle Dragon existe d�éj�à "+session.getAttribute("salleDragon"));
        }
        
//        session.setMaxInactiveInterval(delay);
        
    }
    
    public Room getRoom()
    {
        return (Room)servletContext.getAttribute("salleDragon");
    }
	/**
	 * Returns the subject.
	 * @return Subject
	 */
	public Subject getSubject()
	{
		return subject;
	}

	/**
	 * Sets the subject.
	 * @param subject The subject to set
	 */
	public void setSubject(Subject subject)
	{
		this.subject = subject;
	}

	/**
	 * Returns the user.
	 * @return User
	 */
	public User getUser()
	{
		return user;
	}
	
	public String getName()
	{
		return user==null?"":user.getName();
	}
	
	public String getForename1()
	{
		return user==null?"":user.getForename1();
	}
	
	public String getForename2()
	{
			return user==null?"":user.getForename2();
	}

	public String getLevelPass1()
	{
			return (user==null || user.getUserAuthentication()==null || user.getUserAuthentication().getLevelPass1()==null)?"":"X";
	}

	public String getLevelPass2()
	{
			return (user==null || user.getUserAuthentication()==null || user.getUserAuthentication().getLevelPass2()==null)?"":"X";
	}
	
	public String getLevelPass3()
	{
			return (user==null || user.getUserAuthentication()==null || user.getUserAuthentication().getLevelPass3()==null)?"":"X";
	}
	
	public String getPicture()
	{
		return (user==null || user.getPicture()==null || user.getUserAuthentication()==null)?Constants.DEFAULT_PICTURE_NAME:(user.getUserAuthentication().getLogin()+"."+Constants.DEFAULT_PICTURE_NAME_EXTENSION);
	}

	public int getSex()
	{
		return (user==null)?0:user.getSex();
	}

	public String getRole()
	{
		return (user==null || user.getUserRole()==null)?"":user.getUserRole().getLabelCode();
	}

	public String getLogin()
	{
		return (user==null || user.getUserAuthentication()==null)?"":user.getUserAuthentication().getLogin();
	}

	public String getBirthday()
	{
		Date temp = new Date();
		if(user!=null && user.getBirthdate()!=null) 
			temp = user.getBirthdate();
		return FormatDate.dateToLongString(temp);
	}

	public String getCurrentLanguage()
	{
		return currentLanguage;
	}
	
	public void setCurrentLanguage(String language)
	{
		currentLanguage = language;
	}
	
	public List getLocalesLanguages()
	{
		List result = user.getLocalesLanguages();
		if(result==null || result.size()==0)
		{
			currentLanguage = Constants.DEFAULT_LOCALE_LANGUAGE;
			//S'assurer que dans la table t_locale_language de la base de données, loc_id_language existe pour la constante Constants.DEFAULT_LOCALE  
			user.setLocalesLanguages(UserManagerFactory.getManager().getUserLocalesLanguages(user, currentLanguage));
		}
		return user.getLocalesLanguages();
	}

	public void processLanguage(String language, String country)
	{
		//Permet de s'assurer que le langage courante(currentLanguage) est remis à la langue par défaut(Français) 
		//dans le cas où la liste des langues de l'utilisateur est vide  
		List result = user.getLocalesLanguages();
		if(result==null || result.size()==0)
		{
			currentLanguage = language = Constants.DEFAULT_LOCALE_LANGUAGE;
			//S'assurer que dans la table t_locale_language de la base de données, loc_id_language existe pour la constante Constants.DEFAULT_LOCALE  
			user.setLocalesLanguages(UserManagerFactory.getManager().getUserLocalesLanguages(user, currentLanguage));
		}
		
		if(language==null)
			currentLanguage = Constants.DEFAULT_LOCALE_LANGUAGE;
		else
			currentLanguage = language;
		
		if(country == null)
			country = Constants.DEFAULT_LOCALE_COUNTRY;	
	
		//Struts Configuration For I18n
		session.setAttribute(Globals.LOCALE_KEY, new java.util.Locale(currentLanguage, country));
		//JSTL Configuration For I18n
		Config.set(session, Config.FMT_LOCALE, new java.util.Locale(currentLanguage, country));
	}

/*	
	public String getPasswordByLevel(int levelPassword)
	{
		String result = "";
		switch(levelPassword)
		{
			case 0 :
				result = user.getUserAuthentication().getPassword();
			break;	
			case 1 :
				result = this.getLevelPass1();
			break;	
			case 2 :
				result = this.getLevelPass2();
			break;	
			case 3 :
				result = this.getLevelPass3();
			break;	
		}
		
		return result;
	}
*/	
    public String getCurrentTableListFilter()
    {
        return currentTableListFilter;
    }
    public void setCurrentTableListFilter(String currentTableListFilter)
    {
        this.currentTableListFilter = currentTableListFilter;
    }
}