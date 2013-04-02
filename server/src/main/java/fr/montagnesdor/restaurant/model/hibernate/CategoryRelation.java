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
 *	Cette classe est un mapping de la table t_category_relation.
 */
public class CategoryRelation
{
	
/*
    ctr_id serial,
	pdt_id varchar(5) NOT NULL,
	cat_id int8 NOT NULL,
	ctr_quantity numeric(10,2) NOT NULL DEFAULT 0.00,
*/

	private Long id;
	private Product product;
	private Category category;
	private float quantity; 

	/**
	 * @return
	 */
	public Category getCategory()
	{
		return category;
	}

/**
 * @return
 */
public Product getProduct()
{
	return product;
}

	/**
	 * @return
	 */
	public float getQuantity()
	{
		return quantity;
	}

	/**
	 * @param category
	 */
	public void setCategory(Category category)
	{
		this.category = category;
	}

/**
 * @param product
 */
public void setProduct(Product product)
{
	this.product = product;
}

	/**
	 * @param f
	 */
	public void setQuantity(float f)
	{
		quantity = f;
	}

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

}
