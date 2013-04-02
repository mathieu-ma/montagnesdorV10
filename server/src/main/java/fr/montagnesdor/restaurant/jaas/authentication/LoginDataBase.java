package fr.montagnesdor.restaurant.jaas.authentication;

import java.util.Map;
import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

import fr.montagnesdor.restaurant.jaas.authorization.TypedPrincipal;
import fr.montagnesdor.restaurant.model.hibernate.User;
import fr.montagnesdor.restaurant.services.authentication.UserManagerFactory;

/**
 * @author administrateur
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class LoginDataBase implements LoginModule
{
	// initial state
	private Subject subject;
	private CallbackHandler callbackHandler;
	private Map sharedState;
	private Map options;

	private User user = null;

	/**
	 * Initialize this <code>LoginModule</code>.
	 *
	 * <p>
	 *
	 * @param subject the <code>Subject</code> to be authenticated. <p>
	 *
	 * @param callbackHandler a <code>CallbackHandler</code> for communicating
	 *			with the end user (prompting for usernames and
	 *			passwords, for example). <p>
	 *
	 * @param sharedState shared <code>LoginModule</code> state. <p>
	 *
	 * @param options options specified in the login
	 *			<code>Configuration</code> for this particular
	 *			<code>LoginModule</code>.
	 */
	public void initialize(Subject subject, CallbackHandler callbackHandler, Map sharedState, Map options)
	{
		this.subject = subject;
		this.callbackHandler = callbackHandler;
		this.sharedState = sharedState;
		this.options = options;

		// initialize any configured options
		//boolean debug = "true".equalsIgnoreCase((String) options.get("debug"));
	}

	/**
	 * Authenticate the user by prompting for a username and password.
	 *
	 * <p>
	 *
	 * @return true in all cases since this <code>LoginModule</code>
	 *		should not be ignored.
	 *
	 * @exception FailedLoginException if the authentication fails. <p>
	 *
	 * @exception LoginException if this <code>LoginModule</code>
	 *		is unable to perform the authentication.
	 */
	public boolean login() throws LoginException
	{
		Callback callbacks[] = new Callback[3];
		callbacks[0] = new NameCallback("login");
		callbacks[1] = new PasswordCallback("password", false);
		callbacks[2] = new LevelPasswordCallback("levelPassword");

		try
		{
			callbackHandler.handle(callbacks);
			String login = ((NameCallback) callbacks[0]).getName();
			char password[] = ((PasswordCallback) callbacks[1]).getPassword();
			String levelPassword = ((LevelPasswordCallback) callbacks[2]).getLevelPassword();
			
			user = UserManagerFactory.getManager().getUserByLogin(login);
			
			if(user.getUserAuthentication()==null)
				user = null;
			
			String userPassword = null;
			if(levelPassword==null)
				levelPassword = "0";
			if(levelPassword.equals("1"))
				userPassword = user.getUserAuthentication().getLevelPass1();
			else if(levelPassword.equals("2"))
				userPassword = user.getUserAuthentication().getLevelPass2();		
			else if(levelPassword.equals("3"))
				userPassword = user.getUserAuthentication().getLevelPass3();
			else				
				userPassword = user.getUserAuthentication().getPassword();	
			if((user != null) && !userPassword.equals(new String(password)))
				return false;
			
			//user.getUserAuthentification().setPassword(null);	
			callbacks = new Callback[1];
			callbacks[0] = new UserCallback(user);	
			callbackHandler.handle(callbacks);
		}
		catch (Exception e)
		{
			return false;
		}

		return true;
	}
	

/**
 * <p> This method is called if the LoginContext's
 * overall authentication succeeded
 * (the relevant REQUIRED, REQUISITE, SUFFICIENT and OPTIONAL LoginModules
 * succeeded).
 *
 * <p> If this LoginModule's own authentication attempt
 * succeeded (checked by retrieving the private state saved by the
 * <code>login</code> method), then this method associates a
 * <code>SamplePrincipal</code>
 * with the <code>Subject</code> located in the
 * <code>LoginModule</code>.  If this LoginModule's own
 * authentication attempted failed, then this method removes
 * any state that was originally saved.
 *
 * <p>
 *
 * @exception LoginException if the commit fails.
 *
 * @return true if this LoginModule's own login and commit
 *		attempts succeeded, or false otherwise.
 */
public boolean commit() throws LoginException
{

	// add a Principal (authenticated identity)
	// to the Subject
	// assume the user we authenticated is the SamplePrincipal
	TypedPrincipal userPrincipal;

	if (user != null && user.getUserRole() != null)
		userPrincipal = new TypedPrincipal(user.getUserRole().getLabelCode());
	else
		return false;

	if (!subject.getPrincipals().contains(userPrincipal))
		subject.getPrincipals().add(userPrincipal);

	return true;
}

/**
 * <p> This method is called if the LoginContext's
 * overall authentication failed.
 * (the relevant REQUIRED, REQUISITE, SUFFICIENT and OPTIONAL LoginModules
 * did not succeed).
 *
 * <p> If this LoginModule's own authentication attempt
 * succeeded (checked by retrieving the private state saved by the
 * <code>login</code> and <code>commit</code> methods),
 * then this method cleans up any state that was originally saved.
 *
 * <p>
 *
 * @exception LoginException if the abort fails.
 *
 * @return false if this LoginModule's own login and/or commit attempts
 *		failed, and true otherwise.
 */
public boolean abort() throws LoginException
{
	return true;
}

/**
 * Logout the user.
 *
 * <p> This method removes the <code>SamplePrincipal</code>
 * that was added by the <code>commit</code> method.
 *
 * <p>
 *
 * @exception LoginException if the logout fails.
 *
 * @return true in all cases since this <code>LoginModule</code>
 *          should not be ignored.
 */
public boolean logout() throws LoginException
{
	return true;
}
}
