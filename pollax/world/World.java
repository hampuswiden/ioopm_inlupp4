package pollax.world;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;


/**
 * @author      Jonathan Franzén, Hampus Widén
 * @version     1.0
 * @since       1.0
 */
public class World {
	HashMap<String, Room> db = new HashMap<String, Room>();

	public World() {
		Room r1 = new Room("Room 137", "Room 140", "X", "X", "X", "True", "X", "X", "X");
		Room r2 = new Room("Room 140", "X", "X", "Room 137", "X", "X", "X", "True", "X");
		this.db.put("Room 137", r1);
		this.db.put("Room 140", r2);
	}

	public static void main(String[] args) {
		World world = new World();

	}



}
