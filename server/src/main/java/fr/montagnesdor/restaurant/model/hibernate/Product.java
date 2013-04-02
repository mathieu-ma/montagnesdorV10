/*
 * Created on 29 avr. 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package fr.montagnesdor.restaurant.model.hibernate;

import java.util.Locale;
import java.util.Map;

/**
 * @author Mathieu MA sous conrad
 *
 *	Cette classe est un mapping de la table t_product.
 */
public class Product
{
	/*
--pdt_id : Code produit, identifiant de cette table
--psc_id : Identifiant de la table t_product_special_code. Ce champ permet de savoir s'il faut faire un traitement spécial sur ce produit, par exemple quand le champ pdt_id est "#", cela veut dire la ligne de commande correspondant à ce code est offert, la valeur du produit offert se trouve dans le champ orl_special_code_value de la table t_order_line
--pdt_price : Prix du produit
--pdt_colorRGB : Couleur du produit(de la forme FFFFFF) : permet par exemple d'afficher une ligne de commande dans la couleur choisie, si cette valeur est nulle on garde la couleur par défaut du css
--vat_id : Identifiant de la table t_value_added_tax permettant de récupérer la valeur en pourcentage de la TVA du produit : ceci représente la valeur du champ vat_value de la table t_value_added_tax : c'est une clé étrangère de la table t_value_added_tax
	*/

	private String id;
	private ProductSpecialCode productSpecialCode;
	private float price;
	private String colorRGB;
	private ValueAddedTax vat;
	
	//Liste des libellés suivant le language
	private Map labels;

	//Le chanp currentLabel n'est pas en base
	private String currentLabel;
	
	//Le champ currentLanguage n'est pas en base
	private String currentLanguage = Locale.FRANCE.getLanguage() ;
	
	/**
	 * @return
	 */
	public String getId()
	{
		return id;
	}

	/**
	 * @return
	 */
	public float getPrice()
	{
		return price;
	}

	/**
	 * @return
	 */
	public ValueAddedTax getVat()
	{
		return vat;
	}

	/**
	 * @param f
	 */
	public void setPrice(float f)
	{
		price = f;
	}

	/**
	 * @param tax
	 */
	public void setVat(ValueAddedTax tax)
	{
		vat = tax;
	}

	/**
	 * @param long1
	 */
	public void setId(String string)
	{
		id = string;
	}

	/**
	 * @return
	 */
	public ProductSpecialCode getProductSpecialCode()
	{
		return productSpecialCode;
	}

	/**
	 * @param code
	 */
	public void setProductSpecialCode(ProductSpecialCode code)
	{
		productSpecialCode = code;
	}

	/**
	 * @return
	 */
	public Map getLabels()
	{
		return labels;
	}

	/**
	 * @param map
	 */
	public void setLabels(Map map)
	{
		labels = map;
	}

    /**
     * @return Renvoie colorRGB.
     */
    public String getColorRGB()
    {
        return colorRGB;
    }
    /**
     * @param colorRGB colorRGB à définir.
     */
    public void setColorRGB(String colorRGB)
    {
        this.colorRGB = colorRGB;
    }
    public String getCurrentLabel()
    {
        return currentLabel;
    }
    public void setCurrentLabel(String currentLabel)
    {
        this.currentLabel = currentLabel;
    }
    public String getCurrentLanguage()
    {
        return currentLanguage;
    }
    public void setCurrentLanguage(String currentLanguage)
    {
        this.currentLanguage = currentLanguage;
    }
}
