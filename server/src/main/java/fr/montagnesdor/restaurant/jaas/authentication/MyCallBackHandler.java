package fr.montagnesdor.restaurant.jaas.authentication;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;

import fr.montagnesdor.restaurant.model.hibernate.User;

/**
 * @author administrateur
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class MyCallBackHandler implements CallbackHandler
{
	private String login;
	private String password;
	private String levelPassword;	
	private User user;

	public MyCallBackHandler(String login, String password)
	{
		this(login, password, null);
	}

	public MyCallBackHandler(String login, String password, String levelPassword)
	{
		this.login = login;
		this.password = password;
		this.levelPassword = levelPassword;
	}

	/**
	 * @see javax.security.auth.callback.CallbackHandler#handle(javax.security.auth.callback.Callback)
	 */
	public void handle(Callback[] arg0) throws IOException, UnsupportedCallbackException
	{
		for (int i = 0; i < arg0.length; i++)
		{
			Callback cb = arg0[i];
			if (cb instanceof NameCallback)
			{
				NameCallback nameCallback = (NameCallback) cb;
				nameCallback.setName(login);
			}
			else if (cb instanceof PasswordCallback)
			{
				PasswordCallback passwordCallback = (PasswordCallback) cb;
				passwordCallback.setPassword(password.toCharArray());
				password = null;
			}
			else if(cb instanceof LevelPasswordCallback)
			{
				LevelPasswordCallback levelPasswordCallback = (LevelPasswordCallback) cb;
				levelPasswordCallback.setLevelPassword(levelPassword);
			}
			else if(cb instanceof UserCallback)
			{
				UserCallback userCallback = (UserCallback) cb;
				user = userCallback.getUser();
			}
			else
			{
				throw new UnsupportedCallbackException(cb, "Unsupported Callback Type");
			}
		}
	}
	
	/**
	 * Returns the user.
	 * @return User
	 */
	public User getUser()
	{
		return user;
	}

}
