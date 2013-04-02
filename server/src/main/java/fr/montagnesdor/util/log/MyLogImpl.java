package fr.montagnesdor.util.log;

import java.util.ResourceBundle;

import org.apache.log4j.Appender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;


public class MyLogImpl implements MyLog
{
	public static final ResourceBundle MESSAGES = ResourceBundle.getBundle(MyLogImpl.class.getName());
	
	private Logger myLog4j = null;
	
	protected MyLogImpl(String className)
	{
		myLog4j = Logger.getLogger(className);
	}

	protected MyLogImpl()
	{
		myLog4j = Logger.getRootLogger();
	}

	public void debug(String message, Throwable exception)
	{
		myLog4j.debug(message, exception);
	}
	
	public void debug(String message)
	{
		myLog4j.debug(message);
	}
	
	public void info(String message, Throwable exception)
	{
		myLog4j.info(message, exception);
	}
	
	public void info(String message)
	{
		myLog4j.info(message);
	}

	public void warn(String message, Throwable exception)
	{
		myLog4j.warn(message, exception);
	}
	
	public void warn(String message)
	{
		myLog4j.warn(message);
	}

	public void error(String message, Throwable exception)
	{
		myLog4j.error(message, exception);
	}
	
	public void error(String message)
	{
		myLog4j.error(message);		
	}

	public void fatal(String message, Throwable exception)
	{
		myLog4j.fatal(message, exception);
	}
	
	public void fatal(String message)
	{
		myLog4j.fatal(message);
	}
	
	public void addAppender(Appender newAppender)
	{
		myLog4j.addAppender(newAppender);
	}	
	
	public void setLevel(Level level)
	{
		myLog4j.setLevel(level);
	}	
}