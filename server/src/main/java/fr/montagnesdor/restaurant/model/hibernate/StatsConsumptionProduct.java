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
 *	Cette classe est un mapping de la table t_stats_consumption_product.
 */
public class StatsConsumptionProduct
{
/*	
	scp_id serial,
	scp_update_date timestamp NOT NULL,
	pdt_id int8 NOT NULL,
	scp_quantity numeric(10,2) NOT NULL DEFAULT 0.00,
*/

	private Long id;
	private Short updatedDay;
	private Short updatedMonth;
	private Short updatedYear;
	private Product product;
	private float quantity;

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
 * @param long1
 */
public void setId(Long long1)
{
	id = long1;
}

    public Short getUpdatedDay()
    {
        return updatedDay;
    }
    public void setUpdatedDay(Short updatedDay)
    {
        this.updatedDay = updatedDay;
    }
    public Short getUpdatedMonth()
    {
        return updatedMonth;
    }
    public void setUpdatedMonth(Short updatedMonth)
    {
        this.updatedMonth = updatedMonth;
    }
    public Short getUpdatedYear()
    {
        return updatedYear;
    }
    public void setUpdatedYear(Short updatedYear)
    {
        this.updatedYear = updatedYear;
    }
}
