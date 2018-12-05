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

	public WorldUtils() {

	}

	public <A,B> int size(HashMap<A,B> db) {
		return db.size();
	}

	public Room randomRoom(HashMap<String, Room> db) {
		Random rand = new Random();
		int n = rand.nextInt(db.size()); // this.size() is the maximum and the 1 is our minimum.

		int i = 0;
		for (Object key : db.keySet()) {
    		//System.out.println(key + " = " + db.get(key));
    		if (i == n) {
    			return db.get(key);
    		}
    		i++;
    	}	
		return new Room("FUCKED UP", "X", "X", "X", "X", "X", "X", "X", "X");
	}
}
