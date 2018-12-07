package pollax.creatures;

import pollax.world.*;
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
	private int maxCapacity = 10;

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

	public void go(String direction, World world) {
		super.go(direction, world);
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

	public void pickUp(String itemName, World world) {
		Item item = world.dbItems().get(itemName);
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
			System.out.println("No item '" + itemName + "' in the room.");
		}
	}

	public void drop(String itemName, World world) {
		Item item = world.dbItems().get(itemName);

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
		int sumCapacity = 0;
		for (int i = 0; i < items.size(); i++) {
			item = items.get(i);
			sumCapacity += item.getCapacity();
		} 
		return sumCapacity;
	}

	public boolean checkCapacity(Item item) {
		return this.currentCapacity() + item.getCapacity() <= this.maxCapacity;
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
		retStr += "\nCapacity: " + this.currentCapacity() + "/" + this.maxCapacity;
		System.out.println(retStr);
	}
}
