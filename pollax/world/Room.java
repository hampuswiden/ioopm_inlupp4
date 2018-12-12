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
	/**
	* name for Room.
	*/
	private String name;

	/**
	* List of neighbouring Rooms names.
	*/
	private List<String> rooms;

	/**
	* List of doors to neighbouring Rooms.
	*/
	private List<Door> doors;

	/**
	* List of Creatures in Room.
	*/
	private List<Creature> creatures = new ArrayList<Creature>();

	/**
	* List of Items in Room
	*/
	private List<Item> items = new ArrayList<Item>();

	/**
	* List of InteractiveObjects in Room.
	*/
	private List<InteractiveObject> interactives = new ArrayList<InteractiveObject>();


	/**
	* empty constructor for Room.
	*/
	public Room() {

	}

	/**
	* constructor for Room.
	* @param args arguments to be applied to Room.
	*/
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

	/**
	* toString function for Room.
	* @return room name and things in it, and neighbouring rooms name in their respective directions.
	*/
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

	/**
	* gets items in Room.
	* @return List of items in Room.
	*/
	public List<Item> getItems() {
		return this.items;
	}

	/**
	* adds item to Room.
	* @param item to be added to Room.
	*/
	public void addItem(Item item) {
		this.items.add(item);
	}

	/**
	* removes item from Room.
	* @param item to be removed from room.
	*/
	public void removeItem(Item item) {
		if (this.items.contains(item)) {
			this.items.remove(item);
		}
	}

	/**
	* gets creatures in Room.
	* @return List of creatures in Room.
	*/
	public List<Creature> getCreatures() {
		return this.creatures;
	}

	/**
	* adds creature to Room.
	* @param creature to be added to Room.
	*/
	public void addCreature(Creature creature) {
		this.creatures.add(creature);
	}

	/**
	* removes creature from Room.
	* @param creature to be removed from Room.
	*/
	public void removeCreature(Creature creature) {
		if (this.creatures.contains(creature)) {
			this.creatures.remove(creature);
		}
	}

	/**
	* checks if Room holds any creatures.
	* @return true if there are more than 0 creatures in Room.
	*/
	public boolean hasCreatures() {
		return this.creatures.size() != 0;
	}

	/**
	* checks if Room holds any Teachers.
	* @return true if there are more than 0 Teachers in Room.
	*/
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

	/**
	* gets Teacher in Room.
	* @return Teacher.
	*/
	public Teacher getTeacher() {
		Creature creature;
		for (int i = 0; i < this.creatures.size(); i++) {
        	creature = this.creatures.get(i);
        	if (creature.isTeacher()) {
        		return (Teacher) creature;
        	}
		}
		return new Teacher(); // throw new ....
	}

	/**
	* checks if Room holds any Sfinxes
	* @return true if there is a Sfinx in Room.
	*/
	public boolean hasSfinx() {
		Creature creature;
		for (int i = 0; i < this.creatures.size(); i++) {
        	creature = this.creatures.get(i);
        	if (creature.isSfinx()) {
        		return true;
        	}
		}
		return false;
	}

	/**
	* gets Sfinx in Room.
	* @return Sfinx in Room.
	*/
	public Sfinx getSfinx() {
		Creature creature;
		for (int i = 0; i < this.creatures.size(); i++) {
        	creature = this.creatures.get(i);
        	if (creature.isSfinx()) {
        		return (Sfinx) creature;
        	}
		}
		return new Sfinx(); // throw new ....
	}

	/**
	* converts direction in String format to int format.
	* @param direction to be converted.
	* @return direction in int format.
	* @throws InvalidInputException
	*/
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

	/**
	* checks if a direction has a room and an open door.
	* @param direction to be checked.
	* @return true if room in direction exists and its door is open.
	*/
	public boolean checkDirection(String direction) {
		return checkDirectionRoom(direction) && checkDirectionDoor(direction);
	}

	/**
	* checks if any direction has a room and an open door.
	* @return true if any neighbouring rooms with open doors exist.
	*/
	public boolean checkAllDirections(){
		return checkDirection("north") || checkDirection("east") || checkDirection("south") || checkDirection("west");
	}

	/**
	* checks if there is a neighbouring room in given direction.
	* @param direction to check if a room exists.
	* @return true if neighbouring room exists in direction.
	*/
	public boolean checkDirectionRoom(String direction) {
		int door = this.getDirection(direction);

		if (!this.rooms.get(door).equals("X")) {
			return true;
		}
		return false;
	}

	/**
	* checks if there is open door in given direction.
	* Note: there is always a door in any given direction.
	* @param direction to check if an open door exists.
	* @return true if door in direction is open.
	*/
	public boolean checkDirectionDoor(String direction) {
		int door = this.getDirection(direction);

		if (this.doors.get(door).isOpen()) {
			return true;
		}
		return false;
	}

	/**
	* gets the name of the neighbouring room in given direction
	* @param direction from where to get room.
	* @return name of neighbouring room.
	*/
	public String getRoom(String direction) {
		int door = this.getDirection(direction);
		return this.rooms.get(door);
	}

	/**
	* opens door in given direction
	* @param direction of door to be opened.
	*/
	public void openDoor(String direction) {
		int door = this.getDirection(direction);

		if (this.checkDirectionRoom(direction) && !this.checkDirectionDoor(direction)) {
			this.doors.get(door).open();
		}
	}

	/**
	* checks for equality between this and other Object.
	* @param other Object to check equality with.
	* @return true if this and other are equal.
	*/
	@Override
	public boolean equals(Object other) {
	    if (other instanceof Room) {
	        return this.equals((Room) other);
	    } else {
	        return false;
	    }
	}

	/**
	* checks for equality between this and other Room.
	* @param other Room to check equality with.
	* @return true if this and other names are equal.
	*/
	public boolean equals(Room other) {
		return this.name.equals(other.name);
	}
}
