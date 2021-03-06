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
	/**
	* instance of world utils, used for util functions.
	*/
	private WorldUtils wu = new WorldUtils();

	/**
	* HashMap for rooms
	*/
	private HashMap<String, Room> dbRooms;

	/**
	* HashMap for courses
	*/
	private HashMap<String, Course> dbCourses;

	/**
	* HashMap for items
	*/
	private HashMap<String, Item> dbItems;

	/**
	* HashMap for creatures
	*/
	private HashMap<String, Creature> dbCreatures;

	/**
	* constructor for World
	* Info: generates all databases and creates the layout for world and the objects it holds
	*/
	public World(String text_path) {
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
		String pathRooms = text_path + "rooms.txt";
		p.parse(pathRooms);
		Room instanceOfRoom = new Room();
		p.generateDB(instanceOfRoom, dbRooms, dbRooms);

		// Generate Books
		String pathBooks = text_path + "books.txt";
		p.parse(pathBooks);
		Book instanceOfBook = new Book();
		p.generateDB(instanceOfBook, dbItems, dbItems);

		for (Item book : dbItems.values()) {
    		Room room = this.wu.randomRoom(dbRooms);
    		room.addItem((Book) book);
		}


		// Generate Courses
		String pathCourses = text_path + "courses.txt";
		p.parse(pathCourses);
		Course instanceOfCourse = new Course();
		p.generateDB(instanceOfCourse, dbCourses, dbItems);

		// Assign the corresponding course to all books
		for (Course course : dbCourses.values()) {
    		Book courseBook = course.getCourseBook();
				courseBook.assignCourse(course);
		}

		// Generate Teachers
		String pathTeachers = text_path + "teachers.txt";
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
		String pathStudents = text_path + "students.txt";
		p.parse(pathStudents);
		Student instanceOfStudent = new Student();
		p.generateDB(instanceOfStudent, dbCreatures, dbCourses);

		// Loop through Students and put them into random rooms
		for (Creature cStudent : dbCreatures.values()) {

				if(cStudent instanceof Student){
					Room room = this.wu.randomRoom(dbRooms);
					Student student = (Student) cStudent;
					student.assignRoom(room);
    			room.addCreature((Student) student);

					boolean hasBook = this.wu.random(50);
					List <Course> studentCourse = student.getFinishedCourses();
					if(hasBook && studentCourse.size() == 1){
						student.addItem(studentCourse.get(0).getCourseBook());
					}
				}
		}

		// Generate Sfinxen in a random room
		Sfinx sfinxen = new Sfinx();
		Room sfinxRoom = this.wu.randomRoom(dbRooms);
		sfinxRoom.addCreature(sfinxen);
		dbCreatures.put("sfinxen", sfinxen);

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

	/**
	* chance to move any Student in the Student database to a random neighbouring room.
	*/
	public void moveStudents() {
		int studentGoChance = 10;
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

	/**
	* gets HashMap for rooms and their names, instance variable.
	* @return HashMap for rooms.
	*/
	public HashMap<String, Room> dbRooms() {
		return this.dbRooms;
	}

	/**
	* gets HashMap for items and their names, instance variable.
	* @return HashMap for items.
	*/
	public HashMap<String, Item> dbItems() {
		return this.dbItems;
	}

	/**
	* gets HashMap for courses and their names, instance variable.
	* @return HashMap for courses.
	*/
	public HashMap<String, Course> dbCourses() {
		return this.dbCourses;
	}

	/**
	* gets HashMap for creatures and their names, instance variable.
	* @return HashMap for creatures.
	*/
	public HashMap<String, Creature> dbCreatures() {
		return this.dbCreatures;
	}

	/**
	* gets a random room from the room database.
	* @return randomly picked room.
	*/
	public Room randomRoom() {
		return this.wu.randomRoom(this.dbRooms);
	}

	/**
	* generates a boolean stating success or not, based on the a roll 0-100 and the input int.
	* @param chance a chance between 0-100 for success.
	* @return true if the input chance is higher than the random roll.
	*/
	public boolean random(int chance) {
		return this.wu.random(chance);
	}
}
