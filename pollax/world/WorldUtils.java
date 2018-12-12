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

	/**
	* empty constructor for WorldUtils.
	*/
	public WorldUtils() {

	}

	/**
	* checks the size of any HashMap.
	* @param db to check size of.
	* @return size of input HashMap/db.
	*/
	public <A,B> int size(HashMap<A,B> db) {
		return db.size();
	}

	/**
	* Generates a random number 0-100 and compares it to input int.
	* @param chance int to compare with random generated int.
	* @return true if input chance is higher than random one.
	*/
	public boolean random(int chance) {
		int n = this.rand.nextInt(100);
		return n < chance;
	}

	/**
	* gets a random room from the room database.
	* @param db the HashMap (database) to get room from.
	* @return the randomly picked room.
	*/
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

	/**
	* checks for the number of locked doors in Room database.
	* Note: a "locked door" is only locked one-way.
	* @param db the HashMap of rooms the check doors in.
	* @return number of locked doors.
	*/
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
