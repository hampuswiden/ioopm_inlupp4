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
import java.lang.reflect.Array;

/**
 * @author      Jonathan Franzén, Hampus Widén
 * @version     1.0
 * @since       1.0
 */
public class World {
	private WorldUtils wu = new WorldUtils();
	private HashMap<String, Room> dbRooms;
	private HashMap<String, Course> dbCourses;
	private HashMap<String, Item> dbItems;
	private HashMap<String, Creature> dbCreatures;

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
    		while (room.hasTeacher()) { // Only 1 teacher per room maximum
    			room = this.wu.randomRoom(dbRooms);
    		}
    		room.addCreature((Teacher) teacher);
		}

		// Generate Students
		String pathStudents = "./pollax/text_files/students.txt";
		p.parse(pathStudents);
		Student instanceOfStudent = new Student();
		p.generateDB(instanceOfStudent, dbCreatures, dbCourses);

		// Loop through Students and put them into random rooms
		for (Creature cStudent : dbCreatures.values()) {
    		Room room = this.wu.randomRoom(dbRooms);
				if(cStudent instanceof Student){
					Student student = (Student) cStudent;
					student.assignRoom(room);
    			room.addCreature((Student) student);
				}
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

	public void moveStudents() {
		int studentGoChance = 50;
		int direction;
		String directions[] = {"north", "east", "south", "west"};
		boolean moved;
		boolean moveChance;
		for (Creature cStudent : this.dbCreatures().values()) {
				//Room room = this.wu.randomRoom(dbRooms);
				if((cStudent instanceof Student) && !(cStudent instanceof Avatar)){
					Student student = (Student) cStudent;
					moveChance = this.random(studentGoChance);
					if (moveChance && student.getRoom().checkAllDirections()){
						moved = false;
						while (moved == false) {
							direction = (int) (Math.random()*4);
							if(student.getRoom().checkDirection((String) Array.get(directions, direction)) == true) {
								student.getRoom().removeCreature(student);
								student.go((String) Array.get(directions, direction), this);
								student.getRoom().addCreature((Creature) student);
								moved = true;
							}
						}
					}
				}
		}
	}

	public HashMap<String, Room> dbRooms() {
		return this.dbRooms;
	}

	public HashMap<String, Item> dbItems() {
		return this.dbItems;
	}

	public HashMap<String, Course> dbCourses() {
		return this.dbCourses;
	}

	public HashMap<String, Creature> dbCreatures() {
		return this.dbCreatures;
	}

	public Room randomRoom() {
		return this.wu.randomRoom(this.dbRooms);
	}

	public boolean random(int chance) {
		return this.wu.random(chance);
	}
}
