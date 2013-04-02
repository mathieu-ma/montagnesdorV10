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
 *	Cette classe est un mapping de la table t_order_part.
 */
public class CategoryLanguageID implements Serializable
{
	private Category category;
	private Locale locale;

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj)
	{
		if(obj instanceof  CategoryLanguageID)
		{
			CategoryLanguageID orderPartLanguageID = (CategoryLanguageID) obj;
			return (this.category.getId().longValue() == orderPartLanguageID.getCategory().getId().longValue() && this.locale.getId() == orderPartLanguageID.getLocale().getId());
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode()
	{
		return this.category.getId().hashCode()+this.locale.hashCode();
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
	public Category getCategory()
	{
		return category;
	}

	/**
	 * @param category
	 */
	public void setCategory(Category category)
	{
		this.category = category;
	}

}
