package fr.montagnesdor.restaurant.jaas.authorization;

import java.security.AccessControlException;
import java.security.Permission;
import java.security.PermissionCollection;
import java.security.PrivilegedAction;
import java.util.Enumeration;

import javax.security.auth.Policy;
import javax.security.auth.Subject;

import fr.montagnesdor.util.log.MyLog;
import fr.montagnesdor.util.log.MyLogFactoryImpl;

/**
 * @author administrateur
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class Authorization
{
	private static MyLog logger = MyLogFactoryImpl.getInstance().getLogger(Authorization.class.getName());

	private static Permission getPermission(String url)
	{
		return new URLPermission(url);
	}

	public static String permitted(final Subject subject, final String pageReq)
	{
		final Permission p = Authorization.getPermission(pageReq);

		try
		{
			logger.debug("Essai d'acc�s � l'action : " + p);
			String result = (String) Subject.doAsPrivileged(subject, new PrivilegedAction()
			{
				public Object run()
				{
					Policy policy = Policy.getPolicy();
					PermissionCollection perms = policy.getPermissions(subject, null);
					//L'instruction ci-dessous permet de r�esoudre les permissions de type "Unresolved"
					perms.add(new URLPermission("init","*"));
					
					String action = null;
					try
					{
						for (Enumeration e = perms.elements(); e.hasMoreElements();)
						{
							Permission perm = (Permission) e.nextElement();
//System.out.println(perm);
							if (p.equals(perm))
							{
								action = perm.getActions();
								if(action==null)
									action="*";
								break;
							}
						}
					}
					catch (Exception e)
					{
						action = null;
					}
					
					return action;
				}
			}, null);
			return result;
		}
		catch (AccessControlException ace)
		{
			ace.printStackTrace();
			return null;
		}
		/*		
				catch (PrivilegedActionException pae)
				{
					return false;
				}
				*/
		catch (Exception pae)
		{
			return null;
		}

	}

}
