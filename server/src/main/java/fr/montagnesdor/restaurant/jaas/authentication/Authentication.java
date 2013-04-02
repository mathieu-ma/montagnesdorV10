package fr.montagnesdor.restaurant.jaas.authentication;

import javax.security.auth.Subject;

import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

import fr.montagnesdor.restaurant.model.hibernate.User;

/**
 * @author administrateur
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class Authentication
{
	private User user = null;
	private String login;
	private String password;
	private String levelPassword;
	private LoginContext lc;

	static
	{
//		ResourceBundle resource = ResourceBundle.getBundle(Authentification.class.getName());
		System.setProperty("java.security.auth.login.config", Authentication.class.getResource("montagnesdorjaas.login").getPath());
		//System.setProperty("java.security.auth.login.config", Authentication.class.getResource("montagnesdorjaas.login").toString());		
		System.setProperty("java.security.auth.policy", Authentication.class.getResource("montagnesdorjaas.policy").getPath());
	}

	public Authentication(String login, String password)
	{
		this(login, password, null);

//System.out.println("java.security.auth.login.config : "+ Authentication.class.getResource("montagnesdorjaas.login").getPath());
//System.out.println("java.security.auth.policy : " + Authentication.class.getResource("montagnesdorjaas.policy").getPath());
	}

	public Authentication(String login, String password, String levelPassword)
	{
		this.login = login;
		this.password = password;
		this.levelPassword = levelPassword;
	}


	/**
	 * @see org.apache.struts.webapp.example.Auth#authenticate()
	 */
	public boolean authenticate()
	{
		try
		{
			MyCallBackHandler myCallBackhandler = new MyCallBackHandler(login, password);
			lc = new LoginContext("LoginDataBase", myCallBackhandler);
			lc.login();
			user = myCallBackhandler.getUser();
		}
		catch (LoginException le)
		{
			le.printStackTrace();
			return false;
		}

		return true;
	}
	
	/**
	 * @see org.apache.struts.webapp.example.Auth#getSubject()
	 */
	public Subject getSubject()
	{
		if (lc == null)
		{
			throw new IllegalStateException("either login failed or the authenticate method hasn't been called.");
		}
		else
		{
			return lc.getSubject();
		}
	}
	
	/**
	 * Returns the user.
	 * @return User
	 */
	public User getUser()
	{
		return user;
	}
	
	public static void main(String[] args)
	{
		Authentication authentification = new Authentication("kimsan", "kimsan");
		if(authentification.authenticate())
		{
			System.out.println("Vous �tes autentifi�s"); 
		}
		else
			System.out.println("Vous n'�tes pas autentifi�s");
	}
}
