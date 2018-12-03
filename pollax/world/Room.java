package pollax.world;

import java.util.List;
import java.util.ArrayList;
import pollax.interactive.*;
import pollax.items.*;

/**
 * @author      Jonathan Franzén, Hampus Widén
 * @version     1.0
 * @since       1.0
 */
public class Room extends World {
	private String name;
	private List<Room> rooms = new ArrayList<Room>();
	private List<Door> doors = new ArrayList<Door>();
	private List<Item> items = new ArrayList<Item>();

	public Room(String name) {
		this.name = name;
	}
}
