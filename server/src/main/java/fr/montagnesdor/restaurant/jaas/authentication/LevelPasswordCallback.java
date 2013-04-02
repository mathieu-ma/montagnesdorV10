package fr.montagnesdor.restaurant.jaas.authentication;

import java.io.Serializable;

import javax.security.auth.callback.Callback;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class LevelPasswordCallback implements Callback, Serializable 
{
	
	private String levelPassword = null;
	
	public LevelPasswordCallback(String levelPassword)
	{
		this.levelPassword = levelPassword;
	}

	/**
	 * Returns the levelPassword.
	 * @return String
	 */
	public String getLevelPassword()
	{
		return levelPassword;
	}

	/**
	 * @param string
	 */
	public void setLevelPassword(String string)
	{
		levelPassword = string;
	}

}
