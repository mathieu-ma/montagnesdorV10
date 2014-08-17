package fr.montagnesdor.restaurant.services.users.spi;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.hibernate.Criteria;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import net.sf.hibernate.Transaction;
import net.sf.hibernate.expression.Expression;
import net.sf.hibernate.expression.MatchMode;
import fr.montagnesdor.restaurant.model.hibernate.HibernateUtil;
import fr.montagnesdor.restaurant.model.hibernate.User;
import fr.montagnesdor.restaurant.model.hibernate.UserAddress;
import fr.montagnesdor.restaurant.model.hibernate.UserEmail;
import fr.montagnesdor.restaurant.model.hibernate.UserPhone;
import fr.montagnesdor.restaurant.model.hibernate.UserRole;
import fr.montagnesdor.restaurant.services.users.UsersManager;
import fr.montagnesdor.util.log.MyLog;
import fr.montagnesdor.util.log.MyLogFactoryImpl;
import fr.montagnesdor.util.log.MyLogImpl;

public class DefaultUsersManager implements UsersManager
{
	private static UsersManager instance = null;
	private static MyLog logger = MyLogFactoryImpl.getInstance().getLogger(DefaultUsersManager.class.getName());

	public DefaultUsersManager()
	{
	}

	public static UsersManager getInstance()
	{
		if (instance == null)
			synchronized (DefaultUsersManager.class)
			{
				if (instance == null)
					instance = new DefaultUsersManager();
			}

		return instance;
	}

	public void saveOrUpdateUser(User user) {
		try
		{
			Session session = HibernateUtil.currentSession();
			Transaction tx = session.beginTransaction();
			
			User userFromDb = null;
			if (user.getId() != null) 
			{
				userFromDb = (User) session.load(User.class, user.getId());
			}
			else
			{
				userFromDb = new User();
			}

			// 1) Only save name and forename1
			userFromDb.setName(user.getName());
			userFromDb.setForename1(user.getForename1());
			// Set default because not null constraint
			userFromDb.setBirthdate(new Date());
			
		    if (userFromDb.getUserRole() == null || userFromDb.getUserRole().getId() == null)
		    {
				// Force role to be CUSTOMER
				Criteria criteria = session.createCriteria(UserRole.class);
			    criteria.add(Expression.eq("this.labelCode", "CUSTOMER"));
			    UserRole role = (UserRole) criteria.uniqueResult();
			    userFromDb.setUserRole(role);
		    }
		    
		    if (userFromDb.getUserAuthentication() == null || userFromDb.getUserAuthentication().getId() == null)
		    {
				// Force authentication to be null
			    userFromDb.setUserAuthentication(null);
		    }

		    // 2) Remove all mails, phones, addresses
		    for(UserEmail mail: userFromDb.getEmails())
		    {
		    	session.delete(mail);
		    }
		    for(UserPhone phone: userFromDb.getPhones())
		    {
		    	session.delete(phone);
		    }
		    for(UserAddress address: userFromDb.getAddresses())
		    {
		    	session.delete(address);
		    }
			// Save the user before saving mails, phones, addresses
			session.saveOrUpdate(userFromDb);

		    // 3) Save all mails, phones, addresses
		    for(UserEmail mail: user.getEmails())
		    {
		    	mail.setUser(userFromDb);
		    	session.save(mail);
		    }
		    userFromDb.setEmails(user.getEmails());

		    for(UserPhone phone: user.getPhones())
		    {
		    	phone.setUser(userFromDb);
		    	session.save(phone);
		    }
		    userFromDb.setPhones(user.getPhones());

		    for(UserAddress address: user.getAddresses())
		    {
		    	address.setUser(userFromDb);
		    	session.save(address);
		    }
		    userFromDb.setAddresses(user.getAddresses());

			session.saveOrUpdate(userFromDb);

			tx.commit();
			HibernateUtil.closeSession();
		}
		catch (HibernateException he)
		{
			logger.error(MyLogImpl.MESSAGES.getString("message.error.store.user"));
		}
		finally
		{
			try
			{
				HibernateUtil.closeSession();
			}
			catch (HibernateException he)
			{
			}
		}
	}

	public User findUser(Long id) {
		User result = null;
		try
		{
			Session session = HibernateUtil.currentSession();
			Transaction tx = session.beginTransaction();
			
			result = (User) session.load(User.class, id);

			tx.commit();
			HibernateUtil.closeSession();
		}
		catch (HibernateException he)
		{
			logger.error(MyLogImpl.MESSAGES.getString("message.error.find.user"));
		}
		finally
		{
			try
			{
				HibernateUtil.closeSession();
			}
			catch (HibernateException he)
			{
			}
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<User> searchUsers(String searchedText) {
		List<User> result = new ArrayList<User>();
		try
		{
			Session session = HibernateUtil.currentSession();
			Transaction tx = session.beginTransaction();
			
			Criteria criteria = session.createCriteria(User.class);
		    criteria.add(Expression.or(Expression.ilike("name", searchedText, MatchMode.ANYWHERE), 
				    				Expression.ilike("forename1", searchedText, MatchMode.ANYWHERE)
		    			));
		    List<User> partialResult1 = criteria.list();
		    if (partialResult1 != null)
		    {
		    	result.addAll(partialResult1);
		    }

		    criteria = session.createCriteria(UserEmail.class);
		    criteria.add(Expression.ilike("mail", searchedText, MatchMode.ANYWHERE));
		    List<UserEmail> partialResult2 = criteria.list();
		    if (partialResult2 != null)
		    {
		    	for (UserEmail userEmail : partialResult2) {
		    		result.add(userEmail.getUser());
				}
		    }
		    
			criteria = session.createCriteria(UserPhone.class);
		    criteria.add(Expression.ilike("number", searchedText, MatchMode.ANYWHERE));
		    List<UserPhone> partialResult3 = criteria.list();
		    if (partialResult3 != null)
		    {
		    	for (UserPhone userPhone : partialResult3) {
		    		result.add(userPhone.getUser());
				}
		    }

		    criteria = session.createCriteria(UserAddress.class);
		    criteria.add(Expression.or(Expression.ilike("address1", searchedText, MatchMode.ANYWHERE), 
				    				Expression.ilike("address2", searchedText, MatchMode.ANYWHERE)
		    			));
		    List<UserAddress> partialResult4 = criteria.list();
		    if (partialResult4 != null)
		    {
		    	for (UserAddress userAddress : partialResult4) {
		    		result.add(userAddress.getUser());
				}
		    }

			tx.commit();
			HibernateUtil.closeSession();
		}
		catch (HibernateException he)
		{
			logger.error(MyLogImpl.MESSAGES.getString("message.error.find.users"));
		}
		finally
		{
			try
			{
				HibernateUtil.closeSession();
			}
			catch (HibernateException he)
			{
			}
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<User> findUsers() {
		List<User> result = null;
		try
		{
			Session session = HibernateUtil.currentSession();
			Transaction tx = session.beginTransaction();
			
			Criteria criteria = session.createCriteria(User.class);

			result = criteria.list();

			tx.commit();
			HibernateUtil.closeSession();
		}
		catch (HibernateException he)
		{
			logger.error(MyLogImpl.MESSAGES.getString("message.error.find.users"));
		}
		finally
		{
			try
			{
				HibernateUtil.closeSession();
			}
			catch (HibernateException he)
			{
			}
		}
		return result;
	}

	public void deleteUser(User user) {
		try
		{
			Session session = HibernateUtil.currentSession();
			Transaction tx = session.beginTransaction();
			
			User userFromDb = (User) session.load(User.class, user.getId());
			// Delete children: phones, emails, addresses
		    for(UserEmail mail: userFromDb.getEmails())
		    {
		    	session.delete(mail);
		    }
		    for(UserPhone phone: userFromDb.getPhones())
		    {
		    	session.delete(phone);
		    }
		    for(UserAddress address: userFromDb.getAddresses())
		    {
		    	session.delete(address);
		    }
			// Delete user
			session.delete( userFromDb );

			tx.commit();
			HibernateUtil.closeSession();
		}
		catch (HibernateException he)
		{
			logger.error(MyLogImpl.MESSAGES.getString("message.error.delete.user"));
		}
		finally
		{
			try
			{
				HibernateUtil.closeSession();
			}
			catch (HibernateException he)
			{
			}
		}
	}

	@SuppressWarnings("unchecked")
	public List<UserRole> findUserRoles() {
		List<UserRole> result = null;
		try
		{
			Session session = HibernateUtil.currentSession();
			Transaction tx = session.beginTransaction();
			
			Criteria criteria = session.createCriteria(UserRole.class);
			result = criteria.list();

			tx.commit();
			HibernateUtil.closeSession();
		}
		catch (HibernateException he)
		{
			logger.error(MyLogImpl.MESSAGES.getString("message.error.find.user.roles"));
		}
		finally
		{
			try
			{
				HibernateUtil.closeSession();
			}
			catch (HibernateException he)
			{
			}
		}
		return result;
	}
}
