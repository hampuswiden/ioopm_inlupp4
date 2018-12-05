package pollax.creatures;

import pollax.world.Room;
import pollax.items.*;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author      Jonathan Franzén, Hampus Widén
 * @version     1.0
 * @since       1.0
 */
public class Avatar extends Student {
	private int hp = 60;
	private int capacity = 10;

	public Avatar(String name, Room startRoom) {
		super(name, startRoom);
	}

	@Override
	public boolean isAvatar() {
		return true;
	}

	public Room getRoom() {
		return super.getRoom();
	}

	public void go(String direction, HashMap<String, Room> dbRooms) {
		super.go(direction, dbRooms);
	}

	public void openDoor(String direction) {
		Room room = super.getRoom();
		List<Item> items = super.getItems();

		Key key = new Key();
		if (room.checkDirectionRoom(direction)) {
			if (!room.checkDirectionDoor(direction)) {
				if (items.contains(key)) {
					room.openDoor(direction);
					super.removeItem(key);
					System.out.println("Succesfully opened the " + direction + " door.");
				} else {
					System.out.println("No key(s) in inventory!");	
				}
			} else {
				System.out.println("The " + direction + " door is already open.");
			}
		} else {
			System.out.println("No room to the " + direction + ".");
		}
	}

	public void pickUp(String itemName, HashMap<String, Item> dbItems) {
		Item item = dbItems.get(itemName);
		Room room = super.getRoom();
		List<Item> roomItems = room.getItems();

		if (roomItems.contains(item)) {
			if (checkCapacity(item)) {
				super.addItem(item);
				room.removeItem(item);
				System.out.println("Picked up " + item);
			} else {
				System.out.println("Not enough capacity.");	
			}
		} else {
			System.out.println(itemName + " does not exist.");
		}
	}

	public void drop(String itemName, HashMap<String, Item> dbItems) {
		Item item = dbItems.get(itemName);

		List<Item> items = super.getItems();
		Room room = super.getRoom();
		List<Item> roomItems = room.getItems();

		if (items.contains(item)) {
			room.addItem(item);
			super.removeItem(item);
			System.out.println("Dropped " + item + " on the floor.");
		} else {
			System.out.println("No " + item + " in inventory.");
		}
	}

	public int currentCapacity() {
		List<Item> items = super.getItems();

		Item item;
		int totalCapacity = 0;
		for (int i = 0; i < items.size(); i++) {
			item = items.get(i);
			totalCapacity += item.getCapacity();
		} 
		return totalCapacity;
	}

	public boolean checkCapacity(Item item) {
		return this.currentCapacity() + item.getCapacity() <= this.capacity;
	}

	public void inventory() {
		List<Item> items = super.getItems();

		String retStr = "Items: ";
		Item item;
		for (int i = 0; i < items.size(); i++) {
			item = items.get(i);
			retStr += item.toString();
			if (i != items.size()-1)
        	{
				retStr += ", ";
        	}
		}
		retStr += "\nCapacity: " + this.currentCapacity() + "/" + this.capacity;
		System.out.println(retStr);
	}
}
