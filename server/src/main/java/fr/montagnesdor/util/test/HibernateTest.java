/*
 * Created on 21 avr. 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package fr.montagnesdor.util.test;

import java.io.FileInputStream;

import java.util.Date;
import java.util.List;

import fr.montagnesdor.restaurant.model.hibernate.OrderLine;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import net.sf.hibernate.SessionFactory;
import net.sf.hibernate.Transaction;
import net.sf.hibernate.cfg.Configuration;

/**
 * @author conrad
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class HibernateTest
{
	private static final SessionFactory sessionFactory;

	static {
		try
		{
			sessionFactory = new Configuration().configure().buildSessionFactory();
		}
		catch (HibernateException ex)
		{
			throw new RuntimeException("Exception building SessionFactory: " + ex.getMessage(), ex);
		}
	}

	public static final ThreadLocal sessionThread = new ThreadLocal();

	public static Session currentSession() throws HibernateException
	{
		Session s = (Session) sessionThread.get();
		// Open a new Session, if this Thread has none yet
		if (s == null)
		{
			s = sessionFactory.openSession();
			sessionThread.set(s);
		}
		return s;
	}

	public static void closeSession() throws HibernateException
	{
		Session s = (Session) sessionThread.get();
		sessionThread.set(null);
		if (s != null)
			s.close();
	}

	public static void main(String[] args) throws Exception
	{

		System.out.println("Initializing Hibernate");

		ListOrderLine();
		
		System.out.println("Finished Initializing Hibernate");
	}


	private static void ListOrderLine() throws Exception
	{

		Session session = currentSession();

		Transaction tx = session.beginTransaction();

		List result = session.find("from OrderLine");

		tx.commit();
		session.close();

		for (int i = 0; i < result.size(); i++)
		{
			OrderLine orl = (OrderLine) result.get(i);
			System.out.println("orl.getDinnerTable() " + orl.getDinnerTable()+ ";  getQuantity() " + orl.getQuantity());
			//System.out.println("getLogin " + user.getUserAuth().getLogin()+"  " +user.getUserAuth().getId());
		}

	}

	private static void List() throws Exception
	{

		Session session = currentSession();

		Transaction tx = session.beginTransaction();

		List result = session.find("from Utilisateur");

		tx.commit();
		session.close();

		for (int i = 0; i < result.size(); i++)
		{
			Utilisateur user = (Utilisateur) result.get(i);
			System.out.println("getNom " + user.getNom() + " getPrenom1: " + user.getPrenom1());
			//System.out.println("getLogin " + user.getUserAuth().getLogin()+"  " +user.getUserAuth().getId());
		}

	}

	private static void loadUser() throws Exception
	{
		Session session = currentSession();

		Transaction tx = session.beginTransaction();

		Utilisateur user = (Utilisateur) session.load(Utilisateur.class, new Long(4));

		System.out.println("getNom " + user.getNom() + " getPrenom1: " + user.getPrenom1());
		byte[] ba = user.getPhoto();
		tx.commit();
		session.close();

	}

	private static void store() throws Exception
	{
		try
		{
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();

			Utilisateur user = new Utilisateur();
			user.setDatenaissance(new Date());
			user.setNom("Cochelin");
			user.setPrenom1("Thomas");
			user.setSexe((byte) 0);
			List userAuthList = session.find("FROM UserAuth WHERE idAuth=1");
			UserAuth userAuth = null;
			if (userAuthList != null && userAuthList.size() == 1)
				userAuth = (UserAuth) userAuthList.get(0);
			user.setUserAuth(userAuth);
			List userRoleList = session.find("FROM UserRole WHERE idRole=1");
			UserRole userRole = null;
			if (userRoleList != null && userRoleList.size() == 1)
				userRole = (UserRole) userRoleList.get(0);
			user.setUserRole(userRole);

			String chemin = "/home/conrad/workspace/montagnesdor/images/logo.gif";
			FileInputStream fis = new FileInputStream(chemin);
			byte[] ba = new byte[fis.available()];
			fis.read(ba);
			user.setPhoto(ba);

			session.save(user);

			tx.commit();
			session.close();
		}
		catch (HibernateException e)
		{
			e.printStackTrace();
		}
	}
}
