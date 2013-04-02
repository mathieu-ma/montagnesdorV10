/*
 * Project				montagnesdor
 * File name		UserRoleLanguageID.java
 * Created on		16 juin 2004
 * @author			Mathieu MA sous conrad
 * @version		1.0
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package fr.montagnesdor.restaurant.model.hibernate;

import java.io.Serializable;

public class UserRoleLanguageID  implements Serializable
{
	private UserRole userRole;
	private Locale locale;
	/**
	 * @return
	 */
	public Locale getLocale()
	{
		return locale;
	}

	/**
	 * @return
	 */
	public UserRole getUserRole()
	{
		return userRole;
	}

	/**
	 * @param locale
	 */
	public void setLocale(Locale locale)
	{
		this.locale = locale;
	}

	/**
	 * @param role
	 */
	public void setUserRole(UserRole role)
	{
		userRole = role;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj)
	{
		if(obj instanceof  UserRoleLanguageID)
		{
			UserRoleLanguageID userRoleLanguageID = (UserRoleLanguageID) obj;
			return (this.userRole.getId() == userRoleLanguageID.getUserRole().getId() && this.locale.getId() == userRoleLanguageID.getLocale().getId());
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode()
	{
		return this.userRole.getId().hashCode()+this.locale.getId().hashCode();
	}


}
