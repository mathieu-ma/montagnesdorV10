/*
 * Created on 29 avr. 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package fr.montagnesdor.restaurant.model.hibernate;

import java.io.Serializable;

/**
 * @author Mathieu MA sous conrad
 *
 *	Cette classe est un mapping de la table t_locale_language.
 */
public class LocaleLanguageID implements Serializable
{
	/*
		loc_id varchar(3) NOT NULL DEFAULT 'fr',
  		loc_id_language varchar(3) NOT NULL DEFAULT 'fr',
  		lol_label varchar(50) NOT NULL,
	*/  
	private Locale locale;
	private Locale language;

	
	/**
	 * @return
	 */
	public Locale getLanguage()
	{
		return language;
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
	public void setLanguage(Locale locale)
	{
		language = locale;
	}

	/**
	 * @param locale
	 */
	public void setLocale(Locale locale)
	{
		this.locale = locale;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj)
	{
		if(obj instanceof  LocaleLanguageID)
		{
			LocaleLanguageID localeLanguageID = (LocaleLanguageID) obj;
			return (this.locale.getId() == localeLanguageID.getLocale().getId() && this.language.getId() == localeLanguageID.getLanguage().getId());
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode()
	{
		return this.locale.getId().hashCode()+this.language.hashCode();
	}

}
