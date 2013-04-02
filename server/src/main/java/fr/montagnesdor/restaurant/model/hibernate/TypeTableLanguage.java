/*
 * Created on 29 avr. 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package fr.montagnesdor.restaurant.model.hibernate;

/**
 * @author Mathieu MA sous conrad
 *
 *	Cette classe est un mapping de la table t_type_table_language.
 */
public class TypeTableLanguage
{
	/*
		ttl_id serial,
		ttb_id int8 NOT NULL,
		ttl_label varchar(50) NOT NULL,
		loc_id varchar(3) NOT NULL DEFAULT 'fr',
	*/

	private Long id;
	private TypeTable typeTable;
	private String label;
	private Locale locale;

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
	public String getLabel()
	{
		return label;
	}

	/**
	 * @return
	 */
	public TypeTable getTypeTable()
	{
		return typeTable;
	}

	/**
	 * @param string
	 */
	public void setLabel(String string)
	{
		label = string;
	}

	/**
	 * @param table
	 */
	public void setTypeTable(TypeTable table)
	{
		typeTable = table;
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

}
