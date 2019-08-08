package edu.bsu.emlacy;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class RoomClass {
	private String roomDesc;
	private int north;
	private int east;
	private int south;
	private int west;
	private Inventory roomInventory;
	private String postText;
	private boolean hasInter;
	private boolean hasVisit;
	private int roomNumber;

	
	public RoomClass(String RoomDesc, int roomDir, int roomDir2, int roomDir3, int roomDir4, String itemI, int roomNum) {
		roomDesc = "";
		postText = "";
		hasInter = false;
		hasVisit = false;
		roomDesc = fileReadDesc(RoomDesc);
		postText = getPostText(RoomDesc);
		north = roomDir;
		south = roomDir3;
		east = roomDir2;
		west = roomDir4;
		roomInventory = new Inventory();
		if (!itemI.equals("N")) {
			roomInventory.addItem(itemI);
		}
		roomNumber = roomNum;
	}

	public String fileReadDesc(String path) { //returns the current room description 
		File textFile = new File(path);
		Scanner fileRead;
		try {
			fileRead = new Scanner(textFile);
		} catch (FileNotFoundException e) {
			fileRead = null;
			System.out.println("Something went wrong!");
		}
		
		String tempVar = "";
		while (fileRead.hasNextLine()) {
			tempVar += "\n" + fileRead.nextLine();
		}
		
		return tempVar;
	}
	
	public String getPostText(String path) {
		File myfile = new File(path + "Up");
		
		if (myfile.exists()) {
			Scanner fileIn;
			try {
				fileIn = new Scanner(myfile);
			} catch (FileNotFoundException e) {
				fileIn = null;
			}
			
			String temp = "";
			while (fileIn.hasNextLine()) {
				temp += "\n"+ fileIn.nextLine();
			}
			
			return temp;
		} else {
			return "";
		}
	}
	
	public void outputDesc() {
		if (hasInter) {
			System.out.println(postText);
		} else {
			if (hasVisit) {
				hasVisit = true;
			}
			System.out.println(roomDesc);
		}
		
	}

	public int getNorth(int currentRoom) { // determines if the hero can go North and if not
							// that does not allow it
		if (north == -1) {
			System.out.println("There is nothing in that direction.");
			return currentRoom;
		} else {
			return north;
		}
	}

	public int getEast(int currentRoom) {// determines if the hero can go East and if not
							// that does not allow it
		if (east == -1) {
			System.out.println("There is nothing in that direction.");
			return currentRoom;
		} else {
			return east;
		}

	}
		
	public int getSouth(int currentRoom) {// determines if the hero can go South and if not
							// that does not allow it
		if (south == -1) {
			System.out.println("There is nothing in that direction.");
			return currentRoom;
		} else {
			return south;
		}

	}

	public int getWest(int currentRoom) {// determines if the hero can go East and if not
							// that does not allow it
		if (west == -1) {
			System.out.println("There is nothing in that direction.");
			return currentRoom;
		} else {
			return west;
		}
	}
	
	public void addItem(String name){
		roomInventory.addItem(name);
		if (!name.equals("")) {
			if (roomNumber == 1) {
				hasInter = true;
			}
			System.out.println("You dropped " + name + " in this room.");
		}
	}
	
	public String dropItem(String name){
		if (!name.equals("")) {
			hasInter = true;
		}
		return roomInventory.dropItem(name);
	}
	
	public void getItems() {
		System.out.println("Items in Room: ");
		roomInventory.outputInventoryList();
	}
	
	public boolean hasVisited() {
		return hasVisit;
	}
	
	public int getItemAmount() {
		return roomInventory.getItemAmount();
	}
}
