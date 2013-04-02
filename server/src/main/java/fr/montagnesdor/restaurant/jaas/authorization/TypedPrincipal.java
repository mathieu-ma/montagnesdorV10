
package fr.montagnesdor.restaurant.jaas.authorization;

import java.io.*;
import java.security.Principal;

/**
 * TypedPrincipals are derived from, and can be treated like Principals
 * but they also contain extra information about the type of the Principal
 * which can be USER, GROUP or DOMAIN. I'm not 100% certain that this is a
 * good way of doing things. Suggestions welcome.
 *
 * @author Andy Armstrong
 * @version 1.0.3
 */
public class TypedPrincipal implements Principal, Serializable
{
	/**
	 */
	protected String name;

	/**
	 * Create a TypedPrincipal with a name.
	 *
	 * <p>
	 *
	 * @param name the name for this Principal.
	 * @exception NullPointerException if the <code>name</code>
	 * 			is <code>null</code>.
	 */
	public TypedPrincipal(String name)
	{
		if (name == null)
			throw new NullPointerException("illegal null input");

		this.name = name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * Return the name for this <code>TypedPrincipal</code>.
	 *
	 * <p>
	 *
	 * @return the name for this <code>TypedPrincipal</code>
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Compares the specified Object with this <code>TypedPrincipal</code>
	 * for equality.  Returns true if the given object is also a
	 * <code>TypedPrincipal</code> and the two TypedPrincipals have the
	 * same name and type. If the object is any other kind Principal
	 * its will be considered equal if the name matches.
	 *
	 * <p>
	 *
	 * @param o Object to be compared for equality with this <code>TypedPrincipal</code>.
	 * @return true if the specified Object is equal to this <code>TypedPrincipal</code>.
	 */
	public boolean equals(Object o)
	{
		if (o == null)
		{
			return false;
		}
		else if (this == o)
		{
			return true;
		}
		else if (o instanceof TypedPrincipal)
		{
			TypedPrincipal that = (TypedPrincipal) o;
			return that.getName().equals(getName());
		}
		else if (o instanceof Principal)
		{
			Principal that = (Principal) o;
			return that.getName().equals(getName());
		}
		else
		{
			return false;
		}
	}

	/**
		* Return a string representation of this <code>SamplePrincipal</code>.
		*
		* <p>
		*
		* @return a string representation of this <code>SamplePrincipal</code>.
		*/
	public String toString()
	{
		return ("TypedPrincipal :  " + name);
	}

	/**
	 * Return a hash code for this <code>TypedPrincipal</code>.
	 *
	 * <p>
	 *
	 * @return a hash code for this <code>TypedPrincipal</code>.
	 */
	public int hashCode()
	{
		return name.hashCode();
	}

}