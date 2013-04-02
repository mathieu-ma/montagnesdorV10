package fr.montagnesdor.util;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedInputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.ResourceBundle;


import javax.comm.CommDriver;
import javax.comm.CommPortIdentifier;
import javax.comm.SerialPort;
import javax.comm.SerialPortEvent;
import javax.comm.SerialPortEventListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;



/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class SimulateurImprimante extends JFrame implements WindowListener, ActionListener, SerialPortEventListener
{
	private JTextArea dataToSendArea = new JTextArea(10, 50);
	private JTextArea dataRecievedArea = new JTextArea(20, 100);
	private JButton DSRRecieved = new JButton("DSR reçu");

	private static Logger logger = Logger.getLogger(SimulateurImprimante.class.getName());
	private static ResourceBundle resource = ResourceBundle.getBundle(SimulateurImprimante.class.getName());
	private static String driverName = resource.getString("drivername");
	private static CommPortIdentifier portId = null;
	private SerialPort serialPort = null;

	static 
	{
		Properties prop = new Properties();
		try
		{
			PatternLayout patternLayout1 = new PatternLayout(resource.getString("log4j.appender.A1.layout.ConversionPattern"));
		    ConsoleAppender consoleAppender = new ConsoleAppender(patternLayout1);
		    logger.addAppender(consoleAppender);
		    
			boolean flag = resource.getString("log4j.appender.A2.Append")==null?false:resource.getString("log4j.appender.A2.Append").equals("true");
			String file = SimulateurImprimante.class.getResource(resource.getString("log4j.appender.A2.FileName")).getPath();
			PatternLayout patternLayout2 = new PatternLayout(resource.getString("log4j.appender.A2.layout.ConversionPattern"));
			RollingFileAppender rollingFileAppender = new RollingFileAppender(patternLayout2, file, flag);
			rollingFileAppender.setName(resource.getString("log4j.appender.A2.Name"));			
			rollingFileAppender.setMaxFileSize(resource.getString("log4j.appender.A2.MaxFileSize"));
			rollingFileAppender.setMaxBackupIndex(Integer.parseInt(resource.getString("log4j.appender.A2.MaxBackupIndex")));
			logger.addAppender(rollingFileAppender);
			
			logger.setLevel(Level.toLevel((resource.getString("log4j.rootLogger.level"))));
		}
		catch (Exception e)
		{
			System.err.println("Problème de chargement des propriétés de Log4J : " + e);
		}

		try
		{
			CommDriver driver = (CommDriver) Class.forName(driverName).newInstance();
			driver.initialize();
		}
		catch (Exception e)
		{
			logger.fatal("Impossible de charger le driver de l'imprimante port série " + e);
		}
	}

	public SimulateurImprimante()
	{
		if (portId == null)
		{
			logger.info("DEBUT portId bloc static " + portId);
			try
			{
				portId = CommPortIdentifier.getPortIdentifier(resource.getString("imprimante.portcom"));
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			logger.info("FIN portId bloc static " + portId);
		}
//		this.setSize(50, 50);
		this.addWindowListener(this);
		this.getContentPane().setLayout(new GridLayout(2, 1, 1, 1));
		//this.getContentPane().setLayout(new BorderLayout());
		//this.getContentPane().setLayout(new FlowLayout());
		
		JPanel libelleToSendPanel = new JPanel(new BorderLayout());
		JLabel libelleToSend = new JLabel("Données à envoyer");
		libelleToSendPanel.add(libelleToSend, BorderLayout.NORTH);
		dataToSendArea.setLineWrap(true);
		dataToSendArea.setWrapStyleWord(true);
		JScrollPane dataToSendAreaScroll = new JScrollPane(dataToSendArea);
		libelleToSendPanel.add(dataToSendAreaScroll, BorderLayout.CENTER);
		JPanel DTRToSendPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton DTRToSendT = new JButton("DTR TRUE à envoyer");
		DTRToSendT.addActionListener(this);
		DTRToSendT.setActionCommand("DTRT");
		DTRToSendPanel.add(DTRToSendT);
		JButton DTRToSendF = new JButton("DTR FALSE à envoyer");
		DTRToSendF.addActionListener(this);
		DTRToSendF.setActionCommand("DTRF");
		DTRToSendPanel.add(DTRToSendF);
		JButton dataToSend = new JButton("Données à envoyer");
		dataToSend.addActionListener(this);
		dataToSend.setActionCommand("DATA");
		DTRToSendPanel.add(dataToSend);
		libelleToSendPanel.add(DTRToSendPanel, BorderLayout.SOUTH);
		//this.getContentPane().add(libelleToSendPanel, BorderLayout.NORTH);
		this.getContentPane().add(libelleToSendPanel);

		JPanel libelleRecievedPanel = new JPanel(new BorderLayout());
		JLabel libelleRecieved = new JLabel("Données reçues");
		libelleRecievedPanel.add(libelleRecieved, BorderLayout.NORTH);
		dataRecievedArea.setLineWrap(true);
		dataRecievedArea.setWrapStyleWord(true);
		//dataRecievedArea.setSize(200, 400);
		JScrollPane dataRecievedAreaScroll = new JScrollPane(dataRecievedArea);
		libelleRecievedPanel.add(dataRecievedAreaScroll, BorderLayout.CENTER);
		JPanel DSRRecievedPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		DSRRecievedPanel.add(DSRRecieved);
		JButton removeButton = new JButton("Effacer");
		removeButton.addActionListener(this);
		removeButton.setActionCommand("removeButton");
		DSRRecievedPanel.add(removeButton);
		libelleRecievedPanel.add(DSRRecievedPanel, BorderLayout.SOUTH);
		//this.getContentPane().add(libelleRecievedPanel, BorderLayout.SOUTH);
		this.getContentPane().add(libelleRecievedPanel);

		DSRRecieved.setBackground(Color.RED);

		this.addComponentListener
		(
		        new java.awt.event.ComponentAdapter() 
		        {
		            public void componentResized(ComponentEvent e) 
		            {
		                //JFrame tmp = (JFrame)e.getSource();
		                //tmp.pack();		                
		            }
		        }
		);
		this.pack();
		this.validate();
		this.setVisible(true);

		openSerialPort();
		if (serialPort.isDSR())
			DSRRecieved.setBackground(Color.GREEN);
	}

	public SimulateurImprimante(String title)
	{
		this();
		this.setTitle(title);
	}

	/**
	 * @see java.awt.event.WindowListener#windowActivated(java.awt.event.WindowEvent)
	 */
	public void windowActivated(WindowEvent e)
	{
	}

	/**
	 * @see java.awt.event.WindowListener#windowClosed(java.awt.event.WindowEvent)
	 */
	public void windowClosed(WindowEvent e)
	{
	}

	/**
	 * @see java.awt.event.WindowListener#windowClosing(java.awt.event.WindowEvent)
	 */
	public void windowClosing(WindowEvent e)
	{
		logger.info("On ferme !!!");
		System.exit(0);
	}

	/**
	 * @see java.awt.event.WindowListener#windowDeactivated(java.awt.event.WindowEvent)
	 */
	public void windowDeactivated(WindowEvent e)
	{
	}

	/**
	 * @see java.awt.event.WindowListener#windowDeiconified(java.awt.event.WindowEvent)
	 */
	public void windowDeiconified(WindowEvent e)
	{
	}

	/**
	 * @see java.awt.event.WindowListener#windowIconified(java.awt.event.WindowEvent)
	 */
	public void windowIconified(WindowEvent e)
	{
	}

	/**
	 * @see java.awt.event.WindowListener#windowOpened(java.awt.event.WindowEvent)
	 */
	public void windowOpened(WindowEvent e)
	{
	}

	public void openSerialPort()
	{
		if (portId != null)
		{
			try
			{
				if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL)
				{
					serialPort = (SerialPort) portId.open("montagnesdor", Integer.parseInt(resource.getString("pause"))*2);
					serialPort.setSerialPortParams(Integer.parseInt(resource.getString("serialportBauds")), Integer.parseInt(resource.getString("serialportBits")), Integer.parseInt(resource.getString("serialportStopBits")), Integer.parseInt(resource.getString("serialportParity")));
					serialPort.addEventListener(this);
					// Set notifyOnDataAvailable to true to allow event driven input.
					serialPort.notifyOnDataAvailable(true);
					serialPort.notifyOnOutputEmpty(true);
					serialPort.setOutputBufferSize(3);
					serialPort.notifyOnDSR(true);
					//serialPort.setDTR(true);
					logger.info("Time in milliseconds to block waiting for port open: "+Integer.parseInt(resource.getString("pause"))*2);
					logger.info("serialportBauds: "+Integer.parseInt(resource.getString("serialportBauds")));
					logger.info("serialportBits: "+Integer.parseInt(resource.getString("serialportBits")));
					logger.info("serialportStopBits: "+Integer.parseInt(resource.getString("serialportStopBits")));
					logger.info("serialportParity: "+Integer.parseInt(resource.getString("serialportParity")));
				}
			}
			catch (Exception e)
			{
				logger.fatal("Impossible de récupérer le port série " + e);
			}
		}
		logger.info("Port série prise en compte " + serialPort);
		//return serialPort;
	}

	public static void main(String[] args)
	{
		new SimulateurImprimante("Simulateur Imprimante port série");
	}

	/**
	 * @see javax.comm.SerialPortEventListener#serialEvent(javax.comm.SerialPortEvent)
	 */
	public void serialEvent(SerialPortEvent spEvent)
	{
		//		logger.info("Ev�nement du port série "+spEvent);
		switch (spEvent.getEventType())
		{
			case SerialPortEvent.BI :
				logger.info("SerialPortEvent.BI : " + SerialPortEvent.BI);
				break;

			case SerialPortEvent.OE :
				logger.info("SerialPortEvent.OE : " + SerialPortEvent.OE);
				break;

			case SerialPortEvent.FE :
				logger.info("SerialPortEvent.FE : " + SerialPortEvent.FE);
				break;

			case SerialPortEvent.PE :
				logger.info("SerialPortEvent.PE : " + SerialPortEvent.PE);
				break;

			case SerialPortEvent.CD :
				logger.info("SerialPortEvent.CD : " + SerialPortEvent.CD);
				break;

			case SerialPortEvent.CTS :
				logger.info("SerialPortEvent.CTS : " + SerialPortEvent.CTS);
				break;

			case SerialPortEvent.DSR :
				//logger.info("SerialPortEvent.DSR : " + SerialPortEvent.DSR);
				if (serialPort.isDSR())
					DSRRecieved.setBackground(Color.GREEN);
				else
				{
					DSRRecieved.setBackground(Color.RED);
					//serialPort.setDTR(true);
				}
				break;

			case SerialPortEvent.RI :
				logger.info("SerialPortEvent.RI : " + SerialPortEvent.RI);
				break;

			case SerialPortEvent.OUTPUT_BUFFER_EMPTY :
				logger.info("SerialPortEvent.OUTPUT_BUFFER_EMPTY : " + SerialPortEvent.OUTPUT_BUFFER_EMPTY + " : taille du buffer " + serialPort.getOutputBufferSize());

				break;

			case SerialPortEvent.DATA_AVAILABLE :
				serialPort.setDTR(false);
				logger.info("SerialPortEvent.DATA_AVAILABLE : " + SerialPortEvent.DATA_AVAILABLE);
				try
				{
					BufferedInputStream bis = new BufferedInputStream(serialPort.getInputStream());
					byte[] data = new byte[bis.available()];
					logger.info("Taille des Données reçues " + bis.available());
					bis.read(data);

					logger.info("Données réelles reçues: "+new String(data));
					StringBuffer text = new StringBuffer("");
					for (int i = 0; i < data.length; i++)
					{
						/*  Wrap any control characters	*/
						if (Character.isISOControl((char) data[i]) && !Character.isWhitespace((char) data[i]))
						{
							text.append("<0x");
							text.append((data[i] < 16 ? "0" : "") + Integer.toHexString(data[i]).toUpperCase());
							text.append(">");
						}
						else
						{
						    text.append((char) data[i]);
						}
					}

					dataRecievedArea.append(text.toString());
					
					StringBuffer hexaText = new StringBuffer("");
					for (int i = 0; i < data.length; i++)
					{
					    hexaText.append("<0x");
					    hexaText.append((data[i] < 16 ? "0" : "") + Integer.toHexString(data[i]).toUpperCase());
					    hexaText.append(">");
					}
					logger.info("Données reçues : "+text.toString());
					logger.info("Données reçues en hexa : "+hexaText.toString());
				}
				catch (Exception e)
				{
				}
				serialPort.setDTR(true);
				break;

		}

	}

	/**
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e)
	{
		if (e.getActionCommand().equals("DTRT"))
		{
			//			if(serialPort.isDTR())
			//				serialPort.setDTR(false);
			//			else
			serialPort.setDTR(true);
			logger.info("DTR envoyé TRUE");
		}
		if (e.getActionCommand().equals("DTRF"))
		{
			//			if(serialPort.isDTR())
			//				serialPort.setDTR(false);
			//			else
			serialPort.setDTR(false);
			logger.info("DTR envoyé FALSE");
		}

		if (e.getActionCommand().equals("removeButton"))
		{
		    dataRecievedArea.setText("");
			logger.info("removeButton");
		}
		if (e.getActionCommand().equals("DATA"))
		{
			logger.info("Données à envoyer");
			logger.info("Taille du buffer " + serialPort.getOutputBufferSize());
			String donnees = dataToSendArea.getText().trim();
			//		if(!donnees.equals(""))
			{
				try
				{
					if (serialPort.isDSR())
					{
						logger.info("Données envoyées : " + donnees);
						OutputStream os = serialPort.getOutputStream();
						os.write(donnees.getBytes());
						os.flush();
						os.close();
					}
					else
						logger.info("DTE occupée");
				}
				catch (Exception ex)
				{
					logger.info("Erreur d'envoie de Données", ex);
				}
			}
			serialPort.setDTR(true);
		}

	}

}
