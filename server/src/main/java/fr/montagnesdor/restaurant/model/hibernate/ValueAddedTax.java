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
 *	Cette classe est un mapping de la table t_value_added_tax.
 */
public class ValueAddedTax
{
/*
  vat_id serial,
  vat_value numeric(10,2) NOT NULL DEFAULT 19.60,
 */	

	private Long id;
	private float value;
	
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
	public float getValue()
	{
		return value;
	}

	/**
	 * @param f
	 */
	public void setValue(float f)
	{
		value = f;
	}

/**
 * @param long1
 */
public void setId(Long long1)
{
	id = long1;
}

}
