package edu.bsu.emlacy;

import java.io.*;
import java.util.*;

public class MacBeth {
	private ArrayList<RoomClass> roomList;
	Scanner kb = new Scanner(System.in);
	private ArrayList<Items> itemListt;
	private String currentRoom;
	private Hero hero;

	@SuppressWarnings("resource")
	public MacBeth() {
		hero = new Hero();
		
		Scanner fileRead;
		roomList = new ArrayList<RoomClass>();

		try {
			fileRead = new Scanner(new File("RoomFile"));
		} catch (FileNotFoundException e) {
			System.out.println("Cannot find file!");
			fileRead = null;
		}
		
		int i = 1;
		
		while (fileRead.hasNextLine()) {
			String rawData = fileRead.nextLine();
			String[] splitData = rawData.split(";");
			String roomData = splitData[1];
			String[] roomDir = roomData.split(",");

			roomList.add(new RoomClass(splitData[0], Integer.parseInt(roomDir[0]), Integer.parseInt(roomDir[1]), Integer.parseInt(roomDir[2]), Integer.parseInt(roomDir[3]), splitData[2], i));
			i++;
		}

		while (!hasWon()) {
			takeTurn();
			
		}
		
		File winText = new File("Room1DescWin");
		
		Scanner winTextIn;
		try {
			winTextIn = new Scanner(winText);
		} catch (FileNotFoundException e) {
			winTextIn = null;
		}
		
		while(winTextIn.hasNextLine()) {
			System.out.println(winTextIn.nextLine());
		}
		
	}

	public void oneWords(String action) { // sets up the swing cases for the
										// single word commands entered
		int heroRoom = hero.getRoom();
		
		switch (action) {
		case "NORTH":
			hero.playerRoom(roomList.get(heroRoom - 1).getNorth(heroRoom));
			System.out.println("Room:" + hero.getRoom());
			break;
		case "EAST":
			hero.playerRoom(roomList.get(heroRoom - 1).getEast(heroRoom));
			System.out.println("Room:" + hero.getRoom());
			break;
		case "SOUTH":
			hero.playerRoom(roomList.get(heroRoom - 1).getSouth(heroRoom));
			System.out.println("Room:" + hero.getRoom());
			break;
		case "WEST":
			hero.playerRoom(roomList.get(heroRoom - 1).getWest(heroRoom));
			System.out.println("Room:" + hero.getRoom());
			break;
		case "LOOK":
			look();
			break;
		case "INVENTORY":
			hero.getInventory();
			break;
		case "HELP":
			help();
			break;
		case "QUIT":
			System.exit(0);
			break;
		}
	}

	public void twoWords(String action1, String action2) {// sets up the switch cases for the
		// double word commands entered
		int heroRoom = hero.getRoom();
		
		switch (action1) {
		case "TAKE":
			hero.addItem(roomList.get(heroRoom - 1).dropItem(action2));
			break;
		case "DROP":
			roomList.get(heroRoom - 1).addItem(hero.dropItem(action2));
			break;
		case "GO": case "MOVE":
			if (action2.equals("NORTH")) {
				// User put in move north
				hero.playerRoom(roomList.get(heroRoom - 1).getNorth(heroRoom));
				System.out.println("Room:" + hero.getRoom());
			}
			if (action2.equals("WEST")) {
				hero.playerRoom(roomList.get(heroRoom - 1).getWest(heroRoom));
				System.out.println("Room:" + hero.getRoom());
			}
			if (action2.equals("SOUTH")) {
				hero.playerRoom(roomList.get(heroRoom - 1).getSouth(heroRoom));
				System.out.println("Room:" + hero.getRoom());
			}
			if (action2.equals("EAST")) {
				hero.playerRoom(roomList.get(heroRoom - 1).getEast(heroRoom));
				System.out.println("Room:" + hero.getRoom());
			}
		}
	}

	public void takeTurn() {// allows the hero/player to take their turn and
							// perform whatever action they want
		roomList.get(hero.getRoom() - 1).outputDesc();
		
		System.out.println("What do you want to do?");
		String action = kb.nextLine();
		action = action.toUpperCase();
		String[] splitActions = action.split(" ");

		if (splitActions.length == 1) {
			oneWords(splitActions[0]);
		} else if (splitActions.length == 2) {
			twoWords(splitActions[0], splitActions[1]);
		} else {
			System.out.println("Your input is not valid!");
		}
	}

	
	public void look() {
		roomList.get(hero.getRoom() - 1).getItems();
	}
	
	public void help(){
		System.out.println("Thank you for choosing the help method! \n "
				+ "The commands you can use are the directions: North, South, East, West \n"
				+ "In conjunction(or by themselves) with the Go or Move [direction] command. \n"
				+ "Additionally, You have the Look command to print out the description of the room \n"
				+ "and see if there is an item in the room. Finally you have the commands for items: \n"
				+ "Inventory) which displays the items held by the player, \n "
				+ "Take) which picks up an item from the room and lets the player hold it, \n"
				+ "Drop) which drops a specified item in the current room");
	}
	
	public int calcScore() {
		int score = 0;
		
		for (RoomClass e: roomList) {
			if(e.hasVisited()) {
				score++;
			}
		}
		
		return score + roomList.get(0).getItemAmount();
	}
	
	public boolean hasWon(){
		if(roomList.get(0).getItemAmount() == 5){
			return true;
		} else {
			return false;
		}
	}
}
