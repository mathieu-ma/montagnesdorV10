/*
 * Created on 29 avr. 2004
 */
package fr.montagnesdor.restaurant.model.hibernate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Mathieu MA sous conrad
 *
 *	Cette classe est un mapping de la table t_user.
  */
public class User
{
/*
  usr_id serial,
  aut_id int8 NOT NULL DEFAULT 0,
  rol_id int8 NOT NULL DEFAULT 0,
  loc_id varchar(3),  
  usr_name varchar(20) NOT NULL,  
  usr_forename1 varchar(20) NOT NULL,
  usr_forename2 varchar(20),
  usr_birthdate date NOT NULL,
  usr_sex int2 NOT NULL DEFAULT 0,
  usr_picture bytea,
*/
  private Long id;
  private UserAuthentication userAuthentication = new UserAuthentication();
  private UserRole userRole = new UserRole();
  private String name;
  private String forename1;
  private String forename2;
  private Date birthdate;
  private byte sex;
  private byte[] picture;
  private Locale preferedPrintLanguage;
  //Ce champ n'est pas définie dans le fichier User.hbm.xml
  private List localesLanguages;
  
  private List<UserPhone> phones = new ArrayList<UserPhone>();
  
  private List<UserEmail> emails = new ArrayList<UserEmail>();

  private List<UserAddress> addresses = new ArrayList<UserAddress>();

	/**
	 * @return
	 */
	public Date getBirthdate()
	{
		return birthdate;
	}
	
	/**
	 * @return
	 */
	public String getForename1()
	{
		return forename1;
	}
	
	/**
	 * @return
	 */
	public String getForename2()
	{
		return forename2;
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
	public String getName()
	{
		return name;
	}
	
	/**
	 * @return
	 */
	public byte[] getPicture()
	{
		return picture;
	}
	
	/**
	 * @return
	 */
	public byte getSex()
	{
		return sex;
	}
	
	/**
	 * @return
	 */
	public UserAuthentication getUserAuthentication()
	{
		return userAuthentication;
	}
	
	/**
	 * @return
	 */
	public UserRole getUserRole()
	{
		return userRole;
	}
	
	/**
	 * @param date
	 */
	public void setBirthdate(Date date)
	{
		birthdate = date;
	}
	
	/**
	 * @param string
	 */
	public void setForename1(String string)
	{
		forename1 = string;
	}
	
	/**
	 * @param string
	 */
	public void setForename2(String string)
	{
		forename2 = string;
	}
	
	/**
	 * @param string
	 */
	public void setName(String string)
	{
		name = string;
	}
	
	/**
	 * @param bs
	 */
	public void setPicture(byte[] bs)
	{
		picture = bs;
	}
	
	/**
	 * @param b
	 */
	public void setSex(byte b)
	{
		sex = b;
	}
	
	/**
	 * @param authentication
	 */
	public void setUserAuthentication(UserAuthentication authentication)
	{
		userAuthentication = authentication;
	}
	
	/**
	 * @param role
	 */
	public void setUserRole(UserRole role)
	{
		userRole = role;
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
	public List getLocalesLanguages()
	{
		return localesLanguages;
	}
	
	/**
	 * @param list
	 */
	public void setLocalesLanguages(List list)
	{
		localesLanguages = list;
	}

	public Locale getPreferedPrintLanguage()
	{
	    return preferedPrintLanguage;
	}
	public void setPreferedPrintLanguage(Locale preferedPrintLanguage)
	{
	    this.preferedPrintLanguage = preferedPrintLanguage;
	}
	
	/**
	 * @return the phones
	 */
	public List<UserPhone> getPhones() {
		return phones;
	}
	
	/**
	 * @param phones the phones to set
	 */
	public void setPhones(List<UserPhone> phones) {
		this.phones = phones;
	}
	
	/**
	 * @return the emails
	 */
	public List<UserEmail> getEmails() {
		return emails;
	}
	
	/**
	 * @param emails the emails to set
	 */
	public void setEmails(List<UserEmail> emails) {
		this.emails = emails;
	}
	
	/**
	 * @return the addresses
	 */
	public List<UserAddress> getAddresses() {
		return addresses;
	}
	
	/**
	 * @param addresses the addresses to set
	 */
	public void setAddresses(List<UserAddress> addresses) {
		this.addresses = addresses;
	}
}
