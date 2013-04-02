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
 *	Cette classe est un mapping de la table t_category.
 */
public class Category
{
	/*
		cat_id serial,
	*/
	private Long id;

	//Liste des libellés suivant le language
	private Map labels;

	//Ce champ n'est pas stocker dans la base
	private String currentLanguage = Locale.FRENCH.getLanguage();

	//Ce champ n'est pas stocker dans la base
	private String currentLabel;

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

    public Map getLabels()
    {
        return labels;
    }
    
    public void setLabels(Map labels)
    {
        this.labels = labels;
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
