/*
 * Commandes.java
 *
 * Created on 21 f�vrier 2002, 20:57
 */
package fr.montagnesdor.restaurant.applets;

import gnu.io.CommDriver;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

import java.io.BufferedOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 *
 * @author  maxime
 * @version
 */
public class PrinterApplet extends java.applet.Applet
{
    //Imprime et fait avancer 160/144 pouces environ, puis découpe de la feuille
    public static final byte[] PRINT_FEED_PAPER_CUT_SHEET = 	{(byte) 0x1B, (byte) 0x4A, (byte) 0xF0, (byte) 0x1B, (byte) 0x69 };
    //public static final char[] PRINT_FEED_PAPER_CUT_SHEET = 	{(char) 0x1B, (char) 0x4A, (char) 0xF0, (char) 0x1B, (char) 0x69 };

	//Initialisation de l'imprimante, format de la police
	//Caractères internationnaux : Français pour les accents
    public static final byte[] INITIALIZE_PRINTER_INTERNATIONAL_CHARACTER = {(byte) 0x1B, (byte) 0x40, (byte) 0x1B, (byte) 0x52, (byte) 0x01 };
    
    private String charset = "UTF-8";
	private boolean debug = false;
	private static CommDriver driver = null;
	private static CommPortIdentifier portId = null;
	private static int serialportBauds = 9600;
	private static int serialportBits = 8;
	private static int serialportStopBits = 1;
	private static int serialportParity = 0;
	/*
		public static final int DATABITS_5 = 5;
		public static final int DATABITS_6 = 6;
		public static final int DATABITS_7 = 7;
		public static final int DATABITS_8 = 8;
		public static final int STOPBITS_1 = 1;
		public static final int STOPBITS_2 = 2;
		public static final int STOPBITS_1_5 = 3;
		public static final int PARITY_NONE = 0;
		public static final int PARITY_ODD = 1;
		public static final int PARITY_EVEN = 2;
		public static final int PARITY_MARK = 3;
		public static final int PARITY_SPACE = 4;
	*/

	private static HashMap specialCaracters = null;

	private static Printer printer = null;
	private static int packet = 50;
	private static int pause = 1500;
	private StringBuffer dataBuffer = new StringBuffer();

	/** Initialization method that will be called after the applet is loaded
	 *  into the browser.
	 */
	public void init()
	{
		super.init();

		String os  = System.getProperty("os.name");
		if (driver == null)
		{
		    if(getParameter("charset") != null)    
		        charset = getParameter("charset");
			String specialCaractersString = "#;$;à;°;ç;§;^;`;é;ù;è;¨";
			if (getParameter("specialCaractersString") != null)
			    specialCaractersString = getParameter("specialCaractersString");
			StringTokenizer specialCaractersStk = new StringTokenizer(specialCaractersString, ";");
			String bindCaracteresSpeciauxString = "23;24;40;5B;5C;5D;5E;60;7B;7C;7D;7E";
			if (getParameter("bindCaracteresSpeciauxStr") != null)
			    bindCaracteresSpeciauxString = getParameter("bindCaracteresSpeciauxString");
			StringTokenizer bindCaracteresSpeciauxStk = new StringTokenizer(bindCaracteresSpeciauxString, ";");

			if (specialCaractersStk != null && bindCaracteresSpeciauxStk != null)
			{
			    specialCaracters = new HashMap(specialCaractersStk.countTokens());
				if (specialCaractersStk.countTokens() <= bindCaracteresSpeciauxStk.countTokens())
				{
					while (specialCaractersStk.hasMoreTokens())
					{
					    specialCaracters.put(specialCaractersStk.nextToken(), bindCaracteresSpeciauxStk.nextToken());
					}
				}
			}

			String driverName = "com.sun.comm.Win32Driver";
			String portCom = "COM1";
			try
			{
			    if(os.toLowerCase().matches("linux"))
			    {
					if (getParameter("linuxDriverName") != null)
						driverName = getParameter("linuxDriverName");
					if (getParameter("linuxPortCom") != null)
						portCom = getParameter("linuxPortCom");
			    }    
			    else
			    {    
					if (getParameter("windowsDriverName") != null)
						driverName = getParameter("windowsDriverName");
					if (getParameter("windowsPortCom") != null)
						portCom = getParameter("windowsPortCom");
			    }    
				if (getParameter("serialportBauds") != null)
					serialportBauds = Integer.parseInt(getParameter("serialportBauds"));
				if (getParameter("serialportBits") != null)
					serialportBits = Integer.parseInt(getParameter("serialportBits"));
				if (getParameter("serialportStopBits") != null)
					serialportStopBits = Integer.parseInt(getParameter("serialportStopBits"));
				if (getParameter("serialportParity") != null)
					serialportParity = Integer.parseInt(getParameter("serialportParity"));
				if (getParameter("packet") != null)
					packet = Integer.parseInt(getParameter("packet"));
				if (getParameter("pause") != null)
					pause = Integer.parseInt(getParameter("pause"));

				String debugStr = getParameter("debug");
				debug = (debugStr != null && debugStr.equals("true"));
			}
			catch (Exception e)
			{
//				debug = true;
				System.out.println("Erreur de récupération des paramètres de l'imprimante série");
				e.printStackTrace();
			}
/*
			if (debug)
			{
				System.out.println("Initialisation de l'applet ImpressionApplet : ");
				System.out.println("driver.name : " + driverName);
				System.out.println("imprimante.portcom : " + portCom);
				System.out.println("serialport.bauds : " + serialportBauds);
				System.out.println("serialport.bits : " + serialportBits);
				System.out.println("serialport.stopbits : " + serialportStopBits);
				System.out.println("serialport.parity : " + serialportParity);
				System.out.println("packet : " + packet);
				System.out.println("pause : " + pause);
				System.out.println("imprimante.caracteresSpeciaux : " + specialCaractersString);
				System.out.println("imprimante.bindCaracteresSpeciaux : " + bindCaracteresSpeciauxStr);
			}
*/
			try
			{
				driver = (CommDriver) Class.forName(driverName).newInstance();
				driver.initialize();
			}
			catch (Exception e)
			{
				//debug = true;
				System.out.println("Erreur d'instanciation du driver");
				e.printStackTrace();
			}

			try
			{
				portId = CommPortIdentifier.getPortIdentifier(portCom);
			}
			catch (Exception e)
			{
			//	debug = true;
				System.out.println("Erreur de récupération de l'identifiant du port de communication");
				e.printStackTrace();
			}

			printer = new Printer();
		}
	}

	private byte[] getLine(String line, int size)
	{
	    byte[] result = null; 
		StringBuffer strBuff = new StringBuffer();

		if (size == 2)
		{
			//Format de la police en gras et avec une taille double en largeur et en longueur
			strBuff.append((char) 0x1B);
			strBuff.append((char) 0x21);
			strBuff.append((char) 0x38);
		}

		if (line.equals(""))
			line += " ";

		strBuff.append(bindSpecialCaracters(line));

		if (size == 2)
		{
			//Format de la police par défaut
			strBuff.append((char) 0x1B);
			strBuff.append((char) 0x21);
			strBuff.append((char) 0x01);
		}

		//Saut de ligne
		strBuff.append((char) 0x0A);

		try
		{
		    result = strBuff.toString().getBytes(charset);
		}
		catch(UnsupportedEncodingException uee)
		{
		    result = strBuff.toString().getBytes();
		}
		result = strBuff.toString().getBytes();
		
		return result;
	}
	
	public void print()
	{
		//Initialisation imprimante
		dataBuffer.insert(0, new String(INITIALIZE_PRINTER_INTERNATIONAL_CHARACTER));
		//dataBuffer.append(new String(PRINT_FEED_PAPER_CUT_SHEET));

		if (debug)
		{
			System.out.println("Data dans le buffer avant envoie vers imprimante : \n" + dataBuffer.toString());
		}

		byte[] dataBytes = null;
		try
		{
		    dataBytes = dataBuffer.toString().getBytes(charset);
		}
		catch(UnsupportedEncodingException uee)
		{
		    if (debug)
			{
				System.out.println("Charset impossible d'encoder : "+charset);
			}
		    dataBytes = dataBuffer.toString().getBytes();
		}
		
		byte[] dataPlusCutPaper = new byte[dataBytes.length + PrinterApplet.PRINT_FEED_PAPER_CUT_SHEET.length];
		System.arraycopy(dataBytes, 0, dataPlusCutPaper, 0, dataBytes.length);
		System.arraycopy(PRINT_FEED_PAPER_CUT_SHEET, 0, dataPlusCutPaper, dataBytes.length, PrinterApplet.PRINT_FEED_PAPER_CUT_SHEET.length);

		ThreadPrinter tp = new ThreadPrinter(printer, dataPlusCutPaper);
		dataBuffer = new StringBuffer();
		tp.start();
	}

	public void resetDataBuffer()
	{
		dataBuffer = new StringBuffer();
	}

	public void addData1(String data)
	{
		dataBuffer.append(new String(getLine(data, 1)));
	}

	public void addData2(String data)
	{
		dataBuffer.append(new String(getLine(data, 2)));
	}

	private String bindSpecialCaracters(String data)
	{
		Object[] specialCaractersArray = specialCaracters.keySet().toArray();
		for (int i = 0; i < specialCaractersArray.length; i++)
		{
		    data = data.replace(((String) specialCaractersArray[i]).charAt(0), (char) Integer.parseInt((String) specialCaracters.get(specialCaractersArray[i]), 16));
		}

		return data;
	}

	class Printer
	{
		private SerialPort serialport = null;

		public Printer()
		{
		}

		private void openSerialPort()
		{
			if (portId != null && serialport == null)
			{
				try
				{
					if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL)
					{
						serialport = (SerialPort) portId.open("montagnesdor", 2 * pause);

						serialport.setSerialPortParams(serialportBauds, serialportBits, serialportStopBits, serialportParity);
						serialport.setDTR(true);
					}
				}
				catch (Exception e)
				{
					System.out.println("Impossible de récupérer le port série " + e);
				}
			}
		}

		private void closeSerialPort()
		{
			serialport.close();
			serialport = null;
			try
			{
				Thread.sleep(2 * pause);
			}
			catch (Exception e)
			{
				System.out.println("Impossible de faire la pause de " + (2 * pause) + "\n" + e);
			}
		}

		public synchronized void printData(byte[] data)
		{
			openSerialPort();
			BufferedOutputStream out = null;
			try
			{
				out = new BufferedOutputStream(serialport.getOutputStream());
				int lastLength = data.length % packet;
				int indexDataBegin = 0;
				while (indexDataBegin < data.length)
				{
					if (serialport.isDSR())
					{
						if (indexDataBegin + packet <= data.length)
						{    
							out.write(data, indexDataBegin, packet);
							if (debug)
							{
								System.out.println("Data sent to printer: \n" + new String(data, indexDataBegin, packet));
							}
						}	
						else
						{    
							out.write(data, indexDataBegin, lastLength);
							if (debug)
							{
								System.out.println("Data sent to printer: \n" + new String(data, indexDataBegin, lastLength));
							}
						}	
						out.flush();
						try
						{
							Thread.sleep(pause / 5);
						}
						catch (Exception e)
						{
							System.out.println("Impossible de faire la pause de " + (pause / 5) + " " + e);
						}
						indexDataBegin += packet;
					}
					else
					{
						System.out.println("Imprimante occupée !!! ");
						try
						{
							Thread.sleep(pause);
						}
						catch (Exception e)
						{
							System.out.println("Impossible de faire la pause de " + (pause / 5) + " " + e);
						}
					}

				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			finally
			{
				try
				{
					out.close();
				}
				catch (Exception e)
				{
					System.out.println(e);
				}
			}
			closeSerialPort();
		}
	}

	class ThreadPrinter extends Thread
	{
		Printer printer = null;
		byte[] data = null;

		public ThreadPrinter(Printer printer, byte[] data)
		{
			this.printer = printer;
			this.data = data;
		}

		public void run()
		{
			printer.printData(data);
		}
	}
}
