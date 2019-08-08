package edu.bsu.emlacy;

import java.util.*;

public class Inventory {
	private ArrayList<Items> itemList;
	
	public Inventory() { // constructor
		itemList = new ArrayList<Items>();
	}
	
	public void outputInventoryList(){
		for (Items e: itemList){
			System.out.println(e.getName());
		}
		
	}
	
	public void addItem (String items){
		if (!items.equals("")) {
			itemList.add(new Items(items));
		}
	}
	
	public String dropItem(String name){
		for (Items e: itemList) {
			if (name.equals(e.getName())) {
				itemList.remove(e);
				return e.getName();
			}
		}
		
		return "";
	}
	
	public int getItemAmount() {
		return itemList.size();
	}
}
