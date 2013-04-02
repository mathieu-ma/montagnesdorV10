/*
 * Created on 22 avr. 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package fr.montagnesdor.util.test;

/**
 * @author conrad
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class NumeroThread extends ThreadLocal
{
	static int numeroCourant = 0;
			 protected Object initialValue() {
				numeroCourant++;
				return new Integer(numeroCourant);
			 }
}
