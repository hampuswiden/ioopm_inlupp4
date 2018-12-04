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
		return this.name;
	}
}
