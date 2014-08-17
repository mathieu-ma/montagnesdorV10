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
 *	Cette classe est un mapping de la table t_user_role.
 */
public class UserRole
{
	/*
	  rol_id serial,
	  rol_label_code varchar(20) NOT NULL,  
	 */

	private Long id;
	private String labelCode;
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the labelCode
	 */
	public String getLabelCode() {
		return labelCode;
	}
	/**
	 * @param labelCode the labelCode to set
	 */
	public void setLabelCode(String labelCode) {
		this.labelCode = labelCode;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserRole [id=" + id + ", labelCode=" + labelCode + "]";
	}

}
