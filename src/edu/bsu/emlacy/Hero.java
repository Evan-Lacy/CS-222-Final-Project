package edu.bsu.emlacy;

import java.util.*;

public class Hero {
	private int currentRoom;
	private Inventory heroItems;
	
	public Hero() {
		heroItems = new Inventory();
		currentRoom = 1;
	}

	public void getInventory() {
		heroItems.outputInventoryList();
	}

	public int getRoom() { // needs to print out/determine what the current
							// room the hero is in
		return currentRoom;

	}
	public void playerRoom(int newRoom){
		currentRoom = newRoom;
	}
	
	public void addItem(String name){
		heroItems.addItem(name);
		if (!name.equals("")) {
			System.out.println("You picked up " + name + " from this room.");
		}
	}
	
	public void outputItems() {
		System.out.println("Inventory:");
		heroItems.outputInventoryList();
	}
	
	public String dropItem(String name){
		return heroItems.dropItem(name);
	}
}
