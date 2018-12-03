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

	public Room(String name, String north, String east, String south, String west, String northDoor, String eastDoor, String southDoor, String westDoor) {
		this.name = name;

		// Rooms
		List<String> rooms = new ArrayList<String>();
		rooms.add(north);
		rooms.add(east);
		rooms.add(south);
		rooms.add(west);
		this.rooms = rooms;

		// Doors
		Door nDoor = new Door(northDoor);
		Door eDoor = new Door(eastDoor);
		Door sDoor = new Door(southDoor);
		Door wDoor = new Door(westDoor);

		List<Door> doors = new ArrayList<Door>();
		doors.add(nDoor);
		doors.add(eDoor);
		doors.add(sDoor);
		doors.add(wDoor);
		this.doors = doors;
		
	}
}
