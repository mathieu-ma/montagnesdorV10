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
public class Activite implements Runnable
{
	public void run() {
		   System.out.println("Mon numero est " + (Integer) DemoThreadLocal.numero.get());
		}

}
