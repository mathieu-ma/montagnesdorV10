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
 *	Cette classe est un mapping de la table t_order_line.
 */
public class OrderLine
{
	/*
		orl_id serial,
		dtb_id int8 NOT NULL,
		orl_product_code varchar(5) NOT NULL,
		orl_label varchar(50) NOT NULL,
		orl_quantity numeric(10,2) NOT NULL DEFAULT 0.00,
		orl_unit_price numeric(10,2) NOT NULL DEFAULT 0.00,
		orl_amount numeric(10,2) NOT NULL DEFAULT 0.00,
--prp_id : Identifiant de la table t_product_part : c'est une clé étrangère de cette table t_product
		
		vat_id int8 NOT NULL DEFAULT 1,
	*/

	private Long id;
	private DinnerTable dinnerTable;
	private float quantity;

	private ProductPart productPart;

	private Product product;
	private String specialCodeValue;
	private Product specialCodeValueProduct;
	private String label;
	private float unitPrice;

	private float amount;

	
	/*
		public OrderLine()
		{
		}
	
	
		public OrderLine(OrderLine orderLine, String label)
		{
			this.setId(orderLine.getId());
			this.setDinnerTable(orderLine.getDinnerTable());
			this.setOrderPart(orderLine.getOrderPart());
			this.setProductCode(orderLine.getProductCode());
			this.setLabel(label);
			this.setQuantity(orderLine.getQuantity());
			this.setUnitPrice(orderLine.getUnitPrice());
			this.setAmount(orderLine.getAmount());
			this.setVat(orderLine.getVat());
		}
	*/
	/**
	 * @return
	 */
	public float getAmount()
	{
		return amount;
	}

	/**
	 * @return
	 */
	public DinnerTable getDinnerTable()
	{
		return dinnerTable;
	}

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
	public float getQuantity()
	{
		return quantity;
	}

	/**
	 * @param f
	 */
	public void setAmount(float f)
	{
		amount = f;
	}

	/**
	 * @param table
	 */
	public void setDinnerTable(DinnerTable table)
	{
		dinnerTable = table;
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
	public float getUnitPrice()
	{
		return unitPrice;
	}

	/**
	 * @param string
	 */
	public void setLabel(String string)
	{
		label = string;
	}

	/**
	 * @param f
	 */
	public void setUnitPrice(float f)
	{
		unitPrice = f;
	}


	/**
	 * @return
	 */
	public Product getProduct()
	{
		return product;
	}

	/**
	 * @param product
	 */
	public void setProduct(Product product)
	{
		this.product = product;
	}


	/**
	 * @return
	 */
	public String getSpecialCodeValue()
	{
		return specialCodeValue;
	}

	/**
	 * @return
	 */
	public Product getSpecialCodeValueProduct()
	{
		return specialCodeValueProduct;
	}

	/**
	 * @param string
	 */
	public void setSpecialCodeValue(String string)
	{
		specialCodeValue = string;
	}

	/**
	 * @param product
	 */
	public void setSpecialCodeValueProduct(Product product)
	{
		specialCodeValueProduct = product;
	}

	/**
	 * @return
	 */
	public ProductPart getProductPart()
	{
		return productPart;
	}

	/**
	 * @param part
	 */
	public void setProductPart(ProductPart part)
	{
		productPart = part;
	}
	
}
