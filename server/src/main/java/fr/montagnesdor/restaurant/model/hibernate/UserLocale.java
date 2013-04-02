/*
 * Created on 29 avr. 2004
 */
package fr.montagnesdor.restaurant.model.hibernate;

/**
 * @author Mathieu MA sous conrad
 *
 *	Cette classe est un mapping de la table t_user.
  */
public class UserLocale
{
/*
  ulo_id serial,
  usr_id int8 NOT NULL,
  loc_id varchar(3) NOT NULL DEFAULT 'fr',
*/

  private Long id;
  private User user;
  private Locale locale;
  
  	/**
	 * @return
	 */
	public Long getId()
	{
		return id;
	}
	
	/**
	 * @param long1
	 */
	public void setId(Long long1)
	{
		id = long1;
	}
	
	/**
	 * @return
	 */
	public Locale getLocale()
	{
		return locale;
	}
	
	/**
	 * @param locale
	 */
	public void setLocale(Locale locale)
	{
		this.locale = locale;
	}

	/**
	 * @return
	 */
	public User getUser()
	{
		return user;
	}
	
	/**
	 * @param user
	 */
	public void setUser(User user)
	{
		this.user = user;
	}

}
