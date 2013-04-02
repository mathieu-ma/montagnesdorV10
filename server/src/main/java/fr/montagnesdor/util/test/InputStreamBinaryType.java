/*
 * Created on 22 avr. 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package fr.montagnesdor.util.test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;


import net.sf.hibernate.HibernateException;
import net.sf.hibernate.UserType;

/**
 * @author conrad
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class InputStreamBinaryType implements UserType
{

	/* (non-Javadoc)
	 * @see net.sf.hibernate.UserType#deepCopy(java.lang.Object)
	 */
	public Object deepCopy(Object value) throws HibernateException
	{
		return value;
	}

	/* (non-Javadoc)
	 * @see net.sf.hibernate.UserType#equals(java.lang.Object, java.lang.Object)
	 */
	public boolean equals(Object x, Object y) throws HibernateException
	{
		boolean result = false;
		if(x==y)
			result =true;
		else  if(x != null && y != null)
		{
			InputStream xis = (InputStream)x;
			InputStream yis = (InputStream)y;
			try
			{
				if(xis.available()==yis.available())
				{
					int length = xis.available();
					byte[] xba = new byte[length];
					byte[] yba = new byte[length];
					xis.read(xba);
					yis.read(yba);
					result = java.util.Arrays.equals((byte[]) xba, (byte[]) yba);
				}
			}
			catch(IOException ioe)
			{
				throw new HibernateException("Impossible d'obtenir la taille de l'inputStream ...");
			}
		}
		
		return result;
	}

	/* (non-Javadoc)
	 * @see net.sf.hibernate.UserType#isMutable()
	 */
	public boolean isMutable()
	{
		return true;
	}

	/* (non-Javadoc)
	 * @see net.sf.hibernate.UserType#nullSafeGet(java.sql.ResultSet, java.lang.String[], java.lang.Object)
	 */
	public Object nullSafeGet(ResultSet rs, String[] names, Object owner) throws HibernateException, SQLException
	{
		return rs.getBinaryStream(names[0]);
	}

	/* (non-Javadoc)
	 * @see net.sf.hibernate.UserType#nullSafeSet(java.sql.PreparedStatement, java.lang.Object, int)
	 */
	public void nullSafeSet(PreparedStatement st, Object value, int index)  throws HibernateException, SQLException
	{
		InputStream is = (InputStream)value;
		try
		{
			st.setBinaryStream(index, is, is.available());
		}
		catch(IOException ioe)
		{
			throw new SQLException("Impossible d'obtenir la taille de l'inputStream ...");
		}
	}

	/* (non-Javadoc)
	 * @see net.sf.hibernate.UserType#returnedClass()
	 */
	public Class returnedClass()
	{
		return InputStream.class;
	}

	/* (non-Javadoc)
	 * @see net.sf.hibernate.UserType#sqlTypes()
	 */
	public int[] sqlTypes()
	{
		return new int[] { Types.BINARY };
	}

}
