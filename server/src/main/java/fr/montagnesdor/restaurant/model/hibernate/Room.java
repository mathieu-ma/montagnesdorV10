/*
 * Project				montagnesdor
 * File name		Room.java
 * Created on		4 sept. 2004
 * @author			Mathieu MA sous conrad
 * @version		1.0
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package fr.montagnesdor.restaurant.model.hibernate;

public class Room
{
	DinnerTable currentTable = new DinnerTable();
	 
	public Room()
	{
	}
	
	public Room(String roomName)
	{
	}

	/**
	 * @return
	 */
	public DinnerTable getCurrentTable()
	{
		return currentTable;
	}

	/**
	 * @param table
	 */
	public void setCurrentTable(DinnerTable table)
	{
		currentTable = table;
	}

}
