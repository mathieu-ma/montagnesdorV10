/*
 * Created on 13 juin 2004
 *
 * 
 * 
 */
package fr.montagnesdor.restaurant.model.hibernate;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import net.sf.hibernate.SessionFactory;
import net.sf.hibernate.cfg.Configuration;

/**
 * @author Mathieu MA sous conrad
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class HibernateUtil
{
	private static final SessionFactory sessionFactory;
	private static final ThreadLocal session = new ThreadLocal();

	static 
	{
		try
		{
			// Create the SessionFactory
			sessionFactory = new Configuration().configure().buildSessionFactory();
		}
		catch (HibernateException ex)
		{
			throw new RuntimeException("Configuration problem: " + ex.getMessage(), ex);
		}
	}

	public static Session currentSession() throws HibernateException
	{
		Session s = (Session) session.get();
		// Open a new Session, if this Thread has none yet
		if (s == null)
		{
			s = sessionFactory.openSession();
			session.set(s);
		}
		return s;
	}

	public static void closeSession() throws HibernateException
	{
		Session s = (Session) session.get();
		session.set(null);
		if (s != null && s.isOpen())
			s.close();
	}
}
