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
import pollax.world.Room;

/**
 * @author      Jonathan Franzén, Hampus Widén
 * @version     1.0
 * @since       1.0
 */
public class World {
	WorldUtils wu = new WorldUtils();
	HashMap<String, Room> dbRooms;
	HashMap<String, Course> dbCourses;
	HashMap<String, Book> dbBooks;

	public World(HashMap<String, Room> dbRooms, HashMap<String, Course> dbCourses, HashMap<String, Book> dbBooks) {
		/*
		Room r1 = new Room("Room 137", "Room 140", "X", "X", "X", "True", "X", "X", "X");
		Room r2 = new Room("Room 140", "X", "X", "Room 137", "X", "X", "X", "True", "X");
		this.db.put("Room 137", r1);
		this.db.put("Room 140", r2);
		*/
		this.dbRooms = dbRooms;
		this.dbCourses = dbCourses;
		this.dbBooks = dbBooks;

		Parser p = new Parser();

		// Generate Rooms
		String pathRooms = "./pollax/text_files/rooms.txt";
		p.parse(pathRooms);
		Room instanceOfRoom = new Room();
		p.generateDB(instanceOfRoom, dbRooms);

		// Generate Courses
		String pathCourses = "./pollax/text_files/courses.txt";
		p.parse(pathCourses);
		Course instanceOfCourse = new Course();
		p.generateDB(instanceOfCourse, dbCourses);

		// Generate Books
		String pathBooks = "./pollax/text_files/books.txt";
		p.parse(pathBooks);
		Book instanceOfBook = new Book();
		p.generateDB(instanceOfBook, dbBooks);

		// Generate Keys
		int noKeys = 5;
		for (int keys = noKeys; keys > 0; keys--) {
			Key key = new Key();
			Room randomRoom = this.wu.randomRoom(dbRooms);
			randomRoom.addItem(key);
		}
	}

	public static void main(String[] args) {
		//World world = new World();

	}

	public int size() {
		return this.dbRooms.size();
	}

	public Room randomRoom() {
		return this.wu.randomRoom(this.dbRooms);
	}
}
