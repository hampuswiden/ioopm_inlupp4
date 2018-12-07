package pollax.world;

import java.util.List;
import java.util.ArrayList;
import java.io.IOException;

import pollax.creatures.*;
import pollax.interactive.InteractiveObject;
import pollax.interactive.Door;
import pollax.items.Item;
import pollax.utils.InvalidInputException;

/**
 * @author      Jonathan Franzén, Hampus Widén
 * @version     1.0
 * @since       1.0
 */
public class Room {
	private String name;
	private List<String> rooms;
	private List<Door> doors;
	private List<Creature> creatures = new ArrayList<Creature>();
	private List<Item> items = new ArrayList<Item>();
	private List<InteractiveObject> interactives = new ArrayList<InteractiveObject>();

	public Room() {

	}

	public Room(String... args) {
		this.name = args[0];

		// Rooms
		List<String> rooms = new ArrayList<String>();
		rooms.add(args[1]);
		rooms.add(args[2]);
		rooms.add(args[3]);
		rooms.add(args[4]);
		this.rooms = rooms;

		// Doors
		Door nDoor = new Door(args[5]);
		Door eDoor = new Door(args[6]);
		Door sDoor = new Door(args[7]);
		Door wDoor = new Door(args[8]);

		List<Door> doors = new ArrayList<Door>();
		doors.add(nDoor);
		doors.add(eDoor);
		doors.add(sDoor);
		doors.add(wDoor);
		this.doors = doors;

	}

	public String toString() {
		String retStr = this.name + "\n" +
		"N: " + this.rooms.get(0) +
		" E: " + this.rooms.get(1) +
		" S: " + this.rooms.get(2) +
		" W: " + this.rooms.get(3);

		// Creatures
		retStr += "\nCreatures: ";
		for (int counter = 0; counter < this.creatures.size(); counter++) {
        	retStr += this.creatures.get(counter);
        	if (counter != this.creatures.size()-1) {
				retStr += ", ";
        	}
		}

		// Items
		retStr += "\nItems: ";
		for (int counter = 0; counter < this.items.size(); counter++) {
        	retStr += this.items.get(counter);
        	if (counter != this.items.size()-1) {
				retStr += ", ";
        	}
		}

		// Not using interactives at the moment
		/*
		retStr += "\nInteractives: ";
		for (int counter = 0; counter < this.interactives.size(); counter++) {
        	retStr += this.interactives.get(counter);
        	if (counter != this.interactives.size()-1) {
				retStr += ", ";
        	}
		}
		*/

		return retStr;
	}

	public List<Item> getItems() {
		return this.items;
	}

	public void addItem(Item item) {
		this.items.add(item);
	}

	public void removeItem(Item item) {
		if (this.items.contains(item)) {
			this.items.remove(item);
		}
	}

	public List<Creature> getCreatures() {
		return this.creatures;
	}

	public void addCreature(Creature creature) {
		this.creatures.add(creature);
	}

	public void removeCreature(Creature creature) {
		if (this.creatures.contains(creature)) {
			this.creatures.remove(creature);
		}
	}

	public boolean hasCreatures() {
		return this.creatures.size() != 0;
	}

	public boolean hasTeacher() {
		Creature creature;
		for (int i = 0; i < this.creatures.size(); i++) {
        	creature = this.creatures.get(i);
        	if (creature.isTeacher()) {
        		return true;
        	}
		}
		return false;
	}

	public Teacher getTeacher() {
		Creature creature;
		for (int i = 0; i < this.creatures.size(); i++) {
        	creature = this.creatures.get(i);
        	if (creature.isTeacher()) {
        		return (Teacher) creature;
        	}
		}
		return new Teacher();
	}

	public int getDirection(String direction) throws InvalidInputException {
		int door = -1;
		if (direction.equals("north")) {
			door = 0;
		} else if (direction.equals("east")) {
			door = 1;
		} else if (direction.equals("south")) {
			door = 2;
		} else if (direction.equals("west")) {
			door = 3;
		} else {
			throw new InvalidInputException("'" + direction + "' is not a valid direction");
		}
		return door;
	}

	public boolean checkDirection(String direction) {
		return checkDirectionRoom(direction) && checkDirectionDoor(direction);
	}

	public boolean checkDirectionRoom(String direction) {
		int door = this.getDirection(direction);

		if (!this.rooms.get(door).equals("X")) {
			return true;
		}
		return false;
	}

	public boolean checkDirectionDoor(String direction) {
		int door = this.getDirection(direction);

		if (this.doors.get(door).isOpen()) {
			return true;
		}
		return false;
	}

	public String getRoom(String direction) {
		int door = this.getDirection(direction);
		return this.rooms.get(door);
	}

	public void openDoor(String direction) {
		int door = this.getDirection(direction);

		if (this.checkDirectionRoom(direction) && !this.checkDirectionDoor(direction)) {
			this.doors.get(door).open();
		} 
	}

	@Override
	public boolean equals(Object other) {
	    if (other instanceof Room) {
	        return this.equals((Room) other);
	    } else {
	        return false;
	    }
	}

	public boolean equals(Room other) {
		return this.name.equals(other.name);
	}
}
