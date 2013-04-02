/*
 * Created on 22 avr. 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package fr.montagnesdor.util.test;

/**
 * @author conrad
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class UserAuth
{

	private Long id;
	private String login;
	private String password;
	private String levelpass1;
	private String levelpass2;
	private String levelpass3;

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
	public String getLevelpass1()
	{
		return levelpass1;
	}

	/**
	 * @return
	 */
	public String getLevelpass2()
	{
		return levelpass2;
	}

	/**
	 * @return
	 */
	public String getLevelpass3()
	{
		return levelpass3;
	}

	/**
	 * @return
	 */
	public String getLogin()
	{
		return login;
	}

	/**
	 * @return
	 */
	public String getPassword()
	{
		return password;
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
	public void setLevelpass1(String string)
	{
		levelpass1 = string;
	}

	/**
	 * @param string
	 */
	public void setLevelpass2(String string)
	{
		levelpass2 = string;
	}

	/**
	 * @param string
	 */
	public void setLevelpass3(String string)
	{
		levelpass3 = string;
	}

	/**
	 * @param string
	 */
	public void setLogin(String string)
	{
		login = string;
	}

	/**
	 * @param string
	 */
	public void setPassword(String string)
	{
		password = string;
	}

}
