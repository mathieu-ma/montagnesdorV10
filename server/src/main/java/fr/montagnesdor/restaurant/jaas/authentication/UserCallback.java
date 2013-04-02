package fr.montagnesdor.restaurant.jaas.authentication;

import java.io.Serializable;

import javax.security.auth.callback.Callback;

import fr.montagnesdor.restaurant.model.hibernate.User;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class UserCallback implements Callback, Serializable 
{
	
	private User user = null;
	
	public UserCallback(User user)
	{
		this.user = user;
	}

	/**
	 * Returns the user.
	 * @return User
	 */
	public User getUser()
	{
		return user;
	}

}
