package pollax.world;

import java.util.List;
import java.util.ArrayList;
import pollax.creatures.Creature;
import pollax.interactive.InteractiveObject;
import pollax.interactive.Door;
import pollax.items.Item;

/**
 * @author      Jonathan Franzén, Hampus Widén
 * @version     1.0
 * @since       1.0
 */
public class Room {
	private String name;
	private List<String> rooms;
	private List<Door> doors;
	private List<Creature> creatures;
	private List<Item> items;
	private List<InteractiveObject> interactives;

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
		return this.name + "\n" +
		"N: " + this.rooms.get(0) +
		" E: " + this.rooms.get(1) +
		" S: " + this.rooms.get(2) +
		" W: " + this.rooms.get(3);
	}

	public int getDirection(String direction) {
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
			// Error handling
		}
		return door;
	}

	public boolean checkDirection(String direction) {
		int door = this.getDirection(direction);

		if (!this.rooms.get(door).equals("X")) {
			return this.doors.get(door).isOpen();
		}
		return false;
	}

	public String getRoom(String direction) {
		int door = this.getDirection(direction);
		return this.rooms.get(door);
	}

}
