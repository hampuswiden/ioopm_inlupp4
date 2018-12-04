package pollax.creatures;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import pollax.items.Item;
import pollax.world.Room;

/**
 * @author      Jonathan Franzén, Hampus Widén
 * @version     1.0
 * @since       1.0
 */
public class Avatar extends Creature {
	private String name;
	private Room room;
	private List<Item> items;
	private int hp = 60;
	private List<String> unfinishedCourses;
	private List<String> finishedCourses;

	public Avatar(String name, Room startRoom) {
		this.name = name;
		this.room = startRoom;
	}

	@Override
	public boolean isAvatar() {
		return true;
	}

	public Room currentRoom() {
		return this.room;
	}

	public void go(String direction, HashMap<String, Room> dbRooms) {
		if (this.room.checkDirection(direction)) {
			String newRoom = this.room.getRoom(direction);
			this.room = dbRooms.get(newRoom);
		}
	}
}
