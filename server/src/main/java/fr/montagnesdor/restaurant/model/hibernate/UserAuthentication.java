/*
 * Created on 29 avr. 2004
 *
  */
package fr.montagnesdor.restaurant.model.hibernate;

/**
 * @author Mathieu MA sous conrad
 *
 *	Cette classe est un mapping de la table t_user_authentication.
 */
public class UserAuthentication
{
/*
  aut_id serial,
  aut_login varchar(20) NOT NULL,  
  aut_password varchar(20) NOT NULL,
  aut_level_pass1 varchar(20),
  aut_level_pass2 varchar(20),
  aut_level_pass3 varchar(20),
*/
	private Long id;
	private String login;
	private String password;
	private String levelPass1;
	private String levelPass2;
	private String levelPass3;


	public UserAuthentication()
	{
	}
	
	public UserAuthentication(String login)
	{
	}
	
	/**
	 * @return
	 */
	public Long getId()
	{
		return id;
	}

	/**
	 * @return
	 */
	public String getLevelPass1()
	{
		return levelPass1;
	}

	/**
	 * @return
	 */
	public String getLevelPass2()
	{
		return levelPass2;
	}

	/**
	 * @return
	 */
	public String getLevelPass3()
	{
		return levelPass3;
	}

	/**
	 * @return
	 */
	public String getLogin()
	{
		return login;
	}

	/**
	 * @param string
	 */
	public void setLevelPass1(String string)
	{
		levelPass1 = string;
	}

	/**
	 * @param string
	 */
	public void setLevelPass2(String string)
	{
		levelPass2 = string;
	}

	/**
	 * @param string
	 */
	public void setLevelPass3(String string)
	{
		levelPass3 = string;
	}

	/**
	 * @param string
	 */
	public void setLogin(String string)
	{
		login = string;
	}

	/**
	 * @return
	 */
	public String getPassword()
	{
		return password;
	}

	/**
	 * @param string
	 */
	public void setPassword(String string)
	{
		password = string;
	}

/**
 * @param long1
 */
public void setId(Long long1)
{
	id = long1;
}

}
