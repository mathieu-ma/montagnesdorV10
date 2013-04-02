package fr.montagnesdor.util.log;

import org.apache.log4j.Appender;
import org.apache.log4j.Level;


public interface MyLog
{
	public void debug(String message, Throwable exception);
	public void debug(String message);
	
	public void info(String message, Throwable exception);
	public void info(String message);

	public void warn(String message, Throwable exception);
	public void warn(String message);

	public void error(String message, Throwable exception);
	public void error(String message);

	public void fatal(String message, Throwable exception);
	public void fatal(String message);
	
	public void addAppender(Appender newAppender);
	public void setLevel(Level level);
}