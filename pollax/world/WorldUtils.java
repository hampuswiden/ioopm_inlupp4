package pollax.world;

import java.util.Random;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import pollax.utils.Parser;
import pollax.course.Course;
import pollax.items.Book;
import pollax.world.Room;

/**
 * @author      Jonathan Franzén, Hampus Widén
 * @version     1.0
 * @since       1.0
 */
public class WorldUtils {
	private Random rand = new Random();

	public WorldUtils() {

	}

	public <A,B> int size(HashMap<A,B> db) {
		return db.size();
	}

	public boolean random(int chance) {
		int n = this.rand.nextInt(100);
		return n < chance;
	}

	public Room randomRoom(HashMap<String, Room> db) {
		int n = this.rand.nextInt(db.size()); // this.size() is the maximum and 0 is our minimum.

		int i = 0;
		for (Room room : db.values()) {
    		if (i == n) {
    			return room;
    		}
    		i++;
    	}
		return new Room();
	}

	public int noLockedDoors(HashMap<String, Room> db) {
		int noLockedDoors = 0;
		String[] directions = {"north", "east", "south", "west"};
		for (Room room : db.values()) {
			for (int i = 0; i < directions.length; i++) {
				if (!room.checkDirectionDoor(directions[i])) {
					noLockedDoors += 1;
				}
			}
    	}
    	return noLockedDoors;
	}
}
