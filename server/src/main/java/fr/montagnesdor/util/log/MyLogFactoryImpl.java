package fr.montagnesdor.util.log;

import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;

public final class MyLogFactoryImpl implements MyLogFactory 
{
	private static MyLogFactory myLogFactory = null;
	private static MyLog defaultLog = null;
	private static ResourceBundle resource = ResourceBundle.getBundle(MyLogFactoryImpl.class.getName());
	
	
	static
	{
		loadLogProperties();
	}

    public MyLog getLogger(String name)
    {
    	return new MyLogImpl(name);
    }

	public MyLog getLogger()
	{
		return defaultLog;
	}

	public static MyLogFactory getInstance()
	{
		if(myLogFactory==null)
		{
			synchronized(MyLogFactoryImpl.class)
			{
				myLogFactory = new MyLogFactoryImpl();
			}
		}
		
		return myLogFactory;
	}
    
	public static void loadLogProperties()
	{
		Properties prop = new Properties();
		try
		{
//			prop.load(MyLogFactoryImpl.class.getResource("MyLogFactoryImpl.properties").openStream());
//			PropertyConfigurator.configure(prop);
			defaultLog = new MyLogImpl();
		    
			PatternLayout patternLayout1 = new PatternLayout(resource.getString("log4j.appender.A1.layout.ConversionPattern"));
		    ConsoleAppender consoleAppender = new ConsoleAppender(patternLayout1);
		    defaultLog.addAppender(consoleAppender);
		    
			boolean flag = resource.getString("log4j.appender.A2.Append")==null?false:resource.getString("log4j.appender.A2.Append").equals("true");
			String file = MyLogFactoryImpl.class.getResource(resource.getString("log4j.appender.A2.FileName")).getPath();
			PatternLayout patternLayout2 = new PatternLayout(resource.getString("log4j.appender.A2.layout.ConversionPattern"));
			RollingFileAppender rollingFileAppender = new RollingFileAppender(patternLayout2, file, flag);
			rollingFileAppender.setName(resource.getString("log4j.appender.A2.Name"));			
			rollingFileAppender.setMaxFileSize(resource.getString("log4j.appender.A2.MaxFileSize"));
			rollingFileAppender.setMaxBackupIndex(Integer.parseInt(resource.getString("log4j.appender.A2.MaxBackupIndex")));
			defaultLog.addAppender(rollingFileAppender);
			defaultLog.setLevel(Level.toLevel((resource.getString("log4j.rootLogger.level"))));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}