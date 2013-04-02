package fr.montagnesdor.util.tools;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import fr.montagnesdor.util.log.MyLog;
import fr.montagnesdor.util.log.MyLogFactoryImpl;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class IncludeFile
{
	private MyLog log = MyLogFactoryImpl.getInstance().getLogger(IncludeFile.class.getName());

	private boolean isTop = true;
	private File workFileOrDirectory = new File("J:\\eclipse\\workspace\\montagnesdor\\jsp");
	private File fileTobeIncluded = new File("J:\\eclipse\\workspace\\montagnesdor\\temp\\sample.txt");
	private String extension = ".jsp";

	public void checkParameters()
	{
		if (workFileOrDirectory.exists())
		{
			process(workFileOrDirectory);
		}
	}

	public void process(File dir)
	{
		if (dir == null)
			return;

		if (dir.isDirectory())
		{
			File[] files = dir.listFiles(new MyFileFilter());
			if (files == null)
				return;
			for (int i = 0; i < files.length; i++)
			{
				if (files[i].isDirectory())
				{
					process(files[i]);
				}
				else
				{
					include(files[i]);
				}
			}
		}
		else
		{
			if ((new MyFileFilter()).accept(dir))
			{
				include(dir);
			}
		}
	}

	public void include(File file)
	{
		FileInputStream fis = null;
		FileOutputStream fos = null;
		
		File tempFile  = null;
		try
		{
			tempFile = new File(file.getAbsolutePath()+"tmp");
			fis = new FileInputStream(fileTobeIncluded);
			if (isTop)
				fos = new FileOutputStream(tempFile);
			else
				fos = new FileOutputStream(file);
				
			byte[] b = new byte[fis.available()];
			fis.read(b);
			fos.write(b);
			fos.flush();
			
			if(isTop)
			{
				fis.close();
				fis = new FileInputStream(file);
				int data = -1;
				while((data=fis.read())!=-1)
				{
					fos.write(data);
				}
				fos.flush();
			}
		}
		catch (IOException ioe)
		{

		}
		finally
		{
			try
			{
				fos.close();
				fis.close();
				if(file.delete())
					tempFile.renameTo(file);
			}
			catch (Exception e)
			{
			}
		}
	}

	private File createTempFile(File file)
	{
		File result = null;
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try
		{
			result = File.createTempFile(file.getName(), null);
			fis = new FileInputStream(file);
			fos = new FileOutputStream(result);
			byte[] b = new byte[fis.available()];
			fis.read(b);
			fos.write(b);
			fos.flush();
		}
		catch (IOException e)
		{
		}
		finally
		{
			try
			{
				fos.close();
				fis.close();
			}
			catch (Exception e)
			{
			}
		}

		return result;
	}

	public static void main(String[] args) throws Exception
	{
		IncludeFile inlcudeFile = new IncludeFile();
		inlcudeFile.checkParameters();

	}

	class MyFileFilter implements FileFilter
	{
		/**
		 * @see java.io.FileFilter#accept(java.io.File)
		 */
		public boolean accept(File pathname)
		{
			if (pathname.isDirectory() || pathname.getName().endsWith(extension))
				return true;
			return false;
		}

	}
}
