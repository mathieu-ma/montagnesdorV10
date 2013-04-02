/*
 * Created on 21 avr. 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package fr.montagnesdor.util.test;

import java.util.Date;


/**
 * @author conrad
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class Utilisateur
{

	private Long id;
	private UserAuth userAuth;
	private UserRole userRole;
	private String nom;
	private String prenom1;
	private String prenom2;
	private Date datenaissance;
	private byte sexe;
	private byte[] photo;
	//private InputStream photo;

	/**
	 * @return
	 */
	public Date getDatenaissance()
	{
		return datenaissance;
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
	public String getNom()
	{
		return nom;
	}

	/**
	 * @return
	 */
	public String getPrenom1()
	{
		return prenom1;
	}

	/**
	 * @return
	 */
	public String getPrenom2()
	{
		return prenom2;
	}

	/**
	 * @return
	 */
	public byte getSexe()
	{
		return sexe;
	}

	/**
	 * @param date
	 */
	public void setDatenaissance(Date date)
	{
		datenaissance = date;
	}

	/**
	 * @param long1
	 */
	private void setId(Long long1)
	{
		id = long1;
	}

	/**
	 * @param string
	 */
	public void setNom(String string)
	{
		nom = string;
	}

	/**
	 * @param string
	 */
	public void setPrenom1(String string)
	{
		prenom1 = string;
	}

	/**
	 * @param string
	 */
	public void setPrenom2(String string)
	{
		prenom2 = string;
	}

	/**
	 * @param c
	 */
	public void setSexe(byte b)
	{
		sexe = b;
	}

	/**
	 * @return
	 */
	
		public byte[] getPhoto()
		{
			return photo;
		}

/*
	public InputStream getPhoto()
	{
		return photo;
	}

	public byte[] getPhotoInBytes()
	{
		InputStream is = getPhoto();
		byte[] result  = null;
		try
		{
			if(is !=null)
			{
				result  = new byte[is.available()];
				is.read(result);
				is.close();
			}
			
		}
		catch(IOException ioe)
		{
			LogFactory.getLog("").debug("");
		}
		return result;
	}
*/
	/**
	 * @param blob
	 */
		
		public void setPhoto(byte[] bytea)
		{
			photo = bytea;
		}
	
		/**
	 * @param stream
	 */
/*
	public void setPhoto(InputStream stream)
	{
		photo = stream;
	}
*/
	/**
	 * @return
	 */
	public UserAuth getUserAuth()
	{
		return userAuth;
	}
	/**
	 * @return
	 */
	public UserRole getUserRole()
	{
		return userRole;
	}
	/**
	 * @param auth
	 */
	public void setUserAuth(UserAuth auth)
	{
		userAuth = auth;
	}
	/**
	 * @param role
	 */
	public void setUserRole(UserRole role)
	{
		userRole = role;
	}

}
