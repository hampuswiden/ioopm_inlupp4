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
import pollax.items.*;
import pollax.creatures.*;

/**
 * @author      Jonathan Franzén, Hampus Widén
 * @version     1.0
 * @since       1.0
 */
public class World {
	WorldUtils wu = new WorldUtils();
	HashMap<String, Room> dbRooms;
	HashMap<String, Course> dbCourses;
	HashMap<String, Item> dbItems;
	HashMap<String, Creature> dbCreatures;

	public World() {
		HashMap<String, Room> dbRooms = new HashMap<String, Room>();
		HashMap<String, Item> dbItems = new HashMap<String, Item>();
		HashMap<String, Course> dbCourses = new HashMap<String, Course>();
		HashMap<String, Creature> dbCreatures = new HashMap<String, Creature>();
		this.dbRooms = dbRooms;
		this.dbCourses = dbCourses;
		this.dbItems = dbItems;
		this.dbCreatures = dbCreatures;

		Parser p = new Parser();

		// Generate Rooms
		String pathRooms = "./pollax/text_files/rooms.txt";
		p.parse(pathRooms);
		Room instanceOfRoom = new Room();
		p.generateDB(instanceOfRoom, dbRooms, dbRooms);

		// Generate Books
		String pathBooks = "./pollax/text_files/books.txt";
		p.parse(pathBooks);
		Book instanceOfBook = new Book();
		p.generateDB(instanceOfBook, dbItems, dbItems);

		// Generate Courses
		String pathCourses = "./pollax/text_files/courses.txt";
		p.parse(pathCourses);
		Course instanceOfCourse = new Course();
		p.generateDB(instanceOfCourse, dbCourses, dbItems);

		// Generate Teachers
		String pathTeachers = "./pollax/text_files/teachers.txt";
		p.parse(pathTeachers);
		Teacher instanceOfTeacher = new Teacher();
		p.generateDB(instanceOfTeacher, dbCreatures, dbCourses);

		// Loop through Teachers and put them into random rooms
		for (Creature teacher : dbCreatures.values()) {
    		Room room = this.wu.randomRoom(dbRooms);
    		while (room.hasCreatures()) { // Only 1 teacher per room maximum
    			room = this.wu.randomRoom(dbRooms);
    		}
    		room.addCreature((Teacher) teacher);
		}

		// Generate keys in random rooms
		int noLockedDoors = this.wu.noLockedDoors(dbRooms);
		int noKeys = (int) Math.round(noLockedDoors * 1.5);
		Key dbKey = new Key();
		dbItems.put("key", dbKey);

		for (int keys = noKeys; keys > 0; keys--) {
			Key key = new Key();
			Room randomRoom = this.wu.randomRoom(dbRooms);
			randomRoom.addItem(key);
		}
	}

	public HashMap<String, Room> dbRooms() {
		return this.dbRooms;
	}

	public HashMap<String, Item> dbItems() {
		return this.dbItems;
	}

	public Room randomRoom() {
		return this.wu.randomRoom(this.dbRooms);
	}
}
