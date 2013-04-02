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
	public class DemoThreadLocal extends ThreadLocal{
		
	

		
	  static NumeroThread numero = new NumeroThread();
	  public static void main(String[] unused) {
		 for (int i = 0; i < 5; i++) {
			Activite a = new Activite();
			new Thread(a).start();
		 }
	  }
	}


